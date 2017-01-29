package com.nuaa.ec.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.dao.ScientificResearchSummaryDataDao;
import com.nuaa.ec.summaryDataModel.ScientificResearchModuleData;
import com.nuaa.ec.utils.StoreData;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;

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
	 * 导出科研模块的 汇总数据
	 */
	public void getSummaryDataExcel() throws Exception {
		ByteArrayOutputStream baos = ScientificResearchSummaryDataDao
				.getExcelOutputStream(
						(String) session.get("researchLabId_summary"),
						(String) session.get("foredate_summary"),
						(String) session.put("afterdate_summary", afterdate));
		// byte[] fileContent = baos.toByteArray();
		HttpServletResponse resp = ServletActionContext.getResponse();
		OutputStream out = resp.getOutputStream();
		resp.setHeader("Content-Disposition", "attachment;filename="
				+ URLEncoder.encode("科研数据汇总", "UTF-8") + ".xls");
		byte[] bt = baos.toByteArray();
		out.write(bt, 0, bt.length);
		out.flush();
		out.close();
		// new ByteArrayInputStream(fileContent)
	}

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
			scienReschModuleDataList = ScientificResearchSummaryDataDao
					.packageScienReschSummaryData(researchLabIds, foredate,
							afterdate);
		} else {
			List<String> researchLabIds = new ArrayList<String>();
			researchLabIds.add(researchLabId);
			scienReschModuleDataList = ScientificResearchSummaryDataDao
					.packageScienReschSummaryData(researchLabIds, foredate,
							afterdate);
		}
		this.request.put("scienReschModlSumryData", scienReschModuleDataList);
		/**
		 * 用于导出汇总数据时候不用重新查询，直接从缓存中取出数据，导出以后 清掉session对应的项
		 * session保存的总是最近一次查询的汇总结果
		 */
		session.put("scienReschModlSumryData", scienReschModuleDataList);
		return "success";
	}

	@Override
	public String execute() throws Exception {
		return "success";
	}

	private String foredate;
	private String afterdate;
	private String researchLabId;
	private String teacherId;
	private Map<String, Object> session = ActionContext.getContext()
			.getSession();
	private Map<String, Object> request;

	public String getForedate() {
		return foredate;
	}

	public void setForedate(String foredate) {
		this.foredate = foredate;
		session.put("foredate_summary", foredate);
	}

	public String getAfterdate() {
		return afterdate;
	}

	public void setAfterdate(String afterdate) {
		this.afterdate = afterdate;
		session.put("afterdate_summary", afterdate);
	}

	public String getResearchLabId() {
		return researchLabId;
	}

	public void setResearchLabId(String researchLabId) {
		this.researchLabId = researchLabId;
		session.put("researchLabId_summary", researchLabId);
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
