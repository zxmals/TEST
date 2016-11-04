package com.nuaa.ec.model;

public class TfstudentCompetitionGuidancePerformanceUnionTfterm {
	private TfstudentCompetitionGuidancePerformance studentCompetGuidPerf;
	private Tfterm currentTerm;
	public TfstudentCompetitionGuidancePerformanceUnionTfterm(){}
	public TfstudentCompetitionGuidancePerformanceUnionTfterm(
			TfstudentCompetitionGuidancePerformance studentCompetGuidPerf,
			Tfterm currentTerm) {
		super();
		this.studentCompetGuidPerf = studentCompetGuidPerf;
		this.currentTerm = currentTerm;
	}
	public TfstudentCompetitionGuidancePerformance getStudentCompetGuidPerf() {
		return studentCompetGuidPerf;
	}
	public void setStudentCompetGuidPerf(
			TfstudentCompetitionGuidancePerformance studentCompetGuidPerf) {
		this.studentCompetGuidPerf = studentCompetGuidPerf;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
	
}
