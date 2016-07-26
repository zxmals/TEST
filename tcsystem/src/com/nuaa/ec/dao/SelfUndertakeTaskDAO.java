package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.SelfUndertakeTask;

/**
 	* A data access object (DAO) providing persistence and search support for SelfUndertakeTask entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.SelfUndertakeTask
  * @author MyEclipse Persistence Tools 
 */
public class SelfUndertakeTaskDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SelfUndertakeTaskDAO.class);
		//property constants
	public static final String UNDERTAKE_TASK_NAME = "undertakeTaskName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(SelfUndertakeTask transientInstance) {
        log.debug("saving SelfUndertakeTask instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(SelfUndertakeTask persistentInstance) {
        log.debug("deleting SelfUndertakeTask instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public SelfUndertakeTask findById( java.lang.String id) {
        log.debug("getting SelfUndertakeTask instance with id: " + id);
        try {
            SelfUndertakeTask instance = (SelfUndertakeTask) getSession()
                    .get("com.nuaa.ec.model.SelfUndertakeTask", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SelfUndertakeTask instance) {
        log.debug("finding SelfUndertakeTask instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.SelfUndertakeTask")
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
      log.debug("finding SelfUndertakeTask instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SelfUndertakeTask as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByUndertakeTaskName(Object undertakeTaskName
	) {
		return findByProperty(UNDERTAKE_TASK_NAME, undertakeTaskName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all SelfUndertakeTask instances");
		try {
			String queryString = "from SelfUndertakeTask";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public SelfUndertakeTask merge(SelfUndertakeTask detachedInstance) {
        log.debug("merging SelfUndertakeTask instance");
        try {
            SelfUndertakeTask result = (SelfUndertakeTask) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SelfUndertakeTask instance) {
        log.debug("attaching dirty SelfUndertakeTask instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SelfUndertakeTask instance) {
        log.debug("attaching clean SelfUndertakeTask instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}