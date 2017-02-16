package com.nuaa.ec.dao;

import com.nuaa.ec.model.TeacherLoginInfo;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TeacherLoginInfo entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.nuaa.ec.model.TeacherLoginInfo
 * @author MyEclipse Persistence Tools
 */
public class TeacherLoginInfoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TeacherLoginInfoDAO.class);
	// property constants
	public static final String PASSWORD = "password";
	public static final String LEVEL = "level";
	public static final String SPARE_TIRE = "spareTire";
	public static final String LOGIN_TIME = "loginTime";
	
	public void update(TeacherLoginInfo teacherLoginInfo) throws Exception{
		Session session = getSession();
		session.update(teacherLoginInfo);
		session.beginTransaction().commit();
	}
	
	public TeacherLoginInfo getPasswordByTeacherId(String teacherId) throws Exception{
		Session session = getSession();
		String hql = "FROM TeacherLoginInfo WHERE teacherId=?";
		TeacherLoginInfo teacherLoginInfo = (TeacherLoginInfo) session.createQuery(hql).setParameter(0, teacherId).uniqueResult();
		return teacherLoginInfo;
	}
	
	public String findLevelByTeacherId(String teacherId) throws Exception{
		Session session = getSession();
		String hql = "SELECT level FROM TeacherLoginInfo WHERE teacherId=?";
		String level = (String) session.createQuery(hql).setParameter(0, teacherId).uniqueResult();
		return level;
	}
	
	public void save(TeacherLoginInfo transientInstance) {
		log.debug("saving TeacherLoginInfo instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TeacherLoginInfo persistentInstance) {
		log.debug("deleting TeacherLoginInfo instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TeacherLoginInfo findById(java.lang.String id) {
		log.debug("getting TeacherLoginInfo instance with id: " + id);
		try {
			TeacherLoginInfo instance = (TeacherLoginInfo) getSession().get(
					"com.nuaa.ec.model.TeacherLoginInfo", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TeacherLoginInfo instance) {
		log.debug("finding TeacherLoginInfo instance by example");
		try {
			List results = getSession()
					.createCriteria("com.nuaa.ec.model.TeacherLoginInfo")
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
		log.debug("finding TeacherLoginInfo instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TeacherLoginInfo as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByLevel(Object level) {
		return findByProperty(LEVEL, level);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByLoginTime(Object loginTime) {
		return findByProperty(LOGIN_TIME, loginTime);
	}

	public List findAll() {
		log.debug("finding all TeacherLoginInfo instances");
		try {
			String queryString = "from TeacherLoginInfo";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TeacherLoginInfo merge(TeacherLoginInfo detachedInstance) {
		log.debug("merging TeacherLoginInfo instance");
		try {
			TeacherLoginInfo result = (TeacherLoginInfo) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TeacherLoginInfo instance) {
		log.debug("attaching dirty TeacherLoginInfo instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TeacherLoginInfo instance) {
		log.debug("attaching clean TeacherLoginInfo instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}