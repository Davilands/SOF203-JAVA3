/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Lab_2;

import javax.swing.JOptionPane;

/**
 *
 * @author dangt
 */
public class Bai_2 extends javax.swing.JFrame {

    /**
     * Creates new form Bai_2
     */
    public Bai_2() {
        initComponents();
        casio1.setVisible(false);
        casio2.setVisible(false);
    }
    private long a = 0;
    private String pt = "";

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        casio = new javax.swing.JPanel();
        btn9 = new javax.swing.JButton();
        btnbang = new javax.swing.JButton();
        btn0 = new javax.swing.JButton();
        txtSo = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnct = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btnc = new javax.swing.JButton();
        btn2 = new javax.swing.JButton();
        btnchia = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btnnhan = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btntru = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btncong = new javax.swing.JButton();
        btn6 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        btn7 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        btn8 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        casio1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btn20 = new javax.swing.JButton();
        btnbang2 = new javax.swing.JButton();
        btn21 = new javax.swing.JButton();
        txtSo2 = new javax.swing.JTextField();
        btn22 = new javax.swing.JButton();
        btnc2 = new javax.swing.JButton();
        btn24 = new javax.swing.JButton();
        btn25 = new javax.swing.JButton();
        btn27 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        btn28 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        casio2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnbang3 = new javax.swing.JButton();
        btn31 = new javax.swing.JButton();
        txtSo3 = new javax.swing.JTextField();
        btnct3 = new javax.swing.JButton();
        btn32 = new javax.swing.JButton();
        btnc3 = new javax.swing.JButton();
        btn33 = new javax.swing.JButton();
        btn34 = new javax.swing.JButton();
        btncong3 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        casio.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn9.setText("9");
        btn9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn9, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 162, -1, -1));

        btnbang.setText("=");
        btnbang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbangActionPerformed(evt);
            }
        });
        casio.add(btnbang, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 191, 51, -1));

        btn0.setText("0");
        btn0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn0, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 191, -1, -1));
        casio.add(txtSo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 26, 273, 55));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Casio");
        casio.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 0, -1, -1));

        btnct.setText("+/-");
        casio.add(btnct, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 191, -1, -1));

        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 99, -1, -1));

        btnc.setText("C");
        btnc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncActionPerformed(evt);
            }
        });
        casio.add(btnc, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 191, -1, -1));

        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 99, 50, -1));

        btnchia.setText("/");
        btnchia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchiaActionPerformed(evt);
            }
        });
        casio.add(btnchia, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 99, 47, -1));

        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn3, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 99, -1, -1));

        btnnhan.setText("*");
        btnnhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnnhanActionPerformed(evt);
            }
        });
        casio.add(btnnhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 133, 47, -1));

        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 133, -1, -1));

        btntru.setText("-");
        btntru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntruActionPerformed(evt);
            }
        });
        casio.add(btntru, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 162, 47, -1));

        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn5, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 133, 50, -1));

        btncong.setText("+");
        btncong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncongActionPerformed(evt);
            }
        });
        casio.add(btncong, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 191, 47, -1));

        btn6.setText("6");
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn6, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 133, -1, -1));

        jButton17.setText("sqrt");
        casio.add(jButton17, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 99, -1, -1));

        btn7.setText("7");
        btn7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 162, -1, -1));

        jButton18.setText("%");
        casio.add(jButton18, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 133, 51, -1));

        btn8.setText("8");
        btn8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        casio.add(btn8, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 162, 50, -1));

        jButton19.setText("1/x");
        casio.add(jButton19, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 162, 51, -1));

        getContentPane().add(casio, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 250));

        casio1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Programer");
        casio1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, -1));

        btn20.setText("9");
        btn20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn20btn1ActionPerformed(evt);
            }
        });
        casio1.add(btn20, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 162, 90, -1));

        btnbang2.setText("=");
        btnbang2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbang2ActionPerformed(evt);
            }
        });
        casio1.add(btnbang2, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 191, 51, -1));

        btn21.setText("0");
        btn21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn21btn1ActionPerformed(evt);
            }
        });
        casio1.add(btn21, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 191, 100, -1));
        casio1.add(txtSo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 26, 273, 55));

        btn22.setText("1");
        btn22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn22ActionPerformed(evt);
            }
        });
        casio1.add(btn22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 99, 100, -1));

        btnc2.setText("C");
        btnc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnc2ActionPerformed(evt);
            }
        });
        casio1.add(btnc2, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 191, 90, -1));

        btn24.setText("3");
        btn24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn24btn1ActionPerformed(evt);
            }
        });
        casio1.add(btn24, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 99, 90, -1));

        btn25.setText("4");
        btn25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn25btn1ActionPerformed(evt);
            }
        });
        casio1.add(btn25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 133, 100, -1));

        btn27.setText("6");
        btn27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn27btn1ActionPerformed(evt);
            }
        });
        casio1.add(btn27, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 133, 90, -1));

        jButton23.setText("sqrt");
        casio1.add(jButton23, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 99, -1, -1));

        btn28.setText("7");
        btn28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn28btn1ActionPerformed(evt);
            }
        });
        casio1.add(btn28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 162, 100, -1));

        jButton24.setText("%");
        casio1.add(jButton24, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 133, 51, -1));

        jButton25.setText("1/x");
        casio1.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 162, 51, -1));

        jButton1.setText("A");
        casio1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 40, -1));

        jButton2.setText("B");
        casio1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jButton3.setText("D");
        casio1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        jButton4.setText("E");
        casio1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        jButton5.setText("F");
        casio1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 50, -1));

        getContentPane().add(casio1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 250));

        casio2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Casio IV");
        casio2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 0, -1, -1));

        btnbang3.setText("=");
        btnbang3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbang3ActionPerformed(evt);
            }
        });
        casio2.add(btnbang3, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 191, 51, -1));

        btn31.setText("0");
        btn31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn31btn1ActionPerformed(evt);
            }
        });
        casio2.add(btn31, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 191, -1, -1));
        casio2.add(txtSo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 26, 273, 55));

        btnct3.setText("+/-");
        casio2.add(btnct3, new org.netbeans.lib.awtextra.AbsoluteConstraints(45, 191, -1, -1));

        btn32.setText("1");
        btn32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn32ActionPerformed(evt);
            }
        });
        casio2.add(btn32, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 99, 150, -1));

        btnc3.setText("C");
        btnc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnc3ActionPerformed(evt);
            }
        });
        casio2.add(btnc3, new org.netbeans.lib.awtextra.AbsoluteConstraints(114, 191, -1, -1));

        btn33.setText("2");
        btn33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn33btn1ActionPerformed(evt);
            }
        });
        casio2.add(btn33, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, -1));

        btn34.setText("3");
        btn34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn34btn1ActionPerformed(evt);
            }
        });
        casio2.add(btn34, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 150, -1));

        btncong3.setText("+");
        btncong3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncong3ActionPerformed(evt);
            }
        });
        casio2.add(btncong3, new org.netbeans.lib.awtextra.AbsoluteConstraints(159, 191, 47, -1));

        jButton26.setText("sqrt");
        casio2.add(jButton26, new org.netbeans.lib.awtextra.AbsoluteConstraints(163, 99, 110, -1));

        jButton27.setText("SQR");
        casio2.add(jButton27, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 110, -1));

        jButton28.setText("MAX");
        casio2.add(jButton28, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 110, -1));

        jButton6.setText("A");
        casio2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 40, -1));

        jButton7.setText("B");
        casio2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, -1, -1));

        jButton8.setText("D");
        casio2.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, -1, -1));

        jButton9.setText("E");
        casio2.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        jButton10.setText("F");
        casio2.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 50, -1));

        getContentPane().add(casio2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 250));

        jMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/menu.png"))); // NOI18N

        jMenu2.setText("Programer");
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu2);

        jMenu3.setText("Casio");
        jMenu3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu3MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu3);

        jMenu4.setText("Casio IV");
        jMenu4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu4MouseClicked(evt);
            }
        });
        jMenu1.add(jMenu4);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
