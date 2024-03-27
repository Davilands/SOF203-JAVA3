/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.poly.bai1;

import java.util.ArrayList;

/**
 *
 * @author dangt
 */
public class test {

   public static void main(String[] args) { 
  String s="aaaaeeee";
    int len = s.length();int count;
    //int a[];
    ArrayList<Integer> a = new ArrayList<Integer>();
    for (int i=0; i<s.length(); i++) {
        count=0;
        for (int j=0; j<s.length(); j++) {
            if (s.charAt(i)==s.charAt(j)) {
                count++;
            }
        }
        //count--;
        a.add(Integer.valueOf(count));
        //System.out.println(a.get(i));
        //a[i] = count;
        //a[String.valueOf(value)]
    }
    

    Integer max=a.get(0);
    Integer min = a.get(0);
    for (int i=1;i<len-1; i++) {
        if(a.get(i)<min)
            min = a.get(i);
        if(a.get(i)>max)
            max=a.get(i);
    }
    
    for(int i=0; i<len;i++) {
        if (a.get(i)==max) {
            a.set(i, min);
        }
    }
    
    int max2=a.get(0);
    int index = -1;
    for(int i=1; i<len; i++) {
        if(a.get(i)>max2 && a.get(i)!=max ) {
            max=a.get(i);
            index = i;
        }
    }
    if (index!=-1)
       System.out.println( s.charAt(index));
    else 
           System.out.println("?");
    
//    for (int i=0; i<len; i++) {
//        //if (a.get(i)<a.get(0)) {
//            //return s.charAt(i);
//            System.out.print(s.charAt(i));
//            System.out.println(a.get(i));
//            //break;
//        //}
//    } 
    //System.out.println();
  } 
} 