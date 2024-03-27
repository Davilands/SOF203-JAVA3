/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai_them;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dangt
 */
public class DonViHanhChinh extends javax.swing.JFrame {

    String name;
    String user = "sa";
    String pass = "pc04349";
    String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=TinhThanhVietNam;encrypt=false;";
    List<Provinces> listTinh = new ArrayList<>();
    List<Districts> listHuyen = new ArrayList<>();
    List<Wards> listPhuong = new ArrayList<>();
    
    List<NhanVien> listNhanVien = new ArrayList<>();
    DefaultTableModel model;
    /**
     * Creates new form DonViHanhChinh
     */
    public DonViHanhChinh() {
        initComponents();
        getAllTinh();
        //System.out.println(list.get(15).tenTinh);
        loaddata();
        loaddataModel();
    }

    public void loaddata() {
        for (Provinces tinh : listTinh) {
            cboTinh.addItem(tinh.tenTinh);
        }
    }
    
    public void getAllNV(){
        listNhanVien.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsinhvien";

            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from nhanvien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String HOTEN = rs.getString(1);
                String SDT = rs.getString(2);
                String DIACHI = rs.getString(3);
                //System.out.println(TENTAIKHOAN);
                listNhanVien.add(new NhanVien(HOTEN, SDT, DIACHI));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void loaddataModel() {
        model = new DefaultTableModel();
        //Tạo các cột
        model.addColumn("HỌ TÊN");
        model.addColumn("SỐ ĐIỆN THOẠI");
        model.addColumn("ĐỊA CHỈ");

        //Đẩy Data từ List --> model
        fillToTable();
    }
    public void fillToTable() {
        getAllNV();
        model.setRowCount(0);
        for (NhanVien index : listNhanVien) {
            //for (InforPC index : inforPCDAO.getAll()) {
            model.addRow(new Object[]{index.getHoTen(), index.getSdt(), index.getDiaChi()});
        }
        tblList.setModel(model);
    }
    

    public void loaddataHuyen() {
        getAllHuyen(cboTinh.getSelectedItem().toString());
        System.out.println(cboTinh.getSelectedItem().toString());
        cboHuyen.removeAllItems();
        for (Districts huyen : listHuyen) {
            cboHuyen.addItem(huyen.tenHuyen);
        }
    }

    public void loaddataPhuong() {
        getAllPhuong(cboHuyen.getSelectedItem().toString());
        cboPhuong.removeAllItems();
        for (Wards phuong : listPhuong) {
            cboPhuong.addItem(phuong.tenPhuong);
        }
    }

    public boolean check() {
        if (cboHuyen.getSelectedIndex() < 0) {
            return false;
        }
        if (cboPhuong.getSelectedIndex() < 0) {
            return false;
        }
        if (cboTinh.getSelectedIndex() < 0) {
            return false;
        }
        return true;
    }

    public void add() {
        String dchi = cboPhuong.getSelectedItem().toString() + ", " + cboHuyen.getSelectedItem().toString() + ", " + cboTinh.getSelectedItem().toString();
        if(!check()){
            JOptionPane.showMessageDialog(this, "Vui lòng chọn đầy đủ thông tin địa chỉ!");
            return;
        }
        if(txtHoten.equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng điền tên của bạn!");
            return;
        }
        if(txtSdt.equals("")){
            JOptionPane.showMessageDialog(this, "Vui lòng điền số điện thoại của bạn!");
            return;
        }
            
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into NHANVIEN values(?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, txtHoten.getText());
            st.setString(2, txtSdt.getText());

            st.setString(3, dchi);
            st.executeUpdate();
            //JOptionPane.showMessageDialog(this, "Thêm thành công!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        loaddataModel();
    }

    public void getAllTinh() {
        listTinh.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsinhvien";

            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select code,full_name from provinces";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MATINH = rs.getInt(1);
                String TENTINH = rs.getString(2);
                //System.out.println(TENTAIKHOAN);
                listTinh.add(new Provinces(MATINH, TENTINH));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getAllHuyen(String tenTinh) {
        listHuyen.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsinhvien";

            Connection con = DriverManager.getConnection(url, user, pass);
            //select id,title,province_id from wards where province_id = (select id from provinces where title = 'An Giang') 
            String sql = "select code, full_name,province_code from districts where province_code = (select code from provinces where full_name = N'" + tenTinh + "')";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MAHUYEN = rs.getInt(1);
                String TENHUYEN = rs.getString(2);
                int MATINH = rs.getInt(3);
                //System.out.println(TENTAIKHOAN);
                listHuyen.add(new Districts(MAHUYEN, TENHUYEN, MATINH));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void getAllPhuong(String tenHuyen) {
        listHuyen.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsinhvien";

            Connection con = DriverManager.getConnection(url, user, pass);
            //select id,title,province_id from wards where province_id = (select id from provinces where title = 'An Giang') 
            String sql = "select code, full_name, district_code from wards where district_code = (select code from districts where full_name = N'" + tenHuyen + "')";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                int MAPHUONG = rs.getInt(1);
                String TENPHUONG = rs.getString(2);
                int MAHUYEN = rs.getInt(3);
                //System.out.println(TENTAIKHOAN);
                listPhuong.add(new Wards(MAPHUONG, TENPHUONG, MAHUYEN));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
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

        cboPhuong = new javax.swing.JComboBox<>();
        cboTinh = new javax.swing.JComboBox<>();
        cboHuyen = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblList = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        txtSdt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHoten = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        getContentPane().add(cboPhuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 230, 204, 28));

        cboTinh.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTinhItemStateChanged(evt);
            }
        });
        cboTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cboTinhMouseClicked(evt);
            }
        });
        cboTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTinhActionPerformed(evt);
            }
        });
        getContentPane().add(cboTinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(482, 52, 204, 28));

        getContentPane().add(cboHuyen, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, 204, 28));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Xã/thị trấn");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 230, 80, -1));

        tblList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblList);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 290, 760, 200));

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("tỉnh");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(413, 50, 40, -1));

        jButton1.setText("Xác nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 80, 30));

        jButton3.setText("Xác nhận");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 140, 80, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("huyện");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 140, 50, -1));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel1.add(txtSdt, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, 200, 29));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Số điện thoại");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 89, -1));

        txtHoten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHotenActionPerformed(evt);
            }
        });
        jPanel1.add(txtHoten, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 200, 29));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Họ tên");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        jButton2.setText("Thêm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 240, 80, 30));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cboTinhMouseClicked

    }//GEN-LAST:event_cboTinhMouseClicked

    private void cboTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTinhActionPerformed
        //loaddataHuyen();
    }//GEN-LAST:event_cboTinhActionPerformed

    private void cboTinhItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTinhItemStateChanged

    }//GEN-LAST:event_cboTinhItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loaddataHuyen();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        loaddataPhuong();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtHotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHotenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHotenActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        add();
    }//GEN-LAST:event_jButton2ActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DonViHanhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DonViHanhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DonViHanhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DonViHanhChinh.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DonViHanhChinh().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboHuyen;
    private javax.swing.JComboBox<String> cboPhuong;
    private javax.swing.JComboBox<String> cboTinh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblList;
    private javax.swing.JTextField txtHoten;
    private javax.swing.JTextField txtSdt;
    // End of variables declaration//GEN-END:variables
}
