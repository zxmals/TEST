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
import com.nuaa.ec.model.ScientificResearchReward;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * ScientificResearchReward entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.ScientificResearchReward
 * @author MyEclipse Persistence Tools
 */
public class ScientificResearchRewardDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ScientificResearchRewardDAO.class);
	// property constants
	public static final String SRREWARD_NAME = "srrewardName";
	public static final String REWARD_DATE = "rewardDate";
	public static final String AWARD_DEPARTMENT = "awardDepartment";
	public static final String REWARD_TOTAL_PEOPLE = "rewardTotalPeople";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String CHECKOUT = "checkout";
	private Map<String,Object> session=ActionContext.getContext().getSession();
	
	/**
	 * 所长审核功能
	 * 
	 * @param scienReschRewardList
	 * @return
	 */
	public boolean updateCheckoutStatus(
			List<ScientificResearchReward> scienReschRewardList) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < scienReschRewardList.size(); i++) {
				session.update(scienReschRewardList.get(i));
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
	 * @param scienReschRewardList
	 * @return
	 */
	public boolean cascadeUpdateCheckOutOfMembers(
			List<ScientificResearchReward> scienReschRewardList,String flag) {
		boolean operationFlag=false;
		Session session = this.getSession();
		Transaction tx=null;
		try{
			for (ScientificResearchReward srr : scienReschRewardList) {
				session.createQuery(
						"UPDATE TeacherAndscientificResearchReward TASRR SET TASRR.checkOut="+flag
						+ " WHERE TASRR.scientificResearchReward.srrewardId='"+ srr.getSrrewardId()+"'").executeUpdate();
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
	 * 取出所有符合查询条件的科研项目奖励记录
	 * 
	 * @param transientInstance
	 */
	@SuppressWarnings("unchecked")
	public List<ScientificResearchReward> getAllRecordsWithCondition(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut, boolean isDivided) {
		List<ScientificResearchReward> scienReschRewdList=new ArrayList<ScientificResearchReward>();
		StringBuffer hql = new StringBuffer(
				"FROM ScientificResearchReward SRP WHERE SRP.researchLabId='"
						+ researchLab.getResearchLabId() + "'");
		/*
		 * 基于前端HTML的OPTION的特性， 如果checkOut为NULL，那么一定是第一次进入界面 因为前端表单没有提交到后台
		 * 否则不是第一次进入界面，或者网页被刷新；
		 */
		if (checkOut != null && checkOut.length() != 0
				&& !checkOut.trim().equals("4")) {
			hql.append(" AND SRP.checkout='" + checkOut + "'");
		}
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			hql.append(" AND SRP.rewardDate BETWEEN '"+foredate+"' AND '"+afterdate+"'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		if(!isDivided){
			scienReschRewdList=query.list();
			int size=scienReschRewdList.size();
			session.put("pageCount_GTSRR", size%pageSize==0?(size/pageSize):(size/pageSize+1));
			session.put("recordNumber_GTSRR", size);
		}
		scienReschRewdList=query.setMaxResults(pageSize).setFirstResult((pageIndex-1)*pageSize).list();
		return scienReschRewdList;
	}

	public void save(ScientificResearchReward transientInstance) {
		log.debug("saving ScientificResearchReward instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(ScientificResearchReward persistentInstance) {
		log.debug("deleting ScientificResearchReward instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public ScientificResearchReward findById(java.lang.String id) {
		log.debug("getting ScientificResearchReward instance with id: " + id);
		try {
			ScientificResearchReward instance = (ScientificResearchReward) getSession()
					.get("com.nuaa.ec.model.ScientificResearchReward", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(ScientificResearchReward instance) {
		log.debug("finding ScientificResearchReward instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.ScientificResearchReward")
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
		log.debug("finding ScientificResearchReward instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from ScientificResearchReward as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySrrewardName(Object srrewardName) {
		return findByProperty(SRREWARD_NAME, srrewardName);
	}

	public List findByRewardDate(Object rewardDate) {
		return findByProperty(REWARD_DATE, rewardDate);
	}

	public List findByAwardDepartment(Object awardDepartment) {
		return findByProperty(AWARD_DEPARTMENT, awardDepartment);
	}

	public List findByRewardTotalPeople(Object rewardTotalPeople) {
		return findByProperty(REWARD_TOTAL_PEOPLE, rewardTotalPeople);
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

	public List findAll() {
		log.debug("finding all ScientificResearchReward instances");
		try {
			String queryString = "from ScientificResearchReward where spareTire='1'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAllpaging(int currentrow, int limitrow, String condition) {
		try {
			String queryString = "from ScientificResearchReward where spareTire='1'"
					+ "and rewardLevel.spareTire='1' "
					+ "and rewardType.spareTire='1'  "
					+ condition
					+ "  order by rewardDate";
			Query queryObject = getSession().createQuery(queryString)
					.setFirstResult(currentrow);
			queryObject.setMaxResults(limitrow);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public int getRows(String condition) {
		try {
			String queryString = "from ScientificResearchReward where spareTire='1' "
					+ condition;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public ScientificResearchReward merge(
			ScientificResearchReward detachedInstance) {
		log.debug("merging ScientificResearchReward instance");
		try {
			ScientificResearchReward result = (ScientificResearchReward) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(ScientificResearchReward instance) {
		log.debug("attaching dirty ScientificResearchReward instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(ScientificResearchReward instance) {
		log.debug("attaching clean ScientificResearchReward instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}