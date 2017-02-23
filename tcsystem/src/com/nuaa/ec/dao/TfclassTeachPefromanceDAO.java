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
import com.nuaa.ec.model.TfclassTeachPefromance;
import com.nuaa.ec.model.TfclassTeachPefromanceUnionTfterm;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.teachingData.exportData.ClassTeachExcel;
import com.nuaa.ec.utils.Statistics_asist;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfclassTeachPefromance entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.nuaa.ec.model.TfclassTeachPefromance
 * @author MyEclipse Persistence Tools
 */
public class TfclassTeachPefromanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfclassTeachPefromanceDAO.class);
	// property constants
	public static final String SUMTIME = "sumtime";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private List<TfclassTeachPefromance> TFClassTeachPefroList = null;

	@Test
	public void test(){
		String foreterm = "Term000000001";
		String afterterm = "Term000000010";
		Department depart = new Department("D000000001");
		Statistics_asist e = new TfclassTeachPefromanceDAO().getSA(foreterm, afterterm, depart);
		System.out.println("sum: "+e.getSum()+"||||  avg: "+e.getAvg());
	}
	
	/***
	 * 获取 该 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSA(String foreterm,String afterterm,Department depart){
		try {
			String queryString = "select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(ct.finalScore),0),ISNULL(avg(ct.finalScore),0)) "
					+ "from TfclassTeachPefromance ct,Tfterm term "
					+ "where term.termId=ct.termId"
					+ " and ct.spareTire='1'"
					+ " and ct.checkOut='3'"
					+ " and term.spareTire='1'"
					+ " and ct.tfclassTeachEvaluation.spareTire='1'"
					+ " and ct.tfclassTeachTime.spareTire='1'"
					+ " and ct.teacher.spareTire='1'"
					+ " and ct.termId BETWEEN ? and ? "
					+ " and ct.teacher.department=? ";
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
	 * 获取单个教师 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param teacherId
	 * @return
	 */
	public Statistics_asist getSAperson(String foreterm,String afterterm,String teacherId){
		try {
			StringBuffer queryString = new StringBuffer(); 
					queryString.append("select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(ct.finalScore),0),ISNULL(avg(ct.finalScore),0)) "
					+ "from TfclassTeachPefromance ct,Tfterm term "
					+ "where term.termId=ct.termId"
					+ " and ct.spareTire='1'"
					+ " and ct.checkOut='3'"
					+ " and term.spareTire='1'"
					+ " and ct.tfclassTeachEvaluation.spareTire='1'"
					+ " and ct.tfclassTeachTime.spareTire='1'"
					+ " and ct.teacher.spareTire='1'"
					+ " and ct.termId BETWEEN ? and ? ");
						queryString.append(" and ct.teacher.teacherId='"+teacherId.trim()+"' ");
			Query queryObject = getSession().createQuery(queryString.toString())
					.setParameter(0, foreterm).setParameter(1, afterterm);
			if(queryObject.list().size()>0){
				return (Statistics_asist) queryObject.list().get(0);
			}else return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	/**
	 * 课堂教学模块的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString = "select new com.nuaa.ec.model.TfclassTeachPefromanceUnionTfterm(CT,TERM) from TfclassTeachPefromance CT,Tfterm TERM where TERM.termId=CT.termId"
					+ " and CT.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and CT.tfclassTeachEvaluation.spareTire='1'"
					+ " and CT.tfclassTeachTime.spareTire='1'"
					+ " and CT.teacher.spareTire='1'"
					+ " and CT.termId BETWEEN ? and ? "
					+ " and CT.teacher.department=? "
					+"  and CT.checkOut='3' "
					+ " order by CT.classPefromanceId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					ClassTeachExcel.generateExcel(
							stringstore.classTeaching,
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
	 * 获得所有的记录信息
	 * 
	 * @param searchCondition
	 *            :teacherId
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithDivided_adm(int pageIndex, int pageSize,
			String termId, String searchCondition, boolean isDivided) {
		List<TfclassTeachPefromanceUnionTfterm> list = new ArrayList<TfclassTeachPefromanceUnionTfterm>();
		StringBuffer hql = null;
		if (termId != null && termId.length() != 0 && searchCondition != null
				&& searchCondition.trim().length() != 0) {
			hql = new StringBuffer(
					"select new com.nuaa.ec.model.TfclassTeachPefromanceUnionTfterm(CT,TERM) from TfclassTeachPefromance CT,Tfterm TERM where TERM.termId=CT.termId"
							+ " and CT.spareTire='1'"
							+ " and TERM.spareTire='1'"
							+ " and CT.tfclassTeachEvaluation.spareTire='1'"
							+ " and CT.tfclassTeachTime.spareTire='1'"
							+ " and CT.teacher.spareTire='1'"
							+ " and CT.termId='"
							+ termId
							+ "'"
							+ " and CT.teacher.teacherId='"
							+ searchCondition.trim()
							+ "'"
							+ " order by CT.classPefromanceId desc");
		}
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出所有教师的所有的数据
		 */
		else {
			hql = new StringBuffer(
					"select new com.nuaa.ec.model.TfclassTeachPefromanceUnionTfterm(CT,TERM) from TfclassTeachPefromance CT,Tfterm TERM where TERM.termId=CT.termId"
							+ " and CT.spareTire='1'"
							+ " and TERM.spareTire='1'"
							+ " and CT.tfclassTeachEvaluation.spareTire='1'"
							+ " and CT.tfclassTeachTime.spareTire='1'"
							+ " and CT.teacher.spareTire='1'");
			// + " order by CT.classPefromanceId desc");
			// 有学期但是没有查询条件的情况
			if (termId != null
					&& termId.length() != 0
					&& (searchCondition == null || searchCondition.trim()
							.length() == 0)) {
				hql.append(" and CT.termId='" + termId + "'");
			} else if (searchCondition != null && searchCondition.length() != 0
					&& (termId == null || termId.length() == 0)) {
				// 有查询条件 但是没有学期的情况
				hql.append(" and CT.teacher.teacherId='"
						+ searchCondition.trim() + "'");
			} else {// 学期和查询条件都没有的情况
					// 这块没有业务逻辑，只是为了使得逻辑清楚
			}
			hql.append(" order by CT.classPefromanceId desc");// 指定结果集排序规则
		}
		try {
			if (!isDivided) {
				list = this.getSession().createQuery(hql.toString()).list();
				int listSize = list.size();
				session.put("recordNumber_ATCT", list.size());
				session.put("pageCount_ATCT",
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
		List<TfclassTeachPefromanceUnionTfterm> list = new ArrayList<TfclassTeachPefromanceUnionTfterm>();
		String hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId == null || termId.length() == 0) {
			hql = "select new com.nuaa.ec.model.TfclassTeachPefromanceUnionTfterm(CT,TERM) from TfclassTeachPefromance CT,Tfterm TERM where TERM.termId=CT.termId"
					+ " and CT.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and CT.tfclassTeachEvaluation.spareTire='1'"
					+ " and CT.tfclassTeachTime.spareTire='1'"
					+ " and CT.teacher.spareTire='1'"
					+ " and CT.teacher=?"
					+ " order by CT.classPefromanceId desc";
		} else {
			hql = "select new com.nuaa.ec.model.TfclassTeachPefromanceUnionTfterm(CT,TERM) from TfclassTeachPefromance CT,Tfterm TERM where TERM.termId=CT.termId"
					+ " and CT.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and CT.tfclassTeachEvaluation.spareTire='1'"
					+ " and CT.tfclassTeachTime.spareTire='1'"
					+ " and CT.teacher.spareTire='1'"
					+ " and CT.teacher=?"
					+ " and CT.termId='"
					+ termId
					+ "'"
					+ " order by CT.classPefromanceId desc";
		}
		try {
			if (!isDivided) {
				list = this.getSession().createQuery(hql)
						.setParameter(0, teacherHaveLogin).list();
				int listSize = list.size();
				session.put("recordNumber_GTCT", list.size());
				session.put("pageCount_GTCT",
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
			List<TfclassTeachPefromance> TfclassTeachPerfoList) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < TfclassTeachPerfoList.size(); i++) {
				session.update(TfclassTeachPerfoList.get(i));
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
	public List getTFClassTeachPefro(int pageIndex, int pageSize,
			String termId, Department department, String checkOut,
			boolean isDivided) {
		StringBuffer hqlBuffer = null;
		if (department.getDepartmentId() == null
				|| department.getDepartmentId().length() == 0) {
			/*
			 * 第一次进入的时候，不显示记录
			 */
			session.put("pageCount_CT", 0);
			session.put("recordNumber_CT", 0);
			return TFClassTeachPefroList = new ArrayList<TfclassTeachPefromance>();
		} else {
			if (checkOut.equals("4")) {
				hqlBuffer = new StringBuffer(
						"select CT from TfclassTeachPefromance CT,Tfterm TERM where CT.spareTire='1'"
								+ " and TERM.spareTire='1'"
								+ " and CT.tfclassTeachEvaluation.spareTire='1'"
								+ " and CT.teacher.spareTire='1'"
								+ " and CT.teacher.department.spareTire='1'"
								+ " and CT.teacher.department.departmentId='"
								+ department.getDepartmentId() + "'"
								+ " and CT.termId=TERM.termId"
								+ " and TERM.termId='" + termId + "'");
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"select CT from TfclassTeachPefromance CT,Tfterm TERM where CT.spareTire='1'"
								+ " and CT.checkOut='"
								+ checkOut
								+ "'"
								+ " and TERM.spareTire='1'"
								+ " and CT.tfclassTeachEvaluation.spareTire='1'"
								+ " and CT.teacher.spareTire='1'"
								+ " and CT.teacher.department.spareTire='1'"
								+ " and CT.teacher.department.departmentId='"
								+ department.getDepartmentId() + "'"
								+ " and CT.termId=TERM.termId"
								+ " and TERM.termId='" + termId + "'");
			}
			// 判断是否为分页操作
			if (!isDivided) {
				// 如果不是分页操作，取出所有符合条件的记录
				TFClassTeachPefroList = this.getSession()
						.createQuery(hqlBuffer.toString()).list();
				int recordNumber = TFClassTeachPefroList.size();
				session.put(
						"pageCount_CT",
						recordNumber % pageSize == 0 ? (recordNumber / pageSize)
								: (recordNumber / pageSize + 1));
				session.put("recordNumber_CT", TFClassTeachPefroList.size());
			}
			// 无论是不是分页查询，都在后台进行分页操作。
			TFClassTeachPefroList = this.getSession()
					.createQuery(hqlBuffer.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		}
		return TFClassTeachPefroList;
	}

	public void save(TfclassTeachPefromance transientInstance) {
		log.debug("saving TfclassTeachPefromance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfclassTeachPefromance persistentInstance) {
		log.debug("deleting TfclassTeachPefromance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfclassTeachPefromance findById(java.lang.String id) {
		log.debug("getting TfclassTeachPefromance instance with id: " + id);
		try {
			TfclassTeachPefromance instance = (TfclassTeachPefromance) getSession()
					.get("com.nuaa.ec.model.TfclassTeachPefromance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfclassTeachPefromance instance) {
		log.debug("finding TfclassTeachPefromance instance by example");
		try {
			List results = getSession()
					.createCriteria("com.nuaa.ec.model.TfclassTeachPefromance")
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
		log.debug("finding TfclassTeachPefromance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfclassTeachPefromance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySumtime(Object sumtime) {
		return findByProperty(SUMTIME, sumtime);
	}

	public List findByFinalScore(Object finalScore) {
		return findByProperty(FINAL_SCORE, finalScore);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TfclassTeachPefromance instances");
		try {
			String queryString = "from TfclassTeachPefromance where spareTire = '1'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfclassTeachPefromance merge(TfclassTeachPefromance detachedInstance) {
		log.debug("merging TfclassTeachPefromance instance");
		try {
			TfclassTeachPefromance result = (TfclassTeachPefromance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfclassTeachPefromance instance) {
		log.debug("attaching dirty TfclassTeachPefromance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfclassTeachPefromance instance) {
		log.debug("attaching clean TfclassTeachPefromance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}