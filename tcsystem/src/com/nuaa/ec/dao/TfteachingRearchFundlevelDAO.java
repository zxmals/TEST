package com.nuaa.ec.dao;

import com.nuaa.ec.model.TfteachingRearchFundlevel;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfteachingRearchFundlevel entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfteachingRearchFundlevel
 * @author MyEclipse Persistence Tools
 */
public class TfteachingRearchFundlevelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfteachingRearchFundlevelDAO.class);
	// property constants
	public static final String FUND_LEVEL = "fundLevel";
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";

	public void save(TfteachingRearchFundlevel transientInstance) {
		log.debug("saving TfteachingRearchFundlevel instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfteachingRearchFundlevel persistentInstance) {
		log.debug("deleting TfteachingRearchFundlevel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfteachingRearchFundlevel findById(java.lang.String id) {
		log.debug("getting TfteachingRearchFundlevel instance with id: " + id);
		try {
			TfteachingRearchFundlevel instance = (TfteachingRearchFundlevel) getSession()
					.get("com.nuaa.ec.model.TfteachingRearchFundlevel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfteachingRearchFundlevel instance) {
		log.debug("finding TfteachingRearchFundlevel instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfteachingRearchFundlevel")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TfteachingRearchFundlevel instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfteachingRearchFundlevel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFundLevel(Object fundLevel) {
		return findByProperty(FUND_LEVEL, fundLevel);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TfteachingRearchFundlevel instances");
		try {
			String queryString = "from TfteachingRearchFundlevel";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfteachingRearchFundlevel merge(
			TfteachingRearchFundlevel detachedInstance) {
		log.debug("merging TfteachingRearchFundlevel instance");
		try {
			TfteachingRearchFundlevel result = (TfteachingRearchFundlevel) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfteachingRearchFundlevel instance) {
		log.debug("attaching dirty TfteachingRearchFundlevel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfteachingRearchFundlevel instance) {
		log.debug("attaching clean TfteachingRearchFundlevel instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}