package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.SelfUndertakeTask;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TffamousTeacherTeamPerformance;
import com.nuaa.ec.model.TffamousTeacherTeamProject;
import com.nuaa.ec.model.TfteachingAbilityImprovePerformance;
import com.nuaa.ec.teachingData.exportData.FamousTeacherTeamExcel;
import com.nuaa.ec.teachingData.exportData.OffCampusPracticeGuidanceExcel;
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
 * TffamousTeacherTeamPerformance entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TffamousTeacherTeamPerformance
 * @author MyEclipse Persistence Tools
 */
public class TffamousTeacherTeamPerformanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TffamousTeacherTeamPerformanceDAO.class);
	// property constants
	public static final String SINGEL_SCORE = "singelScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TffamousTeacherTeamPerformance> TFfamousTeacherTeamPefroList = null;
	/***
	 * 获取 该 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSA(String foreterm,String afterterm,Department depart){
		try {
			String queryString = "select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(FTT.singelScore),0),ISNULL(avg(FTT.singelScore),0)) "
					+ "from TffamousTeacherTeamPerformance FTT "
					+ " where FTT.spareTire='1'"
					+ " and FTT.tffamousTeacherTeamProject.spareTire='1'"
					+ " and FTT.selfUndertakeTask.spareTire='1'"
					+ " and FTT.tffamousTeacherTeamProject.tffamousTeacherTeamRewadLevel.spareTire='1'"
					+ " and FTT.tffamousTeacherTeamProject.tfterm.spareTire='1'"
					+ " and FTT.tffamousTeacherTeamProject.tfterm.termId between ? and ?"
					+ " and FTT.teacher.spareTire='1'"
					+ " and FTT.checkOut='3'"
					+ " and FTT.teacher.department.spareTire='1'"
					+ " and FTT.teacher.department=?";
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
	 *教学名师和教学团队模块的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString ="from TffamousTeacherTeamPerformance FTT "
					+ " where FTT.spareTire='1'"
					+ " and FTT.tffamousTeacherTeamProject.spareTire='1'"
					+ " and FTT.selfUndertakeTask.spareTire='1'"
					+ " and FTT.tffamousTeacherTeamProject.tffamousTeacherTeamRewadLevel.spareTire='1'"
					+ " and FTT.tffamousTeacherTeamProject.tfterm.spareTire='1'"
					+ " and FTT.tffamousTeacherTeamProject.tfterm.termId between ? and ?"
					+ " and FTT.teacher.spareTire='1'"
					+ " and FTT.teacher.department.spareTire='1'"
					+ " and FTT.teacher.department=?"
					+"  and FTT.checkOut='3' "
					+ " order by FTT.tffamousTeacherTeamProject.teacherTeamPerformanceId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					FamousTeacherTeamExcel.generateExcel(
							stringstore.famousTeacherTeam,
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
	public boolean updateCheckoutStatus(List<TffamousTeacherTeamPerformance> TffamousTeacherTeamPerfList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TffamousTeacherTeamPerfList.size();i++){
				session.update(TffamousTeacherTeamPerfList.get(i));
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
	public List getTFfamousTeacherTeamPefroList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_FTT", 0);
				session.put("recordNumber_FTT", 0);
				return TFfamousTeacherTeamPefroList = new ArrayList<TffamousTeacherTeamPerformance>();
			} else {
				if (checkOut.equals("4")) {
					hqlBuffer = new StringBuffer(
							"from TffamousTeacherTeamPerformance FTT where FTT.spareTire='1'"
									+ " and FTT.tffamousTeacherTeamProject.spareTire='1'"
									+ " and FTT.selfUndertakeTask.spareTire='1'"
									+ " and FTT.tffamousTeacherTeamProject.tffamousTeacherTeamRewadLevel.spareTire='1'"
									+ " and FTT.tffamousTeacherTeamProject.tfterm.spareTire='1'"
									+ " and FTT.tffamousTeacherTeamProject.tfterm.termId='"+termId+"'"
									+ " and FTT.teacher.spareTire='1'"
									+ " and FTT.teacher.department.spareTire='1'"
									+ " and FTT.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by FTT.tffamousTeacherTeamProject.teacherTeamPerformanceId desc");
				}else {
					
					// 查出符合条件的全部的记录
					hqlBuffer = new StringBuffer(
							"from TffamousTeacherTeamPerformance FTT where FTT.spareTire='1'"
									+ " and FTT.checkOut='" + checkOut + "'"
									+ " and FTT.tffamousTeacherTeamProject.spareTire='1'"
									+ " and FTT.selfUndertakeTask.spareTire='1'"
									+ " and FTT.tffamousTeacherTeamProject.tffamousTeacherTeamRewadLevel.spareTire='1'"
									+ " and FTT.tffamousTeacherTeamProject.tfterm.spareTire='1'"
									+ " and FTT.tffamousTeacherTeamProject.tfterm.termId='"+termId+"'"
									+ " and FTT.teacher.spareTire='1'"
									+ " and FTT.teacher.department.spareTire='1'"
									+ " and FTT.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " order by FTT.tffamousTeacherTeamProject.teacherTeamPerformanceId desc");
				}
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					TFfamousTeacherTeamPefroList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=TFfamousTeacherTeamPefroList.size();
					session.put("pageCount_FTT", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_FTT", TFfamousTeacherTeamPefroList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				TFfamousTeacherTeamPefroList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return TFfamousTeacherTeamPefroList;
	}

	public void save(TffamousTeacherTeamPerformance transientInstance) {
		log.debug("saving TffamousTeacherTeamPerformance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TffamousTeacherTeamPerformance persistentInstance) {
		log.debug("deleting TffamousTeacherTeamPerformance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TffamousTeacherTeamPerformance findById(java.lang.Integer id) {
		log.debug("getting TffamousTeacherTeamPerformance instance with id: "
				+ id);
		try {
			TffamousTeacherTeamPerformance instance = (TffamousTeacherTeamPerformance) getSession()
					.get("com.nuaa.ec.model.TffamousTeacherTeamPerformance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TffamousTeacherTeamPerformance instance) {
		log.debug("finding TffamousTeacherTeamPerformance instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TffamousTeacherTeamPerformance")
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
		log.debug("finding TffamousTeacherTeamPerformance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TffamousTeacherTeamPerformance as model where model."
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
		log.debug("finding all TffamousTeacherTeamPerformance instances");
		try {
			String queryString = "from TffamousTeacherTeamPerformance where spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public boolean checkexist(TffamousTeacherTeamProject project,Teacher teacher){
		try {
			String queryString = "from TffamousTeacherTeamPerformance "
					+ "where spareTire='1' "
					+ "and tffamousTeacherTeamProject=? "
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
	
	public List findMember(TffamousTeacherTeamProject project){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.singelScore) "
					+ "from TffamousTeacherTeamPerformance t "
					+ "where t.spareTire='1' "
					+ "and t.teacher.spareTire='1' "
					+ "and t.tffamousTeacherTeamProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, project);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRef(TffamousTeacherTeamProject ttpro){
		try {
			String queryString = "update TffamousTeacherTeamPerformance "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tffamousTeacherTeamProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, ttpro);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void quitProject(Teacher teacher,TffamousTeacherTeamProject ttpro){
		try {
			String queryString = "update TffamousTeacherTeamPerformance "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and tffamousTeacherTeamProject=? "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, ttpro).setParameter(1, teacher);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateScore(String teacherId,double score,TffamousTeacherTeamProject project){
		try {
			String queryString = "update TffamousTeacherTeamPerformance "
					+ "set singelScore=? "
					+ "where spareTire='1' "
					+ "and tffamousTeacherTeamProject=? "
					+ "and teacher.teacherId=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0,score)
					.setParameter(1, project).setParameter(2, teacherId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
//	public void updateByRef(double score,Teacher teacher,TffamousTeacherTeamPerformance teachteam){
//		try {
//			String queryString = "update TffamousTeacherTeamPerformance "
//					+ "set singelScore=?"
//					+ " where spareTire='1' "
//					+ "and teacher=? "
//					+ "and tffamousTeacherTeamProject=?";
//			Query queryObject = getSession().createQuery(queryString).setParameter(0, score)
//					.setParameter(1, teacher).setParameter(2, teachteam);
//			queryObject.executeUpdate();
//		} catch (RuntimeException re) {
//			log.error("find all failed", re);
//			throw re;
//		}
//	}
	public List findPaging(int currentRow,int limitRow,String condition,Teacher teacher){
		try {
			String queryString = "from TffamousTeacherTeamPerformance "
					+ "where spareTire='1' "
					+ "and tffamousTeacherTeamProject.spareTire='1' "
					+ "and teacher=? "
					+condition
					+ "and teacher.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow)
					.setMaxResults(limitRow).setParameter(0, teacher);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition,Teacher teacher){
		try {
			String queryString = "from TffamousTeacherTeamPerformance "
					+ "where spareTire='1' "
					+ "and tffamousTeacherTeamProject.spareTire='1' "
					+ "and teacher=? "
					+condition
					+ "and teacher.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, teacher);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TffamousTeacherTeamPerformance merge(
			TffamousTeacherTeamPerformance detachedInstance) {
		log.debug("merging TffamousTeacherTeamPerformance instance");
		try {
			TffamousTeacherTeamPerformance result = (TffamousTeacherTeamPerformance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TffamousTeacherTeamPerformance instance) {
		log.debug("attaching dirty TffamousTeacherTeamPerformance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TffamousTeacherTeamPerformance instance) {
		log.debug("attaching clean TffamousTeacherTeamPerformance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}