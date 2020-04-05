/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
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
    private static ArrayList<WrappedObserver> WrappedObserver;
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
    
}
