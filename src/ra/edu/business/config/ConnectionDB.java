package ra.edu.business.config;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private final static String URL = "jdbc:mysql://localhost:3306/qly_khoa_hoc";
    private final static String USER = "root";
    private final static String PASSWORD = "Csl2722005";

    public static Connection openConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        }catch (SQLException e){
            System.err.println("Có lỗi trong quá trình kết nối đến CSDL: " + e.getMessage());
        }catch (Exception e){
            System.err.println("Có lỗi không xác định trong quá trình kết nối: " + e.getMessage());
        }
        return conn;
    }
    public static void closeConnection(Connection conn, CallableStatement callSt) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if (callSt != null) {
            try {
                callSt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
