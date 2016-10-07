package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TffamousTeacherTeamRewadLevel entity. @author MyEclipse Persistence Tools
 */

public class TffamousTeacherTeamRewadLevel implements java.io.Serializable {

	// Fields

	private String teachRewardLevelId;
	private TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular;
	private String teachRewardLevelName;
	private Double score;
	private String spareTire;
	private Set tffamousTeacherTeamProjects = new HashSet(0);

	// Constructors

	/** default constructor */
	public TffamousTeacherTeamRewadLevel() {
	}

	/** minimal constructor */
	public TffamousTeacherTeamRewadLevel(String teachRewardLevelId) {
		this.teachRewardLevelId = teachRewardLevelId;
	}

	/** full constructor */
	public TffamousTeacherTeamRewadLevel(String teachRewardLevelId,
			TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular,
			String teachRewardLevelName, Double score, String spareTire,
			Set tffamousTeacherTeamProjects) {
		this.teachRewardLevelId = teachRewardLevelId;
		this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
		this.teachRewardLevelName = teachRewardLevelName;
		this.score = score;
		this.spareTire = spareTire;
		this.tffamousTeacherTeamProjects = tffamousTeacherTeamProjects;
	}

	// Property accessors

	public String getTeachRewardLevelId() {
		return this.teachRewardLevelId;
	}

	public void setTeachRewardLevelId(String teachRewardLevelId) {
		this.teachRewardLevelId = teachRewardLevelId;
	}

	public TftechingAbilityEffectSubModular getTftechingAbilityEffectSubModular() {
		return this.tftechingAbilityEffectSubModular;
	}

	public void setTftechingAbilityEffectSubModular(
			TftechingAbilityEffectSubModular tftechingAbilityEffectSubModular) {
		this.tftechingAbilityEffectSubModular = tftechingAbilityEffectSubModular;
	}

	public String getTeachRewardLevelName() {
		return this.teachRewardLevelName;
	}

	public void setTeachRewardLevelName(String teachRewardLevelName) {
		this.teachRewardLevelName = teachRewardLevelName;
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

	public Set getTffamousTeacherTeamProjects() {
		return this.tffamousTeacherTeamProjects;
	}

	public void setTffamousTeacherTeamProjects(Set tffamousTeacherTeamProjects) {
		this.tffamousTeacherTeamProjects = tffamousTeacherTeamProjects;
	}

}