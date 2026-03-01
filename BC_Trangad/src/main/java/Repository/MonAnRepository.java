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
        String sql = "SELECT * FROM MonAn";

        try (Connection conn = DataConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new MonAn(
                        rs.getInt("MaMon"),
                        rs.getString("TenMon"),
                        rs.getDouble("DonGia"),
                        rs.getInt("MaLoai"),
                        rs.getInt("TrangThai") == 1,
                        rs.getString("HinhAnh")
                ));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean insert(MonAn ma) {
        String sql = "INSERT INTO MonAn(TenMon,DonGia,MaLoai,TrangThai,HinhAnh) VALUES(?,?,?,?,?)";
        try (Connection conn = DataConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma.getTenMon());
            ps.setDouble(2, ma.getDonGia());
            ps.setInt(3, ma.getMaLoai());
            ps.setInt(4, ma.isTrangThai() ? 1 : 0);
            ps.setString(5, ma.getHinhAnh());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(MonAn ma) {
        String sql = "UPDATE MonAn SET TenMon=?,DonGia=?,MaLoai=?,TrangThai=?,HinhAnh=? WHERE MaMon=?";

        try (Connection conn = DataConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, ma.getTenMon());
            ps.setDouble(2, ma.getDonGia());
            ps.setInt(3, ma.getMaLoai());
            ps.setInt(4, ma.isTrangThai() ? 1 : 0);
            ps.setString(5, ma.getHinhAnh());
            ps.setInt(6, ma.getMaMon());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(int maMon) {
        String sql = "DELETE FROM MonAn WHERE MaMon=?";
        try (Connection conn = DataConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maMon);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
