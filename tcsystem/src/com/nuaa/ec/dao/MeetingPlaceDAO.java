package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.MeetingPlace;

/**
 	* A data access object (DAO) providing persistence and search support for MeetingPlace entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.MeetingPlace
  * @author MyEclipse Persistence Tools 
 */
public class MeetingPlaceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MeetingPlaceDAO.class);
		//property constants
	public static final String MEETING_PLACE = "meetingPlace";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(MeetingPlace transientInstance) {
        log.debug("saving MeetingPlace instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MeetingPlace persistentInstance) {
        log.debug("deleting MeetingPlace instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MeetingPlace findById( java.lang.String id) {
        log.debug("getting MeetingPlace instance with id: " + id);
        try {
            MeetingPlace instance = (MeetingPlace) getSession()
                    .get("com.nuaa.ec.model.MeetingPlace", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(MeetingPlace instance) {
        log.debug("finding MeetingPlace instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.MeetingPlace")
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
      log.debug("finding MeetingPlace instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MeetingPlace as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByMeetingPlace(Object meetingPlace
	) {
		return findByProperty(MEETING_PLACE, meetingPlace
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all MeetingPlace instances");
		try {
			String queryString = "from MeetingPlace";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MeetingPlace merge(MeetingPlace detachedInstance) {
        log.debug("merging MeetingPlace instance");
        try {
            MeetingPlace result = (MeetingPlace) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MeetingPlace instance) {
        log.debug("attaching dirty MeetingPlace instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MeetingPlace instance) {
        log.debug("attaching clean MeetingPlace instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}