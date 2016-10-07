package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TfteachingRearchFundlevel entity. @author MyEclipse Persistence Tools
 */

public class TfteachingRearchFundlevel implements java.io.Serializable {

	// Fields

	private String fundLevelId;
	private TfteachingReformResearchSubModular tfteachingReformResearchSubModular;
	private String fundLevel;
	private Double score;
	private String spareTire;
	private Set tfteachingRearchProjects = new HashSet(0);

	// Constructors

	/** default constructor */
	public TfteachingRearchFundlevel() {
	}

	/** minimal constructor */
	public TfteachingRearchFundlevel(String fundLevelId) {
		this.fundLevelId = fundLevelId;
	}

	/** full constructor */
	public TfteachingRearchFundlevel(
			String fundLevelId,
			TfteachingReformResearchSubModular tfteachingReformResearchSubModular,
			String fundLevel, Double score, String spareTire,
			Set tfteachingRearchProjects) {
		this.fundLevelId = fundLevelId;
		this.tfteachingReformResearchSubModular = tfteachingReformResearchSubModular;
		this.fundLevel = fundLevel;
		this.score = score;
		this.spareTire = spareTire;
		this.tfteachingRearchProjects = tfteachingRearchProjects;
	}

	// Property accessors

	public String getFundLevelId() {
		return this.fundLevelId;
	}

	public void setFundLevelId(String fundLevelId) {
		this.fundLevelId = fundLevelId;
	}

	public TfteachingReformResearchSubModular getTfteachingReformResearchSubModular() {
		return this.tfteachingReformResearchSubModular;
	}

	public void setTfteachingReformResearchSubModular(
			TfteachingReformResearchSubModular tfteachingReformResearchSubModular) {
		this.tfteachingReformResearchSubModular = tfteachingReformResearchSubModular;
	}

	public String getFundLevel() {
		return this.fundLevel;
	}

	public void setFundLevel(String fundLevel) {
		this.fundLevel = fundLevel;
	}

	public Double getScore() {
		return this.score;
	}

	public void setScore(Double score) {
		this.score = score;
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