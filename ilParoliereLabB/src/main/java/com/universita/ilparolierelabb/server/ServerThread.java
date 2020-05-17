
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.client.RegisterData;
import com.universita.ilparolierelabb.common.Game;
import com.universita.ilparolierelabb.common.Room;
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
            if(ServerImplementation.notifyClientsCount(ServerManager.ObserversOnline())) 
                ServerManager._ClientCountChanged = false;
        }
    }

    private synchronized void doEmailstuff() 
    {
        for(RegisterData d : ServerImplementation.registerUserWaiting)
        {
            if(!d.getEmailStatus())
            {
                String code = Utility.randomAlphaNumeric(10);
                if(Utility.sendEmail(d.getEmail(),Utility.welcomeEmailBody(d.getUsername(),code)))
                {
                    d.setVerificationCode(code);
                    d.setEmailStatus(true);
                    tempString = "Email sent to %s (%s) with verification code: %s";
                    tempString = String.format(tempString, d.getEmail(),d.getUsername(),code);
                    ServerManager.addLogData(tempString);
                    ServerImplementation.registerUserWaiting.remove(d);
                }
                else
                {
                    String tempString = "Email failed to sent to %s";
                    tempString = String.format(tempString, d.getEmail());
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
    }

    private synchronized void doRoomstuff() 
    {
        
        if(ServerManager.rooms.isDataChanged())
        {
            //;
            if(ServerImplementation.notifyClientsLobbyData(ServerManager.rooms.createLobbyData()) && ServerImplementation.notifyGameRoomData())
                ServerManager.rooms.confirmDataChanged();
            
        }
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
                    gameArray[i].decrementInitTimer();
                    ServerImplementation.notifyGameInitTimer(gameArray[i].getRoomID(),gameArray[i].getInitTimer());
                    if(gameArray[i].getInitTimer() == 0)
                        gameArray[i].setPhase(Game.Phase.CreateMatrix);
                    break;
                case CreateMatrix:
                    gameArray[i].setMatrix(MatrixFactory.getMatrix());
                    ServerImplementation.notifyGameMatrix(gameArray[i].getRoomID(),gameArray[i].getMatrix());
                    gameArray[i].setPhase(Game.Phase.GameCountDown);
                    break;
                case GameCountDown:
                    gameArray[i].decrementGameTimer();
                    ServerImplementation.notifyGameTimer(gameArray[i].getRoomID(),gameArray[i].getGameTimer());
                    if(gameArray[i].getGameTimer() == 0)
                        gameArray[i].setPhase(Game.Phase.Finished);
                    break;
            }
        }
    }
}
