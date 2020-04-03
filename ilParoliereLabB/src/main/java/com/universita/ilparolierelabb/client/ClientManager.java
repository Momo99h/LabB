/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.common.ServerSettings;
import com.universita.ilparolierelabb.common.SettingsResult;

/**
 *
 * @author Momo
 */
public class ClientManager 
{
    public static void Launch()
    {
        SettingsResult _ConResult = ServerSettings.ConfigureServer();
        if(_ConResult == SettingsResult.ConnectionOk)ClientDBInterface.setDBReference(ServerSettings.getDbReference());
        else System.exit(0);        
        new ClientLogin().setVisible(true);
    }
    
}
