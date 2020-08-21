
package com.universita.ilparolierelabb.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 * Utility Contiene funzioni generali utilizzate nel codice
 * @author Momo
 */
public class Utility 
{
    /***
     * ConsolePrintLine Scrive una linea sulla console
     * @param msg Messaggio da scrivere
     */
    public static void ConsolePrintLine(Object msg)
    {
        System.out.println(msg);
    }
    /***
     * ConsolePrintLine Scrive sulla console
     * @param msg Messaggio da scrivere
     */
    public static void ConsolePrint(Object msg)
    {
        System.out.print(msg);
    }
    /***
     * ShowErrorPopUp Visualizza una popup di errore
     * @param header Titolo
     * @param msg Messaggio
     */
    public static void ShowErrorPopUp(String header,String msg)
    {
        JOptionPane.showMessageDialog(null,msg,header, JOptionPane.ERROR_MESSAGE);
    }
    /**
     * ShowInfoInput Visualizza una popup di inserimento
     * @param header Titolo
     * @param msg Messaggio
     * @return testo inserito
     */
    public static String ShowInfoInput(String header,String msg)
    {
        return JOptionPane.showInputDialog
        (null, msg, header, JOptionPane.INFORMATION_MESSAGE);
    }
    /***
     * ShowInfoPopUp Visualizza una popup di informazione
     * @param header Titolo
     * @param msg Messaggio
     */
    public static void ShowInfoPopUp(String header,String msg)
    {
        JOptionPane.showMessageDialog(null,msg,header, JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * ShowQuestionPopUp Visualizza una popup di conferma
     * @param header Titolo
     * @param msg Messaggio
     * @return true se è stato premuto il pulsante positivo
     */
    public static Boolean ShowQuestionPopUp(String header,String msg)
    {
        return JOptionPane.showConfirmDialog(null,msg,header,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION;
    }
    /***
     * StringMD5Hash Crea la codifica MD5 di una stringa
     * @param s Stringa da crittografare
     * @return Stringa crittografata
     */
    public static String StringMD5Hash(String s)
    {
        String stringToHash = s;
	String stringHashed = null;
        try 
        {
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
    /**
     * randomAlphaNumeric Crea una stringa alfanumerica pseudocasuale
     * @param length Lunghezza stringa
     * @return Stringa casuale
     */
    public static String randomAlphaNumeric(int length)
    {
        String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";    
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ ) 
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    /**
     * randomAlpha Crea una stringa pseudocasuale
     * @param length Lunghezza stringa
     * @return Stringa casuale
     */
    public static String randomAlpha(int length)
    {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";    
        Random rnd = new Random();
        StringBuilder sb = new StringBuilder(length);
        for( int i = 0; i < length; i++ ) 
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }
    /***
     * sendEmail Invia un email
     * @param to Email destinatario
     * @param body Corpo email
     * @return true se l'operazione è andata a buon fine
     */
    public static Boolean sendEmail(String to,String body)
    {
        String username= "sspagnolo1@studenti.uninsubria.it";
        String password= "Jyk11dhrx3!";
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
    /**
     * welcomeEmailBody Formatta una stringa di benvenuto per l'email
     * @param usr Nome utente
     * @param code Codice di verifica
     * @return Stringa di benvenuto formattata
     */
    public static String welcomeEmailBody(String usr,String code)
    {
        String msg = "Ciao %s, benvenuto su Il Paroliere-LabB!";
        msg+="\nQuesto è il codice di verifica per completare la registrazione: %s";
        msg+="\nInseriscilo entro 10 minuti per completare la tua registrazione!";
        msg+="\nBuon divertimento!";
        
        msg = String.format(msg, usr,code);
        
        return msg;
    }
    /**
     * getRandomInt Genera un numero pseudocasuale dati dei limiti
     * @param min Limite minimo
     * @param max Limite massimo
     * @return Numero pseudocasuale
     */
    public static int getRandomInt(int min,int max)
    {
        return (int) (Math.random() * ((max - min) + 1)) + min; 
    }  
    /**
     * changePasswordEmailBody Formatta una stringa di recupero della password
     * @param usr Nome utente
     * @param code Nuova password
     * @return Stringa di recupero formattata
     */
    public static String changePasswordEmailBody(String usr,String code)
    {
        String msg = "Ciao %s, bentornato su Il Paroliere-LabB!";
        msg+="\nLa tua nuova password è :  %s";
        msg = String.format(msg, usr,code);
        
        return msg;
    }
}
