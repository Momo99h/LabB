/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Momo
 */
public class Game implements Serializable
{

    public static enum Phase
    {
        Idle,Ready,InitCountDown,CreateMatrix,GameCountDown,Finished,Conclude
    }
    private int _roomid;
    private int _gameid = 1;
    private int _initTimer = 30;
    private int _gameTimer = 180;
    private ArrayList<User> _Initialusers = new ArrayList<>();
    private ArrayList<User> _Endusers = new ArrayList<>();
    private Phase _phase = Phase.Idle;
    private String[][] _matrix;
    
    public Game(int roomId,int gameId)
    {
        this._roomid = roomId;
        this._gameid = gameId;
    }
    
    public void setID(int value){this._gameid = value;}
    public void decrementInitTimer() {this._initTimer--;}
    public void decrementGameTimer() {this._gameTimer--;}
    public int getInitTimer()
    {
        return this._initTimer;
    }
     public int getGameTimer()
    {
        return this._gameTimer;
    }
    public Phase getPhase()
    {
        return this._phase;
    }
    public void setPhase(Phase value)
    {
       this._phase = value;
    }
    public String[][] getMatrix()
    {
        return this._matrix;
    }
    public void setMatrix(String[][] value)
    {
       this._matrix = value;
    }
    public void addUser(User usr)
    {
       this._Initialusers.add(usr);
       this._Endusers.add(usr);
    }
    public void addScoreToPlayer(String username,int score)
    {
        for(int i = 0; i < this._Initialusers.size();i++)
        {
            if(this._Initialusers.get(i).getUsername().equals(username))
            {
                this._Initialusers.get(i).setGameTotalPoints(score + this._Initialusers.get(i).getGamePoints());
                return;
            }
        }
    }
    public int getBestGameScore()
    {
        int max = 0;
        User usr;
        for(int i = 0; i < this._Initialusers.size();i++)
        {
            usr = this._Initialusers.get(i);
            if(usr.getGamePoints() > max) max = usr.getGamePoints();
        }
        return max;
    }
    public String getBestGameScoreUsername()
    {
        int max = 0;
        User usr;
        User usrBest = null;
        for(int i = 0; i < this._Initialusers.size();i++)
        {
            usr = this._Initialusers.get(i);
            if(usr.getGamePoints() > max)
            {
                max = usr.getGamePoints();
                usrBest = usr;
            }
        }
        return usrBest.getUsername();
    }
     public void resetPlayersReady() 
    {
        for(int i = 0; i < this._Initialusers.size();i++)
        {
            this._Initialusers.get(i).setStatus(UserStatus.NotReady); 
        }
    }
    public int getID(){return this._gameid;}
    public int getRoomID(){return this._roomid;}
    public User[] getListInitialPlayerIn()
    {
        User[] s = new User[this._Initialusers.size()];
        for(int i = 0; i < this._Initialusers.size();i++)
        {
            s[i] = this._Initialusers.get(i);
        }
        return s;
    }
    public User[] getListEndPlayersIn()
    {
        User[] s = new User[this._Endusers.size()];
        for(int i = 0; i < this._Endusers.size();i++)
        {
            s[i] = this._Endusers.get(i);
        }
        return s;
    }
    public String getListInitialPlayersIn(String separator)
    {
        User[] user = getListInitialPlayerIn();
        String users = "";
        for(User s : user)
            users += s.getUsername() + separator;
        return users;
    }
    public String getListEndPlayersIn(String separator)
    {
        User[] user = getListEndPlayersIn();
        String users = "";
        for(User s : user)
            users += s.getUsername() + separator;
        return users;
    }
    
    public int getPlayersIn()
    {
        return this._Initialusers.size();
    }
    
    public boolean isPlayerIn(String username) 
    {
       for(int i = 0; i < _Initialusers.size(); i++)
       {
           if(_Initialusers.get(i).getUsername().equals(username))
           {
               return true;
           }
       }
       return false;
    }
    public void removePlayer(String username) 
    {
       for(int i = 0; i < _Endusers.size(); i++)
       {
           if(_Endusers.get(i).getUsername().equals(username))
           {
               _Endusers.remove(i);
           }
       }
    }
    
}
