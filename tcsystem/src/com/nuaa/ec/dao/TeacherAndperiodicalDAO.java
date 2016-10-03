package com.nuaa.ec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndperiodical;

/**
 	* A data access object (DAO) providing persistence and search support for TeacherAndperiodical entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.TeacherAndperiodical
  * @author MyEclipse Persistence Tools 
 */
public class TeacherAndperiodicalDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherAndperiodicalDAO.class);
		//property constants
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String PPID = "ppid";
	public static final String CHECK_OUT = "checkOut";



    
    public void save(TeacherAndperiodical transientInstance) {
        log.debug("saving TeacherAndperiodical instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public boolean checkexist(String teacherId,String ppId){
    	try {
            String queryString = "from TeacherAndperiodical as model where model.teacher.teacherId = ? and model.ppid = ? and model.spareTire = '1'";
            Query queryObject = getSession().createQuery(queryString);
            queryObject.setParameter(0, teacherId);
            queryObject.setParameter(1, ppId);
            if(queryObject.list().size()>0){
            	return true;
            }else{
            	return false;
            }
         } catch (RuntimeException re) {
            log.error("find by property name failed", re);
            throw re;
         }
    }
    
	public void delete(TeacherAndperiodical persistentInstance) {
        log.debug("deleting TeacherAndperiodical instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    //获取教师以及对应的期刊论文的信息
	public List findTeacherandPaper(Teacher teacher,String condition,int currentrow,int limitrows){
		try {
			String queryString = "select new com.nuaa.ec.model.PeriodicalPapersPerson(p.ppid,p.thesisTitle,p.chargePersonId,p.firstAuthor,p.secondAuthor,t.periodicalPapersScore.score,t.checkOut) from TeacherAndperiodical t,PeriodicalPapers p where t.ppid = p.ppid and t.spareTire = '1' and t.teacher=?"+condition+" order by p.year desc";
	         Query queryObject = getSession().createQuery(queryString).setFirstResult(currentrow);
	         queryObject.setMaxResults(limitrows);
	         queryObject.setParameter(0, teacher);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	public int getrows(Teacher teacher,String condition){
		try {
			String queryString = "select p.ppid from TeacherAndperiodical t,PeriodicalPapers p where t.ppid = p.ppid and t.spareTire = '1' and t.teacher=?"+condition;
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, teacher);
			 return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
    public TeacherAndperiodical findById( java.lang.Integer id) {
        log.debug("getting TeacherAndperiodical instance with id: " + id);
        try {
            TeacherAndperiodical instance = (TeacherAndperiodical) getSession()
                    .get("com.nuaa.ec.model.TeacherAndperiodical", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List findBymergeId(Teacher teacher,String ppid){
    	try {
			String queryString = "from TeacherAndperiodical t where t.teacher=? and t.ppid=?";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, teacher);
	         queryObject.setParameter(1, ppid);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    
    public List findByExample(TeacherAndperiodical instance) {
        log.debug("finding TeacherAndperiodical instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.TeacherAndperiodical")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findMember(String ppId) throws Exception{
    	Connection con = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	List<Object> li = new ArrayList<Object>();
    	Map<String, Object> mp = null;
    	try {
			con = getConn();
			ps = con.prepareStatement("select Teacher.TeacherID ,Teacher.TeacherName from Teacher inner join TeacherANDPeriodical on Teacher.TeacherID = TeacherANDPeriodical.TeacherID and TeacherANDPeriodical.PPID = ? and TeacherANDPeriodical.SpareTire = '1'");
			ps.setString(1, ppId);
			rs = ps.executeQuery();
			while(rs.next()){
				mp = new HashMap<String, Object>();
				mp.put("teacherId", rs.getString(1));
				mp.put("teacherName", rs.getString(2));
				li.add(mp);
			}
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}finally{
			closeSqlAttr(ps, rs);
		}
    	if(li.size()>0){
    		return li;
    	}else{
    		return null;
    	}
    }
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding TeacherAndperiodical instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from TeacherAndperiodical as model where model." 
         						+ propertyName + "= ? and model.spareTire = '1' ";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list().size()>0?queryObject.list():null;
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
	
	public List findByPpid(Object ppid
	) {
		return findByProperty(PPID, ppid
		);
	}
	
	public List findByCheckOut(Object checkOut
	) {
		return findByProperty(CHECK_OUT, checkOut
		);
	}
	

	public List findAll() {
		log.debug("finding all TeacherAndperiodical instances");
		try {
			String queryString = "from TeacherAndperiodical";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public TeacherAndperiodical merge(TeacherAndperiodical detachedInstance) {
        log.debug("merging TeacherAndperiodical instance");
        try {
            TeacherAndperiodical result = (TeacherAndperiodical) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(TeacherAndperiodical instance) {
        log.debug("attaching dirty TeacherAndperiodical instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(TeacherAndperiodical instance) {
        log.debug("attaching clean TeacherAndperiodical instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}