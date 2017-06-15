package com.nuaa.ec.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.NationalityDAO;
import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Nationality;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.nuaa.ec.utils.StoreData;
import com.opensymphony.xwork2.ActionContext;

public class BaseSetAction implements SessionAware{

	private Department depart;
	private ResearchLab research;
	private Nationality nation;
	private Map<String, Object>session;
	private DepartmentDAO departdao = new DepartmentDAO();
	private ResearchLabDAO researchdao = new ResearchLabDAO();
	private NationalityDAO nationdao = new NationalityDAO();
	private TeacherDAO teacherdao = new TeacherDAO();
	private PrimaryKMaker pk = new PrimaryKMaker();
	
	private Tfterm term;
	private TftermDAO termDao = new TftermDAO();

	// default method
	public String execute() {
		return "success";
	}
/**************************系设置*******departSet*****************************/
	public String getDepartinfo() throws Exception{
		ServletActionContext.getRequest().setAttribute("Department", departdao.findAll());
		return "success";
	}
	
	public String adddepart() throws Exception{
		depart.setDepartmentId(pk.mkpk("DepartmentID", "Department", "D"));
		depart.setSpareTire("1");
		Transaction tx = null;
		try {
			departdao.save(depart);
			tx = departdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}finally{
			departdao.closeSession();
			try {
				ServletActionContext.getRequest().setAttribute("Department", departdao.findAll());
			} catch (Exception e2) {
				// TODO: handle exception
				 throw e2;
			}
			getDepartinfo();
			
		}
		return "success";
	}
	
	public void updateDepart() throws Exception{
		Transaction tx = null;
		depart.setDepartAdminId(depart.getDepartAdmin().substring(0, 9));
		depart.setSpareTire("1");
		if(teacherdao.chekTeacherInDepart(depart.getDepartmentId(), depart.getDepartAdminId())==1){
			try {
				departdao.update(depart);
				teacherdao.updateDepartStatus(depart.getDepartAdminId(),depart.getDepartmentId());
				tx = departdao.getSession().beginTransaction();
				tx.commit();
				ServletActionContext.getResponse().getWriter().write("succ");
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				throw e;
			}finally{
				departdao.closeSession();
			}
		}else{
			try {
				ServletActionContext.getResponse().getWriter().write("notin");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 throw e;
			}
		}
	}
	
	public void deleteDepart() throws Exception{
		this.setDepart(departdao.findById(depart.getDepartmentId()));
		depart.setSpareTire("0");
		Transaction tx = null;
		try {
			departdao.merge(depart);
			tx = departdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			try {
				ServletActionContext.getResponse().getWriter().write("del_fail");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				 throw e;
			}
			throw e;
		}finally{
			departdao.closeSession();
		}
	}
/**************************研究所置*******ResearchLabSet*****************************/
	public String getResearchLabinfo() throws Exception{
		ServletActionContext.getRequest().setAttribute("ResearchLab", researchdao.findAll());
		return "success";
	}
	
	public String addResearchLab() throws Exception{
		research.setResearchLabId(pk.mkpk("ResearchLabID", "ResearchLab", "R"));
		research.setSpareTire("1");
		Transaction tx = null;
		try {
			researchdao.save(research);
			tx = researchdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}finally{
			researchdao.closeSession();
			getResearchLabinfo();
		}
		return "success";
	}
	
	public void updateResearchLab() throws Exception{
		Transaction tx = null;
		research.setResearchLabAdminId(research.getResearchLabAdmin().substring(0, 9));
		research.setSpareTire("1");
		if(teacherdao.chekTeacherInResearchLab(research.getResearchLabId(), research.getResearchLabAdminId())==1){
			try {
				researchdao.merge(research);
				teacherdao.updateResearchStatus(research.getResearchLabId(),research.getResearchLabAdminId());
				tx = researchdao.getSession().beginTransaction();
				tx.commit();
				ServletActionContext.getResponse().getWriter().write("succ");
			} catch (Exception e) {
				// TODO: handle exception
				tx.rollback();
				throw e;
			}finally{
				researchdao.closeSession();
			}
		}else{
			try {
				ServletActionContext.getResponse().getWriter().write("notin");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				 throw e;
			}
		}
	}
	
	public void deleteResearch() throws Exception{
		this.setResearch(researchdao.findById(research.getResearchLabId()));
		research.setSpareTire("0");
		Transaction tx = null;
		try {
			researchdao.merge(research);
			tx = researchdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			try {
				ServletActionContext.getResponse().getWriter().write("del_fail");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw e;
			}
			 throw e;
		}finally{
			researchdao.closeSession();
		}
	}
	/**************************国籍所置*******NationalitySet*****************************/
	public String getNationalityinfo() throws Exception{
		ServletActionContext.getRequest().setAttribute("Nationality", nationdao.findAll());
		return "success";
	}
	
	public String addNationality() throws Exception{
		nation.setCountryId(pk.mkpk("CountryID", "Nationality", "N"));
		nation.setSpareTire("1");
		Transaction tx = null;
		try {
			nationdao.save(nation);
			tx = nationdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}finally{
			nationdao.closeSession();
			getNationalityinfo();
		}
		return "success";
	}
	
	public void updateNationality() throws Exception{
		Transaction tx = null;
		nation.setSpareTire("1");
		HttpServletResponse resp = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		try {
			nationdao.merge(nation);
			tx = nationdao.getSession().beginTransaction();
			tx.commit();
			resp.getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			try {
				resp.getWriter().write("fail");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw e;
			}
			throw e;
		}finally{
			nationdao.closeSession();
		}
	}
	
	public void deletenation() throws Exception{
		Transaction tx = null;
		nation.setSpareTire("0");
		HttpServletResponse resp = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		try {
			nationdao.merge(nation);
			tx = nationdao.getSession().beginTransaction();
			tx.commit();
			resp.getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}finally{
			nationdao.closeSession();
		}
	}
	/**************************学期所置*******TermSet*****************************/
	public String getTermList() throws Exception{
		ServletActionContext.getRequest().setAttribute("Term", termDao.findAll());
		return "success";
	}
	
	public String addTerm() throws Exception{
		term.setTermId(pk.mkpk("termID", "Tfterm", "Term"));
		term.setSpareTire("1");
		Transaction tx = null;
		try {
			termDao.save(term);
			tx = termDao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}finally{
			termDao.closeSession();
			getTermList();
		}
		return "success";
	}
	
	public void updateTerm() throws Exception{
		Transaction tx = null;
		term.setSpareTire("1");
		HttpServletResponse resp = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		try {
			termDao.merge(term);
			tx = termDao.getSession().beginTransaction();
			tx.commit();
			resp.getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			try {
				resp.getWriter().write("fail");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw e;
			}
			throw e;
		}finally{
			termDao.closeSession();
		}
	}
	
	public void deleteTerm() throws Exception{
		Transaction tx = null;
		term.setSpareTire("0");
		HttpServletResponse resp = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		try {
			termDao.merge(term);
			tx = termDao.getSession().beginTransaction();
			tx.commit();
			resp.getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}finally{
			termDao.closeSession();
		}
	}


	/**************************Getter&Setter*****************************/
	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

	public ResearchLab getResearch() {
		return research;
	}

	public void setResearch(ResearchLab research) {
		this.research = research;
	}

	public Nationality getNation() {
		return nation;
	}
	

	public void setNation(Nationality nation) {
		this.nation = nation;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public Tfterm getTerm() {
		return term;
	}
	public void setTerm(Tfterm term) {
		this.term = term;
	}
	
	
}
