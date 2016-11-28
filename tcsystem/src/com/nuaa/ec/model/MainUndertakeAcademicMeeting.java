package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;


/**
 * MainUndertakeAcademicMeeting entity. @author MyEclipse Persistence Tools
 */

public class MainUndertakeAcademicMeeting  implements java.io.Serializable {


    // Fields    

     private String acaMeetingId;
     private MainUndertakeAcademicMeetingPlace mainUndertakeAcademicMeetingPlace;
     private MainUndertakeAcademicMeetingType mainUndertakeAcademicMeetingType;
     private String chargePerson;
     private String acaMeetingName;
     private String spareTire;
     private String chargePersonId;
     private String checkout;
     private String meetingdate;
     private String researchLabId;
     public MainUndertakeAcademicMeeting(
			String acaMeetingId,
			MainUndertakeAcademicMeetingPlace mainUndertakeAcademicMeetingPlace,
			MainUndertakeAcademicMeetingType mainUndertakeAcademicMeetingType,
			String chargePerson, String acaMeetingName, String spareTire,
			String chargePersonId, String checkout, String meetingdate,
			String researchLabId, Set teacherAndmainUndertakeAcademicMeetings) {
		this.acaMeetingId = acaMeetingId;
		this.mainUndertakeAcademicMeetingPlace = mainUndertakeAcademicMeetingPlace;
		this.mainUndertakeAcademicMeetingType = mainUndertakeAcademicMeetingType;
		this.chargePerson = chargePerson;
		this.acaMeetingName = acaMeetingName;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.checkout = checkout;
		this.meetingdate = meetingdate;
		this.researchLabId = researchLabId;
		this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
	}

	private Set teacherAndmainUndertakeAcademicMeetings = new HashSet(0);


    // Constructors

    /** default constructor */
    public MainUndertakeAcademicMeeting() {
    }

	/** minimal constructor */
    public MainUndertakeAcademicMeeting(String acaMeetingId) {
        this.acaMeetingId = acaMeetingId;
    }
    
    /** full constructor */
    public MainUndertakeAcademicMeeting(String acaMeetingId, MainUndertakeAcademicMeetingPlace mainUndertakeAcademicMeetingPlace, MainUndertakeAcademicMeetingType mainUndertakeAcademicMeetingType, String chargePerson, String acaMeetingName, String spareTire, String chargePersonId, String checkout, String meetingdate, Set teacherAndmainUndertakeAcademicMeetings) {
        this.acaMeetingId = acaMeetingId;
        this.mainUndertakeAcademicMeetingPlace = mainUndertakeAcademicMeetingPlace;
        this.mainUndertakeAcademicMeetingType = mainUndertakeAcademicMeetingType;
        this.chargePerson = chargePerson;
        this.acaMeetingName = acaMeetingName;
        this.spareTire = spareTire;
        this.chargePersonId = chargePersonId;
        this.checkout = checkout;
        this.meetingdate = meetingdate;
        this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
    }

   
    // Property accessors

    public String getAcaMeetingId() {
        return this.acaMeetingId;
    }
    
    public void setAcaMeetingId(String acaMeetingId) {
        this.acaMeetingId = acaMeetingId;
    }

    public MainUndertakeAcademicMeetingPlace getMainUndertakeAcademicMeetingPlace() {
        return this.mainUndertakeAcademicMeetingPlace;
    }
    
    public void setMainUndertakeAcademicMeetingPlace(MainUndertakeAcademicMeetingPlace mainUndertakeAcademicMeetingPlace) {
        this.mainUndertakeAcademicMeetingPlace = mainUndertakeAcademicMeetingPlace;
    }

    public MainUndertakeAcademicMeetingType getMainUndertakeAcademicMeetingType() {
        return this.mainUndertakeAcademicMeetingType;
    }
    
    public void setMainUndertakeAcademicMeetingType(MainUndertakeAcademicMeetingType mainUndertakeAcademicMeetingType) {
        this.mainUndertakeAcademicMeetingType = mainUndertakeAcademicMeetingType;
    }

    public String getChargePerson() {
        return this.chargePerson;
    }
    
    public void setChargePerson(String chargePerson) {
        this.chargePerson = chargePerson;
    }

    public String getAcaMeetingName() {
        return this.acaMeetingName;
    }
    
    public void setAcaMeetingName(String acaMeetingName) {
        this.acaMeetingName = acaMeetingName;
    }

    public String getSpareTire() {
        return this.spareTire;
    }
    
    public void setSpareTire(String spareTire) {
        this.spareTire = spareTire;
    }

    public String getChargePersonId() {
        return this.chargePersonId;
    }
    
    public void setChargePersonId(String chargePersonId) {
        this.chargePersonId = chargePersonId;
    }

    public String getCheckout() {
        return this.checkout;
    }
    
    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public String getMeetingdate() {
        return this.meetingdate;
    }
    
    public void setMeetingdate(String meetingdate) {
        this.meetingdate = meetingdate;
    }

    public Set getTeacherAndmainUndertakeAcademicMeetings() {
        return this.teacherAndmainUndertakeAcademicMeetings;
    }
    
    public void setTeacherAndmainUndertakeAcademicMeetings(Set teacherAndmainUndertakeAcademicMeetings) {
        this.teacherAndmainUndertakeAcademicMeetings = teacherAndmainUndertakeAcademicMeetings;
    }

	public String getResearchLabId() {
		return researchLabId;
	}

	public void setResearchLabId(String researchLabId) {
		this.researchLabId = researchLabId;
	}
   








}