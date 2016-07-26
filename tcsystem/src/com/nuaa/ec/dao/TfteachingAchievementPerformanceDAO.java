package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfteachingAchievementPerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachingAchievementPerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachingAchievementPerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfteachingAchievementPerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachingAchievementPerformanceDAO.class);
		//property constants
	public static final String COOPERATOR = "cooperator";
	public static final String SINGEL_SCORE = "singelScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	public static final String PROJECT_SUM_SCORE = "projectSumScore";
	public static final String PROJECT_ID = "projectId";
	public static final String PROJECT_NAME = "projectName";



    
    public void save(TfteachingAchievementPerformance transientInstance) {
        log.debug("saving TfteachingAchievementPerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachingAchievementPerformance persistentInstance) {
        log.debug("deleting TfteachingAchievementPerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachingAchievementPerformance findById( java.lang.Integer id) {
        log.debug("getting TfteachingAchievementPerformance instance with id: " + id);
        try {
            TfteachingAchievementPerformance instance = (TfteachingAchievementPerformance) getSession()
                    .get("com.nuaa.ec.model.TfteachingAchievementPerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachingAchievementPerformance instance) {
        log.debug("finding TfteachingAchievementPerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachingAchievementPerformance")
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
      log.debug("finding TfteachingAchievementPerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachingAchievementPerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCooperator(Object cooperator
	) {
		return findByProperty(COOPERATOR, cooperator
		);
	}
	
	public List findBySingelScore(Object singelScore
	) {
		return findByProperty(SINGEL_SCORE, singelScore
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
	
	public List findByProjectSumScore(Object projectSumScore
	) {
		return findByProperty(PROJECT_SUM_SCORE, projectSumScore
		);
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
	

	public List findAll() {
		log.debug("finding all TfteachingAchievementPerformance instances");
		try {
			String queryString = "from TfteachingAchievementPerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachingAchievementPerformance merge(TfteachingAchievementPerformance detachedInstance) {
        log.debug("merging TfteachingAchievementPerformance instance");
        try {
            TfteachingAchievementPerformance result = (TfteachingAchievementPerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachingAchievementPerformance instance) {
        log.debug("attaching dirty TfteachingAchievementPerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachingAchievementPerformance instance) {
        log.debug("attaching clean TfteachingAchievementPerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}