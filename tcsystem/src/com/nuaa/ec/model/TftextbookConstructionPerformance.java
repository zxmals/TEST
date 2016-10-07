package com.nuaa.ec.model;

/**
 * TftextbookConstructionPerformance entity. @author MyEclipse Persistence Tools
 */

public class TftextbookConstructionPerformance implements java.io.Serializable {

	// Fields

	private Integer upid;
	private SelfUndertakeTask selfUndertakeTask;
	private TftextbookConstructionProject tftextbookConstructionProject;
	private Teacher teacher;
	private Double singellScore;
	private String spareTire;
	private String checkOut;

	// Constructors

	/** default constructor */
	public TftextbookConstructionPerformance() {
	}

	/** minimal constructor */
	public TftextbookConstructionPerformance(Integer upid,
			TftextbookConstructionProject tftextbookConstructionProject) {
		this.upid = upid;
		this.tftextbookConstructionProject = tftextbookConstructionProject;
	}

	/** full constructor */
	public TftextbookConstructionPerformance(Integer upid,
			SelfUndertakeTask selfUndertakeTask,
			TftextbookConstructionProject tftextbookConstructionProject,
			Teacher teacher, Double singellScore, String spareTire,
			String checkOut) {
		this.upid = upid;
		this.selfUndertakeTask = selfUndertakeTask;
		this.tftextbookConstructionProject = tftextbookConstructionProject;
		this.teacher = teacher;
		this.singellScore = singellScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
	}

	// Property accessors

	public Integer getUpid() {
		return this.upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	public SelfUndertakeTask getSelfUndertakeTask() {
		return this.selfUndertakeTask;
	}

	public void setSelfUndertakeTask(SelfUndertakeTask selfUndertakeTask) {
		this.selfUndertakeTask = selfUndertakeTask;
	}

	public TftextbookConstructionProject getTftextbookConstructionProject() {
		return this.tftextbookConstructionProject;
	}

	public void setTftextbookConstructionProject(
			TftextbookConstructionProject tftextbookConstructionProject) {
		this.tftextbookConstructionProject = tftextbookConstructionProject;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Double getSingellScore() {
		return this.singellScore;
	}

	public void setSingellScore(Double singellScore) {
		this.singellScore = singellScore;
	}

	public String getSpareTire() {
		return this.spareTire;
	}

	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}

	public String getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

}