package com.nuaa.ec.teaching.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.TfenterpriseWorkstationTrainingBaseConstructionPerformance;
import com.opensymphony.xwork2.ActionContext;

public class TfenterpriseWorkstationTrainingBaseConstructionPerformanceAuditAction
		implements RequestAware {
	public void doCheckOutTask() {
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		List<TfenterpriseWorkstationTrainingBaseConstructionPerformance> checkoutList = new ArrayList<TfenterpriseWorkstationTrainingBaseConstructionPerformance>();
		TfenterpriseWorkstationTrainingBaseConstructionPerformance Tf_EN_WTB_CONS_PERF_List = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				Tf_EN_WTB_CONS_PERF_List = this.TfEN_WTBC_PerformDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(Tf_EN_WTB_CONS_PERF_List!=null){
					Tf_EN_WTB_CONS_PERF_List.setCheckOut("1");
					checkoutList.add(Tf_EN_WTB_CONS_PERF_List);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				Tf_EN_WTB_CONS_PERF_List=this.TfEN_WTBC_PerformDAO.findById(Integer.parseInt(idsNot[i]));
				if(Tf_EN_WTB_CONS_PERF_List!=null){
					Tf_EN_WTB_CONS_PERF_List.setCheckOut("2");
					checkoutList.add(Tf_EN_WTB_CONS_PERF_List);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TfEN_WTBC_PerformDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// this.getResearchLabList();
	}
	public String getTfenterpriseWorkstationTrainingBaseConstructionList() {
		Transaction tx = null;
		boolean isDivided=false;
		//判断是否为分页
		if(this.isDivided!=null && this.isDivided.equals("true")){
			//if里面的两个条件不能交换位置，否则会发生逻辑短路问题，引发空指针异常
			isDivided=true;
		}
		if ((Department) session.get("department_EWTB") == null) {
			session.put("department_EWTB", new Department());
		}
		if ((Integer) session.get("pageSize_EWTB") == null) {
			session.put("pageSize_EWTB", pageSize_EWTB);
		}
		try {
			this.request
					.put("Tf_EN_WTB_CONS_PERF_List",
							this.TfEN_WTBC_PerformDAO.getTf_EN_WTB_Cons_PERF(
									pageIndex,
									(Integer) session.get("pageSize_EWTB"),
									(String) session.get("termId_EWTB"),
									(Department) session.get("department_EWTB"),
									(String) session.get("checkOutStatus_EWTB"),
									isDivided));
			tx = this.TfEN_WTBC_PerformDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		} finally {
//			this.TfEN_WTBC_PerformDAO.closeSession();
		}
		return "success";
	}

	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_EWTB;
	private String termId_EWTB;
	private Department department_EWTB;
	private int operstatus;
	private String checkOutStatus_EWTB;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO TfEN_WTBC_PerformDAO = new TfenterpriseWorkstationTrainingBaseConstructionPerformanceDAO();
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;
	private String isDivided;
	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize_EWTB() {
		return pageSize_EWTB;
	}

	public void setPageSize_EWTB(int pageSize_EWTB) {
		this.pageSize_EWTB = pageSize_EWTB;
		session.put("pageSize_EWTB", pageSize_EWTB);
	}

	public String getTermId_EWTB() {
		return termId_EWTB;
	}

	public void setTermId_EWTB(String termId_EWTB) {
		this.termId_EWTB = termId_EWTB;
		session.put("termId_EWTB", termId_EWTB);
	}

	public Department getDepartment_EWTB() {
		return department_EWTB;
	}

	public void setDepartment_EWTB(Department department_EWTB) {
		this.department_EWTB = department_EWTB;
		session.put("department_EWTB", department_EWTB);
	}

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public String getCheckOutStatus_EWTB() {
		return checkOutStatus_EWTB;
	}

	public void setCheckOutStatus_EWTB(String checkOutStatus_EWTB) {
		this.checkOutStatus_EWTB = checkOutStatus_EWTB;
		session.put("checkOutStatus_EWTB", checkOutStatus_EWTB);
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

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getIsDivided() {
		return isDivided;
	}

	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}
}
