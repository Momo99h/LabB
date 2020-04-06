/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client.frames;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Momo
 */
public class LobbyUsrControl extends javax.swing.JPanel {

    private DefaultTableModel _tablemodel = new DefaultTableModel(new String[] {"Room ID","Date","Users"},0);
    public LobbyUsrControl() 
    {
        initComponents();
        jTable1.setModel(_tablemodel);
        _tablemodel.addRow(new String[] {"Room ID","Date","Users"});
    }
    public void addRoom(String id,String date,String players)
    {
        _tablemodel.addRow(new String[]{id,date,players});
        jTable1.setModel(_tablemodel);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.repaint();
    }
    public void removeRoom(String id)
    {
        //_tablemodel.removeRow();
    }
    public void removeAllRooms()
    {
        for(int i = 0; i < _tablemodel.getRowCount();i++)
            _tablemodel.removeRow(i);
        jTable1.setModel(_tablemodel);
        jTable1.setAutoCreateRowSorter(true);
        jTable1.setDefaultEditor(Object.class, null);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jTable1.repaint();
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
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();

        setBackground(new java.awt.Color(255, 204, 204));
        setLayout(new java.awt.BorderLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Room ID", "Date", "Users"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jList1);

        add(jScrollPane2, java.awt.BorderLayout.LINE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
