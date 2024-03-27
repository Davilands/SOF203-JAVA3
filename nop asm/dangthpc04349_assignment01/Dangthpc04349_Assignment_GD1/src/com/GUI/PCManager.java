package com.GUI;

import Assignment_model.Account;
import Assignment_model.InforPC;
import Assignment_model.PCModel;
import Assignment_model.Standardization;
import com.hicode.switchbutton.Event;
import dao.AccountDAO;
import dao.InforPCDAO;
import dao.PCModelDAO;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import static java.lang.Thread.sleep;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author dangt
 */
public class PCManager extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public boolean loading = false;
    public String tenNguoiDung;
    public boolean quyenTruyCap;
    //quan li   --> true
    //nhan vien --> false
    private String click = "";
    DefaultTableModel model;
    int indexAccount = 0;
    int indexInforPC = 0;
    boolean trangThai;
    
    private byte[] Image;      //Lưu ND của ảnh

    //quản lí thông tin laptop
    InforPCDAO inforPCDAO = new InforPCDAO();
    //List<InforPC> listInforPC = new ArrayList<>();

    //List quan li danh sach tài khoản
    AccountDAO accDAO = new AccountDAO();
    //List<Account> listAccount = new ArrayList<>();

    //List quản lí danh sách hãng sản xuất
    PCModelDAO modelPCDAO = new PCModelDAO();
    //List<PCModel> listPCModel = new ArrayList<>();

    public PCManager(boolean quyen, String ten) {
        initComponents();
        buildGUI();
        runFont();
        this.quyenTruyCap = quyen;
        this.tenNguoiDung = ten;
        xetQuyen();
        fillToUser();

        //process();
        //loadData2();
        jtaInfor.setEditable(false);
        jtaSupport.setEditable(false);

        //tìm kiếm
//        +++++++++++++++++++++++++++++
    }

    private PCManager() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void switchButton() {
        //boolean trangthai = trangThai;
        switchButton1.addEventSelected(new Event() {
            @Override
            public void onSelected(boolean selected) {
                //setTrangThaiHoatDong();
                if (selected) {
                    txtAccountTrangThai.setText("ĐANG HOẠT ĐỘNG");
                    txtAccountTrangThai.setForeground(new Color(0, 153, 51));
                } else {
                    txtAccountTrangThai.setText("NGƯNG HOẠT ĐỘNG");
                    txtAccountTrangThai.setForeground(Color.red);
                }
            }
        });
    }

//    public void updateAccount() {
//        for (Account acc : listAccount) {
//            if (acc.getTenDangNhap().equals(txtAccountTenDangNhap.getText())) {
//                //tiến hành cập nhật
//                acc.setTenNguoiDung(txtAccountTenNguoiDung.getText());
//                acc.setTenDangNhap(txtAccountTenDangNhap.getText());
//                acc.setMaKhau(txtAccountMatKhau.getText());
//                acc.setChucVu(txtAccountChucVu.getText());
//                if (switchButton1.isSelected()) {
//                    acc.setTrangThai(true);
//                } else {
//                    acc.setTrangThai(false);
//                }
//                //switchButton();
//                JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
//                //fillToTable();
//                model.setRowCount(0);
//                for (Account index : listAccount) {
//                    String trangThai = "Ngưng hoạt động";
//                    //model.addRow(new Object[]{index.MSSV, index.hoTen, index.lop, index.moHoc, index.diem});
//                    if (index.isTrangThai()) {
//                        trangThai = "Đang hoạt động";
//                    }
//                    model.addRow(new Object[]{index.getTenNguoiDung(), index.getTenDangNhap(), index.getMaKhau(), index.getChucVu(), trangThai});
//                    //model.addRow(new Object[]{"nguyen van a","nguyen van b","nguyenvanc","ab","dfg"});
//                }
//                return;
//            }
//        }
//    }
    public void xetQuyen() {

        if (!this.quyenTruyCap) {
            //Ngăn nhân viên quản lí tài khoản
            jblTaiKhoan.setVisible(this.quyenTruyCap);
            jplTaiKhoan.setVisible(this.quyenTruyCap);

            //ngăn nhân viên cập nhật dữ liệu
            btnSaveInforPC.setEnabled(this.quyenTruyCap);
            btnDeleteInforPC.setEnabled(this.quyenTruyCap);
            btnSaveModelPC.setEnabled(this.quyenTruyCap);
            btnDeleteModelPC.setEnabled(this.quyenTruyCap);

            //ngăn nhân viên chỉnh sửa thông tin
            txtInforCPU.setEditable(this.quyenTruyCap);
            txtInforDoHoa.setEditable(this.quyenTruyCap);
            txtInforGia.setEditable(this.quyenTruyCap);
            txtInforHang.setEditable(this.quyenTruyCap);
            txtInforHeDieuHanh.setEditable(this.quyenTruyCap);
            txtInforKhoiLuong.setEditable(this.quyenTruyCap);
            txtInforLuuTru.setEditable(this.quyenTruyCap);
            txtInforMaSP.setEditable(this.quyenTruyCap);
            txtInforManHinh.setEditable(this.quyenTruyCap);
            txtInforMauSac.setEditable(this.quyenTruyCap);
            txtInforPIN.setEditable(this.quyenTruyCap);
            txtInforRam.setEditable(this.quyenTruyCap);
            txtInforTenSP.setEditable(this.quyenTruyCap);
            txtInforXuatSu.setEditable(this.quyenTruyCap);
        }
    }

    public void buildGUI() {
        Tab.setUI(new javax.swing.plaf.metal.MetalTabbedPaneUI() {
            protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
            }
        });
    }

    public void runFont() {
        Thread threadl = new Thread() {
            @Override
            public void run() {
                String txt = " " + jblRun.getText() + " ";
                while (true) {
                    txt = txt.substring(1, txt.length()) + txt.charAt(0);
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    jblRun.setText(txt);
                }
            }

        };
        threadl.start();
    }

    public void setColor() {
        jplHoTro.setBackground(new Color(54, 43, 100));
        jplGioiThieu.setBackground(new Color(54, 43, 100));
        jplSanPham.setBackground(new Color(54, 43, 100));
        jplTaiKhoan.setBackground(new Color(54, 43, 100));

    }

    public void setClick(String ketQua) {
        this.click = ketQua;
    }

    public void xuLyClick() {
        if (this.click.equalsIgnoreCase("ho tro")) {
            jplHoTro.setBackground((new Color(125, 27, 126)));
        } else if (this.click.equalsIgnoreCase("tai khoan")) {
            jplTaiKhoan.setBackground((new Color(125, 27, 126)));
        } else if (this.click.equalsIgnoreCase("Gioi thieu")) {
            jplGioiThieu.setBackground((new Color(125, 27, 126)));
        } else if (this.click.equalsIgnoreCase("San pham")) {
            jplSanPham.setBackground((new Color(125, 27, 126)));
        } else if (this.click.equalsIgnoreCase("hang san xuat")) {
            jplHangSanXuat.setBackground((new Color(125, 27, 126)));
        } else {
            //System.out.println("ERROR");
        }
        //System.out.println("'"+this.click+"'");
    }

    public void fillToUser() {
        jblName.setText(this.tenNguoiDung);
        txtInforName.setText(jblName.getText());
        if (this.quyenTruyCap) {
            txtInforRole.setText("Quản lí");
        } else {
            txtInforRole.setText("Nhân viên");
        }
//        txtInforName.setText(click);
//        txtInforRole.setText(click);
    }

    //Thông tin Laptop
//    public void pushInforPC() {
//        listInforPC.clear();
//        listInforPC.add(new InforPC("PC001", "DELL Latutade 354", "DELL", "", "Windows 10 Pro", "Intel Core i5, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 56 Wh", (float) 2.81, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 320, 29000000, "Nhật bản", "đỏ-đen"));
//        listInforPC.add(new InforPC("PC002", "DELL Gaming G15", "DELL", "", "Windows 10 Pro", "Intel Core i7, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 40 Wh", (float) 3.2, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 150, 32000000, "Trung quốc", "xanh-đen"));
//        listInforPC.add(new InforPC("PC003", "Lenovo legion 5 ", "LENOVO", "", "Windows 10 Home", "Intel Core i5, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 34 Wh", (float) 2.81, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 320, 29000000, "Nhật bản", "đỏ-đen"));
//        listInforPC.add(new InforPC("PC004", "DELL Dell Gaming G15", "DELL", "", "Windows 10 Pro", "Intel Core i7, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 56 Wh", (float) 1.7, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 150, 32000000, "Việt nam", "đen"));
//        listInforPC.add(new InforPC("PC005", "HP envy 13", "HP", "", "Windows 10 Home", "Intel Core i5, 2.70 GHz, 12MB", "NIKLYZ GeForce RTX3050, 4 GB", "3-cell Li-ion, 50 Wh", (float) 2.81, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 320, 29000000, "Nhật bản", "đỏ-đen"));
//        listInforPC.add(new InforPC("PC006", "ASUS Gaming G15", "ASUS", "", "Windows 10 Home", "Intel Core i7, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 72 Wh", (float) 2.81, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 150, 32000000, "Trung quốc", "xanh-đen"));
//    }
    public void NewInforPC() {
        txtInforCPU.setText("");
        txtInforDoHoa.setText("");
        txtInforGia.setText("");
        txtInforHang.setText("");
        txtInforHeDieuHanh.setText("");
        txtInforKhoiLuong.setText("");
        txtInforLuuTru.setText("");
        txtInforMaSP.setText("");
        txtInforManHinh.setText("");
        txtInforMauSac.setText("");
        txtInforPIN.setText("");
        txtInforRam.setText("");
        txtInforXuatSu.setText("");;
        txtInforSoLuong.setText("");
        txtInforTenSP.setText("");
    }

    public void loadDataInforPCTable() {
        model = new DefaultTableModel();
        //Tạo các cột
        model.addColumn("MÃ SẢN PHẨM");
        model.addColumn("TÊN SẢN PHẨM");
        model.addColumn("HÃNG SẢN XUẤT");
        model.addColumn("NƠI SẢN XUẤT");
        model.addColumn("SỐ LƯỢNG");
        model.addColumn("GIÁ");

        //Đẩy Data từ List --> model
        fillToTableInforPC();
    }

