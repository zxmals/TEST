package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.SubModular;

/**
 	* A data access object (DAO) providing persistence and search support for SubModular entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.SubModular
  * @author MyEclipse Persistence Tools 
 */
public class SubModularDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(SubModularDAO.class);
		//property constants
	public static final String SUB_MODULAR_NAME = "subModularName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(SubModular transientInstance) {
        log.debug("saving SubModular instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(SubModular persistentInstance) {
        log.debug("deleting SubModular instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public SubModular findById( java.lang.String id) {
        log.debug("getting SubModular instance with id: " + id);
        try {
            SubModular instance = (SubModular) getSession()
                    .get("com.nuaa.ec.model.SubModular", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(SubModular instance) {
        log.debug("finding SubModular instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.SubModular")
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
      log.debug("finding SubModular instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from SubModular as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findBySubModularName(Object subModularName
	) {
		return findByProperty(SUB_MODULAR_NAME, subModularName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all SubModular instances");
		try {
			String queryString = "from SubModular";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public SubModular merge(SubModular detachedInstance) {
        log.debug("merging SubModular instance");
        try {
            SubModular result = (SubModular) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SubModular instance) {
        log.debug("attaching dirty SubModular instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(SubModular instance) {
        log.debug("attaching clean SubModular instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}