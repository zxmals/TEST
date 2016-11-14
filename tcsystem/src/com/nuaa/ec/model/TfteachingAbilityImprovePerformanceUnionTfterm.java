package com.nuaa.ec.model;

public class TfteachingAbilityImprovePerformanceUnionTfterm {
	private TfteachingAbilityImprovePerformance tfteachingAbilityImprovePerformance;
	private Tfterm currentTerm;
	public TfteachingAbilityImprovePerformance getTfteachingAbilityImprovePerformance() {
		return tfteachingAbilityImprovePerformance;
	}
	public void setTfteachingAbilityImprovePerformance(
			TfteachingAbilityImprovePerformance tfteachingAbilityImprovePerformance) {
		this.tfteachingAbilityImprovePerformance = tfteachingAbilityImprovePerformance;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
	public TfteachingAbilityImprovePerformanceUnionTfterm(
			TfteachingAbilityImprovePerformance tfteachingAbilityImprovePerformance,
			Tfterm currentTerm) {
		this.tfteachingAbilityImprovePerformance = tfteachingAbilityImprovePerformance;
		this.currentTerm = currentTerm;
	}
}
