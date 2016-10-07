package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TftextbookConstructionProject entity. @author MyEclipse Persistence Tools
 */

public class TftextbookConstructionProject implements java.io.Serializable {

	// Fields

	private String bookId;
	private TftextbookConstructionTblevel tftextbookConstructionTblevel;
	private Tfterm tfterm;
	private String bookName;
	private String cooperator;
	private Double projectSumScore;
	private String spareTire;
	private String checkOut;
	private Set tftextbookConstructionPerformances = new HashSet(0);

	// Constructors

	/** default constructor */
	public TftextbookConstructionProject() {
	}

	/** minimal constructor */
	public TftextbookConstructionProject(String bookId) {
		this.bookId = bookId;
	}

	/** full constructor */
	public TftextbookConstructionProject(String bookId,
			TftextbookConstructionTblevel tftextbookConstructionTblevel,
			Tfterm tfterm, String bookName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			Set tftextbookConstructionPerformances) {
		this.bookId = bookId;
		this.tftextbookConstructionTblevel = tftextbookConstructionTblevel;
		this.tfterm = tfterm;
		this.bookName = bookName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
	}

	// Property accessors

	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public TftextbookConstructionTblevel getTftextbookConstructionTblevel() {
		return this.tftextbookConstructionTblevel;
	}

	public void setTftextbookConstructionTblevel(
			TftextbookConstructionTblevel tftextbookConstructionTblevel) {
		this.tftextbookConstructionTblevel = tftextbookConstructionTblevel;
	}

	public Tfterm getTfterm() {
		return this.tfterm;
	}

	public void setTfterm(Tfterm tfterm) {
		this.tfterm = tfterm;
	}

	public String getBookName() {
		return this.bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getCooperator() {
		return this.cooperator;
	}

	public void setCooperator(String cooperator) {
		this.cooperator = cooperator;
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

	public Set getTftextbookConstructionPerformances() {
		return this.tftextbookConstructionPerformances;
	}

	public void setTftextbookConstructionPerformances(
			Set tftextbookConstructionPerformances) {
		this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
	}

}