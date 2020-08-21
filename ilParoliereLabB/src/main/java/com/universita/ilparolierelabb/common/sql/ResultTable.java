/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.common.sql;

/**
 *
 * @author Momo
 */
public class ResultTable 
{
    private final String[][] _data;
    private int _rowcount;
    private int _columncount;
    private int _rowIndex = 0;
    private int _columIndex = 0;
    private final String[] _columnNames;
    
    public ResultTable(int rows,int columns)
    {
        this._rowcount = rows;
        this._columncount = columns;
        this._data = new String[rows][columns];
        this._columnNames = new String[columns];
    }
    public int getRowCount()
    {
        return _rowcount;
    }
    public int getColumCount()
    {
        return _columncount;
    }
    public void addRow(String[] columns)
    {
        this._data[_rowIndex] = columns;
        this._rowIndex++;
    }
    public void setColumnNames(String name)
    {
        this._columnNames[_columIndex] = name;
        this._columIndex++;
    }
    public String get(int row,int column)
    {
       return this._data[row][column];
    }
    public String get(int row,String column)
    {
       int index = getIndex(column);
       if(index == -1) return "";
       return this._data[row][index];
    }
    private int getIndex(String name)
    {
        for(int i = 0; i < this._columnNames.length;i++)
        {
            if(this._columnNames[i].equals(name)) return i;
        }
        return -1;
    }
    
    
}
