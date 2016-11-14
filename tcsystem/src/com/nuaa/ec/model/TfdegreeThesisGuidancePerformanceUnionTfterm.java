package com.nuaa.ec.model;

public class TfdegreeThesisGuidancePerformanceUnionTfterm {
	private TfdegreeThesisGuidancePerformance degreeThesisGuidancePerformance;
	private Tfterm currentTerm;
	public TfdegreeThesisGuidancePerformanceUnionTfterm(
			TfdegreeThesisGuidancePerformance degreeThesisGuidancePerformance,
			Tfterm currentTerm) {
		this.degreeThesisGuidancePerformance = degreeThesisGuidancePerformance;
		this.currentTerm = currentTerm;
	}
	public TfdegreeThesisGuidancePerformance getDegreeThesisGuidancePerformance() {
		return degreeThesisGuidancePerformance;
	}
	public void setDegreeThesisGuidancePerformance(
			TfdegreeThesisGuidancePerformance degreeThesisGuidancePerformance) {
		this.degreeThesisGuidancePerformance = degreeThesisGuidancePerformance;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
}
