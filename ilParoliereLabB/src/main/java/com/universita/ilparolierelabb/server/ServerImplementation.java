/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.client.User;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Settings;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author Momo
 */
public class ServerImplementation extends Observable implements ServerInterface
{
    private static Registry rmiRegistry;
    private static ServerInterface rmiService;
    private static ServerImplementation server;
    public static ArrayList<WrappedObserver> WrappedObserver;
    public  static ArrayList<RegisterData> registerUserWaiting;

    private ServerImplementation() throws RemoteException 
    {
        super();
        WrappedObserver= new ArrayList<>();
        registerUserWaiting = new ArrayList<>();
    }
    
    public static void Init()
    {
        try 
        {
            
            rmiRegistry = LocateRegistry.createRegistry(9999);
            server = new ServerImplementation();
            rmiService = (ServerInterface) UnicastRemoteObject
                    .exportObject(server, 9999);
            rmiRegistry.bind("IlParoliereLabB_Server", rmiService);
            Utility.ConsolePrintLine("Server ready");

        } catch (Exception e) 
        {
            Utility.ShowErrorPopUp(Settings.serverName, "Server exception: "+e.toString());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    @Override
    public Boolean clientLogin(String usr, String psw) throws RemoteException
    {
        Boolean b = ServerDBInterface.ClientLogin(usr, psw);
        String msg = usr;
        msg += (b)?" logged successfully":" failed login";
        ServerManager.addLogData(msg);
        return b;
    }

    @Override
    public void addObserver(RemoteObserver o) throws RemoteException {
        WrappedObserver mo = new WrappedObserver(o);
        try
        {
            WrappedObserver.add(mo);
            addObserver(mo);
            ServerManager.addLogData("New client opened: (ID) "+mo.getObId());
        }
        catch(Exception e)
        {
            ServerManager.addLogData("Cannot add client: (ID) "+mo.getObId()+" Reason: "+e.toString());
        }
        
    }

    @Override
    public void removeObserver(RemoteObserver o) throws RemoteException 
    {
        for(WrappedObserver w : WrappedObserver)
        {
            if(w.getOb().equals(o))
            {
                try
                {
                    deleteObserver(w);
                    ServerManager.addLogData("New client disconnected: (ID) "+w.getObId());
                    WrappedObserver.remove(w);
                    break;
                }
                catch(Exception e)
                {
                    ServerManager.addLogData("Cannot delete client: (ID) "+w.getObId()+" Reason: "+e.toString());
                }
            }
        }
        
    }

    @Override
    public void clientRegister(RegisterData d) throws RemoteException 
    {
        registerUserWaiting.add(d);
        ServerManager.addLogData("New user opened registration: (Username) "+d.getUsername());
    }

    @Override
    public Boolean registerWaitingEmailConfirmation(String usr) throws RemoteException 
    {
        for(RegisterData d : registerUserWaiting)
        {
            if(d.getUsername().equals(usr))
            {
                return true;               
            }
        }
        return false;
    }
     @Override
    public Boolean clientIsLogged(String usr) throws RemoteException 
    {
        return ServerDBInterface.clientIsLogged(usr);
    }
    

    @Override
    public Boolean activateAccount(String code) throws RemoteException 
    {
        RegisterData account = null;
        for(RegisterData d : registerUserWaiting)
        {
            if(d.getVerificationCode().equals(code))
            {
                account = d;
                break;
            }
        }
        if(account == null) return false;
        
        if(ServerDBInterface.RegisterAccount(account))
        {
            registerUserWaiting.remove(account);
            return true;
        }
        return false;
    }
    public static void notifyClientsCount(int count)
    {
        for(WrappedObserver d : WrappedObserver)
        {
            try 
            {
                d.getOb().notifyClientsCount(rmiService, count);
            } 
            catch (RemoteException ex) 
            {
                ex.printStackTrace();
            }
        }
    }
    public static void notifyClientsRoomsData(GameRooms gameRooms) 
    {
        for(WrappedObserver d : WrappedObserver)
        {
            try 
            {
                d.getOb().notifyClientsRoomsData(rmiService, gameRooms);
            } 
            catch (RemoteException ex) 
            {
                ex.printStackTrace();
            }
        }
    }
    @Override
    public void disconnectClient(String usr) throws RemoteException 
    {
        if(usr.equals("")) return;
        if(ServerDBInterface.ClientDisconnect(usr))
        ServerManager.addLogData("New user disconnected: (Username) "+usr);
    }

    @Override
    public GameRooms getGameRooms() throws RemoteException
    {
        ServerManager.addLogData("New request of rooms");
        return ServerManager.gameRooms;
    }

    @Override
    public int getLastRoomID() throws RemoteException 
    {
        return ServerManager.gameRooms.getLastID();
    }

    @Override
    public void addRoom(Room r) throws RemoteException 
    {
        ServerManager.addLogData(r.getAdmin()+" added new room: "+r.getId()+" - "+r.getRoomName());
        ServerManager.gameRooms.addRoom(r);
    }

    @Override
    public boolean enterRoom(int roomId,User usr) throws RemoteException 
    {
        Room r = ServerManager.gameRooms.getRoom(roomId);
        if(r.getPlayersIn() >= r.getPlayersNeeded()) return false;
        ServerManager.addLogData(usr.getUsername()+" entered room: "+roomId);
        r.addPlayer(usr);
        ServerManager.gameRooms.setDataChanged(true);
        ServerDBInterface.clientEnterRoom(roomId,usr.getUsername());
        return true;
    }

    @Override
    public void leaveRoom(User usr) throws RemoteException 
    {
        Room r = ServerManager.gameRooms.getRoomWherePlayer(usr);
        if(ServerManager.gameRooms.removePlayerFromRoom(usr))ServerDBInterface.clientLeaveRoom(usr.getUsername());
        if(r == null) return;
        if(r.getPlayersIn() == 0)
        {
            ServerManager.gameRooms.removeRoom(r);
            ServerManager.addLogData("Room ID "+r.getId()+" has been removed because no players are inside");
        }
    }

   
}
