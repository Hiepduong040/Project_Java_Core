
-- Tạo cơ sở dữ liệu
CREATE DATABASE qly_khoa_hoc;
USE qly_khoa_hoc;

-- Tạo bảng Admin
CREATE TABLE Admin
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50)  NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Tạo bảng Student
CREATE TABLE Student
(
    id        INT PRIMARY KEY AUTO_INCREMENT,
    name      VARCHAR(100) NOT NULL,
    dob       DATE         NOT NULL,
    email     VARCHAR(100) NOT NULL UNIQUE,
    sex       BIT          NOT NULL,
    phone     VARCHAR(20),
    password  VARCHAR(255) NOT NULL,
    create_at DATE DEFAULT (CURDATE())
);

-- Tạo bảng Course
CREATE TABLE Course
(
    id         INT PRIMARY KEY AUTO_INCREMENT,
    name       VARCHAR(100) NOT NULL,
    duration   INT          NOT NULL,
    instructor VARCHAR(100) NOT NULL,
    create_at  DATE DEFAULT (CURDATE())
);

-- Tạo bảng Enrollment
CREATE TABLE Enrollment
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    student_id    INT NOT NULL,
    course_id     INT NOT NULL,
    registered_at DATETIME                                        DEFAULT CURRENT_TIMESTAMP,
    status        ENUM ('WAITING', 'DENIED', 'CANCER', 'CONFIRM') DEFAULT 'WAITING',
    FOREIGN KEY (student_id) REFERENCES Student (id),
    FOREIGN KEY (course_id) REFERENCES Course (id)
);

-- Stored procedure cho đăng nhập
DELIMITER //
CREATE PROCEDURE loginByAccount(
    username_in VARCHAR(100),
    password_in VARCHAR(255)
)
BEGIN
    -- Kiểm tra Admin
    IF EXISTS (SELECT * FROM Admin WHERE username = username_in AND password = password_in) THEN
        SELECT id, username, password, 'ADMIN' AS role
        FROM Admin
        WHERE username = username_in AND password = password_in;
        -- Kiểm tra Student
    ELSEIF EXISTS (SELECT * FROM Student WHERE email = username_in AND password = password_in) THEN
        SELECT id,
               name,
               dob,
               email,
               sex,
               phone,
               password,
               create_at,
               'STUDENT' AS role
        FROM Student
        WHERE email = username_in
          AND password = password_in;
    ELSE
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tài khoản hoặc mật khẩu không đúng';
    END IF;
END //
DELIMITER ;

-- Stored procedures cho Course
DELIMITER //
CREATE PROCEDURE findCourseByName(name_in VARCHAR(100))
BEGIN
    SELECT * FROM Course WHERE name = name_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findCourseById(id_in INT)
BEGIN
    SELECT * FROM Course WHERE id = id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE listCourse(
    limit_in INT,
    page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page - 1) * limit_in;
    SELECT *
    FROM Course
    LIMIT limit_in OFFSET offset_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE totalCourse()
BEGIN
    SELECT COUNT(id) FROM Course;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE addCourse(
    name_in VARCHAR(100),
    duration_in INT,
    instructor_in VARCHAR(100)
)
BEGIN
    INSERT INTO Course (name, duration, instructor)
    VALUES (name_in, duration_in, instructor_in);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE listAllCoursesWithStudentCount(
    IN p_pageSize INT,
    IN p_pageNumber INT
)
BEGIN
    DECLARE v_offset INT;
    SET v_offset = (p_pageNumber - 1) * p_pageSize;

    SELECT c.id, c.name, COUNT(e.student_id) AS student_count
    FROM Course c
             LEFT JOIN Enrollment e ON c.id = e.course_id AND e.status = 'CONFIRM'
    GROUP BY c.id, c.name
    ORDER BY c.id ASC
    LIMIT p_pageSize OFFSET v_offset;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE countTotalCourses()
