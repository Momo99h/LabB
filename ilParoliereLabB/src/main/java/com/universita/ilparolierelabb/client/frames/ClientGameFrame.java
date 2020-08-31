/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.universita.ilparolierelabb.client.frames;

import com.universita.ilparolierelabb.client.ClientManager;
import com.universita.ilparolierelabb.client.ClientState;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.UserStatus;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Room;
import java.awt.BorderLayout;
import java.awt.Frame;

/**
 *
 * @author Momo
 */
public class ClientGameFrame extends javax.swing.JFrame {

    
    /*par*/
    private Room _tempRoom;
    private Boolean _playerReady = false;
    
    public ClientGameFrame() 
    {
        initComponents();
        initFunctions();        
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
        buttonLeaveRoom = new javax.swing.JButton();
        buttonReady = new javax.swing.JButton();
        panelContainer = new javax.swing.JPanel();
        gameUsrControl = new com.universita.ilparolierelabb.client.frames.GameUsrControl();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        buttonLeaveRoom.setText("Leave room");
        buttonLeaveRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLeaveRoomActionPerformed(evt);
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonReady, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonLeaveRoom)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLeaveRoom)
                    .addComponent(buttonReady))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContainer.setLayout(new java.awt.BorderLayout());
        panelContainer.add(gameUsrControl, java.awt.BorderLayout.CENTER);

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
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        ClientManager.leaveRoom(ClientManager.currentuser);
        ClientManager.DisconnectFromServer(ClientManager.currentuser.getUsername());
    }//GEN-LAST:event_formWindowClosing

    private void buttonLeaveRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLeaveRoomActionPerformed
        // TODO add your handling code here:
        if(!Utility.ShowQuestionPopUp(Settings.clientName, "Confirm operation?")) return;
        ClientManager.leaveRoom(ClientManager.currentuser);
        ClientManager.getLobbyRooms();
        this.dispose();
        ClientManager.Run(false);
    }//GEN-LAST:event_buttonLeaveRoomActionPerformed

    private void buttonReadyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReadyActionPerformed

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
            java.util.logging.Logger.getLogger(ClientGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClientGameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonLeaveRoom;
    private javax.swing.JButton buttonReady;
    private com.universita.ilparolierelabb.client.frames.GameUsrControl gameUsrControl;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelContainer;
    // End of variables declaration//GEN-END:variables


    private void initFunctions()
    {
        this.setLocationRelativeTo(null);
        this.refreshRooms();
    }
    public void enterRoom(int roomId)
    {
        if(roomId <= 0) return;
        gameUsrControl.setRoom(roomId);
    }
    public void refreshRooms()
    {
        int id = gameUsrControl.getCurrentRoomID();
        if(id == -1) return;
        gameUsrControl.setRoom(id);
        
    }
    
    public void startGame()
    {
        this.buttonReady.setVisible(false);
    }
    
    public void refreshGameInitTimer(int timerCount) 
    {
        //if(gameUsrControl.getCurrentRoomID() != roomId) return;
        gameUsrControl.refreshInitTimer(timerCount);
    }

    public void setGameMatrix(String[][] matrix) 
    {
        gameUsrControl.setMatrix(matrix);
    }
    public void refreshGameTimer(int timerCount)
    {
        gameUsrControl.refreshGameTimer(timerCount);
    }
    public void setVisibilityWordCheck(boolean state)
    {
        gameUsrControl.setVisibilityWordCheck(state);
    }
    public void refreshTable()
    {
        gameUsrControl.refreshTable();
    }

    public void setGameHeaderMessage(String msg) 
    {
       gameUsrControl.setHeaderMessage(msg);
    }

    public void stopGame() 
    {
        this.buttonReady.setVisible(true);
        this._playerReady = false;
        this.buttonReady.setText("Ready");
        gameUsrControl.setMatrixvisibility(false);
        gameUsrControl.resetWordSearch();
    }

}
