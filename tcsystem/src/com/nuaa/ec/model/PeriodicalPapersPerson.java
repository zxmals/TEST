package com.nuaa.ec.model;

public class PeriodicalPapersPerson {

	private String ppid;
	private String thesisTitle;
	private String chargePersonId;
	private String firstAuthor;
	private String secondAuthor;
	private float score;
	private String checkOut;

	public PeriodicalPapersPerson() {

	}

	public PeriodicalPapersPerson(String ppid, String thesisTitle,
			String chargePersonId, String firstAuthor, String secondAuthor,
			float score, String checkOut) {
		this.ppid = ppid;
		this.thesisTitle = thesisTitle;
		this.chargePersonId = chargePersonId;
		this.firstAuthor = firstAuthor;
		this.secondAuthor = secondAuthor;
		this.score = score;
		this.checkOut = checkOut.trim();
	}

	public String getPpid() {
		return ppid;
	}

	public void setPpid(String ppid) {
		this.ppid = ppid;
	}

	public String getThesisTitle() {
		return thesisTitle;
	}

	public void setThesisTitle(String thesisTitle) {
		this.thesisTitle = thesisTitle;
	}

	public String getChargePersonId() {
		return chargePersonId;
	}

	public void setChargePersonId(String chargePersonId) {
		this.chargePersonId = chargePersonId;
	}

	public String getFirstAuthor() {
		return firstAuthor;
	}

	public void setFirstAuthor(String firstAuthor) {
		this.firstAuthor = firstAuthor;
	}

	public String getSecondAuthor() {
		return secondAuthor;
	}

	public void setSecondAuthor(String secondAuthor) {
		this.secondAuthor = secondAuthor;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

}
