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
 * Classe utilizzata in RMI, rappresenta i dati che devono essere viusalizzati in Lobby
 * Usata per alleggerire il carico dei pacchetti
 * @author Momo
 */
public class LobbyData implements Serializable
{
    private ArrayList<String> _roomsData = new ArrayList<>();
    private ArrayList<String> _usersData = new ArrayList<>();
    
    /**
     * Costruttore vuoto
     */
    public LobbyData(){}
    
    /**
     * Aggiunge i dati di una stanza di gioco
     * @param s Stringa rappresentate i dati
     */
    public void addRoomData(String s)
    {
        this._roomsData.add(s);
    }
    /**
     * Aggiunge i dati di dei giocatori
     * @param s Stringa rappresentate i dati dei giocatori
     */
    public void addUsersData(String s)
    {
        this._usersData.add(s);
    }
    /**
     * Ottine tutti i dati delle stanza di gioco
     * @return Array di stringhe rappresentanti i dati delle stanza
     */
    public String[] getRoomData()
    {
        String[] data = new String[this._roomsData.size()];
        for(int i = 0; i < this._roomsData.size(); i++)
        {
            data[i] = this._roomsData.get(i);
        }
        return data;
    }
    /**
     * Ottine tutti i dati dei giocatori all'interno delle stanze
     * @return Array di stringhe rappresentanti i dati dei giocatori
     */
    public String[] getUsersData()
    {
        String[] data = new String[this._usersData.size()];
        for(int i = 0; i < this._usersData.size(); i++)
        {
            data[i] = this._usersData.get(i);
        }
        return data;
    }
    /**
     * Ottiene uno specifico dato di un giocatore
     * @param index Indice nella lista
     * @return Stringa rappresentate i dati di un giocatore in quella posizione
     */
    public String getDefinedUsersData(int index)
    {
        String[] usr = getUsersData();
        return usr[index];
    }
}
