//package ra.edu.business.service.Statistic;
//
//import ra.edu.business.dao.Statistic.StatisticDAO;
//import ra.edu.business.dao.Statistic.StatisticDAOImp;
//import ra.edu.business.model.Statistic;
//import ra.edu.validate.Validator;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class StatisticServiceImp implements StatisticService {
//    private StatisticDAO statisticDAO;
//
//    public StatisticServiceImp() {
//        statisticDAO = new StatisticDAOImp();
//    }
//
//    @Override
//    public void countCoursesAndStudents() {
//        Statistic statistic = statisticDAO.countCoursesAndStudents();
//        if (statistic != null) {
//            System.out.println("Thống kê tổng số:");
//            System.out.println("Tổng số khóa học: " + statistic.getTotalCourses());
//            System.out.println("Tổng số học viên: " + statistic.getTotalStudents());
//        } else {
//            System.out.println("Không thể lấy dữ liệu thống kê!");
//        }
//    }
//
//    @Override
//    public void countStudentsByCourse() {
//        List<Statistic> statistics = statisticDAO.listAllCoursesWithStudentCount();
//        if (!statistics.isEmpty()) {
//            System.out.println("Thống kê học viên theo khóa học:");
//            System.out.println("--------------------------------------------------------------------------------");
//            System.out.printf("%-10s | %-40s | %-15s\n", "Mã KH", "Tên khóa học", "Số học viên");
//            System.out.println("--------------------------------------------------------------------------------");
//            for (Statistic statistic : statistics) {
//                System.out.printf("%-10d | %-40s | %-15d\n",
//                        statistic.getCourseId(), statistic.getCourseName(), statistic.getStudentCount());
//            }
//            System.out.println("--------------------------------------------------------------------------------");
//        } else {
//            System.out.println("Không có khóa học nào để hiển thị!");
//        }
//    }
//
//    @Override
//    public void topFiveCoursesByStudents() {
//        List<Statistic> statistics = statisticDAO.topFiveCoursesByStudents();
//        if (!statistics.isEmpty()) {
//            System.out.println("Top 5 khóa học đông học viên nhất:");
//            System.out.println("--------------------------------------------------------------------------------");
//            System.out.printf("%-10s | %-40s | %-15s\n", "Mã KH", "Tên khóa học", "Số học viên");
//            System.out.println("--------------------------------------------------------------------------------");
//            for (Statistic statistic : statistics) {
//                System.out.printf("%-10d | %-40s | %-15d\n",
//                        statistic.getCourseId(), statistic.getCourseName(), statistic.getStudentCount());
//            }
//            System.out.println("--------------------------------------------------------------------------------");
//        } else {
//            System.out.println("Không có dữ liệu để hiển thị!");
//        }
//    }
//
//    @Override
//    public void listCoursesWithMoreThanTenStudents() {
//        List<Statistic> statistics = statisticDAO.listCoursesWithMoreThanTenStudents();
//        if (!statistics.isEmpty()) {
//            System.out.println("Danh sách khóa học có trên 10 học viên:");
//            System.out.println("--------------------------------------------------------------------------------");
//            System.out.printf("%-10s | %-40s | %-15s\n", "Mã KH", "Tên khóa học", "Số học viên");
//            System.out.println("--------------------------------------------------------------------------------");
//            for (Statistic statistic : statistics) {
//                System.out.printf("%-10d | %-40s | %-15d\n",
//                        statistic.getCourseId(), statistic.getCourseName(), statistic.getStudentCount());
//            }
//            System.out.println("--------------------------------------------------------------------------------");
//        } else {
//            System.out.println("Không có khóa học nào có trên 10 học viên!");
//        }
//    }
//}
package ra.edu.business.service.statistic;

import ra.edu.business.dao.statistic.StatisticDAO;
import ra.edu.business.dao.statistic.StatisticDAOImp;
import ra.edu.business.model.Pagination;
import ra.edu.business.model.Statistic;
import ra.edu.validate.Validator;

import java.util.List;
import java.util.Scanner;

public class StatisticServiceImp implements StatisticService {
    private StatisticDAO statisticDAO;
    private Pagination pagination;

    public StatisticServiceImp() {
        statisticDAO = new StatisticDAOImp();
        pagination = new Pagination();
    }

    @Override
    public void countCoursesAndStudents() {
        Statistic statistic = statisticDAO.countCoursesAndStudents();
        if (statistic != null) {
            System.out.println("Thống kê tổng số:");
            System.out.println("Tổng số khóa học: " + statistic.getTotalCourses());
            System.out.println("Tổng số học viên: " + statistic.getTotalStudents());
        } else {
            System.out.println("Không thể lấy dữ liệu thống kê!");
        }
    }

