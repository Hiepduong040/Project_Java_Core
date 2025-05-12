////package ra.edu.presentation;
////
////import ra.edu.business.model.Course;
////import ra.edu.business.service.Course.CourseServiceImp;
////import ra.edu.validate.CourseValidator;
////import ra.edu.validate.Validator;
////import ra.edu.validate.ValidatorChoice;
////
////import java.util.Scanner;
////
////public class CourseUI {
////    private static CourseServiceImp courseServiceImp;
////
////    public CourseUI() {
////        courseServiceImp = new CourseServiceImp();
////    }
////
////    public static void main(String[] args) {
////        CourseUI courseUI = new CourseUI();
////        Scanner scanner = new Scanner(System.in);
////        boolean exit = false;
////        do {
////            System.out.println("================== Quản lý khóa học ==================");
////            System.out.println("1. Hiển thị danh sách khóa học");
////            System.out.println("2. Thêm mới khóa học");
////            System.out.println("3. Chỉnh sửa thông tin khóa học");
////            System.out.println("4. Xóa khóa học");
////            System.out.println("5. Tìm kiếm khóa học");
////            System.out.println("6. Sắp xếp khóa học");
////            System.out.println("7. Quay về menu chính");
////            int choice = ValidatorChoice.validater(scanner);
////            switch (choice) {
////                case 1:
////                    displayListCoursePagination(scanner);
////                    break;
////                case 2:
////                    addNewCourse(scanner);
////                    break;
////                case 3:
////                    updateCourse(scanner);
////                    break;
////                case 4:
////                    deleteCourse(scanner);
////                    break;
////                case 5:
////                    searchCourse(scanner);
////                    break;
////                case 6:
////                    sortCourse(scanner);
////                    break;
////                case 7:
////                    exit = true;
////                    break;
////                default:
////                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn từ 1 - 7!");
////            }
////        } while (!exit);
////    }
////
////    public static void displayListCoursePagination(Scanner scanner) {
////        courseServiceImp.listCoursesPagination(scanner);
////    }
////
////    public static void addNewCourse(Scanner scanner) {
////        Course course = new Course();
////        course.setName(CourseValidator.validateName(scanner));
////        course.setDuration(CourseValidator.validateDuration(scanner));
////        course.setInstructor(CourseValidator.validateInstructor(scanner));
////        if (courseServiceImp.save(course)) {
////            System.out.println("Thêm khóa học thành công");
////        } else {
////            System.out.println("Thêm khóa học thất bại!");
////        }
////    }
////
////    public static void updateCourse(Scanner scanner) {
////        int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã khóa học cần cập nhật: ", "Mã khóa học");
////        Course course = courseServiceImp.findCourseById(inputId);
////        if (course == null) {
////            System.out.println("Không tìm thấy khóa học");
////        } else {
////            boolean exit = false;
////            System.out.println("Khóa học muốn cập nhật: ");
////            System.out.println(course.toString());
////            do {
////                System.out.println("========================== MENU cập nhật khóa học =====================");
////                System.out.println("1. Cập nhật tên khóa học");
////                System.out.println("2. Cập nhật thời lượng");
////                System.out.println("3. Cập nhật giảng viên");
////                System.out.println("4. Thoát");
////                int choice = ValidatorChoice.validater(scanner);
////                switch (choice) {
////                    case 1:
////                        course.setName(CourseValidator.validateName(scanner));
////                        break;
////                    case 2:
////                        course.setDuration(CourseValidator.validateDuration(scanner));
////                        break;
////                    case 3:
////                        course.setInstructor(CourseValidator.validateInstructor(scanner));
////                        break;
////                    case 4:
////                        exit = true;
////                        break;
////                }
////            } while (!exit);
////            if (courseServiceImp.update(course)) {
////                System.out.println("Cập nhật thành công");
////            } else {
////                System.out.println("Cập nhật thất bại!");
////            }
////        }
////    }
////
////    public static void deleteCourse(Scanner scanner) {
////        int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã khóa học cần xóa: ", "Mã khóa học");
////        Course course = courseServiceImp.findCourseById(inputId);
////        if (course == null) {
////            System.out.println("Không tìm thấy khóa học muốn xóa");
////        } else {
////            System.out.println(course.toString());
////            boolean exit = false;
////            while (!exit) {
////                System.out.println("Bạn có chắc chắn muốn xóa khóa học này không(y/n)");
////                System.out.print("Lựa chọn: ");
////                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
////                switch (choice) {
////                    case 'y':
////                        if (courseServiceImp.delete(course)) {
////                            System.out.println("Xóa thành công");
////                        } else {
////                            System.out.println("Xóa thất bại");
////                        }
////                        exit = true;
////                        break;
////                    case 'n':
////                        System.out.println("Đã hủy xóa");
////                        exit = true;
////                        break;
////                    default:
////                        System.out.println("Lựa chọn không hợp lệ vui lòng chọn y/n");
////                }
////            }
////        }
////    }
////
////    public static void searchCourse(Scanner scanner) {
////        String search = Validator.validateString(scanner, 0, 100, "Nhập tên khóa học muốn tìm: ", "Tên khóa học");
////        courseServiceImp.finCourseByNamePagination(scanner, search);
////    }
////
////    public static void sortCourse(Scanner scanner) {
////        boolean exit = false;
////        do {
////            System.out.println("Sắp xếp khóa học");
////            System.out.println("1. Theo tên");
////            System.out.println("2. Theo mã khóa học");
////            int choice = ValidatorChoice.validater(scanner);
////            switch (choice) {
////                case 1:
////                    courseServiceImp.sortByName(scanner);
////                    exit = true;
////                    break;
////                case 2:
////                    courseServiceImp.sortById(scanner);
////                    exit = true;
////                    break;
////                default:
////                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
////            }
////        } while (!exit);
////    }
////}
//
//
//
//package ra.edu.presentation;
//
//import ra.edu.business.model.Course;
//import ra.edu.business.service.Course.CourseServiceImp;
//import ra.edu.validate.CourseValidator;
//import ra.edu.validate.Validator;
//import ra.edu.validate.ValidatorChoice;
//
//import java.util.Scanner;
//
//public class CourseUI {
//    private static CourseServiceImp courseServiceImp;
//
//    public CourseUI() {
//        courseServiceImp = new CourseServiceImp();
//    }
//
//    public static void main(String[] args) {
//        CourseUI courseUI = new CourseUI();
//        Scanner scanner = new Scanner(System.in);
//        boolean exit = false;
//        do {
//            System.out.println("================== Quản lý khóa học ==================");
//            System.out.println("1. Hiển thị danh sách khóa học");
//            System.out.println("2. Thêm mới khóa học");
//            System.out.println("3. Chỉnh sửa thông tin khóa học");
//            System.out.println("4. Xóa khóa học");
//            System.out.println("5. Tìm kiếm khóa học");
//            System.out.println("6. Sắp xếp khóa học");
//            System.out.println("7. Quay về menu chính");
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    displayListCoursePagination(scanner);
//                    break;
//                case 2:
//                    addNewCourse(scanner);
//                    break;
//                case 3:
//                    updateCourse(scanner);
//                    break;
//                case 4:
//                    deleteCourse(scanner);
//                    break;
//                case 5:
//                    searchCourse(scanner);
//                    break;
//                case 6:
//                    sortCourse(scanner);
//                    break;
//                case 7:
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn từ 1 - 7!");
//            }
//        } while (!exit);
//    }
//
//    public static void displayListCoursePagination(Scanner scanner) {
//        courseServiceImp.listCoursesPagination(scanner);
//    }
//
//    public static void addNewCourse(Scanner scanner) {
//        Course course = new Course();
//        course.setName(CourseValidator.validateName(scanner));
//        course.setDuration(CourseValidator.validateDuration(scanner));
//        course.setInstructor(CourseValidator.validateInstructor(scanner));
//        if (courseServiceImp.save(course)) {
//            System.out.println("Thêm khóa học thành công");
//        } else {
//            System.out.println("Thêm khóa học thất bại!");
//        }
//    }
//
//    public static void updateCourse(Scanner scanner) {
//        int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã khóa học cần cập nhật: ", "Mã khóa học");
//        Course course = courseServiceImp.findCourseById(inputId);
//        if (course == null) {
//            System.out.println("Không tìm thấy khóa học");
//        } else {
//            boolean exit = false;
//            System.out.println("Khóa học muốn cập nhật: ");
//            System.out.println(course.toString());
//            do {
//                System.out.println("========================== MENU cập nhật khóa học =====================");
//                System.out.println("1. Cập nhật tên khóa học");
//                System.out.println("2. Cập nhật thời lượng");
//                System.out.println("3. Cập nhật giảng viên");
//                System.out.println("4. Thoát");
//                int choice = ValidatorChoice.validater(scanner);
//                switch (choice) {
//                    case 1:
//                        course.setName(CourseValidator.validateName(scanner, course.getId()));
//                        break;
//                    case 2:
//                        course.setDuration(CourseValidator.validateDuration(scanner));
//                        break;
//                    case 3:
//                        course.setInstructor(CourseValidator.validateInstructor(scanner));
//                        break;
//                    case 4:
//                        exit = true;
//                        break;
//                    default:
//                        System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
//                }
//            } while (!exit);
//            if (courseServiceImp.update(course)) {
//                System.out.println("Cập nhật thành công");
//            } else {
//                System.out.println("Cập nhật thất bại! Vui lòng kiểm tra lại dữ liệu nhập.");
//            }
//        }
//    }
//
//    public static void deleteCourse(Scanner scanner) {
//        int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã khóa học cần xóa: ", "Mã khóa học");
//        Course course = courseServiceImp.findCourseById(inputId);
//        if (course == null) {
//            System.out.println("Không tìm thấy khóa học muốn xóa");
//        } else {
//            System.out.println(course.toString());
//            boolean exit = false;
//            while (!exit) {
//                System.out.println("Bạn có chắc chắn muốn xóa khóa học này không(y/n)");
//                System.out.print("Lựa chọn: ");
//                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
//                switch (choice) {
//                    case 'y':
//                        if (courseServiceImp.delete(course)) {
//                            System.out.println("Xóa thành công");
//                        } else {
//                            System.out.println("Xóa thất bại");
//                        }
//                        exit = true;
//                        break;
//                    case 'n':
//                        System.out.println("Đã hủy xóa");
//                        exit = true;
//                        break;
//                    default:
//                        System.out.println("Lựa chọn không hợp lệ vui lòng chọn y/n");
//                }
//            }
//        }
//    }
//
//    public static void searchCourse(Scanner scanner) {
//        String search = Validator.validateString(scanner, 0, 100, "Nhập tên khóa học muốn tìm: ", "Tên khóa học");
//        courseServiceImp.finCourseByNamePagination(scanner, search);
//    }
//
//    public static void sortCourse(Scanner scanner) {
//        boolean exit = false;
//        do {
//            System.out.println("Sắp xếp khóa học");
//            System.out.println("1. Theo tên");
//            System.out.println("2. Theo mã khóa học");
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    courseServiceImp.sortByName(scanner);
//                    exit = true;
//                    break;
//                case 2:
//                    courseServiceImp.sortById(scanner);
//                    exit = true;
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
//            }
//        } while (!exit);
//    }
//}

