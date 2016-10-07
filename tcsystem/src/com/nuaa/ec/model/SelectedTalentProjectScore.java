package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * SelectedTalentProjectScore entity. @author MyEclipse Persistence Tools
 */

public class SelectedTalentProjectScore  implements java.io.Serializable {


    // Fields    

     private String stpscoreId;
     private TalentProject talentProject;
     private Long score;
     private String spareTire;
     private Set teacherAndselectedTalentProjects = new HashSet(0);


    // Constructors

    /** default constructor */
    public SelectedTalentProjectScore() {
    }

	/** minimal constructor */
    public SelectedTalentProjectScore(String stpscoreId) {
        this.stpscoreId = stpscoreId;
    }
    
    /** full constructor */
    public SelectedTalentProjectScore(String stpscoreId, TalentProject talentProject, Long score, String spareTire, Set teacherAndselectedTalentProjects) {
        this.stpscoreId = stpscoreId;
        this.talentProject = talentProject;
        this.score = score;
        this.spareTire = spareTire;
        this.teacherAndselectedTalentProjects = teacherAndselectedTalentProjects;
    }

   
    // Property accessors

    public String getStpscoreId() {
        return this.stpscoreId;
    }
    
    public void setStpscoreId(String stpscoreId) {
        this.stpscoreId = stpscoreId;
    }

    public TalentProject getTalentProject() {
        return this.talentProject;
    }
    
    public void setTalentProject(TalentProject talentProject) {
        this.talentProject = talentProject;
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

    public Set getTeacherAndselectedTalentProjects() {
        return this.teacherAndselectedTalentProjects;
    }
    
    public void setTeacherAndselectedTalentProjects(Set teacherAndselectedTalentProjects) {
        this.teacherAndselectedTalentProjects = teacherAndselectedTalentProjects;
    }
   








}