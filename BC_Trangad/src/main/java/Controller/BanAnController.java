/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.BanAn;
import Service.BanAnService;
import View.BanAnView;

import java.util.List;

/**
 *
 * @author DANG
 */
public class BanAnController {

    private BanAnView view;
    private BanAnService service;

    public BanAnController(BanAnView view) {
        this.view = view;
        service = new BanAnService();
    }

    public void loadTable() {
        List<BanAn> list = service.getAll();
        view.showTable(list);
    }

    public void addBan() {

        String ten = view.getTenBan();
        String trangThai = view.getTrangThai();

        if (ten.isEmpty()) {
            view.showMessage("Tên bàn không được để trống!");
            return;
        }

        BanAn b = new BanAn(ten, trangThai);

        if (service.add(b)) {
            view.showMessage("Thêm thành công!");
            loadTable();
            view.clearForm();
        } else {
            view.showMessage("Thêm thất bại!");
        }
    }

    public void updateBan() {

        if (view.getMaBanText().isEmpty()) {
            view.showMessage("Vui lòng chọn bàn cần sửa!");
            return;
        }

        int ma = view.getMaBan();
        String ten = view.getTenBan();
        String trangThai = view.getTrangThai();

        BanAn b = new BanAn(ma, ten, trangThai);

        if (service.update(b)) {
            view.showMessage("Cập nhật thành công!");
            loadTable();
            view.clearForm();
        } else {
            view.showMessage("Cập nhật thất bại!");
        }
    }

    public void deleteBan() {

        if (view.getMaBanText().isEmpty()) {
            view.showMessage("Vui lòng chọn bàn cần xóa!");
            return;
        }

        int ma = view.getMaBan();

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
                null,
                "Bạn có chắc muốn xóa bàn này?",
                "Xác nhận",
                javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == 0) {
            if (service.delete(ma)) {
                view.showMessage("Xóa thành công!");
                loadTable();
                view.clearForm();
            } else {
                view.showMessage("Không thể xóa!");
            }
        }
    }
}
