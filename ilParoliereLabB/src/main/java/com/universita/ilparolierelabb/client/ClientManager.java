/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.common.User;
import com.universita.ilparolierelabb.client.frames.*;
import com.universita.ilparolierelabb.common.LobbyData;
import com.universita.ilparolierelabb.common.UserStatus;
import com.universita.ilparolierelabb.common.Rooms;
import com.universita.ilparolierelabb.common.Room;

/**
 *
 * @author Momo
 */
public class ClientManager 
{
    public static User currentuser = new User();
    public static ClientLobbyFrame lobbyFrame;
    public static ClientGameFrame gameFrame;
    public static LobbyData lobby;
    
    public static void Launch()
    {
        ClientImplementation.Launch();
    }
    public static Boolean getLogin(String usr,String psw)
    {
        return ClientImplementation.getLogin(usr,psw);
    }
    public static Boolean clientRegister(RegisterData d)
    {
        return ClientImplementation.clientRegister(d);
    }
    public static void DisconnectFromServer(String usr)
    {
        ClientImplementation.DisconnectFromServer(usr);
    }
    public static void Login() 
    {
        new ClientLogin().setVisible(true);
    }
    public static RegistrationResult Register()
    {
        return ClientRegistration.Register();
    }
    public static Boolean registerInWaiting(String usr)
    {
        return ClientImplementation.registerInWaiting(usr);
    }
    public static Boolean registerAccount(String code)
    {
        return ClientImplementation.registerAccount(code);
    }
    public static boolean clientIsLogged(String usr) 
    {
        return ClientImplementation.clientIsLogged(usr);
    }
    public static void getLobbyRooms()
    {
        ClientManager.lobby = ClientImplementation.getLobbyRooms();
    }
    public static int getLastRoomID()
    {
        return ClientImplementation.getLastRoomID();
    }
    public static void addRoom(Room r)
    {
        ClientImplementation.addRoom(r);
    }
    public static boolean enterRoom(int roomID,User usr) 
    {
        return ClientImplementation.enterRoom(roomID,usr);
    }
    public static void leaveRoom(User usr) 
    {
        ClientImplementation.leaveRoom(usr);
    }
    public static void changePlayerStatus(User usr,UserStatus status) 
    {
        ClientImplementation.changePlayerStatus(usr,status);
    }
    public static void refreshLobbyRooms() 
    {
        lobbyFrame.refreshRooms();
        
    }
    public static void refreshGameRoom()
    {
        gameFrame.refreshRooms();
    }
    public static void Run(Boolean connect)
    {
        lobbyFrame = new ClientLobbyFrame();
        gameFrame = new ClientGameFrame();
        lobbyFrame.setVisible(true);
        if(!connect) return;
        ClientImplementation.ClientOnline();
    }
    
    // @author AndreaGirola
    public static boolean emailAlreadyTaken(String email) {
        return ClientImplementation.emailAlreadyTaken(email);
    }

    // @author AndreaGirola
    public static boolean userAlreadyTaken(String usr) {
        return ClientImplementation.userAlreadyTaken(usr); 
    }

    public static void refreshGameInitTimer(int timerCount) 
    {
        //clientFrame.refreshGameInitTimer(roomId,timerCount);
        gameFrame.refreshGameInitTimer(timerCount);
    }

    public static void setGameMatrix(String[][] matrix) 
    {
         //clientFrame.setGameMatrix(roomId,matrix);
        gameFrame.setGameMatrix(matrix);
    }
    public static Room getRoomById(int roomId)
    {
        return ClientImplementation.getRoomById(roomId); 
    }
    public static void refreshOnlineCount()
    {
        ClientLobbyFrame.Par_lblUtentiConnessi.setText(ClientImplementation.getOnlineCount()+"");
    }
    
    public static void refreshGameTimer(int timerCount)
    {
        gameFrame.refreshGameTimer(timerCount);
    }
    
    public static boolean recoverPassword(String email)
    {
      return ClientImplementation.recoverPassword(email);
      
    }
    public static void startGame()
    {
        gameFrame.startGame();
    }
    public static void stopGame()
    {
        gameFrame.stopGame();
    }
    
    public static int checkWord(String word, int roomId,String username)
    {
         return ClientImplementation.checkWord(word, roomId,username);
    }
    public static void WordGuessingState(boolean state)
    {
        gameFrame.setVisibilityWordCheck(state);
    }
    public static void RefreshGameTable()
    {
        gameFrame.refreshRooms();
    }
    public static void setGameHeaderMessage(String msg)
    {
        gameFrame.setGameHeaderMessage(msg);
    }
    
    public static String[] getMyStatistics(String user){
        return ClientImplementation.getMyStatistics(user);
     }
    
    
}
