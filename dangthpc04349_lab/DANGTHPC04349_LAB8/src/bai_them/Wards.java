/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bai_them;

/**String 
 *
 * @author dangt
 */
public class Wards {
    int maPhuong;
    String tenPhuong;
    int maHuyen;

    public Wards(int maPhuong, String tenPhuong, int maHuyen) {
        this.maPhuong = maPhuong;
        this.tenPhuong = tenPhuong;
        this.maHuyen = maHuyen;
    }

    public int getMaPhuong() {
        return maPhuong;
    }

    public void setMaPhuong(int maPhuong) {
        this.maPhuong = maPhuong;
    }

    public String getTenPhuong() {
        return tenPhuong;
    }

    public void setTenPhuong(String tenPhuong) {
        this.tenPhuong = tenPhuong;
    }

    public int getMaHuyen() {
        return maHuyen;
    }

    public void setMaHuyen(int maHuyen) {
        this.maHuyen = maHuyen;
    }
    
    
}