BEGIN
    SELECT COUNT(*) AS total FROM Course;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE updateCourse(
    IN course_id_in INT,
    IN new_name_in VARCHAR(255),
    IN new_duration_in INT,
    IN new_instructor_in VARCHAR(255)
)
BEGIN
    -- Kiểm tra tên khóa học trùng lặp, loại trừ bản ghi hiện tại
    IF EXISTS (SELECT 1 FROM Course WHERE name = new_name_in AND id != course_id_in) THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Tên khóa học đã tồn tại';
    END IF;

    -- Cập nhật khóa học
    UPDATE Course
    SET
        name = new_name_in,
        duration = new_duration_in,
        instructor = new_instructor_in
    WHERE id = course_id_in;

    -- Kiểm tra xem có bản ghi nào được cập nhật không
    IF ROW_COUNT() = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Không tìm thấy khóa học để cập nhật';
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delCourse(id_in INT)
BEGIN
    DELETE FROM Course WHERE id = id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findCourseByNamePagination(
    name_in VARCHAR(100),
    limit_in INT,
    page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page - 1) * limit_in;
    SELECT *
    FROM Course
    WHERE name LIKE CONCAT('%', name_in, '%')
    LIMIT limit_in OFFSET offset_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sortByName(
    order_in CHAR(5),
    limit_in INT,
    page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page - 1) * limit_in;
    IF order_in = 'desc' THEN
        SELECT *
        FROM Course
        ORDER BY name DESC
        LIMIT limit_in OFFSET offset_in;
    ELSE
        SELECT *
        FROM Course
        ORDER BY name ASC
        LIMIT limit_in OFFSET offset_in;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sortByID(
    order_in CHAR(5),
    limit_in INT,
    page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page - 1) * limit_in;
    IF order_in = 'desc' THEN
        SELECT *
        FROM Course
        ORDER BY id DESC
        LIMIT limit_in OFFSET offset_in;
    ELSE
        SELECT *
        FROM Course
        ORDER BY id ASC
        LIMIT limit_in OFFSET offset_in;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findAllCourses()
BEGIN
    SELECT * FROM Course;
END //
DELIMITER ;

-- Stored procedures cho Student
DELIMITER //
CREATE PROCEDURE findStudentByEmail(email_in VARCHAR(100))
BEGIN
    SELECT * FROM Student WHERE email = email_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findStudentById(id_in INT)
BEGIN
    SELECT * FROM Student WHERE id = id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE listStudent(
    limit_in INT,
    page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page - 1) * limit_in;
    SELECT *
    FROM Student
    LIMIT limit_in OFFSET offset_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE totalStudent()
BEGIN
    SELECT COUNT(id) FROM Student;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE addStudent(
    name_in VARCHAR(100),
    dob_in DATE,
    email_in VARCHAR(100),
    sex_in BIT,
    phone_in VARCHAR(20),
    password_in VARCHAR(255)
)
BEGIN
    INSERT INTO Student (name, dob, email, sex, phone, password)
    VALUES (name_in, dob_in, email_in, sex_in, phone_in, password_in);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE updateStudent(
    id_in INT,
    name_in VARCHAR(100),
    dob_in DATE,
    email_in VARCHAR(100),
    sex_in BIT,
    phone_in VARCHAR(20)
)
BEGIN
    UPDATE Student
    SET name  = name_in,
        dob   = dob_in,
        email = email_in,
        sex   = sex_in,
        phone = phone_in
    WHERE id = id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE delStudent(id_in INT)
BEGIN
    DELETE FROM Student WHERE id = id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findStudentByNameOrEmailOrId(
    search_in VARCHAR(100),
    limit_in INT,
    page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page - 1) * limit_in;
    SELECT *
    FROM Student
    WHERE name LIKE CONCAT('%', search_in, '%')
       OR email LIKE CONCAT('%', search_in, '%')
       OR id = CAST(search_in AS SIGNED)
    LIMIT limit_in OFFSET offset_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sortStudentByName(
    order_in CHAR(5),
    limit_in INT,
    page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page - 1) * limit_in;
    IF order_in = 'desc' THEN
        SELECT *
        FROM Student
        ORDER BY name DESC
        LIMIT limit_in OFFSET offset_in;
    ELSE
        SELECT *
        FROM Student
        ORDER BY name ASC
        LIMIT limit_in OFFSET offset_in;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE sortStudentById(
    order_in CHAR(5),
    limit_in INT,
    page INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page - 1) * limit_in;
    IF order_in = 'desc' THEN
        SELECT *
        FROM Student
        ORDER BY id DESC
        LIMIT limit_in OFFSET offset_in;
    ELSE
        SELECT *
        FROM Student
        ORDER BY id ASC
        LIMIT limit_in OFFSET offset_in;
    END IF;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findAllStudents()
