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

import com.nuaa.ec.model.MainUndertakeAcademicMeeting;
import com.nuaa.ec.model.PeriodicalPapers;
import com.nuaa.ec.model.ResearchLab;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for MainUndertakeAcademicMeeting entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.MainUndertakeAcademicMeeting
  * @author MyEclipse Persistence Tools 
 */
public class MainUndertakeAcademicMeetingDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(MainUndertakeAcademicMeetingDAO.class);
		//property constants
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String ACA_MEETING_NAME = "acaMeetingName";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHECKOUT = "checkout";
	public static final String MEETINGDATE = "meetingdate";
	private Map<String,Object> session=ActionContext.getContext().getSession();
	
	/**
	 * 所长审核功能
	 * 
	 * @param mainUndertakeAcademicMeetings
	 * @return
	 */
	public boolean updateCheckoutStatus(
			List<MainUndertakeAcademicMeeting> mainUndertakeAcademicMeetings) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < mainUndertakeAcademicMeetings.size(); i++) {
				session.update(mainUndertakeAcademicMeetings.get(i));
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
	 * @param periodicalPaperList
	 * @return
	 */
	public boolean cascadeUpdateCheckOutOfMembers(
			List<MainUndertakeAcademicMeeting> mainUndertakeAcademicMeetings,String flag) {
		boolean operationFlag=false;
		Session session = this.getSession();
		Transaction tx=null;
		try{
			for (MainUndertakeAcademicMeeting maum : mainUndertakeAcademicMeetings) {
				session.createQuery(
						"UPDATE TeacherAndmainUndertakeAcademicMeeting TAMUAM SET TAMUAM.checkOut="+flag
						+ " WHERE TAMUAM.mainUndertakeAcademicMeeting.acaMeetingId='"+maum.getAcaMeetingId()+"'").executeUpdate();
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
	 * 获得符合查询条件的所有承担学术会议项目记录
	 * 
	 * @param transientInstance
	 */
	@SuppressWarnings("unchecked")
	public List<MainUndertakeAcademicMeeting> getAllRecordsWithCondition(int pageIndex,
			int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut, boolean isDivided) {
		List<MainUndertakeAcademicMeeting> mainUndtakAkdmicMetingList = new ArrayList<MainUndertakeAcademicMeeting>();
		StringBuffer hql = new StringBuffer("FROM MainUndertakeAcademicMeeting MUAM WHERE MUAM.spareTire='1'"
				+ " AND MUAM.mainUndertakeAcademicMeetingPlace.spareTire='1' "
				+ " AND MUAM.mainUndertakeAcademicMeetingType.spareTire='1' "
				+ " AND MUAM.researchLabId='"+researchLab.getResearchLabId()+"'");
		if (checkOut != null && checkOut.length() != 0
				&& !checkOut.trim().equals("4")) {
			hql.append(" AND MUAM.checkout='" + checkOut + "'");
		}
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			hql.append(" AND MUAM.meetingdate BETWEEN '"+foredate+"' AND '"+afterdate+"'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		if(!isDivided){
			mainUndtakAkdmicMetingList=query.list();
			int size=mainUndtakAkdmicMetingList.size();
			session.put("pageCount_GTMUAM", size%pageSize==0?(size/pageSize):(size/pageSize+1));
			session.put("recordNumber_GTMUAM", size);
		}
		mainUndtakAkdmicMetingList=query.setMaxResults(pageSize).setFirstResult((pageIndex-1)*pageSize).list();
		return mainUndtakAkdmicMetingList;
	}
    
    public void save(MainUndertakeAcademicMeeting transientInstance) {
        log.debug("saving MainUndertakeAcademicMeeting instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(MainUndertakeAcademicMeeting persistentInstance) {
        log.debug("deleting MainUndertakeAcademicMeeting instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public MainUndertakeAcademicMeeting findById( java.lang.String id) {
        log.debug("getting MainUndertakeAcademicMeeting instance with id: " + id);
        try {
            MainUndertakeAcademicMeeting instance = (MainUndertakeAcademicMeeting) getSession()
                    .get("com.nuaa.ec.model.MainUndertakeAcademicMeeting", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(MainUndertakeAcademicMeeting instance) {
        log.debug("finding MainUndertakeAcademicMeeting instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.MainUndertakeAcademicMeeting")
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
      log.debug("finding MainUndertakeAcademicMeeting instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from MainUndertakeAcademicMeeting as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByChargePerson(Object chargePerson
	) {
		return findByProperty(CHARGE_PERSON, chargePerson
		);
	}
	
	public List findByAcaMeetingName(Object acaMeetingName
	) {
		return findByProperty(ACA_MEETING_NAME, acaMeetingName
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
	
	public List findByCheckout(Object checkout
	) {
		return findByProperty(CHECKOUT, checkout
		);
	}
	
	public List findByMeetingdate(Object meetingdate
	) {
		return findByProperty(MEETINGDATE, meetingdate
		);
	}
	

	public List findAll() {
		log.debug("finding all MainUndertakeAcademicMeeting instances");
		try {
			String queryString = "from MainUndertakeAcademicMeeting";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteBylogic(String acameetId){
		try {
			String queryString = "update MainUndertakeAcademicMeeting set spareTire='0' "
					+ "where  acaMeetingId=?"
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, acameetId);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPageing(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from MainUndertakeAcademicMeeting where spareTire='1' "
					+ "and mainUndertakeAcademicMeetingPlace.spareTire='1' "
					+ "and mainUndertakeAcademicMeetingType.spareTire='1' "
					+condition
					+"order by meetingdate desc";
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
			String queryString = "from MainUndertakeAcademicMeeting where spareTire='1' "
					+ "and mainUndertakeAcademicMeetingPlace.spareTire='1' "
					+ "and mainUndertakeAcademicMeetingType.spareTire='1' "
					+condition;
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public MainUndertakeAcademicMeeting merge(MainUndertakeAcademicMeeting detachedInstance) {
        log.debug("merging MainUndertakeAcademicMeeting instance");
        try {
            MainUndertakeAcademicMeeting result = (MainUndertakeAcademicMeeting) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MainUndertakeAcademicMeeting instance) {
        log.debug("attaching dirty MainUndertakeAcademicMeeting instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(MainUndertakeAcademicMeeting instance) {
        log.debug("attaching clean MainUndertakeAcademicMeeting instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}