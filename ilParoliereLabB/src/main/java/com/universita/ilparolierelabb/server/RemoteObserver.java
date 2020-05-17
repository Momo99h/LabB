
package com.universita.ilparolierelabb.server;

/**
 *
 * @author Momo
 */
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.Room;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote 
{
    public void notifyClientsCount(Object observable, int count)throws RemoteException;
    public void notifyClientsLobbyData(Object observable, LobbyData data)throws RemoteException;
    public void notifyGameInitTimer(Object observable,int timerCount) throws RemoteException;
    public void notifyGameMatrix(Object observable,String[][] matrix) throws RemoteException;
    public void notifyGameRoomData(Object observable,Room room) throws RemoteException;
}