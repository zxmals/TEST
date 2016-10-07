package com.nuaa.ec.model;

/**
 * TffamousTeacherTeamPerformance entity. @author MyEclipse Persistence Tools
 */

public class TffamousTeacherTeamPerformance implements java.io.Serializable {

	// Fields

	private Integer upid;
	private TffamousTeacherTeamProject tffamousTeacherTeamProject;
	private SelfUndertakeTask selfUndertakeTask;
	private Teacher teacher;
	private Double singelScore;
	private String spareTire;
	private String checkOut;

	// Constructors

	/** default constructor */
	public TffamousTeacherTeamPerformance() {
	}

	/** minimal constructor */
	public TffamousTeacherTeamPerformance(Integer upid,
			TffamousTeacherTeamProject tffamousTeacherTeamProject) {
		this.upid = upid;
		this.tffamousTeacherTeamProject = tffamousTeacherTeamProject;
	}

	/** full constructor */
	public TffamousTeacherTeamPerformance(Integer upid,
			TffamousTeacherTeamProject tffamousTeacherTeamProject,
			SelfUndertakeTask selfUndertakeTask, Teacher teacher,
			Double singelScore, String spareTire, String checkOut) {
		this.upid = upid;
		this.tffamousTeacherTeamProject = tffamousTeacherTeamProject;
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

	public TffamousTeacherTeamProject getTffamousTeacherTeamProject() {
		return this.tffamousTeacherTeamProject;
	}

	public void setTffamousTeacherTeamProject(
			TffamousTeacherTeamProject tffamousTeacherTeamProject) {
		this.tffamousTeacherTeamProject = tffamousTeacherTeamProject;
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