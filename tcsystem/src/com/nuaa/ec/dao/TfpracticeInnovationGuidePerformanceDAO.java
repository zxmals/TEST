package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfpracticeInnovationGuidePerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfpracticeInnovationGuidePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfpracticeInnovationGuidePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfpracticeInnovationGuidePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfpracticeInnovationGuidePerformanceDAO.class);
		//property constants
	public static final String PROJECT_ID = "projectId";
	public static final String PROJECT_NAME = "projectName";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(TfpracticeInnovationGuidePerformance transientInstance) {
        log.debug("saving TfpracticeInnovationGuidePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfpracticeInnovationGuidePerformance persistentInstance) {
        log.debug("deleting TfpracticeInnovationGuidePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfpracticeInnovationGuidePerformance findById( java.lang.Integer id) {
        log.debug("getting TfpracticeInnovationGuidePerformance instance with id: " + id);
        try {
            TfpracticeInnovationGuidePerformance instance = (TfpracticeInnovationGuidePerformance) getSession()
                    .get("com.nuaa.ec.model.TfpracticeInnovationGuidePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfpracticeInnovationGuidePerformance instance) {
        log.debug("finding TfpracticeInnovationGuidePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfpracticeInnovationGuidePerformance")
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
      log.debug("finding TfpracticeInnovationGuidePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfpracticeInnovationGuidePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByProjectId(Object projectId
	) {
		return findByProperty(PROJECT_ID, projectId
		);
	}
	
	public List findByProjectName(Object projectName
	) {
		return findByProperty(PROJECT_NAME, projectName
		);
	}
	
	public List findByFinalScore(Object finalScore
	) {
		return findByProperty(FINAL_SCORE, finalScore
		);
	}
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all TfpracticeInnovationGuidePerformance instances");
		try {
			String queryString = "from TfpracticeInnovationGuidePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfpracticeInnovationGuidePerformance merge(TfpracticeInnovationGuidePerformance detachedInstance) {
        log.debug("merging TfpracticeInnovationGuidePerformance instance");
        try {
            TfpracticeInnovationGuidePerformance result = (TfpracticeInnovationGuidePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfpracticeInnovationGuidePerformance instance) {
        log.debug("attaching dirty TfpracticeInnovationGuidePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfpracticeInnovationGuidePerformance instance) {
        log.debug("attaching clean TfpracticeInnovationGuidePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}