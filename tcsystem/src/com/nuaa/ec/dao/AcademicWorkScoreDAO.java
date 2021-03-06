package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.AcademicWorkScore;
import com.nuaa.ec.model.WordsNumber;

/**
 	* A data access object (DAO) providing persistence and search support for AcademicWorkScore entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.AcademicWorkScore
  * @author MyEclipse Persistence Tools 
 */
public class AcademicWorkScoreDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(AcademicWorkScoreDAO.class);
		//property constants
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(AcademicWorkScore transientInstance) {
        log.debug("saving AcademicWorkScore instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(AcademicWorkScore persistentInstance) {
        log.debug("deleting AcademicWorkScore instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AcademicWorkScore findById( java.lang.String id) {
        log.debug("getting AcademicWorkScore instance with id: " + id);
        try {
            AcademicWorkScore instance = (AcademicWorkScore) getSession()
                    .get("com.nuaa.ec.model.AcademicWorkScore", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(AcademicWorkScore instance) {
        log.debug("finding AcademicWorkScore instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.AcademicWorkScore")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public AcademicWorkScore findByWordNum( WordsNumber wn) {
    	try {
            String queryString = "from AcademicWorkScore where  wordsNumber=? and spareTire='1' ";
            Query queryObject = getSession().createQuery(queryString);
   		 queryObject.setParameter(0, wn);
   		 if(queryObject.list().size()>0){
   			 return (AcademicWorkScore) queryObject.list().get(0);
   		 }else return null;
         } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
         }
    }
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding AcademicWorkScore instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from AcademicWorkScore as model where model." 
         						+ propertyName + "= ? and model.spareTire = '1' ";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
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
		log.debug("finding all AcademicWorkScore instances");
		try {
			String queryString = "from AcademicWorkScore where spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public AcademicWorkScore merge(AcademicWorkScore detachedInstance) {
        log.debug("merging AcademicWorkScore instance");
        try {
            AcademicWorkScore result = (AcademicWorkScore) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(AcademicWorkScore instance) {
        log.debug("attaching dirty AcademicWorkScore instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AcademicWorkScore instance) {
        log.debug("attaching clean AcademicWorkScore instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}