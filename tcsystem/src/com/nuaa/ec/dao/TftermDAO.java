package com.nuaa.ec.dao;

import com.nuaa.ec.model.Tfterm;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Tfterm entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.Tfterm
  * @author MyEclipse Persistence Tools 
 */
public class TftermDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TftermDAO.class);
	

    
    public void save(Tfterm transientInstance) {
        log.debug("saving Tfterm instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Tfterm persistentInstance) {
        log.debug("deleting Tfterm instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Tfterm findById( java.lang.String id) {
        log.debug("getting Tfterm instance with id: " + id);
        try {
            Tfterm instance = (Tfterm) getSession()
                    .get("com.nuaa.ec.model.Tfterm", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Tfterm> findByExample(Tfterm instance) {
        log.debug("finding Tfterm instance by example");
        try {
            @SuppressWarnings("unchecked")
			List<Tfterm> results = (List<Tfterm>) getSession()
                    .createCriteria("com.nuaa.ec.model.Tfterm")
                    .add( create(instance) )
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Tfterm instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Tfterm as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	public List findAll() {
		log.debug("finding all Tfterm instances");
		try {
			String queryString = "from Tfterm where spareTire=1";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Tfterm merge(Tfterm detachedInstance) {
        log.debug("merging Tfterm instance");
        try {
            Tfterm result = (Tfterm) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Tfterm instance) {
        log.debug("attaching dirty Tfterm instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Tfterm instance) {
        log.debug("attaching clean Tfterm instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}