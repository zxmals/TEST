package com.nuaa.ec.dao;

import com.nuaa.ec.model.VaunJoinRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
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
	private static final Logger log = LoggerFactory
			.getLogger(VaunJoinRecordDAO.class);
	// property constants
	public static final String TEACHER_ID = "teacherId";
	public static final String ACT_ID = "actId";
	public static final String UNJOINREASON = "unjoinreason";
	public static final String LEAVEREQOBTAIN = "leavereqobtain";
	public static final String RESULTSCORE = "resultscore";
	public static final String SPARETIRE = "sparetire";
	public static final String ASPARETIRE = "asparetire";

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
			VaunJoinRecord instance = (VaunJoinRecord) getSession().get(
					"com.nuaa.ec.model.VaunJoinRecord", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
	
	public List findByExample(VaunJoinRecord instance) {
		log.debug("finding VaunJoinRecord instance by example");
		try {
			List results = getSession()
					.createCriteria("com.nuaa.ec.model.VaunJoinRecord")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List<VaunJoinRecord> findByTimeLimted(String foredate ,String afterdate,String teacherId){
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
			while(rs.next()){
				vaun = new VaunJoinRecord(
						rs.getString("unjoinID"), 
						teacherId, 
						rs.getString("ActID"), 
						rs.getString("ActName"), 
						rs.getString("ActDate"), 
						rs.getString("Attendee"), 
						rs.getString("unjoinreason"), 
						rs.getString("leavereqobtain"), 
						rs.getDouble("resultscore"), 
						rs.getString("Asparetire"));
				vaunli.add(vaun);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			closeSqlAttr(ps, rs);
		}
		if(vaunli.size()!=0)
			return vaunli;
		else 
			return null;
	}
	
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding VaunJoinRecord instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from VaunJoinRecord as model where model."
					+ propertyName + "= ? and model.sparetire = '1'";
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
			String queryString = "from VaunJoinRecord";
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
			VaunJoinRecord result = (VaunJoinRecord) getSession().merge(
					detachedInstance);
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
}