package com.nuaa.ec.teaching.GT_audit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TffineCourseConstructionProjectDAO;
import com.nuaa.ec.dao.TftextbookConstructionProjectDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TffineCourseConstructionProject;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.model.TftextbookConstructionProject;

public class Tfteachingfinecourse_projectAudit implements RequestAware,SessionAware{
	public void doCheckOutTask(){
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");	
		List<TffineCourseConstructionProject> checkoutList = new ArrayList<TffineCourseConstructionProject>();
		List<TffineCourseConstructionProject> checkoutYESList = new ArrayList<TffineCourseConstructionProject>();
		List<TffineCourseConstructionProject> checkoutNOTList = new ArrayList<TffineCourseConstructionProject>();
		TffineCourseConstructionProject tffineCourseConstructionProject = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].length() != 0 && ids[i] != null) {
				tffineCourseConstructionProject = this.tffineCourseConstructionProjectDAO.findById(ids[i]);
				if (tffineCourseConstructionProject!=null) {
					tffineCourseConstructionProject.setCheckOut("1");;
					checkoutList.add(tffineCourseConstructionProject);
					checkoutYESList.add(tffineCourseConstructionProject);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				tffineCourseConstructionProject = this.tffineCourseConstructionProjectDAO.findById(idsNot[i]);
				if (tffineCourseConstructionProject!=null) {
					tffineCourseConstructionProject.setCheckOut("2");
					checkoutList.add(tffineCourseConstructionProject);
					checkoutNOTList.add(tffineCourseConstructionProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tffineCourseConstructionProjectDAO.updateCheckOutStatus(checkoutList) && tffineCourseConstructionProjectDAO.cascadeUpdateProjectMembersCheckout(checkoutYESList,"1")&&tffineCourseConstructionProjectDAO.cascadeUpdateProjectMembersCheckout(checkoutNOTList,"2")) {
				ServletActionContext.getResponse().getWriter().write("succ");
			}else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public String getAllRecord_project() throws Exception{
		Transaction tx = null;
		if ((Integer)session.get("pageSize_TFTR") == null) {
			session.put("pageSize_TFTR", 1);
		}
		if ((Tfterm)session.get("term_TFTR") == null) {
			session.put("term_TFTR", new Tfterm());
		}
		try {
			this.request.put("TFfinecourseConstruction", this.tffineCourseConstructionProjectDAO.getTFfinecourseConstructionProject(pageIndex,
					(Integer) session.get("pageSize_TFTR"),
					(Tfterm) session.get("term_TFTR"),
					(String)session.get("checkOutStatus_TFTR"),
					((Teacher)session.get("teacher")).getDepartment(),
					false
					));
			tx = tffineCourseConstructionProjectDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			this.setOperstatus(-1);
			tx.rollback();
			throw e;
		}
		
		return "success";
	}
	
	public String  getAllRecord_projectAfterDivide(){
		Transaction tx = null;
		if ((Integer)session.get("pageSize_TFTR") == null) {
			session.put("pageSize_TFTR", 1);
		}
		if ((Tfterm)session.get("term_TFTR") == null) {
			session.put("term_TFTR", new Tfterm());
		}
		try {
			this.request.put("TFfinecourseConstruction", this.tffineCourseConstructionProjectDAO.getTFfinecourseConstructionProject(pageIndex,
					(Integer) session.get("pageSize_TFTR"),
					(Tfterm) session.get("term_TFTR"),
					(String)session.get("checkOutStatus_TFTR"),
					((Teacher)session.get("teacher")).getDepartment(),
					true
					));
			tx=this.tffineCourseConstructionProjectDAO.getSession().beginTransaction();
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
	
	public String execute() throws Exception{
		return "success";
	}
	
	private int pageIndex = 1;
	private int pageSize_TFTR;
	private Department department_TFTR;
	private int operstatus;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private TffineCourseConstructionProjectDAO tffineCourseConstructionProjectDAO = new TffineCourseConstructionProjectDAO();
	private String checkOutStatus_TFTR;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private Tfterm term_TFTR;
	
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


	public int getPageSize_TFTR() {
		return pageSize_TFTR;
	}


	public void setPageSize_TFTR(int pageSize_TFTR) {
		this.pageSize_TFTR = pageSize_TFTR;
		session.put("pageSize_TFTR", pageSize_TFTR);
	}


	public Department getDepartment_TFTR() {
		return department_TFTR;
	}


	public void setDepartment_TFTR(Department department_TFTR) {
		this.department_TFTR = department_TFTR;
	}


	public int getOperstatus() {
		return operstatus;
	}


	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	

	
	public TffineCourseConstructionProjectDAO getTffineCourseConstructionProjectDAO() {
		return tffineCourseConstructionProjectDAO;
	}

	public void setTffineCourseConstructionProjectDAO(
			TffineCourseConstructionProjectDAO tffineCourseConstructionProjectDAO) {
		this.tffineCourseConstructionProjectDAO = tffineCourseConstructionProjectDAO;
	}

	public String getCheckOutStatus_TFTR() {
		return checkOutStatus_TFTR;
	}


	public void setCheckOutStatus_TFTR(String checkOutStatus_TFTR) {
		this.checkOutStatus_TFTR = checkOutStatus_TFTR;
		session.put("checkOutStatus_TFTR", checkOutStatus_TFTR);
	}


	public String getCheckOutID() {
		return checkOutIDs;
	}


	public void setCheckOutID(String checkOutID) {
		this.checkOutIDs = checkOutID;
	}


	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}


	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}

	public Tfterm getTerm_TFTR() {
		return term_TFTR;
	}

	public void setTerm_TFTR(Tfterm term_TFTR) {
		this.term_TFTR = term_TFTR;
		session.put("term_TFTR", term_TFTR);
	}

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}

	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	
}
