package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfsummerCourseInternationalConstructionPerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfsummerCourseInternationalConstructionPerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfsummerCourseInternationalConstructionPerformanceDAO.class);
		//property constants
	public static final String PROJECT_ID = "projectId";
	public static final String PROJECT_NAME = "projectName";
	public static final String SCORE = "score";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	public static final String QUANTITY_UNIT = "quantityUnit";



    
    public void save(TfsummerCourseInternationalConstructionPerformance transientInstance) {
        log.debug("saving TfsummerCourseInternationalConstructionPerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfsummerCourseInternationalConstructionPerformance persistentInstance) {
        log.debug("deleting TfsummerCourseInternationalConstructionPerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfsummerCourseInternationalConstructionPerformance findById( java.lang.Integer id) {
        log.debug("getting TfsummerCourseInternationalConstructionPerformance instance with id: " + id);
        try {
            TfsummerCourseInternationalConstructionPerformance instance = (TfsummerCourseInternationalConstructionPerformance) getSession()
                    .get("com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfsummerCourseInternationalConstructionPerformance instance) {
        log.debug("finding TfsummerCourseInternationalConstructionPerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance")
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
      log.debug("finding TfsummerCourseInternationalConstructionPerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfsummerCourseInternationalConstructionPerformance as model where model." 
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
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
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
	
	public List findByQuantityUnit(Object quantityUnit
	) {
		return findByProperty(QUANTITY_UNIT, quantityUnit
		);
	}
	

	public List findAll() {
		log.debug("finding all TfsummerCourseInternationalConstructionPerformance instances");
		try {
			String queryString = "from TfsummerCourseInternationalConstructionPerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfsummerCourseInternationalConstructionPerformance merge(TfsummerCourseInternationalConstructionPerformance detachedInstance) {
        log.debug("merging TfsummerCourseInternationalConstructionPerformance instance");
        try {
            TfsummerCourseInternationalConstructionPerformance result = (TfsummerCourseInternationalConstructionPerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfsummerCourseInternationalConstructionPerformance instance) {
        log.debug("attaching dirty TfsummerCourseInternationalConstructionPerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfsummerCourseInternationalConstructionPerformance instance) {
        log.debug("attaching clean TfsummerCourseInternationalConstructionPerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}