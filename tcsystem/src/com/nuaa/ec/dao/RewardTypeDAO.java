package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.RewardType;

/**
 	* A data access object (DAO) providing persistence and search support for RewardType entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.RewardType
  * @author MyEclipse Persistence Tools 
 */
public class RewardTypeDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(RewardTypeDAO.class);
		//property constants
	public static final String REWARD_TYPE_NAME = "rewardTypeName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(RewardType transientInstance) {
        log.debug("saving RewardType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(RewardType persistentInstance) {
        log.debug("deleting RewardType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public RewardType findById( java.lang.String id) {
        log.debug("getting RewardType instance with id: " + id);
        try {
            RewardType instance = (RewardType) getSession()
                    .get("com.nuaa.ec.model.RewardType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(RewardType instance) {
        log.debug("finding RewardType instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.RewardType")
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
      log.debug("finding RewardType instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from RewardType as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByRewardTypeName(Object rewardTypeName
	) {
		return findByProperty(REWARD_TYPE_NAME, rewardTypeName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all RewardType instances");
		try {
			String queryString = "from RewardType";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public RewardType merge(RewardType detachedInstance) {
        log.debug("merging RewardType instance");
        try {
            RewardType result = (RewardType) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(RewardType instance) {
        log.debug("attaching dirty RewardType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(RewardType instance) {
        log.debug("attaching clean RewardType instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}