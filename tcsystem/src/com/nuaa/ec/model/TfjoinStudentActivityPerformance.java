package com.nuaa.ec.model;



/**
 * TfjoinStudentActivityPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfjoinStudentActivityPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfjoinStudentActivityTime tfjoinStudentActivityTime;
     private Teacher teacher;
     private String activityId;
     private String activityName;
     private Double sumhours;
     private Double finalScore;
     private String spareTire;
     private String checkOut;
     private Integer yearceiling;

     private String termId;

    // Constructors

    /** default constructor */
    public TfjoinStudentActivityPerformance() {
    }

	/** minimal constructor */
    public TfjoinStudentActivityPerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfjoinStudentActivityPerformance(Integer upid, TfjoinStudentActivityTime tfjoinStudentActivityTime, Teacher teacher, String activityId, String activityName, Double sumhours, Double finalScore, String spareTire, String checkOut, Integer yearceiling) {
        this.upid = upid;
        this.tfjoinStudentActivityTime = tfjoinStudentActivityTime;
        this.teacher = teacher;
        this.activityId = activityId;
        this.activityName = activityName;
        this.sumhours = sumhours;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
        this.yearceiling = yearceiling;
    }

   
    
    public TfjoinStudentActivityPerformance(Integer upid,
			TfjoinStudentActivityTime tfjoinStudentActivityTime,
			Teacher teacher, String activityId, String activityName,
			Double sumhours, Double finalScore, String spareTire,
			String checkOut, Integer yearceiling, String termId) {
		this.upid = upid;
		this.tfjoinStudentActivityTime = tfjoinStudentActivityTime;
		this.teacher = teacher;
		this.activityId = activityId;
		this.activityName = activityName;
		this.sumhours = sumhours;
		this.finalScore = finalScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.yearceiling = yearceiling;
		this.termId = termId;
	}

	// Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfjoinStudentActivityTime getTfjoinStudentActivityTime() {
        return this.tfjoinStudentActivityTime;
    }
    
    public void setTfjoinStudentActivityTime(TfjoinStudentActivityTime tfjoinStudentActivityTime) {
        this.tfjoinStudentActivityTime = tfjoinStudentActivityTime;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getActivityId() {
        return this.activityId;
    }
    
    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return this.activityName;
    }
    
    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public Double getSumhours() {
        return this.sumhours;
    }
    
    public void setSumhours(Double sumhours) {
        this.sumhours = sumhours;
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
        return this.checkOut;
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }

    public Integer getYearceiling() {
        return this.yearceiling;
    }
    
    public void setYearceiling(Integer yearceiling) {
        this.yearceiling = yearceiling;
    }

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

}