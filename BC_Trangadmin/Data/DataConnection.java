package Data;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnection {

    private static final String URL =
            "jdbc:sqlserver://localhost:1433;databaseName=QuanLyNhaHang;encrypt=false";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456"; // sửa theo máy bạn

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}