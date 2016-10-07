package com.nuaa.ec.dao;

import com.nuaa.ec.model.TffamousTeacherTeamPerformance;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TffamousTeacherTeamPerformance entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TffamousTeacherTeamPerformance
 * @author MyEclipse Persistence Tools
 */
public class TffamousTeacherTeamPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TffamousTeacherTeamPerformanceDAO.class);
	// property constants
	public static final String SINGEL_SCORE = "singelScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";

	public void save(TffamousTeacherTeamPerformance transientInstance) {
		log.debug("saving TffamousTeacherTeamPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TffamousTeacherTeamPerformance persistentInstance) {
		log.debug("deleting TffamousTeacherTeamPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TffamousTeacherTeamPerformance findById(java.lang.Integer id) {
		log.debug("getting TffamousTeacherTeamPerformance instance with id: "
				+ id);
		try {
			TffamousTeacherTeamPerformance instance = (TffamousTeacherTeamPerformance) getSession()
					.get("com.nuaa.ec.model.TffamousTeacherTeamPerformance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TffamousTeacherTeamPerformance instance) {
		log.debug("finding TffamousTeacherTeamPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TffamousTeacherTeamPerformance")
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
		log.debug("finding TffamousTeacherTeamPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TffamousTeacherTeamPerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySingelScore(Object singelScore) {
		return findByProperty(SINGEL_SCORE, singelScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TffamousTeacherTeamPerformance instances");
		try {
			String queryString = "from TffamousTeacherTeamPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TffamousTeacherTeamPerformance merge(
			TffamousTeacherTeamPerformance detachedInstance) {
		log.debug("merging TffamousTeacherTeamPerformance instance");
		try {
			TffamousTeacherTeamPerformance result = (TffamousTeacherTeamPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TffamousTeacherTeamPerformance instance) {
		log.debug("attaching dirty TffamousTeacherTeamPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TffamousTeacherTeamPerformance instance) {
		log.debug("attaching clean TffamousTeacherTeamPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}