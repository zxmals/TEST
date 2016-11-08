package com.nuaa.ec.model;

public class TfclassTeachPefromanceUnionTfterm {
	private TfclassTeachPefromance classTeachPerformance;
	private Tfterm currentTerm;
	public TfclassTeachPefromance getClassTeachPerformance() {
		return classTeachPerformance;
	}
	public void setClassTeachPerformance(
			TfclassTeachPefromance classTeachPerformance) {
		this.classTeachPerformance = classTeachPerformance;
	}
	public Tfterm getCurrentTerm() {
		return currentTerm;
	}
	public void setCurrentTerm(Tfterm currentTerm) {
		this.currentTerm = currentTerm;
	}
	public TfclassTeachPefromanceUnionTfterm(
			TfclassTeachPefromance classTeachPerformance, Tfterm currentTerm) {
		this.classTeachPerformance = classTeachPerformance;
		this.currentTerm = currentTerm;
	}
}
