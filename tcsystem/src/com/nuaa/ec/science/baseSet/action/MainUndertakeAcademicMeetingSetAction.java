package com.nuaa.ec.science.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.MainUndertakeAcademicMeetingPlaceDAO;
import com.nuaa.ec.dao.MainUndertakeAcademicMeetingScoreDAO;
import com.nuaa.ec.dao.MainUndertakeAcademicMeetingTypeDAO;
import com.nuaa.ec.model.MainUndertakeAcademicMeetingPlace;
import com.nuaa.ec.model.MainUndertakeAcademicMeetingScore;
import com.nuaa.ec.model.MainUndertakeAcademicMeetingType;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;

public class MainUndertakeAcademicMeetingSetAction implements RequestAware {

	private Map<String, Object> request;
	private Integer operstatus;

	private MainUndertakeAcademicMeetingPlace mainmeetingplace;
	private MainUndertakeAcademicMeetingType mainmeetingtype;
	private MainUndertakeAcademicMeetingScore mainmeetingscore;

	private MainUndertakeAcademicMeetingPlaceDAO mainmeetingplacedao = new MainUndertakeAcademicMeetingPlaceDAO();
	private MainUndertakeAcademicMeetingTypeDAO mainmeetingtypedao = new MainUndertakeAcademicMeetingTypeDAO();
	private MainUndertakeAcademicMeetingScoreDAO mainmeetingscoredao = new MainUndertakeAcademicMeetingScoreDAO();

	private PrimaryKMaker pkmk = new PrimaryKMaker();

	// default method
	public String execute() {
		return "success";
	}
	//主承办学术会议会议地点设置 // Main-Undertake-Academic-Meeting-Place   Set
	public String getMeetingPlaceINF() throws Exception {
		request.put("mainmeetplaceli", mainmeetingplacedao.findAll());
		return "success";
	}
	public String addMeetingPlace() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingplace.setSpareTire("1");
			mainmeetingplace.setAcaMeetPlaceId(pkmk.mkpk(EntityUtil.getPkColumnName(MainUndertakeAcademicMeetingPlace.class), EntityUtil.getTableName(MainUndertakeAcademicMeetingPlace.class), "MACAMP"));
			mainmeetingplacedao.save(mainmeetingplace);
			tx = mainmeetingplacedao.getSession().beginTransaction();
			tx.commit();
			getMeetingPlaceINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	public void updateMeetingPlace() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingplace.setSpareTire("1");
			mainmeetingplacedao.merge(mainmeetingplace);
			tx = mainmeetingplacedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteMeetingPlace() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingplace.setSpareTire("0");
			mainmeetingplacedao.merge(mainmeetingplace);
			tx = mainmeetingplacedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//主承办学术会议会议类型设置 // Main-Undertake-Academic-MeetingType   Set
	public String getMeetingTypeINF() throws Exception {
		request.put("mainmeetTypeli", mainmeetingtypedao.findAll());
		return "success";
	}
	public String addMeetingType() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingtype.setSpareTire("1");
			mainmeetingtype.setAcaMeetTypeId(pkmk.mkpk(EntityUtil.getPkColumnName(MainUndertakeAcademicMeetingType.class), EntityUtil.getTableName(MainUndertakeAcademicMeetingType.class), "MACAMT"));
			mainmeetingtypedao.save(mainmeetingtype);
			tx = mainmeetingtypedao.getSession().beginTransaction();
			tx.commit();
			getMeetingTypeINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
		return "success";
	}
	public void updateMeetingType() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingtype.setSpareTire("1");
			mainmeetingtypedao.merge(mainmeetingtype);
			tx = mainmeetingtypedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw  e;
		}
	}
	public void deleteMeetingType() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingtype.setSpareTire("0");
			mainmeetingtypedao.merge(mainmeetingtype);
			tx = mainmeetingtypedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw  e;
		}
	}
	//主承办学术会议评分设置 // Main-Undertake-Academic-Meeting-Score   Set
	public String getMeetingScoreINF() throws Exception{
		request.put("mainmeetscoreli", mainmeetingscoredao.findAll());
		getMeetingPlaceINF();
		getMeetingTypeINF();
		return "success";
	}
	public String addMeetingScore() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingscore.setSpareTire("1");
			mainmeetingscore.setMainUndertakeAcademicMeetingPlace(mainmeetingplacedao.findById(mainmeetingplace.getAcaMeetPlaceId()));
			mainmeetingscore.setMainUndertakeAcademicMeetingType(mainmeetingtypedao.findById(mainmeetingtype.getAcaMeetTypeId()));
			mainmeetingscore.setAcaMeetScoreId(pkmk.mkpk(EntityUtil.getPkColumnName(MainUndertakeAcademicMeetingScore.class), EntityUtil.getTableName(MainUndertakeAcademicMeetingScore.class), "MACAMS"));
			mainmeetingscoredao.merge(mainmeetingscore);
			tx = mainmeetingscoredao.getSession().beginTransaction();
			tx.commit();
			getMeetingScoreINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	public void updateMeetingScore() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingscore.setSpareTire("1");
			mainmeetingscore.setMainUndertakeAcademicMeetingPlace(mainmeetingplacedao.findById(mainmeetingplace.getAcaMeetPlaceId()));
			mainmeetingscore.setMainUndertakeAcademicMeetingType(mainmeetingtypedao.findById(mainmeetingtype.getAcaMeetTypeId()));
			mainmeetingscoredao.merge(mainmeetingscore);
			tx = mainmeetingscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteMeetingScore() throws Exception {
		Transaction tx = null;
		try {
			mainmeetingscore.setSpareTire("0");
			mainmeetingscore.setMainUndertakeAcademicMeetingPlace(mainmeetingplacedao.findById(mainmeetingplace.getAcaMeetPlaceId()));
			mainmeetingscore.setMainUndertakeAcademicMeetingType(mainmeetingtypedao.findById(mainmeetingtype.getAcaMeetTypeId()));
			mainmeetingscoredao.merge(mainmeetingscore);
			tx = mainmeetingscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//Getter & Setter
	public MainUndertakeAcademicMeetingPlace getMainmeetingplace() {
		return mainmeetingplace;
	}

	public void setMainmeetingplace(
			MainUndertakeAcademicMeetingPlace mainmeetingplace) {
		this.mainmeetingplace = mainmeetingplace;
	}

	public MainUndertakeAcademicMeetingType getMainmeetingtype() {
		return mainmeetingtype;
	}

	public void setMainmeetingtype(
			MainUndertakeAcademicMeetingType mainmeetingtype) {
		this.mainmeetingtype = mainmeetingtype;
	}

	public MainUndertakeAcademicMeetingScore getMainmeetingscore() {
		return mainmeetingscore;
	}

	public void setMainmeetingscore(
			MainUndertakeAcademicMeetingScore mainmeetingscore) {
		this.mainmeetingscore = mainmeetingscore;
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

}
