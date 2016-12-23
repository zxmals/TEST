package com.nuaa.ec.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Tfterm;
public class StoreData {

	private static Map<String, Object> teachertranslate = new HashMap<String, Object>();
	private static List<ResearchLab> researchLabList=new ArrayList<ResearchLab>();
	private static List<Tfterm> TftermList=new ArrayList<Tfterm>();
	private static List<Department> departmentList=new ArrayList<Department>();
	private static Map<String, Object> scienexports;
	private static Map<String, Object> vaexporcts;
	
	public static Map<String, Object> getTeachertranslate() {
		return teachertranslate;
	}
	
	public static void setTeachertranslate(Map<String, Object> teachertranslate) {
		StoreData.teachertranslate = teachertranslate;
	}
	public static void setResearchLabList(List<ResearchLab> researchLabList) {
		StoreData.researchLabList = researchLabList;
	}
	
	public static List<ResearchLab> getResearchLabList(){
		return researchLabList;
	}

	public static List<Tfterm> getTftermList() {
		return TftermList;
	}

	public static void setTftermList(List<Tfterm> tftermList) {
		StoreData.TftermList = tftermList;
	}

	public static List<Department> getDepartmentList() {
		return departmentList;
	}

	public static void setDepartmentList(List<Department> departmentList) {
		StoreData.departmentList = departmentList;
	}
	public static Map<String, Object> getScienexports() {
		return scienexports;
	}

	public static void setScienexports() {
		scienexports = new HashMap<String, Object>();
		scienexports.put("学术著作", "AcademicWork");
		scienexports.put("邀请专家讲学", "InvitedExpertsSpeech");
		scienexports.put("参加学术会议", "JoinAcademicMeeting");
		scienexports.put("主承办学术会议", "MainUndertakeAcademicMeeting");
		scienexports.put("期刊论文", "PeriodicalPapers");
		scienexports.put("科研项目", "ScientificResearchProject");
		scienexports.put("科研奖励", "ScientificResearchReward");
		scienexports.put("入选人才工程", "TalentProject");
	}

	public static Map<String, String> getScienFilenameExported() {
		/**
		 * 存储导出数据的文件名
		 */
		 Map<String,String> filenameExported=new HashMap<String,String>();
		 Set<Map.Entry<String,Object>> entrySet=StoreData.getScienexports().entrySet();
		 for(Map.Entry<String,Object> entry:entrySet){
			 filenameExported.put((String) entry.getValue(), entry.getKey());
		 }
		return filenameExported;
	}
	public static Map<String,String> getTeachingFilenameExported(){
		/**
		 * 存储导出数据的文件名
		 */
		 Map<String,String> filenameExported=new HashMap<String,String>();
		 Set<Map.Entry<String,String>> entrySet=StoreData.getTeachingModule().entrySet();
		 for(Map.Entry<String,String> entry:entrySet){
			 filenameExported.put(entry.getValue(), entry.getKey());
		 }
		return filenameExported;
	}
	public static Map<String, Object> getVaexporcts() {
		return vaexporcts;
	}

	public static void setVaexporcts() {
		vaexporcts = new HashMap<String, Object>();
		vaexporcts.put("活动参与情况", "TeacherANDActivity");
		vaexporcts.put("活动缺席情况", "UnjoinedRecord");
//		vaexporcts.put("规定性活动", "ActType1");
	}

	public static Map<String,String> getTeachingModule() {
		Map<String,String> teachingModule=new HashMap<String, String>();
		teachingModule.put("课堂教学模块", "classTeaching");
		teachingModule.put("学位论文指导模块", "degreeThesisGuidance");
		teachingModule.put("教学竞赛模块", "teachingCompetition");
		teachingModule.put("教学能力提升模块", "teachingAbilityImprove");
		teachingModule.put("实践创新指导模块", "practiceInnovationGuidance");
		teachingModule.put("学生竞赛指导模块", "studentCompetitionGuidance");
		teachingModule.put("参与学生活动模块", "joinStudentActivity");
		teachingModule.put("本科生导师指导模块", "undergraduateTutorGuidance");
		teachingModule.put("校外活动指导模块", "offCampusPracticeGuidance");
		teachingModule.put("教学名师与教学团队", "famousTeacherTeam");
		teachingModule.put("教学研究项目", "teachingResearch");
		teachingModule.put("教学论文", "teachingPaper");
		teachingModule.put("教学成果奖", "teachingAchievement");
		teachingModule.put("教材建设", "textbookConstruction");
		teachingModule.put("精品课程建设", "fineCourseConstruction");
		teachingModule.put("专业项目申报", "professionalProjectDeclare");
		teachingModule.put("企业工作站和联合人才培养基地", "enterpriseWorkstation");
		teachingModule.put("暑期课程与国际课程建设", "summerInternationalCourse");
		return teachingModule;
	}
	
	
	
	
	
	
	
}














