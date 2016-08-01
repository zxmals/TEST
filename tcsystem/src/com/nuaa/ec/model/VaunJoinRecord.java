package com.nuaa.ec.model;

/**
 * VaunJoinRecord entity. @author MyEclipse Persistence Tools
 */

public class VaunJoinRecord implements java.io.Serializable {

	// Fields

	private String unjoinId;
	private String teacherId;
	private String actId;
	private String unjoinreason;
	private String leavereqobtain;
	private Double resultscore;
	private String sparetire;
	private String asparetire;

	// Constructors

	/** default constructor */
	public VaunJoinRecord() {
	}

	/** minimal constructor */
	public VaunJoinRecord(String unjoinId, String teacherId, String actId,
			String leavereqobtain) {
		this.unjoinId = unjoinId;
		this.teacherId = teacherId;
		this.actId = actId;
		this.leavereqobtain = leavereqobtain;
	}

	/** full constructor */
	public VaunJoinRecord(String unjoinId, String teacherId, String actId,
			String unjoinreason, String leavereqobtain, Double resultscore,
			String sparetire, String asparetire) {
		this.unjoinId = unjoinId;
		this.teacherId = teacherId;
		this.actId = actId;
		this.unjoinreason = unjoinreason;
		this.leavereqobtain = leavereqobtain;
		this.resultscore = resultscore;
		this.sparetire = sparetire;
		this.asparetire = asparetire;
	}

	// Property accessors

	public String getUnjoinId() {
		return this.unjoinId;
	}

	public void setUnjoinId(String unjoinId) {
		this.unjoinId = unjoinId;
	}

	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getActId() {
		return this.actId;
	}

	public void setActId(String actId) {
		this.actId = actId;
	}

	public String getUnjoinreason() {
		return this.unjoinreason;
	}

	public void setUnjoinreason(String unjoinreason) {
		this.unjoinreason = unjoinreason;
	}

	public String getLeavereqobtain() {
		return this.leavereqobtain;
	}

	public void setLeavereqobtain(String leavereqobtain) {
		this.leavereqobtain = leavereqobtain;
	}

	public Double getResultscore() {
		return this.resultscore;
	}

	public void setResultscore(Double resultscore) {
		this.resultscore = resultscore;
	}

	public String getSparetire() {
		return this.sparetire;
	}

	public void setSparetire(String sparetire) {
		this.sparetire = sparetire;
	}

	public String getAsparetire() {
		return this.asparetire;
	}

	public void setAsparetire(String asparetire) {
		this.asparetire = asparetire;
	}

}