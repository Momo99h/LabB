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
package com.universita.ilparolierelabb.common;
import com.universita.ilparolierelabb.common.sql.SQLConnectionParameters;

/**
 * Impostazioni del programma
 * @author Momo
 */
public class Settings 
{
    public static SQLConnectionParameters connectionParam = new SQLConnectionParameters();
    public static String serverName = "IlParoliereLabB - server";
    public static String clientName = "IlParoliereLabB - client";
    public static int emailCodeTimeOut = 10*60;
}
