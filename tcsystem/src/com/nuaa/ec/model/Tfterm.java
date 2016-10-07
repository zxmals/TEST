package com.nuaa.ec.model;

/**
 * Tfterm entity. @author MyEclipse Persistence Tools
 */

public class Tfterm implements java.io.Serializable {

	// Fields

	private String termId;
	private String term;
	private String spareTire;

	// Constructors

	/** default constructor */
	public Tfterm() {
	}

	/** minimal constructor */
	public Tfterm(String termId) {
		this.termId = termId;
	}

	/** full constructor */
	public Tfterm(String termId, String term, String spareTire) {
		this.termId = termId;
		this.term = term;
		this.spareTire = spareTire;
	}

	// Property accessors

	public String getTermId() {
		return this.termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public String getTerm() {
		return this.term;
	}

	public void setTerm(String term) {
		this.term = term;
	}

	public String getSpareTire() {
		return this.spareTire;
	}

	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}

}