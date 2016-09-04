package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.ScientificResearchRewardScore;

/**
 	* A data access object (DAO) providing persistence and search support for ScientificResearchRewardScore entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.ScientificResearchRewardScore
  * @author MyEclipse Persistence Tools 
 */
public class ScientificResearchRewardScoreDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ScientificResearchRewardScoreDAO.class);
		//property constants
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(ScientificResearchRewardScore transientInstance) {
        log.debug("saving ScientificResearchRewardScore instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ScientificResearchRewardScore persistentInstance) {
        log.debug("deleting ScientificResearchRewardScore instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ScientificResearchRewardScore findById( java.lang.String id) {
        log.debug("getting ScientificResearchRewardScore instance with id: " + id);
        try {
            ScientificResearchRewardScore instance = (ScientificResearchRewardScore) getSession()
                    .get("com.nuaa.ec.model.ScientificResearchRewardScore", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ScientificResearchRewardScore instance) {
        log.debug("finding ScientificResearchRewardScore instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.ScientificResearchRewardScore")
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
      log.debug("finding ScientificResearchRewardScore instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ScientificResearchRewardScore as model where model." 
         						+ propertyName + "= ?";
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
		log.debug("finding all ScientificResearchRewardScore instances");
		try {
			String queryString = "from ScientificResearchRewardScore where spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ScientificResearchRewardScore merge(ScientificResearchRewardScore detachedInstance) {
        log.debug("merging ScientificResearchRewardScore instance");
        try {
            ScientificResearchRewardScore result = (ScientificResearchRewardScore) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ScientificResearchRewardScore instance) {
        log.debug("attaching dirty ScientificResearchRewardScore instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ScientificResearchRewardScore instance) {
        log.debug("attaching clean ScientificResearchRewardScore instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}