/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import java.io.Serializable;

/**
 *
 * @author Momo
 */
public class Game implements Serializable
{
    public static enum Phase
    {
        Idle,Ready,InitCountDown
    }
    private int _roomid;
    private int _gameid = 1;
    private int _initTimer = 30;
    private int _gameTimer = 180;
    private Phase _phase = Phase.Idle;
    public Game(int roomId){this._roomid = roomId;}
    
    public void setID(int value){this._gameid = value;}
    public void decrementInitTimer() {this._initTimer--;}
    public int getInitTimer()
    {
        return this._initTimer;
    }
    public Phase getPhase()
    {
        return this._phase;
    }
    public void setPhase(Phase value)
    {
       this._phase = value;
    }
   
    public int getID(){return this._gameid;}
    public int getRoomID(){return this._roomid;}
    
}