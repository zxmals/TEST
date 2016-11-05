package com.nuaa.ec.model;

public class TfoffCampusPracticeGuidancePerformanceUnionTfterm {
	private TfoffCampusPracticeGuidancePerformance offCampusPracticeGuidancePerformance;
	private Tfterm currentTerm;
	public TfoffCampusPracticeGuidancePerformance getOffCampusPracticeGuidancePerformance() {
		return offCampusPracticeGuidancePerformance;
	}
	public void setOffCampusPracticeGuidancePerformance(
			TfoffCampusPracticeGuidancePerformance offCampusPracticeGuidancePerformance) {
		this.offCampusPracticeGuidancePerformance = offCampusPracticeGuidancePerformance;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
	public TfoffCampusPracticeGuidancePerformanceUnionTfterm(
			TfoffCampusPracticeGuidancePerformance offCampusPracticeGuidancePerformance,
			Tfterm currentTerm) {
		this.offCampusPracticeGuidancePerformance = offCampusPracticeGuidancePerformance;
		this.currentTerm = currentTerm;
	}
	
}
