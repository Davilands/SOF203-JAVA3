/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.bai1;

/**
 *
 * @author dangt
 */
public class test {

    public static void main(String[] args) {
        int arr[] = {
        //    1, 3, 5, 4, 5, 2, 2, 9, 2,9,9
          //   i  i  j  j  j                    
        //1,2,3,2, 1, 4, 1, 4
                2,2,2,2,2,2,2,5,8
        };
        int k = 2;
        int length = arr.length;    //9 -8 -7
        int count = 0;

        //int temp = arr[0];
        for (int i = 0; i < arr.length -1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    for (int z = i; z < arr.length ; z++) {
                        arr[j] = arr[z];
                    }
                    length --;
                    
                }
            }
        }
        if(length<0)
            length*=-1;
        
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
            if (arr[i]%k==0){
            count++;
        }
            
        }
        System.out.println("");
        System.out.println(length+"");
        System.out.println(count);
    }
}
