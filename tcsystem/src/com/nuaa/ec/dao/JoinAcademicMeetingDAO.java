package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.JoinAcademicMeeting;

/**
 	* A data access object (DAO) providing persistence and search support for JoinAcademicMeeting entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.JoinAcademicMeeting
  * @author MyEclipse Persistence Tools 
 */
public class JoinAcademicMeetingDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(JoinAcademicMeetingDAO.class);
		//property constants
	public static final String ACA_MEET_NAME = "acaMeetName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String CHECKOUT = "checkout";
	public static final String MEETINGDATE = "meetingdate";



    
    public void save(JoinAcademicMeeting transientInstance) {
        log.debug("saving JoinAcademicMeeting instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(JoinAcademicMeeting persistentInstance) {
        log.debug("deleting JoinAcademicMeeting instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public JoinAcademicMeeting findById( java.lang.String id) {
        log.debug("getting JoinAcademicMeeting instance with id: " + id);
        try {
            JoinAcademicMeeting instance = (JoinAcademicMeeting) getSession()
                    .get("com.nuaa.ec.model.JoinAcademicMeeting", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(JoinAcademicMeeting instance) {
        log.debug("finding JoinAcademicMeeting instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.JoinAcademicMeeting")
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
      log.debug("finding JoinAcademicMeeting instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from JoinAcademicMeeting as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByAcaMeetName(Object acaMeetName
	) {
		return findByProperty(ACA_MEET_NAME, acaMeetName
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
	
	public List findByChargePerson(Object chargePerson
	) {
		return findByProperty(CHARGE_PERSON, chargePerson
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
		log.debug("finding all JoinAcademicMeeting instances");
		try {
			String queryString = "from JoinAcademicMeeting";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPageing(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from JoinAcademicMeeting where spareTire='1' "
					+ "and meetingPlace.spareTire='1' "
					+ "and meetingType.spareTire='1' "
					+condition+" order by meetingdate desc";
	         Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow);
	         queryObject.setMaxResults(limitRow);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition){
		try {
			String queryString = "from JoinAcademicMeeting where spareTire='1' "
					+ "and meetingPlace.spareTire='1' "
					+ "and meetingType.spareTire='1' "
					+condition;
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public JoinAcademicMeeting merge(JoinAcademicMeeting detachedInstance) {
        log.debug("merging JoinAcademicMeeting instance");
        try {
            JoinAcademicMeeting result = (JoinAcademicMeeting) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void deleteJoinacameeting(String joinacamid){
    	try {
			String queryString = "update JoinAcademicMeeting set spareTire='0' "
					+ "where joinAcaMid=? "
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, joinacamid);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    
    public void attachDirty(JoinAcademicMeeting instance) {
        log.debug("attaching dirty JoinAcademicMeeting instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(JoinAcademicMeeting instance) {
        log.debug("attaching clean JoinAcademicMeeting instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}