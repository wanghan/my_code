/**
 * 
 */
package client.rmi;

import hibernate.DbAuthor;
import hibernate.DbPaper;
import hibernate.HibernateSessionFactory;

import rmi.*;

import java.io.IOException;
import java.rmi.Naming;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

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
		int id=105;
		
		try {
			AssociateResult result[]=SearchRMIClient.getInstance().getAssociatePapers(id);
			for (AssociateResult associateResult : result) {
				System.out.println(associateResult.title);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	private SearcherRMIInterface searcher;
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
	
	public List<DbPaper> getTestPaperList(){
		
		Integer ids[]=new Integer[100];
		for(int i=0;i<100;++i){
			ids[i]=1*5*i;
		}
		
		Criteria criter = session.createCriteria(DbPaper.class);
		Criterion cron = Restrictions.in("tmIndex", ids);
		criter.add(cron);
		List<DbPaper> result = criter.list();
		
		return result;
	}
	
	public static SearchRMIClient getInstance(){
		if(instance==null)
			instance=new SearchRMIClient();
		return instance;
	}
	
	public AssociateResult[] getAssociatePapers(int papertmindex) throws IOException{
		return searcher.getAssociatePapers(papertmindex);
	}
	
	public AssociateResult[] getAssociateAuthors(int authortmindex) throws IOException{
		return searcher.getAssociateAuthors(authortmindex);
	}
	
	public List<DbPaper> searchPapers(String keywords) throws IOException{
		Integer[] re= searcher.searchPapers(keywords);
		
		Criteria criter = session.createCriteria(DbPaper.class);
		Criterion cron = Restrictions.in("tmIndex", re);
		criter.add(cron);
		List<DbPaper> result = criter.list();
		
		return result;
	}
	
	public List<DbPaper> getDbPapersInDBByTmIndex(List<Integer> ids){		
		Criteria criter = session.createCriteria(DbPaper.class);
		Criterion cron = Restrictions.in("tmIndex", ids);
		criter.add(cron);
		List<DbPaper> result = criter.list();
		
		return result;
	}
	
	public List<DbAuthor> getDbAuthorsInDBByTmIndex(List<Integer> ids){		
		Criteria criter = session.createCriteria(DbAuthor.class);
		Criterion cron = Restrictions.in("tmIndex", ids);
		criter.add(cron);
		List<DbAuthor> result = criter.list();
		
		return result;
	}
	
	/**
	 * 
	 * @param id db id
	 * @return
	 */
	public DbAuthor getDbAuthorFromDb(long id){
		Criteria criter = session.createCriteria(DbAuthor.class);
		Criterion cron = Restrictions.eq("id", id);
		criter.add(cron);
		List<DbAuthor> result = criter.list();
		
		if(result.size()>0)
			return result.get(0);
		else
			return null;
	}
	
	public DbPaper getDbPaperFromDb(int id){
		Criteria criter = session.createCriteria(DbPaper.class);
		Criterion cron = Restrictions.eq("id", id);
		criter.add(cron);
		List<DbPaper> result = criter.list();
		
		if(result.size()>0)
			return result.get(0);
		else
			return null;
	}
	
	public List<DbAuthor> searchAuthors(String keywords) throws Exception{
		Integer [] re= searcher.searchAuthors(keywords);
		Criteria criter = session.createCriteria(DbAuthor.class);
		Criterion cron = Restrictions.in("tmIndex", re);
		criter.add(cron);
		List<DbAuthor> result = criter.list();
		
		return result;
	}
}
