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
import com.nuaa.ec.model.TalentProject;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndselectedTalentProject;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndselectedTalentProject entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndselectedTalentProject
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndselectedTalentProjectDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndselectedTalentProjectDAO.class);
		//property constants
	public static final String TPSELECTED_YEAR = "tpselectedYear";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();
	public boolean updateCheckoutStatus(List<TeacherAndselectedTalentProject> TASTalentProList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TASTalentProList.size();i++){
				session.update(TASTalentProList.get(i));
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
	public List findAllWithCondition(int pageIndex, int pageSize,
			String foredate, String afterdate, ResearchLab researchLab,
			String checkOut) {
		log.debug("finding all TeacherAndselectedTalentProject instances");
		StringBuffer hql = null;
		List<TeacherAndselectedTalentProject> list = new ArrayList<TeacherAndselectedTalentProject>();
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据， 所以不显示数据
			 */
			session.put("recordNumber_TAST", 0);
			session.put("pageCount_TAST", 0);
			return list;
		} else {
			hql = new StringBuffer(
					"from TeacherAndselectedTalentProject TAST where TAST.spareTire=1"
							+ " and TAST.talentProject.spareTire='1'"
							+ " and TAST.teacher.spareTire='1'"
							+ " and TAST.checkOut='"
							+ checkOut
							+ "' and TAST.teacher.researchLab.researchLabId='"
							+ researchLab.getResearchLabId() + "'");
		}
		try {
			String append = " and TAST.tpselectedYear between ? and ? ";
			String rank="  order by TAST.talentProject.talentProjectId desc,TAST.teacher.teacherId asc";
			/*
			 * 不一定有日期，所以要判断
			 */
			if (foredate != null && afterdate != null && foredate.length() != 0
					&& afterdate.length() != 0) {
				// 判断日期范围限制
				hql.append(append).append(rank);
				list = this.getSession().createQuery(hql.toString())
						.setString(0, foredate).setString(1, afterdate).list();
			} else {
				list = this.getSession().createQuery(hql.toString()).list();
			}
			/*
			 * 总体查询完毕，把总记录数和总页数放入session用于前台展现。
			 */
			session.put("recordNumber_TAST", list.size());
			session.put("pageCount_TAST",
					list.size() % pageSize == 0 ? (list.size() / pageSize)
							: (list.size() / pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力， 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getTASTalentProListsAfterDivided(pageIndex, pageSize,
					foredate, afterdate, researchLab, checkOut);
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TeacherAndselectedTalentProject> getTASTalentProListsAfterDivided(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut) {
		StringBuffer hql = null;
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			hql = new StringBuffer(
					"from TeacherAndselectedTalentProject TAST where TAST.spareTire=1"
							+ " and TAST.talentProject.spareTire='1'"
							+ " and TAST.teacher.spareTire='1'"
							+ " and TAST.checkOut='"
							+ checkOut + "'");
		} else {
			hql = new StringBuffer(
					"from TeacherAndselectedTalentProject TAST where TAST.spareTire='1'"
							+ " and TAST.talentProject.spareTire='1'"
							+ " and TAST.teacher.spareTire='1'"
							+ " and TAST.checkOut='"+ checkOut
							+ "' and TAST.teacher.researchLab.researchLabId=\'"
							+ researchLab.getResearchLabId() + "\'");
		}
		List<TeacherAndselectedTalentProject> list = new ArrayList<TeacherAndselectedTalentProject>();
		String append = " and TAST.tpselectedYear between ? and ? ";
		String rank="  order by TAST.talentProject.talentProjectId desc,TAST.teacher.teacherId asc";
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			// 判断日期范围限制
			hql.append(append).append(rank);
			list = this.getSession().createQuery(hql.toString())
					.setString(0, foredate).setString(1, afterdate)
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		} else {
			list = this.getSession().createQuery(hql.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageSize).list();
		}
		return list;
	}

    
    public void save(TeacherAndselectedTalentProject transientInstance) {
        log.debug("saving TeacherAndselectedTalentProject instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TeacherAndselectedTalentProject persistentInstance) {
        log.debug("deleting TeacherAndselectedTalentProject instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TeacherAndselectedTalentProject findById( java.lang.Integer id) {
        log.debug("getting TeacherAndselectedTalentProject instance with id: " + id);
        try {
            TeacherAndselectedTalentProject instance = (TeacherAndselectedTalentProject) getSession()
                    .get("com.nuaa.ec.model.TeacherAndselectedTalentProject", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TeacherAndselectedTalentProject instance) {
        log.debug("finding TeacherAndselectedTalentProject instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndselectedTalentProject")
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
      log.debug("finding TeacherAndselectedTalentProject instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndselectedTalentProject as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public List findPageing(int currentRow,int limitRow,String condition,Teacher teacher){
    	try {
            String queryString = "from TeacherAndselectedTalentProject as tp "
            		+ "where tp.spareTire='1' "
            		+ "and tp.talentProject.spareTire='1' "
            		+ "and tp.teacher.spareTire='1' "
            		+ "and tp.teacher=? "
            		+ "and tp.selectedTalentProjectScore.spareTire='1' "
            		+ condition
            		+" order by tp.talentProject.selectedDate desc";
            Query queryObject = getSession().createQuery(queryString).setParameter(0, teacher).setFirstResult(currentRow).setMaxResults(limitRow);
   		 return queryObject.list();
         } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
         }
    }
    
    public int getRows(String condition,Teacher teacher){
    	try {
            String queryString = "select new com.nuaa.ec.model.TeacherMember(tp.teacher.teacherId,tp.teacher.teacherName,'') "
            		+ "from TeacherAndselectedTalentProject as tp "
            		+ "where tp.spareTire='1' "
            		+ "and tp.talentProject.spareTire='1' "
            		+ "and tp.teacher.spareTire='1' "
            		+ "and tp.teacher=? "
            		+ "and tp.selectedTalentProjectScore.spareTire='1' "
            		+ condition;
            Query queryObject = getSession().createQuery(queryString).setParameter(0, teacher);
   		 return queryObject.list().size();
         } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
         }
    }
    
    public List findMember(TalentProject tp){
    	try {
            String queryString = "select new com.nuaa.ec.model.TeacherMember(tat.teacher.teacherId,tat.teacher.teacherName,'') "
            		+ "from TeacherAndselectedTalentProject tat "
            		+ "where talentProject=? "
            		+ "and teacher.spareTire='1' "
            		+ "and talentProject.spareTire='1' "
            		+ "and spareTire='1' ";
            Query queryObject = getSession().createQuery(queryString).setParameter(0, tp);
   		 	return queryObject.list();
         } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
         }
    }
    
	public void deleteBylogic(TalentProject tp){
		try {
			String queryString = "update TeacherAndselectedTalentProject set spareTire ='0' "
					+ "where spareTire='1' "
					+ "and talentProject=? ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, tp);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    
	public void quitProjec(Teacher teacher,TalentProject tp){
		try {
			String queryString = "update TeacherAndselectedTalentProject set spareTire ='0' "
					+ "where spareTire='1' "
					+ "and talentProject=? "
					+ "and teacher=? ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, tp).setParameter(1, teacher);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public boolean checkexist(Teacher teacher,TalentProject tp){
		try {
			String queryString = "from TeacherAndselectedTalentProject  "
					+ "where spareTire='1' "
					+ "and talentProject=? "
					+ "and teacher=? "
					+ "and talentProject.spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, tp).setParameter(1, teacher);
	         if(queryObject.list().size()>0){
	        	 return false;
	         }else return true;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findByTpselectedYear(Object tpselectedYear
	) {
		return findByProperty(TPSELECTED_YEAR, tpselectedYear
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
	

	
	
    public TeacherAndselectedTalentProject merge(TeacherAndselectedTalentProject detachedInstance) {
        log.debug("merging TeacherAndselectedTalentProject instance");
        try {
            TeacherAndselectedTalentProject result = (TeacherAndselectedTalentProject) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndselectedTalentProject instance) {
        log.debug("attaching dirty TeacherAndselectedTalentProject instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndselectedTalentProject instance) {
        log.debug("attaching clean TeacherAndselectedTalentProject instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}