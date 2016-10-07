package com.nuaa.ec.model;



/**
 * TfpracticeInnovationGuidePerformance entity. @author MyEclipse Persistence Tools
 */

public class TfpracticeInnovationGuidePerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfpracticeInnovationGuideLevel tfpracticeInnovationGuideLevel;
     private Teacher teacher;
     private TfpracticeInnovationGuideGraduationThesisGuideEvalution tfpracticeInnovationGuideGraduationThesisGuideEvalution;
     private String projectId;
     private String projectName;
     private Double finalScore;
     private String checkOut;
     private String spareTire;

     private String termId;

    // Constructors

    /** default constructor */
    public TfpracticeInnovationGuidePerformance() {
    }

	/** minimal constructor */
    public TfpracticeInnovationGuidePerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfpracticeInnovationGuidePerformance(Integer upid, TfpracticeInnovationGuideLevel tfpracticeInnovationGuideLevel, Teacher teacher, TfpracticeInnovationGuideGraduationThesisGuideEvalution tfpracticeInnovationGuideGraduationThesisGuideEvalution, String projectId, String projectName, Double finalScore, String checkOut, String spareTire) {
        this.upid = upid;
        this.tfpracticeInnovationGuideLevel = tfpracticeInnovationGuideLevel;
        this.teacher = teacher;
        this.tfpracticeInnovationGuideGraduationThesisGuideEvalution = tfpracticeInnovationGuideGraduationThesisGuideEvalution;
        this.projectId = projectId;
        this.projectName = projectName;
        this.finalScore = finalScore;
        this.checkOut = checkOut;
        this.spareTire = spareTire;
    }

    public TfpracticeInnovationGuidePerformance(
			Integer upid,
			TfpracticeInnovationGuideLevel tfpracticeInnovationGuideLevel,
			Teacher teacher,
			TfpracticeInnovationGuideGraduationThesisGuideEvalution tfpracticeInnovationGuideGraduationThesisGuideEvalution,
			String projectId, String projectName, Double finalScore,
			String checkOut, String spareTire, String termId) {
		this.upid = upid;
		this.tfpracticeInnovationGuideLevel = tfpracticeInnovationGuideLevel;
		this.teacher = teacher;
		this.tfpracticeInnovationGuideGraduationThesisGuideEvalution = tfpracticeInnovationGuideGraduationThesisGuideEvalution;
		this.projectId = projectId;
		this.projectName = projectName;
		this.finalScore = finalScore;
		this.checkOut = checkOut;
		this.spareTire = spareTire;
		this.termId = termId;
	}

	// Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfpracticeInnovationGuideLevel getTfpracticeInnovationGuideLevel() {
        return this.tfpracticeInnovationGuideLevel;
    }
    
    public void setTfpracticeInnovationGuideLevel(TfpracticeInnovationGuideLevel tfpracticeInnovationGuideLevel) {
        this.tfpracticeInnovationGuideLevel = tfpracticeInnovationGuideLevel;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public TfpracticeInnovationGuideGraduationThesisGuideEvalution getTfpracticeInnovationGuideGraduationThesisGuideEvalution() {
        return this.tfpracticeInnovationGuideGraduationThesisGuideEvalution;
    }
    
    public void setTfpracticeInnovationGuideGraduationThesisGuideEvalution(TfpracticeInnovationGuideGraduationThesisGuideEvalution tfpracticeInnovationGuideGraduationThesisGuideEvalution) {
        this.tfpracticeInnovationGuideGraduationThesisGuideEvalution = tfpracticeInnovationGuideGraduationThesisGuideEvalution;
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

	public String getTermId() {
		return termId;
	}

	public void setTermId(String termId) {
		this.termId = termId;
	}
   




}