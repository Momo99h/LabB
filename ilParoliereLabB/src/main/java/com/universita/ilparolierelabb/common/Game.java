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
    private int _gameid;
    private int _initTimer = 30;
    private int _gameTimer = 180;
    private Phase _phase = Phase.Idle;
    public Game(){}
    
    public void setID(int value){this._gameid = value;}
    public void decrementInitTimer() {this._initTimer--;}
    public int getID(){return this._gameid;}
    
}
