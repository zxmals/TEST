package com.nuaa.ec.model;



/**
 * TffamousTeacherTeamPerformance entity. @author MyEclipse Persistence Tools
 */

public class TffamousTeacherTeamPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel;
     private SelfUndertakeTask selfUndertakeTask;
     private Teacher teacher;
     private Double singelScore;
     private String spareTire;
     private String checkOut;
     private String teacherTeamPerformanceId;
     private Double projectSumScore;


    // Constructors

    /** default constructor */
    public TffamousTeacherTeamPerformance() {
    }

	/** minimal constructor */
    public TffamousTeacherTeamPerformance(Integer upid, String teacherTeamPerformanceId) {
        this.upid = upid;
        this.teacherTeamPerformanceId = teacherTeamPerformanceId;
    }
    
    /** full constructor */
    public TffamousTeacherTeamPerformance(Integer upid, TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel, SelfUndertakeTask selfUndertakeTask, Teacher teacher, Double singelScore, String spareTire, String checkOut, String teacherTeamPerformanceId, Double projectSumScore) {
        this.upid = upid;
        this.tffamousTeacherTeamRewadLevel = tffamousTeacherTeamRewadLevel;
        this.selfUndertakeTask = selfUndertakeTask;
        this.teacher = teacher;
        this.singelScore = singelScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
        this.teacherTeamPerformanceId = teacherTeamPerformanceId;
        this.projectSumScore = projectSumScore;
    }

   
    // Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TffamousTeacherTeamRewadLevel getTffamousTeacherTeamRewadLevel() {
        return this.tffamousTeacherTeamRewadLevel;
    }
    
    public void setTffamousTeacherTeamRewadLevel(TffamousTeacherTeamRewadLevel tffamousTeacherTeamRewadLevel) {
        this.tffamousTeacherTeamRewadLevel = tffamousTeacherTeamRewadLevel;
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

    public Double getSingelScore() {
        return this.singelScore;
    }
    
    public void setSingelScore(Double singelScore) {
        this.singelScore = singelScore;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getCheckOut() {
        return this.checkOut;
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public String getTeacherTeamPerformanceId() {
        return this.teacherTeamPerformanceId;
    }
    
    public void setTeacherTeamPerformanceId(String teacherTeamPerformanceId) {
        this.teacherTeamPerformanceId = teacherTeamPerformanceId;
    }

    public Double getProjectSumScore() {
        return this.projectSumScore;
    }
    
    public void setProjectSumScore(Double projectSumScore) {
        this.projectSumScore = projectSumScore;
    }
   








}