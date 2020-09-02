/**
 * 
 * Progetto laboratorio B
 * 
 * Mohamed Hussein,   737787
 * Anrea Girola,      740724
 * Vanessa Squillace, 728078
 * Simone Spagnolo,   737742
 * 
 */
package com.universita.ilparolierelabb.dictionary;

public class InvalidKey extends Exception {

		private static final long serialVersionUID = 1L;
		
		public InvalidKey(){
			super("Parola non contenuta nel dizionario");
		}

	}
