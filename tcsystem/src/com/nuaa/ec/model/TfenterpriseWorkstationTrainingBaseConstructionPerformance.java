package com.nuaa.ec.model;

/**
 * TfenterpriseWorkstationTrainingBaseConstructionPerformance entity. @author
 * MyEclipse Persistence Tools
 */

public class TfenterpriseWorkstationTrainingBaseConstructionPerformance
		implements java.io.Serializable {

	// Fields

	private Integer upid;
	private TfenterpriseWorkstationTrainingBaseConstructionProject tfenterpriseWorkstationTrainingBaseConstructionProject;
	private SelfUndertakeTask selfUndertakeTask;
	private Teacher teacher;
	private Double singleScore;
	private String spareTire;
	private String checkOut;

	// Constructors

	/** default constructor */
	public TfenterpriseWorkstationTrainingBaseConstructionPerformance() {
	}

	/** minimal constructor */
	public TfenterpriseWorkstationTrainingBaseConstructionPerformance(
			Integer upid) {
		this.upid = upid;
	}

	/** full constructor */
	public TfenterpriseWorkstationTrainingBaseConstructionPerformance(
			Integer upid,
			TfenterpriseWorkstationTrainingBaseConstructionProject tfenterpriseWorkstationTrainingBaseConstructionProject,
			SelfUndertakeTask selfUndertakeTask, Teacher teacher,
			Double singleScore, String spareTire, String checkOut) {
		this.upid = upid;
		this.tfenterpriseWorkstationTrainingBaseConstructionProject = tfenterpriseWorkstationTrainingBaseConstructionProject;
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

	public TfenterpriseWorkstationTrainingBaseConstructionProject getTfenterpriseWorkstationTrainingBaseConstructionProject() {
		return this.tfenterpriseWorkstationTrainingBaseConstructionProject;
	}

	public void setTfenterpriseWorkstationTrainingBaseConstructionProject(
			TfenterpriseWorkstationTrainingBaseConstructionProject tfenterpriseWorkstationTrainingBaseConstructionProject) {
		this.tfenterpriseWorkstationTrainingBaseConstructionProject = tfenterpriseWorkstationTrainingBaseConstructionProject;
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