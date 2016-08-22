package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.ResearchLab;

/**
 	* A data access object (DAO) providing persistence and search support for ResearchLab entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.ResearchLab
  * @author MyEclipse Persistence Tools 
 */
public class ResearchLabDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ResearchLabDAO.class);
		//property constants
	public static final String RESEARCH_LAB_NAME = "researchLabName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String RESEARCH_LAB_ADMIN_ID = "researchLabAdminId";
	public static final String RESEARCH_LAB_ADMIN = "researchLabAdmin";



    
    public void save(ResearchLab transientInstance) {
        log.debug("saving ResearchLab instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ResearchLab persistentInstance) {
        log.debug("deleting ResearchLab instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ResearchLab findById( java.lang.String id) {
        log.debug("getting ResearchLab instance with id: " + id);
        try {
            ResearchLab instance = (ResearchLab) getSession()
                    .get("com.nuaa.ec.model.ResearchLab", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ResearchLab instance) {
        log.debug("finding ResearchLab instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.ResearchLab")
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
      log.debug("finding ResearchLab instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ResearchLab as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByResearchLabName(Object researchLabName
	) {
		return findByProperty(RESEARCH_LAB_NAME, researchLabName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByResearchLabAdminId(Object researchLabAdminId
	) {
		return findByProperty(RESEARCH_LAB_ADMIN_ID, researchLabAdminId
		);
	}
	
	public List findByResearchLabAdmin(Object researchLabAdmin
	) {
		return findByProperty(RESEARCH_LAB_ADMIN, researchLabAdmin
		);
	}
	

	public List findAll() {
		log.debug("finding all ResearchLab instances");
		try {
			String queryString = "from ResearchLab where spareTire='1'";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ResearchLab merge(ResearchLab detachedInstance) {
        log.debug("merging ResearchLab instance");
        try {
            ResearchLab result = (ResearchLab) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ResearchLab instance) {
        log.debug("attaching dirty ResearchLab instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ResearchLab instance) {
        log.debug("attaching clean ResearchLab instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}