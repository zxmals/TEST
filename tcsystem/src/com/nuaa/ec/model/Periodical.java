package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * Periodical entity. @author MyEclipse Persistence Tools
 */

public class Periodical  implements java.io.Serializable {


    // Fields    

     private String periodicalId;
     private PeriodicalType periodicalType;
     private String periodicalName;
     private String spareTire;
     private String researchLabId;
     public Periodical(String periodicalId, PeriodicalType periodicalType,
			String periodicalName, String spareTire, String researchLabId,
			Set periodicalPaperses, Set teacherAndperiodicals) {
		this.periodicalId = periodicalId;
		this.periodicalType = periodicalType;
		this.periodicalName = periodicalName;
		this.spareTire = spareTire;
		this.researchLabId = researchLabId;
		this.periodicalPaperses = periodicalPaperses;
		this.teacherAndperiodicals = teacherAndperiodicals;
	}

	private Set periodicalPaperses = new HashSet(0);
     private Set teacherAndperiodicals = new HashSet(0);


    // Constructors

    /** default constructor */
    public Periodical() {
    }

	/** minimal constructor */
    public Periodical(String periodicalId) {
        this.periodicalId = periodicalId;
    }
    
    /** full constructor */
    public Periodical(String periodicalId, PeriodicalType periodicalType, String periodicalName, String spareTire, Set periodicalPaperses, Set teacherAndperiodicals) {
        this.periodicalId = periodicalId;
        this.periodicalType = periodicalType;
        this.periodicalName = periodicalName;
        this.spareTire = spareTire;
        this.periodicalPaperses = periodicalPaperses;
        this.teacherAndperiodicals = teacherAndperiodicals;
    }

   
    // Property accessors

    public String getPeriodicalId() {
        return this.periodicalId;
    }
    
    public void setPeriodicalId(String periodicalId) {
        this.periodicalId = periodicalId;
    }

    public PeriodicalType getPeriodicalType() {
        return this.periodicalType;
    }
    
    public void setPeriodicalType(PeriodicalType periodicalType) {
        this.periodicalType = periodicalType;
    }

    public String getPeriodicalName() {
        return this.periodicalName;
    }
    
    public void setPeriodicalName(String periodicalName) {
        this.periodicalName = periodicalName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getPeriodicalPaperses() {
        return this.periodicalPaperses;
    }
    
    public void setPeriodicalPaperses(Set periodicalPaperses) {
        this.periodicalPaperses = periodicalPaperses;
    }

    public Set getTeacherAndperiodicals() {
        return this.teacherAndperiodicals;
    }
    
    public void setTeacherAndperiodicals(Set teacherAndperiodicals) {
        this.teacherAndperiodicals = teacherAndperiodicals;
    }

	public String getResearchLabId() {
		return researchLabId;
	}

	public void setResearchLabId(String researchLabId) {
		this.researchLabId = researchLabId;
	}
   








}