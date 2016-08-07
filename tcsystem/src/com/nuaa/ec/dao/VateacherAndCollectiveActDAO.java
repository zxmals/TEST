package com.nuaa.ec.dao;

import com.nuaa.ec.model.VateacherAndCollectiveAct;
import com.nuaa.ec.model.VateacherAndCollectiveActId;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * VateacherAndCollectiveAct entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.VateacherAndCollectiveAct
 * @author MyEclipse Persistence Tools
 */
public class VateacherAndCollectiveActDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VateacherAndCollectiveActDAO.class);
	// property constants
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";
	public static final String ASPARE_TIRE = "aspareTire";

	public void save(VateacherAndCollectiveAct transientInstance) {
		log.debug("saving VateacherAndCollectiveAct instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VateacherAndCollectiveAct persistentInstance) {
		log.debug("deleting VateacherAndCollectiveAct instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VateacherAndCollectiveAct findById(
			com.nuaa.ec.model.VateacherAndCollectiveActId id) {
		log.debug("getting VateacherAndCollectiveAct instance with id: " + id);
		try {
			VateacherAndCollectiveAct instance = (VateacherAndCollectiveAct) getSession()
					.get("com.nuaa.ec.model.VateacherAndCollectiveAct", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VateacherAndCollectiveAct instance) {
		log.debug("finding VateacherAndCollectiveAct instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.VateacherAndCollectiveAct")
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
		log.debug("finding VateacherAndCollectiveAct instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VateacherAndCollectiveAct as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByAspareTire(Object aspareTire) {
		return findByProperty(ASPARE_TIRE, aspareTire);
	}

	public List findAll() {
		log.debug("finding all VateacherAndCollectiveAct instances");
		try {
			String queryString = "from VateacherAndCollectiveAct";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VateacherAndCollectiveAct merge(
			VateacherAndCollectiveAct detachedInstance) {
		log.debug("merging VateacherAndCollectiveAct instance");
		try {
			VateacherAndCollectiveAct result = (VateacherAndCollectiveAct) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VateacherAndCollectiveAct instance) {
		log.debug("attaching dirty VateacherAndCollectiveAct instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VateacherAndCollectiveAct instance) {
		log.debug("attaching clean VateacherAndCollectiveAct instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}