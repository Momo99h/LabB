package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.Games;
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.Rooms;
import com.universita.ilparolierelabb.server.frames.ServerLogin;
import com.universita.ilparolierelabb.server.frames.ServerMainFrame;
import com.universita.ilparolierelabb.server.frames.ServerRegistration;
import com.universita.ilparolierelabb.common.SettingsResult;
import com.universita.ilparolierelabb.server.frames.ServerSettings;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ServerManager Gestisce le azioni del modulo Server
 * @author Momo
 */
public class ServerManager
{
    private static SimpleDateFormat _sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static Rooms rooms = new Rooms();
    public static Games games = new Games();
    public static Boolean _ClientCountChanged = false;
    public static LobbyData lobby = new LobbyData();
    
    /***
     * Launch Avvia il server chiedendo i parametri di conessione al Database usato.
     */
    public static void Launch()
    {
        SettingsResult _ConResult = ServerSettings.ConfigureServer();
        if(_ConResult == SettingsResult.ConnectionOk) ServerDBInterface.setDBReference(ServerSettings.getDbReference());
        else System.exit(0);
        checkAdmins();
    }
    /**
     * checkAdmins Controlla se il server ha un amministratore registrato.
     * Se si avvia la fase del login, altrimenti la registrazione.
     */
    private static void checkAdmins()
    {
        if(!ServerDBInterface.HasAdmin())
            new ServerRegistration().setVisible(true);
        else
            new ServerLogin().setVisible(true);
            
    }
    /***
     * ObserversOnline Ottiene il numero di utenti connessi
     * @return 
     */
    public static int ObserversOnline()
    {
        return ServerImplementation.LobbyClients.size()+ServerImplementation.GameClients.size();
    }
    /***
     * Run Avvia la connessione RMI del server e inizializza
     */
    public static void Run()
    {
        ServerImplementation.Init(9999);        
        ServerMainFrame serverFrame = new ServerMainFrame();
        serverFrame.setVisible(true);
        addLogData("Server ready - Waiting connections..");
        ServerDBInterface.resetUsersState();
        MatrixFactory.createDices();
        ServerThread.Run();
    }  
    /***
     * addLogData Aggiunge un dato nel log del server
     * @param logdata Stringa da aggiungere 
     */
    public static void addLogData(String logdata)
    {
        if(ServerMainFrame.Console_Log_Model.getSize() == 50)ServerMainFrame.Console_Log_Model.clear();
        String s = _sdf.format(new Date())+"    -   "+logdata;
        ServerMainFrame.Console_Log_Model.add(0, s);
    }
    /**
     * createGame Crea un gioco identificato dall'ID della stanza
     * @param id Identificativo della stanza
     */
    public static void createGame(int id) 
    {
        games.createGame(id);
        addLogData("Created new game for room (ID): "+id);
    }
    
}