package ra.edu.presentation;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseServiceImp;
import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

import static ra.edu.presentation.AdminUI.*;

public class CourseUI {
    private static CourseServiceImp courseServiceImp;

    public CourseUI() {
        courseServiceImp = new CourseServiceImp();
    }

    public static void main(String[] args) {
        CourseUI courseUI = new CourseUI();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        do {
            System.out.println(CYAN + "========================= QUẢN LÝ KHÓA HỌC ==========================" + RESET);
            System.out.println(CYAN + "╔════════════╦══════════════════════════════════════════════════════╗");
            System.out.printf("║ %-10s ║ %-52s ║\n", "Lựa chọn", "Mô tả");
            System.out.println("╠════════════╬══════════════════════════════════════════════════════╣");

            System.out.printf("║ %-10s ║ %-52s ║\n", "1", "Hiển thị danh sách khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "2", "Thêm mới khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "3", "Chỉnh sửa thông tin khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "4", "Xóa khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "5", "Tìm kiếm khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "6", "Sắp xếp khóa học");
            System.out.printf("║ %-10s ║ %-52s ║\n", "7", "Quay về menu chính");
            System.out.println("╚════════════╩══════════════════════════════════════════════════════╝" + RESET);

            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayListCoursePagination(scanner);
                    break;
                case 2:
                    addNewCourse(scanner);
                    break;
                case 3:
                    updateCourse(scanner);
                    break;
                case 4:
                    deleteCourse(scanner);
                    break;
                case 5:
                    searchCourse(scanner);
                    break;
                case 6:
                    sortCourse(scanner);
                    break;
                case 7:
                    exit = true;
                    break;
                default:
                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng chọn từ 1 - 7!" + RESET);
            }
        } while (!exit);
    }

