package com.nuaa.ec.dao;

import com.nuaa.ec.model.VacollectiveActivitiesPublish;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * VacollectiveActivitiesPublish entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.VacollectiveActivitiesPublish
 * @author MyEclipse Persistence Tools
 */
public class VacollectiveActivitiesPublishDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VacollectiveActivitiesPublishDAO.class);
	// property constants
	public static final String TEACHER_ID = "teacherId";
	public static final String ACT_DATE = "actDate";
	public static final String SPARE_TIRE = "spareTire";
	public static final String ASPARE_TIRE = "aspareTire";

	public void save(VacollectiveActivitiesPublish transientInstance) {
		log.debug("saving VacollectiveActivitiesPublish instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VacollectiveActivitiesPublish persistentInstance) {
		log.debug("deleting VacollectiveActivitiesPublish instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VacollectiveActivitiesPublish findById(java.lang.String id) {
		log.debug("getting VacollectiveActivitiesPublish instance with id: "
				+ id);
		try {
			VacollectiveActivitiesPublish instance = (VacollectiveActivitiesPublish) getSession()
					.get("com.nuaa.ec.model.VacollectiveActivitiesPublish", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VacollectiveActivitiesPublish instance) {
		log.debug("finding VacollectiveActivitiesPublish instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.VacollectiveActivitiesPublish")
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
		log.debug("finding VacollectiveActivitiesPublish instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VacollectiveActivitiesPublish as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTeacherId(Object teacherId) {
		return findByProperty(TEACHER_ID, teacherId);
	}

	public List findByActDate(Object actDate) {
		return findByProperty(ACT_DATE, actDate);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByAspareTire(Object aspareTire) {
		return findByProperty(ASPARE_TIRE, aspareTire);
	}

	public List findAll() {
		log.debug("finding all VacollectiveActivitiesPublish instances");
		try {
			String queryString = "from VacollectiveActivitiesPublish";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VacollectiveActivitiesPublish merge(
			VacollectiveActivitiesPublish detachedInstance) {
		log.debug("merging VacollectiveActivitiesPublish instance");
		try {
			VacollectiveActivitiesPublish result = (VacollectiveActivitiesPublish) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VacollectiveActivitiesPublish instance) {
		log.debug("attaching dirty VacollectiveActivitiesPublish instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VacollectiveActivitiesPublish instance) {
		log.debug("attaching clean VacollectiveActivitiesPublish instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}