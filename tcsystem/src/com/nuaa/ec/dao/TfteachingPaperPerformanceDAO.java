package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TfteachingPaperPerformance;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachingPaperPerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachingPaperPerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfteachingPaperPerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachingPaperPerformanceDAO.class);
		//property constants
	public static final String TEACH_PAPER_ID = "teachPaperId";
	public static final String TEACH_PAPER_NAME = "teachPaperName";
	public static final String OTHER_AUTHOR_JOIN = "otherAuthorJoin";
	public static final String SINGEL_SCORE = "singelScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	public static final String PROJECT_SUM_SCORE = "projectSumScore";



    
    public void save(TfteachingPaperPerformance transientInstance) {
        log.debug("saving TfteachingPaperPerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachingPaperPerformance persistentInstance) {
        log.debug("deleting TfteachingPaperPerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachingPaperPerformance findById( java.lang.Integer id) {
        log.debug("getting TfteachingPaperPerformance instance with id: " + id);
        try {
            TfteachingPaperPerformance instance = (TfteachingPaperPerformance) getSession()
                    .get("com.nuaa.ec.model.TfteachingPaperPerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachingPaperPerformance instance) {
        log.debug("finding TfteachingPaperPerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachingPaperPerformance")
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
      log.debug("finding TfteachingPaperPerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachingPaperPerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTeachPaperId(Object teachPaperId
	) {
		return findByProperty(TEACH_PAPER_ID, teachPaperId
		);
	}
	
	public List findByTeachPaperName(Object teachPaperName
	) {
		return findByProperty(TEACH_PAPER_NAME, teachPaperName
		);
	}
	
	public List findByOtherAuthorJoin(Object otherAuthorJoin
	) {
		return findByProperty(OTHER_AUTHOR_JOIN, otherAuthorJoin
		);
	}
	
	public List findBySingelScore(Object singelScore
	) {
		return findByProperty(SINGEL_SCORE, singelScore
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	
	public List findByProjectSumScore(Object projectSumScore
	) {
		return findByProperty(PROJECT_SUM_SCORE, projectSumScore
		);
	}
	

	public List findAll() {
		log.debug("finding all TfteachingPaperPerformance instances");
		try {
			String queryString = "from TfteachingPaperPerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachingPaperPerformance merge(TfteachingPaperPerformance detachedInstance) {
        log.debug("merging TfteachingPaperPerformance instance");
        try {
            TfteachingPaperPerformance result = (TfteachingPaperPerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachingPaperPerformance instance) {
        log.debug("attaching dirty TfteachingPaperPerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachingPaperPerformance instance) {
        log.debug("attaching clean TfteachingPaperPerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}