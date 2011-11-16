/**
 * 
 */
package rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @author wanghan
 *
 */
public class ModelLoaderRMIClient {
	public ModelLoaderRMIInterface loader;
	public ModelLoaderRMIClient() throws MalformedURLException, RemoteException, NotBoundException {
		 loader=(ModelLoaderRMIInterface)Naming.lookup("//localhost:1099/searcher");
	}
	
	public static void main(String[] args) {
		
		try {
			ModelLoaderRMIClient client=new ModelLoaderRMIClient();
			System.out.println(client.loader.getModel().A);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
