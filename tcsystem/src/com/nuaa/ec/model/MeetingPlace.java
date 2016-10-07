package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * MeetingPlace entity. @author MyEclipse Persistence Tools
 */

public class MeetingPlace  implements java.io.Serializable {


    // Fields    

     private String meetingPlaceId;
     private String meetingPlace;
     private String spareTire;
     private Set joinAcademicMeetings = new HashSet(0);


    // Constructors

    /** default constructor */
    public MeetingPlace() {
    }

	/** minimal constructor */
    public MeetingPlace(String meetingPlaceId) {
        this.meetingPlaceId = meetingPlaceId;
    }
    
    /** full constructor */
    public MeetingPlace(String meetingPlaceId, String meetingPlace, String spareTire, Set joinAcademicMeetings) {
        this.meetingPlaceId = meetingPlaceId;
        this.meetingPlace = meetingPlace;
        this.spareTire = spareTire;
        this.joinAcademicMeetings = joinAcademicMeetings;
    }

   
    // Property accessors

    public String getMeetingPlaceId() {
        return this.meetingPlaceId;
    }
    
    public void setMeetingPlaceId(String meetingPlaceId) {
        this.meetingPlaceId = meetingPlaceId;
    }

    public String getMeetingPlace() {
        return this.meetingPlace;
    }
    
    public void setMeetingPlace(String meetingPlace) {
        this.meetingPlace = meetingPlace;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getJoinAcademicMeetings() {
        return this.joinAcademicMeetings;
    }
    
    public void setJoinAcademicMeetings(Set joinAcademicMeetings) {
        this.joinAcademicMeetings = joinAcademicMeetings;
    }
   








}