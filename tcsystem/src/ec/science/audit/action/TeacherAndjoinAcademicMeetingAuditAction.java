package ec.science.audit.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherAndjoinAcademicMeetingDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndjoinAcademicMeeting;
import com.opensymphony.xwork2.ActionContext;

public class TeacherAndjoinAcademicMeetingAuditAction implements RequestAware {
	public void doCheckOut() {
		String[] ids = this.checkOutIDs.split(",");
		List<TeacherAndjoinAcademicMeeting> checkoutList = new ArrayList<TeacherAndjoinAcademicMeeting>();
		TeacherAndjoinAcademicMeeting TAAMeeting = null;
		for (int i = 0; i < ids.length; i++) {
			TAAMeeting = this.TAAMeetingDAO.findById(Integer.parseInt(ids[i]));
			// 修改checkout 标志
			TAAMeeting.setCheckOut("1");
			checkoutList.add(TAAMeeting);
		}
		// 将待审核的项目传向后台
		try {
			if (TAAMeetingDAO.updateCheckoutStatus(checkoutList)) {
				// 前端显示乱码解决
				ServletActionContext
						.getResponse()
						.getWriter()
						.write("succ");
			} else {
				ServletActionContext
						.getResponse()
						.getWriter()
						.write("error");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String getTAAmeetingListAfterDivide(){
		Transaction tx=this.TAAMeetingDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab_TAAM") == null) {
			session.put("selectedResearchLab_TAAM", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAAM") == null) {
			session.put("pageSize_TAAM", 1);
		}
		try{
			this.request.put("TAAMeetingList", this.TAAMeetingDAO.getTAMMeetingAfterDivided(pageIndex, (Integer) session
					.get("pageSize_TAAM"), (String) session
					.get("foredate_TAAM"), (String) session
					.get("afterdate_TAAM"), (ResearchLab) session
					.get("selectedResearchLab_TAAM"), (String) session
					.get("checkOutStatus_TAAM")));
			tx.commit();
			this.setOperstatus(1);
		}catch(Exception ex){ 
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}
	public String getTAAMeetingList() {
		Transaction tx = this.TAAMeetingDAO.getSession().beginTransaction();
		if ((ResearchLab) session.get("selectedResearchLab_TAAM") == null) {
			session.put("selectedResearchLab_TAAM", new ResearchLab());
		}
		if ((Integer) session.get("pageSize_TAAM") == null) {
			session.put("pageSize_TAAM", 1);
		}
		try {
			this.request.put("TAAMeetingList", this.TAAMeetingDAO
					.findAllWithCondition(pageIndex, (Integer) session
							.get("pageSize_TAAM"), (String) session
							.get("foredate_TAAM"), (String) session
							.get("afterdate_TAAM"), (ResearchLab) session
							.get("selectedResearchLab_TAAM"), (String) session
							.get("checkOutStatus_TAAM")));
			tx.commit();
			this.setOperstatus(1);
		} catch (Exception ex) {
			this.setOperstatus(-1);
			ex.printStackTrace();
			tx.rollback();
		}
		return "success";
	}

	/*
	 * public void getResearchLabList() { Transaction tx = null; try {
	 * this.request.put("researchLabList_TAAM", StoreData.getResearchLabList());
	 * tx = this.researchLabDAO.getSession().beginTransaction(); tx.commit();
	 * this.setOperstatus(1); } catch (Exception ex) { tx.rollback();
	 * this.setOperstatus(-1); ex.printStackTrace(); } }
	 */

	public String execute() throws Exception {
		System.out.println("进入action...");
		return "success";
	}

	private int pageIndex = 1;
	private int pageSize_TAAM;
	private String foredate_TAAM;
	private String afterdate_TAAM;
	private ResearchLab researchLab_TAAM;
	private String checkOutStatus_TAAM;
	private String checkOutIDs;
	private TeacherAndjoinAcademicMeetingDAO TAAMeetingDAO = new TeacherAndjoinAcademicMeetingDAO();
	private Map<String, Object> request;
	private int operstatus;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
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

	public int getPageSize_TAAM() {
		return pageSize_TAAM;
	}

	public void setPageSize_TAAM(int pageSize_TAAM) {
		this.pageSize_TAAM = pageSize_TAAM;
		session.put("pageSize_TAAM", pageSize_TAAM);
	}

	public String getForedate_TAAM() {
		return foredate_TAAM;
	}

	public void setForedate_TAAM(String foredate_TAAM) {
		this.foredate_TAAM = foredate_TAAM;
		session.put("foredate_TAAM", foredate_TAAM);
	}

	public String getAfterdate_TAAM() {
		return afterdate_TAAM;
	}

	public void setAfterdate_TAAM(String afterdate_TAAM) {
		this.afterdate_TAAM = afterdate_TAAM;
		session.put("afterdate_TAAM", afterdate_TAAM);
	}

	public ResearchLab getResearchLab_TAAM() {
		return researchLab_TAAM;
	}

	public void setResearchLab_TAAM(ResearchLab researchLab_TAAM) {
		this.researchLab_TAAM = researchLab_TAAM;
		session.put("selectedResearchLab_TAAM", researchLab_TAAM);
	}

	public String getCheckOutStatus_TAAM() {
		return checkOutStatus_TAAM;
	}

	public void setCheckOutStatus_TAAM(String checkOutStatus_TAAM) {
		this.checkOutStatus_TAAM = checkOutStatus_TAAM;
		session.put("checkOutStatus_TAAM", checkOutStatus_TAAM);
	}

	public String getCheckOutIDs() {
		return checkOutIDs;
	}

	public void setCheckOutIDs(String checkOutIDs) {
		this.checkOutIDs = checkOutIDs;
	}
}
