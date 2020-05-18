/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.UserStatus;
import com.universita.ilparolierelabb.common.sql.*;

/**
 * ServerDBInterface Tutte le richieste a database vengono eseguite qui.
 *
 * @author Momo
 */
public class ServerDBInterface 
{
    private static MySQLEngine _db = new MySQLEngine();
    
    /***
     * setDBReference Imposta il motore di connessione al database.
     * 
     * @param db Motore di connessione già configurato.
     */
    public static void setDBReference(MySQLEngine db)
    {
        _db = db;
    }
    /***
     * HasAdmin controlla nel database se esistono amministratori per il server.
     * 
     * @return ritorna true se esistono amministratori.
     */
    public static boolean HasAdmin() 
    {
        String query = "Select Count(*) from Admins";
        ResultTable return_val = _db.executeQueryRead(query);
        return !return_val.get(0, 0).equals("0");
    }
    /***
     * RegisterAdmin Inserisce un amministratore nel database.
     * 
     * @param usr Username dell'amministratore
     * @param psw Password dell'amministratore
     * @return true se l'istruzione è andata a buon fine.
     */
    public static boolean RegisterAdmin(String usr,String psw) 
    {
        String query = "Insert into Admins(Username,Password) Values ('%s','%s')";
        query = String.format(query,usr,psw);
        return _db.executeQuery(query);
    }
    /***
     * RegisterAccount Inserisce un account utente nel database.
     * @param d Oggetto di tipo RegisterData che rappresenta i dati di registrazione dell'account.
     * @return true se l'istruzione è andata a buon fine.
     */
    public static boolean RegisterAccount(RegisterData d) 
    {
        String query = "Insert into Users(Nome,Cognome,Nickname,Email,Password) Values ('%s','%s','%s','%s','%s')";
        query = String.format(query,d.getName(),d.getSurname(),d.getUsername(),d.getEmail(),d.getPassword());
        Boolean b = _db.executeQuery(query);
        query =  "Insert into UsersState(Nickname,OnlineStatus,IdRoom) Values ('%s','%s','%s')";
        query = String.format(query,d.getUsername(),UserStatus.Offline.getValue(),0);
        Boolean b2 = _db.executeQuery(query);
        query =  "Insert into UsersScore(Nickname,TotalPoints) Values ('%s','%s')";
        query = String.format(query,d.getUsername(),0);
        return b && b2 && _db.executeQuery(query);
    }
    /***
     * LoginAdmin Esegue una query per la verifica dei dati di login di un amministratore.
     * @param usr Username dell'amministratore
     * @param psw Password dell'amministratore
     * @return true se il login è riuscito.
     */
    public static boolean LoginAdmin(String usr,String psw) 
    {
        String query = "Select Count(*) from Admins Where Username='%s' AND Password='%s'";
        query = String.format(query,usr,psw);
        ResultTable return_val = _db.executeQueryRead(query);
        return !return_val.get(0, 0).equals("0");
    }
    /***
     * ClientLogin Esegue una query per la verifica dei dati di login di un utente.
     * @param usr Username dell'utente
     * @param psw Password dell'utente
     * @return true se il login è riuscito.
     */
    public static boolean ClientLogin(String usr,String psw)
    {
        String query = "Select Count(*) from Users Where Nickname='%s' AND Password='%s'";
        query = String.format(query,usr,psw);
        ResultTable return_val = _db.executeQueryRead(query);
        Boolean b = !return_val.get(0, 0).equals("0");
        query = "Update UsersState set OnlineStatus = '%s' where Nickname = '%s'";
        query = String.format(query,UserStatus.Online.getValue(),usr);
        return b && _db.executeQuery(query);
    }
    /***
     * ClientDisconnect Aggiorna lo stato offline dell'utente a livello database.
     * @param usr Username dell'utente
     * @return true se l'istruzione è andata a buon fine.
     */
    public static boolean ClientDisconnect(String usr)
    {
        String query = "Update UsersState set OnlineStatus = '%s' where Nickname = '%s'";
        query = String.format(query,UserStatus.Offline.getValue(),usr);
        return _db.executeQuery(query);
    }
    /***
     * OnlineClientsCount Conta il numero di utenti online.
     * @return il numero intero di utenti online.
     */
    public static int OnlineClientsCount()
    {
        String query = "Select Count(*) from UsersState Where NOT OnlineStatus='%s'";
        query = String.format(query,0);
        ResultTable return_val = _db.executeQueryRead(query);
        return Integer.parseInt(return_val.get(0, 0));
    }
    /***
     * clientIsLogged Controlla se un utente è già all'interno del gioco.
     * @param usr Username dell'utente
     * @return true se l'utente ha già effettuato il login
     */
    public static boolean clientIsLogged(String usr) 
    {
        String query = "Select Count(*) from UsersState Where NOT OnlineStatus='%s' AND Nickname ='%s'";
        query = String.format(query,0,usr);
        ResultTable return_val = _db.executeQueryRead(query);
        return !return_val.get(0, 0).equals("0");
    }
    /***
     * resetUsersState Ripristina alle condizioni iniziali lo stato di tutti gli utenti.
     */
    public static void resetUsersState()
    {
        String query = "Update UsersState set OnlineStatus='%s'";
        query = String.format(query,0);
        _db.executeQuery(query);
    }            
    /***
     * clientEnterRoom Aggiorna lo stato di un utente in una stanza a livello database
     * @param roomId Id della stanza in cui l'utente è entrato.
     * @param usr Username dell'utente.
     * @return true se l'istruzione è andata a buon fine.
     */
    public static boolean clientEnterRoom(int roomId, String usr) 
    {
        String query = "Update UsersState set OnlineStatus = '%s',IdRoom = '%s' where Nickname = '%s'";
        query = String.format(query,UserStatus.InRoom.getValue(),roomId,usr);
        return _db.executeQuery(query);
    }
    /***
     * clientLeaveRoom Aggiorna lo stato di un utente nella lobby a livello database.
     * @param usr Username dell'utente.
     * @return true se l'istruzione è andata a buon fine.
     */
    public static boolean clientLeaveRoom(String usr) 
    {
        String query = "Update UsersState set OnlineStatus = '%s',IdRoom = '%s' where Nickname = '%s'";
        query = String.format(query,UserStatus.Online.getValue(),0,usr);
        return _db.executeQuery(query);
    }
    /***
     * emailAlreadyTaken controlla se l'email è già presente a livello database.
     * @param email Email da controllare.
     * @return true se l'email è gia presente nel database.
     */
    public static boolean emailAlreadyTaken(String email)
    {
        String query = "SELECT COUNT(*) FROM Users WHERE Email='%s'";
        query = String.format(query,email);
        ResultTable val = _db.executeQueryRead(query);
        return (!val.get(0, 0).equals("0")); 
    }
    /**
     * userAlreadyTaken controlla se l'username è già presente a livello database.
     * @param nickname Username da controllare.
     * @return true se l'username è gia presente nel database.
     */
    public static boolean userAlreadyTaken(String nickname)
    {
        String query = "SELECT COUNT(*) FROM Users WHERE Nickname='%s'";
        query = String.format(query,nickname);
        ResultTable val = _db.executeQueryRead(query);
        return (!val.get(0, 0).equals("0")); 
    }
    public static boolean changePassword(String email,String password)
    {
        String query = "Update Users set Password = '%s' WHERE Email = '%s' ";
        query = String.format(query,password,email);
        return _db.executeQuery(query);
    }

}
