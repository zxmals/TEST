package com.nuaa.ec.dao;

import com.nuaa.ec.model.VaunJoinRecord;

import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * VaunJoinRecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.nuaa.ec.model.VaunJoinRecord
 * @author MyEclipse Persistence Tools
 */
public class VaunJoinRecordDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VaunJoinRecordDAO.class);
	// property constants
	public static final String ACT_ID = "actId";
	public static final String UNJOINREASON = "unjoinreason";
	public static final String LEAVEREQOBTAIN = "leavereqobtain";
	public static final String RESULTSCORE = "resultscore";
	public static final String SPARETIRE = "sparetire";

	public void save(VaunJoinRecord transientInstance) {
		log.debug("saving VaunJoinRecord instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VaunJoinRecord persistentInstance) {
		log.debug("deleting VaunJoinRecord instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VaunJoinRecord findById(java.lang.String id) {
		log.debug("getting VaunJoinRecord instance with id: " + id);
		try {
			VaunJoinRecord instance = (VaunJoinRecord) getSession().get(
					"com.nuaa.ec.model.VaunJoinRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VaunJoinRecord instance) {
		log.debug("finding VaunJoinRecord instance by example");
		try {
			List results = getSession()
					.createCriteria("com.nuaa.ec.model.VaunJoinRecord")
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
		log.debug("finding VaunJoinRecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VaunJoinRecord as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByActId(Object actId) {
		return findByProperty(ACT_ID, actId);
	}

	public List findByUnjoinreason(Object unjoinreason) {
		return findByProperty(UNJOINREASON, unjoinreason);
	}

	public List findByLeavereqobtain(Object leavereqobtain) {
		return findByProperty(LEAVEREQOBTAIN, leavereqobtain);
	}

	public List findByResultscore(Object resultscore) {
		return findByProperty(RESULTSCORE, resultscore);
	}

	public List findBySparetire(Object sparetire) {
		return findByProperty(SPARETIRE, sparetire);
	}

	public List findAll() {
		log.debug("finding all VaunJoinRecord instances");
		try {
			String queryString = "from VaunJoinRecord";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VaunJoinRecord merge(VaunJoinRecord detachedInstance) {
		log.debug("merging VaunJoinRecord instance");
		try {
			VaunJoinRecord result = (VaunJoinRecord) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VaunJoinRecord instance) {
		log.debug("attaching dirty VaunJoinRecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VaunJoinRecord instance) {
		log.debug("attaching clean VaunJoinRecord instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}