//    public void fillToTableInforPC() {
//        model.setRowCount(0);
//        for (InforPC index : inforPCDAO.getAll()) {
//            model.addRow(new Object[]{index.getMaSP(), index.getTenSP(), index.getTenHang(), index.getNoiSanXuat(), String.valueOf(index.getSoLuong()), index.getGia()});
//        }
//        tblInforPC.setModel(model);
//    }
    public void fillToTableInforPC() {
        model.setRowCount(0);
        for (InforPC index : inforPCDAO.findByName(txtFind.getText())) {
            model.addRow(new Object[]{index.getMaSP(), index.getTenSP(), index.getTenHang(), index.getNoiSanXuat(), String.valueOf(index.getSoLuong()), index.getGia()});
        }
        tblInforPC.setModel(model);
    }

    public void showDetailInforPC(int index) {
        txtInforMaSP.setText(inforPCDAO.getAll().get(index).getMaSP());
        txtInforTenSP.setText(inforPCDAO.getAll().get(index).getTenSP());
        txtInforHeDieuHanh.setText(inforPCDAO.getAll().get(index).getHeDieuHanh());
        txtInforHang.setText(inforPCDAO.getAll().get(index).getTenHang());
        txtInforXuatSu.setText(inforPCDAO.getAll().get(index).getNoiSanXuat());
        txtInforLuuTru.setText(inforPCDAO.getAll().get(index).getoDia());
        txtInforCPU.setText(inforPCDAO.getAll().get(index).getCpu());
        txtInforManHinh.setText(inforPCDAO.getAll().get(index).getManHinh());
        txtInforKhoiLuong.setText(String.valueOf(inforPCDAO.getAll().get(index).getKhoiLuong()));
        txtInforPIN.setText(inforPCDAO.getAll().get(index).getPin());
        txtInforDoHoa.setText(inforPCDAO.getAll().get(index).getDoHoa());
        txtInforRam.setText(inforPCDAO.getAll().get(index).getRam());
        txtInforMauSac.setText(inforPCDAO.getAll().get(index).getMauSac());
        txtInforSoLuong.setText(String.valueOf(inforPCDAO.getAll().get(index).getSoLuong()));
        txtInforGia.setText(String.valueOf(inforPCDAO.getAll().get(index).getGia()));
        //
        txtInforPCRecord.setText("Bảng ghi: " + (index + 1) + " trên " + inforPCDAO.getAll().size());
        tblInforPC.setRowSelectionInterval(index, index);
    }

    //Nút First
    public void FirstInforPC() {
        this.indexAccount = 0;
        showDetailInforPC(this.indexAccount);
    }

    //Nút Prev
    public void PreviousInforPC() {
        if (this.indexInforPC == 0) {
            this.indexInforPC = inforPCDAO.getAll().size();
        }
        this.indexInforPC--;
        showDetailInforPC(this.indexInforPC);
    }

    //Nút Next
    public void NextInforPC() {
        if (this.indexInforPC == inforPCDAO.getAll().size() - 1) {
            this.indexInforPC = -1;
        }
        this.indexInforPC++;
        showDetailInforPC(this.indexInforPC);
    }

    public void LastInforPC() {
        this.indexInforPC = inforPCDAO.getAll().size() - 1;
        showDetailInforPC(this.indexInforPC);
    }

    //Thương Hiệu
    public void loadDataModelTable() {
        model = new DefaultTableModel();
        //Tạo các cột
        model.addColumn("MÃ HÃNG SẢN XUẤT");
        model.addColumn("TÊN HÃNG SẢN XUẤT");

        //Đẩy Data từ List --> model
        fillToTableModelPC();

    }

    public void fillToTableModelPC() {
        model.setRowCount(0);
        for (PCModel index : modelPCDAO.getAll()) {
            model.addRow(new Object[]{index.getMaHang(), index.getTenHang()});
        }
        tblHangSanXuat.setModel(model);
    }

    public void showDetailModel(int index) {
        txtMaHangSanXuat.setText(modelPCDAO.getAll().get(index).getMaHang());
        txtTenHangSanXuat.setText(modelPCDAO.getAll().get(index).getTenHang());

        //switchButton();
        txtModelRecord.setText("Bảng ghi: " + (index + 1) + " trên " + modelPCDAO.getAll().size());
        tblHangSanXuat.setRowSelectionInterval(index, index);
    }

    //Tài Khoản
    public void setTrangThaiHoatDong() {
        int ketQua = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn thay đổi trạng thái hoạt động của người dùng này?", "Cập nhật trạng thái", JOptionPane.YES_OPTION);
        if (ketQua == JOptionPane.YES_OPTION) {
            //switchButton();
            if (switchButton1.isSelected()) {
                txtAccountTrangThai.setText("Đang hoạt động");
                txtAccountTrangThai.setForeground(new Color(0, 153, 0));
            } else {
                txtAccountTrangThai.setText("Ngưng hoạt động");
                txtAccountTrangThai.setForeground(Color.red);
            }
            //updateAccount();

        } else {
            if (switchButton1.isSelected()) {
                switchButton1.setSelected(false);
            } else {
                switchButton1.setSelected(true);
            }
        }
    }

    /*public void pushAccount() {
        listAccount.clear();
        listAccount.add(new Account("Trần Hữu Đang", "123", "123", "Quản lí", true));
        listAccount.add(new Account("Trần Thị Mỹ Duyên", "duyenttm12", "duyen123", "Nhân viên", true));
        listAccount.add(new Account("Đoàn Hiệp Sỹ", "king", "123", "Nhân viên", false));
        listAccount.add(new Account("Nguyễn Thị Diễm Ngân", "nganntd09", "ngan123", "Nhân viên", true));
        listAccount.add(new Account("Lê thị Ngọc Hân", "hanltn26", "han123", "Nhân viên", true));
        listAccount.add(new Account("Nguyễn Khánh Đan", "dannk", "123", "Nhân viên", true));
        listAccount.add(new Account("Phùng Quốc Vinh", "vinhpq", "123", "Nhân viênadmin", true));
        listAccount.add(new Account("Lê Bích Vi", "vilb", "123", "Nhân viên", false));
    }*/
    public void loadDataAccountTable() {
        model = new DefaultTableModel();
        //Tạo các cột
        model.addColumn("TÊN NGƯỜI DÙNG");
        model.addColumn("TÊN ĐĂNG NHẬP");
        model.addColumn("MẬT KHẨU");
        model.addColumn("EMAIL");
        model.addColumn("CHỨC VỤ");
        model.addColumn("TRẠNG THÁI");

        txtAccountRecord.setText("Bảng ghi: 0 trên " + accDAO.getAll().size());

        //lưu file
        //Đẩy Data từ List --> model
        //fillToTable();
        fillToTableAccount();
    }

    public void fillToTableAccount() {
        model.setRowCount(0);
        for (Account index : accDAO.getAll()) {
            String trangThai = "Ngưng hoạt động";
            if (index.isTrangThai()) {
                trangThai = "Đang hoạt động";
            }
            model.addRow(new Object[]{index.getTenNguoiDung(), index.getTenDangNhap(), index.getMaKhau(), index.getEmail(), index.getChucVu(), trangThai});
        }
        tblListAccount.setModel(model);
    }

    public void showDetailAccount(int index) {
        txtAccountTenNguoiDung.setText(accDAO.getAll().get(index).getTenNguoiDung());
        txtAccountTenDangNhap.setText(accDAO.getAll().get(index).getTenDangNhap());
        txtAccountMatKhau.setText(accDAO.getAll().get(index).getMaKhau());
        txtAccountEmail.setText(accDAO.getAll().get(index).getEmail());
        txtAccountChucVu.setText(accDAO.getAll().get(index).getChucVu());
        //switchButton();
        if (accDAO.getAll().get(index).isTrangThai()) {
            txtAccountTrangThai.setText("Đang hoạt động");
            txtAccountTrangThai.setForeground(new Color(0, 153, 0));
            switchButton1.setSelected(true);
            this.trangThai = true;
        } else {
            txtAccountTrangThai.setText("Ngưng hoạt động");
            txtAccountTrangThai.setForeground(Color.red);
            switchButton1.setSelected(false);
            this.trangThai = false;
        }
        txtAccountRecord.setText("Bảng ghi: " + (index + 1) + " trên " + accDAO.getAll().size());
        tblListAccount.setRowSelectionInterval(index, index);
    }

    public void reNewAccount() {
        txtAccountTenNguoiDung.setText("");
        txtAccountTenDangNhap.setText("");
        txtAccountMatKhau.setText("");
        txtAccountEmail.setText("");
        txtAccountChucVu.setText("");
        txtAccountTrangThai.setText("Trạng thái");
        txtAccountTrangThai.setForeground(new Color(0, 153, 0));
        txtAccountRecord.setText("Bảng ghi: 0 trên " + accDAO.getAll().size());
        switchButton1.setSelected(false);
    }

    public void saveAccount() {
        AccountDAO accDAO = new AccountDAO();
        String validate = accDAO.validate(txtAccountTenNguoiDung.getText(), txtAccountTenDangNhap.getText(), txtAccountMatKhau.getText(), txtAccountEmail.getText(), txtAccountChucVu.getText());
        if (!validate.equals("")) {
            if(validate.equalsIgnoreCase("Tên đăng nhập đã tồn tại vui lòng dùng tên khác!")){
                lblAccountTenDangNhap.setForeground(Color.red);
            }
            if(validate.equalsIgnoreCase("Tên người dùng không hợp lệ!")){
                lblAccountTenNguoiDung.setForeground(Color.red);
            }
            if(validate.equalsIgnoreCase("Tên đăng nhập phải từ 3-15 ký tự!")){
                lblAccountTenDangNhap.setForeground(Color.red);
            }
            if(validate.equalsIgnoreCase("Mật khẩu phải từ 3-15 kí tự")){
                lblAccountMatKhau.setForeground(Color.red);
            }
            if(validate.equalsIgnoreCase("Email không hợp lệ!")){
                lblAccountMatKhau.setForeground(Color.red);
            }
            JOptionPane.showMessageDialog(this, validate);
        } else {
             JOptionPane.showMessageDialog(this, "Thêm dữ liệu thành công!");
        }
           
    }

