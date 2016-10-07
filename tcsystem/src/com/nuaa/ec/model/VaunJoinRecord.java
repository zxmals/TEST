package com.nuaa.ec.model;

/**
 * VaunJoinRecord entity. @author MyEclipse Persistence Tools
 */

public class VaunJoinRecord implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4985914710399952724L;
	private String unjoinId;
	private String teacherId;
	private String actId;
	private String actName;
	private String actDate;
	private String actAttendee;
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

	public VaunJoinRecord(String unjoinId, String teacherId, String actId,
			String actName, String actDate, String actAttendee,
			String unjoinreason, String leavereqobtain, Double resultscore,
			String asparetire) {
		super();
		this.unjoinId = unjoinId;
		this.teacherId = teacherId;
		this.actId = actId;
		this.actName = actName;
		this.actDate = actDate;
		this.actAttendee = actAttendee;
		this.unjoinreason = unjoinreason;
		this.leavereqobtain = leavereqobtain;
		this.resultscore = resultscore;
		this.asparetire = asparetire;
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

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActDate() {
		return actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
	}

	public String getActAttendee() {
		return actAttendee;
	}

	public void setActAttendee(String actAttendee) {
		this.actAttendee = actAttendee;
	}

}