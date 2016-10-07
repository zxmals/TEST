package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TfteachingRearchEvaluation entity. @author MyEclipse Persistence Tools
 */

public class TfteachingRearchEvaluation implements java.io.Serializable {

	// Fields

	private String evaluationId;
	private TfteachingReformResearchSubModular tfteachingReformResearchSubModular;
	private String reaults;
	private Double ratio;
	private String spareTire;
	private Set tfteachingRearchProjects = new HashSet(0);

	// Constructors

	/** default constructor */
	public TfteachingRearchEvaluation() {
	}

	/** minimal constructor */
	public TfteachingRearchEvaluation(String evaluationId) {
		this.evaluationId = evaluationId;
	}

	/** full constructor */
	public TfteachingRearchEvaluation(
			String evaluationId,
			TfteachingReformResearchSubModular tfteachingReformResearchSubModular,
			String reaults, Double ratio, String spareTire,
			Set tfteachingRearchProjects) {
		this.evaluationId = evaluationId;
		this.tfteachingReformResearchSubModular = tfteachingReformResearchSubModular;
		this.reaults = reaults;
		this.ratio = ratio;
		this.spareTire = spareTire;
		this.tfteachingRearchProjects = tfteachingRearchProjects;
	}

	// Property accessors

	public String getEvaluationId() {
		return this.evaluationId;
	}

	public void setEvaluationId(String evaluationId) {
		this.evaluationId = evaluationId;
	}

	public TfteachingReformResearchSubModular getTfteachingReformResearchSubModular() {
		return this.tfteachingReformResearchSubModular;
	}

	public void setTfteachingReformResearchSubModular(
			TfteachingReformResearchSubModular tfteachingReformResearchSubModular) {
		this.tfteachingReformResearchSubModular = tfteachingReformResearchSubModular;
	}

	public String getReaults() {
		return this.reaults;
	}

	public void setReaults(String reaults) {
		this.reaults = reaults;
	}

	public Double getRatio() {
		return this.ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public String getSpareTire() {
		return this.spareTire;
	}

	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}

	public Set getTfteachingRearchProjects() {
		return this.tfteachingRearchProjects;
	}

	public void setTfteachingRearchProjects(Set tfteachingRearchProjects) {
		this.tfteachingRearchProjects = tfteachingRearchProjects;
	}

}