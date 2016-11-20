package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingAchievementPerformance;
import com.nuaa.ec.model.TfteachingAchievementProject;
import com.nuaa.ec.model.TfteachingPaperPerformance;
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
 * TfteachingAchievementPerformance entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TfteachingAchievementPerformance
 * @author MyEclipse Persistence Tools
 */
public class TfteachingAchievementPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfteachingAchievementPerformanceDAO.class);
	// property constants
	public static final String SINGEL_SCORE = "singelScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfteachingAchievementPerformance> TfteachingAchievementPerfList = null;
	public boolean updateCheckoutStatus(List<TfteachingAchievementPerformance> TfteachingAchievementPerformanceList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TfteachingAchievementPerformanceList.size();i++){
				session.update(TfteachingAchievementPerformanceList.get(i));
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
	public List getTFteachingAchievementPefroList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_TAP", 0);
				session.put("recordNumber_TAP", 0);
				return TfteachingAchievementPerfList = new ArrayList<TfteachingAchievementPerformance>();
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"from TfteachingAchievementPerformance TAP where TAP.spareTire='1'"
								+ " and TAP.checkOut='" + checkOut + "'"
								+ " and TAP.tfteachingAchievementProject.spareTire='1'"
								+ " and TAP.tfteachingAchievementProject.tfteachingAchievementRewardLevel.spareTire='1'"
								+ " and TAP.tfteachingAchievementProject.tfterm.spareTire='1'"
								+ " and TAP.selfUndertakeTask.spareTire='1'"
								+ " and TAP.tfteachingAchievementProject.tfterm.termId='"+termId+"'"
								+ " and TAP.teacher.spareTire='1'"
								+ " and TAP.teacher.department.spareTire='1'"
								+ " and TAP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " order by TAP.tfteachingAchievementProject.projectId asc");
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					TfteachingAchievementPerfList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=TfteachingAchievementPerfList.size();
					session.put("pageCount_TAP", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_TAP", TfteachingAchievementPerfList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				TfteachingAchievementPerfList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return TfteachingAchievementPerfList;
	}
	public void save(TfteachingAchievementPerformance transientInstance) {
		log.debug("saving TfteachingAchievementPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfteachingAchievementPerformance persistentInstance) {
		log.debug("deleting TfteachingAchievementPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfteachingAchievementPerformance findById(java.lang.Integer id) {
		log.debug("getting TfteachingAchievementPerformance instance with id: "
				+ id);
		try {
			TfteachingAchievementPerformance instance = (TfteachingAchievementPerformance) getSession()
					.get("com.nuaa.ec.model.TfteachingAchievementPerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfteachingAchievementPerformance instance) {
		log.debug("finding TfteachingAchievementPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TfteachingAchievementPerformance")
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
		log.debug("finding TfteachingAchievementPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfteachingAchievementPerformance as model where model."
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
		log.debug("finding all TfteachingAchievementPerformance instances");
		try {
			String queryString = "from TfteachingAchievementPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPaging(int currentRow,int limitRow,String condition,Teacher teacher){
		try {
			String queryString = "from TfteachingAchievementPerformance "
					+ "where spareTire='1' "
					+ "and tfteachingAchievementProject.spareTire='1' "
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
			String queryString = "from TfteachingAchievementPerformance "
					+ "where spareTire='1' "
					+ "and tfteachingAchievementProject.spareTire='1' "
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
	
	public boolean checkexist(TfteachingAchievementProject project,Teacher teacher){
		try {
			String queryString = "from TfteachingAchievementPerformance "
					+ "where spareTire='1' "
					+ "and tfteachingAchievementProject=? "
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
	
	public List findMember(TfteachingAchievementProject project){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.singelScore) "
					+ " from TfteachingAchievementPerformance t "
					+ "where spareTire='1' "
					+ "and teacher.spareTire='1' "
					+ "and tfteachingAchievementProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRef(TfteachingAchievementProject project){
		try {
			String queryString = "update TfteachingAchievementPerformance "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tfteachingAchievementProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void quitProject(Teacher teacher,TfteachingAchievementProject project){
		try {
			String queryString = "update TfteachingAchievementPerformance "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tfteachingAchievementProject=? "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, project).setParameter(1,teacher);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateScore(String teacherId,double score,TfteachingAchievementProject project){
		try {
			String queryString = "update TfteachingAchievementPerformance "
					+ "set singelScore=? "
					+ "where spareTire='1'"
					+ "and teacher.teacherId=? "
					+ "and tfteachingAchievementProject=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, score).setParameter(1, teacherId)
					.setParameter(2, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TfteachingAchievementPerformance merge(
			TfteachingAchievementPerformance detachedInstance) {
		log.debug("merging TfteachingAchievementPerformance instance");
		try {
			TfteachingAchievementPerformance result = (TfteachingAchievementPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfteachingAchievementPerformance instance) {
		log.debug("attaching dirty TfteachingAchievementPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfteachingAchievementPerformance instance) {
		log.debug("attaching clean TfteachingAchievementPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}