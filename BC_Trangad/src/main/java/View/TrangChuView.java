/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author DANG
 */
public class TrangChuView extends JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TrangChuView.class.getName());

    /**
     * Creates new form TrangChuView
     */
    public JButton btnTrangChu = new JButton("Trang chủ");
    public JButton btnMonAn = new JButton("Quản lý món ăn");
    public JButton btnBan = new JButton("Quản lý bàn");
    public JButton btnHoaDon = new JButton("Quản lý đơn hàng");
    public JButton btnNhanVien = new JButton("Quản lý người dùng");
    public JButton btnDangXuat = new JButton("Đăng xuất");

    public JPanel contentPanel = new JPanel();

    public TrangChuView(String username, String role) {

        setTitle("Quản lý nhà hàng");
        setSize(1200, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color background = new Color(245, 247, 250);

        /* ================= SIDEBAR ================= */
        JPanel menuPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(45, 110, 255),
                        0, getHeight(), new Color(30, 80, 230));
                g2.setPaint(gp);
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        menuPanel.setPreferredSize(new Dimension(260, 0));
        menuPanel.setLayout(new BorderLayout());

        /* ===== TITLE ===== */
        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setPreferredSize(new Dimension(260, 70));
        titlePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        titlePanel.setLayout(new BorderLayout());

        JLabel lblMenuTitle = new JLabel("Quản lý nhà hàng");
        lblMenuTitle.setForeground(Color.WHITE);
        lblMenuTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));

        titlePanel.add(lblMenuTitle, BorderLayout.CENTER);

        /* ===== MENU CENTER ===== */
        JPanel menuCenter = new JPanel();
        menuCenter.setOpaque(false);
        menuCenter.setLayout(new BoxLayout(menuCenter, BoxLayout.Y_AXIS));
        menuCenter.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        menuCenter.add(Box.createVerticalStrut(15)); // khoảng cách với title

        styleSidebarButton(btnTrangChu);
        styleSidebarButton(btnMonAn);
        styleSidebarButton(btnBan);
        styleSidebarButton(btnHoaDon);
        styleSidebarButton(btnNhanVien);

        menuCenter.add(btnTrangChu);
        menuCenter.add(Box.createVerticalStrut(8));

        if (role.equals("Admin")) {
            menuCenter.add(btnMonAn);
            menuCenter.add(Box.createVerticalStrut(8));

            menuCenter.add(btnNhanVien);
            menuCenter.add(Box.createVerticalStrut(8));
        }

        menuCenter.add(btnBan);
        menuCenter.add(Box.createVerticalStrut(8));

        menuCenter.add(btnHoaDon);

        /* ===== USER INFO ===== */
        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));

        JPanel userBox = new JPanel();
        userBox.setLayout(new GridLayout(3, 1));
        userBox.setBackground(new Color(255, 255, 255, 40));
        userBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblLogin = new JLabel("Đang đăng nhập với");
        lblLogin.setForeground(Color.WHITE);
        lblLogin.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JLabel lblUserName = new JLabel(username);
        lblUserName.setForeground(Color.WHITE);
        lblUserName.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JLabel lblRole = new JLabel(role);
        lblRole.setForeground(Color.LIGHT_GRAY);
        lblRole.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        userBox.add(lblLogin);
        userBox.add(lblUserName);
        userBox.add(lblRole);

        /* ===== LOGOUT ===== */
        btnDangXuat.setForeground(new Color(255, 120, 120));
        btnDangXuat.setBorderPainted(false);
        btnDangXuat.setContentAreaFilled(false);
        btnDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
        btnDangXuat.setFont(new Font("Segoe UI", Font.BOLD, 14));

        bottomPanel.add(userBox, BorderLayout.CENTER);
        bottomPanel.add(btnDangXuat, BorderLayout.SOUTH);

        /* ===== ADD SIDEBAR ===== */
        menuPanel.add(titlePanel, BorderLayout.NORTH);

// thêm khoảng cách giữa title và menu
        menuCenter.setBorder(BorderFactory.createEmptyBorder(20, 15, 10, 15));

        menuPanel.add(menuCenter, BorderLayout.CENTER);
        menuPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(menuPanel, BorderLayout.WEST);

        /* ================= CONTENT ================= */
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(background);

        JLabel welcome = new JLabel("HỆ THỐNG QUẢN LÝ NHÀ HÀNG", SwingConstants.CENTER);
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 30));

        JLabel sub = new JLabel("Restaurant Management System", SwingConstants.CENTER);
        sub.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        JPanel center = new JPanel(new GridLayout(2, 1));
        center.setOpaque(false);

        center.add(welcome);
        center.add(sub);

        contentPanel.add(center, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    /* ================= STYLE SIDEBAR BUTTON ================= */
    private void styleSidebarButton(JButton btn) {

        btn.setFocusPainted(false);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        btn.setOpaque(true);
        btn.setBackground(new Color(0, 0, 0, 0));

        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);

        btn.setAlignmentX(Component.LEFT_ALIGNMENT);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));

        btn.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(255, 255, 255, 40));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(new Color(0, 0, 0, 0));
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //java.awt.EventQueue.invokeLater(() -> new TrangChuView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
