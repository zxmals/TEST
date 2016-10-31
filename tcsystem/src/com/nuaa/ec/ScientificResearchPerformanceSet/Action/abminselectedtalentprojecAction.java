package com.nuaa.ec.ScientificResearchPerformanceSet.Action;

import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TalentProjectDAO;
import com.nuaa.ec.dao.TeacherAndselectedTalentProjectDAO;
import com.nuaa.ec.model.SelectedTalentProjectScore;
import com.nuaa.ec.model.TalentProject;
import com.nuaa.ec.model.TeacherAndselectedTalentProject;
import com.nuaa.ec.utils.EntityUtil;

public class abminselectedtalentprojecAction implements RequestAware, SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foredate;
	private String afterdate;
	private Integer operstatus;
	private TalentProject talentp;
	private SelectedTalentProjectScore talentpscore;
	private TeacherAndselectedTalentProject teacherandtalentp;

	private TalentProjectDAO talentpdao = new TalentProjectDAO();
	private TeacherAndselectedTalentProjectDAO teacherandtalentpdao = new TeacherAndselectedTalentProjectDAO();
	// default method
	public String execute() {
		return "success";
	}

	// 人才工程设置
	public String gainAlltalentprojec() {
		int pagenum = 1;
		int limitrow = 5;
		String limit = (String)ServletActionContext.getRequest().getParameter("limit");
		String pagenumber = (String)ServletActionContext.getRequest().getParameter("pagenum");
		if(pagenumber!=null){
			pagenum = !"".equals(pagenumber.trim())?Integer.parseInt(pagenumber):1;
		}
		if(limit!=null){
			limitrow = !"".equals(limit.trim())?Integer.parseInt(limit):5;
		}
		request.put("talentprojects", talentpdao.findPageing((pagenum-1)*limitrow,limitrow,EntityUtil.generateQueryCondition(foredate, afterdate, "selectedDate")));
		int li = talentpdao.getRows(EntityUtil.generateQueryCondition(foredate, afterdate, "selectedDate"));
		int sumpage = 1;
		if(li%limitrow==0){
			sumpage = li/limitrow;
		}else{
			sumpage = li/limitrow+1;
		}
		request.put("sumrow",li);
		request.put("sumpage",sumpage);
		if(pagenum<sumpage){
			request.put("nextpage", 1+pagenum);
		}else{
			request.put("nextpage",pagenum);
		}
		if(pagenum>1){
			request.put("prepage", pagenum-1);
		}else{
			request.put("prepage",1);
		}
		request.put("pagenum", pagenum);
		return "success";
	}

	public void updateTalentPeoject()throws Exception{
		Transaction tx = null;
		try {
			talentp.setSpareTire("1");
			talentpdao.merge(talentp);
			tx = talentpdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	public void deleteTalentProjec()throws Exception{
		Transaction tx = null;
		try {
			this.setTalentp(talentpdao.findById(talentp.getTalentProjectId()));
			talentp.setSpareTire("0");
			talentpdao.merge(talentp);
			teacherandtalentpdao.deleteBylogic(talentp);
			tx = talentpdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	public void getMember()throws Exception{
		try {
			JSONArray jsay = JSONArray.fromObject(teacherandtalentpdao.findMember(talentp));
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().write(jsay.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public void deleteMember()throws Exception{
		Transaction tx = null;
		try {
			teacherandtalentpdao.quitProjec(teacherandtalentp.getTeacher(), talentp);
			tx = teacherandtalentpdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	// Getter & Setter
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

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

	public TalentProject getTalentp() {
		return talentp;
	}

	public void setTalentp(TalentProject talentp) {
		this.talentp = talentp;
	}

	public SelectedTalentProjectScore getTalentpscore() {
		return talentpscore;
	}

	public void setTalentpscore(SelectedTalentProjectScore talentpscore) {
		this.talentpscore = talentpscore;
	}

	public TeacherAndselectedTalentProject getTeacherandtalentp() {
		return teacherandtalentp;
	}

	public void setTeacherandtalentp(
			TeacherAndselectedTalentProject teacherandtalentp) {
		this.teacherandtalentp = teacherandtalentp;
	}

}
