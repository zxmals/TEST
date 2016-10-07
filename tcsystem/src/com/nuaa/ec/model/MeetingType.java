package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * MeetingType entity. @author MyEclipse Persistence Tools
 */

public class MeetingType  implements java.io.Serializable {


    // Fields    

     private String meetingTypeId;
     private String meetingTypeName;
     private String spareTire;
     private Set joinAcademicMeetingScores = new HashSet(0);
     private Set joinAcademicMeetings = new HashSet(0);


    // Constructors

    /** default constructor */
    public MeetingType() {
    }

	/** minimal constructor */
    public MeetingType(String meetingTypeId) {
        this.meetingTypeId = meetingTypeId;
    }
    
    /** full constructor */
    public MeetingType(String meetingTypeId, String meetingTypeName, String spareTire, Set joinAcademicMeetingScores, Set joinAcademicMeetings) {
        this.meetingTypeId = meetingTypeId;
        this.meetingTypeName = meetingTypeName;
        this.spareTire = spareTire;
        this.joinAcademicMeetingScores = joinAcademicMeetingScores;
        this.joinAcademicMeetings = joinAcademicMeetings;
    }

   
    // Property accessors

    public String getMeetingTypeId() {
        return this.meetingTypeId;
    }
    
    public void setMeetingTypeId(String meetingTypeId) {
        this.meetingTypeId = meetingTypeId;
    }

    public String getMeetingTypeName() {
        return this.meetingTypeName;
    }
    
    public void setMeetingTypeName(String meetingTypeName) {
        this.meetingTypeName = meetingTypeName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public Set getJoinAcademicMeetingScores() {
        return this.joinAcademicMeetingScores;
    }
    
    public void setJoinAcademicMeetingScores(Set joinAcademicMeetingScores) {
        this.joinAcademicMeetingScores = joinAcademicMeetingScores;
    }

    public Set getJoinAcademicMeetings() {
        return this.joinAcademicMeetings;
    }
    
    public void setJoinAcademicMeetings(Set joinAcademicMeetings) {
        this.joinAcademicMeetings = joinAcademicMeetings;
    }
   








}