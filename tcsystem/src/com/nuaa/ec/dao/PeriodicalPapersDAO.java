package com.nuaa.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.nuaa.ec.model.Periodical;
import com.nuaa.ec.model.PeriodicalPapers;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.ScientificResearchReward;
import com.nuaa.ec.utils.EntityUtil;
import com.opensymphony.xwork2.ActionContext;

/**
 	* A data access object (DAO) providing persistence and search support for PeriodicalPapers entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.PeriodicalPapers
  * @author MyEclipse Persistence Tools 
 */
public class PeriodicalPapersDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(PeriodicalPapersDAO.class);
		//property constants
	public static final String PPID = "ppid";
	public static final String FIRST_AUTHOR = "firstAuthor";
	public static final String SECOND_AUTHOR = "secondAuthor";
	public static final String THESIS_TITLE = "thesisTitle";
	public static final String YEAR = "year";
	public static final String FILE = "file";
	public static final String PHASE = "phase";
	public static final String DESCRIBE = "describe";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHARGE_PERSON_ID = "chargePersonId";
	public static final String CHARGE_PERSON = "chargePerson";
	public static final String CHECKOUT = "checkout";

	private Map<String,Object> session=ActionContext.getContext().getSession();
	
	/**
	 * 所长审核功能
	 * 
	 * @param periodicalPapersList
	 * @return
	 */
	public boolean updateCheckoutStatus(
			List<PeriodicalPapers> periodicalPapersList) {
		Session session = this.getSession();
		Transaction tx = null;
		boolean updateFlag = false;
		try {
			for (int i = 0; i < periodicalPapersList.size(); i++) {
				session.update(periodicalPapersList.get(i));
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
			List<PeriodicalPapers> periodicalPaperList,String flag) {
		boolean operationFlag=false;
		Session session = this.getSession();
		Transaction tx=null;
		try{
			for (PeriodicalPapers pp : periodicalPaperList) {
				session.createQuery(
						"UPDATE TeacherAndperiodical TAP SET TAP.checkOut="+flag
						+ " WHERE TAP.ppid='"+ pp.getPpid()+"'").executeUpdate();
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
	 * 获得符合查询条件的所有期刊论文项目记录
	 * 
	 * @param transientInstance
	 */
	@SuppressWarnings("unchecked")
	public List<PeriodicalPapers> getAllRecordsWithCondition(int pageIndex,
			int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut, boolean isDivided) {
		List<PeriodicalPapers> periodicalList = new ArrayList<PeriodicalPapers>();
		StringBuffer hql = new StringBuffer("FROM PeriodicalPapers PP WHERE PP.spareTire='1'"
				+ " AND PP.periodical.spareTire='1' "
				+ " AND PP.researchLabId='"+researchLab.getResearchLabId()+"'");
		if (checkOut != null && checkOut.length() != 0
				&& !checkOut.trim().equals("4")) {
			hql.append(" AND PP.checkout='" + checkOut + "'");
		}
		if (foredate != null && afterdate != null && foredate.length() != 0
				&& afterdate.length() != 0) {
			hql.append(" AND PP.year BETWEEN '"+foredate+"' AND '"+afterdate+"'");
		}
		Query query=this.getSession().createQuery(hql.toString());
		if(!isDivided){
			periodicalList=query.list();
			int size=periodicalList.size();
			session.put("pageCount_GTPP", size%pageSize==0?(size/pageSize):(size/pageSize+1));
			session.put("recordNumber_GTPP", size);
		}
		periodicalList=query.setMaxResults(pageSize).setFirstResult((pageIndex-1)*pageSize).list();
		return periodicalList;
	}

    
    public void save(PeriodicalPapers transientInstance) {
        log.debug("saving PeriodicalPapers instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(PeriodicalPapers persistentInstance) {
        log.debug("deleting PeriodicalPapers instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public PeriodicalPapers findById( java.lang.Integer id) {
        log.debug("getting PeriodicalPapers instance with id: " + id);
        try {
            PeriodicalPapers instance = (PeriodicalPapers) getSession()
                    .get("com.nuaa.ec.model.PeriodicalPapers", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(PeriodicalPapers instance) {
        log.debug("finding PeriodicalPapers instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.PeriodicalPapers")
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
      log.debug("finding PeriodicalPapers instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from PeriodicalPapers as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByPpid(Object ppid
	) {
		return findByProperty(PPID, ppid
		);
	}
	
	public List findByFirstAuthor(Object firstAuthor
	) {
		return findByProperty(FIRST_AUTHOR, firstAuthor
		);
	}
	
	public List findBySecondAuthor(Object secondAuthor
	) {
		return findByProperty(SECOND_AUTHOR, secondAuthor
		);
	}
	
	public List findByThesisTitle(Object thesisTitle
	) {
		return findByProperty(THESIS_TITLE, thesisTitle
		);
	}
	
	public List findByYear(Object year
	) {
		return findByProperty(YEAR, year
		);
	}
	
	public List findByFile(Object file
	) {
		return findByProperty(FILE, file
		);
	}
	
	public List findByPhase(Object phase
	) {
		return findByProperty(PHASE, phase
		);
	}
	
	public List findByDescribe(Object describe
	) {
		return findByProperty(DESCRIBE, describe
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
	//分页 --查询
	public List findAll(String condition,int currentrow,int limit) throws Exception{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<PeriodicalPapers> pepali = new ArrayList<PeriodicalPapers>();
		PeriodicalPapers periopaper = null;
		try {
			con = getConn();
			ps = con.prepareStatement("select * from PeriodicalPapers,Periodical  where PeriodicalPapers.SpareTire = '1' and PeriodicalPapers.PeriodicalID=Periodical.PeriodicalID "+condition+" order by PeriodicalPapers.Year desc ",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			rs.absolute(currentrow+1);
			do{
				periopaper = new PeriodicalPapers(
						rs.getString("PPID"), 
						rs.getString("FirstAuthor"), 
						rs.getString("SecondAuthor"), 
						rs.getString("ThesisTitle"), 
						rs.getString("Year"), 
						rs.getString("File"), 
						rs.getString("Phase"), 
						rs.getString("Describe"), 
						rs.getString("SpareTire"), 
						rs.getString("ChargePersonID"), 
						rs.getString("ChargePerson"), 
						rs.getString("checkout"), 
						rs.getString("PeriodicalID"),
						rs.getString("PeriodicalName"),
						rs.getInt("PeriodicalPid"));
				pepali.add(periopaper);
			}while(rs.getRow()<currentrow+limit&&rs.next());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			closeSqlAttr(ps, rs);
		}
		if(pepali.size()>0){
			return pepali;
		}else{
			return null;
		}
	} 
	public List findAll() {
		log.debug("finding all PeriodicalPapers instances");
		try {
			String queryString = "from PeriodicalPapers pp where spareTire = '1' order by pp.year desc";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public List findPageing(int currentRow,int limitRow,String condition){
		try {
			String queryString = "from PeriodicalPapers "
					+ "where spareTire = '1' "
					+ "and periodical.spareTire='1' "
					+condition
					+ "order by year desc";
	         Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow).setMaxResults(limitRow);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition){
		try {
			String queryString = "from PeriodicalPapers "
					+ "where spareTire = '1' "
					+ "and periodical.spareTire='1' "
					+condition
					+ "order by year desc";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public void deleteBylogic(String ppid){
		try {
			String queryString = "update PeriodicalPapers  "
					+ "set spareTire='0' "
					+ "where spareTire = '1' "
					+ "and ppid=? ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, ppid);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public PeriodicalPapers merge(PeriodicalPapers detachedInstance) {
        log.debug("merging PeriodicalPapers instance");
        try {
            PeriodicalPapers result = (PeriodicalPapers) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(PeriodicalPapers instance) {
        log.debug("attaching dirty PeriodicalPapers instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(PeriodicalPapers instance) {
        log.debug("attaching clean PeriodicalPapers instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}