package com.nuaa.ec.model;

/**
 * VateacherAndCollectiveActId entity. @author MyEclipse Persistence Tools
 */

public class VateacherAndCollectiveActId implements java.io.Serializable {

	// Fields

	private VacollectiveActivitiesPublish vacollectiveActivitiesPublish;
	private Teacher teacher;

	// Constructors

	/** default constructor 
	 * 
	 * @param vacollectiveActivitiesPublish2 */
	public VateacherAndCollectiveActId(VacollectiveActivitiesPublish vacollectiveActivitiesPublish2) {
		this.vacollectiveActivitiesPublish = vacollectiveActivitiesPublish2;
	}

	public VateacherAndCollectiveActId(){
		
	}
	
	/** full constructor */
	public VateacherAndCollectiveActId(
			VacollectiveActivitiesPublish vacollectiveActivitiesPublish,
			Teacher teacher) {
		this.vacollectiveActivitiesPublish = vacollectiveActivitiesPublish;
		this.teacher = teacher;
	}

	// Property accessors

	public VacollectiveActivitiesPublish getVacollectiveActivitiesPublish() {
		return this.vacollectiveActivitiesPublish;
	}

	public void setVacollectiveActivitiesPublish(
			VacollectiveActivitiesPublish vacollectiveActivitiesPublish) {
		this.vacollectiveActivitiesPublish = vacollectiveActivitiesPublish;
	}

	public Teacher getTeacher() {
		return this.teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof VateacherAndCollectiveActId))
			return false;
		VateacherAndCollectiveActId castOther = (VateacherAndCollectiveActId) other;

		return ((this.getVacollectiveActivitiesPublish() == castOther
				.getVacollectiveActivitiesPublish()) || (this
				.getVacollectiveActivitiesPublish() != null
				&& castOther.getVacollectiveActivitiesPublish() != null && this
				.getVacollectiveActivitiesPublish().equals(
						castOther.getVacollectiveActivitiesPublish())))
				&& ((this.getTeacher() == castOther.getTeacher()) || (this
						.getTeacher() != null && castOther.getTeacher() != null && this
						.getTeacher().equals(castOther.getTeacher())));
	}

	public int hashCode() {
		int result = 17;

		result = 37
				* result
				+ (getVacollectiveActivitiesPublish() == null ? 0 : this
						.getVacollectiveActivitiesPublish().hashCode());
		result = 37 * result
				+ (getTeacher() == null ? 0 : this.getTeacher().hashCode());
		return result;
	}

}