package ra.edu.business.model;

import ra.edu.validate.CourseValidator;
import ra.edu.validate.Validator;

import java.time.LocalDate;
import java.util.Scanner;

public class Course {
    private int id;
    private String name;
    private int duration;
    private String instructor;
    private LocalDate create_at;

    public Course() {
        create_at = LocalDate.now();
    }

    public Course(int id, String name, int duration, String instructor, LocalDate create_at) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
        this.create_at = create_at;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getCreate_at() {
        return create_at;
    }

    public void setCreate_at(LocalDate create_at) {
        this.create_at = create_at;
    }

    public void inputData(Scanner scanner) {
        this.name = CourseValidator.validateName(scanner);
        this.duration = Validator.validateInt(scanner, 0, 1000, "Nhập vào thời lượng khóa học: ", "Thời lượng");
        this.instructor = Validator.validateString(scanner, 0, 100, "Giảng viên phụ trách: ", "Giảng viên");
    }

    @Override
    public String toString() {
        return "Mã khóa học: " + id + " - Tên khóa học: " + name + " - Thời lượng(giờ): " + duration + " - Giảng viên phụ trách: " + instructor + " - Ngày thêm: " + create_at;
    }
}