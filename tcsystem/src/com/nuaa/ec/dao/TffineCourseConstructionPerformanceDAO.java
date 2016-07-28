package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TffineCourseConstructionPerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TffineCourseConstructionPerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TffineCourseConstructionPerformance
  * @author MyEclipse Persistence Tools 
 */
public class TffineCourseConstructionPerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TffineCourseConstructionPerformanceDAO.class);
		//property constants
	public static final String COURSE_ID = "courseId";
	public static final String COURSE_NAME = "courseName";
	public static final String COOPERATOR = "cooperator";
	public static final String SINGEL_SCORE = "singelScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	public static final String PROJECT_SUM_SCORE = "projectSumScore";



    
    public void save(TffineCourseConstructionPerformance transientInstance) {
        log.debug("saving TffineCourseConstructionPerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TffineCourseConstructionPerformance persistentInstance) {
        log.debug("deleting TffineCourseConstructionPerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TffineCourseConstructionPerformance findById( java.lang.Integer id) {
        log.debug("getting TffineCourseConstructionPerformance instance with id: " + id);
        try {
            TffineCourseConstructionPerformance instance = (TffineCourseConstructionPerformance) getSession()
                    .get("com.nuaa.ec.model.TffineCourseConstructionPerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TffineCourseConstructionPerformance instance) {
        log.debug("finding TffineCourseConstructionPerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TffineCourseConstructionPerformance")
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
      log.debug("finding TffineCourseConstructionPerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TffineCourseConstructionPerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCourseId(Object courseId
	) {
		return findByProperty(COURSE_ID, courseId
		);
	}
	
	public List findByCourseName(Object courseName
	) {
		return findByProperty(COURSE_NAME, courseName
		);
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
	
	public List findByProjectSumScore(Object projectSumScore
	) {
		return findByProperty(PROJECT_SUM_SCORE, projectSumScore
		);
	}
	

	public List findAll() {
		log.debug("finding all TffineCourseConstructionPerformance instances");
		try {
			String queryString = "from TffineCourseConstructionPerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TffineCourseConstructionPerformance merge(TffineCourseConstructionPerformance detachedInstance) {
        log.debug("merging TffineCourseConstructionPerformance instance");
        try {
            TffineCourseConstructionPerformance result = (TffineCourseConstructionPerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TffineCourseConstructionPerformance instance) {
        log.debug("attaching dirty TffineCourseConstructionPerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TffineCourseConstructionPerformance instance) {
        log.debug("attaching clean TffineCourseConstructionPerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}