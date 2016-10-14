package com.nuaa.ec.model;



/**
 * TfoffCampusPracticeGuidancePerformance entity. @author MyEclipse Persistence Tools
 */

public class TfoffCampusPracticeGuidancePerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
//     private String teacherId;
     private Teacher teacher;
     private String projectName;
//     private String projectId;
     private TfoffCampusPracticeGuidanceLevel tfoffCampusPracticeGuidanceLevel;
     private Double finalScore;
     private String checkOut;
     private String spareTire;
     private Integer yearCeiling;
     private Double quantityUnit;
     private Double sumhours;
     private String offguidanceId;

     private String termId;

    // Constructors

    /** default constructor */
    public TfoffCampusPracticeGuidancePerformance() {
    }

	/** minimal constructor */
    public TfoffCampusPracticeGuidancePerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfoffCampusPracticeGuidancePerformance(Integer upid, Teacher teacher, String projectName, TfoffCampusPracticeGuidanceLevel tfoffCampusPracticeGuidanceLevel, Double finalScore, String checkOut, String spareTire, Integer yearCeiling, Double quantityUnit, Double sumhours, String offguidanceId) {
        this.upid = upid;
//        this.teacherId = teacherId;
        this.teacher=teacher;
        this.projectName = projectName;
        this.tfoffCampusPracticeGuidanceLevel=tfoffCampusPracticeGuidanceLevel;
        this.finalScore = finalScore;
        this.checkOut = checkOut;
        this.spareTire = spareTire;
        this.yearCeiling = yearCeiling;
        this.quantityUnit = quantityUnit;
        this.sumhours = sumhours;
        this.offguidanceId = offguidanceId;
    }

    public TfoffCampusPracticeGuidancePerformance(Integer upid,
    		Teacher teacher, String projectName, TfoffCampusPracticeGuidanceLevel tfoffCampusPracticeGuidanceLevel,
			Double finalScore, String checkOut, String spareTire,
			Integer yearCeiling, Double quantityUnit, Double sumhours,
			String offguidanceId, String termId) {
		super();
		this.upid = upid;
//		this.teacherId = teacherId;
		this.teacher=teacher;
		this.projectName = projectName;
//		this.projectId = projectId;
		this.tfoffCampusPracticeGuidanceLevel=tfoffCampusPracticeGuidanceLevel;
		this.finalScore = finalScore;
		this.checkOut = checkOut;
		this.spareTire = spareTire;
		this.yearCeiling = yearCeiling;
		this.quantityUnit = quantityUnit;
		this.sumhours = sumhours;
		this.offguidanceId = offguidanceId;
		this.termId = termId;
	}

	// Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public String getProjectName() {
        return this.projectName;
    }
    
    public void setProjectName(String projectName) {
        this.projectName = projectName;
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

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Integer getYearCeiling() {
        return this.yearCeiling;
    }
    
    public void setYearCeiling(Integer yearCeiling) {
        this.yearCeiling = yearCeiling;
    }

    public Double getQuantityUnit() {
        return this.quantityUnit;
    }
    
    public void setQuantityUnit(Double quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public Double getSumhours() {
        return this.sumhours;
    }
    
    public void setSumhours(Double sumhours) {
        this.sumhours = sumhours;
    }

    public String getOffguidanceId() {
        return this.offguidanceId;
    }
    
    public void setOffguidanceId(String offguidanceId) {
        this.offguidanceId = offguidanceId;
    }

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

	public TfoffCampusPracticeGuidanceLevel getTfoffCampusPracticeGuidanceLevel() {
		return tfoffCampusPracticeGuidanceLevel;
	}

	public void setTfoffCampusPracticeGuidanceLevel(
			TfoffCampusPracticeGuidanceLevel tfoffCampusPracticeGuidanceLevel) {
		this.tfoffCampusPracticeGuidanceLevel = tfoffCampusPracticeGuidanceLevel;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
   

}