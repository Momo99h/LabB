/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.server;

/**
 *
 * @author andreagirola
 */
    
    public class WordFinder {
	public int[][] solution;
	int path = 1;

	// initialize the solution matrix in constructor.
	public WordFinder(int N) {
		solution = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				solution[i][j] = 0;
			}
		}
	}

	public boolean searchWord(String[][] matrix, String word) {
		int N = matrix.length;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (search(matrix, word, i, j, 0, N)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean search(String[][] matrix, String word, int row, int col,
			int index, int N) {

		// check if current cell not already used or character in it is not not
                String s = word.charAt(index)+"";
		if (solution[row][col] != 0 || !matrix[row][col].equals(s)) {
			return false;
		}

		if (index == word.length() - 1) {
			// word is found, return true
			solution[row][col] = path++;
			return true;
		}

		// mark the current cell as 1
		solution[row][col] = path++;		
		// check if cell is already used

		if (row + 1 < N && search(matrix, word, row + 1, col, index + 1, N)) { // go
																				// down
			return true;
		}
		if (row - 1 >= 0 && search(matrix, word, row - 1, col, index + 1, N)) { // go
																				// up
			return true;
		}
		if (col + 1 < N && search(matrix, word, row, col + 1, index + 1, N)) { // go
																				// right
			return true;
		}
		if (col - 1 >= 0 && search(matrix, word, row, col - 1, index + 1, N)) { // go
																				// left
			return true;
		}
		if (row - 1 >= 0 && col + 1 < N
				&& search(matrix, word, row - 1, col + 1, index + 1, N)) {
			// go diagonally up right
			return true;
		}
		if (row - 1 >= 0 && col - 1 >= 0
				&& search(matrix, word, row - 1, col - 1, index + 1, N)) {
			// go diagonally up left
			return true;
		}
		if (row + 1 < N && col - 1 >= 0
				&& search(matrix, word, row + 1, col - 1, index + 1, N)) {
			// go diagonally down left
			return true;
		}
		if (row + 1 < N && col + 1 < N
				&& search(matrix, word, row + 1, col + 1, index + 1, N)) {
			// go diagonally down right
			return true;
		}

		// if none of the option works out, BACKTRACK and return false
		solution[row][col] = 0;
		path--;
		return false;
	}
        
        public static boolean wordIsPresent(String word, String[][] matrix){
            
            WordFinder w = new WordFinder(matrix.length);
            return w.searchWord(matrix, word); 
            
        }
        
        
        //inserire parola già presente nel dizionario!! 
        
        public static int pointsFromWord(String word, String[][] matrix){
            int value = 0;
            int wl = 0;
            
            if(wordIsPresent(word, matrix)){
                wl = word.length();
                
                if(wl== 3 || wl==4){
                value = 1;
                } else if(wl==5){
                value = 2; 
                } else if(wl==6){
                value = 3; 
                } else if(wl==7){
                value = 5;
                } else if(wl>=8){
                value = 11;
                } 
            }
            return value;
        }
        
       
	public void print() {
		for (int i = 0; i < solution.length; i++) {
			for (int j = 0; j < solution.length; j++) {
				System.out.print(" " + solution[i][j]);
			}
			System.out.println();
		}
	} 
}
