package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.VacollectiveAct;

/**
 	* A data access object (DAO) providing persistence and search support for VacollectiveAct entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.VacollectiveAct
  * @author MyEclipse Persistence Tools 
 */
public class VacollectiveActDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(VacollectiveActDAO.class);
		//property constants
	public static final String ACT_NAME = "actName";
	public static final String ATTENDEE = "attendee";
	public static final String SCORE = "score";
	public static final String ACT_TYPE = "actType";
	public static final String BASE_NUM = "baseNum";
	public static final String SPARE_TIRE = "spareTire";
	public static final String ASPARE_TIRE = "aspareTire";



    
    public void save(VacollectiveAct transientInstance) {
        log.debug("saving VacollectiveAct instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(VacollectiveAct persistentInstance) {
        log.debug("deleting VacollectiveAct instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VacollectiveAct findById( java.lang.String id) {
        log.debug("getting VacollectiveAct instance with id: " + id);
        try {
            VacollectiveAct instance = (VacollectiveAct) getSession()
                    .get("com.nuaa.ec.model.VacollectiveAct", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(VacollectiveAct instance) {
        log.debug("finding VacollectiveAct instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.VacollectiveAct")
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
      log.debug("finding VacollectiveAct instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from VacollectiveAct as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByActName(Object actName
	) {
		return findByProperty(ACT_NAME, actName
		);
	}
	
	public List findByAttendee(Object attendee
	) {
		return findByProperty(ATTENDEE, attendee
		);
	}
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
		);
	}
	
	public List findByActType(Object actType
	) {
		return findByProperty(ACT_TYPE, actType
		);
	}
	
	public List findByBaseNum(Object baseNum
	) {
		return findByProperty(BASE_NUM, baseNum
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByAspareTire(Object aspareTire
	) {
		return findByProperty(ASPARE_TIRE, aspareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all VacollectiveAct instances");
		try {
			String queryString = "from VacollectiveAct";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public VacollectiveAct merge(VacollectiveAct detachedInstance) {
        log.debug("merging VacollectiveAct instance");
        try {
            VacollectiveAct result = (VacollectiveAct) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(VacollectiveAct instance) {
        log.debug("attaching dirty VacollectiveAct instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VacollectiveAct instance) {
        log.debug("attaching clean VacollectiveAct instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}