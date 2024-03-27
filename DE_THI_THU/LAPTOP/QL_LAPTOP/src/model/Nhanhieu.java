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
public class Nhanhieu {
    String maNhan;
    String tenNhan;

    public Nhanhieu(String maNhan, String tenNhan) {
        this.maNhan = maNhan;
        this.tenNhan = tenNhan;
    }

    public String getMaNhan() {
        return maNhan;
    }

    public void setMaNhan(String maNhan) {
        this.maNhan = maNhan;
    }

    public String getTenNhan() {
        return tenNhan;
    }

    public void setTenNhan(String tenNhan) {
        this.tenNhan = tenNhan;
    }
    
    
}
