package com.nuaa.ec.model;



/**
 * TeacherAndmainUndertakeAcademicMeeting entity. @author MyEclipse Persistence Tools
 */

public class TeacherAndmainUndertakeAcademicMeeting  implements java.io.Serializable {


    // Fields    

     private Integer teacherAmuamid;
     private MainUndertakeAcademicMeetingScore mainUndertakeAcademicMeetingScore;
     private SelfUndertakeTask selfUndertakeTask;
     private MainUndertakeAcademicMeeting mainUndertakeAcademicMeeting;
     private Teacher teacher;
     private Double finalScore;
     private String spareTire;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TeacherAndmainUndertakeAcademicMeeting() {
    }

	/** minimal constructor */
    public TeacherAndmainUndertakeAcademicMeeting(Integer teacherAmuamid) {
        this.teacherAmuamid = teacherAmuamid;
    }
    
    /** full constructor */
    public TeacherAndmainUndertakeAcademicMeeting(Integer teacherAmuamid, MainUndertakeAcademicMeetingScore mainUndertakeAcademicMeetingScore, SelfUndertakeTask selfUndertakeTask, MainUndertakeAcademicMeeting mainUndertakeAcademicMeeting, Teacher teacher, Double finalScore, String spareTire, String checkOut) {
        this.teacherAmuamid = teacherAmuamid;
        this.mainUndertakeAcademicMeetingScore = mainUndertakeAcademicMeetingScore;
        this.selfUndertakeTask = selfUndertakeTask;
        this.mainUndertakeAcademicMeeting = mainUndertakeAcademicMeeting;
        this.teacher = teacher;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getTeacherAmuamid() {
        return this.teacherAmuamid;
    }
    
    public void setTeacherAmuamid(Integer teacherAmuamid) {
        this.teacherAmuamid = teacherAmuamid;
    }

    public MainUndertakeAcademicMeetingScore getMainUndertakeAcademicMeetingScore() {
        return this.mainUndertakeAcademicMeetingScore;
    }
    
    public void setMainUndertakeAcademicMeetingScore(MainUndertakeAcademicMeetingScore mainUndertakeAcademicMeetingScore) {
        this.mainUndertakeAcademicMeetingScore = mainUndertakeAcademicMeetingScore;
    }

    public SelfUndertakeTask getSelfUndertakeTask() {
        return this.selfUndertakeTask;
    }
    
    public void setSelfUndertakeTask(SelfUndertakeTask selfUndertakeTask) {
        this.selfUndertakeTask = selfUndertakeTask;
    }

    public MainUndertakeAcademicMeeting getMainUndertakeAcademicMeeting() {
        return this.mainUndertakeAcademicMeeting;
    }
    
    public void setMainUndertakeAcademicMeeting(MainUndertakeAcademicMeeting mainUndertakeAcademicMeeting) {
        this.mainUndertakeAcademicMeeting = mainUndertakeAcademicMeeting;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
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