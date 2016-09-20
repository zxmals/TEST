package com.nuaa.ec.ScientificResearchPerformanceSet.Action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.PeriodicalDAO;
import com.nuaa.ec.dao.PeriodicalPapersDAO;
import com.nuaa.ec.model.PeriodicalPapers;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.utils.EntityUtil;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.nuaa.ec.utils.StoreData;

public class periodicalpaperAction implements RequestAware,SessionAware {
	private Map<String, Object> request;
	private Map<String, Object> session;
	private Integer operstatus;
	private String foredate;
	private String afterdate;
	private String currentrow; 
	private PeriodicalPapers periopaper;

	private PeriodicalPapersDAO periopaperdao = new PeriodicalPapersDAO();
	private PeriodicalDAO periodao = new PeriodicalDAO();
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	// default method
	public String execute() {
		return "success";
	}

	public String getPeriodicalPaperINF()throws Exception{
		request.put("periodicalpaperli", periopaperdao.findAll(generateQueryCondition(), 0, 100));
		session.put("periodicalli", periodao.findTranslate());
		return "success";
	}

	public String addPeriodicalPaper() throws Exception{
		Transaction tx = null;
		try {
			periopaper.setPpid(pkmk.mkpk("PPID", EntityUtil.getTableName(PeriodicalPapers.class), "PP"));
			periopaper.setSpareTire("1");
			periopaper.setChargePersonId(((Teacher)session.get("teacher")).getTeacherId());
			periopaper.setChargePerson(((Teacher)session.get("teacher")).getTeacherName());
			periopaper.setPeriodical(periodao.findById(periopaper.getPeriodical().getPeriodicalId()));
			periopaper.setFirstAuthor("first".equals(ServletActionContext.getRequest().getParameter("author"))?((Teacher)session.get("teacher")).getTeacherId():"");
			periopaper.setSecondAuthor("second".equals(ServletActionContext.getRequest().getParameter("author"))?((Teacher)session.get("teacher")).getTeacherId():"");
			periopaper.setCheckout("0");
			periopaperdao.save(periopaper);
			tx = periopaperdao.getSession().beginTransaction();
			tx.commit();
			getPeriodicalPaperINF();
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}
		return "success";
	}
	//Utils meth0d
	public String generateQueryCondition(){
		StringBuffer condition = new StringBuffer();
		condition.append("AND");
		if(foredate!=null){
			if(!"".equals(foredate.trim()))
				condition.append(" Year>='"+foredate+"' AND");
			if(afterdate!=null){
				if(!"".equals(afterdate.trim()))
					condition.append(" Year<='"+afterdate+"' AND");
			}
		}else{
			if(afterdate!=null){
				if(!"".equals(afterdate.trim()))
					condition.append(" Year<='"+afterdate+"' AND");
			}
		}
		return condition.substring(0, condition.length()-3);
	}
	//添加数据（变向分页）
	public void addRows() throws Exception{
		int currentrows = 0;
		if(currentrow!=null){
			currentrows = "".equals(currentrow.trim())?0:Integer.parseInt(currentrow.trim());
		}
		HttpServletResponse resp = ServletActionContext.getResponse();
		JSONArray json = JSONArray.fromObject(periopaperdao.findAll(generateQueryCondition(), currentrows,100));
		try {
			resp.setCharacterEncoding("utf-8");
			resp.getWriter().write(json.toString());
		} catch (Exception e) {
			// TODO: handle exception
			throw e;
		}
	}
	//Getter & Setter
	public Integer getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}

	public PeriodicalPapers getPeriopaper() {
		return periopaper;
	}

	public void setPeriopaper(PeriodicalPapers periopaper) {
		this.periopaper = periopaper;
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

	public String getCurrentrow() {
		return currentrow;
	}

	public void setCurrentrow(String currentrow) {
		this.currentrow = currentrow;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
