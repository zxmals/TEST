package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.model.VateacherAndCollectiveAct;
import com.nuaa.ec.model.VateacherAndCollectiveActId;
import com.nuaa.ec.utils.stringstore;
import com.nuaa.ec.va.exportdata.VaActListExcel;
import com.opensymphony.xwork2.ActionContext;

import java.io.ByteArrayOutputStream;
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
 * VateacherAndCollectiveAct entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.VateacherAndCollectiveAct
 * @author MyEclipse Persistence Tools
 */
public class VateacherAndCollectiveActDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VateacherAndCollectiveActDAO.class);
	// property constants
	public static final String SCORE = "score";
	public static final String SPARE_TIRE = "spareTire";
	public static final String ASPARE_TIRE = "aspareTire";
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private List<VateacherAndCollectiveAct> addJoinedActList = null;
	
	public void save(VateacherAndCollectiveAct transientInstance) {
		log.debug("saving VateacherAndCollectiveAct instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public boolean pretendDelete(VateacherAndCollectiveActId persistentInstance){
		log.debug("deleting VateacherAndCollectiveAct instance");
		boolean flag = true;
		int updatestatus = 0;
		try {
			String hql = "update VateacherAndCollectiveAct  set spareTire=:deleteValue where id=:vatapid";
			Query query = getSession().createQuery(hql);
			query.setParameter("deleteValue", "0");
			query.setParameter("vatapid", persistentInstance);
			updatestatus = query.executeUpdate();
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			flag = false;
			throw re;
		}
		if(updatestatus==0)
				flag = false;
		return flag;
	}
	
	public void delete(VateacherAndCollectiveAct persistentInstance) {
		log.debug("deleting VateacherAndCollectiveAct instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VateacherAndCollectiveAct findById(
			com.nuaa.ec.model.VateacherAndCollectiveActId id) {
		log.debug("getting VateacherAndCollectiveAct instance with id: " + id);
		try {
			VateacherAndCollectiveAct instance = (VateacherAndCollectiveAct) getSession()
					.get("com.nuaa.ec.model.VateacherAndCollectiveAct", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	

	public List findByExample(VateacherAndCollectiveAct instance) {
		log.debug("finding VateacherAndCollectiveAct instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.VateacherAndCollectiveAct")
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
		log.debug("finding VateacherAndCollectiveAct instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VateacherAndCollectiveAct as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByScore(Object score) {
		return findByProperty(SCORE, score);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByAspareTire(Object aspareTire) {
		return findByProperty(ASPARE_TIRE, aspareTire);
	}

	public List findAll() {
		log.debug("finding all VateacherAndCollectiveAct instances");
		try {
			String queryString = "from VateacherAndCollectiveAct";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public List findAllByTimeLimted(String foredate,String afterdate){
		log.debug("finding all VateacherAndCollectiveAct instances By-Time-Limted");
		try {
			String queryString = "from VateacherAndCollectiveAct  as v where v.id.vacollectiveActivitiesPublish.actDate>:foredate and  v.id.vacollectiveActivitiesPublish.actDate<:afterdate and v.spareTire=:exist";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter("foredate", foredate);
			queryObject.setParameter("afterdate", afterdate);
			queryObject.setParameter("exist", "1");
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all By-Time-Limted failed", re);
			throw re;
		}
	}
	public VateacherAndCollectiveAct merge(
			VateacherAndCollectiveAct detachedInstance) {
		log.debug("merging VateacherAndCollectiveAct instance");
		try {
			VateacherAndCollectiveAct result = (VateacherAndCollectiveAct) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VateacherAndCollectiveAct instance) {
		log.debug("attaching dirty VateacherAndCollectiveAct instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VateacherAndCollectiveAct instance) {
		log.debug("attaching clean VateacherAndCollectiveAct instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public boolean updateASparetire(List<VateacherAndCollectiveAct> checkoutList) {
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

	@SuppressWarnings("unchecked")
	public List getVaAddJoinedAct(int pageIndex, int pagesize,
			String foredate, String afterdate, Department department, String checkout) {
		// TODO Auto-generated method stub
		StringBuffer hqlBuffer = null;
		try{
		if (department.getDepartmentId() == null || 
				department.getDepartmentId().length() == 0) {
			/*
			 * 第一次进入的时候，不显示记录
			 */
			
			session.put("pageCount_CT", 0);
			session.put("recordNumber_CT", 0);
			return addJoinedActList = new ArrayList<VateacherAndCollectiveAct>();
		}else {
			hqlBuffer = new StringBuffer(
					"from VateacherAndCollectiveAct VACA where VACA.spareTire='1'"
					+ " and VACA.aspareTire='" + checkout +"'"
					+ " and VACA.id.vacollectiveActivitiesPublish.spareTire='1'"
					+ " and VACA.id.teacher.spareTire='1'"
					+ " and VACA.id.teacher.department.spareTire='1'"
					+ " and VACA.id.teacher.department.departmentId='"+department.getDepartmentId()+"'"
//					+ " order by VACA.id.vacollectiveActivitiesPublish.actPubId asc " 
					);
		}
			String append = " and VACA.id.vacollectiveActivitiesPublish.actDate between ? and ? ";
			String rank = " order by VACA.id.vacollectiveActivitiesPublish.actPubId desc";
				/*
				 * 不一定有日期 要判断
				 */
			if (foredate !=null && afterdate != null && foredate.length() != 0 && afterdate.length() != 0) {
				hqlBuffer.append(append).append(rank);
				addJoinedActList = this.getSession().createQuery(hqlBuffer.toString()).setString(0, foredate)
						.setString(1, afterdate)
						.list();
			}else {
				addJoinedActList = this.getSession().createQuery(hqlBuffer.append(rank).toString()).list();
			}
			//如果不是分页操作，取出所有符合条件的记录
			int recordNumber = addJoinedActList.size();
			session.put("pageCount_CT", recordNumber%pagesize==0?(recordNumber/pagesize):(recordNumber/pagesize+1));
			session.put("recordNumber_CT", addJoinedActList.size());
			
		//无论是不是分页查询，都在后台进行分页操作。
		addJoinedActList = this.getVaAddJoinedActAfterDivide(pageIndex, pagesize, foredate, afterdate, department, checkout);
		}catch(Exception e){
			e.printStackTrace();
		}
			return addJoinedActList;
	}

	@SuppressWarnings("unchecked")
	public List getVaAddJoinedActAfterDivide(int pageIndex, Integer pageSize,
			String foredate, String afterdate, Department department, String checkout) {
		// TODO Auto-generated method stub
		List<VateacherAndCollectiveAct> list = null;
		StringBuffer hql = null;
		if (department.getDepartmentId() == null || department.getDepartmentId().length() == 0) {
			hql = new StringBuffer(
					"from VateacherAndCollectiveAct VACA where VACA.spareTire='1'"
					+ " and VACA.aspareTire='" + checkout +"'"
					+ " and VACA.id.vacollectiveActivitiesPublish.spareTire='1'"
					+ " and VACA.id.teacher.spareTire='1'"
					+ " and VACA.id.teacher.department.spareTire='1'"
					);
		}else {
			hql = new StringBuffer(
					"from VateacherAndCollectiveAct VACA where VACA.spareTire='1'"
					+ " and VACA.aspareTire='" + checkout +"'"
					+ " and VACA.id.vacollectiveActivitiesPublish.spareTire='1'"
					+ " and VACA.id.teacher.spareTire='1'"
					+ " and VACA.id.teacher.department.spareTire='1'"
					+ " and VACA.id.teacher.department.departmentId='"+department.getDepartmentId()+"'"
					);
		}
		list = new ArrayList<VateacherAndCollectiveAct>();
		String append = " and VACA.id.vacollectiveActivitiesPublish.actDate between ? and ? ";
		String rank = " order by VACA.id.vacollectiveActivitiesPublish.actPubId desc";
		
		if (foredate != null && afterdate != null && foredate.length() != 0 && afterdate.length() != 0) {
			hql.append(append).append(rank);
			list = this.getSession().createQuery(hql.append(rank).toString()).setString(0, foredate).setString(1, afterdate).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
		}else {
			list = this.getSession().createQuery(hql.append(rank).toString()).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
		}
		
		return list;
	}

	public ByteArrayOutputStream findwithexport(Department department,
			String condition, String departmentName,
			String foredate, String afterdate) {
		// TODO Auto-generated method stub
		try {
			String query = "from VateacherAndCollectiveAct VA where VA.spareTire='1' "
					+ " and VA.id.teacher.spareTire='1' "
					+ " and VA.id.teacher.department.spareTire='1'"
					+ " and VA.id.teacher.department='" + department +"'"
					+ condition
					+ " and VA.id.teacher.spareTire='1' "
					+ " order by VA.id.vacollectiveActivitiesPublish.actPubId desc ";
			Query query2 = getSession().createQuery(query);
			ByteArrayOutputStream baosStream = new ByteArrayOutputStream();
			if (query2.list().size() > 0) {
				try {
					VaActListExcel.generateExcel(stringstore.vaactList, query2.list(), departmentName, foredate, afterdate);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return baosStream;
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		
		return null;
	}
	
}
	