package com.nuaa.ec.va.vaPerformanceSetAction;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import sun.print.resources.serviceui;

import com.nuaa.ec.dao.VaunJoinRecordDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VaunJoinRecord;
import com.nuaa.ec.utils.EntityUtil;

public class UnjoinedActSetAction implements RequestAware{
	
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
		request.put("List", vaunJoinRecordDAO.findAll((pagenum-1)*pagesize,pagesize,EntityUtil.generateQueryCondition(foredate_CT, afterdate_CT, "VP.actDate")));
		int li = vaunJoinRecordDAO.getRows(EntityUtil.generateQueryCondition(foredate_CT, afterdate_CT, "VP.actDate"));
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
	
	public void deleteRecord(){
		try {
			vaunJoinRecord = vaunJoinRecordDAO.findById(id);
			vaunJoinRecord.setSparetire("0");
			vaunJoinRecordDAO.merge(vaunJoinRecord);
			vaunJoinRecordDAO.getSession().beginTransaction().commit();
			this.response.getWriter().write("succ");
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setOperstatus(-1);
			vaunJoinRecordDAO.getSession().beginTransaction().rollback();
			try {
				this.response.getWriter().write("error");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}
	private String id;
	private VaunJoinRecord vaunJoinRecord;
	private int pageIndex = 1;
	private int pageSize_CT;
	private String foredate_CT;
	private String afterdate_CT;
	private ResearchLab researchLab_CT;
	private Department department_CT;
	private String checkOutStatus_CT;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private int operstatus;
	private Map<String, Object> session;
	private Map<String, Object> request;
	private VaunJoinRecordDAO vaunJoinRecordDAO = new VaunJoinRecordDAO();
	private HttpServletResponse response = ServletActionContext.getResponse();
	public int getPageIndex() {
		return pageIndex;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public VaunJoinRecord getVaunJoinRecord() {
		return vaunJoinRecord;
	}

	public void setVaunJoinRecord(VaunJoinRecord vaunJoinRecord) {
		this.vaunJoinRecord = vaunJoinRecord;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize_CT() {
		return pageSize_CT;
	}
	public void setPageSize_CT(int pageSize_CT) {
		this.pageSize_CT = pageSize_CT;
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
	public VaunJoinRecordDAO getVaunJoinRecordDAO() {
		return vaunJoinRecordDAO;
	}
	public void setVaunJoinRecordDAO(VaunJoinRecordDAO vaunJoinRecordDAO) {
		this.vaunJoinRecordDAO = vaunJoinRecordDAO;
	}
	
	
}
