/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Controller.BanAnController;
import Model.BanAn;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import javax.swing.border.TitledBorder;

/**
 *
 * @author DANG
 */
public class BanAnView extends JPanel {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(BanAnView.class.getName());

    /**
     * Creates new form BanAnView
     */
    private JTextField txtMaBan, txtTenBan;
    private JComboBox<String> cbTrangThai;
    private JTable table;
    private DefaultTableModel model;

    private BanAnController controller;

    public BanAnView() {
        setLayout(new BorderLayout());
        setBackground(new Color(60, 25, 0));

        controller = new BanAnController(this);

        initUI();
        controller.loadTable();
    }

    private void initUI() {

        // ===== TITLE =====
        JLabel lblTitle = new JLabel("QUẢN LÝ BÀN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Serif", Font.BOLD, 28));
        lblTitle.setForeground(new Color(212, 175, 55));
        lblTitle.setBorder(BorderFactory.createEmptyBorder(20,0,20,0));
        add(lblTitle, BorderLayout.NORTH);

        // ===== FORM PANEL =====
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(60, 25, 0));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        JLabel lblMa = new JLabel("Mã Bàn");
        lblMa.setForeground(new Color(212,175,55));
        gbc.gridx=0; gbc.gridy=0;
        formPanel.add(lblMa, gbc);

        txtMaBan = new JTextField(15);
        txtMaBan.setEditable(false); // 🔥 không cho sửa mã
        gbc.gridx=1;
        formPanel.add(txtMaBan, gbc);

        JLabel lblTen = new JLabel("Tên Bàn");
        lblTen.setForeground(new Color(212,175,55));
        gbc.gridx=0; gbc.gridy=1;
        formPanel.add(lblTen, gbc);

        txtTenBan = new JTextField(15);
        gbc.gridx=1;
        formPanel.add(txtTenBan, gbc);

        JLabel lblTrangThai = new JLabel("Trạng Thái");
        lblTrangThai.setForeground(new Color(212,175,55));
        gbc.gridx=0; gbc.gridy=2;
        formPanel.add(lblTrangThai, gbc);

        cbTrangThai = new JComboBox<>(new String[]{"Trống", "Đang phục vụ"});
        gbc.gridx=1;
        formPanel.add(cbTrangThai, gbc);

        // ===== BUTTON PANEL =====
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(60, 25, 0));

        JButton btnAdd = createButton("Thêm");
        JButton btnUpdate = createButton("Sửa");
        JButton btnDelete = createButton("Xóa");
        JButton btnRefresh = createButton("Làm mới");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);

        gbc.gridx=0; gbc.gridy=3; gbc.gridwidth=2;
        formPanel.add(btnPanel, gbc);

        add(formPanel, BorderLayout.CENTER);

        // ===== TABLE =====
        model = new DefaultTableModel(new String[]{"Mã", "Tên Bàn", "Trạng Thái"},0);
        table = new JTable(model);
        table.setRowHeight(25);

        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(new TitledBorder("Danh sách bàn"));
        add(scroll, BorderLayout.SOUTH);

        // ===== EVENTS =====
        btnAdd.addActionListener(e -> controller.addBan());
        btnUpdate.addActionListener(e -> controller.updateBan());
        btnDelete.addActionListener(e -> controller.deleteBan());
        btnRefresh.addActionListener(e -> clearForm());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if(row >= 0){
                txtMaBan.setText(model.getValueAt(row,0).toString());
                txtTenBan.setText(model.getValueAt(row,1).toString());
                cbTrangThai.setSelectedItem(model.getValueAt(row,2).toString());
            }
        });
    }

    private JButton createButton(String text){
        JButton btn = new JButton(text);
        btn.setBackground(new Color(212,175,55));
        btn.setFocusPainted(false);
        return btn;
    }

    public void showTable(List<BanAn> list){
        model.setRowCount(0);
        for(BanAn b : list){
            model.addRow(new Object[]{
                    b.getMaBan(),
                    b.getTenBan(),
                    b.getTrangThai()
            });
        }
    }

    public void clearForm(){
        txtMaBan.setText("");
        txtTenBan.setText("");
        cbTrangThai.setSelectedIndex(0);
        table.clearSelection();
    }

    public String getMaBanText(){
        return txtMaBan.getText();
    }

    public int getMaBan(){
        return Integer.parseInt(txtMaBan.getText());
    }

    public String getTenBan(){
        return txtTenBan.getText();
    }

    public String getTrangThai(){
        return cbTrangThai.getSelectedItem().toString();
    }

    public void showMessage(String msg){
        JOptionPane.showMessageDialog(this, msg);
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
        java.awt.EventQueue.invokeLater(() -> new BanAnView().setVisible(true));
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
