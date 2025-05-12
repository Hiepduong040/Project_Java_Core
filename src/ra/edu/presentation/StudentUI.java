package ra.edu.presentation;

import ra.edu.ManiApplication;
import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Student;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.enrollment.EnrollmentServiceImp;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.List;
import java.util.Scanner;

import static ra.edu.presentation.AdminUI.*;

public class StudentUI {
    private static CourseServiceImp courseServiceImp;
    private static EnrollmentServiceImp enrollmentServiceImp;
    private static StudentServiceImp studentServiceImp;

    public StudentUI() {
        courseServiceImp = new CourseServiceImp();
        enrollmentServiceImp = new EnrollmentServiceImp();
        studentServiceImp = new StudentServiceImp();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentUI studentUI = new StudentUI();
        boolean exit = false;
        do {
            System.out.println(CYAN + "========================== MENU HỌC VIÊN ============================" + RESET);
            System.out.println(CYAN + "╔════════════╦══════════════════════════════════════════════════════╗");
            System.out.printf("║ %-10s ║ %-52s ║\n", "Lựa chọn", "Mô tả");
            System.out.println("╠════════════╬══════════════════════════════════════════════════════╣");

            System.out.printf("║ %-10s ║ %-52s ║\n", "1", "Xem danh sách khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "2", "Tìm kiếm khóa học theo tên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "3", "Đăng ký khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "4", "Xem danh sách khóa học đã đăng ký");
            System.out.printf("║ %-10s ║ %-52s ║\n", "5", "Hủy đăng ký khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "6", "Cập nhật mật khẩu");
            System.out.printf("║ %-10s ║ %-52s ║\n", "7", "Đăng xuất");
            System.out.println("╚════════════╩══════════════════════════════════════════════════════╝" + RESET);

            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    viewCourseList(scanner);
                    break;
                case 2:
                    searchCourseByName(scanner);
                    break;
                case 3:
                    registerCourse(scanner);
                    break;
                case 4:
                    viewRegisteredCourses(scanner);
                    break;
                case 5:
                    cancelRegistration(scanner);
                    break;
                case 6:
                    updatePassword(scanner);
                    break;
                case 7:
                    exit = true;
                    ManiApplication.currentUser = null;
                    System.out.println(YELLOW + "Đã đăng xuất!" + RESET);
                    break;
                default:
                    System.out.println(RED + "Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 7!" + RESET);
            }
        } while (!exit);
    }

    private static void viewCourseList(Scanner scanner) {
        courseServiceImp.listCoursesPagination(scanner);
    }

    private static void searchCourseByName(Scanner scanner) {
        String search = Validator.validateString(scanner, 0, 100, "Nhập tên khóa học muốn tìm: ", "Tên khóa học");
        courseServiceImp.finCourseByNamePagination(scanner, search);
    }

    private static void registerCourse(Scanner scanner) {
        Student currentStudent = (Student) ManiApplication.currentUser;
        if (currentStudent == null) {
            System.out.println(RED + "Vui lòng đăng nhập để đăng ký khóa học!" + RESET);
            return;
        }

        System.out.println(GREEN + "Danh sách khóa học:" + RESET);
        courseServiceImp.listCoursesPagination(scanner);
        int courseId = Validator.validateInt(scanner, 1, Integer.MAX_VALUE, "Nhập mã khóa học muốn đăng ký: ", "Mã khóa học");
        Course course = courseServiceImp.findCourseById(courseId);
        if (course == null) {
            System.out.println(RED + "Không tìm thấy khóa học!" + RESET);
            return;
        }

        List<Enrollment> enrollments = enrollmentServiceImp.getEnrollmentsByStudent(currentStudent.getId());
        boolean alreadyRegistered = enrollments.stream().anyMatch(e -> e.getCourseId() == courseId && !e.getStatus().equals("CANCER"));
        if (alreadyRegistered) {
            System.out.println(RED + "Bạn đã đăng ký khóa học này!" + RESET);
            return;
        }

        Enrollment enrollment = new Enrollment();
        enrollment.setStudentId(currentStudent.getId());
        enrollment.setCourseId(courseId);
        if (enrollmentServiceImp.registerCourse(enrollment)) {
            System.out.println(GREEN + "Đăng ký khóa học thành công! Đang chờ xác nhận." + RESET);
        } else {
            System.out.println(RED + "Đăng ký khóa học thất bại!" + RESET);
        }
    }

    private static void viewRegisteredCourses(Scanner scanner) {
        Student currentStudent = (Student) ManiApplication.currentUser;
        if (currentStudent == null) {
            System.out.println(RED + "Vui lòng đăng nhập để xem danh sách khóa học đã đăng ký!" + RESET);
            return;
        }

        List<Enrollment> enrollments = enrollmentServiceImp.getEnrollmentsByStudent(currentStudent.getId());
        if (enrollments.isEmpty()) {
            System.out.println(RED + "Bạn chưa đăng ký khóa học nào!" + RESET);
            return;
        }

        System.out.println(GREEN + "Danh sách khóa học đã đăng ký:" + RESET);
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-25s | %-15s | %-25s | %-15s\n",
                "Mã ĐK", "Tên khóa học", "Trạng thái", "Ngày đăng ký", "Giảng viên");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Enrollment enrollment : enrollments) {
            Course course = courseServiceImp.findCourseById(enrollment.getCourseId());
            if (course != null) {
                System.out.printf("%-10d | %-25s | %-15s | %-25s | %-15s\n",
                        enrollment.getId(), course.getName(), enrollment.getStatus(),
                        enrollment.getRegisteredAt(), course.getInstructor());
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------");
    }

    private static void cancelRegistration(Scanner scanner) {
        Student currentStudent = (Student) ManiApplication.currentUser;
        if (currentStudent == null) {
            System.out.println(RED + "Vui lòng đăng nhập để hủy đăng ký!" + RESET);
            return;
        }

        List<Enrollment> enrollments = enrollmentServiceImp.getEnrollmentsByStudent(currentStudent.getId());
        List<Enrollment> waitingEnrollments = enrollments.stream()
                .filter(e -> e.getStatus().equals("WAITING"))
                .toList();

        if (waitingEnrollments.isEmpty()) {
            System.out.println(RED + "Bạn không có đăng ký nào đang chờ xác nhận!" + RESET);
            return;
        }

        System.out.println(GREEN + "Danh sách khóa học đang chờ xác nhận:" + RESET);
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-10s | %-25s | %-15s | %-25s | %-15s\n",
                "Mã ĐK", "Tên khóa học", "Trạng thái", "Ngày đăng ký", "Giảng viên");
        System.out.println("---------------------------------------------------------------------------------------------");
        for (Enrollment enrollment : waitingEnrollments) {
            Course course = courseServiceImp.findCourseById(enrollment.getCourseId());
            if (course != null) {
                System.out.printf("%-10d | %-25s | %-15s | %-25s | %-15s\n",
                        enrollment.getId(), course.getName(), enrollment.getStatus(),
                        enrollment.getRegisteredAt(), course.getInstructor());
            }
        }
        System.out.println("---------------------------------------------------------------------------------------------");

        int enrollmentId = Validator.validateInt(scanner, 1, Integer.MAX_VALUE, "Nhập mã đăng ký muốn hủy: ", "Mã đăng ký");
        Enrollment enrollment = waitingEnrollments.stream()
                .filter(e -> e.getId() == enrollmentId)
                .findFirst()
                .orElse(null);

        if (enrollment == null) {
            System.out.println(RED + "Không tìm thấy đăng ký này trong danh sách đang chờ!" + RESET);
            return;
        }

        System.out.println("Bạn có chắc chắn muốn hủy đăng ký này? (y/n)");
        char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
        if (choice == 'y') {
            if (enrollmentServiceImp.cancelEnrollment(enrollmentId)) {
                System.out.println(GREEN + "Hủy đăng ký thành công!" + RESET);
            } else {
                System.out.println(RED + "Hủy đăng ký thất bại!" + RESET);
            }
        } else {
            System.out.println(YELLOW + "Đã hủy thao tác!" + RESET);
        }
    }

