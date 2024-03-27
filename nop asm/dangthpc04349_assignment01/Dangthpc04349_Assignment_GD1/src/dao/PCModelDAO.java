/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import Assignment_model.PCModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dangt
 */
public class PCModelDAO {
     public List<PCModel> getAll(){
        List<PCModel> list = new ArrayList<>();
        list.clear();
        list.add(new PCModel("D03", "DELL"));
        list.add(new PCModel("M51", "MSI"));
        list.add(new PCModel("A5U5", "ASUS"));
        list.add(new PCModel("L3N050", "LENOVO"));
        list.add(new PCModel("H10", "HP"));
        list.add(new PCModel("L6", "LG"));
        return list;
    }
}
