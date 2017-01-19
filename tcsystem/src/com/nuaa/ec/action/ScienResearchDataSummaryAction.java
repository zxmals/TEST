package com.nuaa.ec.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.summaryDataDao.ScientificResearchSummaryDataDao;
import com.nuaa.ec.summaryDataModel.ScientificResearchModuleData;
import com.nuaa.ec.utils.StoreData;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 科研模块的数据汇总
 * 
 * @author RayHauton
 *
 */

public class ScienResearchDataSummaryAction extends ActionSupport implements
		RequestAware {
	private static List<String> researchLabIds = new ArrayList<String>();
	static {
		List<ResearchLab> researchLabs = StoreData.getResearchLabList();
		for (ResearchLab reschLab : researchLabs) {
			researchLabIds.add(reschLab.getResearchLabId());
		}
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 按照研究所汇总数据
	 */
	public String getScienResrchSummaryDateByReschlab() throws Exception {
		/*
		 * check researchLabId if researchLabId equals allReseachLab,using
		 * reserchLabIds to query; else using researchLabId from font view;
		 */
		List<ScientificResearchModuleData> scienReschModuleDataList = new ArrayList<ScientificResearchModuleData>(
				4);
		if (researchLabId.trim().equals("allResearchLab")) {
			scienReschModuleDataList = ScientificResearchSummaryDataDao.packageScienReschSummaryData(researchLabIds, foredate, afterdate);
		}else{
			List<String> researchLabIds = new ArrayList<String>();
			researchLabIds.add(researchLabId);
			scienReschModuleDataList = ScientificResearchSummaryDataDao.packageScienReschSummaryData(researchLabIds, foredate, afterdate);
		}
		this.request.put("scienReschModlSumryData", scienReschModuleDataList);
		return "success";
	}

	@Override
	public String execute() throws Exception {
		return "success";
	}

	private String foredate;
	private String afterdate;
	private String researchLabId;
	private Map<String, Object> request;

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

	public String getResearchLabId() {
		return researchLabId;
	}

	public void setResearchLabId(String researchLabId) {
		this.researchLabId = researchLabId;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

}
