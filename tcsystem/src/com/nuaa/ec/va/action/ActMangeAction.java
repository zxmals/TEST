package com.nuaa.ec.va.action;

import java.io.File;

import org.apache.commons.io.FileUtils;

import com.nuaa.ec.dao.BaseHibernateDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.utils.PrimaryKMaker;

public class ActMangeAction {

	private VacollectiveAct vaact;
	private Teacher teacher;
	private File actFile;
	private String actFileContentType;
	private String actFileFileName;
	private VacollectiveActDAO vadao = new VacollectiveActDAO();
	private PrimaryKMaker pkm = new PrimaryKMaker();
	private TeacherDAO teacherdao = new TeacherDAO();
	private String AddResStatus;

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

}
