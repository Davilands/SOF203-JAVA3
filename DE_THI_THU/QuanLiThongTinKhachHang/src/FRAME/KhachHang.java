/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FRAME;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author
 */
public class KhachHang {

    String maKH;
    String tenKH;
    int tuoi;
    String dt;
    String email;
    String hinhAnh;

    String user = "sa";
    String pass = "pc04349";
    String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qLkh;encrypt=false;";

    public KhachHang() {
    }

    public KhachHang(String maKH, String tenKH, int tuoi, String dt, String email, String hinhAnh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.tuoi = tuoi;
        this.dt = dt;
        this.email = email;
        this.hinhAnh = hinhAnh;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public String getDt() {
        return dt;
    }

    public void setDt(String dt) {
        this.dt = dt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public List<KhachHang> getAll() {
        List<KhachHang> list = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from KHACHHANG";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String maKH = rs.getString(1);
                String tenKH = rs.getString(2);
                int tuoi = rs.getInt(3);
                String dt = rs.getString(4);
                String email = rs.getString(5);
                String hinhanh = rs.getString(6);
                //System.out.println(TENTAIKHOAN);
                list.add(new KhachHang(maKH, tenKH, tuoi, dt, email, hinhanh));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void add(String maKH, String tenKH, int tuoi, String dt, String email, String hinhAnh) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into khachhang values(?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maKH);
            st.setString(2, tenKH);
            st.setInt(3, tuoi);
            st.setString(4, dt);
            st.setString(5, email);
            st.setString(6, hinhAnh);
            st.executeUpdate();

            //JOptionPane.showMessageDialog(this, "Thêm thành công!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void delete(String maKH) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "delete from khachhang where makh = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, maKH);
            st.execute();
            //JOptionPane.showMessageDialog(this, "Delete thành công!");
            con.close();
            //return "";
        } catch (Exception e) {
            System.out.println(e);
            //JOptionPane.showMessageDialog(this, "Error");
            //return String.valueOf(e);
        }
    }

    public void update(String maKH, String tenKH, int tuoi, String dt, String email, String hinhAnh) {
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "update khachhang set tenKH=?,tuoi=?,dt =?, email=?,hinhanh=? where makh = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, tenKH);
            st.setInt(2, tuoi);
            st.setString(3, dt);
            st.setString(4, email);
            st.setString(5, hinhAnh);
            st.setString(6, maKH);
//            st.setString(7, qrCode);
            st.executeUpdate();
            //JOptionPane.showMessageDialog(this, "Update thành công!");
            con.close();
            //LoadDataToArray();
        } catch (Exception e) {
            System.out.println(e);
            //JOptionPane.showMessageDialog(this, "Error");
        }
    }

    public static void main(String[] args) {
        KhachHang kh = new KhachHang();
        System.out.println(kh.getAll().get(0).getMaKH());
    }
}
