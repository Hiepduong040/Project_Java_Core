package ra.edu.business.service.student;

import ra.edu.business.model.Student;

import java.util.List;
import java.util.Scanner;

public interface StudentService {
    Student findByEmail(String email);
    Student findById(int id);
    int totalStudent();
    void listStudentsPagination(Scanner scanner);
    void findStudentByNameOrEmailOrId(Scanner scanner, String search);
    void sortByName(Scanner scanner);
    void sortById(Scanner scanner);
    List<Student> findAll();
    boolean save(Student student);
    boolean update(Student student);
    boolean delete(Student student);
    boolean updatePassword(int studentId, String newPassword);
}