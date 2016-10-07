package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfteachingCompetitionPerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachingCompetitionPerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachingCompetitionPerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfteachingCompetitionPerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachingCompetitionPerformanceDAO.class);
		//property constants
	public static final String COMPETITION_ID = "competitionId";
	public static final String COMPETITION_NAME = "competitionName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";



    
    public void save(TfteachingCompetitionPerformance transientInstance) {
        log.debug("saving TfteachingCompetitionPerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachingCompetitionPerformance persistentInstance) {
        log.debug("deleting TfteachingCompetitionPerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachingCompetitionPerformance findById( java.lang.Integer id) {
        log.debug("getting TfteachingCompetitionPerformance instance with id: " + id);
        try {
            TfteachingCompetitionPerformance instance = (TfteachingCompetitionPerformance) getSession()
                    .get("com.nuaa.ec.model.TfteachingCompetitionPerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachingCompetitionPerformance instance) {
        log.debug("finding TfteachingCompetitionPerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachingCompetitionPerformance")
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
      log.debug("finding TfteachingCompetitionPerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachingCompetitionPerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCompetitionId(Object competitionId
	) {
		return findByProperty(COMPETITION_ID, competitionId
		);
	}
	
	public List findByCompetitionName(Object competitionName
	) {
		return findByProperty(COMPETITION_NAME, competitionName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByFinalScore(Object finalScore
	) {
		return findByProperty(FINAL_SCORE, finalScore
		);
	}
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	

	public List findAll() {
		log.debug("finding all TfteachingCompetitionPerformance instances");
		try {
			String queryString = "from TfteachingCompetitionPerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachingCompetitionPerformance merge(TfteachingCompetitionPerformance detachedInstance) {
        log.debug("merging TfteachingCompetitionPerformance instance");
        try {
            TfteachingCompetitionPerformance result = (TfteachingCompetitionPerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachingCompetitionPerformance instance) {
        log.debug("attaching dirty TfteachingCompetitionPerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachingCompetitionPerformance instance) {
        log.debug("attaching clean TfteachingCompetitionPerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}