public void Nut(java.awt.event.ActionEvent evt){
    String gt = evt.getActionCommand();
    txtSo.setText(txtSo.getText()+gt);
}
    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked
       casio2.setVisible(false);
        casio.setVisible(false);
        casio1.setVisible(true);
    }//GEN-LAST:event_jMenu2MouseClicked

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        Nut(evt);
    }//GEN-LAST:event_btn1ActionPerformed

    private void btncongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncongActionPerformed
        a = Long.parseLong(txtSo.getText());
        pt = "+";
        txtSo.setText("0");
    }//GEN-LAST:event_btncongActionPerformed

    private void btntruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntruActionPerformed
        a = Long.parseLong(txtSo.getText());
        pt = "-";
        txtSo.setText("0");
    }//GEN-LAST:event_btntruActionPerformed

    private void btnnhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnnhanActionPerformed
        a = Long.parseLong(txtSo.getText());
        pt = "*";
        txtSo.setText("0");
    }//GEN-LAST:event_btnnhanActionPerformed

    private void btnchiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchiaActionPerformed
        a = Long.parseLong(txtSo.getText());
        if (a != 0) {
            pt = "/";
            txtSo.setText("0");
        }
    }//GEN-LAST:event_btnchiaActionPerformed

    private void btncActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncActionPerformed
        txtSo.setText("");
    }//GEN-LAST:event_btncActionPerformed

    private void btnbangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbangActionPerformed
        check();
    }//GEN-LAST:event_btnbangActionPerformed

    private void btn20btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn20btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn20btn1ActionPerformed

    private void btnbang2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbang2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbang2ActionPerformed

    private void btn21btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn21btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn21btn1ActionPerformed

    private void btn22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn22ActionPerformed

    private void btnc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnc2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnc2ActionPerformed

    private void btn24btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn24btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn24btn1ActionPerformed

    private void btn25btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn25btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn25btn1ActionPerformed

    private void btn27btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn27btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn27btn1ActionPerformed

    private void btn28btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn28btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn28btn1ActionPerformed

    private void jMenu3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu3MouseClicked
        casio.setVisible(true);
        casio1.setVisible(false);
        casio2.setVisible(false);
    }//GEN-LAST:event_jMenu3MouseClicked

    private void btnbang3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbang3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnbang3ActionPerformed

    private void btn31btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn31btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn31btn1ActionPerformed

    private void btn32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn32ActionPerformed

    private void btnc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnc3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnc3ActionPerformed

    private void btn33btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn33btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn33btn1ActionPerformed

    private void btn34btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn34btn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btn34btn1ActionPerformed

    private void btncong3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncong3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btncong3ActionPerformed

    private void jMenu4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu4MouseClicked
        casio2.setVisible(true);
        casio.setVisible(false);
        casio1.setVisible(false);
    }//GEN-LAST:event_jMenu4MouseClicked
    public void check() {
        long kq = 0;
        long b = Long.parseLong(txtSo.getText());
        if (pt.length() > 0) {
            if (pt.equals("+")) {
                kq = a + b;
                txtSo.setText(String.valueOf(kq));
            } else if (pt.equals("-")) {
                kq = a - b;
                txtSo.setText(String.valueOf(kq));
            } else if (pt.equals("*")) {
                kq = a * b;
                txtSo.setText(String.valueOf(kq));
            } else {

                kq = a / b;
                txtSo.setText(String.valueOf(kq));
            }
            pt = "";
            a = 0;
        }

    }

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
            public void run() {
                new Bai_2().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn0;
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn20;
    private javax.swing.JButton btn21;
    private javax.swing.JButton btn22;
    private javax.swing.JButton btn24;
    private javax.swing.JButton btn25;
    private javax.swing.JButton btn27;
    private javax.swing.JButton btn28;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn31;
    private javax.swing.JButton btn32;
    private javax.swing.JButton btn33;
    private javax.swing.JButton btn34;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btn6;
    private javax.swing.JButton btn7;
    private javax.swing.JButton btn8;
    private javax.swing.JButton btn9;
    private javax.swing.JButton btnbang;
    private javax.swing.JButton btnbang2;
    private javax.swing.JButton btnbang3;
    private javax.swing.JButton btnc;
    private javax.swing.JButton btnc2;
    private javax.swing.JButton btnc3;
    private javax.swing.JButton btnchia;
    private javax.swing.JButton btncong;
    private javax.swing.JButton btncong3;
    private javax.swing.JButton btnct;
    private javax.swing.JButton btnct3;
    private javax.swing.JButton btnnhan;
    private javax.swing.JButton btntru;
    private javax.swing.JPanel casio;
    private javax.swing.JPanel casio1;
    private javax.swing.JPanel casio2;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField txtSo;
    private javax.swing.JTextField txtSo2;
    private javax.swing.JTextField txtSo3;
    // End of variables declaration//GEN-END:variables
}
