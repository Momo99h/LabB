/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;
import com.universita.ilparolierelabb.common.sql.*;

/**
 *
 * @author Momo
 */
public class ClientDBInterface 
{
    private static MySQLEngine _db = new MySQLEngine();
    
    public static void setDBReference(MySQLEngine db)
    {
        _db = db;
    }
    
}
