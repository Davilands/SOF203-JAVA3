/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai_them;

/**
 *
 * @author dangt
 */
public class Districts {
    int maHuyen;
    String tenHuyen;
    int maTinh;

    public Districts(int maHuyen, String tenHuyen, int maTinh) {
        this.maHuyen = maHuyen;
        this.tenHuyen = tenHuyen;
        this.maTinh = maTinh;
    }

    public int getMaHuyen() {
        return maHuyen;
    }

    public void setMaHuyen(int maHuyen) {
        this.maHuyen = maHuyen;
    }

    public String getTenHuyen() {
        return tenHuyen;
    }

    public void setTenHuyen(String tenHuyen) {
        this.tenHuyen = tenHuyen;
    }

    public int getMaTinh() {
        return maTinh;
    }

    public void setMaTinh(int maTinh) {
        this.maTinh = maTinh;
    }
    
    
}
