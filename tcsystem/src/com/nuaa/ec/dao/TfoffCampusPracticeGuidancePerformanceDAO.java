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
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformance;
import com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformanceUnionTfterm;
import com.nuaa.ec.model.TfstudentCompetitionGuidancePerformanceUnionTfterm;
import com.nuaa.ec.teachingData.exportData.OffCampusPracticeGuidanceExcel;
import com.nuaa.ec.teachingData.exportData.UndergraduateTutorGuidanceExcel;
import com.nuaa.ec.utils.Statistics_asist;
import com.nuaa.ec.utils.stringstore;
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
	
	/***
	 * 获取 该 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSA(String foreterm,String afterterm,Department depart){
		try {
			String queryString = "select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(OCP.finalScore),0),ISNULL(avg(OCP.finalScore),0)) "
					+ "from TfoffCampusPracticeGuidancePerformance OCP,Tfterm TERM where TERM.termId=OCP.termId"
					+ " and OCP.spareTire='1'"
					+ " and OCP.checkOut='3'"
					+ " and TERM.spareTire='1'"
					+ " and OCP.tfoffCampusPracticeGuidanceLevel.spareTire='1'"
					+ " and OCP.teacher.spareTire='1'"
					+ " and OCP.termId between ? and ?"
					+ " and OCP.teacher.department=?";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foreterm).setParameter(1, afterterm)
					.setParameter(2, depart);
			if(queryObject.list().size()>0){
				return (Statistics_asist) queryObject.list().get(0);
			}else return null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	/**
	 * 校外实践指导模块的数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString ="select new com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformanceUnionTfterm(OCP,TERM) from TfoffCampusPracticeGuidancePerformance OCP,Tfterm TERM where TERM.termId=OCP.termId"
					+ " and OCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and OCP.tfoffCampusPracticeGuidanceLevel.spareTire='1'"
					+ " and OCP.teacher.spareTire='1'"
					+ " and OCP.termId between ? and ?"
					+ " and OCP.teacher.department=?"
					+"  and OCP.checkOut='3' "
					+ " order by OCP.offguidanceId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					OffCampusPracticeGuidanceExcel.generateExcel(
							stringstore.offCampusPracticeGuidance,
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
		List<TfoffCampusPracticeGuidancePerformanceUnionTfterm> list=new ArrayList<TfoffCampusPracticeGuidancePerformanceUnionTfterm>();
		StringBuffer hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId != null && termId.length() != 0 && searchCondition!=null && searchCondition.trim().length()!=0) {
			hql=new StringBuffer("select new com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformanceUnionTfterm(OCP,TERM) from TfoffCampusPracticeGuidancePerformance OCP,Tfterm TERM where TERM.termId=OCP.termId"
					+ " and OCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and OCP.tfoffCampusPracticeGuidanceLevel.spareTire='1'"
					+ " and OCP.teacher.spareTire='1'"
					+ " and OCP.termId='"+termId+"'"
					+ " and OCP.projectName like '%"+searchCondition+"%'"
					+ " order by OCP.offguidanceId desc");
		} else {
			hql=new StringBuffer("select new com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformanceUnionTfterm(OCP,TERM) from TfoffCampusPracticeGuidancePerformance OCP,Tfterm TERM where TERM.termId=OCP.termId"
					+ " and OCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and OCP.tfoffCampusPracticeGuidanceLevel.spareTire='1'"
					+ " and OCP.teacher.spareTire='1'");


			//有学期但是没有查询条件的情况
			if(termId != null && termId.length() != 0 &&(searchCondition==null || searchCondition.trim().length()==0)){
				hql.append(" and OCP.termId='"+termId+"'");
			}else if(searchCondition!=null && searchCondition.length()!=0 &&(termId == null || termId.length() == 0)){
				//有查询条件 但是没有学期的情况
				hql.append(" and OCP.projectName like '%"+searchCondition+"%'");
			}else{//学期和查询条件都没有的情况
				//这块没有业务逻辑，只是为了使得逻辑清楚
			}
			hql.append(" order by OCP.offguidanceId desc");
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql.toString()).list();
				int listSize=list.size();
				session.put("recordNumber_ATOCP",list.size());
				session.put("pageCount_ATOCP", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
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
		List<TfoffCampusPracticeGuidancePerformanceUnionTfterm> list=new ArrayList<TfoffCampusPracticeGuidancePerformanceUnionTfterm>();
		String hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId == null || termId.length() == 0) {
			hql="select new com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformanceUnionTfterm(OCP,TERM) from TfoffCampusPracticeGuidancePerformance OCP,Tfterm TERM where TERM.termId=OCP.termId"
					+ " and OCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and OCP.tfoffCampusPracticeGuidanceLevel.spareTire='1'"
					+ " and OCP.teacher.spareTire='1'"
					+ " and OCP.teacher=?"
					+ " order by OCP.offguidanceId desc";
		} else {
			hql="select new com.nuaa.ec.model.TfoffCampusPracticeGuidancePerformanceUnionTfterm(OCP,TERM) from TfoffCampusPracticeGuidancePerformance OCP,Tfterm TERM where TERM.termId=OCP.termId"
					+ " and OCP.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and OCP.tfoffCampusPracticeGuidanceLevel.spareTire='1'"
					+ " and OCP.teacher.spareTire='1'"
					+ " and OCP.teacher=?"
					+ " and OCP.termId='"+termId+"'"
					+ " order by OCP.offguidanceId desc";
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql).setParameter(0, teacherHaveLogin).list();
				int listSize=list.size();
				session.put("recordNumber_GTOCP",list.size());
				session.put("pageCount_GTOCP", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
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
				if (checkOut.equals("4")) {
					hqlBuffer = new StringBuffer(
							"select OCP from TfoffCampusPracticeGuidancePerformance OCP,Tfterm TERM where TERM.termId=OCP.termId"
									+ " and OCP.spareTire='1'"
									+ " and TERM.spareTire='1'"
									+ " and OCP.tfoffCampusPracticeGuidanceLevel.spareTire='1'"
									+ " and OCP.teacher.spareTire='1'"
									+ " and OCP.teacher.department.spareTire='1'"
									+ " and OCP.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " and OCP.termId='"+termId+"'"
									+ " order by OCP.offguidanceId desc");
				}else {
					
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
				}
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