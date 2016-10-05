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
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.nuaa.ec.model.TeacherAndscientificResearchReward;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TeacherAndscientificResearchReward entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TeacherAndscientificResearchReward
 * @author MyEclipse Persistence Tools
 */
public class TeacherAndscientificResearchRewardDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TeacherAndscientificResearchRewardDAO.class);
	// property constants
	public static final String REWARD_DATE = "rewardDate";
	public static final String SELF_RANKING = "selfRanking";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	public boolean updateCheckoutStatus(List<TeacherAndscientificResearchReward> TARRewardList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TARRewardList.size();i++){
				session.update(TARRewardList.get(i));
			}
			tx=session.beginTransaction();
			tx.commit();
			updateFlag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return updateFlag;
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithCondition(int pageIndex, int pageSize,
			String foredate, String afterdate, ResearchLab researchLab,
			String checkOut) {
		log.debug("finding all TeacherAndscientificResearchReward instances");
		StringBuffer hql = null;
		List<TeacherAndscientificResearchReward> list = new ArrayList<TeacherAndscientificResearchReward>();
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据， 所以不显示数据
			 */
			session.put("recordNumber_TARR", 0);
			session.put("pageCount_TARR", 0);
			return list;
		} else {
			hql = new StringBuffer(
					"from TeacherAndscientificResearchReward TARR where spareTire=1 and TARR.checkOut='"
							+ checkOut
							+ "' and TARR.teacher.researchLab.researchLabId='"
							+ researchLab.getResearchLabId() + "'");
		}
		try {
			String append = " and TARR.scientificResearchReward.rewardDate between ? and ? ";
			/*
			 * 不一定有日期，所以要判断
			 */
			if (foredate != null && afterdate != null && foredate.length() != 0
					&& afterdate.length() != 0) {
				// 判断日期范围限制
				hql.append(append);
				list = this.getSession().createQuery(hql.toString())
						.setString(0, foredate).setString(1, afterdate).list();
			} else {
				list = this.getSession().createQuery(hql.toString()).list();
			}
			/*
			 * 总体查询完毕，把总记录数和总页数放入session用于前台展现。
			 */
			session.put("recordNumber_TARR", list.size());
			session.put("pageCount_TARR",
					list.size() % pageSize == 0 ? (list.size() / pageSize)
							: (list.size() / pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力， 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getTASRProjectListsAfterDivided(pageIndex, pageSize,
					foredate, afterdate, researchLab, checkOut);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TeacherAndscientificResearchReward> getTASRProjectListsAfterDivided(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut) {
		StringBuffer hql = null;
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			hql = new StringBuffer(
					"from TeacherAndscientificResearchReward TARR where spareTire='1' and checkOut='"
							+ checkOut + "'");
		} else {
			hql = new StringBuffer(
					"from TeacherAndscientificResearchReward TARR where spareTire='1' and checkOut='"
							+ checkOut
							+ "' and TARR.teacher.researchLab.researchLabId=\'"
							+ researchLab.getResearchLabId() + "\'");
		}
		List<TeacherAndscientificResearchReward> list = new ArrayList<TeacherAndscientificResearchReward>();
		String append = " and TARR.scientificResearchReward.rewardDate between ? and ? ";
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			// 判断日期范围限制
			hql.append(append);
			list = this.getSession().createQuery(hql.append(" order by TARR.scientificResearchReward.rewardDate desc").toString())
					.setString(0, foredate).setString(1, afterdate)
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		} else {
			list = this.getSession().createQuery(hql.append(" order by TARR.scientificResearchReward.rewardDate desc").toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		}
		return list;
	}

	public void save(TeacherAndscientificResearchReward transientInstance) {
		log.debug("saving TeacherAndscientificResearchReward instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TeacherAndscientificResearchReward persistentInstance) {
		log.debug("deleting TeacherAndscientificResearchReward instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TeacherAndscientificResearchReward findById(java.lang.Integer id) {
		log.debug("getting TeacherAndscientificResearchReward instance with id: "
				+ id);
		try {
			TeacherAndscientificResearchReward instance = (TeacherAndscientificResearchReward) getSession()
					.get("com.nuaa.ec.model.TeacherAndscientificResearchReward",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TeacherAndscientificResearchReward instance) {
		log.debug("finding TeacherAndscientificResearchReward instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TeacherAndscientificResearchReward")
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
		log.debug("finding TeacherAndscientificResearchReward instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TeacherAndscientificResearchReward as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByRewardDate(Object rewardDate) {
		return findByProperty(REWARD_DATE, rewardDate);
	}

	public List findBySelfRanking(Object selfRanking) {
		return findByProperty(SELF_RANKING, selfRanking);
	}

	public List findByFinalScore(Object finalScore) {
		return findByProperty(FINAL_SCORE, finalScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public TeacherAndscientificResearchReward merge(
			TeacherAndscientificResearchReward detachedInstance) {
		log.debug("merging TeacherAndscientificResearchReward instance");
		try {
			TeacherAndscientificResearchReward result = (TeacherAndscientificResearchReward) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TeacherAndscientificResearchReward instance) {
		log.debug("attaching dirty TeacherAndscientificResearchReward instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TeacherAndscientificResearchReward instance) {
		log.debug("attaching clean TeacherAndscientificResearchReward instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}