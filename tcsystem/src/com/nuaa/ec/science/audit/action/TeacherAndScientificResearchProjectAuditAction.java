package com.nuaa.ec.science.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchProjectDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.opensymphony.xwork2.ActionContext;

public class TeacherAndScientificResearchProjectAuditAction implements RequestAware {
	public void doCheckOut() {
		List<TeacherAndscientificResearchProject> checkoutList=new ArrayList<TeacherAndscientificResearchProject>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TeacherAndscientificResearchProject teacherAndScientificResearchProject = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				teacherAndScientificResearchProject = this.TARProjectDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(teacherAndScientificResearchProject!=null){
					teacherAndScientificResearchProject.setCheckOut("1");
					checkoutList.add(teacherAndScientificResearchProject);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				teacherAndScientificResearchProject=this.TARProjectDAO.findById(Integer.parseInt(idsNot[i]));
				if(teacherAndScientificResearchProject!=null){
					teacherAndScientificResearchProject.setCheckOut("2");
					checkoutList.add(teacherAndScientificResearchProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TARProjectDAO.updateCheckoutStatus(checkoutList)) {
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
		// storageDate();
		Transaction tx = TARProjectDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab") == null) {
			session.put("selectedResearchLab", new ResearchLab());
		}
		if ((Integer) session.get("pageSize") == null) {
			session.put("pageSize", 1);
		}
		try {
			this.request.put("TA_SRProjectList", this.TARProjectDAO
					.getTASRProjectListsWithCondition(pageIndex,
							(Integer) session.get("pageSize"),
							(String) session.get("foredate"),
							(String) session.get("afterdate"),
							(ResearchLab) session.get("selectedResearchLab"),
							(String) session.get("checkoutStatus")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
//		getResearchLabList();
		return "success";
	}

	public String getSRPToBeAudited() {
		Transaction tx = TARProjectDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab") == null) {
			session.put("selectedResearchLab", new ResearchLab());
		}
		if ((Integer) session.get("pageSize") == null) {
			session.put("pageSize", 1);
		}
		try {
			this.request.put("TA_SRProjectList", TARProjectDAO.findAll(
					pageIndex, (Integer) session.get("pageSize"),
					(String) session.get("foredate"),
					(String) session.get("afterdate"),
					(ResearchLab) session.get("selectedResearchLab"),
					(String) session.get("checkoutStatus")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
//		getResearchLabList();
		return "success";
	}

	public void getResearchLabList() {
		Transaction tx = this.researchDAO.getSession().beginTransaction();
		if (researchLab == null) {
			researchLab = new ResearchLab();
		}
		session.put("selectResearchLab", researchLab);
		try {
			this.request.put("researchLabList", researchDAO.findAll());
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
	}

	private String foredate;
	private String afterdate;
	private Integer operstatus;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize = 1;
	private ResearchLab researchLab;
	private String checkout = "0";
	private ResearchLabDAO researchDAO = new ResearchLabDAO();
	private String checkOutIDs;
	private String checkOutIDsNot;
	private TeacherAndscientificResearchProject TARProject = new TeacherAndscientificResearchProject();
	private TeacherAndscientificResearchProjectDAO TARProjectDAO = new TeacherAndscientificResearchProjectDAO();

	public int getPageIndex() {
		// String s=(pageIndex+"").trim();
		// System.out.println("length: ***"+s.length());
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	private int pageCountInReq = 0;

	public int getPageCountInReq() {
		return pageCountInReq;
	}

	public void setPageCount(int pageCount) {
		this.pageCountInReq = pageCount;
	}

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
		session.put("checkoutStatus", checkout);
		this.getTARProject().setCheckOut(checkout);
	}

	private List<ResearchLab> researchList = null;

	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	// private String researchLabId=null;
	public Map<String, Object> getRequest() {
		return request;
	}

	public ResearchLab getResearchLab() {
		return researchLab;
	}

	public void setResearchLab(ResearchLab researchLab) {
		session.put("selectedResearchLab", researchLab);
		this.researchLab = researchLab;
	}

	public List<ResearchLab> getResearchList() {
		return researchList;
	}

	public void setResearchList(List<ResearchLab> researchList) {
		this.researchList = researchList;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		session.put("pageSize", pageSize);
		this.pageSize = pageSize;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

	public TeacherAndscientificResearchProject getTARProject() {
		return TARProject;
	}

	public void setTARProject(TeacherAndscientificResearchProject tARProject) {
		TARProject = tARProject;
	}

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		session.put("foredate", foredate);
		this.foredate = foredate;
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
		session.put("afterdate", afterdate);
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String execute() throws Exception {
		return "success";
	}

	public String getCheckOutIDsNot() {
		return checkOutIDsNot;
	}

	public void setCheckOutIDsNot(String checkOutIDsNot) {
		this.checkOutIDsNot = checkOutIDsNot;
	}
}
