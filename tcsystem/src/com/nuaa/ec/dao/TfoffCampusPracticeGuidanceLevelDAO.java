package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfoffCampusPracticeGuidanceLevel;

/**
 	* A data access object (DAO) providing persistence and search support for TfoffCampusPracticeGuidanceLevel entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfoffCampusPracticeGuidanceLevel
  * @author MyEclipse Persistence Tools 
 */
public class TfoffCampusPracticeGuidanceLevelDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfoffCampusPracticeGuidanceLevelDAO.class);
		//property constants
	public static final String PROJECT_TYPE = "projectType";
	public static final String UNIT = "unit";
	public static final String SCORE = "score";
	public static final String TEACH_GUIDE_ID = "teachGuideId";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(TfoffCampusPracticeGuidanceLevel transientInstance) {
        log.debug("saving TfoffCampusPracticeGuidanceLevel instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfoffCampusPracticeGuidanceLevel persistentInstance) {
        log.debug("deleting TfoffCampusPracticeGuidanceLevel instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfoffCampusPracticeGuidanceLevel findById( java.lang.String id) {
        log.debug("getting TfoffCampusPracticeGuidanceLevel instance with id: " + id);
        try {
            TfoffCampusPracticeGuidanceLevel instance = (TfoffCampusPracticeGuidanceLevel) getSession()
                    .get("com.nuaa.ec.model.TfoffCampusPracticeGuidanceLevel", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfoffCampusPracticeGuidanceLevel instance) {
        log.debug("finding TfoffCampusPracticeGuidanceLevel instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfoffCampusPracticeGuidanceLevel")
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
      log.debug("finding TfoffCampusPracticeGuidanceLevel instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfoffCampusPracticeGuidanceLevel as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByProjectType(Object projectType
	) {
		return findByProperty(PROJECT_TYPE, projectType
		);
	}
	
	public List findByUnit(Object unit
	) {
		return findByProperty(UNIT, unit
		);
	}
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
		);
	}
	
	public List findByTeachGuideId(Object teachGuideId
	) {
		return findByProperty(TEACH_GUIDE_ID, teachGuideId
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all TfoffCampusPracticeGuidanceLevel instances");
		try {
			String queryString = "from TfoffCampusPracticeGuidanceLevel where spareTire=1";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfoffCampusPracticeGuidanceLevel merge(TfoffCampusPracticeGuidanceLevel detachedInstance) {
        log.debug("merging TfoffCampusPracticeGuidanceLevel instance");
        try {
            TfoffCampusPracticeGuidanceLevel result = (TfoffCampusPracticeGuidanceLevel) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfoffCampusPracticeGuidanceLevel instance) {
        log.debug("attaching dirty TfoffCampusPracticeGuidanceLevel instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfoffCampusPracticeGuidanceLevel instance) {
        log.debug("attaching clean TfoffCampusPracticeGuidanceLevel instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}