package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfjoinStudentActivityTime entity. @author MyEclipse Persistence Tools
 */

public class TfjoinStudentActivityTime  implements java.io.Serializable {


    // Fields    

     private String timerId;
     private TfteachGuidanceSubModular tfteachGuidanceSubModular;
     private String timeUnit;
     private Double score;
     private String spareTire;
     private Set tfjoinStudentActivityPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfjoinStudentActivityTime() {
    }

	/** minimal constructor */
    public TfjoinStudentActivityTime(String timerId) {
        this.timerId = timerId;
    }
    
    /** full constructor */
    public TfjoinStudentActivityTime(String timerId, TfteachGuidanceSubModular tfteachGuidanceSubModular, String timeUnit, Double score, String spareTire, Set tfjoinStudentActivityPerformances) {
        this.timerId = timerId;
        this.tfteachGuidanceSubModular = tfteachGuidanceSubModular;
        this.timeUnit = timeUnit;
        this.score = score;
        this.spareTire = spareTire;
        this.tfjoinStudentActivityPerformances = tfjoinStudentActivityPerformances;
    }

   
    // Property accessors

    public String getTimerId() {
        return this.timerId;
    }
    
    public void setTimerId(String timerId) {
        this.timerId = timerId;
    }

    public TfteachGuidanceSubModular getTfteachGuidanceSubModular() {
        return this.tfteachGuidanceSubModular;
    }
    
    public void setTfteachGuidanceSubModular(TfteachGuidanceSubModular tfteachGuidanceSubModular) {
        this.tfteachGuidanceSubModular = tfteachGuidanceSubModular;
    }

    public String getTimeUnit() {
        return this.timeUnit;
    }
    
    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
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

    public Set getTfjoinStudentActivityPerformances() {
        return this.tfjoinStudentActivityPerformances;
    }
    
    public void setTfjoinStudentActivityPerformances(Set tfjoinStudentActivityPerformances) {
        this.tfjoinStudentActivityPerformances = tfjoinStudentActivityPerformances;
    }
   








}