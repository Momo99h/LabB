/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.client.frames.ClientLogin;
import com.universita.ilparolierelabb.common.ServerSettings;
import com.universita.ilparolierelabb.common.SettingsResult;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.settings.Settings;
import com.universita.ilparolierelabb.common.toServerRMI;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Momo
 */
public class ClientManager 
{
    private static toServerRMI _server;
    
    public static void Launch()
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry(1099);
            _server = (toServerRMI) registry.lookup("Server");
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
}
