/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

import com.universita.ilparolierelabb.client.frames.*;

/**
 *
 * @author Momo
 */
public class ClientManager 
{
    public static void Launch()
    {
        ClientImplementation.Launch();
    }
    public static Boolean getLogin(String usr,String psw)
    {
        return ClientImplementation.getLogin(usr,psw);
    }
    public static void DisconnectFromServer()
    {
        ClientImplementation.DisconnectFromServer();
    }
    public static void Login() 
    {
        new ClientLogin().setVisible(true);
    }
    public static void Register()
    {
        ClientRegistration.Register();
    }
}
