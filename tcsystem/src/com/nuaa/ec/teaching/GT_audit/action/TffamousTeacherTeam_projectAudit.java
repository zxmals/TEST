package com.nuaa.ec.teaching.GT_audit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TffamousTeacherTeamProjectDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TffamousTeacherTeamProject;
import com.nuaa.ec.model.Tfterm;
import com.opensymphony.xwork2.ActionContext;
import com.sun.net.httpserver.Authenticator.Success;

public class TffamousTeacherTeam_projectAudit implements RequestAware,SessionAware{
	public void doCheckOutTask(){
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");	
		List<TffamousTeacherTeamProject> checkoutList = new ArrayList<TffamousTeacherTeamProject>();
		TffamousTeacherTeamProject tffamousTeacherTeamProject = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].length() != 0 && ids[i] != null) {
				tffamousTeacherTeamProject = this.tffamousTeacherTeamProjectDAO.findById(ids[i]);
				if (tffamousTeacherTeamProject!=null) {
					tffamousTeacherTeamProject.setCheckout("1");
					checkoutList.add(tffamousTeacherTeamProject);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				tffamousTeacherTeamProject = this.tffamousTeacherTeamProjectDAO.findById(idsNot[i]);
				if (tffamousTeacherTeamProject!=null) {
					tffamousTeacherTeamProject.setCheckout("1");
					checkoutList.add(tffamousTeacherTeamProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tffamousTeacherTeamProjectDAO.updateCheckOutStatus(checkoutList)) {
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
		Transaction tx =null;
		if ((Integer)session.get("pageSize_TFTTPA") == null) {
			session.put("pageSize_TFTTPA", 1);
		}
		if ((Tfterm)session.get("term_TFTTPA") == null) {
			session.put("term_TFTTPA", new Tfterm());
		}
		try {
			this.request.put("TFfamousTeacherTeam", this.tffamousTeacherTeamProjectDAO.getTFfamousTeacherTeam(pageIndex,
					(Integer) session.get("pageSize_TFTTPA"),
					(Tfterm) session.get("term_TFTTPA"),
					(String)session.get("checkOutStatus_TFTTPA"),
					((Teacher)session.get("teacher")).getDepartment(),
					false
					));
			tx = tffamousTeacherTeamProjectDAO.getSession().beginTransaction();
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
		Transaction tx = this.tffamousTeacherTeamProjectDAO.getSession().beginTransaction();
		if ((Integer)session.get("pageSize_TFTTPA") == null) {
			session.put("pageSize_TFTTPA", 1);
		}
		if ((Tfterm)session.get("term_TFTTPA") == null) {
			session.put("term_TFTTPA", new Tfterm());
		}
		try {
			this.request.put("TFfamousTeacherTeam", this.tffamousTeacherTeamProjectDAO.getTFfamousTeacherTeam(pageIndex,
					(Integer) session.get("pageSize_TFTTPA"),
					(Tfterm) session.get("term_TFTTPA"),
					(String)session.get("checkOutStatus_CT"),
					((Teacher)session.get("teacher")).getDepartment(),
					true
					));
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
	private int pageSize_TFTTPA;
	private Department department_TFTTPA;
	private int operstatus;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private TffamousTeacherTeamProjectDAO tffamousTeacherTeamProjectDAO = new TffamousTeacherTeamProjectDAO();
	private String checkOutStatus_TFTTPA;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private Tfterm term_TFTTPA;
	
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


	public int getPageSize_TFTTPA() {
		return pageSize_TFTTPA;
	}


	public void setPageSize_TFTTPA(int pageSize_TFTTPA) {
		this.pageSize_TFTTPA = pageSize_TFTTPA;
		session.put("pageSize_TFTTPA", pageSize_TFTTPA);
	}


	public Department getDepartment_TFTTPA() {
		return department_TFTTPA;
	}


	public void setDepartment_TFTTPA(Department department_TFTTPA) {
		this.department_TFTTPA = department_TFTTPA;
	}


	public int getOperstatus() {
		return operstatus;
	}


	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}


	public TffamousTeacherTeamProjectDAO getTffamousTeacherTeamProjectDAO() {
		return tffamousTeacherTeamProjectDAO;
	}


	public void setTffamousTeacherTeamProjectDAO(
			TffamousTeacherTeamProjectDAO tffamousTeacherTeamProjectDAO) {
		this.tffamousTeacherTeamProjectDAO = tffamousTeacherTeamProjectDAO;
	}


	public String getCheckOutStatus_TFTTPA() {
		return checkOutStatus_TFTTPA;
	}


	public void setCheckOutStatus_TFTTPA(String checkOutStatus_TFTTPA) {
		this.checkOutStatus_TFTTPA = checkOutStatus_TFTTPA;
		session.put("checkOutStatus_TFTTPA", checkOutStatus_TFTTPA);
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

	public Tfterm getTerm_TFTTPA() {
		return term_TFTTPA;
	}

	public void setTerm_TFTTPA(Tfterm term_TFTTPA) {
		this.term_TFTTPA = term_TFTTPA;
		session.put("term_TFTTPA", term_TFTTPA);
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
