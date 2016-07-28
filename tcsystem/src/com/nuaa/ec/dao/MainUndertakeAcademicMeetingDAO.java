package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.MainUndertakeAcademicMeeting;

/**
 	* A data access object (DAO) providing persistence and search support for MainUndertakeAcademicMeeting entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.MainUndertakeAcademicMeeting
  * @author MyEclipse Persistence Tools 
 */
public class MainUndertakeAcademicMeetingDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MainUndertakeAcademicMeetingDAO.class);
		//property constants
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String ACA_MEETING_NAME = "acaMeetingName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHECKOUT = "checkout";
	public static final String MEETINGDATE = "meetingdate";



    
    public void save(MainUndertakeAcademicMeeting transientInstance) {
        log.debug("saving MainUndertakeAcademicMeeting instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MainUndertakeAcademicMeeting persistentInstance) {
        log.debug("deleting MainUndertakeAcademicMeeting instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MainUndertakeAcademicMeeting findById( java.lang.String id) {
        log.debug("getting MainUndertakeAcademicMeeting instance with id: " + id);
        try {
            MainUndertakeAcademicMeeting instance = (MainUndertakeAcademicMeeting) getSession()
                    .get("com.nuaa.ec.model.MainUndertakeAcademicMeeting", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(MainUndertakeAcademicMeeting instance) {
        log.debug("finding MainUndertakeAcademicMeeting instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.MainUndertakeAcademicMeeting")
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
      log.debug("finding MainUndertakeAcademicMeeting instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MainUndertakeAcademicMeeting as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByChargePerson(Object chargePerson
	) {
		return findByProperty(CHARGE_PERSON, chargePerson
		);
	}
	
	public List findByAcaMeetingName(Object acaMeetingName
	) {
		return findByProperty(ACA_MEETING_NAME, acaMeetingName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByChargePersonId(Object chargePersonId
	) {
		return findByProperty(CHARGE_PERSON_ID, chargePersonId
		);
	}
	
	public List findByCheckout(Object checkout
	) {
		return findByProperty(CHECKOUT, checkout
		);
	}
	
	public List findByMeetingdate(Object meetingdate
	) {
		return findByProperty(MEETINGDATE, meetingdate
		);
	}
	

	public List findAll() {
		log.debug("finding all MainUndertakeAcademicMeeting instances");
		try {
			String queryString = "from MainUndertakeAcademicMeeting";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MainUndertakeAcademicMeeting merge(MainUndertakeAcademicMeeting detachedInstance) {
        log.debug("merging MainUndertakeAcademicMeeting instance");
        try {
            MainUndertakeAcademicMeeting result = (MainUndertakeAcademicMeeting) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MainUndertakeAcademicMeeting instance) {
        log.debug("attaching dirty MainUndertakeAcademicMeeting instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MainUndertakeAcademicMeeting instance) {
        log.debug("attaching clean MainUndertakeAcademicMeeting instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}