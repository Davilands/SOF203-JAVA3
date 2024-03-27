/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author dangt
 */
public class Taikhoan {
    String tentaikhoan;
    String tendangnhap;
    String matkhau;
    String email;
    boolean quyen; 
    /*
    - Quyền admin: full quyền
    - quyền user: chỉ xem ,...
    */

    public Taikhoan(String tendangnhap, String matkhau, String email, boolean quyen) {
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.email = email;
        this.quyen = quyen;
    }

    public Taikhoan(String tentaikhoan, String tendangnhap, String matkhau, String email, boolean quyen) {
        this.tentaikhoan = tentaikhoan;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.email = email;
        this.quyen = quyen;
    }

    public String getTentaikhoan() {
        return tentaikhoan;
    }

    public void setTentaikhoan(String tentaikhoan) {
        this.tentaikhoan = tentaikhoan;
    }

    
    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isQuyen() {
        return quyen;
    }

    public void setQuyen(boolean quyen) {
        this.quyen = quyen;
    }
    
    
}
