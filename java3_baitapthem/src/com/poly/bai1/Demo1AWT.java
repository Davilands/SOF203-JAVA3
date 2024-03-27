/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.bai1;

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

/**
 *
 * @author dangt
 */
public class Demo1AWT extends Frame implements ActionListener{
    
    Button btnYellow, btnRed;
    Label label = new Label();
    
    public Demo1AWT(String msg){
        setTitle(msg);
        setLayout(new FlowLayout());
        btnYellow = new Button("Yellow");
        btnRed = new Button("Red");
        add(btnYellow);
        add(btnRed);
        add(label);
        btnYellow.addActionListener(this);
        btnRed.addActionListener(this);
        //setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        String str = e.getActionCommand();
        //System.out.println(str);
        if (str.endsWith("Yellow")){
            label.setText("Ban nhan button mau vang");
            this.setBackground(Color.yellow);
        }
        if (str.endsWith("Red")){
            label.setText("Ban nhan button mau do");
            this.setBackground(Color.red);
        }
    }
    
    public static void main(String[] args) {
        Demo1AWT ab = new Demo1AWT("AWT Fpoly");
        //ab.setSize(450, 200);
        //ab.show();
        //ab.setDefaultCloseOperation(Frame.DISPOSE_ON_CLOSE);
        ab.setSize(450, 200);
        ab.setVisible(true);

    }
}
