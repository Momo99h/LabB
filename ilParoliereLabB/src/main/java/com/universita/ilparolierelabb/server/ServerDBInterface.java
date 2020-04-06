/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.UserStatus;
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
        Boolean b = _db.executeQuery(query);
        query =  "Insert into UsersState(Nickname,OnlineStatus,IdRoom) Values ('%s','%s','%s')";
        query = String.format(query,d.getUsername(),UserStatus.Offline.getValue(),0);
        return b && _db.executeQuery(query);
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
        Boolean b = !return_val[0][0].equals("0");
        query = "Update UsersState set OnlineStatus = '%s' where Nickname = '%s'";
        query = String.format(query,UserStatus.Online.getValue(),usr);
        return b && _db.executeQuery(query);
    }
    public static boolean ClientDisconnect(String usr)
    {
        String query = "Update UsersState set OnlineStatus = '%s' where Nickname = '%s'";
        query = String.format(query,UserStatus.Offline.getValue(),usr);
        return _db.executeQuery(query);
    }
    public static int OnlineClientsCount()
    {
        String query = "Select Count(*) from UsersState Where NOT OnlineStatus='%s'";
        query = String.format(query,0);
        String[][] return_val = _db.executeQueryRead(query);
        return Integer.parseInt(return_val[0][0]);
    }

    public static boolean clientIsLogged(String usr) 
    {
        String query = "Select Count(*) from UsersState Where NOT OnlineStatus='%s' AND Nickname ='%s'";
        query = String.format(query,0,usr);
        String[][] return_val = _db.executeQueryRead(query);
        return !return_val[0][0].equals("0");
    }

    public static void resetUsersState()
    {
        String query = "Update UsersState set OnlineStatus='%s'";
        query = String.format(query,0);
        _db.executeQuery(query);
    }            
}
