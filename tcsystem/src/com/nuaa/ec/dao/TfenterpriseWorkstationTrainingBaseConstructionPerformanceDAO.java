package com.nuaa.ec.dao;

import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfenterpriseWorkstationTrainingBaseConstructionPerformance entities.
 * Transaction control of the save(), update() and delete() operations can
 * directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance
 * @author MyEclipse Persistence Tools
 */
public class TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO
		extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO.class);
	// property constants
	public static final String SINGLE_SCORE = "singleScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";

	public void save(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance transientInstance) {
		log.debug("saving TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance persistentInstance) {
		log.debug("deleting TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfenterpriseWorkstationTrainingBaseConstructionPerformance findById(
			java.lang.Integer id) {
		log.debug("getting TfenterpriseWorkstationTrainingBaseConstructionPerformance instance with id: "
				+ id);
		try {
			TfenterpriseWorkstationTrainingBaseConstructionPerformance instance = (TfenterpriseWorkstationTrainingBaseConstructionPerformance) getSession()
					.get("com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance instance) {
		log.debug("finding TfenterpriseWorkstationTrainingBaseConstructionPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance")
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
		log.debug("finding TfenterpriseWorkstationTrainingBaseConstructionPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionPerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySingleScore(Object singleScore) {
		return findByProperty(SINGLE_SCORE, singleScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TfenterpriseWorkstationTrainingBaseConstructionPerformance instances");
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfenterpriseWorkstationTrainingBaseConstructionPerformance merge(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance detachedInstance) {
		log.debug("merging TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			TfenterpriseWorkstationTrainingBaseConstructionPerformance result = (TfenterpriseWorkstationTrainingBaseConstructionPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance instance) {
		log.debug("attaching dirty TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance instance) {
		log.debug("attaching clean TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}