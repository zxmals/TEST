package com.nuaa.ec.model;

public class TfsummerCourseInternationalConstructionPerformanceUnionTfterm {
	private TfsummerCourseInternationalConstructionPerformance summerCourseInterConsPerf;
	private Tfterm currentTerm;
	public TfsummerCourseInternationalConstructionPerformance getSummerCourseInterConsPerf() {
		return summerCourseInterConsPerf;
	}
	public void setSummerCourseInterConsPerf(
			TfsummerCourseInternationalConstructionPerformance summerCourseInterConsPerf) {
		this.summerCourseInterConsPerf = summerCourseInterConsPerf;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
	public TfsummerCourseInternationalConstructionPerformanceUnionTfterm(
			TfsummerCourseInternationalConstructionPerformance summerCourseInterConsPerf,
			Tfterm currentTerm) {
		this.summerCourseInterConsPerf = summerCourseInterConsPerf;
		this.currentTerm = currentTerm;
	}
}
