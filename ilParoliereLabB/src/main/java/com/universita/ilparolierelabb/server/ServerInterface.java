/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.User;
import com.universita.ilparolierelabb.common.Room;
import com.universita.ilparolierelabb.common.Rooms;
import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.UserStatus;
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
    public void addClientObserver(RemoteObserver o) throws RemoteException;
    public void removeObserver(RemoteObserver o) throws RemoteException;
    public void disconnectClient(String usr) throws RemoteException;
    public Boolean clientIsLogged(String usr) throws RemoteException;
    public LobbyData getLobbyRooms()throws RemoteException;
    public int getLastRoomID()throws RemoteException;
    public void addRoom(Room r)throws RemoteException;
    public boolean enterRoom(RemoteObserver o,int roomId,User usr)throws RemoteException;
    public void leaveRoom(RemoteObserver o,User usr)throws RemoteException;
    public void changePlayerStatus(User usr,UserStatus status) throws RemoteException;
    public boolean emailAlreadyTaken(String email) throws RemoteException;
    public boolean userAlreadyTaken(String user) throws RemoteException;
    public int getOnlineCount() throws RemoteException;
    public Room getRoomById(int id) throws RemoteException;
}
