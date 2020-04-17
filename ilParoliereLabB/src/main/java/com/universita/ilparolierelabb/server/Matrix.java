/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import static java.lang.String.valueOf;
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
  
    
    
    //intro condition per controllare se la parola può esistere nella matrice 
    public static boolean isFirstLetterPresent(String[][] matrix, String w){
        boolean is = false;
        String first = String.valueOf(w.charAt(0));
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
               if(first.equals(matrix[i][j])){
                   is = true; 
               }
            }
        } 
        return is; 
    }
    
    //torna un ArrayList di nodi percè i nodi iniziali possono essere più di uno
    public static ArrayList<Node> getStartingPosition(String[][] m, String w){
        
        ArrayList<Node> nodes = new ArrayList<Node>();
        w = w.toUpperCase();
        
        //creo ed inizializzo arraylist che contiene la parola
        ArrayList<String> letters = new ArrayList<>();
        for(int i=0; i<w.length(); i++){
            letters.add(String.valueOf(w.charAt(i)));
        }
        
        //confronto la prima lettera della parola per trovare un punto di inizio 
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(m[i][j].equals(letters.get(0))){
                 //   nodes.add(new Node(i,j,true));
                }   
            }   
        }   return nodes;
    }
    /*
    //cerca la lettera successiva e torna in un arraylist di nodi che la contengono
    public static ArrayList<Node> getNeighbors(String[][] m, Node node, String nextLetter){
        
        ArrayList<Node> neighbors = new ArrayList<Node>(); 
        
        if(m[node.x+1][node.y].equals(nextLetter)) { //sopra
            neighbors.add( new Node(node.x+1, node.y, true)); 
        } else 
    
        if(m[node.x+1][node.y+1].equals(nextLetter)){ //sopra destra 
            neighbors.add( new Node(node.x+1, node.y+1, true));
        } else 
        
        if(m[node.x][node.y+1].equals(nextLetter)){ //destra  
            neighbors.add( new Node(node.x, node.y+1, true ));
        } else 

        if(m[node.x-1][node.y+1].equals(nextLetter)){ //sotto destra 
            neighbors.add( new Node(node.x-1, node.y+1, true));
        } else 

        if(m[node.x-1][node.y].equals(nextLetter)){ //sotto 
            neighbors.add( new Node(node.x-1, node.y, true));
        } else 
            
        if(m[node.x-1][node.y-1].equals(nextLetter)){ //sotto sinistra 
            neighbors.add( new Node(node.x-1, node.y-1, true));
        } else 
            
        if(m[node.x][node.y-1].equals(nextLetter)){ //sinistra 
            neighbors.add( new Node(node.x, node.y-1, true));
        } else 
            
        if(m[node.x+1][node.y-1].equals(nextLetter)){ //sopra sinistra 
            neighbors.add( new Node(node.x+1, node.y-1, true));
        } 
        
         
        return neighbors; 
                
    }*/
    /*
    public static void main(String[] args){
        
        final String[][] m = {{"G", "A", "U", "T"}, 
                                    {"P", "R", "M", "R"},
                                    {"D", "O", "L", "A"},
                                    {"E", "S", "I", "C"},};
        final String w = "DOSE"; 
        final String w2 = "ARO";
        //Node currentNode = new Node(0,0,false);
        
        System.out.println("Matrice:");
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                System.out.print(" " + m[i][j]);
            }
            System.out.println();
        }
       
        
        System.out.println("Controllo prima lettera della parola " + w + " "); 
        boolean b = Matrix.isFirstLetterPresent(m,w);
        System.out.println(b ? "Lettera PRESENTE" : "Lettera NON PRESENTE");
        if(b){
            //trovo posizione di partenza 
            System.out.println("Visualizzo nodo di partenza ");
            ArrayList<Node> node = new ArrayList<Node>(Matrix.getStartingPosition(m,w));
            for(Node n : node){
                System.out.println(n.x + " " + n.y + " ");
                
            }
            
            //ritorno i suoi vicini che corrispondono
            System.out.println("Trovo nodi successivi");
            ArrayList<Node> vicini = new ArrayList<Node>(Matrix.getNeighbors(m, node.get(0), "O"));
            for(Node n : vicini){
                System.out.println(n.x + " " + n.y + " ");
            }
            
            //Mi sposto su un nodo vicino valido 
            
        }
        
    }*/
}

        
    
   
