package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TffineCourseConstructionPerformance;
import com.nuaa.ec.model.TffineCourseConstructionProject;
import com.nuaa.ec.model.TftextbookConstructionPerformance;
import com.opensymphony.xwork2.ActionContext;

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
 * TffineCourseConstructionPerformance entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TffineCourseConstructionPerformance
 * @author MyEclipse Persistence Tools
 */
public class TffineCourseConstructionPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TffineCourseConstructionPerformanceDAO.class);
	// property constants
	public static final String SINGEL_SCORE = "singelScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TffineCourseConstructionPerformance> tfFineCourseConstructionPerformance = null;
	public boolean updateCheckoutStatus(List<TffineCourseConstructionPerformance> tfFineCourseConstructionPerformanceList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<tfFineCourseConstructionPerformanceList.size();i++){
				session.update(tfFineCourseConstructionPerformanceList.get(i));
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
	public List getTffineCourseConstructionPerfList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_FCC", 0);
				session.put("recordNumber_FCC", 0);
				return tfFineCourseConstructionPerformance = new ArrayList<TffineCourseConstructionPerformance>();
			} else {
				if (checkOut.equals("4")) {
					hqlBuffer = new StringBuffer(
							"from TffineCourseConstructionPerformance FCC where FCC.spareTire='1'"
									+ " and FCC.tffineCourseConstructionProject.spareTire='1'"
									+ " and FCC.tffineCourseConstructionProject.tffineCourseConstructionLevel.spareTire='1'"
									+ " and FCC.tffineCourseConstructionProject.tfterm.spareTire='1'"
									+ " and FCC.selfUndertakeTask.spareTire='1'"
									+ " and FCC.tffineCourseConstructionProject.tfterm.termId='"+termId+"'"
									+ " and FCC.teacher.spareTire='1'"
									+ " and FCC.teacher.department.spareTire='1'"
									+ " and FCC.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by FCC.tffineCourseConstructionProject.courseId desc");
				}else {
					
					// 查出符合条件的全部的记录
					hqlBuffer = new StringBuffer(
							"from TffineCourseConstructionPerformance FCC where FCC.spareTire='1'"
									+ " and FCC.checkOut='" + checkOut + "'"
									+ " and FCC.tffineCourseConstructionProject.spareTire='1'"
									+ " and FCC.tffineCourseConstructionProject.tffineCourseConstructionLevel.spareTire='1'"
									+ " and FCC.tffineCourseConstructionProject.tfterm.spareTire='1'"
									+ " and FCC.selfUndertakeTask.spareTire='1'"
									+ " and FCC.tffineCourseConstructionProject.tfterm.termId='"+termId+"'"
									+ " and FCC.teacher.spareTire='1'"
									+ " and FCC.teacher.department.spareTire='1'"
									+ " and FCC.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by FCC.tffineCourseConstructionProject.courseId desc");
				}
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					tfFineCourseConstructionPerformance = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=tfFineCourseConstructionPerformance.size();
					session.put("pageCount_FCC", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_FCC", tfFineCourseConstructionPerformance.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				tfFineCourseConstructionPerformance = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return tfFineCourseConstructionPerformance;
	}
	public void save(TffineCourseConstructionPerformance transientInstance) {
		log.debug("saving TffineCourseConstructionPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TffineCourseConstructionPerformance persistentInstance) {
		log.debug("deleting TffineCourseConstructionPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TffineCourseConstructionPerformance findById(java.lang.Integer id) {
		log.debug("getting TffineCourseConstructionPerformance instance with id: "
				+ id);
		try {
			TffineCourseConstructionPerformance instance = (TffineCourseConstructionPerformance) getSession()
					.get("com.nuaa.ec.model.TffineCourseConstructionPerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TffineCourseConstructionPerformance instance) {
		log.debug("finding TffineCourseConstructionPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TffineCourseConstructionPerformance")
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
		log.debug("finding TffineCourseConstructionPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TffineCourseConstructionPerformance as model where model."
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

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TffineCourseConstructionPerformance instances");
		try {
			String queryString = "from TffineCourseConstructionPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPaging(int currentRow,int limitRow,String condition,Teacher teacher){
		try {
			String queryString = "from TffineCourseConstructionPerformance "
					+ "where spareTire='1' "
					+ condition
					+ "and tffineCourseConstructionProject.spareTire='1' "
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
			String queryString = "from TffineCourseConstructionPerformance "
					+ "where spareTire='1' "
					+ condition
					+ "and tffineCourseConstructionProject.spareTire='1' "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, teacher);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public boolean checkexist(TffineCourseConstructionProject project,Teacher teacher){
		try {
			String queryString = "from TffineCourseConstructionPerformance "
					+ "where spareTire='1' "
					+ "and teacher=? "
					+ "and tffineCourseConstructionProject=? ";
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
	
	public List findMember(TffineCourseConstructionProject project){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.singelScore) "
					+ " from TffineCourseConstructionPerformance t "
					+ "where spareTire='1' "
					+ "and tffineCourseConstructionProject=? "
					+ "and teacher.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRef(TffineCourseConstructionProject project){
		try {
			String queryString = "update TffineCourseConstructionPerformance "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tffineCourseConstructionProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void quitProject(Teacher teacher,TffineCourseConstructionProject project){
		try {
			String queryString = "update TffineCourseConstructionPerformance "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tffineCourseConstructionProject=? "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, project).setParameter(1, teacher);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateScore(String teacherId,double score,TffineCourseConstructionProject project){
		try {
			String queryString = "update TffineCourseConstructionPerformance "
					+ "set singelScore=? "
					+ "where spareTire='1' "
					+ "and tffineCourseConstructionProject=? "
					+ "and teacher.teacherId=?";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, score).setParameter(1, project).setParameter(2, teacherId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TffineCourseConstructionPerformance merge(
			TffineCourseConstructionPerformance detachedInstance) {
		log.debug("merging TffineCourseConstructionPerformance instance");
		try {
			TffineCourseConstructionPerformance result = (TffineCourseConstructionPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TffineCourseConstructionPerformance instance) {
		log.debug("attaching dirty TffineCourseConstructionPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TffineCourseConstructionPerformance instance) {
		log.debug("attaching clean TffineCourseConstructionPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}