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
		if ((ResearchLab) session.get("selectedResearchLab_UJA") == null) {
			session.put("selectedResearchLab_UJA", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_UJA") == null) {
			session.put("pageSize_UJA", 1);
		}
		
		try {
			this.request.put("UnjoinedActList", this.vaunJoinRecordDAO
					.getUnjoinedActListAfterDivide(pageIndex,
							(Integer) session.get("pageSize_UJA"),
							(String) session.get("foredate_UJA"),
							(String) session.get("afterdate_UJA"),
							(ResearchLab) session.get("selectedResearchLab_UJA"),
							(String) session.get("checkOutStatus_UJA")));
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
		if ((ResearchLab) session.get("selectedResearchLab_UJA") == null) {
			session.put("selectedResearchLab_UJA", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_UJA") == null) {
			session.put("pageSize_UJA", 1);
		}try {
			System.out.println("11111111111111111");
			System.out.println(pageIndex);
			System.out.println(session.get("pageSize_UJA"));
			System.out.println(session.get("foredate_UJA"));
			System.out.println(session.get("afterdate_UJA"));
			System.out.println(session.get("selectedResearchLab_UJA"));
			System.out.println(session.get("checkOutStatus_UJA"));
			System.out.println("2222222222222");
			
			
			this.request.put("UnjoinedActList", this.vaunJoinRecordDAO
					.findAllWithCondition(pageIndex,
							(Integer) session.get("pageSize_UJA"),
							(String) session.get("foredate_UJA"),
							(String) session.get("afterdate_UJA"),
							(ResearchLab) session.get("selectedResearchLab_UJA"),
							(String) session.get("checkOutStatus_UJA")));
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
	
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	
	private int pageIndex = 1;
	private int pageSize_UJA;
	private String foredate_UJA;
	private String afterdate_UJA;
	private ResearchLab researchLab_UJA;
	private String checkOutStatus_UJA;
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

	public int getPageSize_UJA() {
		return pageSize_UJA;
	}

	public void setPageSize_UJA(int pageSize_UJA) {
		this.pageSize_UJA = pageSize_UJA;
		session.put("pageSize_UJA", pageSize_UJA);
	}

	public String getForedate_UJA() {
		return foredate_UJA;
	}

	public void setForedate_UJA(String foredate_UJA) {
		this.foredate_UJA = foredate_UJA;
		session.put("foredate_UJA", foredate_UJA);
	}

	public String getAfterdate_UJA() {
		return afterdate_UJA;
	}

	public void setAfterdate_UJA(String afterdate_UJA) {
		this.afterdate_UJA = afterdate_UJA;
		session.put("afterdate_UJA", afterdate_UJA);
	}

	public ResearchLab getResearchLab_UJA() {
		return researchLab_UJA;
	}

	public void setResearchLab_UJA(ResearchLab researchLab_UJA) {
		this.researchLab_UJA = researchLab_UJA;
		session.put("selectedResearchLab_UJA", researchLab_UJA);
	}

	public String getCheckOutStatus_UJA() {
		return checkOutStatus_UJA;
	}

	public void setCheckOutStatus_UJA(String checkOutStatus_UJA) {
		this.checkOutStatus_UJA = checkOutStatus_UJA;
		session.put("checkOutStatus_UJA", checkOutStatus_UJA);
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

	
	
}
