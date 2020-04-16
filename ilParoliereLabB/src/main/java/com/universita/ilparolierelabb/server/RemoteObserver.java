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
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.Rooms;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote 
{
    public void notifyClientsCount(Object observable, int count)throws RemoteException;
    public void notifyClientsLobbyData(Object observable, LobbyData data)throws RemoteException;
    public void notifyGameInitTimer(Object observable,int roomId,int timerCount) throws RemoteException;
    public void notifyGameMatrix(Object observable,int roomId,String[][] matrix) throws RemoteException;
}