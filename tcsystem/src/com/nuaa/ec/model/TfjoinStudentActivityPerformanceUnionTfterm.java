package com.nuaa.ec.model;

public class TfjoinStudentActivityPerformanceUnionTfterm {
	private TfjoinStudentActivityPerformance joinStudentActivityPerformance;
	private Tfterm currentTerm;

	public TfjoinStudentActivityPerformance getJoinStudentActivityPerformance() {
		return joinStudentActivityPerformance;
	}

	public void setJoinStudentActivityPerformance(
			TfjoinStudentActivityPerformance joinStudentActivityPerformance) {
		this.joinStudentActivityPerformance = joinStudentActivityPerformance;
	}

	public Tfterm getCurrentTerm() {
		return currentTerm;
	}

	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}

	public TfjoinStudentActivityPerformanceUnionTfterm() {
	}

	public TfjoinStudentActivityPerformanceUnionTfterm(
			TfjoinStudentActivityPerformance joinStudentActivityPerformance,
			Tfterm currentTerm) {
		super();
		this.joinStudentActivityPerformance = joinStudentActivityPerformance;
		this.currentTerm = currentTerm;
	}
}
