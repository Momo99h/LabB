/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prove;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;

/**
 *
 * @author Momo
 */
public class Client 
{
    private static RMIInterface look_up;
    
    public static void main(String[] args) throws MalformedURLException, RemoteException, NotBoundException 
    {
		Server.Launch();
		/*look_up = (RMIInterface) Naming.lookup("//localhost/Server");
		String txt = JOptionPane.showInputDialog("What is your name?");
			
		String response = look_up.helloTo(txt);
		JOptionPane.showMessageDialog(null, response);*/

	}
}
