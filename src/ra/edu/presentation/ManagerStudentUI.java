//package ra.edu.presentation;
//
//import ra.edu.business.model.Student;
//import ra.edu.business.service.Student.StudentServiceImp;
//import ra.edu.validate.StudentValidator;
//import ra.edu.validate.Validator;
//import ra.edu.validate.ValidatorChoice;
//
//import java.util.Scanner;
//
//public class ManagerStudentUI {
//    private static StudentServiceImp studentServiceImp;
//    private static StudentValidator studentValidator;
//
//    public ManagerStudentUI() {
//        studentServiceImp = new StudentServiceImp();
//        studentValidator = new StudentValidator();
//    }
//
//    public static void main(String[] args) {
//        ManagerStudentUI studentUI = new ManagerStudentUI();
//        Scanner scanner = new Scanner(System.in);
//        boolean Exit = false;
//        do {
//            System.out.println("================== Quản lý học viên ==================");
//            System.out.println("1. Hiển thị danh sách học viên");
//            System.out.println("2. Thêm mới học viên");
//            System.out.println("3. Chỉnh sửa thông tin học viên");
//            System.out.println("4. Xóa học viên");
//            System.out.println("5. Tìm kiếm học viên");
//            System.out.println("6. Sắp xếp học viên");
//            System.out.println("7. Quay về menu chính");
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    displayListStudentPagination(scanner);
//                    break;
//                case 2:
//                    addNewStudent(scanner);
//                    break;
//                case 3:
//                    updateStudent(scanner);
//                    break;
//                case 4:
//                    deleteStudent(scanner);
//                    break;
//                case 5:
//                    searchStudent(scanner);
//                    break;
//                case 6:
//                    sortStudent(scanner);
//                    break;
//                case 7:
//                    Exit = true;
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ vui lòng chọn từ 1 - 7!");
//            }
//        } while (!Exit);
//    }
//
//    public static void displayListStudentPagination(Scanner scanner) {
//        studentServiceImp.listStudentsPagination(scanner);
//    }
//
//    public static void addNewStudent(Scanner scanner) {
//        Student student = new Student();
//        student.setName(studentValidator.validateName(scanner));
//        student.setDob(studentValidator.validateDob(scanner));
//        student.setEmail(studentValidator.validateEmail(scanner));
//        student.setSex(studentValidator.validateSex(scanner));
//        student.setPhone(studentValidator.validatePhone(scanner));
//        student.setPassword(studentValidator.validatePassword(scanner));
//        if (studentServiceImp.save(student)) {
//            System.out.println("Thêm học viên thành công");
//        } else {
//            System.out.println("Thêm học viên thất bại!");
//        }
//    }
//
//    public static void updateStudent(Scanner scanner) {
//        int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã học viên cần cập nhật: ", "Mã học viên");
//        Student student = studentServiceImp.findById(inputId);
//        if (student == null) {
//            System.out.println("Không tìm thấy học viên");
//        } else {
//            boolean Exit = false;
//            System.out.println("Học viên muốn cập nhật: ");
//            System.out.println(student.toString());
//            do {
//                System.out.println("========================== MENU cập nhật học viên =====================");
//                System.out.println("1. Cập nhật tên học viên");
//                System.out.println("2. Cập nhật ngày sinh");
//                System.out.println("3. Cập nhật email");
//                System.out.println("4. Cập nhật giới tính");
//                System.out.println("5. Cập nhật số điện thoại");
//                System.out.println("6. Thoát");
//                int choice = ValidatorChoice.validater(scanner);
//                switch (choice) {
//                    case 1:
//                        student.setName(studentValidator.validateName(scanner));
//                        break;
//                    case 2:
//                        student.setDob(studentValidator.validateDob(scanner));
//                        break;
//                    case 3:
//                        student.setEmail(studentValidator.validateEmail(scanner));
//                        break;
//                    case 4:
//                        student.setSex(studentValidator.validateSex(scanner));
//                        break;
//                    case 5:
//                        student.setPhone(studentValidator.validatePhone(scanner));
//                        break;
//                    case 6:
//                        Exit = true;
//                        break;
//                }
//            } while (!Exit);
//            if (studentServiceImp.update(student)) {
//                System.out.println("Cập nhật thành công");
//            } else {
//                System.out.println("Cập nhật thất bại!");
//            }
//        }
//    }
//
//    public static void deleteStudent(Scanner scanner) {
//        int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã học viên cần xóa: ", "Mã học viên");
//        Student student = studentServiceImp.findById(inputId);
//        if (student == null) {
//            System.out.println("Không tìm thấy học viên muốn xóa");
//        } else {
//            System.out.println(student.toString());
//            boolean Exit = false;
//            while (!Exit) {
//                System.out.println("Bạn có chắc chắn muốn xóa học viên này không(y/n)");
//                System.out.print("Lựa chọn: ");
//                char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
//                switch (choice) {
//                    case 'y':
//                        if (studentServiceImp.delete(student)) {
//                            System.out.println("Xóa thành công");
//                        } else {
//                            System.out.println("Xóa thất bại");
//                        }
//                        Exit = true;
//                        break;
//                    case 'n':
//                        System.out.println("Đã hủy xóa");
//                        Exit = true;
//                        break;
//                    default:
//                        System.out.println("Lựa chọn không hợp lệ vui lòng chọn y/n");
//                }
//            }
//        }
//    }
//
//    public static void searchStudent(Scanner scanner) {
//        String search = Validator.validateString(scanner, 0, 100, "Nhập tên, email hoặc mã học viên muốn tìm: ", "Tìm kiếm");
//        studentServiceImp.findStudentByNameOrEmailOrId(scanner, search);
//    }
//
//    public static void sortStudent(Scanner scanner) {
//        boolean Exit = false;
//        do {
//            System.out.println("Sắp xếp học viên");
//            System.out.println("1. Theo tên");
//            System.out.println("2. Theo mã học viên");
//            int choice = ValidatorChoice.validater(scanner);
//            switch (choice) {
//                case 1:
//                    studentServiceImp.sortByName(scanner);
//                    Exit = true;
//                    break;
//                case 2:
//                    studentServiceImp.sortById(scanner);
//                    Exit = true;
//                    break;
//                default:
//                    System.out.println("Lựa chọn không hợp lệ, vui lòng nhập lại!");
//            }
//        } while (!Exit);
//    }
//}

