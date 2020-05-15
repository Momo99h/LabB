/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.swing.JOptionPane;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.SendFailedException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

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
    public static Boolean ShowQuestionPopUp(String header,String msg)
    {
        return JOptionPane.showConfirmDialog(null,msg,header,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }
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
    public static String randomAlphaNumeric(int length)
    {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";    
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ ) 
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    public static String randomLetter(int length)
    {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";    
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ ) 
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    public static Boolean sendEmail(String to,String body)
    {
        String username= "";
        String password= "";
        String host = "smtp.office365.com";
	String from = username;
        String subject = Settings.serverName;
	Properties props = System.getProperties();
        props.put("mail.smtp.host",host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port",587);
        
        try
        {
            Session session = Session.getInstance(props);
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(from));
            msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
            msg.setSubject(subject);
            msg.setText(body);

            Transport.send(msg,username,password);
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }  
        
    }
    public static String emailBody(String usr,String code)
    {
        String msg = "Ciao %s, benvenuto su il Parioliere-LabB!";
        msg+="\nQuesto è il codice di verifica per completare la registrazione: %s";
        
        msg = String.format(msg, usr,code);
        
        return msg;
    }
    
    public static int getRandomInt(int min,int max){
        return (int) (Math.random() * ((max - min) + 1)) + min; 
    }     
}
