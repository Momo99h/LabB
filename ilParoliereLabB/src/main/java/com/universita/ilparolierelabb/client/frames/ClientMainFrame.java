/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.universita.ilparolierelabb.client.frames;

import com.universita.ilparolierelabb.client.ClientManager;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.UserStatus;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Room;
import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.JLabel;

/**
 *
 * @author Momo
 */
public class ClientMainFrame extends javax.swing.JFrame {

    
    /*par*/
    public static JLabel Par_lblUtentiConnessi;
    private LobbyUsrControl lobby;
    private GameUsrControl game;
    private Room _tempRoom;
    private Boolean _playerReady = false;
    public ClientMainFrame() 
    {
        initComponents();
        initFunctions();
        initPar();
        
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lblUtentiConnessi = new javax.swing.JLabel();
        buttonAddRoom = new javax.swing.JButton();
        buttonLeaveRoom = new javax.swing.JButton();
        buttonEnterRoom = new javax.swing.JButton();
        buttonReady = new javax.swing.JButton();
        panelContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Utenti connessi:");

        lblUtentiConnessi.setText("1");

        buttonAddRoom.setText("Add room");
        buttonAddRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddRoomActionPerformed(evt);
            }
        });

        buttonLeaveRoom.setText("Leave room");
        buttonLeaveRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLeaveRoomActionPerformed(evt);
            }
        });

        buttonEnterRoom.setText("Enter room");
        buttonEnterRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnterRoomActionPerformed(evt);
            }
        });

        buttonReady.setText("Ready");
        buttonReady.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReadyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUtentiConnessi)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 430, Short.MAX_VALUE)
                .addComponent(buttonReady, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonEnterRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonLeaveRoom)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonAddRoom)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(lblUtentiConnessi))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonAddRoom)
                        .addComponent(buttonLeaveRoom)
                        .addComponent(buttonEnterRoom)
                        .addComponent(buttonReady)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 443, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        ClientManager.leaveRoom(ClientManager.currentuser);
        ClientManager.DisconnectFromServer(ClientManager.currentuser.getUsername());
    }//GEN-LAST:event_formWindowClosing

    private void buttonAddRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddRoomActionPerformed

        int roomId = ClientAddRoom.addRoom();
        if(roomId == -1) return;
        this.enterRoom(roomId);
    }//GEN-LAST:event_buttonAddRoomActionPerformed

    private void buttonLeaveRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLeaveRoomActionPerformed
        // TODO add your handling code here:
        if(!Utility.ShowQuestionPopUp(Settings.clientName, "Confirm operation?")) return;
        ClientManager.leaveRoom(ClientManager.currentuser);
        ClientManager.getGameRooms();
        this.showLobby();
        this.game.setCurrentRoomID(-1);
        this.refreshRooms();
    }//GEN-LAST:event_buttonLeaveRoomActionPerformed

    private void buttonEnterRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEnterRoomActionPerformed
        // TODO add your handling code here:
        int id = lobby.getselectedRoomID();
        if(id == -1 )return;
        this.enterRoom(id);
    }//GEN-LAST:event_buttonEnterRoomActionPerformed

    private void buttonReadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReadyActionPerformed
        // TODO add your handling code here:
        UserStatus status = (_playerReady) ? UserStatus.NotReady : UserStatus.Ready;
        _playerReady = !_playerReady;
        String btn = buttonReady.getText();
        btn = (_playerReady) ? "Not ready" : "Ready";
        buttonReady.setText(btn);
        ClientManager.changePlayerStatus(ClientManager.currentuser, status);
    }//GEN-LAST:event_buttonReadyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ClientMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientMainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientMainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddRoom;
    private javax.swing.JButton buttonEnterRoom;
    private javax.swing.JButton buttonLeaveRoom;
    private javax.swing.JButton buttonReady;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblUtentiConnessi;
    private javax.swing.JPanel panelContainer;
    // End of variables declaration//GEN-END:variables

    private void initPar() 
    {
        Par_lblUtentiConnessi = lblUtentiConnessi;
    }

    private void initFunctions()
    {
        this.setExtendedState(Frame.MAXIMIZED_BOTH);
        this.lobby = new LobbyUsrControl();
        this.game = new GameUsrControl();
        this.panelContainer.setLayout(new BorderLayout());
        this.panelContainer.add(lobby,BorderLayout.CENTER);
        this.buttonLeaveRoom.setVisible(false);
        this.buttonReady.setVisible(false);
        ClientManager.getGameRooms();
        this.refreshRooms();
    }
    public void refreshRooms()
    {
        /** Lobby*/
        this.lobby.removeAllRooms();
        for(int i = 0; i < ClientManager.gameRooms.getRoomsCount();i++)
        {
            this._tempRoom = ClientManager.gameRooms.getRoomObject(i);
            this.lobby.addRoom
            (_tempRoom.getId()+"",_tempRoom.getRoomName(), _tempRoom.getCreationDate(), _tempRoom.getPlayersIn()+"/"+_tempRoom.getPlayersNeeded());
        }
        /** Game*/
        int id = game.getCurrentRoomID();
        if(id == -1) return;
        game.setRoom(id);
    }

    private void enterRoom(int roomId) 
    {
        if(!ClientManager.enterRoom(roomId, ClientManager.currentuser))
        {
            Utility.ShowErrorPopUp(Settings.clientName,"This room is full!");
            return;
        }
        ClientManager.getGameRooms();
        game.setRoom(roomId);
        this.showGame();
       
    }
    private void showGame()
    {
        this.buttonReady.setVisible(true);
        this.buttonAddRoom.setVisible(false);
        this.buttonLeaveRoom.setVisible(true);
        this.buttonEnterRoom.setVisible(false);
        this.panelContainer.remove(lobby);
        this.panelContainer.add(game,BorderLayout.CENTER);
        this.panelContainer.validate();
        this.panelContainer.repaint();
    }
    private void showLobby()
    {
        this.buttonReady.setVisible(false);
        this.buttonAddRoom.setVisible(true);
        this.buttonLeaveRoom.setVisible(false);
        this.buttonEnterRoom.setVisible(true);
        this.panelContainer.remove(game);
        this.panelContainer.add(lobby,BorderLayout.CENTER);
        this.panelContainer.validate();
        this.panelContainer.repaint();
    }

}
