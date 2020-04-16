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
    
    
    /* gli viene passata la parola da cercare e il metodo ritorna le posizioni
    *  della prima lettera della parola nella matrice 
    */
    public static boolean getStartingPosition(String[][] m, String w){
        
        boolean present = false;
        
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
                    present = true;
                }
            }
        }
        return present;  
    }
    
    public ArrayList<Node> getNeighbors(String[][] m,Node node, String nextLetter){
        
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
    
    public boolean isNodeVisited(Node node, ArrayList<Node> nodeList){
        return (nodeList.contains(node)); 
    }
    
    public boolean isFirstLetterPresent(String[][] m, String w){
        return false; 
    }
    
    public boolean isWordPresent(ArrayList<String> letters){
        
        int founded = 0; 
        
        while(founded != letters.size()){  
            for(int i=0; i<letters.size(); i++){
                
                //controllo con getNeighbors se ci sono vicini che corrispondono alla lettere successiva 
                
                
            }
        }
        
        return true; 
    }
    
    public static void main(String[] args){
        
        final String[][] m = {{"G", "A", "U", "T"}, 
                                    {"P", "R", "M", "R"},
                                    {"D", "O", "L", "A"},
                                    {"E", "S", "I", "C"},};
        
        System.out.println("Matrice:");
        //Matrix matrix = new Matrix(m);
        
        //System.out.print();
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(" " + m[i][j]);
            }
            System.out.println();
        }
        
        boolean v = false;
        
        System.out.println("Cerco prima lettera della parola DOSE");
        String parola = "DOSE";
        v = (Matrix.getStartingPosition(m,parola));
        System.out.println(v);
        
        //Matrix.getNeighbors();
        
        System.out.println("Cerco prima lettera della parola ESIC");
        parola = "ESIC";
        v = (Matrix.getStartingPosition(m,parola));
        System.out.println(v);
        
        System.out.println("Cerco prima lettera della parola TRACI");
        parola = "TRACI";
        v = (Matrix.getStartingPosition(m,parola));
        System.out.println(v);
        
        System.out.println("Cerco prima lettera della parola ZORRO");
        parola = "ZORRO";
        v = (Matrix.getStartingPosition(m,parola));
        System.out.println(v);
        
         System.out.println("Cerco prima lettera della parola KILO");
        parola = "KILO";
        v = (Matrix.getStartingPosition(m,parola));
        System.out.println(v);
        
        
        
    }
}

        
    
   
