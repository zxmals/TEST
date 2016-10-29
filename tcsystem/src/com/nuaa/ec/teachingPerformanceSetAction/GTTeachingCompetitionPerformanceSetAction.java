package com.nuaa.ec.teachingPerformanceSetAction;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TfteachingCompetitionPerformanceDAO;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;

public class GTTeachingCompetitionPerformanceSetAction implements RequestAware{
	public String getAllRecordOfCurrentTeacher(){
		Transaction tx=this.teachingCompetitionPerformanceDAO.getSession().beginTransaction();
		try{
			
		}catch(Exception ex){
			ex.printStackTrace();
			this.setOperstatus(-1);
			tx.rollback();
		}
		return "success";
	}
	public String execute() throws Exception{
		return "success";
	}
	private int pageIndex=1;
	private int pageSize_GTTCP;
	private int operstatus;
	private String isDivided;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private HttpServletResponse response=ServletActionContext.getResponse();
	private TfteachingCompetitionPerformanceDAO teachingCompetitionPerformanceDAO=new TfteachingCompetitionPerformanceDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_GTTCP() {
		return pageSize_GTTCP;
	}
	public void setPageSize_GTTCP(int pageSize_GTTCP) {
		this.pageSize_GTTCP = pageSize_GTTCP;
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public String getIsDivided() {
		return isDivided;
	}
	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}
}
