package com.nuaa.ec.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.InvitedExpertsSpeech;
import com.nuaa.ec.model.InvitedExpertsSpeechScore;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech;
import com.nuaa.ec.model.TeacherAndjoinAcademicMeeting;
import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.nuaa.ec.scienresearch.exportdata.InviteExpertsSpeechExcel;
import com.nuaa.ec.scienresearch.exportdata.SelectedTalenteProjectExcel;
import com.nuaa.ec.summaryDataModel.InviteExpertSpeechData;
import com.nuaa.ec.utils.NumberFormatUtil;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndinvitedExpertsSpeech entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndinvitedExpertsSpeechDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndinvitedExpertsSpeechDAO.class);
		//property constants
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String,Object> session=ActionContext.getContext().getSession();
	/**
	 * 邀请专家讲学的数据汇总（按照教师个人汇总）
	 */
	public InviteExpertSpeechData getSummaryDataByTeacher(Teacher teacher,String foredate,String afterdate) throws Exception{
		StringBuffer hql = new StringBuffer(
				"SELECT SUM(TAIES.finalScore),AVG(TAIES.finalScore) FROM TeacherAndinvitedExpertsSpeech TAIES "
						+ "WHERE "
						+ " TAIES.invitedExpertsSpeech.speechDate between ? and ?"
						+ " AND TAIES.spareTire='1'"
						+ " AND TAIES.checkOut='3'"
						+ " AND TAIES.teacher=?");
		InviteExpertSpeechData inviteExpertSpeechData = new InviteExpertSpeechData();
		Object[] datas = (Object[]) this.getSession()
				.createQuery(hql.toString()).setParameter(0, foredate)
				.setParameter(1, afterdate).setParameter(2, teacher)
				.uniqueResult();
		if(datas[0]!=null){
			inviteExpertSpeechData.setSum(NumberFormatUtil.getNumberAfterTransferPrecision((Double) datas[0]));
		}else{
			inviteExpertSpeechData.setSum(0);
		}
		if(datas[1]!=null){
			inviteExpertSpeechData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision((Double) datas[1]));
		}else{
			inviteExpertSpeechData.setAvg(0);
		}
		return inviteExpertSpeechData;
	}
	
	/**
	 * 邀请专家讲学的数据汇总（按照研究所进行汇总）
	 */
	public InviteExpertSpeechData getSummaryDataByResearchLab(
			String researchLabId, String foredate, String afterdate)
			throws Exception {
		StringBuffer hql = new StringBuffer(
				"SELECT SUM(TAIES.finalScore),AVG(TAIES.finalScore) FROM TeacherAndinvitedExpertsSpeech TAIES "
						+ "WHERE "
						+ " TAIES.invitedExpertsSpeech.speechDate between ? and ?"
						+ " AND TAIES.spareTire='1'"
						+ " AND TAIES.checkOut='3'"
						+ " AND TAIES.teacher.researchLab.researchLabId=?");
		InviteExpertSpeechData inviteExpertSpeechData = new InviteExpertSpeechData();
		Object[] datas = (Object[]) this.getSession()
				.createQuery(hql.toString()).setParameter(0, foredate)
				.setParameter(1, afterdate).setParameter(2, researchLabId)
				.uniqueResult();
		if(datas[0]!=null){
			inviteExpertSpeechData.setSum(NumberFormatUtil.getNumberAfterTransferPrecision((Double) datas[0]));
		}else{
			inviteExpertSpeechData.setSum(0);
		}
		if(datas[1]!=null){
			inviteExpertSpeechData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision((Double) datas[1]));
		}else{
			inviteExpertSpeechData.setAvg(0);
		}
		return inviteExpertSpeechData;
	}
	
	
	/**
	 * 邀请专家讲学模块的数据导出
	 */
	 public ByteArrayOutputStream findwithexport(ResearchLab research,String condition,String researchLabName,String foredate,String afterdate){
		 try{
			String queryString = "FROM TeacherAndinvitedExpertsSpeech TAIES "
					+ " WHERE TAIES.spareTire='1' "
					+ " AND TAIES.teacher.spareTire='1'"
					+ " AND TAIES.selfUndertakeTask.spareTire='1' "
					+ " AND TAIES.invitedExpertsSpeech.spareTire='1' "
					+ " AND TAIES.invitedExpertsSpeech.nationality.spareTire='1' "
					+condition
					+ " AND TAIES.teacher.researchLab=? "
					+ " AND TAIES.checkOut='3'"
					+ " ORDER by TAIES.invitedExpertsSpeech.iespeechId desc ";
	    	Query queryObject = getSession().createQuery(queryString).setParameter(0, research);
	    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    	if(queryObject.list().size()>0){
	    		try {
					InviteExpertsSpeechExcel.generateExcel(stringstore.inviteExpertSpeech, queryObject.list(), researchLabName, foredate, afterdate).write(baos);
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
	public boolean updateCheckoutStatus(List<TeacherAndinvitedExpertsSpeech> TAExpertSpeechListToBeAudited){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TAExpertSpeechListToBeAudited.size();i++){
				session.update(TAExpertSpeechListToBeAudited.get(i));
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
		List<TeacherAndinvitedExpertsSpeech> list = new ArrayList<TeacherAndinvitedExpertsSpeech>();
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据， 所以不显示数据
			 */
			session.put("recordNumber_TAES", 0);
			session.put("pageCount_TAES", 0);
			return list;
		} else {
			hql = new StringBuffer(
					"from TeacherAndinvitedExpertsSpeech TAES where "
							+ " TAES.spareTire='1'"
							+ " and TAES.invitedExpertsSpeech.spareTire='1'"
							+ " and TAES.invitedExpertsSpeech.expertType.spareTire='1'"
							+ " and TAES.invitedExpertsSpeech.nationality.spareTire='1'"
							+ " and TAES.invitedExpertsSpeechScore.spareTire='1'"
							+ " and TAES.selfUndertakeTask.spareTire='1'"
							+ " and TAES.teacher.spareTire='1' "
//							+ " and TAES.checkOut='"
//							+ checkOut
							+ " and TAES.teacher.researchLab.researchLabId='"
							+ researchLab.getResearchLabId() + "'");
		}
		if(checkOut!=null && checkOut.length()!=0 && !checkOut.trim().equals("4")){
			hql.append(" AND TAES.checkOut='"+checkOut+"'");
		}
		try {
			String append = " and TAES.invitedExpertsSpeech.speechDate between ? and ? ";
			String rank = "  order by TAES.invitedExpertsSpeech.iespeechId desc";
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
			session.put("recordNumber_TAES", list.size());
			session.put("pageCount_TAES",
					list.size() % pageSize == 0 ? (list.size() / pageSize)
							: (list.size() / pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力， 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getTAExpertSpeechListAfterDivided(pageIndex, pageSize,
					foredate, afterdate, researchLab, checkOut);
		} catch (Exception ex) {
			log.error("find all failed", ex);
			System.out.println(ex.getMessage());
		}
		return list;
	}
	@SuppressWarnings("unchecked")
	public List<TeacherAndinvitedExpertsSpeech> getTAExpertSpeechListAfterDivided(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut) {
		List<TeacherAndinvitedExpertsSpeech> list=null;
		StringBuffer hql = null;
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			hql = new StringBuffer(
					"from TeacherAndinvitedExpertsSpeech TAES where "
							+ " TAES.spareTire=1"
							+ " and TAES.invitedExpertsSpeech.spareTire='1'"
							+ " and TAES.invitedExpertsSpeech.expertType.spareTire='1'"
							+ " and TAES.invitedExpertsSpeech.nationality.spareTire='1'"
							+ " and TAES.invitedExpertsSpeechScore.spareTire='1'"
							+ " and TAES.selfUndertakeTask.spareTire='1'"
							+ " and TAES.teacher.spareTire='1' ");
//							+ " and TAES.checkOut='"
//							+ checkOut+"'");
		} else {
			hql = new StringBuffer(
					"from TeacherAndinvitedExpertsSpeech TAES  where"
							+ " TAES.spareTire=1"
							+ " and TAES.invitedExpertsSpeech.spareTire='1'"
							+ " and TAES.invitedExpertsSpeech.expertType.spareTire='1'"
							+ " and TAES.invitedExpertsSpeech.nationality.spareTire='1'"
							+ " and TAES.invitedExpertsSpeechScore.spareTire='1'"
							+ " and TAES.selfUndertakeTask.spareTire='1'"
							+ " and TAES.teacher.spareTire='1' "
//							+ " and TAES.checkOut='"
//							+ checkOut
							+ " and TAES.teacher.researchLab.researchLabId=\'"
							+ researchLab.getResearchLabId() + "\'");
		}
		if(checkOut!=null && checkOut.length()!=0 && !checkOut.trim().equals("4")){
			hql.append(" AND TAES.checkOut='"+checkOut+"'");
		}
		list = new ArrayList<TeacherAndinvitedExpertsSpeech>();
		String append = " and TAES.invitedExpertsSpeech.speechDate between ? and ? ";
		String rank = "  order by TAES.invitedExpertsSpeech.iespeechId desc";
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
    public void save(TeacherAndinvitedExpertsSpeech transientInstance) {
        log.debug("saving TeacherAndinvitedExpertsSpeech instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(TeacherAndinvitedExpertsSpeech persistentInstance) {
        log.debug("deleting TeacherAndinvitedExpertsSpeech instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public TeacherAndinvitedExpertsSpeech findById( java.lang.Integer id) {
        log.debug("getting TeacherAndinvitedExpertsSpeech instance with id: " + id);
        try {
            TeacherAndinvitedExpertsSpeech instance = (TeacherAndinvitedExpertsSpeech) getSession()
                    .get("com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(TeacherAndinvitedExpertsSpeech instance) {
        log.debug("finding TeacherAndinvitedExpertsSpeech instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech")
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
      log.debug("finding TeacherAndinvitedExpertsSpeech instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndinvitedExpertsSpeech as model where model." 
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
		log.debug("finding all TeacherAndinvitedExpertsSpeech instances");
		try {
			String queryString = "from TeacherAndinvitedExpertsSpeech";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void quitproject(InvitedExpertsSpeech ies,Teacher teacher){
		try {
			String queryString = "update TeacherAndinvitedExpertsSpeech set spareTire='0' "
					+ "where invitedExpertsSpeech=? "
					+ "and teacher=? "
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, ies);
	         queryObject.setParameter(1, teacher);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public boolean checkexist(InvitedExpertsSpeech ies,Teacher teacher){
		try {
			String queryString = "from TeacherAndinvitedExpertsSpeech "
					+ "where invitedExpertsSpeech=? "
					+ "and teacher=? "
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, ies);
	         queryObject.setParameter(1, teacher);
	         if(queryObject.list().size()>0){
	        	 return false;
	         }else return true;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPageing(int currentRow,int limitRow,String condition,Teacher teacher){
		try {
			String queryString = "from TeacherAndinvitedExpertsSpeech where spareTire='1' "
					+ "and teacher.spareTire='1' "
					+ "and invitedExpertsSpeech.spareTire='1' "
					+ "and invitedExpertsSpeechScore.spareTire='1' "
					+ "and teacher=? "
					+ condition+" order by invitedExpertsSpeech.speechDate desc";
	         Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow);
	         queryObject.setParameter(0, teacher);
	         queryObject.setMaxResults(limitRow);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition,Teacher teacher){
		try {
			String queryString = "from TeacherAndinvitedExpertsSpeech where spareTire='1' "
					+ "and teacher.spareTire='1' "
					+ "and invitedExpertsSpeech.spareTire='1' "
					+ "and invitedExpertsSpeechScore.spareTire='1' "
					+ "and teacher=? "
					+ condition;
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, teacher);
			 return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteRefie(InvitedExpertsSpeech iespeech){
		try {
			String queryString = "update TeacherAndinvitedExpertsSpeech set spareTire='0' "
					+ "where invitedExpertsSpeech=?";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, iespeech);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void updateRefSpeech(InvitedExpertsSpeech iespeech,InvitedExpertsSpeechScore iescore,double score){
		try {
			String queryString = "update TeacherAndinvitedExpertsSpeech set invitedExpertsSpeechScore=?,finalScore=? "
					+ "where invitedExpertsSpeech=?"
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, iescore);
	         queryObject.setParameter(1, score);
	         queryObject.setParameter(2, iescore);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TeacherAndinvitedExpertsSpeech merge(TeacherAndinvitedExpertsSpeech detachedInstance) {
        log.debug("merging TeacherAndinvitedExpertsSpeech instance");
        try {
            TeacherAndinvitedExpertsSpeech result = (TeacherAndinvitedExpertsSpeech) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndinvitedExpertsSpeech instance) {
        log.debug("attaching dirty TeacherAndinvitedExpertsSpeech instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndinvitedExpertsSpeech instance) {
        log.debug("attaching clean TeacherAndinvitedExpertsSpeech instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}