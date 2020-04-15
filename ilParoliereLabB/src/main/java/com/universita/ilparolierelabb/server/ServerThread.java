/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.Game;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.Utility;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author Momo
 */
public class ServerThread extends Thread implements ActionListener
{
    private ServerFSMachine _serverStep = ServerFSMachine.Idle;
    private Game[] gameArray;
    private Game.Phase gamePhase;
    private int tempInt = 0;
    private String tempString = "";
    public static void Run()
    {
        ServerThread t = new ServerThread();
        new Timer(1000, (ActionListener) t).start();
    }
    public void actionPerformed(ActionEvent e) 
    {
        run();
    }
    @Override
    public void run()
    {
        doIdlestuff();
        doEmailstuff();
        doRoomstuff();
        doGamestuff();
    }

    private synchronized void doIdlestuff() 
    {
        if(ServerManager._ClientCountChanged)
        {
            ServerManager._ClientCountChanged = false;
            ServerImplementation.notifyClientsCount(ServerManager.ObserversOnline());
        }
        //
       _serverStep = ServerFSMachine.Email;
    }

    private synchronized void doEmailstuff() 
    {
        for(RegisterData d : ServerImplementation.registerUserWaiting)
        {
            if(!d.getEmailStatus())
            {
                String code = Utility.randomAlphaNumeric(10);
                if(Utility.sendEmail())
                {
                    d.setVerificationCode(code);
                    d.setEmailStatus(true);
                    tempString = "Email sent to %s (%s) with verification code: %s";
                    tempString = String.format(tempString, d.getEmail(),d.getUsername(),code);
                    ServerManager.addLogData(tempString);
                }
            }
            else
            {
                tempInt = d.getRemainingTime();
                d.setRemainingTime(tempInt-1);
                if(tempInt == (Settings.emailCodeTimeOut/2))
                {
                    tempString = "Username: %s has not verified email yet. Time remaining: %s seconds";
                    tempString = String.format(tempString, d.getUsername(),tempInt);
                    ServerManager.addLogData(tempString);
                }
                if(tempInt <= 0)
                {
                    tempString = "Username: %s verification time finished.";
                    tempString = String.format(tempString, d.getUsername());
                    ServerManager.addLogData(tempString);
                    ServerImplementation.registerUserWaiting.remove(d);
                }
            }
        }
        _serverStep = ServerFSMachine.Room;
    }

    private synchronized void doRoomstuff() 
    {
        if(ServerManager.rooms.isDataChanged())
        {
            ServerManager.rooms.confirmDataChanged();
            ServerImplementation.notifyClientsRoomsData(ServerManager.rooms);
        }
        _serverStep = ServerFSMachine.Idle;
    }

    private synchronized void doGamestuff() 
    {
        gameArray = ServerManager.games.getGamesArray();
        for(int i = 0; i < gameArray.length; i++)
        {
            gamePhase = gameArray[i].getPhase();
            switch(gamePhase)
            {
                case Ready:
                    gameArray[i].setPhase(Game.Phase.InitCountDown);
                    break;
                case InitCountDown:
                    if(gameArray[i].getInitTimer() <= 0) 
                        gameArray[i].setPhase(Game.Phase.GameCountDown);
                    
                    gameArray[i].decrementInitTimer();

                    
                    ServerImplementation.notifyGameInitTimer(gameArray[i].getRoomID(),gameArray[i].getInitTimer());
                    break;
                case GameCountDown:
                    
                    break;
            }
        }
    }
}
