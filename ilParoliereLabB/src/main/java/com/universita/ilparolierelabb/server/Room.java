/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Momo
 */
public class Room implements Serializable
{
    private int _id;
    private Date _dataCreazione;
    private String _adminUsername;
    private int _playersNeeded;
    private int _playersIn;
    private ArrayList<String> _users = new ArrayList<>();
    private SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public Room()
    {
        this._dataCreazione = new Date();
    }
    
    public void setId(int value){this._id = value;}
    public void setAdmin(String value){this._adminUsername = value;}
    public void setPlayersNeeded(int value){this._playersNeeded = value;}
    public void setPlayersIn(int value){this._playersIn = value;}
    public void addPlayer(String value){this._users.add(value);}
    public void removePlayer(String value){this._users.remove(value);}
    
    public int getId(){return this._id;}
    public String getAdmin(){return this._adminUsername;}
    public int getPlayersNeeded(){return this._playersNeeded;}
    public int getPlayersIn(){return this._playersIn;}
    public String getCreationDate(){return this._sdf.format(_dataCreazione);}
    public Boolean isPlayerIn(String player){return this._users.contains(player);}
}
