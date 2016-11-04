package com.nuaa.ec.model;
//com.nuaa.ec.model.TeacherMember
public class TeacherMember {
	private String teacherId;
	private String teacherName;
	private String spare;

	public TeacherMember() {
	}

	public TeacherMember(String teacherId, String teacherName, String spare) {
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.spare = spare;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getSpare() {
		return spare;
	}

	public void setSpare(String spare) {
		this.spare = spare;
	}

}
