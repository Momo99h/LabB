package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.User;
import com.universita.ilparolierelabb.common.Room;
import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.Game;
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.UserStatus;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Observable;


/**
 * ServerImplementation
 * 
 * Classe di implementazione dell'interfaccia di collocquio RMI.
 * 
 * @author Momo
 */
public class ServerImplementation extends Observable implements ServerInterface
{
    private static Registry rmiRegistry;
    private static ServerInterface rmiService;
    private static ServerImplementation server;
    public static ArrayList<ClientObserver> LobbyClients; //Osservatori client nella lobby
    public static ArrayList<ClientRoom> GameClients; //Osservatori client nelle room di gioco
    public static ArrayList<RegisterData> registerUserWaiting; // Lista di utenti in attesa della mail di conferma

    

    private ServerImplementation() throws RemoteException 
    {
        super();
        LobbyClients = new ArrayList<>();
        GameClients = new ArrayList<>(); 
        registerUserWaiting = new ArrayList<>();
    }
    /***
     * Init Inizializza il registro di connessione al server 
     * 
     * @param portNumber numero di porta per la connessione
     */
    public static void Init(int portNumber)
    {
        try 
        {
            
            rmiRegistry = LocateRegistry.createRegistry(portNumber);
            server = new ServerImplementation();
            rmiService = (ServerInterface) UnicastRemoteObject
                    .exportObject(server, 9999);
            rmiRegistry.bind("IlParoliereLabB_Server", rmiService);
            Utility.ConsolePrintLine("Server ready");

        } catch (Exception e) 
        {
            Utility.ShowErrorPopUp(Settings.serverName, "Server exception: "+e.toString());
            e.printStackTrace();
            System.exit(1);
        }
    }
    
    /***
     * clientLogin (RMI) Controlla se i dati inseriti sono valide credenziali
     * @param usr Username dell'utente
     * @param psw Password dell'utente
     * @return true se il login è stato effettuato con successo
     * @throws RemoteException 
     */
    @Override
    public Boolean clientLogin(String usr, String psw) throws RemoteException
    {
        Boolean b = ServerDBInterface.ClientLogin(usr, psw);
        String msg = usr;
        msg += (b)?" logged successfully":" failed login";
        ServerManager.addLogData(msg);
        return b;
    }
    /***
     * addClientObserver (RMI) Aggiunge un client alla lista di utenti in lobby 
     * @param o Interfaccia del client  (suo identificativo)
     * @throws RemoteException 
     */
    @Override
    public void addClientObserver(RemoteObserver o) throws RemoteException {
        ClientObserver mo = new ClientObserver(o);
        try
        {
            LobbyClients.add(mo);
            addObserver(mo);
            ServerManager._ClientCountChanged = true;
            ServerManager.addLogData("New client opened: (ID) "+mo.getObId());
        }
        catch(Exception e)
        {
            ServerManager.addLogData("Cannot add client: (ID) "+mo.getObId()+" Reason: "+e.toString());
        }
    }
    /***
     * removeClientObserver (RMI) Rimuove un client alla lista di utenti in lobby 
     * @param o Interfaccia del client  (suo identificativo)
     * @throws RemoteException 
     */
    @Override
    public void removeClientObserver(RemoteObserver o) throws RemoteException 
    {
        ClientObserver w;
        for(int i = 0; i < LobbyClients.size(); i++)
        {
            w = LobbyClients.get(i);
            if(w.getOb().equals(o))
            {
                try
                {
                    deleteObserver(w);
                    ServerManager.addLogData("New client disconnected: (ID) "+w.getObId());
                    LobbyClients.remove(w);
                    ServerManager._ClientCountChanged = true;
                    break;
                }
                catch(Exception e)
                {
                    ServerManager.addLogData("Cannot delete client: (ID) "+w.getObId()+" Reason: "+e.toString());
                }
            }
        }        
    }
    /***
     * clientRegister (RMI) Aggiunge un utente in fase di registrazione alla lista
     * @param d Dati di registrazione dell'utente
     * @throws RemoteException 
     */
    @Override
    public void clientRegister(RegisterData d) throws RemoteException 
    {
        registerUserWaiting.add(d);
        ServerManager.addLogData("New user opened registration: (Username) "+d.getUsername());
    }
    @Override
    
