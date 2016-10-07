package com.nuaa.ec.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.Department;

/**
 	* A data access object (DAO) providing persistence and search support for Department entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.Department
  * @author MyEclipse Persistence Tools 
 */
public class DepartmentDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(DepartmentDAO.class);
		//property constants
	public static final String DEPARTMENT_NAME = "departmentName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String DEPART_ADMIN_ID = "departAdminId";
	public static final String DEPART_ADMIN = "departAdmin";

    
    public void save(Department transientInstance) {
        log.debug("saving Department instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Department persistentInstance) {
        log.debug("deleting Department instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Department findById( java.lang.String id) {
        log.debug("getting Department instance with id: " + id);
        try {
            Department instance = (Department) getSession()
                    .get("com.nuaa.ec.model.Department", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Department instance) {
        log.debug("finding Department instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.Department")
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
      log.debug("finding Department instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Department as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByDepartmentName(Object departmentName
	) {
		return findByProperty(DEPARTMENT_NAME, departmentName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByDepartAdminId(Object departAdminId
	) {
		return findByProperty(DEPART_ADMIN_ID, departAdminId
		);
	}
	
	public List findByDepartAdmin(Object departAdmin
	) {
		return findByProperty(DEPART_ADMIN, departAdmin
		);
	}
	

	public void update(Department depart){
		Transaction tx = null;
		try {
			String hql = "update Department set departmentName = :departname ,spareTire=:sparetire,departAdminId=:departadminId,departAdmin=:departadmin where departmentId = :departId";
			Query query  = getSession().createQuery(hql);
			query.setParameter("departname", depart.getDepartmentName());
			query.setParameter("sparetire", depart.getSpareTire());
			query.setParameter("departadminId", depart.getDepartAdminId());
			query.setParameter("departadmin", depart.getDepartAdmin());
			query.setParameter("departId", depart.getDepartmentId());
			query.executeUpdate();
			tx = getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			e.printStackTrace();
		}finally{
			closeSession();
		}
	}
	
	public List findAll() {
		log.debug("finding all Department instances");
		try {
			String queryString = "from Department where spareTire='1'";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Department merge(Department detachedInstance) {
        log.debug("merging Department instance");
        try {
            Department result = (Department) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Department instance) {
        log.debug("attaching dirty Department instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Department instance) {
        log.debug("attaching clean Department instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}