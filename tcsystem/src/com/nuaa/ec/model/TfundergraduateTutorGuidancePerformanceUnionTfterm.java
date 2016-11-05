package com.nuaa.ec.model;

public class TfundergraduateTutorGuidancePerformanceUnionTfterm {
	private TfundergraduateTutorGuidancePerformance undergraduateTutorGuidancePerformance;
	private Tfterm currentTerm;
	public TfundergraduateTutorGuidancePerformance getUndergraduateTutorGuidancePerformance() {
		return undergraduateTutorGuidancePerformance;
	}
	public void setUndergraduateTutorGuidancePerformance(
			TfundergraduateTutorGuidancePerformance undergraduateTutorGuidancePerformance) {
		this.undergraduateTutorGuidancePerformance = undergraduateTutorGuidancePerformance;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
	public TfundergraduateTutorGuidancePerformanceUnionTfterm(
			TfundergraduateTutorGuidancePerformance undergraduateTutorGuidancePerformance,
			Tfterm currentTerm) {
		this.undergraduateTutorGuidancePerformance = undergraduateTutorGuidancePerformance;
		this.currentTerm = currentTerm;
	}
}
