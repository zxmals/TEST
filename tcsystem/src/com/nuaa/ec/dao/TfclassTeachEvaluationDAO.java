package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfclassTeachEvaluation;

/**
 	* A data access object (DAO) providing persistence and search support for TfclassTeachEvaluation entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfclassTeachEvaluation
  * @author MyEclipse Persistence Tools 
 */
public class TfclassTeachEvaluationDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfclassTeachEvaluationDAO.class);
		//property constants
	public static final String EVALU_RESULT = "evaluResult";
	public static final String RATIO = "ratio";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(TfclassTeachEvaluation transientInstance) {
        log.debug("saving TfclassTeachEvaluation instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfclassTeachEvaluation persistentInstance) {
        log.debug("deleting TfclassTeachEvaluation instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfclassTeachEvaluation findById( java.lang.String id) {
        log.debug("getting TfclassTeachEvaluation instance with id: " + id);
        try {
            TfclassTeachEvaluation instance = (TfclassTeachEvaluation) getSession()
                    .get("com.nuaa.ec.model.TfclassTeachEvaluation", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfclassTeachEvaluation instance) {
        log.debug("finding TfclassTeachEvaluation instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfclassTeachEvaluation")
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
      log.debug("finding TfclassTeachEvaluation instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfclassTeachEvaluation as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByEvaluResult(Object evaluResult
	) {
		return findByProperty(EVALU_RESULT, evaluResult
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
		log.debug("finding all TfclassTeachEvaluation instances");
		try {
			String queryString = "from TfclassTeachEvaluation where spareTire=1";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfclassTeachEvaluation merge(TfclassTeachEvaluation detachedInstance) {
        log.debug("merging TfclassTeachEvaluation instance");
        try {
            TfclassTeachEvaluation result = (TfclassTeachEvaluation) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfclassTeachEvaluation instance) {
        log.debug("attaching dirty TfclassTeachEvaluation instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfclassTeachEvaluation instance) {
        log.debug("attaching clean TfclassTeachEvaluation instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}