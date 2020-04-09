/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.client.frames.User;
import com.universita.ilparolierelabb.client.frames.*;
import com.universita.ilparolierelabb.server.GameRooms;
import com.universita.ilparolierelabb.server.Room;

/**
 *
 * @author Momo
 */
public class ClientManager 
{
    public static User currentuser = new User();
    
    public static GameRooms gameRooms = new GameRooms();
    
    private static ClientMainFrame clientFrame;
    
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
        ClientManager.gameRooms = ClientImplementation.getGameRooms();
    }
    public static int getLastRoomID()
    {
        return ClientImplementation.getLastRoomID();
    }
    public static void addRoom(Room r)
    {
        ClientImplementation.addRoom(r);
    }
    public static boolean enterRoom(int roomID,String usr) 
    {
        return ClientImplementation.enterRoom(roomID,usr);
    }
    public static void leaveRoom(String usr) 
    {
        ClientImplementation.leaveRoom(usr);
    }
    public static void refreshRooms() 
    {
        clientFrame.refreshRooms();
    }
    public static void Run()
    {
        clientFrame = new ClientMainFrame();
        clientFrame.setVisible(true);
        ClientImplementation.ClientOnline();
    }

    
    
}
