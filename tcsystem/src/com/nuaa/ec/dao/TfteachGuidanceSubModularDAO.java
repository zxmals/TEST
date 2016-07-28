package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfteachGuidanceSubModular;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachGuidanceSubModular entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachGuidanceSubModular
  * @author MyEclipse Persistence Tools 
 */
public class TfteachGuidanceSubModularDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachGuidanceSubModularDAO.class);
		//property constants
	public static final String TEACH_GUIDE_NAME = "teachGuideName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(TfteachGuidanceSubModular transientInstance) {
        log.debug("saving TfteachGuidanceSubModular instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachGuidanceSubModular persistentInstance) {
        log.debug("deleting TfteachGuidanceSubModular instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachGuidanceSubModular findById( java.lang.String id) {
        log.debug("getting TfteachGuidanceSubModular instance with id: " + id);
        try {
            TfteachGuidanceSubModular instance = (TfteachGuidanceSubModular) getSession()
                    .get("com.nuaa.ec.model.TfteachGuidanceSubModular", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachGuidanceSubModular instance) {
        log.debug("finding TfteachGuidanceSubModular instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachGuidanceSubModular")
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
      log.debug("finding TfteachGuidanceSubModular instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachGuidanceSubModular as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTeachGuideName(Object teachGuideName
	) {
		return findByProperty(TEACH_GUIDE_NAME, teachGuideName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all TfteachGuidanceSubModular instances");
		try {
			String queryString = "from TfteachGuidanceSubModular";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachGuidanceSubModular merge(TfteachGuidanceSubModular detachedInstance) {
        log.debug("merging TfteachGuidanceSubModular instance");
        try {
            TfteachGuidanceSubModular result = (TfteachGuidanceSubModular) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachGuidanceSubModular instance) {
        log.debug("attaching dirty TfteachGuidanceSubModular instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachGuidanceSubModular instance) {
        log.debug("attaching clean TfteachGuidanceSubModular instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}