package com.nuaa.ec.model;

/**
 * VateacherAndCollectiveAct entity. @author MyEclipse Persistence Tools
 */

public class VateacherAndCollectiveAct implements java.io.Serializable {

	// Fields

	private VateacherAndCollectiveActId id;
	private Double score;
	private String spareTire;
	private String aspareTire;
	private String vapid;
	private String teacherid;	

	// Constructors

	/** default constructor */
	public VateacherAndCollectiveAct() {
	}

	/** minimal constructor */
	public VateacherAndCollectiveAct(VateacherAndCollectiveActId id) {
		this.id = id;
	}	
	
	/** private construtr */
	public VateacherAndCollectiveAct(Double score, String spareTire,
			String aspareTire, String vapid, String teacherid) {
		super();
		this.score = score;
		this.spareTire = spareTire;
		this.aspareTire = aspareTire;
		this.vapid = vapid;
		this.teacherid = teacherid;
	}

	/** full constructor */
	public VateacherAndCollectiveAct(VateacherAndCollectiveActId id,
			Double score, String spareTire, String aspareTire) {
		this.id = id;
		this.score = score;
		this.spareTire = spareTire;
		this.aspareTire = aspareTire;
	}

	// Property accessors

	public VateacherAndCollectiveActId getId() {
		return this.id;
	}

	public void setId(VateacherAndCollectiveActId id) {
		this.id = id;
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

	public String getAspareTire() {
		return this.aspareTire;
	}

	public void setAspareTire(String aspareTire) {
		this.aspareTire = aspareTire;
	}

	public String getVapid() {
		return vapid;
	}

	public void setVapid(String vapid) {
		this.vapid = vapid;
	}

	public String getTeacherid() {
		return teacherid;
	}

	public void setTeacherid(String teacherid) {
		this.teacherid = teacherid;
	}

}