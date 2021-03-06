package com.nuaa.ec.ScientificResearchPerformanceSet.Action;

import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ExpertTypeDAO;
import com.nuaa.ec.dao.InvitedExpertsSpeechDAO;
import com.nuaa.ec.dao.InvitedExpertsSpeechScoreDAO;
import com.nuaa.ec.dao.NationalityDAO;
import com.nuaa.ec.dao.SelfUndertakeTaskDAO;
import com.nuaa.ec.dao.TeacherAndinvitedExpertsSpeechDAO;
import com.nuaa.ec.model.ExpertType;
import com.nuaa.ec.model.InvitedExpertsSpeech;
import com.nuaa.ec.model.InvitedExpertsSpeechScore;
import com.nuaa.ec.model.Nationality;
import com.nuaa.ec.model.SelfUndertakeTask;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.TeacherAndinvitedExpertsSpeech;
import com.nuaa.ec.utils.EntityUtil;

public class abmininviteexpertspeechAction implements RequestAware, SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foredate;
	private String afterdate;
	private Integer operstatus;
	private InvitedExpertsSpeech invitespeech;
	private Nationality nation;
	private ExpertType expertType;
	private SelfUndertakeTask selftask;
	private TeacherAndinvitedExpertsSpeech teacherandinvitespeech;
	
	
	private InvitedExpertsSpeechDAO invitespeechdao = new InvitedExpertsSpeechDAO();
	private InvitedExpertsSpeechScoreDAO invitescoredao = new InvitedExpertsSpeechScoreDAO();
	private NationalityDAO nationdao = new NationalityDAO();
	private ExpertTypeDAO expertTypedao = new ExpertTypeDAO();
	private SelfUndertakeTaskDAO selftaskdao = new SelfUndertakeTaskDAO();
	private TeacherAndinvitedExpertsSpeechDAO teacherandinvitespeechdao = new TeacherAndinvitedExpertsSpeechDAO();

	//default method
	public String excute(){
		return "success";
	}
	
	//TODO: 讲学设置
	public String gainAllspeech(){
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
		request.put("invitespeech", invitespeechdao.findPageing((pagenum-1)*limitrow,limitrow,EntityUtil.generateQueryCondition(foredate, afterdate, "speechDate")));
		request.put("nationality", nationdao.findAll());
		request.put("selfdown", selftaskdao.findAll());
		request.put("expertType", expertTypedao.findAll());
		int li = invitespeechdao.getRows(EntityUtil.generateQueryCondition(foredate, afterdate, "speechDate"));
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
	
	public void updateInviteSpeech() throws Exception{
		Transaction tx = null;
		try {
			invitespeech.setSpareTire("1");
			invitespeech.setExpertType(expertTypedao.findById(expertType.getExpertTypeId()));
			invitespeech.setNationality(nationdao.findById(nation.getCountryId()));
			invitespeech.setResearchLabId(EntityUtil.findReasearchLabIdByTeacherId(((Teacher)session.get("teacher")).getTeacherId(), invitescoredao.getSession()));
			InvitedExpertsSpeechScore iespeechcore = invitescoredao.checkexist(expertType);
			if(iespeechcore!=null){
				invitespeechdao.merge(invitespeech);
				teacherandinvitespeechdao.updateRefSpeech(invitespeech, iespeechcore, iespeechcore.getScore());
				ServletActionContext.getResponse().getWriter().write("succ");
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("该专家类型无对应评分项，请联系管理");
				return;
			}
			tx = invitespeechdao.getSession().beginTransaction();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}	
	
	public void deleteInviteSpeech()throws Exception{
		Transaction tx = null;
		try {
			invitespeechdao.deleteINvites(invitespeech.getIespeechId());
			teacherandinvitespeechdao.deleteRefie(invitespeech);
			tx = invitescoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	public void getMember() throws Exception{
		try {
			JSONArray jary = JSONArray.fromObject(invitespeechdao.findMembersano(invitespeech));
			ServletActionContext.getResponse().setCharacterEncoding("utf-8");
			ServletActionContext.getResponse().getWriter().write(jary.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	
	//TODO:个人设置
	public void deleteMember() throws Exception{
		Transaction tx = null;
		try {
			this.setInvitespeech(invitespeechdao.findById(invitespeech.getIespeechId()));
			if(!invitespeech.getChargePersonId().equals(teacherandinvitespeech.getTeacher().getTeacherId())){
				teacherandinvitespeechdao.quitproject(invitespeech, teacherandinvitespeech.getTeacher());
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write("不能移除负责人");
				return ;
			}
			tx = teacherandinvitespeechdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			tx.rollback();
			throw e;
		}
	}
	
	//Getter & Setter
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
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

	public InvitedExpertsSpeech getInvitespeech() {
		return invitespeech;
	}

	public void setInvitespeech(InvitedExpertsSpeech invitespeech) {
		this.invitespeech = invitespeech;
	}

	public Nationality getNation() {
		return nation;
	}

	public void setNation(Nationality nation) {
		this.nation = nation;
	}

	public ExpertType getExpertType() {
		return expertType;
	}

	public SelfUndertakeTask getSelftask() {
		return selftask;
	}

	public void setSelftask(SelfUndertakeTask selftask) {
		this.selftask = selftask;
	}

	public TeacherAndinvitedExpertsSpeech getTeacherandinvitespeech() {
		return teacherandinvitespeech;
	}

	public void setTeacherandinvitespeech(
			TeacherAndinvitedExpertsSpeech teacherandinvitespeech) {
		this.teacherandinvitespeech = teacherandinvitespeech;
	}

	public void setExpertType(ExpertType expertType) {
		this.expertType = expertType;
	}

}
