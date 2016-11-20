package com.nuaa.ec.dao;

import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionProject;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfenterpriseWorkstationTrainingBaseConstructionProject entities. Transaction
 * control of the save(), update() and delete() operations can directly support
 * Spring container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionProject
 * @author MyEclipse Persistence Tools
 */
public class TfenterpriseWorkstationTrainingBaseConstructionProjectDAO extends
		BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfenterpriseWorkstationTrainingBaseConstructionProjectDAO.class);
	// property constants
	public static final String PROJECT_NAME = "projectName";
	public static final String PROJECT_SUM_SCORE = "projectSumScore";
	public static final String QUANTITY_UNIT = "quantityUnit";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";

	public void save(
			TfenterpriseWorkstationTrainingBaseConstructionProject transientInstance) {
		log.debug("saving TfenterpriseWorkstationTrainingBaseConstructionProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(
			TfenterpriseWorkstationTrainingBaseConstructionProject persistentInstance) {
		log.debug("deleting TfenterpriseWorkstationTrainingBaseConstructionProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfenterpriseWorkstationTrainingBaseConstructionProject findById(
			java.lang.String id) {
		log.debug("getting TfenterpriseWorkstationTrainingBaseConstructionProject instance with id: "
				+ id);
		try {
			TfenterpriseWorkstationTrainingBaseConstructionProject instance = (TfenterpriseWorkstationTrainingBaseConstructionProject) getSession()
					.get("com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionProject",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(
			TfenterpriseWorkstationTrainingBaseConstructionProject instance) {
		log.debug("finding TfenterpriseWorkstationTrainingBaseConstructionProject instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionProject")
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
		log.debug("finding TfenterpriseWorkstationTrainingBaseConstructionProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionProject as model where model."
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

	public List findByQuantityUnit(Object quantityUnit) {
		return findByProperty(QUANTITY_UNIT, quantityUnit);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TfenterpriseWorkstationTrainingBaseConstructionProject instances");
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionProject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteBylogic(String projectId){
		try {
			String queryString = "update TfenterpriseWorkstationTrainingBaseConstructionProject "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and projectId=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, projectId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPaging(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionProject "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingbaseConstructionLevel.spareTire='1' "
					+condition
					+ "and tfterm.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString)
					.setFirstResult(currentRow).setMaxResults(limitRow);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition){
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionProject "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingbaseConstructionLevel.spareTire='1' "
					+condition
					+ "and tfterm.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TfenterpriseWorkstationTrainingBaseConstructionProject merge(
			TfenterpriseWorkstationTrainingBaseConstructionProject detachedInstance) {
		log.debug("merging TfenterpriseWorkstationTrainingBaseConstructionProject instance");
		try {
			TfenterpriseWorkstationTrainingBaseConstructionProject result = (TfenterpriseWorkstationTrainingBaseConstructionProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(
			TfenterpriseWorkstationTrainingBaseConstructionProject instance) {
		log.debug("attaching dirty TfenterpriseWorkstationTrainingBaseConstructionProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(
			TfenterpriseWorkstationTrainingBaseConstructionProject instance) {
		log.debug("attaching clean TfenterpriseWorkstationTrainingBaseConstructionProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}