package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.Games;
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.Rooms;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.server.frames.ServerLogin;
import com.universita.ilparolierelabb.server.frames.ServerMainFrame;
import com.universita.ilparolierelabb.server.frames.ServerRegistration;
import com.universita.ilparolierelabb.common.SettingsResult;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.dictionary.Dictionary;
import com.universita.ilparolierelabb.dictionary.DictionaryLoader;
import com.universita.ilparolierelabb.server.frames.ServerSettings;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFileChooser;

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
    public static Dictionary _serverDictionary;
    public static String _DictionaryPath = "C:\\Users\\Momo\\Desktop\\dict-it.oxt";
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
        //Check if file exist;
        _DictionaryPath = ServerDBInterface.getDictionaryPath();
        File f = new File(_DictionaryPath); 
        if (!f.exists())
        {
            Utility.ShowInfoPopUp(Settings.serverName, "Please select dictionary file to continue.");
            final JFileChooser fc = new JFileChooser();
            fc.setToolTipText("Select dictionary file");
            int returnVal = fc.showOpenDialog(null);
            if (returnVal == JFileChooser.APPROVE_OPTION) 
            {
                File file = fc.getSelectedFile();
                _DictionaryPath = file.getAbsolutePath();
                ServerDBInterface.setDictionaryPath(_DictionaryPath);
            }
            else
            {
                Utility.ShowErrorPopUp(Settings.serverName, "Cannot continue without selecting the dictionary file.");
                System.exit(0);
            }
        }
        addLogData("Server ready - Waiting connections..");
        ServerDBInterface.resetUsersState();
        MatrixFactory.createDices();
        ServerThread.Run();
        rooms.setLastID(ServerDBInterface.getRoomLastId());
        
        
        _serverDictionary = DictionaryLoader.loadServerDictionary(_DictionaryPath);
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
    public static void createGame(int roomId) 
    {
        int gId = ServerManager.rooms.getRoom(roomId).nextGameId();
        games.createGame(roomId,gId);
        String s = "Created new game for room (ID) %s with ID: %s";
        s = String.format(s, roomId,gId);
        addLogData(s);
    }
    
}
