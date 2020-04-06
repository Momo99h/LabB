/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.server.frames.ServerMainFrame;
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
    private int tempInt = 0;
    private String tempString = "";
    private int _lastRoomCount = 0;
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
        switch(_serverStep)
        {
            case Idle: doIdlestuff();
                
                break;
            case Email: doEmailstuff();     
                break;
        }
    }

    private void doIdlestuff() 
    {
        ServerImplementation.notifyClientsCount(ServerManager.ObserversOnline());
        if(ServerManager.gameRooms.isDataChanged())
        {
            ServerManager.gameRooms.confirmDataChanged();
            ServerImplementation.notifyClientsRoomsData(ServerManager.gameRooms);
        }
       _serverStep = ServerFSMachine.Email;
    }

    private void doEmailstuff() 
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
        _serverStep = ServerFSMachine.Idle;
    }
}
