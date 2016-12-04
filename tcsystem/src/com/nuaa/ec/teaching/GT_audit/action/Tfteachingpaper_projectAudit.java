package com.nuaa.ec.teaching.GT_audit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingPaperProjectDAO;
import com.nuaa.ec.dao.TfteachingRearchProjectDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TfteachingPaperProject;
import com.nuaa.ec.model.TfteachingRearchProject;
import com.nuaa.ec.model.Tfterm;

public class Tfteachingpaper_projectAudit implements RequestAware,SessionAware{
	public void doCheckOutTask(){
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");	
		List<TfteachingPaperProject> checkoutList = new ArrayList<TfteachingPaperProject>();
		TfteachingPaperProject tfteachingPaperProject = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].length() != 0 && ids[i] != null) {
				tfteachingPaperProject = this.tfteachingPaperProjectDAO.findById(ids[i]);
				if (tfteachingPaperProject!=null) {
					tfteachingPaperProject.setCheckOut("1");;
					checkoutList.add(tfteachingPaperProject);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i] != null && idsNot[i].length() != 0) {
				tfteachingPaperProject = this.tfteachingPaperProjectDAO.findById(idsNot[i]);
				if (tfteachingPaperProject!=null) {
					tfteachingPaperProject.setCheckOut("2");
					checkoutList.add(tfteachingPaperProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (tfteachingPaperProjectDAO.updateCheckOutStatus(checkoutList)) {
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
		if ((Integer)session.get("pageSize_TFTP") == null) {
			session.put("pageSize_TFTP", 1);
		}
		if ((Tfterm)session.get("term_TFTP") == null) {
			session.put("term_TFTP", new Tfterm());
		}
		try {
			this.request.put("TFteachingPaper", this.tfteachingPaperProjectDAO.getTFteachingPaperProject(pageIndex,
					(Integer) session.get("pageSize_TFTP"),
					(Tfterm) session.get("term_TFTP"),
					(String)session.get("checkOutStatus_TFTP"),
					((Teacher)session.get("teacher")).getDepartment(),
					false
					));
			tx = tfteachingPaperProjectDAO.getSession().beginTransaction();
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
		Transaction tx = this.tfteachingPaperProjectDAO.getSession().beginTransaction();
		if ((Integer)session.get("pageSize_TFTP") == null) {
			session.put("pageSize_TFTP", 1);
		}
		if ((Tfterm)session.get("term_TFTP") == null) {
			session.put("term_TFTP", new Tfterm());
		}
		try {
			this.request.put("TFteachingPaper", this.tfteachingPaperProjectDAO.getTFteachingPaperProject(pageIndex,
					(Integer) session.get("pageSize_TFTP"),
					(Tfterm) session.get("term_TFTP"),
					(String)session.get("checkOutStatus_TFTP"),
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
	private int pageSize_TFTP;
	private Department department_TFTP;
	private int operstatus;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private TfteachingPaperProjectDAO tfteachingPaperProjectDAO = new TfteachingPaperProjectDAO();
	private String checkOutStatus_TFTP;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private Tfterm term_TFTP;
	
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


	public int getPageSize_TFTP() {
		return pageSize_TFTP;
	}


	public void setPageSize_TFTP(int pageSize_TFTP) {
		this.pageSize_TFTP = pageSize_TFTP;
		session.put("pageSize_TFTP", pageSize_TFTP);
	}


	public Department getDepartment_TFTP() {
		return department_TFTP;
	}


	public void setDepartment_TFTP(Department department_TFTP) {
		this.department_TFTP = department_TFTP;
	}


	public int getOperstatus() {
		return operstatus;
	}


	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}


	public TfteachingPaperProjectDAO getTfteachingPaperProjectDAO() {
		return tfteachingPaperProjectDAO;
	}

	public void setTfteachingPaperProjectDAO(
			TfteachingPaperProjectDAO tfteachingPaperProjectDAO) {
		this.tfteachingPaperProjectDAO = tfteachingPaperProjectDAO;
	}

	public String getCheckOutStatus_TFTP() {
		return checkOutStatus_TFTP;
	}


	public void setCheckOutStatus_TFTP(String checkOutStatus_TFTP) {
		this.checkOutStatus_TFTP = checkOutStatus_TFTP;
		session.put("checkOutStatus_TFTP", checkOutStatus_TFTP);
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

	public Tfterm getTerm_TFTP() {
		return term_TFTP;
	}

	public void setTerm_TFTP(Tfterm term_TFTP) {
		this.term_TFTP = term_TFTP;
		session.put("term_TFTP", term_TFTP);
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
