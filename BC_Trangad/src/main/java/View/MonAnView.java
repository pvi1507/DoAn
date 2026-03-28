/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.LoaiMon;
import Service.LoaiMonService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import static java.awt.Color.white;
import java.io.File;
import java.util.List;

/**
 *
 * @author DANG
 */
public class MonAnView extends JPanel {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MonAnView.class.getName());

    /**
     * Creates new form MonAnView
     */
    // ===== COLOR =====
    Color bgMain = new Color(240, 248, 255);
    Color bgPanel = new Color(225, 240, 255);
    Color primary = new Color(0, 120, 215);

    // ===== SERVICE =====
    private LoaiMonService loaiService = new LoaiMonService();

    // ===== COMPONENT MÓN ĂN (GIỮ NGUYÊN) =====
    public JTextField txtMa, txtTen, txtGia, txtMoTa, txtTim;
    public JComboBox<String> cbLoai;
    public JLabel lblHinhAnh;
    public JButton btnChonAnh, btnThem, btnSua, btnXoa, btnLamMoi, btnTim;
    public JTable tblMonAn;
    public DefaultTableModel model;
    public String duongDanAnh = "";

    // ===== COMPONENT LOẠI MÓN (THÊM MỚI) =====
    public JTextField txtMaLoai, txtTenLoai, txtTimLoai;
    public JButton btnThemLoai, btnSuaLoai, btnXoaLoai, btnLamMoiLoai, btnTimLoai;
    public JTable tblLoaiMon;
    public DefaultTableModel modelLoai;

    public MonAnView() {
        setLayout(new BorderLayout());
        setBackground(bgMain);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("QUẢN LÝ MÓN ĂN", createMonAnPanel());
        tabs.addTab("QUẢN LÝ LOẠI MÓN", createLoaiMonPanel());

        add(tabs, BorderLayout.CENTER);

        // Khởi tạo dữ liệu ban đầu cho Loại Món
        //loadTableLoai();
        updateComboBoxLoai();
        new Controller.LoaiMonController(this);
    }

    // ================= PANEL MÓN ĂN (GIỮ NGUYÊN CẤU TRÚC CỦA BẠN) =================
    private JPanel createMonAnPanel() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(bgPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));

        JLabel title = new JLabel("QUẢN LÝ MÓN ĂN", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(primary);
        mainPanel.add(title, BorderLayout.NORTH);

        JPanel topPanel = new JPanel(new BorderLayout(10, 10));
        topPanel.setBackground(bgPanel);

        // FORM
        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBackground(bgPanel);
        txtMa = createTextField();
        txtMa.setEditable(false);
        txtTen = createTextField();
        txtGia = createTextField();
        txtMoTa = createTextField();
        cbLoai = new JComboBox<>(); // Sẽ được load từ database

        form.add(createLabel("Mã món"));
        form.add(txtMa);
        form.add(createLabel("Tên món"));
        form.add(txtTen);
        form.add(createLabel("Giá"));
        form.add(txtGia);
        form.add(createLabel("Loại món"));
        form.add(cbLoai);
        form.add(createLabel("Mô tả"));
        form.add(txtMoTa);

        // ẢNH
        lblHinhAnh = new JLabel("Chưa có ảnh", SwingConstants.CENTER);
        lblHinhAnh.setPreferredSize(new Dimension(250, 120));
        lblHinhAnh.setBorder(BorderFactory.createLineBorder(primary));
        lblHinhAnh.setOpaque(true);
        btnChonAnh = createButton("Chọn ảnh");
        btnChonAnh.addActionListener(e -> chonAnh());

        JPanel imgPanel = new JPanel(new BorderLayout(5, 5));
        imgPanel.setBackground(bgPanel);
        imgPanel.add(lblHinhAnh, BorderLayout.CENTER);
        imgPanel.add(btnChonAnh, BorderLayout.SOUTH);

        JPanel formImg = new JPanel(new GridLayout(1, 2, 20, 0));
        formImg.setBackground(bgPanel);
        formImg.add(form);
        formImg.add(imgPanel);

        // SEARCH & BUTTONS
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        searchPanel.setBackground(bgPanel);
        txtTim = new JTextField(20);
        btnTim = createButton("Tìm");
        searchPanel.add(new JLabel("Tìm:"));
        searchPanel.add(txtTim);
        searchPanel.add(btnTim);

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(bgPanel);
        btnThem = createButton("Thêm");
        btnSua = createButton("Sửa");
        btnXoa = createButton("Xóa");
        btnLamMoi = createButton("Làm mới");
        btnPanel.add(btnThem);
        btnPanel.add(btnSua);
        btnPanel.add(btnXoa);
        btnPanel.add(btnLamMoi);

        JPanel containerTop = new JPanel();
        containerTop.setLayout(new BoxLayout(containerTop, BoxLayout.Y_AXIS));
        containerTop.setBackground(bgPanel);
        containerTop.add(searchPanel);
        containerTop.add(formImg);
        containerTop.add(btnPanel);

        topPanel.add(containerTop, BorderLayout.CENTER);

        // TABLE
        // Sửa đoạn khởi tạo model của bạn thành thế này:
        model = new DefaultTableModel(new String[]{"Mã", "Tên", "Giá", "Loại", "Mô tả", "Ảnh"}, 0) {
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Cột Ảnh là cột cuối cùng (chỉ số 5)
                if (columnIndex == 5) {
                    return ImageIcon.class;
                }
                return Object.class;
            }
        };
        tblMonAn = new JTable(model);
        tblMonAn.setRowHeight(80); // Tăng chiều cao dòng để nhìn rõ ảnh

        JScrollPane tableScroll = new JScrollPane(tblMonAn);

        mainPanel.add(topPanel, BorderLayout.CENTER);
        mainPanel.add(tableScroll, BorderLayout.SOUTH);

        return mainPanel;
    }

    // ================= PANEL LOẠI MÓN (THÊM MỚI HOÀN THIỆN) =================
    private JPanel createLoaiMonPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBackground(bgPanel);
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.setBackground(bgPanel);

        // Khởi tạo ở đây
        txtTimLoai = new JTextField(25);
        btnTimLoai = createButton("Tìm kiếm");

        searchPanel.add(new JLabel("Tìm kiếm loại món: "));
        searchPanel.add(txtTimLoai);
        searchPanel.add(btnTimLoai);

        // --- 2. PHẦN BẢNG DỮ LIỆU (CENTER) ---
        modelLoai = new DefaultTableModel(new String[]{"Mã Loại", "Tên Loại Món"}, 0);
        tblLoaiMon = new JTable(modelLoai);
        tblLoaiMon.setRowHeight(30);
        JScrollPane spLoai = new JScrollPane(tblLoaiMon);
        spLoai.setBorder(BorderFactory.createTitledBorder("Danh sách loại món"));

        // --- 3. PHẦN FORM & NÚT (SOUTH) ---
        JPanel southPanel = new JPanel(new GridBagLayout()); // Dùng GridBagLayout để căn chỉnh chuẩn
        southPanel.setBackground(bgPanel);
        southPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các ô
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Hàng 1: Label và TextField
        gbc.gridx = 0;
        gbc.gridy = 0;
        southPanel.add(new JLabel("Mã loại:"), gbc);

        txtMaLoai = createTextField();
        txtMaLoai.setEditable(false);
        txtMaLoai.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        southPanel.add(txtMaLoai, gbc);

        gbc.gridx = 2;
        gbc.gridy = 0;
        southPanel.add(new JLabel("Tên loại:"), gbc);

        txtTenLoai = createTextField();
        txtTenLoai.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Cho ô tên loại giãn ra
        southPanel.add(txtTenLoai, gbc);

        // Hàng 2: Các nút bấm
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnPanel.setBackground(bgPanel);
        btnThemLoai = createButton("Thêm");
        btnSuaLoai = createButton("Sửa");
        btnXoaLoai = createButton("Xóa");
        btnLamMoiLoai = createButton("Làm mới");

        btnPanel.add(btnThemLoai);
        btnPanel.add(btnSuaLoai);
        btnPanel.add(btnXoaLoai);
        btnPanel.add(btnLamMoiLoai);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 4; // Trải dài qua 4 cột
        southPanel.add(btnPanel, gbc);

        // --- GHÉP VÀO PANEL CHÍNH ---
        panel.add(searchPanel, BorderLayout.NORTH);
        panel.add(spLoai, BorderLayout.CENTER);
        panel.add(southPanel, BorderLayout.SOUTH);

        // Khởi tạo sự kiện
        loadTableLoai();

        return panel;
    }

    

    public void loadTableLoai() {
        modelLoai.setRowCount(0);
        loaiService.getAll().forEach(lm -> modelLoai.addRow(new Object[]{lm.getMaLoai(), lm.getTenLoai()}));
    }

    public void updateComboBoxLoai() {
        cbLoai.removeAllItems();
        loaiService.getAll().forEach(lm -> cbLoai.addItem(lm.getTenLoai()));
    }

    private void refreshLoaiData() {
        loadTableLoai();
        updateComboBoxLoai();
    }

    // ================= STYLE HELPERS (GIỮ NGUYÊN) =================
    private JTextField createTextField() {
        JTextField txt = new JTextField();
        txt.setBorder(BorderFactory.createLineBorder(primary));
        return txt;
    }

    private JButton createButton(String text) {
        JButton btn = new JButton(text);
        btn.setBackground(primary);
        btn.setForeground(Color.WHITE);
        return btn;
    }

    private JLabel createLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setForeground(primary);
        return lbl;
    }

    private void chonAnh() {
        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            duongDanAnh = file.getAbsolutePath();
            ImageIcon icon = new ImageIcon(new ImageIcon(duongDanAnh).getImage()
                    .getScaledInstance(250, 120, Image.SCALE_SMOOTH));
            lblHinhAnh.setIcon(icon);
            lblHinhAnh.setText("");
        }
    }

    // ===== CLEAR =====
    public void clearForm() {
        txtMa.setText("");
        txtTen.setText("");
        txtGia.setText("");
        txtMoTa.setText("");
        txtTim.setText("");

        cbLoai.setSelectedIndex(0);

        lblHinhAnh.setIcon(null);
        lblHinhAnh.setText("Chưa có ảnh");

        duongDanAnh = "";
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
