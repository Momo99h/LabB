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
package com.universita.ilparolierelabb.common.sql;

/**
 * Rappresenta i risultati di una query di selezione ottenuti da una sottoclasse della classe astratta sqlEngine
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
    
    /**
     * Costruttore
     * @param rows Numero di righe della tabella ritornata dalla query
     * @param columns Numero di collonne della tabella ritornata dalla query
     */
    public ResultTable(int rows,int columns)
    {
        this._rowcount = rows;
        this._columncount = columns;
        this._data = new String[rows][columns];
        this._columnNames = new String[columns];
    }
    /**
     * Ottine il numero di righe
     * @return Numero di righe
     */
    public int getRowCount()
    {
        return _rowcount;
    }
    /**
     * Ottiene il numero di colonne
     * @return Numero di colonne
     */
    public int getColumCount()
    {
        return _columncount;
    }
    /**
     * Aggiunge una riga
     * @param columns Array con i valori delle colonne
     */
    public void addRow(String[] columns)
    {
        this._data[_rowIndex] = columns;
        this._rowIndex++;
    }
    /**
     * Imposta il nome delle colonne
     * @param name Nome colonna
     */
    public void setColumnNames(String name)
    {
        this._columnNames[_columIndex] = name;
        this._columIndex++;
    }
    /**
     * Ottiene un dato dalla tabella
     * @param row Posizione della riga
     * @param column Posizione della colonna
     * @return Stringa che rappresenta il dato
     */
    public String get(int row,int column)
    {
       return this._data[row][column];
    }
    /**
     * Ottiene un dato dalla tabella
     * @param row Posizione della riga
     * @param column Nome della colonna
     * @return Stringa che rappresenta il dato
     */
    public String get(int row,String column)
    {
       int index = getIndex(column);
       if(index == -1) return "";
       return this._data[row][index];
    }
    /**
     * Ritorna la posizione di una colonna nella tabella
     * @param name Nome colonna
     * @return Poisizione nella tabella
     */
    private int getIndex(String name)
    {
        for(int i = 0; i < this._columnNames.length;i++)
        {
            if(this._columnNames[i].equals(name)) return i;
        }
        return -1;
    }
    
    
}
