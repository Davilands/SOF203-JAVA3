/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab5;

/**
 *
 * @author LE BICH VY
 */
public class Students {
    String MaSV;
    String Hoten;
    String Email;
    String SoDT;
    String Diachi;
    boolean Gioitinh;

    public Students(String MaSV, String Hoten, String Email, String SoDT, String Diachi, boolean Gioitinh) {
        this.MaSV = MaSV;
        this.Hoten = Hoten;
        this.Email = Email;
        this.SoDT = SoDT;
        this.Diachi = Diachi;
        this.Gioitinh = Gioitinh;
    }

    public Students() {
    }

    public String getMaSV() {
        return MaSV;
    }

    public void setMaSV(String MaSV) {
        this.MaSV = MaSV;
    }

    public String getHoten() {
        return Hoten;
    }

    public void setHoten(String Hoten) {
        this.Hoten = Hoten;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSoDT() {
        return SoDT;
    }

    public void setSoDT(String SoDT) {
        this.SoDT = SoDT;
    }

    public String getDiachi() {
        return Diachi;
    }

    public void setDiachi(String Diachi) {
        this.Diachi = Diachi;
    }

    public boolean isGioitinh() {
        return Gioitinh;
    }

   

    public void setGioitinh(boolean Gioitinh) {
        this.Gioitinh = Gioitinh;
    }
    
    
}
