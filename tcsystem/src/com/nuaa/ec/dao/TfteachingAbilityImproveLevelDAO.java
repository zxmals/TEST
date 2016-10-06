package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfteachingAbilityImproveLevel;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachingAbilityImproveLevel entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachingAbilityImproveLevel
  * @author MyEclipse Persistence Tools 
 */
public class TfteachingAbilityImproveLevelDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachingAbilityImproveLevelDAO.class);
		//property constants
	public static final String IMPROVE_LEVEL = "improveLevel";
	public static final String RATIO = "ratio";
	public static final String SPARE_TIRE = "spareTire";
	public static final String UNIT = "unit";



    
    public void save(TfteachingAbilityImproveLevel transientInstance) {
        log.debug("saving TfteachingAbilityImproveLevel instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachingAbilityImproveLevel persistentInstance) {
        log.debug("deleting TfteachingAbilityImproveLevel instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachingAbilityImproveLevel findById( java.lang.String id) {
        log.debug("getting TfteachingAbilityImproveLevel instance with id: " + id);
        try {
            TfteachingAbilityImproveLevel instance = (TfteachingAbilityImproveLevel) getSession()
                    .get("com.nuaa.ec.model.TfteachingAbilityImproveLevel", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachingAbilityImproveLevel instance) {
        log.debug("finding TfteachingAbilityImproveLevel instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachingAbilityImproveLevel")
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
      log.debug("finding TfteachingAbilityImproveLevel instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachingAbilityImproveLevel as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByImproveLevel(Object improveLevel
	) {
		return findByProperty(IMPROVE_LEVEL, improveLevel
		);
	}
	
	public List findByRatio(Object ratio
	) {
		return findByProperty(RATIO, ratio
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
		log.debug("finding all TfteachingAbilityImproveLevel instances");
		try {
			String queryString = "from TfteachingAbilityImproveLevel where spareTire=1";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachingAbilityImproveLevel merge(TfteachingAbilityImproveLevel detachedInstance) {
        log.debug("merging TfteachingAbilityImproveLevel instance");
        try {
            TfteachingAbilityImproveLevel result = (TfteachingAbilityImproveLevel) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachingAbilityImproveLevel instance) {
        log.debug("attaching dirty TfteachingAbilityImproveLevel instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachingAbilityImproveLevel instance) {
        log.debug("attaching clean TfteachingAbilityImproveLevel instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}