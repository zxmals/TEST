package com.nuaa.ec.model;

/**
 * VaunJoinRecord entity. @author MyEclipse Persistence Tools
 */

public class VaunJoinRecord implements java.io.Serializable {

	// Fields

	private String unjoinId;
	private String actId;
	private String unjoinreason;
	private String leavereqobtain;
	private Double resultscore;
	private String sparetire;

	// Constructors

	/** default constructor */
	public VaunJoinRecord() {
	}

	/** minimal constructor */
	public VaunJoinRecord(String unjoinId) {
		this.unjoinId = unjoinId;
	}

	/** full constructor */
	public VaunJoinRecord(String unjoinId, String actId, String unjoinreason,
			String leavereqobtain, Double resultscore, String sparetire) {
		this.unjoinId = unjoinId;
		this.actId = actId;
		this.unjoinreason = unjoinreason;
		this.leavereqobtain = leavereqobtain;
		this.resultscore = resultscore;
		this.sparetire = sparetire;
	}

	// Property accessors

	public String getUnjoinId() {
		return this.unjoinId;
	}

	public void setUnjoinId(String unjoinId) {
		this.unjoinId = unjoinId;
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

}