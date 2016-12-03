package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * JoinAcademicMeeting entity. @author MyEclipse Persistence Tools
 */

public class JoinAcademicMeeting implements java.io.Serializable {

	// Fields

	private String joinAcaMid;
	private MeetingPlace meetingPlace;
	private MeetingType meetingType;
	private String acaMeetName;
	private String spareTire;
	private String chargePersonId;
	private String chargePerson;
	private String checkout;
	private String meetingdate;
	private String researchLabId;
	private Set teacherAndjoinAcademicMeetings = new HashSet(0);

	public JoinAcademicMeeting(String joinAcaMid, MeetingPlace meetingPlace,
			MeetingType meetingType, String acaMeetName, String spareTire,
			String chargePersonId, String chargePerson, String checkout,
			String meetingdate, String researchLabId,
			Set teacherAndjoinAcademicMeetings) {
		this.joinAcaMid = joinAcaMid;
		this.meetingPlace = meetingPlace;
		this.meetingType = meetingType;
		this.acaMeetName = acaMeetName;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.chargePerson = chargePerson;
		this.checkout = checkout;
		this.meetingdate = meetingdate;
		this.researchLabId = researchLabId;
		this.teacherAndjoinAcademicMeetings = teacherAndjoinAcademicMeetings;
	}

	// Constructors

	/** default constructor */
	public JoinAcademicMeeting() {
	}

	/** minimal constructor */
	public JoinAcademicMeeting(String joinAcaMid) {
		this.joinAcaMid = joinAcaMid;
	}

	/** full constructor */
	public JoinAcademicMeeting(String joinAcaMid, MeetingPlace meetingPlace,
			MeetingType meetingType, String acaMeetName, String spareTire,
			String chargePersonId, String chargePerson, String checkout,
			String meetingdate, Set teacherAndjoinAcademicMeetings) {
		this.joinAcaMid = joinAcaMid;
		this.meetingPlace = meetingPlace;
		this.meetingType = meetingType;
		this.acaMeetName = acaMeetName;
		this.spareTire = spareTire;
		this.chargePersonId = chargePersonId;
		this.chargePerson = chargePerson;
		this.checkout = checkout;
		this.meetingdate = meetingdate;
		this.teacherAndjoinAcademicMeetings = teacherAndjoinAcademicMeetings;
	}

	// Property accessors

	public String getJoinAcaMid() {
		return this.joinAcaMid;
	}

	public void setJoinAcaMid(String joinAcaMid) {
		this.joinAcaMid = joinAcaMid;
	}

	public MeetingPlace getMeetingPlace() {
		return this.meetingPlace;
	}

	public void setMeetingPlace(MeetingPlace meetingPlace) {
		this.meetingPlace = meetingPlace;
	}

	public MeetingType getMeetingType() {
		return this.meetingType;
	}

	public void setMeetingType(MeetingType meetingType) {
		this.meetingType = meetingType;
	}

	public String getAcaMeetName() {
		return this.acaMeetName;
	}

	public void setAcaMeetName(String acaMeetName) {
		this.acaMeetName = acaMeetName;
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

	public String getChargePerson() {
		return this.chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
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

	public Set getTeacherAndjoinAcademicMeetings() {
		return this.teacherAndjoinAcademicMeetings;
	}

	public void setTeacherAndjoinAcademicMeetings(
			Set teacherAndjoinAcademicMeetings) {
		this.teacherAndjoinAcademicMeetings = teacherAndjoinAcademicMeetings;
	}

	public String getResearchLabId() {
		return researchLabId;
	}

	public void setResearchLabId(String researchLabId) {
		this.researchLabId = researchLabId;
	}

}