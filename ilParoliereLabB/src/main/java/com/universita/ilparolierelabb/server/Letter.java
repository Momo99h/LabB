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
public class Letter 
{
    private String value;
    private ArrayList<MatrixPosition> posinmatrix = new ArrayList<>();
    public Letter(String v){ this.value = v;}
    public String getLetter()
    {
        return this.value;
    }
    public int positionCount()
    {
        return this.posinmatrix.size();
    }
    public ArrayList<MatrixPosition> getLetterPositions()
    {
        return this.posinmatrix;
    }
    public void setPositions(ArrayList<MatrixPosition> p){
        this.posinmatrix = p;
    }
    /* DA ELIMINAREE**/
    public void print()
    {
        try
        {
            for(MatrixPosition p : posinmatrix)
            {
                String s = "X-> %s Y -> %S";
                s = String.format(s,p.getX(),p.getY());
                System.out.print(" "+s);
            } 
        }
        catch(Exception e)
        {}
           
    }
    
}
