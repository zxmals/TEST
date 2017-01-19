package com.nuaa.ec.summaryDataModel;

public class ScientificResearchModuleData {
	private String researchLabId;
	private String researchLabName;
	private AcademicWorkData academicWorkData;
	private InviteExpertSpeechData inviteExpertSpeechData;
	private JoinAcademicMeetingData joinAcademicMeetingData;
	private PeriodicalData periodicalData;
	private ScientificResearchProData scientificResearchProData;
	private ScientificResearchRewardData scientificRewardData;
	private SelectTalentProData talentProData;
	private UndertakeAcademicMeetingData undertakeAcademicMeetingData;
	private float sum;
	private float avg;
	public AcademicWorkData getAcademicWorkData() {
		return academicWorkData;
	}
	public void setAcademicWorkData(AcademicWorkData academicWorkData) {
		this.academicWorkData = academicWorkData;
	}
	public InviteExpertSpeechData getInviteExpertSpeechData() {
		return inviteExpertSpeechData;
	}
	public void setInviteExpertSpeechData(
			InviteExpertSpeechData inviteExpertSpeechData) {
		this.inviteExpertSpeechData = inviteExpertSpeechData;
	}
	public PeriodicalData getPeriodicalData() {
		return periodicalData;
	}
	public void setPeriodicalData(PeriodicalData periodicalData) {
		this.periodicalData = periodicalData;
	}
	public ScientificResearchProData getScientificResearchProData() {
		return scientificResearchProData;
	}
	public void setScientificResearchProData(
			ScientificResearchProData scientificResearchProData) {
		this.scientificResearchProData = scientificResearchProData;
	}
	public ScientificResearchRewardData getScientificRewardData() {
		return scientificRewardData;
	}
	public void setScientificRewardData(ScientificResearchRewardData scientificRewardData) {
		this.scientificRewardData = scientificRewardData;
	}
	public SelectTalentProData getTalentProData() {
		return talentProData;
	}
	public void setTalentProData(SelectTalentProData talentProData) {
		this.talentProData = talentProData;
	}
	public UndertakeAcademicMeetingData getUndertakeAcademicMeetingData() {
		return undertakeAcademicMeetingData;
	}
	public void setUndertakeAcademicMeetingData(
			UndertakeAcademicMeetingData undertakeAcademicMeetingData) {
		this.undertakeAcademicMeetingData = undertakeAcademicMeetingData;
	}
	public float getSum() {
		return sum;
	}
	public float getAvg(){
		return avg;
	}
	public JoinAcademicMeetingData getJoinAcademicMeetingData() {
		return joinAcademicMeetingData;
	}
	public void setJoinAcademicMeetingData(
			JoinAcademicMeetingData joinAcademicMeetingData) {
		this.joinAcademicMeetingData = joinAcademicMeetingData;
	}
	public String getResearchLabId() {
		return researchLabId;
	}
	public void setResearchLabId(String researchLabId) {
		this.researchLabId = researchLabId;
	}
	public String getResearchLabName() {
		return researchLabName;
	}
	public void setResearchLabName(String researchLabName) {
		this.researchLabName = researchLabName;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
}
