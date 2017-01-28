package com.nuaa.ec.va.vaPerformanceSetAction;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.utils.EntityUtil;

public class NewactAPPlySetAction implements RequestAware{
	public void deleteRecord(){
		try {
			vacollectiveAct = vacollectiveActDAO.findById(id);
			vacollectiveAct.setSpareTire("0");
			vacollectiveActDAO.merge(vacollectiveAct);
			vacollectiveActDAO.getSession().beginTransaction().commit();
			this.response.getWriter().write("succ");
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vacollectiveActDAO.getSession().beginTransaction().rollback();
			this.setOperstatus(-1);
		}
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
		request.put("List", vacollectiveActDAO.findAll((pagenum-1)*pagesize,pagesize,EntityUtil.generateQueryCondition(foredate_CT, afterdate_CT, "VAP.actDate")));
		int li = vacollectiveActDAO.getRows(EntityUtil.generateQueryCondition(foredate_CT, afterdate_CT, "VAP.actDate"));
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
	
	public void updateRecord(){
		try {
			System.out.println(actapplyname);
			System.out.println(actapplytype);
			System.out.println(actapplyscore);
			vacollectiveAct = vacollectiveActDAO.findById(actapplynumber);
			vacollectiveAct.setActName(actapplyname);
			vacollectiveAct.setActType(actapplytype);
			vacollectiveAct.setScore(Double.parseDouble(actapplyscore));
			vacollectiveAct.setSpareTire("1");
			vacollectiveAct.setAspareTire("1");
			vacollectiveActDAO.merge(vacollectiveAct);
			vacollectiveActDAO.getSession().beginTransaction().commit();
			this.setOperstatus(1);
			this.response.getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vacollectiveActDAO.getSession().beginTransaction().rollback();
			this.setOperstatus(-1);
			try {
				this.response.getWriter().write("error");
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}finally{
			try {
				getWorkall();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private String id;
	private String actapplytype = "1";
	private String actapplyscore = null;
//	Double score = 1.0;
	private String actapplyname = null;
	private String actInstuction = null;
	private String actapplynumber;
	private int pageIndex = 1;
	private int pageSize_CT;
	private ResearchLab researchLab_CT;
	private int operstatus;
	private Department department_CT;
	private String foredate_CT;
	private String afterdate_CT;
	private Map<String, Object> session ;
	private Map<String, Object> request;
	private String checkOutStatus_CT;
	private String checkOutIDs;
	private String checkOutIDsNot;
	private VacollectiveAct vacollectiveAct = new VacollectiveAct();
	private VacollectiveActDAO vacollectiveActDAO = new VacollectiveActDAO();
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

	public String getActInstuction() {
		return actInstuction;
	}

	public void setActInstuction(String actInstuction) {
		this.actInstuction = actInstuction;
	}

	public String getActapplytype() {
		return actapplytype;
	}

	public void setActapplytype(String actapplytype) {
		this.actapplytype = actapplytype;
	}

	public String getActapplyscore() {
		return actapplyscore;
	}

	public void setActapplyscore(String actapplyscore) {
		this.actapplyscore = actapplyscore;
	}

	public String getActapplyname() {
		return actapplyname;
	}

	public void setActapplyname(String actapplyname) {
		this.actapplyname = actapplyname;
	}

	public String getActapplynumber() {
		return actapplynumber;
	}

	public void setActapplynumber(String actapplynumber) {
		this.actapplynumber = actapplynumber;
	}

	public VacollectiveAct getVacollectiveAct() {
		return vacollectiveAct;
	}
	public void setVacollectiveAct(VacollectiveAct vacollectiveAct) {
		this.vacollectiveAct = vacollectiveAct;
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
	public ResearchLab getResearchLab_CT() {
		return researchLab_CT;
	}
	public void setResearchLab_CT(ResearchLab researchLab_CT) {
		this.researchLab_CT = researchLab_CT;
	}
	public int getOperstatus() {
		return operstatus;
	}
	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}
	public Department getDepartment_CT() {
		return department_CT;
	}
	public void setDepartment_CT(Department department_CT) {
		this.department_CT = department_CT;
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
	public VacollectiveActDAO getVacollectiveActDAO() {
		return vacollectiveActDAO;
	}
	public void setVacollectiveActDAO(VacollectiveActDAO vacollectiveActDAO) {
		this.vacollectiveActDAO = vacollectiveActDAO;
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
}
