package com.nuaa.ec.va.action;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.nuaa.ec.dao.BaseHibernateDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.dao.VacollectiveActivitiesPublishDAO;
import com.nuaa.ec.dao.VateacherAndCollectiveActDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.model.VacollectiveActivitiesPublish;
import com.nuaa.ec.model.VateacherAndCollectiveAct;
import com.nuaa.ec.model.VateacherAndCollectiveActId;
import com.nuaa.ec.utils.PrimaryKMaker;

public class ActMangeAction implements SessionAware{

	private VacollectiveAct vaact;
	private Teacher teacher;
	private File actFile;
	private String actFileContentType;
	private String actFileFileName;
	private String AddResStatus;
	private String foredate;
	private String afterdate;
	private VacollectiveActivitiesPublish vapm;
	private VateacherAndCollectiveAct vateacherandact;
	private Map<String, Object>session;
	private String addactstatus;
	private VateacherAndCollectiveActDAO vateacherandactdao = new VateacherAndCollectiveActDAO();
	private VacollectiveActDAO vadao = new VacollectiveActDAO();
	private PrimaryKMaker pkm = new PrimaryKMaker();
	private TeacherDAO teacherdao = new TeacherDAO();	
	private VacollectiveActivitiesPublishDAO vapubdao = new VacollectiveActivitiesPublishDAO();
	public String execute() {
		return "success";
	}

	/***
	 * Action
	 * 添加一个活动申请/add a apply for act(activity)
	 * @return
	 */
	public String addAnoAct() {
		/*文件保存路径 /File save-path*/
		String destPath = "F:/Tomcat/Tomcat-6.0.45/work";
		/*文件全/complete name of the File*/
		String filepath = destPath + actFileFileName;
		/*
		 * set-attribute to vaact/ 为对象vaact补充属性值
		 */
		vaact.setActapplyfile(filepath);
		vaact.setTeacher(teacherdao.findById(teacher.getTeacherId()));
		vaact.setActId(pkm.mkpk("ActID", "VACollectiveAct", "vaact"));
		vaact.setScore(0.0);
		vaact.setBaseNum(null);
		vaact.setAspareTire("0");
		vaact.setSpareTire("1");
		/*存文件以及保存对象到数据库/execute save File and save object to database */
		try {
			// System.out.println("Src File name: " + actFile);
			// System.out.println("Dst File name: " + actFileFileName);
			File destFile = new File(destPath, actFileFileName);
			FileUtils.copyFile(actFile, destFile);
			vadao.save(vaact);
			new BaseHibernateDAO().getSession().beginTransaction().commit();
			this.setAddResStatus("已申请，请等待审核！");
		} catch (Exception e) {
			e.printStackTrace();
			/*保存失败 事物回滚/save object fail ,rollback transaction*/
			new BaseHibernateDAO().getSession().beginTransaction().rollback();
			this.setAddResStatus("申请失败，请稍后重试！");
		} finally {
			/*close session, release space/关闭session，以免内存溢出*/
			new BaseHibernateDAO().closeSession();
		}
		return "success";
	}
/**
 * 根据用户划定的时间范围，获取对应时间范围内举办的活动
 * according to the time-limted get the Acts inner time-limted
 * @return
 */
	public String getPubAct(){
		HttpServletRequest req = ServletActionContext.getRequest();
		req.setAttribute("sreqvapm", vapubdao.getJoinPublishAct(foredate, afterdate));
		return "success";
	}
/**
 * 添加用户自己新增的活动/add act ~ user own added(needs to be check
 * @return
 */
	public String addJoinedAct(){
		VacollectiveActivitiesPublish vap = vapubdao.findById(vapm.getActPubId());
		this.setTeacher((Teacher)session.get("teacher"));
		VateacherAndCollectiveActId vapid = new VateacherAndCollectiveActId(vap, teacher);
		this.vateacherandact = new VateacherAndCollectiveAct();
		vateacherandact.setId(vapid);
		vateacherandact.setScore(vap.getVacollectiveAct().getScore());
		vateacherandact.setSpareTire("1");
		/*默认状态为0未审核，需经审核/default with 0 ,needs to be check*/
		vateacherandact.setAspareTire("0");
		try {
			vateacherandactdao.save(vateacherandact);
			new BaseHibernateDAO().getSession().beginTransaction().commit();
			this.setAddactstatus("增加成功，待审核！");
			System.out.println("增加成功，待审核！");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			new BaseHibernateDAO().getSession().beginTransaction().rollback();
			this.setAddactstatus("增加失败，请稍后重试！");
			System.out.println("增加失败，请稍后重试！");
		}finally{
			new BaseHibernateDAO().closeSession();
		}		
		return "success";
	}
	/***\
	 * 
	 * 获得 教师个人所参与的活动的情况
	 * @return
	 */
	public String getMyAct(){
		String resu = "success";
		try {
			List<VateacherAndCollectiveAct> liteachandpub = vateacherandactdao.findAllByTimeLimted(foredate, afterdate);
			ServletActionContext.getRequest().setAttribute("myact", liteachandpub);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			resu = "error";
		}
		return resu;
	}
	
	/***
	 * execute delete-an personal-act :transport-ajax//执行删除一个活动，连接传输:ajax,
	 * no return ? return at line 156 or 161 or at the jsp-ajax-error:function
	 * 传参方式 用response.getwirter().write(""); 出错时再页面会有对应的处理函数
	 */
	public void deleteMyAct(){
		try {
			vapm = vapubdao.findById(ServletActionContext.getRequest().getParameter("pubactid"));
			vateacherandactdao.pretendDelete(new VateacherAndCollectiveActId(vapm, (Teacher)session.get("teacher")));
			new BaseHibernateDAO().getSession().beginTransaction().commit();
			ServletActionContext.getResponse().getWriter().write("succ");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			try {
				ServletActionContext.getResponse().getWriter().write("fail");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}finally{
			new BaseHibernateDAO().closeSession();
		}
	}
	
	public String getUnjoinedRuledAct(){
		
		return "success";
	}	
	
	public VacollectiveAct getVaact() {
		return vaact;
	}

	public void setVaact(VacollectiveAct vaact) {
		this.vaact = vaact;
	}

	public File getActFile() {
		return actFile;
	}

	public void setActFile(File actFile) {
		this.actFile = actFile;
	}

	public String getActFileContentType() {
		return actFileContentType;
	}

	public void setActFileContentType(String actFileContentType) {
		this.actFileContentType = actFileContentType;
	}

	public String getActFileFileName() {
		return actFileFileName;
	}

	public void setActFileFileName(String actFileFileName) {
		this.actFileFileName = actFileFileName;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getAddResStatus() {
		return AddResStatus;
	}

	public void setAddResStatus(String addResStatus) {
		AddResStatus = addResStatus;
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

	public VacollectiveActivitiesPublish getVapm() {
		return vapm;
	}

	public void setVapm(VacollectiveActivitiesPublish vapm) {
		this.vapm = vapm;
	}

	public VateacherAndCollectiveAct getVateacherandact() {
		return vateacherandact;
	}

	public void setVateacherandact(VateacherAndCollectiveAct vateacherandact) {
		this.vateacherandact = vateacherandact;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getAddactstatus() {
		return addactstatus;
	}

	public void setAddactstatus(String addactstatus) {
		this.addactstatus = addactstatus;
	}

	
}
