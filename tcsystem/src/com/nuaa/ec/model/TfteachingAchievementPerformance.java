package com.nuaa.ec.model;

/**
 * TfteachingAchievementPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfteachingAchievementPerformance implements java.io.Serializable {

	// Fields

	private Integer upid;
	private TfteachingAchievementProject tfteachingAchievementProject;
	private SelfUndertakeTask selfUndertakeTask;
	private Teacher teacher;
	private Double singelScore;
	private String spareTire;
	private String checkOut;

	// Constructors

	/** default constructor */
	public TfteachingAchievementPerformance() {
	}

	/** minimal constructor */
	public TfteachingAchievementPerformance(Integer upid) {
		this.upid = upid;
	}

	/** full constructor */
	public TfteachingAchievementPerformance(Integer upid,
			TfteachingAchievementProject tfteachingAchievementProject,
			SelfUndertakeTask selfUndertakeTask, Teacher teacher,
			Double singelScore, String spareTire, String checkOut) {
		this.upid = upid;
		this.tfteachingAchievementProject = tfteachingAchievementProject;
		this.selfUndertakeTask = selfUndertakeTask;
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

	public TfteachingAchievementProject getTfteachingAchievementProject() {
		return this.tfteachingAchievementProject;
	}

	public void setTfteachingAchievementProject(
			TfteachingAchievementProject tfteachingAchievementProject) {
		this.tfteachingAchievementProject = tfteachingAchievementProject;
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