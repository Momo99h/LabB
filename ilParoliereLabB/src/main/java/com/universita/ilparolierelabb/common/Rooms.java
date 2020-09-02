/**
 * 
 * Progetto laboratorio B
 * 
 * Mohamed Hussein,   737787
 * Anrea Girola,      740724
 * Vanessa Squillace, 728078
 * Simone Spagnolo,   737742
 * 
 */
package com.universita.ilparolierelabb.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe usata in RMI, rappresenta la lista di tutte le stanza disponibili
 * @author Momo
 */
public class Rooms implements Serializable
{
    private ArrayList<Room> _rooms = new ArrayList<>();
    private boolean _dataChanged = false;
    private int _lastID = 0;
    /**
     * Costruttore vuoto
     */
    public Rooms(){}
    /**
     * Ottiene la lista di tutte le stanza
     * @return ArrayList di oggetti Room
     */
    public ArrayList<Room> getAllRoomsData()
    {
        return this._rooms;
    }
    /**
     * Imposta la lista di tutte le stanze
     * @param rooms ArrayList di oggetti Room
     */
    public void setAllRoomsData(ArrayList<Room> rooms)
    {
        this._rooms = rooms;
    }
    /**
     * Ottiene il nuemro di stanze presenti
     * @return Numero di stanze
     */
    public int getRoomsCount()
    {
        return this._rooms.size();
    }
    /**
     * Ottine un stanza dalla lista 
     * @param id Identificativo della stanza
     * @return Oggetto Room con quell'identificativo
     */
    public Room getRoom(int id)
    {
        for(Room r : _rooms)
        {
            if(r.getId() == id) return r;
        }
        return null;
    }
    /**
     * Ottiene una stanza dalla lista
     * @param position Posizione nella lista
     * @return Oggetto Room che si trova nella posizione passata come parametro nella lista
     */
    public Room getRoomObject(int position)
    {
        return this._rooms.get(position);
    }
    /**
     * Aggiunge una stanza alla lista
     * @param r Oggetto Room da aggiungere
     */
    public void addRoom(Room r)
    {
        this._rooms.add(r);
        if(r.getId() > _lastID) _lastID = r.getId();
    }
    /**
     * Rimuove una stanza dalla lista
     * @param r Oggetto Room da rimuovere
     */
    public void removeRoom(Room r)
    {
        this._rooms.remove(r);
    }
    /**
     * Imposta che la lista ha avuto dei cambiamenti
     * @param value Valore da impostare
     */
    public void setDataChanged(Boolean value)
    {
        this._dataChanged = value;
    }
    /**
     * Ritorna se la lista ha dei cambiamenti
     * @return true se la lista ha avuto die cambiamenti
     */
    public Boolean isDataChanged()
    {
        return this._dataChanged;
    }
    /**
     * Conferma l'handling dei cambiamenti della lista
     */
    public void confirmDataChanged()
    {
        this._dataChanged = false;
    }
    /**
     * Ottiene l'ultimo identificativo della stanza
     * @return Ultimo identificativo della stanza
     */
    public int getLastID()
    {
        return this._lastID;
    }
    /**
     * Imposta l'ultimo identificativo della stanza
     * @param value Valore da impostare
     */
    public void setLastID(int value)
    {
        this._lastID = value;
    }
    /**
     * Controlla se un utente è presente in una stanza
     * @param player Oggetto che rappresenta l'utente
     * @return true se l'utente è in un stanza
     */
    public boolean isPlayerInAnyRoom(User player)
    {
        for(int i = 0; i < _rooms.size(); i++)
        {
            if(getRoomObject(i).isPlayerIn(player)) return true;
        }
        return false;
    }
    /**
     * Ritorna la Room dove un utente è presente
     * @param player Oggeto che rappresenta l'utente
     * @return Oggetto che rappresenta la stanza
     */
    public Room getRoomWherePlayer(User player)
    {
        Room r = null;
        for(int i = 0; i < _rooms.size(); i++)
        {
            r = getRoomObject(i);
            if(r.isPlayerIn(player)) return r;
        }
        return null;
    }
    /**
     * Rimuove un utente dalla stanza in cui si trova
     * @param player Oggetto che rappresenta l'utente
     * @return true se il giocatore è stato rimosso
     */
    public boolean removePlayerFromRoom(User player)
    {
        Room r = null;
        for(int i = 0; i < _rooms.size(); i++)
        {
            r = getRoomObject(i);
            if(r.isPlayerIn(player)) 
            {
                r.removePlayer(player);
                return true;
            }
        }
        return false;
    }
    /**
     * Controlla se la stanza è pronta per iniziare le fasi di gioco
     * @param roomId Identificativo della stanza
     * @return true se la stanza è pronta
     */
    public boolean isRoomReadyToPlay(int roomId)
    {
        Room r = getRoom(roomId);
        if (r.getPlayersIn() != r.getPlayersNeeded()) return false;
        User[] players = r.getListPlayerIn();
        for(int i = 0; i < players.length;i++)
        {
            if(players[i].getStatus() != UserStatus.Ready) return false;
        }
        return true;
    }
    /**
     * Genera il pacchetto LobbyData dalla lista delle stanze
     * @return Oggetto che rappresenta i dati della lobby
     */
    public LobbyData createLobbyData()
    {
        LobbyData d = new LobbyData();
        Room r;
        String s = "";
        for(int i = 0; i < getRoomsCount(); i++)
        {
            r = getRoomObject(i);
            d.addRoomData(r.getId()+";"+r.getRoomName()+";"+r.getCreationDate()+";"+r.getPlayersIn()+"/"+r.getPlayersNeeded());
            User[] usr = r.getListPlayerIn();
            for(int j = 0; j < usr.length; j++)
            {
                s += usr[j].getUsername()+";";
            }
            d.addUsersData(s);
        }
        return d;
    }
    /**
     * Ritorna la lista di giocatori all'interno di una stanza
     * @param roomId Identificativo della stanza
     * @return Array di oggetti User
     */
    public User[] getListPlayersInRoom(int roomId)
    {
        return getRoom(roomId).getListPlayerIn();
    }
}
