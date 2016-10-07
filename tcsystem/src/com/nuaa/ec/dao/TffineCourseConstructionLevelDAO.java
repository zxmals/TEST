package com.nuaa.ec.dao;

import com.nuaa.ec.model.TffineCourseConstructionLevel;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TffineCourseConstructionLevel entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TffineCourseConstructionLevel
 * @author MyEclipse Persistence Tools
 */
public class TffineCourseConstructionLevelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TffineCourseConstructionLevelDAO.class);
	// property constants
	public static final String COURSE_LEVEL = "courseLevel";
	public static final String SCORE = "score";
	public static final String REFORM_RESEARCH_ID = "reformResearchId";
	public static final String SPARE_TIRE = "spareTire";

	public void save(TffineCourseConstructionLevel transientInstance) {
		log.debug("saving TffineCourseConstructionLevel instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TffineCourseConstructionLevel persistentInstance) {
		log.debug("deleting TffineCourseConstructionLevel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TffineCourseConstructionLevel findById(java.lang.String id) {
		log.debug("getting TffineCourseConstructionLevel instance with id: "
				+ id);
		try {
			TffineCourseConstructionLevel instance = (TffineCourseConstructionLevel) getSession()
					.get("com.nuaa.ec.model.TffineCourseConstructionLevel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TffineCourseConstructionLevel instance) {
		log.debug("finding TffineCourseConstructionLevel instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TffineCourseConstructionLevel")
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
		log.debug("finding TffineCourseConstructionLevel instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TffineCourseConstructionLevel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCourseLevel(Object courseLevel) {
		return findByProperty(COURSE_LEVEL, courseLevel);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findByReformResearchId(Object reformResearchId) {
		return findByProperty(REFORM_RESEARCH_ID, reformResearchId);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TffineCourseConstructionLevel instances");
		try {
			String queryString = "from TffineCourseConstructionLevel";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TffineCourseConstructionLevel merge(
			TffineCourseConstructionLevel detachedInstance) {
		log.debug("merging TffineCourseConstructionLevel instance");
		try {
			TffineCourseConstructionLevel result = (TffineCourseConstructionLevel) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TffineCourseConstructionLevel instance) {
		log.debug("attaching dirty TffineCourseConstructionLevel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TffineCourseConstructionLevel instance) {
		log.debug("attaching clean TffineCourseConstructionLevel instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}