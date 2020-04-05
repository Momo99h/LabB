/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    public static String ShowInfoInput(String header,String msg)
    {
        return JOptionPane.showInputDialog
        (null, msg, header, JOptionPane.INFORMATION_MESSAGE);
    }
    public static void ShowInfoPopUp(String header,String msg)
    {
        JOptionPane.showMessageDialog(null,msg,header, JOptionPane.INFORMATION_MESSAGE);
    }
    /**
	 * metodo usato per generare un hash md5 da una stringa
	 * @param s stringa su cui lavorare
	 * @return string con Hash MD5
    */
    public static String StringMD5Hash(String s)
    {
        String stringToHash = s;
	String stringHashed = null;
        try {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(stringToHash.getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest();
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                stringHashed = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
                e.printStackTrace();
        }
        return stringHashed;
    }
    
}
