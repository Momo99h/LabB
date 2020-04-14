/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common.sql;

/**
 *
 * @author Momo
 */
abstract class sqlEngine 
{
    protected abstract void setConnectionString(SQLConnectionParameters param);
    protected abstract String getConnectionString();
    protected abstract Boolean checkConnection();
    protected abstract Boolean executeQuery(String query);
    protected abstract String[][] executeQueryRead(String query);
}
