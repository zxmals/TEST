package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * ScientificResearchProjectScore entity. @author MyEclipse Persistence Tools
 */

public class ScientificResearchProjectScore  implements java.io.Serializable {


    // Fields    

     private String srprojectScoreId;
     private SubModular subModular;
     private ProjectType projectType;
     private Long score;
     private String spareTire;
     private Set teacherAndscientificResearchProjects = new HashSet(0);


    // Constructors

    /** default constructor */
    public ScientificResearchProjectScore() {
    }

	/** minimal constructor */
    public ScientificResearchProjectScore(String srprojectScoreId) {
        this.srprojectScoreId = srprojectScoreId;
    }
    
    /** full constructor */
    public ScientificResearchProjectScore(String srprojectScoreId, SubModular subModular, ProjectType projectType, Long score, String spareTire, Set teacherAndscientificResearchProjects) {
        this.srprojectScoreId = srprojectScoreId;
        this.subModular = subModular;
        this.projectType = projectType;
        this.score = score;
        this.spareTire = spareTire;
        this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
    }

   
    // Property accessors

    public String getSrprojectScoreId() {
        return this.srprojectScoreId;
    }
    
    public void setSrprojectScoreId(String srprojectScoreId) {
        this.srprojectScoreId = srprojectScoreId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public ProjectType getProjectType() {
        return this.projectType;
    }
    
    public void setProjectType(ProjectType projectType) {
        this.projectType = projectType;
    }

    public Long getScore() {
        return this.score;
    }
    
    public void setScore(Long score) {
        this.score = score;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTeacherAndscientificResearchProjects() {
        return this.teacherAndscientificResearchProjects;
    }
    
    public void setTeacherAndscientificResearchProjects(Set teacherAndscientificResearchProjects) {
        this.teacherAndscientificResearchProjects = teacherAndscientificResearchProjects;
    }
   








}