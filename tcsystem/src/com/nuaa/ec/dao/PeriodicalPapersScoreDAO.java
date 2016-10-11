package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.PeriodicalPapersScore;
import com.nuaa.ec.model.PeriodicalType;

/**
 	* A data access object (DAO) providing persistence and search support for PeriodicalPapersScore entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.PeriodicalPapersScore
  * @author MyEclipse Persistence Tools 
 */
public class PeriodicalPapersScoreDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PeriodicalPapersScoreDAO.class);
		//property constants
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(PeriodicalPapersScore transientInstance) {
        log.debug("saving PeriodicalPapersScore instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PeriodicalPapersScore persistentInstance) {
        log.debug("deleting PeriodicalPapersScore instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PeriodicalPapersScore findById( java.lang.String id) {
        log.debug("getting PeriodicalPapersScore instance with id: " + id);
        try {
            PeriodicalPapersScore instance = (PeriodicalPapersScore) getSession()
                    .get("com.nuaa.ec.model.PeriodicalPapersScore", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PeriodicalPapersScore instance) {
        log.debug("finding PeriodicalPapersScore instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.PeriodicalPapersScore")
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
      log.debug("finding PeriodicalPapersScore instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PeriodicalPapersScore as model where model." 
         						+ propertyName + "= ?  and  spareTire='1' ";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public PeriodicalPapersScore findByPeriodicalType(PeriodicalType value) {
        try {
           String queryString = "from PeriodicalPapersScore where periodicalType=? and spareTire='1' ";
           Query queryObject = getSession().createQuery(queryString);
  		 queryObject.setParameter(0, value);
  		 if(queryObject.list().size()>0){
  			return (PeriodicalPapersScore) queryObject.list().get(0);
  		 }else return null;
        } catch (RuntimeException re) {
           log.error("find by property name failed", re);
           throw re;
        }
  	}
    
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all PeriodicalPapersScore instances");
		try {
			String queryString = "from PeriodicalPapersScore where spareTire = '1'";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PeriodicalPapersScore merge(PeriodicalPapersScore detachedInstance) {
        log.debug("merging PeriodicalPapersScore instance");
        try {
            PeriodicalPapersScore result = (PeriodicalPapersScore) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PeriodicalPapersScore instance) {
        log.debug("attaching dirty PeriodicalPapersScore instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PeriodicalPapersScore instance) {
        log.debug("attaching clean PeriodicalPapersScore instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}