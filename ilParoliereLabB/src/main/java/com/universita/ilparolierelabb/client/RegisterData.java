/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import java.io.Serializable;

/**
 * RegisterData Utilizzata dalla RMI, Classe che rappresenta un utente da registrare al sistema
 * @author Momo
 */
public class RegisterData implements Serializable
{
    private static final long serialVersionUID = 1L;
    
    private String _name;
    private String _surname;
    private String _usr;
    private String _psw;
    private String _email;
    private String _verificationCode;
    private int _remainingTime;
    private Boolean _emailSent = false;
    
    /**
     * RegisterData Costruttore vuoto
     */
    public RegisterData(){}
    
    /**
     * getName Ottiene il nome del giocatore
     * @return 
     */
    public String getName(){return this._name;}
    /**
     * getSurname Ottiene il cognome del giocatore
     * @return 
     */
    public String getSurname(){return this._surname;}
    /**
     * getUsername Ottiene lo username del giocatore
     * @return 
     */
    public String getUsername(){return this._usr;}
    /**
     * getPassword Ottiene la password del giocatore
     * @return 
     */
    public String getPassword(){return this._psw;}
    /**
     * getEmail Ottiene l'email del giocatore
     * @return 
     */
    public String getEmail(){return this._email;}
    /**
     * getVerificationCode Ottiene il codice di verifica del giocatore
     * @return 
     */
    public String getVerificationCode(){return this._verificationCode;}
    /**
     * getRemainingTime Ottiene il tempo rimanente del codice di verifica del giocatore
     * @return 
     */
    public int getRemainingTime(){return this._remainingTime;}
    /**
     * getEmailStatus Ottiene lo stato di invio della mail al giocatore
     * @return 
     */
    public Boolean getEmailStatus(){return this._emailSent;}
    
    /**
     * setName Imposta il nome del giocatore
     * @param val Valore da impostare
     */
    public void setName(String val) {this._name = val;}
    /**
     * setSurname Imposta il cognome del giocatore
     * @param val Valore da impostare
     */
    public void setSurname(String val) {this._surname = val;}
    /**
     * setUsername Imposta lo username del giocatore
     * @param val Valore da impostare
     */
    public void setUsername(String val) {this._usr = val;}
    /**
     * setPassword Imposta la password del giocatore
     * @param val Valore da impostare
     */
    public void setPassword(String val) {this._psw = val;}
    /**
     * setEmail Imposta l'email del giocatore
     * @param val Valore da impostare
     */
    public void setEmail(String val) {this._email = val;}
    /**
     * setVerificationCode Imposta il codice di verifica del giocatore
     * @param val Valore da impostare
     */
    public void setVerificationCode(String val) {this._verificationCode = val;}
    /**
     * setRemainingTime Imposta il tempo rimanente per la verifica del codice del giocatore
     * @param val Valore da impostare
     */
    public void setRemainingTime(int val) {this._remainingTime = val;}
    /**
     * setEmailStatus Imposta lo stato di invio della mail al giocatore
     * @param val Valore da impostare
     */
    public void setEmailStatus(Boolean val) {this._emailSent = val;}
    
}
