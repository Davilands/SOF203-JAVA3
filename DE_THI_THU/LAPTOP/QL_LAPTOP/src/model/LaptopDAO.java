/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangt
 */
public class LaptopDAO {
    String name;
    String user = "sa";
    String pass = "pc04349";
    String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=QLLT;encrypt=false;";
    
    public List<Laptop> getAll() {
        List<Laptop> list = new ArrayList<>();
        list.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from LAPTOP";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String MALT = rs.getString(1);
                String TENLT = rs.getString(2);
                String MALOAI = rs.getString(3);
                String MAHIEU = rs.getString(4);
                String MAU = rs.getString(5);
                float GIA = rs.getFloat(6);
                //System.out.println(TENTAIKHOAN);
                list.add(new Laptop(MALT, TENLT, MALOAI, MAHIEU, MAU, GIA));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Nhanhieu> getAllNhan() {
        List<Nhanhieu> list = new ArrayList<>();
        list.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsinhvien";
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from NHANHIEU";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String MAHIEU = rs.getString(1);
                String TENHIEU = rs.getString(2);
                //System.out.println(TENTAIKHOAN);
                list.add(new Nhanhieu(MAHIEU,TENHIEU));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
     public List<Loai> getAllLoai() {
        List<Loai> list = new ArrayList<>();
        list.clear();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsinhvien";
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from Loai";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String MALOAI = rs.getString(1);
                String TENLOAI = rs.getString(2);
                //System.out.println(TENTAIKHOAN);
                list.add(new Loai(MALOAI,TENLOAI));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
     
     
}
