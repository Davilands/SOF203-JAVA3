package dao;

import Assignment_model.Account;
import Assignment_model.Standardization;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangt
 */
public class AccountDAO {

    //List<Account> list = new ArrayList<>();
    String name;

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        list.clear();
        list.add(new Account("Trần Hữu Đang", "admin", "123", "dangthpc04349@fpt.edu.vn", "Quản lí", true));
        list.add(new Account("Trần Thị Mỹ Duyên", "duyenttm12", "duyen123", "duyenttmpc05508@fpt.edu.vn", "Nhân viên", true));
        list.add(new Account("Nguyễn Thị Diễm Ngân", "nganmayy", "ngan123", "", "Nhân viên", true));
        list.add(new Account("Đoàn Hiệp Sỹ", "hiepsylon", "123", "sydhpc@fpt.edu.vn", "Nhân viên", false));
        list.add(new Account("Phùng Quốc Vinh", "vinhpq", "123", "vinhpqpc@fpt.edu.vn", "Nhân viên", true));
        list.add(new Account("Lê thị Ngọc Hân", "hannah", "han123", "", "Nhân viên", true));
        list.add(new Account("Nguyễn Khánh Đan", "danthoilai", "123", "dannkpc@fpt.edu.vn", "Nhân viên", false));
        list.add(new Account("Lê Bích Vi", "bichve", "123", "vilbpc@fpt.edu.vn", "Nhân viên", false));
        return list;
    }

//    public addAccountDAO() {
//        list.add(new Account("Trần Hữu Đang", "123", "123", "Quản lí", true));
//        list.add(new Account("Trần Thị Mỹ Duyên", "duyenttm12", "duyen123", "Nhân viên", true));
//        list.add(new Account("Đoàn Hiệp Sỹ", "king", "123", "Nhân viên", false));
//        list.add(new Account("Nguyễn Thị Diễm Ngân", "nganntd09", "ngan123", "Nhân viên", true));
//        list.add(new Account("Lê thị Ngọc Hân", "hanltn26", "han123", "Nhân viên", true));
//        list.add(new Account("Nguyễn Khánh Đan", "dannk", "123", "Nhân viên", true));
//        list.add(new Account("Phùng Quốc Vinh", "vinhpq", "123", "Nhân viênadmin", true));
//        list.add(new Account("Lê Bích Vi", "vilb", "123", "Nhân viên", false));
//    }
    public String checkLoginFirts(String username, String password) {
        AccountDAO accDAO = new AccountDAO();
        //chỉ return về "nhan vien" hoặc "quan li"
        for (Account account : accDAO.getAll()) {
            if (account.isTrangThai()) {
                if (account.getTenDangNhap().equals(username) && account.getMaKhau().equals(password)) {
                    if (account.getChucVu().equalsIgnoreCase("Quản lí")) {
                        return "quan li";
                    }
                    if (account.getChucVu().equalsIgnoreCase("Nhân viên")) {
                        return "nhan vien";
                    }
                }
            }
        }
        return "";
    }

    public String checkLogin(String username, String password) {
        AccountDAO accDAO = new AccountDAO();
        for (Account acc : accDAO.getAll()) {
            if (acc.getTenDangNhap().equals(username) && acc.getMaKhau().equals(password)) {
                if (acc.isTrangThai()) {
                    return acc.getChucVu().trim();
                }
            }
        }
        return "";
    }

    public String name(String username, String password) {
        AccountDAO accDAO = new AccountDAO();
        //return về tên tài khoản
        for (Account account : accDAO.getAll()) {
            if (account.isTrangThai()) {
                if (account.getTenDangNhap().equals(username) && account.getMaKhau().equals(password)) {
                    return account.getTenNguoiDung().trim();
                }
            }
        }
        return "";
    }

    public boolean checkMail(String email) {
        String reEmail = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        if (email.matches(reEmail)) {
            return true;
        }
        return false;
    }

    public String validate(String tenNguoiDung, String tenDangNhap, String matKhau, String email, String chucVu) {
        AccountDAO accDAO = new AccountDAO();
        Standardization std = new Standardization();
        for (Account account : accDAO.getAll()) {
            if (account.getTenDangNhap().equals(tenDangNhap)) {
                return "Tên đăng nhập đã tồn tại vui lòng dùng tên khác!";
            }
        }
        if (!std.hoTen(tenNguoiDung).equalsIgnoreCase(tenNguoiDung)) {
            return "Tên người dùng không hợp lệ!";
        }
        if (tenDangNhap.length() < 3 || tenDangNhap.length() > 15) {
            return "Tên đăng nhập phải từ 3-15 ký tự!";
        }
        if (matKhau.length() < 3 || matKhau.length() > 15) {
            return "Mật khẩu phải từ 3-15 kí tự";
        }
        if (!accDAO.checkMail(email)) {
            return "Email không hợp lệ!";
        }
        //if (chucVu.equalsIgnoreCase("Quản lí") || chucVu.equalsIgnoreCase("Nhân viên"))

        return "";
    }

    public static void main(String[] args) {
        AccountDAO t = new AccountDAO();
        System.out.println(t.checkLogin("duyenttm12", "duyen123"));
    }
}
