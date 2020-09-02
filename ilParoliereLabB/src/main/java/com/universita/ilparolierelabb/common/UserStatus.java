package com.universita.ilparolierelabb.common;

import java.io.Serializable;

/**
 * Enumerativo usato in RMI, che rappresenta lo stato di un giocatore
 * @author Momo
 */
public enum UserStatus implements Serializable
{
    Offline(0), Online(1), InRoom(2), InGame(3),Ready(4),NotReady(5);
    
    private final int value;

    private UserStatus(int value) 
    {
        this.value = value;
    }

    public int getValue() 
    {
        return value;
    }
    public String getName()    
    {
        String s = "";
        switch(value)
        {
            case 0: s = "Offline"; break;
            case 1: s = "Online"; break;
            case 2: s = "InRoom"; break;
            case 3: s = "InGame"; break;
            case 4: s = "Ready"; break;
            case 5: s = "Not Ready"; break;
        }
        return s;
    }
}
