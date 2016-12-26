package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingRearchPerformance;
import com.nuaa.ec.model.TfteachingRearchProject;
import com.nuaa.ec.teachingData.exportData.TeachingResearchExcel;
import com.nuaa.ec.utils.Statistics_asist;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

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

/**
 * A data access object (DAO) providing persistence and search support for
 * TfteachingRearchPerformance entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfteachingRearchPerformance
 * @author MyEclipse Persistence Tools
 */
public class TfteachingRearchPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfteachingRearchPerformanceDAO.class);
	// property constants
	public static final String SPARE_TIRE = "spareTire";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfteachingRearchPerformance> TfteachingRearchPerformanceList = null;
	
	/***
	 * 获取 该 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSA(String foreterm,String afterterm,Department depart){
		try {
			String queryString = "select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(TRP.finalScore),0),ISNULL(avg(TRP.finalScore),0)) "
					+ "from TfteachingRearchPerformance TRP where TRP.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.tfteachingRearchEvaluation.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.tfteachingRearchFundlevel.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.tfterm.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.tfterm.termId between ? and ?"
					+ " and TRP.teacher.spareTire='1'"
					+ " and TRP.checkOut='3'"
					+ " and TRP.teacher.department.spareTire='1'"
					+ " and TRP.teacher.department=?";
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
	
	/**
	 *教学研究的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString ="from TfteachingRearchPerformance TRP where TRP.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.tfteachingRearchEvaluation.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.tfteachingRearchFundlevel.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.tfterm.spareTire='1'"
					+ " and TRP.tfteachingRearchProject.tfterm.termId between ? and ?"
					+ " and TRP.teacher.spareTire='1'"
					+ " and TRP.teacher.department.spareTire='1'"
					+ " and TRP.teacher.department=?"
					+"  and TRP.checkOut='3' "
					+ " order by TRP.tfteachingRearchProject.projectId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					TeachingResearchExcel.generateExcel(
							stringstore.teachingResearch,
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
	public boolean updateCheckoutStatus(List<TfteachingRearchPerformance> TfteachingRearchPerfList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TfteachingRearchPerfList.size();i++){
				session.update(TfteachingRearchPerfList.get(i));
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
	public List getTFteachingRearchPefroList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_TRP", 0);
				session.put("recordNumber_TRP", 0);
				return TfteachingRearchPerformanceList = new ArrayList<TfteachingRearchPerformance>();
			} else {
				if (checkOut.equals("4")) {
					hqlBuffer = new StringBuffer(
							"from TfteachingRearchPerformance TRP where TRP.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.tfteachingRearchEvaluation.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.tfteachingRearchFundlevel.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.tfterm.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.tfterm.termId='"+termId+"'"
									+ " and TRP.teacher.spareTire='1'"
									+ " and TRP.teacher.department.spareTire='1'"
									+ " and TRP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by TRP.tfteachingRearchProject.projectId desc");
				}else{
					// 查出符合条件的全部的记录
					hqlBuffer = new StringBuffer(
							"from TfteachingRearchPerformance TRP where TRP.spareTire='1'"
									+ " and TRP.checkOut='" + checkOut + "'"
									+ " and TRP.tfteachingRearchProject.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.tfteachingRearchEvaluation.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.tfteachingRearchFundlevel.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.tfterm.spareTire='1'"
									+ " and TRP.tfteachingRearchProject.tfterm.termId='"+termId+"'"
									+ " and TRP.teacher.spareTire='1'"
									+ " and TRP.teacher.department.spareTire='1'"
									+ " and TRP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by TRP.tfteachingRearchProject.projectId desc");
				}
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					TfteachingRearchPerformanceList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=TfteachingRearchPerformanceList.size();
					session.put("pageCount_TRP", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_TRP", TfteachingRearchPerformanceList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				TfteachingRearchPerformanceList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return TfteachingRearchPerformanceList;
	}
	public void save(TfteachingRearchPerformance transientInstance) {
		log.debug("saving TfteachingRearchPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfteachingRearchPerformance persistentInstance) {
		log.debug("deleting TfteachingRearchPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfteachingRearchPerformance findById(java.lang.Integer id) {
		log.debug("getting TfteachingRearchPerformance instance with id: " + id);
		try {
			TfteachingRearchPerformance instance = (TfteachingRearchPerformance) getSession()
					.get("com.nuaa.ec.model.TfteachingRearchPerformance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfteachingRearchPerformance instance) {
		log.debug("finding TfteachingRearchPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfteachingRearchPerformance")
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
		log.debug("finding TfteachingRearchPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfteachingRearchPerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByFinalScore(Object finalScore) {
		return findByProperty(FINAL_SCORE, finalScore);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TfteachingRearchPerformance instances");
		try {
			String queryString = "from TfteachingRearchPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPaging(int currentRow,int lmitRow,String condition,Teacher teacher){
		try {
			String queryString = "from TfteachingRearchPerformance "
					+ "where spareTire='1' "
					+ "and tfteachingRearchProject.spareTire='1' "
					+condition
					+ "and teacher.spareTire='1' "
					+ "and teacher=?";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, teacher)
					.setFirstResult(currentRow).setMaxResults(lmitRow);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition,Teacher teacher){
		try {
			String queryString = "from TfteachingRearchPerformance "
					+ "where spareTire='1' "
					+ "and tfteachingRearchProject.spareTire='1' "
					+condition
					+ "and teacher.spareTire='1' "
					+ "and teacher=?";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, teacher);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRef(TfteachingRearchProject project){
		try {
			String queryString = "update TfteachingRearchPerformance "
					+ "set spareTire='0' "
					+ "where tfteachingRearchProject=? "
					+ "and spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void quitProject(Teacher teacher,TfteachingRearchProject project){
		try {
			String queryString = "update TfteachingRearchPerformance "
					+ "set spareTire='0' "
					+ "where tfteachingRearchProject=? "
					+ "and spareTire='1' "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, project).setParameter(1, teacher);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public boolean checkexist(TfteachingRearchProject project,Teacher teacher){
		try {
			String queryString = "from TfteachingRearchPerformance "
					+ "where spareTire='1' "
					+ "and teacher=? "
					+ "and tfteachingRearchProject=? "
					+ "and tfteachingRearchProject.spareTire='1' "
					+ "and teacher.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, teacher).setParameter(1, project);
			if(queryObject.list().size()>0){
				return false;
			}else return true;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMember(TfteachingRearchProject project){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.finalScore) "
					+ "from TfteachingRearchPerformance t "
					+ "where spareTire='1' "
					+ "and tfteachingRearchProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateScore(String teacherId,double score,TfteachingRearchProject project){
		try {
			String queryString = "update TfteachingRearchPerformance "
					+ "set finalScore=? "
					+ "where tfteachingRearchProject=? "
					+ "and spareTire='1' "
					+ "and teacher.teacherId=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, score)
					.setParameter(1, project).setParameter(2, teacherId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TfteachingRearchPerformance merge(
			TfteachingRearchPerformance detachedInstance) {
		log.debug("merging TfteachingRearchPerformance instance");
		try {
			TfteachingRearchPerformance result = (TfteachingRearchPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfteachingRearchPerformance instance) {
		log.debug("attaching dirty TfteachingRearchPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfteachingRearchPerformance instance) {
		log.debug("attaching clean TfteachingRearchPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}