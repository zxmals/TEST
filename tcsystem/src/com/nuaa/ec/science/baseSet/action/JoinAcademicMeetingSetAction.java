package com.nuaa.ec.science.baseSet.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.JoinAcademicMeetingScoreDAO;
import com.nuaa.ec.dao.MeetingPlaceDAO;
import com.nuaa.ec.dao.MeetingTypeDAO;
import com.nuaa.ec.dao.PaperRetrievalConditionDAO;
import com.nuaa.ec.model.JoinAcademicMeetingScore;
import com.nuaa.ec.model.MeetingPlace;
import com.nuaa.ec.model.MeetingType;
import com.nuaa.ec.model.PaperRetrievalCondition;
import com.nuaa.ec.utils.PrimaryKMaker;

public class JoinAcademicMeetingSetAction implements RequestAware {

	private Map<String, Object> request;
	
	private MeetingPlace meetingplace;
	private MeetingType meetingtype;
	private PaperRetrievalCondition paperretrieval;
	private JoinAcademicMeetingScore joinmeetingscore;
	
	private MeetingPlaceDAO meetingplacedao = new MeetingPlaceDAO();
	private MeetingTypeDAO meetingtpdao = new MeetingTypeDAO();
	private PaperRetrievalConditionDAO paprerevaldao  = new PaperRetrievalConditionDAO();
	private JoinAcademicMeetingScoreDAO joinmeetingdao = new  JoinAcademicMeetingScoreDAO();
	
	private PrimaryKMaker pkmk = new  PrimaryKMaker();
	//default method
	public String execute(){
		return "success";
	}
	//TODO: 会议类型设置 // meeting Type - Set
	public String getAcademicMeetingType() throws Exception{
		return "success";
	}
	//TODO: 会议地点设置 // meeting place - Set
	public String getAcademicMeetingPlaceINF() throws Exception {
		request.put("meetingplaceli", meetingplacedao.findAll());
		return "success";
	}
	//TODO: 会议论文检索设置 // meeting -paper - retrieval - Set
	//TODO:  参与会议评分设置 //join- meeting -score - Set
	//Getter and Setter
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public MeetingPlace getMeetingplace() {
		return meetingplace;
	}

	public void setMeetingplace(MeetingPlace meetingplace) {
		this.meetingplace = meetingplace;
	}

	public MeetingType getMeetingtype() {
		return meetingtype;
	}

	public void setMeetingtype(MeetingType meetingtype) {
		this.meetingtype = meetingtype;
	}

	public PaperRetrievalCondition getPaperretrieval() {
		return paperretrieval;
	}

	public void setPaperretrieval(PaperRetrievalCondition paperretrieval) {
		this.paperretrieval = paperretrieval;
	}

	public JoinAcademicMeetingScore getJoinmeetingscore() {
		return joinmeetingscore;
	}

	public void setJoinmeetingscore(JoinAcademicMeetingScore joinmeetingscore) {
		this.joinmeetingscore = joinmeetingscore;
	}
	
}
