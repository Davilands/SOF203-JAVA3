/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author dangt
 */
public class Laptop {
    String maSP;
    String tenSp;
    String maLoai;
    String maHieu;
    String mau;
    Float gia;

    public Laptop(String maSP, String tenSp, String maLoai, String maHieu, String mau, Float gia) {
        this.maSP = maSP;
        this.tenSp = tenSp;
        this.maLoai = maLoai;
        this.maHieu = maHieu;
        this.mau = mau;
        this.gia = gia;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getMaHieu() {
        return maHieu;
    }

    public void setMaHieu(String maHieu) {
        this.maHieu = maHieu;
    }

    public String getMau() {
        return mau;
    }

    public void setMau(String mau) {
        this.mau = mau;
    }

    public Float getGia() {
        return gia;
    }

    public void setGia(Float gia) {
        this.gia = gia;
    }
    
    

    
}
