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
    public void analizzaParola(String[][] matrice,String parola)
    {
        
    }
    public static void main (String[] args){
    
      
        
       
        //Client dice al server di verificare una parola su un matrice di lettere(Stringhe)
        final String[][] m = {{"G", "A", "U", "O"}, 
                              {"P", "R", "U", "R"},
                              {"O", "Z", "Z", "A"},
                              {"E", "S", "O", "C"}}; //Sicuro.
        
        Node[][] nodeMatrix = createNodeMatrix(m); //Sicuro.
       
        ArrayList<Letter> letters= new ArrayList<>();
        
        String p = "GAROZZO";
        String[] parola = new String[p.length()]; //trasformo la parola in array normale di string 
        for(int i = 0; i < p.length();i++){
            parola[i] = String.valueOf(p.charAt(i));
        }
        
        for(String s : parola) {
            Letter l = new Letter(s);
            l.setPositions( getLetterPosition(nodeMatrix, s));
            letters.add(l);
            l.print();
            System.out.println();
        }
        
        for(int i=0; i<letters.size(); i++){    //stampa le lettere della parola 
            System.out.print(letters.get(i).getLetter() + " ");
        }
        System.out.println();
        
        //NEW 
        //Mi setto sulla posizione iniziale 
        MatrixPosition myMatrixPosition = new MatrixPosition(); 
        //myMatrixPosition.setX(letters.getLetter());
        //myMatrixPosition.setY(y);
        
        
        /*
        Per cercarlo devi estrapolare la posizione da letter
        E con questa posizione posizionarmi in matrix position del nodo
        */
        
        
        
    } 
}
