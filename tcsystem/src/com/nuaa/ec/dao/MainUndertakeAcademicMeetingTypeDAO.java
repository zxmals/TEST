package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.MainUndertakeAcademicMeetingType;

/**
 	* A data access object (DAO) providing persistence and search support for MainUndertakeAcademicMeetingType entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.MainUndertakeAcademicMeetingType
  * @author MyEclipse Persistence Tools 
 */
public class MainUndertakeAcademicMeetingTypeDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MainUndertakeAcademicMeetingTypeDAO.class);
		//property constants
	public static final String ACA_MEET_TYPE = "acaMeetType";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(MainUndertakeAcademicMeetingType transientInstance) {
        log.debug("saving MainUndertakeAcademicMeetingType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MainUndertakeAcademicMeetingType persistentInstance) {
        log.debug("deleting MainUndertakeAcademicMeetingType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MainUndertakeAcademicMeetingType findById( java.lang.String id) {
        log.debug("getting MainUndertakeAcademicMeetingType instance with id: " + id);
        try {
            MainUndertakeAcademicMeetingType instance = (MainUndertakeAcademicMeetingType) getSession()
                    .get("com.nuaa.ec.model.MainUndertakeAcademicMeetingType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(MainUndertakeAcademicMeetingType instance) {
        log.debug("finding MainUndertakeAcademicMeetingType instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.MainUndertakeAcademicMeetingType")
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
      log.debug("finding MainUndertakeAcademicMeetingType instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MainUndertakeAcademicMeetingType as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByAcaMeetType(Object acaMeetType
	) {
		return findByProperty(ACA_MEET_TYPE, acaMeetType
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all MainUndertakeAcademicMeetingType instances");
		try {
			String queryString = "from MainUndertakeAcademicMeetingType where spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MainUndertakeAcademicMeetingType merge(MainUndertakeAcademicMeetingType detachedInstance) {
        log.debug("merging MainUndertakeAcademicMeetingType instance");
        try {
            MainUndertakeAcademicMeetingType result = (MainUndertakeAcademicMeetingType) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MainUndertakeAcademicMeetingType instance) {
        log.debug("attaching dirty MainUndertakeAcademicMeetingType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MainUndertakeAcademicMeetingType instance) {
        log.debug("attaching clean MainUndertakeAcademicMeetingType instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}