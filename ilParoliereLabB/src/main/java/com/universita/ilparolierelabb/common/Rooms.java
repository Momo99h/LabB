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
public class Rooms implements Serializable
{
    private ArrayList<Room> _rooms = new ArrayList<>();
    private boolean _dataChanged = false;
    private int _lastID = 0;
    public Rooms(){}
    
    public ArrayList<Room> getAllRoomsData()
    {
        return this._rooms;
    }
    public void setAllRoomsData(ArrayList<Room> rooms)
    {
        this._rooms = rooms;
    }
    public int getRoomsCount()
    {
        return this._rooms.size();
    }
    public Room getRoom(int id)
    {
        for(Room r : _rooms)
        {
            if(r.getId() == id) return r;
        }
        return null;
    }
    public Room getRoomObject(int position)
    {
        return this._rooms.get(position);
    }
    public void addRoom(Room r)
    {
        this._rooms.add(r);
        if(r.getId() > _lastID) _lastID = r.getId();
    }
    public void removeRoom(Room r)
    {
        this._rooms.remove(r);
    }
    public void setDataChanged(Boolean value)
    {
        this._dataChanged = value;
    }
    public Boolean isDataChanged()
    {
        return this._dataChanged;
    }
    public void confirmDataChanged()
    {
        this._dataChanged = false;
    }
    public int getLastID()
    {
        return this._lastID;
    }
    public boolean isPlayerInAnyRoom(User player)
    {
        for(int i = 0; i < _rooms.size(); i++)
        {
            if(getRoomObject(i).isPlayerIn(player)) return true;
        }
        return false;
    }
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
}
