/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;
import Data.DataConnection;
import Model.BanAn;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author DANG
 */
public class BanAnRepository {
    public List<BanAn> getAll() {
        List<BanAn> list = new ArrayList<>();
        String sql = "SELECT * FROM BanAn";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BanAn b = new BanAn(
                        rs.getInt("MaBan"),
                        rs.getString("TenBan"),
                        rs.getString("TrangThai")
                );
                list.add(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(BanAn b) {
        String sql = "INSERT INTO BanAn (TenBan, TrangThai) VALUES (?, ?)";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getTenBan());
            ps.setString(2, b.getTrangThai());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(BanAn b) {
        String sql = "UPDATE BanAn SET TenBan=?, TrangThai=? WHERE MaBan=?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getTenBan());
            ps.setString(2, b.getTrangThai());
            ps.setInt(3, b.getMaBan());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int maBan) {
        String sql = "DELETE FROM BanAn WHERE MaBan=?";

        try (Connection conn = DataConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maBan);
            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
