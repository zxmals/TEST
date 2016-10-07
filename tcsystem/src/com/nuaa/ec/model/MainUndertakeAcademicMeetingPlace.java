package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * MainUndertakeAcademicMeetingPlace entity. @author MyEclipse Persistence Tools
 */

public class MainUndertakeAcademicMeetingPlace  implements java.io.Serializable {


    // Fields    

     private String acaMeetPlaceId;
     private String acaMeetPlace;
     private String spareTire;
     private Set mainUndertakeAcademicMeetings = new HashSet(0);
     private Set mainUndertakeAcademicMeetingScores = new HashSet(0);


    // Constructors

    /** default constructor */
    public MainUndertakeAcademicMeetingPlace() {
    }

	/** minimal constructor */
    public MainUndertakeAcademicMeetingPlace(String acaMeetPlaceId) {
        this.acaMeetPlaceId = acaMeetPlaceId;
    }
    
    /** full constructor */
    public MainUndertakeAcademicMeetingPlace(String acaMeetPlaceId, String acaMeetPlace, String spareTire, Set mainUndertakeAcademicMeetings, Set mainUndertakeAcademicMeetingScores) {
        this.acaMeetPlaceId = acaMeetPlaceId;
        this.acaMeetPlace = acaMeetPlace;
        this.spareTire = spareTire;
        this.mainUndertakeAcademicMeetings = mainUndertakeAcademicMeetings;
        this.mainUndertakeAcademicMeetingScores = mainUndertakeAcademicMeetingScores;
    }

   
    // Property accessors

    public String getAcaMeetPlaceId() {
        return this.acaMeetPlaceId;
    }
    
    public void setAcaMeetPlaceId(String acaMeetPlaceId) {
        this.acaMeetPlaceId = acaMeetPlaceId;
    }

    public String getAcaMeetPlace() {
        return this.acaMeetPlace;
    }
    
    public void setAcaMeetPlace(String acaMeetPlace) {
        this.acaMeetPlace = acaMeetPlace;
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