package com.nuaa.ec.dao;

import com.nuaa.ec.model.TfteachingPaperRetrievalCondition;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfteachingPaperRetrievalCondition entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfteachingPaperRetrievalCondition
 * @author MyEclipse Persistence Tools
 */
public class TfteachingPaperRetrievalConditionDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfteachingPaperRetrievalConditionDAO.class);
	// property constants
	public static final String AUTHOR = "author";
	public static final String THESIS_RETRIEVAL = "thesisRetrieval";
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";

	public void save(TfteachingPaperRetrievalCondition transientInstance) {
		log.debug("saving TfteachingPaperRetrievalCondition instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfteachingPaperRetrievalCondition persistentInstance) {
		log.debug("deleting TfteachingPaperRetrievalCondition instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfteachingPaperRetrievalCondition findById(java.lang.String id) {
		log.debug("getting TfteachingPaperRetrievalCondition instance with id: "
				+ id);
		try {
			TfteachingPaperRetrievalCondition instance = (TfteachingPaperRetrievalCondition) getSession()
					.get("com.nuaa.ec.model.TfteachingPaperRetrievalCondition",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfteachingPaperRetrievalCondition instance) {
		log.debug("finding TfteachingPaperRetrievalCondition instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfteachingPaperRetrievalCondition")
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
		log.debug("finding TfteachingPaperRetrievalCondition instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfteachingPaperRetrievalCondition as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAuthor(Object author) {
		return findByProperty(AUTHOR, author);
	}

	public List findByThesisRetrieval(Object thesisRetrieval) {
		return findByProperty(THESIS_RETRIEVAL, thesisRetrieval);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TfteachingPaperRetrievalCondition instances");
		try {
			String queryString = "from TfteachingPaperRetrievalCondition";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfteachingPaperRetrievalCondition merge(
			TfteachingPaperRetrievalCondition detachedInstance) {
		log.debug("merging TfteachingPaperRetrievalCondition instance");
		try {
			TfteachingPaperRetrievalCondition result = (TfteachingPaperRetrievalCondition) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfteachingPaperRetrievalCondition instance) {
		log.debug("attaching dirty TfteachingPaperRetrievalCondition instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfteachingPaperRetrievalCondition instance) {
		log.debug("attaching clean TfteachingPaperRetrievalCondition instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}