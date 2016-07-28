package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TeacherAndselectedTalentProject;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndselectedTalentProject entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndselectedTalentProject
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndselectedTalentProjectDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndselectedTalentProjectDAO.class);
		//property constants
	public static final String TPSELECTED_YEAR = "tpselectedYear";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";



    
    public void save(TeacherAndselectedTalentProject transientInstance) {
        log.debug("saving TeacherAndselectedTalentProject instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TeacherAndselectedTalentProject persistentInstance) {
        log.debug("deleting TeacherAndselectedTalentProject instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TeacherAndselectedTalentProject findById( java.lang.Integer id) {
        log.debug("getting TeacherAndselectedTalentProject instance with id: " + id);
        try {
            TeacherAndselectedTalentProject instance = (TeacherAndselectedTalentProject) getSession()
                    .get("com.nuaa.ec.model.TeacherAndselectedTalentProject", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TeacherAndselectedTalentProject instance) {
        log.debug("finding TeacherAndselectedTalentProject instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndselectedTalentProject")
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
      log.debug("finding TeacherAndselectedTalentProject instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndselectedTalentProject as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTpselectedYear(Object tpselectedYear
	) {
		return findByProperty(TPSELECTED_YEAR, tpselectedYear
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
		log.debug("finding all TeacherAndselectedTalentProject instances");
		try {
			String queryString = "from TeacherAndselectedTalentProject";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TeacherAndselectedTalentProject merge(TeacherAndselectedTalentProject detachedInstance) {
        log.debug("merging TeacherAndselectedTalentProject instance");
        try {
            TeacherAndselectedTalentProject result = (TeacherAndselectedTalentProject) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndselectedTalentProject instance) {
        log.debug("attaching dirty TeacherAndselectedTalentProject instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndselectedTalentProject instance) {
        log.debug("attaching clean TeacherAndselectedTalentProject instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}