package com.nuaa.ec.va.action;

import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.dao.VacollectiveActivitiesPublishDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.VacollectiveActivitiesPublish;
import com.nuaa.ec.utils.PrimaryKMaker;

public class ActPublishManage implements SessionAware{

	private Map<String , Object> session;
	private String foredate;
	private String afterdate;
	private VacollectiveActivitiesPublish foreact;
	private VacollectiveActDAO vacollectdao = new VacollectiveActDAO();
	private VacollectiveActivitiesPublishDAO vapdao = new VacollectiveActivitiesPublishDAO();
	private PrimaryKMaker pkdao = new PrimaryKMaker();
	//default method
	public String execute(){
		return "success";
	}
/***
 * get -Acts -of-ready-to publish
 * 获取可以发布的活动 
 * @return
 */
	public String getReadyAct(){
		try {
			session.put("readyactli", vacollectdao.findByAspareTire("1"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "success";
	}
	/***
	 * apply/add - a - ready - to - publish -Act
	 * 增加或申请一项活动发布
	 * @return
	 */
	public String addPublishApply(){
		try {
			/*    初始化预备插入的数据  /  init-data-for-insert  */
			foreact.setActPubId(pkdao.mkpk("ActPubID", "VACollectiveActivitiesPublish", "acp"));
			foreact.setSpareTire("1");
			foreact.setAspareTire("0");
			foreact.setTeacherId(((Teacher)session.get("teacher")).getTeacherId());
			vapdao.save(foreact);
			vapdao.getSession().beginTransaction().commit();
			ServletActionContext.getRequest().setAttribute("applystatus", "申请成功,等待审核");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			vapdao.getSession().beginTransaction().rollback();
			ServletActionContext.getRequest().setAttribute("applystatus", "申请失败,请联系管理员");
		}
		return "success";
	}
	
	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
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

	public VacollectiveActivitiesPublish getForeact() {
		return foreact;
	}

	public void setForeact(VacollectiveActivitiesPublish foreact) {
		this.foreact = foreact;
	}
	 
}
