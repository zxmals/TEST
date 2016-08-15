package com.nuaa.ec.va.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.BaseHibernateDAO;
import com.nuaa.ec.dao.VaunJoinRecordDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.VaunJoinRecord;
import com.nuaa.ec.utils.PrimaryKMaker;

public class UnjoinedRuledActManage implements SessionAware {

	private String foredate;
	private String afterdate;
	private String submitstatus;
	private VaunJoinRecord unjoinact;
	private Map<String, Object> session;
	private VaunJoinRecordDAO vaunjoindao = new VaunJoinRecordDAO();
	private PrimaryKMaker pk = new PrimaryKMaker();

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

	public String addOrUpdateUNjoinreason(){
		try {
			List<VaunJoinRecord> unjoinli = vaunjoindao.findByActId(unjoinact.getActId());
			if(unjoinli.size()!=0){
				unjoinact.setAsparetire("0");
				unjoinact.setSparetire("1");
				unjoinact.setTeacherId(((Teacher)session.get("teacher")).getTeacherId());
				unjoinact.setResultscore(0.0);
				vaunjoindao.merge(unjoinact);
				this.setSubmitstatus("提交成功");
			}else{
				unjoinact.setAsparetire("0");
				unjoinact.setSparetire("1");
				unjoinact.setTeacherId(((Teacher)session.get("teacher")).getTeacherId());
				unjoinact.setUnjoinId(pk.mkpk("unjoinID", "VAUnjoinRecord", "UNJ"));
				unjoinact.setResultscore(0.0);
				vaunjoindao.save(unjoinact);
				this.setSubmitstatus("提交成功");
			}
			vaunjoindao.getSession().beginTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setSubmitstatus("提交失败");
			vaunjoindao.getSession().beginTransaction().rollback();
		}finally{
			vaunjoindao.closeSession();
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

	public VaunJoinRecord getUnjoinact() {
		return unjoinact;
	}

	public void setUnjoinact(VaunJoinRecord unjoinact) {
		this.unjoinact = unjoinact;
	}

	public String getSubmitstatus() {
		return submitstatus;
	}

	public void setSubmitstatus(String submitstatus) {
		this.submitstatus = submitstatus;
	}
	
}