BEGIN
    SELECT * FROM Student;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE updateStudentPassword(
    student_id_in INT,
    new_password_in VARCHAR(255)
)
BEGIN
    UPDATE Student
    SET password = new_password_in
    WHERE id = student_id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE addEnrollment(
    student_id_in INT,
    course_id_in INT
)
BEGIN
    INSERT INTO Enrollment (student_id, course_id)
    VALUES (student_id_in, course_id_in);
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE cancelEnrollment(
    enrollment_id_in INT
)
BEGIN
    UPDATE Enrollment
    SET status = 'CANCER'
    WHERE id = enrollment_id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findEnrollmentsByStudentId(
    student_id_in INT
)
BEGIN
    SELECT * FROM Enrollment WHERE student_id = student_id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE totalEnrollmentsByStudent(
    student_id_in INT
)
BEGIN
    SELECT COUNT(id) FROM Enrollment WHERE student_id = student_id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE listEnrollmentsByCourse(
    IN course_id_in INT,
    IN limit_in INT,
    IN page_in INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page_in - 1) * limit_in;
    SELECT e.id, e.student_id, s.name AS student_name, e.course_id, c.name AS course_name, e.status, e.registered_at
    FROM Enrollment e
             JOIN Student s ON e.student_id = s.id
             JOIN Course c ON e.course_id = c.id
    WHERE e.course_id = course_id_in
    LIMIT limit_in OFFSET offset_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE countEnrollmentsByCourse(
    IN course_id_in INT
)
BEGIN
    SELECT COUNT(*) AS total
    FROM Enrollment
    WHERE course_id = course_id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE approveEnrollment(
    IN enrollment_id_in INT,
    IN new_status VARCHAR(10)
)
BEGIN
    UPDATE Enrollment
    SET status = new_status
    WHERE id = enrollment_id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findAllEnrollments()
BEGIN
    SELECT * FROM Enrollment;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE findEnrollmentById(
    IN enrollment_id_in INT
)
BEGIN
    SELECT e.id, e.student_id, s.name AS student_name, e.course_id, c.name AS course_name, e.status, e.registered_at
    FROM Enrollment e
             JOIN Student s ON e.student_id = s.id
             JOIN Course c ON e.course_id = c.id
    WHERE e.id = enrollment_id_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE listWaitingEnrollments(
    IN limit_in INT,
    IN page_in INT
)
BEGIN
    DECLARE offset_in INT;
    SET offset_in = (page_in - 1) * limit_in;
    SELECT e.id, e.student_id, s.name AS student_name, e.course_id, c.name AS course_name, e.status, e.registered_at
    FROM Enrollment e
             JOIN Student s ON e.student_id = s.id
             JOIN Course c ON e.course_id = c.id
    WHERE e.status = 'WAITING'
    LIMIT limit_in OFFSET offset_in;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE countWaitingEnrollments()
BEGIN
    SELECT COUNT(*) AS total
    FROM Enrollment
    WHERE status = 'WAITING';
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE countCoursesAndStudents()
BEGIN
    DECLARE total_courses INT;
    DECLARE total_students INT;

    SELECT COUNT(*) INTO total_courses FROM Course;
    SELECT COUNT(DISTINCT student_id)
    INTO total_students
    FROM Enrollment
    WHERE status = 'CONFIRM';

    SELECT total_courses AS TotalCourses, total_students AS TotalStudents;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE countStudentsByCourse(IN course_id_in INT)
BEGIN
    SELECT c.id, c.name, COUNT(e.student_id) AS student_count
    FROM Course c
             LEFT JOIN Enrollment e ON c.id = e.course_id AND e.status = 'CONFIRM'
    WHERE c.id = course_id_in
    GROUP BY c.id, c.name;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE topFiveCoursesByStudents()
BEGIN
    SELECT c.id, c.name, COUNT(e.student_id) AS student_count
    FROM Course c
             LEFT JOIN Enrollment e ON c.id = e.course_id AND e.status = 'CONFIRM'
    GROUP BY c.id, c.name
    ORDER BY student_count DESC
    LIMIT 5;
END //
DELIMITER ;

DELIMITER //
CREATE PROCEDURE listCoursesWithMoreThanTenStudents()
BEGIN
    SELECT c.id, c.name, COUNT(e.student_id) AS student_count
    FROM Course c
             LEFT JOIN Enrollment e ON c.id = e.course_id AND e.status = 'CONFIRM'
    GROUP BY c.id, c.name
    HAVING COUNT(e.student_id) > 10;
END //
DELIMITER ;

-- Chèn dữ liệu mẫu
INSERT INTO Admin (username, password)
VALUES ('admin@gmail.com', 'admin123');

