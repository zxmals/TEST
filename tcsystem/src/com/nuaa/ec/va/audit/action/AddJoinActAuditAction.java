package com.nuaa.ec.va.audit.action;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.model.VacollectiveAct;
import com.opensymphony.xwork2.ActionContext;

public class AddJoinActAuditAction {
	public void doCheckOut(){
		java.util.List<VacollectiveAct> checkoutList = new ArrayList<VacollectiveAct>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		VacollectiveAct vacollectiveAct = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				vacollectiveAct = this.vacollectiveActDAO.findById(Integer.parseInt(ids[i]));
				//修改ASpareTire 标志
				if (vacollectiveAct != null) {
					vacollectiveAct.setSpareTire("1");
					checkoutList.add(vacollectiveAct);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				vacollectiveAct=this.vacollectiveActDAO.findById(Integer.parseInt(idsNot[i]));
				if(vacollectiveAct!=null){
					vacollectiveAct.setAspareTire("2");
					checkoutList.add(vacollectiveAct);
				}
			}
		}
		try {
			if (vacollectiveActDAO.updateAsparetire(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 分页控制action
	 */
	public String getRecordsInWithConditions() {
		//storageDate();
		Transaction tx = vacollectiveActDAO.getSession().beginTransaction();

		if ((Integer) session.get("pageSize") == null) {
			session.put("pageSize", 1);
		}
		try {
			this.request.put("VA_AddJoinActList", vacollectiveActDAO.findAll(pageIndex,
					(Integer)session.get("pageSize"), 
					(String) session.get("foredate"),
					(String) session.get("afterdate"),
					(String)session.get("AspareTire")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			this.setOperstatus(-1);
			e.printStackTrace();
			tx.rollback();		
		}
		return "success";
	}
 
	public String getAddJoinActToBeAudited(){
		Transaction tx = vacollectiveActDAO.getSession().beginTransaction();
		if ((Integer) session.get("pageSize") == null) {
			session.put("pageSize", 1);
		}
		try {
			this.request.put("VA_AddJoinActList", vacollectiveActDAO.findAll(pageIndex,
					(Integer)session.get("pageSize"), 
					(String) session.get("foredate"),
					(String) session.get("afterdate"),
					(String)session.get("AspareTire")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			this.setOperstatus(-1);
			e.printStackTrace();
			tx.rollback();		
		}
		return "success";
	}
		
	
	
	private String foredate;
	private String afterdate;
	private Integer operstatus;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize = 1;
	private String ASpareTire = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	private VacollectiveAct vacollectiveAct = new VacollectiveAct();
	private VacollectiveActDAO vacollectiveActDAO = new VacollectiveActDAO();
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getForedate() {
		return foredate;
	}
	public void setForedate(String foredate) {
		this.foredate = foredate;
	}
	public String getAfterdate() {
		return afterdate;
	}
	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
	}
	public Integer getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public String getAspareTire() {
		return ASpareTire;
	}
	public void setAspareTire(String aspareTire) {
		ASpareTire = aspareTire;
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
	public VacollectiveAct getVacollectiveAct() {
		return vacollectiveAct;
	}
	public void setVacollectiveAct(VacollectiveAct vacollectiveAct) {
		this.vacollectiveAct = vacollectiveAct;
	}
	public VacollectiveActDAO getVacollectiveActDAO() {
		return vacollectiveActDAO;
	}
	public void setVacollectiveActDAO(VacollectiveActDAO vacollectiveActDAO) {
		this.vacollectiveActDAO = vacollectiveActDAO;
	}
}
