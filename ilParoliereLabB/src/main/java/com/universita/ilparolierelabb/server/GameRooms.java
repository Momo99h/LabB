/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Momo
 */
public class GameRooms implements Serializable
{
    private ArrayList<Room> _rooms = new ArrayList<>();
    private boolean _dataChanged = false;
    private int _lastID = 0;
    public GameRooms(){}
    
    public ArrayList<Room> getAllRoomsData()
    {
        return this._rooms;
    }
    public void setAllRoomsData(ArrayList<Room> rooms)
    {
        this._dataChanged = true;
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
        this._dataChanged = true;
        this._rooms.add(r);
        if(r.getId() > _lastID) _lastID = r.getId();
    }
    public void removeRoom(Room r)
    {
        this._dataChanged = true;
        this._rooms.remove(r);
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
}
