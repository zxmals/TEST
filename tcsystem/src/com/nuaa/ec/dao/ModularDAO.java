package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.Modular;

/**
 	* A data access object (DAO) providing persistence and search support for Modular entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.Modular
  * @author MyEclipse Persistence Tools 
 */
public class ModularDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ModularDAO.class);
		//property constants
	public static final String MODULAR_NAME = "modularName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(Modular transientInstance) {
        log.debug("saving Modular instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Modular persistentInstance) {
        log.debug("deleting Modular instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Modular findById( java.lang.String id) {
        log.debug("getting Modular instance with id: " + id);
        try {
            Modular instance = (Modular) getSession()
                    .get("com.nuaa.ec.model.Modular", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Modular instance) {
        log.debug("finding Modular instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.Modular")
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
      log.debug("finding Modular instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Modular as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByModularName(Object modularName
	) {
		return findByProperty(MODULAR_NAME, modularName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all Modular instances");
		try {
			String queryString = "from Modular";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Modular merge(Modular detachedInstance) {
        log.debug("merging Modular instance");
        try {
            Modular result = (Modular) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Modular instance) {
        log.debug("attaching dirty Modular instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Modular instance) {
        log.debug("attaching clean Modular instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}