/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

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
       /*tempInt = ServerMainFrame.Par_progressBarRunning.getValue();
       if(tempInt == ServerMainFrame.Par_progressBarRunning.getMaximum() - 1) tempInt = 0;
       ServerMainFrame.Par_progressBarRunning.setValue(tempInt);
       ServerMainFrame.Par_progressBarRunning.repaint();*/
       _serverStep = ServerFSMachine.Email;
    }

    private void doEmailstuff() 
    {
        _serverStep = ServerFSMachine.Idle;
    }
}
