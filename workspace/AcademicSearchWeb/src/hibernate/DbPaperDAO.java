package hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for DbPaper entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see hibernate.DbPaper
  * @author MyEclipse Persistence Tools 
 */

public class DbPaperDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(DbPaperDAO.class);
		//property constants
	public static final String TITLE = "title";
	public static final String PAGES = "pages";
	public static final String SOURCE = "source";
	public static final String LINK = "link";
	public static final String ABSTRACT_ = "abstract_";
	public static final String DOI = "doi";
	public static final String DOI_LINK = "doiLink";
	public static final String TM_INDEX = "tmIndex";



    
    public void save(DbPaper transientInstance) {
        log.debug("saving DbPaper instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(DbPaper persistentInstance) {
        log.debug("deleting DbPaper instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DbPaper findById( java.lang.Integer id) {
        log.debug("getting DbPaper instance with id: " + id);
        try {
            DbPaper instance = (DbPaper) getSession()
                    .get("hibernate.DbPaper", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(DbPaper instance) {
        log.debug("finding DbPaper instance by example");
        try {
            List results = getSession()
                    .createCriteria("hibernate.DbPaper")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding DbPaper instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from DbPaper as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTitle(Object title
	) {
		return findByProperty(TITLE, title
		);
	}
	
	public List findByPages(Object pages
	) {
		return findByProperty(PAGES, pages
		);
	}
	
	public List findBySource(Object source
	) {
		return findByProperty(SOURCE, source
		);
	}
	
	public List findByLink(Object link
	) {
		return findByProperty(LINK, link
		);
	}
	
	public List findByAbstract_(Object abstract_
	) {
		return findByProperty(ABSTRACT_, abstract_
		);
	}
	
	public List findByDoi(Object doi
	) {
		return findByProperty(DOI, doi
		);
	}
	
	public List findByDoiLink(Object doiLink
	) {
		return findByProperty(DOI_LINK, doiLink
		);
	}
	
	public List findByTmIndex(Object tmIndex
	) {
		return findByProperty(TM_INDEX, tmIndex
		);
	}
	

	public List findAll() {
		log.debug("finding all DbPaper instances");
		try {
			String queryString = "from DbPaper";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public DbPaper merge(DbPaper detachedInstance) {
        log.debug("merging DbPaper instance");
        try {
            DbPaper result = (DbPaper) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(DbPaper instance) {
        log.debug("attaching dirty DbPaper instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DbPaper instance) {
        log.debug("attaching clean DbPaper instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}