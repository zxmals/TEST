package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.Nationality;

/**
 	* A data access object (DAO) providing persistence and search support for Nationality entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.Nationality
  * @author MyEclipse Persistence Tools 
 */
public class NationalityDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(NationalityDAO.class);
		//property constants
	public static final String COUNTRY_NAME = "countryName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(Nationality transientInstance) {
        log.debug("saving Nationality instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Nationality persistentInstance) {
        log.debug("deleting Nationality instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Nationality findById( java.lang.String id) {
        log.debug("getting Nationality instance with id: " + id);
        try {
            Nationality instance = (Nationality) getSession()
                    .get("com.nuaa.ec.model.Nationality", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Nationality instance) {
        log.debug("finding Nationality instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.Nationality")
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
      log.debug("finding Nationality instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Nationality as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCountryName(Object countryName
	) {
		return findByProperty(COUNTRY_NAME, countryName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all Nationality instances");
		try {
			String queryString = "from Nationality";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Nationality merge(Nationality detachedInstance) {
        log.debug("merging Nationality instance");
        try {
            Nationality result = (Nationality) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Nationality instance) {
        log.debug("attaching dirty Nationality instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Nationality instance) {
        log.debug("attaching clean Nationality instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}