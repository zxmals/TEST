package com.nuaa.ec.dao;

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

import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.ScientificResearchProject;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * ScientificResearchProject entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.ScientificResearchProject
 * @author MyEclipse Persistence Tools
 */
public class ScientificResearchProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ScientificResearchProjectDAO.class);
	// property constants
	public static final String SRPNAME = "srpname";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String PROJECT_NUMBER = "projectNumber";
	public static final String PROJECT_SOURCE = "projectSource";
	public static final String ADMITED_PROJECT_YEAR = "admitedProjectYear";
	public static final String SUM_FUNDS = "sumFunds";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHECKOUT = "checkout";

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	/**
	 * 所长审核功能
	 * 
	 * @param scientificResearchProjectList
	 * @return
	 */
	public boolean updateCheckoutStatus(
			List<ScientificResearchProject> scientificResearchProjectList) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < scientificResearchProjectList.size(); i++) {
				session.update(scientificResearchProjectList.get(i));
			}
			tx = session.beginTransaction();
			tx.commit();
			updateFlag = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return updateFlag;
	}

	/**
	 * 如果项目没有通过那么项目里的所有成员都将不通过。
	 * 
	 * @param scientificResearchProjectList
	 * @return
	 */
	public boolean cascadeUpdateCheckOutOfMembers(
			List<ScientificResearchProject> scientificResearchProjectList,String flag) {
		boolean operationFlag=false;
		Session session = this.getSession();
		Transaction tx=null;
		try{
			for (ScientificResearchProject srp : scientificResearchProjectList) {
				session.createQuery(
						"UPDATE TeacherAndscientificResearchProject TASRP SET TASRP.checkOut="+flag
						+ " WHERE TASRP.scientificResearchProject.srprojectId='"+ srp.getSrprojectId()+"'").executeUpdate();
			}
			tx=session.beginTransaction();
			tx.commit();
			operationFlag=true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
		}
		return operationFlag;
	}

	@SuppressWarnings("unchecked")
	public List<ScientificResearchProject> getAllRecordWithCondition_RT(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut, boolean isDivided) {
		List<ScientificResearchProject> scienResProList;
		// 获取当前登录的教师的研究所
		ResearchLab currentResearchLab = ((Teacher) session.get("teacher"))
				.getResearchLab();
		/*
		 * 利用审核状态是否是NULL来判断是否是第一次登陆 如果checkout是NULL，那么说明是第一次登陆
		 */
		StringBuffer hql = null;
		if (checkOut != null && checkOut.length() != 0) {// 第一次进入界面或者选择了查看全部的记录
			if (!checkOut.trim().equals("4")) {
				hql = new StringBuffer(
						"FROM ScientificResearchProject SRP WHERE SRP.checkout='"
								+ checkOut + "'");
				if (foredate != null && afterdate != null
						&& foredate.length() != 0 && afterdate.length() != 0) {
					hql.append(" AND SRP.admitedProjectYear BETWEEN '"
							+ foredate + "' AND '" + afterdate + "'");
				}
				hql.append(" AND SRP.researchLabId='"
						+ currentResearchLab.getResearchLabId() + "'");
			} else {
				hql = new StringBuffer("FROM ScientificResearchProject SRP ");
				if (foredate != null && afterdate != null
						&& foredate.length() != 0 && afterdate.length() != 0) {
					hql.append(" WHERE SRP.admitedProjectYear BETWEEN '"
							+ foredate + "' AND '" + afterdate + "'");
					hql.append(" AND SRP.researchLabId='"
							+ currentResearchLab.getResearchLabId() + "'");
				} else {
					hql.append(" WHERE SRP.researchLabId='"
							+ currentResearchLab.getResearchLabId() + "'");
				}

			}
		} else {
			hql = new StringBuffer("FROM ScientificResearchProject SRP ");
			if (foredate != null && afterdate != null && foredate.length() != 0
					&& afterdate.length() != 0) {
				hql.append(" WHERE SRP.admitedProjectYear BETWEEN '" + foredate
						+ "' AND '" + afterdate + "'");
				hql.append(" AND SRP.researchLabId='"
						+ currentResearchLab.getResearchLabId() + "'");
			} else {
				hql.append(" WHERE SRP.researchLabId='"
						+ currentResearchLab.getResearchLabId() + "'");
			}
		}
		if (!isDivided) {
			scienResProList = this.getSession().createQuery(hql.toString())
					.list();
			int size = scienResProList.size();
			session.put("pageCount_GTSRP",
					size % pageSize == 0 ? (size / pageSize)
							: (size / pageSize + 1));
			session.put("recordNumber_GTSRP", size);
		}
		scienResProList = this.getSession().createQuery(hql.toString())
				.setMaxResults(pageSize)
				.setFirstResult((pageIndex - 1) * pageSize).list();
		return scienResProList;
	}

	public void save(ScientificResearchProject transientInstance) {
		log.debug("saving ScientificResearchProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ScientificResearchProject persistentInstance) {
		log.debug("deleting ScientificResearchProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ScientificResearchProject findById(java.lang.String id) {
		log.debug("getting ScientificResearchProject instance with id: " + id);
		try {
			ScientificResearchProject instance = (ScientificResearchProject) getSession()
					.get("com.nuaa.ec.model.ScientificResearchProject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ScientificResearchProject instance) {
		log.debug("finding ScientificResearchProject instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.ScientificResearchProject")
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
		log.debug("finding ScientificResearchProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ScientificResearchProject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySrpname(Object srpname) {
		return findByProperty(SRPNAME, srpname);
	}

	public List findByChargePerson(Object chargePerson) {
		return findByProperty(CHARGE_PERSON, chargePerson);
	}

	public List findByProjectNumber(Object projectNumber) {
		return findByProperty(PROJECT_NUMBER, projectNumber);
	}

	public List findByProjectSource(Object projectSource) {
		return findByProperty(PROJECT_SOURCE, projectSource);
	}

	public List findByAdmitedProjectYear(Object admitedProjectYear) {
		return findByProperty(ADMITED_PROJECT_YEAR, admitedProjectYear);
	}

	public List findBySumFunds(Object sumFunds) {
		return findByProperty(SUM_FUNDS, sumFunds);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByChargePersonId(Object chargePersonId) {
		return findByProperty(CHARGE_PERSON_ID, chargePersonId);
	}

	public List findByCheckout(Object checkout) {
		return findByProperty(CHECKOUT, checkout);
	}

	public List findAll() {
		log.debug("finding all ScientificResearchProject instances");
		try {
			String queryString = "from ScientificResearchProject  where spareTire=1";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteBylogic(String srpId) {
		try {
			String queryString = "update ScientificResearchProject set spareTire='0' "
					+ "where srprojectId=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, srpId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findMember(ScientificResearchProject srp) {
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(tsp.teacher.teacherId,tsp.teacher.teacherName,tsp.selfUndertakeTask.undertakeTaskName) "
					+ "from TeacherAndscientificResearchProject tsp "
					+ "where tsp.spareTire=1 "
					+ "and tsp.scientificResearchProject=? "
					+ "and tsp.scientificResearchProject.spareTire='1' "
					+ "and tsp.teacher.spareTire='1' "
					+ "and tsp.scientificResearchProjectScore.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, srp);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPageing(int currentRow, int limitRow, String condition) {
		try {
			String queryString = "from ScientificResearchProject  where spareTire=1 "
					+ "and projectType.spareTire='1' "
					+ condition
					+ " order by admitedProjectYear desc";
			Query queryObject = getSession().createQuery(queryString)
					.setFirstResult(currentRow).setMaxResults(limitRow);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public int getRows(String condition) {
		try {
			String queryString = "from ScientificResearchProject  where spareTire=1 "
					+ "and projectType.spareTire='1' " + condition;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ScientificResearchProject merge(
			ScientificResearchProject detachedInstance) {
		log.debug("merging ScientificResearchProject instance");
		try {
			ScientificResearchProject result = (ScientificResearchProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ScientificResearchProject instance) {
		log.debug("attaching dirty ScientificResearchProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ScientificResearchProject instance) {
		log.debug("attaching clean ScientificResearchProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}