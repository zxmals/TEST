package com.nuaa.ec.science.rt_audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherAndscientificResearchProjectDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.opensymphony.xwork2.ActionContext;

public class GTScientificResearchProjectAuditAction_person implements
		RequestAware {
	/**
	 * 取出团队成员
	 */
	public String getAllRecord_person() {
		Transaction tx = null;
		boolean isDivided = false;
		try {
			if (this.isDivided != null && this.isDivided.trim().equals("true")) {
				isDivided = true;
			}
			String pageSize_s = null;
			if (session.get("pageSize_GTTASRP") != null) {
				pageSize_s = (String) (session.get("pageSize_GTTASRP") + "");
				if (pageSize_s != null && pageSize_s.trim().length() != 0) {
					pageSize_GTTASRP = Integer.parseInt(pageSize_s);
				}
			}
			this.request
					.put("TeacherAndSRP_PersonList",
							this.TASRProjectDAO.getAllMembersOfProject(
									pageIndex, pageSize_GTTASRP,
									(String) session.get("foredate_GTTASRP"),
									(String) session.get("afterdate_GTTASRP"),
									((Teacher) session.get("teacher"))
											.getResearchLab(), (String) session
											.get("checkout_GTTASRP"), isDivided));
			tx=this.TASRProjectDAO.getSession().beginTransaction();
			tx.commit();
		} catch (Exception ex) {
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}

	/**
	 * 审核团队中的成员
	 */
	public void doCheckOut_person() {
		/**
		 * 这个地方的代码与管理员审核是一样的，
		 * 只是checkOut的状态有所区别而已
		 */
//		TeacherAndScientificResearchProjectAuditAction TAScienResearchProAuditAction=new TeacherAndScientificResearchProjectAuditAction();
//		TAScienResearchProAuditAction.setCheckout(this.checkOutIDs);
//		TAScienResearchProAuditAction.setCheckOutIDsNot(this.checkOutIDsNot);
		/**
		 * 这个地方的代码与管理员审核是一样的，
		 * 只是checkOut的状态有所区别而已
		 */
		List<TeacherAndscientificResearchProject> checkoutList=new ArrayList<TeacherAndscientificResearchProject>();
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		TeacherAndscientificResearchProject teacherAndScientificResearchProject = null;
		for (int i = 0; i < ids.length; i++) {
			if(ids[i]!=null && ids[i].length()!=0 ){
				teacherAndScientificResearchProject = this.TASRProjectDAO.findById(Integer.parseInt(ids[i]));
				// 修改checkout 标志
				if(teacherAndScientificResearchProject!=null){
					teacherAndScientificResearchProject.setCheckOut("1");
					checkoutList.add(teacherAndScientificResearchProject);
				}
			}
		}
		for(int i=0;i<idsNot.length;i++){
			if(idsNot[i]!=null && idsNot[i].length()!=0){
				teacherAndScientificResearchProject=this.TASRProjectDAO.findById(Integer.parseInt(idsNot[i]));
				if(teacherAndScientificResearchProject!=null){
					teacherAndScientificResearchProject.setCheckOut("2");
					checkoutList.add(teacherAndScientificResearchProject);
				}
			}
		}
		// 将待审核的项目传向后台
		try {
			if (TASRProjectDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext.getResponse().getWriter().write("succ");
			} else {
				ServletActionContext.getResponse().getWriter().write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String foredate_GTTASRP;
	private String afterdate_GTTASRP;
	private Map<String, Object> request = null;
	private int pageIndex = 1;
	private int pageSize_GTTASRP = 1;
	private String checkout_GTTASRP;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private String isDivided;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private TeacherAndscientificResearchProjectDAO TASRProjectDAO = new TeacherAndscientificResearchProjectDAO();

	public String getForedate_GTTASRP() {
		return foredate_GTTASRP;
	}

	public void setForedate_GTTASRP(String foredate_GTTASRP) {
		this.foredate_GTTASRP = foredate_GTTASRP;
		session.put("foredate_GTTASRP", foredate_GTTASRP);
	}

	public String getAfterdate_GTTASRP() {
		return afterdate_GTTASRP;
	}

	public void setAfterdate_GTTASRP(String afterdate_GTTASRP) {
		this.afterdate_GTTASRP = afterdate_GTTASRP;
		session.put("afterdate_GTTASRP", afterdate_GTTASRP);
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

	public int getPageSize_GTTASRP() {
		return pageSize_GTTASRP;
	}

	public void setPageSize_GTTASRP(int pageSize_GTTASRP) {
		this.pageSize_GTTASRP = pageSize_GTTASRP;
		session.put("pageSize_GTTASRP", pageSize_GTTASRP);
	}

	public String getCheckout_GTTASRP() {
		return checkout_GTTASRP;
	}

	public void setCheckout_GTTASRP(String checkout_GTTASRP) {
		this.checkout_GTTASRP = checkout_GTTASRP;
		session.put("checkout_GTTASRP", checkout_GTTASRP);
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

	public String getIsDivided() {
		return isDivided;
	}

	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}
}
