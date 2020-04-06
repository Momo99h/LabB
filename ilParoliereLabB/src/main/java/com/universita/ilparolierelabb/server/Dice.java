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

    
    private final int _min = 0; 
    private final int _max = 5; 
    private int _id = 0; 
    private String[] _faces;
    
    
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
    public String getRandomFace()
    {
        int rand = (int) (Math.random() * ((_max - _min) + 1)) + _min; 
        return this._faces[rand]; 
    }
}