    // Remaining methods omitted for brevity...


    public static void displayListCoursePagination(Scanner scanner) {
        courseServiceImp.listCoursesPagination(scanner);
    }

    public static void addNewCourse(Scanner scanner) {
        Course course = new Course();
        course.setName(CourseValidator.validateName(scanner));
        course.setDuration(CourseValidator.validateDuration(scanner));
        course.setInstructor(CourseValidator.validateInstructor(scanner));
        if (courseServiceImp.save(course)) {
            System.out.println(GREEN + "Thêm khóa học thành công" + RESET);
        } else {
            System.out.println(RED + "Thêm khóa học thất bại!" + RESET);
        }
    }

    public static void updateCourse(Scanner scanner) {
        int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã khóa học cần cập nhật: ", "Mã khóa học");
        Course course = courseServiceImp.findCourseById(inputId);
        if (course == null) {
            System.out.println(RED + "Không tìm thấy khóa học" + RESET);
        } else {
            boolean exit = false;
            System.out.println("Khóa học muốn cập nhật: ");
            System.out.println(course.toString());
            do {
                System.out.println("========================== MENU cập nhật khóa học =====================");
                System.out.println(GREEN + "1. Cập nhật tên khóa học" + RESET);
                System.out.println(GREEN + "2. Cập nhật thời lượng" + RESET);
                System.out.println(GREEN + "3. Cập nhật giảng viên" + RESET);
                System.out.println(GREEN + "4. Thoát" + RESET);
                int choice = ValidatorChoice.validater(scanner);
                switch (choice) {
                    case 1:
                        course.setName(CourseValidator.validateName(scanner, course.getId()));
                        break;
                    case 2:
                        course.setDuration(CourseValidator.validateDuration(scanner));
                        break;
                    case 3:
                        course.setInstructor(CourseValidator.validateInstructor(scanner));
                        break;
                    case 4:
                        exit = true;
                        break;
                    default:
                        System.out.println(RED + "Lựa chọn không hợp lệ vui lòng nhập lại!" + RESET);
                }
            } while (!exit);
            if (courseServiceImp.update(course)) {
                System.out.println(GREEN + "Cập nhật thành công" + RESET);
            } else {
                System.out.println(RED + "Cập nhật thất bại! Vui lòng kiểm tra lại dữ liệu nhập." + RESET);
            }
        }
    }

