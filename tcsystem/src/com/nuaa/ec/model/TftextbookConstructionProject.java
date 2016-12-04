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
	private String chargePersonId;
	private String chargePersonName;
	private Set tftextbookConstructionPerformances = new HashSet(0);
    private String departmentId;
	public TftextbookConstructionProject(String bookId,
			TftextbookConstructionTblevel tftextbookConstructionTblevel,
			Tfterm tfterm, String bookName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			String chargePersonId, Set tftextbookConstructionPerformances,
			String departmentId) {
		this.bookId = bookId;
		this.tftextbookConstructionTblevel = tftextbookConstructionTblevel;
		this.tfterm = tfterm;
		this.bookName = bookName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
		this.departmentId = departmentId;
	}

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

	public TftextbookConstructionProject(String bookId,
			TftextbookConstructionTblevel tftextbookConstructionTblevel,
			Tfterm tfterm, String bookName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			String chargePersonId, Set tftextbookConstructionPerformances) {
		super();
		this.bookId = bookId;
		this.tftextbookConstructionTblevel = tftextbookConstructionTblevel;
		this.tfterm = tfterm;
		this.bookName = bookName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.tftextbookConstructionPerformances = tftextbookConstructionPerformances;
	}
	
	public TftextbookConstructionProject(String bookId,
			TftextbookConstructionTblevel tftextbookConstructionTblevel,
			Tfterm tfterm, String bookName, String cooperator,
			Double projectSumScore, String spareTire, String checkOut,
			String chargePersonId, String chargePersonName, String departmentId) {
		super();
		this.bookId = bookId;
		this.tftextbookConstructionTblevel = tftextbookConstructionTblevel;
		this.tfterm = tfterm;
		this.bookName = bookName;
		this.cooperator = cooperator;
		this.projectSumScore = projectSumScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.chargePersonId = chargePersonId;
		this.chargePersonName = chargePersonName;
		this.departmentId = departmentId;
	}

	public String getChargePersonName() {
		return chargePersonName;
	}

	public void setChargePersonName(String chargePersonName) {
		this.chargePersonName = chargePersonName;
	}

	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getChargePersonId() {
		return chargePersonId;
	}

	public void setChargePersonId(String chargePersonId) {
		this.chargePersonId = chargePersonId;
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

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

}