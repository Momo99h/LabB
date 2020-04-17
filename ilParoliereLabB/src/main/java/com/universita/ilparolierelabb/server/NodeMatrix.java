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
    
    public static Node _currentPosition; 
    
    public static ArrayList<String> stringToArrayList(String s){        //trasforma Stringa in arraylist di stringhe dividendo ogni lettera 
        
        ArrayList<String> word = new ArrayList<String>(); 
        
        for(int i=0; i<s.length(); i++){
            word.add(String.valueOf(s.charAt(i)));      //Controllare caso di valore "Qu" 
        }
        return word; 
    }
    
    public static ArrayList<Node> createNodeMatrix(String[][] matrix){
        
        ArrayList<Node> temp = new ArrayList<Node>();
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                temp.add(new Node(matrix[i][j], i, j, false));
            }
        }
        return temp;
    }
    
    public static ArrayList<Node> getStartingPoints(ArrayList<Node> n , ArrayList<String> s){
        
        ArrayList<Node> startingPoints = new ArrayList<Node>(); 
        
        //scorro matrice di nodi 
        for(Node node : n){
            if(s.get(0).equals(node.s)){ 
                startingPoints.add(node);
            }
        }
        
        return startingPoints; 
    }
    
    public static ArrayList<Node> getNeighbors(ArrayList<Node> nodeMatrix, Node startingNode, String nextLetter){
        
        ArrayList<Node> neighbors = new ArrayList<Node>(); 
        
        if()
        
        return neighbors; 
    }
    
    /*public static void showMatrix(ArrayList<Node> _matrixNodes){
        int count=0; 
        if(_matrixNodes != null){
            for(Node n: _matrixNodes){
                System.out.println(n + " ");
                count++; 
                if(count == 4){ System.out.println("");}
            }
        }
    }*/
    
    
    
    
    
    
    
    public static void main (String[] args){
    
        final String[][] m = {{"G", "A", "U", "T"}, 
                              {"P", "R", "M", "R"},
                              {"D", "O", "L", "A"},
                              {"E", "S", "I", "C"}};
        
        ArrayList<String> word = new ArrayList<String>(); //parola da confrontare,  dovrà diventare un arraylist di parole da confrontare 
        word = (NodeMatrix.stringToArrayList("ARO"));
        
        //System.out.println("Inizializzo matrix");
        ArrayList<Node> matrixNodes = new ArrayList<Node>(); 
        matrixNodes = NodeMatrix.createNodeMatrix(m);
        
        //System.out.println("Stampo matrix");
        for(Node n : matrixNodes){
            System.out.println("Node: " + n.s + 
                               " Coordinate: " + n.x + "," + n.y + 
                               " Visited: " + n.v);
        }
        
        System.out.println("Lista nodi di partenza: "); 
        ArrayList<Node> startings = new ArrayList<Node>();
        startings = NodeMatrix.getStartingPoints(matrixNodes , word);
        for(Node n : startings){
            System.out.println("Node: " + n.s + 
                               " Coordinate: " + n.x + "," + n.y + 
                               " Visited: " + n.v);
        }
        
       
        
    }
    
    
    
}
