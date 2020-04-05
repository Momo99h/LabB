/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import java.io.Serializable;

/**
 *
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
    
    public RegisterData(){}
    
    public String getName(){return this._name;}
    public String getSurname(){return this._surname;}
    public String getUsername(){return this._usr;}
    public String getPassword(){return this._psw;}
    public String getEmail(){return this._email;}
    public String getVerificationCode(){return this._verificationCode;}
    public int getRemainingTime(){return this._remainingTime;}
    public Boolean getEmailStatus(){return this._emailSent;}
    
    public void setName(String val) {this._name = val;}
    public void setSurname(String val) {this._surname = val;}
    public void setUsername(String val) {this._usr = val;}
    public void setPassword(String val) {this._psw = val;}
    public void setEmail(String val) {this._email = val;}
    public void setVerificationCode(String val) {this._verificationCode = val;}
    public void setRemainingTime(int val) {this._remainingTime = val;}
    public void setEmailStatus(Boolean val) {this._emailSent = val;}
    
}
