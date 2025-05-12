package ra.edu.validate;

import ra.edu.business.model.Course;
import ra.edu.business.service.course.CourseServiceImp;

import java.util.Scanner;

public class CourseValidator {
    private static CourseServiceImp courseServiceImp = new CourseServiceImp();

    public static String validateName(Scanner scanner, Integer courseId) {
        while (true) {
            System.out.print("Nhập tên khóa học: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Tên khóa học không được để trống!");
                continue;
            }
            // Kiểm tra trùng lặp tên, bỏ qua bản ghi hiện tại
            Course existingCourse = courseServiceImp.findByName(name);
            if (existingCourse != null && (courseId == null || existingCourse.getId() != courseId)) {
                System.out.println("Tên khóa học đã tồn tại vui lòng thử lại!");
                continue;
            }
            return name;
        }
    }

    public static String validateName(Scanner scanner) {
        return validateName(scanner, null);
    }

    public static int validateDuration(Scanner scanner) {
        while (true) {
            System.out.print("Nhập thời lượng khóa học (giờ): ");
            String input = scanner.nextLine().trim();
            try {
                int duration = Integer.parseInt(input);
                if (duration <= 0) {
                    System.out.println("Thời lượng phải lớn hơn 0!");
                    continue;
                }
                return duration;
            } catch (NumberFormatException e) {
                System.out.println("Thời lượng nhập vào không phải số nguyên!");
            }
        }
    }

    public static String validateInstructor(Scanner scanner) {
        while (true) {
            System.out.print("Nhập tên giảng viên: ");
            String instructor = scanner.nextLine().trim();
            if (instructor.isEmpty()) {
                System.out.println("Tên giảng viên không được để trống!");
                continue;
            }
            if (instructor.matches("\\d+")) {
                System.out.println("Tên giảng viên không được là số nguyên!");
                continue;
            }
            return instructor;
        }
    }
}