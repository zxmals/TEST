package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TfteachingRearchProject entity. @author MyEclipse Persistence Tools
 */

public class TfteachingRearchProject implements java.io.Serializable {

	// Fields

	private String projectId;
	private TfteachingRearchEvaluation tfteachingRearchEvaluation;
	private Tfterm tfterm;
	private TfteachingRearchFundlevel tfteachingRearchFundlevel;
	private String project;
	private String spareTire;
	private String checkOut;
	private Double projetScore;
	private Set tfteachingRearchPerformances = new HashSet(0);

	// Constructors

	/** default constructor */
	public TfteachingRearchProject() {
	}

	/** minimal constructor */
	public TfteachingRearchProject(String projectId) {
		this.projectId = projectId;
	}

	/** full constructor */
	public TfteachingRearchProject(String projectId,
			TfteachingRearchEvaluation tfteachingRearchEvaluation,
			Tfterm tfterm, TfteachingRearchFundlevel tfteachingRearchFundlevel,
			String project, String spareTire, String checkOut,
			Double projetScore, Set tfteachingRearchPerformances) {
		this.projectId = projectId;
		this.tfteachingRearchEvaluation = tfteachingRearchEvaluation;
		this.tfterm = tfterm;
		this.tfteachingRearchFundlevel = tfteachingRearchFundlevel;
		this.project = project;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.projetScore = projetScore;
		this.tfteachingRearchPerformances = tfteachingRearchPerformances;
	}

	// Property accessors

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public TfteachingRearchEvaluation getTfteachingRearchEvaluation() {
		return this.tfteachingRearchEvaluation;
	}

	public void setTfteachingRearchEvaluation(
			TfteachingRearchEvaluation tfteachingRearchEvaluation) {
		this.tfteachingRearchEvaluation = tfteachingRearchEvaluation;
	}

	public Tfterm getTfterm() {
		return this.tfterm;
	}

	public void setTfterm(Tfterm tfterm) {
		this.tfterm = tfterm;
	}

	public TfteachingRearchFundlevel getTfteachingRearchFundlevel() {
		return this.tfteachingRearchFundlevel;
	}

	public void setTfteachingRearchFundlevel(
			TfteachingRearchFundlevel tfteachingRearchFundlevel) {
		this.tfteachingRearchFundlevel = tfteachingRearchFundlevel;
	}

	public String getProject() {
		return this.project;
	}

	public void setProject(String project) {
		this.project = project;
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

	public Double getProjetScore() {
		return this.projetScore;
	}

	public void setProjetScore(Double projetScore) {
		this.projetScore = projetScore;
	}

	public Set getTfteachingRearchPerformances() {
		return this.tfteachingRearchPerformances;
	}

	public void setTfteachingRearchPerformances(Set tfteachingRearchPerformances) {
		this.tfteachingRearchPerformances = tfteachingRearchPerformances;
	}

}