//package ra.edu.presentation;
//
//import ra.edu.business.service.Statistic.StatisticService;
//import ra.edu.business.service.Statistic.StatisticServiceImp;
//import ra.edu.validate.ValidatorChoice;
//
//import java.util.Scanner;
//
//public class Dashboard {
//    private static StatisticService statisticService;
//
//    public Dashboard() {
//        statisticService = new StatisticServiceImp();
//    }
//
//    public static void main(String[] args) {
//        Dashboard dashboard = new Dashboard();
//        Scanner scanner = new Scanner(System.in);
//        boolean exit = false;
//        do {
//            System.out.println("===================== Thống kê ======================");
//            System.out.println("1. Thống kê tổng số lượng khóa học và học viên");
//            System.out.println("2. Thống kê học viên theo từng khóa học");
//            System.out.println("3. Top 5 khóa học đông học viên nhất");
//            System.out.println("4. Liệt kê khóa học có trên 10 học viên");
//            System.out.println("5. Quay về menu chính");
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    statisticService.countCoursesAndStudents();
//                    break;
//                case 2:
//                    statisticService.countStudentsByCourse(scanner);
//                    break;
//                case 3:
//                    statisticService.topFiveCoursesByStudents();
//                    break;
//                case 4:
//                    statisticService.listCoursesWithMoreThanTenStudents();
//                    break;
//                case 5:
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 5!");
//            }
//        } while (!exit);
//    }
//}

package ra.edu.presentation;

import ra.edu.business.service.statistic.StatisticService;
import ra.edu.business.service.statistic.StatisticServiceImp;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

import static ra.edu.presentation.AdminUI.*;

public class Dashboard {
    private static StatisticService statisticService;

    public Dashboard() {
        statisticService = new StatisticServiceImp();
    }

    public static void main(String[] args) {
        Dashboard dashboard = new Dashboard();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(CYAN + "============================= THỐNG KÊ ==============================" + RESET);
            System.out.println(CYAN + "╔════════════╦══════════════════════════════════════════════════════╗");
            System.out.printf("║ %-10s ║ %-52s ║\n", "Lựa chọn", "Mô tả");
            System.out.println("╠════════════╬══════════════════════════════════════════════════════╣");

            System.out.printf("║ %-10s ║ %-52s ║\n", "1", "Thống kê tổng số khóa học và học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "2", "Thống kê học viên theo từng khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "3", "Top 5 khóa học đông học viên nhất");
            System.out.printf("║ %-10s ║ %-52s ║\n", "4", "Liệt kê khóa học có trên 10 học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "5", "Quay về menu chính");
            System.out.println("╚════════════╩══════════════════════════════════════════════════════╝" + RESET);

            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    statisticService.countCoursesAndStudents();
                    break;
                case 2:
                    statisticService.countStudentsByCourse(scanner);
                    break;
                case 3:
                    statisticService.topFiveCoursesByStudents();
                    break;
                case 4:
                    statisticService.listCoursesWithMoreThanTenStudents();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println(RED + "Lựa chọn không hợp lệ, vui lòng chọn từ 1 - 5!" + RESET);
            }
        } while (!exit);
    }
}