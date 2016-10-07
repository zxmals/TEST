package com.nuaa.ec.model;



/**
 * TfdegreeThesisGuidancePerformance entity. @author MyEclipse Persistence Tools
 */

public class TfdegreeThesisGuidancePerformance  implements java.io.Serializable {


    // Fields    

     private String degreeThesisId;
     private TfdegreeThesisGuidanceRewardLevel tfdegreeThesisGuidanceRewardLevel;
     private Teacher teacher;
     private String degreeThesisnName;
     private Double finalScore;
     private String spareTire;
     private String checkOut;
     private String termId;

    // Constructors

    /** default constructor */
    public TfdegreeThesisGuidancePerformance() {
    }

	/** minimal constructor */
    public TfdegreeThesisGuidancePerformance(String degreeThesisId) {
        this.degreeThesisId = degreeThesisId;
    }
    
    /** full constructor */
    public TfdegreeThesisGuidancePerformance(String degreeThesisId, TfdegreeThesisGuidanceRewardLevel tfdegreeThesisGuidanceRewardLevel, Teacher teacher, String degreeThesisnName, Double finalScore, String spareTire, String checkOut) {
        this.degreeThesisId = degreeThesisId;
        this.tfdegreeThesisGuidanceRewardLevel = tfdegreeThesisGuidanceRewardLevel;
        this.teacher = teacher;
        this.degreeThesisnName = degreeThesisnName;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    
    // Property accessors

    public TfdegreeThesisGuidancePerformance(
			String degreeThesisId,
			TfdegreeThesisGuidanceRewardLevel tfdegreeThesisGuidanceRewardLevel,
			Teacher teacher, String degreeThesisnName, Double finalScore,
			String spareTire, String checkOut, String termId) {
		this.degreeThesisId = degreeThesisId;
		this.tfdegreeThesisGuidanceRewardLevel = tfdegreeThesisGuidanceRewardLevel;
		this.teacher = teacher;
		this.degreeThesisnName = degreeThesisnName;
		this.finalScore = finalScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.termId = termId;
	}

	public String getDegreeThesisId() {
        return this.degreeThesisId;
    }
    
    public void setDegreeThesisId(String degreeThesisId) {
        this.degreeThesisId = degreeThesisId;
    }

    public TfdegreeThesisGuidanceRewardLevel getTfdegreeThesisGuidanceRewardLevel() {
        return this.tfdegreeThesisGuidanceRewardLevel;
    }
    
    public void setTfdegreeThesisGuidanceRewardLevel(TfdegreeThesisGuidanceRewardLevel tfdegreeThesisGuidanceRewardLevel) {
        this.tfdegreeThesisGuidanceRewardLevel = tfdegreeThesisGuidanceRewardLevel;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getDegreeThesisnName() {
        return this.degreeThesisnName;
    }
    
    public void setDegreeThesisnName(String degreeThesisnName) {
        this.degreeThesisnName = degreeThesisnName;
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

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

}