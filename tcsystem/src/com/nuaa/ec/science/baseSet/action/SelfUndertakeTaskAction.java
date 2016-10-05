package com.nuaa.ec.science.baseSet.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.hibernate.Transaction;

import com.nuaa.ec.dao.SelfUndertakeTaskDAO;
import com.nuaa.ec.model.SelfUndertakeTask;
import com.nuaa.ec.utils.PrimaryKMaker;

public class SelfUndertakeTaskAction implements RequestAware{
	public void setRequest(Map<String, Object> request) {
		this.request=request;
	}

	public int getOperstatus() {
		return operstatus;
	}

	public void setOperstatus(int operstatus) {
		this.operstatus = operstatus;
	}

	public SelfUndertakeTask getSelfUndertakeTask() {
		return selfUndertakeTask;
	}

	public void setSelfUndertakeTask(SelfUndertakeTask selfUndertakeTask) {
		this.selfUndertakeTask = selfUndertakeTask;
	}
	public String execute() throws Exception{
		return "success";
	}
	/*
	 * 更新任务信息
	 */
	public void updateTask(){
		selfUndertakeTask.setSpareTire("1");
		Transaction tx=null;
		try{
			selfUndertakeTaskDAO.merge(selfUndertakeTask);
			tx=selfUndertakeTaskDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			this.viewAllTaskUndertaked();
			ServletActionContext.getResponse().getWriter().write("succ");
		}catch(Exception ex){
			tx.rollback();
			this.setOperstatus(-1);
			ex.printStackTrace();
		}
	}
	/**
	 * 删除一条本人承担的任务
	 */
	public void deleteTask(){
		selfUndertakeTask.setSpareTire("0");
		Transaction tx=null;
		try{
			this.selfUndertakeTaskDAO.merge(selfUndertakeTask);
			tx=this.selfUndertakeTaskDAO.getSession().beginTransaction();
			tx.commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		}catch(Exception ex){
			tx.rollback();
			this.setOperstatus(-1);
			ex.printStackTrace();
		}
	}
	/*
	 * 添加一条任务信息
	 */
	public String addTaskUndertaked(){
		selfUndertakeTask.setSpareTire("1");
		System.out.println(selfUndertakeTask.getUndertakeTaskName());
		selfUndertakeTask.setUndertakeTaskId(pkmk.mkpk("UndertakeTaskID", "SelfUndertakeTask", "SU"));
		Transaction tx=null;
		try{
			selfUndertakeTaskDAO.save(selfUndertakeTask);
			tx=selfUndertakeTaskDAO.getSession().beginTransaction();
			tx.commit();
			this.setOperstatus(1);
			this.viewAllTaskUndertaked();
		}catch(Exception ex){
			tx.rollback();
			this.setOperstatus(-1);
			ex.printStackTrace();
		}
		return "success";
	}
	/**
	 * 获得本人承担的所有任务
	 */
	public String viewAllTaskUndertaked(){
		this.request.put("taskList", selfUndertakeTaskDAO.findAll());
		return "success";
	}
	private SelfUndertakeTask selfUndertakeTask=null;
	private PrimaryKMaker pkmk = new PrimaryKMaker();
	private SelfUndertakeTaskDAO selfUndertakeTaskDAO=new SelfUndertakeTaskDAO();
	private int operstatus;
	private Map<String,Object> request=null;
}
