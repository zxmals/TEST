package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfsummerCourseInternationalConstructionLevel;

/**
 	* A data access object (DAO) providing persistence and search support for TfsummerCourseInternationalConstructionLevel entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfsummerCourseInternationalConstructionLevel
  * @author MyEclipse Persistence Tools 
 */
public class TfsummerCourseInternationalConstructionLevelDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfsummerCourseInternationalConstructionLevelDAO.class);
		//property constants
	public static final String PROJECT_LEVEL = "projectLevel";
	public static final String SCORE = "score";
	public static final String REFORM_RESEARCH_ID = "reformResearchId";
	public static final String SPARE_TIRE = "spareTire";
	public static final String UNIT = "unit";



    
    public void save(TfsummerCourseInternationalConstructionLevel transientInstance) {
        log.debug("saving TfsummerCourseInternationalConstructionLevel instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfsummerCourseInternationalConstructionLevel persistentInstance) {
        log.debug("deleting TfsummerCourseInternationalConstructionLevel instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfsummerCourseInternationalConstructionLevel findById( java.lang.String id) {
        log.debug("getting TfsummerCourseInternationalConstructionLevel instance with id: " + id);
        try {
            TfsummerCourseInternationalConstructionLevel instance = (TfsummerCourseInternationalConstructionLevel) getSession()
                    .get("com.nuaa.ec.model.TfsummerCourseInternationalConstructionLevel", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfsummerCourseInternationalConstructionLevel instance) {
        log.debug("finding TfsummerCourseInternationalConstructionLevel instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfsummerCourseInternationalConstructionLevel")
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
      log.debug("finding TfsummerCourseInternationalConstructionLevel instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfsummerCourseInternationalConstructionLevel as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByProjectLevel(Object projectLevel
	) {
		return findByProperty(PROJECT_LEVEL, projectLevel
		);
	}
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
		);
	}
	
	public List findByReformResearchId(Object reformResearchId
	) {
		return findByProperty(REFORM_RESEARCH_ID, reformResearchId
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByUnit(Object unit
	) {
		return findByProperty(UNIT, unit
		);
	}
	

	public List findAll() {
		log.debug("finding all TfsummerCourseInternationalConstructionLevel instances");
		try {
			String queryString = "from TfsummerCourseInternationalConstructionLevel where spareTire=1";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfsummerCourseInternationalConstructionLevel merge(TfsummerCourseInternationalConstructionLevel detachedInstance) {
        log.debug("merging TfsummerCourseInternationalConstructionLevel instance");
        try {
            TfsummerCourseInternationalConstructionLevel result = (TfsummerCourseInternationalConstructionLevel) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfsummerCourseInternationalConstructionLevel instance) {
        log.debug("attaching dirty TfsummerCourseInternationalConstructionLevel instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfsummerCourseInternationalConstructionLevel instance) {
        log.debug("attaching clean TfsummerCourseInternationalConstructionLevel instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}