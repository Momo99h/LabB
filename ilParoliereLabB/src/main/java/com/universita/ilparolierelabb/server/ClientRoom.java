/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

/**
 * Rappresenta il client in correlazione alla stanza in cui si trova
 * @author Momo
 */
public class ClientRoom 
{
    private int _roomId;
    private ClientObserver _client; 
    
    /**
     * Costruttore
     * @param roomid Identificativo della stanza
     * @param client Oggetto client
     */
    public ClientRoom(int roomid,ClientObserver client)
    {
        this._roomId = roomid;
        this._client = client;
    }
    /**
     * Ritorna l'identificativo della stanza
     * @return Identificativo della stanza
     */
    public int getRoomId()
    {
        return this._roomId;
    }
    /**
     * Ritorna l'oggetto client
     * @return Oggetto client
     */
    public ClientObserver getClient()
    {
        return this._client;
    }
}
