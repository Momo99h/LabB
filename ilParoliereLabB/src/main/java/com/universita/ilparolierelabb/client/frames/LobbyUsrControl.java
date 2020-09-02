/**
 * 
 * Progetto laboratorio B
 * 
 * Mohamed Hussein,   737787
 * Anrea Girola,      740724
 * Vanessa Squillace, 728078
 * Simone Spagnolo,   737742
 * 
 */
package com.universita.ilparolierelabb.client.frames;

import com.universita.ilparolierelabb.client.ClientManager;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Momo
 */
public class LobbyUsrControl extends javax.swing.JPanel {

    private DefaultTableModel _tableModel = new DefaultTableModel(new String[] {"Room ID","Room Name","Date","Users"},0);
    private DefaultListModel _listModel = new DefaultListModel();
    public LobbyUsrControl() 
    {
        initComponents();
        jTableRooms.setModel(_tableModel);
        jListPlayers.setModel(_listModel);
        jTableRooms.setDefaultEditor(Object.class, null);
        jTableRooms.setColumnSelectionAllowed(false);
        jTableRooms.setRowSelectionAllowed(true);
        jTableRooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }
    public void addRoom(String id,String name,String date,String players)
    {
        _tableModel.addRow(new String[]{id,name,date,players});
    }
    public void removeRoom(String id)
    {
        //_tablemodel.removeRow();
    }
    public void removeAllRooms()
    {
        _tableModel.setRowCount(0);
    }
    public int getselectedRoomID()
    {
        int selected = jTableRooms.getSelectedRow();
        return (selected == - 1) ? -1 : Integer.parseInt((String) jTableRooms.getValueAt(selected, 0));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelTableContainer = new javax.swing.JScrollPane();
        jTableRooms = new javax.swing.JTable();
        panelListContainer = new javax.swing.JScrollPane();
        jListPlayers = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();
        lblRoomID = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 204, 204));
        setLayout(new java.awt.BorderLayout());

        jTableRooms.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jTableRooms.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Room Name", "Date", "Users"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRooms.setColumnSelectionAllowed(true);
        jTableRooms.setRowHeight(30);
        jTableRooms.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRoomsMouseClicked(evt);
            }
        });
        panelTableContainer.setViewportView(jTableRooms);
        jTableRooms.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        add(panelTableContainer, java.awt.BorderLayout.CENTER);

        jListPlayers.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jListPlayers.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        panelListContainer.setViewportView(jListPlayers);

        add(panelListContainer, java.awt.BorderLayout.LINE_END);

        lblRoomID.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        lblRoomID.setText("Room: Lobby");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblRoomID, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addGap(392, 392, 392))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRoomID, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableRoomsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRoomsMouseClicked
        // TODO add your handling code here:
        int selected = jTableRooms.getSelectedRow();
        if(selected == -1)return;
        String usrs = ClientManager.lobby.getDefinedUsersData(selected);
        String[] usrsInside = usrs.split(";");
        _listModel.clear();
        _listModel.addElement("Players inside room:");
        for(int i = 0; i < usrsInside.length;i++) _listModel.addElement(usrsInside[i]);
        
    }//GEN-LAST:event_jTableRoomsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jListPlayers;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTable jTableRooms;
    private javax.swing.JLabel lblRoomID;
    private javax.swing.JScrollPane panelListContainer;
    private javax.swing.JScrollPane panelTableContainer;
    // End of variables declaration//GEN-END:variables
}
