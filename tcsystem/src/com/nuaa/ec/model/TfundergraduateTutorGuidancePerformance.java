package com.nuaa.ec.model;



/**
 * TfundergraduateTutorGuidancePerformance entity. @author MyEclipse Persistence Tools
 */

public class TfundergraduateTutorGuidancePerformance  implements java.io.Serializable {


    // Fields    

     private Integer upid;
     private TfundergraduateTutorGuidanceCache tfundergraduateTutorGuidanceCache;
     private Teacher teacher;
     private Integer studentQuantity;
     private Double years;
     private Double finalScore;
     private String checkOut;
     private String spareTire;
     private Integer yearceiling;


    // Constructors

    /** default constructor */
    public TfundergraduateTutorGuidancePerformance() {
    }

	/** minimal constructor */
    public TfundergraduateTutorGuidancePerformance(Integer upid) {
        this.upid = upid;
    }
    
    /** full constructor */
    public TfundergraduateTutorGuidancePerformance(Integer upid, TfundergraduateTutorGuidanceCache tfundergraduateTutorGuidanceCache, Teacher teacher, Integer studentQuantity, Double years, Double finalScore, String checkOut, String spareTire, Integer yearceiling) {
        this.upid = upid;
        this.tfundergraduateTutorGuidanceCache = tfundergraduateTutorGuidanceCache;
        this.teacher = teacher;
        this.studentQuantity = studentQuantity;
        this.years = years;
        this.finalScore = finalScore;
        this.checkOut = checkOut;
        this.spareTire = spareTire;
        this.yearceiling = yearceiling;
    }

   
    // Property accessors

    public Integer getUpid() {
        return this.upid;
    }
    
    public void setUpid(Integer upid) {
        this.upid = upid;
    }

    public TfundergraduateTutorGuidanceCache getTfundergraduateTutorGuidanceCache() {
        return this.tfundergraduateTutorGuidanceCache;
    }
    
    public void setTfundergraduateTutorGuidanceCache(TfundergraduateTutorGuidanceCache tfundergraduateTutorGuidanceCache) {
        this.tfundergraduateTutorGuidanceCache = tfundergraduateTutorGuidanceCache;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getStudentQuantity() {
        return this.studentQuantity;
    }
    
    public void setStudentQuantity(Integer studentQuantity) {
        this.studentQuantity = studentQuantity;
    }

    public Double getYears() {
        return this.years;
    }
    
    public void setYears(Double years) {
        this.years = years;
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

    public Integer getYearceiling() {
        return this.yearceiling;
    }
    
    public void setYearceiling(Integer yearceiling) {
        this.yearceiling = yearceiling;
    }
   








}