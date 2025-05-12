//package ra.edu.presentation;
//
//import ra.edu.ManiApplication;
//import ra.edu.validate.ValidatorChoice;
//
//import java.util.Scanner;
//
//public class AdminUI {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        boolean exit = false;
//        do {
//            System.out.println("================== Menu Quản trị viên ==================");
//            System.out.println("1. Quản lý khóa học");
//            System.out.println("2. Quản lý học viên");
//            System.out.println("3. Quản lý đăng ký học");
//            System.out.println("4. Thống kê học viên theo khóa học");
//            System.out.println("5. Đăng xuất");
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    CourseUI.main(null);
//                    break;
//                case 2:
//                    ManagerStudentUI.main(null);
//                    break;
//                case 3:
//                    EnrollmentUI.main(null);
//                    break;
//                case 4:
//                    Dashboard.main(null);
//                    break;
//                case 5:
//                    exit = true;
//                    ManiApplication.currentUser = null;
//                    System.out.println("Đã đăng xuất!");
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 3!");
//            }
//        } while (!exit);
//    }
//}


package ra.edu.presentation;

import ra.edu.ManiApplication;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class AdminUI {
    // ANSI Color Codes
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(CYAN + "============================= ADMIN UI ==============================" + RESET);
            System.out.println(CYAN + "╔════════════╦══════════════════════════════════════════════════════╗");
            System.out.printf("║ %-10s ║ %-52s ║\n", "Lựa chọn", "Mô tả");
            System.out.println("╠════════════╬══════════════════════════════════════════════════════╣");

            System.out.printf("║ %-10s ║ %-52s ║\n", "1", "Quản lý khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "2", "Quản lý học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "3", "Quản lý đăng ký học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "4", "Thống kê học viên theo khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "5", "Đăng xuất");
            System.out.printf("║ %-10s ║ %-52s ║\n", "6", "Thoát chương trình.");
            System.out.println("╚════════════╩══════════════════════════════════════════════════════╝" + RESET);

            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    CourseUI.main(null);
                    break;
                case 2:
                    ManagerStudentUI.main(null);
                    break;
                case 3:
                    EnrollmentUI.main(null);
                    break;
                case 4:
                    Dashboard.main(null);
                    break;
                case 5:
                    exit = true;
                    ManiApplication.currentUser = null;
                    System.out.println(YELLOW + "Đã đăng xuất!" + RESET);
                    break;
                default:
                    System.out.println(RED + "Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 6!" + RESET);
            }
        } while (!exit);
    }
}