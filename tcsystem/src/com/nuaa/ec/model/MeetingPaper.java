package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * MeetingPaper entity. @author MyEclipse Persistence Tools
 */

public class MeetingPaper  implements java.io.Serializable {


    // Fields    

     private String meetingPaperId;
     private PaperRetrievalCondition paperRetrievalCondition;
     private String authorName;
     private String authorIdentity;
     private String paperTitle;
     private String spareTire;
     private Set teacherAndjoinAcademicMeetings = new HashSet(0);


    // Constructors

    /** default constructor */
    public MeetingPaper() {
    }

	/** minimal constructor */
    public MeetingPaper(String meetingPaperId) {
        this.meetingPaperId = meetingPaperId;
    }
    
    /** full constructor */
    public MeetingPaper(String meetingPaperId, PaperRetrievalCondition paperRetrievalCondition, String authorName, String authorIdentity, String paperTitle, String spareTire, Set teacherAndjoinAcademicMeetings) {
        this.meetingPaperId = meetingPaperId;
        this.paperRetrievalCondition = paperRetrievalCondition;
        this.authorName = authorName;
        this.authorIdentity = authorIdentity;
        this.paperTitle = paperTitle;
        this.spareTire = spareTire;
        this.teacherAndjoinAcademicMeetings = teacherAndjoinAcademicMeetings;
    }

   
    // Property accessors

    public String getMeetingPaperId() {
        return this.meetingPaperId;
    }
    
    public void setMeetingPaperId(String meetingPaperId) {
        this.meetingPaperId = meetingPaperId;
    }

    public PaperRetrievalCondition getPaperRetrievalCondition() {
        return this.paperRetrievalCondition;
    }
    
    public void setPaperRetrievalCondition(PaperRetrievalCondition paperRetrievalCondition) {
        this.paperRetrievalCondition = paperRetrievalCondition;
    }

    public String getAuthorName() {
        return this.authorName;
    }
    
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorIdentity() {
        return this.authorIdentity;
    }
    
    public void setAuthorIdentity(String authorIdentity) {
        this.authorIdentity = authorIdentity;
    }

    public String getPaperTitle() {
        return this.paperTitle;
    }
    
    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
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