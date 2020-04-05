/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.sql.*;

/**
 *
 * @author Momo
 */
public class ServerDBInterface 
{
    private static MySQLEngine _db = new MySQLEngine();
    
    public static void setDBReference(MySQLEngine db)
    {
        _db = db;
    }

    public static boolean HasAdmin() 
    {
        String query = "Select Count(*) from Admins";
        String[][] return_val = _db.executeQueryRead(query);
        return !return_val[0][0].equals("0");
    }
    
    public static boolean RegisterAdmin(String usr,String psw) 
    {
        String query = "Insert into Admins(Username,Password) Values ('%s','%s')";
        query = String.format(query,usr,psw);
        return _db.executeQuery(query);
    }
    public static boolean RegisterAccount(RegisterData d) 
    {
        String query = "Insert into Users(Nome,Cognome,Nickname,Email,Password) Values ('%s','%s','%s','%s','%s')";
        query = String.format(query,d.getName(),d.getSurname(),d.getUsername(),d.getEmail(),d.getPassword());
        return _db.executeQuery(query);
    }
    
    public static boolean LoginAdmin(String usr,String psw) 
    {
        String query = "Select Count(*) from Admins Where Username='%s' AND Password='%s'";
        query = String.format(query,usr,psw);
        String[][] return_val = _db.executeQueryRead(query);
        return !return_val[0][0].equals("0");
    }
    public static boolean ClientLogin(String usr,String psw)
    {
        String query = "Select Count(*) from Users Where Nickname='%s' AND Password='%s'";
        query = String.format(query,usr,psw);
        String[][] return_val = _db.executeQueryRead(query);
        return !return_val[0][0].equals("0");
    }
    
    
            
}
