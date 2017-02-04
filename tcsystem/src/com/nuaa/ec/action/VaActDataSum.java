package com.nuaa.ec.action;

import com.nuaa.ec.va.exportdata.TeacherJoinedData;
import com.nuaa.ec.va.exportdata.UnjoinedActData;

public class VaActDataSum {
	private String departmentId;
	private String departmentName;
	private String teacherId;
	private String teacherName;
	private TeacherJoinedData teacherJoinedData;
	private UnjoinedActData unjoinedActData;
	private float sum;
	private float average;
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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
	public TeacherJoinedData getTeacherJoinedData() {
		return teacherJoinedData;
	}
	public void setTeacherJoinedData(TeacherJoinedData teacherJoinedData) {
		this.teacherJoinedData = teacherJoinedData;
	}
	public UnjoinedActData getUnjoinedActData() {
		return unjoinedActData;
	}
	public void setUnjoinedActData(UnjoinedActData unjoinedActData) {
		this.unjoinedActData = unjoinedActData;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public float getAverage() {
		return average;
	}
	public void setAverage(float average) {
		this.average = average;
	}
	
}
