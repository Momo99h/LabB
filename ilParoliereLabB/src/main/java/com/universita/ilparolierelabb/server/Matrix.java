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
public class Matrix {
    
    private static String[][] _passedMatrix = new String[4][4];
    private ArrayList<Node> nodes = new ArrayList<Node>();
    
    
    public Matrix(String[][] m){
        this._passedMatrix = m; 
    }
    
    public boolean isNodeVisited(Node node, ArrayList<Node> nodeList){
        return (nodeList.contains(node)); 
    }
    
    //intro condition per controllare se la parola può esistere nella matrice 
    public boolean isFirstLetterPresent(String[][] matrix, String w){
        boolean is = false;
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(matrix[i][j].equals(w.charAt(0))){
                    is = true; 
                }   
            }
        } 
        return is; 
    }
    
    public static ArrayList<Node> getStartingPosition(String[][] m, String w){
        
        ArrayList<Node> nodes = new ArrayList<Node>();
        
        //creo ed inizializzo arraylist che contiene la parola
        ArrayList<String> letters = new ArrayList<>();
        for(int i=0; i<w.length(); i++){
            //letters.add(String.valueOf(w.toLowerCase().charAt(i))); 
            letters.add(String.valueOf(w.charAt(i)));
        }
        
        //confronto la prima lettera della parola per trovare un punto di inizio 
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(m[i][j].equals(letters.get(0))){
                    nodes.add(new Node(i,j));
                }   
            }   
        }   return nodes;
    }
   
   
    public ArrayList<Node> getNeighbors(String[][] m, Node node, String nextLetter){
        
        ArrayList<Node> neighbors = new ArrayList<Node>(); 
        
        if(m[node.x+1][node.y].equals(nextLetter)) { //sopra
            neighbors.add( new Node(node.x+1, node.y)); 
        } else 
    
        if(m[node.x+1][node.y+1].equals(nextLetter)){ //sopra destra 
            neighbors.add( new Node(node.x+1, node.y+1));
        } else 
        
        if(m[node.x][node.y+1].equals(nextLetter)){ //destra  
            neighbors.add( new Node(node.x, node.y+1));
        } else 

        if(m[node.x-1][node.y+1].equals(nextLetter)){ //sotto destra 
            neighbors.add( new Node(node.x-1, node.y+1));
        } else 

        if(m[node.x-1][node.y].equals(nextLetter)){ //sotto 
            neighbors.add( new Node(node.x-1, node.y));
        } else 
            
        if(m[node.x-1][node.y-1].equals(nextLetter)){ //sotto sinistra 
            neighbors.add( new Node(node.x-1, node.y-1));
        } else 
            
        if(m[node.x][node.y-1].equals(nextLetter)){ //sinistra 
            neighbors.add( new Node(node.x, node.y-1));
        } else 
            
        if(m[node.x+1][node.y-1].equals(nextLetter)){ //sopra sinistra 
            neighbors.add( new Node(node.x+1, node.y-1));
        } 
        
         
        return neighbors; 
                
    }
    
    
    
    public boolean isWordPresent(String[][] matrix, ArrayList<String> letters){
        
        boolean present = false;
        String word = letters.toString();
        System.out.print("First letter present? > ");
        //present = getStartingPosition(matrix, word);
        System.out.println(present ? "SI":"NO");
        if(!present){
            System.out.println("Parola non esiste nella matrice");
        } else {
            System.out.println("Lettere vicine: "); 
            ArrayList<Node> n = new ArrayList<Node>(); 
            //n = getNeighbors(String[][] m, Node node, String nextLetter);
        }
        return true; 
    }
    
    public static void main(String[] args){
        
        final String[][] m = {{"G", "A", "U", "T"}, 
                                    {"P", "R", "M", "R"},
                                    {"D", "O", "L", "A"},
                                    {"E", "S", "I", "C"},};
        String w = "DOSE";
        boolean firstLetter = false; 
        
        System.out.println("Matrice:");
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(" " + m[i][j]);
            }
            System.out.println();
        }
       
        
        System.out.println("Controllo se la prima lettera è presente"); 

        
        
        System.out.println("Visualizzo i suoi vicini");
        
        // TO DO 
        
        
        
    }
}

        
    
   
