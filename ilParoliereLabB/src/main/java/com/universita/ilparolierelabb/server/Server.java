/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.settings.Settings;
import com.universita.ilparolierelabb.common.toServerRMI;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Momo
 */
public class Server extends UnicastRemoteObject implements toServerRMI
{
    private static Registry rgsty;
    private Server() throws RemoteException 
    {
        super();
    }
    public static void Init()
    {
        try 
        {
            Server obj = new Server();
            rgsty = LocateRegistry.createRegistry(10);
            rgsty.rebind("Server", obj);
            Utility.ConsolePrintLine("Server ready");

        } catch (RemoteException e) 
        {
            Utility.ShowErrorPopUp(Settings.serverName, "Server exception: "+e.toString());
            System.exit(1);
        }
    }
    
    @Override
    public Boolean clientLogin(String usr, String psw) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
