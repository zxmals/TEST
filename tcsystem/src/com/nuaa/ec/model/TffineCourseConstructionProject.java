package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TffineCourseConstructionProject entity. @author MyEclipse Persistence Tools
 */

public class TffineCourseConstructionProject implements java.io.Serializable {

	// Fields

	private String courseId;
	private TffineCourseConstructionLevel tffineCourseConstructionLevel;
	private Tfterm tfterm;
	private String courseName;
	private String cooperator;
	private Double projectSumScore;
	private String spareTire;
	private String checkOut;
	private Set tffineCourseConstructionPerformances = new HashSet(0);

	// Constructors

	/** default constructor */
	public TffineCourseConstructionProject() {
	}

	/** minimal constructor */
	public TffineCourseConstructionProject(String courseId) {
		this.courseId = courseId;
	}

	/** full constructor */
	public TffineCourseConstructionProject(String courseId,
			TffineCourseConstructionLevel tffineCourseConstructionLevel,
			Tfterm tfterm, String courseName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			Set tffineCourseConstructionPerformances) {
		this.courseId = courseId;
		this.tffineCourseConstructionLevel = tffineCourseConstructionLevel;
		this.tfterm = tfterm;
		this.courseName = courseName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.tffineCourseConstructionPerformances = tffineCourseConstructionPerformances;
	}

	// Property accessors

	public String getCourseId() {
		return this.courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public TffineCourseConstructionLevel getTffineCourseConstructionLevel() {
		return this.tffineCourseConstructionLevel;
	}

	public void setTffineCourseConstructionLevel(
			TffineCourseConstructionLevel tffineCourseConstructionLevel) {
		this.tffineCourseConstructionLevel = tffineCourseConstructionLevel;
	}

	public Tfterm getTfterm() {
		return this.tfterm;
	}

	public void setTfterm(Tfterm tfterm) {
		this.tfterm = tfterm;
	}

	public String getCourseName() {
		return this.courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCooperator() {
		return this.cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
	}

	public Double getProjectSumScore() {
		return this.projectSumScore;
	}

	public void setProjectSumScore(Double projectSumScore) {
		this.projectSumScore = projectSumScore;
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

	public Set getTffineCourseConstructionPerformances() {
		return this.tffineCourseConstructionPerformances;
	}

	public void setTffineCourseConstructionPerformances(
			Set tffineCourseConstructionPerformances) {
		this.tffineCourseConstructionPerformances = tffineCourseConstructionPerformances;
	}

}