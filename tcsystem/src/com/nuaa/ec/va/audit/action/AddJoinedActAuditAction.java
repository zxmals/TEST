package com.nuaa.ec.va.audit.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;













import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.VacollectiveActivitiesPublishDAO;
import com.nuaa.ec.dao.VateacherAndCollectiveActDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VateacherAndCollectiveAct;
import com.nuaa.ec.model.VateacherAndCollectiveActId;
import com.opensymphony.xwork2.ActionContext;

public class AddJoinedActAuditAction implements RequestAware ,SessionAware{
	public void doCheckOutTask(){
		String[] ids = this.checkOutIDs.split(",");
		String[] idsNot=this.checkOutIDsNot.split(",");
		List<VateacherAndCollectiveAct> checkoutList = new ArrayList<VateacherAndCollectiveAct>();
		VateacherAndCollectiveAct vateacherAndCollectiveAct = null;
		for (int i = 0; i < ids.length; i++) {
			if (ids[i]!=null && ids[i].length()!=0) {
				VateacherAndCollectiveActId vateacherAndCollectiveActId = new VateacherAndCollectiveActId(
						new VacollectiveActivitiesPublishDAO().findById(ids[i]),
						new TeacherDAO().findById(new VacollectiveActivitiesPublishDAO().findById(ids[i]).getTeacherId())
						);
				
				vateacherAndCollectiveAct = this.vateacherAndCollectiveActDAO.findById(vateacherAndCollectiveActId);
				// 修改checkout 标志
				if (vateacherAndCollectiveAct!=null) {
					vateacherAndCollectiveAct.setAspareTire("1");
					checkoutList.add(vateacherAndCollectiveAct);
				}
			}
		}
		for (int i = 0; i < idsNot.length; i++) {
			if (idsNot[i]!=null && idsNot[i].length()!=0 ) {
				
				VateacherAndCollectiveActId vateacherAndCollectiveActId = new VateacherAndCollectiveActId(
						new VacollectiveActivitiesPublishDAO().findById(idsNot[i]),
						new TeacherDAO().findById(new VacollectiveActivitiesPublishDAO().findById(idsNot[i]).getTeacherId())
						);
				
				vateacherAndCollectiveAct=this.vateacherAndCollectiveActDAO.findById(vateacherAndCollectiveActId);
				if(vateacherAndCollectiveAct!=null){
					vateacherAndCollectiveAct.setAspareTire("2");
					checkoutList.add(vateacherAndCollectiveAct);
			}
		}
		
	}
		// 将待审核的项目传向后台
	try {
		if (vateacherAndCollectiveActDAO.updateASparetire(checkoutList)) {
			ServletActionContext.getResponse().getWriter().write("succ");
		}else {
			ServletActionContext.getResponse().getWriter().write("error");
		}
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	}
	
	public String getAddJoinedActListAfterDivide(){
		if ((Department) session.get("department_CT") == null) {
			session.put("department_CT", new Department());
		}
		if ((Integer) session.get("pageSize_CT") == null) {
			session.put("pageSize_CT", 1);
		}
		
		try {
			this.request.put("VaAddJoinedActList", this.vateacherAndCollectiveActDAO
					.getVaAddJoinedActAfterDivide(pageIndex,
							(Integer) session.get("pageSize_CT"),
							(String)session.get("foredate_CT"),
							(String)session.get("afterdate_CT"),
							(Department) session.get("department_CT"),
							(String)session.get("checkOutStatus_CT")
							));
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setOperstatus(-1);
		}
		
		
		return "success";
	}
	
	public String getAddJoinedActList(){
		//判断是否分页
		if ((Department) session.get("department_CT") == null) {
			session.put("department_CT", new Department());
		}
		if ((Integer) session.get("pageSize_CT") == null) {
			session.put("pageSize_CT", 1);
		}
		try {
			this.request.put("VaAddJoinedActList", this.vateacherAndCollectiveActDAO
					.getVaAddJoinedAct(pageIndex,
							(Integer) session.get("pageSize_CT"),
							(String)session.get("foredate_CT"),
							(String)session.get("afterdate_CT"),
							(Department) session.get("department_CT"),
							(String)session.get("checkOutStatus_CT")
							));
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setOperstatus(-1);
		}
		return "success";
	}
	
	
	private int pageIndex = 1;
	private int pageSize_CT;
	private ResearchLab researchLab_CT;
	private Department department_CT;
	private int operstatus;
	private Map<String, Object> session ;
	private Map<String, Object> request;
	private String checkOutStatus_CT;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO = new VateacherAndCollectiveActDAO();
	private String foredate_CT;
	private String afterdate_CT;
	
	
	
	public VateacherAndCollectiveActDAO getVateacherAndCollectiveActDAO() {
		return vateacherAndCollectiveActDAO;
	}

	public void setVateacherAndCollectiveActDAO(
			VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO) {
		this.vateacherAndCollectiveActDAO = vateacherAndCollectiveActDAO;
	}

	public ResearchLab getResearchLab_CT() {
		return researchLab_CT;
	}

	public void setResearchLab_CT(ResearchLab researchLab_CT) {
		this.researchLab_CT = researchLab_CT;
		session.put("researchLab_CT", researchLab_CT);
	}
	
	public void setDepartment_CT(Department department_CT) {
		this.department_CT = department_CT;
		session.put("department_CT", department_CT);
	}
	public Department getDepartment_CT() {
		return department_CT;
	}
	
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_CT() {
		return pageSize_CT;
	}
	public void setPageSize_CT(int pageSize_CT) {
		this.pageSize_CT = pageSize_CT;
		session.put("pageSize_CT", pageSize_CT);
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
	public Map<String, Object> getRequest() {
		return request;
	}
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	public String getCheckOutStatus_CT() {
		return checkOutStatus_CT;
	}
	public void setCheckOutStatus_CT(String checkOutStatus_CT) {
		this.checkOutStatus_CT = checkOutStatus_CT;
		session.put("checkOutStatus_CT", checkOutStatus_CT);
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

	public String getForedate_CT() {
		return foredate_CT;
	}

	public void setForedate_CT(String foredate_CT) {
		this.foredate_CT = foredate_CT;
		session.put("foredate_CT", foredate_CT);
	}

	public String getAfterdate_CT() {
		return afterdate_CT;
	}

	public void setAfterdate_CT(String afterdate_CT) {
		this.afterdate_CT = afterdate_CT;
		session.put("afterdate_CT", afterdate_CT);
	}
	
}
