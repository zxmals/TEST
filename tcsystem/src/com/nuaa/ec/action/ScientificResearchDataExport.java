package com.nuaa.ec.action;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherAndperiodicalDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndacademicWork;
import com.nuaa.ec.utils.EntityUtil;

public class ScientificResearchDataExport implements RequestAware, SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foredate;
	private String afterdate;
	private ResearchLab research;
	private String entitys;

	private ResearchLabDAO researchdao = new ResearchLabDAO();
	private TeacherAndperiodicalDAO tapdao = new TeacherAndperiodicalDAO();
	private TeacherAndacademicWork academicdao = new TeacherAndacademicWork();
	//default method
	public String execute(){
		return "success";
	}
	
	public void generateExportData() throws Exception{
		try {
			ByteArrayOutputStream baos = null;
			
			if("PeriodicalPapers".equals(entitys.trim())){
				baos = tapdao.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "pp.year")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}
			
			if(baos!=null){
				HttpServletResponse resp = ServletActionContext.getResponse();
				OutputStream out = resp.getOutputStream();
				resp.setHeader("Content-Disposition", "attachment;filename=ScientificResearch.xls");
				byte[] bt = baos.toByteArray();
				out.write(bt,0,bt.length);
				out.flush();
				out.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw e;
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

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		this.foredate = foredate;
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
	}

	public ResearchLab getResearch() {
		return research;
	}

	public void setResearch(ResearchLab research) {
		this.research = research;
	}

	public String getEntitys() {
		return entitys;
	}

	public void setEntitys(String entitys) {
		this.entitys = entitys;
	}

}
