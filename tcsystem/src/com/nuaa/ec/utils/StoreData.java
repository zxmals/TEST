package com.nuaa.ec.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nuaa.ec.model.ResearchLab;
import com.opensymphony.xwork2.ActionContext;

public class StoreData {

	private static Map<String, Object> teachertranslate = new HashMap<String, Object>();
	private static List<ResearchLab> researchLabList=new ArrayList<ResearchLab>();

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
}
