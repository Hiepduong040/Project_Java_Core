//package ra.edu.business.service.Course;
//
//import ra.edu.business.dao.Course.CourseDAOImp;
//import ra.edu.business.model.Course;
//import ra.edu.business.model.Pagination;
//import ra.edu.validate.Validator;
//import ra.edu.validate.ValidatorChoice;
//
//import java.time.format.DateTimeFormatter;
//import java.util.List;
//import java.util.Scanner;
//
//public class CourseServiceImp implements CourseService {
//    private CourseDAOImp courseDAOImp;
//
//    public CourseServiceImp() {
//        courseDAOImp = new CourseDAOImp();
//    }
//
//    Pagination pagination = new Pagination();
//
//    @Override
//    public Course findByName(String name) {
//        return courseDAOImp.findByName(name);
//    }
//
//    public List<Course> listCourses(String search) {
//        if (search == null) {
//            return courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage());
//        } else {
//            return courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
//        }
//    }
//
//private void displayCourseListPagination(Scanner scanner, String search, String sortBy, String sortOrder) {
//    boolean Exit = false;
//    pagination.setCurrentpage(1);
//    pagination.setPagesize(5);
//
//    // Define color constants
//    final String BLUE = "\u001B[34m";
//    final String CYAN = "\u001B[36m";
//    final String WHITE = "\u001B[37m";
//    final String RED = "\u001B[31m";
//    final String YELLOW = "\u001B[33m";
//    final String GREEN = "\u001B[32m";
//    final String RESET = "\u001B[0m";
//
//    do {
//        List<Course> listPagination = null;
//        // Calculate total results before pagination
//        int totalResults = search == null ? totalCourse() : courseDAOImp.findByNamePagianation(search, Integer.MAX_VALUE, 1).size();
//        int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
//        pagination.setTotalpages(totalPages);
//
//        if (sortBy == null) {
//            listPagination = (search == null) ?
//                    courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage()) :
//                    courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
//        } else {
//            listPagination = (sortBy.equals("NAME")) ?
//                    courseDAOImp.sortByName(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder) :
//                    courseDAOImp.sortById(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder);
//        }
//
//        // Display table header
//        System.out.println(BLUE + "╠═════╦════════════════════════════════════════════╦══════════╦═══════════════════════════════╦════════════╣" + RESET);
//        System.out.printf(CYAN + "║ %-3s ║ %-42s ║ %-8s ║ %-29s ║ %-10s ║\n" + RESET,
//                "ID", "Tên khóa học", "Số buổi", "Giảng viên", "Ngày tạo");
//        System.out.println(BLUE + "╠═════╬════════════════════════════════════════════╬══════════╬═══════════════════════════════╬════════════╣" + RESET);
//
//        if (listPagination.isEmpty()) {
//            System.out.printf(RED + "║ %-94s ║\n" + RESET, "Không có khóa học nào.");
//        } else {
//            for (Course course : listPagination) {
//                System.out.printf(WHITE + "║ %-3d ║ %-42s ║ %-8d ║ %-29s ║ %-10s ║\n" + RESET,
//                        course.getId(), course.getName(), course.getDuration(),
//                        course.getInstructor(), course.getCreate_at().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//            }
//        }
//
//        System.out.println(BLUE + "╚═════╩════════════════════════════════════════════╩══════════╩═══════════════════════════════╩════════════╝" + RESET);
//
//        // Display pagination
//        if (totalResults <= pagination.getPagesize()) {
//            // Case: ≤ pagesize results: only show page 1
//            System.out.println("Trang: " + YELLOW + "1" + RESET);
//            System.out.println(GREEN + "1. Chọn trang" + RESET);
//            System.out.println(RED + "2. Thoát" + RESET);
//            System.out.print(CYAN + "Lựa chọn: " + RESET);
//        } else {
//            // Case: multiple pages
//            System.out.print(CYAN + "Trang: " + RESET);
//            if (pagination.getCurrentpage() > 1) {
//                System.out.print(GREEN + "« Previous" + RESET + "  ");
//                if (pagination.getCurrentpage() >= 3) System.out.print(WHITE + "..." + RESET + " ");
//                System.out.print(WHITE + (pagination.getCurrentpage() - 1) + RESET);
//            }
//            System.out.print(YELLOW + "  [" + pagination.getCurrentpage() + "]  " + RESET);
//            if (pagination.getCurrentpage() < totalPages) {
//                System.out.print(WHITE + (pagination.getCurrentpage() + 1) + RESET);
//                if (totalPages - pagination.getCurrentpage() >= 2) System.out.print(" " + WHITE + "..." + RESET);
//                System.out.print("  " + GREEN + "Next »" + RESET);
//            }
//            System.out.println();
//            if (pagination.getCurrentpage() > 1) System.out.println(GREEN + "P. Trang trước" + RESET);
//            if (pagination.getCurrentpage() < totalPages) System.out.println(GREEN + "N. Trang tiếp" + RESET);
//            System.out.println(GREEN + "1. Chọn trang" + RESET);
//            System.out.println(RED + "2. Thoát" + RESET);
//            System.out.print(CYAN + "Lựa chọn: " + RESET);
//        }
//
//        while (true) {
//            String input = scanner.nextLine().trim();
//            if (input.isEmpty()) {
//                System.out.println(RED + "Lựa chọn không được để trống! " + RESET);
//                System.out.println(CYAN + "Vui lòng nhập lại:" + RESET);
//                continue;
//            }
//            char choice = Character.toUpperCase(input.charAt(0));
//            switch (choice) {
//                case '1':
//                    int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
//                    pagination.setCurrentpage(page);
//                    break;
//                case '2':
//                    Exit = true;
//                    break;
//                case 'P':
//                    if (pagination.getCurrentpage() > 1)
//                        pagination.setCurrentpage(pagination.getCurrentpage() - 1);
//                    break;
//                case 'N':
//                    if (pagination.getCurrentpage() < totalPages)
//                        pagination.setCurrentpage(pagination.getCurrentpage() + 1);
//                    break;
//                default:
//                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
//                    continue;
//            }
//            break;
//        }
//    } while (!Exit);
//}
//
//    @Override
//    public void listCoursesPagination(Scanner scanner) {
//        displayCourseListPagination(scanner, null, null, null);
//    }
//
//    @Override
//    public int totalCourse() {
//        return courseDAOImp.totalCount();
//    }
//
//    @Override
//    public Course findCourseById(int id) {
//        return courseDAOImp.findById(id);
//    }
//
//    @Override
//    public List<Course> findAll() {
//        return courseDAOImp.findAll();
//    }
//
//    @Override
//    public boolean save(Course course) {
//        return courseDAOImp.save(course);
//    }
//
//    @Override
//    public boolean update(Course course) {
//        return courseDAOImp.update(course);
//    }
//
//    @Override
//    public boolean delete(Course course) {
//        return courseDAOImp.delete(course);
//    }
//
//    public void finCourseByNamePagination(Scanner scanner, String search) {
//        displayCourseListPagination(scanner, search, null, null);
//    }
//
//    @Override
//    public void sortByName(Scanner scanner) {
//        boolean Exit = false;
//        do {
//            System.out.println("Sắp xếp: ");
//            System.out.println("1. Tăng dần");
//            System.out.println("2. Giảm dần");
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    displayCourseListPagination(scanner, null, "NAME", "asc");
//                    Exit = true;
//                    break;
//                case 2:
//                    displayCourseListPagination(scanner, null, "NAME", "desc");
//                    Exit = true;
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
//            }
//        } while (!Exit);
//    }
//
//    @Override
//    public void sortById(Scanner scanner) {
//        boolean Exit = false;
//        do {
//            System.out.println("Sắp xếp: ");
//            System.out.println("1. Tăng dần");
//            System.out.println("2. Giảm dần");
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    displayCourseListPagination(scanner, null, "ID", "asc");
//                    Exit = true;
//                    break;
//                case 2:
//                    displayCourseListPagination(scanner, null, "ID", "desc");
//                    Exit = true;
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
//            }
//        } while (!Exit);
//    }
//}
//




