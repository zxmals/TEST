package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfundergraduateTutorGuidanceCache entity. @author MyEclipse Persistence Tools
 */

public class TfundergraduateTutorGuidanceCache  implements java.io.Serializable {


    // Fields    

     private String guidanceId;
     private TfteachGuidanceSubModular tfteachGuidanceSubModular;
     private String describe;
     private Double score;
     private String spareTire;
     private Set tfundergraduateTutorGuidancePerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfundergraduateTutorGuidanceCache() {
    }

	/** minimal constructor */
    public TfundergraduateTutorGuidanceCache(String guidanceId) {
        this.guidanceId = guidanceId;
    }
    
    /** full constructor */
    public TfundergraduateTutorGuidanceCache(String guidanceId, TfteachGuidanceSubModular tfteachGuidanceSubModular, String describe, Double score, String spareTire, Set tfundergraduateTutorGuidancePerformances) {
        this.guidanceId = guidanceId;
        this.tfteachGuidanceSubModular = tfteachGuidanceSubModular;
        this.describe = describe;
        this.score = score;
        this.spareTire = spareTire;
        this.tfundergraduateTutorGuidancePerformances = tfundergraduateTutorGuidancePerformances;
    }

   
    // Property accessors

    public String getGuidanceId() {
        return this.guidanceId;
    }
    
    public void setGuidanceId(String guidanceId) {
        this.guidanceId = guidanceId;
    }

    public TfteachGuidanceSubModular getTfteachGuidanceSubModular() {
        return this.tfteachGuidanceSubModular;
    }
    
    public void setTfteachGuidanceSubModular(TfteachGuidanceSubModular tfteachGuidanceSubModular) {
        this.tfteachGuidanceSubModular = tfteachGuidanceSubModular;
    }

    public String getDescribe() {
        return this.describe;
    }
    
    public void setDescribe(String describe) {
        this.describe = describe;
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

    public Set getTfundergraduateTutorGuidancePerformances() {
        return this.tfundergraduateTutorGuidancePerformances;
    }
    
    public void setTfundergraduateTutorGuidancePerformances(Set tfundergraduateTutorGuidancePerformances) {
        this.tfundergraduateTutorGuidancePerformances = tfundergraduateTutorGuidancePerformances;
    }
   








}