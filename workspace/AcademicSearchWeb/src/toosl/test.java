package toosl;

import hibernate.DbAuthor;
import hibernate.HibernateSessionFactory;

import java.rmi.Naming;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

import rmi.SearcherRMIInterface;

import dao.hibernate.HibernateUtil;

public class test {

	public test() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			SearcherRMIInterface searcher = (SearcherRMIInterface) Naming
					.lookup("//localhost:1099/searcher");
			Integer ids[] = searcher.searchAuthors("semantic web");

			Session session = HibernateSessionFactory.getSession();

			long be = System.currentTimeMillis();
			Criteria criter = session.createCriteria(DbAuthor.class);
			Criterion cron = Restrictions.in("tmIndex", ids);
			criter.add(cron);
			List<DbAuthor> result = criter.list();
			long end = System.currentTimeMillis();
			System.out.println(end - be);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
