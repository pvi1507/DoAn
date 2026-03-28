/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.MonAnView;
import Service.MonAnService;
import Model.MonAn;
import java.awt.Image;
import javax.swing.*;
import java.util.List;

/**
 *
 * @author DANG
 */
public class MonAnController {

    private MonAnView view;
    private MonAnService service = new MonAnService();

    public MonAnController(MonAnView view) {
        this.view = view;

        loadData();

        view.btnThem.addActionListener(e -> them());
        view.btnSua.addActionListener(e -> sua());
        view.btnXoa.addActionListener(e -> xoa());
        view.btnTim.addActionListener(e -> tim());

        view.tblMonAn.getSelectionModel().addListSelectionListener(e -> showDetail());
        view.btnLamMoi.addActionListener(e -> {
            view.clearForm();
            loadData();
        });
    }

    // ================= LOAD =================
    private void loadData() {

        view.model.setRowCount(0);
        List<MonAn> list = service.getAll();

        for (MonAn ma : list) {

            ImageIcon icon = loadImage(ma.getHinhAnh());

            view.model.addRow(new Object[]{
                ma.getMaMon(),
                ma.getTenMon(),
                ma.getDonGia(),
                ma.getTenLoai(), // ✅ dùng luôn TenLoai
                ma.getMoTa(),
                icon
            });
        }
    }

    // ================= LOAD ẢNH =================
    private ImageIcon loadImage(String path) {

        if (path == null || path.isEmpty()) {
            return null;
        }

        ImageIcon img = new ImageIcon(path);
        Image scaled = img.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);

        return new ImageIcon(scaled);
    }

    // ================= THÊM =================
    private void them() {

        try {

            MonAn ma = getFormData();

            if (service.add(ma)) {

                JOptionPane.showMessageDialog(view, "Thêm thành công");
                view.clearForm();
                loadData();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi dữ liệu!");
        }
    }

    // ================= SỬA =================
    private void sua() {
        try {

            int row = view.tblMonAn.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(view, "Chọn dòng cần sửa!");
                return;
            }

            MonAn ma = getFormData();
            ma.setMaMon(Integer.parseInt(view.txtMa.getText()));

            // 🔥 FIX GIỮ ẢNH CŨ
            if (view.duongDanAnh == null || view.duongDanAnh.isEmpty()) {
                // lấy ảnh cũ từ list
                String oldImg = service.getAll().get(row).getHinhAnh();
                ma.setHinhAnh(oldImg);
            }

            if (service.update(ma)) {
                JOptionPane.showMessageDialog(view, "Sửa thành công");
                view.clearForm();
                loadData();
            }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi dữ liệu!");
        }
    }

    // ================= XÓA =================
    private void xoa() {

        if (view.txtMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Chọn món cần xóa");
            return;
        }

        int maMon = Integer.parseInt(view.txtMa.getText());

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn chắc muốn xóa?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {

            if (service.delete(maMon)) {

                JOptionPane.showMessageDialog(view, "Xóa thành công");
                loadData();
                view.clearForm();
            }
        }
    }

    // ================= TÌM =================
    private void tim() {

        String keyword = view.txtTim.getText();
        List<MonAn> list = service.search(keyword);

        view.model.setRowCount(0);

        for (MonAn ma : list) {

            ImageIcon icon = loadImage(ma.getHinhAnh());

            view.model.addRow(new Object[]{
                ma.getMaMon(),
                ma.getTenMon(),
                ma.getDonGia(),
                ma.getTenLoai(), // ✅ FIX: hiển thị tên loại
                ma.getMoTa(),
                icon
            });
        }
    }

    // ================= SHOW DETAIL =================
    private void showDetail() {

        int row = view.tblMonAn.getSelectedRow();

        if (row >= 0) {

            view.txtMa.setText(view.model.getValueAt(row, 0).toString());
            view.txtTen.setText(view.model.getValueAt(row, 1).toString());
            view.txtGia.setText(view.model.getValueAt(row, 2).toString());
            view.txtMoTa.setText(view.model.getValueAt(row, 4).toString());

            // set combobox theo tên loại
            String tenLoai = view.model.getValueAt(row, 3).toString();
            view.cbLoai.setSelectedItem(tenLoai);

            // hiển thị ảnh lại
            ImageIcon icon = (ImageIcon) view.model.getValueAt(row, 5);
            view.lblHinhAnh.setIcon(icon);
            view.lblHinhAnh.setText("");
        }
    }

    // ================= LẤY DATA FORM =================
    private MonAn getFormData() {

        String ten = view.txtTen.getText().trim();
        double gia = Double.parseDouble(view.txtGia.getText().trim());
        int maLoai = view.cbLoai.getSelectedIndex() + 1;
        String moTa = view.txtMoTa.getText();
        String hinh = view.duongDanAnh;

        MonAn ma = new MonAn();
        ma.setTenMon(ten);
        ma.setDonGia(gia);
        ma.setMaLoai(maLoai);
        ma.setMoTa(moTa);
        ma.setHinhAnh(hinh);

        return ma;
    }
}
