package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ScientificResearchProject entity. @author MyEclipse Persistence Tools
 */

public class ScientificResearchProject implements java.io.Serializable {

	// Fields

	public String getResearchLabId() {
		return researchLabId;
	}

	public void setResearchLabId(String researchLabId) {
		this.researchLabId = researchLabId;
	}

	private String srprojectId;
	private ProjectType projectType;
	private String srpname;
	private String chargePerson;
	private String projectNumber;
	private String projectSource;
	private String admitedProjectYear;
	private String sumFunds;
	private String spareTire;
	private String chargePersonId;
	private String researchLabId;
	private String checkout;
	private Set teacherAndscientificResearchProjects = new HashSet(0);

	// Constructors

	/** default constructor */
	public ScientificResearchProject() {
	}

	/** minimal constructor */
	public ScientificResearchProject(String srprojectId) {
		this.srprojectId = srprojectId;
	}

	/** full constructor */
	public ScientificResearchProject(String srprojectId,
			ProjectType projectType, String srpname, String chargePerson,
			String projectNumber, String projectSource,
			String admitedProjectYear, String sumFunds, String spareTire,
			String chargePersonId, String checkout,
			Set teacherAndscientificResearchProjects) {
		this.srprojectId = srprojectId;
		this.projectType = projectType;
		this.srpname = srpname;
		this.chargePerson = chargePerson;
		this.projectNumber = projectNumber;
		this.projectSource = projectSource;
		this.admitedProjectYear = admitedProjectYear;
		this.sumFunds = sumFunds;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.checkout = checkout;
		this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
	}

	// Property accessors

	public ScientificResearchProject(String srprojectId,
			ProjectType projectType, String srpname, String chargePerson,
			String projectNumber, String projectSource,
			String admitedProjectYear, String sumFunds, String spareTire,
			String chargePersonId, String researchLabId, String checkout,
			Set teacherAndscientificResearchProjects) {
		this.srprojectId = srprojectId;
		this.projectType = projectType;
		this.srpname = srpname;
		this.chargePerson = chargePerson;
		this.projectNumber = projectNumber;
		this.projectSource = projectSource;
		this.admitedProjectYear = admitedProjectYear;
		this.sumFunds = sumFunds;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.researchLabId = researchLabId;
		this.checkout = checkout;
		this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
	}

	public String getSrprojectId() {
		return this.srprojectId;
	}

	public void setSrprojectId(String srprojectId) {
		this.srprojectId = srprojectId;
	}

	public ProjectType getProjectType() {
		return this.projectType;
	}

	public void setProjectType(ProjectType projectType) {
		this.projectType = projectType;
	}

	public String getSrpname() {
		return this.srpname;
	}

	public void setSrpname(String srpname) {
		this.srpname = srpname;
	}

	public String getChargePerson() {
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getProjectNumber() {
		return this.projectNumber;
	}

	public void setProjectNumber(String projectNumber) {
		this.projectNumber = projectNumber;
	}

	public String getProjectSource() {
		return this.projectSource;
	}

	public void setProjectSource(String projectSource) {
		this.projectSource = projectSource;
	}

	public String getAdmitedProjectYear() {
		return this.admitedProjectYear;
	}

	public void setAdmitedProjectYear(String admitedProjectYear) {
		this.admitedProjectYear = admitedProjectYear;
	}

	public String getSumFunds() {
		return this.sumFunds;
	}

	public void setSumFunds(String sumFunds) {
		this.sumFunds = sumFunds;
	}

	public String getSpareTire() {
		return this.spareTire;
	}

	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}

	public String getChargePersonId() {
		return this.chargePersonId;
	}

	public void setChargePersonId(String chargePersonId) {
		this.chargePersonId = chargePersonId;
	}

	public String getCheckout() {
		return this.checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public Set getTeacherAndscientificResearchProjects() {
		return this.teacherAndscientificResearchProjects;
	}

	public void setTeacherAndscientificResearchProjects(
			Set teacherAndscientificResearchProjects) {
		this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
	}

}