/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frame;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangt
 */
public class NhanVien {

    String msnv;
    String Hoten;
    String quequan;
    String cccd;
    boolean gioitinh;
    String hinhanh;
    String sdt;
    float luong;

    public NhanVien(String msnv, String Hoten, String quequan, String cccd, boolean gioitinh, String hinhanh, String sdt, float luong) {
        this.msnv = msnv;
        this.Hoten = Hoten;
        this.quequan = quequan;
        this.cccd = cccd;
        this.gioitinh = gioitinh;
        this.hinhanh = hinhanh;
        this.sdt = sdt;
        this.luong = luong;
    }

    public String getMsnv() {
        return msnv;
    }

    public void setMsnv(String msnv) {
        this.msnv = msnv;
    }

    

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public String getQuequan() {
        return quequan;
    }

    public void setQuequan(String quequan) {
        this.quequan = quequan;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public float getLuong() {
        return luong;
    }

    public void setLuong(float luong) {
        this.luong = luong;
    }
    
    

    public NhanVien() {
    }

    

    public List<NhanVien> getAll() {
        List<NhanVien> list = new ArrayList<>();
        String user = "sa";
        String pass = "pc04349";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlnv;encrypt=false;";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from nhanvien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String msnv = rs.getString(1);
                String ten = rs.getString(2);
                String quequan = rs.getString(3);
                String cccd = rs.getString(4);
                Boolean gioitinh = rs.getBoolean(5);
                String hinhanh = rs.getString(6);
                String sdt = rs.getString(7);
                Float luong = rs.getFloat(8);
                
                //System.out.println(TENTAIKHOAN);
                list.add(new NhanVien(msnv, ten, quequan, cccd, gioitinh, hinhanh, sdt, luong));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void add(String msnv, String hoten, String quequan, String cccd, boolean gioitinh, String hinhanh, String sdt, float luong) {
        String user = "sa";
        String pass = "pc04349";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlnv;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into nhanvien values(?,?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, msnv);
            st.setString(2, hoten);
            st.setString(3, quequan);
            st.setString(4, cccd);
            st.setBoolean(5,gioitinh );
            st.setString(6, hinhanh);
            st.setString(7, sdt);
            st.setFloat(8, luong);
            st.executeUpdate();
            
            //JOptionPane.showMessageDialog(this, "Thêm thành công!");
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public void delete(String MSSV) {
        String user = "sa";
        String pass = "pc04349";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlnv;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "delete from nhanvien where msnv = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, MSSV);
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
    
    public void update(String msnv, String hoten, String quequan, String cccd, boolean gioitinh, String hinhanh, String sdt, float luong) {
        String user = "sa";
        String pass = "pc04349";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlnv;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "update nhanvien set hoten=?,quequan=?,cccd =?,gioitinh=?,hinhanh=?, sdt=? ,luong=? where msnv = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, hoten);
            st.setString(2, quequan);
            st.setString(3, cccd);
            st.setBoolean(4, gioitinh);
            st.setString(5, hinhanh);
            st.setString(6, sdt);
            st.setFloat(7, luong);
            st.setString(8, msnv);
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
        NhanVien sv = new NhanVien();
        //System.out.println(sv.getAll().get(0).getHoten());
        System.out.println(sv.getAll().get(0).getLuong());
    }
}
