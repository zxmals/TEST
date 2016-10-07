package com.nuaa.ec.dao;

import com.nuaa.ec.model.TfprofessionalProjectDeclareProject;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfprofessionalProjectDeclareProject entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfprofessionalProjectDeclareProject
 * @author MyEclipse Persistence Tools
 */
public class TfprofessionalProjectDeclareProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfprofessionalProjectDeclareProjectDAO.class);
	// property constants
	public static final String PROJECT_NAME = "projectName";
	public static final String PROJECT_SUM_SCORE = "projectSumScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";

	public void save(TfprofessionalProjectDeclareProject transientInstance) {
		log.debug("saving TfprofessionalProjectDeclareProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfprofessionalProjectDeclareProject persistentInstance) {
		log.debug("deleting TfprofessionalProjectDeclareProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfprofessionalProjectDeclareProject findById(java.lang.String id) {
		log.debug("getting TfprofessionalProjectDeclareProject instance with id: "
				+ id);
		try {
			TfprofessionalProjectDeclareProject instance = (TfprofessionalProjectDeclareProject) getSession()
					.get("com.nuaa.ec.model.TfprofessionalProjectDeclareProject",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfprofessionalProjectDeclareProject instance) {
		log.debug("finding TfprofessionalProjectDeclareProject instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfprofessionalProjectDeclareProject")
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
		log.debug("finding TfprofessionalProjectDeclareProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfprofessionalProjectDeclareProject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProjectName(Object projectName) {
		return findByProperty(PROJECT_NAME, projectName);
	}

	public List findByProjectSumScore(Object projectSumScore) {
		return findByProperty(PROJECT_SUM_SCORE, projectSumScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TfprofessionalProjectDeclareProject instances");
		try {
			String queryString = "from TfprofessionalProjectDeclareProject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfprofessionalProjectDeclareProject merge(
			TfprofessionalProjectDeclareProject detachedInstance) {
		log.debug("merging TfprofessionalProjectDeclareProject instance");
		try {
			TfprofessionalProjectDeclareProject result = (TfprofessionalProjectDeclareProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfprofessionalProjectDeclareProject instance) {
		log.debug("attaching dirty TfprofessionalProjectDeclareProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfprofessionalProjectDeclareProject instance) {
		log.debug("attaching clean TfprofessionalProjectDeclareProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}