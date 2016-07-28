package com.nuaa.ec.model;



/**
 * TeacherAndperiodical entity. @author MyEclipse Persistence Tools
 */

public class TeacherAndperiodical  implements java.io.Serializable {


    // Fields    

     private Integer teacherAndPid;
     private PeriodicalPapersScore periodicalPapersScore;
     private Teacher teacher;
     private Periodical periodical;
     private Double finalScore;
     private String spareTire;
     private String ppid;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TeacherAndperiodical() {
    }

	/** minimal constructor */
    public TeacherAndperiodical(Integer teacherAndPid, String ppid) {
        this.teacherAndPid = teacherAndPid;
        this.ppid = ppid;
    }
    
    /** full constructor */
    public TeacherAndperiodical(Integer teacherAndPid, PeriodicalPapersScore periodicalPapersScore, Teacher teacher, Periodical periodical, Double finalScore, String spareTire, String ppid, String checkOut) {
        this.teacherAndPid = teacherAndPid;
        this.periodicalPapersScore = periodicalPapersScore;
        this.teacher = teacher;
        this.periodical = periodical;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.ppid = ppid;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getTeacherAndPid() {
        return this.teacherAndPid;
    }
    
    public void setTeacherAndPid(Integer teacherAndPid) {
        this.teacherAndPid = teacherAndPid;
    }

    public PeriodicalPapersScore getPeriodicalPapersScore() {
        return this.periodicalPapersScore;
    }
    
    public void setPeriodicalPapersScore(PeriodicalPapersScore periodicalPapersScore) {
        this.periodicalPapersScore = periodicalPapersScore;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Periodical getPeriodical() {
        return this.periodical;
    }
    
    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
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

    public String getPpid() {
        return this.ppid;
    }
    
    public void setPpid(String ppid) {
        this.ppid = ppid;
    }

    public String getCheckOut() {
        return this.checkOut;
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
   








}