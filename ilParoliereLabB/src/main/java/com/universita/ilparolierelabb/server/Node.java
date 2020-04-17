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
public class Node {
    private String value;   //cell value 
    private int x;      //row
    private int y;      //column
    private boolean visited;  //visited flag 

    public Node(String value, int x, int y, boolean visited)
    {
        this.value = value;
        this.x = x; 
        this.y = y; 
        this.visited= visited;
    }
    
    public static enum Position{
        Top, TopRight, Right, BottomRight, Bottom, BottomLeft, Left, TopLeft
    }
    /**
     
       [A][][][]
     * [][][][]1,0 1,1 1,2
     * [][][A][]2,0 2,1 2,2
     * [][][][]3,0 3,1 3,2
     
     */
    public String getValue(){
        return this.value; 
    }
    
    public int xPosition(){
        return this.x; 
    }
    
    public int yPosition(){
        return this.y; 
    }
    
    public MatrixPosition getMatrixPosition(){
        MatrixPosition mp = new MatrixPosition(); 
        mp.setX(this.x);
        mp.setY(this.y);
        return mp;
    }
    
    public boolean getVisit(){
        return this.visited; 
    }
    
    public void setVisit(boolean b){
        this.visited = b; 
    }
    
    public MatrixPosition getNeighborPosition(Position position){
        
        MatrixPosition matrixPosition = new MatrixPosition(); 
        
        switch(position){
            
            case Top :
                matrixPosition.setX(this.x-1);
                matrixPosition.setY(this.y); 
                break; 
                
            case TopRight :
                matrixPosition.setX(this.x-1);
                matrixPosition.setY(this.y+1);
                break;
                
            case Right :
                matrixPosition.setX(this.x);
                matrixPosition.setY(this.y+1);
                break;
                
            case BottomRight :
                matrixPosition.setX(this.x+1);
                matrixPosition.setY(this.y+1);
                break;
                
            case Bottom :
                matrixPosition.setX(this.x+1);
                matrixPosition.setY(this.y);
                break;
                
            case BottomLeft :
                matrixPosition.setX(this.x+1);
                matrixPosition.setY(this.y-1);
                break;
            
            case Left :
                matrixPosition.setX(this.x);
                matrixPosition.setY(this.y-1);
                break;
                
            case TopLeft :
                matrixPosition.setX(this.x-1);
                matrixPosition.setY(this.y-1);
                break;
        } 
        
        return matrixPosition; 
    }
}
