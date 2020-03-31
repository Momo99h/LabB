/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.sql.PostgreSQLEngine;

/**
 *
 * @author Momo
 */
public class ServerDBInterface 
{
    private static PostgreSQLEngine _db = new PostgreSQLEngine();
    
    public static void setDBReference(PostgreSQLEngine db)
    {
        _db = db;
    }
    
}
