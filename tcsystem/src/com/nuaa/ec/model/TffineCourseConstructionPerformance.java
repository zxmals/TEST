package com.nuaa.ec.model;

/**
 * TffineCourseConstructionPerformance entity. @author MyEclipse Persistence
 * Tools
 */

public class TffineCourseConstructionPerformance implements
		java.io.Serializable {

	// Fields

	private Integer upid;
	private TffineCourseConstructionProject tffineCourseConstructionProject;
	private SelfUndertakeTask selfUndertakeTask;
	private Teacher teacher;
	private Double singelScore;
	private String checkOut;
	private String spareTire;

	// Constructors

	/** default constructor */
	public TffineCourseConstructionPerformance() {
	}

	/** minimal constructor */
	public TffineCourseConstructionPerformance(Integer upid) {
		this.upid = upid;
	}

	/** full constructor */
	public TffineCourseConstructionPerformance(Integer upid,
			TffineCourseConstructionProject tffineCourseConstructionProject,
			SelfUndertakeTask selfUndertakeTask, Teacher teacher,
			Double singelScore, String checkOut, String spareTire) {
		this.upid = upid;
		this.tffineCourseConstructionProject = tffineCourseConstructionProject;
		this.selfUndertakeTask = selfUndertakeTask;
		this.teacher = teacher;
		this.singelScore = singelScore;
		this.checkOut = checkOut;
		this.spareTire = spareTire;
	}

	// Property accessors

	public Integer getUpid() {
		return this.upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	public TffineCourseConstructionProject getTffineCourseConstructionProject() {
		return this.tffineCourseConstructionProject;
	}

	public void setTffineCourseConstructionProject(
			TffineCourseConstructionProject tffineCourseConstructionProject) {
		this.tffineCourseConstructionProject = tffineCourseConstructionProject;
	}

	public SelfUndertakeTask getSelfUndertakeTask() {
		return this.selfUndertakeTask;
	}

	public void setSelfUndertakeTask(SelfUndertakeTask selfUndertakeTask) {
		this.selfUndertakeTask = selfUndertakeTask;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Double getSingelScore() {
		return this.singelScore;
	}

	public void setSingelScore(Double singelScore) {
		this.singelScore = singelScore;
	}

	public String getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getSpareTire() {
		return this.spareTire;
	}

	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}

}