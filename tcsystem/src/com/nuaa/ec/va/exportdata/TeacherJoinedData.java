package com.nuaa.ec.va.exportdata;

public class TeacherJoinedData {
	private float sum;
	private float average;

	public TeacherJoinedData() {

	};

	public TeacherJoinedData(float sum, float average) {
		super();
		this.sum = sum;
		this.average = average;
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
