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
package com.universita.ilparolierelabb.server;


/**
 * Dice Rappresenta un dado
 * @author andreagirola
 */
public class Dice
{

    
    private final int _min = 0; 
    private final int _max = 5; 
    private int _id = 0; 
    private final String[] _faces;
    
    /***
     * Dice Costruisce un dado
     * @param id Identificativo del dado
     * @param face Facce del dado
     */
    public Dice(int id, String[] face){ 
        this._id = id; 
        this._faces = face; 
    }
    /***
     * getDiceId Ritorna l'identificativo del dado
     * @return l'identificativo del dado
     */
    public int getDiceId(){
        return this._id; 
    }
    /**
     * getRandomFace Ottiene una faccia casuale del dado
     * @return stringa che rappresenta il contenuto della faccia generata
     */
    public String getRandomFace()
    {
        int rand = (int) (Math.random() * ((_max - _min) + 1)) + _min; 
        return this._faces[rand]; 
    }
}