    @Override
    public void countStudentsByCourse(Scanner scanner) {
        boolean exit = false;
        pagination.setCurrentpage(1); // Sửa CurrentPage thành currentpage
        pagination.setPagesize(5);   // Sửa PageSize thành pagesize

        do {
            // Tính tổng số khóa học
            int totalCourses = statisticDAO.countTotalCourses();
            pagination.setTotalpages(totalCourses); // Truyền totalCourses thay vì totalPages

            // Lấy danh sách khóa học phân trang
            List<Statistic> statistics = statisticDAO.listAllCoursesWithStudentCount(
                    pagination.getPagesize(), pagination.getCurrentpage());

            if (!statistics.isEmpty()) {
                System.out.println("Thống kê học viên theo khóa học:");
                System.out.println("--------------------------------------------------------------------------------");
                System.out.printf("%-10s | %-40s | %-15s\n", "Mã KH", "Tên khóa học", "Số học viên");
                System.out.println("--------------------------------------------------------------------------------");
                for (Statistic statistic : statistics) {
                    System.out.printf("%-10d | %-40s | %-15d\n",
                            statistic.getCourseId(), statistic.getCourseName(), statistic.getStudentCount());
                }
                System.out.println("--------------------------------------------------------------------------------");

                // Hiển thị phân trang
                if (totalCourses <= pagination.getPagesize()) {
                    // Trường hợp ≤ pagesize kết quả: chỉ hiển thị trang 1
                    System.out.println("Trang: \u001B[33m1\u001B[0m");
                    System.out.println("1. Chọn trang");
                    System.out.println("2. Thoát");
                    System.out.print("Lựa chọn: ");
                } else {
                    // Trường hợp có nhiều trang
                    System.out.print("Trang: ");
                    if (pagination.getCurrentpage() > 1) {
                        System.out.print("Previous      ");
                        if (pagination.getCurrentpage() >= 3) System.out.print("... ");
                        System.out.print(pagination.getCurrentpage() - 1);
                    }
                    System.out.print("\u001B[33m    " + pagination.getCurrentpage() + "     \u001B[0m");
                    if (pagination.getCurrentpage() < pagination.getTotalpages()) {
                        System.out.print(" " + (pagination.getCurrentpage() + 1));
                        if (pagination.getTotalpages() - pagination.getCurrentpage() >= 2) System.out.print(" ...");
                        System.out.print("      Next");
                    }
                    System.out.println();
                    if (pagination.getCurrentpage() > 1) System.out.println("P. Trang trước");
                    if (pagination.getCurrentpage() < pagination.getTotalpages()) System.out.println("N. Trang tiếp");
                    System.out.println("1. Chọn trang");
                    System.out.println("2. Thoát");
                    System.out.print("Lựa chọn: ");
                }

                while (true) {
                    String input = scanner.nextLine().trim();
                    if (input.isEmpty()) {
                        System.out.println("Lựa chọn không được để trống! Vui lòng nhập lại:");
                        continue;
                    }
                    char choice = Character.toUpperCase(input.charAt(0));
                    switch (choice) {
                        case '1':
                            int page = Validator.validateInt(scanner, 1, pagination.getTotalpages(), "Nhập trang: ", "Trang");
                            pagination.setCurrentpage(page);
                            break;
                        case '2':
                            exit = true;
                            break;
                        case 'P':
                            if (pagination.getCurrentpage() > 1)
                                pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                            break;
                        case 'N':
                            if (pagination.getCurrentpage() < pagination.getTotalpages())
                                pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                            break;
                        default:
                            System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
                            continue;
                    }
                    break;
                }
            } else {
                System.out.println("Không có khóa học nào để hiển thị!");
                break;
            }
        } while (!exit);
    }

    @Override
    public void topFiveCoursesByStudents() {
        List<Statistic> statistics = statisticDAO.topFiveCoursesByStudents();
        if (!statistics.isEmpty()) {
            System.out.println("Top 5 khóa học đông học viên nhất:");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-40s | %-15s\n", "Mã KH", "Tên khóa học", "Số học viên");
            System.out.println("--------------------------------------------------------------------------------");
            for (Statistic statistic : statistics) {
                System.out.printf("%-10d | %-40s | %-15d\n",
                        statistic.getCourseId(), statistic.getCourseName(), statistic.getStudentCount());
            }
            System.out.println("--------------------------------------------------------------------------------");
        } else {
            System.out.println("Không có dữ liệu để hiển thị!");
        }
    }

    @Override
    public void listCoursesWithMoreThanTenStudents() {
        List<Statistic> statistics = statisticDAO.listCoursesWithMoreThanTenStudents();
        if (!statistics.isEmpty()) {
            System.out.println("Danh sách khóa học có trên 10 học viên:");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.printf("%-10s | %-40s | %-15s\n", "Mã KH", "Tên khóa học", "Số học viên");
            System.out.println("--------------------------------------------------------------------------------");
            for (Statistic statistic : statistics) {
                System.out.printf("%-10d | %-40s | %-15d\n",
                        statistic.getCourseId(), statistic.getCourseName(), statistic.getStudentCount());
            }
            System.out.println("--------------------------------------------------------------------------------");
        } else {
            System.out.println("Không có khóa học nào có trên 10 học viên!");
        }
    }
}