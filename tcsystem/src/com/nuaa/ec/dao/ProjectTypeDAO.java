package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.ProjectType;

/**
 	* A data access object (DAO) providing persistence and search support for ProjectType entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.ProjectType
  * @author MyEclipse Persistence Tools 
 */
public class ProjectTypeDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ProjectTypeDAO.class);
		//property constants
	public static final String PROJECT_TP_NAME = "projectTpName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(ProjectType transientInstance) {
        log.debug("saving ProjectType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ProjectType persistentInstance) {
        log.debug("deleting ProjectType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ProjectType findById( java.lang.String id) {
        log.debug("getting ProjectType instance with id: " + id);
        try {
            ProjectType instance = (ProjectType) getSession()
                    .get("com.nuaa.ec.model.ProjectType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ProjectType instance) {
        log.debug("finding ProjectType instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.ProjectType")
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
      log.debug("finding ProjectType instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ProjectType as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByProjectTpName(Object projectTpName
	) {
		return findByProperty(PROJECT_TP_NAME, projectTpName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all ProjectType instances");
		try {
			String queryString = "from ProjectType where spareTire=1";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ProjectType merge(ProjectType detachedInstance) {
        log.debug("merging ProjectType instance");
        try {
            ProjectType result = (ProjectType) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ProjectType instance) {
        log.debug("attaching dirty ProjectType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ProjectType instance) {
        log.debug("attaching clean ProjectType instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}