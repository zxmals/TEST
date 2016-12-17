package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TffineCourseConstructionPerformance;
import com.nuaa.ec.model.TfprofessionalProjectDeclarePerformance;
import com.nuaa.ec.model.TfprofessionalProjectDeclareProject;
import com.nuaa.ec.teachingData.exportData.FineCourseConstructionExcel;
import com.nuaa.ec.teachingData.exportData.ProfessionalProjectDeclareExcel;
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
 * TfprofessionalProjectDeclarePerformance entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfprofessionalProjectDeclarePerformance
 * @author MyEclipse Persistence Tools
 */
public class TfprofessionalProjectDeclarePerformanceDAO extends
		BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfprofessionalProjectDeclarePerformanceDAO.class);
	// property constants
	public static final String SINGLE_SCORE = "singleScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfprofessionalProjectDeclarePerformance> tfProfessionalProjectDeclarePerformance = null;
	

	/**
	 *专业项目申报的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString ="from TfprofessionalProjectDeclarePerformance PPD where PPD.spareTire='1'"
					+ " and PPD.tfprofessionalProjectDeclareProject.spareTire='1'"
					+ " and PPD.tfprofessionalProjectDeclareProject.tfprofessionalProjectDeclareLevel.spareTire='1'"
					+ " and PPD.tfprofessionalProjectDeclareProject.tfterm.spareTire='1'"
					+ " and PPD.selfUndertakeTask.spareTire='1'"
					+ " and PPD.tfprofessionalProjectDeclareProject.tfterm.termId between ? and ?"
					+ " and PPD.teacher.spareTire='1'"
					+ " and PPD.teacher.department.spareTire='1'"
					+ " and PPD.teacher.department=?"
					+ " order by PPD.tfprofessionalProjectDeclareProject.projectId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					ProfessionalProjectDeclareExcel.generateExcel(
							stringstore.professionalProjectDeclare,
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
	
	public boolean updateCheckoutStatus(List<TfprofessionalProjectDeclarePerformance> tfProfessionalProjectDeclarePerformanceList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<tfProfessionalProjectDeclarePerformanceList.size();i++){
				session.update(tfProfessionalProjectDeclarePerformanceList.get(i));
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
	public List getTfProfessionalProjectDeclarePerfList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_PPD", 0);
				session.put("recordNumber_PPD", 0);
				return tfProfessionalProjectDeclarePerformance = new ArrayList<TfprofessionalProjectDeclarePerformance>();
			} else {
				if (checkOut.equals("4")) {
					hqlBuffer = new StringBuffer(
							"from TfprofessionalProjectDeclarePerformance PPD where PPD.spareTire='1'"
									+ " and PPD.tfprofessionalProjectDeclareProject.spareTire='1'"
									+ " and PPD.tfprofessionalProjectDeclareProject.tfprofessionalProjectDeclareLevel.spareTire='1'"
									+ " and PPD.tfprofessionalProjectDeclareProject.tfterm.spareTire='1'"
									+ " and PPD.selfUndertakeTask.spareTire='1'"
									+ " and PPD.tfprofessionalProjectDeclareProject.tfterm.termId='"+termId+"'"
									+ " and PPD.teacher.spareTire='1'"
									+ " and PPD.teacher.department.spareTire='1'"
									+ " and PPD.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by PPD.tfprofessionalProjectDeclareProject.projectId desc");
				}else {
					
					// 查出符合条件的全部的记录
					hqlBuffer = new StringBuffer(
							"from TfprofessionalProjectDeclarePerformance PPD where PPD.spareTire='1'"
									+ " and PPD.checkOut='" + checkOut + "'"
									+ " and PPD.tfprofessionalProjectDeclareProject.spareTire='1'"
									+ " and PPD.tfprofessionalProjectDeclareProject.tfprofessionalProjectDeclareLevel.spareTire='1'"
									+ " and PPD.tfprofessionalProjectDeclareProject.tfterm.spareTire='1'"
									+ " and PPD.selfUndertakeTask.spareTire='1'"
									+ " and PPD.tfprofessionalProjectDeclareProject.tfterm.termId='"+termId+"'"
									+ " and PPD.teacher.spareTire='1'"
									+ " and PPD.teacher.department.spareTire='1'"
									+ " and PPD.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by PPD.tfprofessionalProjectDeclareProject.projectId desc");
				}
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					tfProfessionalProjectDeclarePerformance = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=tfProfessionalProjectDeclarePerformance.size();
					session.put("pageCount_PPD", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_PPD", tfProfessionalProjectDeclarePerformance.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				tfProfessionalProjectDeclarePerformance = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return tfProfessionalProjectDeclarePerformance;
	}
	public void save(TfprofessionalProjectDeclarePerformance transientInstance) {
		log.debug("saving TfprofessionalProjectDeclarePerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(
			TfprofessionalProjectDeclarePerformance persistentInstance) {
		log.debug("deleting TfprofessionalProjectDeclarePerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfprofessionalProjectDeclarePerformance findById(java.lang.Integer id) {
		log.debug("getting TfprofessionalProjectDeclarePerformance instance with id: "
				+ id);
		try {
			TfprofessionalProjectDeclarePerformance instance = (TfprofessionalProjectDeclarePerformance) getSession()
					.get("com.nuaa.ec.model.TfprofessionalProjectDeclarePerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfprofessionalProjectDeclarePerformance instance) {
		log.debug("finding TfprofessionalProjectDeclarePerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfprofessionalProjectDeclarePerformance")
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
		log.debug("finding TfprofessionalProjectDeclarePerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfprofessionalProjectDeclarePerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySingleScore(Object singleScore) {
		return findByProperty(SINGLE_SCORE, singleScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TfprofessionalProjectDeclarePerformance instances");
		try {
			String queryString = "from TfprofessionalProjectDeclarePerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPaging(int currentRow,int limitRow,String condition,Teacher teacher){
		try {
			String queryString = "from TfprofessionalProjectDeclarePerformance "
					+ "where spareTire='1' "
					+ "and tfprofessionalProjectDeclareProject.spareTire='1'"
					+condition
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, teacher)
					.setFirstResult(currentRow).setMaxResults(limitRow);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition,Teacher teacher){
		try {
			String queryString = "from TfprofessionalProjectDeclarePerformance "
					+ "where spareTire='1' "
					+ "and tfprofessionalProjectDeclareProject.spareTire='1'"
					+condition
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, teacher);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateScore(String teacherId,double score,TfprofessionalProjectDeclareProject project){
		try {
			String queryString = "update TfprofessionalProjectDeclarePerformance "
					+ "set singleScore=? "
					+ "where teacher.teacherId=?"
					+ "and tfprofessionalProjectDeclareProject=? "
					+ "and spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, score).setParameter(1, teacherId).setParameter(2, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMember(TfprofessionalProjectDeclareProject project){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.singleScore) "
					+ "from TfprofessionalProjectDeclarePerformance t "
					+ "where spareTire='1' "
					+ "and tfprofessionalProjectDeclareProject=? "
					+ "and teacher.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRef(TfprofessionalProjectDeclareProject project){
		try {
			String queryString = "update TfprofessionalProjectDeclarePerformance "
					+ "set spareTire='0' "
					+ "where tfprofessionalProjectDeclareProject=? "
					+ "and spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void quitProject(Teacher teacher,TfprofessionalProjectDeclareProject project){
		try {
			String queryString = "update TfprofessionalProjectDeclarePerformance "
					+ "set spareTire='0' "
					+ "where tfprofessionalProjectDeclareProject=? "
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
	
	public boolean checkexist(TfprofessionalProjectDeclareProject project,Teacher teacher){
		try {
			String queryString = "from TfprofessionalProjectDeclarePerformance "
					+ "where spareTire='1' "
					+ "and tfprofessionalProjectDeclareProject=? "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, project).setParameter(1, teacher);
			if(queryObject.list().size()>0){
				return false;
			}else return true;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TfprofessionalProjectDeclarePerformance merge(
			TfprofessionalProjectDeclarePerformance detachedInstance) {
		log.debug("merging TfprofessionalProjectDeclarePerformance instance");
		try {
			TfprofessionalProjectDeclarePerformance result = (TfprofessionalProjectDeclarePerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfprofessionalProjectDeclarePerformance instance) {
		log.debug("attaching dirty TfprofessionalProjectDeclarePerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfprofessionalProjectDeclarePerformance instance) {
		log.debug("attaching clean TfprofessionalProjectDeclarePerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}