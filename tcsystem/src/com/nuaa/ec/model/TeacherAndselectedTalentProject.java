package com.nuaa.ec.model;



/**
 * TeacherAndselectedTalentProject entity. @author MyEclipse Persistence Tools
 */

public class TeacherAndselectedTalentProject  implements java.io.Serializable {


    // Fields    

     private Integer teacherAstpid;
     private Teacher teacher;
     private TalentProject talentProject;
     private SelectedTalentProjectScore selectedTalentProjectScore;
     private String tpselectedYear;
     private Double finalScore;
     private String spareTire;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TeacherAndselectedTalentProject() {
    }

	/** minimal constructor */
    public TeacherAndselectedTalentProject(Integer teacherAstpid) {
        this.teacherAstpid = teacherAstpid;
    }
    
    /** full constructor */
    public TeacherAndselectedTalentProject(Integer teacherAstpid, Teacher teacher, TalentProject talentProject, SelectedTalentProjectScore selectedTalentProjectScore, String tpselectedYear, Double finalScore, String spareTire, String checkOut) {
        this.teacherAstpid = teacherAstpid;
        this.teacher = teacher;
        this.talentProject = talentProject;
        this.selectedTalentProjectScore = selectedTalentProjectScore;
        this.tpselectedYear = tpselectedYear;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getTeacherAstpid() {
        return this.teacherAstpid;
    }
    
    public void setTeacherAstpid(Integer teacherAstpid) {
        this.teacherAstpid = teacherAstpid;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TalentProject getTalentProject() {
        return this.talentProject;
    }
    
    public void setTalentProject(TalentProject talentProject) {
        this.talentProject = talentProject;
    }

    public SelectedTalentProjectScore getSelectedTalentProjectScore() {
        return this.selectedTalentProjectScore;
    }
    
    public void setSelectedTalentProjectScore(SelectedTalentProjectScore selectedTalentProjectScore) {
        this.selectedTalentProjectScore = selectedTalentProjectScore;
    }

    public String getTpselectedYear() {
        return this.tpselectedYear;
    }
    
    public void setTpselectedYear(String tpselectedYear) {
        this.tpselectedYear = tpselectedYear;
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