//    public void fillToTable() {
//        model.setRowCount(0);
//        for (Account index : listAccount) {
//            String trangThai = "Đang hoạt động";
//            //model.addRow(new Object[]{index.MSSV, index.hoTen, index.lop, index.moHoc, index.diem});
//            if (index.isTrangThai()) {
//                trangThai = "Ngưng hoạt động";
//            }
//            model.addRow(new Object[]{index.tenNguoiDung, index.tenDangNhap, index.maKhau, index.chucVu, trangThai});
//        }
//    }
    //Nút First
    public void FirstAccount() {
        this.indexAccount = 0;
        showDetailAccount(this.indexAccount);
    }

    //Nút Prev
    public void PreviousAccount() {
        if (this.indexAccount == 0) {
            this.indexAccount = accDAO.getAll().size();
        }
        this.indexAccount--;
        showDetailAccount(this.indexAccount);
    }

    //Nút Next
    public void NextAccount() {
        if (this.indexAccount == accDAO.getAll().size() - 1) {
            this.indexAccount = -1;
        }
        this.indexAccount++;
        showDetailAccount(this.indexAccount);
    }

    public void LastAccount() {
        this.indexAccount = accDAO.getAll().size() - 1;
        showDetailAccount(this.indexAccount);
    }

    /**
     * * This method is called from within the constructor to initialize the
     * form. WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jplMain = new javax.swing.JPanel();
        jplLose = new javax.swing.JPanel();
        jlbClose = new javax.swing.JLabel();
        jplContainer = new javax.swing.JPanel();
        jplMenu = new javax.swing.JPanel();
        jblImage = new javax.swing.JLabel();
        jplTaiKhoan = new javax.swing.JPanel();
        jblTaiKhoan = new javax.swing.JLabel();
        jplGioiThieu = new javax.swing.JPanel();
        jblGioiThieu = new javax.swing.JLabel();
        jblName = new javax.swing.JLabel();
        jplDangXuat = new javax.swing.JPanel();
        jblDangXuat = new javax.swing.JLabel();
        jplSanPham = new javax.swing.JPanel();
        jblSanPham = new javax.swing.JLabel();
        jplHoTro = new javax.swing.JPanel();
        jblHoTro = new javax.swing.JLabel();
        jplHangSanXuat = new javax.swing.JPanel();
        jblHangSanXuat = new javax.swing.JLabel();
        Tab = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jplContainer1 = new javax.swing.JPanel();
        jblImage1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jblRun = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        lblAccountTenNguoiDung = new javax.swing.JLabel();
        sptAccount_TenNguoiDung = new javax.swing.JSeparator();
        txtAccountTenNguoiDung = new javax.swing.JTextField();
        lblAccountTenDangNhap = new javax.swing.JLabel();
        sptAccount_TenDangNhap = new javax.swing.JSeparator();
        txtAccountTenDangNhap = new javax.swing.JTextField();
        sptAccountMatKhau = new javax.swing.JSeparator();
        lblAccountMatKhau = new javax.swing.JLabel();
        lblAccountChucVu = new javax.swing.JLabel();
        sptAccountChucVu = new javax.swing.JSeparator();
        txtAccountChucVu = new javax.swing.JTextField();
        txtAccountTrangThai = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblListAccount = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        txtAccountRecord = new javax.swing.JLabel();
        btnLastAccount = new javax.swing.JLabel();
        btnNextAccount = new javax.swing.JLabel();
        btnPreviousAccount = new javax.swing.JLabel();
        btnFirstAccount = new javax.swing.JLabel();
        txtAccountMatKhau = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        btnNew1 = new javax.swing.JButton();
        btnSave1 = new javax.swing.JButton();
        btnDelete1 = new javax.swing.JButton();
        btnFind1 = new javax.swing.JButton();
        jPanel12 = new javax.swing.JPanel();
        switchButton1 = new com.hicode.switchbutton.SwitchButton();
        lblAccountEmail = new javax.swing.JLabel();
        sptAccountEmail = new javax.swing.JSeparator();
        txtAccountEmail = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHangSanXuat = new javax.swing.JTable();
        jLabel45 = new javax.swing.JLabel();
        sptHang_TenHang = new javax.swing.JSeparator();
        txtTenHangSanXuat = new javax.swing.JTextField();
        sptHang_MaHang = new javax.swing.JSeparator();
        txtMaHangSanXuat = new javax.swing.JTextField();
        jPanel20 = new javax.swing.JPanel();
        txtModelRecord = new javax.swing.JLabel();
        txtFindHangSX = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        btnNewModel = new javax.swing.JButton();
        btnSaveModelPC = new javax.swing.JButton();
        btnDeleteModelPC = new javax.swing.JButton();
        jPanel21 = new javax.swing.JPanel();
        cboSort = new javax.swing.JComboBox<>();
        rdbAZ = new javax.swing.JRadioButton();
        rdbZA = new javax.swing.JRadioButton();
        btnSort2 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        sptInforMaSp = new javax.swing.JSeparator();
        txtInforMaSP = new javax.swing.JTextField();
        sptInforHangSX = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        txtInforHang = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        sptInforTenSp = new javax.swing.JSeparator();
        txtInforTenSP = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        sptInforHeDieuHanh = new javax.swing.JSeparator();
        txtInforHeDieuHanh = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        sptInforCPU = new javax.swing.JSeparator();
        txtInforCPU = new javax.swing.JTextField();
        sptInforDoHoa = new javax.swing.JSeparator();
        txtInforDoHoa = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        sptInforRam = new javax.swing.JSeparator();
        txtInforRam = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        sptInforLuuTru = new javax.swing.JSeparator();
        txtInforLuuTru = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        sptInforXuatXu = new javax.swing.JSeparator();
        txtInforXuatSu = new javax.swing.JTextField();
        sptInforMauSac = new javax.swing.JSeparator();
        txtInforMauSac = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        sptInforPin = new javax.swing.JSeparator();
        txtInforPIN = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        sptInforManHinh = new javax.swing.JSeparator();
        txtInforManHinh = new javax.swing.JTextField();
        jLabel30 = new javax.swing.JLabel();
        sptInforKhoiLuong = new javax.swing.JSeparator();
        txtInforKhoiLuong = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        sptInforSoLuong = new javax.swing.JSeparator();
        txtInforSoLuong = new javax.swing.JTextField();
        sptInforGia = new javax.swing.JSeparator();
        txtInforGia = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblInforPC = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        btnNew = new javax.swing.JButton();
        btnSaveInforPC = new javax.swing.JButton();
        btnDeleteInforPC = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        cboSort1 = new javax.swing.JComboBox<>();
        rdbAZ1 = new javax.swing.JRadioButton();
        rdbZA1 = new javax.swing.JRadioButton();
        btnSort3 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        btnFirst = new javax.swing.JLabel();
        btnPrevious = new javax.swing.JLabel();
        btnNext = new javax.swing.JLabel();
        btnLast = new javax.swing.JLabel();
        txtInforPCRecord = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jtaInfor = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel36 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jtaSupport = new javax.swing.JTextArea();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        txtInforName = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jSeparator11 = new javax.swing.JSeparator();
        txtInforRole = new javax.swing.JTextField();
        jPanel18 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        jplState = new javax.swing.JPanel();
        jlbState = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jplMain.setBackground(new java.awt.Color(64, 43, 100));
        jplMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplLose.setBackground(new java.awt.Color(255, 255, 255));
        jplLose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jplLoseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jplLoseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jplLoseMouseExited(evt);
            }
        });
        jplLose.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbClose.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jlbClose.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbClose.setText(" X");
        jlbClose.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlbCloseFocusGained(evt);
            }
        });
        jlbClose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbCloseMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbCloseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlbCloseMouseExited(evt);
            }
        });
        jplLose.add(jlbClose, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 60, 30));

        jplMain.add(jplLose, new org.netbeans.lib.awtextra.AbsoluteConstraints(1170, 0, 60, -1));

        jplContainer.setBackground(new java.awt.Color(255, 255, 255));
        jplContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jplMenu.setBackground(new java.awt.Color(54, 33, 89));
        jplMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblImage.setForeground(new java.awt.Color(255, 255, 255));
        jblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/LogoRyzen.png"))); // NOI18N
        jblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblImageMouseClicked(evt);
            }
        });
        jplMenu.add(jblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 20, 140, 140));

        jplTaiKhoan.setBackground(new java.awt.Color(54, 43, 100));

        jblTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jblTaiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        jblTaiKhoan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblTaiKhoan.setText("Tài Khoản");
        jblTaiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblTaiKhoanMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblTaiKhoanMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblTaiKhoanMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jplTaiKhoanLayout = new javax.swing.GroupLayout(jplTaiKhoan);
        jplTaiKhoan.setLayout(jplTaiKhoanLayout);
        jplTaiKhoanLayout.setHorizontalGroup(
            jplTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jblTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jplTaiKhoanLayout.setVerticalGroup(
            jplTaiKhoanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jblTaiKhoan, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jplMenu.add(jplTaiKhoan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 300, 40));

        jplGioiThieu.setBackground(new java.awt.Color(54, 43, 100));

        jblGioiThieu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jblGioiThieu.setForeground(new java.awt.Color(255, 255, 255));
        jblGioiThieu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblGioiThieu.setText("Giới Thiệu");
        jblGioiThieu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblGioiThieuMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblGioiThieuMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblGioiThieuMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jplGioiThieuLayout = new javax.swing.GroupLayout(jplGioiThieu);
        jplGioiThieu.setLayout(jplGioiThieuLayout);
        jplGioiThieuLayout.setHorizontalGroup(
            jplGioiThieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jblGioiThieu, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        jplGioiThieuLayout.setVerticalGroup(
            jplGioiThieuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jblGioiThieu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jplMenu.add(jplGioiThieu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 350, 300, 40));

        jblName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jblName.setForeground(new java.awt.Color(255, 255, 255));
        jblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblName.setText("Tên người dùng");
        jblName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblNameMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblNameMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblNameMouseExited(evt);
            }
        });
        jplMenu.add(jblName, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 300, 30));

        jplDangXuat.setBackground(new java.awt.Color(54, 43, 100));

        jblDangXuat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jblDangXuat.setForeground(new java.awt.Color(255, 255, 255));
        jblDangXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblDangXuat.setText("Đăng xuất");
        jblDangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblDangXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblDangXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblDangXuatMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jplDangXuatLayout = new javax.swing.GroupLayout(jplDangXuat);
        jplDangXuat.setLayout(jplDangXuatLayout);
        jplDangXuatLayout.setHorizontalGroup(
            jplDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplDangXuatLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jplDangXuatLayout.setVerticalGroup(
            jplDangXuatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplDangXuatLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jblDangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jplMenu.add(jplDangXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 620, 300, 30));

        jplSanPham.setBackground(new java.awt.Color(54, 43, 100));
        jplSanPham.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jblSanPham.setForeground(new java.awt.Color(255, 255, 255));
        jblSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblSanPham.setText("Sản Phẩm");
        jblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblSanPhamMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblSanPhamMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblSanPhamMouseExited(evt);
            }
        });
        jplSanPham.add(jblSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        jplMenu.add(jplSanPham, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 300, 40));

        jplHoTro.setBackground(new java.awt.Color(54, 43, 100));
        jplHoTro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblHoTro.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jblHoTro.setForeground(new java.awt.Color(255, 255, 255));
        jblHoTro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblHoTro.setText("Hỗ Trợ");
        jblHoTro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblHoTroMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblHoTroMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblHoTroMouseExited(evt);
            }
        });
        jplHoTro.add(jblHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -1, 300, 40));

        jplMenu.add(jplHoTro, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 390, 300, 40));

        jplHangSanXuat.setBackground(new java.awt.Color(54, 43, 100));
        jplHangSanXuat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblHangSanXuat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jblHangSanXuat.setForeground(new java.awt.Color(255, 255, 255));
        jblHangSanXuat.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblHangSanXuat.setText("Hãng Sản Xuất");
        jblHangSanXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jblHangSanXuatMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jblHangSanXuatMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jblHangSanXuatMouseExited(evt);
            }
        });
        jplHangSanXuat.add(jblHangSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 40));

        jplMenu.add(jplHangSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 270, 300, 40));

        jplContainer.add(jplMenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 300, 650));

        Tab.setBackground(new java.awt.Color(255, 255, 255));
        Tab.setForeground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(204, 204, 255));
        jPanel5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel17.setBackground(new java.awt.Color(204, 204, 255));

        jLabel38.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(102, 102, 102));
        jLabel38.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel38.setText("TRANG CHỦ");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel5.add(jPanel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 40));

        jplContainer1.setBackground(new java.awt.Color(153, 102, 255));
        jplContainer1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jblImage1.setForeground(new java.awt.Color(255, 255, 255));
        jblImage1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jblImage1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/slogan.png"))); // NOI18N
        jplContainer1.add(jblImage1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 210, 220));

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 204, 255));
        jLabel2.setText("như yêu vợ bạn !!!");
        jplContainer1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 390, 250, 50));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 255, 255));
        jLabel4.setText("Chúng tôi yêu laptop");
        jplContainer1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 340, 250, 50));

        jblRun.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jblRun.setForeground(new java.awt.Color(153, 255, 255));
        jblRun.setText("  Phần mềm được phát triển bởi Trần Hữu Đang  (github.com/theanishtar)");
        jplContainer1.add(jblRun, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 530, 560, 20));

        jPanel5.add(jplContainer1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 930, 550));

        Tab.addTab("Trang chu", jPanel5);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel13.setBackground(new java.awt.Color(204, 204, 255));

        jLabel34.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(102, 102, 102));
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("QUẢN LÍ TÀI KHOẢN");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel34, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel2.add(jPanel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 40));

        lblAccountTenNguoiDung.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAccountTenNguoiDung.setForeground(new java.awt.Color(54, 33, 89));
        lblAccountTenNguoiDung.setText("TÊN NGƯỜI DÙNG");
        jPanel2.add(lblAccountTenNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, 190, 32));
        jPanel2.add(sptAccount_TenNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, 240, 26));

        txtAccountTenNguoiDung.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAccountTenNguoiDung.setBorder(null);
        txtAccountTenNguoiDung.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAccountTenNguoiDung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAccountTenNguoiDungMouseClicked(evt);
            }
        });
        txtAccountTenNguoiDung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountTenNguoiDungActionPerformed(evt);
            }
        });
        jPanel2.add(txtAccountTenNguoiDung, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 240, 40));

        lblAccountTenDangNhap.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAccountTenDangNhap.setForeground(new java.awt.Color(54, 33, 89));
        lblAccountTenDangNhap.setText("TÊN ĐĂNG NHẬP");
        jPanel2.add(lblAccountTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 190, 32));
        jPanel2.add(sptAccount_TenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 230, 240, 26));

        txtAccountTenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAccountTenDangNhap.setBorder(null);
        txtAccountTenDangNhap.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAccountTenDangNhap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAccountTenDangNhapMouseClicked(evt);
            }
        });
        txtAccountTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountTenDangNhapActionPerformed(evt);
            }
        });
        jPanel2.add(txtAccountTenDangNhap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 240, 40));
        jPanel2.add(sptAccountMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 330, 240, 20));

        lblAccountMatKhau.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAccountMatKhau.setForeground(new java.awt.Color(54, 33, 89));
        lblAccountMatKhau.setText("MẬT KHẨU");
        jPanel2.add(lblAccountMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 190, 32));

        lblAccountChucVu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAccountChucVu.setForeground(new java.awt.Color(54, 33, 89));
        lblAccountChucVu.setText("CHỨC VỤ");
        jPanel2.add(lblAccountChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 170, 190, 32));
        jPanel2.add(sptAccountChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 230, 210, 26));

        txtAccountChucVu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAccountChucVu.setBorder(null);
        txtAccountChucVu.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAccountChucVu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAccountChucVuMouseClicked(evt);
            }
        });
        txtAccountChucVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountChucVuActionPerformed(evt);
            }
        });
        jPanel2.add(txtAccountChucVu, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 200, 210, 40));

        txtAccountTrangThai.setEditable(false);
        txtAccountTrangThai.setBackground(new java.awt.Color(255, 255, 255));
        txtAccountTrangThai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtAccountTrangThai.setForeground(new java.awt.Color(0, 153, 0));
        txtAccountTrangThai.setText("TRẠNG THÁI");
        txtAccountTrangThai.setBorder(null);
        txtAccountTrangThai.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAccountTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountTrangThaiActionPerformed(evt);
            }
        });
        jPanel2.add(txtAccountTrangThai, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 300, 140, 40));

        jLabel43.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(54, 33, 89));
        jLabel43.setText("TRẠNG THÁI HOẠT ĐỘNG");
        jPanel2.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 270, 210, 32));

        jScrollPane2.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblListAccount.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên người dùng", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblListAccount.setGridColor(new java.awt.Color(255, 255, 255));
        tblListAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblListAccountMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblListAccount);

        jPanel2.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 370, 870, 160));

        jPanel8.setBackground(new java.awt.Color(204, 204, 255));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtAccountRecord.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtAccountRecord.setForeground(new java.awt.Color(255, 51, 51));
        txtAccountRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jPanel8.add(txtAccountRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, 230, -1));

        btnLastAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLastAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastAccountMouseClicked(evt);
            }
        });
        jPanel8.add(btnLastAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        btnNextAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNextAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextAccountMouseClicked(evt);
            }
        });
        jPanel8.add(btnNextAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        btnPreviousAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPreviousAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreviousAccountMouseClicked(evt);
            }
        });
        jPanel8.add(btnPreviousAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        btnFirstAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirstAccount.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstAccountMouseClicked(evt);
            }
        });
        jPanel8.add(btnFirstAccount, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jPanel2.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 530, 870, 50));

        txtAccountMatKhau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAccountMatKhau.setBorder(null);
        txtAccountMatKhau.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAccountMatKhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAccountMatKhauMouseClicked(evt);
            }
        });
        txtAccountMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountMatKhauActionPerformed(evt);
            }
        });
        jPanel2.add(txtAccountMatKhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 300, 240, 40));

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNew1.setBackground(new java.awt.Color(204, 204, 255));
        btnNew1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNew1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/edit (1).png"))); // NOI18N
        btnNew1.setText("Làm mới");
        btnNew1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnNew1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNew1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNew1MouseExited(evt);
            }
        });
        btnNew1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNew1ActionPerformed(evt);
            }
        });
        jPanel11.add(btnNew1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 110, 40));

        btnSave1.setBackground(new java.awt.Color(204, 204, 255));
        btnSave1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSave1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/diskette.png"))); // NOI18N
        btnSave1.setText("Lưu");
        btnSave1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnSave1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSave1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSave1MouseExited(evt);
            }
        });
        btnSave1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSave1ActionPerformed(evt);
            }
        });
        jPanel11.add(btnSave1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 110, 40));

        btnDelete1.setBackground(new java.awt.Color(204, 204, 255));
        btnDelete1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDelete1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/delete.png"))); // NOI18N
        btnDelete1.setText("Xóa");
        btnDelete1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnDelete1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDelete1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDelete1MouseExited(evt);
            }
        });
        btnDelete1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDelete1ActionPerformed(evt);
            }
        });
        jPanel11.add(btnDelete1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 110, 40));

        btnFind1.setBackground(new java.awt.Color(204, 204, 255));
        btnFind1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFind1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        btnFind1.setText("Tìm kiếm");
        btnFind1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnFind1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnFind1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnFind1MouseExited(evt);
            }
        });
        btnFind1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFind1ActionPerformed(evt);
            }
        });
        jPanel11.add(btnFind1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 110, 40));

        jPanel2.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 40, 150, 300));

        jPanel12.setOpaque(false);

        switchButton1.setBackground(new java.awt.Color(0, 153, 51));
        switchButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                switchButton1MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                switchButton1MouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(switchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(switchButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.add(jPanel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 300, 60, 40));

        lblAccountEmail.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblAccountEmail.setForeground(new java.awt.Color(54, 33, 89));
        lblAccountEmail.setText("EMAIL");
        jPanel2.add(lblAccountEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, 210, 32));
        jPanel2.add(sptAccountEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 130, 210, 26));

        txtAccountEmail.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtAccountEmail.setBorder(null);
        txtAccountEmail.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAccountEmail.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAccountEmailMouseClicked(evt);
            }
        });
        txtAccountEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAccountEmailActionPerformed(evt);
            }
        });
        jPanel2.add(txtAccountEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 210, 40));

        Tab.addTab("TaiKhoan", jPanel2);

        jPanel16.setBackground(new java.awt.Color(255, 255, 255));
        jPanel16.setForeground(new java.awt.Color(255, 255, 255));
        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel19.setBackground(new java.awt.Color(204, 204, 255));

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(102, 102, 102));
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("HÃNG SẢN XUẤT");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel44, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel16.add(jPanel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 40));

        jScrollPane3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblHangSanXuat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên người dùng", "Tên đăng nhập", "Mật khẩu", "Chức vụ", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHangSanXuat.setGridColor(new java.awt.Color(255, 255, 255));
        tblHangSanXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHangSanXuatMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHangSanXuat);

        jPanel16.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 870, 160));

        jLabel45.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(54, 33, 89));
        jLabel45.setText("Mã hãng sản xuất");
        jPanel16.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 190, 32));
        jPanel16.add(sptHang_TenHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 270, 250, 26));

        txtTenHangSanXuat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenHangSanXuat.setBorder(null);
        txtTenHangSanXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtTenHangSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenHangSanXuatActionPerformed(evt);
            }
        });
        jPanel16.add(txtTenHangSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 240, 40));
        jPanel16.add(sptHang_MaHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, 240, 26));

        txtMaHangSanXuat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMaHangSanXuat.setBorder(null);
        txtMaHangSanXuat.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtMaHangSanXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHangSanXuatActionPerformed(evt);
            }
        });
        jPanel16.add(txtMaHangSanXuat, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 240, 40));

        jPanel20.setBackground(new java.awt.Color(204, 204, 255));
        jPanel20.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtModelRecord.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtModelRecord.setForeground(new java.awt.Color(255, 51, 51));
        txtModelRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jPanel20.add(txtModelRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 260, -1));

        txtFindHangSX.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtFindHangSXMouseClicked(evt);
            }
        });
        jPanel20.add(txtFindHangSX, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 310, 30));

        jLabel46.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel46.setForeground(new java.awt.Color(54, 33, 89));
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/search.png"))); // NOI18N
        jLabel46.setText("Tìm theo tên hãng:");
        jPanel20.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, 190, 32));

        jPanel16.add(jPanel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 870, 50));

        btnNewModel.setBackground(new java.awt.Color(204, 204, 255));
        btnNewModel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNewModel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/edit (1).png"))); // NOI18N
        btnNewModel.setText("Làm mới");
        btnNewModel.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnNewModel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewModelMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewModelMouseExited(evt);
            }
        });
        btnNewModel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewModelActionPerformed(evt);
            }
        });
        jPanel16.add(btnNewModel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 110, 40));

        btnSaveModelPC.setBackground(new java.awt.Color(204, 204, 255));
        btnSaveModelPC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSaveModelPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/diskette.png"))); // NOI18N
        btnSaveModelPC.setText("Lưu");
        btnSaveModelPC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnSaveModelPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveModelPCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveModelPCMouseExited(evt);
            }
        });
        btnSaveModelPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveModelPCActionPerformed(evt);
            }
        });
        jPanel16.add(btnSaveModelPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 240, 110, 40));

        btnDeleteModelPC.setBackground(new java.awt.Color(204, 204, 255));
        btnDeleteModelPC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteModelPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/delete.png"))); // NOI18N
        btnDeleteModelPC.setText("Xóa");
        btnDeleteModelPC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnDeleteModelPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteModelPCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteModelPCMouseExited(evt);
            }
        });
        btnDeleteModelPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteModelPCActionPerformed(evt);
            }
        });
        jPanel16.add(btnDeleteModelPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 180, 110, 40));

        jPanel21.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sắp xếp dữ liệu theo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N
        jPanel21.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboSort.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboSort.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hãng", "Tên hãng" }));
        cboSort.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cboSort.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSortActionPerformed(evt);
            }
        });
        jPanel21.add(cboSort, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 180, 30));

        buttonGroup2.add(rdbAZ);
        rdbAZ.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbAZ.setForeground(new java.awt.Color(51, 0, 204));
        rdbAZ.setText("Tăng");
        jPanel21.add(rdbAZ, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 80, -1));

        buttonGroup2.add(rdbZA);
        rdbZA.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbZA.setForeground(new java.awt.Color(0, 0, 204));
        rdbZA.setText("Giảm");
        jPanel21.add(rdbZA, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 80, -1));

        btnSort2.setBackground(new java.awt.Color(204, 204, 255));
        btnSort2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSort2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/sort.png"))); // NOI18N
        btnSort2.setText("Sắp xếp");
        btnSort2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnSort2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSort2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSort2MouseExited(evt);
            }
        });
        btnSort2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSort2ActionPerformed(evt);
            }
        });
        jPanel21.add(btnSort2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 180, 40));

        jPanel16.add(jPanel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 110, 220, 190));

        jLabel48.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(54, 33, 89));
        jLabel48.setText("Tên hãng sản xuất");
        jPanel16.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 190, 32));

        Tab.addTab("hang san xuat", jPanel16);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(204, 204, 255));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(102, 102, 102));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("QUẢN LÍ SẢN PHẨM");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(54, 33, 89));
        jLabel17.setText("MÃ SẢN PHẨM");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 70, 120, 32));
        jPanel3.add(sptInforMaSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, 100, 26));

        txtInforMaSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforMaSP.setText("D365");
        txtInforMaSP.setBorder(null);
        txtInforMaSP.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforMaSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforMaSPActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforMaSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 100, 100, 40));
        jPanel3.add(sptInforHangSX, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 120, 20));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(54, 33, 89));
        jLabel18.setText("HÃNG SẢN XUẤT");
        jPanel3.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 70, 130, 32));

        txtInforHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforHang.setText("DELL");
        txtInforHang.setBorder(null);
        txtInforHang.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforHangActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforHang, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 120, 40));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(54, 33, 89));
        jLabel19.setText("TÊN SẢN PHẨM");
        jPanel3.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 156, 32));
        jPanel3.add(sptInforTenSp, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 220, 120, 20));

        txtInforTenSP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforTenSP.setText("LATUTADE 365");
        txtInforTenSP.setBorder(null);
        txtInforTenSP.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforTenSP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforTenSPActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforTenSP, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 190, 120, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(54, 33, 89));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/images/ASUS_MODEL.jpg"))); // NOI18N
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        jPanel3.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 50, 160, 160));
        jPanel3.add(sptInforHeDieuHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 200, 20));

        txtInforHeDieuHanh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforHeDieuHanh.setText("WINDOWS 10 PRO");
        txtInforHeDieuHanh.setBorder(null);
        txtInforHeDieuHanh.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforHeDieuHanh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforHeDieuHanhActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforHeDieuHanh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 100, 200, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(54, 33, 89));
        jLabel21.setText("CPU");
        jPanel3.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 240, 156, 32));
        jPanel3.add(sptInforCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 300, 280, 20));

        txtInforCPU.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforCPU.setText("Intel Core i5, 2.40 GHz, 8MB");
        txtInforCPU.setBorder(null);
        txtInforCPU.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforCPU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforCPUActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforCPU, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 270, 270, 40));
        jPanel3.add(sptInforDoHoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 390, 110, 20));

        txtInforDoHoa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforDoHoa.setText("Iris Xe 1300MHz");
        txtInforDoHoa.setBorder(null);
        txtInforDoHoa.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforDoHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforDoHoaActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforDoHoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 360, 120, 40));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(54, 33, 89));
        jLabel23.setText("RAM");
        jPanel3.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 320, 120, 32));
        jPanel3.add(sptInforRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 390, 110, 20));

        txtInforRam.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforRam.setText("8GB, 3600MHz");
        txtInforRam.setBorder(null);
        txtInforRam.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforRam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforRamActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforRam, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 360, 110, 40));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(54, 33, 89));
        jLabel24.setText("LƯU TRỮ");
        jPanel3.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 160, 156, 32));
        jPanel3.add(sptInforLuuTru, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 220, 197, 20));

        txtInforLuuTru.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforLuuTru.setText("SSD M2. PCIe, 512GB");
        txtInforLuuTru.setBorder(null);
        txtInforLuuTru.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforLuuTru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforLuuTruActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforLuuTru, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 190, 197, 40));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(54, 33, 89));
        jLabel25.setText("HỆ ĐIỀU HÀNH");
        jPanel3.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 70, 156, 32));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(54, 33, 89));
        jLabel26.setText("XUẤT XỨ");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 100, 32));
        jPanel3.add(sptInforXuatXu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 220, 120, 20));

        txtInforXuatSu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforXuatSu.setText("TRUNG QUỐC");
        txtInforXuatSu.setBorder(null);
        txtInforXuatSu.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforXuatSu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforXuatSuActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforXuatSu, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 190, 120, 40));
        jPanel3.add(sptInforMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 390, 70, 20));

        txtInforMauSac.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforMauSac.setText("ĐEN ĐỎ");
        txtInforMauSac.setBorder(null);
        txtInforMauSac.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforMauSacActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforMauSac, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, 70, 40));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(54, 33, 89));
        jLabel27.setText("MÀU SẮC");
        jPanel3.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 320, 90, 32));
        jPanel3.add(sptInforPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 390, 130, 20));

        txtInforPIN.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforPIN.setText("Lithium polymer, 45W");
        txtInforPIN.setBorder(null);
        txtInforPIN.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforPIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforPINActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforPIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 360, 130, 40));

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(54, 33, 89));
        jLabel28.setText("PIN");
        jPanel3.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 120, 32));

        jLabel29.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(54, 33, 89));
        jLabel29.setText("MÀN HÌNH");
        jPanel3.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 156, 32));
        jPanel3.add(sptInforManHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 300, 200, 20));

        txtInforManHinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforManHinh.setText("15.6 INCH, 1920 x 1080 Pixels");
        txtInforManHinh.setBorder(null);
        txtInforManHinh.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforManHinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforManHinhActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforManHinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 270, 200, 40));

        jLabel30.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(54, 33, 89));
        jLabel30.setText("KHỐI LƯỢNG");
        jPanel3.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 240, 156, 32));
        jPanel3.add(sptInforKhoiLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 300, 150, 20));

        txtInforKhoiLuong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforKhoiLuong.setText("1.66 kg");
        txtInforKhoiLuong.setBorder(null);
        txtInforKhoiLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforKhoiLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforKhoiLuongActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforKhoiLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 270, 150, 40));

        jLabel31.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(54, 33, 89));
        jLabel31.setText("SỐ LƯỢNG");
        jPanel3.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 320, 90, 32));
        jPanel3.add(sptInforSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 390, 60, 20));

        txtInforSoLuong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforSoLuong.setText("500");
        txtInforSoLuong.setBorder(null);
        txtInforSoLuong.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforSoLuong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforSoLuongActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforSoLuong, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 360, 60, 40));
        jPanel3.add(sptInforGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 390, 70, 20));

        txtInforGia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtInforGia.setText("16000000");
        txtInforGia.setBorder(null);
        txtInforGia.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtInforGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforGiaActionPerformed(evt);
            }
        });
        jPanel3.add(txtInforGia, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 360, 70, 40));

        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(54, 33, 89));
        jLabel33.setText("GIÁ");
        jPanel3.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 320, 70, 32));

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));

        tblInforPC.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tblInforPC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã SP", "Tên SP", "Xuất sứ", "Số lượng", "Giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblInforPC.setGridColor(new java.awt.Color(255, 255, 255));
        tblInforPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblInforPCMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblInforPC);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 420, 870, 120));

        jPanel7.setBackground(new java.awt.Color(204, 204, 255));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnNew.setBackground(new java.awt.Color(204, 204, 255));
        btnNew.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/edit (1).png"))); // NOI18N
        btnNew.setText("Làm mới");
        btnNew.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnNew.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnNewMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnNewMouseExited(evt);
            }
        });
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });
        jPanel7.add(btnNew, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 110, 40));

        btnSaveInforPC.setBackground(new java.awt.Color(204, 204, 255));
        btnSaveInforPC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSaveInforPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/diskette.png"))); // NOI18N
        btnSaveInforPC.setText("Lưu");
        btnSaveInforPC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnSaveInforPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSaveInforPCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSaveInforPCMouseExited(evt);
            }
        });
        btnSaveInforPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveInforPCActionPerformed(evt);
            }
        });
        jPanel7.add(btnSaveInforPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 40));

        btnDeleteInforPC.setBackground(new java.awt.Color(204, 204, 255));
        btnDeleteInforPC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnDeleteInforPC.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/delete.png"))); // NOI18N
        btnDeleteInforPC.setText("Xóa");
        btnDeleteInforPC.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnDeleteInforPC.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnDeleteInforPCMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnDeleteInforPCMouseExited(evt);
            }
        });
        btnDeleteInforPC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteInforPCActionPerformed(evt);
            }
        });
        jPanel7.add(btnDeleteInforPC, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 110, 40));

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sắp xếp dữ liệu theo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 10))); // NOI18N
        jPanel22.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cboSort1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        cboSort1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mã hãng", "Tên hãng" }));
        cboSort1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        cboSort1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSort1ActionPerformed(evt);
            }
        });
        jPanel22.add(cboSort1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 110, 30));

        buttonGroup1.add(rdbAZ1);
        rdbAZ1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbAZ1.setForeground(new java.awt.Color(51, 0, 204));
        rdbAZ1.setText("Tăng");
        rdbAZ1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbAZ1ActionPerformed(evt);
            }
        });
        jPanel22.add(rdbAZ1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 80, -1));

        buttonGroup1.add(rdbZA1);
        rdbZA1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        rdbZA1.setForeground(new java.awt.Color(0, 0, 204));
        rdbZA1.setText("Giảm");
        jPanel22.add(rdbZA1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 80, -1));

        btnSort3.setBackground(new java.awt.Color(204, 204, 255));
        btnSort3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSort3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/sort.png"))); // NOI18N
        btnSort3.setText("Sắp xếp");
        btnSort3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        btnSort3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnSort3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnSort3MouseExited(evt);
            }
        });
        btnSort3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSort3ActionPerformed(evt);
            }
        });
        jPanel22.add(btnSort3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 110, 30));

        jPanel7.add(jPanel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 130, 160));

        jPanel3.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 60, 150, 340));

        jPanel9.setBackground(new java.awt.Color(204, 204, 255));
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFirst.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/previous.png"))); // NOI18N
        btnFirst.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnFirstMouseClicked(evt);
            }
        });
        jPanel9.add(btnFirst, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        btnPrevious.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/left-arrow.png"))); // NOI18N
        btnPrevious.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPreviousMouseClicked(evt);
            }
        });
        jPanel9.add(btnPrevious, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, -1, -1));

        btnNext.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/arrow-right.png"))); // NOI18N
        btnNext.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnNextMouseClicked(evt);
            }
        });
        jPanel9.add(btnNext, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        btnLast.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/next.png"))); // NOI18N
        btnLast.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLastMouseClicked(evt);
            }
        });
        jPanel9.add(btnLast, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));

        txtInforPCRecord.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        txtInforPCRecord.setForeground(new java.awt.Color(255, 51, 51));
        txtInforPCRecord.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/library/icon/visits (1).png"))); // NOI18N
        jPanel9.add(txtInforPCRecord, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 200, -1));

        txtFind.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtFindCaretUpdate(evt);
            }
        });
        jPanel9.add(txtFind, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 210, 30));

        jLabel22.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(54, 33, 89));
        jLabel22.setText("Tìm theo tên sản phẩm:");
        jPanel9.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 10, 180, 32));

        jPanel3.add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 870, 50));

        jLabel32.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(54, 33, 89));
        jLabel32.setText("ĐỒ HỌA");
        jPanel3.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 80, 32));

        Tab.addTab("san pham", jPanel3);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel14.setBackground(new java.awt.Color(204, 204, 255));

        jLabel35.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(102, 102, 102));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setText("GIỚI THIỆU PHẦN MỀM");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel4.add(jPanel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 40));

        jtaInfor.setColumns(20);
        jtaInfor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtaInfor.setRows(5);
        jtaInfor.setText("\n      Phần mềm QUẢN LÍ THÔNG TIN LAPTOP được xây dựng và phát triển dựa trên NNLT Java.\n\n\t- Phần mềm có giao diện ưa nhìn (sẽ được nâng cấp tính năng dark-mode)\n\n\t- Có giao diện đăng nhập và phân quyền quản trị \n\t       (chỉ Quản lí mới được chỉnh sửa dữ liệu, ngoài ra chỉ được xem)\n\n\t- Được xây dựng trong khoảng thời gian ngắn vì vậy khó tránh việc chưa tối ưu, \n\t       nhưng sẽ được bảo trì dài hạn\n\n\t- Phần mềm có chương trình bắt lỗi khá tối ưu, \n\t       thao tác gần gũi giúp người dùng không cần dùng chuột quá nhiều\n\n\t- Xin cảm ơn, hãy góp ý để tôi hoàn thiện phần mềm tối ưu hơn!\n\n\t- Chúc bạn có một trải nghiệm tuyệt vời!");
        jtaInfor.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane4.setViewportView(jtaInfor);

        jPanel4.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 810, 410));

        Tab.addTab("GioiThieu", jPanel4);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel15.setBackground(new java.awt.Color(204, 204, 255));

        jLabel36.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(102, 102, 102));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel36.setText("HỖ TRỢ NGƯỜI DÙNG");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel36, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 40));

        jtaSupport.setColumns(20);
        jtaSupport.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jtaSupport.setRows(5);
        jtaSupport.setText("\n\t\t       ĐĂNG NHẬP\n\n- Đăng nhập: nhập đúng tên đăng nhập và mật khẩu được cấp.\n\t\t\n\n\n\t\t       NHẬP LIỆU\n\n- Bạn cần nhập đúng mã hãng sản xuất của sản phẩm (nếu chưa có bạn phải tạo hãng mới).\n\n\n\n- Bạn có thể liên hệ cô ty phần mềm của chúng tôi để được hỗ trợ nhanh nhất.\n\tFacebook: facebook.com/RyzenV.sortware\n\tZalo: 0392023330\n\tGithub: github.com/TheAnIshtar\n");
        jtaSupport.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        jScrollPane5.setViewportView(jtaSupport);

        jPanel1.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 110, 810, 430));

        Tab.addTab("Ho tro", jPanel1);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setForeground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(54, 33, 89));
        jLabel9.setText("Họ và tên");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 118, 41));

        jSeparator10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel6.add(jSeparator10, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 190, 340, 20));

        txtInforName.setEditable(false);
        txtInforName.setBackground(new java.awt.Color(255, 255, 255));
        txtInforName.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtInforName.setText("Nguyễn Văn An");
        txtInforName.setBorder(null);
        txtInforName.setCaretColor(new java.awt.Color(255, 255, 255));
        txtInforName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforNameActionPerformed(evt);
            }
        });
        jPanel6.add(txtInforName, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 160, 340, 32));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(54, 33, 89));
        jLabel10.setText("Chức vụ");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 118, 41));

        jSeparator11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel6.add(jSeparator11, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 340, 20));

        txtInforRole.setEditable(false);
        txtInforRole.setBackground(new java.awt.Color(255, 255, 255));
        txtInforRole.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtInforRole.setText("Thu ngân");
        txtInforRole.setBorder(null);
        txtInforRole.setCaretColor(new java.awt.Color(255, 255, 255));
        txtInforRole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInforRoleActionPerformed(evt);
            }
        });
        jPanel6.add(txtInforRole, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 240, 340, 32));

        jPanel18.setBackground(new java.awt.Color(255, 255, 255));

        jLabel39.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(102, 102, 102));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel39.setText("THÔNG TIN TÀI KHOẢN");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 807, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 123, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel39, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jPanel6.add(jPanel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 930, 40));

        Tab.addTab("readme", jPanel6);

        jplContainer.add(Tab, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 30, 930, 620));

        jplState.setBackground(new java.awt.Color(255, 255, 255));

        jlbState.setFont(new java.awt.Font("Baskerville Old Face", 1, 48)); // NOI18N
        jlbState.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jlbState.setText("-");
        jlbState.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jlbStateFocusGained(evt);
            }
        });
        jlbState.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jlbStateMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jlbStateMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jlbStateMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jplStateLayout = new javax.swing.GroupLayout(jplState);
        jplState.setLayout(jplStateLayout);
        jplStateLayout.setHorizontalGroup(
            jplStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jlbState, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );
        jplStateLayout.setVerticalGroup(
            jplStateLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jplStateLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jlbState, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jplContainer.add(jplState, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 0, 60, -1));

        jplMain.add(jplContainer, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1230, 650));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jplMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jplMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jplLoseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseExited
        // TODO add your handling code here:
        jplLose.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_jplLoseMouseExited

    private void jplLoseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseEntered
        jplLose.setBackground(Color.red);
        //jlbClose.setForeground(Color.white);
    }//GEN-LAST:event_jplLoseMouseEntered

    private void jplLoseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jplLoseMouseClicked
        System.exit(0);
    }//GEN-LAST:event_jplLoseMouseClicked

    private void jlbCloseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseExited
        // TODO add your handling code here:
        jplLose.setBackground(new Color(255, 255, 255));
        jlbClose.setForeground(Color.black);
    }//GEN-LAST:event_jlbCloseMouseExited

    private void jlbCloseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseEntered
        // TODO add your handling code here:
        jplLose.setBackground(Color.red);
        jlbClose.setForeground(Color.white);
    }//GEN-LAST:event_jlbCloseMouseEntered

    private void jlbCloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbCloseMouseClicked
        int ketQua = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn đóng chương trình không?", "Đóng chương trình", JOptionPane.YES_OPTION);
        if (ketQua == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_jlbCloseMouseClicked

    private void jlbCloseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlbCloseFocusGained
        //jplLose.setBackground(Color.red);
        System.exit(0);
    }//GEN-LAST:event_jlbCloseFocusGained

    private void jblTaiKhoanMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblTaiKhoanMouseEntered
        if (!this.click.equalsIgnoreCase("tai khoan"))
            jplTaiKhoan.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_jblTaiKhoanMouseEntered

    private void jblGioiThieuMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblGioiThieuMouseEntered
        if (!this.click.equalsIgnoreCase("gioi thieu"))
            jplGioiThieu.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_jblGioiThieuMouseEntered

    private void jblTaiKhoanMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblTaiKhoanMouseExited
        if (!this.click.equalsIgnoreCase("tai khoan"))
            jplTaiKhoan.setBackground(new Color(54, 43, 100));
    }//GEN-LAST:event_jblTaiKhoanMouseExited

    private void jblGioiThieuMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblGioiThieuMouseExited
        if (!this.click.equalsIgnoreCase("gioi thieu"))
            jplGioiThieu.setBackground(new Color(54, 43, 100));
    }//GEN-LAST:event_jblGioiThieuMouseExited

    private void jblDangXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblDangXuatMouseExited
        jplDangXuat.setBackground(new Color(54, 43, 100));
//        jplKhachHang.setBackground(new Color(54, 43, 100));
//        jplTaiKhoan.setBackground(new Color(54, 43, 100));
//        jplGiaoDich.setBackground(new Color(54, 43, 100));
    }//GEN-LAST:event_jblDangXuatMouseExited

    private void jblDangXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblDangXuatMouseEntered
        jplDangXuat.setBackground(new Color(125, 27, 126));
    }//GEN-LAST:event_jblDangXuatMouseEntered

    private void jblTaiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblTaiKhoanMouseClicked
        setClick("tai khoan");
        xuLyClick();
        jplHoTro.setBackground(new Color(54, 43, 100));
        jplGioiThieu.setBackground(new Color(54, 43, 100));
        jplSanPham.setBackground(new Color(54, 43, 100));
        jplHangSanXuat.setBackground(new Color(54, 43, 100));
        Tab.setSelectedIndex(1);
        loadDataAccountTable();
    }//GEN-LAST:event_jblTaiKhoanMouseClicked

    private void jblGioiThieuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblGioiThieuMouseClicked
        setClick("gioi thieu");
        xuLyClick();
        jplHoTro.setBackground(new Color(54, 43, 100));
        jplTaiKhoan.setBackground(new Color(54, 43, 100));
        jplSanPham.setBackground(new Color(54, 43, 100));
        Tab.setSelectedIndex(4);
    }//GEN-LAST:event_jblGioiThieuMouseClicked

    private void jblDangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblDangXuatMouseClicked
        int ketQua = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn đăng xuất không?", "Đăng xuất", JOptionPane.YES_OPTION);
        if (ketQua == JOptionPane.YES_OPTION) {
            Login login = new Login();
            login.setVisible(true);
            this.dispose();
        }
        setClick("dang xuat");
        //xuLyClick();
        setColor();
    }//GEN-LAST:event_jblDangXuatMouseClicked

    private void jblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblSanPhamMouseClicked
        setClick("san pham");
        xuLyClick();
        jplGioiThieu.setBackground(new Color(54, 43, 100));
        jplTaiKhoan.setBackground(new Color(54, 43, 100));
        jplHoTro.setBackground(new Color(54, 43, 100));
        jplHangSanXuat.setBackground(new Color(54, 43, 100));
        Tab.setSelectedIndex(3);
        loadDataInforPCTable();
    }//GEN-LAST:event_jblSanPhamMouseClicked

    private void jblSanPhamMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblSanPhamMouseEntered
        if (!this.click.equalsIgnoreCase("san pham"))
            jplSanPham.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_jblSanPhamMouseEntered

    private void jblSanPhamMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblSanPhamMouseExited
        if (!this.click.equalsIgnoreCase("san pham"))
            jplSanPham.setBackground(new Color(54, 43, 100));
    }//GEN-LAST:event_jblSanPhamMouseExited

    private void jblNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblNameMouseClicked
        Tab.setSelectedIndex(6);
    }//GEN-LAST:event_jblNameMouseClicked

    private void txtInforNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforNameActionPerformed

    private void txtInforRoleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforRoleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforRoleActionPerformed

    private void jblNameMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblNameMouseEntered
        jblName.setForeground(Color.red);
    }//GEN-LAST:event_jblNameMouseEntered

    private void jblNameMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblNameMouseExited
        jblName.setForeground(Color.white);
    }//GEN-LAST:event_jblNameMouseExited

    private void txtInforMaSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforMaSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforMaSPActionPerformed

    private void txtInforHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforHangActionPerformed

    private void txtInforTenSPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforTenSPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforTenSPActionPerformed

    private void txtInforHeDieuHanhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforHeDieuHanhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforHeDieuHanhActionPerformed

    private void txtInforCPUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforCPUActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforCPUActionPerformed

    private void txtInforDoHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforDoHoaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforDoHoaActionPerformed

    private void txtInforRamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforRamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforRamActionPerformed

    private void txtInforLuuTruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforLuuTruActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforLuuTruActionPerformed

    private void txtInforXuatSuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforXuatSuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforXuatSuActionPerformed

    private void txtInforMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforMauSacActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforMauSacActionPerformed

    private void txtInforPINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforPINActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforPINActionPerformed

    private void txtInforManHinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforManHinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforManHinhActionPerformed

    private void txtInforKhoiLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforKhoiLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforKhoiLuongActionPerformed

    private void txtInforSoLuongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforSoLuongActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforSoLuongActionPerformed

    private void txtInforGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInforGiaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInforGiaActionPerformed

    private void btnLastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastMouseClicked
        LastInforPC();
    }//GEN-LAST:event_btnLastMouseClicked

    private void btnNextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextMouseClicked
        NextInforPC();
    }//GEN-LAST:event_btnNextMouseClicked

    private void btnPreviousMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviousMouseClicked
        PreviousInforPC();
    }//GEN-LAST:event_btnPreviousMouseClicked

    private void btnFirstMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstMouseClicked
        FirstInforPC();
    }//GEN-LAST:event_btnFirstMouseClicked

    private void txtAccountTenNguoiDungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountTenNguoiDungActionPerformed
        
    }//GEN-LAST:event_txtAccountTenNguoiDungActionPerformed

    private void txtAccountTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccountTenDangNhapActionPerformed

    private void txtAccountChucVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountChucVuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccountChucVuActionPerformed

    private void txtAccountTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountTrangThaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccountTrangThaiActionPerformed

    private void btnFirstAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFirstAccountMouseClicked
        FirstAccount();
    }//GEN-LAST:event_btnFirstAccountMouseClicked

    private void btnPreviousAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPreviousAccountMouseClicked
        PreviousAccount();
    }//GEN-LAST:event_btnPreviousAccountMouseClicked

    private void btnNextAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNextAccountMouseClicked
        NextAccount();
    }//GEN-LAST:event_btnNextAccountMouseClicked

    private void btnLastAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLastAccountMouseClicked
        LastAccount();
    }//GEN-LAST:event_btnLastAccountMouseClicked

    private void btnNewMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseEntered
        ///jplNew.setBackground(new Color(0,102,102));
        btnNew.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnNewMouseEntered

    private void btnNewMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewMouseExited
        //jplNew.setBackground(new Color(51,51,51));
        btnNew.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_btnNewMouseExited

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        NewInforPC();
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnSaveInforPCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveInforPCMouseEntered
        //jplSave.setBackground(new Color(0,102,102));
        btnSaveInforPC.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnSaveInforPCMouseEntered

    private void btnSaveInforPCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveInforPCMouseExited
        //jplSave.setBackground(new Color(51,51,51));
        btnSaveInforPC.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_btnSaveInforPCMouseExited

    private void btnSaveInforPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveInforPCActionPerformed

    }//GEN-LAST:event_btnSaveInforPCActionPerformed

    private void btnDeleteInforPCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteInforPCMouseEntered
        //jplDelete.setBackground(new Color(0,102,102));
        btnDeleteInforPC.setBackground(new Color(0, 102, 102));
    }//GEN-LAST:event_btnDeleteInforPCMouseEntered

    private void btnDeleteInforPCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteInforPCMouseExited
        //jplDelete.setBackground(new Color(51,51,51));
        btnDeleteInforPC.setBackground(new Color(204, 204, 255));
    }//GEN-LAST:event_btnDeleteInforPCMouseExited

    private void btnDeleteInforPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteInforPCActionPerformed
        //HideErrorThongTinNull();

    }//GEN-LAST:event_btnDeleteInforPCActionPerformed

    private void tblListAccountMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblListAccountMouseClicked
        int index = tblListAccount.getSelectedRow();
        showDetailAccount(index);
        this.indexAccount = index;
    }//GEN-LAST:event_tblListAccountMouseClicked

    private void txtAccountMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccountMatKhauActionPerformed

    private void btnNew1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNew1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNew1MouseEntered

    private void btnNew1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNew1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNew1MouseExited

    private void btnNew1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNew1ActionPerformed
        txtAccountTenNguoiDung.setText("");
        txtAccountTenDangNhap.setText("");
        txtAccountMatKhau.setText("");
        txtAccountChucVu.setText("");
        txtAccountTrangThai.setText("Trạng thái");
        txtAccountTrangThai.setForeground(new Color(0, 153, 0));
        switchButton1.setSelected(false);
    }//GEN-LAST:event_btnNew1ActionPerformed

    private void btnSave1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave1MouseEntered

    private void btnSave1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSave1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSave1MouseExited

    private void btnSave1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSave1ActionPerformed
        saveAccount();
    }//GEN-LAST:event_btnSave1ActionPerformed

    private void btnDelete1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelete1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelete1MouseEntered

    private void btnDelete1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDelete1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelete1MouseExited

    private void btnDelete1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDelete1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDelete1ActionPerformed

    private void btnFind1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFind1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFind1MouseEntered

    private void btnFind1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnFind1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFind1MouseExited

    private void btnFind1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFind1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFind1ActionPerformed

    private void jblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblImageMouseClicked
        Tab.setSelectedIndex(0);
    }//GEN-LAST:event_jblImageMouseClicked

    private void jblHangSanXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblHangSanXuatMouseClicked
        setClick("hang san xuat");
        xuLyClick();
        jplTaiKhoan.setBackground(new Color(54, 43, 100));
        jplSanPham.setBackground(new Color(54, 43, 100));
        jplGioiThieu.setBackground(new Color(54, 43, 100));
        jplHoTro.setBackground(new Color(54, 43, 100));
        Tab.setSelectedIndex(2);
        loadDataModelTable();
    }//GEN-LAST:event_jblHangSanXuatMouseClicked

    private void jblHangSanXuatMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblHangSanXuatMouseEntered
        if (!this.click.equalsIgnoreCase("hang san xuat"))
            jplHangSanXuat.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_jblHangSanXuatMouseEntered

    private void jblHangSanXuatMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblHangSanXuatMouseExited
        if (!this.click.equalsIgnoreCase("hang san xuat"))
            jplHangSanXuat.setBackground(new Color(54, 43, 100));
    }//GEN-LAST:event_jblHangSanXuatMouseExited

    private void jblHoTroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblHoTroMouseClicked
        setClick("ho tro");
        xuLyClick();
        jplGioiThieu.setBackground(new Color(54, 43, 100));
        jplTaiKhoan.setBackground(new Color(54, 43, 100));
        jplSanPham.setBackground(new Color(54, 43, 100));
        jplHangSanXuat.setBackground(new Color(54, 43, 100));
        Tab.setSelectedIndex(5);
    }//GEN-LAST:event_jblHoTroMouseClicked

    private void jblHoTroMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblHoTroMouseEntered
        if (!this.click.equalsIgnoreCase("ho tro"))
            jplHoTro.setBackground(new Color(85, 65, 118));
    }//GEN-LAST:event_jblHoTroMouseEntered

    private void jblHoTroMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jblHoTroMouseExited
        //xuLyClick();
        if (!this.click.equalsIgnoreCase("ho tro")) {
            jplHoTro.setBackground(new Color(54, 43, 100));
        }
    }//GEN-LAST:event_jblHoTroMouseExited

    private void tblHangSanXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHangSanXuatMouseClicked
        int index = tblHangSanXuat.getSelectedRow();
        showDetailModel(index);
    }//GEN-LAST:event_tblHangSanXuatMouseClicked

    private void switchButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_switchButton1MouseClicked
        //setTrangThaiHoatDong();
    }//GEN-LAST:event_switchButton1MouseClicked

    private void switchButton1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_switchButton1MouseReleased
        setTrangThaiHoatDong();

    }//GEN-LAST:event_switchButton1MouseReleased

    private void txtMaHangSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHangSanXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHangSanXuatActionPerformed

    private void txtTenHangSanXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenHangSanXuatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenHangSanXuatActionPerformed

    private void btnNewModelMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewModelMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewModelMouseEntered

    private void btnNewModelMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnNewModelMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnNewModelMouseExited

    private void btnNewModelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewModelActionPerformed
        txtMaHangSanXuat.setText("");
        txtTenHangSanXuat.setText("");
    }//GEN-LAST:event_btnNewModelActionPerformed

    private void btnSaveModelPCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveModelPCMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveModelPCMouseEntered

    private void btnSaveModelPCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSaveModelPCMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveModelPCMouseExited

    private void btnSaveModelPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveModelPCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSaveModelPCActionPerformed

    private void btnDeleteModelPCMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteModelPCMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteModelPCMouseEntered

    private void btnDeleteModelPCMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnDeleteModelPCMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteModelPCMouseExited

    private void btnDeleteModelPCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteModelPCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteModelPCActionPerformed

    private void btnSort2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSort2MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSort2MouseEntered

    private void btnSort2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSort2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSort2MouseExited

    private void btnSort2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSort2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSort2ActionPerformed

    private void cboSortActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSortActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSortActionPerformed

    private void tblInforPCMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblInforPCMouseClicked
        int index = tblInforPC.getSelectedRow();
        showDetailInforPC(index);
        this.indexInforPC = index;
    }//GEN-LAST:event_tblInforPCMouseClicked

    private void jlbStateFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jlbStateFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jlbStateFocusGained

    private void jlbStateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseClicked
        this.setState(1);
    }//GEN-LAST:event_jlbStateMouseClicked

    private void jlbStateMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseEntered
        jplState.setBackground(new Color(229, 221, 242));
    }//GEN-LAST:event_jlbStateMouseEntered

    private void jlbStateMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jlbStateMouseExited
        jplState.setBackground(Color.white);

    }//GEN-LAST:event_jlbStateMouseExited

    private void txtFindCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtFindCaretUpdate
        fillToTableInforPC();
    }//GEN-LAST:event_txtFindCaretUpdate

    private void txtAccountEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAccountEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAccountEmailActionPerformed

    private void txtFindHangSXMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtFindHangSXMouseClicked
        txtFindHangSX.setText("");
    }//GEN-LAST:event_txtFindHangSXMouseClicked

    private void cboSort1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSort1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboSort1ActionPerformed

    private void btnSort3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSort3MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSort3MouseEntered

    private void btnSort3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnSort3MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSort3MouseExited

    private void btnSort3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSort3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSort3ActionPerformed

    private void rdbAZ1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbAZ1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdbAZ1ActionPerformed

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
//        JFileChooser chooser = new JFileChooser();
//
//        //Thiết lập bộ lọc chọn ảnh
//        chooser.setFileFilter(new FileFilter() {
//            @Override
//            public boolean accept(File f) {
//
//                if (f.isDirectory()) {
//                    return true;
//                } else {
//                    //CSDL không có đuôi mở rộng của ảnh nên ngầm định chỉ làm việc với file png
//                    return f.getName().toLowerCase().endsWith(".png");
//                }
//            }
//
//            @Override
//            public String getDescription() {
//                //Xác định loại File mà người dùng có thể chọn
//                return "Image File (*.png)";
//            }
//        });
//
//        //Chọn cancel sẽ ngừng
//
//        //Lấy dữ liệu do người dùng chọn
//        File file = chooser.getSelectedFile();
//        try {
//            //Tạo đối tượng từ đường dẫn
//            ImageIcon icon = new ImageIcon(file.getPath());
//            //Điều chỉnh kích thước ảnh
//            Image img = EditImages.reSize(icon.getImage(), 200, 200);
//
//            //Tạo lại đối tượng img để hiển thị
//            ImageIcon resizeIcon = new ImageIcon(img);
//            tblImg.setIcon(resizeIcon);
//
//            //Chuyển ND của hình thảnh dạng mảng byte để 
//            Image = EditImages.toByteArray(img, "png");
//        } catch (Exception e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(this, e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_jLabel20MouseClicked

    private void txtAccountTenNguoiDungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAccountTenNguoiDungMouseClicked
        lblAccountTenNguoiDung.setForeground(new Color(54,33,89));
    }//GEN-LAST:event_txtAccountTenNguoiDungMouseClicked

    private void txtAccountEmailMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAccountEmailMouseClicked
        lblAccountEmail.setForeground(new Color(54,33,89));
    }//GEN-LAST:event_txtAccountEmailMouseClicked

    private void txtAccountTenDangNhapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAccountTenDangNhapMouseClicked
        lblAccountTenDangNhap.setForeground(new Color(54,33,89));
    }//GEN-LAST:event_txtAccountTenDangNhapMouseClicked

    private void txtAccountChucVuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAccountChucVuMouseClicked
        lblAccountChucVu.setForeground(new Color(54,33,89));
    }//GEN-LAST:event_txtAccountChucVuMouseClicked

    private void txtAccountMatKhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAccountMatKhauMouseClicked
       lblAccountMatKhau.setForeground(new Color(54,33,89));
    }//GEN-LAST:event_txtAccountMatKhauMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PCManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PCManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PCManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PCManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PCManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane Tab;
    private javax.swing.JButton btnDelete1;
    private javax.swing.JButton btnDeleteInforPC;
    private javax.swing.JButton btnDeleteModelPC;
    private javax.swing.JButton btnFind1;
    private javax.swing.JLabel btnFirst;
    private javax.swing.JLabel btnFirstAccount;
    private javax.swing.JLabel btnLast;
    private javax.swing.JLabel btnLastAccount;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnNew1;
    private javax.swing.JButton btnNewModel;
    private javax.swing.JLabel btnNext;
    private javax.swing.JLabel btnNextAccount;
    private javax.swing.JLabel btnPrevious;
    private javax.swing.JLabel btnPreviousAccount;
    private javax.swing.JButton btnSave1;
    private javax.swing.JButton btnSaveInforPC;
    private javax.swing.JButton btnSaveModelPC;
    private javax.swing.JButton btnSort2;
    private javax.swing.JButton btnSort3;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<String> cboSort;
    private javax.swing.JComboBox<String> cboSort1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JLabel jblDangXuat;
    private javax.swing.JLabel jblGioiThieu;
    private javax.swing.JLabel jblHangSanXuat;
    private javax.swing.JLabel jblHoTro;
    private javax.swing.JLabel jblImage;
    private javax.swing.JLabel jblImage1;
    private javax.swing.JLabel jblName;
    private javax.swing.JLabel jblRun;
    private javax.swing.JLabel jblSanPham;
    private javax.swing.JLabel jblTaiKhoan;
    private javax.swing.JLabel jlbClose;
    private javax.swing.JLabel jlbState;
    private javax.swing.JPanel jplContainer;
    private javax.swing.JPanel jplContainer1;
    private javax.swing.JPanel jplDangXuat;
    private javax.swing.JPanel jplGioiThieu;
    private javax.swing.JPanel jplHangSanXuat;
    private javax.swing.JPanel jplHoTro;
    private javax.swing.JPanel jplLose;
    private javax.swing.JPanel jplMain;
    private javax.swing.JPanel jplMenu;
    private javax.swing.JPanel jplSanPham;
    private javax.swing.JPanel jplState;
    private javax.swing.JPanel jplTaiKhoan;
    private javax.swing.JTextArea jtaInfor;
    private javax.swing.JTextArea jtaSupport;
    private javax.swing.JLabel lblAccountChucVu;
    private javax.swing.JLabel lblAccountEmail;
    private javax.swing.JLabel lblAccountMatKhau;
    private javax.swing.JLabel lblAccountTenDangNhap;
    private javax.swing.JLabel lblAccountTenNguoiDung;
    private javax.swing.JRadioButton rdbAZ;
    private javax.swing.JRadioButton rdbAZ1;
    private javax.swing.JRadioButton rdbZA;
    private javax.swing.JRadioButton rdbZA1;
    private javax.swing.JSeparator sptAccountChucVu;
    private javax.swing.JSeparator sptAccountEmail;
    private javax.swing.JSeparator sptAccountMatKhau;
    private javax.swing.JSeparator sptAccount_TenDangNhap;
    private javax.swing.JSeparator sptAccount_TenNguoiDung;
    private javax.swing.JSeparator sptHang_MaHang;
    private javax.swing.JSeparator sptHang_TenHang;
    private javax.swing.JSeparator sptInforCPU;
    private javax.swing.JSeparator sptInforDoHoa;
    private javax.swing.JSeparator sptInforGia;
    private javax.swing.JSeparator sptInforHangSX;
    private javax.swing.JSeparator sptInforHeDieuHanh;
    private javax.swing.JSeparator sptInforKhoiLuong;
    private javax.swing.JSeparator sptInforLuuTru;
    private javax.swing.JSeparator sptInforMaSp;
    private javax.swing.JSeparator sptInforManHinh;
    private javax.swing.JSeparator sptInforMauSac;
    private javax.swing.JSeparator sptInforPin;
    private javax.swing.JSeparator sptInforRam;
    private javax.swing.JSeparator sptInforSoLuong;
    private javax.swing.JSeparator sptInforTenSp;
    private javax.swing.JSeparator sptInforXuatXu;
    private com.hicode.switchbutton.SwitchButton switchButton1;
    private javax.swing.JTable tblHangSanXuat;
    private javax.swing.JTable tblInforPC;
    private javax.swing.JTable tblListAccount;
    private javax.swing.JTextField txtAccountChucVu;
    private javax.swing.JTextField txtAccountEmail;
    private javax.swing.JTextField txtAccountMatKhau;
    private javax.swing.JLabel txtAccountRecord;
    private javax.swing.JTextField txtAccountTenDangNhap;
    private javax.swing.JTextField txtAccountTenNguoiDung;
    private javax.swing.JTextField txtAccountTrangThai;
    private javax.swing.JTextField txtFind;
    private javax.swing.JTextField txtFindHangSX;
    private javax.swing.JTextField txtInforCPU;
    private javax.swing.JTextField txtInforDoHoa;
    private javax.swing.JTextField txtInforGia;
    private javax.swing.JTextField txtInforHang;
    private javax.swing.JTextField txtInforHeDieuHanh;
    private javax.swing.JTextField txtInforKhoiLuong;
    private javax.swing.JTextField txtInforLuuTru;
    private javax.swing.JTextField txtInforMaSP;
    private javax.swing.JTextField txtInforManHinh;
    private javax.swing.JTextField txtInforMauSac;
    private javax.swing.JTextField txtInforName;
    private javax.swing.JLabel txtInforPCRecord;
    private javax.swing.JTextField txtInforPIN;
    private javax.swing.JTextField txtInforRam;
    private javax.swing.JTextField txtInforRole;
    private javax.swing.JTextField txtInforSoLuong;
    private javax.swing.JTextField txtInforTenSP;
    private javax.swing.JTextField txtInforXuatSu;
    private javax.swing.JTextField txtMaHangSanXuat;
    private javax.swing.JLabel txtModelRecord;
    private javax.swing.JTextField txtTenHangSanXuat;
    // End of variables declaration//GEN-END:variables
}
