package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TffineCourseConstructionProject;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.model.TftextbookConstructionProject;
import com.opensymphony.xwork2.ActionContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * TffineCourseConstructionProject entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TffineCourseConstructionProject
 * @author MyEclipse Persistence Tools
 */
public class TffineCourseConstructionProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TffineCourseConstructionProjectDAO.class);
	// property constants
	public static final String COURSE_NAME = "courseName";
	public static final String COOPERATOR = "cooperator";
	public static final String PROJECT_SUM_SCORE = "projectSumScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String, Object> session = ActionContext.getContext().getSession();
	public void save(TffineCourseConstructionProject transientInstance) {
		log.debug("saving TffineCourseConstructionProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TffineCourseConstructionProject persistentInstance) {
		log.debug("deleting TffineCourseConstructionProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TffineCourseConstructionProject findById(java.lang.String id) {
		log.debug("getting TffineCourseConstructionProject instance with id: "
				+ id);
		try {
			TffineCourseConstructionProject instance = (TffineCourseConstructionProject) getSession()
					.get("com.nuaa.ec.model.TffineCourseConstructionProject",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TffineCourseConstructionProject instance) {
		log.debug("finding TffineCourseConstructionProject instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TffineCourseConstructionProject")
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
		log.debug("finding TffineCourseConstructionProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TffineCourseConstructionProject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCourseName(Object courseName) {
		return findByProperty(COURSE_NAME, courseName);
	}

	public List findByCooperator(Object cooperator) {
		return findByProperty(COOPERATOR, cooperator);
	}

	public List findByProjectSumScore(Object projectSumScore) {
		return findByProperty(PROJECT_SUM_SCORE, projectSumScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findAll() {
		log.debug("finding all TffineCourseConstructionProject instances");
		try {
			String queryString = "from TffineCourseConstructionProject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteBylogic(String projectId){
		try {
			String queryString = "update TffineCourseConstructionProject "
					+ "set spareTire='0' "
					+ "where courseId=?";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, projectId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}	
	}
	
	public List findPaging(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from TffineCourseConstructionProject "
					+ "where spareTire='1' "
					+ "and tffineCourseConstructionLevel.spareTire='1' "
					+condition
					+ "and tfterm.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString)
					.setFirstResult(currentRow).setMaxResults(limitRow);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition){
		try {
			String queryString = "from TffineCourseConstructionProject "
					+ "where spareTire='1' "
					+ "and tffineCourseConstructionLevel.spareTire='1' "
					+condition
					+ "and tfterm.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TffineCourseConstructionProject merge(
			TffineCourseConstructionProject detachedInstance) {
		log.debug("merging TffineCourseConstructionProject instance");
		try {
			TffineCourseConstructionProject result = (TffineCourseConstructionProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TffineCourseConstructionProject instance) {
		log.debug("attaching dirty TffineCourseConstructionProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TffineCourseConstructionProject instance) {
		log.debug("attaching clean TffineCourseConstructionProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public boolean updateCheckOutStatus(
			List<TffineCourseConstructionProject> checkoutList) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		
		try {
			for (int i = 0; i < checkoutList.size(); i++) {
				session.update(checkoutList.get(i));
			}
			tx = session.beginTransaction();
			tx.commit();
			updateFlag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return updateFlag;
	}

	public Object getTFfinecourseConstructionProject(int pageIndex,
			Integer pagesize, Tfterm term, String checkout,
			Department department, boolean isdivided) {
		// TODO Auto-generated method stub
		List<TffineCourseConstructionProject> tffineCourseConstructionProjectList = null;
		StringBuffer hql = null;
		try {
			if (term.getTermId() == null || term.getTermId().length() == 0) {
				session.put("pageCount_TFTR", 0);
				session.put("recordNumber_TFTR", 0);
				return tffineCourseConstructionProjectList = new ArrayList<TffineCourseConstructionProject>();
			}else {
				if (checkout.trim().equals("4")) {
					hql = new StringBuffer(
							"select new com.nuaa.ec.model.TffineCourseConstructionProject(TR.courseId,TR.tffineCourseConstructionLevel,TR.tfterm,TR.courseName,TR.cooperator,TR.projectSumScore,TR.spareTire,TR.checkOut,teacher.teacherId,teacher.teacherName,TR.departmentId) "
									+ " from TffineCourseConstructionProject TR,Teacher teacher where "
									+ " TR.spareTire = '1' "
									+ " and TR.tfterm.termId='" +term.getTermId() +"'"
									+ " and TR.tfterm.spareTire='1'"
									+ " and teacher.spareTire = '1'"
									+ " and teacher.department.departmentId = '" + department.getDepartmentId() + "'"
									+ " and teacher.department.spareTire = '1'"
									+ " and TR.chargePersonId = teacher.teacherId"
									+ " order by TR.courseId desc"
							);
				}else {
					hql = new StringBuffer(
							"select new com.nuaa.ec.model.TffineCourseConstructionProject(TR.courseId,TR.tffineCourseConstructionLevel,TR.tfterm,TR.courseName,TR.cooperator,TR.projectSumScore,TR.spareTire,TR.checkOut,teacher.teacherId,teacher.teacherName,TR.departmentId) "
									+ " from TffineCourseConstructionProject TR,Teacher teacher where "
									+ " TR.spareTire = '1' "
									+ " and TR.tfterm.termId='" +term.getTermId() +"'"
									+ " and TR.tfterm.spareTire='1'"
									+ " and teacher.spareTire = '1'"
									+ " and teacher.department.departmentId = '" + department.getDepartmentId() + "'"
									+ " and teacher.department.spareTire = '1'"
									+ " and TR.chargePersonId = teacher.teacherId"
									+ " and TR.checkOut = '" + checkout + "'"
									+ " order by TR.courseId desc"
							);
				}
				tffineCourseConstructionProjectList = new ArrayList<TffineCourseConstructionProject>();
				if (!isdivided) {
					tffineCourseConstructionProjectList = this.getSession().createQuery(hql.toString()).list();
					int recordNumber = tffineCourseConstructionProjectList.size();
					session.put("pageCount_TFTR", recordNumber%pagesize==0?(recordNumber/pagesize):(recordNumber/pagesize+1));
					session.put("recordNumber_TFTR", tffineCourseConstructionProjectList.size());
				}
				tffineCourseConstructionProjectList = this.getSession().createQuery(hql.toString()).setFirstResult((pageIndex - 1) * pagesize).setMaxResults(pagesize).list();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return tffineCourseConstructionProjectList;
	}

	public boolean cascadeUpdateProjectMembersCheckout(
			List<TffineCourseConstructionProject> checkoutList, String checkout) {
		// TODO Auto-generated method stub
		Session session = this.getSession();
		boolean flag = false;
		Transaction txTransaction = null;
		try {
			for (TffineCourseConstructionProject tffineCourseConstructionProject : checkoutList) {
				
				String sqlString = "update TffineCourseConstructionPerformance TR set TR.checkOut='" + checkout +"' where TR.tffineCourseConstructionProject.courseId = '" + tffineCourseConstructionProject.getCourseId() + "'"; 
				session.createQuery(sqlString).executeUpdate();
				
			}
			txTransaction = session.beginTransaction();
			txTransaction.commit();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			txTransaction.rollback();
		}
		return flag;
	}
}