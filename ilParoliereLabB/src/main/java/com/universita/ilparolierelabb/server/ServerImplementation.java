/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.User;
import com.universita.ilparolierelabb.common.Room;
import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.UserStatus;
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
    public static ArrayList<ClientObserver> LobbyClients;
    public static ArrayList<ClientObserver> GameClients;
    public static ArrayList<RegisterData> registerUserWaiting;

    private ServerImplementation() throws RemoteException 
    {
        super();
        LobbyClients = new ArrayList<>();
        GameClients = new ArrayList<>();
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
        ClientObserver mo = new ClientObserver(o);
        try
        {
            LobbyClients.add(mo);
            addObserver(mo);
            ServerManager._ClientCountChanged = true;
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
        ClientObserver w;
        for(int i = 0; i < LobbyClients.size(); i++)
        {
            w = LobbyClients.get(i);
            if(w.getOb().equals(o))
            {
                try
                {
                    deleteObserver(w);
                    ServerManager.addLogData("New client disconnected: (ID) "+w.getObId());
                    LobbyClients.remove(w);
                    ServerManager._ClientCountChanged = true;
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
    public static synchronized boolean notifyClientsCount(int count)
    {
        ClientObserver w;
        boolean success = true;
        for(int i = 0; i < LobbyClients.size(); i++)
        {
            w = LobbyClients.get(i);
            try 
            {
                w.getOb().notifyClientsCount(rmiService, count);
            } 
            catch (RemoteException ex) 
            {
                ex.printStackTrace();
                success = false;
            }
        }
        return success;
    }
    public static synchronized boolean notifyClientsLobbyData(LobbyData data) 
    {
        ClientObserver w;
        Boolean success = true;
        for(int i = 0; i < LobbyClients.size(); i++)
        {
            w = LobbyClients.get(i);
            try 
            {
                w.getOb().notifyClientsLobbyData(rmiService, data);
            } 
            catch (RemoteException ex) 
            {
                ex.printStackTrace();
                success = false;
            }
        }
        return success;
    }
    public static synchronized void notifyGameInitTimer(int roomId,int timerCount)
    {
        ClientObserver w;
        for(int i = 0; i < LobbyClients.size(); i++)
        {
            w = LobbyClients.get(i);
            try 
            {
                w.getOb().notifyGameInitTimer(rmiService, roomId,timerCount);
            } 
            catch (RemoteException ex) 
            {
                ex.printStackTrace();
            }
        }
    }
    public static synchronized void notifyGameMatrix(int roomId,String[][] matrix)
    {
        ClientObserver w;
        for(int i = 0; i < LobbyClients.size(); i++)
        {
            w = LobbyClients.get(i);
            try 
            {
                w.getOb().notifyGameMatrix(rmiService, roomId, matrix);
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
    public LobbyData getLobbyRooms() throws RemoteException
    {
        ServerManager.addLogData("New request of lobby rooms");
        ServerManager.lobby = ServerManager.rooms.createLobbyData();
        return ServerManager.lobby;
    }

    @Override
    public int getLastRoomID() throws RemoteException 
    {
        return ServerManager.rooms.getLastID();
    }

    @Override
    public void addRoom(Room r) throws RemoteException 
    {
        ServerManager.addLogData(r.getAdmin()+" added new room: "+r.getId()+" - "+r.getRoomName());
        ServerManager.rooms.addRoom(r);
         ServerManager.rooms.setDataChanged(true);
    }

    @Override
    public boolean enterRoom(RemoteObserver o,int roomId,User usr) throws RemoteException 
    {
        Room r = ServerManager.rooms.getRoom(roomId);
        if(r.getPlayersIn() >= r.getPlayersNeeded()) return false;
        ServerManager.addLogData(usr.getUsername()+" entered room: "+roomId);
        usr.setStatus(UserStatus.NotReady);
        r.addPlayer(usr);
        ServerDBInterface.clientEnterRoom(roomId,usr.getUsername());
        ServerManager.rooms.setDataChanged(true);
        removeObserver(o);
        return true;
    }

    @Override
    public void leaveRoom(RemoteObserver o,User usr) throws RemoteException 
    {
        Room r = ServerManager.rooms.getRoomWherePlayer(usr);
        if(ServerManager.rooms.removePlayerFromRoom(usr))
        {
            ServerDBInterface.clientLeaveRoom(usr.getUsername());
            ServerManager.addLogData(usr.getUsername()+" left room: "+r.getId());
            ServerManager.rooms.setDataChanged(true);
            addObserver(o);
        }
        if(r == null) return;
        if(r.getPlayersIn() == 0)
        {
            ServerManager.rooms.removeRoom(r);
            ServerManager.addLogData("Room ID "+r.getId()+" has been removed because no players are inside");
            ServerManager.rooms.setDataChanged(true);
        }
    }

    @Override
    public void changePlayerStatus(User usr, UserStatus status) throws RemoteException 
    {
        Room r = ServerManager.rooms.getRoomWherePlayer(usr);
        r.changePlayerStatus(usr, status);
        if(ServerManager.rooms.isRoomReadyToPlay(r.getId()) && !ServerManager.games.hasRoomGame(r.getId())) 
        {
            ServerManager.createGame(r.getId());
        }
        ServerManager.rooms.setDataChanged(true);
    }
    
    
    // @author AndreaGirola
    @Override
    public boolean emailAlreadyTaken(String email) throws RemoteException 
    {
        return ServerDBInterface.emailAlreadyTaken(email);
    }
    // @author AndreaGirola
    @Override
    public boolean userAlreadyTaken(String user) throws RemoteException 
    {
        return ServerDBInterface.userAlreadyTaken(user);
    }

    @Override
    public Room getRoomById(int id) throws RemoteException 
    {
        return ServerManager.rooms.getRoom(id);
    }

    @Override
    public int getOnlineCount() throws RemoteException 
    {
        return ServerManager.ObserversOnline();
    }

   
}
