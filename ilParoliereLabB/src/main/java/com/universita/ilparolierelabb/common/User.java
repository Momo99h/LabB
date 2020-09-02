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
package com.universita.ilparolierelabb.common;

import com.universita.ilparolierelabb.common.UserStatus;
import java.io.Serializable;

/**
 * Classe usata in RMI, rappresenta un utente
 * @author Momo
 */
public class User implements Serializable
{
    private String _username;
    private int _totalPoints = 0;
    private int _gametotalPoints = 0;
    private UserStatus _status = UserStatus.Offline;
    /**
     * Costruttore vuoto
     */
    public User(){}
    
    /**
     * Ottiene lo username
     * @return Username
     */
    public String getUsername(){return this._username;}
    /**
     * Ottiene lo stato del giocatore
     * @return Lo stato del giocatore
     */
    public UserStatus getStatus(){return this._status;}
    /**
     * Ottiene i punti totali del giocatore
     * @return Punti totali
     */
    public int getTotalPoints(){return this._totalPoints;}
    /**
     * Ottiene i punti nella fase di gioco del giocatore
     * @return Punti nella fase di gioco
     */
    public int getGamePoints(){return this._gametotalPoints;}
    
    /**
     * Imposta lo username
     * @param val Valore da impostare
     */
    public void setUsername(String   val){this._username = val;}
    /**
     * Imposta lo stato
     * @param val Valore da impostare
     */
    public void setStatus(UserStatus val){this._status = val;}
    /**
     * Imposta lo i punti totali
     * @param val Valore da impostare
     */
    public void setTotalPoints(int val){this._totalPoints = val;}
    /**
     * Imposta lo i punti della fase di gioco
     * @param val Valore da impostare
     */
    public void setGameTotalPoints(int val){this._gametotalPoints = val;}

    
}
