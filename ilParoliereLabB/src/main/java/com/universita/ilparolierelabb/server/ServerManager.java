/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.server.frames.ServerLogin;
import com.universita.ilparolierelabb.server.frames.ServerMainFrame;
import com.universita.ilparolierelabb.server.frames.ServerRegistration;
import com.universita.ilparolierelabb.common.SettingsResult;
import com.universita.ilparolierelabb.server.frames.ServerSettings;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Momo
 */
public class ServerManager
{
    private static SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static GameRooms gameRooms = new GameRooms();
    
    public static void Launch()
    {
        SettingsResult _ConResult = ServerSettings.ConfigureServer();
        if(_ConResult == SettingsResult.ConnectionOk) ServerDBInterface.setDBReference(ServerSettings.getDbReference());
        else System.exit(0);
        checkAdmins();
    }
    private static void checkAdmins()
    {
        if(!ServerDBInterface.HasAdmin())
            new ServerRegistration().setVisible(true);
        else
            new ServerLogin().setVisible(true);
            
    }
    public static void Run()
    {
        ServerImplementation.Init();        
        ServerMainFrame serverFrame = new ServerMainFrame();
        serverFrame.setVisible(true);
        addLogData("Server ready - Waiting connections..");
        ServerDBInterface.resetUsersState();
        ServerThread.Run();
        Room r = new Room();
        r.setAdmin("Pippo");
        r.setId(1);
        r.setPlayersIn(1);
        r.setPlayersNeeded(1);
        gameRooms.addRoom(r);
    }  
    public static void addLogData(String logdata)
    {
        if(ServerMainFrame.Console_Log_Model.getSize() == 50)ServerMainFrame.Console_Log_Model.clear();
        String s = _sdf.format(new Date())+"    -   "+logdata;
        ServerMainFrame.Console_Log_Model.add(0, s);
    }
    
}
