/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import Assignment_model.InforPC;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author dangt
 */
public class InforPCDAO {
    //List<InforPC> list = new ArrayList<>();
    
    public List<InforPC> getAll(){
        List<InforPC> list = new ArrayList<>();
        //list.clear();
        list.add(new InforPC("PC001", "DELL Latutade 354", "DELL", "", "Windows 10 Pro", "Intel Core i5, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 56 Wh", (float) 2.81, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 320, 29000000, "Nhật bản", "đỏ-đen"));
        list.add(new InforPC("PC002", "DELL Gaming G15", "DELL", "", "Windows 10 Pro", "Intel Core i7, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 40 Wh", (float) 3.2, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 150, 32000000, "Trung quốc", "xanh-đen"));
        list.add(new InforPC("PC003", "Lenovo legion 5 ", "LENOVO", "", "Windows 10 Home", "Intel Core i5, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 34 Wh", (float) 2.81, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 320, 29000000, "Nhật bản", "đỏ-đen"));
        list.add(new InforPC("PC004", "DELL Dell Gaming G15", "DELL", "", "Windows 10 Pro", "Intel Core i7, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 56 Wh", (float) 1.7, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 150, 32000000, "Việt nam", "đen"));
        list.add(new InforPC("PC005", "HP envy 13", "HP", "", "Windows 10 Home", "Intel Core i5, 2.70 GHz, 12MB", "NIKLYZ GeForce RTX3050, 4 GB", "3-cell Li-ion, 50 Wh", (float) 2.81, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 320, 29000000, "Nhật bản", "đỏ-đen"));
        list.add(new InforPC("PC006", "ASUS Gaming G15", "ASUS", "", "Windows 10 Home", "Intel Core i7, 2.70 GHz, 12MB", "Card rời - NVIDIA GeForce RTX3050, 4 GB", "3-cell Li-ion, 72 Wh", (float) 2.81, "15.6 inch, Full HD (1920 x 1080)", "DDR4, 32GB", "256 GB SSD NVMe PCIe", 150, 32000000, "Trung quốc", "xanh-đen"));
        return list;
    }
    
    public List<InforPC> findByName(String name) {
        List<InforPC> list = new ArrayList<>();
        InforPCDAO inforPCDAO = new InforPCDAO();
        
        for (InforPC index : inforPCDAO.getAll()) {
            String temp = index.getTenSP().toLowerCase();
            if (temp.contains(name)) {
                list.add(index);
            }
        }
        return list;
    }
}
