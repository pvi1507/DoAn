/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;

/**
 *
 * @author DANG
 */
public class MonAnView extends JPanel {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MonAnView.class.getName());

    /**
     * Creates new form MonAnView
     */
    public JTextField txtMa, txtTen, txtGia;
    public JComboBox<String> cbLoai;
    public JComboBox<String> cbTrangThai;
    public JLabel lblHinhAnh;
    public JButton btnChonAnh, btnThem, btnSua, btnXoa, btnLamMoi;

    public JTable tblMonAn;
    public DefaultTableModel model;
    public JTextField txtHinh;
    public JButton btnChonHinh;

    public String duongDanAnh = "";

    public MonAnView() {

        setLayout(new BorderLayout(20, 20));
        setBackground(new Color(30, 15, 5));

        // ===== TITLE =====
        JLabel title = new JLabel("QUẢN LÝ MÓN ĂN", SwingConstants.CENTER);
        title.setFont(new Font("Serif", Font.BOLD, 28));
        title.setForeground(new Color(212, 175, 55));
        add(title, BorderLayout.NORTH);

        // ===== MAIN PANEL =====
        JPanel mainPanel = new JPanel(new BorderLayout(20, 20));
        mainPanel.setBackground(new Color(45, 25, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        // ===== TOP PANEL (FORM + BUTTONS) =====
        JPanel topPanel = new JPanel(new BorderLayout(20, 20));
        topPanel.setBackground(new Color(45, 25, 10));

        // ===== FORM =====
        JPanel form = new JPanel(new GridLayout(6, 2, 15, 15));
        form.setBackground(new Color(45, 25, 10));
        form.setPreferredSize(new Dimension(1000, 260));
        txtMa = createTextField();
        txtMa.setEditable(false);
        txtTen = createTextField();
        txtGia = createTextField();

        cbLoai = new JComboBox<>(new String[]{"Món chính", "Khai vị", "Tráng miệng", "Đồ uống"});
        cbTrangThai = new JComboBox<>(new String[]{"Đang bán", "Ngưng bán"});

        lblHinhAnh = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
        lblHinhAnh.setPreferredSize(new Dimension(250, 200));
        lblHinhAnh.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        lblHinhAnh.setForeground(Color.WHITE);

        btnChonAnh = createButton("Chọn ảnh");
        btnChonAnh.addActionListener(e -> chonAnh());

        form.add(createLabel("Mã Món"));
        form.add(txtMa);
        form.add(createLabel("Tên Món"));
        form.add(txtTen);
        form.add(createLabel("Giá"));
        form.add(txtGia);
        form.add(createLabel("Loại Món"));
        form.add(cbLoai);
        form.add(createLabel("Trạng Thái"));
        form.add(cbTrangThai);
        form.add(lblHinhAnh);
        form.add(btnChonAnh);

        topPanel.add(form, BorderLayout.CENTER);

        // ===== BUTTONS =====
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        btnPanel.setBackground(new Color(45, 25, 10));

        btnThem = createButton("Thêm");
        btnSua = createButton("Sửa");
        btnXoa = createButton("Xóa");
        btnLamMoi = createButton("Làm mới");

        btnPanel.add(btnThem);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);
        btnPanel.add(btnLamMoi);

        topPanel.add(btnPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"Mã", "Tên", "Giá", "Loại", "Trạng thái", "Hình ảnh"},
                0
        ) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) {
                    return ImageIcon.class;
                }
                return String.class;
            }
        };

        tblMonAn = new JTable(model);
        tblMonAn.setRowHeight(70);

        JScrollPane scroll = new JScrollPane(tblMonAn);
        scroll.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(212, 175, 55)),
                "Danh sách món ăn"
        ));

        mainPanel.add(scroll, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.CENTER);

        btnLamMoi.addActionListener(e -> clearForm());

        // Khi click dòng hiển thị lại ảnh lên label
        tblMonAn.getSelectionModel().addListSelectionListener(e -> {
            int row = tblMonAn.getSelectedRow();
            if (row >= 0) {
                ImageIcon icon = (ImageIcon) model.getValueAt(row, 5);
                lblHinhAnh.setIcon(icon);
                lblHinhAnh.setText("");
            }
        });
    }

    private void chonAnh() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            duongDanAnh = file.getAbsolutePath();

            ImageIcon icon = new ImageIcon(new ImageIcon(duongDanAnh)
                    .getImage()
                    .getScaledInstance(120, 100, Image.SCALE_SMOOTH));

            lblHinhAnh.setText("");
            lblHinhAnh.setIcon(icon);
        }
    }

    public void clearForm() {
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        cbLoai.setSelectedIndex(0);
        cbTrangThai.setSelectedIndex(0);
        lblHinhAnh.setText("Chưa có ảnh");
        lblHinhAnh.setIcon(null);
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(new Color(212, 175, 55));
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        return lbl;
    }

    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return txt;
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(new Color(212, 175, 55));
        btn.setForeground(Color.BLACK);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return btn;
    }

    private void btnChonAnhActionPerformed(java.awt.event.ActionEvent evt) {

        JFileChooser chooser = new JFileChooser();
        int result = chooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();

            duongDanAnh = file.getAbsolutePath();

            ImageIcon icon = new ImageIcon(duongDanAnh);
            Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            lblHinhAnh.setIcon(new ImageIcon(img));
        }
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
        java.awt.EventQueue.invokeLater(() -> new MonAnView().setVisible(true));
    }

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private Container getContentPane() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void pack() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
