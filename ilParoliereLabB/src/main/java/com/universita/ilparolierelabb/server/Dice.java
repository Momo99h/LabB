/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import java.lang.*;

/**
 *
 * @author andreagirola
 */
public class Dice implements DiceInterface {

    private String[][] _letterMatrix = {{"B", "A", "O", "O", "Qu", "M"}, 
                                        {"U", "T", "E", "S", "L", "P"},
                                        {"I", "G", "E", "N", "V", "T"},
                                        {"O", "U", "L", "I", "E", "R"},
                                        {"A", "C", "E", "S", "L", "R"},
                                        {"R", "A", "T", "I", "B", "L"},
                                        {"S", "M", "I", "R", "O", "A"}, 
                                        {"I", "S", "E", "E", "F", "H"},
                                        {"S", "O", "T", "E", "N", "D"},
                                        {"A", "I", "C", "O", "F", "R"},
                                        {"V", "N", "Z", "D", "A", "E"},
                                        {"I", "E", "A", "T", "A", "O"}, 
                                        {"O", "T", "U", "C", "E", "N"}, 
                                        {"N", "O", "L", "G", "U", "E"},
                                        {"D", "C", "M", "P", "A", "E"},
                                        {"E", "R", "I", "N", "S", "H"}}; 
    
    private int _min=1; 
    private int _max=6; 
    private int _id; 
    private String[] _faces;
    private Dice[] _dices; 
    
    
    //constructor
    //gli si passa un id (numero del dado da 1 a 16)
    //e un array di lettere corrispondenti al dado in base alle specifiche 
    public Dice(int id, String[] face){ 
        this._id = id; 
        this._faces = face; 
    }
    
    //getter
    public int getDiceId(){
        return this._id; 
    }
    
    //ritorna una faccia del dado
    public String getRandomFace(){
        int rand = (int) (Math.random() * ((_max - _min) + 1)) + _min; 
        return this._faces[rand]; 
    }
    
    public void createDices(){
        for(int i=0; i<15; i++){
            for(int j=0; j<5; j++){ 
                //to-do \
            }
        }
    }
    
}
