package com.nuaa.ec.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfjoinStudentActivityPerformance;
import com.nuaa.ec.model.TfjoinStudentActivityPerformanceUnionTfterm;
import com.nuaa.ec.teachingData.exportData.JoinStudentActivityExcel;
import com.nuaa.ec.teachingData.exportData.StudentCompetitionExcel;
import com.nuaa.ec.utils.Statistics_asist;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfjoinStudentActivityPerformance entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfjoinStudentActivityPerformance
 * @author MyEclipse Persistence Tools
 */
public class TfjoinStudentActivityPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfjoinStudentActivityPerformanceDAO.class);
	// property constants
	public static final String ACTIVITY_ID = "activityId";
	public static final String ACTIVITY_NAME = "activityName";
	public static final String SUMHOURS = "sumhours";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	public static final String YEARCEILING = "yearceiling";

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	private List<TfjoinStudentActivityPerformance> tfJoinStudentActivityPerformanceList = null;
	
	/***
	 * 获取 该 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSA(String foreterm,String afterterm,Department depart){
		try {
			String queryString = "select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(JSA.finalScore),0),ISNULL(avg(JSA.finalScore),0)) "
					+ "from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
					+ " and JSA.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
					+ " and JSA.teacher.spareTire='1'"
					+ " and JSA.checkOut='3'"
					+ " and JSA.termId between ? and ?"
					+ " and JSA.teacher.department=?";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foreterm).setParameter(1, afterterm)
					.setParameter(2, depart);
			if(queryObject.list().size()>0){
				return (Statistics_asist) queryObject.list().get(0);
			}else return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	/***
	 * 获取 单个教师 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSAperson(String foreterm,String afterterm,String teacherId){
		try {
			String queryString = "select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(JSA.finalScore),0),ISNULL(avg(JSA.finalScore),0)) "
					+ "from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
					+ " and JSA.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
					+ " and JSA.teacher.spareTire='1'"
					+ " and JSA.checkOut='3'"
					+ " and JSA.termId between ? and ?"
					+ " and JSA.teacher.teacherId=?";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foreterm).setParameter(1, afterterm)
					.setParameter(2, teacherId);
			if(queryObject.list().size()>0){
				return (Statistics_asist) queryObject.list().get(0);
			}else return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	/**
	 * 参与学生活动指导模块的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString ="select new com.nuaa.ec.model.TfjoinStudentActivityPerformanceUnionTfterm(JSA,TERM) from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
					+ " and JSA.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
					+ " and JSA.teacher.spareTire='1'"
					+ " and JSA.termId between ? and ?"
					+ " and JSA.teacher.department=?"
					+"  and JSA.checkOut='3' "
					+ " order by JSA.activityId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					JoinStudentActivityExcel.generateExcel(
							stringstore.joinStudentActivity,
							queryObject.list(), departmentName, foredate,
							afterdate).write(baos);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return baos;
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	/**
	 * 获得所有的记录信息 但是顺便实现了分页
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithDivided_adm(int pageIndex, int pageSize, String termId,
			String searchCondition,boolean isDivided) {
		List<TfjoinStudentActivityPerformanceUnionTfterm> list = new ArrayList<TfjoinStudentActivityPerformanceUnionTfterm>();
		StringBuffer hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId != null && termId.length() != 0 && searchCondition!=null && searchCondition.trim().length()!=0) {
			hql = new StringBuffer("select new com.nuaa.ec.model.TfjoinStudentActivityPerformanceUnionTfterm(JSA,TERM) from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
					+ " and JSA.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
					+ " and JSA.teacher.spareTire='1'"
					+ " and JSA.termId='"+termId+"'"
					+ " and JSA.activityName like '%"+searchCondition+"%'"
					+ " order by JSA.activityId desc");
		} else {
			hql = new StringBuffer("select new com.nuaa.ec.model.TfjoinStudentActivityPerformanceUnionTfterm(JSA,TERM) from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
					+ " and JSA.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
					+ " and JSA.teacher.spareTire='1'");
			//有学期但是没有查询条件的情况
			if(termId != null && termId.length() != 0 &&(searchCondition==null || searchCondition.trim().length()==0)){
				hql.append(" and JSA.termId='"+termId+"'");
			}else if(searchCondition!=null && searchCondition.length()!=0 &&(termId == null || termId.length() == 0)){
				//有查询条件 但是没有学期的情况
				hql.append(" and JSA.activityName like '%"+searchCondition+"%'");
			}else{//学期和查询条件都没有的情况
				//这块没有业务逻辑，只是为了使得逻辑清楚
			}
			hql.append(" order by JSA.activityId desc");
		}
		try {
			if (!isDivided) {
				list = this.getSession().createQuery(hql.toString()).list();
				int listSize = list.size();
				session.put("recordNumber_ATJSA", list.size());
				session.put("pageCount_ATJSA",
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
		List<TfjoinStudentActivityPerformanceUnionTfterm> list = new ArrayList<TfjoinStudentActivityPerformanceUnionTfterm>();
		String hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId == null || termId.length() == 0) {
			hql = "select new com.nuaa.ec.model.TfjoinStudentActivityPerformanceUnionTfterm(JSA,TERM) from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
					+ " and JSA.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
					+ " and JSA.teacher.spareTire='1'"
					+ " and JSA.teacher=?"
					+ " order by JSA.activityId desc";
		} else {
			hql = "select new com.nuaa.ec.model.TfjoinStudentActivityPerformanceUnionTfterm(JSA,TERM) from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
					+ " and JSA.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
					+ " and JSA.teacher.spareTire='1'"
					+ " and JSA.teacher=?"
					+ " and JSA.termId='"
					+ termId
					+ "'"
					+ " order by JSA.activityId desc";
		}
		try {
			if (!isDivided) {
				list = this.getSession().createQuery(hql)
						.setParameter(0, teacherHaveLogin).list();
				int listSize = list.size();
				session.put("recordNumber_GTJSA", list.size());
				session.put("pageCount_GTJSA",
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
			List<TfjoinStudentActivityPerformance> tfJoinStudentActivityPerfList) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < tfJoinStudentActivityPerfList.size(); i++) {
				session.update(tfJoinStudentActivityPerfList.get(i));
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
	public List getTfJoinStudentActivityPerformanceListToBeAudited(
			int pageIndex, int pageSize, String termId, Department department,
			String checkOut, boolean isDivided) {
		try {
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_JSA", 0);
				session.put("recordNumber_JSA", 0);
				return tfJoinStudentActivityPerformanceList = new ArrayList<TfjoinStudentActivityPerformance>();
			} else {
				if (checkOut.equals("4")) {
					hqlBuffer = new StringBuffer(
							"select JSA from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
									+ " and JSA.spareTire='1'"
									+ " and TERM.spareTire='1'"
									+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
									+ " and JSA.teacher.spareTire='1'"
									+ " and JSA.teacher.department.spareTire='1'"
									+ " and JSA.teacher.department.departmentId='"
									+ department.getDepartmentId()
									+ "'"
									+ " and JSA.termId='"
									+ termId
									+ "'"
									+ " order by JSA.activityId asc");
				}else {
					
					// 查出符合条件的全部的记录
					hqlBuffer = new StringBuffer(
							"select JSA from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
									+ " and JSA.spareTire='1'"
									+ " and TERM.spareTire='1'"
									+ " and JSA.checkOut='"
									+ checkOut
									+ "'"
									+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
									+ " and JSA.teacher.spareTire='1'"
									+ " and JSA.teacher.department.spareTire='1'"
									+ " and JSA.teacher.department.departmentId='"
									+ department.getDepartmentId()
									+ "'"
									+ " and JSA.termId='"
									+ termId
									+ "'"
									+ " order by JSA.activityId asc");
				}
				// 判断是否为分页操作
				if (!isDivided) {
					// 如果不是分页操作，取出所有符合条件的记录
					tfJoinStudentActivityPerformanceList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber = tfJoinStudentActivityPerformanceList
							.size();
					session.put(
							"pageCount_JSA",
							recordNumber % pageSize == 0 ? (recordNumber / pageSize)
									: (recordNumber / pageSize + 1));
					session.put("recordNumber_JSA",
							tfJoinStudentActivityPerformanceList.size());
				}
				// 无论是不是分页查询，都在后台进行分页操作。
				tfJoinStudentActivityPerformanceList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return tfJoinStudentActivityPerformanceList;
	}

	public void save(TfjoinStudentActivityPerformance transientInstance) {
		log.debug("saving TfjoinStudentActivityPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfjoinStudentActivityPerformance persistentInstance) {
		log.debug("deleting TfjoinStudentActivityPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfjoinStudentActivityPerformance findById(java.lang.Integer id) {
		log.debug("getting TfjoinStudentActivityPerformance instance with id: "
				+ id);
		try {
			TfjoinStudentActivityPerformance instance = (TfjoinStudentActivityPerformance) getSession()
					.get("com.nuaa.ec.model.TfjoinStudentActivityPerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfjoinStudentActivityPerformance instance) {
		log.debug("finding TfjoinStudentActivityPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfjoinStudentActivityPerformance")
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
		log.debug("finding TfjoinStudentActivityPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfjoinStudentActivityPerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByActivityId(Object activityId) {
		return findByProperty(ACTIVITY_ID, activityId);
	}

	public List findByActivityName(Object activityName) {
		return findByProperty(ACTIVITY_NAME, activityName);
	}

	public List findBySumhours(Object sumhours) {
		return findByProperty(SUMHOURS, sumhours);
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

	public List findByYearceiling(Object yearceiling) {
		return findByProperty(YEARCEILING, yearceiling);
	}

	public List findAll() {
		log.debug("finding all TfjoinStudentActivityPerformance instances");
		try {
			String queryString = "from TfjoinStudentActivityPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfjoinStudentActivityPerformance merge(
			TfjoinStudentActivityPerformance detachedInstance) {
		log.debug("merging TfjoinStudentActivityPerformance instance");
		try {
			TfjoinStudentActivityPerformance result = (TfjoinStudentActivityPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfjoinStudentActivityPerformance instance) {
		log.debug("attaching dirty TfjoinStudentActivityPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfjoinStudentActivityPerformance instance) {
		log.debug("attaching clean TfjoinStudentActivityPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}