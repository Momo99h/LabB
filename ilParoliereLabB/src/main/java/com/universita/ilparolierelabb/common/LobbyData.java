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
public class LobbyData implements Serializable
{
    private ArrayList<String> _roomsData = new ArrayList<>();
    private ArrayList<String> _usersData = new ArrayList<>();
    
    public LobbyData(){}
    
    public void addRoomData(String s)
    {
        this._roomsData.add(s);
    }
    public void addUsersData(String s)
    {
        this._usersData.add(s);
    }
    public String[] getRoomData()
    {
        String[] data = new String[this._roomsData.size()];
        for(int i = 0; i < this._roomsData.size(); i++)
        {
            data[i] = this._roomsData.get(i);
        }
        return data;
    }
    public String[] getUsersData()
    {
        String[] data = new String[this._usersData.size()];
        for(int i = 0; i < this._usersData.size(); i++)
        {
            data[i] = this._usersData.get(i);
        }
        return data;
    }
    public String getDefinedUsersData(int index)
    {
        String[] usr = getUsersData();
        return usr[index];
    }
}
