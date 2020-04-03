/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prove;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Momo
 */
public class Server extends UnicastRemoteObject implements RMIInterface{

    private static final long serialVersionUID = 1L;

    private Server() throws RemoteException 
    {
        super();
    }

    @Override
    public String helloTo(String name) throws RemoteException
    {
        System.err.println(name + " is trying to contact!");
        return "Server says hello to " + name;
    }
    
    public static void Launch()
    {
         try {

            Naming.rebind("//localhost/Server", new Server());            
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }
    }
}