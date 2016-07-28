package com.nuaa.ec.model;



/**
 * TffineCourseConstructionPerformance entity. @author MyEclipse Persistence Tools
 */

public class TffineCourseConstructionPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private SelfUndertakeTask selfUndertakeTask;
     private TffineCourseConstructionLevel tffineCourseConstructionLevel;
     private Teacher teacher;
     private String courseId;
     private String courseName;
     private String cooperator;
     private Double singelScore;
     private String checkOut;
     private String spareTire;
     private Double projectSumScore;


    // Constructors

    /** default constructor */
    public TffineCourseConstructionPerformance() {
    }

	/** minimal constructor */
    public TffineCourseConstructionPerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TffineCourseConstructionPerformance(Integer upid, SelfUndertakeTask selfUndertakeTask, TffineCourseConstructionLevel tffineCourseConstructionLevel, Teacher teacher, String courseId, String courseName, String cooperator, Double singelScore, String checkOut, String spareTire, Double projectSumScore) {
        this.upid = upid;
        this.selfUndertakeTask = selfUndertakeTask;
        this.tffineCourseConstructionLevel = tffineCourseConstructionLevel;
        this.teacher = teacher;
        this.courseId = courseId;
        this.courseName = courseName;
        this.cooperator = cooperator;
        this.singelScore = singelScore;
        this.checkOut = checkOut;
        this.spareTire = spareTire;
        this.projectSumScore = projectSumScore;
    }

   
    // Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public SelfUndertakeTask getSelfUndertakeTask() {
        return this.selfUndertakeTask;
    }
    
    public void setSelfUndertakeTask(SelfUndertakeTask selfUndertakeTask) {
        this.selfUndertakeTask = selfUndertakeTask;
    }

    public TffineCourseConstructionLevel getTffineCourseConstructionLevel() {
        return this.tffineCourseConstructionLevel;
    }
    
    public void setTffineCourseConstructionLevel(TffineCourseConstructionLevel tffineCourseConstructionLevel) {
        this.tffineCourseConstructionLevel = tffineCourseConstructionLevel;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCooperator() {
        return this.cooperator;
    }
    
    public void setCooperator(String cooperator) {
        this.cooperator = cooperator;
    }

    public Double getSingelScore() {
        return this.singelScore;
    }
    
    public void setSingelScore(Double singelScore) {
        this.singelScore = singelScore;
    }

    public String getCheckOut() {
        return this.checkOut;
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Double getProjectSumScore() {
        return this.projectSumScore;
    }
    
    public void setProjectSumScore(Double projectSumScore) {
        this.projectSumScore = projectSumScore;
    }
   








}