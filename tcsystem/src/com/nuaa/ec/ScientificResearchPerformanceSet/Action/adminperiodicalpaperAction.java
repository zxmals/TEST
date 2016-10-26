package com.nuaa.ec.ScientificResearchPerformanceSet.Action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.PeriodicalDAO;
import com.nuaa.ec.dao.PeriodicalPapersDAO;
import com.nuaa.ec.dao.PeriodicalPapersScoreDAO;
import com.nuaa.ec.dao.TeacherAndperiodicalDAO;
import com.nuaa.ec.model.Periodical;
import com.nuaa.ec.model.PeriodicalPapers;
import com.nuaa.ec.model.PeriodicalPapersScore;
import com.nuaa.ec.model.TeacherAndperiodical;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class adminperiodicalpaperAction implements RequestAware, SessionAware {
	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foredate;
	private String afterdate;
	private Integer operstatus;
	private PeriodicalPapers periopaper;
	private TeacherAndperiodical tap;
	private PeriodicalPapersScore ppscore;
	private Periodical periodical;

	private PeriodicalPapersDAO periopaperdao = new PeriodicalPapersDAO();
	private PeriodicalDAO periodao = new PeriodicalDAO();
	private TeacherAndperiodicalDAO tapdao = new TeacherAndperiodicalDAO();
	private PeriodicalPapersScoreDAO ppscoredao = new PeriodicalPapersScoreDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	
	//default method
	public String execute(){
		return "success";
	}
	
	public String getPeriodicalPaperINF()throws Exception{
		try {
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
			request.put("periodicalpaper", periopaperdao.findPageing((pagenum-1)*limitrow,limitrow,EntityUtil.generateQueryCondition(foredate, afterdate, "year")));
			request.put("periodical", periodao.findAll());
			int li = periopaperdao.getRows(EntityUtil.generateQueryCondition(foredate, afterdate, "year"));
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
		} catch (Exception e) {
			// TODO: handle exception
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

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

	public PeriodicalPapers getPeriopaper() {
		return periopaper;
	}

	public void setPeriopaper(PeriodicalPapers periopaper) {
		this.periopaper = periopaper;
	}

	public TeacherAndperiodical getTap() {
		return tap;
	}

	public void setTap(TeacherAndperiodical tap) {
		this.tap = tap;
	}

	public PeriodicalPapersScore getPpscore() {
		return ppscore;
	}

	public void setPpscore(PeriodicalPapersScore ppscore) {
		this.ppscore = ppscore;
	}

	public Periodical getPeriodical() {
		return periodical;
	}

	public void setPeriodical(Periodical periodical) {
		this.periodical = periodical;
	}

}
