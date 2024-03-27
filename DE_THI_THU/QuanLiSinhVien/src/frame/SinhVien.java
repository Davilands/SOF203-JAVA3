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
public class SinhVien {

    String MSSV;
    String Hoten;
    String quequan;
    String cccd;
    boolean gioitinh;
    String lop;
    String hinhanh;
    boolean trangThai;

    public SinhVien() {
    }

    public SinhVien(String MSSV, String Hoten, String quequan, String cccd, boolean gioitinh, String lop, String hinhanh) {
        this.MSSV = MSSV;
        this.Hoten = Hoten;
        this.quequan = quequan;
        this.cccd = cccd;
        this.gioitinh = gioitinh;
        this.lop = lop;
        this.hinhanh = hinhanh;
    }
    
    

    public SinhVien(String MSSV, String Hoten, String quequan, String cccd, boolean gioitinh, String lop, String hinhanh, boolean trangThai) {
        this.MSSV = MSSV;
        this.Hoten = Hoten;
        this.quequan = quequan;
        this.cccd = cccd;
        this.gioitinh = gioitinh;
        this.lop = lop;
        this.hinhanh = hinhanh;
        this.trangThai = trangThai;
    }

    public String getMSSV() {
        return MSSV;
    }

    public void setMSSV(String MSSV) {
        this.MSSV = MSSV;
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

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
    
    public List<SinhVien> getAll() {
        List<SinhVien> list = new ArrayList<>();
        String user = "sa";
        String pass = "pc04349";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlsv;encrypt=false;";
        
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from sinhvien";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String mssv = rs.getString(1);
                String tensv = rs.getString(2);
                String quequan = rs.getString(3);
                String cccd = rs.getString(4);
                Boolean gioitinh = rs.getBoolean(5);
                String lop = rs.getString(6);
                String hinhanh = rs.getString(7);
                //System.out.println(TENTAIKHOAN);
                list.add(new SinhVien(mssv, tensv, quequan, cccd, gioitinh, lop, hinhanh));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void add(String mssv, String hoTen, String queQuan, String cccd, boolean gioiTinh, String lop, String hinhAnh) {
        String user = "sa";
        String pass = "pc04349";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlsv;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "insert into sinhvien values(?,?,?,?,?,?,?)";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, mssv);
            st.setString(2, hoTen);
            st.setString(3, queQuan);
            st.setString(4, cccd);
            st.setBoolean(5,gioiTinh );
            st.setString(6, lop);
            st.setString(7, hinhAnh);
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
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlsv;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "delete from sinhvien where mssv = ?";
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
    
    public void update(String mssv, String hoTen, String queQuan, String cccd, boolean gioiTinh, String lop, String hinhAnh) {
        String user = "sa";
        String pass = "pc04349";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlsv;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "update sinhvien set hoten=?,quequan=?,cccd =?,gioitinh=?,lop=?, hinhanh=?  where mssv = ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setString(1, hoTen);
            st.setString(2, queQuan);
            st.setString(3, cccd);
            st.setBoolean(4, gioiTinh);
            st.setString(5, lop);
            st.setString(6, hinhAnh);
            st.setString(7, mssv);
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
        SinhVien sv = new SinhVien();
        System.out.println(sv.getAll().get(0).getHoten());
    }
}
