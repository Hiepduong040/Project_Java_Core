package ra.edu.business.dao.course;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Course;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImp implements CourseDAO {
    @Override
    public Course findByName(String name) {
        Connection conn = null;
        CallableStatement callSt = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findCourseByName(?)}");
            callSt.setString(1, name);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                return course;
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return course;
    }

    @Override
    public List<Course> listPagination(int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call listCourse(?,?)}");
            callSt.setInt(1, limit);
            callSt.setInt(2, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public int totalCount() {
        Connection conn = null;
        CallableStatement callSt = null;
        int totalCourse = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call totalCourse()}");
            callSt.execute();
            ResultSet rs = callSt.getResultSet();
            if (rs.next()) {
                totalCourse = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totalCourse;
    }

    @Override
    public Course findById(int id) {
        Connection conn = null;
        CallableStatement callSt = null;
        Course course = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findCourseById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                return course;
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return course;
    }

    @Override
    public List<Course> findByNamePagianation(String name, int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findCourseByNamePagination(?,?,?)}");
            callSt.setString(1, name);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm kiếm: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm kiếm: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public List<Course> sortByName(int limit, int page, String sortBy) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sortByName(?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public List<Course> sortById(int limit, int page, String sortBy) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call sortByID(?,?,?)}");
            callSt.setString(1, sortBy);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
            return courses;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình sắp xếp: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình sắp xếp: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public List<Course> findAll() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Course> courses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findAllCourses()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setId(rs.getInt("id"));
                course.setName(rs.getString("name"));
                course.setDuration(rs.getInt("duration"));
                course.setInstructor(rs.getString("instructor"));
                course.setCreate_at(rs.getDate("create_at").toLocalDate());
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình lấy danh sách: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình lấy danh sách: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return courses;
    }

    @Override
    public boolean save(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addCourse(?,?,?)}");
            callSt.setString(1, course.getName());
            callSt.setInt(2, course.getDuration());
            callSt.setString(3, course.getInstructor());
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
    public boolean update(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call updateCourse(?,?,?,?)}");
            callSt.setInt(1, course.getId());
            callSt.setString(2, course.getName());
            callSt.setInt(3, course.getDuration());
            callSt.setString(4, course.getInstructor());
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
    public boolean delete(Course course) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call delCourse(?)}");
            callSt.setInt(1, course.getId());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình xóa: Không thể xóa khóa học đang có học viên theo học!");
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình xóa: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }
}