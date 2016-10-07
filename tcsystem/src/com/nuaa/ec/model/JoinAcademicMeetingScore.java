package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * JoinAcademicMeetingScore entity. @author MyEclipse Persistence Tools
 */

public class JoinAcademicMeetingScore  implements java.io.Serializable {


    // Fields    

     private String joinAmscoreId;
     private SubModular subModular;
     private PaperRetrievalCondition paperRetrievalCondition;
     private MeetingType meetingType;
     private Long score;
     private String spareTire;
     private Set teacherAndjoinAcademicMeetings = new HashSet(0);


    // Constructors

    /** default constructor */
    public JoinAcademicMeetingScore() {
    }

	/** minimal constructor */
    public JoinAcademicMeetingScore(String joinAmscoreId) {
        this.joinAmscoreId = joinAmscoreId;
    }
    
    /** full constructor */
    public JoinAcademicMeetingScore(String joinAmscoreId, SubModular subModular, PaperRetrievalCondition paperRetrievalCondition, MeetingType meetingType, Long score, String spareTire, Set teacherAndjoinAcademicMeetings) {
        this.joinAmscoreId = joinAmscoreId;
        this.subModular = subModular;
        this.paperRetrievalCondition = paperRetrievalCondition;
        this.meetingType = meetingType;
        this.score = score;
        this.spareTire = spareTire;
        this.teacherAndjoinAcademicMeetings = teacherAndjoinAcademicMeetings;
    }

   
    // Property accessors

    public String getJoinAmscoreId() {
        return this.joinAmscoreId;
    }
    
    public void setJoinAmscoreId(String joinAmscoreId) {
        this.joinAmscoreId = joinAmscoreId;
    }

    public SubModular getSubModular() {
        return this.subModular;
    }
    
    public void setSubModular(SubModular subModular) {
        this.subModular = subModular;
    }

    public PaperRetrievalCondition getPaperRetrievalCondition() {
        return this.paperRetrievalCondition;
    }
    
    public void setPaperRetrievalCondition(PaperRetrievalCondition paperRetrievalCondition) {
        this.paperRetrievalCondition = paperRetrievalCondition;
    }

    public MeetingType getMeetingType() {
        return this.meetingType;
    }
    
    public void setMeetingType(MeetingType meetingType) {
        this.meetingType = meetingType;
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

    public Set getTeacherAndjoinAcademicMeetings() {
        return this.teacherAndjoinAcademicMeetings;
    }
    
    public void setTeacherAndjoinAcademicMeetings(Set teacherAndjoinAcademicMeetings) {
        this.teacherAndjoinAcademicMeetings = teacherAndjoinAcademicMeetings;
    }
   








}