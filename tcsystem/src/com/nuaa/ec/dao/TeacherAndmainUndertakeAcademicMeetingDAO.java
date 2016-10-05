package com.nuaa.ec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.nuaa.ec.model.TeacherAndperiodical;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TeacherAndmainUndertakeAcademicMeeting entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting
 * @author MyEclipse Persistence Tools
 */
public class TeacherAndmainUndertakeAcademicMeetingDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TeacherAndmainUndertakeAcademicMeetingDAO.class);
	// property constants
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findAllWithCondition(int pageIndex, int pageSize,
			String foredate, String afterdate, ResearchLab researchLab,
			String checkOut) {
		log.debug("finding all TeacherAndmainUndertakeAcademicMeeting instances");
		StringBuffer hql = null;
		List<TeacherAndmainUndertakeAcademicMeeting> list = new ArrayList<TeacherAndmainUndertakeAcademicMeeting>();
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据， 所以不显示数据
			 */
			session.put("recordNumber_TAUA", 0);
			session.put("pageCount_TAUA", 0);
			return list;
		} else {
			hql = new StringBuffer(
					"from TeacherAndmainUndertakeAcademicMeeting TAUA where "
							+ " TAUA.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.mainUndertakeAcademicMeetingType.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.mainUndertakeAcademicMeetingPlace.spareTire=1"
							+ " and TAUA.teacher.spareTire=1 "
							+ " and TAUA.selfUndertakeTask.spareTire=1 "
							+ " and TAUA.checkOut='"
							+ checkOut
							+ "' and TAUA.teacher.researchLab.researchLabId='"
							+ researchLab.getResearchLabId() + "'");
		}
		try {
			String append = " and TAUA.mainUndertakeAcademicMeeting.meetingdate between ? and ? ";
			// String rank = "  order by PP.periodicalPid asc";
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
			session.put("recordNumber_TAUA", list.size());
			session.put("pageCount_TAUA",
					list.size() % pageSize == 0 ? (list.size() / pageSize)
							: (list.size() / pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力， 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getTAUAcademicAfterDivided(pageIndex, pageSize,
					foredate, afterdate, researchLab, checkOut);
		} catch (Exception ex) {
			log.error("find all failed", ex);
			System.out.println(ex.getMessage());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TeacherAndmainUndertakeAcademicMeeting> getTAUAcademicAfterDivided(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut) {
		System.out.println("pageIndex:" + pageIndex);
		StringBuffer hql = null;
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			hql = new StringBuffer(
					"from TeacherAndmainUndertakeAcademicMeeting TAUA where "
							+ " TAUA.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.mainUndertakeAcademicMeetingType.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.mainUndertakeAcademicMeetingPlace.spareTire=1"
							+ " and TAUA.teacher.spareTire=1 "
							+ " and TAUA.selfUndertakeTask.spareTire=1 "
							+ " and TAUA.checkOut='"
							+ checkOut+"'");
		} else {
			hql = new StringBuffer(
					"from TeacherAndmainUndertakeAcademicMeeting TAUA  where"
							+ " TAUA.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.mainUndertakeAcademicMeetingType.spareTire=1"
							+ " and TAUA.mainUndertakeAcademicMeeting.mainUndertakeAcademicMeetingPlace.spareTire=1"
							+ " and TAUA.teacher.spareTire=1 "
							+ " and TAUA.selfUndertakeTask.spareTire=1 "
							+ " and TAUA.checkOut='"
							+ checkOut
							+ "' and TAUA.teacher.researchLab.researchLabId=\'"
							+ researchLab.getResearchLabId() + "\'");
		}
		List<TeacherAndmainUndertakeAcademicMeeting> list = new ArrayList<TeacherAndmainUndertakeAcademicMeeting>();
		String append = " and TAUA.mainUndertakeAcademicMeeting.meetingdate between ? and ? ";
		// String rank = "  order by PP.periodicalPid asc";
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			// 判断日期范围限制
			hql.append(append);
			list = this.getSession().createQuery(hql.toString())
					.setString(0, foredate).setString(1, afterdate)
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		} else {
			list = this.getSession().createQuery(hql.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();

		}
		return list;
	}

	public void save(TeacherAndmainUndertakeAcademicMeeting transientInstance) {
		log.debug("saving TeacherAndmainUndertakeAcademicMeeting instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TeacherAndmainUndertakeAcademicMeeting persistentInstance) {
		log.debug("deleting TeacherAndmainUndertakeAcademicMeeting instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TeacherAndmainUndertakeAcademicMeeting findById(java.lang.Integer id) {
		log.debug("getting TeacherAndmainUndertakeAcademicMeeting instance with id: "
				+ id);
		try {
			TeacherAndmainUndertakeAcademicMeeting instance = (TeacherAndmainUndertakeAcademicMeeting) getSession()
					.get("com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TeacherAndmainUndertakeAcademicMeeting instance) {
		log.debug("finding TeacherAndmainUndertakeAcademicMeeting instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting")
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
		log.debug("finding TeacherAndmainUndertakeAcademicMeeting instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TeacherAndmainUndertakeAcademicMeeting as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
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

	public TeacherAndmainUndertakeAcademicMeeting merge(
			TeacherAndmainUndertakeAcademicMeeting detachedInstance) {
		log.debug("merging TeacherAndmainUndertakeAcademicMeeting instance");
		try {
			TeacherAndmainUndertakeAcademicMeeting result = (TeacherAndmainUndertakeAcademicMeeting) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TeacherAndmainUndertakeAcademicMeeting instance) {
		log.debug("attaching dirty TeacherAndmainUndertakeAcademicMeeting instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TeacherAndmainUndertakeAcademicMeeting instance) {
		log.debug("attaching clean TeacherAndmainUndertakeAcademicMeeting instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}