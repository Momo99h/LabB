/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client.frames;

import com.universita.ilparolierelabb.client.ClientManager;
import com.universita.ilparolierelabb.common.Room;
import com.universita.ilparolierelabb.common.User;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Momo
 */
public class GameUsrControl extends javax.swing.JPanel {

    private Room _room;
    private String[][] _gameMatrix;
    private int _currentRoomID = -1;
    private DefaultTableModel _tableModel = new DefaultTableModel(new String[] {"Player","Total score","Status"},0);
    
    /**
     * Creates new form GameUsrControl
     */
    public GameUsrControl() {
        initComponents();
        initFunctions();
        matrixUsrControl.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablePlayers = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        lblRoomName = new javax.swing.JLabel();
        lblWaitingPlayers = new javax.swing.JLabel();
        panelContainer = new javax.swing.JPanel();
        matrixUsrControl = new com.universita.ilparolierelabb.client.frames.MatrixUsrControl();

        setBackground(new java.awt.Color(255, 204, 204));

        tablePlayers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane1.setViewportView(tablePlayers);

        lblRoomName.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblRoomName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRoomName.setText("Room: Lobby");

        lblWaitingPlayers.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblWaitingPlayers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblWaitingPlayers.setText("Waiting for N players");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblWaitingPlayers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblRoomName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRoomName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWaitingPlayers)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addGap(402, 402, 402)
                .addComponent(matrixUsrControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(matrixUsrControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(165, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 935, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelContainer, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public void setRoom(int roomID) 
    {
        this._currentRoomID = roomID;
        this._room = ClientManager.rooms.getRoom(roomID);
        this.lblRoomName.setText(this._room.getRoomName());
        this.refreshHeaderMessage();
        this.refreshTable();
    }
    
    private void refreshHeaderMessage()
    {
        String waiting = "";
        int missing = this._room.getPlayersNeeded()-this._room.getPlayersIn();
        if(missing != 0)
        {
            waiting  = "Waiting for %s player%s...";
            waiting = (missing > 1) ? String.format(waiting,missing,"s") : String.format(waiting,missing,""); 
        }
        else
        {
            waiting  = "Waiting players to be ready...";
        }    
        this.lblWaitingPlayers.setText(waiting);
    }
    private void refreshTable()
    {
        _tableModel.setRowCount(0);
        User [] players = this._room.getListPlayerIn();
        for(int i = 0; i < players.length; i++)
        {
            _tableModel.addRow(new String[]{players[i].getUsername(),
                players[i].getTotalPoints()+"",players[i].getStatus().getName()});
        }
    }
    public int getCurrentRoomID()
    {
        return this._currentRoomID;
    }
    public void setCurrentRoomID(int id)
    {
        this._currentRoomID = id;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblRoomName;
    private javax.swing.JLabel lblWaitingPlayers;
    private com.universita.ilparolierelabb.client.frames.MatrixUsrControl matrixUsrControl;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JTable tablePlayers;
    // End of variables declaration//GEN-END:variables

    private void initFunctions() 
    {
        tablePlayers.setModel(_tableModel);
        tablePlayers.setDefaultEditor(Object.class, null);
        tablePlayers.setColumnSelectionAllowed(false);
        tablePlayers.setRowSelectionAllowed(true);
        tablePlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    void refreshInitTimer(int timerCount) 
    {
        String waiting = "The game is starting in %s seconds...";
        waiting = String.format(waiting, timerCount);
        this.lblWaitingPlayers.setText(waiting);
        if(!matrixUsrControl.isVisible())matrixUsrControl.setVisible(true);
        matrixUsrControl.shuffleAnim();
    }

    void setMatrix(String[][] matrix) 
    {
        this._gameMatrix = matrix;
        matrixUsrControl.setMatrix(matrix);
    }
}
