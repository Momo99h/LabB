/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

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
    private ArrayList<WrappedObserver> WrappedObserver;
    
    private ServerImplementation() throws RemoteException 
    {
        super();
        WrappedObserver= new ArrayList<WrappedObserver>();
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
    public Boolean clientLogin(String usr, String psw) 
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
            ServerManager.addLogData("New client opened; "+mo);
        }
        catch(Exception e)
        {
            ServerManager.addLogData("Cannot add client: "+mo+" Reason: "+e.toString());
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
                    ServerManager.addLogData("New client disconnected: "+w);
                    WrappedObserver.remove(w);
                    break;
                }
                catch(Exception e)
                {
                    ServerManager.addLogData("Cannot delete client: "+w+" Reason: "+e.toString());
                }
            }
        }
        
    }
    
}