    private static void updatePassword(Scanner scanner) {
        Student currentStudent = (Student) ManiApplication.currentUser;
        if (currentStudent == null) {
            System.out.println(RED + "Vui lòng đăng nhập để cập nhật mật khẩu!" + RESET);
            return;
        }

        String email = Validator.validateString(scanner, 0, 255, "Nhập email tài khoản hiện tại: ", "Email");
        Student dbStudent = studentServiceImp.findByEmail(email);
        if (dbStudent == null || !dbStudent.getEmail().equals(currentStudent.getEmail())) {
            System.out.println(RED + "Email không đúng hoặc không khớp với tài khoản hiện tại!" + RESET);
            return;
        }

        String oldPassword = Validator.validateString(scanner, 6, 255, "Nhập mật khẩu hiện tại: ", "Mật khẩu");
        if (!oldPassword.equals(dbStudent.getPassword())) {
            System.out.println(RED + "Mật khẩu hiện tại không đúng!" + RESET);
            return;
        }

        String newPassword = Validator.validateString(scanner, 6, 255, "Nhập mật khẩu mới: ", "Mật khẩu mới");
        String confirmPassword = Validator.validateString(scanner, 6, 255, "Xác nhận mật khẩu mới: ", "Xác nhận mật khẩu");
        if (!newPassword.equals(confirmPassword)) {
            System.out.println(RED + "Mật khẩu xác nhận không khớp!" + RESET);
            return;
        }

        if (studentServiceImp.updatePassword(currentStudent.getId(), newPassword)) {
            currentStudent.setPassword(newPassword);
            System.out.println(GREEN + "Cập nhật mật khẩu thành công!" + RESET);
        } else {
            System.out.println(RED + "Cập nhật mật khẩu thất bại!" + RESET);
        }
    }
}