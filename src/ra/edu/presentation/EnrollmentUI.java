//package ra.edu.presentation;
//
//import ra.edu.business.model.Course;
//import ra.edu.business.model.Enrollment;
//import ra.edu.business.model.Pagination;
//import ra.edu.business.service.course.CourseServiceImp;
//import ra.edu.business.service.enrollment.EnrollmentServiceImp;
//import ra.edu.validate.Validator;
//import ra.edu.validate.ValidatorChoice;
//
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Scanner;
//
//public class EnrollmentUI {
//    private static CourseServiceImp courseServiceImp;
//    private static EnrollmentServiceImp enrollmentServiceImp;
//
//    // Define color constants
//    private static final String BLUE = "\u001B[34m";
//    private static final String CYAN = "\u001B[36m";
//    private static final String WHITE = "\u001B[37m";
//    private static final String RED = "\u001B[31m";
//    private static final String YELLOW = "\u001B[33m";
//    private static final String GREEN = "\u001B[32m";
//    private static final String RESET = "\u001B[0m";
//
//    public EnrollmentUI() {
//        courseServiceImp = new CourseServiceImp();
//        enrollmentServiceImp = new EnrollmentServiceImp();
//    }
//
//    public static void main(String[] args) {
//        EnrollmentUI enrollmentUI = new EnrollmentUI();
//        Scanner scanner = new Scanner(System.in);
//        boolean exit = false;
//        do {
//            System.out.println(CYAN + "========================== QUẢN LÝ ĐĂNG KÝ ==========================" + RESET);
//            System.out.println(CYAN + "╔════════════╦══════════════════════════════════════════════════════╗");
//            System.out.printf("║ %-10s ║ %-52s ║\n", "Lựa chọn", "Mô tả");
//            System.out.println("╠════════════╬══════════════════════════════════════════════════════╣");
//
//            System.out.printf("║ %-10s ║ %-52s ║\n", "1", "Hiển thị danh sách sinh viên đăng ký");
//            System.out.printf("║ %-10s ║ %-52s ║\n", "2", "Duyệt đăng ký khóa học");
//            System.out.printf("║ %-10s ║ %-52s ║\n", "3", "Hủy đăng ký khóa học");
//            System.out.printf("║ %-10s ║ %-52s ║\n", "4", "Quay về menu quản trị");
//            System.out.println("╚════════════╩══════════════════════════════════════════════════════╝" + RESET);
//
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    listEnrollmentsByCourse(scanner);
//                    break;
//                case 2:
//                    approveEnrollment(scanner);
//                    break;
//                case 3:
//                    cancelEnrollment(scanner);
//                    break;
//                case 4:
//                    exit = true;
//                    break;
//                default:
//                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng chọn từ 1 - 4!" + RESET);
//            }
//        } while (!exit);
//    }
//
//    public static void listEnrollmentsByCourse(Scanner scanner) {
//        boolean exit = false;
//        Pagination pagination = new Pagination();
//        pagination.setCurrentpage(1);
//        pagination.setPagesize(5);
//
//        do {
//            // Calculate total courses
//            int totalResults = courseServiceImp.totalCourse();
//            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
//            pagination.setTotalpages(totalPages);
//
//            // Fetch paginated courses
//            List<Course> courses = courseServiceImp.listCourses(null, pagination);
//
//            // Display courses
//            System.out.println(BLUE + "╠═════╦════════════════════════════════════════════╦══════════╦═══════════════════════════════╦════════════╣" + RESET);
//            System.out.printf(CYAN + "║ %-3s ║ %-42s ║ %-8s ║ %-29s ║ %-10s ║\n" + RESET,
//                    "ID", "Tên khóa học", "Số buổi", "Giảng viên", "Ngày tạo");
//            System.out.println(BLUE + "╠═════╬════════════════════════════════════════════╬══════════╬═══════════════════════════════╬════════════╣" + RESET);
//
//            if (courses.isEmpty()) {
//                System.out.printf(RED + "║ %-94s ║\n" + RESET, "Không có khóa học nào.");
//            } else {
//                for (Course course : courses) {
//                    System.out.printf(WHITE + "║ %-3d ║ %-42s ║ %-8d ║ %-29s ║ %-10s ║\n" + RESET,
//                            course.getId(), course.getName(), course.getDuration(),
//                            course.getInstructor(), course.getCreate_at().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//                }
//            }
//
//            System.out.println(BLUE + "╚═════╩════════════════════════════════════════════╩══════════╩═══════════════════════════════╩════════════╝" + RESET);
//
//            // Display pagination
//            if (totalResults <= pagination.getPagesize()) {
//                System.out.println("Trang: " + YELLOW + "1" + RESET);
//                System.out.println(GREEN + "1. Chọn khóa học" + RESET);
//                System.out.println(GREEN + "2. Chọn trang" + RESET);
//                System.out.println(RED + "3. Thoát" + RESET);
//                System.out.print(CYAN + "Lựa chọn: " + RESET);
//            } else {
//                System.out.print(CYAN + "Trang: " + RESET);
//                if (pagination.getCurrentpage() > 1) {
//                    System.out.print(GREEN + "« Previous" + RESET + "  ");
//                    if (pagination.getCurrentpage() >= 3) System.out.print(WHITE + "..." + RESET + " ");
//                    System.out.print(WHITE + (pagination.getCurrentpage() - 1) + RESET);
//                }
//                System.out.print(YELLOW + "  [" + pagination.getCurrentpage() + "]  " + RESET);
//                if (pagination.getCurrentpage() < totalPages) {
//                    System.out.print(WHITE + (pagination.getCurrentpage() + 1) + RESET);
//                    if (totalPages - pagination.getCurrentpage() >= 2) System.out.print(" " + WHITE + "..." + RESET);
//                    System.out.print("  " + GREEN + "Next »" + RESET);
//                }
//                System.out.println();
//                if (pagination.getCurrentpage() > 1) System.out.println(GREEN + "P. Trang trước" + RESET);
//                if (pagination.getCurrentpage() < totalPages) System.out.println(GREEN + "N. Trang tiếp" + RESET);
//                System.out.println(GREEN + "1. Chọn khóa học" + RESET);
//                System.out.println(GREEN + "2. Chọn trang" + RESET);
//                System.out.println(RED + "3. Thoát" + RESET);
//                System.out.print(CYAN + "Lựa chọn: " + RESET);
//            }
//
//            while (true) {
//                String input = scanner.nextLine().trim();
//                if (input.isEmpty()) {
//                    System.out.println(RED + "Lựa chọn không được để trống! Vui lòng nhập lại:" + RESET);
//                    continue;
//                }
//                if (!input.equals("1") && !input.equals("2") && !input.equals("3") &&
//                        !input.equalsIgnoreCase("P") && !input.equalsIgnoreCase("N")) {
//                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
//                    continue;
//                }
//
//                switch (input.toUpperCase()) {
//                    case "1":
//                        int courseId = Validator.validateInt(scanner, 0, 1000, CYAN + "Nhập mã khóa học: " + RESET, "Mã khóa học");
//                        Course course = courseServiceImp.findCourseById(courseId);
//                        if (course == null) {
//                            System.out.println(RED + "Không tìm thấy khóa học!" + RESET);
//                        } else {
//                            enrollmentServiceImp.listEnrollmentsByCourse(scanner, courseId);
//                        }
//                        break;
//                    case "2":
//                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
//                        pagination.setCurrentpage(page);
//                        break;
//                    case "3":
//                        exit = true;
//                        break;
//                    case "P":
//                        if (pagination.getCurrentpage() > 1)
//                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
//                        break;
//                    case "N":
//                        if (pagination.getCurrentpage() < totalPages)
//                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
//                        break;
//                }
//                break;
//            }
//        } while (!exit);
//    }
//
//    public static void approveEnrollment(Scanner scanner) {
//        boolean exit = false;
//        Pagination pagination = new Pagination();
//        pagination.setCurrentpage(1);
//        pagination.setPagesize(5);
//
//        do {
//            // Calculate total waiting enrollments
//            int totalResults = enrollmentServiceImp.countWaitingEnrollments();
//            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
//            pagination.setTotalpages(totalPages);
//
//            // Fetch paginated waiting enrollments
//            List<Enrollment> enrollments = enrollmentServiceImp.listWaitingEnrollments(pagination.getPagesize(), pagination.getCurrentpage());
//
//            // Display enrollments
//            System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
//            System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
//                    "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
//            System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);
//
//            if (enrollments.isEmpty()) {
//                System.out.printf(RED + "║ %-96s ║\n" + RESET, "Không có đăng ký nào đang chờ duyệt.");
//                System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);
//                System.out.println(RED + "3. Thoát" + RESET);
//                System.out.print(CYAN + "Lựa chọn: " + RESET);
//            } else {
//                for (Enrollment enrollment : enrollments) {
//                    System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
//                            enrollment.getId(), enrollment.getStudentName(), enrollment.getCourseName(),
//                            enrollment.getStatus(), enrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//                }
//                System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);
//
//                // Display pagination
//                if (totalResults <= pagination.getPagesize()) {
//                    System.out.println("Trang: " + YELLOW + "1" + RESET);
//                    System.out.println(GREEN + "1. Chọn đăng ký" + RESET);
//                    System.out.println(GREEN + "2. Chọn trang" + RESET);
//                    System.out.println(RED + "3. Thoát" + RESET);
//                    System.out.print(CYAN + "Lựa chọn: " + RESET);
//                } else {
//                    System.out.print(CYAN + "Trang: " + RESET);
//                    if (pagination.getCurrentpage() > 1) {
//                        System.out.print(GREEN + "« Previous" + RESET + "  ");
//                        if (pagination.getCurrentpage() >= 3) System.out.print(WHITE + "..." + RESET + " ");
//                        System.out.print(WHITE + (pagination.getCurrentpage() - 1) + RESET);
//                    }
//                    System.out.print(YELLOW + "  [" + pagination.getCurrentpage() + "]  " + RESET);
//                    if (pagination.getCurrentpage() < totalPages) {
//                        System.out.print(WHITE + (pagination.getCurrentpage() + 1) + RESET);
//                        if (totalPages - pagination.getCurrentpage() >= 2) System.out.print(" " + WHITE + "..." + RESET);
//                        System.out.print("  " + GREEN + "Next »" + RESET);
//                    }
//                    System.out.println();
//                    if (pagination.getCurrentpage() > 1) System.out.println(GREEN + "P. Trang trước" + RESET);
//                    if (pagination.getCurrentpage() < totalPages) System.out.println(GREEN + "N. Trang tiếp" + RESET);
//                    System.out.println(GREEN + "1. Chọn đăng ký" + RESET);
//                    System.out.println(GREEN + "2. Chọn trang" + RESET);
//                    System.out.println(RED + "3. Thoát" + RESET);
//                    System.out.print(CYAN + "Lựa chọn: " + RESET);
//                }
//            }
//
//            while (true) {
//                String input = scanner.nextLine().trim();
//                if (input.isEmpty()) {
//                    System.out.println(RED + "Lựa chọn không được để trống! Vui lòng nhập lại:" + RESET);
//                    continue;
//                }
//                if (!input.equals("1") && !input.equals("2") && !input.equals("3") &&
//                        !input.equalsIgnoreCase("P") && !input.equalsIgnoreCase("N")) {
//                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
//                    continue;
//                }
//
//                switch (input.toUpperCase()) {
//                    case "1":
//                        if (enrollments.isEmpty()) {
//                            System.out.println(RED + "Không có đăng ký nào để chọn!" + RESET);
//                            break;
//                        }
//                        boolean validEnrollment = false;
//                        Enrollment selectedEnrollment = null;
//                        int enrollmentId;
//                        do {
//                            enrollmentId = Validator.validateInt(scanner, 0, 1000, CYAN + "Nhập mã đăng ký cần duyệt: " + RESET, "Mã đăng ký");
//                            for (Enrollment e : enrollments) {
//                                if (e.getId() == enrollmentId && e.getStatus().equals("WAITING")) {
//                                    selectedEnrollment = e;
//                                    validEnrollment = true;
//                                    break;
//                                }
//                            }
//                            if (!validEnrollment) {
//                                System.out.println(RED + "Mã đăng ký không hợp lệ hoặc không ở trạng thái WAITING! Vui lòng nhập lại." + RESET);
//                            }
//                        } while (!validEnrollment);
//
//                        // Display selected enrollment details
//                        System.out.println(CYAN + "Thông tin đăng ký:" + RESET);
//                        System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
//                        System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
//                                "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
//                        System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);
//                        System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
//                                selectedEnrollment.getId(), selectedEnrollment.getStudentName(), selectedEnrollment.getCourseName(),
//                                selectedEnrollment.getStatus(), selectedEnrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//                        System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);
//
//                        // Prompt for status
//                        System.out.println(GREEN + "Chọn trạng thái:" + RESET);
//                        System.out.println(GREEN + "1. CONFIRM" + RESET);
//                        System.out.println(GREEN + "2. DENIED" + RESET);
//                        int statusChoice = ValidatorChoice.validater(scanner);
//                        String newStatus = statusChoice == 1 ? "CONFIRM" : "DENIED";
//
//                        if (enrollmentServiceImp.approveEnrollment(enrollmentId, newStatus)) {
//                            System.out.println(GREEN + "Duyệt đăng ký thành công" + RESET);
//                        } else {
//                            System.out.println(RED + "Duyệt đăng ký thất bại!" + RESET);
//                        }
//                        break;
//                    case "2":
//                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
//                        pagination.setCurrentpage(page);
//                        break;
//                    case "3":
//                        exit = true;
//                        break;
//                    case "P":
//                        if (pagination.getCurrentpage() > 1)
//                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
//                        break;
//                    case "N":
//                        if (pagination.getCurrentpage() < totalPages)
//                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
//                        break;
//                }
//                break;
//            }
//        } while (!exit);
//    }
//
//    public static void cancelEnrollment(Scanner scanner) {
//        int enrollmentId = Validator.validateInt(scanner, 0, 1000, CYAN + "Nhập mã đăng ký cần hủy: " + RESET, "Mã đăng ký");
//        boolean exit = false;
//        while (!exit) {
//            System.out.println("Bạn có chắc chắn muốn hủy đăng ký này không(y/n)");
//            System.out.print(CYAN + "Lựa chọn: " + RESET);
//            char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
//            switch (choice) {
//                case 'y':
//                    if (enrollmentServiceImp.cancelEnrollment(enrollmentId)) {
//                        System.out.println(GREEN + "Hủy đăng ký thành công" + RESET);
//                    } else {
//                        System.out.println(RED + "Hủy đăng ký thất bại!" + RESET);
//                    }
//                    exit = true;
//                    break;
//                case 'n':
//                    System.out.println(YELLOW + "Đã hủy thao tác" + RESET);
//                    exit = true;
//                    break;
//                default:
//                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng chọn y/n" + RESET);
//            }
//        }
//    }
//}



