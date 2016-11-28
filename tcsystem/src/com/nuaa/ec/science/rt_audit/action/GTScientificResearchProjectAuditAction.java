package com.nuaa.ec.science.rt_audit.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.ScientificResearchProjectDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchProjectDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.opensymphony.xwork2.ActionContext;

public class GTScientificResearchProjectAuditAction implements RequestAware {
	public String getAllRecord(){
		Transaction tx = null;
		boolean isDivided = false;
		String isDivided_s=(String) session.get("isDivided");
		if (isDivided_s!=null && isDivided_s.trim().equals("true")) {
			isDivided = true;
		}
		String pageSize_s = (String) session.get("pageSize_GTSRP");
		if (pageSize_s != null) {
			pageSize_GTSRP = Integer.parseInt(pageSize_s);
		}
		this.request.put("SRPProjectList", this.scientificResearchProjectDAO
				.getAllRecordWithCondition_RT(pageIndex, pageSize_GTSRP,
						foredate_GTSRP, afterdate_GTSRP, researchLab_GTSRP,
						checkout_GTSRP, isDivided));
		try{
			this.TARProjectDAO.getSession().beginTransaction().commit();
		}catch(Exception ex){
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}

	private String foredate_GTSRP;
	private String afterdate_GTSRP;
	private Integer operstatus;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTSRP = 1;
	private ResearchLab researchLab_GTSRP;
	private String checkout_GTSRP = "0";
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private ResearchLabDAO researchDAO = new ResearchLabDAO();
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private ScientificResearchProjectDAO scientificResearchProjectDAO = new ScientificResearchProjectDAO();
	private TeacherAndscientificResearchProject TARProject = new TeacherAndscientificResearchProject();
	private TeacherAndscientificResearchProjectDAO TARProjectDAO = new TeacherAndscientificResearchProjectDAO();

	public String getForedate_GTSRP() {
		return foredate_GTSRP;
	}

	public void setForedate_GTSRP(String foredate_GTSRP) {
		this.foredate_GTSRP = foredate_GTSRP;
		session.put("foredate_GTSRP", foredate_GTSRP);
	}

	public String getAfterdate_GTSRP() {
		return afterdate_GTSRP;
	}

	public void setAfterdate_GTSRP(String afterdate_GTSRP) {
		this.afterdate_GTSRP = afterdate_GTSRP;
		session.put("afterdate_GTSRP", afterdate_GTSRP);
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
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

	public int getPageSize_GTSRP() {
		return pageSize_GTSRP;
	}

	public void setPageSize_GTSRP(int pageSize_GTSRP) {
		this.pageSize_GTSRP = pageSize_GTSRP;
		session.put("pageSize_GTSRP", pageSize_GTSRP);
	}

	public ResearchLab getResearchLab_GTSRP() {
		return researchLab_GTSRP;
	}

	public void setResearchLab_GTSRP(ResearchLab researchLab_GTSRP) {
		this.researchLab_GTSRP = researchLab_GTSRP;
		session.put("researchLab_GTSRP", researchLab_GTSRP);
	}

	public TeacherAndscientificResearchProject getTARProject() {
		return TARProject;
	}

	public void setTARProject(TeacherAndscientificResearchProject tARProject) {
		TARProject = tARProject;
	}

	public String getIsDivided() {
		return isDivided;
	}

	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}

	public String getCheckout_GTSRP() {
		return checkout_GTSRP;
	}

	public void setCheckout_GTSRP(String checkout_GTSRP) {
		this.checkout_GTSRP = checkout_GTSRP;
		session.put("checkout_GTSRP", checkout_GTSRP);
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
}
