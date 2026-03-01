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
        view.tblMonAn.getSelectionModel().addListSelectionListener(e -> showDetail());
    }

    private void loadData() {
        view.model.setRowCount(0);
        List<MonAn> list = service.getAll();

        for (MonAn ma : list) {

            String tenLoai = switch (ma.getMaLoai()) {
                case 1 ->
                    "Món chính";
                case 2 ->
                    "Khai vị";
                case 3 ->
                    "Tráng miệng";
                case 4 ->
                    "Đồ uống";
                default ->
                    "";
            };

            String trangThai = ma.isTrangThai()
                    ? "Đang bán"
                    : "Ngưng bán";

            ImageIcon icon = null;

            if (ma.getHinhAnh() != null && !ma.getHinhAnh().isEmpty()) {
                ImageIcon img = new ImageIcon(ma.getHinhAnh());
                Image scaled = img.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
                icon = new ImageIcon(scaled);
            }

            view.model.addRow(new Object[]{
                ma.getMaMon(),
                ma.getTenMon(),
                ma.getDonGia(),
                tenLoai,
                trangThai,
                icon
            });
        }
    }

    private void them() {
        try {
            String ten = view.txtTen.getText().trim();
            double gia = Double.parseDouble(view.txtGia.getText().trim());
            int maLoai = view.cbLoai.getSelectedIndex() + 1;
            boolean trangThai = view.cbTrangThai.getSelectedIndex() == 0;

            String hinh = view.duongDanAnh;   // LẤY ĐÚNG Ở ĐÂY

            if (hinh == null || hinh.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn hình!");
                return;
            }

            MonAn ma = new MonAn();
            ma.setTenMon(ten);
            ma.setDonGia(gia);
            ma.setMaLoai(maLoai);
            ma.setTrangThai(trangThai);
            ma.setHinhAnh(hinh);

            if (service.add(ma)) {
                JOptionPane.showMessageDialog(view, "Thêm thành công!");
                view.clearForm();
                loadData();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi dữ liệu!");
        }
    }

    private void sua() {
        try {
            int maMon = Integer.parseInt(view.txtMa.getText().trim());
            String ten = view.txtTen.getText().trim();
            double gia = Double.parseDouble(view.txtGia.getText().trim());

            int maLoai = view.cbLoai.getSelectedIndex() + 1;
            boolean trangThai = view.cbTrangThai.getSelectedIndex() == 0;

            String hinh = view.duongDanAnh;   // LẤY ĐÚNG

            MonAn ma = new MonAn();
            ma.setMaMon(maMon);
            ma.setTenMon(ten);
            ma.setDonGia(gia);
            ma.setMaLoai(maLoai);
            ma.setTrangThai(trangThai);
            ma.setHinhAnh(hinh);

            if (service.update(ma)) {
                JOptionPane.showMessageDialog(view, "Sửa thành công!");
                view.clearForm();
                loadData();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi dữ liệu!");
        }
    }

    private void xoa() {
        if (view.txtMa.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn món cần xóa!");
            return;
        }

        int maMon = Integer.parseInt(view.txtMa.getText());

        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc muốn xóa?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            if (service.delete(maMon)) {
                JOptionPane.showMessageDialog(view, "Xóa thành công!");
                loadData();
            } else {
                JOptionPane.showMessageDialog(view, "Xóa thất bại!");
            }
        }
    }

    private void showDetail() {
        int row = view.tblMonAn.getSelectedRow();

        if (row >= 0) {

            view.txtMa.setText(view.model.getValueAt(row, 0).toString());
            view.txtTen.setText(view.model.getValueAt(row, 1).toString());
            view.txtGia.setText(view.model.getValueAt(row, 2).toString());

            view.cbLoai.setSelectedItem(
                    view.model.getValueAt(row, 3).toString()
            );
            view.cbTrangThai.setSelectedItem(
                    view.model.getValueAt(row, 4).toString()
            );
        }
    }
}
