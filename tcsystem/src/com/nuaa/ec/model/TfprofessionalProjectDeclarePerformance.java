package com.nuaa.ec.model;

/**
 * TfprofessionalProjectDeclarePerformance entity. @author MyEclipse Persistence
 * Tools
 */

public class TfprofessionalProjectDeclarePerformance implements
		java.io.Serializable {

	// Fields

	private Integer upid;
	private TfprofessionalProjectDeclareProject tfprofessionalProjectDeclareProject;
	private SelfUndertakeTask selfUndertakeTask;
	private Teacher teacher;
	private Double singleScore;
	private String spareTire;
	private String checkOut;

	// Constructors

	/** default constructor */
	public TfprofessionalProjectDeclarePerformance() {
	}

	/** minimal constructor */
	public TfprofessionalProjectDeclarePerformance(Integer upid) {
		this.upid = upid;
	}

	/** full constructor */
	public TfprofessionalProjectDeclarePerformance(
			Integer upid,
			TfprofessionalProjectDeclareProject tfprofessionalProjectDeclareProject,
			SelfUndertakeTask selfUndertakeTask, Teacher teacher,
			Double singleScore, String spareTire, String checkOut) {
		this.upid = upid;
		this.tfprofessionalProjectDeclareProject = tfprofessionalProjectDeclareProject;
		this.selfUndertakeTask = selfUndertakeTask;
		this.teacher = teacher;
		this.singleScore = singleScore;
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

	public TfprofessionalProjectDeclareProject getTfprofessionalProjectDeclareProject() {
		return this.tfprofessionalProjectDeclareProject;
	}

	public void setTfprofessionalProjectDeclareProject(
			TfprofessionalProjectDeclareProject tfprofessionalProjectDeclareProject) {
		this.tfprofessionalProjectDeclareProject = tfprofessionalProjectDeclareProject;
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

	public Double getSingleScore() {
		return this.singleScore;
	}

	public void setSingleScore(Double singleScore) {
		this.singleScore = singleScore;
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