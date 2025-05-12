package ra.edu.validate;

import ra.edu.business.model.Student;
import ra.edu.business.service.student.StudentServiceImp;

import java.time.LocalDate;
import java.util.Scanner;

public class StudentValidator {
    private static final StudentServiceImp studentServiceImp = new StudentServiceImp();

    public static String validateName(Scanner scanner) {
        while (true) {
            String name = Validator.validateString(scanner, 0, 100, "Nhập tên học viên: ", "Tên học viên");
            // Kiểm tra xem tên học viên có phải là số nguyên không
            try {
                Integer.parseInt(name);
                System.out.println("Tên học viên không được là số nguyên!");
            } catch (NumberFormatException e) {
                // Không phải số nguyên, hợp lệ
                return name;
            }
        }
    }

    public static String validateEmail(Scanner scanner) {
        while (true) {
            String email = Validator.validateEmail(scanner);
            Student student = studentServiceImp.findByEmail(email);
            if (student == null) {
                return email;
            } else {
                System.out.println("Email đã tồn tại, vui lòng thử lại!");
            }
        }
    }

    public static String validatePhone(Scanner scanner) {
        return Validator.validPhoneNumberVN(scanner);
    }

    public static LocalDate validateDob(Scanner scanner) {
        return Validator.validateDate(scanner, "Nhập ngày sinh (YYYY-MM-DD): ", "Ngày sinh");
    }

    public static boolean validateSex(Scanner scanner) {
        while (true) {
            System.out.print("Nhập giới tính (Nam/Nữ): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("nam")) {
                return true;
            } else if (input.equals("nữ")) {
                return false;
            } else {
                System.out.println("Giới tính không hợp lệ, vui lòng nhập Nam hoặc Nữ!");
            }
        }
    }

    public static String validatePassword(Scanner scanner) {
        return Validator.validateString(scanner, 6, 255, "Nhập mật khẩu (tối thiểu 6 ký tự): ", "Mật khẩu");
    }
}