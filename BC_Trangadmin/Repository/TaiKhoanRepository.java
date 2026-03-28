package Repository;

import Data.DataConnection;
import Model.TaiKhoan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiKhoanRepository {

    public TaiKhoan login(String username, String password) {

        String sql = """
                SELECT tk.MaTK, tk.TenDangNhap, vt.TenVaiTro
                FROM TaiKhoan tk
                JOIN VaiTro vt ON tk.MaVaiTro = vt.MaVaiTro
                WHERE tk.TenDangNhap = ? 
                AND tk.MatKhau = ?
                AND tk.TrangThai = 1
                """;

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new TaiKhoan(
                        rs.getInt("MaTK"),
                        rs.getString("TenDangNhap"),
                        rs.getString("TenVaiTro")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}