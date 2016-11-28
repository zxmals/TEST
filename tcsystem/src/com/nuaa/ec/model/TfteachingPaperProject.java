package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TfteachingPaperProject entity. @author MyEclipse Persistence Tools
 */

public class TfteachingPaperProject implements java.io.Serializable {

	// Fields

	private String teachPaperId;
	private Tfterm tfterm;
	private TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition;
	private String teachPaperName;
	private String otherAuthorJoin;
	private Double projectSumScore;
	private String spareTire;
	private String checkOut;
	private String chargePersonId;
	private String departmentId;
	private Set tfteachingPaperPerformances = new HashSet(0);

	// Constructors
	
	/** default constructor */
	public TfteachingPaperProject() {
	}

	public TfteachingPaperProject(
			String teachPaperId,
			Tfterm tfterm,
			TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition,
			String teachPaperName, String otherAuthorJoin,
			Double projectSumScore, String spareTire, String checkOut,
			String chargePersonId, String departmentId,
			Set tfteachingPaperPerformances) {
		this.teachPaperId = teachPaperId;
		this.tfterm = tfterm;
		this.tfteachingPaperRetrievalCondition = tfteachingPaperRetrievalCondition;
		this.teachPaperName = teachPaperName;
		this.otherAuthorJoin = otherAuthorJoin;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.departmentId = departmentId;
		this.tfteachingPaperPerformances = tfteachingPaperPerformances;
	}

	/** minimal constructor */
	public TfteachingPaperProject(String teachPaperId) {
		this.teachPaperId = teachPaperId;
	}

	/** full constructor */
	public TfteachingPaperProject(
			String teachPaperId,
			Tfterm tfterm,
			TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition,
			String teachPaperName, String otherAuthorJoin,
			Double projectSumScore, String spareTire, String checkOut,
			Set tfteachingPaperPerformances) {
		this.teachPaperId = teachPaperId;
		this.tfterm = tfterm;
		this.tfteachingPaperRetrievalCondition = tfteachingPaperRetrievalCondition;
		this.teachPaperName = teachPaperName;
		this.otherAuthorJoin = otherAuthorJoin;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.tfteachingPaperPerformances = tfteachingPaperPerformances;
	}

	// Property accessors

	public TfteachingPaperProject(
			String teachPaperId,
			Tfterm tfterm,
			TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition,
			String teachPaperName, String otherAuthorJoin,
			Double projectSumScore, String spareTire, String checkOut,
			String chargePersonId, Set tfteachingPaperPerformances) {
		super();
		this.teachPaperId = teachPaperId;
		this.tfterm = tfterm;
		this.tfteachingPaperRetrievalCondition = tfteachingPaperRetrievalCondition;
		this.teachPaperName = teachPaperName;
		this.otherAuthorJoin = otherAuthorJoin;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.tfteachingPaperPerformances = tfteachingPaperPerformances;
	}

	public String getTeachPaperId() {
		return this.teachPaperId;
	}

	public void setTeachPaperId(String teachPaperId) {
		this.teachPaperId = teachPaperId;
	}

	public Tfterm getTfterm() {
		return this.tfterm;
	}

	public void setTfterm(Tfterm tfterm) {
		this.tfterm = tfterm;
	}

	public TfteachingPaperRetrievalCondition getTfteachingPaperRetrievalCondition() {
		return this.tfteachingPaperRetrievalCondition;
	}

	public void setTfteachingPaperRetrievalCondition(
			TfteachingPaperRetrievalCondition tfteachingPaperRetrievalCondition) {
		this.tfteachingPaperRetrievalCondition = tfteachingPaperRetrievalCondition;
	}

	public String getChargePersonId() {
		return chargePersonId;
	}

	public void setChargePersonId(String chargePersonId) {
		this.chargePersonId = chargePersonId;
	}

	public String getTeachPaperName() {
		return this.teachPaperName;
	}

	public void setTeachPaperName(String teachPaperName) {
		this.teachPaperName = teachPaperName;
	}

	public String getOtherAuthorJoin() {
		return this.otherAuthorJoin;
	}

	public void setOtherAuthorJoin(String otherAuthorJoin) {
		this.otherAuthorJoin = otherAuthorJoin;
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

	public Set getTfteachingPaperPerformances() {
		return this.tfteachingPaperPerformances;
	}

	public void setTfteachingPaperPerformances(Set tfteachingPaperPerformances) {
		this.tfteachingPaperPerformances = tfteachingPaperPerformances;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}