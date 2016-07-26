package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * PaperRetrievalCondition entity. @author MyEclipse Persistence Tools
 */

public class PaperRetrievalCondition  implements java.io.Serializable {


    // Fields    

     private String prconditionId;
     private String prcondition;
     private String spareTire;
     private Set joinAcademicMeetingScores = new HashSet(0);
     private Set meetingPapers = new HashSet(0);


    // Constructors

    /** default constructor */
    public PaperRetrievalCondition() {
    }

	/** minimal constructor */
    public PaperRetrievalCondition(String prconditionId) {
        this.prconditionId = prconditionId;
    }
    
    /** full constructor */
    public PaperRetrievalCondition(String prconditionId, String prcondition, String spareTire, Set joinAcademicMeetingScores, Set meetingPapers) {
        this.prconditionId = prconditionId;
        this.prcondition = prcondition;
        this.spareTire = spareTire;
        this.joinAcademicMeetingScores = joinAcademicMeetingScores;
        this.meetingPapers = meetingPapers;
    }

   
    // Property accessors

    public String getPrconditionId() {
        return this.prconditionId;
    }
    
    public void setPrconditionId(String prconditionId) {
        this.prconditionId = prconditionId;
    }

    public String getPrcondition() {
        return this.prcondition;
    }
    
    public void setPrcondition(String prcondition) {
        this.prcondition = prcondition;
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

    public Set getMeetingPapers() {
        return this.meetingPapers;
    }
    
    public void setMeetingPapers(Set meetingPapers) {
        this.meetingPapers = meetingPapers;
    }
   








}