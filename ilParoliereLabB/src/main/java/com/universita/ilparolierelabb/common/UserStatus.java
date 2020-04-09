/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common;

/**
 *
 * @author Momo
 */
public enum UserStatus 
{
    Offline(0), Online(1), InRoom(2), InGame(3),Ready(4);
    
    private final int value;

    private UserStatus(int value) 
    {
        this.value = value;
    }

    public int getValue() 
    {
        return value;
    }
}
