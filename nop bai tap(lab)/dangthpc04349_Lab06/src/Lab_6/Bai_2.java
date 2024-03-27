/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Lab_6;

import com.microsoft.sqlserver.jdbc.SQLServerPreparedStatement;
import java.util.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author dangt
 */
public class Bai_2 extends javax.swing.JFrame {

    boolean addNew = false;
    boolean fill = false;
    Vector data = new Vector();
    Vector header = new Vector();
    Vector col = new Vector();
    PreparedStatement pstDetails = null;
    PreparedStatement pstDetails1 = null;
    String sqlInsert = "Insert into Students (Name,Address,ParentName,Phone,standard) values(?,?,?,?,?)";
    String sqlDelete = "Delete from Students where Name=?";
    String sqlUpdate = "Update Students set Address=?, ParentName=?,Phone=? ,standard=? where Name=?";
    ResultSet rts , rts1 ;
    String user = "sa";
    String pass = "pc04349";
    String url = "jdbc:sqlserver://localhost:1433;database= KidszoneSchool";

    /**
     * Creates new form Bai_2
     */
    public Bai_2() throws SQLException {
//        "jdbc:sqlserver://localhost:1433;database=KidszoneSchool;userName=sa;password =090103"
        initComponents();
        database("select *from Students");
        setResizable(false);
        setLocationRelativeTo(null);
//         this.loadCombobox();

    }

