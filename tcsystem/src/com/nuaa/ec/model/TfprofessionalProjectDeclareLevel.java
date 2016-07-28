package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfprofessionalProjectDeclareLevel entity. @author MyEclipse Persistence Tools
 */

public class TfprofessionalProjectDeclareLevel  implements java.io.Serializable {


    // Fields    

     private String projectLevelId;
     private String projectLevel;
     private Double score;
     private String spareTire;
     private String reformResearchId;
     private Set tfprofessionalProjectDeclarePerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfprofessionalProjectDeclareLevel() {
    }

	/** minimal constructor */
    public TfprofessionalProjectDeclareLevel(String projectLevelId) {
        this.projectLevelId = projectLevelId;
    }
    
    /** full constructor */
    public TfprofessionalProjectDeclareLevel(String projectLevelId, String projectLevel, Double score, String spareTire, String reformResearchId, Set tfprofessionalProjectDeclarePerformances) {
        this.projectLevelId = projectLevelId;
        this.projectLevel = projectLevel;
        this.score = score;
        this.spareTire = spareTire;
        this.reformResearchId = reformResearchId;
        this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
    }

   
    // Property accessors

    public String getProjectLevelId() {
        return this.projectLevelId;
    }
    
    public void setProjectLevelId(String projectLevelId) {
        this.projectLevelId = projectLevelId;
    }

    public String getProjectLevel() {
        return this.projectLevel;
    }
    
    public void setProjectLevel(String projectLevel) {
        this.projectLevel = projectLevel;
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

    public String getReformResearchId() {
        return this.reformResearchId;
    }
    
    public void setReformResearchId(String reformResearchId) {
        this.reformResearchId = reformResearchId;
    }

    public Set getTfprofessionalProjectDeclarePerformances() {
        return this.tfprofessionalProjectDeclarePerformances;
    }
    
    public void setTfprofessionalProjectDeclarePerformances(Set tfprofessionalProjectDeclarePerformances) {
        this.tfprofessionalProjectDeclarePerformances = tfprofessionalProjectDeclarePerformances;
    }
   








}