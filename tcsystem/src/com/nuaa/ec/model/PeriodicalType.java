package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * PeriodicalType entity. @author MyEclipse Persistence Tools
 */

public class PeriodicalType  implements java.io.Serializable {


    // Fields    

     private String ptypeId;
     private String ptypeName;
     private String spareTire;
     private Set periodicalPapersScores = new HashSet(0);
     private Set periodicals = new HashSet(0);


    // Constructors

    /** default constructor */
    public PeriodicalType() {
    }

	/** minimal constructor */
    public PeriodicalType(String ptypeId) {
        this.ptypeId = ptypeId;
    }
    
    /** full constructor */
    public PeriodicalType(String ptypeId, String ptypeName, String spareTire, Set periodicalPapersScores, Set periodicals) {
        this.ptypeId = ptypeId;
        this.ptypeName = ptypeName;
        this.spareTire = spareTire;
        this.periodicalPapersScores = periodicalPapersScores;
        this.periodicals = periodicals;
    }

   
    // Property accessors

    public String getPtypeId() {
        return this.ptypeId;
    }
    
    public void setPtypeId(String ptypeId) {
        this.ptypeId = ptypeId;
    }

    public String getPtypeName() {
        return this.ptypeName;
    }
    
    public void setPtypeName(String ptypeName) {
        this.ptypeName = ptypeName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getPeriodicalPapersScores() {
        return this.periodicalPapersScores;
    }
    
    public void setPeriodicalPapersScores(Set periodicalPapersScores) {
        this.periodicalPapersScores = periodicalPapersScores;
    }

    public Set getPeriodicals() {
        return this.periodicals;
    }
    
    public void setPeriodicals(Set periodicals) {
        this.periodicals = periodicals;
    }
   








}