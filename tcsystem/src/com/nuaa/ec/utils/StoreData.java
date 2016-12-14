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
	private static Map<String, String> filenameExported = new HashMap<String, String>();
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

	public static Map<String, String> getFilenameExported() {
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

	public static Map<String, Object> getVaexporcts() {
		return vaexporcts;
	}

	public static void setVaexporcts() {
		vaexporcts = new HashMap<String, Object>();
		
	}
	
	
	
	
	
	
	
}














