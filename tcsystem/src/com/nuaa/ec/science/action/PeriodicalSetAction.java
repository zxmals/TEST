package com.nuaa.ec.science.action;

import com.nuaa.ec.dao.PeriodicalDAO;
import com.nuaa.ec.dao.PeriodicalPapersScoreDAO;
import com.nuaa.ec.dao.PeriodicalTypeDAO;
import com.nuaa.ec.model.Periodical;
import com.nuaa.ec.model.PeriodicalPapersScore;
import com.nuaa.ec.model.PeriodicalType;

public class PeriodicalSetAction {
	private PeriodicalType ptype;
	private PeriodicalPapersScore ppaperscore;
	private Periodical periodi;

	private PeriodicalDAO periodicaldao = new PeriodicalDAO();
	private PeriodicalTypeDAO ptypedao = new PeriodicalTypeDAO();
	private PeriodicalPapersScoreDAO ppaperscoredao = new PeriodicalPapersScoreDAO();
	
	

	public PeriodicalType getPtype() {
		return ptype;
	}

	public void setPtype(PeriodicalType ptype) {
		this.ptype = ptype;
	}

	public PeriodicalPapersScore getPpaperscore() {
		return ppaperscore;
	}

	public void setPpaperscore(PeriodicalPapersScore ppaperscore) {
		this.ppaperscore = ppaperscore;
	}

	public Periodical getPeriodi() {
		return periodi;
	}

	public void setPeriodi(Periodical periodi) {
		this.periodi = periodi;
	}

}
