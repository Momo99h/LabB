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
    private Boolean _dataChanged = false;
    public void createGame(int roomId,int gameId)
    {
        Game g = new Game(roomId,gameId);
        g.setPhase(Game.Phase.Ready);
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
    public boolean hasRoomGame(int roomId)
    {
        for(int i = 0; i < gamesCount();i++)
        {
            if(this._games.get(i).getRoomID() == roomId) return true;
        }
        return false;
    }
    public void deleteAllGames()
    {
        _games.clear();
    }
    public void deleteGame(int roomId)
    {
        for(int i = 0; i < gamesCount();i++)
        {
            if(this._games.get(i).getRoomID() == roomId)
                _games.remove(i);
        }
    }
    public Game getCurrentRoomGame(int roomId)
    {
        Game g;
        for(int i = 0; i < _games.size();i++)
        {
            g = _games.get(i);
            if(g.getPhase() != Game.Phase.Finished && g.getRoomID() == roomId)
            {
                return g;
            }
        }
        return null;
    }
    public void addScoreToPlayer(int roomId,int score,String username)
    {
        Game g = getCurrentRoomGame(roomId);
        if(g == null) return;
        g.addScoreToPlayer(username, score);
    }
    public void setDataChanged()
    {
        this._dataChanged = true;
    }
    public void confirmDataChanged()
    {
        this._dataChanged = false;
    }
    public Boolean getDataChanged()
    {
        return this._dataChanged;
    }
    
}
