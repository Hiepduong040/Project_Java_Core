////package ra.edu.business.service.Enrollment;
////
////import ra.edu.business.dao.Enrollment.EnrollmentDAOImp;
////import ra.edu.business.model.Enrollment;
////import ra.edu.business.model.Pagination;
////import ra.edu.validate.Validator;
////
////import java.time.format.DateTimeFormatter;
////import java.util.List;
////import java.util.Scanner;
////
////public class EnrollmentServiceImp implements EnrollmentService {
////    private EnrollmentDAOImp enrollmentDAOImp;
////    private Pagination pagination;
////
////    // Define color constants
////    private static final String BLUE = "\u001B[34m";
////    private static final String CYAN = "\u001B[36m";
////    private static final String WHITE = "\u001B[37m";
////    private static final String RED = "\u001B[31m";
////    private static final String YELLOW = "\u001B[33m";
////    private static final String GREEN = "\u001B[32m";
////    private static final String RESET = "\u001B[0m";
////
////    public EnrollmentServiceImp() {
////        enrollmentDAOImp = new EnrollmentDAOImp();
////        pagination = new Pagination();
////    }
////
////    @Override
////    public boolean registerCourse(Enrollment enrollment) {
////        return enrollmentDAOImp.save(enrollment);
////    }
////
////    @Override
////    public boolean cancelEnrollment(int enrollmentId) {
////        return enrollmentDAOImp.cancel(enrollmentId);
////    }
////
////    @Override
////    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
////        return enrollmentDAOImp.findByStudentId(studentId);
////    }
////
////    @Override
////    public int countEnrollmentsByStudent(int studentId) {
////        return enrollmentDAOImp.totalEnrollmentsByStudent(studentId);
////    }
////
////    @Override
////    public void listEnrollmentsByCourse(Scanner scanner, int courseId) {
////        boolean exit = false;
////        pagination.setCurrentpage(1);
////        pagination.setPagesize(5);
////
////        do {
////            // Calculate total results
////            int totalResults = enrollmentDAOImp.countEnrollmentsByCourse(courseId);
////            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
////            pagination.setTotalpages(totalPages);
////
////            // Fetch paginated enrollments
////            List<Enrollment> enrollments = enrollmentDAOImp.listEnrollmentsByCourse(courseId, pagination.getPagesize(), pagination.getCurrentpage());
////
////            // Display table header
////            System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
////            System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
////                    "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
////            System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);
////
////            if (enrollments.isEmpty()) {
////                System.out.printf(RED + "║ %-96s ║\n" + RESET, "Không có đăng ký nào cho khóa học này.");
////            } else {
////                for (Enrollment enrollment : enrollments) {
////                    System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
////                            enrollment.getId(), enrollment.getStudentName(), enrollment.getCourseName(),
////                            enrollment.getStatus(), enrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
////                }
////            }
////
////            System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);
////
////            // Display pagination
////            if (totalResults <= pagination.getPagesize()) {
////                System.out.println("Trang: " + YELLOW + "1" + RESET);
////                System.out.println(GREEN + "1. Chọn trang" + RESET);
////                System.out.println(RED + "2. Thoát" + RESET);
////                System.out.print(CYAN + "Lựa chọn: " + RESET);
////            } else {
////                System.out.print(CYAN + "Trang: " + RESET);
////                if (pagination.getCurrentpage() > 1) {
////                    System.out.print(GREEN + "« Previous" + RESET + "  ");
////                    if (pagination.getCurrentpage() >= 3) System.out.print(WHITE + "..." + RESET + " ");
////                    System.out.print(WHITE + (pagination.getCurrentpage() - 1) + RESET);
////                }
////                System.out.print(YELLOW + "  [" + pagination.getCurrentpage() + "]  " + RESET);
////                if (pagination.getCurrentpage() < totalPages) {
////                    System.out.print(WHITE + (pagination.getCurrentpage() + 1) + RESET);
////                    if (totalPages - pagination.getCurrentpage() >= 2) System.out.print(" " + WHITE + "..." + RESET);
////                    System.out.print("  " + GREEN + "Next »" + RESET);
////                }
////                System.out.println();
////                if (pagination.getCurrentpage() > 1) System.out.println(GREEN + "P. Trang trước" + RESET);
////                if (pagination.getCurrentpage() < totalPages) System.out.println(GREEN + "N. Trang tiếp" + RESET);
////                System.out.println(GREEN + "1. Chọn trang" + RESET);
////                System.out.println(RED + "2. Thoát" + RESET);
////                System.out.print(CYAN + "Lựa chọn: " + RESET);
////            }
////
////            while (true) {
////                String input = scanner.nextLine().trim();
////                if (input.isEmpty()) {
////                    System.out.println(RED + "Lựa chọn không được để trống! Vui lòng nhập lại:" + RESET);
////                    continue;
////                }
////                char choice = Character.toUpperCase(input.charAt(0));
////                switch (choice) {
////                    case '1':
////                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
////                        pagination.setCurrentpage(page);
////                        break;
////                    case '2':
////                        exit = true;
////                        break;
////                    case 'P':
////                        if (pagination.getCurrentpage() > 1)
////                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
////                        break;
////                    case 'N':
////                        if (pagination.getCurrentpage() < totalPages)
////                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
////                        break;
////                    default:
////                        System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
////                        continue;
////                }
////                break;
////            }
////        } while (!exit);
////    }
////
////    @Override
////    public boolean approveEnrollment(int enrollmentId, String newStatus) {
////        return enrollmentDAOImp.approveEnrollment(enrollmentId, newStatus);
////    }
////
////    @Override
////    public Enrollment findEnrollmentById(int enrollmentId) {
////        return enrollmentDAOImp.findById(enrollmentId);
////    }
////}
//
//package ra.edu.business.service.enrollment;
//
//import ra.edu.business.dao.enrollment.EnrollmentDAOImp;
//import ra.edu.business.model.Enrollment;
//import ra.edu.business.model.Pagination;
//import ra.edu.validate.Validator;
//
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Scanner;
//
//public class EnrollmentServiceImp implements EnrollmentService {
//    private EnrollmentDAOImp enrollmentDAOImp;
//    private Pagination pagination;
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
//    public EnrollmentServiceImp() {
//        enrollmentDAOImp = new EnrollmentDAOImp();
//        pagination = new Pagination();
//    }
//
//    @Override
//    public boolean registerCourse(Enrollment enrollment) {
//        return enrollmentDAOImp.save(enrollment);
//    }
//
//    @Override
//    public boolean cancelEnrollment(int enrollmentId) {
//        return enrollmentDAOImp.cancel(enrollmentId);
//    }
//
//    @Override
//    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
//        return enrollmentDAOImp.findByStudentId(studentId);
//    }
//
//    @Override
//    public int countEnrollmentsByStudent(int studentId) {
//        return enrollmentDAOImp.totalEnrollmentsByStudent(studentId);
//    }
//
//    @Override
//    public void listEnrollmentsByCourse(Scanner scanner, int courseId) {
//        boolean exit = false;
//        pagination.setCurrentpage(1);
//        pagination.setPagesize(5);
//
//        do {
//            // Calculate total results
//            int totalResults = enrollmentDAOImp.countEnrollmentsByCourse(courseId);
//            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
//            pagination.setTotalpages(totalPages);
//
//            // Fetch paginated enrollments
//            List<Enrollment> enrollments = enrollmentDAOImp.listEnrollmentsByCourse(courseId, pagination.getPagesize(), pagination.getCurrentpage());
//
//            // Display table header
//            System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
//            System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
//                    "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
//            System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);
//
//            if (enrollments.isEmpty()) {
//                System.out.printf(RED + "║ %-96s ║\n" + RESET, "Không có đăng ký nào cho khóa học này.");
//            } else {
//                for (Enrollment enrollment : enrollments) {
//                    System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
//                            enrollment.getId(), enrollment.getStudentName(), enrollment.getCourseName(),
//                            enrollment.getStatus(), enrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//                }
//            }
//
//            System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);
//
//            // Display pagination
//            if (totalResults <= pagination.getPagesize()) {
//                System.out.println("Trang: " + YELLOW + "1" + RESET);
//                System.out.println(GREEN + "1. Chọn trang" + RESET);
//                System.out.println(RED + "2. Thoát" + RESET);
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
//                System.out.println(GREEN + "1. Chọn trang" + RESET);
//                System.out.println(RED + "2. Thoát" + RESET);
//                System.out.print(CYAN + "Lựa chọn: " + RESET);
//            }
//
//            while (true) {
//                String input = scanner.nextLine().trim();
//                if (input.isEmpty()) {
//                    System.out.println(RED + "Lựa chọn không được để trống! Vui lòng nhập lại:" + RESET);
//                    continue;
//                }
//                if (!input.equals("1") && !input.equals("2") &&
//                        !input.equalsIgnoreCase("P") && !input.equalsIgnoreCase("N")) {
//                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
//                    continue;
//                }
//
//                switch (input.toUpperCase()) {
//                    case "1":
//                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
//                        pagination.setCurrentpage(page);
//                        break;
//                    case "2":
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
//    @Override
//    public boolean approveEnrollment(int enrollmentId, String newStatus) {
//        return enrollmentDAOImp.approveEnrollment(enrollmentId, newStatus);
//    }
//
//    @Override
//    public Enrollment findEnrollmentById(int enrollmentId) {
//        return enrollmentDAOImp.findById(enrollmentId);
//    }
//
//    @Override
//    public List<Enrollment> listWaitingEnrollments(int pageSize, int currentPage) {
//        return enrollmentDAOImp.listWaitingEnrollments(pageSize, currentPage);
//    }
//
//    @Override
//    public int countWaitingEnrollments() {
//        return enrollmentDAOImp.countWaitingEnrollments();
//    }
//}

