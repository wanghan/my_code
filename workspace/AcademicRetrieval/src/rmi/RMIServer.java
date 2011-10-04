/**
 * 
 */
package rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

/**
 * @author wanghan
 *
 */
public class RMIServer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			LocateRegistry.createRegistry(1099);
			SearcherRMIInterface searcher=new SearcherRMI();
			Naming.rebind("searcher", searcher);
			System.out.println("Server is ready");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
