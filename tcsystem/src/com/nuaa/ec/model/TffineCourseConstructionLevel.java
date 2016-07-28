package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TffineCourseConstructionLevel entity. @author MyEclipse Persistence Tools
 */

public class TffineCourseConstructionLevel  implements java.io.Serializable {


    // Fields    

     private String courseLevelId;
     private String courseLevel;
     private Double score;
     private String reformResearchId;
     private String spareTire;
     private Set tffineCourseConstructionPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TffineCourseConstructionLevel() {
    }

	/** minimal constructor */
    public TffineCourseConstructionLevel(String courseLevelId) {
        this.courseLevelId = courseLevelId;
    }
    
    /** full constructor */
    public TffineCourseConstructionLevel(String courseLevelId, String courseLevel, Double score, String reformResearchId, String spareTire, Set tffineCourseConstructionPerformances) {
        this.courseLevelId = courseLevelId;
        this.courseLevel = courseLevel;
        this.score = score;
        this.reformResearchId = reformResearchId;
        this.spareTire = spareTire;
        this.tffineCourseConstructionPerformances = tffineCourseConstructionPerformances;
    }

   
    // Property accessors

    public String getCourseLevelId() {
        return this.courseLevelId;
    }
    
    public void setCourseLevelId(String courseLevelId) {
        this.courseLevelId = courseLevelId;
    }

    public String getCourseLevel() {
        return this.courseLevel;
    }
    
    public void setCourseLevel(String courseLevel) {
        this.courseLevel = courseLevel;
    }

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }

    public String getReformResearchId() {
        return this.reformResearchId;
    }
    
    public void setReformResearchId(String reformResearchId) {
        this.reformResearchId = reformResearchId;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTffineCourseConstructionPerformances() {
        return this.tffineCourseConstructionPerformances;
    }
    
    public void setTffineCourseConstructionPerformances(Set tffineCourseConstructionPerformances) {
        this.tffineCourseConstructionPerformances = tffineCourseConstructionPerformances;
    }
   








}