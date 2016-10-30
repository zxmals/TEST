package com.nuaa.ec.model;



/**
 * TeacherAndinvitedExpertsSpeech entity. @author MyEclipse Persistence Tools
 */

public class TeacherAndinvitedExpertsSpeech  implements java.io.Serializable {


    // Fields    

     private Integer teacherAinvEsid;
     private InvitedExpertsSpeechScore invitedExpertsSpeechScore;
     private SelfUndertakeTask selfUndertakeTask;
     private Teacher teacher;
     private InvitedExpertsSpeech invitedExpertsSpeech;
     private Double finalScore;
     private String spareTire;
     private String checkOut;


    // Constructors

    /** default constructor */
    public TeacherAndinvitedExpertsSpeech() {
    }

	/** minimal constructor */
    public TeacherAndinvitedExpertsSpeech(Integer teacherAinvEsid) {
        this.teacherAinvEsid = teacherAinvEsid;
    }
    
    /** full constructor */
    public TeacherAndinvitedExpertsSpeech(Integer teacherAinvEsid, InvitedExpertsSpeechScore invitedExpertsSpeechScore, SelfUndertakeTask selfUndertakeTask, Teacher teacher, InvitedExpertsSpeech invitedExpertsSpeech, Double finalScore, String spareTire, String checkOut) {
        this.teacherAinvEsid = teacherAinvEsid;
        this.invitedExpertsSpeechScore = invitedExpertsSpeechScore;
        this.selfUndertakeTask = selfUndertakeTask;
        this.teacher = teacher;
        this.invitedExpertsSpeech = invitedExpertsSpeech;
        this.finalScore = finalScore;
        this.spareTire = spareTire;
        this.checkOut = checkOut;
    }

   
    // Property accessors

    public Integer getTeacherAinvEsid() {
        return this.teacherAinvEsid;
    }
    
    public void setTeacherAinvEsid(Integer teacherAinvEsid) {
        this.teacherAinvEsid = teacherAinvEsid;
    }

    public InvitedExpertsSpeechScore getInvitedExpertsSpeechScore() {
        return this.invitedExpertsSpeechScore;
    }
    
    public void setInvitedExpertsSpeechScore(InvitedExpertsSpeechScore invitedExpertsSpeechScore) {
        this.invitedExpertsSpeechScore = invitedExpertsSpeechScore;
    }

    public SelfUndertakeTask getSelfUndertakeTask() {
        return this.selfUndertakeTask;
    }
    
    public void setSelfUndertakeTask(SelfUndertakeTask selfUndertakeTask) {
        this.selfUndertakeTask = selfUndertakeTask;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public InvitedExpertsSpeech getInvitedExpertsSpeech() {
        return this.invitedExpertsSpeech;
    }
    
    public void setInvitedExpertsSpeech(InvitedExpertsSpeech invitedExpertsSpeech) {
        this.invitedExpertsSpeech = invitedExpertsSpeech;
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
        return this.checkOut.trim();
    }
    
    public void setCheckOut(String checkOut) {
        this.checkOut = checkOut;
    }
   








}