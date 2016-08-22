package com.nuaa.ec.dao;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;

/**
 	* A data access object (DAO) providing persistence and search support for Teacher entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.nuaa.ec.model.Teacher
  * @author MyEclipse Persistence Tools 
 */
public class TeacherDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(TeacherDAO.class);
		//property constants
	public static final String SPARE_TIRE = "spareTire";
	public static final String TEACHER_NAME = "teacherName";
	public static final String DEPART_ADMIN = "departAdmin";
	public static final String RESEARCH_LAB_ADMIN = "researchLabAdmin";
	public static final String TEACHERPRIMARYID = "teacherprimaryid";
	public static final String TEACHER_POST = "teacherPost";

	public void updateDepartStatus(String teacherId,String departId){
		String replace = "update Teacher set departAdmin = '0' where departAdmin= '1' and departmentId = ?";
		String update = "update Teacher set departAdmin = '1' where teacherId=?";
		Transaction tx = null;
		Session session = null;
		try {
			session = getSession();
			Query reset = session.createQuery(replace);
			reset.setParameter(0, departId);
			tx = session.beginTransaction();
			reset.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}try {
			session = getSession();
			Query setnew = session.createQuery(update);
			setnew.setParameter(0, teacherId);
			tx = session.beginTransaction();
			setnew.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
	} 
	
	public void updateResearchStatus(String teacherId,String researchId){
		String replace = "update Teacher set researchLabAdmin = '0' where researchLabAdmin= '1' and researchLabId = ?";
		String update = "update Teacher set researchLabAdmin = '1' where teacherId=?";
		Transaction tx = null;
		Session session = null;
		try {
			session = getSession();
			Query reset = session.createQuery(replace);
			reset.setParameter(0, researchId);
			tx = session.beginTransaction();
			reset.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}try {
			session = getSession();
			Query setnew = session.createQuery(update);
			setnew.setParameter(0, teacherId);
			tx = session.beginTransaction();
			setnew.executeUpdate();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			session.close();
		}
	}

	public int chekTeacherInDepart(String departId,String teacherId){
		int num = 0;
		try {
	         String queryString = "from Teacher as model where model.department.departmentId = ? and model.teacherId = ?";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, departId);
	         queryObject.setParameter(1, teacherId);
	         num = queryObject.list().size(); 
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		return num;
	}
	
	public int chekTeacherInResearchLab(String researchId,String teacherId){
		int num = 0;
		try {
	         String queryString = "from Teacher as model where model.researchLab.researchLabId = ? and model.teacherId = ?";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, researchId);
	         queryObject.setParameter(1, teacherId);
	         num = queryObject.list().size(); 
	      } catch (RuntimeException re) {
	         log.error("find by property name failed", re);
	         throw re;
	      }
		return num;
	}
    
    public void save(Teacher transientInstance) {
        log.debug("saving Teacher instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Teacher persistentInstance) {
        log.debug("deleting Teacher instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Teacher findById( java.lang.String id) {
        log.debug("getting Teacher instance with id: " + id);
        try {
            Teacher instance = (Teacher) getSession()
                    .get("com.nuaa.ec.model.Teacher", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Teacher instance) {
        log.debug("finding Teacher instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.nuaa.ec.model.Teacher")
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
      log.debug("finding Teacher instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Teacher as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findBySpareTire(Object spareTire
	) {
		return findByProperty(SPARE_TIRE, spareTire
		);
	}
	
	public List findByTeacherName(Object teacherName
	) {
		return findByProperty(TEACHER_NAME, teacherName
		);
	}
	
	public List findByDepartAdmin(Object departAdmin
	) {
		return findByProperty(DEPART_ADMIN, departAdmin
		);
	}
	
	public List findByResearchLabAdmin(Object researchLabAdmin
	) {
		return findByProperty(RESEARCH_LAB_ADMIN, researchLabAdmin
		);
	}
	
	public List findByTeacherprimaryid(Object teacherprimaryid
	) {
		return findByProperty(TEACHERPRIMARYID, teacherprimaryid
		);
	}
	
	public List findByTeacherPost(Object teacherPost
	) {
		return findByProperty(TEACHER_POST, teacherPost
		);
	}
	

	public List findAll() {
		log.debug("finding all Teacher instances");
		try {
			String queryString = "from Teacher as t where t.spareTire = '1'";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
	public Map<String, Object> findAllT(){
		Map<String, Object> teacherm = new HashMap<String, Object>();
		List<Teacher> teacherli = new ArrayList<Teacher>();
		log.debug("finding all Teacher exchange to map--translate instances ");
		try {
			String queryString = "from Teacher as t where t.spareTire = '1'";
	         Query queryObject = getSession().createQuery(queryString);
	         teacherli = queryObject.list();
	         if(teacherli!=null)
		         for(int i=0;i<teacherli.size();i++){
		        	 teacherm.put(teacherli.get(i).getTeacherId(), teacherli.get(i).getTeacherName());
		         }
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
		return teacherm;
	}
	
    public Teacher merge(Teacher detachedInstance) {
        log.debug("merging Teacher instance");
        try {
            Teacher result = (Teacher) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Teacher instance) {
        log.debug("attaching dirty Teacher instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Teacher instance) {
        log.debug("attaching clean Teacher instance");
        try {
                      	getSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}