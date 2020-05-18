/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import com.universita.ilparolierelabb.common.UserStatus;
import java.io.Serializable;

/**
 *
 * @author Momo
 */
public class User implements Serializable
{
    private String _username;
    private int _totalPoints = 0;
    private int _gametotalPoints = 0;
    private UserStatus _status = UserStatus.Offline;
    public User(){}
    
    public String getUsername(){return this._username;}
    public UserStatus getStatus(){return this._status;}
    public int getTotalPoints(){return this._totalPoints;}
    public int getGameTotalPoints(){return this._gametotalPoints;}
    
    public void setUsername(String   val){this._username = val;}
    public void setStatus(UserStatus val){this._status = val;}
    public void setTotalPoints(int val){this._totalPoints = val;}
    public void setGameTotalPoints(int val){this._gametotalPoints = val;}

    
}
