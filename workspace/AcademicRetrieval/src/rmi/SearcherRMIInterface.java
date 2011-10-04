/**
 * 
 */
package rmi;

import java.rmi.Remote;


import actm.data.Author;
import actm.data.Paper;


/**
 * @author wanghan
 *
 */
public interface SearcherRMIInterface extends Remote {
	public Paper[] searchPapers(String keywords) throws Exception;
	public Author[] searchAuthors(String keywords) throws Exception;
	public AssociateResult[] getAssociatePapers(int paperId) throws Exception;
	public String getPaperTitleById(int id) throws Exception;
}
