package com.nuaa.ec.science.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.ExpertTypeDAO;
import com.nuaa.ec.dao.InvitedExpertsSpeechScoreDAO;
import com.nuaa.ec.model.ExpertType;
import com.nuaa.ec.model.InvitedExpertsSpeechScore;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class InviteExpertSpeechSetAction implements RequestAware {

	private Map<String, Object> request;
	private Integer operstatus;

	private ExpertType expertTyp;
	private InvitedExpertsSpeechScore invitespeechscore;

	private ExpertTypeDAO expertTypdao = new ExpertTypeDAO();
	private InvitedExpertsSpeechScoreDAO invitespeechscoredao = new InvitedExpertsSpeechScoreDAO();

	private PrimaryKMaker pkmk = new PrimaryKMaker();

	// default method
	public String execute() {
		return "success";
	}

	//TODO: 专家类别设置 // expert Type -Set
	public String  getExpertTypeINF() throws Exception{
		request.put("expertTypeli", expertTypdao.findAll());
		return "success";
	}
	
	public String addExpertType() throws Exception {
		Transaction tx = null;
		try {
			expertTyp.setSpareTire("1");
			expertTyp.setExpertTypeId(pkmk.mkpk(EntityUtil.getPkColumnName(ExpertType.class), EntityUtil.getTableName(ExpertType.class), "ET"));
			expertTypdao.save(expertTyp);
			tx = expertTypdao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			getExpertTypeINF();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	
	public void updateExpertType() throws Exception {
		Transaction tx = null;
		try {
			expertTyp.setSpareTire("1");
			expertTypdao.merge(expertTyp);
			tx = expertTypdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteExpertType() throws Exception {
		Transaction tx = null;
		try {
			expertTyp.setSpareTire("0");
			expertTypdao.merge(expertTyp);
			tx = expertTypdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO: 邀请专家讲学评分设置 // Invite-expert-speech Score -Set
	public String getInviteExpertSpeechScoreINF() throws Exception{
		request.put("invitespeechscoreli", invitespeechscoredao.findAll());
		getExpertTypeINF();
		return "success";
	}
	public String addInviteExpertSpeechScore() throws Exception{
		Transaction tx = null;
		try {
			invitespeechscore.setSpareTire("1");
			invitespeechscore.setExpertType(expertTypdao.findById(expertTyp.getExpertTypeId()));
			invitespeechscore.setIesscoreId(pkmk.mkpk(EntityUtil.getPkColumnName(InvitedExpertsSpeechScore.class), EntityUtil.getTableName(InvitedExpertsSpeechScore.class), "IESS"));
			invitespeechscoredao.save(invitespeechscore);
			tx = invitespeechscoredao.getSession().beginTransaction();
			tx.commit();
			getInviteExpertSpeechScoreINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	public void updateInviteExpertSpeechScore() throws Exception {
		Transaction tx = null;
		try {
			invitespeechscore.setSpareTire("1");
			invitespeechscore.setExpertType(expertTypdao.findById(expertTyp.getExpertTypeId()));
			invitespeechscoredao.merge(invitespeechscore);
			tx = invitespeechscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteInviteExpertSpeechScore() throws Exception {
		Transaction tx = null;
		try {
			invitespeechscore.setSpareTire("0");
			invitespeechscore.setExpertType(expertTypdao.findById(expertTyp.getExpertTypeId()));
			invitespeechscoredao.merge(invitespeechscore);
			tx = invitespeechscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

	public ExpertType getExpertTyp() {
		return expertTyp;
	}

	public void setExpertTyp(ExpertType expertTyp) {
		this.expertTyp = expertTyp;
	}

	public InvitedExpertsSpeechScore getInvitespeechscore() {
		return invitespeechscore;
	}

	public void setInvitespeechscore(InvitedExpertsSpeechScore invitespeechscore) {
		this.invitespeechscore = invitespeechscore;
	}

}
