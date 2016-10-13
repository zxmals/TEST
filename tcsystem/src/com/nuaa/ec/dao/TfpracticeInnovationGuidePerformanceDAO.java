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
import com.nuaa.ec.model.TfpracticeInnovationGuidePerformance;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TfpracticeInnovationGuidePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfpracticeInnovationGuidePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfpracticeInnovationGuidePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfpracticeInnovationGuidePerformanceDAO.class);
		//property constants
	public static final String PROJECT_ID = "projectId";
	public static final String PROJECT_NAME = "projectName";
	public static final String FINAL_SCORE = "finalScore";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";

	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfpracticeInnovationGuidePerformance> tfPracticeInnovationGuidePerformanceList = null;
	public boolean updateCheckoutStatus(List<TfpracticeInnovationGuidePerformance> tfPracticeInnovationGuidePerformance){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<tfPracticeInnovationGuidePerformance.size();i++){
				session.update(tfPracticeInnovationGuidePerformance.get(i));
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
	public List getTfPracticeInnovationGuidePerformanceListToBeAudited(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_PIG", 0);
				session.put("recordNumber_PIG", 0);
				return tfPracticeInnovationGuidePerformanceList = new ArrayList<TfpracticeInnovationGuidePerformance>();
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"select PIG from TfpracticeInnovationGuidePerformance PIG,Tfterm TERM where TERM.termId=PIG.termId"
								+ " and PIG.spareTire='1'"
								+ " and TERM.spareTire='1'"
								+ " and PIG.checkOut='" + checkOut + "'"
								+ " and PIG.tfpracticeInnovationGuideLevel.spareTire='1'"
								+ " and PIG.tfpracticeInnovationGuideGraduationThesisGuideEvalution.spareTire='1'"
								+ " and PIG.teacher.spareTire='1'"
								+ " and PIG.teacher.department.spareTire='1'"
								+ " and PIG.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " and PIG.termId='"+termId+"'"
								+ " order by PIG.projectId asc");
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					tfPracticeInnovationGuidePerformanceList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=tfPracticeInnovationGuidePerformanceList.size();
					session.put("pageCount_PIG", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_PIG", tfPracticeInnovationGuidePerformanceList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				tfPracticeInnovationGuidePerformanceList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return tfPracticeInnovationGuidePerformanceList;
	}

    
    public void save(TfpracticeInnovationGuidePerformance transientInstance) {
        log.debug("saving TfpracticeInnovationGuidePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfpracticeInnovationGuidePerformance persistentInstance) {
        log.debug("deleting TfpracticeInnovationGuidePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfpracticeInnovationGuidePerformance findById( java.lang.Integer id) {
        log.debug("getting TfpracticeInnovationGuidePerformance instance with id: " + id);
        try {
            TfpracticeInnovationGuidePerformance instance = (TfpracticeInnovationGuidePerformance) getSession()
                    .get("com.nuaa.ec.model.TfpracticeInnovationGuidePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfpracticeInnovationGuidePerformance instance) {
        log.debug("finding TfpracticeInnovationGuidePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfpracticeInnovationGuidePerformance")
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
      log.debug("finding TfpracticeInnovationGuidePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfpracticeInnovationGuidePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByProjectId(Object projectId
	) {
		return findByProperty(PROJECT_ID, projectId
		);
	}
	
	public List findByProjectName(Object projectName
	) {
		return findByProperty(PROJECT_NAME, projectName
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
	

	public List findAll() {
		log.debug("finding all TfpracticeInnovationGuidePerformance instances");
		try {
			String queryString = "from TfpracticeInnovationGuidePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfpracticeInnovationGuidePerformance merge(TfpracticeInnovationGuidePerformance detachedInstance) {
        log.debug("merging TfpracticeInnovationGuidePerformance instance");
        try {
            TfpracticeInnovationGuidePerformance result = (TfpracticeInnovationGuidePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfpracticeInnovationGuidePerformance instance) {
        log.debug("attaching dirty TfpracticeInnovationGuidePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfpracticeInnovationGuidePerformance instance) {
        log.debug("attaching clean TfpracticeInnovationGuidePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}