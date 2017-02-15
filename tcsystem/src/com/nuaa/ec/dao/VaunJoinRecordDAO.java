package com.nuaa.ec.dao;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndacademicWork;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.model.VaunJoinRecord;
import com.nuaa.ec.utils.NumberFormatUtil;
import com.nuaa.ec.utils.StoreData;
import com.nuaa.ec.utils.stringstore;
import com.nuaa.ec.va.exportdata.TeacherJoinedData;
import com.nuaa.ec.va.exportdata.UnjoinedActData;
import com.nuaa.ec.va.exportdata.VaActListExcel;
import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.jdbc.connections.spi.ConnectionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * VaunJoinRecord entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.nuaa.ec.model.VaunJoinRecord
 * @author MyEclipse Persistence Tools
 */
public class VaunJoinRecordDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(VaunJoinRecordDAO.class);
	// property constants
	public static final String TEACHER_ID = "teacherId";
	public static final String ACT_ID = "actId";
	public static final String UNJOINREASON = "unjoinreason";
	public static final String LEAVEREQOBTAIN = "leavereqobtain";
	public static final String RESULTSCORE = "resultscore";
	public static final String SPARETIRE = "sparetire";
	public static final String ASPARETIRE = "asparetire";
	private TeacherDAO teacherDAO = new TeacherDAO();
	private Map<String, Object> teacherMap = StoreData.getTeachertranslate();
	private Map<String, Object> session = ActionContext.getContext().getSession();

	public void save(VaunJoinRecord transientInstance) {
		log.debug("saving VaunJoinRecord instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(VaunJoinRecord persistentInstance) {
		log.debug("deleting VaunJoinRecord instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public VaunJoinRecord findById(java.lang.String id) {
		log.debug("getting VaunJoinRecord instance with id: " + id);
		try {
			VaunJoinRecord instance = (VaunJoinRecord) getSession().get("com.nuaa.ec.model.VaunJoinRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(VaunJoinRecord instance) {
		log.debug("finding VaunJoinRecord instance by example");
		try {
			List results = getSession().createCriteria("com.nuaa.ec.model.VaunJoinRecord")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<VaunJoinRecord> findByTimeLimted(String foredate, String afterdate, String teacherId) {
		List<VaunJoinRecord> vaunli = new ArrayList<VaunJoinRecord>();
		VaunJoinRecord vaun = null;
		String sql = "SELECT VACollectiveAct.ActID,VACollectiveAct.ActName,VACollectiveActivitiesPublish.ActDate, VACollectiveAct.Attendee,VAUnJoinRecord.unjoinreason,VAUnJoinRecord.leavereqobtain, VAUnJoinRecord.resultscore,VAUnJoinRecord.Asparetire,VAUnJoinRecord.unjoinID  FROM (VACollectiveAct INNER JOIN VACollectiveActivitiesPublish  ON VACollectiveAct.ActType = '1'/*制定活动类型为规定性集体活动*/   /*活动一经发布，并且所有人都参与了，即使活动本身被取消，绩效还算，仍然可以通过添加该活动的参与记录获得相应的绩效分*/   AND VACollectiveAct.ActID = VACollectiveActivitiesPublish.ActID  AND VACollectiveActivitiesPublish.SpareTire = '1'   AND VACollectiveActivitiesPublish.ActDate BETWEEN  ?  AND  ?  AND VACollectiveActivitiesPublish.ActPubID IN( 	SELECT VACollectiveActivitiesPublish.ActPubID   	FROM VACollectiveActivitiesPublish   	WHERE ActPubID NOT IN(  		SELECT VACollectiveActivitiesPublish.ActPubID		FROM VATeacherAndCollectiveAct,VACollectiveActivitiesPublish		WHERE VATeacherAndCollectiveAct.TeacherID = ?	 AND VACollectiveActivitiesPublish.ActPubID = VATeacherAndCollectiveAct.ActPubID) ))LEFT JOIN VAUnJoinRecord   ON VACollectiveAct.ActID = VAUnJoinRecord.ActID  AND VAUnJoinRecord.Sparetire = '1'";
		PreparedStatement ps = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, foredate);
			ps.setString(2, afterdate);
			ps.setString(3, teacherId);
			rs = ps.executeQuery();
			while (rs.next()) {
				vaun = new VaunJoinRecord(rs.getString("unjoinID"), teacherId, rs.getString("ActID"),
						rs.getString("ActName"), rs.getString("ActDate"), rs.getString("Attendee"),
						rs.getString("unjoinreason"), rs.getString("leavereqobtain"), rs.getDouble("resultscore"),
						rs.getString("Asparetire"));
				vaunli.add(vaun);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeSqlAttr(ps, rs);
		}
		if (vaunli.size() != 0)
			return vaunli;
		else
			return null;
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding VaunJoinRecord instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from VaunJoinRecord as model where model." + propertyName
					+ "= ? and model.sparetire = '1'";
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

	public List findByActId(Object actId) {
		return findByProperty(ACT_ID, actId);
	}

	public List findByUnjoinreason(Object unjoinreason) {
		return findByProperty(UNJOINREASON, unjoinreason);
	}

	public List findByLeavereqobtain(Object leavereqobtain) {
		return findByProperty(LEAVEREQOBTAIN, leavereqobtain);
	}

	public List findByResultscore(Object resultscore) {
		return findByProperty(RESULTSCORE, resultscore);
	}

	public List findBySparetire(Object sparetire) {
		return findByProperty(SPARETIRE, sparetire);
	}

	public List findByAsparetire(Object asparetire) {
		return findByProperty(ASPARETIRE, asparetire);
	}

	public List findAll() {
		log.debug("finding all VaunJoinRecord instances");
		try {
			String queryString = "from VaunJoinRecord where spareTire='1'";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public VaunJoinRecord merge(VaunJoinRecord detachedInstance) {
		log.debug("merging VaunJoinRecord instance");
		try {
			VaunJoinRecord result = (VaunJoinRecord) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(VaunJoinRecord instance) {
		log.debug("attaching dirty VaunJoinRecord instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(VaunJoinRecord instance) {
		log.debug("attaching clean VaunJoinRecord instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public boolean updateCheckoutStatus(List<VaunJoinRecord> checkoutList) {
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return updateFlag;
	}

	@SuppressWarnings("unchecked")
	public List<VaunJoinRecord> getUnjoinedActListAfterDivide(int pageIndex, Integer pageSize, String foredate,
			String afterdate, Department department, String checkOut) {
		// TODO Auto-generated method stub
		List<VaunJoinRecord> list = null;
		StringBuffer hql = null;
		if (department.getDepartmentId() == null || department.getDepartmentId().length() == 0) {
			hql = new StringBuffer(
					"select new com.nuaa.ec.model.VaunJoinRecord(UJ.unjoinId,UJ.teacherId,VA.actId,VA.actName,UJ.actDate,VA.attendee,UJ.unjoinreason,UJ.leavereqobtain,UJ.resultscore,UJ.sparetire,UJ.asparetire,T.teacherName) "
							+ " from VaunJoinRecord UJ,VacollectiveAct VA,Teacher T "
							+ " where UJ.sparetire ='1' "
							+ " and UJ.asparetire = '" + checkOut + "'"
							+ " and VA.spareTire ='1' "
							+ " and UJ.teacherId = T.teacherId"
							+ " and VA.actId = UJ.actId "
							+ " and T.spareTire='1'"
			);
		} else {
			// 查出符合条件的全部的记录
			hql = new StringBuffer(
					"select new com.nuaa.ec.model.VaunJoinRecord(UJ.unjoinId,UJ.teacherId,VA.actId,VA.actName,UJ.actDate,VA.attendee,UJ.unjoinreason,UJ.leavereqobtain,UJ.resultscore,UJ.sparetire,UJ.asparetire,T.teacherName) "
							+ " from VaunJoinRecord UJ,VacollectiveAct VA,Teacher T "
							+ " where UJ.sparetire ='1' "
							+ " and UJ.asparetire = '" + checkOut + "'"
							+ " and VA.spareTire ='1' "
							+ " and UJ.teacherId = T.teacherId"
							+ " and VA.actId = UJ.actId "
							+ " and T.spareTire='1'"
							+ " and T.department.spareTire='1'"
							+ " and T.department.departmentId='" + department.getDepartmentId() + "'");
		}
		list = new ArrayList<VaunJoinRecord>();
		String append = " and UJ.actDate >= ? and UJ.actDate <= ? ";
		String rank = " order by UJ.unjoinId desc";

		if (foredate != null && afterdate != null && foredate.length() != 0 && afterdate.length() != 0) {
			// 判断日期范围限制
			hql.append(append).append(rank);
			list = this.getSession().createQuery(hql.append(rank).toString()).setString(0, foredate)
					.setString(1, afterdate).setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
		} else {
			list = this.getSession().createQuery(hql.append(rank).toString())
					.setFirstResult((pageIndex - 1) * pageSize).setMaxResults(pageSize).list();
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List findAllWithCondition(int pageIndex, Integer pageSize, String foredate, String afterdate,
			Department department, String checkOut) {
		// TODO Auto-generated method stub
		StringBuffer hql = null;
		List<VaunJoinRecord> list = new ArrayList<VaunJoinRecord>();
		if (department.getDepartmentId() == null || department.getDepartmentId().length() == 0) {
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据， 所以不显示数据
			 */
			session.put("recordNumber_UA", 0);
			session.put("pageCount_UA", 0);
			return list;
		} else {
//			hql = new StringBuffer(
//					" select new com.nuaa.ec.model.VaunJoinRecord(UJ.unjoinId,UJ.teacherId,VA.actId,VA.actName, VP.actDate,VA.attendee,UJ.unjoinreason,UJ.leavereqobtain,UJ.resultscore,UJ.sparetire,UJ.asparetire,VA.teacher.teacherName)"
//							+ " from VaunJoinRecord UJ,VacollectiveAct VA,VacollectiveActivitiesPublish VP "
//							+ " where UJ.asparetire='"
//							+ checkOut
//							+ "'"
//							+ " and VA.spareTire='1' and UJ.sparetire='1' and VP.spareTire='1' "
//							+ " and VA.actId = UJ.actId "
//							+ " and UJ.actId = VP.vacollectiveAct.actId "
//							+ " and VA.teacher.spareTire='1'"
//							+ " and VA.teacher.department.spareTire='1'"
//							+ " and VA.teacher.department.departmentId='" + department.getDepartmentId() + "'");
			hql = new StringBuffer(
					"select new com.nuaa.ec.model.VaunJoinRecord(UJ.unjoinId,UJ.teacherId,VA.actId,VA.actName,UJ.actDate,VA.attendee,UJ.unjoinreason,UJ.leavereqobtain,UJ.resultscore,UJ.sparetire,UJ.asparetire,T.teacherName) "
							+ " from VaunJoinRecord UJ,VacollectiveAct VA,Teacher T "
							+ " where UJ.sparetire ='1' "
							+ " and UJ.asparetire = '" + checkOut + "'"
							+ " and VA.spareTire ='1' "
							+ " and UJ.teacherId = T.teacherId"
							+ " and VA.actId = UJ.actId "
							+ " and T.spareTire='1'"
							+ " and T.department.spareTire='1'"
							+ " and T.department.departmentId='" + department.getDepartmentId() + "'"
					);
		}
		try {
			String append = " and UJ.actDate >= ? and UJ.actDate <= ? ";
			String rank = " order by UJ.unjoinId desc";
			/*
			 * 不一定有日期，所以要判断
			 */
			if (foredate != null && afterdate != null && foredate.length() != 0 && afterdate.length() != 0) {
				// 判断日期范围限制
				hql.append(append).append(rank);
				list = this.getSession().createQuery(hql.append(rank).toString()).setString(0, foredate)
						.setString(1, afterdate).list();
			} else {
				list = this.getSession().createQuery(hql.append(rank).toString()).list();
			}
			/*
			 * 总体查询完毕，把总记录数和总页数放入session用于前台展现。
			 */
			session.put("recordNumber_UA", list.size());
			session.put("pageCount_UA", list.size() % pageSize == 0 ? (list.size() / pageSize) : (list.size()
					/ pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力， 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getUnjoinedActListAfterDivide(pageIndex, pageSize, foredate, afterdate, department, checkOut);
		} catch (Exception ex) {
			// TODO: handle exception
			log.error("find all failed", ex);
			System.out.println(ex.getMessage());
		}
		return list;

	}

	public List findAll(int currentRow, int pagesize, String generateQueryCondition) {
		// TODO Auto-generated method stub
		String queryString = " select new com.nuaa.ec.model.VaunJoinRecord(UJ.unjoinId,VA.teacher.teacherId,VA.actId,VA.actName, VP.actDate,VA.attendee,UJ.unjoinreason,UJ.leavereqobtain,UJ.resultscore,UJ.sparetire,UJ.asparetire,VA.teacher.teacherName)"
				+ " from VaunJoinRecord UJ,VacollectiveAct VA,VacollectiveActivitiesPublish VP "
				+ " where VA.spareTire='1' and UJ.sparetire='1' and VP.spareTire='1' "
				+ " and VA.actId = UJ.actId "
				+ " and UJ.actId = VP.vacollectiveAct.actId "
				+ " and VA.teacher.spareTire='1'"
				+ generateQueryCondition + "order by UJ.unjoinId desc";
		Query query2 = getSession().createQuery(queryString).setFirstResult(currentRow).setMaxResults(pagesize);
		return query2.list();
	}

	public int getRows(String generateQueryCondition) {
		// TODO Auto-generated method stub
		String queryString = " select new com.nuaa.ec.model.VaunJoinRecord(UJ.unjoinId,VA.teacher.teacherId,VA.actId,VA.actName, VP.actDate,VA.attendee,UJ.unjoinreason,UJ.leavereqobtain,UJ.resultscore,UJ.sparetire,UJ.asparetire,VA.teacher.teacherName)"
				+ " from VaunJoinRecord UJ,VacollectiveAct VA,VacollectiveActivitiesPublish VP "
				+ " where VA.spareTire='1' and UJ.sparetire='1' and VP.spareTire='1' "
				+ " and VA.actId = UJ.actId "
				+ " and UJ.actId = VP.vacollectiveAct.actId "
				+ " and VA.teacher.spareTire='1'"
				+ generateQueryCondition + "order by UJ.unjoinId desc";
		Query query2 = getSession().createQuery(queryString);
		return query2.list().size();
	}

	public UnjoinedActData getSummaryDataByTeacher(Teacher teacher, String foredate, String afterdate) {
		// TODO Auto-generated method stub
		String hql = "select sum(VA.resultscore),avg(VA.resultscore) from VaunJoinRecord VA "
				+ " where VA.sparetire = '1'"
//				+ " and VA.teacherId = T.teacherId "
				+ " and VA.asparetire = '1'"
				+ " and VA.actDate >='" + foredate +"'"
				+ " and VA.actDate <='" + afterdate +"'"
				+ " and VA.teacherId = '" + teacher.getTeacherId() + "'"
				;
		UnjoinedActData unjoinedActData = new UnjoinedActData();
		Object[] datas = (Object[]) this.getSession().createQuery(hql).uniqueResult();
		if (datas[0] != null) {
			unjoinedActData.setSum(NumberFormatUtil.getNumberAfterTransferPrecision((Double) datas[0]));
		} else {
			unjoinedActData.setSum(0);
		}
		if (datas[1] != null) {
			unjoinedActData.setAverage(NumberFormatUtil.getNumberAfterTransferPrecision((Double) datas[1]));
		} else {
			unjoinedActData.setAverage(0);
		}
		return unjoinedActData;
	}

	public UnjoinedActData getSummaryDataByDepartment(String departmentId, String foredate, String afterdate) {
		// TODO Auto-generated method stub
		String hql = "select sum(VA.resultscore),avg(VA.resultscore) from VaunJoinRecord VA,Teacher T"
				+ " where VA.sparetire='1'"
				+ " and VA.teacherId = T.teacherId"
				+ " and VA.asparetire='1'"
				+ " and VA.actDate >='" + foredate +"'"
				+ " and VA.actDate <='" + afterdate +"'"
				+ " and T.department.departmentId = '" + departmentId + "'"
				+ " ";
		UnjoinedActData unjoinedActData = new UnjoinedActData();
		Object[] datas = (Object[]) this.getSession().createQuery(hql).uniqueResult();
		if (datas[0] != null) {
			unjoinedActData.setSum(NumberFormatUtil.getNumberAfterTransferPrecision((Double) datas[0]));
		} else {
			unjoinedActData.setSum(0);
		}
		if (datas[1] != null) {
			unjoinedActData.setAverage(NumberFormatUtil.getNumberAfterTransferPrecision((Double) datas[1]));
		} else {
			unjoinedActData.setAverage(0);
		}
		return unjoinedActData;
	}

	public ByteArrayOutputStream findwithexport(String actId, String actDate, String actName) {
		// TODO Auto-generated method stub
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String queryString = "from VaunJoinRecord VA"
				+ " where VA.sparetire = '1'"
				+ " and VA.actDate = '" + actDate +"'"
				;
		Query query = this.getSession().createQuery(queryString);
		if (query.list().size() > 0) {
			try {
				VaActListExcel.generateTeacherUnJoinedExcel(stringstore.vaUnjoinedAct, query.list(), actDate, actName).write(baos);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return baos;
		}else {
			return null;
		}
	}

	public List<VaunJoinRecord> getPersonDetailsOfUnjoinedAct(String teacherId, String foredate, String afterdate) {
		// TODO Auto-generated method stub
		List<VaunJoinRecord> vaunJoinRecords = new ArrayList<VaunJoinRecord>();
		String hql = "from VaunJoinRecord VA where VA.sparetire = '1'"
				+ " and VA.asparetire = '1'"
				+ " and VA.actDate >= '" +  foredate + "'"
				+ " and VA.actDate <= '" + afterdate + "'"
				+ " and VA.teacherId ='" + teacherId + "'";
		vaunJoinRecords = this.getSession().createQuery(hql).list();
		return vaunJoinRecords;
	}

}