/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.client.frames.*;
import com.universita.ilparolierelabb.server.GameRooms;

/**
 *
 * @author Momo
 */
public class ClientManager 
{
    public static String currentuser;
    public static GameRooms gameRooms = new GameRooms();
    public static Boolean refreshRooms = false;
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
    public static void Run()
    {     
        ClientMainFrame serverFrame = new ClientMainFrame();
        serverFrame.setVisible(true);
    }  
    
}
