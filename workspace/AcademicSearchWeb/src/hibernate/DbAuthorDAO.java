package hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for DbAuthor entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see hibernate.DbAuthor
  * @author MyEclipse Persistence Tools 
 */

public class DbAuthorDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(DbAuthorDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String LINK = "link";
	public static final String ACM_INDEX = "acmIndex";
	public static final String TM_INDEX = "tmIndex";
	public static final String POSITION = "position";
	public static final String AFFILIATION = "affiliation";
	public static final String ADDRESS = "address";
	public static final String HOMEPAGE = "homepage";
	public static final String INTEREST = "interest";



    
    public void save(DbAuthor transientInstance) {
        log.debug("saving DbAuthor instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(DbAuthor persistentInstance) {
        log.debug("deleting DbAuthor instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DbAuthor findById( java.lang.Long id) {
        log.debug("getting DbAuthor instance with id: " + id);
        try {
            DbAuthor instance = (DbAuthor) getSession()
                    .get("hibernate.DbAuthor", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(DbAuthor instance) {
        log.debug("finding DbAuthor instance by example");
        try {
            List results = getSession()
                    .createCriteria("hibernate.DbAuthor")
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
      log.debug("finding DbAuthor instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from DbAuthor as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List findByLink(Object link
	) {
		return findByProperty(LINK, link
		);
	}
	
	public List findByAcmIndex(Object acmIndex
	) {
		return findByProperty(ACM_INDEX, acmIndex
		);
	}
	
	public List findByTmIndex(Object tmIndex
	) {
		return findByProperty(TM_INDEX, tmIndex
		);
	}
	
	public List findByPosition(Object position
	) {
		return findByProperty(POSITION, position
		);
	}
	
	public List findByAffiliation(Object affiliation
	) {
		return findByProperty(AFFILIATION, affiliation
		);
	}
	
	public List findByAddress(Object address
	) {
		return findByProperty(ADDRESS, address
		);
	}
	
	public List findByHomepage(Object homepage
	) {
		return findByProperty(HOMEPAGE, homepage
		);
	}
	
	public List findByInterest(Object interest
	) {
		return findByProperty(INTEREST, interest
		);
	}
	

	public List findAll() {
		log.debug("finding all DbAuthor instances");
		try {
			String queryString = "from DbAuthor";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public DbAuthor merge(DbAuthor detachedInstance) {
        log.debug("merging DbAuthor instance");
        try {
            DbAuthor result = (DbAuthor) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(DbAuthor instance) {
        log.debug("attaching dirty DbAuthor instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DbAuthor instance) {
        log.debug("attaching clean DbAuthor instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}