/**
 * 
 * Progetto laboratorio B
 * 
 * Mohamed Hussein,   737787
 * Anrea Girola,      740724
 * Vanessa Squillace, 728078
 * Simone Spagnolo,   737742
 * 
 */
package com.universita.ilparolierelabb.common;

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
     * Ottiene il nome del giocatore
     * @return 
     */
    public String getName(){return this._name;}
    /**
     *  Ottiene il cognome del giocatore
     * @return 
     */
    public String getSurname(){return this._surname;}
    /**
     *  Ottiene lo username del giocatore
     * @return 
     */
    public String getUsername(){return this._usr;}
    /**
     *  Ottiene la password del giocatore
     * @return 
     */
    public String getPassword(){return this._psw;}
    /**
     *  Ottiene l'email del giocatore
     * @return 
     */
    public String getEmail(){return this._email;}
    /**
     *  Ottiene il codice di verifica del giocatore
     * @return 
     */
    public String getVerificationCode(){return this._verificationCode;}
    /**
     *  Ottiene il tempo rimanente del codice di verifica del giocatore
     * @return 
     */
    public int getRemainingTime(){return this._remainingTime;}
    /**
     *  Ottiene lo stato di invio della mail al giocatore
     * @return 
     */
    public Boolean getEmailStatus(){return this._emailSent;}
    
    /**
     *  Imposta il nome del giocatore
     * @param val Valore da impostare
     */
    public void setName(String val) {this._name = val;}
    /**
     *  Imposta il cognome del giocatore
     * @param val Valore da impostare
     */
    public void setSurname(String val) {this._surname = val;}
    /**
     *  Imposta lo username del giocatore
     * @param val Valore da impostare
     */
    public void setUsername(String val) {this._usr = val;}
    /**
     *  Imposta la password del giocatore
     * @param val Valore da impostare
     */
    public void setPassword(String val) {this._psw = val;}
    /**
     *  Imposta l'email del giocatore
     * @param val Valore da impostare
     */
    public void setEmail(String val) {this._email = val;}
    /**
     *  Imposta il codice di verifica del giocatore
     * @param val Valore da impostare
     */
    public void setVerificationCode(String val) {this._verificationCode = val;}
    /**
     *  Imposta il tempo rimanente per la verifica del codice del giocatore
     * @param val Valore da impostare
     */
    public void setRemainingTime(int val) {this._remainingTime = val;}
    /**
     *  Imposta lo stato di invio della mail al giocatore
     * @param val Valore da impostare
     */
    public void setEmailStatus(Boolean val) {this._emailSent = val;}
    
}
