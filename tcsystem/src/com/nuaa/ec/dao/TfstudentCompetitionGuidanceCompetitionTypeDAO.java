package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfstudentCompetitionGuidanceCompetitionType;

/**
 	* A data access object (DAO) providing persistence and search support for TfstudentCompetitionGuidanceCompetitionType entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfstudentCompetitionGuidanceCompetitionType
  * @author MyEclipse Persistence Tools 
 */
public class TfstudentCompetitionGuidanceCompetitionTypeDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfstudentCompetitionGuidanceCompetitionTypeDAO.class);
		//property constants
	public static final String COMPETITION_TYPE = "competitionType";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(TfstudentCompetitionGuidanceCompetitionType transientInstance) {
        log.debug("saving TfstudentCompetitionGuidanceCompetitionType instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfstudentCompetitionGuidanceCompetitionType persistentInstance) {
        log.debug("deleting TfstudentCompetitionGuidanceCompetitionType instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfstudentCompetitionGuidanceCompetitionType findById( java.lang.String id) {
        log.debug("getting TfstudentCompetitionGuidanceCompetitionType instance with id: " + id);
        try {
            TfstudentCompetitionGuidanceCompetitionType instance = (TfstudentCompetitionGuidanceCompetitionType) getSession()
                    .get("com.nuaa.ec.model.TfstudentCompetitionGuidanceCompetitionType", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfstudentCompetitionGuidanceCompetitionType instance) {
        log.debug("finding TfstudentCompetitionGuidanceCompetitionType instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfstudentCompetitionGuidanceCompetitionType")
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
      log.debug("finding TfstudentCompetitionGuidanceCompetitionType instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfstudentCompetitionGuidanceCompetitionType as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCompetitionType(Object competitionType
	) {
		return findByProperty(COMPETITION_TYPE, competitionType
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all TfstudentCompetitionGuidanceCompetitionType instances");
		try {
			String queryString = "from TfstudentCompetitionGuidanceCompetitionType";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfstudentCompetitionGuidanceCompetitionType merge(TfstudentCompetitionGuidanceCompetitionType detachedInstance) {
        log.debug("merging TfstudentCompetitionGuidanceCompetitionType instance");
        try {
            TfstudentCompetitionGuidanceCompetitionType result = (TfstudentCompetitionGuidanceCompetitionType) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfstudentCompetitionGuidanceCompetitionType instance) {
        log.debug("attaching dirty TfstudentCompetitionGuidanceCompetitionType instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfstudentCompetitionGuidanceCompetitionType instance) {
        log.debug("attaching clean TfstudentCompetitionGuidanceCompetitionType instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}