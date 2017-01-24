package com.nuaa.ec.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.ScientificResearchSummaryDataDao;
import com.nuaa.ec.dao.TeacherAndacademicWorkDAO;
import com.nuaa.ec.dao.TeacherAndinvitedExpertsSpeechDAO;
import com.nuaa.ec.dao.TeacherAndjoinAcademicMeetingDAO;
import com.nuaa.ec.dao.TeacherAndmainUndertakeAcademicMeetingDAO;
import com.nuaa.ec.dao.TeacherAndperiodicalDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchProjectDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchRewardDAO;
import com.nuaa.ec.dao.TeacherAndselectedTalentProjectDAO;
import com.nuaa.ec.model.PeriodicalPaperInfoUnionModel;
import com.nuaa.ec.model.TeacherAndacademicWork;
import com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech;
import com.nuaa.ec.model.TeacherAndjoinAcademicMeeting;
import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.nuaa.ec.model.TeacherAndscientificResearchReward;
import com.nuaa.ec.model.TeacherAndselectedTalentProject;
import com.nuaa.ec.utils.JsonUtil;
import com.opensymphony.xwork2.ActionContext;

public class ScienResearchDataSummaryAction_person implements RequestAware {
	public void getDetailDataInfoJson() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		String jsonString = null;
		if (this.moduleName.trim().equals("scienReschPro")) {
			List<TeacherAndscientificResearchProject> tAScienReschProList = tAScienReschProDao
					.getPersonDetailsOfScienRescPro(teacherId, foredate,
							afterdate);
			jsonString = JsonUtil.getJsonStrOfPersonPerfDataDetail(moduleName,
					tAScienReschProList);
		} else if (this.moduleName.trim().equals("scienReschRewd")) {
			List<TeacherAndscientificResearchReward> tAScienReschRewdList = tAScienReshRewardDao
					.getPersonDetailsOfScienReschRewd(teacherId, foredate,
							afterdate);
			jsonString = JsonUtil.getJsonStrOfPersonPerfDataDetail(moduleName,
					tAScienReschRewdList);
		} else if (this.moduleName.trim().equals("AkdmkWork")) {
			List<TeacherAndacademicWork> tAAkdmkWorkList = tAAkdmkWorkDao
					.getPersonDetailsOfAkdmkWork(teacherId, foredate, afterdate);
			jsonString = JsonUtil.getJsonStrOfPersonPerfDataDetail(moduleName,
					tAAkdmkWorkList);
		} else if (this.moduleName.trim().equals("JoinAkdmkMeeting")) {
			List<TeacherAndjoinAcademicMeeting> tAJoinAkdmkMeetingList = tAJoinAkdmkMeetingDao
					.getPersonDetailsOfJoinAkdmkMeetin(teacherId, foredate,
							afterdate);
			jsonString = JsonUtil.getJsonStrOfPersonPerfDataDetail(moduleName,
					tAJoinAkdmkMeetingList);
		} else if (this.moduleName.trim().equals("SelectTalentkPro")) {
			List<TeacherAndselectedTalentProject> tASelectedTalentProList = tASelectedTalentProDao
					.getPersonDetailsOfSlktTlntPro(teacherId, foredate,
							afterdate);
			jsonString = JsonUtil.getJsonStrOfPersonPerfDataDetail(moduleName,
					tASelectedTalentProList);
		} else if (this.moduleName.trim().equals("InviteExpertSpeech")) {
			List<TeacherAndinvitedExpertsSpeech> tAInviteExpertSpeechList = tAInviteExpertSpeechDao
					.getPersonDetailsOfInvtEksptSpch(teacherId, foredate,
							afterdate);
			jsonString = JsonUtil.getJsonStrOfPersonPerfDataDetail(moduleName,
					tAInviteExpertSpeechList);
		} else if (this.moduleName.trim().equals("periodicalPaper")) {
			List<PeriodicalPaperInfoUnionModel> periodicalPaperInfoList = tAPeriodicalDao.getPersonDetailsOfPeriodical(teacherId, foredate, afterdate);
			jsonString = JsonUtil.getJsonStrOfPersonPerfDataDetail(moduleName,
					periodicalPaperInfoList);
		} else if(this.moduleName.trim().equals("mainUndertkAkdmkMeeting")){
			List<TeacherAndmainUndertakeAcademicMeeting> tAMainUndtakAkdmkMeetingList = tAMainUndertakeAkdmkMeetingDao.getPersonDetailsOfScienReschRewd(teacherId, foredate, afterdate);
			jsonString = JsonUtil.getJsonStrOfPersonPerfDataDetail(moduleName,
					tAMainUndtakAkdmkMeetingList);
		}
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
	}

	//

	/*
	 * 按照个人汇总科研绩效
	 */
	public String getScienReschSummaryDataByTeacher() throws Exception {
		this.request.put("scienReschModlSumryDataPerson",
				ScientificResearchSummaryDataDao
						.summaryScienReschModuleDataByPerson(teacherId,
								foredate, afterdate));
		return "success";
	}

	public String execute() throws Exception {
		return "success";
	}

	private String foredate;
	private String afterdate;
	private String teacherId;
	private String moduleName;
	private Map<String, Object> request;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private static TeacherAndscientificResearchProjectDAO tAScienReschProDao = new TeacherAndscientificResearchProjectDAO();
	private static TeacherAndscientificResearchRewardDAO tAScienReshRewardDao = new TeacherAndscientificResearchRewardDAO();
	private static TeacherAndacademicWorkDAO tAAkdmkWorkDao = new TeacherAndacademicWorkDAO();
	private static TeacherAndjoinAcademicMeetingDAO tAJoinAkdmkMeetingDao = new TeacherAndjoinAcademicMeetingDAO();
	private static TeacherAndselectedTalentProjectDAO tASelectedTalentProDao = new TeacherAndselectedTalentProjectDAO();
	private static TeacherAndinvitedExpertsSpeechDAO tAInviteExpertSpeechDao = new TeacherAndinvitedExpertsSpeechDAO();
	private static TeacherAndperiodicalDAO tAPeriodicalDao = new TeacherAndperiodicalDAO();
	private static TeacherAndmainUndertakeAcademicMeetingDAO tAMainUndertakeAkdmkMeetingDao = new TeacherAndmainUndertakeAcademicMeetingDAO();

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		this.foredate = foredate;
		session.put("foredate_person", foredate);
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
		session.put("afterdate_person", afterdate);
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
		System.out.println("********" + teacherId);
		session.put("teacherId_person", teacherId);
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
		session.put("moduleName_person", moduleName);
	}

}
