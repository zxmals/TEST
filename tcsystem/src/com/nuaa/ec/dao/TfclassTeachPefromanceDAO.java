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
import com.nuaa.ec.model.TeacherAndacademicWork;
import com.nuaa.ec.model.TfclassTeachPefromance;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TfclassTeachPefromance entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.nuaa.ec.model.TfclassTeachPefromance
 * @author MyEclipse Persistence Tools
 */
public class TfclassTeachPefromanceDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TfclassTeachPefromanceDAO.class);
	// property constants
	public static final String SUMTIME = "sumtime";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private List<TfclassTeachPefromance> TFClassTeachPefroList = null;
	public boolean updateCheckoutStatus(List<TfclassTeachPefromance> TfclassTeachPerfoList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TfclassTeachPerfoList.size();i++){
				session.update(TfclassTeachPerfoList.get(i));
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
	public List getTFClassTeachPefro(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		StringBuffer hqlBuffer = null;
		if (department.getDepartmentId() == null
				|| department.getDepartmentId().length() == 0) {
			/*
			 * 第一次进入的时候，不显示记录
			 */
			session.put("pageCount_CT", 0);
			session.put("recordNumber_CT", 0);
			return TFClassTeachPefroList = new ArrayList<TfclassTeachPefromance>();
		} else {
			// 查出符合条件的全部的记录
			hqlBuffer = new StringBuffer(
					"select CT from TfclassTeachPefromance CT,Tfterm TERM where CT.spareTire='1'"
							+ " and CT.checkOut='" + checkOut + "'"
							+ " and TERM.spareTire='1'"
							+ " and CT.tfclassTeachEvaluation.spareTire='1'"
							+ " and CT.teacher.spareTire='1'"
							+ " and CT.teacher.department.spareTire='1'"
							+ " and CT.teacher.department.departmentId='"+department.getDepartmentId()+"'"
							+ " and CT.termId=TERM.termId" + " and TERM.termId='"
							+ termId+"'");
			// 判断是否为分页操作
			if (!isDivided) {
				//如果不是分页操作，取出所有符合条件的记录
				TFClassTeachPefroList = this.getSession()
						.createQuery(hqlBuffer.toString()).list();
				int recordNumber=TFClassTeachPefroList.size();
				session.put("pageCount_CT", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
				session.put("recordNumber_CT", TFClassTeachPefroList.size());
			} 
			//无论是不是分页查询，都在后台进行分页操作。
			TFClassTeachPefroList = this.getSession()
					.createQuery(hqlBuffer.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		}
		return TFClassTeachPefroList;
	}

	public void save(TfclassTeachPefromance transientInstance) {
		log.debug("saving TfclassTeachPefromance instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TfclassTeachPefromance persistentInstance) {
		log.debug("deleting TfclassTeachPefromance instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TfclassTeachPefromance findById(java.lang.String id) {
		log.debug("getting TfclassTeachPefromance instance with id: " + id);
		try {
			TfclassTeachPefromance instance = (TfclassTeachPefromance) getSession()
					.get("com.nuaa.ec.model.TfclassTeachPefromance", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TfclassTeachPefromance instance) {
		log.debug("finding TfclassTeachPefromance instance by example");
		try {
			List results = getSession()
					.createCriteria("com.nuaa.ec.model.TfclassTeachPefromance")
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
		log.debug("finding TfclassTeachPefromance instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TfclassTeachPefromance as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findBySumtime(Object sumtime) {
		return findByProperty(SUMTIME, sumtime);
	}

	public List findByFinalScore(Object finalScore) {
		return findByProperty(FINAL_SCORE, finalScore);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findAll() {
		log.debug("finding all TfclassTeachPefromance instances");
		try {
			String queryString = "from TfclassTeachPefromance";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public TfclassTeachPefromance merge(TfclassTeachPefromance detachedInstance) {
		log.debug("merging TfclassTeachPefromance instance");
		try {
			TfclassTeachPefromance result = (TfclassTeachPefromance) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TfclassTeachPefromance instance) {
		log.debug("attaching dirty TfclassTeachPefromance instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TfclassTeachPefromance instance) {
		log.debug("attaching clean TfclassTeachPefromance instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}