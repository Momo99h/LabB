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
abstract class sqlEngine 
{
    /**
     * Imposta connessione
     * @param param Oggetto che rappresenta la connessione
     */
    protected abstract void setConnectionString(SQLConnectionParameters param);
    /**
     * Ottiene la stringa di connessione
     * @return Stringa di connessione
     */
    protected abstract String getConnectionString();
    /**
     * Verifica la connnessione al database
     * @return true se la connessione è riuscita
     */
    protected abstract Boolean checkConnection();
    /**
     * Esegue un query verso il database
     * @param query Query da eseguire
     * @return true se l'operazione è andata a buon fine
     */
    protected abstract Boolean executeQuery(String query);
    /**
     * Esegue una query di selezione verso il database
     * @param query Query da eseguire
     * @return Oggetto che rappresenta la tabella di selezione
     */
    protected abstract ResultTable executeQueryRead(String query);
}
