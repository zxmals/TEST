package com.nuaa.ec.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.NationalityDAO;
import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Nationality;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.utils.PrimaryKMaker;

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

	// default method
	public String execute() {
		return "success";
	}

	public String getDepartinfo(){
		try {
			ServletActionContext.getRequest().setAttribute("Department", departdao.findAll());
			session.put("teachertranslate", teacherdao.findAllT());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	
	public String adddepart(){
		depart.setDepartmentId(pk.mkpk("DepartmentID", "Department", "D"));
		depart.setSpareTire("1");
		Transaction tx = null;
		try {
			departdao.save(depart);			
			tx = departdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}finally{
			departdao.closeSession();
			try {
				ServletActionContext.getRequest().setAttribute("Department", departdao.findAll());
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return "success";
	}
	
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

}
