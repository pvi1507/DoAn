/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Data.DataConnection;
import Model.LoaiMon;
import Service.LoaiMonService;
import View.MonAnView;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author DANG
 */
public class LoaiMonController {

    private MonAnView view;
    private LoaiMonService service = new LoaiMonService();

    public LoaiMonController(MonAnView view) {
        this.view = view;
        loadData();

        // Đăng ký sự kiện
        view.btnThemLoai.addActionListener(e -> them());
        view.btnSuaLoai.addActionListener(e -> sua());
        view.btnXoaLoai.addActionListener(e -> xoa());
        view.btnTimLoai.addActionListener(e -> tim());
        view.btnLamMoiLoai.addActionListener(e -> lamMoi());

        // Sự kiện click bảng
        view.tblLoaiMon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                showDetail();
            }
        });
    }

    private void loadData() {
        view.modelLoai.setRowCount(0);
        List<LoaiMon> list = service.getAll();
        for (LoaiMon lm : list) {
            view.modelLoai.addRow(new Object[]{lm.getMaLoai(), lm.getTenLoai()});
        }
    }

    private void showDetail() {
        int row = view.tblLoaiMon.getSelectedRow();
        if (row >= 0) {
            view.txtMaLoai.setText(view.modelLoai.getValueAt(row, 0).toString());
            view.txtTenLoai.setText(view.modelLoai.getValueAt(row, 1).toString());
        }
    }

    private void them() {
        String ten = view.txtTenLoai.getText().trim();
        if (ten.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập tên loại!");
            return;
        }
        if (service.add(new LoaiMon(0, ten))) {
            JOptionPane.showMessageDialog(view, "Thêm thành công");
            loadData();
            view.updateComboBoxLoai();
            lamMoi();
        }
    }

    private void sua() {
        if (view.txtMaLoai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn loại món từ bảng!");
            return;
        }
        int id = Integer.parseInt(view.txtMaLoai.getText());
        String ten = view.txtTenLoai.getText().trim();
        if (service.update(new LoaiMon(id, ten))) {
            JOptionPane.showMessageDialog(view, "Cập nhật thành công");
            loadData();
            view.updateComboBoxLoai();
        }
    }

    private void xoa() {
        if (view.txtMaLoai.getText().isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn loại món cần xóa!");
            return;
        }
        int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc muốn xóa?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(view.txtMaLoai.getText());
            if (service.delete(id)) {
                JOptionPane.showMessageDialog(view, "Xóa thành công");
                loadData();
                view.updateComboBoxLoai();
                lamMoi();
            } else {
                JOptionPane.showMessageDialog(view, "Lỗi: Loại món này đang được sử dụng!");
            }
        }
    }

    private void tim() {
        String keyword = view.txtTimLoai.getText().trim();
        List<LoaiMon> list = service.search(keyword);
        view.modelLoai.setRowCount(0);
        for (LoaiMon lm : list) {
            view.modelLoai.addRow(new Object[]{lm.getMaLoai(), lm.getTenLoai()});
        }
    }

    private void lamMoi() {
        view.txtMaLoai.setText("");
        view.txtTenLoai.setText("");
        view.txtTimLoai.setText("");
        loadData();
    }
}
