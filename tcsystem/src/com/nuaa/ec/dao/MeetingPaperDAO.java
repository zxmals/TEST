package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.MeetingPaper;

/**
 	* A data access object (DAO) providing persistence and search support for MeetingPaper entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.MeetingPaper
  * @author MyEclipse Persistence Tools 
 */
public class MeetingPaperDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MeetingPaperDAO.class);
		//property constants
	public static final String AUTHOR_NAME = "authorName";
	public static final String AUTHOR_IDENTITY = "authorIdentity";
	public static final String PAPER_TITLE = "paperTitle";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(MeetingPaper transientInstance) {
        log.debug("saving MeetingPaper instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MeetingPaper persistentInstance) {
        log.debug("deleting MeetingPaper instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MeetingPaper findById( java.lang.String id) {
        log.debug("getting MeetingPaper instance with id: " + id);
        try {
            MeetingPaper instance = (MeetingPaper) getSession()
                    .get("com.nuaa.ec.model.MeetingPaper", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(MeetingPaper instance) {
        log.debug("finding MeetingPaper instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.MeetingPaper")
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
      log.debug("finding MeetingPaper instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MeetingPaper as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByAuthorName(Object authorName
	) {
		return findByProperty(AUTHOR_NAME, authorName
		);
	}
	
	public List findByAuthorIdentity(Object authorIdentity
	) {
		return findByProperty(AUTHOR_IDENTITY, authorIdentity
		);
	}
	
	public List findByPaperTitle(Object paperTitle
	) {
		return findByProperty(PAPER_TITLE, paperTitle
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all MeetingPaper instances");
		try {
			String queryString = "from MeetingPaper";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MeetingPaper merge(MeetingPaper detachedInstance) {
        log.debug("merging MeetingPaper instance");
        try {
            MeetingPaper result = (MeetingPaper) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MeetingPaper instance) {
        log.debug("attaching dirty MeetingPaper instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MeetingPaper instance) {
        log.debug("attaching clean MeetingPaper instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}