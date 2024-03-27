/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timebar;

/**
 *
 * @author dangt
 */
public class main {

    public static void main(String[] args) {
        time time = new time();
        try {
            time.setVisible(true);
            while (true) {
               
                if (time.start) {
                    for (int i = 0; i <= 100; i++) {
                        Thread.sleep(100);
                        time.jbltime.setText(Integer.toString(i) + "%");
                        time.bar.setValue(i);

                    }
                }
            }

        } catch (Exception e) {

        }
    }
}
