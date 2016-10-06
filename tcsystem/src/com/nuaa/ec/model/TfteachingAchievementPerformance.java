package com.nuaa.ec.model;



/**
 * TfteachingAchievementPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfteachingAchievementPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private SelfUndertakeTask selfUndertakeTask;
     private TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel;
     private Teacher teacher;
     private String cooperator;
     private Double singelScore;
     private String spareTire;
     private String checkOut;
     private Double projectSumScore;
     private String projectId;
     private String projectName;

     private String termId;

    // Constructors

    /** default constructor */
    public TfteachingAchievementPerformance() {
    }

	/** minimal constructor */
    public TfteachingAchievementPerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfteachingAchievementPerformance(Integer upid, SelfUndertakeTask selfUndertakeTask, TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel, Teacher teacher, String cooperator, Double singelScore, String spareTire, String checkOut, Double projectSumScore, String projectId, String projectName) {
        this.upid = upid;
        this.selfUndertakeTask = selfUndertakeTask;
        this.tfteachingAchievementRewardLevel = tfteachingAchievementRewardLevel;
        this.teacher = teacher;
        this.cooperator = cooperator;
        this.singelScore = singelScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
        this.projectSumScore = projectSumScore;
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public TfteachingAchievementPerformance(Integer upid,
			SelfUndertakeTask selfUndertakeTask,
			TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel,
			Teacher teacher, String cooperator, Double singelScore,
			String spareTire, String checkOut, Double projectSumScore,
			String projectId, String projectName, String termId) {
		this.upid = upid;
		this.selfUndertakeTask = selfUndertakeTask;
		this.tfteachingAchievementRewardLevel = tfteachingAchievementRewardLevel;
		this.teacher = teacher;
		this.cooperator = cooperator;
		this.singelScore = singelScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.projectSumScore = projectSumScore;
		this.projectId = projectId;
		this.projectName = projectName;
		this.termId = termId;
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

    public TfteachingAchievementRewardLevel getTfteachingAchievementRewardLevel() {
        return this.tfteachingAchievementRewardLevel;
    }
    
    public void setTfteachingAchievementRewardLevel(TfteachingAchievementRewardLevel tfteachingAchievementRewardLevel) {
        this.tfteachingAchievementRewardLevel = tfteachingAchievementRewardLevel;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

    public Double getProjectSumScore() {
        return this.projectSumScore;
    }
    
    public void setProjectSumScore(Double projectSumScore) {
        this.projectSumScore = projectSumScore;
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

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}
   


}