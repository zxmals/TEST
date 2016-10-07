package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * PeriodicalPapersScore entity. @author MyEclipse Persistence Tools
 */

public class PeriodicalPapersScore  implements java.io.Serializable {


    // Fields    

     private String scoreId;
     private SubModular subModular;
     private PeriodicalType periodicalType;
     private float score;
     private String spareTire;
     private Set teacherAndperiodicals = new HashSet(0);


    // Constructors

    /** default constructor */
    public PeriodicalPapersScore() {
    }

	/** minimal constructor */
    public PeriodicalPapersScore(String scoreId) {
        this.scoreId = scoreId;
    }
    
    /** full constructor */
    public PeriodicalPapersScore(String scoreId, SubModular subModular, PeriodicalType periodicalType, Long score, String spareTire, Set teacherAndperiodicals) {
        this.scoreId = scoreId;
        this.subModular = subModular;
        this.periodicalType = periodicalType;
        this.score = score;
        this.spareTire = spareTire;
        this.teacherAndperiodicals = teacherAndperiodicals;
    }

   
    // Property accessors

    public String getScoreId() {
        return this.scoreId;
    }
    
    public void setScoreId(String scoreId) {
        this.scoreId = scoreId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public PeriodicalType getPeriodicalType() {
        return this.periodicalType;
    }
    
    public void setPeriodicalType(PeriodicalType periodicalType) {
        this.periodicalType = periodicalType;
    }

    public float getScore() {
        return this.score;
    }
    
    public void setScore(Float score) {
        this.score = score;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTeacherAndperiodicals() {
        return this.teacherAndperiodicals;
    }
    
    public void setTeacherAndperiodicals(Set teacherAndperiodicals) {
        this.teacherAndperiodicals = teacherAndperiodicals;
    }
   








}