INSERT INTO Student (name, dob, email, sex, phone, password)
VALUES
    ('Nguyễn Văn A', '2000-01-01', 'student1@gmail.com', 1, '0981234567', 'student123'),
    ('Trần Thị B', '1999-05-15', 'student2@gmail.com', 0, '0972345678', 'student123'),
    ('Lê Văn C', '2001-03-10', 'student3@gmail.com', 1, '0963456789', 'student123'),
    ('Phạm Thị D', '2000-07-20', 'student4@gmail.com', 0, '0934567890', 'student123'),
    ('Hoàng Văn E', '1998-11-30', 'student5@gmail.com', 1, '0915678901', 'student123'),
    ('Nguyễn Thanh Huy', '2001-02-20', 'student6@gmail.com', 1, '0901000015', 'pass123'),
    ('Trần Thu Hà', '2002-03-15', 'student7@gmail.com', 0, '0901000016', 'pass123'),
    ('Phạm Văn Dũng', '2000-06-25', 'student8@gmail.com', 1, '0901000017', 'pass123'),
    ('Lê Thị Mai', '2001-09-12', 'student9@gmail.com', 0, '0901000018', 'pass123'),
    ('Hoàng Văn Khoa', '2003-01-11', 'student10@gmail.com', 1, '0901000019', 'pass123'),
    ('Vũ Minh Hoàng', '2001-11-01', 'student11@gmail.com', 1, '0901000020', 'pass123'),
    ('Lê Văn Long', '2002-10-22', 'student12@gmail.com', 1, '0901000021', 'pass123'),
    ('Nguyễn Thị Duyên', '2000-07-17', 'student13@gmail.com', 0, '0901000022', 'pass123'),
    ('Phạm Anh Tuấn', '2002-04-30', 'student14@gmail.com', 1, '0901000023', 'pass123'),
    ('Đỗ Văn Hòa', '2001-12-19', 'student15@gmail.com', 1, '0901000024', 'pass123'),
    ('Trần Thị Thủy', '2003-05-01', 'student16@gmail.com', 0, '0901000025', 'pass123'),
    ('Lê Hồng Quân', '2000-02-08', 'student17@gmail.com', 1, '0901000026', 'pass123'),
    ('Nguyễn Minh Châu', '2002-08-23', 'student18@gmail.com', 1, '0901000027', 'pass123'),
    ('Phạm Thị Hằng', '2001-03-16', 'student19@gmail.com', 0, '0901000028', 'pass123'),
    ('Đỗ Văn Thành', '1999-12-01', 'student20@gmail.com', 1, '0901000029', 'pass123');

INSERT INTO Course (name, duration, instructor)
VALUES
    ('Cybersecurity Essentials', 40, 'Nguyễn Văn A'),
    ('Web Development Bootcamp', 90, 'Nguyễn Văn B'),
    ('Mobile App Development', 80, 'Nguyễn Văn C'),
    ('Machine Learning Intro', 60, 'Nguyễn Văn D'),
    ('Introduction to Python', 45, 'Nguyễn Văn E'),
    ('Java for Beginners', 75, 'Nguyễn Văn F'),
    ('Data Science Fundamentals', 60, 'Nguyễn Văn G'),
    ('UI/UX Design Principles', 55, 'Nguyễn Văn H'),
    ('Cloud Computing Basics', 50, 'Nguyễn Văn I'),
    ('Database Design', 30, 'Nguyễn Văn J'),
    ('Advanced Java Programming', 90, 'Nguyễn Văn Hào'),
    ('ReactJS Masterclass', 80, 'Trần Thị Hoa'),
    ('Data Structures & Algorithms', 70, 'Lê Minh Tuấn'),
    ('Kỹ thuật lập trình C', 60, 'Phạm Quốc Cường'),
    ('Spring Boot Web Development', 75, 'Đỗ Thị Yến'),
    ('Python for Data Science', 85, 'Võ Thành Tài');

INSERT INTO Enrollment (student_id, course_id, status)
VALUES
    (1, 1, 'CONFIRM'), (1, 2, 'WAITING'), (2, 3, 'CONFIRM'), (3, 4, 'DENIED'),
    (4, 5, 'CONFIRM'), (5, 6, 'WAITING'),
    (6, 11, 'CONFIRM'), (7, 11, 'CONFIRM'), (8, 11, 'CONFIRM'), (9, 11, 'CONFIRM'), (10, 11, 'CONFIRM'),
    (11, 11, 'CONFIRM'), (12, 11, 'CONFIRM'), (13, 11, 'CONFIRM'), (14, 11, 'CONFIRM'), (15, 11, 'CONFIRM'),
    (16, 11, 'CONFIRM'),
    (6, 12, 'CONFIRM'), (7, 12, 'CONFIRM'), (8, 12, 'CONFIRM'), (9, 12, 'CONFIRM'), (10, 12, 'CONFIRM'),
    (6, 13, 'CONFIRM'), (7, 13, 'CONFIRM'), (8, 13, 'CONFIRM'), (9, 13, 'CONFIRM'), (10, 13, 'CONFIRM'),
    (6, 14, 'CONFIRM'), (7, 14, 'CONFIRM'), (8, 14, 'CONFIRM'), (9, 14, 'CONFIRM'),
    (6, 15, 'CONFIRM'), (7, 16, 'CONFIRM');
