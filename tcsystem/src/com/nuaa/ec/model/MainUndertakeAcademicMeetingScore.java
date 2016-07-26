package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * MainUndertakeAcademicMeetingScore entity. @author MyEclipse Persistence Tools
 */

public class MainUndertakeAcademicMeetingScore  implements java.io.Serializable {


    // Fields    

     private String acaMeetScoreId;
     private SubModular subModular;
     private MainUndertakeAcademicMeetingPlace mainUndertakeAcademicMeetingPlace;
     private MainUndertakeAcademicMeetingType mainUndertakeAcademicMeetingType;
     private Long score;
     private String spareTire;
     private Set teacherAndmainUndertakeAcademicMeetings = new HashSet(0);


    // Constructors

    /** default constructor */
    public MainUndertakeAcademicMeetingScore() {
    }

	/** minimal constructor */
    public MainUndertakeAcademicMeetingScore(String acaMeetScoreId) {
        this.acaMeetScoreId = acaMeetScoreId;
    }
    
    /** full constructor */
    public MainUndertakeAcademicMeetingScore(String acaMeetScoreId, SubModular subModular, MainUndertakeAcademicMeetingPlace mainUndertakeAcademicMeetingPlace, MainUndertakeAcademicMeetingType mainUndertakeAcademicMeetingType, Long score, String spareTire, Set teacherAndmainUndertakeAcademicMeetings) {
        this.acaMeetScoreId = acaMeetScoreId;
        this.subModular = subModular;
        this.mainUndertakeAcademicMeetingPlace = mainUndertakeAcademicMeetingPlace;
        this.mainUndertakeAcademicMeetingType = mainUndertakeAcademicMeetingType;
        this.score = score;
        this.spareTire = spareTire;
        this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
    }

   
    // Property accessors

    public String getAcaMeetScoreId() {
        return this.acaMeetScoreId;
    }
    
    public void setAcaMeetScoreId(String acaMeetScoreId) {
        this.acaMeetScoreId = acaMeetScoreId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public MainUndertakeAcademicMeetingPlace getMainUndertakeAcademicMeetingPlace() {
        return this.mainUndertakeAcademicMeetingPlace;
    }
    
    public void setMainUndertakeAcademicMeetingPlace(MainUndertakeAcademicMeetingPlace mainUndertakeAcademicMeetingPlace) {
        this.mainUndertakeAcademicMeetingPlace = mainUndertakeAcademicMeetingPlace;
    }

    public MainUndertakeAcademicMeetingType getMainUndertakeAcademicMeetingType() {
        return this.mainUndertakeAcademicMeetingType;
    }
    
    public void setMainUndertakeAcademicMeetingType(MainUndertakeAcademicMeetingType mainUndertakeAcademicMeetingType) {
        this.mainUndertakeAcademicMeetingType = mainUndertakeAcademicMeetingType;
    }

    public Long getScore() {
        return this.score;
    }
    
    public void setScore(Long score) {
        this.score = score;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getTeacherAndmainUndertakeAcademicMeetings() {
        return this.teacherAndmainUndertakeAcademicMeetings;
    }
    
    public void setTeacherAndmainUndertakeAcademicMeetings(Set teacherAndmainUndertakeAcademicMeetings) {
        this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
    }
   








}