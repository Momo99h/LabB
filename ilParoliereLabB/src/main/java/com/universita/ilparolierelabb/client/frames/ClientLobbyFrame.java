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
import com.universita.ilparolierelabb.common.UserStatus;
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
        initPar();
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
        jLabel1 = new javax.swing.JLabel();
        lblUtentiConnessi = new javax.swing.JLabel();
        buttonAddRoom = new javax.swing.JButton();
        buttonEnterRoom = new javax.swing.JButton();
        label1 = new java.awt.Label();
        label2 = new java.awt.Label();
        panelContainer = new javax.swing.JPanel();
        lobbyUsrControl = new com.universita.ilparolierelabb.client.frames.LobbyUsrControl();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        menuItemDisconnect = new javax.swing.JMenuItem();
        mnItemMyStatistic = new javax.swing.JMenuItem();

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

        label1.setText("Username:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblUtentiConnessi)
                .addGap(29, 29, 29)
                .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(buttonAddRoom)
                        .addComponent(buttonEnterRoom))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(lblUtentiConnessi))
                            .addComponent(label2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        label1.getAccessibleContext().setAccessibleName("Username");

        panelContainer.setLayout(new java.awt.BorderLayout());
        panelContainer.add(lobbyUsrControl, java.awt.BorderLayout.CENTER);

        jMenu1.setText("Me");

        menuItemDisconnect.setText("Disconnect");
        menuItemDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItemDisconnectActionPerformed(evt);
            }
        });
        jMenu1.add(menuItemDisconnect);

        mnItemMyStatistic.setText("Statistics");
        mnItemMyStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnItemMyStatisticActionPerformed(evt);
            }
        });
        jMenu1.add(mnItemMyStatistic);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

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
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

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

    private void menuItemDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItemDisconnectActionPerformed
        
        if(!Utility.ShowQuestionPopUp(Settings.clientName, "Disconnect from the game?"))
            return;
        ClientManager.leaveRoom(ClientManager.currentuser);
        ClientManager.DisconnectFromServer(ClientManager.currentuser.getUsername());
        this.dispose();
        ClientManager.Login();
    }//GEN-LAST:event_menuItemDisconnectActionPerformed

    private void mnItemMyStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnItemMyStatisticActionPerformed
        // TODO add your handling code here:
        
        //Dialog show my statistic
        ClientMyStatistics.Show();
        
    }//GEN-LAST:event_mnItemMyStatisticActionPerformed

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
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private javax.swing.JLabel lblUtentiConnessi;
    private com.universita.ilparolierelabb.client.frames.LobbyUsrControl lobbyUsrControl;
    private javax.swing.JMenuItem menuItemDisconnect;
    private javax.swing.JMenuItem mnItemMyStatistic;
    private javax.swing.JPanel panelContainer;
    // End of variables declaration//GEN-END:variables

    private void initPar() 
    {
        Par_lblUtentiConnessi = lblUtentiConnessi;
    }
    
    private void displayUsername(){
        label2.setText(ClientManager.currentuser.getUsername());
    }

    private void initFunctions()
    {
        this.setLocationRelativeTo(null);
        displayUsername();              //username name bottom left 
        ClientManager.getLobbyRooms();
        ClientManager.refreshOnlineCount();
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
