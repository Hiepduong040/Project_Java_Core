//package ra.edu.business.dao.enrollment;
//
//import ra.edu.business.config.ConnectionDB;
//import ra.edu.business.model.Enrollment;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class EnrollmentDAOImp implements EnrollmentDAO {
//
//    @Override
//    public boolean save(Enrollment enrollment) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call addEnrollment(?,?)}");
//            callSt.setInt(1, enrollment.getStudentId());
//            callSt.setInt(2, enrollment.getCourseId());
//            callSt.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình thêm đăng ký: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình thêm đăng ký: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return false;
//    }
//
//    @Override
//    public boolean cancel(int enrollmentId) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call cancelEnrollment(?)}");
//            callSt.setInt(1, enrollmentId);
//            callSt.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình hủy đăng ký: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình hủy đăng ký: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return false;
//    }
//
//    @Override
//    public List<Enrollment> findByStudentId(int studentId) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        List<Enrollment> enrollments = new ArrayList<>();
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call findEnrollmentsByStudentId(?)}");
//            callSt.setInt(1, studentId);
//            ResultSet rs = callSt.executeQuery();
//            while (rs.next()) {
//                Enrollment enrollment = new Enrollment();
//                enrollment.setId(rs.getInt("id"));
//                enrollment.setStudentId(rs.getInt("student_id"));
//                enrollment.setCourseId(rs.getInt("course_id"));
//                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
//                enrollment.setStatus(rs.getString("status"));
//                enrollments.add(enrollment);
//            }
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình lấy danh sách đăng ký: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình lấy danh sách đăng ký: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return enrollments;
//    }
//
//    @Override
//    public int totalEnrollmentsByStudent(int studentId) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        int total = 0;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call totalEnrollmentsByStudent(?)}");
//            callSt.setInt(1, studentId);
//            ResultSet rs = callSt.executeQuery();
//            if (rs.next()) {
//                total = rs.getInt(1);
//            }
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình đếm đăng ký: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình đếm đăng ký: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return total;
//    }
//
//    @Override
//    public List<Enrollment> listEnrollmentsByCourse(int courseId, int limit, int page) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        List<Enrollment> enrollments = new ArrayList<>();
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call listEnrollmentsByCourse(?,?,?)}");
//            callSt.setInt(1, courseId);
//            callSt.setInt(2, limit);
//            callSt.setInt(3, page);
//            ResultSet rs = callSt.executeQuery();
//            while (rs.next()) {
//                Enrollment enrollment = new Enrollment();
//                enrollment.setId(rs.getInt("id"));
//                enrollment.setStudentId(rs.getInt("student_id"));
//                enrollment.setStudentName(rs.getString("student_name"));
//                enrollment.setCourseId(rs.getInt("course_id"));
//                enrollment.setCourseName(rs.getString("course_name"));
//                enrollment.setStatus(rs.getString("status"));
//                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
//                enrollments.add(enrollment);
//            }
//            return enrollments;
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình duyệt danh sách đăng ký: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình duyệt danh sách đăng ký: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return enrollments;
//    }
//
//    @Override
//    public int countEnrollmentsByCourse(int courseId) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        int total = 0;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call countEnrollmentsByCourse(?)}");
//            callSt.setInt(1, courseId);
//            ResultSet rs = callSt.executeQuery();
//            if (rs.next()) {
//                total = rs.getInt("total");
//            }
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình đếm đăng ký: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình đếm đăng ký: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return total;
//    }
//
//    @Override
//    public boolean approveEnrollment(int enrollmentId, String newStatus) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call approveEnrollment(?,?)}");
//            callSt.setInt(1, enrollmentId);
//            callSt.setString(2, newStatus);
//            callSt.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình duyệt đăng ký: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình duyệt đăng ký: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return false;
//    }
//
//    @Override
//    public Enrollment findById(int enrollmentId) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        Enrollment enrollment = null;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call findEnrollmentById(?)}");
//            callSt.setInt(1, enrollmentId);
//            ResultSet rs = callSt.executeQuery();
//            if (rs.next()) {
//                enrollment = new Enrollment();
//                enrollment.setId(rs.getInt("id"));
//                enrollment.setStudentId(rs.getInt("student_id"));
//                enrollment.setStudentName(rs.getString("student_name"));
//                enrollment.setCourseId(rs.getInt("course_id"));
//                enrollment.setCourseName(rs.getString("course_name"));
//                enrollment.setStatus(rs.getString("status"));
//                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
//            }
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình tìm đăng ký: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình tìm đăng ký: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return enrollment;
//    }
//
//    @Override
//    public List<Enrollment> listWaitingEnrollments(int pageSize, int currentPage) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        List<Enrollment> enrollments = new ArrayList<>();
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call listWaitingEnrollments(?,?)}");
//            callSt.setInt(1, pageSize);
//            callSt.setInt(2, currentPage);
//            ResultSet rs = callSt.executeQuery();
//            while (rs.next()) {
//                Enrollment enrollment = new Enrollment();
//                enrollment.setId(rs.getInt("id"));
//                enrollment.setStudentId(rs.getInt("student_id"));
//                enrollment.setStudentName(rs.getString("student_name"));
//                enrollment.setCourseId(rs.getInt("course_id"));
//                enrollment.setCourseName(rs.getString("course_name"));
//                enrollment.setStatus(rs.getString("status"));
//                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
//                enrollments.add(enrollment);
//            }
//            return enrollments;
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình lấy danh sách đăng ký WAITING: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình lấy danh sách đăng ký WAITING: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return enrollments;
//    }
//
//    @Override
//    public int countWaitingEnrollments() {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        int total = 0;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call countWaitingEnrollments()}");
//            ResultSet rs = callSt.executeQuery();
//            if (rs.next()) {
//                total = rs.getInt("total");
//            }
//        } catch (SQLException e) {
//            System.out.println("Có lỗi trong quá trình đếm đăng ký WAITING: " + e.getMessage());
//        } catch (Exception e) {
//            System.out.println("Có lỗi không xác định trong quá trình đếm đăng ký WAITING: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return total;
//    }
//}