package ra.edu.business.service.enrollment;

import ra.edu.business.dao.enrollment.EnrollmentDAOImp;
import ra.edu.business.model.Enrollment;
import ra.edu.business.model.Pagination;
import ra.edu.validate.Validator;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class EnrollmentServiceImp implements EnrollmentService {
    private EnrollmentDAOImp enrollmentDAOImp;
    private Pagination pagination;

    // Define color constants
    private static final String BLUE = "\u001B[34m";
    private static final String CYAN = "\u001B[36m";
    private static final String WHITE = "\u001B[37m";
    private static final String RED = "\u001B[31m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String RESET = "\u001B[0m";

    public EnrollmentServiceImp() {
        enrollmentDAOImp = new EnrollmentDAOImp();
        pagination = new Pagination();
    }

    @Override
    public boolean registerCourse(Enrollment enrollment) {
        return enrollmentDAOImp.save(enrollment);
    }

    @Override
    public boolean cancelEnrollment(int enrollmentId) {
        return enrollmentDAOImp.cancel(enrollmentId);
    }

    @Override
    public List<Enrollment> getEnrollmentsByStudent(int studentId) {
        return enrollmentDAOImp.findByStudentId(studentId);
    }

    @Override
    public int countEnrollmentsByStudent(int studentId) {
        return enrollmentDAOImp.totalEnrollmentsByStudent(studentId);
    }

    @Override
    public void listEnrollmentsByCourse(Scanner scanner, int courseId) {
        boolean exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);

        do {
            // Calculate total results
            int totalResults = enrollmentDAOImp.countEnrollmentsByCourse(courseId);
            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
            pagination.setTotalpages(totalPages);

            // Fetch paginated enrollments
            List<Enrollment> enrollments = enrollmentDAOImp.listEnrollmentsByCourse(courseId, pagination.getPagesize(), pagination.getCurrentpage());

            // Display table header
            System.out.println(BLUE + "╠═════╦═════════════════════════════╦═════════════════════════════╦═══════════════╦════════════════════╣" + RESET);
            System.out.printf(CYAN + "║ %-3s ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                    "ID", "Tên sinh viên", "Tên khóa học", "Trạng thái", "Ngày đăng ký");
            System.out.println(BLUE + "╠═════╬═════════════════════════════╬═════════════════════════════╬═══════════════╬════════════════════╣" + RESET);

            if (enrollments.isEmpty()) {
                System.out.printf(RED + "║ %-96s ║\n" + RESET, "Không có đăng ký nào cho khóa học này.");
            } else {
                for (Enrollment enrollment : enrollments) {
                    System.out.printf(WHITE + "║ %-3d ║ %-27s ║ %-27s ║ %-13s ║ %-18s ║\n" + RESET,
                            enrollment.getId(), enrollment.getStudentName(), enrollment.getCourseName(),
                            enrollment.getStatus(), enrollment.getRegisteredAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                }
            }

            System.out.println(BLUE + "╚═════╩═════════════════════════════╩═════════════════════════════╩═══════════════╩════════════════════╝" + RESET);

            // Display pagination
            if (totalResults <= pagination.getPagesize()) {
                System.out.println("Trang: " + YELLOW + "1" + RESET);
                System.out.println(GREEN + "1. Chọn trang" + RESET);
                System.out.println(RED + "2. Thoát" + RESET);
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
                System.out.println(GREEN + "1. Chọn trang" + RESET);
                System.out.println(RED + "2. Thoát" + RESET);
                System.out.print(CYAN + "Lựa chọn: " + RESET);
            }

            while (true) {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println(RED + "Lựa chọn không được để trống! Vui lòng nhập lại:" + RESET);
                    continue;
                }
                if (!input.equals("1") && !input.equals("2") &&
                        !input.equalsIgnoreCase("P") && !input.equalsIgnoreCase("N")) {
                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
                    continue;
                }

                switch (input.toUpperCase()) {
                    case "1":
                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case "2":
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

    @Override
    public boolean approveEnrollment(int enrollmentId, String newStatus) {
        return enrollmentDAOImp.approveEnrollment(enrollmentId, newStatus);
    }

    @Override
    public Enrollment findEnrollmentById(int enrollmentId) {
        return enrollmentDAOImp.findById(enrollmentId);
    }

    @Override
    public List<Enrollment> listWaitingEnrollments(int pageSize, int currentPage) {
        return enrollmentDAOImp.listWaitingEnrollments(pageSize, currentPage);
    }

    @Override
    public int countWaitingEnrollments() {
        return enrollmentDAOImp.countWaitingEnrollments();
    }

    @Override
    public List<Enrollment> listDeniedEnrollments(int pageSize, int currentPage) {
        return enrollmentDAOImp.listDeniedEnrollments(pageSize, currentPage);
    }

    @Override
    public int countDeniedEnrollments() {
        return enrollmentDAOImp.countDeniedEnrollments();
    }
}