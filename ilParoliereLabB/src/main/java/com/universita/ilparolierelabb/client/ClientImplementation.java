package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.client.frames.ClientLobbyFrame;
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.UserStatus;
import com.universita.ilparolierelabb.server.RemoteObserver;
import com.universita.ilparolierelabb.common.Room;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import com.universita.ilparolierelabb.server.ServerInterface;
import com.universita.ilparolierelabb.common.User;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Momo
 * 
 */
public class ClientImplementation extends UnicastRemoteObject implements RemoteObserver
{
    
    public static ServerInterface _server = null;
    public static ClientImplementation _client;

    /**
     * ClientImplementation Costruttore
     * @throws RemoteException 
     */
    private ClientImplementation() throws RemoteException 
    {
        super();
    }
    /**
     * initConnection Inizializza connessione verso il server sulla porta 9999
     * @param ip Indirizzo ip di connessione al server
     * @return 
     */
    private static Boolean initConnection(String ip)
    {
        try
        {
            Registry registry = LocateRegistry.getRegistry(ip,9999);
            _server = (ServerInterface) registry.lookup("IlParoliereLabB_Server");
            _client = new ClientImplementation();
            ClientManager.Login();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }
    /***
     * ClientOnline Comunica al server che il client è andato online
     */
    public static void ClientOnline()
    {
        try
        {
            _server.addClientObserver(_client);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
        }
    }
    /**
     * Launch Avvia il modulo client
     */
    public static void Launch()
    {
        if(!initConnection("localhost"))
        {
            String newIp = Utility.ShowInfoInput(Settings.clientName, "Connection failed, please enter Server's ip:");
            if(!initConnection(newIp))
            {
                Utility.ShowErrorPopUp(Settings.clientName, "Cannot reach server. Application is closing.");
                System.exit(1);
            }
        }
    }
    /**
     * getLogin Chiede al server la verifica di login di un utente
     * @param usr Nome utente
     * @param psw Password utente
     * @return true se l'operazione è andata a buon fine
     */
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
    /**
     * clientRegister Comunica al server i dati di registrazione di un utente
     * @param d Dati di registrazione
     * @return true se l'operazione è andata a buon fine
     */
    public static Boolean clientRegister(RegisterData d)
    {
       try
       {
           _server.clientRegister(d);
           return true;
       }
       catch(RemoteException e)
       {
           Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
           return false;
       }
    }
    /**
     * DisconnectFromServer Comunica al server l'avvenuta disconnessione di un utente
     * @param usr 
     */
    public static void DisconnectFromServer(String usr)
    {
        try
        {
            _server.disconnectClient(usr);
            _server.removeClientObserver(_client);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
        }
    }
    /**
     * registerInWaiting Chiede al server di verificare se l'utente è in attesa di conferma per codice di verifica
     * @param usr Nome utente
     * @return true se l'utente deve confermare il codice inviato per mail
     */
    public static Boolean registerInWaiting(String usr)
    {
        try
        {
            return _server.registerWaitingEmailConfirmation(usr);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false;
        }
    }
    /**
     * registerAccount Comunica al server la registrazione di un utente tramite il codice
     * @param code Codice di registrazione
     * @return true se la registrazione è avvenuta con successo
     */
    public static Boolean registerAccount(String code)
    {
        try
        {
            return _server.activateAccount(code);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false;
        }
    }
    /***
     * clientIsLogged Chiede al server se l'utente ha già effettuato l'accesso
     * @param usr Nome utente
     * @return true se l'utente ha gia effettuato l'accesso
     */
    public static boolean clientIsLogged(String usr) 
    {
        try
        {
            return _server.clientIsLogged(usr);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false;
        }
    }
    /**
     * getLobbyRooms Chiede al server il pacchetto dati della lobby
     * @return Il pacchetto dati della lobby
     */
    public static LobbyData getLobbyRooms() 
    {
        try
        {
            return _server.getLobbyRooms();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return null;
        }
    }
    /**
     * getLastRoomID Chiede al server l'ultimo ID disponibile delle stanze presenti
     * @return L'id disponibile
     */
    public static int getLastRoomID() 
    {
        try
        {
            return _server.getLastRoomID();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return -1;
        }
    }
    /**
     * addRoom Chiede al server di aggiungere una stanza
     * @param r Oggetto che rappresenza una stanza
     */
    public static void addRoom(Room r) 
    {
        try
        {
            _server.addRoom(r);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            
        }
    }
    /**
     * enterRoom Chiede al server di fare entrare un utente all'interno di una stanza
     * @param roomID Identificativo della stanza
     * @param usr Oggetto che rappresenta l'utente
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean enterRoom(int roomID, User usr)
    {
        try
        {
            return _server.enterRoom(_client,roomID,usr);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false; 
        }
    }
    /**
     * leaveRoom Chiede al server di togliere un utente dalle stanze
     * @param usr Oggetto che rapprenta l'utente
     */
    public static void leaveRoom(User usr) 
    {
       try
        {
            _server.leaveRoom(_client,usr);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            
        }
    }
    /**
     * changePlayerStatus Chiede al server di cambiare lo stato di un utente
     * @param usr Oggetto che rappresenta l'utente
     * @param status Enumerazione che rappresenza lo stato
     */
    public static void changePlayerStatus(User usr,UserStatus status)
    {
       try
        {
            _server.changePlayerStatus(usr, status);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            
        }
    }
    /**
     * getOnlineCount Chiede al server il numero di client online
     * @return Il numero di client online
     */
    public static int getOnlineCount()
    {
       try
        {
            return _server.getOnlineCount();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return 0;
        }
    }
    /**
     * emailAlreadyTaken Chiede al server di verificare se una mail è gia presente nel database
     * @param email Email da verificare
     * @return true se l'email è gia presente
     */
    public static boolean emailAlreadyTaken(String email){
        try
        {
            return _server.emailAlreadyTaken(email);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false; 
            
        }
    }
    /**
     * getRoomById Chiede al server di ottenere l'oggetto che rappresenza una stanza da un identificativo
     * @param roomId Identificativo della stanza
     * @return Oggetto che rappresenta la stanza
     */
    public static Room getRoomById(int roomId) 
    {
        try
        {
            return _server.getRoomById(roomId);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return null; 
            
        }
       
    }
    /**
     * recoverPassword Chiede al server di reimpostare la password 
     * @param email Email al quale impostare e reinviare la nuova password
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean recoverPassword(String email) 
    {
        try
        {
            return _server.recoverPassword(email);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false; 
            
        }
    }
    /***
     * userAlreadyTaken Chiede al server di verificare se una nickname è gia presente nel database
     * @param user Nickname da controllare
     * @return true se già presente
     */
    public static boolean userAlreadyTaken(String user){
        try
        {
            return _server.userAlreadyTaken(user);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return false; 
            
        }
    }
    /**
     * checkWord Chiede al server di verificare una parola inserita all'interno del gioco
     * @param word Parola da verificare
     * @param roomId Identificativo della stanza di gioco (usato per estrapolare le fasi di gioco e la matrice)
     * @param username Username dell'utente che inserisce la parola (Usato per assegnare i punteggi)
     * @return Punteggio derivante dal controllo
     */
    public static int checkWord(String word, int roomId,String username)
    {
        try
        {
            return _server.checkWord(word, roomId, username);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            Utility.ShowErrorPopUp(Settings.clientName, e.toString());
            System.exit(1);
            return -1; 
        }
    }
    /**
     * notifyClientsCount Eseguito dal server per notificare il numero di client connessi
     * @param observable Interfaccia del client
     * @param count Numero di clienti connessi
     * @throws RemoteException 
     */
    @Override
    public void notifyClientsCount(Object observable, int count) throws RemoteException 
    {
        try
        {
            ClientLobbyFrame.Par_lblUtentiConnessi.setText(count+"");
        }
        catch(Exception e)
        {
            
        }
    }
    /**
     * notifyClientsLobbyData Usato dal server per notificare il pacchetto dati della lobby
     * @param observable Interfaccia del client
     * @param data Pacchetto dati lobby
     * @throws RemoteException 
     */
    @Override
    public void notifyClientsLobbyData(Object observable, LobbyData data) throws RemoteException 
    {
        ClientManager.lobby = data;
        ClientManager.refreshLobbyRooms();
    }
    /**
     * notifyGameInitTimer Usato dal server per notificare il conteggio del timer iniziale di una fase di gioco
     * @param observable Interfaccia del client
     * @param timerCount Conteggio del timer
     * @throws RemoteException 
     */
    @Override
    public void notifyGameInitTimer(Object observable, int timerCount) throws RemoteException 
    {
        ClientManager.refreshGameInitTimer(timerCount);
    }

    @Override
    public void notifyGameMatrix(Object observable, String[][] matrix) throws RemoteException 
    {
        ClientManager.setGameMatrix(matrix);
    }

    @Override
    public void notifyGameRoomData(Object observable, Room room) throws RemoteException 
    {
        ClientManager.refreshGameRoom();
    }

    @Override
    public void notifyGameTimer(Object observable, int timerCount) throws RemoteException 
    {
       ClientManager.refreshGameTimer(timerCount);
    }

    @Override
    public void notifyClientsGameStarted(Object observable) throws RemoteException 
    {
        ClientManager.startGame();
    }

    @Override
    public void notifyWordGuessingState(Object observable, boolean state) throws RemoteException 
    {
        ClientManager.WordGuessingState(state);
    }

    @Override
    public void notifyRefreshTable(Object observable) throws RemoteException 
    {
        ClientManager.RefreshGameTable();
    }

    @Override
    public void notifyHeaderGameMessage(Object observable, String Message) throws RemoteException 
    {
         ClientManager.setGameHeaderMessage(Message);
    }

    @Override
    public void notifyClientsGameFinished(Object observable) throws RemoteException 
    {
        ClientManager.stopGame();
    }
    
    public static String[] getMyStatistics(String user) {
        try
        {
            return _server.getMyStatistics(user);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
       
    }
    public static String[][] getStatisticPoint1() 
    {
         try
        {
            return _server.getStatisticPoint1();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    static String[] getStatisticPoint1b() 
    {
        try
        {
            return _server.getStatisticPoint1b();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
     static String[][] getStatisticPoint1c() 
     {
        try
        {
            return _server.getStatisticPoint1c();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
     static String[] getStatisticPoint1e() 
     {
        try
        {
            return _server.getStatisticPoint1e();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    static String[][] getStatisticPoint2() {
         try
        {
            return _server.getStatisticPoint2();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    static String[][] getStatisticPoint3() 
    {
         try
        {
            return _server.getStatisticPoint3();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    static String[][] getStatisticPoint4() 
    {
        try
        {
            return _server.getStatisticPoint4();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    static String[][] getStatisticPoint5() 
    {
        try
        {
            return _server.getStatisticPoint5();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    static String[] getStatisticPoint1d() 
    {
       
        try
        {
            return _server.getStatisticPoint1d();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    
    static String getDefinition(String word,int RoomID) 
    {
        try
        {
            return _server.getDefinition(word,RoomID);
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    static String[][] getStatisticPoint7() 
    {
        try
        {
            return _server.getStatisticPoint7();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
    static String[] getStatisticPoint8() 
    {
        try
        {
            return _server.getStatisticPoint8();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
    }
   static String[][] getStatisticPoint6() 
   {
        try
        {
            return _server.getStatisticPoint6();
        }
        catch(Exception e)
        {
            Utility.ShowErrorPopUp(Settings.clientName, e.getMessage());
            System.exit(1);
            return null;
        }
   }

    @Override
    public void notifyDisableRoom(Object observable) throws RemoteException 
    {
        ClientManager.notifyDisableRoom();
    }
    



}
