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
import com.nuaa.ec.model.ScientificResearchProject;
import com.nuaa.ec.model.ScientificResearchProjectScore;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.opensymphony.xwork2.ActionContext;

/**
 * A data access object (DAO) providing persistence and search support for
 * TeacherAndscientificResearchProject entities. Transaction control of the
 * save(), update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see com.nuaa.ec.model.TeacherAndscientificResearchProject
 * @author MyEclipse Persistence Tools
 */
public class TeacherAndscientificResearchProjectDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(TeacherAndscientificResearchProjectDAO.class);
	// property constants
	public static final String YEAR_FUNDS = "yearFunds";
	public static final String FINAL_SCORE = "finalScore";
	public static final String SPARE_TIRE = "spareTire";
	public static final String CHECK_OUT = "checkOut";
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	public boolean updateCheckoutStatus(List<TeacherAndscientificResearchProject> TARProList){
		Session session=this.getSession();
		Transaction tx=null;
		boolean updateFlag=false;
		try{
			for(int i=0;i<TARProList.size();i++){
				session.update(TARProList.get(i));
			}
			tx=session.beginTransaction();
			tx.commit();
			updateFlag=true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return updateFlag;
	}
	/**
	 * 完成分页功能(只针对分页！其他更换时间等调用改动的findall()方法)
	 * 
	 * @param transientInstance
	 */
	@SuppressWarnings("unchecked")
	public List<TeacherAndscientificResearchProject> getTASRProjectListsWithCondition(
			int pageIndex, int pageSize, String foredate, String afterdate,ResearchLab researchLab,String checkOut) {
		StringBuffer hql=null;
		if(researchLab.getResearchLabId()==null || researchLab.getResearchLabId().length()==0){
			hql=new StringBuffer(
					"from TeacherAndscientificResearchProject TARP where TARP.spareTire='1'"
							+ " and TARP.scientificResearchProject.spareTire='1'"
							+ " and TARP.teacher.spareTire='1'"
							+ " and TARP.selfUndertakeTask.spareTire='1'"
							+ " and TARP.checkOut='"+checkOut+"'");
		}else{
			hql = new StringBuffer(
					"from TeacherAndscientificResearchProject TARP where spareTire='1' "
							+ " and TARP.scientificResearchProject.spareTire='1'"
							+ " and TARP.teacher.spareTire='1'"
							+ " and TARP.selfUndertakeTask.spareTire='1'"
							+ " and TARP.checkOut='"+checkOut+"'"
							+ " and TARP.teacher.researchLab.researchLabId=\'"+researchLab.getResearchLabId()+"\'");
		}
		List<TeacherAndscientificResearchProject> list = new ArrayList<TeacherAndscientificResearchProject>();
		String append = " and TARP.scientificResearchProject.admitedProjectYear between ? and ? ";
		if (foredate != null && afterdate != null && foredate.length()!=0 && afterdate.length()!=0) {
			// 判断日期范围限制
			hql.append(append);
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

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List findAll(int pageIndex, int pageSize, String foredate,
			String afterdate,ResearchLab researchLab,String checkOut) {
		log.debug("finding all TeacherAndscientificResearchProject instances");
		StringBuffer hql=null;
		List<TeacherAndscientificResearchProject> list = new ArrayList<TeacherAndscientificResearchProject>();
		if(researchLab.getResearchLabId()==null || researchLab.getResearchLabId().length()==0){
//			hql=new StringBuffer(
//					"from TeacherAndscientificResearchProject TARP where spareTire=1 and TARP.checkOut='"+checkOut+"'");
			/*
			 * 如果第一次进入界面 没有选择研究所 session里面没有对应的数据，
			 * 所以不显示数据
			 */
			session.put("recordNumber", 0);
			session.put("pageCount",0);
			return list;
		}else{
			hql = new StringBuffer(
					"from TeacherAndscientificResearchProject TARP where spareTire='1'"
							+ " and TARP.scientificResearchProject.spareTire='1'"
							+ " and TARP.teacher.spareTire='1'"
							+ " and TARP.selfUndertakeTask.spareTire='1'"
							+ " and TARP.checkOut='"+checkOut+"'"
							+ " and TARP.teacher.researchLab.researchLabId='"+researchLab.getResearchLabId()+"'");
		}
		try {
//			hql = new StringBuffer(
//					"from TeacherAndscientificResearchProject TARP where TARP.spareTire=1");
			String append = " and TARP.scientificResearchProject.admitedProjectYear between ? and ? ";
			/*
			 * 不一定有日期，所以要判断
			 */
			if (foredate != null && afterdate != null && foredate.length()!=0 && afterdate.length()!=0) {
				// 判断日期范围限制
				hql.append(append);
				list = this.getSession().createQuery(hql.toString())
						.setString(0, foredate).setString(1, afterdate).list();
			} else {
				list = this.getSession().createQuery(hql.toString()).list();
			}
			/*
			 * 总体查询完毕，把总记录数和总页数放入session用于前台展现。
			 */
			session.put("recordNumber", list.size());
			session.put("pageCount",list.size() % pageSize == 0 ? (list.size() / pageSize): (list.size() / pageSize + 1));
			/*
			 * 调用分页函数，缓解前台压力，
			 * 在后台完成分页，在前台展示相应数据。
			 */
			list = this.getTASRProjectListsWithCondition(pageIndex, pageSize,foredate, afterdate,researchLab,checkOut);
			return list;
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	/**
	 * 实现查询在指定时间范围内记录的功能
	 * 
	 * @param transientInstance
	 */
	@SuppressWarnings("unchecked")
	public List<TeacherAndscientificResearchProject> getTASRProjectListsWithDate(
			int pageSize, String foredate, String afterdate) {
		List<TeacherAndscientificResearchProject> list = new ArrayList<TeacherAndscientificResearchProject>();
		String hql = "from TeacherAndscientificResearchProject TARP where TARP.scientificResearchProject.admitedProjectYear between ? and ? ";
		list = this.getSession().createQuery(hql.toString())
				.setString(0, foredate).setString(1, afterdate).list();
		/**
		 * 利用session来存储记录的总条数用于前台展现， 如果换了查询时间范围，那么新的数据就会覆盖原来的总记录条数以保证数据 的更新
		 */
		session.put("recordNumber", list.size());
		session.put(
				"pageCount",
				list.size() % pageSize == 0 ? (list.size() / pageSize) : (list
						.size() / pageSize + 1));
		return list;
	}

	public void save(TeacherAndscientificResearchProject transientInstance) {
		log.debug("saving TeacherAndscientificResearchProject instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(TeacherAndscientificResearchProject persistentInstance) {
		log.debug("deleting TeacherAndscientificResearchProject instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public TeacherAndscientificResearchProject findById(java.lang.Integer id) {
		log.debug("getting TeacherAndscientificResearchProject instance with id: "
				+ id);
		try {
			TeacherAndscientificResearchProject instance = (TeacherAndscientificResearchProject) getSession()
					.get("com.nuaa.ec.model.TeacherAndscientificResearchProject",
							id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(TeacherAndscientificResearchProject instance) {
		log.debug("finding TeacherAndscientificResearchProject instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.nuaa.ec.model.TeacherAndscientificResearchProject")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding TeacherAndscientificResearchProject instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from TeacherAndscientificResearchProject as model where model."
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
			String queryString = "from TeacherAndscientificResearchProject where spareTire='1' "
					+ "and teacher.spareTire='1' "
					+ "and teacher=? "
					+ "and scientificResearchProject.projectType.spareTire='1' "
					+ "and scientificResearchProject.spareTire='1' "
					+condition
					+ " order by scientificResearchProject.admitedProjectYear desc";
			Query queryObject = getSession().createQuery(queryString).setFirstResult(currentRow).setMaxResults(limitRow).setParameter(0, teacher);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public int getRows(String condition,Teacher teacher){
		try {
			String queryString = "from TeacherAndscientificResearchProject where spareTire='1' "
					+ "and teacher.spareTire='1' "
					+ "and scientificResearchProject.projectType.spareTire='1' "
					+ "and scientificResearchProject.spareTire='1' "
					+condition;
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list().size();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public boolean checkexist(Teacher teacher,ScientificResearchProject srp){
		try {
			String queryString = "from TeacherAndscientificResearchProject where spareTire='1' "
					+ "and scientificResearchProject=? "
					+ "and teacher=? "
					+ "and teacher.spareTire='1' "
					+ "and scientificResearchProject.spareTire='1' ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, srp).setParameter(1, teacher);
			if(queryObject.list().size()>0){
				return false;
			}else return true;
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public void updateRef(ScientificResearchProjectScore srpscore,ScientificResearchProject srp){
		try {
			String queryString = "update TeacherAndscientificResearchProject "
					+ "set scientificResearchProjectScore=? "
					+ ",finalScore=? "
					+ "where spareTire='1' "
					+ "and scientificResearchProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, srpscore)
					.setParameter(1, (double)srpscore.getScore()).setParameter(2, srp);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public void deleteRef(ScientificResearchProject srp){
		try {
			String queryString = "update TeacherAndscientificResearchProject set spareTire='0' "
					+ "where spareTire='1' "
					+ "and scientificResearchProject=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, srp);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public void quitProject(ScientificResearchProject srp,Teacher teacher){
		try {
			String queryString = "update TeacherAndscientificResearchProject set spareTire='0' "
					+ "where spareTire='1' "
					+ "and scientificResearchProject=? "
					+ "and teacher=? ";
			Query queryObject = getSession().createQuery(queryString).setParameter(0, srp).setParameter(1, teacher);
			queryObject.executeUpdate();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
	
	public List findByYearFunds(Object yearFunds) {
		return findByProperty(YEAR_FUNDS, yearFunds);
	}

	public List findByFinalScore(Object finalScore) {
		return findByProperty(FINAL_SCORE, finalScore);
	}

	public List findBySpareTire(Object spareTire) {
		return findByProperty(SPARE_TIRE, spareTire);
	}

	public List findByCheckOut(Object checkOut) {
		return findByProperty(CHECK_OUT, checkOut);
	}

	public TeacherAndscientificResearchProject merge(
			TeacherAndscientificResearchProject detachedInstance) {
		log.debug("merging TeacherAndscientificResearchProject instance");
		try {
			TeacherAndscientificResearchProject result = (TeacherAndscientificResearchProject) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(TeacherAndscientificResearchProject instance) {
		log.debug("attaching dirty TeacherAndscientificResearchProject instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(TeacherAndscientificResearchProject instance) {
		log.debug("attaching clean TeacherAndscientificResearchProject instance");
		try {
			getSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}