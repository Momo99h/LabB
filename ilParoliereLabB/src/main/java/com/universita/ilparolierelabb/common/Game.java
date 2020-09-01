/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Rappresenta la fase di gioco, Utilizzata dall'RMI
 * @author Momo
 */
public class Game implements Serializable
{

    /**
     * Enumerazione che rappresenza lo stato della fase di gioco
     */
    public static enum Phase
    {
        Idle,
        /**
         * Inattivo
         */
        Ready,
        /**
         * Giocatori tutti pronti
         */
        InitCountDown,
        /**
         * Countdown iniziale
         */
        CreateMatrix,
        /**
         * Creazione matrice
         */
        GameCountDown,
        /**
         * Countdown della fase
         */
        Finished,
        /**
         * Fine
         */
        Conclude
        /**
         * Conclusione, usata per eseguire azioni dopo che la fase è finita
         */
    }
    private int _roomid;
    private int _gameid = 1;
    private int _initTimer = 30;
    private int _gameTimer = 180;
    private ArrayList<User> _Initialusers = new ArrayList<>();
    private ArrayList<User> _Endusers = new ArrayList<>();
    private Phase _phase = Phase.Idle;
    private String[][] _matrix;
    
    /**
     * Costruttore
     * @param roomId Identificativo della stanza a cui associare la fase
     * @param gameId Identificativo della fase
     */
    public Game(int roomId,int gameId)
    {
        this._roomid = roomId;
        this._gameid = gameId;
    }
    
    /**
     * Imposta identificativo
     * @param value Valore da impostare
     */
    public void setID(int value){this._gameid = value;}
    /**
     * Decrementa timer iniziale
     */
    public void decrementInitTimer() {this._initTimer--;}
    /**
     * Decrementa timer di gioco
     */
    public void decrementGameTimer() {this._gameTimer--;}
    /**
     * Ottiene il valore del timer iniziale
     * @return valore del timer iniziale
     */
    public int getInitTimer()
    {
        return this._initTimer;
    }
    /**
     * Ottiene il valore del timer di gioco
     * @return valore del timer di gioco
     */
     public int getGameTimer()
    {
        return this._gameTimer;
    }
    /**
     * Ottiene lo stato
     * @return lo stato della fase di gioco
     */
    public Phase getPhase()
    {
        return this._phase;
    }
    /**
     * Imposta lo stato della fase di gioco
     * @param value Valore da impostare
     */
    public void setPhase(Phase value)
    {
       this._phase = value;
    }
    /**
     * Ottiene la matrice usata dalla fase di gioco
     * @return la matrice della fase di gioco
     */
    public String[][] getMatrix()
    {
        return this._matrix;
    }
    /**
     * Imposta la matrice della fase di gioco
     * @param value La matrice di gioco
     */
    public void setMatrix(String[][] value)
    {
       this._matrix = value;
    }
    /**
     * Aggiunge un utente alla fase di gioco
     * @param usr Oggetto che rappresenta l'utente da aggiungere
     */
    public void addUser(User usr)
    {
       this._Initialusers.add(usr);
       this._Endusers.add(usr);
    }
    /**
     * Aggiunge punti ad un utente presente nella fase di gioco
     * @param username Nome utente
     * @param score Punteggio da aggiungere
     */
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
    /**
     * Ottiene il massimo punteggio della fase di gioco
     * @return Massimo punteggio della fase di gioco
     */
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
    /**
     * Ottiene il nome del giocatore con il massimo punteggio della fase di gioco
     * @return Nome del giocatore con il massimo puneggio
     */
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
    /**
     * Imposta lo stato dei giocatori a NotReady
     */
    public void resetPlayersReady() 
    {
        for(int i = 0; i < this._Initialusers.size();i++)
        {
            this._Initialusers.get(i).setStatus(UserStatus.NotReady); 
        }
    }
    /**
     * Ritorna l'identificativo della fase di gioco
     * @return Identificativo della fase di gioco
     */
    public int getID(){return this._gameid;}
    /**
     * Ritorna l'identificativo della stanza che detiene la fase di gioco
     * @return Identificativo della stanza
     */
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
