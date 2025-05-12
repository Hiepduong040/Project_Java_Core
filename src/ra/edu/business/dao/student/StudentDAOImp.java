package ra.edu.business.dao.student;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImp implements StudentDAO {
    @Override
    public Student findByEmail(String email) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findStudentByEmail(?)}");
            callSt.setString(1, email);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                return student;
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    @Override
    public Student findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Student student = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findStudentById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                return student;
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return student;
    }

    @Override
    public List<Student> listPagination(int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call listStudent(?,?)}");
            callSt.setInt(1, limit);
            callSt.setInt(2, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public int totalCount() {
        Connection conn = null;
        CallableStatement callSt = null;
        int totalStudent = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call totalStudent()}");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                totalStudent = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totalStudent;
    }

    @Override
    public List<Student> findByNameOrEmailOrId(String search, int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findStudentByNameOrEmailOrId(?,?,?)}");
            callSt.setString(1, search);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> sortByName(int limit, int page, String sortBy) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sortStudentByName(?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> sortById(int limit, int page, String sortBy) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sortStudentById(?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public List<Student> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Student> students = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findAllStudents()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setDob(rs.getDate("dob").toLocalDate());
                student.setEmail(rs.getString("email"));
                student.setSex(rs.getBoolean("sex"));
                student.setPhone(rs.getString("phone"));
                student.setPassword(rs.getString("password"));
                student.setCreate_at(rs.getDate("create_at").toLocalDate());
                students.add(student);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình lấy danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình lấy danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return students;
    }

    @Override
    public boolean save(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addStudent(?,?,?,?,?,?)}");
            callSt.setString(1, student.getName());
            callSt.setDate(2, java.sql.Date.valueOf(student.getDob()));
            callSt.setString(3, student.getEmail());
            callSt.setBoolean(4, student.isSex());
            callSt.setString(5, student.getPhone());
            callSt.setString(6, student.getPassword());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình thêm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thêm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean update(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateStudent(?,?,?,?,?,?)}");
            callSt.setInt(1, student.getId());
            callSt.setString(2, student.getName());
            callSt.setDate(3, java.sql.Date.valueOf(student.getDob()));
            callSt.setString(4, student.getEmail());
            callSt.setBoolean(5, student.isSex());
            callSt.setString(6, student.getPhone());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình cập nhật: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình cập nhật: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean delete(Student student) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delStudent(?)}");
            callSt.setInt(1, student.getId());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình xóa: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình xóa: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean updatePassword(int studentId, String newPassword) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateStudentPassword(?,?)}");
            callSt.setInt(1, studentId);
            callSt.setString(2, newPassword);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình cập nhật mật khẩu: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình cập nhật mật khẩu: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}