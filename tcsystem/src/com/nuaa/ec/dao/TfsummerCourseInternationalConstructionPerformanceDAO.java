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
import com.nuaa.ec.model.TfprofessionalProjectDeclarePerformance;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TfsummerCourseInternationalConstructionPerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfsummerCourseInternationalConstructionPerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfsummerCourseInternationalConstructionPerformanceDAO.class);
		//property constants
	public static final String PROJECT_ID = "projectId";
	public static final String PROJECT_NAME = "projectName";
	public static final String SCORE = "score";
	public static final String CHECK_OUT = "checkOut";
	public static final String SPARE_TIRE = "spareTire";
	public static final String QUANTITY_UNIT = "quantityUnit";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfsummerCourseInternationalConstructionPerformance> tfSummerAndInternationalCourseConstructionPerformanceList = null;
	public boolean updateCheckoutStatus(List<TfsummerCourseInternationalConstructionPerformance> tfSummerAndInternationalConsrseConstructionPerfList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<tfSummerAndInternationalConsrseConstructionPerfList.size();i++){
				session.update(tfSummerAndInternationalConsrseConstructionPerfList.get(i));
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
	public List getTfSummerAndInternationalConstructionPerformanceListToBeAudited(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_SCI", 0);
				session.put("recordNumber_SCI", 0);
				return tfSummerAndInternationalCourseConstructionPerformanceList = new ArrayList<TfsummerCourseInternationalConstructionPerformance>();
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"select SCI from TfsummerCourseInternationalConstructionPerformance SCI,Tfterm TERM where TERM.termId=SCI.termId"
								+ " and SCI.spareTire='1'"
								+ " and TERM.spareTire='1'"
								+ " and SCI.checkOut='" + checkOut + "'"
								+ " and SCI.tfsummerCourseInternationalConstructionLevel.spareTire='1'"
								+ " and SCI.teacher.spareTire='1'"
								+ " and SCI.teacher.department.spareTire='1'"
								+ " and SCI.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " and SCI.termId='"+termId+"'"
								+ " order by SCI.projectId asc");
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					tfSummerAndInternationalCourseConstructionPerformanceList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=tfSummerAndInternationalCourseConstructionPerformanceList.size();
					session.put("pageCount_SCI", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_SCI", tfSummerAndInternationalCourseConstructionPerformanceList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				tfSummerAndInternationalCourseConstructionPerformanceList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return tfSummerAndInternationalCourseConstructionPerformanceList;
	}


    
    public void save(TfsummerCourseInternationalConstructionPerformance transientInstance) {
        log.debug("saving TfsummerCourseInternationalConstructionPerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfsummerCourseInternationalConstructionPerformance persistentInstance) {
        log.debug("deleting TfsummerCourseInternationalConstructionPerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfsummerCourseInternationalConstructionPerformance findById( java.lang.Integer id) {
        log.debug("getting TfsummerCourseInternationalConstructionPerformance instance with id: " + id);
        try {
            TfsummerCourseInternationalConstructionPerformance instance = (TfsummerCourseInternationalConstructionPerformance) getSession()
                    .get("com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfsummerCourseInternationalConstructionPerformance instance) {
        log.debug("finding TfsummerCourseInternationalConstructionPerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance")
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
      log.debug("finding TfsummerCourseInternationalConstructionPerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfsummerCourseInternationalConstructionPerformance as model where model." 
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
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
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
	
	public List findByQuantityUnit(Object quantityUnit
	) {
		return findByProperty(QUANTITY_UNIT, quantityUnit
		);
	}
	

	public List findAll() {
		log.debug("finding all TfsummerCourseInternationalConstructionPerformance instances");
		try {
			String queryString = "from TfsummerCourseInternationalConstructionPerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfsummerCourseInternationalConstructionPerformance merge(TfsummerCourseInternationalConstructionPerformance detachedInstance) {
        log.debug("merging TfsummerCourseInternationalConstructionPerformance instance");
        try {
            TfsummerCourseInternationalConstructionPerformance result = (TfsummerCourseInternationalConstructionPerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfsummerCourseInternationalConstructionPerformance instance) {
        log.debug("attaching dirty TfsummerCourseInternationalConstructionPerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfsummerCourseInternationalConstructionPerformance instance) {
        log.debug("attaching clean TfsummerCourseInternationalConstructionPerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}