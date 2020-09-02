/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe utilizzata nell'RMI che rappresenta la lista di tutte le fasi di gioco presenti
 * @author Momo
 */
public class Games implements Serializable
{
    private ArrayList<Game> _games = new ArrayList<>();
    private Boolean _dataChanged = false;
    
    /**
     * Crea una fase di gioco
     * @param roomId Identificativo della stanza
     * @param gameId Identificativo della fase
     */
    public void createGame(int roomId,int gameId)
    {
        Game g = new Game(roomId,gameId);
        g.setPhase(Game.Phase.Ready);
        this._games.add(g);
    }
    /**
     * Ritorna la lista di tutte le fase di gioco come array
     * @return Array di oggetti Game
     */
    public Game[] getGamesArray()
    {
        Game[] arr = new Game[gamesCount()];
        for(int i = 0; i < gamesCount(); i++)
        {
            arr[i] = this._games.get(i);
        }
        return arr;
    }
    /**
     * Ritorna il coteggio delle fasi di gioco presenti
     * @return Il numero delle fasi di gioco presenti
     */
    public int gamesCount()
    {
        return this._games.size();
    }
    /**
     * Ritorna se una stanza ha una fase di gioco
     * @param roomId Identificativo della stanza
     * @return true se la stanza ha una fase di gioco
     */
    public boolean hasRoomGame(int roomId)
    {
        for(int i = 0; i < gamesCount();i++)
        {
            if(this._games.get(i).getRoomID() == roomId) return true;
        }
        return false;
    }
    /**
     * Cancella tutte le fasi di gioco
     */
    public void deleteAllGames()
    {
        _games.clear();
    }
    /**
     * Cancella le fasi di gioco di una stanza
     * @param roomId Identificativo stanza
     */
    public void deleteGame(int roomId)
    {
        for(int i = 0; i < gamesCount();i++)
        {
            if(this._games.get(i).getRoomID() == roomId)
                _games.remove(i);
        }
    }
    /**
     * Ritorna la fase di gioco attuale di una stanza
     * @param roomId Identificativo della stanza
     * @return Oggetto Game
     */
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
    /**
     * Aggiunge un punteggio a un giocatore presente in una stanza, automaticamente riconosce dove il giocatore si trova nella fase di gioco
     * @param roomId Identificativo stanza
     * @param score Punteggio
     * @param username Nome giocatore
     */
    public void addScoreToPlayer(int roomId,int score,String username)
    {
        Game g = getCurrentRoomGame(roomId);
        if(g == null) return;
        g.addScoreToPlayer(username, score);
    }
    /**
     * Imposta che la lista di fasi di gioco ha avuto dei cambiamenti
     */
    public void setDataChanged()
    {
        this._dataChanged = true;
    }
    /**
     * Conferma l'handling dei cambiamenti della fase di gioco
     */
    public void confirmDataChanged()
    {
        this._dataChanged = false;
    }
    /**
     * Ottiene lo stato dei cambiamenti della lista di fasi di gioco
     * @return true se la lista ha avuto dei cambiamenti
     */
    public Boolean getDataChanged()
    {
        return this._dataChanged;
    }
    /**
     * Rimuove un giocatore dalla fase di gioco in cui si trova
     * @param usr Oggetto che rappresenta il giocatore
     */
    public void removePlayerFromGame(User usr) 
    {
        Game g = null;
        for(int i = 0; i < _games.size(); i++)
        {
            g = _games.get(i);
            if(g.isPlayerIn(usr.getUsername())) 
            {
                g.removePlayer(usr.getUsername());
            }
        }
    }
    
}
