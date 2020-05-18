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
        Idle,Ready,InitCountDown,CreateMatrix,GameCountDown,Finished
    }
    private int _roomid;
    private int _gameid = 1;
    private int _initTimer = 30;
    private int _gameTimer = 180;
    private ArrayList<User> _users = new ArrayList<>();
    private Phase _phase = Phase.Idle;
    private String[][] _matrix;
    
    public Game(int roomId){this._roomid = roomId;}
    
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
       this._users.add(usr);
    }
   
   
    public int getID(){return this._gameid;}
    public int getRoomID(){return this._roomid;}
    
}
