package com.nuaa.ec.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.TeachingSummaryDao;
import com.nuaa.ec.model.Department;
import com.nuaa.ec.utils.E_SummaryOfTeaching;

public class TeachingDataSummaryAction implements RequestAware, SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foreterm;
	private String afterterm;
	private Department depart;
	
	private TeachingSummaryDao teachsumdao = new TeachingSummaryDao();
	//default method
	public String execute(){
		return "success";
	}
	// 获取系汇总数据
	public String getDepartSummaryData() throws Exception{
		try {
			if(null!=depart)
				if("alldepart".equals(depart.getDepartmentId().trim())){
					session.put("departsumdata", teachsumdao.getAllSum(foreterm,afterterm));
				}else{
					session.put("departsumdata", teachsumdao.getSingleSum(foreterm, afterterm, depart));
				}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
		}
		return "success";
	}
	//
	public String getTeacherSummaryData(){
		request.put("teachersum", teachsumdao.getTeacherSUM(foreterm, afterterm, ServletActionContext.getRequest().getParameter("teacherId")));
		return "success";
	}
	
	// 导出数据 
	public void exportData(){
		ByteArrayOutputStream baos =  teachsumdao.genarateTeachingSUMExpot(foreterm, afterterm, depart.getDepartmentId(), (List<E_SummaryOfTeaching>) session.get("departsumdata"));
			try {
				if(baos!=null){
					HttpServletResponse resp = ServletActionContext.getResponse();
					OutputStream out = resp.getOutputStream();
					resp.setHeader(
							"Content-Disposition",
							"attachment;filename="
									+ URLEncoder.encode(
											"教学汇总数据",
											"UTF-8") + ".xls");
					byte[] bt = baos.toByteArray();
					out.write(bt, 0, bt.length);
					out.flush();
					out.close();
				}else{
					ServletActionContext.getResponse()
					.setCharacterEncoding("utf-8");
					ServletActionContext.getResponse().getWriter()
					.write(" Opps..... 请返回到上一页 ");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	//Getter & Setter
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

	public String getForeterm() {
		return foreterm;
	}

	public void setForeterm(String foreterm) {
		this.foreterm = foreterm;
	}

	public String getAfterterm() {
		return afterterm;
	}

	public void setAfterterm(String afterterm) {
		this.afterterm = afterterm;
	}

	public Department getDepart() {
		return depart;
	}

	public void setDepart(Department depart) {
		this.depart = depart;
	}

}
