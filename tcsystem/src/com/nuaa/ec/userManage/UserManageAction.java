package com.nuaa.ec.userManage;

import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.nuaa.ec.dao.TeacherDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class UserManageAction extends ActionSupport implements RequestAware{
	public String get() {
		String pageSize_s=(String) session.get("pageSize_user");
		if(pageSize_s!=null && pageSize_s.trim().length()!=0){
			pageSize_user=Integer.parseInt(pageSize_s);
		}
		boolean isDivided=false;
		if(this.getIsDivided()!=null && this.getIsDivided().trim().equals("true")){
			isDivided=true;
		}
		this.request.put("Teachers", this.teacherDAO.findAllByCondition(pageIndex_user, pageSize_user, findCondition, isDivided));
		System.out.println("guoalie");
		return "success";
	}

	public void add() {

	}

	@Override
	public String execute() throws Exception {
		return "success";
	}

	private int pageIndex_user = 1;
	private int pageSize_user = 1;
	private String isDivided;
	private String findCondition;
	private TeacherDAO teacherDAO=new TeacherDAO();
	private Map<String,Object> session=ActionContext.getContext().getSession();
	private Map<String,Object> request;
	public int getPageIndex_user() {
		return pageIndex_user;
	}

	public void setPageIndex_user(int pageIndex_user) {
		this.pageIndex_user = pageIndex_user;
	}

	public String getIsDivided() {
		return isDivided;
	}

	public void setIsDivided(String isDivided) {
		this.isDivided = isDivided;
	}

	public String getFindCondition() {
		return findCondition;
	}

	public void setFindCondition(String findCondition) {
		this.findCondition = findCondition;
		session.put("findCondition", findCondition);
	}

	public int getPageSize_user() {
		return pageSize_user;
	}

	public void setPageSize_user(int pageSize_user) {
		this.pageSize_user = pageSize_user;
		session.put("pageSize_user", pageSize_user);
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
}
