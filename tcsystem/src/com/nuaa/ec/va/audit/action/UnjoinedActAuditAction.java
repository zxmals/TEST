package com.nuaa.ec.va.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherAndacademicWorkDAO;
import com.nuaa.ec.dao.VaunJoinRecordDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VaunJoinRecord;
import com.opensymphony.xwork2.ActionContext;

public class UnjoinedActAuditAction implements RequestAware{
	public void doCheckOutTask(){
		List<VaunJoinRecord> checkoutList = new ArrayList<VaunJoinRecord>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		VaunJoinRecord vaunJoinRecord = null;
		for (int i = 0; i < ids.length; i++) {
			vaunJoinRecord = this.vaunJoinRecordDAO.findById(ids[i]);
			// 修改checkout 标志
			if(vaunJoinRecord!=null){
				vaunJoinRecord.setAsparetire("1");
				checkoutList.add(vaunJoinRecord);
			}
		}
		
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				vaunJoinRecord=this.vaunJoinRecordDAO.findById(idsNot[i]);
				if(vaunJoinRecord!=null){
					vaunJoinRecord.setAsparetire("2");
					checkoutList.add(vaunJoinRecord);
				}
			}
		}
		
		try {
			if (vaunJoinRecordDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getUnjoinedActListAfterDivide(){
		Transaction tx = this.vaunJoinRecordDAO.getSession().beginTransaction();
		if ((Department) session.get("department_UA") == null) {
			session.put("department_UA", new Department());
		}
		if ((Integer) session.get("pageSize_UA") == null) {
			session.put("pageSize_UA", 1);
		}
		try {
			this.request.put("UnjoinedActList",  this.vaunJoinRecordDAO
					.getUnjoinedActListAfterDivide(pageIndex,
							(Integer) session.get("pageSize_UA"),
							(String) session.get("foredate_UA"),
							(String) session.get("afterdate_UA"),
							(Department) session.get("department_UA"),
							(String) session.get("checkOutStatus_UA")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	
	public String getUnjoinedActList(){
		Transaction tx = this.vaunJoinRecordDAO.getSession()
				.beginTransaction();
		if ((Department) session.get("department_UA") == null) {
			session.put("department_UA", new Department());
		}
		if ((Integer) session.get("pageSize_UA") == null) {
			session.put("pageSize_UA", 1);
		}try {
			
			this.request.put("UnjoinedActList",  this.vaunJoinRecordDAO
					.findAllWithCondition(pageIndex,
							(Integer) session.get("pageSize_UA"),
							(String) session.get("foredate_UA"),
							(String) session.get("afterdate_UA"),
							(Department) session.get("department_UA"),
							(String) session.get("checkOutStatus_UA")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	public String execute()throws Exception{
		return "success";
	}
	
	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	private int pageIndex = 1;
	private int pageSize_UA;
	private String foredate_UA;
	private String afterdate_UA;
	private ResearchLab researchLab_UA;
	private Department department_UA;
	private String checkOutStatus_UA;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private int operstatus;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;
	private VaunJoinRecordDAO vaunJoinRecordDAO = new VaunJoinRecordDAO();
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_UA() {
		return pageSize_UA;
	}

	public void setPageSize_UA(int pageSize_UA) {
		this.pageSize_UA = pageSize_UA;
		session.put("pageSize_UA", pageSize_UA);
	}

	public String getForedate_UA() {
		return foredate_UA;
	}

	public void setForedate_UA(String foredate_UA) {
		this.foredate_UA = foredate_UA;
		session.put("foredate_UA", foredate_UA);
	}

	public String getAfterdate_UA() {
		return afterdate_UA;
	}

	public void setAfterdate_UA(String afterdate_UA) {
		this.afterdate_UA = afterdate_UA;
		session.put("afterdate_UA", afterdate_UA);
	}

	public ResearchLab getResearchLab_UA() {
		return researchLab_UA;
	}

	public void setResearchLab_UA(ResearchLab researchLab_UA) {
		this.researchLab_UA = researchLab_UA;
		session.put("researchLab_UA", researchLab_UA);
	}

	public String getCheckOutStatus_UA() {
		return checkOutStatus_UA;
	}

	public void setCheckOutStatus_UA(String checkOutStatus_UA) {
		this.checkOutStatus_UA = checkOutStatus_UA;
		session.put("checkOutStatus_UA", checkOutStatus_UA);
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

	public VaunJoinRecordDAO getVaunJoinRecordDAO() {
		return vaunJoinRecordDAO;
	}

	public void setVaunJoinRecordDAO(VaunJoinRecordDAO vaunJoinRecordDAO) {
		this.vaunJoinRecordDAO = vaunJoinRecordDAO;
	}

	public Map<String, Object> getRequest() {
		return request;
	}
	
	public Department getDepartment_UA() {
		return department_UA;
	}
	public void setDepartment_UA(Department department_UA) {
		this.department_UA = department_UA;
		session.put("department_UA", department_UA);
	}
	
	
}
