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

	public String execute() {
		return "success";
	}

	public String addAnoAct() {
		String destPath = "F:/Tomcat/Tomcat-6.0.45/work";
		String filepath = destPath + actFileFileName;
		try {
			// System.out.println("Src File name: " + actFile);
			// System.out.println("Dst File name: " + actFileFileName);
			File destFile = new File(destPath, actFileFileName);
			FileUtils.copyFile(actFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		vaact.setActapplyfile(filepath);
		vaact.setTeacher(teacherdao.findById(teacher.getTeacherId()));
		vaact.setActId(pkm.mkpk("ActID", "VACollectiveAct", "vaact"));
		vaact.setScore(0.0);
		vaact.setBaseNum(null);
		vaact.setSpareTire("10");
		vaact.setSpareTire("0");
		new BaseHibernateDAO().closeSession();
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

}
