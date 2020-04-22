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
    
    public static String[] stringToArrayString(String p){                    //trasforma Stringa in arraylist di stringhe dividendo ogni lettera 
        
        String[] word = new String[p.length()];
        for(int i = 0; i < p.length();i++){
            word[i] = String.valueOf(p.charAt(i));
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
        Boolean exist = false;
        //scorro matrice di nodi 
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                if(letter.equals(nodeMatrix[i][j].getValue()))
                {
                    pos = new MatrixPosition();
                    pos = nodeMatrix[i][j].getMatrixPosition();
                    letterPositions.add(pos);
                    exist = true;
                }
            }
        }
        return (exist) ? letterPositions : null; 
    }
    public static Boolean analizzaPercoso(Letter currentLetter,Letter nextLetter)
    {
        System.out.println("Analizzo percorso: "+currentLetter.getLetter()+"->"+nextLetter.getLetter());
        
        ArrayList<MatrixPosition> currLetterPositions = currentLetter.getLetterPositions();
        ArrayList<MatrixPosition> nextLetterPositions = nextLetter.getLetterPositions();
        if(currLetterPositions == null || nextLetterPositions == null) return false;
    
        MatrixPosition currLetterPosition;
        MatrixPosition nextLetterPosition;
        
        for(int i = 0; i < currLetterPositions.size();i++)
        {
            currLetterPosition = currLetterPositions.get(i);
            
            for(int j = 0; j < nextLetterPositions.size(); j++)
            {
                nextLetterPosition = nextLetterPositions.get(j);
                if(nodeMatrix[currLetterPosition.getX()][currLetterPosition.getY()].hasNeighbor(nextLetterPosition)) return true;
            }
        }
        
        return false;
    }
    public static Node[][] nodeMatrix;
    public static int analizzaParola(String[][] matrice,String parola)
    {
        //1) Matrice in matrice di nodi, perche la classe nodi ha metodi implementati come
        //Visitato,Vicino,Posizione e cosi via.
        nodeMatrix = createNodeMatrix(matrice); 
        //2) Costruisco array di stringhe rappresentati i caratteri
        String[] word = stringToArrayString(parola);
        //3) Controllo se ha senso fare l'algoritmo
        if(getLetterPosition(nodeMatrix,word[0]) == null) return 0;        
        //4) Rappresentare le lettere nella matrice con le loro posizioni
        ArrayList<Letter> letters = new ArrayList<>();
        for(String s : word) 
        {
            Letter l = new Letter(s);
            l.setPositions(getLetterPosition(nodeMatrix, s)); //Cerca le posizioni e le imposta su letter
            letters.add(l);
            System.out.print(l.getLetter()+" "); l.print();
            System.out.println();
        }
        //5) Ricerca
        for(int i = 0; i < letters.size() - 1; i++)
        {
            //Cosa vuol dire analisi.
            //Se io sono A devo controllare B è vicino di A.
            //Se SI, Continuo in quella direzione.
            //Consideriamo GAROZZO
            if(!analizzaPercoso(letters.get(i),letters.get(i+1))) return 0;
        }
        
        return 10;
    }
    public static void main (String[] args){
    
      
        
       
        //Client dice al server di verificare una parola su un matrice di lettere(Stringhe)
        final String[][] m = {{"G", "A", "U", "F"}, 
                              {"P", "R", "U", "A"},
                              {"F", "Z", "Z", "R"},
                              {"E", "S", "F", "O"}}; //Sicuro.
        
        System.out.println(analizzaParola(m,"ARA"));
       
        /*ArrayList<Letter> letters= new ArrayList<>();
        
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
