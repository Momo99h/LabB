/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

import java.util.Random;

/**
 *
 * @author andreagirola
 */

public class Matrix {
    Random random = new Random(); 
	int[][] matrix; 


	public int[][] createMatrix(int[][] matrix){
		matrix = new int[4][4];  
	
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				matrix[i][j] = random.nextInt(); 
			}
		}
            return matrix; 
	}

	public void showMatrix(){
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(matrix[i][j] + " "); 
			}
			System.out.println("");
                }
        }

        /*
	public static void main (String[] args) {
		System.out.println("Creating and populating matrix"); 
		createMatrix(matrix);

		//Stampo matrice 
		for(int i=0; i<4; i++) {
			for(int j=0; j<4; j++) {
				System.out.print(matrix[i][j] + " "); 
			}
			System.out.println("");
		}
	} */
}
