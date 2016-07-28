package com.nuaa.ec.model;



/**
 * TfteachingAbilityImprovePerformance entity. @author MyEclipse Persistence Tools
 */

public class TfteachingAbilityImprovePerformance  implements java.io.Serializable {


    // Fields    

     private Integer puid;
     private TfteachingAbilityImproveLevel tfteachingAbilityImproveLevel;
     private Teacher teacher;
     private String eventId;
     private String eventName;
     private Double finalScore;
     private String spareTire;
     private String checkOut;
     private Integer yearceiling;
     private Double sumhours;


    // Constructors

    /** default constructor */
    public TfteachingAbilityImprovePerformance() {
    }

	/** minimal constructor */
    public TfteachingAbilityImprovePerformance(Integer puid, String eventId) {
        this.puid = puid;
        this.eventId = eventId;
    }
    
    /** full constructor */
    public TfteachingAbilityImprovePerformance(Integer puid, TfteachingAbilityImproveLevel tfteachingAbilityImproveLevel, Teacher teacher, String eventId, String eventName, Double finalScore, String spareTire, String checkOut, Integer yearceiling, Double sumhours) {
        this.puid = puid;
        this.tfteachingAbilityImproveLevel = tfteachingAbilityImproveLevel;
        this.teacher = teacher;
        this.eventId = eventId;
        this.eventName = eventName;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
        this.yearceiling = yearceiling;
        this.sumhours = sumhours;
    }

   
    // Property accessors

    public Integer getPuid() {
        return this.puid;
    }
    
    public void setPuid(Integer puid) {
        this.puid = puid;
    }

    public TfteachingAbilityImproveLevel getTfteachingAbilityImproveLevel() {
        return this.tfteachingAbilityImproveLevel;
    }
    
    public void setTfteachingAbilityImproveLevel(TfteachingAbilityImproveLevel tfteachingAbilityImproveLevel) {
        this.tfteachingAbilityImproveLevel = tfteachingAbilityImproveLevel;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getEventId() {
        return this.eventId;
    }
    
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getEventName() {
        return this.eventName;
    }
    
    public void setEventName(String eventName) {
        this.eventName = eventName;
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

    public Double getSumhours() {
        return this.sumhours;
    }
    
    public void setSumhours(Double sumhours) {
        this.sumhours = sumhours;
    }
   








}