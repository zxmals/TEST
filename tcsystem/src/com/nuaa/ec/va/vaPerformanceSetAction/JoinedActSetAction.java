package com.nuaa.ec.va.vaPerformanceSetAction;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.VacollectiveActivitiesPublishDAO;
import com.nuaa.ec.dao.VateacherAndCollectiveActDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VateacherAndCollectiveAct;
import com.nuaa.ec.model.VateacherAndCollectiveActId;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.stringstore;
import com.opensymphony.xwork2.ActionContext;

public class JoinedActSetAction implements RequestAware{

	public void deleteRecord(){
		try {
//			System.out.println(actPubId);
//			System.out.println(ServletActionContext.getRequest().getParameter("actPubId"));
//			System.out.println(teacherId);
			VateacherAndCollectiveActId vateacherAndCollectiveActId = new VateacherAndCollectiveActId(
					new VacollectiveActivitiesPublishDAO().findById(actPubId),
					new TeacherDAO().findById(teacherId)
					);
			vateacherAndCollectiveAct = vateacherAndCollectiveActDAO.findById(vateacherAndCollectiveActId);
			vateacherAndCollectiveAct.setSpareTire("0");
			vateacherAndCollectiveActDAO.merge(vateacherAndCollectiveAct);
			vateacherAndCollectiveActDAO.getSession().beginTransaction().commit();
			this.response.getWriter().write("succ");
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vateacherAndCollectiveActDAO.getSession().beginTransaction().rollback();
			this.setOperstatus(-1);
			try {
				this.response.getWriter().write("error");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	
	public String getAllRecord(){
		Transaction tx = null;
		boolean isDivided = false;
		
		if (this.isDivided != null && this.isDivided.length()!=0 && this.isDivided.equals("true")) {
			isDivided = true;
		}
		try {
			if (session.get("pageSize_CT") != null) {
				pageSize_CT = (Integer) session.get("pageSize_CT");
			}else {
				pageSize_CT = 5;
			}
			this.request.put("List", vateacherAndCollectiveActDAO.findAllWithDivided(
					pageIndex,
					pageSize_CT,
					(String)session.get("foredate_CT"),
					(String)session.get("afterdate_CT"),
					isDivided
					));
			tx = this.vateacherAndCollectiveActDAO.getSession().beginTransaction();
			tx.commit();
//			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
//			this.setOperstatus(-1);
		}
		return "success";
	}
	
	public String getWorkall() throws Exception{
		int pagenum = 1;
		int pagesize = 5;
		String limit = (String)ServletActionContext.getRequest().getParameter("limit");
		String pagenumber = ServletActionContext.getRequest().getParameter("pagenum");
		if (pagenumber !=null) {
			pagenum = !"".equals(pagenumber.trim())?Integer.parseInt(pagenumber):1;
		}
		if (limit!=null) {
			pagesize = !"".equals(limit.trim())?Integer.parseInt(limit):5;
		}
		request.put("List", vateacherAndCollectiveActDAO.findAll((pagenum-1)*pagesize,pagesize,EntityUtil.generateQueryCondition(foredate_CT, afterdate_CT, "VACA.id.vacollectiveActivitiesPublish.actDate")));
		int li = vateacherAndCollectiveActDAO.getRows(EntityUtil.generateQueryCondition(foredate_CT, afterdate_CT, "VACA.id.vacollectiveActivitiesPublish.actDate"));
		int sumpage = 1 ;
		if (li % pagesize == 0) {
			sumpage = li / pagesize;
		}else {
			sumpage = li / pagesize + 1;
		}
		request.put("sumrow", li);
		request.put("sumpage", sumpage);
		if (pagenum < sumpage) {
			request.put("nextpage", pagenum + 1);
		}else {
			request.put("nextpage", pagenum);
		}
		if (pagenum > 1) {
			request.put("prepage", pagenum - 1);
		}else {
			request.put("prepage", pagenum);
		}
		request.put("pagenum", pagenum);
		return "success";
	}
	
	public String execute() throws Exception{
		return "success";
	}
	
	private String actPubId;
	private int pageIndex = 1;
	private int pageSize_CT;
	private String teacherId;
	private ResearchLab researchLab_CT;
	private Department department_CT;
	private int operstatus;
	private Map<String, Object> session = ActionContext.getContext().getSession();
	private Map<String, Object> request;
	private String checkOutStatus_CT;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO = new VateacherAndCollectiveActDAO();
	private String foredate_CT;
	private String afterdate_CT;
	private String isDivided;
	private VateacherAndCollectiveAct vateacherAndCollectiveAct;
	private HttpServletResponse response = ServletActionContext.getResponse();
	public int getPageIndex() {
		return pageIndex;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getActPubId() {
		return actPubId;
	}

	public void setActPubId(String actPubId) {
		this.actPubId = actPubId;
	}

	public String getIsDivided() {
		return isDivided;
	}

	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}

	public int getPageSize_CT() {
		return pageSize_CT;
	}

	public void setPageSize_CT(int pageSize_CT) {
		this.pageSize_CT = pageSize_CT;
	}

	public ResearchLab getResearchLab_CT() {
		return researchLab_CT;
	}

	public void setResearchLab_CT(ResearchLab researchLab_CT) {
		this.researchLab_CT = researchLab_CT;
	}

	public Department getDepartment_CT() {
		return department_CT;
	}

	public void setDepartment_CT(Department department_CT) {
		this.department_CT = department_CT;
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

	public String getCheckOutStatus_CT() {
		return checkOutStatus_CT;
	}

	public void setCheckOutStatus_CT(String checkOutStatus_CT) {
		this.checkOutStatus_CT = checkOutStatus_CT;
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
	}

	public String getAfterdate_CT() {
		return afterdate_CT;
	}

	public void setAfterdate_CT(String afterdate_CT) {
		this.afterdate_CT = afterdate_CT;
	}

	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public Map<String, Object> getRequest() {
		return request;
	}
}
