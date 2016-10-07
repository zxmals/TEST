package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * VacollectiveAct entity. @author MyEclipse Persistence Tools
 */

public class VacollectiveAct  implements java.io.Serializable {


    // Fields    

     private String actId;
     private Teacher teacher;
     private String actName;
     private String attendee;
     private Double score;
     private String actType;
     private Double baseNum;
     private String actapplyfile;
     private String spareTire;
     private String aspareTire;
     private Set vacollectiveActivitiesPublishs = new HashSet(0);


    // Constructors

    /** default constructor */
    public VacollectiveAct() {
    }

	/** minimal constructor */
    public VacollectiveAct(String actId) {
        this.actId = actId;
    }

    
    
    public VacollectiveAct(String actName, String attendee) {
		super();
		this.actName = actName;
		this.attendee = attendee;
	}

	/** full constructor */
    public VacollectiveAct(String actId, Teacher teacher, String actName, String attendee, Double score, String actType, Double baseNum, String actapplyfile,String spareTire, String aspareTire, Set vacollectiveActivitiesPublishs) {
        this.actId = actId;
        this.teacher = teacher;
        this.actName = actName;
        this.attendee = attendee;
        this.score = score;
        this.actType = actType;
        this.baseNum = baseNum;
        this.actapplyfile = actapplyfile;
        this.spareTire = spareTire;
        this.aspareTire = aspareTire;
        this.vacollectiveActivitiesPublishs = vacollectiveActivitiesPublishs;
    }

   
    // Property accessors

    public String getActId() {
        return this.actId;
    }
    
    public void setActId(String actId) {
        this.actId = actId;
    }

    public Teacher getTeacher() {
        return this.teacher;
    }
    
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String getActName() {
        return this.actName;
    }
    
    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getAttendee() {
        return this.attendee;
    }
    
    public void setAttendee(String attendee) {
        this.attendee = attendee;
    }

    public Double getScore() {
        return this.score;
    }
    
    public void setScore(Double score) {
        this.score = score;
    }

    public String getActType() {
        return this.actType;
    }
    
    public void setActType(String actType) {
        this.actType = actType;
    }

    public Double getBaseNum() {
        return this.baseNum;
    }
    
    public void setBaseNum(Double baseNum) {
        this.baseNum = baseNum;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getAspareTire() {
        return this.aspareTire;
    }
    
    public void setAspareTire(String aspareTire) {
        this.aspareTire = aspareTire;
    }

    public Set getVacollectiveActivitiesPublishs() {
        return this.vacollectiveActivitiesPublishs;
    }
    
    public void setVacollectiveActivitiesPublishs(Set vacollectiveActivitiesPublishs) {
        this.vacollectiveActivitiesPublishs = vacollectiveActivitiesPublishs;
    }

	public String getActapplyfile() {
		return actapplyfile;
	}

	public void setActapplyfile(String actapplyfile) {
		this.actapplyfile = actapplyfile;
	}
   








}