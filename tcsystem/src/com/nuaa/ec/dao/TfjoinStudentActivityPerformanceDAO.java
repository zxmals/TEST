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
import com.nuaa.ec.model.TfjoinStudentActivityPerformance;
import com.nuaa.ec.model.TfstudentCompetitionGuidancePerformance;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TfjoinStudentActivityPerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfjoinStudentActivityPerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfjoinStudentActivityPerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfjoinStudentActivityPerformanceDAO.class);
		//property constants
	public static final String ACTIVITY_ID = "activityId";
	public static final String ACTIVITY_NAME = "activityName";
	public static final String SUMHOURS = "sumhours";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	public static final String YEARCEILING = "yearceiling";

	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfjoinStudentActivityPerformance> tfJoinStudentActivityPerformanceList = null;
	public boolean updateCheckoutStatus(List<TfjoinStudentActivityPerformance> tfJoinStudentActivityPerfList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<tfJoinStudentActivityPerfList.size();i++){
				session.update(tfJoinStudentActivityPerfList.get(i));
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
	public List getTfJoinStudentActivityPerformanceListToBeAudited(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_JSA", 0);
				session.put("recordNumber_JSA", 0);
				return tfJoinStudentActivityPerformanceList = new ArrayList<TfjoinStudentActivityPerformance>();
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"select JSA from TfjoinStudentActivityPerformance JSA,Tfterm TERM where TERM.termId=JSA.termId"
								+ " and JSA.spareTire='1'"
								+ " and TERM.spareTire='1'"
								+ " and JSA.checkOut='" + checkOut + "'"
								+ " and JSA.tfjoinStudentActivityTime.spareTire='1'"
								+ " and JSA.teacher.spareTire='1'"
								+ " and JSA.teacher.department.spareTire='1'"
								+ " and JSA.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " and JSA.termId='"+termId+"'"
								+ " order by JSA.activityId asc");
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					tfJoinStudentActivityPerformanceList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=tfJoinStudentActivityPerformanceList.size();
					session.put("pageCount_JSA", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_JSA", tfJoinStudentActivityPerformanceList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				tfJoinStudentActivityPerformanceList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return tfJoinStudentActivityPerformanceList;
	}

    
    public void save(TfjoinStudentActivityPerformance transientInstance) {
        log.debug("saving TfjoinStudentActivityPerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfjoinStudentActivityPerformance persistentInstance) {
        log.debug("deleting TfjoinStudentActivityPerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfjoinStudentActivityPerformance findById( java.lang.Integer id) {
        log.debug("getting TfjoinStudentActivityPerformance instance with id: " + id);
        try {
            TfjoinStudentActivityPerformance instance = (TfjoinStudentActivityPerformance) getSession()
                    .get("com.nuaa.ec.model.TfjoinStudentActivityPerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfjoinStudentActivityPerformance instance) {
        log.debug("finding TfjoinStudentActivityPerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfjoinStudentActivityPerformance")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding TfjoinStudentActivityPerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfjoinStudentActivityPerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByActivityId(Object activityId
	) {
		return findByProperty(ACTIVITY_ID, activityId
		);
	}
	
	public List findByActivityName(Object activityName
	) {
		return findByProperty(ACTIVITY_NAME, activityName
		);
	}
	
	public List findBySumhours(Object sumhours
	) {
		return findByProperty(SUMHOURS, sumhours
		);
	}
	
	public List findByFinalScore(Object finalScore
	) {
		return findByProperty(FINAL_SCORE, finalScore
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	
	public List findByYearceiling(Object yearceiling
	) {
		return findByProperty(YEARCEILING, yearceiling
		);
	}
	

	public List findAll() {
		log.debug("finding all TfjoinStudentActivityPerformance instances");
		try {
			String queryString = "from TfjoinStudentActivityPerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfjoinStudentActivityPerformance merge(TfjoinStudentActivityPerformance detachedInstance) {
        log.debug("merging TfjoinStudentActivityPerformance instance");
        try {
            TfjoinStudentActivityPerformance result = (TfjoinStudentActivityPerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfjoinStudentActivityPerformance instance) {
        log.debug("attaching dirty TfjoinStudentActivityPerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfjoinStudentActivityPerformance instance) {
        log.debug("attaching clean TfjoinStudentActivityPerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}