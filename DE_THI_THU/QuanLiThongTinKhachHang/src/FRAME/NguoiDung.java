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
public class NguoiDung {

    String tenDangNhap;
    String matKhau;

    String user = "sa";
    String pass = "pc04349";
    String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlkh;encrypt=false;";

    public NguoiDung() {
    }

    public NguoiDung(String tenDangNhap, String matKhau) {
        this.tenDangNhap = tenDangNhap;
        this.matKhau = matKhau;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public List<NguoiDung> getAll() {
        List<NguoiDung> list = new ArrayList<>();

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from nguoidung";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String userName = rs.getString(1);
                String passWord = rs.getString(2);
                //System.out.println(TENTAIKHOAN);
                list.add(new NguoiDung(userName, passWord));
            }
            rs.close();
            con.close();
            //System.out.println(list.get(0).email);
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        NguoiDung nd = new NguoiDung();
        System.out.println(nd.getAll().get(0).getTenDangNhap());
    }
}
