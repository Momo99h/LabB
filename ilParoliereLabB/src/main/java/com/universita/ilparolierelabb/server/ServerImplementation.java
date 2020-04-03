/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.settings.Settings;
import com.universita.ilparolierelabb.common.toServerRMI;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Momo
 */
public class ServerImplementation extends UnicastRemoteObject implements toServerRMI
{
    private static Registry rgsty;
    private ServerImplementation() throws RemoteException 
    {
        super();
    }
    public static void Init()
    {
        try 
        {
            ServerImplementation obj = new ServerImplementation();
            rgsty = LocateRegistry.createRegistry(1099);
            rgsty.rebind("Server", obj);
            Utility.ConsolePrintLine("Server ready");

        } catch (Exception e) 
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
