package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TffamousTeacherTeamProject;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.teach.baseSet.action.TermSetAction;
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
 * TffamousTeacherTeamProject entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TffamousTeacherTeamProject
 * @author MyEclipse Persistence Tools
 */
public class TffamousTeacherTeamProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TffamousTeacherTeamProjectDAO.class);
	// property constants
	public static final String PROJECT_SUM_SCORE = "projectSumScore";
	public static final String TERM_ID = "termId";
	public static final String SPARE_TIRE = "spareTire";
	private Map<String, Object> session = ActionContext.getContext().getSession();
	
	public void save(TffamousTeacherTeamProject transientInstance) {
		log.debug("saving TffamousTeacherTeamProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TffamousTeacherTeamProject persistentInstance) {
		log.debug("deleting TffamousTeacherTeamProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TffamousTeacherTeamProject findById(java.lang.String id) {
		log.debug("getting TffamousTeacherTeamProject instance with id: " + id);
		try {
			TffamousTeacherTeamProject instance = (TffamousTeacherTeamProject) getSession()
					.get("com.nuaa.ec.model.TffamousTeacherTeamProject", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TffamousTeacherTeamProject instance) {
		log.debug("finding TffamousTeacherTeamProject instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TffamousTeacherTeamProject")
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
		log.debug("finding TffamousTeacherTeamProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TffamousTeacherTeamProject as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProjectSumScore(Object projectSumScore) {
		return findByProperty(PROJECT_SUM_SCORE, projectSumScore);
	}

	public List findByTermId(Object termId) {
		return findByProperty(TERM_ID, termId);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TffamousTeacherTeamProject instances");
		try {
			String queryString = "from TffamousTeacherTeamProject";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public void deleteBylogic(String projectId){
		try {
			String queryString = "update TffamousTeacherTeamProject "
					+ "set spareTire='0' "
					+ "where teacherTeamPerformanceId=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, projectId);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPaging(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from TffamousTeacherTeamProject "
					+ "where spareTire='1' "
					+ "and tffamousTeacherTeamRewadLevel.spareTire='1' "
					+ "and tfterm.spareTire='1' "
					+condition;
			Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow).setMaxResults(limitRow);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition){
		try {
			String queryString = "from TffamousTeacherTeamProject "
					+ "where spareTire='1' "
					+ "and tffamousTeacherTeamRewadLevel.spareTire='1' "
					+ "and tfterm.spareTire='1' "
					+condition;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public TffamousTeacherTeamProject merge(
			TffamousTeacherTeamProject detachedInstance) {
		log.debug("merging TffamousTeacherTeamProject instance");
		try {
			TffamousTeacherTeamProject result = (TffamousTeacherTeamProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TffamousTeacherTeamProject instance) {
		log.debug("attaching dirty TffamousTeacherTeamProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TffamousTeacherTeamProject instance) {
		log.debug("attaching clean TffamousTeacherTeamProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public boolean updateCheckOutStatus(
			List<TffamousTeacherTeamProject> checkoutList) {
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
		}
		return updateFlag;
	}

	public List getTFfamousTeacherTeam(int pageIndex, Integer pageSize,
			Tfterm term,String checkout, Department department, boolean isDivided) {
		// TODO Auto-generated method stub
		List<TffamousTeacherTeamProject> tffamousTeacherTeamProjectList = null;
		StringBuffer hql = null;
		try {
			if( term.getTermId() == null || term.getTermId().length() == 0){
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_TFTTPA", 0);
				session.put("recordNumber_TFTTPA", 0);
				return tffamousTeacherTeamProjectList = new ArrayList<TffamousTeacherTeamProject>();
			}else {
				if (checkout.trim().equals("4")) {
					hql = new StringBuffer(
							"from TffamousTeacherTeamProject TR where"
							+ "TR.spareTire = '1' "
							+ " and TR.tfterm.termId= '" +term.getTermId()  + "'"
							+ " and TR.tfterm.spareTire = '1' "
							+ " and TR.departmentId = '" + department.getDepartmentId() + "'"
							+ ""
							
							);
				}else {
					hql = new StringBuffer(
							"select new com.nuaa.ec.model.TffamousTeacherTeamProject(TR.teacherTeamPerformanceId,TR.tffamousTeacherTeamRewadLevel,TR.projectSumScore,TR.tfterm,TR.spareTire,teacher.teacherId,TR.checkout,TR.name,teacher.department.departmentId,teacher.teacherName)"
//							"select TR "
							+ " from TffamousTeacherTeamProject TR,Teacher teacher where "
							+ " TR.spareTire = '1' "
							+ " and TR.tfterm.termId= '" + term.getTermId()  + "'"
							+ " and TR.tfterm.spareTire = '1' "
							+ " and teacher.department.departmentId = '" + department.getDepartmentId() + "'"
							+ " and TR.checkout = '" + checkout + "'"
							+ " and TR.chargePersonId = teacher.teacherId "
							
							);
					tffamousTeacherTeamProjectList = new ArrayList<TffamousTeacherTeamProject>();
					if (!isDivided) {
						tffamousTeacherTeamProjectList = this.getSession().createQuery(hql.toString()).list();
						int recordNumber = tffamousTeacherTeamProjectList.size();
						session.put("pageCount_TFTTPA", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
						session.put("recordNumber_TFTTPA", tffamousTeacherTeamProjectList.size());
					}
					tffamousTeacherTeamProjectList = this.getSession().createQuery(hql.toString()).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
					
				}
						
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return tffamousTeacherTeamProjectList;
	}
}