package ra.edu.business.service.course;

import ra.edu.business.dao.course.CourseDAOImp;
import ra.edu.business.model.Course;
import ra.edu.business.model.Pagination;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CourseServiceImp implements CourseService {
    private CourseDAOImp courseDAOImp;

    public CourseServiceImp() {
        courseDAOImp = new CourseDAOImp();
    }

    Pagination pagination = new Pagination();

    @Override
    public Course findByName(String name) {
        return courseDAOImp.findByName(name);
    }

//    public List<Course> listCourses(String search) {
//        if (search == null) {
//            return courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage());
//        } else {
//            return courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
//
//        }
//    }
public List<Course> listCourses(String search, Pagination pagination) {
    if (search == null) {
        return courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage());
    } else {
        return courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
    }
}
    private void displayCourseListPagination(Scanner scanner, String search, String sortBy, String sortOrder) {
        boolean Exit = false;
        pagination.setCurrentpage(1);
        pagination.setPagesize(5);

        // Define color constants
        final String BLUE = "\u001B[34m";
        final String CYAN = "\u001B[36m";
        final String WHITE = "\u001B[37m";
        final String RED = "\u001B[31m";
        final String YELLOW = "\u001B[33m";
        final String GREEN = "\u001B[32m";
        final String RESET = "\u001B[0m";

        do {
            List<Course> listPagination = null;
            // Calculate total results before pagination
            int totalResults = search == null ? totalCourse() : courseDAOImp.findByNamePagianation(search, Integer.MAX_VALUE, 1).size();
            int totalPages = (int) Math.ceil((double) totalResults / pagination.getPagesize());
            pagination.setTotalpages(totalPages);

            if (sortBy == null) {
                listPagination = (search == null) ?
                        courseDAOImp.listPagination(pagination.getPagesize(), pagination.getCurrentpage()) :
                        courseDAOImp.findByNamePagianation(search, pagination.getPagesize(), pagination.getCurrentpage());
            } else {
                listPagination = (sortBy.equals("NAME")) ?
                        courseDAOImp.sortByName(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder) :
                        courseDAOImp.sortById(pagination.getPagesize(), pagination.getCurrentpage(), sortOrder);
            }

            // Display table header
            System.out.println(BLUE + "╠═════╦════════════════════════════════════════════╦══════════╦═══════════════════════════════╦════════════╣" + RESET);
            System.out.printf(CYAN + "║ %-3s ║ %-42s ║ %-8s ║ %-29s ║ %-10s ║\n" + RESET,
                    "ID", "Tên khóa học", "Số buổi", "Giảng viên", "Ngày tạo");
            System.out.println(BLUE + "╠═════╬════════════════════════════════════════════╬══════════╬═══════════════════════════════╬════════════╣" + RESET);

            if (listPagination.isEmpty()) {
                System.out.printf(RED + "║ %-94s ║\n" + RESET, "Không có khóa học nào.");
            } else {
                for (Course course : listPagination) {
                    System.out.printf(WHITE + "║ %-3d ║ %-42s ║ %-8d ║ %-29s ║ %-10s ║\n" + RESET,
                            course.getId(), course.getName(), course.getDuration(),
                            course.getInstructor(), course.getCreate_at().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                }
            }

            System.out.println(BLUE + "╚═════╩════════════════════════════════════════════╩══════════╩═══════════════════════════════╩════════════╝" + RESET);

            // Display pagination
            if (totalResults <= pagination.getPagesize()) {
                // Case: ≤ pagesize results: only show page 1
                System.out.println("Trang: " + YELLOW + "1" + RESET);
                System.out.println(GREEN + "1. Chọn trang" + RESET);
                System.out.println(RED + "2. Thoát" + RESET);
                System.out.print(CYAN + "Lựa chọn: " + RESET);
            } else {
                // Case: multiple pages
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
                    System.out.println(RED + "Lựa chọn không được để trống! " + RESET);
                    System.out.println(CYAN + "Vui lòng nhập lại:" + RESET);
                    continue;
                }
                char choice = Character.toUpperCase(input.charAt(0));
                switch (choice) {
                    case '1':
                        int page = Validator.validateInt(scanner, 1, totalPages, CYAN + "Nhập trang: " + RESET, "Trang");
                        pagination.setCurrentpage(page);
                        break;
                    case '2':
                        Exit = true;
                        break;
                    case 'P':
                        if (pagination.getCurrentpage() > 1)
                            pagination.setCurrentpage(pagination.getCurrentpage() - 1);
                        break;
                    case 'N':
                        if (pagination.getCurrentpage() < totalPages)
                            pagination.setCurrentpage(pagination.getCurrentpage() + 1);
                        break;
                    default:
                        System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
                        continue;
                }
                break;
            }
        } while (!Exit);
    }

    @Override
    public void listCoursesPagination(Scanner scanner) {
        displayCourseListPagination(scanner, null, null, null);
    }

    @Override
    public int totalCourse() {
        return courseDAOImp.totalCount();
    }

    @Override
    public Course findCourseById(int id) {
        return courseDAOImp.findById(id);
    }

    @Override
    public List<Course> findAll() {
        return courseDAOImp.findAll();
    }

    @Override
    public boolean save(Course course) {
        return courseDAOImp.save(course);
    }

    @Override
    public boolean update(Course course) {
        return courseDAOImp.update(course);
    }

    @Override
    public boolean delete(Course course) {
        return courseDAOImp.delete(course);
    }

    public void finCourseByNamePagination(Scanner scanner, String search) {
        displayCourseListPagination(scanner, search, null, null);
    }

    @Override
    public void sortByName(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayCourseListPagination(scanner, null, "NAME", "asc");
                    Exit = true;
                    break;
                case 2:
                    displayCourseListPagination(scanner, null, "NAME", "desc");
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (!Exit);
    }

    @Override
    public void sortById(Scanner scanner) {
        boolean Exit = false;
        do {
            System.out.println("Sắp xếp: ");
            System.out.println("1. Tăng dần");
            System.out.println("2. Giảm dần");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayCourseListPagination(scanner, null, "ID", "asc");
                    Exit = true;
                    break;
                case 2:
                    displayCourseListPagination(scanner, null, "ID", "desc");
                    Exit = true;
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
            }
        } while (!Exit);
    }
}

