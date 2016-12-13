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

import com.nuaa.ec.model.JoinAcademicMeeting;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndjoinAcademicMeeting;
import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.nuaa.ec.scienresearch.exportdata.AcademicWorkExcel;
import com.nuaa.ec.scienresearch.exportdata.JoinAcademicMeetingExcel;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndjoinAcademicMeeting entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndjoinAcademicMeeting
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndjoinAcademicMeetingDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndjoinAcademicMeetingDAO.class);
		//property constants
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();
	
	/**
	 * 参加学术会议模块的数据导出
	 */
	 public ByteArrayOutputStream findwithexport(ResearchLab research,String condition,String researchLabName,String foredate,String afterdate){
		 try{
			String queryString = "FROM TeacherAndjoinAcademicMeeting TAJAM "
					+ " WHERE TAJAM.spareTire='1' "
					+ " AND TAJAM.teacher.spareTire='1'"
					+ " AND TAJAM.meetingPaper.spareTire='1' "
					+ " AND TAJAM.meetingPaper.paperRetrievalCondition.spareTire='1' "
					+ " AND TAJAM.joinAcademicMeeting.spareTire='1' "
					+ " AND TAJAM.joinAcademicMeeting.meetingPlace.spareTire='1' "
					+ " AND TAJAM.joinAcademicMeeting.meetingType.spareTire='1' "
					+condition
					+ " AND TAJAM.teacher.researchLab=? "
					+ " ORDER by TAJAM.joinAcademicMeeting.joinAcaMid desc ";
	    	Query queryObject = getSession().createQuery(queryString).setParameter(0, research);
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	if(queryObject.list().size()>0){
	    		try {
					JoinAcademicMeetingExcel.generateExcel(stringstore.joinacademicw, queryObject.list(), researchLabName, foredate, afterdate).write(baos);
				} catch (IOException e) {
					e.printStackTrace();
				}
	    		return baos;
			}else{
				return null;
			}
		 }catch(RuntimeException re){
			 log.error("find by property name failed", re);
				throw re;
		 }
	 }
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List findAllWithCondition(int pageIndex, int pageSize,
			String foredate, String afterdate, ResearchLab researchLab,
			String checkOut) {
		log.debug("finding all TeacherAndjoinAcademicMeeting instances");
		StringBuffer hql = null;
		List<TeacherAndjoinAcademicMeeting> list = new ArrayList<TeacherAndjoinAcademicMeeting>();
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据， 所以不显示数据
			 */
			session.put("recordNumber_TAAM", 0);
			session.put("pageCount_TAAM", 0);
			return list;
		} else {
			hql = new StringBuffer(
					"from TeacherAndjoinAcademicMeeting TAAM where "
							+ " TAAM.spareTire='1'"
							+ " and TAAM.meetingPaper.spareTire='1'"
							+ " and TAAM.joinAcademicMeetingScore.spareTire='1'"
							+ " and TAAM.joinAcademicMeeting.spareTire='1'"
							+ " and TAAM.teacher.spareTire='1' "
//							+ " and TAAM.checkOut='"
//							+ checkOut
							+ " and TAAM.teacher.researchLab.researchLabId='"
							+ researchLab.getResearchLabId() + "'");
		}
		if(checkOut!=null && checkOut.length()!=0 && !checkOut.trim().equals("4")){
			hql.append(" AND TAAM.checkOut='"+checkOut+"'");
		}
		try {
			String append = " and TAAM.joinAcademicMeeting.meetingdate between ? and ? ";
			String rank = "  order by TAAM.joinAcademicMeeting.joinAcaMid desc";
			/*
			 * 不一定有日期，所以要判断
			 */
			if (foredate != null && afterdate != null && foredate.length() != 0
					&& afterdate.length() != 0) {
				// 判断日期范围限制
				hql.append(append);
				list = this.getSession().createQuery(hql.append(rank).toString())
						.setString(0, foredate).setString(1, afterdate).list();
			} else {
				list = this.getSession().createQuery(hql.append(rank).toString()).list();
			}
			/*
			 * 总体查询完毕，把总记录数和总页数放入session用于前台展现。
			 */
			session.put("recordNumber_TAAM", list.size());
			session.put("pageCount_TAAM",
					list.size() % pageSize == 0 ? (list.size() / pageSize)
							: (list.size() / pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力， 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getTAMMeetingAfterDivided(pageIndex, pageSize,
					foredate, afterdate, researchLab, checkOut);
		} catch (Exception ex) {
			log.error("find all failed", ex);
			System.out.println(ex.getMessage());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TeacherAndjoinAcademicMeeting> getTAMMeetingAfterDivided(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut) {
		StringBuffer hql = null;
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			hql = new StringBuffer(
					"from TeacherAndjoinAcademicMeeting TAAM where "
							+ " TAAM.spareTire=1"
							+ " and TAAM.meetingPaper.spareTire='1'"
							+ " and TAAM.joinAcademicMeetingScore.spareTire='1'"
							+ " and TAAM.joinAcademicMeeting.spareTire='1'"
							+ " and TAAM.teacher.spareTire='1' ");
//							+ " and TAAM.checkOut='"
//							+ checkOut+"'");
		} else {
			hql = new StringBuffer(
					"from TeacherAndjoinAcademicMeeting TAAM  where"
							+ " TAAM.spareTire=1"
							+ " and TAAM.meetingPaper.spareTire='1'"
							+ " and TAAM.joinAcademicMeetingScore.spareTire='1'"
							+ " and TAAM.joinAcademicMeeting.spareTire='1'"
							+ " and TAAM.teacher.spareTire='1' "
//							+ " and TAAM.checkOut='"
//							+ checkOut
							+ " and TAAM.teacher.researchLab.researchLabId=\'"
							+ researchLab.getResearchLabId() + "\'");
		}
		if(checkOut!=null && checkOut.length()!=0 && !checkOut.trim().equals("4")){
			hql.append(" AND TAAM.checkOut='"+checkOut+"'");
		}
		List<TeacherAndjoinAcademicMeeting> list = new ArrayList<TeacherAndjoinAcademicMeeting>();
		String append = " and TAAM.joinAcademicMeeting.meetingdate between ? and ? ";
		String rank = "  order by TAAM.joinAcademicMeeting.joinAcaMid desc";
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			// 判断日期范围限制
			hql.append(append);
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

	public boolean updateCheckoutStatus(List<TeacherAndjoinAcademicMeeting> TAMMeetingListToBeAudited){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TAMMeetingListToBeAudited.size();i++){
				session.update(TAMMeetingListToBeAudited.get(i));
			}
			tx=session.beginTransaction();
			tx.commit();
			updateFlag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return updateFlag;
	}
    public void save(TeacherAndjoinAcademicMeeting transientInstance) {
        log.debug("saving TeacherAndjoinAcademicMeeting instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TeacherAndjoinAcademicMeeting persistentInstance) {
        log.debug("deleting TeacherAndjoinAcademicMeeting instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TeacherAndjoinAcademicMeeting findById( java.lang.Integer id) {
        log.debug("getting TeacherAndjoinAcademicMeeting instance with id: " + id);
        try {
            TeacherAndjoinAcademicMeeting instance = (TeacherAndjoinAcademicMeeting) getSession()
                    .get("com.nuaa.ec.model.TeacherAndjoinAcademicMeeting", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TeacherAndjoinAcademicMeeting instance) {
        log.debug("finding TeacherAndjoinAcademicMeeting instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndjoinAcademicMeeting")
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
      log.debug("finding TeacherAndjoinAcademicMeeting instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndjoinAcademicMeeting as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

    public boolean checkexist(Teacher teacher,JoinAcademicMeeting joinacam){
    	try {
            String queryString = "from TeacherAndjoinAcademicMeeting "
            		+ "where teacher=? "
            		+ "and joinAcademicMeeting=? "
            		+ "and spareTire='1' "
            		+ "and teacher.spareTire='1' "
            		+ "and joinAcademicMeeting.spareTire='1' "; 
            Query queryObject = getSession().createQuery(queryString);
   		 	queryObject.setParameter(0, teacher);
   		 	queryObject.setParameter(1, joinacam);
   		 	if(queryObject.list().size()>0){
   		 		return true;
   		 	}else return false;
         } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
         }
    }
    
    public void quitObject(Teacher teacher,JoinAcademicMeeting joinacam){
    	try {
            String queryString = "update TeacherAndjoinAcademicMeeting set spareTire='0' where teacher=:teacher and joinAcademicMeeting=:joinacam "; 
            Query queryObject = getSession().createQuery(queryString);
   		 	queryObject.setParameter("teacher", teacher);
   		 	queryObject.setParameter("joinacam", joinacam);
   		 	queryObject.executeUpdate();
         } catch (RuntimeException re) {
            log.error("update TeacherAndjoinAcademicMeeting failed", re);
            throw re;
         }
    }
    
    public List findPageing(int currentRow,int lmitRow,String condition,Teacher teacher){
    	try {
            String queryString = "from TeacherAndjoinAcademicMeeting where spareTire='1' "
            		+ "and joinAcademicMeeting.spareTire='1' "
            		+ "and teacher.spareTire='1' "
            		+ "and teacher=? "+condition+" order by joinAcademicMeeting.meetingdate desc"; 
            Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow);
            queryObject.setMaxResults(lmitRow);
   		 	queryObject.setParameter(0, teacher);
   		 	return queryObject.list();
         } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
         }
    }
    
    public int getRows(String condition,Teacher teacher){
    	try {
            String queryString = "from TeacherAndjoinAcademicMeeting where spareTire='1' "
            		+ "and joinAcademicMeeting.spareTire='1' "
            		+ "and teacher.spareTire='1' "
            		+ "and teacher=? "+condition; 
            Query queryObject = getSession().createQuery(queryString);
   		 	queryObject.setParameter(0, teacher);
   		 	return queryObject.list().size();
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
		log.debug("finding all TeacherAndjoinAcademicMeeting instances");
		try {
			String queryString = "from TeacherAndjoinAcademicMeeting";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deletRefJoinAcademic(String joinacademicId){
		try {
			String queryString = "update TeacherAndjoinAcademicMeeting set spareTire='0' "
					+ "where joinAcademicMeeting.joinAcaMid=? "
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, joinacademicId);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findJMember(String joinacaMiD){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(tj.teacher.teacherId,tj.teacher.teacherName,'') "
					+ "from TeacherAndjoinAcademicMeeting tj "
					+ "where tj.joinAcademicMeeting.joinAcaMid=? "
					+ "and tj.spareTire='1' "
					+ "and tj.joinAcademicMeeting.spareTire='1' "
					+ "and tj.joinAcademicMeetingScore.spareTire='1'";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, joinacaMiD);
	         return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findJMembersano(String joinacaMiD){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(tj.teacher.teacherId,tj.teacher.teacherName,'') "
					+ "from TeacherAndjoinAcademicMeeting tj "
					+ "where tj.joinAcademicMeeting.joinAcaMid=? "
					+ "and tj.spareTire='1' "
					+ "and tj.joinAcademicMeeting.spareTire='1' "
					+ "and tj.joinAcademicMeetingScore.spareTire='1'";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, joinacaMiD);
	         return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TeacherAndjoinAcademicMeeting merge(TeacherAndjoinAcademicMeeting detachedInstance) {
        log.debug("merging TeacherAndjoinAcademicMeeting instance");
        try {
            TeacherAndjoinAcademicMeeting result = (TeacherAndjoinAcademicMeeting) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndjoinAcademicMeeting instance) {
        log.debug("attaching dirty TeacherAndjoinAcademicMeeting instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndjoinAcademicMeeting instance) {
        log.debug("attaching clean TeacherAndjoinAcademicMeeting instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}