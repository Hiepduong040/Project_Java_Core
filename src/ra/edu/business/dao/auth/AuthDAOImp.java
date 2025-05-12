package ra.edu.business.dao.auth;

import ra.edu.business.config.ConnectionDB;
import ra.edu.business.model.Admin;
import ra.edu.business.model.Student;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAOImp implements AuthDAO {
    @Override
    public Object login(String username, String password) {
        Connection conn = null;
        CallableStatement callSt = null;
        try {
            conn = ConnectionDB.openConnection();
            callSt = conn.prepareCall("{call loginByAccount(?,?)}");
            callSt.setString(1, username);
            callSt.setString(2, password);
            ResultSet rs = callSt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                if ("ADMIN".equals(role)) {
                    Admin admin = new Admin();
                    admin.setId(rs.getInt("id"));
                    admin.setUsername(rs.getString("username"));
                    admin.setPassword(rs.getString("password"));
                    return admin;
                } else {
                    Student student = new Student();
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
            }
        } catch (SQLException e) {
            System.out.println("Có lỗi trong quá trình đăng nhập: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Có lỗi không xác định trong quá trình đăng nhập: " + e.getMessage());
        } finally {
            ConnectionDB.closeConnection(conn, callSt);
        }
        return null;
    }
}