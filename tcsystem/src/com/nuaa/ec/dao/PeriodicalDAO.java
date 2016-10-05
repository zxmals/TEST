package com.nuaa.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.Periodical;

/**
 	* A data access object (DAO) providing persistence and search support for Periodical entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.Periodical
  * @author MyEclipse Persistence Tools 
 */
public class PeriodicalDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PeriodicalDAO.class);
		//property constants
	public static final String PERIODICAL_NAME = "periodicalName";
	public static final String SPARE_TIRE = "spareTire";



    
    public void save(Periodical transientInstance) {
        log.debug("saving Periodical instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Periodical persistentInstance) {
        log.debug("deleting Periodical instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Periodical findById( java.lang.String id) {
        log.debug("getting Periodical instance with id: " + id);
        try {
            Periodical instance = (Periodical) getSession()
                    .get("com.nuaa.ec.model.Periodical", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Periodical instance) {
        log.debug("finding Periodical instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.Periodical")
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
      log.debug("finding Periodical instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Periodical as model where model." 
         						+ propertyName + "= ? and spareTire = '1'";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public Map<String, Object> findTranslate() throws Exception{
    	Map<String, Object>mp = new HashMap<String, Object>();
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	try {
			con = getConn();
			ps = con.prepareStatement("select PeriodicalID,PeriodicalName from Periodical where SpareTire='1' ");
			rs = ps.executeQuery();
			while(rs.next()){
				mp.put(rs.getString(1), rs.getString(2));
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			closeSqlAttr(ps, rs);
		}
    	return mp;
    }
    
	public List findByPeriodicalName(Object periodicalName
	) {
		return findByProperty(PERIODICAL_NAME, periodicalName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all Periodical instances");
		try {
			String queryString = "from Periodical where spareTire = '1'";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Periodical merge(Periodical detachedInstance) {
        log.debug("merging Periodical instance");
        try {
            Periodical result = (Periodical) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Periodical instance) {
        log.debug("attaching dirty Periodical instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Periodical instance) {
        log.debug("attaching clean Periodical instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}