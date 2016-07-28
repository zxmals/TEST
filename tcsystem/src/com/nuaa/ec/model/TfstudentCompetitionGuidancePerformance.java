package com.nuaa.ec.model;



/**
 * TfstudentCompetitionGuidancePerformance entity. @author MyEclipse Persistence Tools
 */

public class TfstudentCompetitionGuidancePerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfstudentCompetitionGuidanceScore tfstudentCompetitionGuidanceScore;
     private Teacher teacher;
     private Double finalScore;
     private String spareTire;
     private String competitionId;
     private String competitionName;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TfstudentCompetitionGuidancePerformance() {
    }

	/** minimal constructor */
    public TfstudentCompetitionGuidancePerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfstudentCompetitionGuidancePerformance(Integer upid, TfstudentCompetitionGuidanceScore tfstudentCompetitionGuidanceScore, Teacher teacher, Double finalScore, String spareTire, String competitionId, String competitionName, String checkOut) {
        this.upid = upid;
        this.tfstudentCompetitionGuidanceScore = tfstudentCompetitionGuidanceScore;
        this.teacher = teacher;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfstudentCompetitionGuidanceScore getTfstudentCompetitionGuidanceScore() {
        return this.tfstudentCompetitionGuidanceScore;
    }
    
    public void setTfstudentCompetitionGuidanceScore(TfstudentCompetitionGuidanceScore tfstudentCompetitionGuidanceScore) {
        this.tfstudentCompetitionGuidanceScore = tfstudentCompetitionGuidanceScore;
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

    public String getCompetitionId() {
        return this.competitionId;
    }
    
    public void setCompetitionId(String competitionId) {
        this.competitionId = competitionId;
    }

    public String getCompetitionName() {
        return this.competitionName;
    }
    
    public void setCompetitionName(String competitionName) {
        this.competitionName = competitionName;
    }

    public String getCheckOut() {
        return this.checkOut;
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
   








}