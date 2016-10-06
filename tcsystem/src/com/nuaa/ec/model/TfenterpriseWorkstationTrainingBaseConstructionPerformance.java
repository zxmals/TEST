package com.nuaa.ec.model;



/**
 * TfenterpriseWorkstationTrainingBaseConstructionPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfenterpriseWorkstationTrainingBaseConstructionPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfenterpriseWorkstationTrainingbaseConstructionLevel tfenterpriseWorkstationTrainingbaseConstructionLevel;
     private SelfUndertakeTask selfUndertakeTask;
     private Teacher teacher;
     private String projectId;
     private String projectName;
     private Double projectSumScore;
     private Double singleScore;
     private String spareTire;
     private String checkOut;
     private Double quantityUnit;

     private String termId;

    // Constructors

    /** default constructor */
    public TfenterpriseWorkstationTrainingBaseConstructionPerformance() {
    }

	/** minimal constructor */
    public TfenterpriseWorkstationTrainingBaseConstructionPerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfenterpriseWorkstationTrainingBaseConstructionPerformance(Integer upid, TfenterpriseWorkstationTrainingbaseConstructionLevel tfenterpriseWorkstationTrainingbaseConstructionLevel, SelfUndertakeTask selfUndertakeTask, Teacher teacher, String projectId, String projectName, Double projectSumScore, Double singleScore, String spareTire, String checkOut, Double quantityUnit) {
        this.upid = upid;
        this.tfenterpriseWorkstationTrainingbaseConstructionLevel = tfenterpriseWorkstationTrainingbaseConstructionLevel;
        this.selfUndertakeTask = selfUndertakeTask;
        this.teacher = teacher;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectSumScore = projectSumScore;
        this.singleScore = singleScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
        this.quantityUnit = quantityUnit;
    }

   
    // Property accessors

    public TfenterpriseWorkstationTrainingBaseConstructionPerformance(
			Integer upid,
			TfenterpriseWorkstationTrainingbaseConstructionLevel tfenterpriseWorkstationTrainingbaseConstructionLevel,
			SelfUndertakeTask selfUndertakeTask, Teacher teacher,
			String projectId, String projectName, Double projectSumScore,
			Double singleScore, String spareTire, String checkOut,
			Double quantityUnit, String termId) {
		this.upid = upid;
		this.tfenterpriseWorkstationTrainingbaseConstructionLevel = tfenterpriseWorkstationTrainingbaseConstructionLevel;
		this.selfUndertakeTask = selfUndertakeTask;
		this.teacher = teacher;
		this.projectId = projectId;
		this.projectName = projectName;
		this.projectSumScore = projectSumScore;
		this.singleScore = singleScore;
		this.spareTire = spareTire;
		this.checkOut = checkOut;
		this.quantityUnit = quantityUnit;
		this.termId = termId;
	}

	public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfenterpriseWorkstationTrainingbaseConstructionLevel getTfenterpriseWorkstationTrainingbaseConstructionLevel() {
        return this.tfenterpriseWorkstationTrainingbaseConstructionLevel;
    }
    
    public void setTfenterpriseWorkstationTrainingbaseConstructionLevel(TfenterpriseWorkstationTrainingbaseConstructionLevel tfenterpriseWorkstationTrainingbaseConstructionLevel) {
        this.tfenterpriseWorkstationTrainingbaseConstructionLevel = tfenterpriseWorkstationTrainingbaseConstructionLevel;
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

    public Double getQuantityUnit() {
        return this.quantityUnit;
    }
    
    public void setQuantityUnit(Double quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}

}