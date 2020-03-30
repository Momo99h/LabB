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
public class SQLConnectionParameters 
{
    private String _ip;
    private String _db;
    private String _usr;
    private String _pwd;
    
    public SQLConnectionParameters(){}
    
    public void setIP(String val) {this._ip = val;}
    public void setDBName(String val) {this._db = val;}
    public void setUsername(String val) {this._usr = val;}
    public void setPassword(String val) {this._pwd = val;}
    
    public String getIP() {return this._ip;}
    public String getDBName() {return this._db;}
    public String getUsername() {return this._usr;}
    public String getPassword() {return this._pwd;}
    
    
}
