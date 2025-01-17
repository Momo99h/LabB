/**
 * 
 * Progetto laboratorio B
 * 
 * Mohamed Hussein,   737787
 * Anrea Girola,      740724
 * Vanessa Squillace, 728078
 * Simone Spagnolo,   737742
 * 
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.RegisterData;
import com.universita.ilparolierelabb.common.Game;
import com.universita.ilparolierelabb.common.Room;
import com.universita.ilparolierelabb.common.UserStatus;
import com.universita.ilparolierelabb.common.sql.*;

/**
 * ServerDBInterface Tutte le richieste a database vengono eseguite qui.
 *
 * @author Momo
 */
public class ServerDBInterface 
{
    //private static MySQLEngine _db = new MySQLEngine();
    private static PostgreSQLEngine _db = new PostgreSQLEngine();
    /***
     * setDBReference Imposta il motore di connessione al database.
     * 
     * @param db Motore di connessione già configurato.
     */
    public static void setDBReference(PostgreSQLEngine db)
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
    /**
     * Cambia la password di un account utente
     * @param email Email dell'utente
     * @param password Nuova password dell'utente
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean changePassword(String email,String password)
    {
        String query = "Update Users set Password = '%s' WHERE Email = '%s' ";
        query = String.format(query,password,email);
        return _db.executeQuery(query);
    }
    /**
     * Aggiunge punti al punteggio totale di un giocatore
     * @param username Nome utente del giocatore
     * @param score Punteggio da aggiungere
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean addScoreToPlayer(String username, int score) 
    {
        String query = "Select TotalPoints from UsersScore where Nickname = '%s'";
        query = String.format(query, username);
        ResultTable val = _db.executeQueryRead(query);
        int lastScore = Integer.parseInt(val.get(0, 0));
        int _score = score + lastScore;
        query = "Update UsersScore set TotalPoints = '%s' where Nickname = '%s'";
        query = String.format(query, _score,username);
        return _db.executeQuery(query);
    }
    /**
     * Inserisce una parola scritta dal giocatore 
     * @param username Username del giocatore
     * @param word Parola inserita
     * @param score Punteggio della parola
     * @param roomId Identificativo della stanza
     * @param gameId Identificativo della fase di gioco
     * @param exist Esistenza nel dizionario
     * @param explanation Definizione della parola
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean addWordOfPlayer(String username,String word,int score,int roomId,int gameId,int exist,String explanation)
    {
        String query = "Insert into UsersWords (RoomID,GameID,Nickname,Word,InDictionary,Score,Explanation) Values ('%s','%s','%s','%s','%s','%s','%s')";
        query = String.format(query, roomId,gameId,username,word,exist,score,explanation);
        return _db.executeQuery(query);
    }
    /**
     * Aggiunge una stanza
     * @param r Oggetto che rappresenta stanza
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean addRoom(Room r)
    {
        String query = "Insert into Rooms (RoomId,Name,Players,PlayersNicknames) Values ('%s','%s','%s','%s')";
        String users = r.getListPlayerIn(";");
        query = String.format(query, r.getId(),r.getRoomName(),r.getPlayersNeeded(), users );
        return _db.executeQuery(query);
    }
    /**
     * Ottiene l'ultimo identificativo delle stanze salvate
     * @return Identificativo della stanza, 0 se ha errori/non esiste
     */
    public static int getRoomLastId()
    {
        String query= "SELECT MAX(RoomId) FROM Rooms";
        ResultTable val = _db.executeQueryRead(query); 
        try
        {
            return Integer.parseInt(val.get(0,0));
        }
        catch(Exception i)
        {
            return 0;
        }
        
    }
    /**
     * Aggiunge un fase di gioco
     * @param g Oggetto che rappresenta un fase di gioco
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean addGame(Game g)
    {
        String query = "Insert into Games (RoomID,GameID,Players,PlayersNicknames,PlayersNicknamesEnd,GameFinalScore) Values ('%s','%s','%s','%s','%s','%s')";
        String users = g.getListInitialPlayersIn(";");
        String endusers = g.getListEndPlayersIn(";");
        query = String.format(query, g.getRoomID(),g.getID(),g.getPlayersIn(), users,endusers,g.getBestGameScore() );
        return _db.executeQuery(query);
    }
    /**
     * Ottiene le statistiche di un giocatore
     * @param user Nome utente del giocatore
     * @return Array di stringhe che rappresenza le statistiche, null se ha errori
     */
    public static String[] getMyStatistics(String user){
        String[] stats = new String[3];
        try{
            String query0 = "SELECT TotalPoints FROM UsersScore WHERE Nickname = '%s'";
            query0 = String.format(query0, user);
            ResultTable val = _db.executeQueryRead(query0);
            stats[0] = val.get(0,0);
            
            String query1 = "SELECT COUNT(Word) FROM UsersWords WHERE Nickname = '%s' AND Score <> '0'";
            query1 = String.format(query1, user);
            val = _db.executeQueryRead(query1);
            stats[1] = val.get(0,0);
            
            String query2 = "SELECT COUNT(Word) FROM UsersWords WHERE Nickname = '%s'";
            query2 = String.format(query2, user);
            val = _db.executeQueryRead(query2);
            stats[2] = val.get(0,0);
            
        } catch (Exception e) { 
            return null;
        }
        
        
        return stats; 
    }
    /**
     * Ottiene le statistiche del punto 1 richieste nelle specifiche del progetto
     * @return Matrice di stringhe
     */
    public static String[][] getStatisticPoint1(){
        try
        {
            String query0 = "SELECT DISTINCT ON(RoomId, GameId) RoomId, GameId, NickName, MAX(TotalPoints) AS TotalPoints\n" +
                            "FROM (SELECT SUM(Score) AS TotalPoints, GameId , RoomId, NickName\n" +
                            "      FROM UsersWords \n" +
                            "      WHERE NickName=NickName\n" +
                            "	  GROUP BY GameId, RoomId, NickName\n" +
                            "      ORDER BY TotalPoints DESC) MaxScore\n" +
                            "GROUP BY GameId, RoomId, NickName\n" +
                            "ORDER BY RoomId desc , GameId desc";
            ResultTable val = _db.executeQueryRead(query0);
            String[][] ret = new String[val.getRowCount()][val.getColumCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
               for(int z = 0; z < val.getColumCount();z++)
               {
                    ret[i][z] = val.get(i, z);
               }  
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Ottiene il percoso del dizionario
     * @return Percoso del dizionario
     */
    public static String getDictionaryPath()
    {
        try
        {
            String query0 = "SELECT * FROM Settings";
            ResultTable val = _db.executeQueryRead(query0);
            return val.get(0, 0);
        } 
        catch(Exception e)
        {
            e.printStackTrace();
            return "";
        }     
       
    }
    /**
     * Imposta il percorso del dizionario
     * @param path Percorso del dizionario
     * @return true se l'operazione è andata a buon fine
     */
    public static Boolean setDictionaryPath(String path)
    {
        try
        {
            String query0 = "Update Settings Set DictionaryPath = '%s'";
            String internalPath = path;//.replace("\\", "\\\\");
            query0 = String.format(query0, internalPath);
            return _db.executeQuery(query0);
        } 
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        } 
    }
    /**
     * Ottiene le statistiche del punto 1b richieste nelle specifiche del progetto
     * @return Array di stringhe
     */
    public static String[] getStatisticPoint1b() 
    {
        try
        {
            String query0 = "SELECT NickName, MAX(GameSession) AS GameSession\n" +
                            "FROM (\n" +
                            "SELECT NickName, COUNT(Distinct (RoomId,Gameid)) AS GameSession \n" +
                            "FROM UsersWords\n" +
                            "WHERE NickName= NickName\n" +
                            "GROUP BY NickName) NSessions\n" +
                            "GROUP BY NickName\n" +
                            "ORDER BY GameSession DESC LIMIT 1";
            
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[] ret = new String[10];
                for(int i = 0; i < 10; i++)
                {
                    ret[i] = "";
                }
                return ret;
            }
            String[] ret = new String[2];
            ret[0] = val.get(0, 0);
            ret[1] = val.get(0, 1);
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        } 
    }
    /**
     * Ottiene le statistiche del punto 1c richieste nelle specifiche del progetto
     * @return Matrice di stringhe
     */
    public static String[][] getStatisticPoint1c() {
        try
        {
            String query0 = "SELECT DISTINCT ON (RoomId , GameId)RoomId,GameId, MAX(AverageScore) AS AverageScore, NickName\n" +
                            "FROM (\n" +
                            "SELECT AVG(Score):: numeric(6,2) AS AverageScore, GameId , RoomId, NickName \n" +
                            "      FROM UsersWords \n" +
                            "      WHERE NickName=NickName\n" +
                            "      GROUP BY GameId, RoomId, NickName) AvgScore\n" +
                            "GROUP BY GameId, NickName, RoomId\n" +
                            "ORDER BY GameId DESC";
                                        ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[][] ret = new String[1][10];
                for(int i = 0; i < 10; i++)
                {
                    ret[0][i] = "";
                }
                return ret;
            }
            String[][] ret = new String[val.getRowCount()][val.getColumCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
               for(int z = 0; z < val.getColumCount();z++)
               {
                    ret[i][z] = val.get(i, z);
               }  
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Ottiene le statistiche del punto 1e richieste nelle specifiche del progetto
     * @return Array di stringhe
     */
    public static String[] getStatisticPoint1e() 
    {
         try
        {
            String query0 = "SELECT NickName, MAX(NWords) AS MaxWords \n" +
                            "FROM(\n" +
                            "SELECT NickName, COUNT(Word) AS NWords\n" +
                            "FROM UsersWords\n" +
                            "WHERE Score= '0' AND Explanation= 'Parola non derivabile dalla griglia' OR \n" +
                            "                     indictionary = '0'\n" +
                            "GROUP BY NickName ) CountWords\n" +
                            "GROUP BY NickName\n" +
                            "ORDER BY MaxWords DESC LIMIT 1";
            
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[] ret = new String[10];
                for(int i = 0; i < 10; i++)
                {
                    ret[i] = "";
                }
                return ret;
            }
            String[] ret = new String[2];
            ret[0] = val.get(0, 0);
            ret[1] = val.get(0, 1);
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        } 
    }
    /**
     * Ottiene le statistiche del punto 2 richieste nelle specifiche del progetto
     * @return Matrice di stringhe
     */
    public static String[][] getStatisticPoint2() 
    {
        try
        {
            String query0 = "SELECT Word , COUNT(Word) AS Occorrenze\n" +
                            "FROM UsersWords\n" +
                            "WHERE Score > 0 \n" +
                            "GROUP BY Word \n" +
                            "ORDER BY Word ASC";
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[][] ret = new String[1][10];
                for(int i = 0; i < 10; i++)
                {
                    ret[0][i] = "";
                }
                return ret;
            }
            String[][] ret = new String[val.getRowCount()][val.getColumCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
               for(int z = 0; z < val.getColumCount();z++)
               {
                    ret[i][z] = val.get(i, z);
               }  
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Ottiene le statistiche del punto 3 richieste nelle specifiche del progetto
     * @return Matrice di stringhe
     */
    public static String[][] getStatisticPoint3()
    {
        try
        {
            String query0 = "SELECT DISTINCT ON (RoomId,GameId) RoomId,GameId, Word, MAX(Score) AS MaxIncrementation \n" +
                            "FROM UsersWords WHERE Score != 0\n" +
                            "GROUP BY Word, Score, GameId,RoomId\n" +
                            "ORDER BY GameId,RoomId, MaxIncrementation DESC";
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[][] ret = new String[1][10];
                for(int i = 0; i < 10; i++)
                {
                    ret[0][i] = "";
                }
                return ret;
            }
            String[][] ret = new String[val.getRowCount()][val.getColumCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
               for(int z = 0; z < val.getColumCount();z++)
               {
                    ret[i][z] = val.get(i, z);
               }  
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Ottiene le statistiche del punto 4 richieste nelle specifiche del progetto
     * @return Matrice di stringhe
     */
    public static String[][] getStatisticPoint4() 
    {
        try
        {
            String query0 = "SELECT AVG(GameId)::numeric(6,2) AS AvgGamesRounds, Players\n" +
                            "FROM(\n" +
                            "SELECT COUNT(DISTINCT RoomId) AS RoomId, COUNT(DISTINCT GameId) AS GameId, Players\n" +
                            "FROM Games\n" +
                            "GROUP BY RoomId, Players) CountRoom\n" +
                            "WHERE Players='2' OR Players='3' OR Players='4' OR Players='5' OR Players='6'\n" +
                            "GROUP BY Players";
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[][] ret = new String[1][10];
                for(int i = 0; i < 10; i++)
                {
                    ret[0][i] = "";
                }
                return ret;
            }
            String[][] ret = new String[val.getRowCount()][val.getColumCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
               for(int z = 0; z < val.getColumCount();z++)
               {
                    ret[i][z] = val.get(i, z);
               }  
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Ottiene le statistiche del punto 5 richieste nelle specifiche del progetto
     * @return Matrice di stringhe
     */
    public static String[][] getStatisticPoint5() 
    {
        try
        {
            String query0 = "SELECT MIN(GameId) AS NMinRound, MAX(GameId) AS NMaxRound, Players\n" +
                            "FROM(\n" +
                            "SELECT RoomId, COUNT(DISTINCT GameId) AS GameId, Players\n" +
                            "FROM Games\n" +
                            "GROUP BY RoomId, Players\n" +
                            "ORDER BY RoomId) CountRoom\n" +
                            "WHERE Players='2' OR Players='3' OR Players='4' OR Players='5' OR Players='6'\n" +
                            "GROUP BY Players";
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[][] ret = new String[1][10];
                for(int i = 0; i < 10; i++)
                {
                    ret[0][i] = "";
                }
                return ret;
            }
            String[][] ret = new String[val.getRowCount()][val.getColumCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
               for(int z = 0; z < val.getColumCount();z++)
               {
                    ret[i][z] = val.get(i, z);
               }  
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Ottiene le statistiche del punto 1d richieste nelle specifiche del progetto
     * @return Array di stringhe
     */
    public static String[] getStatisticPoint1d() 
    {
        try
        {
            String query0 = "SELECT Nickname,Count(Duplicati) as Duplicati\n" +
                            "FROM(\n" +
                            "	SELECT Nickname \n" +
                            "	FROM userswords\n" +
                            "	WHERE Word IN \n" +
                            "	(\n" +
                            "		SELECT Word\n" +
                            "		FROM userswords\n" +
                            "		group by Word,GameId,RoomId\n" +
                            "		HAVING COUNT(Word) > 1\n" +
                            "	)		group by Nickname,Word ) Duplicati\n" +
                            "	group by Nickname order by Duplicati DESC LIMIT 1";
            
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[] ret = new String[10];
                for(int i = 0; i < 10; i++)
                {
                    ret[i] = "";
                }
                return ret;
            }
            String[] ret = new String[2];
            ret[0] = val.get(0, 0);
            ret[1] = val.get(0, 1);
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        } 
    }
    /**
     * Inserisce la definizione di una parola
     * @param word Parola
     * @param definition Definizione della parola
     * @param RoomID Identificativo della stanza
     * @return true se l'operazione è andata a buon fine
     */
    public static Boolean insertWordDefinition(String word, String definition,int RoomID) 
    {
        try
        {
            String query = "Insert into wordsdefinitions values ('%s','%s','%s')";
            query = String.format(query, word,definition,RoomID);
            return _db.executeQuery(query);
        }
        catch(Exception e)
        {
            return false;
        }
    }
    /**
     * Ottiene le statistiche del punto 7 richieste nelle specifiche del progetto
     * @return Matrice di stringhe
     */
    public static String[][] getStatisticPoint7() 
    {
       try
        {
            String query0 = "SELECT Word , COUNT(Word) AS Occorrenze\n" +
                            "FROM public.wordsdefinitions\n" +
                            "GROUP BY Word\n" +
                            "ORDER BY Occorrenze DESC";
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[][] ret = new String[1][10];
                for(int i = 0; i < 10; i++)
                {
                    ret[0][i] = "";
                }
                return ret;
            }
            String[][] ret = new String[val.getRowCount()][val.getColumCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
               for(int z = 0; z < val.getColumCount();z++)
               {
                    ret[i][z] = val.get(i, z);
               }  
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Ottiene le statistiche del punto 8 richieste nelle specifiche del progetto
     * @return Array di stringhe
     */
    public static String[] getStatisticPoint8() 
    {
        try
        {
            String query0 = "select distinct roomid from wordsdefinitions";
            
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[] ret = new String[10];
                for(int i = 0; i < 10; i++)
                {
                    ret[i] = "";
                }
                return ret;
            }
            String[] ret = new String[val.getRowCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
                ret[i] = val.get(i, 0);
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        } 
    }
    /**
     * Aggiorna la ricorrenza delle lettere che appaiono nella matrice
     * @param matrix Matrice di gioco
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean updateLetterOccurencies(String[][] matrix) 
    {
       try
       {
           for(int i = 0; i < matrix.length; i++)
           {
                for(int j = 0; j < matrix[0].length;j++)
                {
                    String query = "Update lettersoccurencies set occurrency = occurrency + 1 where letter = '%s'";
                    query = String.format(query, matrix[i][j]);
                    return  _db.executeQuery(query);
                }
           }
       }
       catch(Exception e)
       {
           return false;
       }
       return false;
    }
    /**
     * Ottiene le statistiche del punto 6 richieste nelle specifiche del progetto
     * @return Matrice di stringhe
     */
    static String[][] getStatisticPoint6() 
    {
        try
        {
            String query0 = "SELECT * FROM public.lettersoccurencies order by letter asc";
            ResultTable val = _db.executeQueryRead(query0);
            if(val == null)
            {
                String[][] ret = new String[1][10];
                for(int i = 0; i < 10; i++)
                {
                    ret[0][i] = "";
                }
                return ret;
            }
            String[][] ret = new String[val.getRowCount()][val.getColumCount()];
            for(int i = 0; i < val.getRowCount();i++)
            {
               for(int z = 0; z < val.getColumCount();z++)
               {
                    ret[i][z] = val.get(i, z);
               }  
            }
            return ret;
            
        } catch (Exception e) 
        { 
            e.printStackTrace();
            return null;
        }
    }
    /**
     * Cambia la password dell'amministratore
     * @param adminUsername Nome utente dell'amministratore
     * @param psw Nuova password
     * @return true se l'operazione è andata a buon fine
     */
    public static boolean changeAdminPassword(String adminUsername, String psw) 
    {
        String query = "Update admins set password = '%s' where username = '%s'";
        query = String.format(query,psw, adminUsername);
        return _db.executeQuery(query);
    }

}
