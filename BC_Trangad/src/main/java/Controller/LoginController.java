/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;
import Service.TaiKhoanService;
import View.LoginView;
import Model.TaiKhoan;
import View.TrangChuView;
import javax.swing.*;
/**
 *
 * @author DANG
 */
public class LoginController {
    private LoginView view;
    private TaiKhoanService service;

    public LoginController(LoginView view) {
        this.view = view;
        this.service = new TaiKhoanService();

        view.btnLogin.addActionListener(e -> login());
    }

    private void login() {
    String user = view.txtUsername.getText();
    String pass = new String(view.txtPassword.getPassword());

    TaiKhoan tk = service.login(user, pass);

    if (tk != null) {

        String role = (tk.getMaVaiTro() == 1) ? "Admin" : "Nhân viên";

        JOptionPane.showMessageDialog(view, "Đăng nhập thành công!");

        view.dispose(); // đóng login

        TrangChuView trangChu = new TrangChuView(user, role);
        new TrangChuController(trangChu);
        trangChu.setVisible(true);

    } else {
        JOptionPane.showMessageDialog(view, "Sai tài khoản hoặc mật khẩu!");
    }
}
}
