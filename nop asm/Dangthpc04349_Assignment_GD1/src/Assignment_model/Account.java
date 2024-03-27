/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment_model;

/**
 *
 * @author dangt
 */
public class Account {
    public String tenNguoiDung;
    public String tenDangNhap;
    public String maKhau;
    public String chucVu;
    public boolean trangThai;

    public Account(String tenNguoiDung, String tenDangNhap, String maKhau, String chucVu, boolean trangThai) {
        this.tenNguoiDung = tenNguoiDung;
        this.tenDangNhap = tenDangNhap;
        this.maKhau = maKhau;
        this.chucVu = chucVu;
        this.trangThai = trangThai;
    }

   

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getMaKhau() {
        return maKhau;
    }

    public void setMaKhau(String maKhau) {
        this.maKhau = maKhau;
    }

    public String getTenNguoiDung() {
        return tenNguoiDung;
    }

    public void setTenNguoiDung(String tenNguoiDung) {
        this.tenNguoiDung = tenNguoiDung;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    

    
    
    
}
