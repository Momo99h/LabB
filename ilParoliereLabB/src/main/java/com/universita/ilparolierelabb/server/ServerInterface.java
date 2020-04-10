/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.client.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Momo
 */
public interface ServerInterface extends Remote 
{
    public Boolean clientLogin(String usr,String psw) throws RemoteException;
    public void clientRegister(RegisterData d) throws RemoteException;
    public Boolean registerWaitingEmailConfirmation(String usr)throws RemoteException;
    public Boolean activateAccount(String code)throws RemoteException;
    public void addObserver(RemoteObserver o) throws RemoteException;
    public void removeObserver(RemoteObserver o) throws RemoteException;
    public void disconnectClient(String usr) throws RemoteException;
    public Boolean clientIsLogged(String usr) throws RemoteException;
    public GameRooms getGameRooms()throws RemoteException;
    public int getLastRoomID()throws RemoteException;
    public void addRoom(Room r)throws RemoteException;
    public boolean enterRoom(int roomId,User usr)throws RemoteException;
    public void leaveRoom(User usr)throws RemoteException;
}
