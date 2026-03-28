/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import Model.MonAn;
import Data.DataConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DANG
 */
public class MonAnRepository {

    public List<MonAn> getAll() {
        List<MonAn> list = new ArrayList<>();

        String sql = """
            SELECT m.MaMon, m.TenMon, m.Gia, m.MaLoai,
                   l.TenLoai,
                   m.MoTa, m.HinhAnh
            FROM MonAn m
            JOIN LoaiMon l ON m.MaLoai = l.MaLoai
        """;

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                MonAn ma = new MonAn(
                        rs.getInt("MaMon"),
                        rs.getString("TenMon"),
                        rs.getDouble("Gia"),
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getString("MoTa"),      // đúng thứ tự
                        rs.getString("HinhAnh")
                );

                list.add(ma);
            }

        } catch (Exception e) {
            System.out.println("Lỗi getAll: " + e.getMessage());
        }

        return list;
    }

    // ================= INSERT =================
    public boolean insert(MonAn ma) {

        String sql = """
            INSERT INTO MonAn(TenMon,Gia,MaLoai,HinhAnh,MoTa)
            VALUES(?,?,?,?,?)
        """;

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma.getTenMon());
            ps.setDouble(2, ma.getDonGia());
            ps.setInt(3, ma.getMaLoai());
            ps.setString(4, ma.getHinhAnh());
            ps.setString(5, ma.getMoTa());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi INSERT: " + e.getMessage());
            return false;
        }
    }

    // ================= UPDATE =================
    public boolean update(MonAn ma) {

        String sql = """
            UPDATE MonAn
            SET TenMon=?, Gia=?, MaLoai=?, HinhAnh=?, MoTa=?
            WHERE MaMon=?
        """;

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma.getTenMon());
            ps.setDouble(2, ma.getDonGia());          // ✅ FIX
            ps.setInt(3, ma.getMaLoai());
            ps.setString(4, ma.getHinhAnh());
            ps.setString(5, ma.getMoTa());
            ps.setInt(6, ma.getMaMon());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi UPDATE: " + e.getMessage());
            return false;
        }
    }

    // ================= DELETE =================
    public boolean delete(int maMon) {

        String sql = "DELETE FROM MonAn WHERE MaMon=?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maMon);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Lỗi DELETE: " + e.getMessage());
            return false;
        }
    }

    // ================= SEARCH =================
    public List<MonAn> search(String tenMon) {

        List<MonAn> list = new ArrayList<>();

        String sql = """
            SELECT m.MaMon, m.TenMon, m.Gia, m.MaLoai,
                   l.TenLoai,
                   m.MoTa, m.HinhAnh
            FROM MonAn m
            JOIN LoaiMon l ON m.MaLoai = l.MaLoai
            WHERE m.TenMon LIKE ?
        """;

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + tenMon + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                MonAn ma = new MonAn(
                        rs.getInt("MaMon"),
                        rs.getString("TenMon"),
                        rs.getDouble("Gia"),
                        rs.getInt("MaLoai"),
                        rs.getString("TenLoai"),
                        rs.getString("MoTa"),      // ✅ FIX đúng thứ tự
                        rs.getString("HinhAnh")
                );

                list.add(ma);
            }

        } catch (Exception e) {
            System.out.println("Lỗi SEARCH: " + e.getMessage());
        }

        return list;
    }
}
