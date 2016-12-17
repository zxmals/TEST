package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingPaperPerformance;
import com.nuaa.ec.model.TfteachingPaperProject;
import com.nuaa.ec.model.TfteachingRearchPerformance;
import com.nuaa.ec.teachingData.exportData.TeachingPaperExcel;
import com.nuaa.ec.teachingData.exportData.TeachingResearchExcel;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfteachingPaperPerformance entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfteachingPaperPerformance
 * @author MyEclipse Persistence Tools
 */
public class TfteachingPaperPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfteachingPaperPerformanceDAO.class);
	// property constants
	public static final String SINGEL_SCORE = "singelScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfteachingPaperPerformance> TfteachingPaperPerfList = null;
	/**
	 *教学论文的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString ="from TfteachingPaperPerformance TPP where TPP.spareTire='1'"
					+ " and TPP.tfteachingPaperProject.spareTire='1'"
					+ " and TPP.tfteachingPaperProject.tfteachingPaperRetrievalCondition.spareTire='1'"
					+ " and TPP.tfteachingPaperProject.tfterm.spareTire='1'"
					+ " and TPP.selfUndertakeTask.spareTire='1'"
					+ " and TPP.tfteachingPaperProject.tfterm.termId between ? and ?"
					+ " and TPP.teacher.spareTire='1'"
					+ " and TPP.teacher.department.spareTire='1'"
					+ " and TPP.teacher.department=?"
					+ " order by TPP.tfteachingPaperProject.teachPaperId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					TeachingPaperExcel.generateExcel(
							stringstore.teachingPaper,
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
	public boolean updateCheckoutStatus(List<TfteachingPaperPerformance> TfteachingPaperPerformanceList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TfteachingPaperPerformanceList.size();i++){
				session.update(TfteachingPaperPerformanceList.get(i));
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
	public List getTFteachingPaperPefroList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_TPP", 0);
				session.put("recordNumber_TPP", 0);
				return TfteachingPaperPerfList = new ArrayList<TfteachingPaperPerformance>();
			} else {
				if (checkOut.equals("4")) {
					// 查出符合条件的全部的记录
					hqlBuffer = new StringBuffer(
							"from TfteachingPaperPerformance TPP where TPP.spareTire='1'"
									+ " and TPP.tfteachingPaperProject.spareTire='1'"
									+ " and TPP.tfteachingPaperProject.tfteachingPaperRetrievalCondition.spareTire='1'"
									+ " and TPP.tfteachingPaperProject.tfterm.spareTire='1'"
									+ " and TPP.selfUndertakeTask.spareTire='1'"
									+ " and TPP.tfteachingPaperProject.tfterm.termId='"+termId+"'"
									+ " and TPP.teacher.spareTire='1'"
									+ " and TPP.teacher.department.spareTire='1'"
									+ " and TPP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by TPP.tfteachingPaperProject.teachPaperId desc");
				}else {
					// 查出符合条件的全部的记录
					hqlBuffer = new StringBuffer(
							"from TfteachingPaperPerformance TPP where TPP.spareTire='1'"
									+ " and TPP.checkOut='" + checkOut + "'"
									+ " and TPP.tfteachingPaperProject.spareTire='1'"
									+ " and TPP.tfteachingPaperProject.tfteachingPaperRetrievalCondition.spareTire='1'"
									+ " and TPP.tfteachingPaperProject.tfterm.spareTire='1'"
									+ " and TPP.selfUndertakeTask.spareTire='1'"
									+ " and TPP.tfteachingPaperProject.tfterm.termId='"+termId+"'"
									+ " and TPP.teacher.spareTire='1'"
									+ " and TPP.teacher.department.spareTire='1'"
									+ " and TPP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by TPP.tfteachingPaperProject.teachPaperId desc");
				}
				
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					TfteachingPaperPerfList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=TfteachingPaperPerfList.size();
					session.put("pageCount_TPP", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_TPP", TfteachingPaperPerfList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				TfteachingPaperPerfList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return TfteachingPaperPerfList;
	}
	public void save(TfteachingPaperPerformance transientInstance) {
		log.debug("saving TfteachingPaperPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfteachingPaperPerformance persistentInstance) {
		log.debug("deleting TfteachingPaperPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfteachingPaperPerformance findById(java.lang.Integer id) {
		log.debug("getting TfteachingPaperPerformance instance with id: " + id);
		try {
			TfteachingPaperPerformance instance = (TfteachingPaperPerformance) getSession()
					.get("com.nuaa.ec.model.TfteachingPaperPerformance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfteachingPaperPerformance instance) {
		log.debug("finding TfteachingPaperPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfteachingPaperPerformance")
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
		log.debug("finding TfteachingPaperPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfteachingPaperPerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySingelScore(Object singelScore) {
		return findByProperty(SINGEL_SCORE, singelScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TfteachingPaperPerformance instances");
		try {
			String queryString = "from TfteachingPaperPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPaging(int currentRow,int limitRow,String conition,Teacher teacher){
		try {
			String queryString = "from TfteachingPaperPerformance "
					+ "where spareTire='1' "
					+ "and teacher=? "
					+ "and teacher.spareTire='1' "
					+conition
					+ "and tfteachingPaperProject.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, teacher)
					.setFirstResult(currentRow).setMaxResults(limitRow);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		
	}
	
	public int getRows(String conition,Teacher teacher){
		try {
			String queryString = "from TfteachingPaperPerformance "
					+ "where spareTire='1' "
					+ "and teacher=? "
					+ "and teacher.spareTire='1' "
					+conition
					+ "and tfteachingPaperProject.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, teacher);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public boolean checkexist(TfteachingPaperProject project,Teacher teacher){
		try {
			String queryString = "from TfteachingPaperPerformance "
					+ "where teacher=? "
					+ "and tfteachingPaperProject=? "
					+ "and spareTire='1' ";
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
	
	public List findMember(TfteachingPaperProject project){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.singelScore) "
					+ " from TfteachingPaperPerformance t "
					+ "where t.spareTire='1' "
					+ "and t.tfteachingPaperProject=? "
					+ "and t.tfteachingPaperProject.spareTire='1' "
					+ "and t.teacher.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRef(TfteachingPaperProject project){
		try {
			String queryString = "update TfteachingPaperPerformance "
					+ "set spareTire='0' "
					+ "where tfteachingPaperProject=? "
					+ "and spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void quitProject(Teacher teacher,TfteachingPaperProject project){
		try {
			String queryString = "update TfteachingPaperPerformance  "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tfteachingPaperProject=? "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, project).setParameter(1, teacher);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateScore(String teacherId,double score,TfteachingPaperProject project){
		try {
			String queryString = "update TfteachingPaperPerformance "
					+ "set singelScore=? "
					+ "where tfteachingPaperProject=? "
					+ "and spareTire='1' "
					+ "and teacher.teacherId=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, score).setParameter(1, project).setParameter(2, teacherId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TfteachingPaperPerformance merge(
			TfteachingPaperPerformance detachedInstance) {
		log.debug("merging TfteachingPaperPerformance instance");
		try {
			TfteachingPaperPerformance result = (TfteachingPaperPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfteachingPaperPerformance instance) {
		log.debug("attaching dirty TfteachingPaperPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfteachingPaperPerformance instance) {
		log.debug("attaching clean TfteachingPaperPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}