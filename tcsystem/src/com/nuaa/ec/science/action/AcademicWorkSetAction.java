package com.nuaa.ec.science.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.AcademicWorkScoreDAO;
import com.nuaa.ec.dao.PublishClubDAO;
import com.nuaa.ec.dao.PublishClubTypeDAO;
import com.nuaa.ec.dao.WordsNumberDAO;
import com.nuaa.ec.model.AcademicWorkScore;
import com.nuaa.ec.model.PublishClub;
import com.nuaa.ec.model.PublishClubType;
import com.nuaa.ec.model.WordsNumber;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;


public class AcademicWorkSetAction implements RequestAware{

	private Map<String, Object> request;
	
	private Integer operstatus;
	private WordsNumber wordnum;
	private PublishClubType publishcbtype;
	private PublishClub publishcb;
	private AcademicWorkScore academicscode;
	
	private WordsNumberDAO worddao = new WordsNumberDAO();
	private PublishClubTypeDAO publishcbtypedao = new PublishClubTypeDAO();
	private PublishClubDAO publishcbdao = new PublishClubDAO();
	private AcademicWorkScoreDAO academicscoredao = new AcademicWorkScoreDAO();
	//utils
	private PrimaryKMaker pkmk = new PrimaryKMaker(); 
	
	//default method
	public String execute(){
		return "success";
	}
	//TODO: 字数设置 // wordNumber set
	/***
	 * 获取字数信息 // get wordNumber info
	 * @return
	 * @throws Exception 
	 */
	public String getWordNumberINF() throws Exception{
		request.put("wordnum", worddao.findAll());
		return "success";
	} 
	/**
	 * 添加一个字数信息 // add word Number -set information
	 * @return
	 * @throws Exception
	 */
	public String addwordNum() throws Exception{
		Transaction tx = null;
		try {
			wordnum.setSpareTire("1");
			wordnum.setWordId(pkmk.mkpk(EntityUtil.getPkColumnName(WordsNumber.class), EntityUtil.getTableName(WordsNumber.class), "NUM"));
			worddao.save(wordnum);
			tx = worddao.getSession().beginTransaction();
			tx.commit();
			getWordNumberINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	/***
	 * 更新一个字数 范围信息
	 * @throws Exception
	 */
	public void updateWordNum() throws Exception{
		Transaction tx = null;
		try {
			wordnum.setSpareTire("1");
			worddao.merge(wordnum);
			tx = worddao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	/***
	 * 删除一个字数 范围信息
	 * @throws Exception
	 */
	public void deleteWordNum() throws Exception{
		Transaction tx = null;
		try {
			wordnum.setSpareTire("0");
			worddao.merge(wordnum);
			tx = worddao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	
	//TODO:出版社类型设置 /PublishClubType -Set
	/***
	 *  get all of publish club -type info
	 * @return
	 * @throws Exception
	 */
	public String getPublishClubTypeINF() throws Exception{
		request.put("publishclubtype", publishcbtypedao.findAll());
		return "success";
	}
	/***
	 * add a publishType
	 * @return
	 * @throws Exception
	 */
	public String addpublishType() throws Exception{
		Transaction tx = null;
		try {
			publishcbtype.setPculbTypeId(pkmk.mkpk(EntityUtil.getPkColumnName(PublishClubType.class), EntityUtil.getTableName(PublishClubType.class), "PUBT"));
			publishcbtype.setSpareTire("1");
			publishcbtypedao.save(publishcbtype);
			tx = publishcbtypedao.getSession().beginTransaction();
			tx.commit();
			getPublishClubTypeINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	/***
	 * update the pubslish-club-type inf
	 * @throws Exception
	 */
	public void updatePublishType() throws Exception{
		Transaction tx = null;
		try {
			publishcbtype.setSpareTire("1");
			publishcbtypedao.merge(publishcbtype);
			tx = publishcbtypedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	/**
	 * delete the publish-club-type inf
	 * @throws Exception
	 */
	public void deletePublishType() throws Exception{
		Transaction tx = null;
		try {
			publishcbtype.setSpareTire("0");
			publishcbtypedao.merge(publishcbtype);
			tx = publishcbtypedao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO:publishcb SET // 出版社设置
	/***
	 * 获取出版社信息
	 * @return
	 * @throws Exception
	 */
	public String getPublishClubINF() throws Exception{
		request.put("publishclubLi", publishcbdao.findAll());
		getPublishClubTypeINF();
		return "success";
	}
	/***
	 * 增加 一个出版社
	 * @return
	 * @throws Exception
	 */
	public String addpublishClub() throws Exception{
		Transaction tx = null;
		try {
			publishcb.setPublishClubId(pkmk.mkpk(EntityUtil.getPkColumnName(PublishClub.class), EntityUtil.getTableName(PublishClub.class), "PUB"));
			publishcb.setSpareTire("1");
			publishcb.setPublishClubType(publishcbtypedao.findById(publishcb.getPublishClubType().getPculbTypeId()));
			publishcbdao.save(publishcb);
			tx = publishcbdao.getSession().beginTransaction();
			tx.commit();
			getPublishClubINF();
			this.setOperstatus(1);
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	/***
	 * 更新出版社
	 * @throws Exception
	 */
	public void updatepublishClub() throws Exception{
		Transaction tx = null;
		try {
			publishcb.setSpareTire("1");
			publishcb.setPublishClubType(publishcbtypedao.findById(publishcb.getPublishClubType().getPculbTypeId()));
			publishcbdao.merge(publishcb);
			tx = publishcbdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	/***
	 * 删除一个出版社
	 * @throws Exception
	 */
	public void deletepublishClub() throws Exception{
		Transaction tx = null;
		try {
			publishcb.setSpareTire("0");
			publishcb.setPublishClubType(publishcbtypedao.findById(publishcb.getPublishClubType().getPculbTypeId()));
			publishcbdao.merge(publishcb);
			tx = publishcbdao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO : 期刊论文评分设置 // academic-work --score -Set
	public String getAcadWorkScoreINF() throws Exception{
		request.put("acadworkScoreLi", academicscoredao.findAll());
		getWordNumberINF();
		return "success";
	}
	public String addAcadWorkScore() throws Exception{
		Transaction tx = null;
		try {
			academicscode.setSpareTire("1");
			academicscode.setAcaWorkScoreId(pkmk.mkpk(EntityUtil.getPkColumnName(AcademicWorkScore.class), EntityUtil.getTableName(AcademicWorkScore.class), "ACASCO"));
			academicscode.setWordsNumber(worddao.findById(academicscode.getWordsNumber().getWordId()));
			academicscoredao.save(academicscode);
			tx = academicscoredao.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			getAcadWorkScoreINF();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			this.setOperstatus(-1);
			throw e;
		}
		return "success";
	}
	public void updateAcadWorkScore() throws Exception{
		Transaction tx = null;
		try {
			academicscode.setSpareTire("1");
			academicscode.setWordsNumber(worddao.findById(academicscode.getWordsNumber().getWordId()));
			academicscoredao.merge(academicscode);
			tx = academicscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	public void deleteAcadWorkScore() throws Exception{
		Transaction tx = null;
		try {
			academicscode.setSpareTire("0");
			academicscode.setWordsNumber(worddao.findById(academicscode.getWordsNumber().getWordId()));
			academicscoredao.merge(academicscode);
			tx = academicscoredao.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
	}
	//TODO : Getter and Setter
	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public WordsNumber getWordnum() {
		return wordnum;
	}

	public void setWordnum(WordsNumber wordnum) {
		this.wordnum = wordnum;
	}

	public PublishClub getPublishcb() {
		return publishcb;
	}

	public void setPublishcb(PublishClub publishcb) {
		this.publishcb = publishcb;
	}

	public PublishClubType getPublishcbtype() {
		return publishcbtype;
	}

	public void setPublishcbtype(PublishClubType publishcbtype) {
		this.publishcbtype = publishcbtype;
	}

	public AcademicWorkScore getAcademicscode() {
		return academicscode;
	}

	public void setAcademicscode(AcademicWorkScore academicscode) {
		this.academicscode = academicscode;
	}
	
	public Integer getOperstatus() {
		return operstatus;
	}
	
	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}
}
