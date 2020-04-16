/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.common.User;
import com.universita.ilparolierelabb.client.frames.*;
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
    
    public static Rooms rooms = new Rooms();
    
    public static ClientLobbyFrame lobbyFrame;
    public static ClientGameFrame gameFrame;
    
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
    public static void getGameRooms()
    {
        ClientManager.rooms = ClientImplementation.getGameRooms();
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
    public static void refreshRooms() 
    {
        lobbyFrame.refreshRooms();
        gameFrame.refreshRooms();
    }
    public static void Run()
    {
        lobbyFrame = new ClientLobbyFrame();
        gameFrame = new ClientGameFrame();
        lobbyFrame.setVisible(true);
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

    public static void refreshGameInitTimer(int roomId, int timerCount) 
    {
        //clientFrame.refreshGameInitTimer(roomId,timerCount);
    }

    static void setGameMatrix(int roomId, String[][] matrix) 
    {
         //clientFrame.setGameMatrix(roomId,matrix);
    }

    
    
}
