package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfoffCampusPracticeGuidancePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfoffCampusPracticeGuidancePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfoffCampusPracticeGuidancePerformanceDAO.class);
		//property constants
	public static final String TEACHER_ID = "teacherId";
	public static final String PROJECT_NAME = "projectName";
	public static final String PROJECT_ID = "projectId";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	public static final String YEAR_CEILING = "yearCeiling";
	public static final String QUANTITY_UNIT = "quantityUnit";
	public static final String SUMHOURS = "sumhours";
	public static final String OFFGUIDANCE_ID = "offguidanceId";



    
    public void save(TfoffCampusPracticeGuidancePerformance transientInstance) {
        log.debug("saving TfoffCampusPracticeGuidancePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfoffCampusPracticeGuidancePerformance persistentInstance) {
        log.debug("deleting TfoffCampusPracticeGuidancePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfoffCampusPracticeGuidancePerformance findById( java.lang.Integer id) {
        log.debug("getting TfoffCampusPracticeGuidancePerformance instance with id: " + id);
        try {
            TfoffCampusPracticeGuidancePerformance instance = (TfoffCampusPracticeGuidancePerformance) getSession()
                    .get("com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfoffCampusPracticeGuidancePerformance instance) {
        log.debug("finding TfoffCampusPracticeGuidancePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance")
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
      log.debug("finding TfoffCampusPracticeGuidancePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfoffCampusPracticeGuidancePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTeacherId(Object teacherId
	) {
		return findByProperty(TEACHER_ID, teacherId
		);
	}
	
	public List findByProjectName(Object projectName
	) {
		return findByProperty(PROJECT_NAME, projectName
		);
	}
	
	public List findByProjectId(Object projectId
	) {
		return findByProperty(PROJECT_ID, projectId
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
	
	public List findByYearCeiling(Object yearCeiling
	) {
		return findByProperty(YEAR_CEILING, yearCeiling
		);
	}
	
	public List findByQuantityUnit(Object quantityUnit
	) {
		return findByProperty(QUANTITY_UNIT, quantityUnit
		);
	}
	
	public List findBySumhours(Object sumhours
	) {
		return findByProperty(SUMHOURS, sumhours
		);
	}
	
	public List findByOffguidanceId(Object offguidanceId
	) {
		return findByProperty(OFFGUIDANCE_ID, offguidanceId
		);
	}
	

	public List findAll() {
		log.debug("finding all TfoffCampusPracticeGuidancePerformance instances");
		try {
			String queryString = "from TfoffCampusPracticeGuidancePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfoffCampusPracticeGuidancePerformance merge(TfoffCampusPracticeGuidancePerformance detachedInstance) {
        log.debug("merging TfoffCampusPracticeGuidancePerformance instance");
        try {
            TfoffCampusPracticeGuidancePerformance result = (TfoffCampusPracticeGuidancePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfoffCampusPracticeGuidancePerformance instance) {
        log.debug("attaching dirty TfoffCampusPracticeGuidancePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfoffCampusPracticeGuidancePerformance instance) {
        log.debug("attaching clean TfoffCampusPracticeGuidancePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}