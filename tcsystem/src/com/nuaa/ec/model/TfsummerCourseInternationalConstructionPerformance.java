package com.nuaa.ec.model;



/**
 * TfsummerCourseInternationalConstructionPerformance entity. @author MyEclipse Persistence Tools
 */

public class TfsummerCourseInternationalConstructionPerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfsummerCourseInternationalConstructionLevel tfsummerCourseInternationalConstructionLevel;
     private Teacher teacher;
     private String projectId;
     private String projectName;
     private Double score;
     private String checkOut;
     private String spareTire;
     private Double quantityUnit;

     private String termId;

    // Constructors

    /** default constructor */
    public TfsummerCourseInternationalConstructionPerformance() {
    }

	/** minimal constructor */
    public TfsummerCourseInternationalConstructionPerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfsummerCourseInternationalConstructionPerformance(Integer upid, TfsummerCourseInternationalConstructionLevel tfsummerCourseInternationalConstructionLevel, Teacher teacher, String projectId, String projectName, Double score, String checkOut, String spareTire, Double quantityUnit) {
        this.upid = upid;
        this.tfsummerCourseInternationalConstructionLevel = tfsummerCourseInternationalConstructionLevel;
        this.teacher = teacher;
        this.projectId = projectId;
        this.projectName = projectName;
        this.score = score;
        this.checkOut = checkOut;
        this.spareTire = spareTire;
        this.quantityUnit = quantityUnit;
    }

    public TfsummerCourseInternationalConstructionPerformance(
			Integer upid,
			TfsummerCourseInternationalConstructionLevel tfsummerCourseInternationalConstructionLevel,
			Teacher teacher, String projectId, String projectName,
			Double score, String checkOut, String spareTire,
			Double quantityUnit, String termId) {
		this.upid = upid;
		this.tfsummerCourseInternationalConstructionLevel = tfsummerCourseInternationalConstructionLevel;
		this.teacher = teacher;
		this.projectId = projectId;
		this.projectName = projectName;
		this.score = score;
		this.checkOut = checkOut;
		this.spareTire = spareTire;
		this.quantityUnit = quantityUnit;
		this.termId = termId;
	}

	// Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfsummerCourseInternationalConstructionLevel getTfsummerCourseInternationalConstructionLevel() {
        return this.tfsummerCourseInternationalConstructionLevel;
    }
    
    public void setTfsummerCourseInternationalConstructionLevel(TfsummerCourseInternationalConstructionLevel tfsummerCourseInternationalConstructionLevel) {
        this.tfsummerCourseInternationalConstructionLevel = tfsummerCourseInternationalConstructionLevel;
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

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
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