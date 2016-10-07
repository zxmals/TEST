package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TfenterpriseWorkstationTrainingBaseConstructionProject entity. @author
 * MyEclipse Persistence Tools
 */

public class TfenterpriseWorkstationTrainingBaseConstructionProject implements
		java.io.Serializable {

	// Fields

	private String projectId;
	private TfenterpriseWorkstationTrainingbaseConstructionLevel tfenterpriseWorkstationTrainingbaseConstructionLevel;
	private Tfterm tfterm;
	private String projectName;
	private Double projectSumScore;
	private String quantityUnit;
	private String spareTire;
	private String checkOut;
	private Set tfenterpriseWorkstationTrainingBaseConstructionPerformances = new HashSet(
			0);

	// Constructors

	/** default constructor */
	public TfenterpriseWorkstationTrainingBaseConstructionProject() {
	}

	/** minimal constructor */
	public TfenterpriseWorkstationTrainingBaseConstructionProject(
			String projectId) {
		this.projectId = projectId;
	}

	/** full constructor */
	public TfenterpriseWorkstationTrainingBaseConstructionProject(
			String projectId,
			TfenterpriseWorkstationTrainingbaseConstructionLevel tfenterpriseWorkstationTrainingbaseConstructionLevel,
			Tfterm tfterm, String projectName, Double projectSumScore,
			String quantityUnit, String spareTire, String checkOut,
			Set tfenterpriseWorkstationTrainingBaseConstructionPerformances) {
		this.projectId = projectId;
		this.tfenterpriseWorkstationTrainingbaseConstructionLevel = tfenterpriseWorkstationTrainingbaseConstructionLevel;
		this.tfterm = tfterm;
		this.projectName = projectName;
		this.projectSumScore = projectSumScore;
		this.quantityUnit = quantityUnit;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.tfenterpriseWorkstationTrainingBaseConstructionPerformances = tfenterpriseWorkstationTrainingBaseConstructionPerformances;
	}

	// Property accessors

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public TfenterpriseWorkstationTrainingbaseConstructionLevel getTfenterpriseWorkstationTrainingbaseConstructionLevel() {
		return this.tfenterpriseWorkstationTrainingbaseConstructionLevel;
	}

	public void setTfenterpriseWorkstationTrainingbaseConstructionLevel(
			TfenterpriseWorkstationTrainingbaseConstructionLevel tfenterpriseWorkstationTrainingbaseConstructionLevel) {
		this.tfenterpriseWorkstationTrainingbaseConstructionLevel = tfenterpriseWorkstationTrainingbaseConstructionLevel;
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

	public String getQuantityUnit() {
		return this.quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
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

	public Set getTfenterpriseWorkstationTrainingBaseConstructionPerformances() {
		return this.tfenterpriseWorkstationTrainingBaseConstructionPerformances;
	}

	public void setTfenterpriseWorkstationTrainingBaseConstructionPerformances(
			Set tfenterpriseWorkstationTrainingBaseConstructionPerformances) {
		this.tfenterpriseWorkstationTrainingBaseConstructionPerformances = tfenterpriseWorkstationTrainingBaseConstructionPerformances;
	}

}