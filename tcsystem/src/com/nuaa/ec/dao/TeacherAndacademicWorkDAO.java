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

import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndacademicWork;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndacademicWork entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndacademicWork
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndacademicWorkDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndacademicWorkDAO.class);
		//property constants
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();
	
	public boolean updateCheckoutStatus(List<TeacherAndacademicWork> TAAcademicWorkListToBeAudited){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TAAcademicWorkListToBeAudited.size();i++){
				session.update(TAAcademicWorkListToBeAudited.get(i));
			}
			tx=session.beginTransaction();
			tx.commit();
			updateFlag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return updateFlag;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findAllWithCondition(int pageIndex, int pageSize,
			String foredate, String afterdate, ResearchLab researchLab,
			String checkOut) {
		StringBuffer hql = null;
		List<TeacherAndacademicWork> list = new ArrayList<TeacherAndacademicWork>();
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据， 所以不显示数据
			 */
			session.put("recordNumber_TAAW", 0);
			session.put("pageCount_TAAW", 0);
			return list;
		} else {
			hql = new StringBuffer(
					"from TeacherAndacademicWork TAAW where "
							+ " TAAW.spareTire='1'"
							+ " and TAAW.academicWork.spareTire='1'"
							+ " and TAAW.academicWork.publishClub.spareTire='1'"
							+ " and TAAW.selfUndertakeTask.spareTire='1'"
							+ " and TAAW.teacher.spareTire='1' "
							+ " and TAAW.checkOut='"
							+ checkOut
							+ "' and TAAW.teacher.researchLab.researchLabId='"
							+ researchLab.getResearchLabId() + "'");
		}
		try {
			String append = " and TAAW.academicWork.publishDate between ? and ? ";
			String rank = "  order by TAAW.academicWork.acaworkId asc";
			/*
			 * 不一定有日期，所以要判断
			 */
			if (foredate != null && afterdate != null && foredate.length() != 0
					&& afterdate.length() != 0) {
				// 判断日期范围限制
				hql.append(append).append(rank);
				list = this.getSession().createQuery(hql.append(rank).toString())
						.setString(0, foredate).setString(1, afterdate).list();
			} else {
				list = this.getSession().createQuery(hql.append(rank).toString()).list();
			}
			/*
			 * 总体查询完毕，把总记录数和总页数放入session用于前台展现。
			 */
			session.put("recordNumber_TAAW", list.size());
			session.put("pageCount_TAAW",
					list.size() % pageSize == 0 ? (list.size() / pageSize)
							: (list.size() / pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力， 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getTAAcademicWorkListAfterDivided(pageIndex, pageSize,
					foredate, afterdate, researchLab, checkOut);
		} catch (Exception ex) {
			log.error("find all failed", ex);
			System.out.println(ex.getMessage());
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TeacherAndacademicWork> getTAAcademicWorkListAfterDivided(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut) {
		List<TeacherAndacademicWork> list=null;
		StringBuffer hql = null;
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			hql = new StringBuffer(
					"from TeacherAndacademicWork TAAW where "
							+ " TAAW.spareTire=1"
							+ " and TAAW.academicWork.spareTire='1'"
							+ " and TAAW.academicWork.publishClub.spareTire='1'"
							+ " and TAAW.selfUndertakeTask.spareTire='1'"
							+ " and TAAW.teacher.spareTire='1' "
							+ " and TAAW.checkOut='"
							+ checkOut+"'");
		} else {
			hql = new StringBuffer(
					"from TeacherAndacademicWork TAAW  where"
							+ " TAAW.spareTire=1"
							+ " and TAAW.academicWork.spareTire='1'"
							+ " and TAAW.academicWork.publishClub.spareTire='1'"
							+ " and TAAW.selfUndertakeTask.spareTire='1'"
							+ " and TAAW.teacher.spareTire='1' "
							+ " and TAAW.checkOut='"
							+ checkOut
							+ "' and TAAW.teacher.researchLab.researchLabId=\'"
							+ researchLab.getResearchLabId() + "\'");
		}
		list = new ArrayList<TeacherAndacademicWork>();
		String append = " and TAAW.academicWork.publishDate between ? and ? ";
		String rank = "  order by TAAW.academicWork.acaworkId asc";
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			// 判断日期范围限制
			hql.append(append).append(rank);
			list = this.getSession().createQuery(hql.append(rank).toString())
					.setString(0, foredate).setString(1, afterdate)
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		} else {
			list = this.getSession().createQuery(hql.append(rank).toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();

		}
		return list;
	}

    
    public void save(TeacherAndacademicWork transientInstance) {
        log.debug("saving TeacherAndacademicWork instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TeacherAndacademicWork persistentInstance) {
        log.debug("deleting TeacherAndacademicWork instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TeacherAndacademicWork findById( java.lang.Integer id) {
        log.debug("getting TeacherAndacademicWork instance with id: " + id);
        try {
            TeacherAndacademicWork instance = (TeacherAndacademicWork) getSession()
                    .get("com.nuaa.ec.model.TeacherAndacademicWork", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TeacherAndacademicWork instance) {
        log.debug("finding TeacherAndacademicWork instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndacademicWork")
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
      log.debug("finding TeacherAndacademicWork instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndacademicWork as model where model." 
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
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	

	public List findAll() {
		log.debug("finding all TeacherAndacademicWork instances");
		try {
			String queryString = "from TeacherAndacademicWork";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TeacherAndacademicWork merge(TeacherAndacademicWork detachedInstance) {
        log.debug("merging TeacherAndacademicWork instance");
        try {
            TeacherAndacademicWork result = (TeacherAndacademicWork) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndacademicWork instance) {
        log.debug("attaching dirty TeacherAndacademicWork instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndacademicWork instance) {
        log.debug("attaching clean TeacherAndacademicWork instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}