    public void database(String s) {
        try {
            String name = txtName.getText();
            String addr = txtAddress.getText();
            String parentName = txtParentName.getText();
            String phone = txtContactNo.getText();
            String standard = (String) cbbStandard.getSelectedItem();
            String fees = (String) cbbFees.getSelectedItem();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            java.sql.Connection con = DriverManager.getConnection(url, user, pass);
            String sql = s;
            String s1 = "Insert into Standars (standard,fees) values(?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            PreparedStatement st1 = con.prepareStatement(s1);
            if (s.contains("Insert")) {
                st1.setString(1, standard);
                st1.setString(2, fees);
                st.setString(1, name);
                st.setString(2, addr);
                st.setString(3, parentName);
                st.setString(4, phone);
                st.setString(5, standard);

                st1.executeUpdate();
                st.executeUpdate();
            } else if (s.contains("Update")) {      //"    IT17307" --> trim() ==> "IT17307"
                st.setString(5, this.txtName.getText().trim());
                st.setString(2, this.txtAddress.getText().trim());
                st.setString(1, this.txtParentName.getText().trim());
                st.setString(3, this.txtContactNo.getText().trim());
                st.setString(4, (String) cbbStandard.getSelectedItem());
                st.executeUpdate();
            } else if (s.equalsIgnoreCase("Delete from Students where Name = ?")) {
                st1 = con.prepareStatement("Delete from Standars where standard = ?");
                st1.setString(1, standard);
                st.setString(1, name);
                st.executeUpdate();
                st1.executeUpdate();
            } else if (s.equalsIgnoreCase("select * from Students")) {
                pstDetails1 = con.prepareStatement("select *from Standars", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rts1 = pstDetails1.executeQuery();
                pstDetails = con.prepareStatement("select *from Students", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rts = pstDetails.executeQuery();
                JOptionPane.showMessageDialog(this, "Connection Database Successful!");
            }

            this.loadCombobox();
            this.loadData();
            fill = true;

//            con.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
            return;
        }
        btnUpdate.setEnabled(false);

    }

    private void loadCombobox() {

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            java.sql.Connection con = DriverManager.getConnection(url, user, pass);
            Statement stm = con.createStatement();
            String sql1 = "select * from Standars";
            ResultSet rs = stm.executeQuery(sql1);
            Vector<String> standards = new Vector<String>();
            Vector<Integer> fees = new Vector<Integer>();
            while (rs.next()) {
                cbbStandard.addItem(rs.getString(1));
                cbbFees.addItem(rs.getString(2));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
            System.exit(0);
        }
    }

    private void loadData() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            java.sql.Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "Select Name, standard from Students";
            ResultSet rs = st.executeQuery(sql);
            data.removeAllElements();
//           
            if (!fill) {
                //get header
                ResultSetMetaData rsmd = rs.getMetaData();
                int n = rsmd.getColumnCount();

                col.add(rsmd.getColumnName(1));
                col.add(rsmd.getColumnName(2));
            }
            //get data
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString(1));
                v.add(rs.getString(2));
                data.add(v);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e);
        }
        TableModel tbl = new DefaultTableModel(data, col);
        tblStudent.setModel(tbl);
    }

    boolean validates() {
        if (txtName.getText().matches("^\\w+$") == false) {
            JOptionPane.showMessageDialog(this, "Name ko dc trong ", "Chu y", 1);
            txtName.requestFocus();
            return false;
        }
        String pName = this.txtName.getText().trim();
        Iterator it = data.iterator();
        while (it.hasNext()) {
            Vector v = (Vector) it.next();
            String name = ((String) v.get(0)).trim();
            if (pName.equalsIgnoreCase(name)) {
                JOptionPane.showMessageDialog(this, "Ten Sinh Vien nay da ton tai!");
                this.txtName.grabFocus();
                return false;
            }
        }
        if (txtAddress.getText().matches("^\\w+$") == false) {
            JOptionPane.showMessageDialog(this, "Address khong duoc de trong ", "Chu y", 1);
            txtAddress.requestFocus();
            return false;
        }
        if (txtParentName.getText().matches("^\\w+$") == false) {
            JOptionPane.showMessageDialog(this, "ParentsName khong duoc de trong ", "Chu y", 1);
            txtParentName.requestFocus();
            return false;
        }
        if (txtContactNo.getText().matches("^\\d{7,11}$") == false) {
            JOptionPane.showMessageDialog(this, "Contact(Phone) khong duoc de trong va phai la 7-12 so ", "Chu y", 1);
            txtContactNo.requestFocus();
            return false;
        }
        return true;
    }

    boolean duplicate(String s) {
        if (addNew == false) {
            return false;
        }
        for (int i = 0; i < data.size(); i++) {
            Vector v = (Vector) data.get(i);
            if (s.equalsIgnoreCase((String) v.get(0))) {
                return true;
            }
        }
        return false;
    }

    private void clearForm() {
        txtName.setText("");
        txtAddress.setText("");
        txtParentName.setText("");
        txtContactNo.setText("");
        cbbStandard.setSelectedIndex(0);
        cbbFees.setSelectedIndex(0);
        txtName.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblStudent = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAddress = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        txtParentName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtContactNo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cbbStandard = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        cbbFees = new javax.swing.JComboBox<>();
        btnNew = new javax.swing.JButton();
        btnInsert = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnPre = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblStudent.setModel(new javax.swing.table.DefaultTableModel(
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
        tblStudent.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblStudentMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tblStudentMouseReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tblStudent);

        jLabel1.setText("Name :");

        jLabel2.setText("Address:");

        txtAddress.setColumns(20);
        txtAddress.setRows(5);
        jScrollPane2.setViewportView(txtAddress);

        jLabel3.setText("ParentName:");

        jLabel4.setText("ContactNo:");

        jLabel5.setText("Standard:");

        cbbStandard.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbbStandard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbStandardActionPerformed(evt);
            }
        });

        jLabel6.setText("Fees:");

        cbbFees.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "item11", "item12", "item13", "item14" }));
        cbbFees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbFeesActionPerformed(evt);
            }
        });

        btnNew.setText("New");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnInsert.setText("Insert");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnPre.setText("Previous");
        btnPre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPreActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 323, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(btnNew, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtParentName))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(cbbStandard, javax.swing.GroupLayout.Alignment.LEADING, 0, 192, Short.MAX_VALUE)
                                .addComponent(txtContactNo, javax.swing.GroupLayout.Alignment.LEADING))
                            .addComponent(cbbFees, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(23, 23, 23))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnPre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap(20, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel3))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtParentName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtContactNo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(cbbStandard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbFees, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNew)
                            .addComponent(btnInsert)
                            .addComponent(btnEdit)
                            .addComponent(btnUpdate))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNext)
                            .addComponent(btnPre)
                            .addComponent(btnDelete)
                            .addComponent(btnExit))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCancel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbbStandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbStandardActionPerformed
//        cbbFees.setSelectedIndex(cbbStandard.getSelectedIndex());
    }//GEN-LAST:event_cbbStandardActionPerformed

    private void cbbFeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbFeesActionPerformed
