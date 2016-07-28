package com.nuaa.ec.dao;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.TeacherAndscientificResearchProject;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndscientificResearchProject entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndscientificResearchProject
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndscientificResearchProjectDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndscientificResearchProjectDAO.class);
		//property constants
	public static final String YEAR_FUNDS = "yearFunds";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";



    
    public void save(TeacherAndscientificResearchProject transientInstance) {
        log.debug("saving TeacherAndscientificResearchProject instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TeacherAndscientificResearchProject persistentInstance) {
        log.debug("deleting TeacherAndscientificResearchProject instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TeacherAndscientificResearchProject findById( java.lang.Integer id) {
        log.debug("getting TeacherAndscientificResearchProject instance with id: " + id);
        try {
            TeacherAndscientificResearchProject instance = (TeacherAndscientificResearchProject) getSession()
                    .get("com.nuaa.ec.model.TeacherAndscientificResearchProject", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TeacherAndscientificResearchProject instance) {
        log.debug("finding TeacherAndscientificResearchProject instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndscientificResearchProject")
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
      log.debug("finding TeacherAndscientificResearchProject instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndscientificResearchProject as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByYearFunds(Object yearFunds
	) {
		return findByProperty(YEAR_FUNDS, yearFunds
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
		log.debug("finding all TeacherAndscientificResearchProject instances");
		try {
			String queryString = "from TeacherAndscientificResearchProject";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TeacherAndscientificResearchProject merge(TeacherAndscientificResearchProject detachedInstance) {
        log.debug("merging TeacherAndscientificResearchProject instance");
        try {
            TeacherAndscientificResearchProject result = (TeacherAndscientificResearchProject) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndscientificResearchProject instance) {
        log.debug("attaching dirty TeacherAndscientificResearchProject instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndscientificResearchProject instance) {
        log.debug("attaching clean TeacherAndscientificResearchProject instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}