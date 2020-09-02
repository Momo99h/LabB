
package com.universita.ilparolierelabb.common;

/**
 * Interfaccia del client
 * @author Momo
 */

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObserver extends Remote 
{
    public void notifyClientsCount(Object observable, int count)throws RemoteException;
    public void notifyClientsGameStarted(Object observable) throws RemoteException;
    public void notifyClientsGameFinished(Object observable) throws RemoteException;
    public void notifyClientsLobbyData(Object observable, LobbyData data)throws RemoteException;
    public void notifyGameInitTimer(Object observable,int timerCount) throws RemoteException;
    public void notifyGameMatrix(Object observable,String[][] matrix) throws RemoteException;
    public void notifyGameRoomData(Object observable,Room room) throws RemoteException;
    public void notifyGameTimer(Object observable,int timerCount) throws RemoteException;
    public void notifyWordGuessingState(Object observable,boolean state) throws RemoteException;
    public void notifyRefreshTable(Object observable) throws RemoteException;
    public void notifyHeaderGameMessage(Object observable,String Message) throws RemoteException;
    public void notifyDisableRoom(Object observable) throws RemoteException;
}