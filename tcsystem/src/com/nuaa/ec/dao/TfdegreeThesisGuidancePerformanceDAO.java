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

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfdegreeThesisGuidancePerformance;
import com.nuaa.ec.model.TfdegreeThesisGuidancePerformanceUnionTfterm;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfdegreeThesisGuidancePerformance entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfdegreeThesisGuidancePerformance
 * @author MyEclipse Persistence Tools
 */
public class TfdegreeThesisGuidancePerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfdegreeThesisGuidancePerformanceDAO.class);
	// property constants
	public static final String DEGREE_THESISN_NAME = "degreeThesisnName";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private List<TfdegreeThesisGuidancePerformance> TFdegreeThesisGuidancePefroList = null;

	/**
	 * （管理员操作）获得所有的记录信息 但是顺便实现了分页
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithDivided_adm(int pageIndex, int pageSize, String termId,
			String searchCondition,boolean isDivided) {
		List<TfdegreeThesisGuidancePerformanceUnionTfterm> list = new ArrayList<TfdegreeThesisGuidancePerformanceUnionTfterm>();
		StringBuffer hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId != null && termId.length() != 0 && searchCondition!=null && searchCondition.trim().length()!=0 ) {
			hql = new StringBuffer("select new com.nuaa.ec.model.TfdegreeThesisGuidancePerformanceUnionTfterm(DTG,TERM) from TfdegreeThesisGuidancePerformance DTG,Tfterm TERM where DTG.spareTire='1'"
						+ " and TERM.spareTire='1'"
						+ " and DTG.tfdegreeThesisGuidanceRewardLevel.spareTire='1'"
						+ " and DTG.teacher.spareTire='1'"
						+ " and DTG.termId=TERM.termId"
						+ " and TERM.termId='" + termId + "'"
						+ " and DTG.degreeThesisnName like '%"+searchCondition+"%'"
						+ " order by DTG.degreeThesisId desc");
		} else {
			hql = new StringBuffer("select new com.nuaa.ec.model.TfdegreeThesisGuidancePerformanceUnionTfterm(DTG,TERM) from TfdegreeThesisGuidancePerformance DTG,Tfterm TERM where DTG.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and DTG.tfdegreeThesisGuidanceRewardLevel.spareTire='1'"
					+ " and DTG.teacher.spareTire='1'"
					+ " and DTG.termId=TERM.termId");
//					+ " order by DTG.degreeThesisId desc");
			//有学期但是没有查询条件的情况
			if(termId != null && termId.length() != 0 &&(searchCondition==null || searchCondition.trim().length()==0)){
				hql.append(" and DTG.termId='"+termId+"'");
			}else if(searchCondition!=null && searchCondition.length()!=0 &&(termId == null || termId.length() == 0)){
				//有查询条件 但是没有学期的情况
				hql.append(" and DTG.degreeThesisnName like '%"+searchCondition+"%'");
			}else{//学期和查询条件都没有的情况
				//这块没有业务逻辑，只是为了使得逻辑清楚
			}
			hql.append(" order by DTG.degreeThesisId desc");//指定结果集排序规则
		}
		try {
			if (!isDivided) {
				list = this.getSession().createQuery(hql.toString()).list();
				int listSize = list.size();
				session.put("recordNumber_ATDTG", list.size());
				session.put("pageCount_ATDTG",
						listSize % pageSize == 0 ? (listSize / pageSize)
								: (listSize / pageSize + 1));
			}
			/*
			 * 分页 pageIndex 默认是1，显示第一页， 但以后会随着前台的分页操作同步更新。
			 */
			list = this.getSession().createQuery(hql.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 获得所有的记录信息 但是顺便实现了分页
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithDivided(int pageIndex, int pageSize, String termId,
			boolean isDivided) {
		Teacher teacherHaveLogin = (Teacher) session.get("teacher");
		List<TfdegreeThesisGuidancePerformanceUnionTfterm> list = new ArrayList<TfdegreeThesisGuidancePerformanceUnionTfterm>();
		String hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId == null || termId.length() == 0) {
			hql = "select new com.nuaa.ec.model.TfdegreeThesisGuidancePerformanceUnionTfterm(DTG,TERM) from TfdegreeThesisGuidancePerformance DTG,Tfterm TERM where DTG.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and DTG.tfdegreeThesisGuidanceRewardLevel.spareTire='1'"
					+ " and DTG.teacher.spareTire='1'"
					+ " and DTG.teacher=?"
					+ " and DTG.termId=TERM.termId"
					+ " order by DTG.degreeThesisId desc";
		} else {
			hql = "select new com.nuaa.ec.model.TfdegreeThesisGuidancePerformanceUnionTfterm(DTG,TERM) from TfdegreeThesisGuidancePerformance DTG,Tfterm TERM where DTG.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and DTG.tfdegreeThesisGuidanceRewardLevel.spareTire='1'"
					+ " and DTG.teacher.spareTire='1'"
					+ " and DTG.teacher=?"
					+ " and DTG.termId=TERM.termId"
					+ " and TERM.termId='" + termId + "'"
					+ " order by DTG.degreeThesisId desc";
		}
		try {
			if (!isDivided) {
				list = this.getSession().createQuery(hql)
						.setParameter(0, teacherHaveLogin).list();
				int listSize = list.size();
				session.put("recordNumber_GTDTG", list.size());
				session.put("pageCount_GTDTG",
						listSize % pageSize == 0 ? (listSize / pageSize)
								: (listSize / pageSize + 1));
			}
			/*
			 * 分页 pageIndex 默认是1，显示第一页， 但以后会随着前台的分页操作同步更新。
			 */
			list = this.getSession().createQuery(hql)
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).setParameter(0, teacherHaveLogin)
					.list();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	public boolean updateCheckoutStatus(
			List<TfdegreeThesisGuidancePerformance> TfDTGPerfoList) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < TfDTGPerfoList.size(); i++) {
				session.update(TfDTGPerfoList.get(i));
			}
			tx = session.beginTransaction();
			tx.commit();
			updateFlag = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return updateFlag;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List getTFdegreeThesisGuidancePefro(int pageIndex, int pageSize,
			String termId, Department department, String checkOut,
			boolean isDivided) {
		StringBuffer hqlBuffer = null;
		if (department.getDepartmentId() == null
				|| department.getDepartmentId().length() == 0) {
			/*
			 * 第一次进入的时候，不显示记录
			 */
			session.put("pageCount_DTG", 0);
			session.put("recordNumber_DTG", 0);
			return TFdegreeThesisGuidancePefroList = new ArrayList<TfdegreeThesisGuidancePerformance>();
		} else {
			if (checkOut.equals("4")) {
				hqlBuffer = new StringBuffer(
						"select DTG from TfdegreeThesisGuidancePerformance DTG,Tfterm TERM where DTG.spareTire='1'"
								+ " and TERM.spareTire='1'"
								+ " and DTG.tfdegreeThesisGuidanceRewardLevel.spareTire='1'"
								+ " and DTG.teacher.spareTire='1'"
								+ " and DTG.teacher.department.spareTire='1'"
								+ " and DTG.teacher.department.departmentId='"
								+ department.getDepartmentId()
								+ "'"
								+ " and DTG.termId=TERM.termId"
								+ ""
								+ " and TERM.termId='" + termId + "'");
			}else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"select DTG from TfdegreeThesisGuidancePerformance DTG,Tfterm TERM where DTG.spareTire='1'"
								+ " and DTG.checkOut='"
								+ checkOut
								+ "'"
								+ " and TERM.spareTire='1'"
								+ " and DTG.tfdegreeThesisGuidanceRewardLevel.spareTire='1'"
								+ " and DTG.teacher.spareTire='1'"
								+ " and DTG.teacher.department.spareTire='1'"
								+ " and DTG.teacher.department.departmentId='"
								+ department.getDepartmentId()
								+ "'"
								+ " and DTG.termId=TERM.termId"
								+ ""
								+ " and TERM.termId='" + termId + "'");
			}
			// 判断是否为分页操作
			if (!isDivided) {
				// 如果不是分页操作，取出所有符合条件的记录
				TFdegreeThesisGuidancePefroList = this.getSession()
						.createQuery(hqlBuffer.toString()).list();
				int recordNumber = TFdegreeThesisGuidancePefroList.size();
				session.put(
						"pageCount_DTG",
						recordNumber % pageSize == 0 ? (recordNumber / pageSize)
								: (recordNumber / pageSize + 1));
				session.put("recordNumber_DTG",
						TFdegreeThesisGuidancePefroList.size());
			}
			// 无论是不是分页查询，都在后台进行分页操作。
			TFdegreeThesisGuidancePefroList = this.getSession()
					.createQuery(hqlBuffer.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		}
		return TFdegreeThesisGuidancePefroList;
	}

	public void save(TfdegreeThesisGuidancePerformance transientInstance) {
		log.debug("saving TfdegreeThesisGuidancePerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfdegreeThesisGuidancePerformance persistentInstance) {
		log.debug("deleting TfdegreeThesisGuidancePerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfdegreeThesisGuidancePerformance findById(java.lang.String id) {
		log.debug("getting TfdegreeThesisGuidancePerformance instance with id: "
				+ id);
		try {
			TfdegreeThesisGuidancePerformance instance = (TfdegreeThesisGuidancePerformance) getSession()
					.get("com.nuaa.ec.model.TfdegreeThesisGuidancePerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfdegreeThesisGuidancePerformance instance) {
		log.debug("finding TfdegreeThesisGuidancePerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfdegreeThesisGuidancePerformance")
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
		log.debug("finding TfdegreeThesisGuidancePerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfdegreeThesisGuidancePerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDegreeThesisnName(Object degreeThesisnName) {
		return findByProperty(DEGREE_THESISN_NAME, degreeThesisnName);
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

	public List findAll() {
		log.debug("finding all TfdegreeThesisGuidancePerformance instances");
		try {
			String queryString = "from TfdegreeThesisGuidancePerformance where spareTire='1'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfdegreeThesisGuidancePerformance merge(
			TfdegreeThesisGuidancePerformance detachedInstance) {
		log.debug("merging TfdegreeThesisGuidancePerformance instance");
		try {
			TfdegreeThesisGuidancePerformance result = (TfdegreeThesisGuidancePerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfdegreeThesisGuidancePerformance instance) {
		log.debug("attaching dirty TfdegreeThesisGuidancePerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfdegreeThesisGuidancePerformance instance) {
		log.debug("attaching clean TfdegreeThesisGuidancePerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}