package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Pagination;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.business.service.enrollment.EnrollmentServiceImp;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EnrollmentUI {
    private static CourseServiceImp courseServiceImp;
    private static EnrollmentServiceImp enrollmentServiceImp;

    // Define color constants
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public EnrollmentUI() {
        courseServiceImp = new CourseServiceImp();
        enrollmentServiceImp = new EnrollmentServiceImp();
    }

    public static void main(String[] args) {
        EnrollmentUI enrollmentUI = new EnrollmentUI();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(CYAN + "========================== QUẢN LÝ ĐĂNG KÝ ==========================" + RESET);
            System.out.println(CYAN + "╔════════════╦══════════════════════════════════════════════════════╗");
            System.out.printf("║ %-10s ║ %-52s ║\n", "Lựa chọn", "Mô tả");
            System.out.println("╠════════════╬══════════════════════════════════════════════════════╣");

            System.out.printf("║ %-10s ║ %-52s ║\n", "1", "Hiển thị danh sách sinh viên đăng ký");
            System.out.printf("║ %-10s ║ %-52s ║\n", "2", "Duyệt đăng ký khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "3", "Hủy đăng ký khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "4", "Quay về menu quản trị");
            System.out.println("╚════════════╩══════════════════════════════════════════════════════╝" + RESET);

            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    listEnrollmentsByCourse(scanner);
                    break;
                case 2:
                    approveEnrollment(scanner);
                    break;
                case 3:
                    cancelEnrollment(scanner);
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng chọn từ 1 - 4!" + RESET);
            }
        } while (!exit);
    }

    public static void listEnrollmentsByCourse(Scanner scanner) {
        boolean exit = false;
        Pagination pagination = new Pagination();
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);

        do {
            // Calculate total courses
            int totalResults = courseServiceImp.totalCourse();
            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
            pagination.setTotalpages(totalPages);

            // Fetch paginated courses
            List<Course> courses = courseServiceImp.listCourses(null, pagination);

            // Display courses
            System.out.println(BLUE + "╠═════╦════════════════════════════════════════════╦══════════╦═══════════════════════════════╦════════════╣" + RESET);
            System.out.printf(CYAN + "║ %-3s ║ %-42s ║ %-8s ║ %-29s ║ %-10s ║\n" + RESET,
                    "ID", "Tên khóa học", "Số buổi", "Giảng viên", "Ngày tạo");
            System.out.println(BLUE + "╠═════╬════════════════════════════════════════════╬══════════╬═══════════════════════════════╬════════════╣" + RESET);

            if (courses.isEmpty()) {
                System.out.printf(RED + "║ %-94s ║\n" + RESET, "Không có khóa học nào.");
            } else {
                for (Course course : courses) {
                    System.out.printf(WHITE + "║ %-3d ║ %-42s ║ %-8d ║ %-29s ║ %-10s ║\n" + RESET,
                            course.getId(), course.getName(), course.getDuration(),
                            course.getInstructor(), course.getCreate_at().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
            }

            System.out.println(BLUE + "╚═════╩════════════════════════════════════════════╩══════════╩═══════════════════════════════╩════════════╝" + RESET);

            // Display pagination
            if (totalResults <= pagination.getPagesize()) {
                System.out.println("Trang: " + YELLOW + "1" + RESET);
                System.out.println(GREEN + "1. Chọn khóa học" + RESET);
                System.out.println(GREEN + "2. Chọn trang" + RESET);
                System.out.println(RED + "3. Thoát" + RESET);
                System.out.print(CYAN + "Lựa chọn: " + RESET);
            } else {
                System.out.print(CYAN + "Trang: " + RESET);
                if (pagination.getCurrentpage() > 1) {
                    System.out.print(GREEN + "« Previous" + RESET + "  ");
                    if (pagination.getCurrentpage() >= 3) System.out.print(WHITE + "..." + RESET + " ");
                    System.out.print(WHITE + (pagination.getCurrentpage() - 1) + RESET);
                }
                System.out.print(YELLOW + "  [" + pagination.getCurrentpage() + "]  " + RESET);
                if (pagination.getCurrentpage() < totalPages) {
                    System.out.print(WHITE + (pagination.getCurrentpage() + 1) + RESET);
                    if (totalPages - pagination.getCurrentpage() >= 2) System.out.print(" " + WHITE + "..." + RESET);
                    System.out.print("  " + GREEN + "Next »" + RESET);
                }
                System.out.println();
                if (pagination.getCurrentpage() > 1) System.out.println(GREEN + "P. Trang trước" + RESET);
                if (pagination.getCurrentpage() < totalPages) System.out.println(GREEN + "N. Trang tiếp" + RESET);
                System.out.println(GREEN + "1. Chọn khóa học" + RESET);
                System.out.println(GREEN + "2. Chọn trang" + RESET);
                System.out.println(RED + "3. Thoát" + RESET);
                System.out.print(CYAN + "Lựa chọn: " + RESET);
            }

            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(RED + "Lựa chọn không được để trống! Vui lòng nhập lại:" + RESET);
                    continue;
                }
                if (!input.equals("1") && !input.equals("2") && !input.equals("3") &&
                        !input.equalsIgnoreCase("P") && !input.equalsIgnoreCase("N")) {
                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
                    continue;
                }

                switch (input.toUpperCase()) {
                    case "1":
                        int courseId = Validator.validateInt(scanner, 0, 1000, CYAN + "Nhập mã khóa học: " + RESET, "Mã khóa học");
                        Course course = courseServiceImp.findCourseById(courseId);
                        if (course == null) {
                            System.out.println(RED + "Không tìm thấy khóa học!" + RESET);
                        } else {
                            enrollmentServiceImp.listEnrollmentsByCourse(scanner, courseId);
                        }
                        break;
                    case "2":
                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case "3":
                        exit = true;
                        break;
                    case "P":
                        if (pagination.getCurrentpage() > 1)
                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                        break;
                    case "N":
                        if (pagination.getCurrentpage() < totalPages)
                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                        break;
                }
                break;
            }
        } while (!exit);
    }

    public static void approveEnrollment(Scanner scanner) {
        boolean exit = false;
        Pagination pagination = new Pagination();
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);

        do {
            // Calculate total waiting enrollments
            int totalResults = enrollmentServiceImp.countWaitingEnrollments();
            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
            pagination.setTotalpages(totalPages);

            // Fetch paginated waiting enrollments
            List<Enrollment> enrollments = enrollmentServiceImp.listWaitingEnrollments(pagination.getPagesize(), pagination.getCurrentpage());

            // Display enrollments
            System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
            System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                    "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
            System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);

            if (enrollments.isEmpty()) {
                System.out.printf(RED + "║ %-96s ║\n" + RESET, "Không có đăng ký nào đang chờ duyệt.");
                System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);
                System.out.println(RED + "3. Thoát" + RESET);
                System.out.print(CYAN + "Lựa chọn: " + RESET);
            } else {
                for (Enrollment enrollment : enrollments) {
                    System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                            enrollment.getId(), enrollment.getStudentName(), enrollment.getCourseName(),
                            enrollment.getStatus(), enrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
                System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);

                // Display pagination
                if (totalResults <= pagination.getPagesize()) {
                    System.out.println("Trang: " + YELLOW + "1" + RESET);
                    System.out.println(GREEN + "1. Chọn đăng ký" + RESET);
                    System.out.println(GREEN + "2. Chọn trang" + RESET);
                    System.out.println(RED + "3. Thoát" + RESET);
                    System.out.print(CYAN + "Lựa chọn: " + RESET);
                } else {
                    System.out.print(CYAN + "Trang: " + RESET);
                    if (pagination.getCurrentpage() > 1) {
                        System.out.print(GREEN + "« Previous" + RESET + "  ");
                        if (pagination.getCurrentpage() >= 3) System.out.print(WHITE + "..." + RESET + " ");
                        System.out.print(WHITE + (pagination.getCurrentpage() - 1) + RESET);
                    }
                    System.out.print(YELLOW + "  [" + pagination.getCurrentpage() + "]  " + RESET);
                    if (pagination.getCurrentpage() < totalPages) {
                        System.out.print(WHITE + (pagination.getCurrentpage() + 1) + RESET);
                        if (totalPages - pagination.getCurrentpage() >= 2) System.out.print(" " + WHITE + "..." + RESET);
                        System.out.print("  " + GREEN + "Next »" + RESET);
                    }
                    System.out.println();
                    if (pagination.getCurrentpage() > 1) System.out.println(GREEN + "P. Trang trước" + RESET);
                    if (pagination.getCurrentpage() < totalPages) System.out.println(GREEN + "N. Trang tiếp" + RESET);
                    System.out.println(GREEN + "1. Chọn đăng ký" + RESET);
                    System.out.println(GREEN + "2. Chọn trang" + RESET);
                    System.out.println(RED + "3. Thoát" + RESET);
                    System.out.print(CYAN + "Lựa chọn: " + RESET);
                }
            }

            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(RED + "Lựa chọn không được để trống! Vui lòng nhập lại:" + RESET);
                    continue;
                }
                if (!input.equals("1") && !input.equals("2") && !input.equals("3") &&
                        !input.equalsIgnoreCase("P") && !input.equalsIgnoreCase("N")) {
                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
                    continue;
                }

                switch (input.toUpperCase()) {
                    case "1":
                        if (enrollments.isEmpty()) {
                            System.out.println(RED + "Không có đăng ký nào để chọn!" + RESET);
                            break;
                        }
                        boolean validEnrollment = false;
                        Enrollment selectedEnrollment = null;
                        int enrollmentId;
                        do {
                            enrollmentId = Validator.validateInt(scanner, 0, 1000, CYAN + "Nhập mã đăng ký cần duyệt: " + RESET, "Mã đăng ký");
                            for (Enrollment e : enrollments) {
                                if (e.getId() == enrollmentId && e.getStatus().equals("WAITING")) {
                                    selectedEnrollment = e;
                                    validEnrollment = true;
                                    break;
                                }
                            }
                            if (!validEnrollment) {
                                System.out.println(RED + "Mã đăng ký không hợp lệ hoặc không ở trạng thái WAITING! Vui lòng nhập lại." + RESET);
                            }
                        } while (!validEnrollment);

                        // Display selected enrollment details
                        System.out.println(CYAN + "Thông tin đăng ký:" + RESET);
                        System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
                        System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                                "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
                        System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);
                        System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                                selectedEnrollment.getId(), selectedEnrollment.getStudentName(), selectedEnrollment.getCourseName(),
                                selectedEnrollment.getStatus(), selectedEnrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                        System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);

                        // Prompt for status
                        System.out.println(GREEN + "Chọn trạng thái:" + RESET);
                        System.out.println(GREEN + "1. CONFIRM" + RESET);
                        System.out.println(GREEN + "2. DENIED" + RESET);
                        int statusChoice = ValidatorChoice.validater(scanner);
                        String newStatus = statusChoice == 1 ? "CONFIRM" : "DENIED";

                        if (enrollmentServiceImp.approveEnrollment(enrollmentId, newStatus)) {
                            System.out.println(GREEN + "Duyệt đăng ký thành công" + RESET);
                        } else {
                            System.out.println(RED + "Duyệt đăng ký thất bại!" + RESET);
                        }
                        break;
                    case "2":
                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case "3":
                        exit = true;
                        break;
                    case "P":
                        if (pagination.getCurrentpage() > 1)
                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                        break;
                    case "N":
                        if (pagination.getCurrentpage() < totalPages)
                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                        break;
                }
                break;
            }
        } while (!exit);
    }

    public static void cancelEnrollment(Scanner scanner) {
        boolean exit = false;
        Pagination pagination = new Pagination();
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);

        do {
            // Calculate total denied enrollments
            int totalResults = enrollmentServiceImp.countDeniedEnrollments();
            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
            pagination.setTotalpages(totalPages);

            // Fetch paginated denied enrollments
            List<Enrollment> enrollments = enrollmentServiceImp.listDeniedEnrollments(pagination.getPagesize(), pagination.getCurrentpage());

            // Display enrollments
            System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
            System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                    "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
            System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);

            if (enrollments.isEmpty()) {
                System.out.printf(RED + "║ %-96s ║\n" + RESET, "Không có đăng ký nào ở trạng thái DENIED.");
                System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);
                System.out.println(RED + "3. Thoát" + RESET);
                System.out.print(CYAN + "Lựa chọn: " + RESET);
            } else {
                for (Enrollment enrollment : enrollments) {
                    System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                            enrollment.getId(), enrollment.getStudentName(), enrollment.getCourseName(),
                            enrollment.getStatus(), enrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
                System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);

                // Display pagination
                if (totalResults <= pagination.getPagesize()) {
                    System.out.println("Trang: " + YELLOW + "1" + RESET);
                    System.out.println(GREEN + "1. Chọn đăng ký để hủy" + RESET);
                    System.out.println(GREEN + "2. Chọn trang" + RESET);
                    System.out.println(RED + "3. Thoát" + RESET);
                    System.out.print(CYAN + "Lựa chọn: " + RESET);
                } else {
                    System.out.print(CYAN + "Trang: " + RESET);
                    if (pagination.getCurrentpage() > 1) {
                        System.out.print(GREEN + "« Previous" + RESET + "  ");
                        if (pagination.getCurrentpage() >= 3) System.out.print(WHITE + "..." + RESET + " ");
                        System.out.print(WHITE + (pagination.getCurrentpage() - 1) + RESET);
                    }
                    System.out.print(YELLOW + "  [" + pagination.getCurrentpage() + "]  " + RESET);
                    if (pagination.getCurrentpage() < totalPages) {
                        System.out.print(WHITE + (pagination.getCurrentpage() + 1) + RESET);
                        if (totalPages - pagination.getCurrentpage() >= 2) System.out.print(" " + WHITE + "..." + RESET);
                        System.out.print("  " + GREEN + "Next »" + RESET);
                    }
                    System.out.println();
                    if (pagination.getCurrentpage() > 1) System.out.println(GREEN + "P. Trang trước" + RESET);
                    if (pagination.getCurrentpage() < totalPages) System.out.println(GREEN + "N. Trang tiếp" + RESET);
                    System.out.println(GREEN + "1. Chọn đăng ký để hủy" + RESET);
                    System.out.println(GREEN + "2. Chọn trang" + RESET);
                    System.out.println(RED + "3. Thoát" + RESET);
                    System.out.print(CYAN + "Lựa chọn: " + RESET);
                }
            }

            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(RED + "Lựa chọn không được để trống! Vui lòng nhập lại:" + RESET);
                    continue;
                }
                if (!input.equals("1") && !input.equals("2") && !input.equals("3") &&
                        !input.equalsIgnoreCase("P") && !input.equalsIgnoreCase("N")) {
                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
                    continue;
                }

                switch (input.toUpperCase()) {
                    case "1":
                        if (enrollments.isEmpty()) {
                            System.out.println(RED + "Không có đăng ký nào để hủy!" + RESET);
                            break;
                        }
                        boolean validEnrollment = false;
                        Enrollment selectedEnrollment = null;
                        int enrollmentId;
                        do {
                            enrollmentId = Validator.validateInt(scanner, 0, 1000, CYAN + "Nhập mã đăng ký cần hủy: " + RESET, "Mã đăng ký");
                            for (Enrollment e : enrollments) {
                                if (e.getId() == enrollmentId && e.getStatus().equals("DENIED")) {
                                    selectedEnrollment = e;
                                    validEnrollment = true;
                                    break;
                                }
                            }
                            if (!validEnrollment) {
                                System.out.println(RED + "Mã đăng ký không hợp lệ hoặc không ở trạng thái DENIED! Vui lòng nhập lại." + RESET);
                            }
                        } while (!validEnrollment);

                        // Display selected enrollment details
                        System.out.println(CYAN + "Thông tin đăng ký:" + RESET);
                        System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
                        System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                                "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
                        System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);
                        System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                                selectedEnrollment.getId(), selectedEnrollment.getStudentName(), selectedEnrollment.getCourseName(),
                                selectedEnrollment.getStatus(), selectedEnrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                        System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);

                        // Confirm cancellation
                        boolean confirmExit = false;
                        while (!confirmExit) {
                            System.out.println("Bạn có chắc chắn muốn hủy đăng ký này không (y/n)?");
                            System.out.print(CYAN + "Lựa chọn: " + RESET);
                            String choice = scanner.nextLine().trim().toLowerCase();
                            if (choice.isEmpty() || (!choice.equals("y") && !choice.equals("n"))) {
                                System.out.println(RED + "Lựa chọn không hợp lệ, vui lòng chọn y/n!" + RESET);
                                continue;
                            }
                            switch (choice) {
                                case "y":
                                    if (enrollmentServiceImp.cancelEnrollment(enrollmentId)) {
                                        System.out.println(GREEN + "Hủy đăng ký thành công" + RESET);
                                    } else {
                                        System.out.println(RED + "Hủy đăng ký thất bại!" + RESET);
                                    }
                                    confirmExit = true;
                                    break;
                                case "n":
                                    System.out.println(YELLOW + "Đã hủy thao tác" + RESET);
                                    confirmExit = true;
                                    break;
                            }
                        }
                        break;
                    case "2":
                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case "3":
                        exit = true;
                        break;
                    case "P":
                        if (pagination.getCurrentpage() > 1)
                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                        break;
                    case "N":
                        if (pagination.getCurrentpage() < totalPages)
                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                        break;
                }
                break;
            }
        } while (!exit);
    }
}