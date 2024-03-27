/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.poly.lab01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author dangt
 */
public class Calculator extends javax.swing.JFrame {

    /**
     * Creates new form Calculator2
     */
    public Calculator() {
        initComponents();
        chu_chay();
    }
    
    public void chu_chay() {
        Thread threadl = new Thread() {
            @Override
            public void run() {
                String txt = lblChuChay.getText() + "  ";
                while (true) {
                    txt = txt.substring(1,txt.length() ) + txt.charAt(0);
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                lblChuChay.setText(txt);
                }
            }
            
        };
        threadl.start();
    }
    
    float a, b;
    String first, second, result;
    private final static String FILE_URL = "fileLab01\\\\calculator.txt";
    
    public void setXY() {
        this.first = txtFirstNumber.getText();
        this.second = txtSecondNumber.getText();
    }
    
    public boolean checkNull() {
        if (txtFirstNumber.getText().equals("") && txtSecondNumber.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị vào ô \n\tFirstNumber và SecondNumber!");
            txtFirstNumber.requestFocus();
            return false;
        }
        if (txtFirstNumber.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị vào ô \n\tFirstNumber!");
            txtFirstNumber.requestFocus();
            return false;
        }
        if (txtSecondNumber.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập giá trị vào ô \n\tSecondNumber!");
            txtSecondNumber.requestFocus();
            return false;
        }
        return true;
    }

