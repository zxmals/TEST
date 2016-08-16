package com.nuaa.ec.action;

import org.apache.struts2.ServletActionContext;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.NationalityDAO;
import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Nationality;
import com.nuaa.ec.model.ResearchLab;

public class BaseSetAction {

	private Department depart;
	private ResearchLab research;
	private Nationality nation;
	private DepartmentDAO departdao = new DepartmentDAO();
	private ResearchLabDAO researchdao = new ResearchLabDAO();
	private NationalityDAO nationdao = new NationalityDAO();

	// default method
	public String execute() {
		return "success";
	}

	public String getDepartinfo(){
		try {
			ServletActionContext.getRequest().setAttribute("Department", departdao.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

}
