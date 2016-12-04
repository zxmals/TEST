package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * TfteachingAchievementProject entity. @author MyEclipse Persistence Tools
 */

public class TfteachingAchievementProject implements java.io.Serializable {

	// Fields

	private String projectId;
	private TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel;
	private Tfterm tfterm;
	private String projectName;
	private String cooperator;
	private Double projectSumScore;
	private String spareTire;
	private String checkOut;
	private String chargePersonId;
	private String chargePersonName;
	private Set tfteachingAchievementPerformances = new HashSet(0);
	private String departmentId;

	public TfteachingAchievementProject(String projectId,
			TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel,
			Tfterm tfterm, String projectName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			String chargePersonId, Set tfteachingAchievementPerformances,
			String departmentId) {
		this.projectId = projectId;
		this.tfteachingAchievementRewardLevel = tfteachingAchievementRewardLevel;
		this.tfterm = tfterm;
		this.projectName = projectName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
		this.departmentId = departmentId;
	}

	// Constructors

	/** default constructor */
	public TfteachingAchievementProject() {
	}

	/** minimal constructor */
	public TfteachingAchievementProject(String projectId) {
		this.projectId = projectId;
	}

	/** full constructor */
	public TfteachingAchievementProject(String projectId,
			TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel,
			Tfterm tfterm, String projectName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			Set tfteachingAchievementPerformances) {
		this.projectId = projectId;
		this.tfteachingAchievementRewardLevel = tfteachingAchievementRewardLevel;
		this.tfterm = tfterm;
		this.projectName = projectName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
	}

	// Property accessors

	public TfteachingAchievementProject(String projectId,
			TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel,
			Tfterm tfterm, String projectName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			String chargePersonId, Set tfteachingAchievementPerformances) {
		this.projectId = projectId;
		this.tfteachingAchievementRewardLevel = tfteachingAchievementRewardLevel;
		this.tfterm = tfterm;
		this.projectName = projectName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
	}

	public TfteachingAchievementProject(String projectId,
			TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel,
			Tfterm tfterm, String projectName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			String chargePersonId, String chargePersonName, String departmentId) {
		super();
		this.projectId = projectId;
		this.tfteachingAchievementRewardLevel = tfteachingAchievementRewardLevel;
		this.tfterm = tfterm;
		this.projectName = projectName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.chargePersonName = chargePersonName;
		this.departmentId = departmentId;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getChargePersonName() {
		return chargePersonName;
	}

	public void setChargePersonName(String chargePersonName) {
		this.chargePersonName = chargePersonName;
	}

	public TfteachingAchievementRewardLevel getTfteachingAchievementRewardLevel() {
		return this.tfteachingAchievementRewardLevel;
	}

	public void setTfteachingAchievementRewardLevel(
			TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel) {
		this.tfteachingAchievementRewardLevel = tfteachingAchievementRewardLevel;
	}

	public Tfterm getTfterm() {
		return this.tfterm;
	}

	public String getChargePersonId() {
		return chargePersonId;
	}

	public void setChargePersonId(String chargePersonId) {
		this.chargePersonId = chargePersonId;
	}

	public void setTfterm(Tfterm tfterm) {
		this.tfterm = tfterm;
	}

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	public Set getTfteachingAchievementPerformances() {
		return this.tfteachingAchievementPerformances;
	}

	public void setTfteachingAchievementPerformances(
			Set tfteachingAchievementPerformances) {
		this.tfteachingAchievementPerformances = tfteachingAchievementPerformances;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}