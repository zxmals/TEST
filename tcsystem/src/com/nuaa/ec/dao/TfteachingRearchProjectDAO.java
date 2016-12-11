package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfteachingRearchProject;
import com.nuaa.ec.model.Tfterm;
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
 * TfteachingRearchProject entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.nuaa.ec.model.TfteachingRearchProject
 * @author MyEclipse Persistence Tools
 */
public class TfteachingRearchProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfteachingRearchProjectDAO.class);
	// property constants
	public static final String PROJECT = "project";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	public static final String PROJET_SCORE = "projetScore";
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	public void save(TfteachingRearchProject transientInstance) {
		log.debug("saving TfteachingRearchProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfteachingRearchProject persistentInstance) {
		log.debug("deleting TfteachingRearchProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfteachingRearchProject findById(java.lang.String id) {
		log.debug("getting TfteachingRearchProject instance with id: " + id);
		try {
			TfteachingRearchProject instance = (TfteachingRearchProject) getSession()
					.get("com.nuaa.ec.model.TfteachingRearchProject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfteachingRearchProject instance) {
		log.debug("finding TfteachingRearchProject instance by example");
		try {
			List results = getSession()
					.createCriteria("com.nuaa.ec.model.TfteachingRearchProject")
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
		log.debug("finding TfteachingRearchProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfteachingRearchProject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProject(Object project) {
		return findByProperty(PROJECT, project);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findByProjetScore(Object projetScore) {
		return findByProperty(PROJET_SCORE, projetScore);
	}

	public List findAll() {
		log.debug("finding all TfteachingRearchProject instances");
		try {
			String queryString = "from TfteachingRearchProject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteBylogic(String projectId){
		try {
			String queryString = "update TfteachingRearchProject "
					+ "set spareTire='0' "
					+ "where spareTire='1' "
					+ "and projectId=? ";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, projectId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPaging(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from TfteachingRearchProject "
					+ "where spareTire='1' "
					+ "and tfteachingRearchEvaluation.spareTire = '1' "
					+ "and tfterm.spareTire='1' "
					+condition
					+ "and tfteachingRearchFundlevel.spareTire = '1' ";
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
			String queryString = "from TfteachingRearchProject "
					+ "where spareTire='1' "
					+ "and tfteachingRearchEvaluation.spareTire = '1' "
					+ "and tfterm.spareTire='1' "
					+condition
					+ "and tfteachingRearchFundlevel.spareTire = '1' ";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TfteachingRearchProject merge(
			TfteachingRearchProject detachedInstance) {
		log.debug("merging TfteachingRearchProject instance");
		try {
			TfteachingRearchProject result = (TfteachingRearchProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfteachingRearchProject instance) {
		log.debug("attaching dirty TfteachingRearchProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfteachingRearchProject instance) {
		log.debug("attaching clean TfteachingRearchProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public boolean updateCheckOutStatus(
			List<TfteachingRearchProject> checkoutList) {
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

	public Object getTFteachingResearchProject(int pageIndex, Integer pageSize,
			Tfterm term, String checkout, Department department, boolean isdivided) {
		// TODO Auto-generated method stub
		List<TfteachingRearchProject> tfteachingRearchProjectList = null;
		StringBuffer hql = null;
		try {
			if (term.getTermId() == null || term.getTermId().length() == 0) {
				session.put("pageCount_TFTR", 0);
				session.put("recordNumber_TFTR", 0);
				return tfteachingRearchProjectList = new ArrayList<TfteachingRearchProject>();
			}else {
				if (checkout.trim().equals("4")) {
					hql = new StringBuffer(
							"select new com.nuaa.ec.model.TfteachingRearchProject(TR.projectId,TR.tfteachingRearchEvaluation,TR.tfterm,TR.tfteachingRearchFundlevel,TR.project,TR.spareTire,TR.checkOut,TR.projetScore,teacher.teacherId,teacher.teacherName,TR.departmentId) "
							+ " from TfteachingRearchProject TR,Teacher teacher where "
							+ " TR.spareTire = '1' "
							+ " and TR.tfterm.termId='" +term.getTermId() +"'"
							+ " and TR.tfterm.spareTire='1'"
							+ " and teacher.spareTire = '1'"
							+ " and teacher.department.departmentId = '" + department.getDepartmentId() + "'"
							+ " and teacher.department.spareTire = '1'"
							+ " and TR.chargePersonId = teacher.teacherId"
							+ " order by TR.projectId desc"
							);
				}else {
					hql = new StringBuffer(
							"select new com.nuaa.ec.model.TfteachingRearchProject(TR.projectId,TR.tfteachingRearchEvaluation,TR.tfterm,TR.tfteachingRearchFundlevel,TR.project,TR.spareTire,TR.checkOut,TR.projetScore,teacher.teacherId,teacher.teacherName,TR.departmentId) "
									+ " from TfteachingRearchProject TR,Teacher teacher where "
									+ " TR.spareTire = '1' "
									+ " and TR.tfterm.termId='" +term.getTermId() +"'"
									+ " and TR.tfterm.spareTire='1'"
									+ " and teacher.spareTire = '1'"
									+ " and teacher.department.departmentId = '" + department.getDepartmentId() + "'"
									+ " and teacher.department.spareTire = '1'"
									+ " and TR.chargePersonId = teacher.teacherId"
									+ " and TR.checkOut = '" + checkout + "'"
									+ " order by TR.projectId desc"
							);
				}
				tfteachingRearchProjectList = new ArrayList<TfteachingRearchProject>();
				if (!isdivided) {
					tfteachingRearchProjectList = this.getSession().createQuery(hql.toString()).list();
					int recordNumber = tfteachingRearchProjectList.size();
					session.put("pageCount_TFTR", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_TFTR", tfteachingRearchProjectList.size());
				}
				tfteachingRearchProjectList = this.getSession().createQuery(hql.toString()).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return tfteachingRearchProjectList;
	}

	public boolean cascadeUpdateProjectMembersCheckout(
			List<TfteachingRearchProject> checkoutList, String checkout) {
		// TODO Auto-generated method stub
		boolean flag = false;
		Session session = this.getSession();
		Transaction tx = null;
		try {
			for (TfteachingRearchProject tfteachingRearchProject : checkoutList) {
				session.createQuery("update TfteachingRearchPerformance TR set TR.checkOut='" + checkout +
						"' where TR.tfteachingRearchProject.projectId = '" + tfteachingRearchProject.getProjectId() + "'"
						).executeUpdate();
			}
			tx = session.beginTransaction();
			tx.commit();
			flag = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
		return flag;
	}
}