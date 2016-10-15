package com.nuaa.ec.dao;

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

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance;
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
							+ " order by EWTB.tfenterpriseWorkstationTrainingBaseConstructionProject.projectId asc ");
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