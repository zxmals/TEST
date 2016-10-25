package com.nuaa.ec.model;



/**
 * TeacherAndscientificResearchProject entity. @author MyEclipse Persistence Tools
 */

public class TeacherAndscientificResearchProject  implements java.io.Serializable {


    // Fields    

     private Integer teacherAscienRpid;
     private ScientificResearchProjectScore scientificResearchProjectScore;
     private ScientificResearchProject scientificResearchProject;
     private SelfUndertakeTask selfUndertakeTask;
     private Teacher teacher;
     private String yearFunds;
     private Double finalScore;
     private String spareTire;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TeacherAndscientificResearchProject() {
    }

	/** minimal constructor */
    public TeacherAndscientificResearchProject(Integer teacherAscienRpid) {
        this.teacherAscienRpid = teacherAscienRpid;
    }
    
    /** full constructor */
    public TeacherAndscientificResearchProject(Integer teacherAscienRpid, ScientificResearchProjectScore scientificResearchProjectScore, ScientificResearchProject scientificResearchProject, SelfUndertakeTask selfUndertakeTask, Teacher teacher, String yearFunds, Double finalScore, String spareTire, String checkOut) {
        this.teacherAscienRpid = teacherAscienRpid;
        this.scientificResearchProjectScore = scientificResearchProjectScore;
        this.scientificResearchProject = scientificResearchProject;
        this.selfUndertakeTask = selfUndertakeTask;
        this.teacher = teacher;
        this.yearFunds = yearFunds;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getTeacherAscienRpid() {
        return this.teacherAscienRpid;
    }
    
    public void setTeacherAscienRpid(Integer teacherAscienRpid) {
        this.teacherAscienRpid = teacherAscienRpid;
    }

    public ScientificResearchProjectScore getScientificResearchProjectScore() {
        return this.scientificResearchProjectScore;
    }
    
    public void setScientificResearchProjectScore(ScientificResearchProjectScore scientificResearchProjectScore) {
        this.scientificResearchProjectScore = scientificResearchProjectScore;
    }

    public ScientificResearchProject getScientificResearchProject() {
        return this.scientificResearchProject;
    }
    
    public void setScientificResearchProject(ScientificResearchProject scientificResearchProject) {
        this.scientificResearchProject = scientificResearchProject;
    }

    public SelfUndertakeTask getSelfUndertakeTask() {
        return this.selfUndertakeTask;
    }
    
    public void setSelfUndertakeTask(SelfUndertakeTask selfUndertakeTask) {
        this.selfUndertakeTask = selfUndertakeTask;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getYearFunds() {
        return this.yearFunds;
    }
    
    public void setYearFunds(String yearFunds) {
        this.yearFunds = yearFunds;
    }

    public Double getFinalScore() {
        return this.finalScore;
    }
    
    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getCheckOut() {
        return this.checkOut.trim();
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
   








}