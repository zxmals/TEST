package com.nuaa.ec.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import com.nuaa.ec.model.TfdegreeThesisGuidancePerformance;
import com.nuaa.ec.model.TfteachingCompetitionPerformance;
import com.nuaa.ec.model.TfteachingCompetitionPerformanceUnionTfterm;
import com.nuaa.ec.teachingData.exportData.DegreeThesisGuidanceExcel;
import com.nuaa.ec.teachingData.exportData.TeachingCompetitionExcel;
import com.nuaa.ec.utils.stringstore;
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
	/**
	 * 教学竞赛指导模块的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString = "select new com.nuaa.ec.model.TfteachingCompetitionPerformanceUnionTfterm(TCP,TERM) from TfteachingCompetitionPerformance TCP,Tfterm TERM where TCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and TCP.tfteachingCompetitionRewardLevel.spareTire='1'"
					+ " and TCP.teacher.spareTire='1'"
					+ " and TCP.termId=TERM.termId"
					+ " and TCP.termId between ? and ?"
					+ " and TCP.teacher.department=?"
					+ " order by TCP.competitionId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					TeachingCompetitionExcel.generateExcel(
							stringstore.teachingCompetition,
							queryObject.list(), departmentName, foredate,
							afterdate).write(baos);
				} catch (IOException e) {
					e.printStackTrace();
				}
				return baos;
			} else {
				return null;
			}
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	/**
	 * 获得所有的记录信息 但是顺便实现了分页
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithDivided_adm(int pageIndex,int pageSize,String termId,String searchCondition,boolean isDivided){
		List<TfteachingCompetitionPerformanceUnionTfterm> list=new ArrayList<TfteachingCompetitionPerformanceUnionTfterm>();
		StringBuffer hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId != null && termId.length() != 0 && searchCondition!=null && searchCondition.trim().length()!=0 ) {
			hql = new StringBuffer("select new com.nuaa.ec.model.TfteachingCompetitionPerformanceUnionTfterm(TCP,TERM) from TfteachingCompetitionPerformance TCP,Tfterm TERM where TCP.spareTire='1'"
						+ " and TERM.spareTire='1'"
						+ " and TCP.tfteachingCompetitionRewardLevel.spareTire='1'"
						+ " and TCP.teacher.spareTire='1'"
						+ " and TCP.termId=TERM.termId"
						+ " and TCP.termId='"+termId+"'"
						+ " and TCP.competitionName like '%"+searchCondition+"%'"
						+ " order by TCP.competitionId desc");
		} else {
			hql = new StringBuffer("select new com.nuaa.ec.model.TfteachingCompetitionPerformanceUnionTfterm(TCP,TERM) from TfteachingCompetitionPerformance TCP,Tfterm TERM where TCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and TCP.tfteachingCompetitionRewardLevel.spareTire='1'"
					+ " and TCP.teacher.spareTire='1'"
					+ " and TCP.termId=TERM.termId");
//					+ " order by TCP.competitionId desc";
			//有学期但是没有查询条件的情况
			if(termId != null && termId.length() != 0 &&(searchCondition==null || searchCondition.trim().length()==0)){
				hql.append(" and TCP.termId='"+termId+"'");
			}else if(searchCondition!=null && searchCondition.length()!=0 &&(termId == null || termId.length() == 0)){
				//有查询条件 但是没有学期的情况
				hql.append(" and TCP.competitionName like '%"+searchCondition+"%'");
			}else{//学期和查询条件都没有的情况
				//这块没有业务逻辑，只是为了使得逻辑清楚
			}
			hql.append(" order by TCP.competitionId desc");//指定结果集排序规则
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql.toString()).list();
				int listSize=list.size();
				session.put("recordNumber_ATTCP",list.size());
				session.put("pageCount_ATTCP", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
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
		List<TfteachingCompetitionPerformanceUnionTfterm> list=new ArrayList<TfteachingCompetitionPerformanceUnionTfterm>();
		String hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId == null || termId.length() == 0) {
//			hql = "select new com.nuaa.ec.model.TfteachingCompetitionPerformanceUnionTfterm(TCP,TERM) from TfteachingCompetitionPerformance TCP where spareTire='1' and TCP.teacher=? order by TCP.competitionId desc";
			hql = "select new com.nuaa.ec.model.TfteachingCompetitionPerformanceUnionTfterm(TCP,TERM) from TfteachingCompetitionPerformance TCP,Tfterm TERM where TCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and TCP.tfteachingCompetitionRewardLevel.spareTire='1'"
					+ " and TCP.teacher.spareTire='1'"
					+ " and TCP.teacher=?"
					+ " and TCP.termId=TERM.termId"
					+ " order by TCP.competitionId desc";
		} else {
			hql = "select new com.nuaa.ec.model.TfteachingCompetitionPerformanceUnionTfterm(TCP,TERM) from TfteachingCompetitionPerformance TCP,Tfterm TERM where TCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and TCP.tfteachingCompetitionRewardLevel.spareTire='1'"
					+ " and TCP.teacher.spareTire='1'"
					+ " and TCP.teacher=?"
					+ " and TCP.termId=TERM.termId"
					+ " and TERM.termId='"+ termId+"'"
					+ " order by TCP.competitionId desc";
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql).setParameter(0, teacherHaveLogin).list();
				int listSize=list.size();
				session.put("recordNumber_GTTCP",list.size());
				session.put("pageCount_GTTCP", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
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
			if (checkOut.equals("4")) {
				hqlBuffer = new StringBuffer(
						"select TCP from TfteachingCompetitionPerformance TCP,Tfterm TERM where TCP.spareTire='1'"
								+ " and TERM.spareTire='1'"
								+ " and TCP.tfteachingCompetitionRewardLevel.spareTire='1'"
								+ " and TCP.teacher.spareTire='1'"
								+ " and TCP.teacher.department.spareTire='1'"
								+ " and TCP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " and TCP.termId=TERM.termId"
								+ " and TERM.termId='"+ termId+"'");
			}else {
				
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
			}
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