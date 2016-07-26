package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfundergraduateTutorGuidanceCache;

/**
 	* A data access object (DAO) providing persistence and search support for TfundergraduateTutorGuidanceCache entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfundergraduateTutorGuidanceCache
  * @author MyEclipse Persistence Tools 
 */
public class TfundergraduateTutorGuidanceCacheDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfundergraduateTutorGuidanceCacheDAO.class);
		//property constants
	public static final String DESCRIBE = "describe";
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(TfundergraduateTutorGuidanceCache transientInstance) {
        log.debug("saving TfundergraduateTutorGuidanceCache instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfundergraduateTutorGuidanceCache persistentInstance) {
        log.debug("deleting TfundergraduateTutorGuidanceCache instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfundergraduateTutorGuidanceCache findById( java.lang.String id) {
        log.debug("getting TfundergraduateTutorGuidanceCache instance with id: " + id);
        try {
            TfundergraduateTutorGuidanceCache instance = (TfundergraduateTutorGuidanceCache) getSession()
                    .get("com.nuaa.ec.model.TfundergraduateTutorGuidanceCache", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfundergraduateTutorGuidanceCache instance) {
        log.debug("finding TfundergraduateTutorGuidanceCache instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfundergraduateTutorGuidanceCache")
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
      log.debug("finding TfundergraduateTutorGuidanceCache instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfundergraduateTutorGuidanceCache as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDescribe(Object describe
	) {
		return findByProperty(DESCRIBE, describe
		);
	}
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all TfundergraduateTutorGuidanceCache instances");
		try {
			String queryString = "from TfundergraduateTutorGuidanceCache";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfundergraduateTutorGuidanceCache merge(TfundergraduateTutorGuidanceCache detachedInstance) {
        log.debug("merging TfundergraduateTutorGuidanceCache instance");
        try {
            TfundergraduateTutorGuidanceCache result = (TfundergraduateTutorGuidanceCache) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfundergraduateTutorGuidanceCache instance) {
        log.debug("attaching dirty TfundergraduateTutorGuidanceCache instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfundergraduateTutorGuidanceCache instance) {
        log.debug("attaching clean TfundergraduateTutorGuidanceCache instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}