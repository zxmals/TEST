package com.nuaa.ec.science.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.SelectedTalentProjectScoreDAO;
import com.nuaa.ec.dao.TalentProjectDAO;
import com.nuaa.ec.model.SelectedTalentProjectScore;
import com.nuaa.ec.model.TalentProject;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class SelectedTalentProjectSetAction implements RequestAware {

	private Map<String, Object> request;
	private Integer operstatus;

	private SelectedTalentProjectScore selectedproscore;
	private TalentProject talentproject;

	private SelectedTalentProjectScoreDAO selectedproscoredao = new SelectedTalentProjectScoreDAO();
	private TalentProjectDAO talentprojectdao = new TalentProjectDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	
	//人才工程设置//  Talent-Project Set
	public String getSelectedTalentProjectINF() throws Exception {
		request.put("talentproli", talentprojectdao.findAll());
		return "success";
	}
	public String addSelectedTalentProject() throws Exception {
		Transaction tx = null;
		try {
			talentproject.setSpareTire("1");
			talentproject.setTalentProjectId(pkmk.mkpk(EntityUtil.getPkColumnName(TalentProject.class), EntityUtil.getTableName(TalentProject.class), "STP"));
			talentprojectdao.save(talentproject);
			tx = talentprojectdao.getSession().beginTransaction();
			tx.commit();
			getSelectedTalentProjectINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	public void updateSelectedTalentProject() throws Exception {
		Transaction tx = null;
		try {
			talentproject.setSpareTire("1");
			talentprojectdao.merge(talentproject);
			tx = talentprojectdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteSelectedTalentProject() throws Exception {
		Transaction tx = null;
		try {
			talentproject.setSpareTire("0");
			talentprojectdao.merge(talentproject);
			tx = talentprojectdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//人才工程评分设置//  Talent-Project-Score Set
	public String getSelectedTalentProjectScoreINF() throws Exception {
		request.put("talentproscoreli",selectedproscoredao.findAll());
		getSelectedTalentProjectINF();
		return "success";
	}
	public String addSelectedTalentProjectScore() throws Exception{
		Transaction tx = null;
		try {
			selectedproscore.setSpareTire("1");
			selectedproscore.setTalentProject(talentprojectdao.findById(talentproject.getTalentProjectId()));
			selectedproscore.setStpscoreId(pkmk.mkpk(EntityUtil.getPkColumnName(SelectedTalentProjectScore.class), EntityUtil.getTableName(SelectedTalentProjectScore.class), "STPS"));
			selectedproscoredao.save(selectedproscore);
			tx = selectedproscoredao.getSession().beginTransaction();
			tx.commit();
			getSelectedTalentProjectScoreINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	public void updateSelectedTalentProjectScore() throws Exception {
		Transaction tx = null;
		try {
			selectedproscore.setSpareTire("1");
			selectedproscore.setTalentProject(talentprojectdao.findById(talentproject.getTalentProjectId()));
			selectedproscoredao.merge(selectedproscore);
			tx = selectedproscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteSelectedTalentProjectScore() throws Exception {
		Transaction tx = null;
		try {
			selectedproscore.setSpareTire("0");
			selectedproscore.setTalentProject(talentprojectdao.findById(talentproject.getTalentProjectId()));
			selectedproscoredao.merge(selectedproscore);
			tx = selectedproscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//Getter & Setter
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

	public SelectedTalentProjectScore getSelectedproscore() {
		return selectedproscore;
	}

	public void setSelectedproscore(SelectedTalentProjectScore selectedproscore) {
		this.selectedproscore = selectedproscore;
	}

	public TalentProject getTalentproject() {
		return talentproject;
	}

	public void setTalentproject(TalentProject talentproject) {
		this.talentproject = talentproject;
	}

}
