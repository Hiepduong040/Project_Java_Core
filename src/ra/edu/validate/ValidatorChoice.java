package ra.edu.validate;

import java.util.Scanner;

public class ValidatorChoice {
    public static int validater(Scanner scanner){
        while (true){
            System.out.print("Nhập lựa chọn: ");
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.println("Lựa chọn không hợp lệ vui lòng nhập lại!");
            }
        }
    }
}
