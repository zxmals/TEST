package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.AcademicWork;

/**
 	* A data access object (DAO) providing persistence and search support for AcademicWork entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.AcademicWork
  * @author MyEclipse Persistence Tools 
 */
public class AcademicWorkDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(AcademicWorkDAO.class);
		//property constants
	public static final String FIRST_AUTHOR = "firstAuthor";
	public static final String WORK_NAME = "workName";
	public static final String PUBLISH_DATE = "publishDate";
	public static final String ISBN = "isbn";
	public static final String WORD_NUMBER = "wordNumber";
	public static final String OTHER_AUTHOR_JOIN = "otherAuthorJoin";
	public static final String SPARE_TIRE = "spareTire";
	public static final String WORD_ID = "wordId";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String CHECKOUT = "checkout";



    
    public void save(AcademicWork transientInstance) {
        log.debug("saving AcademicWork instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(AcademicWork persistentInstance) {
        log.debug("deleting AcademicWork instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public AcademicWork findById( java.lang.String id) {
        log.debug("getting AcademicWork instance with id: " + id);
        try {
            AcademicWork instance = (AcademicWork) getSession()
                    .get("com.nuaa.ec.model.AcademicWork", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(AcademicWork instance) {
        log.debug("finding AcademicWork instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.AcademicWork")
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
      log.debug("finding AcademicWork instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from AcademicWork as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByFirstAuthor(Object firstAuthor
	) {
		return findByProperty(FIRST_AUTHOR, firstAuthor
		);
	}
	
	public List findByWorkName(Object workName
	) {
		return findByProperty(WORK_NAME, workName
		);
	}
	
	public List findByPublishDate(Object publishDate
	) {
		return findByProperty(PUBLISH_DATE, publishDate
		);
	}
	
	public List findByIsbn(Object isbn
	) {
		return findByProperty(ISBN, isbn
		);
	}
	
	public List findByWordNumber(Object wordNumber
	) {
		return findByProperty(WORD_NUMBER, wordNumber
		);
	}
	
	public List findByOtherAuthorJoin(Object otherAuthorJoin
	) {
		return findByProperty(OTHER_AUTHOR_JOIN, otherAuthorJoin
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByWordId(Object wordId
	) {
		return findByProperty(WORD_ID, wordId
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
	

	public List findAll() {
		log.debug("finding all AcademicWork instances");
		try {
			String queryString = "from AcademicWork";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public AcademicWork merge(AcademicWork detachedInstance) {
        log.debug("merging AcademicWork instance");
        try {
            AcademicWork result = (AcademicWork) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(AcademicWork instance) {
        log.debug("attaching dirty AcademicWork instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(AcademicWork instance) {
        log.debug("attaching clean AcademicWork instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}