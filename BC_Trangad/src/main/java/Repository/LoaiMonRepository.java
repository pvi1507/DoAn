/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Data.DataConnection;
import Model.LoaiMon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DANG
 */
public class LoaiMonRepository {
    public List<LoaiMon> selectAll() {
        List<LoaiMon> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiMon";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new LoaiMon(rs.getInt("MaLoai"), rs.getString("TenLoai")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public boolean insert(LoaiMon lm) {
        String sql = "INSERT INTO LoaiMon(TenLoai) VALUES(?)";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lm.getTenLoai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean update(LoaiMon lm) {
        String sql = "UPDATE LoaiMon SET TenLoai = ? WHERE MaLoai = ?";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, lm.getTenLoai());
            ps.setInt(2, lm.getMaLoai());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    public boolean delete(int maLoai) {
        String sql = "DELETE FROM LoaiMon WHERE MaLoai = ?";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, maLoai);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { return false; }
    }

    // Chức năng tìm kiếm theo tên
    public List<LoaiMon> search(String ten) {
        List<LoaiMon> list = new ArrayList<>();
        String sql = "SELECT * FROM LoaiMon WHERE TenLoai LIKE ?";
        try (Connection con = DataConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + ten + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new LoaiMon(rs.getInt("MaLoai"), rs.getString("TenLoai")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}
