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
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author Momo
 */
public class ClientManager 
{
    private static toServerRMI look_up;
    
    public static void Launch()
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry(1099);
            look_up = (toServerRMI) registry.lookup("Server");
            new ClientLogin().setVisible(true);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
        }
    }
    
}
