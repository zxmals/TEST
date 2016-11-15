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
import com.nuaa.ec.model.TfstudentCompetitionGuidancePerformance;
import com.nuaa.ec.model.TfstudentCompetitionGuidancePerformanceUnionTfterm;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TfstudentCompetitionGuidancePerformance entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TfstudentCompetitionGuidancePerformance
  * @author MyEclipse Persistence Tools 
 */
public class TfstudentCompetitionGuidancePerformanceDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TfstudentCompetitionGuidancePerformanceDAO.class);
		//property constants
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String COMPETITION_ID = "competitionId";
	public static final String COMPETITION_NAME = "competitionName";
	public static final String CHECK_OUT = "checkOut";

	private Map<String,Object> session=ActionContext.getContext().getSession();

	private List<TfstudentCompetitionGuidancePerformance> tfStudentCompetitionGuidancePerformanceList = null;
	/**
	 * 获得所有的记录信息 但是顺便实现了分页
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAllWithDivided_adm(int pageIndex,int pageSize,String termId,String searchCondition,boolean isDivided){
		List<TfstudentCompetitionGuidancePerformanceUnionTfterm> list=new ArrayList<TfstudentCompetitionGuidancePerformanceUnionTfterm>();
		StringBuffer hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId != null && termId.length() != 0 && searchCondition!=null && searchCondition.trim().length()!=0) {
			hql=new StringBuffer("select new com.nuaa.ec.model.TfstudentCompetitionGuidancePerformanceUnionTfterm(SCG,TERM) from TfstudentCompetitionGuidancePerformance SCG,Tfterm TERM where TERM.termId=SCG.termId"
					+ " and SCG.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceCompetitionType.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceRewardLevel.spareTire='1'"
					+ " and SCG.teacher.spareTire='1'"
					+ " and SCG.termId='"+termId+"'"
					+ " and SCG.competitionName like '%"+searchCondition+"%'"
					+ " order by SCG.competitionId desc");
		} else {
			hql=new StringBuffer("select new com.nuaa.ec.model.TfstudentCompetitionGuidancePerformanceUnionTfterm(SCG,TERM) from TfstudentCompetitionGuidancePerformance SCG,Tfterm TERM where TERM.termId=SCG.termId"
					+ " and SCG.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceCompetitionType.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceRewardLevel.spareTire='1'"
					+ " and SCG.teacher.spareTire='1'");
			//有学期但是没有查询条件的情况
			if(termId != null && termId.length() != 0 &&(searchCondition==null || searchCondition.trim().length()==0)){
				hql.append(" and SCG.termId='"+termId+"'");
			}else if(searchCondition!=null && searchCondition.length()!=0 &&(termId == null || termId.length() == 0)){
				//有查询条件 但是没有学期的情况
				hql.append(" and SCG.competitionName like '%"+searchCondition+"%'");
			}else{//学期和查询条件都没有的情况
				//这块没有业务逻辑，只是为了使得逻辑清楚
			}
			hql.append(" order by SCG.competitionId desc");
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql.toString()).list();
				int listSize=list.size();
				session.put("recordNumber_ATSCG",list.size());
				session.put("pageCount_ATSCG", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
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
		List<TfstudentCompetitionGuidancePerformanceUnionTfterm> list=new ArrayList<TfstudentCompetitionGuidancePerformanceUnionTfterm>();
		String hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId == null || termId.length() == 0) {
			hql="select new com.nuaa.ec.model.TfstudentCompetitionGuidancePerformanceUnionTfterm(SCG,TERM) from TfstudentCompetitionGuidancePerformance SCG,Tfterm TERM where TERM.termId=SCG.termId"
					+ " and SCG.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceCompetitionType.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceRewardLevel.spareTire='1'"
					+ " and SCG.teacher.spareTire='1'"
					+ " and SCG.teacher=?"
					+ " order by SCG.competitionId desc";
		} else {
			hql="select new com.nuaa.ec.model.TfstudentCompetitionGuidancePerformanceUnionTfterm(SCG,TERM) from TfstudentCompetitionGuidancePerformance SCG,Tfterm TERM where TERM.termId=SCG.termId"
					+ " and SCG.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceCompetitionType.spareTire='1'"
					+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceRewardLevel.spareTire='1'"
					+ " and SCG.teacher.spareTire='1'"
					+ " and SCG.teacher=?"
					+ " and SCG.termId='"+termId+"'"
					+ " order by SCG.competitionId desc";
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql).setParameter(0, teacherHaveLogin).list();
				int listSize=list.size();
				session.put("recordNumber_GTSCG",list.size());
				session.put("pageCount_GTSCG", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
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
	public boolean updateCheckoutStatus(List<TfstudentCompetitionGuidancePerformance> tfStudentCompetitionGuidancePerformance){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<tfStudentCompetitionGuidancePerformance.size();i++){
				session.update(tfStudentCompetitionGuidancePerformance.get(i));
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
	public List getTfStudentCompetitionGuidancePerformanceListToBeAudited(int pageIndex, int pageSize, String termId,
			Department department, String checkOut, boolean isDivided) {
		try{
			StringBuffer hqlBuffer = null;
			if (department.getDepartmentId() == null
					|| department.getDepartmentId().length() == 0) {
				/*
				 * 第一次进入的时候，不显示记录
				 */
				session.put("pageCount_SCG", 0);
				session.put("recordNumber_SCG", 0);
				return tfStudentCompetitionGuidancePerformanceList = new ArrayList<TfstudentCompetitionGuidancePerformance>();
			} else {
				// 查出符合条件的全部的记录
				hqlBuffer = new StringBuffer(
						"select SCG from TfstudentCompetitionGuidancePerformance SCG,Tfterm TERM where TERM.termId=SCG.termId"
								+ " and SCG.spareTire='1'"
								+ " and TERM.spareTire='1'"
								+ " and SCG.checkOut='" + checkOut + "'"
								+ " and SCG.tfstudentCompetitionGuidanceScore.spareTire='1'"
								+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceCompetitionType.spareTire='1'"
								+ " and SCG.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceRewardLevel.spareTire='1'"
								+ " and SCG.teacher.spareTire='1'"
								+ " and SCG.teacher.department.spareTire='1'"
								+ " and SCG.teacher.department.departmentId='"+department.getDepartmentId()+"'"
								+ " and SCG.termId='"+termId+"'"
								+ " order by SCG.competitionId asc");
				// 判断是否为分页操作
				if (!isDivided) {
					//如果不是分页操作，取出所有符合条件的记录
					tfStudentCompetitionGuidancePerformanceList = this.getSession()
							.createQuery(hqlBuffer.toString()).list();
					int recordNumber=tfStudentCompetitionGuidancePerformanceList.size();
					session.put("pageCount_SCG", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
					session.put("recordNumber_SCG", tfStudentCompetitionGuidancePerformanceList.size());
				} 
				//无论是不是分页查询，都在后台进行分页操作。
				tfStudentCompetitionGuidancePerformanceList = this.getSession()
						.createQuery(hqlBuffer.toString())
						.setFirstResult((pageIndex - 1) * pageSize)
						.setMaxResults(pageSize).list();
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return tfStudentCompetitionGuidancePerformanceList;
	}

    
    public void save(TfstudentCompetitionGuidancePerformance transientInstance) {
        log.debug("saving TfstudentCompetitionGuidancePerformance instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TfstudentCompetitionGuidancePerformance persistentInstance) {
        log.debug("deleting TfstudentCompetitionGuidancePerformance instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TfstudentCompetitionGuidancePerformance findById( java.lang.Integer id) {
        log.debug("getting TfstudentCompetitionGuidancePerformance instance with id: " + id);
        try {
            TfstudentCompetitionGuidancePerformance instance = (TfstudentCompetitionGuidancePerformance) getSession()
                    .get("com.nuaa.ec.model.TfstudentCompetitionGuidancePerformance", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TfstudentCompetitionGuidancePerformance instance) {
        log.debug("finding TfstudentCompetitionGuidancePerformance instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TfstudentCompetitionGuidancePerformance")
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
      log.debug("finding TfstudentCompetitionGuidancePerformance instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TfstudentCompetitionGuidancePerformance as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
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
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	

	public List findAll() {
		log.debug("finding all TfstudentCompetitionGuidancePerformance instances");
		try {
			String queryString = "from TfstudentCompetitionGuidancePerformance";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TfstudentCompetitionGuidancePerformance merge(TfstudentCompetitionGuidancePerformance detachedInstance) {
        log.debug("merging TfstudentCompetitionGuidancePerformance instance");
        try {
            TfstudentCompetitionGuidancePerformance result = (TfstudentCompetitionGuidancePerformance) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TfstudentCompetitionGuidancePerformance instance) {
        log.debug("attaching dirty TfstudentCompetitionGuidancePerformance instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TfstudentCompetitionGuidancePerformance instance) {
        log.debug("attaching clean TfstudentCompetitionGuidancePerformance instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}