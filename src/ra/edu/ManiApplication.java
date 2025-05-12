package ra.edu;

import ra.edu.business.model.Admin;
import ra.edu.business.service.auth.AuthServiceImp;
import ra.edu.presentation.AdminUI;
import ra.edu.presentation.StudentUI;
import ra.edu.validate.Validator;
import ra.edu.validate.ValidatorChoice;

import java.util.Scanner;

public class ManiApplication {
    private static AuthServiceImp authServiceImp;

    private ManiApplication() {
        authServiceImp = new AuthServiceImp();
    }

    public static Object currentUser = null;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ManiApplication app = new ManiApplication();
        do {
            System.out.println("===================== Hệ thống quản lý đào tạo =============");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Thoát");
            System.out.println("==========================================================");
            int choice = ValidatorChoice.validater(scanner);
            switch (choice) {
                case 1:
                    login(scanner);
                    break;
                case 2:
                    System.exit(0);
                default:
                    System.out.println("Lựa chọn không hợp lệ vui, vui lòng nhập từ 1 - 2!");
            }
        } while (true);
    }

    public static void login(Scanner scanner) {
        String username = Validator.validateEmail(scanner);
        String pass = Validator.validateString(scanner, 0, 255, "Nhập vào mật khẩu: ", "Mật khẩu");
        currentUser = authServiceImp.login(username, pass);
        if (currentUser != null) {
            if (currentUser instanceof Admin) {
                System.out.println("Chào mừng Admin đến với trang quản trị");
                AdminUI.main(null);
            } else {
                System.out.println("Chào mừng học viên đến với trang học viên");
                StudentUI.main(null);
            }
        } else {
            System.out.println("Vui lòng đăng nhập lại!");
        }
    }
}