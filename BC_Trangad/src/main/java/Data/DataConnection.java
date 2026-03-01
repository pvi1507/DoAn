/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author DANG
 */
public class DataConnection {
    private static final String DB_URL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaHang;encrypt=true;trustServerCertificate=true;";
    private static final String USER = "sa"; 
    private static final String PASS = "1234"; 

    private Connection conn;
    
    public static Connection getConnection() {
        Connection conn = null;
        try {
            
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
           
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Kết nối cơ sở dữ liệu thành công!");
        } catch (SQLException e) {
            System.err.println("Lỗi kết nối cơ sở dữ liệu: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.err.println("Không tìm thấy Driver JDBC: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }

  
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("Đã đóng kết nối cơ sở dữ liệu.");
            } catch (SQLException e) {
                System.err.println("Lỗi khi đóng kết nối: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

   
    public static void main(String[] args) {
        Connection testConn = getConnection();
        if (testConn != null) {
            closeConnection(testConn);
        }
    }
}
