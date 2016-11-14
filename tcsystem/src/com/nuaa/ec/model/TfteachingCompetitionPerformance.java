package com.nuaa.ec.model;



/**
 * TfteachingCompetitionPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfteachingCompetitionPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfteachingCompetitionRewardLevel tfteachingCompetitionRewardLevel;
     private Teacher teacher;
     private String competitionId;
     private String competitionName;
     private String spareTire;
     private Double finalScore;
     private String checkOut;

     private String termId;

    // Constructors

    /** default constructor */
    public TfteachingCompetitionPerformance() {
    }

	/** minimal constructor */
    public TfteachingCompetitionPerformance(Integer upid, String competitionId) {
        this.upid = upid;
        this.competitionId = competitionId;
    }
    
    /** full constructor */
    public TfteachingCompetitionPerformance(Integer upid, TfteachingCompetitionRewardLevel tfteachingCompetitionRewardLevel, Teacher teacher, String competitionId, String competitionName, String spareTire, Double finalScore, String checkOut) {
        this.upid = upid;
        this.tfteachingCompetitionRewardLevel = tfteachingCompetitionRewardLevel;
        this.teacher = teacher;
        this.competitionId = competitionId;
        this.competitionName = competitionName;
        this.spareTire = spareTire;
        this.finalScore = finalScore;
        this.checkOut = checkOut;
    }

    public TfteachingCompetitionPerformance(Integer upid,
			TfteachingCompetitionRewardLevel tfteachingCompetitionRewardLevel,
			Teacher teacher, String competitionId, String competitionName,
			String spareTire, Double finalScore, String checkOut, String termId) {
		this.upid = upid;
		this.tfteachingCompetitionRewardLevel = tfteachingCompetitionRewardLevel;
		this.teacher = teacher;
		this.competitionId = competitionId;
		this.competitionName = competitionName;
		this.spareTire = spareTire;
		this.finalScore = finalScore;
		this.checkOut = checkOut;
		this.termId = termId;
	}

	// Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfteachingCompetitionRewardLevel getTfteachingCompetitionRewardLevel() {
        return this.tfteachingCompetitionRewardLevel;
    }
    
    public void setTfteachingCompetitionRewardLevel(TfteachingCompetitionRewardLevel tfteachingCompetitionRewardLevel) {
        this.tfteachingCompetitionRewardLevel = tfteachingCompetitionRewardLevel;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}
}