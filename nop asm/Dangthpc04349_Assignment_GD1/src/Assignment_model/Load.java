/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment_model;

import com.GUI.Loading;

/**
 *
 * @author dangt
 */
public class Load {

    public void loading(){
        Loading loading = new Loading();
        loading.setVisible(true);
        try {
            for (int i = 0; i <= 100; i++) {
                Thread.sleep(40);
                loading.lblLoading.setText(Integer.toString(i) + "%");
            }
        } catch (Exception e) {

        }
    } 
    public static void main(String[] args) {
        Load load = new Load();
        load.loading();
        
    }
}
