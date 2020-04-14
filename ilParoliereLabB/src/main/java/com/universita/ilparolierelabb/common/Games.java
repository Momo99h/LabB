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
public class Games implements Serializable
{
    private ArrayList<Game> _games = new ArrayList<>();
    
    public void createGame(int roomId)
    {
        Game g = new Game(roomId);
        g.setPhase(Game.Phase.InitCountDown);
        this._games.add(g);
    }
    public Game[] getGamesArray()
    {
        Game[] arr = new Game[gamesCount()];
        for(int i = 0; i < gamesCount(); i++)
        {
            arr[i] = this._games.get(i);
        }
        return arr;
    }
    public int gamesCount()
    {
        return this._games.size();
    }
    
}
