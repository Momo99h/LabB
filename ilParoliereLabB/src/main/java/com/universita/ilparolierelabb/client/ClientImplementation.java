/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.client.frames.ClientLogin;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.server.RemoteObserver;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.universita.ilparolierelabb.server.ServerInterface;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Momo
 */
public class ClientImplementation extends UnicastRemoteObject implements RemoteObserver
{
    private static ServerInterface _server = null;
    private static ClientImplementation _client;
    private ClientImplementation() throws RemoteException 
    {
        super();
    }
    
    public static void Launch()
    {
        try
        {
             Registry registry = LocateRegistry.getRegistry(9999);
            _server = (ServerInterface) registry.lookup("IlParoliereLabB_Server");
            _client = new ClientImplementation();
            _server.addObserver(_client);
            new ClientLogin().setVisible(true);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
        }
    }
    public static void Run()
    {
        
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
    public static void DisconnectFromServer()
    {
        try
        {
            _server.removeObserver(_client);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
        }
    }
    @Override
    public void update(Object observable, Object updateMsg) throws RemoteException 
    {
        
    }
}
