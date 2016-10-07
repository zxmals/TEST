package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.PeriodicalType;

/**
 	* A data access object (DAO) providing persistence and search support for PeriodicalType entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.PeriodicalType
  * @author MyEclipse Persistence Tools 
 */
public class PeriodicalTypeDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PeriodicalTypeDAO.class);
		//property constants
	public static final String PTYPE_NAME = "ptypeName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(PeriodicalType transientInstance) {
        log.debug("saving PeriodicalType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PeriodicalType persistentInstance) {
        log.debug("deleting PeriodicalType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PeriodicalType findById( java.lang.String id) {
        log.debug("getting PeriodicalType instance with id: " + id);
        try {
            PeriodicalType instance = (PeriodicalType) getSession()
                    .get("com.nuaa.ec.model.PeriodicalType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PeriodicalType instance) {
        log.debug("finding PeriodicalType instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.PeriodicalType")
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
      log.debug("finding PeriodicalType instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PeriodicalType as model where model." 
         						+ propertyName + "= ? and spareTire= '1'";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPtypeName(Object ptypeName
	) {
		return findByProperty(PTYPE_NAME, ptypeName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all PeriodicalType instances");
		try {
			String queryString = "from PeriodicalType where spareTire = '1'";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PeriodicalType merge(PeriodicalType detachedInstance) {
        log.debug("merging PeriodicalType instance");
        try {
            PeriodicalType result = (PeriodicalType) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PeriodicalType instance) {
        log.debug("attaching dirty PeriodicalType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PeriodicalType instance) {
        log.debug("attaching clean PeriodicalType instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}