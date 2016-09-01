package com.nuaa.ec.science.action;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.AcademicWorkScoreDAO;
import com.nuaa.ec.dao.PublishClubDAO;
import com.nuaa.ec.dao.PublishClubTypeDAO;
import com.nuaa.ec.dao.WordsNumberDAO;
import com.nuaa.ec.model.AcademicWorkScore;
import com.nuaa.ec.model.PublishClub;
import com.nuaa.ec.model.PublishClubType;
import com.nuaa.ec.model.WordsNumber;


public class AcademicWorkSetAction implements RequestAware{

	private Map<String, Object> request;
	
	private WordsNumber wordnum;
	private PublishClub publishcb;
	private PublishClubType publishcbtype;
	private AcademicWorkScore academicscode;
	
	private WordsNumberDAO worddao = new WordsNumberDAO();
	private PublishClubDAO publishcbdao = new PublishClubDAO();
	private PublishClubTypeDAO publishcbtypedao = new PublishClubTypeDAO();
	private AcademicWorkScoreDAO academicscoredao = new AcademicWorkScoreDAO();
	
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
	
}
