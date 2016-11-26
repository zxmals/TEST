package com.nuaa.ec.va.audit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transaction;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VacollectiveAct;
import com.opensymphony.xwork2.ActionContext;

public class NewActApplyAudit implements RequestAware{
	public void doCheckOutTask(){
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
	List<VacollectiveAct> checkoutList = new ArrayList<VacollectiveAct>();
	VacollectiveAct vacollectiveAct = null;
	for (int i = 0; i < ids.length; i++) {
		if (ids[i]!=null && ids[i].length()!=0) {
			vacollectiveAct = this.vacollectiveActDAO.findById(ids[i]);
			
			// 修改checkout 标志
			if (vacollectiveAct!=null) {
				vacollectiveAct.setAspareTire("1");
				checkoutList.add(vacollectiveAct);
			}
		}
	}
	for (int i = 0; i < idsNot.length; i++) {
		if (idsNot[i]!=null && idsNot[i].length()!=0 ) {
			
			vacollectiveAct=this.vacollectiveActDAO.findById(idsNot[i]);
			if(vacollectiveAct!=null){
				vacollectiveAct.setAspareTire("2");
				checkoutList.add(vacollectiveAct);
		}
	}
	}
	try {
		if (vacollectiveActDAO.updateASparetire(checkoutList)) {
			ServletActionContext.getResponse().getWriter().write("succ");
		}else {
			ServletActionContext.getResponse().getWriter().write("error");
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}
	
	public String getNewActApplyList(){
		org.hibernate.Transaction tx = this.vacollectiveActDAO.getSession().beginTransaction();
		if ((Department)session.get("department_CT")== null) {
			session.put("department_CT", new Department());
		}
		if ((Integer)session.get("pageSize_CT")== null) {
			session.put("pageSize_CT", 1);
		}
		try {
			this.request.put("newActApplyList", this.vacollectiveActDAO.
					getNewActApplyList(
							pageIndex,
							(Integer)session.get("pageSize_CT"),
							(Department)session.get("department_CT"),
							(String)session.get("checkOutStatus_CT"),
							false)
					);
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
		
	}
	
	public String getNewActApplyListAfterDivided(){
		org.hibernate.Transaction tx = this.vacollectiveActDAO.getSession().beginTransaction();
		if ((Department)session.get("department_CT")== null) {
			session.put("department_CT", new Department());
		}
		if ((Integer)session.get("pageSize_CT")== null) {
			session.put("pageSize_CT", 1);
		}
		try {
			this.request.put("newActApplyList", this.vacollectiveActDAO.
					getNewActApplyList(
							pageIndex,
							(Integer)session.get("pageSize_CT"),
							(Department)session.get("department_CT"),
							(String)session.get("checkOutStatus_CT"),
							true)
					);
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
		
	}
	
	
	
	
	private int pageIndex = 1;
	private int pageSize_CT;
	private ResearchLab researchLab_CT;
	private int operstatus;
	private Department department_CT;
	private String foredate_CT;
	private String afterdate_CT;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;
	private String checkOutStatus_CT;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private VacollectiveActDAO vacollectiveActDAO = new VacollectiveActDAO();
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_CT() {
		return pageSize_CT;
	}
	public void setPageSize_CT(int pageSize_CT) {
		this.pageSize_CT = pageSize_CT;
		session.put("pageSize_CT", pageSize_CT);
	}
	public ResearchLab getResearchLab_CT() {
		return researchLab_CT;
	}
	public void setResearchLab_CT(ResearchLab researchLab_CT) {
		this.researchLab_CT = researchLab_CT;
		session.put("researchLab_CT", researchLab_CT);
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	public String getCheckOutStatus_CT() {
		return checkOutStatus_CT;
	}
	public void setCheckOutStatus_CT(String checkOutStatus_CT) {
		this.checkOutStatus_CT = checkOutStatus_CT;
		session.put("checkOutStatus_CT", checkOutStatus_CT);
	}
	public String getCheckOutIDs() {
		return checkOutIDs;
	}
	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}
	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}
	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}
	public VacollectiveActDAO getVacollectiveActDAO() {
		return vacollectiveActDAO;
	}
	public void setVacollectiveActDAO(VacollectiveActDAO vacollectiveActDAO) {
		this.vacollectiveActDAO = vacollectiveActDAO;
	}
	public Map<String, Object> getRequest() {
		return request;
	}

	public String getForedate_CT() {
		return foredate_CT;
	}

	public void setForedate_CT(String foredate_CT) {
		this.foredate_CT = foredate_CT;
		session.put("foredate_CT", foredate_CT);
	}

	public String getAfterdate_CT() {
		return afterdate_CT;
	}

	public void setAfterdate_CT(String afterdate_CT) {
		this.afterdate_CT = afterdate_CT;
		session.put("afterdate_CT", afterdate_CT);
	}
	public Department getDepartment_CT() {
		return department_CT;
	}
	public void setDepartment_CT(Department department_CT) {
		this.department_CT = department_CT;
		session.put("department_CT", department_CT);
	}
}
