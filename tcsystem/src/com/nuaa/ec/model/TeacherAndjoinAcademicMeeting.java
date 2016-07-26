package com.nuaa.ec.model;



/**
 * TeacherAndjoinAcademicMeeting entity. @author MyEclipse Persistence Tools
 */

public class TeacherAndjoinAcademicMeeting  implements java.io.Serializable {


    // Fields    

     private Integer teacherAjacaMid;
     private MeetingPaper meetingPaper;
     private JoinAcademicMeetingScore joinAcademicMeetingScore;
     private Teacher teacher;
     private JoinAcademicMeeting joinAcademicMeeting;
     private Double finalScore;
     private String spareTire;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TeacherAndjoinAcademicMeeting() {
    }

	/** minimal constructor */
    public TeacherAndjoinAcademicMeeting(Integer teacherAjacaMid) {
        this.teacherAjacaMid = teacherAjacaMid;
    }
    
    /** full constructor */
    public TeacherAndjoinAcademicMeeting(Integer teacherAjacaMid, MeetingPaper meetingPaper, JoinAcademicMeetingScore joinAcademicMeetingScore, Teacher teacher, JoinAcademicMeeting joinAcademicMeeting, Double finalScore, String spareTire, String checkOut) {
        this.teacherAjacaMid = teacherAjacaMid;
        this.meetingPaper = meetingPaper;
        this.joinAcademicMeetingScore = joinAcademicMeetingScore;
        this.teacher = teacher;
        this.joinAcademicMeeting = joinAcademicMeeting;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getTeacherAjacaMid() {
        return this.teacherAjacaMid;
    }
    
    public void setTeacherAjacaMid(Integer teacherAjacaMid) {
        this.teacherAjacaMid = teacherAjacaMid;
    }

    public MeetingPaper getMeetingPaper() {
        return this.meetingPaper;
    }
    
    public void setMeetingPaper(MeetingPaper meetingPaper) {
        this.meetingPaper = meetingPaper;
    }

    public JoinAcademicMeetingScore getJoinAcademicMeetingScore() {
        return this.joinAcademicMeetingScore;
    }
    
    public void setJoinAcademicMeetingScore(JoinAcademicMeetingScore joinAcademicMeetingScore) {
        this.joinAcademicMeetingScore = joinAcademicMeetingScore;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public JoinAcademicMeeting getJoinAcademicMeeting() {
        return this.joinAcademicMeeting;
    }
    
    public void setJoinAcademicMeeting(JoinAcademicMeeting joinAcademicMeeting) {
        this.joinAcademicMeeting = joinAcademicMeeting;
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
   








}