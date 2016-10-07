package com.nuaa.ec.ScientificResearchPerformanceSet.Action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.RewardLevelDAO;
import com.nuaa.ec.dao.RewardTypeDAO;
import com.nuaa.ec.dao.ScientificResearchRewardDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchRewardDAO;
import com.nuaa.ec.model.RewardLevel;
import com.nuaa.ec.model.RewardType;
import com.nuaa.ec.model.ScientificResearchReward;
import com.nuaa.ec.model.TeacherAndscientificResearchReward;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class scienceResearchRewardAction implements RequestAware, SessionAware {

	private Map<String, Object> request;
	private Map<String, Object> session;
	private String foredate;
	private String afterdate;
	private Integer operstatus;

	private ScientificResearchReward scienceReward;
	private TeacherAndscientificResearchReward teacherandsr;
	private RewardLevel rewardlevel;
	private RewardType rewardtype;
	
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private ScientificResearchRewardDAO sciencerewarddao = new ScientificResearchRewardDAO();
	private TeacherAndscientificResearchRewardDAO teacherandsrdao = new TeacherAndscientificResearchRewardDAO();
	private RewardLevelDAO rewardleveldao = new RewardLevelDAO();
	private RewardTypeDAO rewardtypedao = new RewardTypeDAO();
	// default method
	public String execute() {
		return "success";
	}

	//TODO: 科研奖励设置
	public String getscienceReward() throws Exception{
		int pagenum = 1;
		int limitrow = 5;
		String limit = (String)ServletActionContext.getRequest().getParameter("limit");
		String pagenumber = (String)ServletActionContext.getRequest().getParameter("pagenum");
		if(pagenumber!=null){
			pagenum = !"".equals(pagenumber.trim())?Integer.parseInt(pagenumber):1;
		}
		if(limit!=null){
			limitrow = !"".equals(limit.trim())?Integer.parseInt(limit):5;
		}
		request.put("sciencerewards", sciencerewarddao.findAllpaging((pagenum-1)*limitrow,limitrow,EntityUtil.generateQueryCondition(foredate, afterdate, "aw.publishDate")));
//		request.put("publishclubli", publishdao.findAll());
//		request.put("wordnum", wordnumdao.findAll());
//		request.put("selfdown", selftaskdao.findAll());
		int li = sciencerewarddao.getRows(EntityUtil.generateQueryCondition(foredate, afterdate, "aw.publishDate"));
		int sumpage = 1;
		if(li%limitrow==0){
			sumpage = li/limitrow;
		}else{
			sumpage = li/limitrow+1;
		}
		request.put("sumrow",li);
		request.put("sumpage",sumpage);
		if(pagenum<sumpage){
			request.put("nextpage", 1+pagenum);
		}else{
			request.put("nextpage",pagenum);
		}
		if(pagenum>1){
			request.put("prepage", pagenum-1);
		}else{
			request.put("prepage",1);
		}
		request.put("pagenum", pagenum);
		return "success";
	}
	
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
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

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

	public ScientificResearchReward getSciencereward() {
		return scienceReward;
	}

	public void setSciencereward(ScientificResearchReward sciencereward) {
		this.scienceReward = sciencereward;
	}

	public TeacherAndscientificResearchReward getTeacherandsr() {
		return teacherandsr;
	}

	public void setTeacherandsr(TeacherAndscientificResearchReward teacherandsr) {
		this.teacherandsr = teacherandsr;
	}

	public RewardLevel getRewardlevel() {
		return rewardlevel;
	}

	public void setRewardlevel(RewardLevel rewardlevel) {
		this.rewardlevel = rewardlevel;
	}

	public RewardType getRewardtype() {
		return rewardtype;
	}

	public void setRewardtype(RewardType rewardtype) {
		this.rewardtype = rewardtype;
	}
	
}
