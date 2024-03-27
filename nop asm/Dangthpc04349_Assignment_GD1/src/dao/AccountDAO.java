
package dao;

import Assignment_model.Account;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangt
 */
public class AccountDAO {
    List<Account> list = new ArrayList<>();
    String name;
    public AccountDAO() {
        list.add(new Account("Trần Hữu Đang", "123", "123", "Quản lí", true));
        list.add(new Account("Trần Thị Mỹ Duyên", "duyenttm12", "duyen123", "Nhân viên", true));
        list.add(new Account("Đoàn Hiệp Sỹ", "king", "123", "Nhân viên", false));
        list.add(new Account("Nguyễn Thị Diễm Ngân", "nganntd09", "ngan123", "Nhân viên", true));
        list.add(new Account("Lê thị Ngọc Hân", "hanltn26", "han123", "Nhân viên", true));
        list.add(new Account("Nguyễn Khánh Đan", "dannk", "123", "Nhân viên", true));
        list.add(new Account("Phùng Quốc Vinh", "vinhpq", "123", "Nhân viênadmin", true));
        list.add(new Account("Lê Bích Vi", "vilb", "123", "Nhân viên", false));
    }
    
    public String checkLoginFirts(String username, String password) {
        //chỉ return về "nhan vien" hoặc "quan li"
        for(Account account:list) {
            if (account.isTrangThai()) {
                if (account.getTenDangNhap().equals(username) && account.getMaKhau().equals(password)) {
                    if(account.getChucVu().equalsIgnoreCase("Quản lí")){
                        return "quan li";
                    }
                    if (account.getChucVu().equalsIgnoreCase("Nhân viên")) 
                        return "nhan vien";
                } 
            }
        }
        return "";
    }
    
    public String checkLogin(String username, String password) {
        for (Account acc:list) {
            if (acc.getTenDangNhap().equals(username) && acc.getMaKhau().equals(password)) {
                if (acc.isTrangThai()) {
                    return acc.getChucVu().trim();
                }
            }
        }
        return "";
    }
    
    public String name(String username, String password) {
        //return về tên tài khoản
        for(Account account:list) {
            if (account.isTrangThai()) {
                if (account.getTenDangNhap().equals(username) && account.getMaKhau().equals(password)) {
                    return account.getTenNguoiDung().trim();
                }
            }
        }
        return "";
    }
    
    public static void main(String[] args) {
        AccountDAO t = new AccountDAO();
        System.out.println(t.checkLogin("duyenttm12", "duyen123"));
    }
}