    public boolean checkValidate() {
        try {
            this.a = Float.valueOf(txtFirstNumber.getText());
            this.b = Float.valueOf(txtSecondNumber.getText());
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void calculator(String s) {
        if (checkNull()) {
            if (checkValidate()) {
                if (s == "+") {
                    float tong = a + b;
                    if ((int) tong == tong) {
                        txtResult.setText(String.valueOf((int) tong));
                    } else {
                        txtResult.setText(String.valueOf(tong));
                    }
                    return;
                }
                if (s == "-") {
                    float hieu = a - b;
                    if ((int) hieu == hieu) {
                        txtResult.setText(String.valueOf((int) hieu));
                    } else {
                        txtResult.setText(String.valueOf(hieu));
                    }
                    return;
                }
                if (s == "*") {
                    float tich = a * b;
                    if ((int) tich == tich) {
                        txtResult.setText(String.valueOf((int) tich));
                    } else {
                        txtResult.setText(String.valueOf(tich));
                    }
                    return;
                }
                if (s == "/") {
                    //float temp = b;
                    while (true) {
                        if (b == 0) {
                            String temp = JOptionPane.showInputDialog(this, "Giá trị của SecondNumber không hợp lệ\n Vui lòng nhập lại giá trị?");
                            try {
                                b = Float.valueOf(temp);
                            } catch (Exception e) {
                                b = 0;
                            }
                        } else {
                            if ((int)b == b) {
                                txtSecondNumber.setText(String.valueOf((int) b));
                            } else {
                                txtSecondNumber.setText(String.valueOf(b));
                            }
                            //txtSecondNumber.setText(String.valueOf(b));
                            break;
                        }
                    }
                    float thuong = a / b;
                    if ((int) thuong == thuong) {
                        txtResult.setText(String.valueOf((int) thuong));
                    } else {
                        txtResult.setText(String.valueOf(thuong));
                    }
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(this, "Bạn nhập giá trị chưa đúng\n Vui lòng nhập lại!");
            }
        }
    }

    public void readFile() throws FileNotFoundException, IOException {
        File file = new File(FILE_URL);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line = "";
        int count = 0;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            if (count == 0) {
                txtFirstNumber.setText(line);
            }
            if (count == 1) {
                txtSecondNumber.setText(line);
            }
            if (count == 2) {
                txtResult.setText(line);
            }
            count++;

        }
    }
    
    public void outPutFile() throws FileNotFoundException, IOException {
        File file = new File(FILE_URL);
        InputStream inputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        String line = "";
        int count = 0;
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            if (count == 0) {
                //txtFirstNumber.setText(line);
                this.first=line;
            }
            if (count == 1) {
                //txtSecondNumber.setText(line);
                this.second=line;
            }
            if (count == 2) {
                //txtResult.setText(line);
                this.result=line;
            }
            count++;

        }
    }

    public void saveFile() throws FileNotFoundException, IOException {
        String[] data = {
            String.valueOf(txtFirstNumber.getText()),
            String.valueOf(txtSecondNumber.getText()),
            String.valueOf(txtResult.getText())
        };
        File file = new File(FILE_URL);
        OutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);

        for (String item : data) {
            outputStreamWriter.write(item);
            // Dùng để xuống hàng
            outputStreamWriter.write("\n");
        }
        // Đây là phương thức quan trọng!
        // Nó sẽ bắt chương trình chờ ghi dữ liệu xong thì mới kết thúc chương trình.
        outputStreamWriter.flush();
//        if (checkValidate()) {
//            String filePath = "fileLab01\\Calculator3.txt";
//            String noiDung = String.valueOf(a) + String.valueOf(b);
//            try {
//                BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));
//                bw.write(noiDung);
//                bw.close();
//                JOptionPane.showMessageDialog(this, "Saved");
//            } catch (IOException ex) {
//                JOptionPane.showMessageDialog(this, ex.getMessage());
//            }
//        }

//if (checkValidate()) {
//    try {
//        byte[] data = {
//            (byte) a, (byte) b
//        };
//        //byte[] data = XFile.read("fileLab01\\Calculator3.txt");
//        //byte[] data = XFile.read("fileLab05\\file1.txt");
//        XFile.write("fileLab01\\Calculator3.txt", data);
//        System.out.println("Thao tác đã hoàn thành vui lòng xem lại file");
//    } catch (Exception ex) {
//        System.out.println(ex.getMessage());
//    }
//}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnThuong = new javax.swing.JButton();
        btnHieu = new javax.swing.JButton();
        btnTong = new javax.swing.JButton();
        btnOpen = new javax.swing.JButton();
        btnSave = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        lblChuChay = new javax.swing.JLabel();
        txtSecondNumber = new javax.swing.JTextField();
        txtFirstNumber = new javax.swing.JTextField();
        txtResult = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btnTich = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnThuong.setText("/");
        btnThuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThuongActionPerformed(evt);
            }
        });

        btnHieu.setText("-");
        btnHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHieuActionPerformed(evt);
            }
        });

        btnTong.setText("+");
        btnTong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTongActionPerformed(evt);
            }
        });

        btnOpen.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnOpen.setForeground(new java.awt.Color(255, 0, 51));
        btnOpen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Open file.png"))); // NOI18N
        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        btnSave.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSave.setForeground(new java.awt.Color(255, 0, 51));
        btnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Save.png"))); // NOI18N
        btnSave.setText("Save");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnReset.setForeground(new java.awt.Color(255, 0, 51));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        lblChuChay.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        lblChuChay.setForeground(new java.awt.Color(51, 51, 255));
        lblChuChay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/businessman (1).png"))); // NOI18N
        lblChuChay.setText("Calculator by Tran Huu Dang");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("First number:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Second number:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText(" Result:");

        btnTich.setText("*");
        btnTich.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTichActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(137, Short.MAX_VALUE)
                .addComponent(lblChuChay, javax.swing.GroupLayout.PREFERRED_SIZE, 390, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(82, 82, 82))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(12, 12, 12)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnOpen)
                            .addGap(18, 18, 18)
                            .addComponent(btnSave)
                            .addGap(18, 18, 18)
                            .addComponent(btnReset)
                            .addGap(18, 18, 18)
                            .addComponent(btnTong, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(btnTich, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFirstNumber, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
                                .addComponent(txtSecondNumber, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtResult, javax.swing.GroupLayout.Alignment.TRAILING))))
                    .addGap(13, 13, 13)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(lblChuChay, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(360, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(120, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFirstNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addGap(31, 31, 31)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSecondNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))
                    .addGap(41, 41, 41)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtResult, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3))
                    .addGap(37, 37, 37)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTich, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnHieu, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnTong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThuong, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnOpen)
                        .addComponent(btnSave)
                        .addComponent(btnReset))
                    .addGap(47, 47, 47)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnThuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThuongActionPerformed
        calculator(btnThuong.getText());
    }//GEN-LAST:event_btnThuongActionPerformed

    private void btnHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHieuActionPerformed
        calculator(btnHieu.getText());
    }//GEN-LAST:event_btnHieuActionPerformed

    private void btnTongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTongActionPerformed
        calculator(btnTong.getText());
    }//GEN-LAST:event_btnTongActionPerformed

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        try {
            //saveFile();
            readFile();
            JOptionPane.showMessageDialog(this, "Đã mở lại thao tác cũ!");
        } catch (IOException ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveActionPerformed
        if (!txtFirstNumber.getText().equals("") && !txtSecondNumber.getText().equals("")){
            try {
                saveFile();
                JOptionPane.showMessageDialog(this, "Lưu thao tác thành công!");
            } catch (IOException ex) {
                Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Không thể lưu thao tác!");
        }

    }//GEN-LAST:event_btnSaveActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtFirstNumber.setText("");
        txtSecondNumber.setText("");
        txtResult.setText("");
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnTichActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTichActionPerformed
        calculator(btnTich.getText());
    }//GEN-LAST:event_btnTichActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            outPutFile();
        } catch (IOException ex) {
            Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!txtFirstNumber.getText().equals(first) || !txtSecondNumber.getText().equals(second) || !txtResult.getText().equals(result) ) {
            if (!txtFirstNumber.getText().equals("") && !txtSecondNumber.getText().equals("")){
                int ketQua = JOptionPane.showConfirmDialog(this, "Bạn đang muốn thoát?\nBạn muốn lưu trước khi thoát không?", "Lưu các giá trị", JOptionPane.YES_OPTION);
                if (ketQua == JOptionPane.YES_OPTION) {
                    try {
                        saveFile();
                    } catch (IOException ex) {
                        Logger.getLogger(Calculator.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JOptionPane.showMessageDialog(this, "Lưu thao tác thành công!");
                }

            }

        }

        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculator.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculator().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHieu;
    private javax.swing.JButton btnOpen;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSave;
    private javax.swing.JButton btnThuong;
    private javax.swing.JButton btnTich;
    private javax.swing.JButton btnTong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lblChuChay;
    private javax.swing.JTextField txtFirstNumber;
    private javax.swing.JTextField txtResult;
    private javax.swing.JTextField txtSecondNumber;
    // End of variables declaration//GEN-END:variables
}
