package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * ProjectType entity. @author MyEclipse Persistence Tools
 */

public class ProjectType  implements java.io.Serializable {


    // Fields    

     private String projectTypeId;
     private String projectTpName;
     private String spareTire;
     private Set scientificResearchProjects = new HashSet(0);
     private Set scientificResearchProjectScores = new HashSet(0);


    // Constructors

    /** default constructor */
    public ProjectType() {
    }

	/** minimal constructor */
    public ProjectType(String projectTypeId) {
        this.projectTypeId = projectTypeId;
    }
    
    /** full constructor */
    public ProjectType(String projectTypeId, String projectTpName, String spareTire, Set scientificResearchProjects, Set scientificResearchProjectScores) {
        this.projectTypeId = projectTypeId;
        this.projectTpName = projectTpName;
        this.spareTire = spareTire;
        this.scientificResearchProjects = scientificResearchProjects;
        this.scientificResearchProjectScores = scientificResearchProjectScores;
    }

   
    // Property accessors

    public String getProjectTypeId() {
        return this.projectTypeId;
    }
    
    public void setProjectTypeId(String projectTypeId) {
        this.projectTypeId = projectTypeId;
    }

    public String getProjectTpName() {
        return this.projectTpName;
    }
    
    public void setProjectTpName(String projectTpName) {
        this.projectTpName = projectTpName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getScientificResearchProjects() {
        return this.scientificResearchProjects;
    }
    
    public void setScientificResearchProjects(Set scientificResearchProjects) {
        this.scientificResearchProjects = scientificResearchProjects;
    }

    public Set getScientificResearchProjectScores() {
        return this.scientificResearchProjectScores;
    }
    
    public void setScientificResearchProjectScores(Set scientificResearchProjectScores) {
        this.scientificResearchProjectScores = scientificResearchProjectScores;
    }
   








}