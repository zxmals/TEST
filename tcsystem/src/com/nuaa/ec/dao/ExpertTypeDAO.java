package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.ExpertType;

/**
 	* A data access object (DAO) providing persistence and search support for ExpertType entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.ExpertType
  * @author MyEclipse Persistence Tools 
 */
public class ExpertTypeDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ExpertTypeDAO.class);
		//property constants
	public static final String EXPERT_TYPE_NAME = "expertTypeName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(ExpertType transientInstance) {
        log.debug("saving ExpertType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ExpertType persistentInstance) {
        log.debug("deleting ExpertType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ExpertType findById( java.lang.String id) {
        log.debug("getting ExpertType instance with id: " + id);
        try {
            ExpertType instance = (ExpertType) getSession()
                    .get("com.nuaa.ec.model.ExpertType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ExpertType instance) {
        log.debug("finding ExpertType instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.ExpertType")
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
      log.debug("finding ExpertType instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ExpertType as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByExpertTypeName(Object expertTypeName
	) {
		return findByProperty(EXPERT_TYPE_NAME, expertTypeName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all ExpertType instances");
		try {
			String queryString = "from ExpertType";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ExpertType merge(ExpertType detachedInstance) {
        log.debug("merging ExpertType instance");
        try {
            ExpertType result = (ExpertType) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ExpertType instance) {
        log.debug("attaching dirty ExpertType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ExpertType instance) {
        log.debug("attaching clean ExpertType instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}