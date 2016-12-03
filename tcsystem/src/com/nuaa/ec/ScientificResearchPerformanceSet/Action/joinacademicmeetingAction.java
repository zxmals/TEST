package com.nuaa.ec.ScientificResearchPerformanceSet.Action;

import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.JoinAcademicMeetingDAO;
import com.nuaa.ec.dao.JoinAcademicMeetingScoreDAO;
import com.nuaa.ec.dao.MeetingPaperDAO;
import com.nuaa.ec.dao.MeetingPlaceDAO;
import com.nuaa.ec.dao.MeetingTypeDAO;
import com.nuaa.ec.dao.PaperRetrievalConditionDAO;
import com.nuaa.ec.dao.TeacherAndjoinAcademicMeetingDAO;
import com.nuaa.ec.model.JoinAcademicMeeting;
import com.nuaa.ec.model.JoinAcademicMeetingScore;
import com.nuaa.ec.model.MeetingPaper;
import com.nuaa.ec.model.MeetingPlace;
import com.nuaa.ec.model.MeetingType;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndjoinAcademicMeeting;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class joinacademicmeetingAction implements RequestAware, SessionAware {

	private Map<String, Object> request;
	private Map<String, Object> session;
	private String foredate;
	private String afterdate;
	private Integer operstatus;
	
	private JoinAcademicMeeting joinacademic;
	private JoinAcademicMeetingScore joinacademicscore;
	private TeacherAndjoinAcademicMeeting teacherandjoinacademic;
	private MeetingPlace meetplace;
	private MeetingType meettype;
	private MeetingPaper meetpaper;
	
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private JoinAcademicMeetingDAO joinacademicdao = new JoinAcademicMeetingDAO();
	private JoinAcademicMeetingScoreDAO joinacademicscoredao = new JoinAcademicMeetingScoreDAO();
	private TeacherAndjoinAcademicMeetingDAO teacherandjoinacademicdao = new TeacherAndjoinAcademicMeetingDAO();
	private MeetingPlaceDAO meetplacedao = new MeetingPlaceDAO();
	private MeetingTypeDAO meettypedao = new MeetingTypeDAO();
	private PaperRetrievalConditionDAO meetpaperretridao = new PaperRetrievalConditionDAO();
	private MeetingPaperDAO meetpaperdao = new MeetingPaperDAO();
	//default method
	public String execute(){
		return "success";
	}

	//会议设置
	public String gainAllJoinacademic(){
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
		request.put("joinacademic", joinacademicdao.findPageing((pagenum-1)*limitrow,limitrow,EntityUtil.generateQueryCondition(foredate, afterdate, "meetingdate")));
		request.put("meetingplace", meetplacedao.findAll());
		request.put("meetingtype", meettypedao.findAll());
		request.put("paperretri", meetpaperretridao.findAll());
		int li = joinacademicdao.getRows(EntityUtil.generateQueryCondition(foredate, afterdate, "meetingdate"));
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
	
	public void addJoinacademicmeeting() throws Exception{
		Transaction tx = null;
		try {
			joinacademic.setJoinAcaMid(pkmk.mkpk(EntityUtil.getPkColumnName(JoinAcademicMeeting.class), EntityUtil.getTableName(JoinAcademicMeeting.class), "Jam"));
			joinacademic.setSpareTire("1");
			joinacademic.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			joinacademic.setCheckout("5");
			this.setMeettype(meettypedao.findById(meettype.getMeetingTypeId()));
			joinacademic.setMeetingPlace(meetplacedao.findById(meetplace.getMeetingPlaceId()));
			if(joinacademicscoredao.findByMeetType(meettype)>0){
				joinacademic.setMeetingType(meettype);
				joinacademicdao.save(joinacademic);
				ServletActionContext.getResponse().getWriter().write("succ");
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("该会议类型不存在匹配的评分项，请联系管理");
			}
			tx = joinacademicdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	public void updateJoinacademic() throws Exception{
		Transaction tx = null;
		try {
			joinacademic.setSpareTire("1");
			joinacademic.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			this.setMeettype(meettypedao.findById(meettype.getMeetingTypeId()));
			joinacademic.setMeetingPlace(meetplacedao.findById(meetplace.getMeetingPlaceId()));
			if(joinacademicscoredao.findByMeetType(meettype)>0){
				joinacademic.setMeetingType(meettype);
				joinacademicdao.merge(joinacademic);
				ServletActionContext.getResponse().getWriter().write("succ");
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("该会议类型不存在匹配的评分项，请联系管理");
			}
			tx = joinacademicdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	public void deleteJoinacademic() throws Exception{
		Transaction tx = null;
		try {
			joinacademicdao.deleteJoinacameeting(joinacademic.getJoinAcaMid());
			teacherandjoinacademicdao.deletRefJoinAcademic(joinacademic.getJoinAcaMid());
			tx = joinacademicdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	public void getMember() throws Exception{
		try {
			JSONArray jary = JSONArray.fromObject(teacherandjoinacademicdao.findJMember(joinacademic.getJoinAcaMid()));
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().write(jary.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	public void joinacameeing() throws Exception{
		Transaction tx = null;
		try {
			this.setJoinacademic(joinacademicdao.findById(joinacademic.getJoinAcaMid()));
			teacherandjoinacademic = new TeacherAndjoinAcademicMeeting();
			if(teacherandjoinacademicdao.checkexist((Teacher)session.get("teacher"), joinacademic)){
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("不能重复加入");
				return;
			}
			if(meetpaper!=null){
				meetpaper.setMeetingPaperId(pkmk.mkpk(EntityUtil.getPkColumnName(MeetingPaper.class), EntityUtil.getTableName(MeetingPaper.class), "Jam"));
				meetpaper.setAuthorName(((Teacher)session.get("teacher")).getTeacherName());
				meetpaper.setSpareTire("1");
				teacherandjoinacademic.setMeetingPaper(meetpaper);
				teacherandjoinacademic.setCheckOut("0");
				teacherandjoinacademic.setSpareTire("1");
				teacherandjoinacademic.setJoinAcademicMeeting(joinacademic);
				teacherandjoinacademic.setTeacher((Teacher)session.get("teacher"));
				joinacademicscore = joinacademicscoredao.findByMeetTypeAndPaperretri(joinacademic.getMeetingType(), meetpaper.getPaperRetrievalCondition().getPrconditionId());
				if(joinacademicscore!=null){
					meetpaperdao.save(meetpaper);
					teacherandjoinacademic.setJoinAcademicMeetingScore(joinacademicscore);
					teacherandjoinacademic.setFinalScore((double)joinacademicscore.getScore());
					teacherandjoinacademicdao.save(teacherandjoinacademic);
					ServletActionContext.getResponse().getWriter().write("succ");
				}else{
					ServletActionContext.getResponse().setCharacterEncoding("utf-8");
					ServletActionContext.getResponse().getWriter().write("论文检索无对应评分项，请联系管理");
				}
			}else{
				teacherandjoinacademic.setCheckOut("0");
				teacherandjoinacademic.setSpareTire("1");
				teacherandjoinacademic.setJoinAcademicMeeting(joinacademic);
				teacherandjoinacademic.setTeacher((Teacher)session.get("teacher"));
				joinacademicscore = joinacademicscoredao.findByMeetTypeAndPaperretri(joinacademic.getMeetingType(), "mpr0");
				if(joinacademicscore!=null){
					teacherandjoinacademic.setJoinAcademicMeetingScore(joinacademicscore);
					teacherandjoinacademic.setFinalScore((double)joinacademicscore.getScore());
					teacherandjoinacademicdao.save(teacherandjoinacademic);
					ServletActionContext.getResponse().getWriter().write("succ");
				}else{
					ServletActionContext.getResponse().setCharacterEncoding("utf-8");
					ServletActionContext.getResponse().getWriter().write("论文检索无对应评分项，请联系管理");
				}
			}
			tx = teacherandjoinacademicdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
		}
	}
	//个人参与设置
	public String getPersonJoin(){
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
		request.put("teacherandjoinacam", teacherandjoinacademicdao.findPageing((pagenum-1)*limitrow,limitrow,EntityUtil.generateQueryCondition(foredate, afterdate, "joinAcademicMeeting.meetingdate"),(Teacher)session.get("teacher")));
		int li = teacherandjoinacademicdao.getRows(EntityUtil.generateQueryCondition(foredate, afterdate, "meetingdate"),(Teacher)session.get("teacher"));
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
	
	public void quitProject() throws Exception{
		Transaction tx = null;
		try {
			teacherandjoinacademicdao.quitObject((Teacher)session.get("teacher"), joinacademic);
			tx = teacherandjoinacademicdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
//			throw e;
			e.printStackTrace();
			tx.rollback();
		}
	}
	//TODO: Getter & Setter
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
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

	public JoinAcademicMeeting getJoinacademic() {
		return joinacademic;
	}

	public void setJoinacademic(JoinAcademicMeeting joinacademic) {
		this.joinacademic = joinacademic;
	}

	public JoinAcademicMeetingScore getJoinacademicscore() {
		return joinacademicscore;
	}

	public void setJoinacademicscore(JoinAcademicMeetingScore joinacademicscore) {
		this.joinacademicscore = joinacademicscore;
	}

	public TeacherAndjoinAcademicMeeting getTeacherandjoinacademic() {
		return teacherandjoinacademic;
	}

	public void setTeacherandjoinacademic(
			TeacherAndjoinAcademicMeeting teacherandjoinacademic) {
		this.teacherandjoinacademic = teacherandjoinacademic;
	}

	public MeetingPlace getMeetplace() {
		return meetplace;
	}

	public void setMeetplace(MeetingPlace meetplace) {
		this.meetplace = meetplace;
	}

	public MeetingType getMeettype() {
		return meettype;
	}

	public void setMeettype(MeetingType meettype) {
		this.meettype = meettype;
	}

	public MeetingPaper getMeetpaper() {
		return meetpaper;
	}

	public void setMeetpaper(MeetingPaper meetpaper) {
		this.meetpaper = meetpaper;
	}

}
