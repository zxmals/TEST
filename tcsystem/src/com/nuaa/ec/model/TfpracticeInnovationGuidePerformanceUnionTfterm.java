package com.nuaa.ec.model;

public class TfpracticeInnovationGuidePerformanceUnionTfterm {
	private TfpracticeInnovationGuidePerformance pracInnoGuidPerf;
	private Tfterm currentTerm;
	public TfpracticeInnovationGuidePerformance getPracInnoGuidPerf() {
		return pracInnoGuidPerf;
	}
	public void setPracInnoGuidPerf(
			TfpracticeInnovationGuidePerformance pracInnoGuidPerf) {
		this.pracInnoGuidPerf = pracInnoGuidPerf;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
	public TfpracticeInnovationGuidePerformanceUnionTfterm(
			TfpracticeInnovationGuidePerformance pracInnoGuidPerf,
			Tfterm currentTerm) {
		this.pracInnoGuidPerf = pracInnoGuidPerf;
		this.currentTerm = currentTerm;
	}
	
}
