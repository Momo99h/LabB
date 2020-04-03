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
public class DiceFactory implements DiceInterface {
    
    private String[] d1 = {"B", "A", "O", "O", "Qu", "M"}; 
    private String[] d2 = {"U", "T", "E", "S", "L", "P"};
    private String[] d3 = {"I", "G", "E", "N", "V", "T"};
    private String[] d4 = {"O", "U", "L", "I", "E", "R"};
    private String[] d5 = {"A", "C", "E", "S", "L", "R"};
    private String[] d6 = {"R", "A", "T", "I", "B", "L"};
    private String[] d7 = {"S", "M", "I", "R", "O", "A"};
    private String[] d8 = {"I", "S", "E", "E", "F", "H"};
    private String[] d9 = {"S", "O", "T", "E", "N", "D"};
    private String[] d10 = {"A", "I", "C", "O", "F", "R"};
    private String[] d11 = {"V", "N", "Z", "D", "A", "E"};
    private String[] d12 = {"I", "E", "A", "T", "A", "O"};
    private String[] d13 = {"O", "T", "U", "C", "E", "N"};
    private String[] d14 = {"N", "O", "L", "G", "U", "E"};
    private String[] d15 = {"D", "C", "M", "P", "A", "E"};
    private String[] d16 = {"E", "R", "I", "N", "S", "H"};

    
    private int _min=1; 
    private int _max=6; 
    private int _id; 
    private String[] _faces;
    
    
    //constructor
    //gli si passa un id (numero del dado da 1 a 16)
    //e un array di lettere corrispondenti al dado in base alle specifiche 
    public DiceFactory(int id, String[] face){ 
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
    
}
