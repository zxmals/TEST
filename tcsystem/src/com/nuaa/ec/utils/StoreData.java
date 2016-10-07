package com.nuaa.ec.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nuaa.ec.model.Department;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.Tfterm;
import com.opensymphony.xwork2.ActionContext;

public class StoreData {

	private static Map<String, Object> teachertranslate = new HashMap<String, Object>();
	private static List<ResearchLab> researchLabList=new ArrayList<ResearchLab>();
	private static List<Tfterm> TftermList=new ArrayList<Tfterm>();
	private static List<Department> departmentList=new ArrayList<Department>();
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
}
