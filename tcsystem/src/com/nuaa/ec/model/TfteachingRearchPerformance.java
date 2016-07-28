package com.nuaa.ec.model;



/**
 * TfteachingRearchPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfteachingRearchPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfteachingRearchEvaluation tfteachingRearchEvaluation;
     private TfteachingRearchFundlevel tfteachingRearchFundlevel;
     private Teacher teacher;
     private String projectId;
     private String project;
     private String spareTire;
     private Double finalScore;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TfteachingRearchPerformance() {
    }

	/** minimal constructor */
    public TfteachingRearchPerformance(Integer upid, Teacher teacher) {
        this.upid = upid;
        this.teacher = teacher;
    }
    
    /** full constructor */
    public TfteachingRearchPerformance(Integer upid, TfteachingRearchEvaluation tfteachingRearchEvaluation, TfteachingRearchFundlevel tfteachingRearchFundlevel, Teacher teacher, String projectId, String project, String spareTire, Double finalScore, String checkOut) {
        this.upid = upid;
        this.tfteachingRearchEvaluation = tfteachingRearchEvaluation;
        this.tfteachingRearchFundlevel = tfteachingRearchFundlevel;
        this.teacher = teacher;
        this.projectId = projectId;
        this.project = project;
        this.spareTire = spareTire;
        this.finalScore = finalScore;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfteachingRearchEvaluation getTfteachingRearchEvaluation() {
        return this.tfteachingRearchEvaluation;
    }
    
    public void setTfteachingRearchEvaluation(TfteachingRearchEvaluation tfteachingRearchEvaluation) {
        this.tfteachingRearchEvaluation = tfteachingRearchEvaluation;
    }

    public TfteachingRearchFundlevel getTfteachingRearchFundlevel() {
        return this.tfteachingRearchFundlevel;
    }
    
    public void setTfteachingRearchFundlevel(TfteachingRearchFundlevel tfteachingRearchFundlevel) {
        this.tfteachingRearchFundlevel = tfteachingRearchFundlevel;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProject() {
        return this.project;
    }
    
    public void setProject(String project) {
        this.project = project;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Double getFinalScore() {
        return this.finalScore;
    }
    
    public void setFinalScore(Double finalScore) {
        this.finalScore = finalScore;
    }

    public String getCheckOut() {
        return this.checkOut;
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
   








}