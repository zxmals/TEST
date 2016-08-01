package com.nuaa.ec.va.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.nuaa.ec.dao.VacollectiveActDAO;
import com.nuaa.ec.model.VacollectiveAct;

public class ApplyForaddActAction {

	private VacollectiveAct vaact;
	private File actFile;
	private String actFileContentType;
	private String actFileFileName;
	private VacollectiveActDAO vadao = new VacollectiveActDAO();

	public String execute() {
		return "success";
	}

	public String addAnoAct() {
		String destPath = "F:/Tomcat/Tomcat-6.0.45/work";
		String filepath = destPath+actFileFileName;
		try {
//			 System.out.println("Src File name: " + actFile);
//			 System.out.println("Dst File name: " + actFileFileName);
			File destFile = new File(destPath, actFileFileName);
			FileUtils.copyFile(actFile, destFile);
		} catch (IOException e) {
			e.printStackTrace();
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

}
