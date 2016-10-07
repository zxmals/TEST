package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfundergraduateTutorGuidancePerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfundergraduateTutorGuidancePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfundergraduateTutorGuidancePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfundergraduateTutorGuidancePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfundergraduateTutorGuidancePerformanceDAO.class);
		//property constants
	public static final String STUDENT_QUANTITY = "studentQuantity";
	public static final String YEARS = "years";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	public static final String YEARCEILING = "yearceiling";



    
    public void save(TfundergraduateTutorGuidancePerformance transientInstance) {
        log.debug("saving TfundergraduateTutorGuidancePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfundergraduateTutorGuidancePerformance persistentInstance) {
        log.debug("deleting TfundergraduateTutorGuidancePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfundergraduateTutorGuidancePerformance findById( java.lang.Integer id) {
        log.debug("getting TfundergraduateTutorGuidancePerformance instance with id: " + id);
        try {
            TfundergraduateTutorGuidancePerformance instance = (TfundergraduateTutorGuidancePerformance) getSession()
                    .get("com.nuaa.ec.model.TfundergraduateTutorGuidancePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfundergraduateTutorGuidancePerformance instance) {
        log.debug("finding TfundergraduateTutorGuidancePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfundergraduateTutorGuidancePerformance")
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
      log.debug("finding TfundergraduateTutorGuidancePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfundergraduateTutorGuidancePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByStudentQuantity(Object studentQuantity
	) {
		return findByProperty(STUDENT_QUANTITY, studentQuantity
		);
	}
	
	public List findByYears(Object years
	) {
		return findByProperty(YEARS, years
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
	
	public List findByYearceiling(Object yearceiling
	) {
		return findByProperty(YEARCEILING, yearceiling
		);
	}
	

	public List findAll() {
		log.debug("finding all TfundergraduateTutorGuidancePerformance instances");
		try {
			String queryString = "from TfundergraduateTutorGuidancePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfundergraduateTutorGuidancePerformance merge(TfundergraduateTutorGuidancePerformance detachedInstance) {
        log.debug("merging TfundergraduateTutorGuidancePerformance instance");
        try {
            TfundergraduateTutorGuidancePerformance result = (TfundergraduateTutorGuidancePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfundergraduateTutorGuidancePerformance instance) {
        log.debug("attaching dirty TfundergraduateTutorGuidancePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfundergraduateTutorGuidancePerformance instance) {
        log.debug("attaching clean TfundergraduateTutorGuidancePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}