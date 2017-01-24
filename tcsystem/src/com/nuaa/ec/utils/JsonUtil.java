package com.nuaa.ec.utils;
import java.util.List;
import java.util.Map;

import com.nuaa.ec.model.PeriodicalPaperInfoUnionModel;
import com.nuaa.ec.model.TeacherAndacademicWork;
import com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech;
import com.nuaa.ec.model.TeacherAndjoinAcademicMeeting;
import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.nuaa.ec.model.TeacherAndscientificResearchProject;
import com.nuaa.ec.model.TeacherAndscientificResearchReward;
import com.nuaa.ec.model.TeacherAndselectedTalentProject;
/**
 * 用于拼接个人绩效数据汇总明细的json字符串
 * @author RayHauton
 *
 */
public class JsonUtil {
	private static Map<String,Object> teachers = StoreData.getTeachertranslate();
	public static String getJsonStrOfPersonPerfDataDetail(String moduleName,Object dataList){
		StringBuffer dataJson=new StringBuffer("");
		if(moduleName.trim().equals("scienReschPro")){
			@SuppressWarnings("unchecked")
			List<TeacherAndscientificResearchProject> tAScienReschProList = (List<TeacherAndscientificResearchProject>) dataList;
			//{"科研项目编号","科研项目名称","项目类型","项目原编号","项目来源","立项年份","项目总金/万","当年到款/万","负责人ID","负责人姓名","教师编号","教师姓名","本人承担任务","基础分数"};
			dataJson.append("{'moduleName':'科研项目绩效明细',"
					+ "'field':['科研项目编号','科研项目名称','项目类型','项目原编号','项目来源','立项年份','项目总金/万','当年到款/万','负责人ID','负责人姓名','教师编号','教师姓名','本人承担任务','基础分数'],"
					+ "'dataArray':[");
			for(TeacherAndscientificResearchProject item: tAScienReschProList ){
				dataJson.append("['"+item.getScientificResearchProject().getSrprojectId()+"',"
								+ "'"+item.getScientificResearchProject().getSrpname()+"',"
								+ "'"+item.getScientificResearchProject().getProjectType().getProjectTpName()+"',"
								+ "'"+item.getScientificResearchProject().getProjectNumber()+"',"
								+ "'"+item.getScientificResearchProject().getProjectSource()+"',"
								+ "'"+item.getScientificResearchProject().getAdmitedProjectYear()+"',"
								+ "'"+item.getScientificResearchProject().getSumFunds()+"',"
								+ "'"+item.getYearFunds()+"',"
								+ "'"+item.getScientificResearchProject().getChargePersonId()+"',"
								+ "'"+teachers.get(item.getScientificResearchProject().getChargePersonId())+"',"
								+ "'"+item.getTeacher().getTeacherId()+"',"
								+ "'"+item.getTeacher().getTeacherName()+"',"
								+ "'"+item.getSelfUndertakeTask().getUndertakeTaskName()+"',"
								+ "'"+item.getFinalScore()+"'],");
			}
		}else if(moduleName.trim().equals("scienReschRewd")){
			@SuppressWarnings("unchecked")
			List<TeacherAndscientificResearchReward> tAScienReschRewdList = (List<TeacherAndscientificResearchReward>) dataList;
			/**
			 * {"科研奖励编号","获奖级别","获奖类别","科研奖励名称",
			 * "授奖部门","获奖日期","获奖总人数","负责人ID",
			 * "负责人姓名","当前教师编号","当前教师姓名","本人排名","当前教师绩效"};
			 */
			dataJson.append("{'moduleName':'科研项目奖励绩效明细',"
					+ "'field':['科研奖励编号','获奖级别','获奖类别','科研奖励名称','授奖部门','获奖日期','获奖总人数','负责人ID','负责人姓名','教师编号','教师姓名','本人排名','基础分数'],"
					+ "'dataArray':[");
			for(TeacherAndscientificResearchReward item:tAScienReschRewdList){
				dataJson.append("['"+item.getScientificResearchReward().getSrrewardId()+"',"
						+ "'"+item.getScientificResearchReward().getRewardLevel().getRewardLevelName()+"',"
						+ "'"+item.getScientificResearchReward().getRewardType().getRewardTypeName()+"',"
						+ "'"+item.getScientificResearchReward().getSrrewardName()+"',"
						+ "'"+item.getScientificResearchReward().getAwardDepartment()+"',"
						+ "'"+item.getRewardDate()+"',"
						+ "'"+item.getScientificResearchReward().getRewardTotalPeople()+"',"
						+ "'"+item.getScientificResearchReward().getChargePersonId()+"',"
						+ "'"+teachers.get(item.getScientificResearchReward().getChargePersonId())+"',"
						+ "'"+item.getTeacher().getTeacherId()+"',"
						+ "'"+item.getTeacher().getTeacherName()+"',"
						+ "'"+item.getSelfRanking()+"',"
						+ "'"+item.getFinalScore()+"'],");
			}
		}else if(moduleName.trim().equals("AkdmkWork")){
			@SuppressWarnings("unchecked")
			List<TeacherAndacademicWork> tAAkdmkWookList = (List<TeacherAndacademicWork>) dataList;
			/*
			 * {"学术著作编号","第一作者","学术著作名称","出版日期",
			 * "出版社名称","ISBN","字数","负责人ID","负责人姓名",
			 * "是否其他作者参与","当前教师编号","当前教师姓名","本人承担任务","当前教师绩效"};
			 */
			dataJson.append("{'moduleName':'学术著作绩效明细',"
					+ "'field':['学术著作编号','第一作者','学术著作名称','出版日期','出版社名称','ISBN','字数','负责人ID','负责人姓名','是否其他作者参与','教师编号','教师姓名','本人承担任务','分数'],"
					+ "'dataArray':[");
			for(TeacherAndacademicWork item:tAAkdmkWookList){
				dataJson.append("['"+item.getAcademicWork().getAcaworkId()+"',"
						+ "'"+item.getAcademicWork().getFirstAuthor()+"',"
						+ "'"+item.getAcademicWork().getWorkName()+"',"
						+ "'"+item.getAcademicWork().getPublishDate()+"',"
						+ "'"+item.getAcademicWork().getPublishClub().getPublishClubName()+"',"
						+ "'"+item.getAcademicWork().getIsbn()+"',"
						+ "'"+item.getAcademicWork().getWordsNumber().getWordNumber()+"',"
						+ "'"+item.getAcademicWork().getChargePersonId()+"',"
						+ "'"+teachers.get(item.getAcademicWork().getChargePersonId())+"',"
						+ "'"+(item.getAcademicWork().getOtherAuthorJoin().trim().equals("1")?"是":"否")+"',"
						+ "'"+item.getTeacher().getTeacherId()+"',"
						+ "'"+item.getTeacher().getTeacherName()+"',"
						+ "'"+item.getSelfUndertakeTask().getUndertakeTaskName()+"',"
						+ "'"+item.getFinalScore()+"'],");
			}
		}else if(moduleName.trim().equals("JoinAkdmkMeeting")){
			@SuppressWarnings("unchecked")
			List<TeacherAndjoinAcademicMeeting> tAJoinAkdmkMeetingList = (List<TeacherAndjoinAcademicMeeting>) dataList;
			/*
			 * {"学术会议编号","会议类型","会议地点","会议时间",
			 * "学术会议名称","负责人ID","负责人姓名","会议论文编号",
			 * "作者","作者身份","论文标题","检索情况",
			 * "当前教师编号","当前教师姓名","当前教师绩效"};
			 */
			dataJson.append("{'moduleName':'参加学术会议绩效明细',"
					+ "'field':['学术会议编号','会议类型','会议地点','会议时间','学术会议名称',"
					+ "'负责人ID','负责人姓名','会议论文编号','论文标题','作者','作者身份',"
					+ "'检索情况','教师编号','教师姓名','分数'],"
					+ "'dataArray':[");
			for(TeacherAndjoinAcademicMeeting item: tAJoinAkdmkMeetingList){
				dataJson.append("['"+item.getJoinAcademicMeeting().getJoinAcaMid()+"',"
						+ "'"+item.getJoinAcademicMeeting().getMeetingType().getMeetingTypeName()+"',"
						+ "'"+item.getJoinAcademicMeeting().getMeetingPlace().getMeetingPlace()+"',"
						+ "'"+item.getJoinAcademicMeeting().getMeetingdate()+"',"
						+ "'"+item.getJoinAcademicMeeting().getAcaMeetName()+"',"
						+ "'"+item.getJoinAcademicMeeting().getChargePersonId()+"',"
						+ "'"+teachers.get(item.getJoinAcademicMeeting().getChargePersonId())+"',"
						+ "'"+item.getMeetingPaper().getMeetingPaperId()+"',"
						+ "'"+item.getMeetingPaper().getPaperTitle()+"',"
						+ "'"+item.getMeetingPaper().getAuthorName()+"',"
						+ "'"+item.getMeetingPaper().getAuthorIdentity()+"',"
						+ "'"+item.getMeetingPaper().getPaperRetrievalCondition().getPrcondition()+"',"
						+ "'"+item.getTeacher().getTeacherId()+"',"
						+ "'"+item.getTeacher().getTeacherName()+"',"
						+ "'"+item.getFinalScore()+"'],");
			}
		}else if(moduleName.trim().equals("SelectTalentkPro")){
			@SuppressWarnings("unchecked")
			List<TeacherAndselectedTalentProject> tASelectTalentProList = (List<TeacherAndselectedTalentProject>) dataList;
			/*
			 * {"人才工程编号","人才工程名称","负责人ID","负责人姓名","当前教师编号","当前教师姓名","入选年份","当前教师绩效"};
			 */
			dataJson.append("{'moduleName':'入选人才工程绩效明细',"
					+ "'field':['人才工程编号','人才工程名称','入选年份','负责人ID','负责人姓名','教师编号',"
					+ "'教师姓名','分数'],'dataArray':[");
			for(TeacherAndselectedTalentProject item:tASelectTalentProList){
				dataJson.append("['"+item.getTalentProject().getTalentProjectId()+"',"
						+ "'"+item.getTalentProject().getTalentProjectName()+"',"
						+ "'"+item.getTalentProject().getSelectedDate()+"',"
						+ "'"+item.getTalentProject().getChargePersonId()+"',"
						+ "'"+teachers.get(item.getTalentProject().getChargePersonId())+"',"
						+ "'"+item.getTeacher().getTeacherId()+"',"
						+ "'"+item.getTeacher().getTeacherName()+"',"
						+ "'"+item.getFinalScore()+"'],");
			}
		}else if(moduleName.trim().equals("InviteExpertSpeech")){
			@SuppressWarnings("unchecked")
			List<TeacherAndinvitedExpertsSpeech> tAInviteExpertSpeechList = (List<TeacherAndinvitedExpertsSpeech>) dataList;
			/**
			 * {"专家讲学编号","国家","专家类型","专家姓名",
			 * "讲座名称","讲座时间","负责人ID","负责人姓名",
			 * "当前教师编号","当前教师姓名","本人承担任务","当前教师绩效"};
			 */
			dataJson.append("{'moduleName':'邀请专家讲学绩效明细',"
					+ "'field':['专家讲学编号','国家','专家类型','专家姓名','讲座名称','讲座时间','负责人ID','负责人姓名','教师编号','教师姓名','本人承担任务','分数'],"
					+ "'dataArray':[");
			for(TeacherAndinvitedExpertsSpeech item:tAInviteExpertSpeechList){
				dataJson.append("['"+item.getInvitedExpertsSpeech().getIespeechId()+"',"
						+ "'"+item.getInvitedExpertsSpeech().getNationality().getCountryName()+"',"
						+ "'"+item.getInvitedExpertsSpeech().getExpertType().getExpertTypeName()+"',"
						+ "'"+item.getInvitedExpertsSpeech().getExpertsName()+"',"
						+ "'"+item.getInvitedExpertsSpeech().getLectureName()+"',"
						+ "'"+item.getInvitedExpertsSpeech().getSpeechDate()+"',"
						+ "'"+item.getInvitedExpertsSpeech().getChargePersonId()+"',"
						+ "'"+teachers.get(item.getInvitedExpertsSpeech().getChargePersonId())+"',"
						+ "'"+item.getTeacher().getTeacherId()+"',"
						+ "'"+item.getTeacher().getTeacherName()+"',"
						+ "'"+item.getSelfUndertakeTask().getUndertakeTaskName()+"',"
						+ "'"+item.getFinalScore()+"'],");
			}
		}else if(moduleName.trim().equals("periodicalPaper")){
			@SuppressWarnings("unchecked")
			List<PeriodicalPaperInfoUnionModel> periodicalPaperList = (List<PeriodicalPaperInfoUnionModel>) dataList;
			/*
			 * {"期刊论文编号","第一作者","第二作者","期刊论文标题","期刊名称","年份","卷","期","相关描述","当前教师Id","当前教师姓名","当前教师绩效"};
			 */
			dataJson.append("{'moduleName':'期刊论文绩效明细',"
					+ "'field':['期刊论文编号','第一作者','第二作者','期刊论文标题','期刊名称','年份','卷','期','相关描述','教师编号','教师姓名','分数'],"
					+ "'dataArray':[");
			for(PeriodicalPaperInfoUnionModel item:periodicalPaperList){
				dataJson.append("['"+item.getPeriodicalPapers().getPeriodicalId()+"',"
						+ "'"+item.getPeriodicalPapers().getFirstAuthor()+"',"
						+ "'"+item.getPeriodicalPapers().getSecondAuthor()+"',"
						+ "'"+item.getPeriodicalPapers().getThesisTitle()+"',"
						+ "'"+item.getPeriodicalPapers().getPeriodicalName()+"',"
						+ "'"+item.getPeriodicalPapers().getYear()+"',"
						+ "'"+item.getPeriodicalPapers().getFile()+"',"
						+ "'"+item.getPeriodicalPapers().getPhase()+"',"
						+ "'"+item.getPeriodicalPapers().getDescribe()+"',"
						+ "'"+item.getTAPeriodical().getTeacher().getTeacherId()+"',"
						+ "'"+item.getTAPeriodical().getTeacher().getTeacherName()+"',"
						+ "'"+item.getTAPeriodical().getFinalScore()+"'],");
			}
		}else if(moduleName.trim().equals("mainUndertkAkdmkMeeting")){
			@SuppressWarnings("unchecked")
			List<TeacherAndmainUndertakeAcademicMeeting> tAMainUndtakAkdmkMeetingList =(List<TeacherAndmainUndertakeAcademicMeeting>) dataList;
			/*
			 * {"主承办学术会议编号","主承办学术会议名称","会议类型","会议地点",
			 * "会议时间","负责人ID","负责人姓名","参与教师编号",
			 * "参与教师姓名","本人承担任务","基础分数"};
			 */
			dataJson.append("{'moduleName':'主承办学术会议绩效明细',"
					+ "'field':['学术会议编号','学术会议名称','会议类型','会议地点','会议时间','负责人ID','负责人姓名','教师编号','教师姓名','本人承担任务','分数'],"
					+ "'dataArray':[");
			for(TeacherAndmainUndertakeAcademicMeeting item:tAMainUndtakAkdmkMeetingList){
				dataJson.append("['"+item.getMainUndertakeAcademicMeeting().getAcaMeetingId()+"',"
						+ "'"+item.getMainUndertakeAcademicMeeting().getAcaMeetingName()+"',"
						+ "'"+item.getMainUndertakeAcademicMeeting().getMainUndertakeAcademicMeetingType().getAcaMeetType()+"',"
						+ "'"+item.getMainUndertakeAcademicMeeting().getMainUndertakeAcademicMeetingPlace().getAcaMeetPlace()+"',"
						+ "'"+item.getMainUndertakeAcademicMeeting().getMeetingdate()+"',"
						+ "'"+item.getMainUndertakeAcademicMeeting().getChargePersonId()+"',"
						+ "'"+teachers.get(item.getMainUndertakeAcademicMeeting().getChargePersonId())+"',"
						+ "'"+item.getTeacher().getTeacherId()+"',"
						+ "'"+item.getTeacher().getTeacherName()+"',"
						+ "'"+item.getSelfUndertakeTask().getUndertakeTaskName()+"',"
						+ "'"+item.getFinalScore()+"'],");
			}
		}
		//去掉结尾多余的一个逗号；
		String jsonExceptDot = dataJson.substring(0, dataJson.length()-1);
		jsonExceptDot+="]}";
		return jsonExceptDot;
	}
}
