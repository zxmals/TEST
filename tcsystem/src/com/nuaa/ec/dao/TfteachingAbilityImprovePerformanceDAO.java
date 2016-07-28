package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfteachingAbilityImprovePerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachingAbilityImprovePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachingAbilityImprovePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfteachingAbilityImprovePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachingAbilityImprovePerformanceDAO.class);
		//property constants
	public static final String EVENT_ID = "eventId";
	public static final String EVENT_NAME = "eventName";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	public static final String YEARCEILING = "yearceiling";
	public static final String SUMHOURS = "sumhours";



    
    public void save(TfteachingAbilityImprovePerformance transientInstance) {
        log.debug("saving TfteachingAbilityImprovePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachingAbilityImprovePerformance persistentInstance) {
        log.debug("deleting TfteachingAbilityImprovePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachingAbilityImprovePerformance findById( java.lang.Integer id) {
        log.debug("getting TfteachingAbilityImprovePerformance instance with id: " + id);
        try {
            TfteachingAbilityImprovePerformance instance = (TfteachingAbilityImprovePerformance) getSession()
                    .get("com.nuaa.ec.model.TfteachingAbilityImprovePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachingAbilityImprovePerformance instance) {
        log.debug("finding TfteachingAbilityImprovePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachingAbilityImprovePerformance")
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
      log.debug("finding TfteachingAbilityImprovePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachingAbilityImprovePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByEventId(Object eventId
	) {
		return findByProperty(EVENT_ID, eventId
		);
	}
	
	public List findByEventName(Object eventName
	) {
		return findByProperty(EVENT_NAME, eventName
		);
	}
	
	public List findByFinalScore(Object finalScore
	) {
		return findByProperty(FINAL_SCORE, finalScore
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	
	public List findByYearceiling(Object yearceiling
	) {
		return findByProperty(YEARCEILING, yearceiling
		);
	}
	
	public List findBySumhours(Object sumhours
	) {
		return findByProperty(SUMHOURS, sumhours
		);
	}
	

	public List findAll() {
		log.debug("finding all TfteachingAbilityImprovePerformance instances");
		try {
			String queryString = "from TfteachingAbilityImprovePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachingAbilityImprovePerformance merge(TfteachingAbilityImprovePerformance detachedInstance) {
        log.debug("merging TfteachingAbilityImprovePerformance instance");
        try {
            TfteachingAbilityImprovePerformance result = (TfteachingAbilityImprovePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachingAbilityImprovePerformance instance) {
        log.debug("attaching dirty TfteachingAbilityImprovePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachingAbilityImprovePerformance instance) {
        log.debug("attaching clean TfteachingAbilityImprovePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}