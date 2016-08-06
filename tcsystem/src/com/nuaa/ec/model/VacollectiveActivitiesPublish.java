package com.nuaa.ec.model;

import java.util.HashSet;
import java.util.Set;

/**
 * VacollectiveActivitiesPublish entity. @author MyEclipse Persistence Tools
 */

public class VacollectiveActivitiesPublish implements java.io.Serializable {

	// Fields

	private String actPubId;
	private VacollectiveAct vacollectiveAct;
	private String teacherId;
	private String actDate;
	private String spareTire;
	private String aspareTire;
	private Set vateacherAndCollectiveActs = new HashSet(0);

	// Constructors

	/** default constructor */
	public VacollectiveActivitiesPublish() {
	}

	/** minimal constructor */
	public VacollectiveActivitiesPublish(String actPubId,
			VacollectiveAct vacollectiveAct, String teacherId) {
		this.actPubId = actPubId;
		this.vacollectiveAct = vacollectiveAct;
		this.teacherId = teacherId;
	}

	
	
	public VacollectiveActivitiesPublish(String actPubId,
			VacollectiveAct vacollectiveAct, String actDate,String NULL) {
		super();
		this.actPubId = actPubId;
		this.vacollectiveAct = vacollectiveAct;
		this.actDate = actDate;
	}

	/** full constructor */
	public VacollectiveActivitiesPublish(String actPubId,
			VacollectiveAct vacollectiveAct, String teacherId, String actDate,
			String spareTire, String aspareTire, Set vateacherAndCollectiveActs) {
		this.actPubId = actPubId;
		this.vacollectiveAct = vacollectiveAct;
		this.teacherId = teacherId;
		this.actDate = actDate;
		this.spareTire = spareTire;
		this.aspareTire = aspareTire;
		this.vateacherAndCollectiveActs = vateacherAndCollectiveActs;
	}

	// Property accessors

	public String getActPubId() {
		return this.actPubId;
	}

	public void setActPubId(String actPubId) {
		this.actPubId = actPubId;
	}

	public VacollectiveAct getVacollectiveAct() {
		return this.vacollectiveAct;
	}

	public void setVacollectiveAct(VacollectiveAct vacollectiveAct) {
		this.vacollectiveAct = vacollectiveAct;
	}

	public String getTeacherId() {
		return this.teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getActDate() {
		return this.actDate;
	}

	public void setActDate(String actDate) {
		this.actDate = actDate;
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

	public Set getVateacherAndCollectiveActs() {
		return this.vateacherAndCollectiveActs;
	}

	public void setVateacherAndCollectiveActs(Set vateacherAndCollectiveActs) {
		this.vateacherAndCollectiveActs = vateacherAndCollectiveActs;
	}

}