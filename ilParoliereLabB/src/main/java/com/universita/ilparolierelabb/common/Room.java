/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

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
    private Date _date;
    private String _adminUsername;
    private String _roomName;
    private int _playersNeeded;
    private int _playersIn = 0;
    private int _lastGameId = 0;
    
    private ArrayList<User> _users = new ArrayList<>();
    private SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public Room()
    {
        this._date = new Date();
    }
    
    public void setId(int value){this._id = value;}
    public void setAdmin(String value){this._adminUsername = value;}
    public void setPlayersNeeded(int value){this._playersNeeded = value;}
    public void setRoomName(String value){this._roomName = value;}
    public void addPlayer(User player)
    {
        this._users.add(player); 
        this._playersIn++;
    }
    public void removePlayer(User player)
    {
        for(int i = 0; i < this._users.size(); i++)
        {
            if(player.getUsername().equals(this._users.get(i).getUsername()))
            {
               this._users.remove(i); 
               break;
            }
        }
        this._playersIn--;
    }
    public void changePlayerStatus(User player,UserStatus status)
    {
        for(int i = 0; i < this._users.size(); i++)
        {
            if(player.getUsername().equals(this._users.get(i).getUsername()))
            {
               this._users.get(i).setStatus(status);
               break;
            }
        }
    }
    public int getId(){return this._id;}
    public String getAdmin(){return this._adminUsername;}
    public int getPlayersNeeded(){return this._playersNeeded;}
    public int getPlayersIn(){return this._playersIn;}
    public String getCreationDate(){return this._sdf.format(_date);}
    public String getRoomName(){return this._roomName;}
    public int nextGameId()
    {
        this._lastGameId++;
        return this._lastGameId;
    }
    public Boolean isPlayerIn(User player)
    {
        for(int i = 0; i < this._users.size();i++)
        {
            if(_users.get(i).getUsername().equals(player.getUsername())) return true;
        }
        return false;
    }
    public User[] getListPlayerIn()
    {
        User[] s = new User[this._users.size()];
        for(int i = 0; i < this._users.size();i++)
        {
            s[i] = this._users.get(i);
        }
        return s;
    }
}
