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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
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
    private JPopupMenu popup;
    private JMenuItem jmi1;
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
        jScrollPane3 = new javax.swing.JScrollPane();
        listParole = new javax.swing.JList<>();
        btnCheckParoa = new javax.swing.JButton();

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

        panelContainer.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panelContainer.add(matrixUsrControl, new org.netbeans.lib.awtextra.AbsoluteConstraints(375, 15, 164, -1));

        Words.setText("Words founded");
        panelContainer.add(Words, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 39, -1, -1));

        label1.setText("Write here:");
        panelContainer.add(label1, new org.netbeans.lib.awtextra.AbsoluteConstraints(407, 212, -1, -1));

        txbParolaInserita.setName(""); // NOI18N
        txbParolaInserita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txbParolaInseritaActionPerformed(evt);
            }
        });
        panelContainer.add(txbParolaInserita, new org.netbeans.lib.awtextra.AbsoluteConstraints(321, 242, 248, -1));

        jScrollPane3.setViewportView(listParole);

        panelContainer.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 69, 217, 227));

        btnCheckParoa.setText("Check word!");
        btnCheckParoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckParoaActionPerformed(evt);
            }
        });
        panelContainer.add(btnCheckParoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(392, 273, -1, -1));

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
        word = word.toUpperCase();
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
        int score = ClientManager.checkWord(word.toUpperCase(), _currentRoomID,ClientManager.currentuser.getUsername());
        _wordAlreadyUsed.add(word);
        Word_List_Model.add(0, word+" - "+score);
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
    public void refreshTable()
    {
        _tableModel.setRowCount(0);
        User [] players = this._room.getListPlayerIn();
        for(int i = 0; i < players.length; i++)
        {
            _tableModel.addRow(new String[]{players[i].getUsername(),
                players[i].getGamePoints()+"",players[i].getStatus().getName()});
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
    private javax.swing.JButton btnCheckParoa;
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
        
        initPopUpMenu();
        
        
        
        
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
    void setHeaderMessage(String msg)
    {
         this.lblWaitingPlayers.setText(msg);
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
    public void setMatrixvisibility(Boolean state)
    {
        matrixUsrControl.setVisible(state);
    }

    public void resetWordSearch() 
    {
        Word_List_Model.clear();
        _wordAlreadyUsed.clear();
        txbParolaInserita.setText("");
    }

    private void initPopUpMenu() 
    {
        popup = new JPopupMenu();
        popup.add(jmi1= new JMenuItem("Get definition"));
        listParole.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent me) 
            {
                if (SwingUtilities.isRightMouseButton(me) && !listParole.isSelectionEmpty() && listParole.locationToIndex(me.getPoint()) == listParole.getSelectedIndex()) 
                {
                    popup.show(listParole, me.getX(), me.getY());
                }
            }
        });
        jmi1.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent ae) 
            {
                String parola = listParole.getSelectedValue().toString().split("-")[0].trim();
                Utility.ShowInfoPopUp(Settings.clientName + " - Definition", ClientManager.getDefinition(parola.toUpperCase()));
            }
        });
    }
}
