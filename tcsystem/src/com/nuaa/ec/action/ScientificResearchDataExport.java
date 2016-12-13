package com.nuaa.ec.action;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.ResearchLabDAO;
import com.nuaa.ec.dao.TeacherAndacademicWorkDAO;
import com.nuaa.ec.dao.TeacherAndinvitedExpertsSpeechDAO;
import com.nuaa.ec.dao.TeacherAndjoinAcademicMeetingDAO;
import com.nuaa.ec.dao.TeacherAndmainUndertakeAcademicMeetingDAO;
import com.nuaa.ec.dao.TeacherAndperiodicalDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchProjectDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchRewardDAO;
import com.nuaa.ec.dao.TeacherAndselectedTalentProjectDAO;
import com.nuaa.ec.model.ResearchLab;
import com.nuaa.ec.model.TeacherAndmainUndertakeAcademicMeeting;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.StoreData;

public class ScientificResearchDataExport implements RequestAware, SessionAware {

	private Map<String, Object> session;
	private Map<String, Object> request;
	private String foredate;
	private String afterdate;
	private ResearchLab research;
	private String entitys;
	private Map<String,String> filenameExported=StoreData.getFilenameExported();
	private ResearchLabDAO researchdao = new ResearchLabDAO();
	private TeacherAndperiodicalDAO tapdao = new TeacherAndperiodicalDAO();
	private TeacherAndscientificResearchRewardDAO techrAndScienReschRewdDAO=new TeacherAndscientificResearchRewardDAO();
	private TeacherAndacademicWorkDAO academicdao = new TeacherAndacademicWorkDAO();
	private TeacherAndjoinAcademicMeetingDAO TAJoinAkdmkMetingDAO=new TeacherAndjoinAcademicMeetingDAO();
	private TeacherAndselectedTalentProjectDAO TASlktTalntProjktDAO=new TeacherAndselectedTalentProjectDAO();
	private TeacherAndinvitedExpertsSpeechDAO TAInvtEksptSpechDAO = new TeacherAndinvitedExpertsSpeechDAO();
	private TeacherAndmainUndertakeAcademicMeetingDAO TAMnUndrtkAkdmkMetingDAO=new TeacherAndmainUndertakeAcademicMeetingDAO();
	private TeacherAndscientificResearchProjectDAO TAScienReschProjktDAO = new TeacherAndscientificResearchProjectDAO();
	//default method
	public String execute(){
		return "success";
	}
	
	public void generateExportData() throws Exception{
		try {
			ByteArrayOutputStream baos = null;
			if("PeriodicalPapers".equals(entitys.trim())){
				baos = tapdao.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "pp.year")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}else if("ScientificResearchReward".equals(entitys.trim())){
				baos = techrAndScienReschRewdDAO.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "TASRR.rewardDate")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}else if("AcademicWork".equals(entitys.trim())){
				baos = academicdao.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "TAAW.academicWork.publishDate")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}else if("JoinAcademicMeeting".equals(entitys.trim())){
				baos = TAJoinAkdmkMetingDAO.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "TAJAM.joinAcademicMeeting.meetingdate")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}else if("TalentProject".equals(entitys.trim())){
				baos = TASlktTalntProjktDAO.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "TASTP.tpselectedYear")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}else if("InvitedExpertsSpeech".equals(entitys.trim())){
				baos = TAInvtEksptSpechDAO.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "TAIES.invitedExpertsSpeech.speechDate")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}else if("MainUndertakeAcademicMeeting".equals(entitys.trim())){
				baos = TAMnUndrtkAkdmkMetingDAO.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "TAMAM.mainUndertakeAcademicMeeting.meetingdate")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}else if("ScientificResearchProject".equals(entitys.trim())){
				baos = TAScienReschProjktDAO.findwithexport(research, 
						EntityUtil.generateQueryCondition(foredate, afterdate, "TASRP.scientificResearchProject.admitedProjectYear")
						, (researchdao.findById(research.getResearchLabId())).getResearchLabName(), foredate, afterdate);
			}
			if(baos!=null){
				HttpServletResponse resp = ServletActionContext.getResponse();
				OutputStream out = resp.getOutputStream();
				resp.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(filenameExported.get(entitys.trim()), "UTF-8")+".xls");
				byte[] bt = baos.toByteArray();
				out.write(bt,0,bt.length);
				out.flush();
				out.close();
			}else{
				ServletActionContext.getResponse().setCharacterEncoding("utf-8");
				ServletActionContext.getResponse().getWriter().write(" 该区间暂时没有数据..... 请返回到上一页 ");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
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

	public ResearchLab getResearch() {
		return research;
	}

	public void setResearch(ResearchLab research) {
		this.research = research;
	}

	public String getEntitys() {
		return entitys;
	}

	public void setEntitys(String entitys) {
		this.entitys = entitys;
	}

}
