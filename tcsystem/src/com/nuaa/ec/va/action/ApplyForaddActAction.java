package com.nuaa.ec.va.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.nuaa.ec.dao.BaseHibernateDAO;
import com.nuaa.ec.dao.TeacherDAO;
import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.model.Teacher;
import com.nuaa.ec.model.VacollectiveAct;
import com.nuaa.ec.utils.PrimaryKMaker;

public class ApplyForaddActAction {

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

	public String addAnoAct() {
		String destPath = "F:/Tomcat/Tomcat-6.0.45/work";
		String filepath = destPath + actFileFileName;
		vaact.setActapplyfile(filepath);
		vaact.setTeacher(teacherdao.findById(teacher.getTeacherId()));
		vaact.setActId(pkm.mkpk("ActID", "VACollectiveAct", "vaact"));
		vaact.setScore(0.0);
		vaact.setBaseNum(null);
		vaact.setAspareTire("0");
		vaact.setSpareTire("1");
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
			new BaseHibernateDAO().getSession().beginTransaction().rollback();
			this.setAddResStatus("申请失败，请稍后重试！");
		} finally {
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
