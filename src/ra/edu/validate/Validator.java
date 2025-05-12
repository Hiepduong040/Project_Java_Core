package ra.edu.validate;

import java.time.LocalDate;
import java.util.Scanner;

public class Validator {
    private static final String PHONE_VIETTEL_PREFIXES = "086|096|097|098|039|038|037|036|035|034|033|032";
    private static final String PHONE_VINAPHONE_PREFIXES = "091|094|088|083|084|085|081|082";
    private static final String PHONE_MOBIFONE_PREFIXES = "070|079|077|076|078|089|090|093";
    public static int validateInt(Scanner scanner,int min,int max,String message, String name) {
        while (true){
            System.out.print(message);
            String input = scanner.nextLine();
            if (input.isEmpty()){
                System.out.println(name + " không được để trống");
            }else {
                try {
                    int valInput = Integer.parseInt(input);
                    if (Integer.parseInt(input) > max || Integer.parseInt(input) < min) {
                        System.out.println(name + " không hợp lệ");
                    }else {
                        return valInput;
                    }
                }catch (NumberFormatException e) {
                    System.out.println(name + "nhập vào không phải số nguyên!");
                }
            }
        }
    }

    public static String validateString(Scanner scanner,int minLength,int maxLength,String message, String name){
        while(true){
            System.out.print(message);
            String input = scanner.nextLine().trim();
            if(input.isEmpty()){
                System.out.println(name+" không được để trống!");
            }else if(input.length() < minLength || input.length() > maxLength){
                System.out.println("Độ dài "+name+" không hợp lệ");
            }else {
                return input;
            }
        }
    }
    public static String validateEmail(Scanner scanner){
        while(true){
            System.out.print("Nhập email: ");
            String input = scanner.nextLine().trim();
            String regexEmail = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
            if(input.isEmpty()){
                System.out.println("Email không được để trống!");
            }else if(!input.matches(regexEmail)){
                System.out.println("Dữ liệu không đúng định dạng email!");
            }else {
                return input;
            }
        }
    }

    public static String validPhoneNumberVN(Scanner scanner) {
        String phoneRegex = "(" + PHONE_VIETTEL_PREFIXES + "|" + PHONE_VINAPHONE_PREFIXES + "|" + PHONE_MOBIFONE_PREFIXES + ")\\d{7}";
        while (true) {
            System.out.print("Nhập vào số điện thoại: ");
            String phoneNumber = scanner.nextLine().trim();
            if (phoneNumber.isEmpty()) {
                System.out.println("Số điện thoại không được để trống");
            } else if (!phoneNumber.matches(phoneRegex)) {
                System.out.println("Số điện thoại không đúng định dạng số Việt Nam");
            } else {
                return phoneNumber;
            }
        }
    }
    public static LocalDate validateDate(Scanner scanner, String message, String name) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            String regexDate = "\\d{4}-\\d{2}-\\d{2}";
            if(input.isEmpty()){
                System.out.println(name + " không được để trống");
            }else if(!input.matches(regexDate)){
                System.out.println("Định dạng dữ liệu không hợp lệ");
            }else {
                return LocalDate.parse(input);
            }
        }
    }
}
