package com.nuaa.ec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.InvitedExpertsSpeech;
import com.nuaa.ec.model.JoinAcademicMeeting;
import com.nuaa.ec.model.ResearchLab;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for InvitedExpertsSpeech entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.InvitedExpertsSpeech
  * @author MyEclipse Persistence Tools 
 */
public class InvitedExpertsSpeechDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(InvitedExpertsSpeechDAO.class);
		//property constants
	public static final String EXPERTS_NAME = "expertsName";
	public static final String LECTURE_NAME = "lectureName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String CHECKOUT = "checkout";
	public static final String SPEECH_DATE = "speechDate";
	private Map<String,Object> session=ActionContext.getContext().getSession();


	/**
	 * 所长审核功能
	 * 
	 * @param invitedExpertsSpeechs
	 * @return
	 */
	public boolean updateCheckoutStatus(
			List<InvitedExpertsSpeech> invitedExpertsSpeechs) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < invitedExpertsSpeechs.size(); i++) {
				session.update(invitedExpertsSpeechs.get(i));
			}
			tx = session.beginTransaction();
			tx.commit();
			updateFlag = true;
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return updateFlag;
	}
	/**
	 * 如果项目没有通过那么项目里的所有成员都将不通过。
	 * 
	 * @param invitedExpertsSpeechs
	 * @return
	 */
	public boolean cascadeUpdateCheckOutOfMembers(
			List<InvitedExpertsSpeech> invitedExpertsSpeechs,String flag) {
		boolean operationFlag=false;
		Session session = this.getSession();
		Transaction tx=null;
		try{
			for (InvitedExpertsSpeech ies : invitedExpertsSpeechs) {
				session.createQuery(
						"UPDATE TeacherAndinvitedExpertsSpeech TAIES SET TAIES.checkOut="+flag
						+ " WHERE TAIES.invitedExpertsSpeech.iespeechId='"+ ies.getIespeechId()+"'").executeUpdate();
			}
			tx=session.beginTransaction();
			tx.commit();
			operationFlag=true;
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
		}
		return operationFlag;
	}

	/**
	 * function：获得符合条件的所有参加学术会议的记录
	 * @param
	 * @param transientInstance
	 */
    @SuppressWarnings("unchecked")
	public List<InvitedExpertsSpeech> getAllRecordsWithCondition(int pageIndex,
			int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut, boolean isDivided){
    	StringBuffer hql=new StringBuffer("FROM InvitedExpertsSpeech IES WHERE IES.spareTire='1'"
    			+ " AND IES.researchLabId='"+researchLab.getResearchLabId()+"'"
				+ " AND IES.nationality.spareTire='1'"
				+ " AND IES.expertType.spareTire='1'");
    	List<InvitedExpertsSpeech> invitedExpertsSpeechs=new ArrayList<InvitedExpertsSpeech>();
		if (checkOut != null && checkOut.length() != 0
				&& !checkOut.trim().equals("4")) {
			hql.append(" AND IES.checkout='" + checkOut + "'");
		}
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			hql.append(" AND IES.speechDate BETWEEN '"+foredate+"' AND '"+afterdate+"'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		if(!isDivided){
			invitedExpertsSpeechs=query.list();
			int size=invitedExpertsSpeechs.size();
			session.put("pageCount_GTIES", size%pageSize==0?(size/pageSize):(size/pageSize+1));
			session.put("recordNumber_GTIES", size);
		}
		invitedExpertsSpeechs=query.setMaxResults(pageSize).setFirstResult((pageIndex-1)*pageSize).list();
    	return invitedExpertsSpeechs;
    }

    
    public void save(InvitedExpertsSpeech transientInstance) {
        log.debug("saving InvitedExpertsSpeech instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(InvitedExpertsSpeech persistentInstance) {
        log.debug("deleting InvitedExpertsSpeech instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public InvitedExpertsSpeech findById( java.lang.String id) {
        log.debug("getting InvitedExpertsSpeech instance with id: " + id);
        try {
            InvitedExpertsSpeech instance = (InvitedExpertsSpeech) getSession()
                    .get("com.nuaa.ec.model.InvitedExpertsSpeech", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(InvitedExpertsSpeech instance) {
        log.debug("finding InvitedExpertsSpeech instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.InvitedExpertsSpeech")
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
      log.debug("finding InvitedExpertsSpeech instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from InvitedExpertsSpeech as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByExpertsName(Object expertsName
	) {
		return findByProperty(EXPERTS_NAME, expertsName
		);
	}
	
	public List findByLectureName(Object lectureName
	) {
		return findByProperty(LECTURE_NAME, lectureName
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByChargePersonId(Object chargePersonId
	) {
		return findByProperty(CHARGE_PERSON_ID, chargePersonId
		);
	}
	
	public List findByChargePerson(Object chargePerson
	) {
		return findByProperty(CHARGE_PERSON, chargePerson
		);
	}
	
	public List findByCheckout(Object checkout
	) {
		return findByProperty(CHECKOUT, checkout
		);
	}
	
	public List findBySpeechDate(Object speechDate
	) {
		return findByProperty(SPEECH_DATE, speechDate
		);
	}
	

	public List findAll() {
		log.debug("finding all InvitedExpertsSpeech instances");
		try {
			String queryString = "from InvitedExpertsSpeech";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMember(InvitedExpertsSpeech iespeech){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,'') "
					+ "from TeacherAndinvitedExpertsSpeech t "
					+ "where t.spareTire='1' "
					+ "and t.invitedExpertsSpeech.spareTire='1' "
					+ "and t.invitedExpertsSpeech=? "
					+ "and t.teacher.spareTire='1' "
					+ "and t.invitedExpertsSpeechScore.spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, iespeech);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findMembersano(InvitedExpertsSpeech iespeech){
		try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(t.teacher.teacherId,t.teacher.teacherName,t.selfUndertakeTask.undertakeTaskName) "
					+ "from TeacherAndinvitedExpertsSpeech t "
					+ "where t.spareTire='1' "
					+ "and t.invitedExpertsSpeech.spareTire='1' "
					+ "and t.invitedExpertsSpeech=? "
					+ "and t.teacher.spareTire='1' "
					+ "and t.invitedExpertsSpeechScore.spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, iespeech);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteINvites(String iespeechId){
		try {
			String queryString = "update InvitedExpertsSpeech set spareTire='0' "
					+ "where iespeechId=?";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, iespeechId);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPageing(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from InvitedExpertsSpeech where spareTire='1' "
					+ "and nationality.spareTire='1' "
					+ "and expertType.spareTire='1' "
					+ condition+" order by speechDate desc";
	         Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow);
	         queryObject.setMaxResults(limitRow);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition){
		try {
			String queryString = "from InvitedExpertsSpeech where spareTire='1' "
					+ "and nationality.spareTire='1' "
					+ "and expertType.spareTire='1' "
					+ condition;
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public InvitedExpertsSpeech merge(InvitedExpertsSpeech detachedInstance) {
        log.debug("merging InvitedExpertsSpeech instance");
        try {
            InvitedExpertsSpeech result = (InvitedExpertsSpeech) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(InvitedExpertsSpeech instance) {
        log.debug("attaching dirty InvitedExpertsSpeech instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(InvitedExpertsSpeech instance) {
        log.debug("attaching clean InvitedExpertsSpeech instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}