    /***
     * registerWaitingEmailConfirmation (RMI) Controlla se un utente che sta effettutando il login deve confermare i dati
     * @param usr Username dell'utente
     * @throws RemoteException 
     */
    public Boolean registerWaitingEmailConfirmation(String usr) throws RemoteException 
    {
        for(RegisterData d : registerUserWaiting)
        {
            if(d.getUsername().equals(usr))
            {
                return true;               
            }
        }
        return false;
    }
    /***
     * clientIsLogged (RMI) Controlla se un utente è già all'interno del gioco
     * @param usr Username utente
     * @return true se l'utente è all'interno del gioco
     * @throws RemoteException 
     */
     @Override
    public Boolean clientIsLogged(String usr) throws RemoteException 
    {
        return ServerDBInterface.clientIsLogged(usr);
    }
    /***
     * activateAccount (RMI) Attiva l'account utente che corrisponde al codice fornito
     * @param code Codice di attivazione
     * @return true se l'account è stato attivato
     * @throws RemoteException 
     */
    @Override
    public Boolean activateAccount(String code) throws RemoteException 
    {
        RegisterData account = null;
        for(RegisterData d : registerUserWaiting)
        {
            if(d.getVerificationCode().equals(code))
            {
                account = d;
                break;
            }
        }
        if(account == null) return false;
        
        if(ServerDBInterface.RegisterAccount(account))
        {
            registerUserWaiting.remove(account);
            return true;
        }
        return false;
    }
    /***
     * notifyClientsCount (RMI) Notifica gli osservatori client del numero di utenti connessi
     * @param count Numero utenti connessi
     * @return true se l'operazione è stata eseguita con successo
     */
    public static synchronized boolean notifyClientsCount(int count)
    {
        ClientObserver w;
        boolean success = true;
        for(int i = 0; i < LobbyClients.size(); i++)
        {
            w = LobbyClients.get(i);
            try 
            {
                w.getOb().notifyClientsCount(rmiService, count);
            } 
            catch (RemoteException ex) 
            {
                ex.printStackTrace();
                success = false;
            }
        }
        return success;
    }
    /***
     * notifyClientsLobbyData (RMI) Notifica gli osservatori client dei dati di intestazione delle stanze di gioco
     * Esempio: Id,Nome,Giocatori e data di creazione
     * @param data Dati da essere notificati
     * @return true se l'operazione è stata eseguita con successo
     */
    public static synchronized boolean notifyClientsLobbyData(LobbyData data) 
    {
        ClientObserver w;
        Boolean success = true;
        for(int i = 0; i < LobbyClients.size(); i++)
        {
            w = LobbyClients.get(i);
            try 
            {
                w.getOb().notifyClientsLobbyData(rmiService, data);
            } 
            catch (RemoteException ex) 
            {
                ex.printStackTrace();
                success = false;
            }
        }
        return success;
    }
    /***
     * notifyGameInitTimer (RMI) Notifica gli osservatori client in una stanza il timer iniziale del gioco
     * @param roomId Identificativo della stanza
     * @param timerCount Valore del contatore
     */
    public static synchronized void notifyGameInitTimer(int roomId,int timerCount)
    {
        ClientRoom g;
        for(int i=0; i<ServerImplementation.GameClients.size();i++)
        {
            g = ServerImplementation.GameClients.get(i);
            if(g.getRoomId() == roomId) 
            {
                try 
                {
                    g.getClient().getOb().notifyGameInitTimer(rmiService, timerCount);
                } 
                catch (RemoteException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static synchronized void notifyHeaderGameMessage(int roomId,String Message)
    {
        ClientRoom g;
        for(int i=0; i<ServerImplementation.GameClients.size();i++)
        {
            g = ServerImplementation.GameClients.get(i);
            if(g.getRoomId() == roomId) 
            {
                try 
                {
                    g.getClient().getOb().notifyHeaderGameMessage(rmiService, Message);
                } 
                catch (RemoteException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static synchronized void notifyWordGuessingState(int roomId,boolean state)
    {
        ClientRoom g;
        for(int i=0; i<ServerImplementation.GameClients.size();i++)
        {
            g = ServerImplementation.GameClients.get(i);
            if(g.getRoomId() == roomId) 
            {
                try 
                {
                    g.getClient().getOb().notifyWordGuessingState(rmiService, state);
                } 
                catch (RemoteException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public static synchronized void notifyGameTimer(int roomId, int timerCount) {
        ClientRoom g;
        for(int i=0; i<ServerImplementation.GameClients.size();i++)
        {
            g = ServerImplementation.GameClients.get(i);
            if(g.getRoomId() == roomId) 
            {
                try 
                {
                    g.getClient().getOb().notifyGameTimer(rmiService, timerCount);
                } 
                catch (RemoteException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    /***
     * notifyGameMatrix (RMI) Notifica gli osservatori client in una stanza la matrice di gioco
     * @param roomId Identificativo della stanza
     * @param matrix Matrice di gioco
     */
    public static synchronized void notifyGameMatrix(int roomId,String[][] matrix)
    {
        ClientRoom g;
        for(int i=0; i<ServerImplementation.GameClients.size();i++)
        {
            g = ServerImplementation.GameClients.get(i);
            if(g.getRoomId() == roomId) 
            {
                try 
                {
                    g.getClient().getOb().notifyGameMatrix(rmiService, matrix);
                } 
                catch (RemoteException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static synchronized boolean notifyGameRoomData()
    {
        ClientRoom g;
        Room r;
        Boolean success = true;
        for(int i=0; i<ServerImplementation.GameClients.size();i++)
        {
            g = ServerImplementation.GameClients.get(i);
            r = ServerManager.rooms.getRoom(g.getRoomId());
            try 
            {
                g.getClient().getOb().notifyGameRoomData(rmiService, r);
            } 
            catch (RemoteException ex) 
            {
                success = false;
                ex.printStackTrace();
            }
        }
        return success;
    }
    public static synchronized void notifyGameRoomStarted(int roomId)
    {
        ClientRoom g;
        for(int i=0; i<ServerImplementation.GameClients.size();i++)
        {
            g = ServerImplementation.GameClients.get(i);
            if(g.getRoomId() == roomId) 
            {
                try 
                {
                    g.getClient().getOb().notifyClientsGameStarted(rmiService);
                } 
                catch (RemoteException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    public static synchronized void notifyGameRoomFinished(int roomId)
    {
        ClientRoom g;
        for(int i=0; i<ServerImplementation.GameClients.size();i++)
        {
            g = ServerImplementation.GameClients.get(i);
            if(g.getRoomId() == roomId) 
            {
                try 
                {
                    g.getClient().getOb().notifyClientsGameFinished(rmiService);
                } 
                catch (RemoteException ex) 
                {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    /**
     * disconnectClient (RMI) Disconnette un utente dal gioco
     * @param usr Username utente
     * @throws RemoteException 
     */
    @Override
    public void disconnectClient(String usr) throws RemoteException 
    {
        if(usr.equals("")) return;
        if(ServerDBInterface.ClientDisconnect(usr))
        ServerManager.addLogData("New user disconnected: (Username) "+usr);
    }
    /**
     * LobbyData (RMI) Richiesta di aggiornamento dei dati di intestazione delle stanze di gioco
     * @return I dati di intestazione delle stanze di gioco
     * @throws RemoteException 
     */
    @Override
    public LobbyData getLobbyRooms() throws RemoteException
    {
        ServerManager.addLogData("New request of lobby rooms");
        ServerManager.lobby = ServerManager.rooms.createLobbyData();
        return ServerManager.lobby;
    }
    /**
     * getLastRoomID (RMI) Richiesta dell'ultimo ID delle stanze create
     * @return Ultimo id di stanza creata
     * @throws RemoteException 
     */
    @Override
    public int getLastRoomID() throws RemoteException 
    {
        return ServerManager.rooms.getLastID();
    }
    /**
     * addRoom (RMI) Aggiunge una stanza alla lista di stanze
     * @param r Ogetto stanza
     * @throws RemoteException 
     */
    @Override
    public void addRoom(Room r) throws RemoteException 
    {
        ServerManager.addLogData(r.getAdmin()+" added new room: "+r.getId()+" - "+r.getRoomName());
        ServerManager.rooms.addRoom(r);
        ServerManager.rooms.setDataChanged(true);
    }
    /***
     * enterRoom (RMI) Aggiunge un utente alla stanza di gioco
     * @param o Observer client
     * @param roomId Id della stanza
     * @param usr Ogetto utente
     * @return true se l'operazione è andata a buon fine
     * @throws RemoteException 
     */
    @Override
    public boolean enterRoom(RemoteObserver o,int roomId,User usr) throws RemoteException 
    {
        try
        {
            Room r = ServerManager.rooms.getRoom(roomId);
            if(r.getPlayersIn() >= r.getPlayersNeeded()) return false;
            ServerManager.addLogData(usr.getUsername()+" entered room: "+roomId);
            usr.setStatus(UserStatus.NotReady);
            r.addPlayer(usr);
            ServerDBInterface.clientEnterRoom(roomId,usr.getUsername());
            ServerManager.rooms.setDataChanged(true);
            ServerManager.games.setDataChanged();
            addGameObserver(o,roomId);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;   
        }
    }
    /***
     * addGameObserver Aggiunge un Observer client alla lista di observer nelle room
     * @param o Ogetto Observer client
     * @throws RemoteException 
     */
    public void addGameObserver(RemoteObserver o,int roomId) throws RemoteException
    {
        this.removeClientObserver(o);
        ClientObserver mo = new ClientObserver(o);
        ClientRoom go = new ClientRoom(roomId,mo);
        GameClients.add(go);
        String s = "Client observer %s entered in room";
        s = String.format(s, mo.getObId());
        ServerManager.addLogData(s);
    }
    /***
     * removeGameObserver Rimuove un Observer client alla lista di observer nelle room
     * @param o Ogetto Observer client
     * @throws RemoteException 
     */
    public void removeGameObserver(RemoteObserver o) throws RemoteException
    {
        ClientObserver w; 
        ClientRoom g;
        for(int i=0; i<GameClients.size(); i++){
            g = GameClients.get(i);
            w = g.getClient();
            if(w.getOb().equals(o)){
                try
                {
                    deleteObserver(w);
                    ServerManager.addLogData("New client left room: (ID) "+w.getObId());
                    GameClients.remove(g);
                    break;
                }
                catch(Exception e)
                {
                    ServerManager.addLogData("Cannot delete client: (ID) "+w.getObId()+" Reason: "+e.toString());
                }
            }
        }
    }
    /**
     * leaveRoom (RMI) Rimuove un utente dalla stanza in cui si trova
     * Se la stanza conteneva solo quell'utente viene eliminata
     * @param o Ogetto Observer client
     * @param usr Ogetto utente
     * @throws RemoteException 
     */
    @Override
    public void leaveRoom(RemoteObserver o,User usr) throws RemoteException 
    {
        Room r = ServerManager.rooms.getRoomWherePlayer(usr);
        if(ServerManager.rooms.removePlayerFromRoom(usr))
        {
            ServerDBInterface.clientLeaveRoom(usr.getUsername());
            ServerManager.addLogData(usr.getUsername()+" left room: "+r.getId());
            ServerManager.rooms.setDataChanged(true);
            ServerManager.games.removePlayerFromGame(usr);
            ServerManager.games.setDataChanged();
            removeGameObserver(o);
            addClientObserver(o);
        }
        if(r == null) return;
        if(r.getPlayersIn() == 0)
        {
            ServerManager.rooms.removeRoom(r);
            ServerManager.games.deleteGame(r.getId());
            ServerManager.addLogData("Room ID "+r.getId()+" has been removed because no players are inside");
            ServerManager.rooms.setDataChanged(true);
            ServerManager.games.setDataChanged();
        }
    }
    /**
     * changePlayerStatus (RMI) Cambia lo stato dell'utente
     * Se la stanza contiene tutti gli utenti pronti al gioco viene creato l'oggetto che rappresenta il game
     * @param usr Ogetto utente
     * @param status Ogetto stato
     * @throws RemoteException 
     */
    @Override
    public void changePlayerStatus(User usr, UserStatus status) throws RemoteException 
    {
        Room r = ServerManager.rooms.getRoomWherePlayer(usr);
        r.changePlayerStatus(usr, status);
        if(ServerManager.rooms.isRoomReadyToPlay(r.getId()) && !ServerManager.games.hasRoomGame(r.getId())) 
        {
            ServerManager.createGame(r.getId());
            notifyGameRoomStarted(r.getId());
            if(!r.getDbUpdateStatus() )
            {
                if(ServerDBInterface.addRoom(r))r.setDbUpdateStatus();
            }
            
        }
        ServerManager.rooms.setDataChanged(true);
        ServerManager.games.setDataChanged();
    }
    
    /**
     * emailAlreadyTaken (RMI) Controlla se l'email esiste già
     * @param email Email da controllare
     * @return true se l'email esiste
     * @throws RemoteException 
     */
    @Override
    public boolean emailAlreadyTaken(String email) throws RemoteException 
    {
        return ServerDBInterface.emailAlreadyTaken(email);
    }
    /**
     * userAlreadyTaken (RMI) Controlla se l'username esiste già
     * @param user Username da controllare
     * @return true se lo username esiste
     * @throws RemoteException 
     */
    @Override
    public boolean userAlreadyTaken(String user) throws RemoteException 
    {
        return ServerDBInterface.userAlreadyTaken(user);
    }
    /***
     * getRoomById (RMI) Ritorna l'oggetto Stanza rappresentato da un ID
     * @param id Id stanza
     * @return ogetto Stanza
     * @throws RemoteException 
     */
    @Override
    public Room getRoomById(int id) throws RemoteException 
    {
        return ServerManager.rooms.getRoom(id);
    }
    /**
     * getOnlineCount (RMI) Ritorna il numero di utenti connessi
     * @return Numero di utenti connessi
     * @throws RemoteException 
     */
    @Override
    public int getOnlineCount() throws RemoteException 
    {
        return ServerManager.ObserversOnline();
    }

    @Override
    public boolean recoverPassword(String email) throws RemoteException 
    {
        String newPassword = Utility.randomAlphaNumeric(10);
        String passCrypto = Utility.StringMD5Hash(newPassword);
        String bodyMail = Utility.changePasswordEmailBody("", newPassword);
        boolean b = Utility.sendEmail(email, bodyMail);
        if(!b) return false;
        boolean b2 = ServerDBInterface.changePassword(email, passCrypto);
        return b && b2;
    }

    @Override
    public int checkWord(String word, int roomId,String username) throws RemoteException 
    {
        Game[] games = ServerManager.games.getGamesArray();
        Game game = null; 
        
        for(int i=0; i<games.length; i++){
            if(games[i].getRoomID() == roomId){
               game = games[i];
               break;
            }
        }
        //estrapolo matrice e cerco parola 
        String[][] matrix = game.getMatrix();
        //Check parola in dizionario 
        
        int score = 0;
        boolean exist = ServerManager._serverDictionary.exists(word.toLowerCase());
        
        if(exist) score = WordFinder.pointsFromWord(word, matrix);
        ServerDBInterface.addWordOfPlayer(username, word, score, roomId, game.getID(),(exist)? 1 : 0);
        if(score != 0)
        {
            ServerManager.games.addScoreToPlayer(roomId, score, username);
            ServerDBInterface.addScoreToPlayer(username, score);
            ServerManager.games.setDataChanged();
        }
        return score;
        
    }

    @Override
    public String[] getMyStatistics(String user) throws RemoteException {
        return ServerDBInterface.getMyStatistics(user);
    }

    @Override
    public String[][] getStatisticPoint1() throws RemoteException {
        return ServerDBInterface.getStatisticPoint1();
    }
      
    
   
}
