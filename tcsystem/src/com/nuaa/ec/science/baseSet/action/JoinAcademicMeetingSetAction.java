package com.nuaa.ec.science.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.JoinAcademicMeetingScoreDAO;
import com.nuaa.ec.dao.MeetingPlaceDAO;
import com.nuaa.ec.dao.MeetingTypeDAO;
import com.nuaa.ec.dao.PaperRetrievalConditionDAO;
import com.nuaa.ec.model.JoinAcademicMeetingScore;
import com.nuaa.ec.model.MeetingPlace;
import com.nuaa.ec.model.MeetingType;
import com.nuaa.ec.model.PaperRetrievalCondition;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class JoinAcademicMeetingSetAction implements RequestAware {

	private Map<String, Object> request;
	private Integer operstatus;
	
	private MeetingPlace meetingplace;
	private MeetingType meetingtype;
	private PaperRetrievalCondition paperretrieval;
	private JoinAcademicMeetingScore joinmeetingscore;
	
	private MeetingPlaceDAO meetingplacedao = new MeetingPlaceDAO();
	private MeetingTypeDAO meetingtpdao = new MeetingTypeDAO();
	private PaperRetrievalConditionDAO paprerevaldao  = new PaperRetrievalConditionDAO();
	private JoinAcademicMeetingScoreDAO joinmeetingdao = new  JoinAcademicMeetingScoreDAO();
	
	private PrimaryKMaker pkmk = new  PrimaryKMaker();
	//default method
	public String execute(){
		return "success";
	}
	//TODO: 会议类型设置 // meeting Type - Set
	public String getAcademicMeetingTypeINF() throws Exception{
		request.put("meetingtypeli", meetingtpdao.findAll());
		return "success";
	}
	public String addAcademicMeetingType() throws Exception {
		Transaction tx = null;
		try {
			meetingtype.setMeetingTypeId(pkmk.mkpk(EntityUtil.getPkColumnName(MeetingType.class), EntityUtil.getTableName(MeetingType.class), "MT"));
			meetingtype.setSpareTire("1");
			meetingtpdao.save(meetingtype);
			tx = meetingtpdao.getSession().beginTransaction();
			tx.commit();
			getAcademicMeetingTypeINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	} 
	public void updateAcademicMeetingType() throws Exception {
		Transaction tx = null;
		try {
			meetingtype.setSpareTire("1");
			meetingtpdao.merge(meetingtype);
			tx = meetingtpdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handl exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteAcademicMeetingType() throws Exception {
		Transaction tx = null;
		try {
			meetingtype.setSpareTire("0");
			meetingtpdao.merge(meetingtype);
			tx = meetingtpdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handl exception
			tx.rollback();
			throw e;
		}
	}
	//TODO: 会议地点设置 // meeting place - Set
	public String getAcademicMeetingPlaceINF() throws Exception {
		request.put("meetingplaceli", meetingplacedao.findAll());
		return "success";
	}
	public String addAcademicMeetingPlace() throws Exception {
		Transaction tx = null;
		try {
			meetingplace.setSpareTire("1");
			meetingplace.setMeetingPlaceId(pkmk.mkpk(EntityUtil.getPkColumnName(MeetingPlace.class), EntityUtil.getTableName(MeetingPlace.class), "MP"));
			meetingplacedao.save(meetingplace);
			tx = meetingplacedao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			getAcademicMeetingPlaceINF();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	public void updateAcademicMeetingPlace() throws Exception {
		Transaction tx = null;
		try {
			meetingplace.setSpareTire("1");
			meetingplacedao.merge(meetingplace);
			tx = meetingplacedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteAcademicMeetingPlace() throws Exception {
		Transaction tx = null;
		try {
			meetingplace.setSpareTire("0");
			meetingplacedao.merge(meetingplace);
			tx = meetingplacedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO: 会议论文检索设置 // meeting -paper - retrieval - Set
	public String  getPaperRetrievalConditionINF() throws Exception {
		request.put("meetingpaperretrievalli", paprerevaldao.findAll());
		return "success";
	}
	public String addPaperRetrievalCondition() throws Exception {
		Transaction tx = null;
		try {
			paperretrieval.setSpareTire("1");
			paperretrieval.setPrconditionId(pkmk.mkpk(EntityUtil.getPkColumnName(PaperRetrievalCondition.class), EntityUtil.getTableName(PaperRetrievalCondition.class), "MPR"));
			paprerevaldao.save(paperretrieval);
			tx = paprerevaldao.getSession().beginTransaction();
			tx.commit();
			getPaperRetrievalConditionINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	public void updatePaperRetrievalCondition() throws Exception {
		Transaction tx = null;
		try {
			paperretrieval.setSpareTire("1");
			paprerevaldao.merge(paperretrieval);
			tx = paprerevaldao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deletePaperRetrievalCondition() throws Exception {
		Transaction tx = null;
		try {
			paperretrieval.setSpareTire("0");
			paprerevaldao.merge(paperretrieval);
			tx = paprerevaldao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO:  参与会议评分设置 //join- meeting -score - Set
	//Getter and Setter
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public MeetingPlace getMeetingplace() {
		return meetingplace;
	}

	public void setMeetingplace(MeetingPlace meetingplace) {
		this.meetingplace = meetingplace;
	}

	public MeetingType getMeetingtype() {
		return meetingtype;
	}

	public void setMeetingtype(MeetingType meetingtype) {
		this.meetingtype = meetingtype;
	}

	public PaperRetrievalCondition getPaperretrieval() {
		return paperretrieval;
	}

	public void setPaperretrieval(PaperRetrievalCondition paperretrieval) {
		this.paperretrieval = paperretrieval;
	}

	public JoinAcademicMeetingScore getJoinmeetingscore() {
		return joinmeetingscore;
	}

	public void setJoinmeetingscore(JoinAcademicMeetingScore joinmeetingscore) {
		this.joinmeetingscore = joinmeetingscore;
	}
	
	public Integer getOperstatus() {
		return operstatus;
	}
	
	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}
}
