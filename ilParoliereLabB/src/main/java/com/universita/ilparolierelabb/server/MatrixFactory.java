/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

/**
 *
 * @author Momo
 */
public class MatrixFactory 
{
    
    private static String[][] _letterMatrix = {{"B", "A", "O", "O", "Qu", "M"}, 
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
    public static String[][] getLetterMatrix(){ 
        return _letterMatrix; 
    }
    
    public static Dice[] CreateDices()
    {
        Dice[] d = new Dice[16];
        for(int i = 0; i < 16; i++)
        {
            d[i] = new Dice(i,_letterMatrix[i]);
        }
        return d;
    }
    
    
}
