/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client.frames;

import com.universita.ilparolierelabb.client.ClientManager;
import com.universita.ilparolierelabb.common.Room;
import com.universita.ilparolierelabb.common.Settings;
import com.universita.ilparolierelabb.common.User;
import com.universita.ilparolierelabb.common.Utility;
import static com.universita.ilparolierelabb.server.frames.ServerMainFrame.Console_Log_Model;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Momo
 */
public class GameUsrControl extends javax.swing.JPanel {

    private Room _room;
    private ArrayList<String> _wordAlreadyUsed = new ArrayList<>();
    private String[][] _gameMatrix;
    private int _currentRoomID = -1;
    private DefaultTableModel _tableModel = new DefaultTableModel(new String[] {"Player","Total score","Status"},0);
    public static DefaultListModel Word_List_Model = new DefaultListModel();
    
    /**
     * Creates new form GameUsrControl
     */
    public GameUsrControl() {
        initComponents();
        initFunctions();
        matrixUsrControl.setVisible(false);
        listParole.setModel(Word_List_Model);
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
        Words = new java.awt.Label();
        label1 = new java.awt.Label();
        txbParolaInserita = new javax.swing.JTextField();
        btnCheckParoa = new java.awt.Button();
        jScrollPane3 = new javax.swing.JScrollPane();
        listParole = new javax.swing.JList<>();

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

        Words.setText("Words founded");

        label1.setText("Write here:");

        txbParolaInserita.setName(""); // NOI18N
        txbParolaInserita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txbParolaInseritaActionPerformed(evt);
            }
        });

        btnCheckParoa.setActionCommand("wordsCheckButton");
        btnCheckParoa.setLabel("Check!");
        btnCheckParoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckParoaActionPerformed(evt);
            }
        });

        jScrollPane3.setViewportView(listParole);

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Words, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addGap(171, 171, 171)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(matrixUsrControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addGap(85, 85, 85)
                        .addComponent(txbParolaInserita, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addGap(174, 174, 174)
                        .addComponent(btnCheckParoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addComponent(matrixUsrControl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(label1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txbParolaInserita, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCheckParoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelContainerLayout.createSequentialGroup()
                        .addComponent(Words, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
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

    private void txbParolaInseritaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txbParolaInseritaActionPerformed
        
        // TODO add your handling code here:
    }//GEN-LAST:event_txbParolaInseritaActionPerformed

    private void btnCheckParoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckParoaActionPerformed
        
        
        String word = txbParolaInserita.getText();
        if(word.length() <= 3)
        {
            Utility.ShowInfoPopUp(Settings.clientName, "Word that are shorter than 4 characters cannot be added!");
            txbParolaInserita.setText("");
            return;
        }
        if(wordAlreadyUsed(word))
        {
            Utility.ShowInfoPopUp(Settings.clientName, "Word already used!");
            txbParolaInserita.setText("");
            return;
        }
        int score = ClientManager.checkWord(word.toUpperCase(), _currentRoomID);
        _wordAlreadyUsed.add(word);
        if(score>0){
            Word_List_Model.add(0, word+" - "+score);
        } else {
            Word_List_Model.add(0, "PUPPAAA");
        }
        txbParolaInserita.setText("");
        
    }//GEN-LAST:event_btnCheckParoaActionPerformed

    private boolean wordAlreadyUsed(String word)
    {
        for(int i = 0; i < _wordAlreadyUsed.size(); i++)
        {
            if(_wordAlreadyUsed.get(i).equals(word))
                return true;
        }
        return false;
    }
    public void setRoom(int roomID) 
    {
        this._currentRoomID = roomID;
        this._room = ClientManager.getRoomById(roomID);
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
    private java.awt.Label Words;
    private java.awt.Button btnCheckParoa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private java.awt.Label label1;
    private javax.swing.JLabel lblRoomName;
    private javax.swing.JLabel lblWaitingPlayers;
    private javax.swing.JList<String> listParole;
    private com.universita.ilparolierelabb.client.frames.MatrixUsrControl matrixUsrControl;
    private javax.swing.JPanel panelContainer;
    private javax.swing.JTable tablePlayers;
    private javax.swing.JTextField txbParolaInserita;
    // End of variables declaration//GEN-END:variables

    private void initFunctions() 
    {
        tablePlayers.setModel(_tableModel);
        tablePlayers.setDefaultEditor(Object.class, null);
        tablePlayers.setColumnSelectionAllowed(false);
        tablePlayers.setRowSelectionAllowed(true);
        tablePlayers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        setVisibilityWordCheck(false);
    }

    void refreshInitTimer(int timerCount) 
    {
        String waiting = "The game is starting in %s seconds...";
        waiting = String.format(waiting, timerCount);
        this.lblWaitingPlayers.setText(waiting);
        if(!matrixUsrControl.isVisible())matrixUsrControl.setVisible(true);
        matrixUsrControl.shuffleAnim();
    }
    void refreshGameTimer(int timerCount) 
    {
        String waiting = "Game running: %s seconds remaining..";
        waiting = String.format(waiting, timerCount);
        this.lblWaitingPlayers.setText(waiting);
        
        //setVisibilityWordCheck(true);
    }

    void setMatrix(String[][] matrix) 
    {
        this._gameMatrix = matrix;
        matrixUsrControl.setMatrix(matrix);
    }
    
    public void setVisibilityWordCheck(boolean visible){
        listParole.setVisible(visible);
        Words.setVisible(visible);
        label1.setVisible(visible);
        btnCheckParoa.setVisible(visible);
        txbParolaInserita.setVisible(visible);
        jScrollPane3.setVisible(visible);
    }
}
