package com.nuaa.ec.dao;

import com.nuaa.ec.model.TftextbookConstructionProject;

import java.util.List;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TftextbookConstructionProject entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TftextbookConstructionProject
 * @author MyEclipse Persistence Tools
 */
public class TftextbookConstructionProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TftextbookConstructionProjectDAO.class);
	// property constants
	public static final String BOOK_NAME = "bookName";
	public static final String COOPERATOR = "cooperator";
	public static final String PROJECT_SUM_SCORE = "projectSumScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";

	public void save(TftextbookConstructionProject transientInstance) {
		log.debug("saving TftextbookConstructionProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TftextbookConstructionProject persistentInstance) {
		log.debug("deleting TftextbookConstructionProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TftextbookConstructionProject findById(java.lang.String id) {
		log.debug("getting TftextbookConstructionProject instance with id: "
				+ id);
		try {
			TftextbookConstructionProject instance = (TftextbookConstructionProject) getSession()
					.get("com.nuaa.ec.model.TftextbookConstructionProject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TftextbookConstructionProject instance) {
		log.debug("finding TftextbookConstructionProject instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TftextbookConstructionProject")
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
		log.debug("finding TftextbookConstructionProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TftextbookConstructionProject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBookName(Object bookName) {
		return findByProperty(BOOK_NAME, bookName);
	}

	public List findByCooperator(Object cooperator) {
		return findByProperty(COOPERATOR, cooperator);
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
		log.debug("finding all TftextbookConstructionProject instances");
		try {
			String queryString = "from TftextbookConstructionProject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteBylogic(String projectId){
		try {
			String queryString = "update TftextbookConstructionProject "
					+ "set spareTire='0' "
					+ "where bookId=? "
					+ "and spareTire='1'";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, projectId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPaging(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from TftextbookConstructionProject "
					+ "where spareTire='1' "
					+condition
					+ "and tftextbookConstructionTblevel.spareTire='1'"
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
			String queryString = "from TftextbookConstructionProject "
					+ "where spareTire='1' "
					+condition
					+ "and tftextbookConstructionTblevel.spareTire='1'"
					+ "and tfterm.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TftextbookConstructionProject merge(
			TftextbookConstructionProject detachedInstance) {
		log.debug("merging TftextbookConstructionProject instance");
		try {
			TftextbookConstructionProject result = (TftextbookConstructionProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TftextbookConstructionProject instance) {
		log.debug("attaching dirty TftextbookConstructionProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TftextbookConstructionProject instance) {
		log.debug("attaching clean TftextbookConstructionProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}