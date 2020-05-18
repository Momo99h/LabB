/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import com.universita.ilparolierelabb.common.Utility;
import java.util.ArrayList;

/**
 *
 * @author Momo
 */
public class MatrixFactory 
{
    
    private static Dice[] _dices = new Dice[16];
    private static String[][] gameMatrix = new String[4][4]; 
    private static ArrayList<Integer> list = new ArrayList<Integer>();
    
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
    
    public static void createDices()
    {
        for(int i = 0; i < 16; i++)
        {
            _dices[i] = new Dice(i,_letterMatrix[i]);
        }
    }
    
    public static String[][] getRandomMatrix()
    {
        
        for(int i=0; i<16; i++){
            list.add(i); 
        }
        
        for(int i=0; i<4; i++){
            for(int j=0; j<4; j++){
                int indice = Utility.getRandomInt(0, list.size()-1);
                gameMatrix[i][j] = _dices[list.get(indice)].getRandomFace(); 
                list.remove(indice);
            }
        }
        return gameMatrix;
       
    }
    
    
}
