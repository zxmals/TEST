package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionProject;
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingbaseConstructionLevel;
import com.nuaa.ec.model.TfteachingRearchProject;
import com.nuaa.ec.model.Tfterm;
import com.opensymphony.xwork2.ActionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfenterpriseWorkstationTrainingbaseConstructionLevel entities. Transaction
 * control of the save(), update() and delete() operations can directly support
 * Spring container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfenterpriseWorkstationTrainingbaseConstructionLevel
 * @author MyEclipse Persistence Tools
 */
public class TfenterpriseWorkstationTrainingbaseConstructionLevelDAO extends
		BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfenterpriseWorkstationTrainingbaseConstructionLevelDAO.class);
	// property constants
	public static final String TRAINING_CONSTRU_LEVEL = "trainingConstruLevel";
	public static final String SCORE = "score";
	public static final String REFROM_RESEARCH_ID = "refromResearchId";
	public static final String SPARE_TIRE = "spareTire";
	private Map<String, Object> session = ActionContext.getContext().getSession();

	public void save(
			TfenterpriseWorkstationTrainingbaseConstructionLevel transientInstance) {
		log.debug("saving TfenterpriseWorkstationTrainingbaseConstructionLevel instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(
			TfenterpriseWorkstationTrainingbaseConstructionLevel persistentInstance) {
		log.debug("deleting TfenterpriseWorkstationTrainingbaseConstructionLevel instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfenterpriseWorkstationTrainingbaseConstructionLevel findById(
			java.lang.String id) {
		log.debug("getting TfenterpriseWorkstationTrainingbaseConstructionLevel instance with id: "
				+ id);
		try {
			TfenterpriseWorkstationTrainingbaseConstructionLevel instance = (TfenterpriseWorkstationTrainingbaseConstructionLevel) getSession()
					.get("com.nuaa.ec.model.TfenterpriseWorkstationTrainingbaseConstructionLevel",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(
			TfenterpriseWorkstationTrainingbaseConstructionLevel instance) {
		log.debug("finding TfenterpriseWorkstationTrainingbaseConstructionLevel instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfenterpriseWorkstationTrainingbaseConstructionLevel")
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
		log.debug("finding TfenterpriseWorkstationTrainingbaseConstructionLevel instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfenterpriseWorkstationTrainingbaseConstructionLevel as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTrainingConstruLevel(Object trainingConstruLevel) {
		return findByProperty(TRAINING_CONSTRU_LEVEL, trainingConstruLevel);
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findByRefromResearchId(Object refromResearchId) {
		return findByProperty(REFROM_RESEARCH_ID, refromResearchId);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TfenterpriseWorkstationTrainingbaseConstructionLevel instances");
		try {
			String queryString = "from TfenterpriseWorkstationTrainingbaseConstructionLevel "
					+ "where spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfenterpriseWorkstationTrainingbaseConstructionLevel merge(
			TfenterpriseWorkstationTrainingbaseConstructionLevel detachedInstance) {
		log.debug("merging TfenterpriseWorkstationTrainingbaseConstructionLevel instance");
		try {
			TfenterpriseWorkstationTrainingbaseConstructionLevel result = (TfenterpriseWorkstationTrainingbaseConstructionLevel) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(
			TfenterpriseWorkstationTrainingbaseConstructionLevel instance) {
		log.debug("attaching dirty TfenterpriseWorkstationTrainingbaseConstructionLevel instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(
			TfenterpriseWorkstationTrainingbaseConstructionLevel instance) {
		log.debug("attaching clean TfenterpriseWorkstationTrainingbaseConstructionLevel instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	

	
}