package com.nuaa.ec.model;

public class PeriodicalPaperInfoUnionModel {
	private PeriodicalPapers periodicalPapers;
	private TeacherAndperiodical TAPeriodical;

	public PeriodicalPaperInfoUnionModel(TeacherAndperiodical tAPeriodical,
			PeriodicalPapers periodicalPapers) {
		this.periodicalPapers = periodicalPapers;
		TAPeriodical = tAPeriodical;
	}

	public PeriodicalPapers getPeriodicalPapers() {
		return periodicalPapers;
	}

	public void setPeriodicalPapers(PeriodicalPapers periodicalPapers) {
		this.periodicalPapers = periodicalPapers;
	}

	public TeacherAndperiodical getTAPeriodical() {
		return TAPeriodical;
	}

	public void setTAPeriodical(TeacherAndperiodical tAPeriodical) {
		TAPeriodical = tAPeriodical;
	}
}
