/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.BanAnView;
import View.TrangChuView;
import View.LoginView;
import View.MonAnView;
import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author DANG
 */
public class TrangChuController {

    private TrangChuView view;

    public TrangChuController(TrangChuView view) {
        this.view = view;

        view.btnDangXuat.addActionListener(e -> dangXuat());
        view.btnTrangChu.addActionListener(e -> loadTrangChu());
        view.btnMonAn.addActionListener(e -> loadMonAn());
        view.btnBan.addActionListener(e -> loadBan());
        view.btnHoaDon.addActionListener(e -> loadHoaDon());
        view.btnNhanVien.addActionListener(e -> loadNhanVien());
    }

    private void dangXuat() {
        int confirm = JOptionPane.showConfirmDialog(
                view,
                "Bạn có chắc muốn đăng xuất?",
                "Xác nhận",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            view.dispose();
            LoginView login = new LoginView();
            new LoginController(login);
            login.setVisible(true);
        }
    }

    private void loadTrangChu() {
        view.contentPanel.removeAll();
        JLabel lbl = new JLabel("WELCOME TO LE CHÂTEAU DORÉ", SwingConstants.CENTER);
        lbl.setFont(new java.awt.Font("Serif", java.awt.Font.BOLD, 30));
        lbl.setForeground(new java.awt.Color(212, 175, 55));
        view.contentPanel.add(lbl);
        view.contentPanel.revalidate();
        view.contentPanel.repaint();
    }

    private void loadMonAn() {
        view.contentPanel.removeAll();

        MonAnView monAnView = new MonAnView();
        new MonAnController(monAnView);

        view.contentPanel.setLayout(new java.awt.BorderLayout());
        view.contentPanel.add(monAnView, java.awt.BorderLayout.CENTER);

        view.contentPanel.revalidate();
        view.contentPanel.repaint();
    }

    private void loadBan() {
        view.contentPanel.removeAll();

        BanAnView banView = new BanAnView();
        view.contentPanel.setLayout(new BorderLayout());
        view.contentPanel.add(banView, BorderLayout.CENTER);

        view.contentPanel.revalidate();
        view.contentPanel.repaint();
    }

    private void loadHoaDon() {
        view.contentPanel.removeAll();
        view.contentPanel.add(new JLabel("QUẢN LÝ HÓA ĐƠN", SwingConstants.CENTER));
        view.contentPanel.revalidate();
        view.contentPanel.repaint();
    }

    private void loadNhanVien() {
        view.contentPanel.removeAll();
        view.contentPanel.add(new JLabel("QUẢN LÝ NHÂN VIÊN", SwingConstants.CENTER));
        view.contentPanel.revalidate();
        view.contentPanel.repaint();
    }
}
