package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfenterpriseWorkstationTrainingbaseConstructionLevel entity. @author MyEclipse Persistence Tools
 */

public class TfenterpriseWorkstationTrainingbaseConstructionLevel  implements java.io.Serializable {


    // Fields    

     private String trainingConstruLevelId;
     private String trainingConstruLevel;
     private Double score;
     private String refromResearchId;
     private String spareTire;
     private Set tfenterpriseWorkstationTrainingBaseConstructionPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfenterpriseWorkstationTrainingbaseConstructionLevel() {
    }

	/** minimal constructor */
    public TfenterpriseWorkstationTrainingbaseConstructionLevel(String trainingConstruLevelId) {
        this.trainingConstruLevelId = trainingConstruLevelId;
    }
    
    /** full constructor */
    public TfenterpriseWorkstationTrainingbaseConstructionLevel(String trainingConstruLevelId, String trainingConstruLevel, Double score, String refromResearchId, String spareTire, Set tfenterpriseWorkstationTrainingBaseConstructionPerformances) {
        this.trainingConstruLevelId = trainingConstruLevelId;
        this.trainingConstruLevel = trainingConstruLevel;
        this.score = score;
        this.refromResearchId = refromResearchId;
        this.spareTire = spareTire;
        this.tfenterpriseWorkstationTrainingBaseConstructionPerformances = tfenterpriseWorkstationTrainingBaseConstructionPerformances;
    }

   
    // Property accessors

    public String getTrainingConstruLevelId() {
        return this.trainingConstruLevelId;
    }
    
    public void setTrainingConstruLevelId(String trainingConstruLevelId) {
        this.trainingConstruLevelId = trainingConstruLevelId;
    }

    public String getTrainingConstruLevel() {
        return this.trainingConstruLevel;
    }
    
    public void setTrainingConstruLevel(String trainingConstruLevel) {
        this.trainingConstruLevel = trainingConstruLevel;
    }

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }

    public String getRefromResearchId() {
        return this.refromResearchId;
    }
    
    public void setRefromResearchId(String refromResearchId) {
        this.refromResearchId = refromResearchId;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTfenterpriseWorkstationTrainingBaseConstructionPerformances() {
        return this.tfenterpriseWorkstationTrainingBaseConstructionPerformances;
    }
    
    public void setTfenterpriseWorkstationTrainingBaseConstructionPerformances(Set tfenterpriseWorkstationTrainingBaseConstructionPerformances) {
        this.tfenterpriseWorkstationTrainingBaseConstructionPerformances = tfenterpriseWorkstationTrainingBaseConstructionPerformances;
    }
   








}