package ra.edu.presentation;

import ra.edu.business.model.Student;
import ra.edu.business.service.student.StudentServiceImp;
import ra.edu.validate.StudentValidator;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

import static ra.edu.presentation.AdminUI.*;

public class ManagerStudentUI {
    private static StudentServiceImp studentServiceImp;
    private static StudentValidator studentValidator;

    public ManagerStudentUI() {
        studentServiceImp = new StudentServiceImp();
        studentValidator = new StudentValidator();
    }

    public static void main(String[] args) {
        ManagerStudentUI studentUI = new ManagerStudentUI();
        Scanner scanner = new Scanner(System.in);
        boolean Exit = false;
        do {
            System.out.println(CYAN + "========================= QUẢN LÝ HỌC VIÊN ==========================" + RESET);
            System.out.println(CYAN + "╔════════════╦══════════════════════════════════════════════════════╗");
            System.out.printf("║ %-10s ║ %-52s ║\n", "Lựa chọn", "Mô tả");
            System.out.println("╠════════════╬══════════════════════════════════════════════════════╣");

            System.out.printf("║ %-10s ║ %-52s ║\n", "1", "Hiển thị danh sách học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "2", "Thêm mới học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "3", "Chỉnh sửa thông tin học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "4", "Xóa học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "5", "Tìm kiếm học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "6", "Sắp xếp học viên");
            System.out.printf("║ %-10s ║ %-52s ║\n", "7", "Quay về menu chính");
            System.out.println("╚════════════╩══════════════════════════════════════════════════════╝" + RESET);

            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    displayListStudentPagination(scanner);
                    break;
                case 2:
                    addNewStudent(scanner);
                    break;
                case 3:
                    updateStudent(scanner);
                    break;
                case 4:
                    deleteStudent(scanner);
                    break;
                case 5:
                    searchStudent(scanner);
                    break;
                case 6:
                    sortStudent(scanner);
                    break;
                case 7:
                    Exit = true;
                    break;
                default:
                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng chọn từ 1 - 7!" + RESET);
            }
        } while (!Exit);
    }

    // Remaining methods omitted for brevity...


    public static void displayListStudentPagination(Scanner scanner) {
        studentServiceImp.listStudentsPagination(scanner);
    }

