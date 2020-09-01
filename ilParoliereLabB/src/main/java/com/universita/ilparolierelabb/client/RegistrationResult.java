/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.universita.ilparolierelabb.client;

/**
 * Enumerazione che rappresenta il risultato di una registrazione
 * @author Momo
 */
public enum RegistrationResult 
{
    Ok,
    /**
     * Registazione riuscita
     */
    Failed,
    /**
     * Registazione fallita
     */
    NotConfirmed,
    /**
     * Registazione in attesa
     */
    Aborted
    /**
     * Registrazione abortita
     */
}
