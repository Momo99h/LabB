/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Momo
 */
public interface toServerRMI extends Remote 
{
    public Boolean clientLogin(String usr,String psw) throws RemoteException;
}
