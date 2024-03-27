/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class TaikhoanDAO {

    public List<Taikhoan> getAll() {
        List<Taikhoan> list = new ArrayList<>();
        String userName = "sa";
        String password = "pc04349";
        //String url = "jdbc:sqlserver://localhost:1433;databaseName=QLSINHVIEN";
        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=TUTOR_JAVA3;encrypt=false;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection con = DriverManager.getConnection(url, userName, password);
            Statement st = con.createStatement();
            String sql = "select * from TAIKHOAN";
            ResultSet rs = st.executeQuery(sql);
            list.clear();
            while (rs.next()) {
                String tentaikhoan = rs.getString(1);
                String tendangnhap = rs.getString(2);
                String matkhau = rs.getString(3);
                String email = rs.getString(4);
                boolean quyen = rs.getBoolean(5);
                list.add(new Taikhoan(tentaikhoan, tendangnhap, matkhau, email, quyen));
            }
            con.close();
        } catch (Exception ex) {
            System.out.println(ex);
        }
//        list.add(new Taikhoan("Trần Hữu Đang", "admin", "123", "email@gmail.com", true));
//        list.add(new Taikhoan("user", "123", "user@gmail.com", false));
        return list;
    }

    public int login(String username, String password) {
        TaikhoanDAO acc = new TaikhoanDAO();
        for (Taikhoan tk : acc.getAll()) {
            if (tk.getTendangnhap().equals(username) && tk.getMatkhau().equals(password)) {
                if (tk.isQuyen()) {
                    //thằng này là admin --> 1
                    return 1;
                } else {
                    //thằng này éo phải admin
                    return 2;
                }
            }
        }
        return 0;
        //--> thằng này éo tồn tại trong database
    }

    public static void main(String[] args) {
        TaikhoanDAO acc = new TaikhoanDAO();
        System.out.println(acc.login("user", "123"));
    }
}
