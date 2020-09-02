/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common.sql;

/**
 * Rappresenta i parametri di connessione verso un database
 * @author Momo
 */
public class SQLConnectionParameters 
{
    private String _ip;
    private String _db;
    private String _usr;
    private String _pwd;
    
    /**
     * Costruttore vuoto
     */
    public SQLConnectionParameters(){}
    
    /**
     * Imposta l'indirizzo ip
     * @param val Valore da impostare
     */
    public void setIP(String val) {this._ip = val;}
    /**
     * Imposta il nome del database
     * @param val Valore da impostare
     */
    public void setDBName(String val) {this._db = val;}
    /**
     * Imposta lo username di accesso
     * @param val Valore da impostare 
     */
    public void setUsername(String val) {this._usr = val;}
    /**
     * Imposta la password di accesso
     * @param val Valore da impostare 
     */
    public void setPassword(String val) {this._pwd = val;}
    
    /**
     * Ritorna l'ip
     * @return Ip
     */
    public String getIP() {return this._ip;}
    /**
     * Ritorna il nome del database
     * @return Nome database
     */
    public String getDBName() {return this._db;}
    /**
     * Ritorna lo username di accesso
     * @return Username di accesso
     */
    public String getUsername() {return this._usr;}
    /**
     * Ritorna la password di accesso
     * @return Password di accesso
     */
    public String getPassword() {return this._pwd;}
    
    
}