    public static void deleteCourse(Scanner scanner) {
        int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã khóa học cần xóa: ", "Mã khóa học");
        Course course = courseServiceImp.findCourseById(inputId);
        if (course == null) {
            System.out.println(RED + "Không tìm thấy khóa học muốn xóa" + RESET);
        } else {
            System.out.println(course.toString());
            boolean exit = false;
            while (!exit) {
                System.out.println("Bạn có chắc chắn muốn xóa khóa học này không(y/n)");
                System.out.print("Lựa chọn: ");
                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
                switch (choice) {
                    case 'y':
                        if (courseServiceImp.delete(course)) {
                            System.out.println(GREEN + "Xóa thành công" + RESET);
                        } else {
                            System.out.println(RED + "Xóa thất bại" + RESET);
                        }
                        exit = true;
                        break;
                    case 'n':
                        System.out.println(YELLOW + "Đã hủy xóa" + RESET);
                        exit = true;
                        break;
                    default:
                        System.out.println(RED + "Lựa chọn không hợp lệ vui lòng chọn y/n" + RESET);
                }
            }
        }
    }

    public static void searchCourse(Scanner scanner) {
        String search = Validator.validateString(scanner, 0, 100, "Nhập tên khóa học muốn tìm: ", "Tên khóa học");
        courseServiceImp.finCourseByNamePagination(scanner, search);
    }

    public static void sortCourse(Scanner scanner) {
        boolean exit = false;
        do {
            System.out.println(GREEN + "Sắp xếp khóa học" + RESET);
            System.out.println("1. Theo tên");
            System.out.println("2. Theo mã khóa học");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    courseServiceImp.sortByName(scanner);
                    exit = true;
                    break;
                case 2:
                    courseServiceImp.sortById(scanner);
                    exit = true;
                    break;
                default:
                    System.out.println(RED + "Lựa chọn không hợp lệ, vui lòng nhập lại!" + RESET);
            }
        } while (!exit);
    }
}