package com.nuaa.ec.model;

public class TfteachingCompetitionPerformanceUnionTfterm {
	private TfteachingCompetitionPerformance tfTeachingCompetitionPerformance;
	private Tfterm currentTerm;
	public TfteachingCompetitionPerformanceUnionTfterm(
			TfteachingCompetitionPerformance tfTeachingCompetitionPerformance,
			Tfterm currentTerm) {
		this.tfTeachingCompetitionPerformance = tfTeachingCompetitionPerformance;
		this.currentTerm = currentTerm;
	}
	public TfteachingCompetitionPerformance getTfTeachingCompetitionPerformance() {
		return tfTeachingCompetitionPerformance;
	}
	public void setTfTeachingCompetitionPerformance(
			TfteachingCompetitionPerformance tfTeachingCompetitionPerformance) {
		this.tfTeachingCompetitionPerformance = tfTeachingCompetitionPerformance;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
}
