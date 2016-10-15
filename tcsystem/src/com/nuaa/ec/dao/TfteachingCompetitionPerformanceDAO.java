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
import com.nuaa.ec.model.TfdegreeThesisGuidancePerformance;
import com.nuaa.ec.model.TfteachingCompetitionPerformance;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachingCompetitionPerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachingCompetitionPerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfteachingCompetitionPerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachingCompetitionPerformanceDAO.class);
		//property constants
	public static final String COMPETITION_ID = "competitionId";
	public static final String COMPETITION_NAME = "competitionName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfteachingCompetitionPerformance> TFteachingCompetitionPefroList = null;
	public boolean updateCheckoutStatus(List<TfteachingCompetitionPerformance> TfTeachingCompetitionPerfList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TfTeachingCompetitionPerfList.size();i++){
				session.update(TfTeachingCompetitionPerfList.get(i));
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
	public List getTFTeachingCompetitionPefroList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		StringBuffer hqlBuffer = null;
		if (department.getDepartmentId() == null
				|| department.getDepartmentId().length() == 0) {
			/*
			 * 第一次进入的时候，不显示记录
			 */
			session.put("pageCount_TCP", 0);
			session.put("recordNumber_TCP", 0);
			return TFteachingCompetitionPefroList = new ArrayList<TfteachingCompetitionPerformance>();
		} else {
			// 查出符合条件的全部的记录
			hqlBuffer = new StringBuffer(
					"select TCP from TfteachingCompetitionPerformance TCP,Tfterm TERM where TCP.spareTire='1'"
							+ " and TCP.checkOut='" + checkOut + "'"
							+ " and TERM.spareTire='1'"
							+ " and TCP.tfteachingCompetitionRewardLevel.spareTire='1'"
							+ " and TCP.teacher.spareTire='1'"
							+ " and TCP.teacher.department.spareTire='1'"
							+ " and TCP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
							+ " and TCP.termId=TERM.termId"
							+ " and TERM.termId='"+ termId+"'");
			// 判断是否为分页操作
			if (!isDivided) {
				//如果不是分页操作，取出所有符合条件的记录
				TFteachingCompetitionPefroList = this.getSession()
						.createQuery(hqlBuffer.toString()).list();
				int recordNumber=TFteachingCompetitionPefroList.size();
				session.put("pageCount_TCP", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
				session.put("recordNumber_TCP", TFteachingCompetitionPefroList.size());
			} 
			//无论是不是分页查询，都在后台进行分页操作。
			TFteachingCompetitionPefroList = this.getSession()
					.createQuery(hqlBuffer.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		}
		return TFteachingCompetitionPefroList;
	}
    
    public void save(TfteachingCompetitionPerformance transientInstance) {
        log.debug("saving TfteachingCompetitionPerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachingCompetitionPerformance persistentInstance) {
        log.debug("deleting TfteachingCompetitionPerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachingCompetitionPerformance findById( java.lang.Integer id) {
        log.debug("getting TfteachingCompetitionPerformance instance with id: " + id);
        try {
            TfteachingCompetitionPerformance instance = (TfteachingCompetitionPerformance) getSession()
                    .get("com.nuaa.ec.model.TfteachingCompetitionPerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachingCompetitionPerformance instance) {
        log.debug("finding TfteachingCompetitionPerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachingCompetitionPerformance")
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
      log.debug("finding TfteachingCompetitionPerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachingCompetitionPerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByCompetitionId(Object competitionId
	) {
		return findByProperty(COMPETITION_ID, competitionId
		);
	}
	
	public List findByCompetitionName(Object competitionName
	) {
		return findByProperty(COMPETITION_NAME, competitionName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByFinalScore(Object finalScore
	) {
		return findByProperty(FINAL_SCORE, finalScore
		);
	}
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	

	public List findAll() {
		log.debug("finding all TfteachingCompetitionPerformance instances");
		try {
			String queryString = "from TfteachingCompetitionPerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachingCompetitionPerformance merge(TfteachingCompetitionPerformance detachedInstance) {
        log.debug("merging TfteachingCompetitionPerformance instance");
        try {
            TfteachingCompetitionPerformance result = (TfteachingCompetitionPerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachingCompetitionPerformance instance) {
        log.debug("attaching dirty TfteachingCompetitionPerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachingCompetitionPerformance instance) {
        log.debug("attaching clean TfteachingCompetitionPerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}