package com.nuaa.ec.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.ScientificResearchSummaryDataDao;

public class ScienResearchDataSummaryAction_person implements RequestAware{
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
	public String execute() throws Exception{
		return "success";
	}
	private String foredate;
	private String afterdate;
	private String teacherId;
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
	}

}
