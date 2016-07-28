package com.nuaa.ec.model;



/**
 * TfoffCampusPracticeGuidanceLevel entity. @author MyEclipse Persistence Tools
 */

public class TfoffCampusPracticeGuidanceLevel  implements java.io.Serializable {


    // Fields    

     private String projectId;
     private String projectType;
     private String unit;
     private Double score;
     private String teachGuideId;
     private String spareTire;


    // Constructors

    /** default constructor */
    public TfoffCampusPracticeGuidanceLevel() {
    }

	/** minimal constructor */
    public TfoffCampusPracticeGuidanceLevel(String projectId) {
        this.projectId = projectId;
    }
    
    /** full constructor */
    public TfoffCampusPracticeGuidanceLevel(String projectId, String projectType, String unit, Double score, String teachGuideId, String spareTire) {
        this.projectId = projectId;
        this.projectType = projectType;
        this.unit = unit;
        this.score = score;
        this.teachGuideId = teachGuideId;
        this.spareTire = spareTire;
    }

   
    // Property accessors

    public String getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectType() {
        return this.projectType;
    }
    
    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }

    public String getTeachGuideId() {
        return this.teachGuideId;
    }
    
    public void setTeachGuideId(String teachGuideId) {
        this.teachGuideId = teachGuideId;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }
   








}