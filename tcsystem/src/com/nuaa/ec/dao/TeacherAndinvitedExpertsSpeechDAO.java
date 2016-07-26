package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndinvitedExpertsSpeech entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndinvitedExpertsSpeechDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndinvitedExpertsSpeechDAO.class);
		//property constants
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";



    
    public void save(TeacherAndinvitedExpertsSpeech transientInstance) {
        log.debug("saving TeacherAndinvitedExpertsSpeech instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TeacherAndinvitedExpertsSpeech persistentInstance) {
        log.debug("deleting TeacherAndinvitedExpertsSpeech instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TeacherAndinvitedExpertsSpeech findById( java.lang.Integer id) {
        log.debug("getting TeacherAndinvitedExpertsSpeech instance with id: " + id);
        try {
            TeacherAndinvitedExpertsSpeech instance = (TeacherAndinvitedExpertsSpeech) getSession()
                    .get("com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TeacherAndinvitedExpertsSpeech instance) {
        log.debug("finding TeacherAndinvitedExpertsSpeech instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech")
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
      log.debug("finding TeacherAndinvitedExpertsSpeech instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndinvitedExpertsSpeech as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
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
		log.debug("finding all TeacherAndinvitedExpertsSpeech instances");
		try {
			String queryString = "from TeacherAndinvitedExpertsSpeech";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TeacherAndinvitedExpertsSpeech merge(TeacherAndinvitedExpertsSpeech detachedInstance) {
        log.debug("merging TeacherAndinvitedExpertsSpeech instance");
        try {
            TeacherAndinvitedExpertsSpeech result = (TeacherAndinvitedExpertsSpeech) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndinvitedExpertsSpeech instance) {
        log.debug("attaching dirty TeacherAndinvitedExpertsSpeech instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndinvitedExpertsSpeech instance) {
        log.debug("attaching clean TeacherAndinvitedExpertsSpeech instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}