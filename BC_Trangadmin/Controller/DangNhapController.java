package Controller;

import Model.TaiKhoan;
import Service.TaiKhoanService;
import View.DangNhapView;
import View.TrangChuView;

import javax.swing.*;

public class DangNhapController {

    private DangNhapView view;
    private TaiKhoanService service = new TaiKhoanService();

    public DangNhapController(DangNhapView view) {
        this.view = view;

        view.btnLogin.addActionListener(e -> login());
    }

    private void login() {

        String user = view.txtUser.getText();
        String pass = new String(view.txtPass.getPassword());

        TaiKhoan tk = service.login(user, pass);

        if (tk != null) {
            JOptionPane.showMessageDialog(view, "Đăng nhập thành công!");

            view.dispose();

            new TrangChuView(
                    tk.getTenDangNhap(),
                    tk.getTenVaiTro()
            ).setVisible(true);

        } else {
            JOptionPane.showMessageDialog(view, "Sai tài khoản hoặc mật khẩu!");
        }
    }
}