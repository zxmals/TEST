package com.nuaa.ec.model;



/**
 * TfprofessionalProjectDeclarePerformance entity. @author MyEclipse Persistence Tools
 */

public class TfprofessionalProjectDeclarePerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfprofessionalProjectDeclareLevel tfprofessionalProjectDeclareLevel;
     private SelfUndertakeTask selfUndertakeTask;
     private Teacher teacher;
     private String projectId;
     private String projectName;
     private Double projectSumScore;
     private Double singleScore;
     private String spareTire;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TfprofessionalProjectDeclarePerformance() {
    }

	/** minimal constructor */
    public TfprofessionalProjectDeclarePerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfprofessionalProjectDeclarePerformance(Integer upid, TfprofessionalProjectDeclareLevel tfprofessionalProjectDeclareLevel, SelfUndertakeTask selfUndertakeTask, Teacher teacher, String projectId, String projectName, Double projectSumScore, Double singleScore, String spareTire, String checkOut) {
        this.upid = upid;
        this.tfprofessionalProjectDeclareLevel = tfprofessionalProjectDeclareLevel;
        this.selfUndertakeTask = selfUndertakeTask;
        this.teacher = teacher;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectSumScore = projectSumScore;
        this.singleScore = singleScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfprofessionalProjectDeclareLevel getTfprofessionalProjectDeclareLevel() {
        return this.tfprofessionalProjectDeclareLevel;
    }
    
    public void setTfprofessionalProjectDeclareLevel(TfprofessionalProjectDeclareLevel tfprofessionalProjectDeclareLevel) {
        this.tfprofessionalProjectDeclareLevel = tfprofessionalProjectDeclareLevel;
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

    public String getProjectId() {
        return this.projectId;
    }
    
    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return this.projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Double getProjectSumScore() {
        return this.projectSumScore;
    }
    
    public void setProjectSumScore(Double projectSumScore) {
        this.projectSumScore = projectSumScore;
    }

    public Double getSingleScore() {
        return this.singleScore;
    }
    
    public void setSingleScore(Double singleScore) {
        this.singleScore = singleScore;
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
   








}