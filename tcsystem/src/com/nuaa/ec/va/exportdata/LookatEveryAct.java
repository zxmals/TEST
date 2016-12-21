package com.nuaa.ec.va.exportdata;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.VacollectiveActivitiesPublishDAO;
import com.nuaa.ec.dao.VateacherAndCollectiveActDAO;
import com.nuaa.ec.model.Department;

public class LookatEveryAct implements SessionAware,RequestAware{
	private String afterdate;
	private String foredate;
	private Map<String, Object> session ;
	private Map<String, Object> request;
	private Department department;
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private VacollectiveActivitiesPublishDAO vacollectiveActivitiesPublishDAO = new VacollectiveActivitiesPublishDAO();
	private VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO = new VateacherAndCollectiveActDAO();
	private String vaacttype;
	public String execute(){
		return "success";
	}
	public void lookateveryAct() throws Exception{
		Transaction tx = null;
		try {
			this.request.put("vaactList", this.vacollectiveActivitiesPublishDAO.getNewActPublishAct(department,
					vaacttype,
					foredate,
					afterdate
					));
			tx = this.vacollectiveActivitiesPublishDAO.getSession().beginTransaction();
			tx.commit();
					
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
	}
	
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
	}

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		this.foredate = foredate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getVaacttype() {
		return vaacttype;
	}

	public void setVaacttype(String vaacttype) {
		this.vaacttype = vaacttype;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}
}
