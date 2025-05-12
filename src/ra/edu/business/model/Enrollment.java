package ra.edu.business.model;

import java.time.LocalDateTime;

public class Enrollment {
    private int id;
    private int studentId;
    private String studentName;
    private int courseId;
    private String courseName;
    private LocalDateTime registeredAt;
    private String status;

    public Enrollment() {
        this.registeredAt = LocalDateTime.now();
        this.status = "WAITING";
    }

    public Enrollment(int id, int studentId, String studentName, int courseId, String courseName, LocalDateTime registeredAt, String status) {
        this.id = id;
        this.studentId = studentId;
        this.studentName = studentName;
        this.courseId = courseId;
        this.courseName = courseName;
        this.registeredAt = registeredAt;
        this.status = status;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(LocalDateTime registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ID Đăng ký: " + id + " - Tên sinh viên: " + studentName + " - Khóa học: " + courseName +
                " - Ngày đăng ký: " + registeredAt + " - Trạng thái: " + status;
    }
}