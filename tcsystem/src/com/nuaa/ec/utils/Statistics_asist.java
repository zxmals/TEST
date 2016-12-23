package com.nuaa.ec.utils;
//com.nuaa.ec.utils.Statistics_asist
public class Statistics_asist {
	private double sum;
	private double avg;
	private String spareTire;

	public Statistics_asist(double sum, double avg, String spareTire) {
		this.sum = sum;
		this.avg = avg;
		this.spareTire = spareTire;
	}

	public Statistics_asist(double sum, double avg) {
		super();
		this.sum = sum;
		this.avg = avg;
	}

	public Statistics_asist() {
		// TODO Auto-generated constructor stub
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) {
		this.sum = sum;
	}

	public double getAvg() {
		return avg;
	}

	public void setAvg(double avg) {
		this.avg = avg;
	}

	public String getSpareTire() {
		return spareTire;
	}

	public void setSpareTire(String spareTire) {
		this.spareTire = spareTire;
	}

}
