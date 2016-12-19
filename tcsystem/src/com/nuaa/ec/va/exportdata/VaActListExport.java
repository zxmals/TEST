package com.nuaa.ec.va.exportdata;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.DepartmentDAO;
import com.nuaa.ec.dao.VateacherAndCollectiveActDAO;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.stringstore;

public class VaActListExport implements SessionAware,RequestAware{

	private String afterdate;
	private String foredate;
	private Map<String, Object> session ;
	private Map<String, Object> request;
	private Department department;
	private DepartmentDAO departmentDAO = new DepartmentDAO();
	private VateacherAndCollectiveActDAO vateacherAndCollectiveActDAO = new VateacherAndCollectiveActDAO();
	private String vaacttype;
	public String execute(){
		return "success";
	}
	
	public void generateExportData() throws Exception{
		try {
			ByteArrayOutputStream baos = null;
			baos = vateacherAndCollectiveActDAO.findwithexport(department,EntityUtil.generateQueryCondition(foredate, afterdate, ""),(departmentDAO.findById(department.getDepartmentId())).getDepartmentName(),foredate,afterdate);
			if (baos!=null) {
				HttpServletResponse resp = ServletActionContext.getResponse();
				OutputStream outStream = resp.getOutputStream();
//				System.out.println("1");
				resp.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(foredate + "-" + afterdate + "活动信息", "utf-8")+".xls");
				byte[] bt = baos.toByteArray();
				outStream.write(bt, 0, bt.length);
				outStream.flush();
				outStream.close();
			}else {
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("该时段区间没有数据");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
	}
	
	public void setRequest(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}

	public void setSession(Map<String, Object> arg0) {
		// TODO Auto-generated method stub
		this.session = arg0;
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
	}

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		this.foredate = foredate;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getVaacttype() {
		return vaacttype;
	}

	public void setVaacttype(String vaacttype) {
		this.vaacttype = vaacttype;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	
}
