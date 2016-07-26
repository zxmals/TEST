package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * TfsummerCourseInternationalConstructionLevel entity. @author MyEclipse Persistence Tools
 */

public class TfsummerCourseInternationalConstructionLevel  implements java.io.Serializable {


    // Fields    

     private String projectLevelId;
     private String projectLevel;
     private Double score;
     private String reformResearchId;
     private String spareTire;
     private String unit;
     private Set tfsummerCourseInternationalConstructionPerformances = new HashSet(0);


    // Constructors

    /** default constructor */
    public TfsummerCourseInternationalConstructionLevel() {
    }

	/** minimal constructor */
    public TfsummerCourseInternationalConstructionLevel(String projectLevelId) {
        this.projectLevelId = projectLevelId;
    }
    
    /** full constructor */
    public TfsummerCourseInternationalConstructionLevel(String projectLevelId, String projectLevel, Double score, String reformResearchId, String spareTire, String unit, Set tfsummerCourseInternationalConstructionPerformances) {
        this.projectLevelId = projectLevelId;
        this.projectLevel = projectLevel;
        this.score = score;
        this.reformResearchId = reformResearchId;
        this.spareTire = spareTire;
        this.unit = unit;
        this.tfsummerCourseInternationalConstructionPerformances = tfsummerCourseInternationalConstructionPerformances;
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

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Set getTfsummerCourseInternationalConstructionPerformances() {
        return this.tfsummerCourseInternationalConstructionPerformances;
    }
    
    public void setTfsummerCourseInternationalConstructionPerformances(Set tfsummerCourseInternationalConstructionPerformances) {
        this.tfsummerCourseInternationalConstructionPerformances = tfsummerCourseInternationalConstructionPerformances;
    }
   








}