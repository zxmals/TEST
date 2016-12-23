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
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformance;
import com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformanceUnionTfterm;
import com.nuaa.ec.teachingData.exportData.EnterpriseWorkStationExcel;
import com.nuaa.ec.teachingData.exportData.SummerAndInternationCourseConstructionExcel;
import com.nuaa.ec.utils.Statistics_asist;
import com.nuaa.ec.utils.stringstore;
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
	
	/***
	 * 获取 该 统计信息
	 * @param foreterm
	 * @param afterterm
	 * @param depart
	 * @return
	 */
	public Statistics_asist getSA(String foreterm,String afterterm,Department depart){
		try {
			String queryString = "select new com.nuaa.ec.utils.Statistics_asist(ISNULL(sum(SCI.score),0),ISNULL(avg(SCI.score),0)) "
					+ "from TfsummerCourseInternationalConstructionPerformance SCI,Tfterm TERM where TERM.termId=SCI.termId"
					+ " and SCI.spareTire='1'"
					+ " and SCI.checkOut='3'"
					+ " and TERM.spareTire='1'"
					+ " and SCI.tfsummerCourseInternationalConstructionLevel.spareTire='1'"
					+ " and SCI.teacher.spareTire='1'"
					+ " and SCI.termId between ? and ?"
					+ " and SCI.teacher.department=?";
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
	 *暑期课程与国际课程的建设数据导出
	 */
	@SuppressWarnings("unchecked")
	public ByteArrayOutputStream findwithexport(Department department,
			String foredate, String afterdate, String departmentName) {
		try {
			String queryString ="select new com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformanceUnionTfterm(SCI,TERM) from TfsummerCourseInternationalConstructionPerformance SCI,Tfterm TERM where TERM.termId=SCI.termId"
					+ " and SCI.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCI.tfsummerCourseInternationalConstructionLevel.spareTire='1'"
					+ " and SCI.teacher.spareTire='1'"
					+ " and SCI.termId between ? and ?"
					+ " and SCI.teacher.department=?"
					+ " order by SCI.projectId desc";
			Query queryObject = getSession().createQuery(queryString)
					.setParameter(0, foredate).setParameter(1, afterdate)
					.setParameter(2, department);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			if (queryObject.list().size() > 0) {
				try {
					SummerAndInternationCourseConstructionExcel.generateExcel(
							stringstore.summerInternationalCourse,
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
		List<TfsummerCourseInternationalConstructionPerformanceUnionTfterm> list=new ArrayList<TfsummerCourseInternationalConstructionPerformanceUnionTfterm>();
		StringBuffer hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId != null && termId.length() != 0 && searchCondition!=null && searchCondition.trim().length()!=0 ) {
			hql=new StringBuffer("select new com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformanceUnionTfterm(SCI,TERM) from TfsummerCourseInternationalConstructionPerformance SCI,Tfterm TERM where TERM.termId=SCI.termId"
					+ " and SCI.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCI.tfsummerCourseInternationalConstructionLevel.spareTire='1'"
					+ " and SCI.teacher.spareTire='1'"
					+ " and SCI.termId='"+termId+"'"
					+ " and SCI.projectName like '%"+searchCondition+"%'"
					+ " order by SCI.projectId desc");
		} else {
			hql=new StringBuffer("select new com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformanceUnionTfterm(SCI,TERM) from TfsummerCourseInternationalConstructionPerformance SCI,Tfterm TERM where TERM.termId=SCI.termId"
					+ " and SCI.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCI.tfsummerCourseInternationalConstructionLevel.spareTire='1'"
					+ " and SCI.teacher.spareTire='1'");
			//有学期但是没有查询条件的情况
			if(termId != null && termId.length() != 0 &&(searchCondition==null || searchCondition.trim().length()==0)){
				hql.append(" and SCI.termId='"+termId+"'");
			}else if(searchCondition!=null && searchCondition.length()!=0 &&(termId == null || termId.length() == 0)){
				//有查询条件 但是没有学期的情况
				hql.append(" and SCI.projectName like '%"+searchCondition+"%'");
			}else{//学期和查询条件都没有的情况
				//这块没有业务逻辑，只是为了使得逻辑清楚
			}
			hql.append(" order by SCI.projectId desc");//指定结果集排序规则
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql.toString()).list();
				int listSize=list.size();
				session.put("recordNumber_ATSCI",list.size());
				session.put("pageCount_ATSCI", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
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
		List<TfsummerCourseInternationalConstructionPerformanceUnionTfterm> list=new ArrayList<TfsummerCourseInternationalConstructionPerformanceUnionTfterm>();
		String hql = null;
		/*
		 * 第一次进来的时候 TermID应该为空，默认取出当前教师所有的数据
		 */
		if (termId == null || termId.length() == 0) {
			hql="select new com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformanceUnionTfterm(SCI,TERM) from TfsummerCourseInternationalConstructionPerformance SCI,Tfterm TERM where TERM.termId=SCI.termId"
					+ " and SCI.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCI.tfsummerCourseInternationalConstructionLevel.spareTire='1'"
					+ " and SCI.teacher.spareTire='1'"
					+ " and SCI.teacher=?"
					+ " order by SCI.projectId desc";
		} else {
			hql="select new com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformanceUnionTfterm(SCI,TERM)select new com.nuaa.ec.model.TfsummerCourseInternationalConstructionPerformanceUnionTfterm(SCI,TERM) from TfsummerCourseInternationalConstructionPerformance SCI,Tfterm TERM where TERM.termId=SCI.termId"
					+ " and SCI.spareTire='1'"
					+ " and TERM.spareTire='1'"
					+ " and SCI.tfsummerCourseInternationalConstructionLevel.spareTire='1'"
					+ " and SCI.teacher.spareTire='1'"
					+ " and SCI.teacher=?"
					+ " and SCI.termId='"+termId+"'"
					+ " order by SCI.projectId desc";
		}
		try{
			if(!isDivided){
				list=this.getSession().createQuery(hql).setParameter(0, teacherHaveLogin).list();
				int listSize=list.size();
				session.put("recordNumber_GTSCI",list.size());
				session.put("pageCount_GTSCI", listSize%pageSize==0?(listSize/pageSize):(listSize/pageSize+1));
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
				if (checkOut.equals("4")) {
					hqlBuffer = new StringBuffer(
							"select SCI from TfsummerCourseInternationalConstructionPerformance SCI,Tfterm TERM where TERM.termId=SCI.termId"
									+ " and SCI.spareTire='1'"
									+ " and TERM.spareTire='1'"
									+ " and SCI.tfsummerCourseInternationalConstructionLevel.spareTire='1'"
									+ " and SCI.teacher.spareTire='1'"
									+ " and SCI.teacher.department.spareTire='1'"
									+ " and SCI.teacher.department.departmentId='"+department.getDepartmentId()+"'"
									+ " and SCI.termId='"+termId+"'"
									+ " order by SCI.projectId desc");
				}else {
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
									+ " order by SCI.projectId desc");
					
				}
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