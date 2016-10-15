package com.nuaa.ec.model;

/**
 * TfteachingPaperPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfteachingPaperPerformance implements java.io.Serializable {

	// Fields

	private Integer upid;
	private SelfUndertakeTask selfUndertakeTask;
	private TfteachingPaperProject tfteachingPaperProject;
	private Teacher teacher;
	private Double singelScore;
	private String spareTire;
	private String checkOut;

	// Constructors

	/** default constructor */
	public TfteachingPaperPerformance() {
	}

	/** minimal constructor */
	public TfteachingPaperPerformance(Integer upid,
			TfteachingPaperProject tfteachingPaperProject) {
		this.upid = upid;
		this.tfteachingPaperProject = tfteachingPaperProject;
	}

	/** full constructor */
	public TfteachingPaperPerformance(Integer upid,
			SelfUndertakeTask selfUndertakeTask,
			TfteachingPaperProject tfteachingPaperProject, Teacher teacher,
			Double singelScore, String spareTire, String checkOut) {
		this.upid = upid;
		this.selfUndertakeTask = selfUndertakeTask;
		this.tfteachingPaperProject = tfteachingPaperProject;
		this.teacher = teacher;
		this.singelScore = singelScore;
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

	public TfteachingPaperProject getTfteachingPaperProject() {
		return this.tfteachingPaperProject;
	}

	public void setTfteachingPaperProject(
			TfteachingPaperProject tfteachingPaperProject) {
		this.tfteachingPaperProject = tfteachingPaperProject;
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