/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.client.frames.ClientLobbyFrame;
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.UserStatus;
import com.universita.ilparolierelabb.common.Rooms;
import com.universita.ilparolierelabb.server.RemoteObserver;
import com.universita.ilparolierelabb.common.Room;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.universita.ilparolierelabb.server.ServerInterface;
import com.universita.ilparolierelabb.common.User;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Momo
 */
public class ClientImplementation extends UnicastRemoteObject implements RemoteObserver
{
    public static ServerInterface _server = null;
    public static ClientImplementation _client;

    
    
    private ClientImplementation() throws RemoteException 
    {
        super();
    }
    private static Boolean initConnection(String ip)
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry(ip,9999);
            _server = (ServerInterface) registry.lookup("IlParoliereLabB_Server");
            _client = new ClientImplementation();
            ClientManager.Login();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public static void ClientOnline()
    {
        try
        {
            _server.addObserver(_client);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
        }
    }
    public static void Launch()
    {
        if(!initConnection("localhost"))
        {
            String newIp = Utility.ShowInfoInput(Settings.clientName, "Connection failed, please enter Server's ip:");
            if(!initConnection(newIp))
            {
                Utility.ShowErrorPopUp(Settings.clientName, "Cannot reach server. Application is closing.");
                System.exit(1);
            }
        }
    }
    public static Boolean getLogin(String usr,String psw)
    {
       Boolean success = false;
       try
       {
           success = _server.clientLogin(usr, psw);
       }
       catch(RemoteException e)
       {
           Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
       }
       return success;
    }
    public static Boolean clientRegister(RegisterData d)
    {
       try
       {
           _server.clientRegister(d);
           return true;
       }
       catch(RemoteException e)
       {
           Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
           return false;
       }
    }
    public static void DisconnectFromServer(String usr)
    {
        try
        {
            _server.disconnectClient(usr);
            _server.removeObserver(_client);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
        }
    }
    public static Boolean registerInWaiting(String usr)
    {
        try
        {
            return _server.registerWaitingEmailConfirmation(usr);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false;
        }
    }
    public static Boolean registerAccount(String code)
    {
        try
        {
            return _server.activateAccount(code);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false;
        }
    }
    public static boolean clientIsLogged(String usr) 
    {
        try
        {
            return _server.clientIsLogged(usr);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false;
        }
    }
    public static LobbyData getLobbyRooms() 
    {
        try
        {
            return _server.getLobbyRooms();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return null;
        }
    }
    public static int getLastRoomID() 
    {
        try
        {
            return _server.getLastRoomID();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return -1;
        }
    }
    public static void addRoom(Room r) 
    {
        try
        {
            _server.addRoom(r);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            
        }
    }
    public static boolean enterRoom(int roomID, User usr)
    {
        try
        {
            return _server.enterRoom(roomID,usr);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false; 
        }
    }
    public static void leaveRoom(User usr) 
    {
       try
        {
            _server.leaveRoom(usr);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            
        }
    }
    public static void changePlayerStatus(User usr,UserStatus status)
    {
       try
        {
            _server.changePlayerStatus(usr, status);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            
        }
    }
   
    // @author AndreaGirola
    public static boolean emailAlreadyTaken(String email){
        try
        {
            return _server.emailAlreadyTaken(email);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false; 
            
        }
    }
    public static Room getRoomById(int roomId) 
    {
        try
        {
            return _server.getRoomById(roomId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return null; 
            
        }
    }
    // @author AndreaGirola
    public static boolean userAlreadyTaken(String user){
        try
        {
            return _server.userAlreadyTaken(user);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false; 
            
        }
    }

    @Override
    public void notifyClientsCount(Object observable, int count) throws RemoteException 
    {
        try
        {
            ClientLobbyFrame.Par_lblUtentiConnessi.setText(count+"");
        }
        catch(Exception e)
        {
            
        }
    }
    @Override
    public void notifyClientsLobbyData(Object observable, LobbyData data) throws RemoteException 
    {
        ClientManager.lobby = data;
        ClientManager.refreshRooms();
    }

    @Override
    public void notifyGameInitTimer(Object observable, int roomId, int timerCount) throws RemoteException 
    {
        ClientManager.refreshGameInitTimer(roomId,timerCount);
    }

    @Override
    public void notifyGameMatrix(Object observable, int roomId, String[][] matrix) throws RemoteException 
    {
        ClientManager.setGameMatrix(roomId,matrix);
    }

}
