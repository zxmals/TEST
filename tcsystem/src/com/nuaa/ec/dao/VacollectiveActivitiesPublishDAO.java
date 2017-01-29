package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.model.VacollectiveActivitiesPublish;
import com.nuaa.ec.model.VateacherAndCollectiveAct;
import com.nuaa.ec.utils.stringstore;
import com.nuaa.ec.va.exportdata.VaActListExcel;
import com.opensymphony.xwork2.ActionContext;

import java.io.ByteArrayOutputStream;
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
 * VacollectiveActivitiesPublish entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.VacollectiveActivitiesPublish
 * @author MyEclipse Persistence Tools
 */
public class VacollectiveActivitiesPublishDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(VacollectiveActivitiesPublishDAO.class);
	// property constants
	public static final String TEACHER_ID = "teacherId";
	public static final String ACT_DATE = "actDate";
	public static final String SPARE_TIRE = "spareTire";
	public static final String ASPARE_TIRE = "aspareTire";
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private List<VacollectiveActivitiesPublish> newActPulishList = null;
	

	public void save(VacollectiveActivitiesPublish transientInstance) {
		log.debug("saving VacollectiveActivitiesPublish instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}
/**
 * 根据用户划定的时间范围，获取对应时间范围内举办的活动
 * according to the time-limted get the Acts inner time-limted
 * @param foredate 前定时间
 * @param afterdate 后界时间
 * @return
 */
	public List<VacollectiveActivitiesPublish> getJoinPublishAct(String foredate,String afterdate){
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		String hql = "from VacollectiveActivitiesPublish v where v.actDate>:foredate and v.actDate<:lastdate and v.spareTire='1' and v.aspareTire='1'";
		VacollectiveActivitiesPublish vap = null;
		List<VacollectiveActivitiesPublish> vapli = new ArrayList<VacollectiveActivitiesPublish>();
		try {
			Query query = session.createQuery(hql);
			query.setParameter("foredate", foredate);
			query.setParameter("lastdate", afterdate);
			List res = query.list();
			for(int i=0;i<res.size();i++){
				vap = new VacollectiveActivitiesPublish(
						((VacollectiveActivitiesPublish)res.get(i)).getActPubId(),
						new VacollectiveAct(
								((VacollectiveActivitiesPublish)res.get(i)).getVacollectiveAct().getActName(),
								((VacollectiveActivitiesPublish)res.get(i)).getVacollectiveAct().getAttendee()), 
							((VacollectiveActivitiesPublish)res.get(i)).getActDate(), 
							((VacollectiveActivitiesPublish)res.get(i)).getVacollectiveAct().getActType());
				vapli.add(vap);
			}
			tx.commit();
			session.close();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(vapli.size()!=0)
			return vapli;
		else
			return null;
	} 
//	public static void main(String[] args){
//		List<VacollectiveActivitiesPublish> vap = new VacollectiveActivitiesPublishDAO().getJoinPublishAct();
//		System.out.println("_____________________________________________________");
//		for(int i=0;i<vap.size();i++){
//			System.out.println(vap.get(i).getVacollectiveAct().getActName());
//		}
//	}
	public void delete(VacollectiveActivitiesPublish persistentInstance) {
		log.debug("deleting VacollectiveActivitiesPublish instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VacollectiveActivitiesPublish findById(java.lang.String id) {
		log.debug("getting VacollectiveActivitiesPublish instance with id: "
				+ id);
		try {
			VacollectiveActivitiesPublish instance = (VacollectiveActivitiesPublish) getSession()
					.get("com.nuaa.ec.model.VacollectiveActivitiesPublish", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VacollectiveActivitiesPublish instance) {
		log.debug("finding VacollectiveActivitiesPublish instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.VacollectiveActivitiesPublish")
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
		log.debug("finding VacollectiveActivitiesPublish instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VacollectiveActivitiesPublish as model where model."
					+ propertyName + "= ?  and model.spareTire = '1'";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTeacherId(Object teacherId) {
		return findByProperty(TEACHER_ID, teacherId);
	}

	public List findByActDate(Object actDate) {
		return findByProperty(ACT_DATE, actDate);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByAspareTire(Object aspareTire) {
		return findByProperty(ASPARE_TIRE, aspareTire);
	}

	public List findAll() {
		log.debug("finding all VacollectiveActivitiesPublish instances");
		try {
			String queryString = "from VacollectiveActivitiesPublish";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VacollectiveActivitiesPublish merge(
			VacollectiveActivitiesPublish detachedInstance) {
		log.debug("merging VacollectiveActivitiesPublish instance");
		try {
			VacollectiveActivitiesPublish result = (VacollectiveActivitiesPublish) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VacollectiveActivitiesPublish instance) {
		log.debug("attaching dirty VacollectiveActivitiesPublish instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VacollectiveActivitiesPublish instance) {
		log.debug("attaching clean VacollectiveActivitiesPublish instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
	public boolean updateASparetire(
			List<VacollectiveActivitiesPublish> checkoutList) {
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
	public List getNewActPublishAct(int pageIndex, int pagesize,
			ResearchLab researchLab, String checkout, boolean isDivided) {
		// TODO Auto-generated method stub
		StringBuffer hqlBuffer = null;
		try {
			if (researchLab.getResearchLabId() == null || 
				researchLab.getResearchLabId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				
				session.put("pageCount_CT", 0);
				session.put("recordNumber_CT", 0);
				return newActPulishList = new ArrayList<VacollectiveActivitiesPublish>();
			}else {
				hqlBuffer = new StringBuffer(
						"from VacollectiveActivitiesPublish VA"
						+ " where VA.aspareTire='" + checkout +"'"
						+ " and VA.spareTire = '1' "
						+ " and VA.vacollectiveAct.spareTire='1'"
						+ " and VA.vacollectiveAct.teacher.spareTire='1' "
						+ " and VA.vacollectiveAct.teacher.researchLab.researchLabId='" + researchLab.getResearchLabId() + "'"
						+ " and VA.vacollectiveAct.teacher.researchLab.spareTire='1'"
						+ " order by VA.actPubId desc"
						);
			}
			if (!isDivided) {
				newActPulishList = this.getSession().createQuery(hqlBuffer.toString()).list();
				int recordNumber = newActPulishList.size();
				session.put("pageCount_CT", recordNumber%pagesize==0?(recordNumber/pagesize):(recordNumber/pagesize+1));
				session.put("recordNumber_CT", newActPulishList.size());
			}
			//无论是不是分页查询，都在后台进行分页操作。
			newActPulishList = this.getSession()
					.createQuery(hqlBuffer.toString())
					.setFirstResult((pageIndex - 1) * pagesize)
					.setMaxResults(pagesize).list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return newActPulishList;
	}
	@SuppressWarnings("unchecked")
	public List getNewActPublishAct(int pageIndex, Integer pagesize,
			Department department, String checkout, String foredate, String afterdate) {
		// TODO Auto-generated method stub
		List<VacollectiveActivitiesPublish> list = null;
		StringBuffer hql = null;
		try{
		if (department.getDepartmentId() == null || department.getDepartmentId().length() == 0) {
			session.put("pageCount_CT", 0);
			session.put("recordNumber_CT", 0);
			return list = new ArrayList<VacollectiveActivitiesPublish>();
		}else {
			hql = new StringBuffer(
					"from VacollectiveActivitiesPublish VA"
							+ " where VA.aspareTire='" + checkout +"'"
							+ " and VA.spareTire = '1' "
							+ " and VA.vacollectiveAct.spareTire='1'"
							+ " and VA.vacollectiveAct.teacher.spareTire='1' "
							+ " and VA.vacollectiveAct.teacher.department.departmentId='" + department.getDepartmentId() + "'"
							+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
//							+ " order by VA.actPubId asc"
					);
		}
		String append = " and VA.actDate between ? and ? ";
		String rankString = " order by VA.actPubId desc ";
		
		if (foredate != null && afterdate != null &&foredate.length()!= 0 && afterdate.length()!= 0) {
			hql.append(append).append(rankString);
			list = this.getSession().createQuery(hql.append(rankString).toString()).setString(0, foredate).setString(1, afterdate).list();
		}else {
			list = this.getSession().createQuery(hql.append(rankString).toString()).list();
		}
		session.put("recordNumber_CT", list.size());
		session.put("pageCount_CT", list.size()%pagesize == 0?(list.size()/pagesize):(list.size()/pagesize + 1));
		
		list = this.getNewActPublishActAfterDivide(pageIndex, pagesize, department, checkout, foredate, afterdate);
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List getNewActPublishActAfterDivide(int pageIndex,
			Integer pagesize, Department department, String checkout,
			String foredate, String afterdate) {
		// TODO Auto-generated method stub
		List<VacollectiveActivitiesPublish> list = null;
		StringBuffer hql = null;
		try{
		if (department.getDepartmentId() == null || department.getDepartmentId().length() == 0) {
			hql = new StringBuffer(
					"from VacollectiveActivitiesPublish VA"
							+ " where VA.aspareTire='" + checkout +"'"
							+ " and VA.spareTire = '1' "
							+ " and VA.vacollectiveAct.spareTire='1'"
							+ " and VA.vacollectiveAct.teacher.spareTire='1' "
							+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
					);
		}else {
			hql = new StringBuffer(
					"from VacollectiveActivitiesPublish VA"
							+ " where VA.aspareTire='" + checkout +"'"
							+ " and VA.spareTire = '1' "
							+ " and VA.vacollectiveAct.spareTire='1'"
							+ " and VA.vacollectiveAct.teacher.spareTire='1' "
							+ " and VA.vacollectiveAct.teacher.department.departmentId='" + department.getDepartmentId() + "'"
							+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
					);
		}
		String append = " and VA.actDate between ? and ? ";
		String rankString = " order by VA.actPubId desc ";
		
		list = new ArrayList<VacollectiveActivitiesPublish>();
		if (foredate != null && afterdate != null &&foredate.length()!= 0 && afterdate.length()!= 0) {
			list = this.getSession().createQuery(hql.append(append).append(rankString).toString())
					.setString(0, foredate).setString(1, afterdate)
					.setFirstResult((pageIndex - 1) * pagesize).setMaxResults(pagesize).list();
		}else {
			list = this.getSession().createQuery(hql.append(rankString).toString()).setFirstResult((pageIndex - 1) * pagesize).setMaxResults(pagesize).list();
		}

		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public ByteArrayOutputStream findwithexport(Department department,
			String actType, String departmentName,
			String foredate, String afterdate) throws Exception {
		// TODO Auto-generated method stub
		String query ;
		try {
			if (actType.equals("0")) {
				query = "from VacollectiveActivitiesPublish VA where VA.spareTire='1' "
						+ " and VA.aspareTire='1' " 
						+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
						+ " and VA.vacollectiveAct.teacher.department.departmentId='" + department.getDepartmentId() +"'"
						+ " and VA.vacollectiveAct.spareTire='1'"
						+ " and VA.actDate >= '" + foredate +"'"
						+ " and VA.actDate <= '" + afterdate + "'"
						+ " and VA.vacollectiveAct.teacher.spareTire='1' "
						+ " order by VA.actPubId desc ";
			}else {
				query = "from VacollectiveActivitiesPublish VA where VA.spareTire='1' "
						+ " and VA.aspareTire='1' " 
						+ " and VA.vacollectiveAct.actType ='" + actType +"'"
						+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
						+ " and VA.vacollectiveAct.teacher.department.departmentId='" + department.getDepartmentId() +"'"
						+ " and VA.vacollectiveAct.spareTire='1'"
						+ " and VA.actDate >= '" + foredate +"'"
						+ " and VA.actDate <= '" + afterdate + "'"
						+ " and VA.vacollectiveAct.teacher.spareTire='1' "
						+ " order by VA.actPubId desc ";
				
			}
			Query query2 = getSession().createQuery(query);
			ByteArrayOutputStream baosStream = new ByteArrayOutputStream();
			if (query2.list().size() > 0) {
				try {
					VaActListExcel.generateExcel(stringstore.vaactList, query2.list(), departmentName, foredate, afterdate).write(baosStream);;
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				return baosStream;
			}else {
				return null;
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	public Object getNewActPublishAct(Department department, String vaacttype,
			String foredate, String afterdate) {
		// TODO Auto-generated method stub
		List<VacollectiveActivitiesPublish> list = null;
		String query ;
		if (vaacttype.equals("0")) {
			query = "from VacollectiveActivitiesPublish VA where VA.spareTire='1' "
					+ " and VA.aspareTire='1' " 
					+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
					+ " and VA.vacollectiveAct.teacher.department.departmentId='" + department.getDepartmentId() +"'"
					+ " and VA.vacollectiveAct.spareTire='1'"
					+ " and VA.actDate >= '" + foredate +"'"
					+ " and VA.actDate <= '" + afterdate + "'"
					+ " and VA.vacollectiveAct.teacher.spareTire='1' "
					+ " order by VA.actPubId desc ";
		}else {
			query = "from VacollectiveActivitiesPublish VA where VA.spareTire='1' "
					+ " and VA.aspareTire='1' " 
					+ " and VA.vacollectiveAct.actType ='" + vaacttype +"'"
					+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
					+ " and VA.vacollectiveAct.teacher.department.departmentId='" + department.getDepartmentId() +"'"
					+ " and VA.vacollectiveAct.spareTire='1'"
					+ " and VA.actDate >= '" + foredate +"'"
					+ " and VA.actDate <= '" + afterdate + "'"
					+ " and VA.vacollectiveAct.teacher.spareTire='1' "
					+ " order by VA.actPubId desc ";
		}
		list = new ArrayList<VacollectiveActivitiesPublish>();
		list = this.getSession().createQuery(query).list();
		return list;
	}
	public List findAll(int currentrow, int pagesize, String generateQueryCondition) {
		// TODO Auto-generated method stub
		String queryString = "from VacollectiveActivitiesPublish VA where VA.spareTire='1' "
				+ " and VA.aspareTire='1' " 
				+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
				+ " and VA.vacollectiveAct.spareTire='1'"
				+ " and VA.vacollectiveAct.teacher.spareTire='1' "
				+ generateQueryCondition
				+ " order by VA.actPubId desc ";
		Query query = getSession().createQuery(queryString).setFirstResult(currentrow).setMaxResults(pagesize);
		return query.list();
	}
	public int getRows(String generateQueryCondition) {
		// TODO Auto-generated method stub
		String queryString = "from VacollectiveActivitiesPublish VA where VA.spareTire='1' "
				+ " and VA.aspareTire='1' " 
				+ " and VA.vacollectiveAct.teacher.department.spareTire='1'"
				+ " and VA.vacollectiveAct.spareTire='1'"
				+ " and VA.vacollectiveAct.teacher.spareTire='1' "
				+ generateQueryCondition
				+ " order by VA.actPubId desc ";
		Query query = getSession().createQuery(queryString);
		return query.list().size();
	}
	
}