//        cbbStandard.setSelectedIndex(cbbFees.getSelectedIndex());
    }//GEN-LAST:event_cbbFeesActionPerformed

    private void tblStudentMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseReleased
        if (this.tblStudent.getCellEditor() != null)
            this.tblStudent.getCellEditor().cancelCellEditing();
    }//GEN-LAST:event_tblStudentMouseReleased

    private void tblStudentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblStudentMouseClicked
        try {
            clearForm();
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            java.sql.Connection con = DriverManager.getConnection(url, user, pass);
            Statement st = con.createStatement();
            String sql = "select *from Students,Standars WHERE Students.standard =Standars.standard";
            ResultSet rs = st.executeQuery(sql);
            int row = tblStudent.getSelectedRow();
            String name = (String) tblStudent.getValueAt(row, 0);
            while (rs.next()) {
                String str = rs.getString(2);
                if (str.equalsIgnoreCase(name)) {
                    txtName.setText(rs.getString(2));
                    txtAddress.setText(rs.getString(3));
                    txtParentName.setText(rs.getString(4));
                    txtContactNo.setText(rs.getInt(5) + "");
                    cbbStandard.setSelectedItem(rs.getString(7));
                    cbbFees.setSelectedItem(rs.getString(9));
                    //String parent=dencry(rts.getString(5));
                    //com_fees.setSelectedItem(rts.getInt(7));
                    break;
                }
            }
        } catch (Exception e) {
        }
        txtName.setEnabled(false);
        txtAddress.setEnabled(false);
        txtParentName.setEnabled(false);
        txtContactNo.setEnabled(false);
        cbbStandard.setEnabled(false);
        cbbFees.setEnabled(false);

    }//GEN-LAST:event_tblStudentMouseClicked

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
Iterator it = data.iterator();
        while (it.hasNext()) {
            Vector v = (Vector) it.next();
            String name = ((String) v.get(0)).trim();
            if (txtName.getText().equalsIgnoreCase(name)) {
                int choose = (JOptionPane.showConfirmDialog(this, "Bạn có muốn cập nhật lại", "Confirm", JOptionPane.YES_NO_OPTION));
                if (choose == JOptionPane.YES_OPTION) {
                    database(sqlUpdate);
                }
                return;
            }
        }

        database(sqlInsert);

    }//GEN-LAST:event_btnUpdateActionPerformed
public void eDit(){
     btnUpdate.setEnabled(true);
        btnCancel.setEnabled(true);
        btnEdit.setEnabled(true);
        txtName.setEnabled(true);
        txtAddress.setEnabled(true);
        txtParentName.setEnabled(true);
        txtContactNo.setEnabled(true);
        cbbStandard.setEnabled(true);
        cbbFees.setEnabled(true);
}
    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        eDit();
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        clearForm();
        txtName.setEnabled(true);
        txtAddress.setEnabled(true);
        txtParentName.setEnabled(true);
        txtContactNo.setEnabled(true);
        cbbFees.setEnabled(true);
        cbbStandard.setEnabled(true);
        txtName.requestFocus();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnPreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPreActionPerformed
        try {

            rts.previous();

            btnNext.setEnabled(true);
            if (rts.isBeforeFirst()) {

                btnPre.setEnabled(false);
                btnNext.setEnabled(true);
                JOptionPane.showMessageDialog(null, "You have reached the first record "
                        + "of the ResultSet!!!!");
            } else {
                txtName.setText(rts.getString(2));
                txtAddress.setText(rts.getString(3));
                txtParentName.setText(rts.getString(4));
                txtContactNo.setText(rts.getString(5));
                cbbStandard.setSelectedItem(rts.getString(7));
            }
        } catch (Exception e) {

        }

    }//GEN-LAST:event_btnPreActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        //loadCombobox();
//        try {
//            rts.next();
//            btnPre.setEnabled(true);
//            if (rts.isAfterLast() || rts.isBeforeFirst()) {
//                btnNext.setEnabled(false);
//                btnPre.setEnabled(true);
//                JOptionPane.showMessageDialog(null, "You have reached the last record"
//                        + " of the ResultSet!!!!");
//            } else {
//                txtName.setText(rts.getString(2));
//                txtAddress.setText(rts.getString(3));
//                txtParentName.setText(rts.getString(4));
//                txtContactNo.setText(rts.getString(5));
//                cbbStandard.setSelectedItem(rts.getString(7));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
try {
            rts.next();
            btnPre.setEnabled(true);
            if (rts.isAfterLast() || rts.isBeforeFirst()) {
                btnNext.setEnabled(false);
                btnPre.setEnabled(true);
                JOptionPane.showMessageDialog(null, "You have reached the last record"
                        + " of the ResultSet!!!!");
            } else {
                txtName.setText(rts.getString(2));
                txtAddress.setText(rts.getString(3));
                txtParentName.setText(rts.getString(4));
                txtContactNo.setText(rts.getString(5));
                cbbStandard.setSelectedItem(rts.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        fill = false;
        System.exit(0);

    }//GEN-LAST:event_btnExitActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        database("Delete from Students where Name = ?");
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertActionPerformed
        if (!validates()) {
            return;
        }
        database(sqlInsert);
    }//GEN-LAST:event_btnInsertActionPerformed

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        clearForm();
        eDit();
    }//GEN-LAST:event_btnNewActionPerformed

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
            java.util.logging.Logger.getLogger(Bai_2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Bai_2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Bai_2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Bai_2.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new Bai_2().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Bai_2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPre;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cbbFees;
    private javax.swing.JComboBox<String> cbbStandard;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tblStudent;
    private javax.swing.JTextArea txtAddress;
    private javax.swing.JTextField txtContactNo;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtParentName;
    // End of variables declaration//GEN-END:variables
}
