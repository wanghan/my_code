/**
 * 
 */
package rmi;

import java.rmi.Remote;

import org.hibernate.Session;


/**
 * @author wanghan
 *
 */
public interface SearcherRMIInterface extends Remote {
	public Integer[] searchPapers(String keywords) throws Exception;
	public Integer[] searchAuthors(String keywords) throws Exception;
	public AssociateResult[] getAssociatePapers(int paperId) throws Exception;
}
