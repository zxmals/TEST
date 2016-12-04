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
	private String chargePersonId;
	private String checkout;
	private String name;
	private String departmentId;
	private String chargePersonName;
	public TffamousTeacherTeamProject(String teacherTeamPerformanceId,
			TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel,
			Double projectSumScore, Tfterm tfterm, String spareTire,
			String chargePersonId, String checkout, String name,
			String departmentId, Set tffamousTeacherTeamPerformances) {
		this.teacherTeamPerformanceId = teacherTeamPerformanceId;
		this.tffamousTeacherTeamRewadLevel = tffamousTeacherTeamRewadLevel;
		this.projectSumScore = projectSumScore;
		this.tfterm = tfterm;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.checkout = checkout;
		this.name = name;
		this.departmentId = departmentId;
		this.tffamousTeacherTeamPerformances = tffamousTeacherTeamPerformances;
	}

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

	
	public TffamousTeacherTeamProject(String teacherTeamPerformanceId,
			TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel,
			Double projectSumScore, Tfterm tfterm, String spareTire,
			String chargePersonId, String checkout, String name,
			String departmentId) {
		super();
		this.teacherTeamPerformanceId = teacherTeamPerformanceId;
		this.tffamousTeacherTeamRewadLevel = tffamousTeacherTeamRewadLevel;
		this.projectSumScore = projectSumScore;
		this.tfterm = tfterm;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.checkout = checkout;
		this.name = name;
		this.departmentId = departmentId;
	}

	public TffamousTeacherTeamProject(String teacherTeamPerformanceId,
			TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel,
			Double projectSumScore, Tfterm tfterm, String spareTire,
			String chargePersonId, String checkout, String name,
			Set tffamousTeacherTeamPerformances) {
		this.teacherTeamPerformanceId = teacherTeamPerformanceId;
		this.tffamousTeacherTeamRewadLevel = tffamousTeacherTeamRewadLevel;
		this.projectSumScore = projectSumScore;
		this.tfterm = tfterm;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.checkout = checkout;
		this.name = name;
		this.tffamousTeacherTeamPerformances = tffamousTeacherTeamPerformances;
	}
	
	// Property accessors

	public TffamousTeacherTeamProject(String teacherTeamPerformanceId,
			TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel,
			Double projectSumScore, Tfterm tfterm, String spareTire,
			String chargePersonId, String checkout, String name,
			String departmentId, String chargePersonName) {
		super();
		this.teacherTeamPerformanceId = teacherTeamPerformanceId;
		this.tffamousTeacherTeamRewadLevel = tffamousTeacherTeamRewadLevel;
		this.projectSumScore = projectSumScore;
		this.tfterm = tfterm;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.checkout = checkout;
		this.name = name;
		this.departmentId = departmentId;
		this.chargePersonName = chargePersonName;
	}

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


	public String getChargePersonName() {
		return chargePersonName;
	}

	public void setChargePersonName(String chargePersonName) {
		this.chargePersonName = chargePersonName;
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

	public String getChargePersonId() {
		return chargePersonId;
	}

	public void setChargePersonId(String chargePersonId) {
		this.chargePersonId = chargePersonId;
	}

	public String getCheckout() {
		return checkout;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}