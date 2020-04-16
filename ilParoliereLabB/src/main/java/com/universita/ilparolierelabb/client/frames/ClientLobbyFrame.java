/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.universita.ilparolierelabb.client.frames;

import com.universita.ilparolierelabb.client.ClientManager;
import static com.universita.ilparolierelabb.client.ClientManager.gameFrame;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.Utility;
import com.universita.ilparolierelabb.common.Room;
import javax.swing.JLabel;

/**
 *
 * @author Momo
 */
public class ClientLobbyFrame extends javax.swing.JFrame {

    
    /*par*/
    public static JLabel Par_lblUtentiConnessi;
    private Room _tempRoom;
    public ClientLobbyFrame() 
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
        buttonEnterRoom = new javax.swing.JButton();
        panelContainer = new javax.swing.JPanel();
        lobbyUsrControl = new com.universita.ilparolierelabb.client.frames.LobbyUsrControl();

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

        buttonEnterRoom.setText("Enter room");
        buttonEnterRoom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnterRoomActionPerformed(evt);
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonEnterRoom)
                .addGap(18, 18, 18)
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
                        .addComponent(buttonEnterRoom)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelContainer.setLayout(new java.awt.BorderLayout());
        panelContainer.add(lobbyUsrControl, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
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

    private void buttonEnterRoomActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEnterRoomActionPerformed
        // TODO add your handling code here:
        int id = lobbyUsrControl.getselectedRoomID();
        if(id == -1 )return;
        this.enterRoom(id);
    }//GEN-LAST:event_buttonEnterRoomActionPerformed

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
            java.util.logging.Logger.getLogger(ClientLobbyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClientLobbyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClientLobbyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientLobbyFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddRoom;
    private javax.swing.JButton buttonEnterRoom;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblUtentiConnessi;
    private com.universita.ilparolierelabb.client.frames.LobbyUsrControl lobbyUsrControl;
    private javax.swing.JPanel panelContainer;
    // End of variables declaration//GEN-END:variables

    private void initPar() 
    {
        Par_lblUtentiConnessi = lblUtentiConnessi;
    }

    private void initFunctions()
    {
        this.setLocationRelativeTo(null);
        ClientManager.getLobbyRooms();
        this.refreshRooms();
    }
    public void refreshRooms()
    {
        this.lobbyUsrControl.removeAllRooms();
        String[] data;
        String[] rooms = ClientManager.lobby.getRoomData();
        for(int i = 0; i < rooms.length;i++)
        {
            data = rooms[i].split(";");
            this.lobbyUsrControl.addRoom
            (data[0],data[1],data[2],data[3]);
        }
    }

    private void enterRoom(int roomId) 
    {
        if(!ClientManager.enterRoom(roomId, ClientManager.currentuser))
        {
            Utility.ShowErrorPopUp(Settings.clientName,"This room is full!");
            return;
        }
        gameFrame.enterRoom(roomId);
        gameFrame.setVisible(true);
        this.dispose();
    }

}
