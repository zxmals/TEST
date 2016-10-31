package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.ScientificResearchProject;

/**
 	* A data access object (DAO) providing persistence and search support for ScientificResearchProject entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.ScientificResearchProject
  * @author MyEclipse Persistence Tools 
 */
public class ScientificResearchProjectDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(ScientificResearchProjectDAO.class);
		//property constants
	public static final String SRPNAME = "srpname";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String PROJECT_NUMBER = "projectNumber";
	public static final String PROJECT_SOURCE = "projectSource";
	public static final String ADMITED_PROJECT_YEAR = "admitedProjectYear";
	public static final String SUM_FUNDS = "sumFunds";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHECKOUT = "checkout";



    
    public void save(ScientificResearchProject transientInstance) {
        log.debug("saving ScientificResearchProject instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(ScientificResearchProject persistentInstance) {
        log.debug("deleting ScientificResearchProject instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public ScientificResearchProject findById( java.lang.String id) {
        log.debug("getting ScientificResearchProject instance with id: " + id);
        try {
            ScientificResearchProject instance = (ScientificResearchProject) getSession()
                    .get("com.nuaa.ec.model.ScientificResearchProject", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(ScientificResearchProject instance) {
        log.debug("finding ScientificResearchProject instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.ScientificResearchProject")
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
      log.debug("finding ScientificResearchProject instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from ScientificResearchProject as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findBySrpname(Object srpname
	) {
		return findByProperty(SRPNAME, srpname
		);
	}
	
	public List findByChargePerson(Object chargePerson
	) {
		return findByProperty(CHARGE_PERSON, chargePerson
		);
	}
	
	public List findByProjectNumber(Object projectNumber
	) {
		return findByProperty(PROJECT_NUMBER, projectNumber
		);
	}
	
	public List findByProjectSource(Object projectSource
	) {
		return findByProperty(PROJECT_SOURCE, projectSource
		);
	}
	
	public List findByAdmitedProjectYear(Object admitedProjectYear
	) {
		return findByProperty(ADMITED_PROJECT_YEAR, admitedProjectYear
		);
	}
	
	public List findBySumFunds(Object sumFunds
	) {
		return findByProperty(SUM_FUNDS, sumFunds
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
	

	public List findAll() {
		log.debug("finding all ScientificResearchProject instances");
		try {
			String queryString = "from ScientificResearchProject  where spareTire=1";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteBylogic(String srpId){
		try {
			String queryString = "update ScientificResearchProject set spareTire='0' "
					+ "where srprojectId=? ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, srpId);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMember(ScientificResearchProject srp){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(tsp.teacher.teacherId,tsp.teacher.teacherName,tsp.selfUndertakeTask.undertakeTaskName) "
					+ "from TeacherAndscientificResearchProject tsp "
					+ "where tsp.spareTire=1 "
					+ "and tsp.scientificResearchProject=? "
					+ "and tsp.scientificResearchProject.spareTire='1' "
					+ "and tsp.teacher.spareTire='1' "
					+ "and tsp.scientificResearchProjectScore.spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, srp);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPageing(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from ScientificResearchProject  where spareTire=1 "
					+ "and projectType.spareTire='1' "
					+condition
					+ " order by admitedProjectYear desc";
	         Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow).setMaxResults(limitRow);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition){
		try {
			String queryString = "from ScientificResearchProject  where spareTire=1 "
					+ "and projectType.spareTire='1' "
					+condition;
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public ScientificResearchProject merge(ScientificResearchProject detachedInstance) {
        log.debug("merging ScientificResearchProject instance");
        try {
            ScientificResearchProject result = (ScientificResearchProject) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(ScientificResearchProject instance) {
        log.debug("attaching dirty ScientificResearchProject instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(ScientificResearchProject instance) {
        log.debug("attaching clean ScientificResearchProject instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}