package com.nuaa.ec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.nuaa.ec.dao.TeacherAndacademicWorkDAO;
import com.nuaa.ec.dao.TeacherAndinvitedExpertsSpeechDAO;
import com.nuaa.ec.dao.TeacherAndjoinAcademicMeetingDAO;
import com.nuaa.ec.dao.TeacherAndmainUndertakeAcademicMeetingDAO;
import com.nuaa.ec.dao.TeacherAndperiodicalDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchProjectDAO;
import com.nuaa.ec.dao.TeacherAndscientificResearchRewardDAO;
import com.nuaa.ec.dao.TeacherAndselectedTalentProjectDAO;
import com.nuaa.ec.summaryDataModel.AcademicWorkData;
import com.nuaa.ec.summaryDataModel.InviteExpertSpeechData;
import com.nuaa.ec.summaryDataModel.JoinAcademicMeetingData;
import com.nuaa.ec.summaryDataModel.PeriodicalData;
import com.nuaa.ec.summaryDataModel.ScientificResearchModuleData;
import com.nuaa.ec.summaryDataModel.ScientificResearchProData;
import com.nuaa.ec.summaryDataModel.ScientificResearchRewardData;
import com.nuaa.ec.summaryDataModel.SelectTalentProData;
import com.nuaa.ec.summaryDataModel.UndertakeAcademicMeetingData;
import com.nuaa.ec.utils.NumberFormatUtil;
import com.nuaa.ec.utils.StoreData;

public class ScientificResearchSummaryDataDao {
	private static TeacherAndscientificResearchProjectDAO tAScienReschProDao = new TeacherAndscientificResearchProjectDAO();
	private static TeacherAndscientificResearchRewardDAO tAScientificRewardDao = new TeacherAndscientificResearchRewardDAO();
	private static TeacherAndacademicWorkDAO tAAcademicWorkDao = new TeacherAndacademicWorkDAO();
	private static TeacherAndjoinAcademicMeetingDAO tAJoinAcademicMeetingDao = new TeacherAndjoinAcademicMeetingDAO();
	private static TeacherAndselectedTalentProjectDAO tASelectTalentProDao = new TeacherAndselectedTalentProjectDAO();
	private static TeacherAndinvitedExpertsSpeechDAO tAInviteExpertSpeechDao = new TeacherAndinvitedExpertsSpeechDAO();
	private static TeacherAndperiodicalDAO tAPeriodicalDao = new TeacherAndperiodicalDAO();
	private static TeacherAndmainUndertakeAcademicMeetingDAO tAMainUndrtkAcademicMeetingDao = new TeacherAndmainUndertakeAcademicMeetingDAO();
	private static Map<String, String> researchLabMap = StoreData
			.getResearchLabMap();
	/**
	 * function：按照教师进行科研模块的数据汇总
	 */
	
	public static List<ScientificResearchModuleData> summaryScienReschModuleDataByPerson() throws Exception{
		
		return null;
	}
	
