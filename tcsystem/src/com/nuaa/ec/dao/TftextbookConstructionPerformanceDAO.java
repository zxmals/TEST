package com.nuaa.ec.dao;

import com.nuaa.ec.model.TftextbookConstructionPerformance;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TftextbookConstructionPerformance entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TftextbookConstructionPerformance
 * @author MyEclipse Persistence Tools
 */
public class TftextbookConstructionPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TftextbookConstructionPerformanceDAO.class);
	// property constants
	public static final String SINGELL_SCORE = "singellScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";

	public void save(TftextbookConstructionPerformance transientInstance) {
		log.debug("saving TftextbookConstructionPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TftextbookConstructionPerformance persistentInstance) {
		log.debug("deleting TftextbookConstructionPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TftextbookConstructionPerformance findById(java.lang.Integer id) {
		log.debug("getting TftextbookConstructionPerformance instance with id: "
				+ id);
		try {
			TftextbookConstructionPerformance instance = (TftextbookConstructionPerformance) getSession()
					.get("com.nuaa.ec.model.TftextbookConstructionPerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TftextbookConstructionPerformance instance) {
		log.debug("finding TftextbookConstructionPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TftextbookConstructionPerformance")
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
		log.debug("finding TftextbookConstructionPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TftextbookConstructionPerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySingellScore(Object singellScore) {
		return findByProperty(SINGELL_SCORE, singellScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TftextbookConstructionPerformance instances");
		try {
			String queryString = "from TftextbookConstructionPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TftextbookConstructionPerformance merge(
			TftextbookConstructionPerformance detachedInstance) {
		log.debug("merging TftextbookConstructionPerformance instance");
		try {
			TftextbookConstructionPerformance result = (TftextbookConstructionPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TftextbookConstructionPerformance instance) {
		log.debug("attaching dirty TftextbookConstructionPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TftextbookConstructionPerformance instance) {
		log.debug("attaching clean TftextbookConstructionPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}