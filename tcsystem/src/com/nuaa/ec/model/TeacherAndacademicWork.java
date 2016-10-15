package com.nuaa.ec.model;



/**
 * TeacherAndacademicWork entity. @author MyEclipse Persistence Tools
 */

public class TeacherAndacademicWork  implements java.io.Serializable {


    // Fields    

     private Integer teacherAndAcawid;
     private AcademicWork academicWork;
     private AcademicWorkScore academicWorkScore;
     private SelfUndertakeTask selfUndertakeTask;
     private Teacher teacher;
     private Double finalScore;
     private String spareTire;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TeacherAndacademicWork() {
    }

	/** minimal constructor */
    public TeacherAndacademicWork(Integer teacherAndAcawid) {
        this.teacherAndAcawid = teacherAndAcawid;
    }
    
    /** full constructor */
    public TeacherAndacademicWork(Integer teacherAndAcawid, AcademicWork academicWork, AcademicWorkScore academicWorkScore, SelfUndertakeTask selfUndertakeTask, Teacher teacher, Double finalScore, String spareTire, String checkOut) {
        this.teacherAndAcawid = teacherAndAcawid;
        this.academicWork = academicWork;
        this.academicWorkScore = academicWorkScore;
        this.selfUndertakeTask = selfUndertakeTask;
        this.teacher = teacher;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getTeacherAndAcawid() {
        return this.teacherAndAcawid;
    }
    
    public void setTeacherAndAcawid(Integer teacherAndAcawid) {
        this.teacherAndAcawid = teacherAndAcawid;
    }

    public AcademicWork getAcademicWork() {
        return this.academicWork;
    }
    
    public void setAcademicWork(AcademicWork academicWork) {
        this.academicWork = academicWork;
    }

    public AcademicWorkScore getAcademicWorkScore() {
        return this.academicWorkScore;
    }
    
    public void setAcademicWorkScore(AcademicWorkScore academicWorkScore) {
        this.academicWorkScore = academicWorkScore;
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