package com.nuaa.ec.teach.baseSet.action;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.TftermDAO;
import com.nuaa.ec.model.Tfterm;
import com.nuaa.ec.utils.PrimaryKMaker;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class TermSetAction extends ActionSupport implements RequestAware{
	private Tfterm term;
	private TftermDAO termDao = new TftermDAO();
	private PrimaryKMaker pk = new PrimaryKMaker();
	private Map<String, Object> request;
	private Integer operstatus;
	
	public String execute(){
		return SUCCESS;
	}

	public String getTermList() throws Exception{
		request.put("Term", termDao.findAll());
		return "success";
	}
	
	public String addTerm() throws Exception{
		term.setTermId(pk.mkpk("termID", "Tfterm", "Term"));
		term.setSpareTire("1");
		Transaction txTransaction = null;
		try {
			termDao.save(term);
			txTransaction = termDao.getSession().beginTransaction();
			txTransaction.commit();
			this.setOperstatus(1);
			getTermList();
		} catch (Exception e) {
			// TODO: handle exception
			txTransaction.rollback();
			this.setOperstatus(-1);
			throw e;
		}finally{
			termDao.closeSession();
		}
		return "success";
	}
	
	public void updateTerm() throws Exception{
		Transaction tx = null;
		term.setSpareTire("1");
		HttpServletResponse resp = (HttpServletResponse) ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		try {
			termDao.merge(term);
			tx = termDao.getSession().beginTransaction();
			tx.commit();
			resp.getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			try {
				resp.getWriter().write("fail");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				throw e;
			}
			throw e;
		}finally{
			termDao.closeSession();
		}
	}
	
	public void deleteTerm() throws Exception{
		Transaction tx = null;
		term.setSpareTire("0");
		HttpServletResponse resp = (HttpServletResponse)ActionContext.getContext().get(org.apache.struts2.StrutsStatics.HTTP_RESPONSE);
		try {
			termDao.merge(term);
			tx = termDao.getSession().beginTransaction();
			tx.commit();
			resp.getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			tx.rollback();
			throw e;
		}finally{
			termDao.closeSession();
		}
	}
	
	
	
	public Tfterm getTerm() {
		return term;
	}


	public void setTerm(Tfterm term) {
		this.term = term;
	}

	public void setRequest(Map<String, Object> request) {
		// TODO Auto-generated method stub
		this.request = request;
	}


	public Integer getOperstatus() {
		return operstatus;
	}


	public void setOperstatus(Integer operstatus) {
		this.operstatus = operstatus;
	}


	public Map<String, Object> getRequest() {
		return request;
	}
	
}
