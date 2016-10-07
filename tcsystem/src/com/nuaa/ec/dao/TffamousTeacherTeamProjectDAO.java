package com.nuaa.ec.dao;

import com.nuaa.ec.model.TffamousTeacherTeamProject;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TffamousTeacherTeamProject entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TffamousTeacherTeamProject
 * @author MyEclipse Persistence Tools
 */
public class TffamousTeacherTeamProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TffamousTeacherTeamProjectDAO.class);
	// property constants
	public static final String PROJECT_SUM_SCORE = "projectSumScore";
	public static final String TERM_ID = "termId";
	public static final String SPARE_TIRE = "spareTire";

	public void save(TffamousTeacherTeamProject transientInstance) {
		log.debug("saving TffamousTeacherTeamProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TffamousTeacherTeamProject persistentInstance) {
		log.debug("deleting TffamousTeacherTeamProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TffamousTeacherTeamProject findById(java.lang.String id) {
		log.debug("getting TffamousTeacherTeamProject instance with id: " + id);
		try {
			TffamousTeacherTeamProject instance = (TffamousTeacherTeamProject) getSession()
					.get("com.nuaa.ec.model.TffamousTeacherTeamProject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TffamousTeacherTeamProject instance) {
		log.debug("finding TffamousTeacherTeamProject instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TffamousTeacherTeamProject")
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
		log.debug("finding TffamousTeacherTeamProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TffamousTeacherTeamProject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProjectSumScore(Object projectSumScore) {
		return findByProperty(PROJECT_SUM_SCORE, projectSumScore);
	}

	public List findByTermId(Object termId) {
		return findByProperty(TERM_ID, termId);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TffamousTeacherTeamProject instances");
		try {
			String queryString = "from TffamousTeacherTeamProject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TffamousTeacherTeamProject merge(
			TffamousTeacherTeamProject detachedInstance) {
		log.debug("merging TffamousTeacherTeamProject instance");
		try {
			TffamousTeacherTeamProject result = (TffamousTeacherTeamProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TffamousTeacherTeamProject instance) {
		log.debug("attaching dirty TffamousTeacherTeamProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TffamousTeacherTeamProject instance) {
		log.debug("attaching clean TffamousTeacherTeamProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}