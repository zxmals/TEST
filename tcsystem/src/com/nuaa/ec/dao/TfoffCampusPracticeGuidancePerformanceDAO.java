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
import com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TfoffCampusPracticeGuidancePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfoffCampusPracticeGuidancePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfoffCampusPracticeGuidancePerformanceDAO.class);
		//property constants
	public static final String TEACHER_ID = "teacherId";
	public static final String PROJECT_NAME = "projectName";
	public static final String PROJECT_ID = "projectId";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	public static final String YEAR_CEILING = "yearCeiling";
	public static final String QUANTITY_UNIT = "quantityUnit";
	public static final String SUMHOURS = "sumhours";
	public static final String OFFGUIDANCE_ID = "offguidanceId";

	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfoffCampusPracticeGuidancePerformance> tfOffCampusPracticeGuidancePerformanceList = null;
	public boolean updateCheckoutStatus(List<TfoffCampusPracticeGuidancePerformance> tfOffCampusPracticeGuidancePerfList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<tfOffCampusPracticeGuidancePerfList.size();i++){
				session.update(tfOffCampusPracticeGuidancePerfList.get(i));
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
	public List getOffCampusPracticeGuidancePerformanceListToBeAudit(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_OCP", 0);
				session.put("recordNumber_OCP", 0);
				return tfOffCampusPracticeGuidancePerformanceList = new ArrayList<TfoffCampusPracticeGuidancePerformance>();
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"select OCP from TfoffCampusPracticeGuidancePerformance OCP,Tfterm TERM where TERM.termId=OCP.termId"
								+ " and OCP.spareTire='1'"
								+ " and TERM.spareTire='1'"
								+ " and OCP.checkOut='" + checkOut + "'"
								+ " and OCP.tfoffCampusPracticeGuidanceLevel.spareTire='1'"
								+ " and OCP.teacher.spareTire='1'"
								+ " and OCP.teacher.department.spareTire='1'"
								+ " and OCP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " and OCP.termId='"+termId+"'"
								+ " order by OCP.offguidanceId desc");
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					tfOffCampusPracticeGuidancePerformanceList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=tfOffCampusPracticeGuidancePerformanceList.size();
					session.put("pageCount_OCP", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_OCP", tfOffCampusPracticeGuidancePerformanceList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				tfOffCampusPracticeGuidancePerformanceList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return tfOffCampusPracticeGuidancePerformanceList;
	}

    
    public void save(TfoffCampusPracticeGuidancePerformance transientInstance) {
        log.debug("saving TfoffCampusPracticeGuidancePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfoffCampusPracticeGuidancePerformance persistentInstance) {
        log.debug("deleting TfoffCampusPracticeGuidancePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfoffCampusPracticeGuidancePerformance findById( java.lang.Integer id) {
        log.debug("getting TfoffCampusPracticeGuidancePerformance instance with id: " + id);
        try {
            TfoffCampusPracticeGuidancePerformance instance = (TfoffCampusPracticeGuidancePerformance) getSession()
                    .get("com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfoffCampusPracticeGuidancePerformance instance) {
        log.debug("finding TfoffCampusPracticeGuidancePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance")
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
      log.debug("finding TfoffCampusPracticeGuidancePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfoffCampusPracticeGuidancePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByTeacherId(Object teacherId
	) {
		return findByProperty(TEACHER_ID, teacherId
		);
	}
	
	public List findByProjectName(Object projectName
	) {
		return findByProperty(PROJECT_NAME, projectName
		);
	}
	
	public List findByProjectId(Object projectId
	) {
		return findByProperty(PROJECT_ID, projectId
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
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByYearCeiling(Object yearCeiling
	) {
		return findByProperty(YEAR_CEILING, yearCeiling
		);
	}
	
	public List findByQuantityUnit(Object quantityUnit
	) {
		return findByProperty(QUANTITY_UNIT, quantityUnit
		);
	}
	
	public List findBySumhours(Object sumhours
	) {
		return findByProperty(SUMHOURS, sumhours
		);
	}
	
	public List findByOffguidanceId(Object offguidanceId
	) {
		return findByProperty(OFFGUIDANCE_ID, offguidanceId
		);
	}
	

	public List findAll() {
		log.debug("finding all TfoffCampusPracticeGuidancePerformance instances");
		try {
			String queryString = "from TfoffCampusPracticeGuidancePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfoffCampusPracticeGuidancePerformance merge(TfoffCampusPracticeGuidancePerformance detachedInstance) {
        log.debug("merging TfoffCampusPracticeGuidancePerformance instance");
        try {
            TfoffCampusPracticeGuidancePerformance result = (TfoffCampusPracticeGuidancePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfoffCampusPracticeGuidancePerformance instance) {
        log.debug("attaching dirty TfoffCampusPracticeGuidancePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfoffCampusPracticeGuidancePerformance instance) {
        log.debug("attaching clean TfoffCampusPracticeGuidancePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}