	/**
	 * 获得每一个模块的所有研究所的汇总情况
	 * @param scienResearchModlDataList
	 * @throws Exception
	 */
	public static void getEachModuleAllResearchLab(
			List<ScientificResearchModuleData> scienResearchModlDataList)
			throws Exception {
		ScientificResearchModuleData scienReschModuleData = new ScientificResearchModuleData();
		float scienReschProColumn = 0, scienReschRewardColumn = 0, 
				academicWorkColumn = 0, joinAcademicMeetingColumn = 0, 
				selectTalentProColumn = 0, inviteExpertSpeechColumn = 0, 
				periodicalColumn = 0, mainUndertakeAcademicMeetingColumn = 0;
		for (ScientificResearchModuleData srmd : scienResearchModlDataList) {
			scienReschProColumn+=srmd.getScientificResearchProData().getSum();
			scienReschRewardColumn+=srmd.getScientificRewardData().getSum();
			academicWorkColumn+=srmd.getAcademicWorkData().getSum();
			joinAcademicMeetingColumn+=srmd.getJoinAcademicMeetingData().getSum();
			selectTalentProColumn+=srmd.getTalentProData().getSum();
			inviteExpertSpeechColumn+=srmd.getInviteExpertSpeechData().getSum();
			periodicalColumn+=srmd.getPeriodicalData().getSum();
			mainUndertakeAcademicMeetingColumn+=srmd.getUndertakeAcademicMeetingData().getSum();
		}
		ScientificResearchProData scienReschProData = new ScientificResearchProData();
		ScientificResearchRewardData scienReschRewardData = new ScientificResearchRewardData();
		AcademicWorkData academicWorkData = new AcademicWorkData();
		JoinAcademicMeetingData joinAcademicMeetingData = new JoinAcademicMeetingData();
		SelectTalentProData selectTalentProData = new SelectTalentProData();
		InviteExpertSpeechData inviteExpertSpeechData = new InviteExpertSpeechData();
		PeriodicalData periodicalData = new PeriodicalData();
		UndertakeAcademicMeetingData undertakeAcademicMeetingData = new UndertakeAcademicMeetingData();
		int size = scienResearchModlDataList.size();
		//科研项目
		scienReschProData.setSum(scienReschProColumn);
		scienReschProData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(scienReschProColumn/size));
		//科研奖励
		scienReschRewardData.setSum(scienReschRewardColumn);
		scienReschRewardData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(scienReschRewardColumn/size));
		//学术著作
		academicWorkData.setSum(academicWorkColumn);
		academicWorkData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(academicWorkColumn/size));
		//参加学术会议
		joinAcademicMeetingData.setSum(joinAcademicMeetingColumn);
		joinAcademicMeetingData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(joinAcademicMeetingColumn/size));
		//入选人才工程
		selectTalentProData.setSum(selectTalentProColumn);
		selectTalentProData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(selectTalentProColumn/size));
		//邀请专家讲学
		inviteExpertSpeechData.setSum(inviteExpertSpeechColumn);
		inviteExpertSpeechData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(inviteExpertSpeechColumn/size));
		//期刊论文
		periodicalData.setSum(periodicalColumn);
		periodicalData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(periodicalColumn/size));
		//主承担学术会议
		undertakeAcademicMeetingData.setSum(mainUndertakeAcademicMeetingColumn);
		undertakeAcademicMeetingData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(mainUndertakeAcademicMeetingColumn/size));
		
		scienReschModuleData.setResearchLabId("allResearchLab");
		scienReschModuleData.setResearchLabName("合计");
		scienReschModuleData.setAcademicWorkData(academicWorkData);
		scienReschModuleData.setInviteExpertSpeechData(inviteExpertSpeechData);
		scienReschModuleData.setJoinAcademicMeetingData(joinAcademicMeetingData);
		scienReschModuleData.setPeriodicalData(periodicalData);
		scienReschModuleData.setScientificResearchProData(scienReschProData);
		scienReschModuleData.setScientificRewardData(scienReschRewardData);
		scienReschModuleData.setTalentProData(selectTalentProData);
		scienReschModuleData.setUndertakeAcademicMeetingData(undertakeAcademicMeetingData);
		scienReschModuleData.setSum(academicWorkData.getSum()+inviteExpertSpeechData.getSum()
				+joinAcademicMeetingData.getSum()+periodicalData.getSum()
				+scienReschProData.getSum()+scienReschRewardData.getSum()
				+selectTalentProData.getSum()+undertakeAcademicMeetingData.getSum());
		scienReschModuleData.setAvg(NumberFormatUtil.getNumberAfterTransferPrecision(scienReschModuleData.getSum()/8));
		scienResearchModlDataList.add(scienReschModuleData);
	}

	/**
	 * 以研究所为单位进行汇总
	 * 
	 * @param researchLabIds
	 * @param foredate
	 * @param afterdate
	 * @return
	 * @throws Exception
	 */
	public static List<ScientificResearchModuleData> packageScienReschSummaryData(
			List<String> researchLabIds, String foredate, String afterdate)
			throws Exception {
		/*
		 * check researchLabId if researchLabId equals allReseachLab,using
		 * reserchLabIds to query; else using researchLabId from font view;
		 */
		List<ScientificResearchModuleData> scienReschModuleDataList = new ArrayList<ScientificResearchModuleData>(
				4);
		ScientificResearchModuleData scienReschModuleData = null;
		for (String researchLabId : researchLabIds) {
			scienReschModuleData = new ScientificResearchModuleData();
			scienReschModuleData.setResearchLabId(researchLabId);
			scienReschModuleData.setResearchLabName(researchLabMap
					.get(researchLabId));
			// ScientificResearchProject
			ScientificResearchProData scienReschProData = tAScienReschProDao
					.getSummaryDataByResearchLab(researchLabId, foredate,
							afterdate);
			// ScientificResearchReward
			ScientificResearchRewardData scienReschRewardData = tAScientificRewardDao
					.getSummaryDataByResearchLab(researchLabId, foredate,
							afterdate);
			// AcademicWork
			AcademicWorkData academicWorkData = tAAcademicWorkDao
					.getSummaryDataByResearchLab(researchLabId, foredate,
							afterdate);
			// JoinAcademicMeeting
			JoinAcademicMeetingData joinAcademicMeetingData = tAJoinAcademicMeetingDao
					.getSummaryDataByResearchLab(researchLabId, foredate,
							afterdate);
			// SelectTalentProject
			SelectTalentProData selectTalentProData = tASelectTalentProDao
					.getSummaryDataByResearchLab(researchLabId, foredate,
							afterdate);

			// InviteExpertSpeech
			InviteExpertSpeechData inviteExpertSpeechData = tAInviteExpertSpeechDao
					.getSummaryDataByResearchLab(researchLabId, foredate,
							afterdate);

			// Periocidal
			PeriodicalData periodicalData = tAPeriodicalDao
					.getSummaryDataByResearchLab(researchLabId, foredate,
							afterdate);

			// mainUndertakeAcademicMeeting
			UndertakeAcademicMeetingData undertakeAcademicMeetingData = tAMainUndrtkAcademicMeetingDao
					.getSummaryDataByResearchLab(researchLabId, foredate,
							afterdate);

			scienReschModuleData
					.setScientificResearchProData(scienReschProData);
			scienReschModuleData.setScientificRewardData(scienReschRewardData);
			scienReschModuleData
					.setJoinAcademicMeetingData(joinAcademicMeetingData);
			scienReschModuleData.setAcademicWorkData(academicWorkData);
			scienReschModuleData
					.setInviteExpertSpeechData(inviteExpertSpeechData);
			scienReschModuleData.setPeriodicalData(periodicalData);
			scienReschModuleData.setTalentProData(selectTalentProData);
			scienReschModuleData
					.setUndertakeAcademicMeetingData(undertakeAcademicMeetingData);
			scienReschModuleData.setSum(joinAcademicMeetingData.getSum()
					+ academicWorkData.getSum()
					+ inviteExpertSpeechData.getSum() + periodicalData.getSum()
					+ scienReschProData.getSum()
					+ scienReschRewardData.getSum()
					+ selectTalentProData.getSum()
					+ undertakeAcademicMeetingData.getSum());

			scienReschModuleData.setAvg(scienReschModuleData.getSum() / 8);

			scienReschModuleDataList.add(scienReschModuleData);
		}
		getEachModuleAllResearchLab(scienReschModuleDataList);
		return scienReschModuleDataList;
	}
}
