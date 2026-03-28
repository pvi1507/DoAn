package repository;

import Data.DataConnection;
import Model.TaiKhoan;
import java.sql.*;

public class TaiKhoanRepository {

    public TaiKhoan login(String username, String password) {

        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap=? AND MatKhau=?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                TaiKhoan tk = new TaiKhoan();

                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMatKhau(rs.getString("MatKhau"));
                tk.setMaNhanVien(rs.getInt("MaNhanVien"));
                tk.setQuyen(rs.getString("Quyen"));

                return tk;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}