package ra.edu.business.dao.enrollment;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Enrollment;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAOImp implements EnrollmentDAO {

    @Override
    public boolean save(Enrollment enrollment) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call addEnrollment(?,?)}");
            callSt.setInt(1, enrollment.getStudentId());
            callSt.setInt(2, enrollment.getCourseId());
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình thêm đăng ký: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình thêm đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public boolean cancel(int enrollmentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call cancelEnrollment(?)}");
            callSt.setInt(1, enrollmentId);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình hủy đăng ký: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình hủy đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public List<Enrollment> findByStudentId(int studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findEnrollmentsByStudentId(?)}");
            callSt.setInt(1, studentId);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                enrollment.setStatus(rs.getString("status"));
                enrollments.add(enrollment);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình lấy danh sách đăng ký: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình lấy danh sách đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return enrollments;
    }

    @Override
    public int totalEnrollmentsByStudent(int studentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        int total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call totalEnrollmentsByStudent(?)}");
            callSt.setInt(1, studentId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                total = rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình đếm đăng ký: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình đếm đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return total;
    }

    @Override
    public List<Enrollment> listEnrollmentsByCourse(int courseId, int limit, int page) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call listEnrollmentsByCourse(?,?,?)}");
            callSt.setInt(1, courseId);
            callSt.setInt(2, limit);
            callSt.setInt(3, page);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setStudentName(rs.getString("student_name"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setCourseName(rs.getString("course_name"));
                enrollment.setStatus(rs.getString("status"));
                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                enrollments.add(enrollment);
            }
            return enrollments;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt danh sách đăng ký: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt danh sách đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return enrollments;
    }

    @Override
    public int countEnrollmentsByCourse(int courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        int total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countEnrollmentsByCourse(?)}");
            callSt.setInt(1, courseId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình đếm đăng ký: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình đếm đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return total;
    }

    @Override
    public boolean approveEnrollment(int enrollmentId, String newStatus) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call approveEnrollment(?,?)}");
            callSt.setInt(1, enrollmentId);
            callSt.setString(2, newStatus);
            callSt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình duyệt đăng ký: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình duyệt đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return false;
    }

    @Override
    public Enrollment findById(int enrollmentId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Enrollment enrollment = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call findEnrollmentById(?)}");
            callSt.setInt(1, enrollmentId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setStudentName(rs.getString("student_name"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setCourseName(rs.getString("course_name"));
                enrollment.setStatus(rs.getString("status"));
                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình tìm đăng ký: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình tìm đăng ký: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return enrollment;
    }

    @Override
    public List<Enrollment> listWaitingEnrollments(int pageSize, int currentPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call listWaitingEnrollments(?,?)}");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, currentPage);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setStudentName(rs.getString("student_name"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setCourseName(rs.getString("course_name"));
                enrollment.setStatus(rs.getString("status"));
                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                enrollments.add(enrollment);
            }
            return enrollments;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình lấy danh sách đăng ký WAITING: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình lấy danh sách đăng ký WAITING: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return enrollments;
    }

    @Override
    public int countWaitingEnrollments() {
        Connection conn = null;
        CallableStatement callSt = null;
        int total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countWaitingEnrollments()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình đếm đăng ký WAITING: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình đếm đăng ký WAITING: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return total;
    }

    @Override
    public List<Enrollment> listDeniedEnrollments(int pageSize, int currentPage) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Enrollment> enrollments = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call listDeniedEnrollments(?,?)}");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, currentPage);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Enrollment enrollment = new Enrollment();
                enrollment.setId(rs.getInt("id"));
                enrollment.setStudentId(rs.getInt("student_id"));
                enrollment.setStudentName(rs.getString("student_name"));
                enrollment.setCourseId(rs.getInt("course_id"));
                enrollment.setCourseName(rs.getString("course_name"));
                enrollment.setStatus(rs.getString("status"));
                enrollment.setRegisteredAt(rs.getTimestamp("registered_at").toLocalDateTime());
                enrollments.add(enrollment);
            }
            return enrollments;
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình lấy danh sách đăng ký DENIED: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình lấy danh sách đăng ký DENIED: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return enrollments;
    }

    @Override
    public int countDeniedEnrollments() {
        Connection conn = null;
        CallableStatement callSt = null;
        int total = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countDeniedEnrollments()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình đếm đăng ký DENIED: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình đếm đăng ký DENIED: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return total;
    }
}