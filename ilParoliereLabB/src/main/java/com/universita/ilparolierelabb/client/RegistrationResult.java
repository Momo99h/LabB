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
