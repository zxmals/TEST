package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingAchievementPerformance;
import com.nuaa.ec.model.TftextbookConstructionPerformance;
import com.nuaa.ec.model.TftextbookConstructionProject;
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
 * TftextbookConstructionPerformance entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TftextbookConstructionPerformance
 * @author MyEclipse Persistence Tools
 */
public class TftextbookConstructionPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TftextbookConstructionPerformanceDAO.class);
	// property constants
	public static final String SINGELL_SCORE = "singellScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TftextbookConstructionPerformance> TftextbookConstructionPerformance = null;
	public boolean updateCheckoutStatus(List<TftextbookConstructionPerformance> TftextbookConstructionPerformanceList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TftextbookConstructionPerformanceList.size();i++){
				session.update(TftextbookConstructionPerformanceList.get(i));
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
	public List getTftextbookConstructionPerfList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_TBC", 0);
				session.put("recordNumber_TBC", 0);
				return TftextbookConstructionPerformance = new ArrayList<TftextbookConstructionPerformance>();
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"from TftextbookConstructionPerformance TBC where TBC.spareTire='1'"
								+ " and TBC.checkOut='" + checkOut + "'"
								+ " and TBC.tftextbookConstructionProject.spareTire='1'"
								+ " and TBC.tftextbookConstructionProject.tftextbookConstructionTblevel.spareTire='1'"
								+ " and TBC.tftextbookConstructionProject.tfterm.spareTire='1'"
								+ " and TBC.selfUndertakeTask.spareTire='1'"
								+ " and TBC.tftextbookConstructionProject.tfterm.termId='"+termId+"'"
								+ " and TBC.teacher.spareTire='1'"
								+ " and TBC.teacher.department.spareTire='1'"
								+ " and TBC.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " order by TBC.tftextbookConstructionProject.bookId asc");
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					TftextbookConstructionPerformance = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=TftextbookConstructionPerformance.size();
					session.put("pageCount_TBC", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_TBC", TftextbookConstructionPerformance.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				TftextbookConstructionPerformance = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return TftextbookConstructionPerformance;
	}
	public void save(TftextbookConstructionPerformance transientInstance) {
		log.debug("saving TftextbookConstructionPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TftextbookConstructionPerformance persistentInstance) {
		log.debug("deleting TftextbookConstructionPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TftextbookConstructionPerformance findById(java.lang.Integer id) {
		log.debug("getting TftextbookConstructionPerformance instance with id: "
				+ id);
		try {
			TftextbookConstructionPerformance instance = (TftextbookConstructionPerformance) getSession()
					.get("com.nuaa.ec.model.TftextbookConstructionPerformance",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TftextbookConstructionPerformance instance) {
		log.debug("finding TftextbookConstructionPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TftextbookConstructionPerformance")
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
		log.debug("finding TftextbookConstructionPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TftextbookConstructionPerformance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySingellScore(Object singellScore) {
		return findByProperty(SINGELL_SCORE, singellScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TftextbookConstructionPerformance instances");
		try {
			String queryString = "from TftextbookConstructionPerformance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findPaging(int currentRow,int limitRow,String condition,Teacher teacher){
		try {
			String queryString = "from TftextbookConstructionPerformance "
					+ "where spareTire='1' "
					+ "and tftextbookConstructionProject.spareTire='1' "
					+condition
					+ "and teacher=?";
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
			String queryString = "from TftextbookConstructionPerformance "
					+ "where spareTire='1' "
					+ "and tftextbookConstructionProject.spareTire='1' "
					+condition
					+ "and teacher=?";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, teacher);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public boolean checkexist(TftextbookConstructionProject project,Teacher teacher){
		try {
			String queryString = "from TftextbookConstructionPerformance "
					+ "where spareTire='1' "
					+ "and teacher=? "
					+ "and tftextbookConstructionProject=? ";
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
	
	public List findMember(TftextbookConstructionProject project){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.singellScore) "
					+ " from TftextbookConstructionPerformance t "
					+ "where spareTire='1' "
					+ "and tftextbookConstructionProject=? "
					+ "and teacher.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRef(TftextbookConstructionProject project){
		try {
			String queryString = "update TftextbookConstructionPerformance "
					+ "set spareTire='0' "
					+ "where tftextbookConstructionProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void quitProject(Teacher teacher,TftextbookConstructionProject project){
		try {
			String queryString = "update TftextbookConstructionPerformance "
					+ "set spareTire='0' "
					+ "where tftextbookConstructionProject=? "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, project).setParameter(1, teacher);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateScore(String teacherId,double score,TftextbookConstructionProject project){
		try {
			String queryString = "update TftextbookConstructionPerformance "
					+ "set singellScore=? "
					+ "where tftextbookConstructionProject=? "
					+ "and teacher.teacherId=? "
					+ "and spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, score).setParameter(1, project)
					.setParameter(2, teacherId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TftextbookConstructionPerformance merge(
			TftextbookConstructionPerformance detachedInstance) {
		log.debug("merging TftextbookConstructionPerformance instance");
		try {
			TftextbookConstructionPerformance result = (TftextbookConstructionPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TftextbookConstructionPerformance instance) {
		log.debug("attaching dirty TftextbookConstructionPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TftextbookConstructionPerformance instance) {
		log.debug("attaching clean TftextbookConstructionPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}