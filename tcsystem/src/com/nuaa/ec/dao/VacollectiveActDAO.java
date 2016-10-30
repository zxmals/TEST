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

import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.nuaa.ec.model.VacollectiveAct;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for VacollectiveAct entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.VacollectiveAct
  * @author MyEclipse Persistence Tools 
 */
public class VacollectiveActDAO extends BaseHibernateDAO  {
	private static final Logger log = LoggerFactory.getLogger(VacollectiveActDAO.class);
		//property constants
	public static final String ACT_NAME = "actName";
	public static final String ATTENDEE = "attendee";
	public static final String SCORE = "score";
	public static final String ACT_TYPE = "actType";
	public static final String BASE_NUM = "baseNum";
	public static final String SPARE_TIRE = "spareTire";
	public static final String ASPARE_TIRE = "aspareTire";
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private List<VacollectiveAct> addJoinedActList = null;

    
    public void save(VacollectiveAct transientInstance) {
        log.debug("saving VacollectiveAct instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(VacollectiveAct persistentInstance) {
        log.debug("deleting VacollectiveAct instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public VacollectiveAct findById( java.lang.String id) {
        log.debug("getting VacollectiveAct instance with id: " + id);
        try {
            VacollectiveAct instance = (VacollectiveAct) getSession()
                    .get("com.nuaa.ec.model.VacollectiveAct", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(VacollectiveAct instance) {
        log.debug("finding VacollectiveAct instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.VacollectiveAct")
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
      log.debug("finding VacollectiveAct instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from VacollectiveAct as model where model." 
         						+ propertyName + "= ?   and model.spareTire = '1' ";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByActName(Object actName
	) {
		return findByProperty(ACT_NAME, actName
		);
	}
	
	public List findByAttendee(Object attendee
	) {
		return findByProperty(ATTENDEE, attendee
		);
	}
	
	public List findByScore(Object score
	) {
		return findByProperty(SCORE, score
		);
	}
	
	public List findByActType(Object actType
	) {
		return findByProperty(ACT_TYPE, actType
		);
	}
	
	public List findByBaseNum(Object baseNum
	) {
		return findByProperty(BASE_NUM, baseNum
		);
	}
	
	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByAspareTire(Object aspareTire
	) {
		return findByProperty(ASPARE_TIRE, aspareTire
		);
	}
	

	public List findAll() {
		log.debug("finding all VacollectiveAct instances");
		try {
			String queryString = "from VacollectiveAct";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public VacollectiveAct merge(VacollectiveAct detachedInstance) {
        log.debug("merging VacollectiveAct instance");
        try {
            VacollectiveAct result = (VacollectiveAct) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(VacollectiveAct instance) {
        log.debug("attaching dirty VacollectiveAct instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(VacollectiveAct instance) {
        log.debug("attaching clean VacollectiveAct instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public boolean updateASparetire(List<VacollectiveAct> checkoutList) {
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<checkoutList.size();i++){
				session.update(checkoutList.get(i));
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
	public List getVaAddJoinedAct(int pageIndex, int pageSize,ResearchLab researchLab,String checkOut,
			boolean isDivided) {
		// TODO Auto-generated method stub
		StringBuffer hqlBuffer = null;
		if (researchLab.getResearchLabId() == null || 
				researchLab.getResearchLabId().length() == 0) {
			/*
			 * 第一次进入的时候，不显示记录
			 */
			
			session.put("pageCount_CT", 0);
			session.put("recordNumber_CT", 0);
			return addJoinedActList = new ArrayList<VacollectiveAct>();
		}else {
			// 查出符合条件的全部的记录
			hqlBuffer = new StringBuffer(
					"from VacollectiveAct CT where CT.spareTire='1' "
							+ "and CT.aspareTire='" + checkOut + "' "
							+ "and CT.teacher.spareTire = '1' "
//							+ "and VACA.spareTire = '1' "
//							+ "and CT.teacher.teacherId = VACA.TeacherID "
							+ "and CT.teacher.researchLab.spareTire='1' "
							+ "and CT.teacher.researchLab.researchLabId='"
							+ researchLab.getResearchLabId() + "'");
			// 判断是否为分页操作
			if (!isDivided) {
				//如果不是分页操作，取出所有符合条件的记录
				addJoinedActList = this.getSession().createQuery(hqlBuffer.toString()).list();
				int recordNumber=addJoinedActList.size();
				session.put("pageCount_CT", recordNumber%pageSize==0?(recordNumber/pageSize):(recordNumber/pageSize+1));
				session.put("recordNumber_CT", addJoinedActList.size());
			}
			//无论是不是分页查询，都在后台进行分页操作
			addJoinedActList = this.getSession()
					.createQuery(hqlBuffer.toString())
					.setFirstResult((pageIndex - 1) * pageSize)
					.setMaxResults(pageIndex).list();
			
		}
		
		return addJoinedActList;
	}
	

	

}