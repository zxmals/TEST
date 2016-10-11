package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TffamousTeacherTeamProject entity. @author MyEclipse Persistence Tools
 */

public class TffamousTeacherTeamProject implements java.io.Serializable {

	// Fields

	private String teacherTeamPerformanceId;
	private TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel;
	private Double projectSumScore;
//	private String termId;
	private Tfterm tfterm;
	private String spareTire;
	private Set tffamousTeacherTeamPerformances = new HashSet(0);

	// Constructors

	/** default constructor */
	public TffamousTeacherTeamProject() {
	}

	/** minimal constructor */
	public TffamousTeacherTeamProject(String teacherTeamPerformanceId) {
		this.teacherTeamPerformanceId = teacherTeamPerformanceId;
	}

	/** full constructor */
	public TffamousTeacherTeamProject(String teacherTeamPerformanceId,
			TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel,
			Double projectSumScore, Tfterm tfterm, String spareTire,
			Set tffamousTeacherTeamPerformances) {
		this.teacherTeamPerformanceId = teacherTeamPerformanceId;
		this.tffamousTeacherTeamRewadLevel = tffamousTeacherTeamRewadLevel;
		this.projectSumScore = projectSumScore;
		this.tfterm = tfterm;
		this.spareTire = spareTire;
		this.tffamousTeacherTeamPerformances = tffamousTeacherTeamPerformances;
	}

	// Property accessors

	public String getTeacherTeamPerformanceId() {
		return this.teacherTeamPerformanceId;
	}

	public void setTeacherTeamPerformanceId(String teacherTeamPerformanceId) {
		this.teacherTeamPerformanceId = teacherTeamPerformanceId;
	}

	public TffamousTeacherTeamRewadLevel getTffamousTeacherTeamRewadLevel() {
		return this.tffamousTeacherTeamRewadLevel;
	}

	public void setTffamousTeacherTeamRewadLevel(
			TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel) {
		this.tffamousTeacherTeamRewadLevel = tffamousTeacherTeamRewadLevel;
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

	public Set getTffamousTeacherTeamPerformances() {
		return this.tffamousTeacherTeamPerformances;
	}

	public void setTffamousTeacherTeamPerformances(
			Set tffamousTeacherTeamPerformances) {
		this.tffamousTeacherTeamPerformances = tffamousTeacherTeamPerformances;
	}

	public Tfterm getTfterm() {
		return tfterm;
	}

	public void setTfterm(Tfterm tfterm) {
		this.tfterm = tfterm;
	}

}