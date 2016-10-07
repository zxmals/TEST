package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfdegreeThesisGuidancePerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfdegreeThesisGuidancePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfdegreeThesisGuidancePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfdegreeThesisGuidancePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfdegreeThesisGuidancePerformanceDAO.class);
		//property constants
	public static final String DEGREE_THESISN_NAME = "degreeThesisnName";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";



    
    public void save(TfdegreeThesisGuidancePerformance transientInstance) {
        log.debug("saving TfdegreeThesisGuidancePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfdegreeThesisGuidancePerformance persistentInstance) {
        log.debug("deleting TfdegreeThesisGuidancePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfdegreeThesisGuidancePerformance findById( java.lang.String id) {
        log.debug("getting TfdegreeThesisGuidancePerformance instance with id: " + id);
        try {
            TfdegreeThesisGuidancePerformance instance = (TfdegreeThesisGuidancePerformance) getSession()
                    .get("com.nuaa.ec.model.TfdegreeThesisGuidancePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfdegreeThesisGuidancePerformance instance) {
        log.debug("finding TfdegreeThesisGuidancePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfdegreeThesisGuidancePerformance")
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
      log.debug("finding TfdegreeThesisGuidancePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfdegreeThesisGuidancePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDegreeThesisnName(Object degreeThesisnName
	) {
		return findByProperty(DEGREE_THESISN_NAME, degreeThesisnName
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
	

	public List findAll() {
		log.debug("finding all TfdegreeThesisGuidancePerformance instances");
		try {
			String queryString = "from TfdegreeThesisGuidancePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfdegreeThesisGuidancePerformance merge(TfdegreeThesisGuidancePerformance detachedInstance) {
        log.debug("merging TfdegreeThesisGuidancePerformance instance");
        try {
            TfdegreeThesisGuidancePerformance result = (TfdegreeThesisGuidancePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfdegreeThesisGuidancePerformance instance) {
        log.debug("attaching dirty TfdegreeThesisGuidancePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfdegreeThesisGuidancePerformance instance) {
        log.debug("attaching clean TfdegreeThesisGuidancePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}