package hibernate;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for DbConference entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see hibernate.DbConference
  * @author MyEclipse Persistence Tools 
 */

public class DbConferenceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(DbConferenceDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String TM_INDEX = "tmIndex";
	public static final String GLOBAL_INDEX = "globalIndex";
	public static final String GLOBAL_NAME = "globalName";



    
    public void save(DbConference transientInstance) {
        log.debug("saving DbConference instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(DbConference persistentInstance) {
        log.debug("deleting DbConference instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public DbConference findById( java.lang.Integer id) {
        log.debug("getting DbConference instance with id: " + id);
        try {
            DbConference instance = (DbConference) getSession()
                    .get("hibernate.DbConference", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(DbConference instance) {
        log.debug("finding DbConference instance by example");
        try {
            List results = getSession()
                    .createCriteria("hibernate.DbConference")
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
      log.debug("finding DbConference instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from DbConference as model where model." 
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
	
	public List findByTmIndex(Object tmIndex
	) {
		return findByProperty(TM_INDEX, tmIndex
		);
	}
	
	public List findByGlobalIndex(Object globalIndex
	) {
		return findByProperty(GLOBAL_INDEX, globalIndex
		);
	}
	
	public List findByGlobalName(Object globalName
	) {
		return findByProperty(GLOBAL_NAME, globalName
		);
	}
	

	public List findAll() {
		log.debug("finding all DbConference instances");
		try {
			String queryString = "from DbConference";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public DbConference merge(DbConference detachedInstance) {
        log.debug("merging DbConference instance");
        try {
            DbConference result = (DbConference) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(DbConference instance) {
        log.debug("attaching dirty DbConference instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(DbConference instance) {
        log.debug("attaching clean DbConference instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}