    public static void addNewStudent(Scanner scanner) {
        Student student = new Student();
        student.setName(studentValidator.validateName(scanner));
        student.setDob(studentValidator.validateDob(scanner));
        student.setEmail(studentValidator.validateEmail(scanner));
        student.setSex(studentValidator.validateSex(scanner));
        student.setPhone(studentValidator.validatePhone(scanner));
        student.setPassword(studentValidator.validatePassword(scanner));
        if (studentServiceImp.save(student)) {
            System.out.println(GREEN + "Thêm học viên thành công" + RESET);
        } else {
            System.out.println(RED + "Thêm học viên thất bại!" + RESET);
        }
    }

    public static void updateStudent(Scanner scanner) {
int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã học viên cần cập nhật: ", "Mã học viên");
Student student = studentServiceImp.findById(inputId);
        if (student == null) {
        System.out.println(RED + "Không tìm thấy học viên" + RESET);
        } else {
boolean Exit = false;
            System.out.println("Học viên muốn cập nhật: ");
            System.out.println(student.toString());
        do {
        System.out.println("========================== MENU cập nhật học viên =====================");
                System.out.println(GREEN + "1. Cập nhật tên học viên" + RESET);
                System.out.println(GREEN + "2. Cập nhật ngày sinh" + RESET);
                System.out.println(GREEN + "3. Cập nhật email" + RESET);
                System.out.println(GREEN + "4. Cập nhật giới tính" + RESET);
                System.out.println(GREEN + "5. Cập nhật số điện thoại" + RESET);
                System.out.println(GREEN + "6. Thoát" + RESET);
int choice = ValidatorChoice.validater(scanner);
                switch (choice) {
        case 1:
        student.setName(studentValidator.validateName(scanner));
        break;
        case 2:
        student.setDob(studentValidator.validateDob(scanner));
        break;
        case 3:
        student.setEmail(studentValidator.validateEmail(scanner));
        break;
        case 4:
        student.setSex(studentValidator.validateSex(scanner));
        break;
        case 5:
        student.setPhone(studentValidator.validatePhone(scanner));
        break;
        case 6:
Exit = true;
        break;
default:
        System.out.println(RED + "Lựa chọn không hợp lệ, vui lòng nhập lại!" + RESET);
                }
                        } while (!Exit);
        if (studentServiceImp.update(student)) {
        System.out.println(GREEN + "Cập nhật thành công" + RESET);
            } else {
                    System.out.println(RED + "Cập nhật thất bại!" + RESET);
            }
                    }
                    }

public static void deleteStudent(Scanner scanner) {
    int inputId = Validator.validateInt(scanner, 0, 1000, "Nhập vào mã học viên cần xóa: ", "Mã học viên");
    Student student = studentServiceImp.findById(inputId);
    if (student == null) {
        System.out.println(RED + "Không tìm thấy học viên muốn xóa" + RESET);
    } else {
        System.out.println(student.toString());
        boolean Exit = false;
        while (!Exit) {
            System.out.println("Bạn có chắc chắn muốn xóa học viên này không(y/n)");
            System.out.print("Lựa chọn: ");
            char choice = Character.toLowerCase(scanner.nextLine().charAt(0));
            switch (choice) {
                case 'y':
                    if (studentServiceImp.delete(student)) {
                        System.out.println(GREEN + "Xóa thành công" + RESET);
                    } else {
                        System.out.println(RED + "Xóa thất bại" + RESET);
                    }
                    Exit = true;
                    break;
                case 'n':
                    System.out.println(YELLOW + "Đã hủy xóa" + RESET);
                    Exit = true;
                    break;
                default:
                    System.out.println(RED + "Lựa chọn không hợp lệ vui lòng chọn y/n" + RESET);
            }
        }
    }
}

public static void searchStudent(Scanner scanner) {
    String search = Validator.validateString(scanner, 0, 100, "Nhập tên, email hoặc mã học viên muốn tìm: ", "Tìm kiếm");
    studentServiceImp.findStudentByNameOrEmailOrId(scanner, search);
}

public static void sortStudent(Scanner scanner) {
    boolean Exit = false;
    do {
        System.out.println(GREEN + "Sắp xếp học viên" + RESET);
        System.out.println("1. Theo tên");
        System.out.println("2. Theo mã học viên");
        int choice = ValidatorChoice.validater(scanner);
        switch (choice) {
            case 1:
                studentServiceImp.sortByName(scanner);
                Exit = true;
                break;
            case 2:
                studentServiceImp.sortById(scanner);
                Exit = true;
                break;
            default:
                System.out.println(RED + "Lựa chọn không hợp lệ, vui lòng nhập lại!" + RESET);
        }
    } while (!Exit);
}
}