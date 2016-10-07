package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * MainUndertakeAcademicMeetingType entity. @author MyEclipse Persistence Tools
 */

public class MainUndertakeAcademicMeetingType  implements java.io.Serializable {


    // Fields    

     private String acaMeetTypeId;
     private String acaMeetType;
     private String spareTire;
     private Set mainUndertakeAcademicMeetings = new HashSet(0);
     private Set mainUndertakeAcademicMeetingScores = new HashSet(0);


    // Constructors

    /** default constructor */
    public MainUndertakeAcademicMeetingType() {
    }

	/** minimal constructor */
    public MainUndertakeAcademicMeetingType(String acaMeetTypeId) {
        this.acaMeetTypeId = acaMeetTypeId;
    }
    
    /** full constructor */
    public MainUndertakeAcademicMeetingType(String acaMeetTypeId, String acaMeetType, String spareTire, Set mainUndertakeAcademicMeetings, Set mainUndertakeAcademicMeetingScores) {
        this.acaMeetTypeId = acaMeetTypeId;
        this.acaMeetType = acaMeetType;
        this.spareTire = spareTire;
        this.mainUndertakeAcademicMeetings = mainUndertakeAcademicMeetings;
        this.mainUndertakeAcademicMeetingScores = mainUndertakeAcademicMeetingScores;
    }

   
    // Property accessors

    public String getAcaMeetTypeId() {
        return this.acaMeetTypeId;
    }
    
    public void setAcaMeetTypeId(String acaMeetTypeId) {
        this.acaMeetTypeId = acaMeetTypeId;
    }

    public String getAcaMeetType() {
        return this.acaMeetType;
    }
    
    public void setAcaMeetType(String acaMeetType) {
        this.acaMeetType = acaMeetType;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getMainUndertakeAcademicMeetings() {
        return this.mainUndertakeAcademicMeetings;
    }
    
    public void setMainUndertakeAcademicMeetings(Set mainUndertakeAcademicMeetings) {
        this.mainUndertakeAcademicMeetings = mainUndertakeAcademicMeetings;
    }

    public Set getMainUndertakeAcademicMeetingScores() {
        return this.mainUndertakeAcademicMeetingScores;
    }
    
    public void setMainUndertakeAcademicMeetingScores(Set mainUndertakeAcademicMeetingScores) {
        this.mainUndertakeAcademicMeetingScores = mainUndertakeAcademicMeetingScores;
    }
   








}