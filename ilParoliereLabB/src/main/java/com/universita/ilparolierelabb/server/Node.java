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

    public boolean hasNeighbor(MatrixPosition nextLetterPosition) 
    {
        MatrixPosition posVicino;
        Direction dir = Direction.Top;
        for(int i = 0; i <= 7; i++)
        {
            dir.setValue(i);
            posVicino = this.getNeighborPosition(dir);
            if(posVicino.getX() == nextLetterPosition.getX() && posVicino.getY() == nextLetterPosition.getY())
            {
                return true;
            }
        }
        return false;
    }
    
    public static enum Direction
    {
        Top(0),TopRight(1),Right(2),BottomRight(3),Bottom(4),BottomLeft(5),Left(6),TopLeft(7);
        
        private int value;

        Direction(int value) 
        {
            this.value = value;
        }
        public int getValue() 
        {
            return value;
        }
        public void setValue(int val)
        {
            this.value = val;
        }
    }
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
    
    public MatrixPosition getNeighborPosition(Direction position){
        
        MatrixPosition matrixPosition = new MatrixPosition(); 
        switch(position.getValue()){
            
            case 0:
                matrixPosition.setX(this.x-1);
                matrixPosition.setY(this.y); 
                break; 
                
            case 1 :
                matrixPosition.setX(this.x-1);
                matrixPosition.setY(this.y+1);
                break;
                
            case 2 :
                matrixPosition.setX(this.x);
                matrixPosition.setY(this.y+1);
                break;
                
            case 3 :
                matrixPosition.setX(this.x+1);
                matrixPosition.setY(this.y+1);
                break;
                
            case 4 :
                matrixPosition.setX(this.x+1);
                matrixPosition.setY(this.y);
                break;
                
            case 5 :
                matrixPosition.setX(this.x+1);
                matrixPosition.setY(this.y-1);
                break;
            
            case 6 :
                matrixPosition.setX(this.x);
                matrixPosition.setY(this.y-1);
                break;
                
            case 7 :
                matrixPosition.setX(this.x-1);
                matrixPosition.setY(this.y-1);
                break;
        } 
        
        return matrixPosition; 
    }
}
