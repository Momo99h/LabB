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
 * Classe usata in RMI, rappresenta una stanza di gioco
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
    private boolean _dbUpdated = false;
    private ArrayList<User> _users = new ArrayList<>();
    private SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    /**
     * Costruttore, imposta automaticamente la data della stanza alla data attuale del calcolatore
     */
    public Room()
    {
        this._date = new Date();
    }
    /**
     * Imposta l'identificativo della stanza
     * @param value Valore da impostare
     */
    public void setId(int value){this._id = value;}
    /**
     * Imposta l'amministratore della stanza
     * @param value Valore da impostare
     */
    public void setAdmin(String value){this._adminUsername = value;}
    /**
     * Imposta il numero di giocatori necessari per giocare
     * @param value Valore da impostare
     */
    public void setPlayersNeeded(int value){this._playersNeeded = value;}
    /**
     * Imposta il nome della stanza
     * @param value Valore da impostare
     */
    public void setRoomName(String value){this._roomName = value;}
    /**
     * Ritorna lo stato di aggiornamento della stanza verso il database
     * @return true se la stanza è stata già aggiornata
     */
    public boolean getDbUpdateStatus(){return this._dbUpdated;}
    /**
     * Aggiunge un giocatore alla stanza
     * @param player Oggetto che rappresenta un giocatore
     */
    public void addPlayer(User player)
    {
        this._users.add(player); 
        this._playersIn++;
    }
    /**
     * Rimuove un giocatore dalla stanza
     * @param player Oggetto che rappresenta un giocatore
     */
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
    /**
     * Modifica lo stato di un giocatore all'interno della stanza
     * @param player Oggeto che rappresenta il giocatore
     * @param status Enumerativo di stato
     */
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
    /**
     * Ottiene l'identificativo della stanza
     * @return L'identificativo della stanza
     */
    public int getId(){return this._id;}
    /**
     * Ottine l'amministratore della stanza
     * @return Il nome dell'amministratore della stanza
     */
    public String getAdmin(){return this._adminUsername;}
    /**
     * Ottine il numero di giocatori necessari della stanza
     * @return Numero di giocatori
     */
    public int getPlayersNeeded(){return this._playersNeeded;}
    /**
     * Ottine il numero di giocatori all'interno della stanza
     * @return Numero di giocatori
     */
    public int getPlayersIn(){return this._playersIn;}
    /**
     * Ottine la stringa che rappresenta la data di creazione formattata
     * @return Stringa della data
     */
    public String getCreationDate(){return this._sdf.format(_date);}
    /**
     * Ottiene il nome della stanza
     * @return Nome della stanza
     */
    public String getRoomName(){return this._roomName;}
    /**
     * Ritorna il prossimo identificativo disponibile per una fase di gioco
     * @return Identificativo della fase di gioco + 1
     */
    public int nextGameId()
    {
        this._lastGameId++;
        return this._lastGameId;
    }
    /**
     * Controlla se un giocatore è all'interno della stanza
     * @param player Oggetto che rappresenta un giocatore
     * @return  true se all'interno della stanza
     */
    public Boolean isPlayerIn(User player)
    {
        for(int i = 0; i < this._users.size();i++)
        {
            if(_users.get(i).getUsername().equals(player.getUsername())) return true;
        }
        return false;
    }
    /**
     * Ottiene la lista di giocatori all'interno della stanza
     * @return Array di oggetti che rappresentano i giocatori all'interno di una stanza
     */
    public User[] getListPlayerIn()
    {
        User[] s = new User[this._users.size()];
        for(int i = 0; i < this._users.size();i++)
        {
            s[i] = this._users.get(i);
        }
        return s;
    }
    /**
     * Ritorna una stringa con i nomi dei giocatori suddivisi da un separatore all'interno di una stanza
     * @param separator separatore
     * @return Stringa con separatore
     */
    public String getListPlayerIn(String separator)
    {
        User[] user = getListPlayerIn();
        String users = "";
        for(User s : user)
            users += s.getUsername() + separator;
        return users;
    }
    /**
     * Imposta lo stato di aggiornamento al database al valore = true
     */
    public void setDbUpdateStatus()
    {
        this._dbUpdated = true; 
    }
    
}
