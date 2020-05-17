/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

/**
 *
 * @author Momo
 */
public class ClientRoom 
{
    private int _roomId;
    private ClientObserver _client; 
    
    public ClientRoom(int roomid,ClientObserver client)
    {
        this._roomId = roomid;
        this._client = client;
    }
    
    public int getRoomId()
    {
        return this._roomId;
    }
    public ClientObserver getClient()
    {
        return this._client;
    }
}
