package com.nuaa.ec.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.nuaa.ec.model.PeriodicalPapersScore;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndperiodical;
import com.nuaa.ec.scienresearch.exportdata.PeriodicalPaperExcel;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TeacherAndperiodical entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.nuaa.ec.model.TeacherAndperiodical
 * @author MyEclipse Persistence Tools
 */
public class TeacherAndperiodicalDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TeacherAndperiodicalDAO.class);
	// property constants
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String PPID = "ppid";
	public static final String CHECK_OUT = "checkOut";
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	
	public boolean updateCheckoutStatus(List<TeacherAndperiodical> TAPeriodical){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TAPeriodical.size();i++){
				session.update(TAPeriodical.get(i));
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
			String checkOut){
		log.debug("finding all TeacherAndperiodical instances");
		StringBuffer hql = null;
		List<TeacherAndperiodical> list = new ArrayList<TeacherAndperiodical>();
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据， 所以不显示数据
			 */
			session.put("recordNumber_TAPA", 0);
			session.put("pageCount_TAPA", 0);
			return list;
		} else {
			hql = new StringBuffer(
					"select new com.nuaa.ec.model.PeriodicalPaperInfoUnionModel(TAPA,PP) from TeacherAndperiodical TAPA "
							+ " , PeriodicalPapers PP where TAPA.spareTire='1'"
							+ " and TAPA.periodical.spareTire='1'"
							+ " and TAPA.teacher.spareTire='1'"
							+ " and PP.spareTire='1'"
							+ " and TAPA.ppid=PP.ppid"
//							+ " and TAPA.checkOut='"+ checkOut+"'"
							+ " and TAPA.teacher.researchLab.researchLabId='"+ researchLab.getResearchLabId() + "'");
		}
		if(checkOut!=null && checkOut.length()!=0 && !checkOut.trim().equals("4")){
			hql.append(" AND TAPA.checkOut='"+checkOut+"'");
		}
		try {
			String append = " and PP.year between ? and ? ";
			String rank = "  order by PP.periodicalPid desc";
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
			session.put("recordNumber_TAPA", list.size());
			session.put("pageCount_TAPA",
					list.size() % pageSize == 0 ? (list.size() / pageSize)
							: (list.size() / pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力， 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getTAPeriodicalListsAfterDivided(pageIndex, pageSize,
					foredate, afterdate, researchLab, checkOut);
		} 
		 catch(Exception ex){
			 log.error("find all failed", ex);
			 System.out.println(ex.getMessage());
		}
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<TeacherAndperiodical> getTAPeriodicalListsAfterDivided(
			int pageIndex, int pageSize, String foredate, String afterdate,
			ResearchLab researchLab, String checkOut) {
		System.out.println("pageIndex:"+pageIndex);
		StringBuffer hql = null;
		if (researchLab.getResearchLabId() == null
				|| researchLab.getResearchLabId().length() == 0) {
			hql = new StringBuffer(
					"select new com.nuaa.ec.model.PeriodicalPaperInfoUnionModel(TAPA,PP) from TeacherAndperiodical TAPA "
					+ " ,PeriodicalPapers PP where TAPA.spareTire='1'"
					+ " and TAPA.periodical.spareTire='1'"
					+ " and TAPA.teacher.spareTire='1'"
					+ " and PP.spareTire='1'"
					+ " and TAPA.ppid=PP.ppid");
//					+ " and TAPA.checkOut='"+ checkOut + "'");
		} else {
			hql = new StringBuffer(
					"select new com.nuaa.ec.model.PeriodicalPaperInfoUnionModel(TAPA,PP) from TeacherAndperiodical TAPA "
						+ " ,PeriodicalPapers PP where TAPA.spareTire=1"
						+ " and TAPA.periodical.spareTire='1'"
						+ " and TAPA.teacher.spareTire='1'"
						+ " and PP.spareTire='1'"
						+ " and TAPA.ppid=PP.ppid"
//						+ " and TAPA.checkOut='"+ checkOut+"'"
						+ " and TAPA.teacher.researchLab.researchLabId=\'"+ researchLab.getResearchLabId() + "\'");
		}
		if(checkOut!=null && checkOut.length()!=0 && !checkOut.trim().equals("4")){
			hql.append(" AND TAPA.checkOut='"+checkOut+"'");
		}
		List<TeacherAndperiodical> list = new ArrayList<TeacherAndperiodical>();
		String append = " and PP.year between ? and ? ";
		String rank = "  order by PP.periodicalPid desc";
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

	public boolean checkexist(String teacherId, String ppId) {
		try {
			String queryString = "from TeacherAndperiodical as model where model.teacher.teacherId = ? and model.ppid = ? and model.spareTire = '1'";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, teacherId);
			queryObject.setParameter(1, ppId);
			if (queryObject.list().size() > 0) {
				return true;
			} else {
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

	public TeacherAndperiodical findById(java.lang.Integer id) {
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

    //获取教师以及对应的期刊论文的信息
	public List findTeacherandPaper(Teacher teacher,String condition,int currentrow,int limitrows){
		try {
			String queryString = "select new com.nuaa.ec.model.PeriodicalPapersPerson(p.ppid,p.thesisTitle,p.chargePersonId,p.firstAuthor,p.secondAuthor,t.finalScore,t.checkOut) from TeacherAndperiodical t,PeriodicalPapers p where t.ppid = p.ppid and t.spareTire = '1' and p.spareTire='1' and t.teacher=?"+condition+" order by p.year,t.teacherAndPid desc";
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
    
    public void updateRefTeacher(String ppid,double score,PeriodicalPapersScore ppscore){
    	try {
			String queryString = "update TeacherAndperiodical set finalScore=?,periodicalPapersScore=?"
					+ " where ppid=? "
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, score);
	         queryObject.setParameter(1, ppscore);
	         queryObject.setParameter(2, ppid);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    
    public void deleteMember(String ppid,String teacherId){
    	try {
			String queryString = "update TeacherAndperiodical set spareTire='0' "
					+ " where ppid=? "
					+ "and teacher.teacherId=? "
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString).setParameter(0, ppid).setParameter(1, teacherId);
	         queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
    }
    
    public void deleteRefTeacher(String ppid){
    	try {
			String queryString = "update TeacherAndperiodical set spareTire='0' "
					+ " where ppid=? "
					+ "and spareTire='1' ";
	         Query queryObject = getSession().createQuery(queryString);
	         queryObject.setParameter(0, ppid);
	         queryObject.executeUpdate();
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
    

    public List findMembers(String ppid){
    	try {
			String queryString = "select new com.nuaa.ec.model.TeacherMember(tp.teacher.teacherId,tp.teacher.teacherName,'') "
					+ "from TeacherAndperiodical tp "
					+ "where spareTire='1' "
					+ "and periodicalPapersScore.spareTire='1' "
					+ "and teacher.spareTire='1' "
					+ "and ppid=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, ppid);
			return queryObject.list().size() > 0 ? queryObject.list() : null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
    }
    
    public ByteArrayOutputStream findwithexport(ResearchLab research,String condition,String researchLabName,String foredate,String afterdate){
    	try {
	    	String queryString = "select new com.nuaa.ec.model.PeriodicalPaperInfoUnionModel(tap,pp) "
	    			+ "from TeacherAndperiodical tap ,PeriodicalPapers pp "
	    			+ "where tap.spareTire='1' "
						+ " and tap.teacher.spareTire='1' "
						+ " and pp.spareTire='1' "
						+ " and tap.ppid=pp.ppid "
						+condition
						+ " and tap.teacher.researchLab=?";
    	Query queryObject = getSession().createQuery(queryString).setParameter(0, research);
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	if(queryObject.list().size()>0){
    		try {
				PeriodicalPaperExcel.generateExcel(stringstore.peroidicalPaper, queryObject.list(), researchLabName, foredate, afterdate).write(baos);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		return baos;
		}else{
			return null;
		}
	} catch (RuntimeException re) {
		log.error("find by property name failed", re);
		throw re;
	}
    }
    
	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TeacherAndperiodical instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TeacherAndperiodical as model where model."
					+ propertyName + "= ? and model.spareTire = '1' ";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list().size() > 0 ? queryObject.list() : null;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFinalScore(Object finalScore) {
		return findByProperty(FINAL_SCORE, finalScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByPpid(Object ppid) {
		return findByProperty(PPID, ppid);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
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