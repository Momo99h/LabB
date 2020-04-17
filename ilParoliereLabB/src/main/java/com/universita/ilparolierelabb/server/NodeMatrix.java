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
    public static Node[][] _nodeMatrix;
    
    public NodeMatrix(){ 
        //to do 
    }   
    
    public static ArrayList<String> stringToArrayList(String s){                    //trasforma Stringa in arraylist di stringhe dividendo ogni lettera 
        
        ArrayList<String> word = new ArrayList<String>(); 
        
        for(int i=0; i<s.length(); i++){
            word.add(String.valueOf(s.charAt(i)));      //Controllare caso di valore "Qu" 
        }
        return word; 
    }
    
    public static Node[][] createNodeMatrix(String[][] matrix){                     //crea matrice di nodi passandogli una matrice di string
        
        Node[][] nodes = new Node[4][4];
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                nodes[i][j] = new Node(matrix[i][j],i,j,false);
            }
        }
        return nodes;
    }
    
    public static ArrayList<MatrixPosition> getLetterPosition(Node[][] nodeMatrix , String letter){ //ritorna arrayList di di Nodi di partenza (tenendo ripetizioni possibili)
        
        ArrayList<MatrixPosition> letterPositions = new ArrayList<>(); 
        MatrixPosition pos;
        //scorro matrice di nodi 
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(letter.equals(nodeMatrix[i][j].getValue())){
                    
                    pos = new MatrixPosition();
                    pos = nodeMatrix[i][j].getMatrixPosition();
                    letterPositions.add(pos);
                }
            }
        }
        
        return letterPositions; 
    }
    
    public static void main (String[] args){
    
        
       
        
        final String[][] m = {{"G", "A", "U", "O"}, 
                              {"P", "R", "U", "R"},
                              {"O", "Z", "Z", "A"},
                              {"E", "S", "O", "C"}};
        
        Node[][] nodeMatrix = createNodeMatrix(m);
        
        ArrayList<Letter> letters= new ArrayList<>();
        String p = "GAROZZO";
        String[] paroladimerda = new String[p.length()];
        for(int i = 0; i < p.length();i++)
        {
            paroladimerda[i] = String.valueOf(p.charAt(i));
        }
        
        for(String s : paroladimerda)
        {
            Letter l = new Letter(s);
            l.setPositions( getLetterPosition(nodeMatrix, s));
            letters.add(l);
            l.print();
            System.out.println();
        }
        for(int i=0; i<letters.size(); i++){
            System.out.println(letters.get(i).getLetter());
        }
        
        /*
            LetterA.setPositions(getLetterPosition(nodeMatrix, "A"));
            
        */
        /*
        
        ArrayList<ArrayList<MatrixPosition>>
        A
        A = 0,1 / 2,3
        R = 1,1 / 1,3
        O = 0,3 / 3,2
        quando salgo resetto le visite del livello inferiore
        */
        /*
        [][A][][]
        [][][][]
        [][][][A]
        [][][][]
*/      
        /*
        0,0 0,1 0,2 -> 0,0 XXX 0,2
        1,0 1,1 1,2 -> 1,0 1,1 1,2
                    -> 2,0 2,1 2,2 */
    } 
}
