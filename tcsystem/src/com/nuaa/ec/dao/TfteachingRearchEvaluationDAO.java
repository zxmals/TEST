package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfteachingRearchEvaluation;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachingRearchEvaluation entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachingRearchEvaluation
  * @author MyEclipse Persistence Tools 
 */
public class TfteachingRearchEvaluationDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachingRearchEvaluationDAO.class);
		//property constants
	public static final String REAULTS = "reaults";
	public static final String RATIO = "ratio";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(TfteachingRearchEvaluation transientInstance) {
        log.debug("saving TfteachingRearchEvaluation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachingRearchEvaluation persistentInstance) {
        log.debug("deleting TfteachingRearchEvaluation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachingRearchEvaluation findById( java.lang.String id) {
        log.debug("getting TfteachingRearchEvaluation instance with id: " + id);
        try {
            TfteachingRearchEvaluation instance = (TfteachingRearchEvaluation) getSession()
                    .get("com.nuaa.ec.model.TfteachingRearchEvaluation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachingRearchEvaluation instance) {
        log.debug("finding TfteachingRearchEvaluation instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachingRearchEvaluation")
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
      log.debug("finding TfteachingRearchEvaluation instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachingRearchEvaluation as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByReaults(Object reaults
	) {
		return findByProperty(REAULTS, reaults
		);
	}
	
	public List findByRatio(Object ratio
	) {
		return findByProperty(RATIO, ratio
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all TfteachingRearchEvaluation instances");
		try {
			String queryString = "from TfteachingRearchEvaluation";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachingRearchEvaluation merge(TfteachingRearchEvaluation detachedInstance) {
        log.debug("merging TfteachingRearchEvaluation instance");
        try {
            TfteachingRearchEvaluation result = (TfteachingRearchEvaluation) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachingRearchEvaluation instance) {
        log.debug("attaching dirty TfteachingRearchEvaluation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachingRearchEvaluation instance) {
        log.debug("attaching clean TfteachingRearchEvaluation instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}