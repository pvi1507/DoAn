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
    // ===== COLOR (GIỐNG MÓN ĂN) =====
    Color bgMain = new Color(240, 248, 255);
    Color bgPanel = new Color(225, 240, 255);
    Color primary = new Color(0, 120, 215);
    Color textDark = new Color(30, 30, 30);
    Color white = Color.WHITE;

    private JTextField txtMaBan, txtTenBan;
    private JComboBox<String> cbTrangThai;
    private JTable table;
    private DefaultTableModel model;

    private BanAnController controller;

    public BanAnView() {
        setLayout(new BorderLayout(20,20));
        setBackground(bgMain);

        controller = new BanAnController(this);

        initUI();
        controller.loadTable();
    }

    private void initUI() {

        // ===== TITLE =====
        JLabel lblTitle = new JLabel("QUẢN LÝ BÀN", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 26));
        lblTitle.setForeground(primary);
        add(lblTitle, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout(20,20));
        mainPanel.setBackground(bgPanel);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20,40,20,40));

        // ===== FORM =====
        JPanel form = new JPanel(new GridLayout(3,2,15,15));
        form.setBackground(bgPanel);

        txtMaBan = createTextField();
        txtMaBan.setEditable(false);

        txtTenBan = createTextField();

        cbTrangThai = new JComboBox<>(new String[]{"Trống","Đang phục vụ"});
        styleComboBox(cbTrangThai);

        form.add(createLabel("Mã bàn"));
        form.add(txtMaBan);
        form.add(createLabel("Tên bàn"));
        form.add(txtTenBan);
        form.add(createLabel("Trạng thái"));
        form.add(cbTrangThai);

        // ===== BUTTON =====
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(bgPanel);

        JButton btnAdd = createButton("Thêm");
        JButton btnUpdate = createButton("Sửa");
        JButton btnDelete = createButton("Xóa");
        JButton btnRefresh = createButton("Làm mới");

        btnPanel.add(btnAdd);
        btnPanel.add(btnUpdate);
        btnPanel.add(btnDelete);
        btnPanel.add(btnRefresh);

        // ===== TOP =====
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
        top.setBackground(bgPanel);

        top.add(form);
        top.add(Box.createVerticalStrut(10));
        top.add(btnPanel);

        mainPanel.add(top, BorderLayout.NORTH);

        // ===== TABLE =====
        model = new DefaultTableModel(
                new String[]{"Mã","Tên bàn","Trạng thái"},0
        );

        table = new JTable(model);
        table.setRowHeight(30);
        table.setBackground(white);
        table.setForeground(textDark);
        table.setSelectionBackground(primary);
        table.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(table);
        mainPanel.add(scroll, BorderLayout.CENTER);

        add(mainPanel, BorderLayout.CENTER);

        // ===== EVENT =====
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

    // ===== STYLE =====
    private JTextField createTextField(){
        JTextField txt = new JTextField();
        txt.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txt.setBorder(BorderFactory.createLineBorder(primary));
        return txt;
    }

    private JLabel createLabel(String text){
        JLabel lbl = new JLabel(text);
        lbl.setForeground(primary);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return lbl;
    }

    private JButton createButton(String text){
        JButton btn = new JButton(text);
        btn.setBackground(primary);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void styleComboBox(JComboBox<?> cb){
        cb.setBackground(white);
        cb.setBorder(BorderFactory.createLineBorder(primary));
    }

    // ===== METHODS =====
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
