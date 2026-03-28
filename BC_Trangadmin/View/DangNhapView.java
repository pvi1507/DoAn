package View;

import javax.swing.*;
import java.awt.*;

public class DangNhapView extends JFrame {

    public JTextField txtUser = new JTextField();
    public JPasswordField txtPass = new JPasswordField();
    public JButton btnLogin = new JButton("ĐĂNG NHẬP");

    public DangNhapView() {

        setTitle("Đăng nhập - Quản Lý Nhà Hàng");
        setSize(450, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);

        // ===== Panel chính =====
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(34, 40, 49));
        mainPanel.setLayout(null);

        // ===== Tiêu đề =====
        JLabel lblTitle = new JLabel("QUẢN LÝ NHÀ HÀNG");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitle.setBounds(90, 30, 300, 40);
        mainPanel.add(lblTitle);

        JLabel lblSub = new JLabel("HỆ THỐNG ĐĂNG NHẬP");
        lblSub.setForeground(Color.LIGHT_GRAY);
        lblSub.setFont(new Font("Arial", Font.PLAIN, 14));
        lblSub.setBounds(130, 65, 250, 30);
        mainPanel.add(lblSub);

        // ===== Username =====
        JLabel lblUser = new JLabel("Tên đăng nhập");
        lblUser.setForeground(Color.WHITE);
        lblUser.setBounds(80, 110, 150, 25);
        mainPanel.add(lblUser);

        txtUser.setBounds(80, 135, 280, 35);
        txtUser.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(txtUser);

        // ===== Password =====
        JLabel lblPass = new JLabel("Mật khẩu");
        lblPass.setForeground(Color.WHITE);
        lblPass.setBounds(80, 180, 150, 25);
        mainPanel.add(lblPass);

        txtPass.setBounds(80, 205, 280, 35);
        txtPass.setFont(new Font("Arial", Font.PLAIN, 14));
        mainPanel.add(txtPass);

        // ===== Nút Login =====
        btnLogin.setBounds(80, 260, 280, 40);
        btnLogin.setBackground(new Color(0, 173, 181));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setFocusPainted(false);
        btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        mainPanel.add(btnLogin);

        add(mainPanel);
    }
}