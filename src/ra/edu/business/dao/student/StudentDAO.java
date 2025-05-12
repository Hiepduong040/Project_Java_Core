package ra.edu.business.dao.student;

import ra.edu.business.model.Student;

import java.util.List;

public interface StudentDAO {
    Student findByEmail(String email);
    Student findById(int id);
    List<Student> listPagination(int limit, int page);
    int totalCount();
    List<Student> findByNameOrEmailOrId(String search, int limit, int page);
    List<Student> sortByName(int limit, int page, String sortBy);
    List<Student> sortById(int limit, int page, String sortBy);
    List<Student> findAll();
    boolean save(Student student);
    boolean update(Student student);
    boolean delete(Student student);
    boolean updatePassword(int studentId, String newPassword);
}