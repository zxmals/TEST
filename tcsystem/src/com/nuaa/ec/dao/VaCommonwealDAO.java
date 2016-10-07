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
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VaCommonweal;

/**
 	* A data access object (DAO) providing persistence and search support for Department entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.Department
  * @author MyEclipse Persistence Tools 
 */
public class VaCommonwealDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(VaCommonwealDAO.class);
		//property constants
	public static final String SPARE_TIRE = "spareTire";
	public static final String VACOMMONWEAL_ADMIN_ID = "VaCommonwealAdminId";
	public static final String VACOMMONWEAL_ADMIN = "VaCommonwealAdmin";

    
    public void save(VaCommonweal transientInstance) {
        log.debug("saving VaCommonweal instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(VaCommonweal persistentInstance) {
        log.debug("deleting VaCommonweal instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VaCommonweal findById( java.lang.String id) {
        log.debug("getting VaCommonweal instance with id: " + id);
        try {
            VaCommonweal instance = (VaCommonweal) getSession()
                    .get("com.nuaa.ec.model.VaCommonweal", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(VaCommonweal instance) {
        log.debug("finding VaCommonweal instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.VaCommonweal")
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
      log.debug("finding VaCommonweal instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from VaCommonweal as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByDepartAdminId(Object departAdminId
	) {
		return findByProperty(VACOMMONWEAL_ADMIN_ID, departAdminId
		);
	}
	
	public List findByDepartAdmin(Object departAdmin
	) {
		return findByProperty(VACOMMONWEAL_ADMIN, departAdmin
		);
	}
	

	public void update(VaCommonweal va){
		Transaction tx = null;
		try {
			String hql = "update teacher set spareTire=:sparetire,departAdminId=:departadminId,departAdmin=:departadmin where departmentId = :departId";
			Query query  = getSession().createQuery(hql);
			query.setParameter("sparetire", va.getSpareTire());
			query.setParameter("departadminId", va.getVaAdminID());
			query.setParameter("departadmin", va.getVaAdmin());
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
	
    public VaCommonweal merge(VaCommonweal va) {
        log.debug("merging VaCommonweal instance");
        try {
            VaCommonweal result = (VaCommonweal) getSession()
                    .merge(va);
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