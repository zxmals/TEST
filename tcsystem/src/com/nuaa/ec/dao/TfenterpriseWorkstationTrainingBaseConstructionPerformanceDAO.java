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
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance;
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionProject;
import com.nuaa.ec.teachingData.exportData.EnterpriseWorkStationExcel;
import com.nuaa.ec.teachingData.exportData.ProfessionalProjectDeclareExcel;
import com.nuaa.ec.utils.Statistics_asist;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfenterpriseWorkstationTrainingBaseConstructionPerformance entities.
 * Transaction control of the save(), update() and delete() operations can
 * directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance
 * @author MyEclipse Persistence Tools
 */
public class TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO
		extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO.class);
	// property constants
	public static final String SINGLE_SCORE = "singleScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private List<TfenterpriseWorkstationTrainingBaseConstructionPerformance> Tf_EN_WTB_CONS_PERF_List = null;
	
	/***
	 * 获取 该 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSA(String foreterm,String afterterm,Department depart){
		try {
			String queryString = "select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(EWTB.singleScore),0),ISNULL(avg(EWTB.singleScore),0)) "
					+ "from TfenterpriseWorkstationTrainingBaseConstructionPerformance EWTB where EWTB.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.termId between ? and ?"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfenterpriseWorkstationTrainingbaseConstructionLevel.spareTire='1'"
					+ " and EWTB.selfUndertakeTask.spareTire='1'"
					+ " and EWTB.teacher.spareTire='1'"
					+ " and EWTB.checkOut='3'"
					+ " and EWTB.teacher.department.spareTire='1'"
					+ " and EWTB.teacher.department=?";
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
	 * 获取 单个教师  统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSAperson(String foreterm,String afterterm,String teacherId){
		try {
			StringBuffer queryString = new StringBuffer();
			queryString.append("select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(EWTB.singleScore),0),ISNULL(avg(EWTB.singleScore),0)) "
					+ "from TfenterpriseWorkstationTrainingBaseConstructionPerformance EWTB where EWTB.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.termId between ? and ?"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfenterpriseWorkstationTrainingbaseConstructionLevel.spareTire='1'"
					+ " and EWTB.selfUndertakeTask.spareTire='1'"
					+ " and EWTB.teacher.spareTire='1'"
					+ " and EWTB.checkOut='3'"
					+ " and EWTB.teacher.department.spareTire='1'");
//					+ " and EWTB.teacher.department=?";
			if(null!=teacherId&&!"".equals(teacherId.trim())){
				queryString.append(" and EWTB.teacher.teacherId like %"+teacherId.trim()+"% ");
			}
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
	 *企业工作站和联合培养基地的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString ="from TfenterpriseWorkstationTrainingBaseConstructionPerformance EWTB where EWTB.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.spareTire='1'"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.termId between ? and ?"
					+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfenterpriseWorkstationTrainingbaseConstructionLevel.spareTire='1'"
					+ " and EWTB.selfUndertakeTask.spareTire='1'"
					+ " and EWTB.teacher.spareTire='1'"
					+ " and EWTB.teacher.department.spareTire='1'"
					+ " and EWTB.teacher.department=?"
					+"  and EWTB.checkOut='3' "
					+ " order by EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.projectId desc ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					EnterpriseWorkStationExcel.generateExcel(
							stringstore.enterpriseWorkstation,
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
	 * function:audit
	 * @param TfDTGPerfoList
	 * @return boolean
	 */
	public boolean updateCheckoutStatus(List<TfenterpriseWorkstationTrainingBaseConstructionPerformance> Tf_EN_WTB_CONS_PERF_List){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<Tf_EN_WTB_CONS_PERF_List.size();i++){
				session.update(Tf_EN_WTB_CONS_PERF_List.get(i));
			}
			tx=session.beginTransaction();
			tx.commit();
			updateFlag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return updateFlag;
	}
	/**
	 * function：取出符合条件的信息
	 * 
	 * @param transientInstance
	 */
	@SuppressWarnings("unchecked")
	public List<TfenterpriseWorkstationTrainingBaseConstructionPerformance> getTf_EN_WTB_Cons_PERF(
			int pageIndex, int pageSize, String termId, Department department,
			String checkOut, boolean isDivided) {
		StringBuffer hqlBuffer = null;
		if (department.getDepartmentId() == null
				|| department.getDepartmentId().length() == 0) {
			/*
			 * 第一次进入的时候，不显示记录
			 */
			session.put("pageCount_EWTB", 0);
			session.put("recordNumber_EWTB", 0);
			return Tf_EN_WTB_CONS_PERF_List = new ArrayList<TfenterpriseWorkstationTrainingBaseConstructionPerformance>();
		} else {
			if (checkOut.equals("4")) {
				hqlBuffer = new StringBuffer(
						"from TfenterpriseWorkstationTrainingBaseConstructionPerformance EWTB where EWTB.spareTire='1'"
								+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.spareTire='1'"
								+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.spareTire='1'"
								+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.termId='"+termId+"'"
								+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfenterpriseWorkstationTrainingbaseConstructionLevel.spareTire='1'"
								+ " and EWTB.selfUndertakeTask.spareTire='1'"
								+ " and EWTB.teacher.spareTire='1'"
								+ " and EWTB.teacher.department.spareTire='1'"
								+ " and EWTB.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " order by EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.projectId desc ");
			}else {
				
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"from TfenterpriseWorkstationTrainingBaseConstructionPerformance EWTB where EWTB.spareTire='1'"
								+ " and EWTB.checkOut='" + checkOut + "'"
								+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.spareTire='1'"
								+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.spareTire='1'"
								+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfterm.termId='"+termId+"'"
								+ " and EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.tfenterpriseWorkstationTrainingbaseConstructionLevel.spareTire='1'"
								+ " and EWTB.selfUndertakeTask.spareTire='1'"
								+ " and EWTB.teacher.spareTire='1'"
								+ " and EWTB.teacher.department.spareTire='1'"
								+ " and EWTB.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " order by EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.projectId desc ");
			}
			// 判断是否为分页操作
			if (!isDivided) {
				//如果不是分页操作，取出所有符合条件的记录
				Tf_EN_WTB_CONS_PERF_List = this.getSession()
						.createQuery(hqlBuffer.toString()).list();
				int recordNumber=Tf_EN_WTB_CONS_PERF_List.size();
				session.put("pageCount_EWTB", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
				session.put("recordNumber_EWTB", Tf_EN_WTB_CONS_PERF_List.size());
			} 
			//无论是不是分页查询，都在后台进行分页操作。
			Tf_EN_WTB_CONS_PERF_List = this.getSession()
					.createQuery(hqlBuffer.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		}
		return Tf_EN_WTB_CONS_PERF_List;
	}

	public void save(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance transientInstance) {
		log.debug("saving TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance persistentInstance) {
		log.debug("deleting TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfenterpriseWorkstationTrainingBaseConstructionPerformance findById(
			java.lang.Integer id) {
		log.debug("getting TfenterpriseWorkstationTrainingBaseConstructionPerformance instance with id: "
				+ id);
		try {
			TfenterpriseWorkstationTrainingBaseConstructionPerformance instance = (TfenterpriseWorkstationTrainingBaseConstructionPerformance) getSession()
					.get("com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance instance) {
		log.debug("finding TfenterpriseWorkstationTrainingBaseConstructionPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance")
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
		log.debug("finding TfenterpriseWorkstationTrainingBaseConstructionPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionPerformance as model where model."
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
		log.debug("finding all TfenterpriseWorkstationTrainingBaseConstructionPerformance instances");
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPaging(int currentRow,int limitRow,String condition,Teacher teacher){
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionPerformance "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingBaseConstructionProject.spareTire='1' "
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
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionPerformance "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingBaseConstructionProject.spareTire='1' "
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
	
	public boolean checkexist(TfenterpriseWorkstationTrainingBaseConstructionProject project,Teacher teacher){
		try {
			String queryString = "from TfenterpriseWorkstationTrainingBaseConstructionPerformance "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingBaseConstructionProject=? "
					+ "and teacher=?";
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
	
	public List findMember(TfenterpriseWorkstationTrainingBaseConstructionProject project){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.singleScore) "
					+ " from TfenterpriseWorkstationTrainingBaseConstructionPerformance t "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingBaseConstructionProject=? "
					+ "and teacher.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRef(TfenterpriseWorkstationTrainingBaseConstructionProject project){
		try {
			String queryString = "update TfenterpriseWorkstationTrainingBaseConstructionPerformance "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingBaseConstructionProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void quitProject(Teacher teacher,TfenterpriseWorkstationTrainingBaseConstructionProject project){
		try {
			String queryString = "update TfenterpriseWorkstationTrainingBaseConstructionPerformance "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingBaseConstructionProject=? "
					+ "and teacher=?";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, project).setParameter(1, teacher);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateScore(String teacherId,double score,TfenterpriseWorkstationTrainingBaseConstructionProject project){
		try {
			String queryString = "update TfenterpriseWorkstationTrainingBaseConstructionPerformance "
					+ "set singleScore=? "
					+ "where spareTire='1' "
					+ "and tfenterpriseWorkstationTrainingBaseConstructionProject=? "
					+ "and teacher.teacherId=?";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(1, project).setParameter(2, teacherId).setParameter(0, score);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		
	}
	
	public TfenterpriseWorkstationTrainingBaseConstructionPerformance merge(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance detachedInstance) {
		log.debug("merging TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			TfenterpriseWorkstationTrainingBaseConstructionPerformance result = (TfenterpriseWorkstationTrainingBaseConstructionPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance instance) {
		log.debug("attaching dirty TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(
			TfenterpriseWorkstationTrainingBaseConstructionPerformance instance) {
		log.debug("attaching clean TfenterpriseWorkstationTrainingBaseConstructionPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}