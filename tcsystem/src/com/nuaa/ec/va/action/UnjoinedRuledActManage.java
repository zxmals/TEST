package com.nuaa.ec.va.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.VaunJoinRecordDAO;
import com.nuaa.ec.model.Teacher;

public class UnjoinedRuledActManage implements SessionAware {

	private String foredate;
	private String afterdate;
	private Map<String, Object> session;
	private VaunJoinRecordDAO vaunjoindao = new VaunJoinRecordDAO();

	// default method
	public String execute() {
		return "success";
	}

	/***
	 * user sql-select get unjoined - ruled Act with reason etc . . .
	 * 
	 * @return
	 */
	public String getUnjoinedRuledAct() {
		try {
			ServletActionContext.getRequest().setAttribute("vaunjoinedli", vaunjoindao.findByTimeLimted(foredate, afterdate, ((Teacher) session.get("teacher")).getTeacherId()));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}

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

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
