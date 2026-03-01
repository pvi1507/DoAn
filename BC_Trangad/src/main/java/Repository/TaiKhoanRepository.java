package repository;

import Data.DataConnection;
import Model.TaiKhoan;
import java.sql.*;

public class TaiKhoanRepository {

    public TaiKhoan login(String username, String password) {
        String sql = "SELECT * FROM TaiKhoan WHERE TenDangNhap=? AND MatKhau=? AND TrangThai=1";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                TaiKhoan tk = new TaiKhoan();
                tk.setMaTK(rs.getInt("MaTK"));
                tk.setTenDangNhap(rs.getString("TenDangNhap"));
                tk.setMaVaiTro(rs.getInt("MaVaiTro"));
                tk.setTrangThai(rs.getBoolean("TrangThai"));
                return tk;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}