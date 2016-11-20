package com.nuaa.ec.dao;

import com.nuaa.ec.model.TffamousTeacherTeamRewadLevel;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TffamousTeacherTeamRewadLevel entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TffamousTeacherTeamRewadLevel
 * @author MyEclipse Persistence Tools
 */
public class TffamousTeacherTeamRewadLevelDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TffamousTeacherTeamRewadLevelDAO.class);
	// property constants
	public static final String TEACH_REWARD_LEVEL_NAME = "teachRewardLevelName";
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";

	public void save(TffamousTeacherTeamRewadLevel transientInstance) {
		log.debug("saving TffamousTeacherTeamRewadLevel instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TffamousTeacherTeamRewadLevel persistentInstance) {
		log.debug("deleting TffamousTeacherTeamRewadLevel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TffamousTeacherTeamRewadLevel findById(java.lang.String id) {
		log.debug("getting TffamousTeacherTeamRewadLevel instance with id: "
				+ id);
		try {
			TffamousTeacherTeamRewadLevel instance = (TffamousTeacherTeamRewadLevel) getSession()
					.get("com.nuaa.ec.model.TffamousTeacherTeamRewadLevel", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TffamousTeacherTeamRewadLevel instance) {
		log.debug("finding TffamousTeacherTeamRewadLevel instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TffamousTeacherTeamRewadLevel")
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
		log.debug("finding TffamousTeacherTeamRewadLevel instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TffamousTeacherTeamRewadLevel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTeachRewardLevelName(Object teachRewardLevelName) {
		return findByProperty(TEACH_REWARD_LEVEL_NAME, teachRewardLevelName);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TffamousTeacherTeamRewadLevel instances");
		try {
			String queryString = "from TffamousTeacherTeamRewadLevel where spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TffamousTeacherTeamRewadLevel merge(
			TffamousTeacherTeamRewadLevel detachedInstance) {
		log.debug("merging TffamousTeacherTeamRewadLevel instance");
		try {
			TffamousTeacherTeamRewadLevel result = (TffamousTeacherTeamRewadLevel) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TffamousTeacherTeamRewadLevel instance) {
		log.debug("attaching dirty TffamousTeacherTeamRewadLevel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TffamousTeacherTeamRewadLevel instance) {
		log.debug("attaching clean TffamousTeacherTeamRewadLevel instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}