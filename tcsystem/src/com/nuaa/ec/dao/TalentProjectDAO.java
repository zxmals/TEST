package com.nuaa.ec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TalentProject;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TalentProject entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.nuaa.ec.model.TalentProject
 * @author MyEclipse Persistence Tools
 */
public class TalentProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TalentProjectDAO.class);
	// property constants
	public static final String TALENT_PROJECT_NAME = "talentProjectName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String CHECKOUT = "checkout";
	public static final String SELECTED_DATE = "selectedDate";
	private Map<String,Object> session=ActionContext.getContext().getSession();
	
	/**
	 * 所长审核功能
	 * 
	 * @param talentProjectList
	 * @return
	 */
	public boolean updateCheckoutStatus(
			List<TalentProject> talentProjectList) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < talentProjectList.size(); i++) {
				session.update(talentProjectList.get(i));
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
	 * @param talentProjectList
	 * @return
	 */
	public boolean cascadeUpdateCheckOutOfMembers(
			List<TalentProject> talentProjectList,String flag) {
		boolean operationFlag=false;
		Session session = this.getSession();
		Transaction tx=null;
		try{
			for (TalentProject tp : talentProjectList) {
				session.createQuery(
						"UPDATE TeacherAndselectedTalentProject TASTP SET TASTP.checkOut="+flag
						+ " WHERE TASTP.talentProject.talentProjectId='"+ tp.getTalentProjectId()+"'").executeUpdate();
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
	
	/**
	 * 获得所有的符合查询条件的入选人才工程项目记录
	 * 
	 * @param transientInstance
	 */
	@SuppressWarnings("unchecked")
	public List<TalentProject> getAllRecordsWithCondition(int pageIndex,
			int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut, boolean isDivided) {
		List<TalentProject> talentProList=new ArrayList<TalentProject>();
		StringBuffer hql=new StringBuffer("FROM TalentProject TP WHERE TP.spareTire='1'"
				+ "AND TP.researchLabId='"+researchLab.getResearchLabId()+"'");
		if (checkOut != null && checkOut.length() != 0
				&& !checkOut.trim().equals("4")) {
			hql.append(" AND TP.checkout='" + checkOut + "'");
		}
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			hql.append(" AND TP.selectedDate BETWEEN '"+foredate+"' AND '"+afterdate+"'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		if(!isDivided){
			talentProList=query.list();
			int size=talentProList.size();
			session.put("pageCount_GTTP", size%pageSize==0?(size/pageSize):(size/pageSize+1));
			session.put("recordNumber_GTTP", size);
		}
		talentProList=query.setMaxResults(pageSize).setFirstResult((pageIndex-1)*pageSize).list();
		return talentProList;
	}

	public void save(TalentProject transientInstance) {
		log.debug("saving TalentProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TalentProject persistentInstance) {
		log.debug("deleting TalentProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TalentProject findById(java.lang.String id) {
		log.debug("getting TalentProject instance with id: " + id);
		try {
			TalentProject instance = (TalentProject) getSession().get(
					"com.nuaa.ec.model.TalentProject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TalentProject instance) {
		log.debug("finding TalentProject instance by example");
		try {
			List results = getSession()
					.createCriteria("com.nuaa.ec.model.TalentProject")
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
		log.debug("finding TalentProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TalentProject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTalentProjectName(Object talentProjectName) {
		return findByProperty(TALENT_PROJECT_NAME, talentProjectName);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByChargePersonId(Object chargePersonId) {
		return findByProperty(CHARGE_PERSON_ID, chargePersonId);
	}

	public List findByChargePerson(Object chargePerson) {
		return findByProperty(CHARGE_PERSON, chargePerson);
	}

	public List findByCheckout(Object checkout) {
		return findByProperty(CHECKOUT, checkout);
	}

	public List findBySelectedDate(Object selectedDate) {
		return findByProperty(SELECTED_DATE, selectedDate);
	}

	public List findAll() {
		log.debug("finding all TalentProject instances");
		try {
			String queryString = "from TalentProject where spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPageing(int currentRow, int limitRow, String condition) {
		try {
			String queryString = "from TalentProject where spareTire='1' "
					+ condition + " order by selectedDate desc";
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
			String queryString = "from TalentProject where spareTire='1' "
					+ condition;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TalentProject merge(TalentProject detachedInstance) {
		log.debug("merging TalentProject instance");
		try {
			TalentProject result = (TalentProject) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TalentProject instance) {
		log.debug("attaching dirty TalentProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TalentProject instance) {
		log.debug("attaching clean TalentProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}