package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfpracticeInnovationGuideLevel entity. @author MyEclipse Persistence Tools
 */

public class TfpracticeInnovationGuideLevel  implements java.io.Serializable {


    // Fields    

     private String innovationGuideLevelId;
     private TfteachGuidanceSubModular tfteachGuidanceSubModular;
     private String innovatIonguideLevel;
     private Double score;
     private String spareTire;
     private Set tfpracticeInnovationGuidePerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfpracticeInnovationGuideLevel() {
    }

	/** minimal constructor */
    public TfpracticeInnovationGuideLevel(String innovationGuideLevelId) {
        this.innovationGuideLevelId = innovationGuideLevelId;
    }
    
    /** full constructor */
    public TfpracticeInnovationGuideLevel(String innovationGuideLevelId, TfteachGuidanceSubModular tfteachGuidanceSubModular, String innovatIonguideLevel, Double score, String spareTire, Set tfpracticeInnovationGuidePerformances) {
        this.innovationGuideLevelId = innovationGuideLevelId;
        this.tfteachGuidanceSubModular = tfteachGuidanceSubModular;
        this.innovatIonguideLevel = innovatIonguideLevel;
        this.score = score;
        this.spareTire = spareTire;
        this.tfpracticeInnovationGuidePerformances = tfpracticeInnovationGuidePerformances;
    }

   
    // Property accessors

    public String getInnovationGuideLevelId() {
        return this.innovationGuideLevelId;
    }
    
    public void setInnovationGuideLevelId(String innovationGuideLevelId) {
        this.innovationGuideLevelId = innovationGuideLevelId;
    }

    public TfteachGuidanceSubModular getTfteachGuidanceSubModular() {
        return this.tfteachGuidanceSubModular;
    }
    
    public void setTfteachGuidanceSubModular(TfteachGuidanceSubModular tfteachGuidanceSubModular) {
        this.tfteachGuidanceSubModular = tfteachGuidanceSubModular;
    }

    public String getInnovatIonguideLevel() {
        return this.innovatIonguideLevel;
    }
    
    public void setInnovatIonguideLevel(String innovatIonguideLevel) {
        this.innovatIonguideLevel = innovatIonguideLevel;
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

    public Set getTfpracticeInnovationGuidePerformances() {
        return this.tfpracticeInnovationGuidePerformances;
    }
    
    public void setTfpracticeInnovationGuidePerformances(Set tfpracticeInnovationGuidePerformances) {
        this.tfpracticeInnovationGuidePerformances = tfpracticeInnovationGuidePerformances;
    }
   








}