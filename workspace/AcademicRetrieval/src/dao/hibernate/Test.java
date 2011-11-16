/**
 * 
 */
package dao.hibernate;

import java.rmi.Naming;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import rmi.SearcherRMIInterface;

/**
 * @author wanghan
 * 
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SearcherRMIInterface searcher = (SearcherRMIInterface) Naming
					.lookup("//localhost:1099/searcher");
			Integer ids[]=searcher.searchAuthors("semantic web");
			
			Session session = HibernateUtil.getSessionFactory().openSession();
			
			long be=System.currentTimeMillis();
			Criteria criter=session.createCriteria(DbAuthor.class);
			Criterion cron=Restrictions.in("TmIndex", ids);
			criter.add(cron);
			List<DbAuthor> result=criter.list();
			long end=System.currentTimeMillis();
			System.out.println(end-be);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
