package com.nuaa.ec.ScientificResearchPerformanceSet.Action;

import java.util.Map;



import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.MainUndertakeAcademicMeetingDAO;
import com.nuaa.ec.dao.MainUndertakeAcademicMeetingPlaceDAO;
import com.nuaa.ec.dao.MainUndertakeAcademicMeetingScoreDAO;
import com.nuaa.ec.dao.MainUndertakeAcademicMeetingTypeDAO;
import com.nuaa.ec.dao.SelfUndertakeTaskDAO;
import com.nuaa.ec.dao.TeacherAndmainUndertakeAcademicMeetingDAO;
import com.nuaa.ec.model.MainUndertakeAcademicMeeting;
import com.nuaa.ec.model.MainUndertakeAcademicMeetingPlace;
import com.nuaa.ec.model.MainUndertakeAcademicMeetingScore;
import com.nuaa.ec.model.MainUndertakeAcademicMeetingType;
import com.nuaa.ec.model.SelfUndertakeTask;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class undertakeacademicmeetAction implements RequestAware, SessionAware {
//	Attr
	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foredate;
	private String afterdate;
	private Integer operstatus;
	private MainUndertakeAcademicMeeting undertakemeet;
	private MainUndertakeAcademicMeetingScore undertakemeetscore;
	private MainUndertakeAcademicMeetingPlace meetplace;
	private MainUndertakeAcademicMeetingType meettype;
	private TeacherAndmainUndertakeAcademicMeeting teacherandudtmeet;
	private SelfUndertakeTask selftask;
//	DAO-attr
	private MainUndertakeAcademicMeetingDAO undertakemeetdao = new MainUndertakeAcademicMeetingDAO();
	private MainUndertakeAcademicMeetingScoreDAO undertakemeetscoredao = new MainUndertakeAcademicMeetingScoreDAO();
	private MainUndertakeAcademicMeetingPlaceDAO meetplacedao = new MainUndertakeAcademicMeetingPlaceDAO();
	private MainUndertakeAcademicMeetingTypeDAO meettypedao = new MainUndertakeAcademicMeetingTypeDAO();
	private TeacherAndmainUndertakeAcademicMeetingDAO teacherandudtmeetdao = new TeacherAndmainUndertakeAcademicMeetingDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private SelfUndertakeTaskDAO selftaskdao = new SelfUndertakeTaskDAO();
	
	//default method
	public String execute(){
		return "success";
	}
	
	//TODO: 会议设置
	public String gainAllacademicm()throws Exception{
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
		request.put("undertakemeeting", undertakemeetdao.findPageing((pagenum-1)*limitrow,limitrow,EntityUtil.generateQueryCondition(foredate, afterdate, "meetingdate")));
		request.put("meetingtype", meettypedao.findAll());
		request.put("meetingplace", meetplacedao.findAll());
		request.put("selfdown", selftaskdao.findAll());
		int li = undertakemeetdao.getRows(EntityUtil.generateQueryCondition(foredate, afterdate, "meetingdate"));
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
	
	public void addUndertakemeeting()throws Exception{
		Transaction tx = null;
		try {
			undertakemeetscore = undertakemeetscoredao.findByscoreCondition(meetplace, meettype);
			if(undertakemeetscore!=null){
				undertakemeet.setAcaMeetingId(pkmk.mkpk(EntityUtil.getPkColumnName(MainUndertakeAcademicMeeting.class), EntityUtil.getTableName(MainUndertakeAcademicMeeting.class), "MUACAM"));
				undertakemeet.setSpareTire("1");
				undertakemeet.setMainUndertakeAcademicMeetingPlace(meetplacedao.findById(meetplace.getAcaMeetPlaceId()));
				undertakemeet.setMainUndertakeAcademicMeetingType(meettypedao.findById(meettype.getAcaMeetTypeId()));
				undertakemeet.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
				undertakemeet.setCheckout("5");
				undertakemeetdao.save(undertakemeet);
				teacherandudtmeet = new TeacherAndmainUndertakeAcademicMeeting();
				teacherandudtmeet.setCheckOut("0");
				teacherandudtmeet.setFinalScore((double)undertakemeetscore.getScore());
				teacherandudtmeet.setMainUndertakeAcademicMeeting(undertakemeet);
				teacherandudtmeet.setMainUndertakeAcademicMeetingScore(undertakemeetscore);
				teacherandudtmeet.setSelfUndertakeTask(selftaskdao.findByUndertakeTaskNameDim());
				teacherandudtmeet.setSpareTire("1");
				teacherandudtmeet.setTeacher((Teacher)session.get("teacher"));
				teacherandudtmeetdao.save(teacherandudtmeet);
				ServletActionContext.getResponse().getWriter().write("succ");
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("会议地点与回忆类型无对应评分项，请联系管理");
				return ;
			}
			tx = undertakemeetdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	public void updateUnderacademic()throws Exception{
		Transaction tx = null;
		try {
			undertakemeetscore = undertakemeetscoredao.findByscoreCondition(meetplace, meettype);
			if(undertakemeetscore!=null){
				undertakemeet.setSpareTire("1");
				undertakemeet.setMainUndertakeAcademicMeetingPlace(meetplacedao.findById(meetplace.getAcaMeetPlaceId()));
				undertakemeet.setMainUndertakeAcademicMeetingType(meettypedao.findById(meettype.getAcaMeetTypeId()));
				undertakemeet.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
				undertakemeetdao.merge(undertakemeet);
				teacherandudtmeetdao.updateRefMeeting((Teacher)session.get("teacher"), undertakemeet,undertakemeetscore);
				ServletActionContext.getResponse().getWriter().write("succ");
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("会议地点与回忆类型无对应评分项，请联系管理");
				return ;
			}
			tx = undertakemeetdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	public void deleteAcademicm()throws Exception{
		Transaction tx = null;
		try {
			undertakemeetdao.deleteBylogic(undertakemeet.getAcaMeetingId());
			teacherandudtmeetdao.deleteRefMainMeet(undertakemeet);
			tx = undertakemeetdao.getSession().beginTransaction();
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
			JSONArray jary = JSONArray.fromObject(teacherandudtmeetdao.findMember(undertakemeet));
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().write(jary.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public void joinmainacam()throws Exception{
		Transaction tx = null;
		try {
			teacherandudtmeet = new TeacherAndmainUndertakeAcademicMeeting();
			this.setUndertakemeet(undertakemeetdao.findById(undertakemeet.getAcaMeetingId()));
			undertakemeetscore = undertakemeetscoredao.findByscoreCondition(undertakemeet.getMainUndertakeAcademicMeetingPlace(), undertakemeet.getMainUndertakeAcademicMeetingType());
			if(undertakemeetscore!=null){
				teacherandudtmeet.setCheckOut("0");
				teacherandudtmeet.setFinalScore((double)undertakemeetscore.getScore());
				teacherandudtmeet.setMainUndertakeAcademicMeeting(undertakemeet);
				teacherandudtmeet.setMainUndertakeAcademicMeetingScore(undertakemeetscore);
				teacherandudtmeet.setSelfUndertakeTask(selftaskdao.findById(selftask.getUndertakeTaskId()));
				teacherandudtmeet.setSpareTire("1");
				teacherandudtmeet.setTeacher((Teacher)session.get("teacher"));
				teacherandudtmeetdao.save(teacherandudtmeet);
				ServletActionContext.getResponse().getWriter().write("succ");
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("该项目对应评分项已经被破坏，请联系管理");
				return;
			}
			tx = teacherandudtmeetdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO: 个人设置
	public String getPersonJoin()throws Exception{
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
		request.put("teacherandmacam", teacherandudtmeetdao.findPageing((pagenum-1)*limitrow,limitrow,EntityUtil.generateQueryCondition(foredate, afterdate, "mainUndertakeAcademicMeeting.meetingdate"),(Teacher)session.get("teacher")));
		int li = teacherandudtmeetdao.getRows(EntityUtil.generateQueryCondition(foredate, afterdate, "mainUndertakeAcademicMeeting.meetingdate"));
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
	
	public void quitProject()throws Exception{
		Transaction tx = null;
		try {
			teacherandudtmeetdao.deleteBylogic((Teacher)session.get("teacher"), undertakemeet);
			tx = teacherandudtmeetdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
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

	public MainUndertakeAcademicMeeting getUndertakemeet() {
		return undertakemeet;
	}

	public void setUndertakemeet(MainUndertakeAcademicMeeting undertakemeet) {
		this.undertakemeet = undertakemeet;
	}

	public MainUndertakeAcademicMeetingScore getUndertakemeetscore() {
		return undertakemeetscore;
	}

	public void setUndertakemeetscore(
			MainUndertakeAcademicMeetingScore undertakemeetscore) {
		this.undertakemeetscore = undertakemeetscore;
	}

	public MainUndertakeAcademicMeetingPlace getMeetplace() {
		return meetplace;
	}

	public void setMeetplace(MainUndertakeAcademicMeetingPlace meetplace) {
		this.meetplace = meetplace;
	}

	public MainUndertakeAcademicMeetingType getMeettype() {
		return meettype;
	}

	public void setMeettype(MainUndertakeAcademicMeetingType meettype) {
		this.meettype = meettype;
	}

	public TeacherAndmainUndertakeAcademicMeeting getTeacherandudtmeet() {
		return teacherandudtmeet;
	}

	public void setTeacherandudtmeet(
			TeacherAndmainUndertakeAcademicMeeting teacherandudtmeet) {
		this.teacherandudtmeet = teacherandudtmeet;
	}

	public SelfUndertakeTask getSelftask() {
		return selftask;
	}

	public void setSelftask(SelfUndertakeTask selftask) {
		this.selftask = selftask;
	}
}
