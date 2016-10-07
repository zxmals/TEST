package com.nuaa.ec.model;

/**
 * TfteachingRearchPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfteachingRearchPerformance implements java.io.Serializable {

	// Fields

	private Integer upid;
	private TfteachingRearchProject tfteachingRearchProject;
	private Teacher teacher;
	private String spareTire;
	private Double finalScore;
	private String checkOut;

	// Constructors

	/** default constructor */
	public TfteachingRearchPerformance() {
	}

	/** minimal constructor */
	public TfteachingRearchPerformance(Integer upid, Teacher teacher) {
		this.upid = upid;
		this.teacher = teacher;
	}

	/** full constructor */
	public TfteachingRearchPerformance(Integer upid,
			TfteachingRearchProject tfteachingRearchProject, Teacher teacher,
			String spareTire, Double finalScore, String checkOut) {
		this.upid = upid;
		this.tfteachingRearchProject = tfteachingRearchProject;
		this.teacher = teacher;
		this.spareTire = spareTire;
		this.finalScore = finalScore;
		this.checkOut = checkOut;
	}

	// Property accessors

	public Integer getUpid() {
		return this.upid;
	}

	public void setUpid(Integer upid) {
		this.upid = upid;
	}

	public TfteachingRearchProject getTfteachingRearchProject() {
		return this.tfteachingRearchProject;
	}

	public void setTfteachingRearchProject(
			TfteachingRearchProject tfteachingRearchProject) {
		this.tfteachingRearchProject = tfteachingRearchProject;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public String getSpareTire() {
		return this.spareTire;
	}

	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}

	public Double getFinalScore() {
		return this.finalScore;
	}

	public void setFinalScore(Double finalScore) {
		this.finalScore = finalScore;
	}

	public String getCheckOut() {
		return this.checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

}