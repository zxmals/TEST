package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TeacherAndscientificResearchReward;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndscientificResearchReward entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndscientificResearchReward
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndscientificResearchRewardDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndscientificResearchRewardDAO.class);
		//property constants
	public static final String REWARD_DATE = "rewardDate";
	public static final String SELF_RANKING = "selfRanking";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";



    
    public void save(TeacherAndscientificResearchReward transientInstance) {
        log.debug("saving TeacherAndscientificResearchReward instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TeacherAndscientificResearchReward persistentInstance) {
        log.debug("deleting TeacherAndscientificResearchReward instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TeacherAndscientificResearchReward findById( java.lang.Integer id) {
        log.debug("getting TeacherAndscientificResearchReward instance with id: " + id);
        try {
            TeacherAndscientificResearchReward instance = (TeacherAndscientificResearchReward) getSession()
                    .get("com.nuaa.ec.model.TeacherAndscientificResearchReward", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TeacherAndscientificResearchReward instance) {
        log.debug("finding TeacherAndscientificResearchReward instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndscientificResearchReward")
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
      log.debug("finding TeacherAndscientificResearchReward instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndscientificResearchReward as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByRewardDate(Object rewardDate
	) {
		return findByProperty(REWARD_DATE, rewardDate
		);
	}
	
	public List findBySelfRanking(Object selfRanking
	) {
		return findByProperty(SELF_RANKING, selfRanking
		);
	}
	
	public List findByFinalScore(Object finalScore
	) {
		return findByProperty(FINAL_SCORE, finalScore
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
	

	public List findAll() {
		log.debug("finding all TeacherAndscientificResearchReward instances");
		try {
			String queryString = "from TeacherAndscientificResearchReward";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TeacherAndscientificResearchReward merge(TeacherAndscientificResearchReward detachedInstance) {
        log.debug("merging TeacherAndscientificResearchReward instance");
        try {
            TeacherAndscientificResearchReward result = (TeacherAndscientificResearchReward) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndscientificResearchReward instance) {
        log.debug("attaching dirty TeacherAndscientificResearchReward instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndscientificResearchReward instance) {
        log.debug("attaching clean TeacherAndscientificResearchReward instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}