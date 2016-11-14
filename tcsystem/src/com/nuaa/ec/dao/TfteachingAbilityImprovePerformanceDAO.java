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
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingAbilityImprovePerformance;
import com.nuaa.ec.model.TfteachingAbilityImprovePerformanceUnionTfterm;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TfteachingAbilityImprovePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfteachingAbilityImprovePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfteachingAbilityImprovePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfteachingAbilityImprovePerformanceDAO.class);
		//property constants
	public static final String EVENT_ID = "eventId";
	public static final String EVENT_NAME = "eventName";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	public static final String YEARCEILING = "yearceiling";
	public static final String SUMHOURS = "sumhours";
	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfteachingAbilityImprovePerformance> TFteachingAbilityImproPefroList = null;
	/**
	 * 获得所有的记录信息 但是顺便实现了分页
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithDivided_adm(int pageIndex,int pageSize,String termId,String searchCondition,boolean isDivided){
		List<TfteachingAbilityImprovePerformanceUnionTfterm> list=new ArrayList<TfteachingAbilityImprovePerformanceUnionTfterm>();
		StringBuffer hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId != null && termId.length() != 0 && searchCondition!=null && searchCondition.trim().length()!=0 ) {
			hql=new StringBuffer("select new com.nuaa.ec.model.TfteachingAbilityImprovePerformanceUnionTfterm(TAI,TERM) from TfteachingAbilityImprovePerformance TAI,Tfterm TERM where TAI.spareTire='1'"
						+ " and TERM.spareTire='1'"
						+ " and TAI.tfteachingAbilityImproveLevel.spareTire='1'"
						+ " and TAI.teacher.spareTire='1'"
						+ " and TAI.termId=TERM.termId"
						+ " and TAI.termId='"+termId+"'"
						+ " and TAI.eventName like '%"+searchCondition+"%'"
						+ " order by TAI.eventId desc");
		} else {
			hql=new StringBuffer("select new com.nuaa.ec.model.TfteachingAbilityImprovePerformanceUnionTfterm(TAI,TERM) from TfteachingAbilityImprovePerformance TAI,Tfterm TERM where TAI.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and TAI.tfteachingAbilityImproveLevel.spareTire='1'"
					+ " and TAI.teacher.spareTire='1'"
					+ " and TAI.termId=TERM.termId");
			//有学期但是没有查询条件的情况
			if(termId != null && termId.length() != 0 &&(searchCondition==null || searchCondition.trim().length()==0)){
				hql.append(" and TAI.termId='"+termId+"'");
			}else if(searchCondition!=null && searchCondition.length()!=0 &&(termId == null || termId.length() == 0)){
				//有查询条件 但是没有学期的情况
				hql.append(" and TAI.eventName like '%"+searchCondition+"%'");
			}else{//学期和查询条件都没有的情况
				//这块没有业务逻辑，只是为了使得逻辑清楚
			}
			hql.append(" order by TAI.eventId desc");//指定结果集排序规则
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql.toString()).list();
				int listSize=list.size();
				session.put("recordNumber_ATTAI",list.size());
				session.put("pageCount_ATTAI", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
			}
			/*
			 * 分页 pageIndex 默认是1，显示第一页，
			 * 但以后会随着前台的分页操作同步更新。
			 */
			list=this.getSession().createQuery(hql.toString()).setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).list();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	/**
	 * 获得所有的记录信息 但是顺便实现了分页
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithDivided(int pageIndex,int pageSize,String termId,boolean isDivided){
		Teacher teacherHaveLogin=(Teacher) session.get("teacher");
		List<TfteachingAbilityImprovePerformanceUnionTfterm> list=new ArrayList<TfteachingAbilityImprovePerformanceUnionTfterm>();
		String hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId == null || termId.length() == 0) {
			hql="select new com.nuaa.ec.model.TfteachingAbilityImprovePerformanceUnionTfterm(TAI,TERM) from TfteachingAbilityImprovePerformance TAI,Tfterm TERM where TAI.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and TAI.tfteachingAbilityImproveLevel.spareTire='1'"
					+ " and TAI.teacher.spareTire='1'"
					+ " and TAI.teacher=?"
					+ " and TAI.termId=TERM.termId"
					+ " order by TAI.eventId desc";
		} else {
			hql="select new com.nuaa.ec.model.TfteachingAbilityImprovePerformanceUnionTfterm(TAI,TERM) from TfteachingAbilityImprovePerformance TAI,Tfterm TERM where TAI.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and TAI.tfteachingAbilityImproveLevel.spareTire='1'"
					+ " and TAI.teacher.spareTire='1'"
					+ " and TAI.teacher=?"
					+ " and TAI.termId=TERM.termId"
					+ " and TERM.termId='"+ termId+"'"
					+ " order by TAI.eventId desc";
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql).setParameter(0, teacherHaveLogin).list();
				int listSize=list.size();
				session.put("recordNumber_GTTAI",list.size());
				session.put("pageCount_GTTAI", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
			}
			/*
			 * 分页 pageIndex 默认是1，显示第一页，
			 * 但以后会随着前台的分页操作同步更新。
			 */
			list=this.getSession().createQuery(hql).setFirstResult((pageIndex-1)*pageSize).setMaxResults(pageSize).setParameter(0, teacherHaveLogin).list();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return list;
	}
	public boolean updateCheckoutStatus(List<TfteachingAbilityImprovePerformance> TfTeachingAbilityImproPerfList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TfTeachingAbilityImproPerfList.size();i++){
				session.update(TfTeachingAbilityImproPerfList.get(i));
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
	public List getTFTeachingAbilityImprovePefroList(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_TAI", 0);
				session.put("recordNumber_TAI", 0);
				return TFteachingAbilityImproPefroList = new ArrayList<TfteachingAbilityImprovePerformance>();
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"select TAI from TfteachingAbilityImprovePerformance TAI,Tfterm TERM where TAI.spareTire='1'"
								+ " and TAI.checkOut='" + checkOut + "'"
								+ " and TERM.spareTire='1'"
								+ " and TAI.tfteachingAbilityImproveLevel.spareTire='1'"
								+ " and TAI.teacher.spareTire='1'"
								+ " and TAI.teacher.department.spareTire='1'"
								+ " and TAI.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " and TAI.termId=TERM.termId"
								+ " and TERM.termId='"+ termId+"'");
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					TFteachingAbilityImproPefroList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=TFteachingAbilityImproPefroList.size();
					session.put("pageCount_TAI", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_TAI", TFteachingAbilityImproPefroList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				TFteachingAbilityImproPefroList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return TFteachingAbilityImproPefroList;
	}


    
    public void save(TfteachingAbilityImprovePerformance transientInstance) {
        log.debug("saving TfteachingAbilityImprovePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfteachingAbilityImprovePerformance persistentInstance) {
        log.debug("deleting TfteachingAbilityImprovePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfteachingAbilityImprovePerformance findById( java.lang.Integer id) {
        log.debug("getting TfteachingAbilityImprovePerformance instance with id: " + id);
        try {
            TfteachingAbilityImprovePerformance instance = (TfteachingAbilityImprovePerformance) getSession()
                    .get("com.nuaa.ec.model.TfteachingAbilityImprovePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfteachingAbilityImprovePerformance instance) {
        log.debug("finding TfteachingAbilityImprovePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfteachingAbilityImprovePerformance")
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
      log.debug("finding TfteachingAbilityImprovePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfteachingAbilityImprovePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByEventId(Object eventId
	) {
		return findByProperty(EVENT_ID, eventId
		);
	}
	
	public List findByEventName(Object eventName
	) {
		return findByProperty(EVENT_NAME, eventName
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
	
	public List findBySumhours(Object sumhours
	) {
		return findByProperty(SUMHOURS, sumhours
		);
	}
	

	public List findAll() {
		log.debug("finding all TfteachingAbilityImprovePerformance instances");
		try {
			String queryString = "from TfteachingAbilityImprovePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfteachingAbilityImprovePerformance merge(TfteachingAbilityImprovePerformance detachedInstance) {
        log.debug("merging TfteachingAbilityImprovePerformance instance");
        try {
            TfteachingAbilityImprovePerformance result = (TfteachingAbilityImprovePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfteachingAbilityImprovePerformance instance) {
        log.debug("attaching dirty TfteachingAbilityImprovePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfteachingAbilityImprovePerformance instance) {
        log.debug("attaching clean TfteachingAbilityImprovePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}