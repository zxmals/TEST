package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TfprofessionalProjectDeclareProject entity. @author MyEclipse Persistence
 * Tools
 */

public class TfprofessionalProjectDeclareProject implements
		java.io.Serializable {

	// Fields

	private String projectId;
	private TfprofessionalProjectDeclareLevel tfprofessionalProjectDeclareLevel;
	private Tfterm tfterm;
	private String projectName;
	private Double projectSumScore;
	private String spareTire;
	private String checkOut;
	private String chargePersonId;
	private Set tfprofessionalProjectDeclarePerformances = new HashSet(0);

	// Constructors

	/** default constructor */
	public TfprofessionalProjectDeclareProject() {
	}

	/** minimal constructor */
	public TfprofessionalProjectDeclareProject(String projectId) {
		this.projectId = projectId;
	}

	/** full constructor */
	public TfprofessionalProjectDeclareProject(
			String projectId,
			TfprofessionalProjectDeclareLevel tfprofessionalProjectDeclareLevel,
			Tfterm tfterm, String projectName, Double projectSumScore,
			String spareTire, String checkOut,
			Set tfprofessionalProjectDeclarePerformances) {
		this.projectId = projectId;
		this.tfprofessionalProjectDeclareLevel = tfprofessionalProjectDeclareLevel;
		this.tfterm = tfterm;
		this.projectName = projectName;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
	}

	// Property accessors

	public TfprofessionalProjectDeclareProject(
			String projectId,
			TfprofessionalProjectDeclareLevel tfprofessionalProjectDeclareLevel,
			Tfterm tfterm, String projectName, Double projectSumScore,
			String spareTire, String checkOut, String chargePersonId,
			Set tfprofessionalProjectDeclarePerformances) {
		super();
		this.projectId = projectId;
		this.tfprofessionalProjectDeclareLevel = tfprofessionalProjectDeclareLevel;
		this.tfterm = tfterm;
		this.projectName = projectName;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public TfprofessionalProjectDeclareLevel getTfprofessionalProjectDeclareLevel() {
		return this.tfprofessionalProjectDeclareLevel;
	}

	public void setTfprofessionalProjectDeclareLevel(
			TfprofessionalProjectDeclareLevel tfprofessionalProjectDeclareLevel) {
		this.tfprofessionalProjectDeclareLevel = tfprofessionalProjectDeclareLevel;
	}

	public Tfterm getTfterm() {
		return this.tfterm;
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

	public Double getProjectSumScore() {
		return this.projectSumScore;
	}

	public void setProjectSumScore(Double projectSumScore) {
		this.projectSumScore = projectSumScore;
	}

	public String getChargePersonId() {
		return chargePersonId;
	}

	public void setChargePersonId(String chargePersonId) {
		this.chargePersonId = chargePersonId;
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

	public Set getTfprofessionalProjectDeclarePerformances() {
		return this.tfprofessionalProjectDeclarePerformances;
	}

	public void setTfprofessionalProjectDeclarePerformances(
			Set tfprofessionalProjectDeclarePerformances) {
		this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
	}

}