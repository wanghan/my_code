/**
 * 
 */
package client.rmi;

import hibernate.HibernateSessionFactory;

import java.rmi.Naming;

import org.hibernate.Session;

import rmi.SearcherRMIInterface;

/**
 * @author wanghan
 *
 */
public class SearchRMIClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	SearcherRMIInterface searcher;
	private static SearchRMIClient instance=null;
	private Session session;
	private SearchRMIClient() {
		// TODO Auto-generated constructor stub
		try {
			searcher = (SearcherRMIInterface) Naming.lookup("//localhost:1099/searcher");
			session = HibernateSessionFactory.getSession();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	
	
	
	public static SearchRMIClient getInstance(){
		if(instance==null)
			instance=new SearchRMIClient();
		return instance;
	}
}
