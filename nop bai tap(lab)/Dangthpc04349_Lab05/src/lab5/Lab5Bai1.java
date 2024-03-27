/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * *

 * @author dangt
 */
public class Lab5Bai1 {

    public static void main(String[] args) {
        
        try {
            String user = "sa";
            String pass = "pc04349";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String url = "jdbc:sqlserver://localhost:1433;databaseName=qlsinhvien";
            String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=qlsinhvien;encrypt=false;";
            Connection con = DriverManager.getConnection(url, user, pass);
            String sql = "select * from STUDENTS";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.print(rs.getString("MaSV") + ", ");
                System.out.print(rs.getString("Hoten") + ", ");
                System.out.print(rs.getString("Email") + ", ");
                System.out.print(rs.getString("SoDT")+ ", ");
                System.out.print(rs.getString("Gioitinh")+ ", ");
                System.out.print(rs.getString("Diachi")+ ". \n");
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
