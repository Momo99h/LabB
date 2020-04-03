/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import javax.swing.JOptionPane;

/**
 *
 * @author Momo
 */
public class Utility 
{
    public static void ConsolePrintLine(Object msg)
    {
        System.out.println(msg);
    }
    public static void ConsolePrint(Object msg)
    {
        System.out.print(msg);
    }
    public static void ShowErrorPopUp(String header,String msg)
    {
        JOptionPane.showMessageDialog(null,msg,header, JOptionPane.ERROR_MESSAGE);
    }
    public static void ShowInfoPopUp(String header,String msg)
    {
        JOptionPane.showMessageDialog(null,msg,header, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
