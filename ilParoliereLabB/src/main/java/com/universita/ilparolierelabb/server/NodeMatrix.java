/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import java.util.ArrayList;

/**
 *
 * @author andreagirola
 */
public class NodeMatrix {
    
    //public static ArrayList<Node> _matrixNodes;
    
    public static ArrayList<Node> createNodeMatrix(String[][] matrix){
        
        ArrayList<Node> temp = new ArrayList<Node>();
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                temp.add(new Node(matrix[i][j], i, j, false));
            }
        }
        return temp;
    }
    
    public static void showMatrix(ArrayList<Node> _matrixNodes){
        int count=0; 
        if(_matrixNodes != null){
            for(Node n: _matrixNodes){
                System.out.println(n + " ");
                count++; 
                if(count == 4){ System.out.println("");}
            }
        }
    }
    
    
    
    
    
    
    
    
    public static void main (String[] args){
    
        final String[][] m = {{"G", "A", "U", "T"}, 
                              {"P", "R", "M", "R"},
                              {"D", "O", "L", "A"},
                              {"E", "S", "I", "C"}};
        
        //System.out.println("Inizializzo matrix");
        ArrayList<Node> matrixNodes = new ArrayList<Node>(); 
        matrixNodes = NodeMatrix.createNodeMatrix(m);
        
        //System.out.println("Stampo matrix");
        for(Node n : matrixNodes){
            System.out.println("Node: " + n.s + 
                               " Coordinate: " + n.x + "," + n.y + 
                               " Visited: " + n.v);
        }
    }
    
    
    
}
