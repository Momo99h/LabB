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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Interfaccio di collocquio prioritaria verso database MYSQL.
 * @author Momo
 */
public class MySQLEngine extends sqlEngine
{
    private String _DbConnectionString = "jdbc:mysql://%s:3306/%s";
    private String _usr;
    private String _psw;
    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private Statement st = null;
    
    public MySQLEngine()
    { 
        try
        {
            Class.forName("com.mysql.jdbc.Driver"); 
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    @Override
    public void setConnectionString(SQLConnectionParameters param) 
    {
        _DbConnectionString = String.format(_DbConnectionString,
                param.getIP(),param.getDBName());
        this._psw = param.getPassword();
        this._usr = param.getUsername();
    }

    @Override
    public String getConnectionString() {
        return this._DbConnectionString;
    }

    @Override
    public Boolean checkConnection() 
    {
        Boolean connected = false;
        try 
        {
            con = DriverManager.getConnection(_DbConnectionString,this._usr,this._psw);
            connected = true;        
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        } 
        finally 
        {
            try {
                if (con != null) 
                {
                    con.close();
                }
            } 
            catch (SQLException ex) 
            {
                System.out.println(ex.getMessage());
            }
        }
        return connected;
    }

    @Override
    public Boolean executeQuery(String query) 
    {
        try
        {
           con = DriverManager.getConnection(_DbConnectionString,this._usr,this._psw);
           st = con.createStatement();
           st.executeUpdate(query);
           return true;
        }
        catch(Exception sql_ex)
        {
            sql_ex.printStackTrace();
            return false;
        }
        finally
        {
            try { st.close(); } catch (Exception e) { /* ignored */ }
            try { ps.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
        }
    }

    @Override
    public ResultTable executeQueryRead(String query) 
    {
        ResultTable table;
        String[] tmp;
        try
        {
           con = DriverManager.getConnection(_DbConnectionString,this._usr,this._psw);
           PreparedStatement ps_2 = con.prepareStatement(query,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
           ps = con.prepareStatement(query);
           ResultSet rs2 = ps_2.executeQuery();
           int numRows, numCols;
	   if(!rs2.next())
           {
               return null;
           }
           rs2.last();
           numRows = rs2.getRow();
           numCols = rs2.getMetaData().getColumnCount();
           rs2.first();
           table = new ResultTable(numRows,numCols);
           tmp = new String[numCols];
           rs = ps.executeQuery();
           /*Nomi colonne*/
           for(int i = 0; i < numCols;i++)
           {
               table.setColumnNames(rs.getMetaData().getColumnName(i+1));
           }
           int i = 0;
           while(rs.next())
           {
               for (int j=0; j < numCols; j++)
               {
                   tmp[j] = String.valueOf(rs.getObject(j+1));
                   table.addRow(tmp);
               }
              i++;
           }
        }
        catch(Exception sql_ex)
        {
            sql_ex.printStackTrace();
            return null;
        }
        finally
        {
            try { rs.close(); } catch (Exception e) { /* ignored */ }
            try { ps.close(); } catch (Exception e) { /* ignored */ }
            try { con.close(); } catch (Exception e) { /* ignored */ }
           
        }
        return table;
    }
    
}
