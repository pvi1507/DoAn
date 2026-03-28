package View;

import javax.swing.*;
import java.awt.*;

public class TrangChuView extends JFrame {

    public JButton btnTrangChu = new JButton("TRANG CHỦ");
    public JButton btnMonAn = new JButton("QUẢN LÝ MÓN");
    public JButton btnBan = new JButton("QUẢN LÝ BÀN");
    public JButton btnHoaDon = new JButton("HÓA ĐƠN");
    public JButton btnNhanVien = new JButton("NHÂN VIÊN");
    public JButton btnDangXuat = new JButton("ĐĂNG XUẤT");

    public JPanel contentPanel = new JPanel();

    public TrangChuView(String username, String role) {

        setTitle("LE CHÂTEAU DORÉ - ADMIN");
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color gold = new Color(212, 175, 55);
        Color darkBrown = new Color(30, 20, 10);
        Color softWhite = new Color(245, 240, 230);

        // ===== HEADER =====
        JPanel header = new JPanel();
        header.setBackground(darkBrown);
        header.setPreferredSize(new Dimension(1000, 80));
        header.setLayout(new BorderLayout());

        JLabel lblTitle = new JLabel("  LE CHÂTEAU DORÉ");
        lblTitle.setForeground(gold);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 26));

        JLabel lblUser = new JLabel("Xin chào: " + username + " (" + role + ")   ");
        lblUser.setForeground(softWhite);
        lblUser.setFont(new Font("SansSerif", Font.PLAIN, 14));

        header.add(lblTitle, BorderLayout.WEST);
        header.add(lblUser, BorderLayout.EAST);

        add(header, BorderLayout.NORTH);

        // ===== MENU =====
        JPanel menuPanel = new JPanel();
        menuPanel.setBackground(new Color(45, 30, 15));
        menuPanel.setPreferredSize(new Dimension(230, 0));
        menuPanel.setLayout(new GridLayout(7,1,10,10));

        styleButton(btnTrangChu, gold);
        styleButton(btnMonAn, gold);
        styleButton(btnBan, gold);
        styleButton(btnHoaDon, gold);
        styleButton(btnNhanVien, gold);
        styleButton(btnDangXuat, gold);

        menuPanel.add(btnTrangChu);

        if(role.equals("Admin")) {
            menuPanel.add(btnMonAn);
            menuPanel.add(btnNhanVien);
        }

        menuPanel.add(btnBan);
        menuPanel.add(btnHoaDon);
        menuPanel.add(btnDangXuat);

        add(menuPanel, BorderLayout.WEST);

        // ===== CONTENT =====
        contentPanel.setBackground(darkBrown);
        contentPanel.setLayout(new BorderLayout());

        JLabel lblWelcome = new JLabel("WELCOME TO LE CHÂTEAU DORÉ", SwingConstants.CENTER);
        lblWelcome.setFont(new Font("Serif", Font.BOLD, 32));
        lblWelcome.setForeground(gold);

        JLabel lblSub = new JLabel("Fine European Cuisine Management System", SwingConstants.CENTER);
        lblSub.setFont(new Font("SansSerif", Font.PLAIN, 16));
        lblSub.setForeground(softWhite);

        JPanel centerBox = new JPanel();
        centerBox.setBackground(darkBrown);
        centerBox.setLayout(new GridLayout(2,1));
        centerBox.add(lblWelcome);
        centerBox.add(lblSub);

        contentPanel.add(centerBox, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void styleButton(JButton button, Color gold) {
        button.setFocusPainted(false);
        button.setBackground(new Color(60, 40, 20));
        button.setForeground(gold);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBorder(BorderFactory.createLineBorder(gold, 1));
    }
}