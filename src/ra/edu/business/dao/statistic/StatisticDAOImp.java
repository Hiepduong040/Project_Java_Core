
//
//package ra.edu.business.dao.Statistic;
//
//import ra.edu.business.config.ConnectionDB;
//import ra.edu.business.model.Statistic;
//
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class StatisticDAOImp implements StatisticDAO {
//
//    @Override
//    public Statistic countCoursesAndStudents() {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        Statistic statistic = null;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call countCoursesAndStudents()}");
//            ResultSet rs = callSt.executeQuery();
//            if (rs.next()) {
//                statistic = new Statistic(rs.getInt("TotalCourses"), rs.getInt("TotalStudents"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi khi thống kê tổng số: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return statistic;
//    }
//
//    @Override
//    public Statistic countStudentsByCourse(int courseId) {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        Statistic statistic = null;
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call countStudentsByCourse(?)}");
//            callSt.setInt(1, courseId);
//            ResultSet rs = callSt.executeQuery();
//            if (rs.next()) {
//                statistic = new Statistic(rs.getInt("id"), rs.getString("name"), rs.getInt("student_count"));
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi khi thống kê học viên theo khóa học: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return statistic;
//    }
//
//    @Override
//    public List<Statistic> topFiveCoursesByStudents() {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        List<Statistic> statistics = new ArrayList<>();
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call topFiveCoursesByStudents()}");
//            ResultSet rs = callSt.executeQuery();
//            while (rs.next()) {
//                Statistic statistic = new Statistic(rs.getInt("id"), rs.getString("name"), rs.getInt("student_count"));
//                statistics.add(statistic);
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi khi lấy top 5 khóa học: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return statistics;
//    }
//
//    @Override
//    public List<Statistic> listCoursesWithMoreThanTenStudents() {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        List<Statistic> statistics = new ArrayList<>();
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call listCoursesWithMoreThanTenStudents()}");
//            ResultSet rs = callSt.executeQuery();
//            while (rs.next()) {
//                Statistic statistic = new Statistic(rs.getInt("id"), rs.getString("name"), rs.getInt("student_count"));
//                statistics.add(statistic);
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi khi liệt kê khóa học có trên 10 học viên: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return statistics;
//    }
//
//    @Override
//    public List<Statistic> listAllCoursesWithStudentCount() {
//        Connection conn = null;
//        CallableStatement callSt = null;
//        List<Statistic> statistics = new ArrayList<>();
//        try {
//            conn = ConnectionDB.openConnection();
//            callSt = conn.prepareCall("{call listAllCoursesWithStudentCount()}");
//            ResultSet rs = callSt.executeQuery();
//            while (rs.next()) {
//                Statistic statistic = new Statistic(rs.getInt("id"), rs.getString("name"), rs.getInt("student_count"));
//                statistics.add(statistic);
//            }
//        } catch (SQLException e) {
//            System.out.println("Lỗi khi lấy danh sách khóa học: " + e.getMessage());
//        } finally {
//            ConnectionDB.closeConnection(conn, callSt);
//        }
//        return statistics;
//    }
//}

package ra.edu.business.dao.statistic;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Statistic;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticDAOImp implements StatisticDAO {

    @Override
    public Statistic countCoursesAndStudents() {
        Connection conn = null;
        CallableStatement callSt = null;
        Statistic statistic = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countCoursesAndStudents()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                statistic = new Statistic(rs.getInt("TotalCourses"), rs.getInt("TotalStudents"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thống kê tổng số: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return statistic;
    }

    @Override
    public Statistic countStudentsByCourse(int courseId) {
        Connection conn = null;
        CallableStatement callSt = null;
        Statistic statistic = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countStudentsByCourse(?)}");
            callSt.setInt(1, courseId);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                statistic = new Statistic(rs.getInt("id"), rs.getString("name"), rs.getInt("student_count"));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi thống kê học viên theo khóa học: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return statistic;
    }

    @Override
    public List<Statistic> topFiveCoursesByStudents() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Statistic> statistics = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call topFiveCoursesByStudents()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Statistic statistic = new Statistic(rs.getInt("id"), rs.getString("name"), rs.getInt("student_count"));
                statistics.add(statistic);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy top 5 khóa học: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return statistics;
    }

    @Override
    public List<Statistic> listCoursesWithMoreThanTenStudents() {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Statistic> statistics = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call listCoursesWithMoreThanTenStudents()}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Statistic statistic = new Statistic(rs.getInt("id"), rs.getString("name"), rs.getInt("student_count"));
                statistics.add(statistic);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi liệt kê khóa học có trên 10 học viên: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return statistics;
    }

    @Override
    public List<Statistic> listAllCoursesWithStudentCount(int pageSize, int pageNumber) {
        Connection conn = null;
        CallableStatement callSt = null;
        List<Statistic> statistics = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call listAllCoursesWithStudentCount(?, ?)}");
            callSt.setInt(1, pageSize);
            callSt.setInt(2, pageNumber);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Statistic statistic = new Statistic(rs.getInt("id"), rs.getString("name"), rs.getInt("student_count"));
                statistics.add(statistic);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách khóa học: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return statistics;
    }

    @Override
    public int countTotalCourses() {
        Connection conn = null;
        CallableStatement callSt = null;
        int totalCourses = 0;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call countTotalCourses()}");
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                totalCourses = rs.getInt("total");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đếm tổng số khóa học: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return totalCourses;
    }
}