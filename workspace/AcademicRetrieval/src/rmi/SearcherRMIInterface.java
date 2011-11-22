/**
 * 
 */
package rmi;

import java.io.IOException;
import java.rmi.Remote;


/**
 * @author wanghan
 *
 */
public interface SearcherRMIInterface extends Remote {
	public Integer[] searchPapers(String keywords) throws IOException;
	public Integer[] searchAuthors(String keywords) throws IOException;
	public AssociateResult[] getAssociatePapers(int paperId) throws IOException;
	public AssociateResult[] getAssociateAuthors(int authorId) throws IOException;
}
