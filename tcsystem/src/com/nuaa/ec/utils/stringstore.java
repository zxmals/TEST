package com.nuaa.ec.utils;

public class stringstore {
	/**
	 * 教学绩效数据导出Excel字段名存储
	 */
	public static final String[] classTeaching={"课堂绩效编号","总课时","综合教学评估结果","当前教师工号","当前教师姓名","绩效成绩","学期"};
	public static final String[] degreeThesisGuidance={"学位论文编号","学位论文标题","获奖级别","当前教师工号","当前教师姓名","绩效成绩","学期"};
	public static final String[] teachingCompetition={"教学竞赛编号","竞赛名称","竞赛获奖等级","当前教师工号","当前教师姓名","教师绩效","学期"};
	public static final String[] teachingAbilityImprove={"能力提升项目编号","能力提升项目名称","参与总时间/h","提升等级","当前教师工号","当前教师姓名","教师绩效","学期"};
	public static final String[] practiceInnovationGuidance={"创新指导项目编号","项目名称","结题项目等级","成绩评估","当前教师工号","当前教师姓名","教师绩效","学期"};
	public static final String[] studentCompetitionGuidance={"指导竞赛编号","竞赛名称","竞赛等级","获奖级别","当前教师工号","当前教师姓名","教师绩效","学期"};
	public static final String[] joinStudentActivity={"参与活动编号","活动名称","参与时长/h","参与总时长","当前教师工号","当前教师姓名","教师绩效","学期"};
	public static final String[] undergraduateTutorGuidance={"指导编号","学生数量","指导年数","当前教师工号","当前教师姓名","教师绩效","学期"};
	public static final String[] offCampusPracticeGuidance={"指导项目编号","指导项目名称","项目类型","单位数量","数量30'/个","实习时长（2'/2h）","当前教师工号","当前教师姓名","教师绩效","学期"};
	///////////////
	public static final String[] famousTeacherTeam={"教学名师与教学团队编号","获奖级别","项目总分","本人承担任务","负责人工号","负责人姓名","当前教师编号","当前教师姓名","教师绩效","学期"};
	public static final String[] teachingResearch={"研究项目编号 ","项目名称","当年项目到款等级","项目评估等级","项目总分","负责人工号","负责人姓名","当前教师工号","当前教师姓名","教师绩效","学期"};
	public static final String[] teachingPaper={"教学论文编号","论文题目","论文检索情况","本人承担任务","是否其他作者参与","教师担任作者","项目总分","负责人工号","负责人姓名","当前教师工号","当前教师姓名","教师绩效","学期"};
	public static final String[] teachingAchievement={"教学成果奖编号","教学成果奖名称","是否他人合作","获奖级别","项目总分","负责人工号","负责人姓名","当前教师工号","当前教师姓名","本人承担任务","教师绩效","学期"};
	public static final String[] textbookConstruction={"教材建设编号","教材名称","是否合作","教材等级","项目总分","负责人工号","负责人姓名","当前教师工号","当前教师姓名","本人承担任务","教师绩效","学期"};
	public static final String[] fineCourseConstruction={"课程编号","课程名称","课程等级","是否合作","项目总分","负责人工号","负责人姓名","当前教师工号","当前教师姓名","本人承担任务","教师绩效","学期"};
	public static final String[] professionalProjectDeclare={"项目编号","项目名称","项目等级","项目总分","负责人工号","负责人姓名","当前教师工号","当前教师姓名","本人承担任务","教师绩效","学期"};
	public static final String[] enterpriseWorkstation={"项目编号","项目名称","建设水平","项目总分","数量单位","负责人工号","负责人姓名","当前教师工号","当前教师姓名","本人承担任务","教师绩效","学期"};
	public static final String[] summerInternationalCourse={"项目编号","项目名称","项目等级","数量单位","当前教师工号","当前教师姓名","分数","学期"};
	
	//FRO SCIENTIFICRESEARCH
	/* periodical_paper parameter*/
	public static final String[] peroidicalPaper = {"期刊论文编号","第一作者","第二作者","期刊论文标题","期刊名称","年份","卷","期","相关描述","当前教师Id","当前教师姓名","当前教师绩效"};
	public final String peroidicalPaperSQLh = "select PeriodicalPapers.PPId,PeriodicalPapers.FirstAuthor ,PeriodicalPapers.SecondAuthor,PeriodicalPapers.ThesisTitle ,	Periodical.PeriodicalName,PeriodicalPapers.[Year]  ,PeriodicalPapers.[File],PeriodicalPapers.Phase,PeriodicalPapers.Describe  ,Teacher.TeacherID,Teacher.TeacherName,TeacherANDPeriodical.FinalScore	  from PeriodicalPapers,Periodical,TeacherANDPeriodical,Teacher   where Teacher.ResearchLabID = '";
	public final String peroidicalPaperSQLt = "'  and TeacherANDPeriodical.PeriodicalID = Periodical.PeriodicalID   and Teacher.TeacherID = TeacherANDPeriodical.TeacherID 	  and PeriodicalPapers.PPId = TeacherANDPeriodical.PPId	  and Teacher.SpareTire = '1'	   and TeacherANDPeriodical.SpareTire = '1'   and TeacherANDPeriodical.CheckOut = '0' ";
	/*AcademicWork parameter*/
	public final static String[] academicwork  = {"学术著作编号","第一作者","学术著作名称","出版日期","出版社名称","ISBN","字数","负责人ID","负责人姓名","是否其他作者参与","当前教师编号","当前教师姓名","本人承担任务","	当前教师绩效"};
	public final String academicworkSQLh = "select PublishClub.PublishClubName,AcademicWork.AcaworkID,AcademicWork.FirstAuthor,AcademicWork.WorkName,AcademicWork.PublishDate,AcademicWork.ISBN,WordsNumber.wordNumber,AcademicWork.OtherAuthorJoin,TeacherANDAcademicWork.TeacherID,Teacher.TeacherName,SelfUndertakeTask.UndertakeTaskName,TeacherANDAcademicWork.FinalScore     from WordsNumber,TeacherANDAcademicWork,AcademicWork,Teacher,SelfUndertakeTask,PublishClub,AcademicWorkScore   where Teacher.ResearchLabID = '";
	public final String academicworkSQLt = "'and TeacherANDAcademicWork.TeacherID = Teacher.TeacherID   and AcademicWork.PublishClubID = PublishClub.PublishClubID     and TeacherANDAcademicWork .UndertakeTaskID = SelfUndertakeTask.UndertakeTaskID     and  TeacherANDAcademicWork.AcaworkID = AcademicWork.AcaworkID      and TeacherANDAcademicWork.CheckOut = '0'      and TeacherANDAcademicWork.SpareTire = '1'    and WordsNumber.wordID = AcademicWork.wordID  and Teacher.SpareTire = '1'   and TeacherANDAcademicWork.AcaWorkScoreID = AcademicWorkScore.AcaWorkScoreID";
	/* JoinAcademicWork Parameter */
	public final static String[] joinacademicw = {"学术会议编号","会议类型","会议地点","会议时间","学术会议名称","负责人ID","负责人姓名","会议论文编号","作者","作者身份","论文标题","检索情况","当前教师编号","当前教师姓名","当前教师绩效"};
	public final String joinacademicwSQLh = "select JoinAcademicMeeting.JoinAcaMID,MeetingType.MeetingTypeName,MeetingPlace.MeetingPlace,JoinAcademicMeeting.AcaMeetName,MeetingPaper.MeetingPaperID,MeetingPaper.AuthorName,MeetingPaper.AuthorIdentity,MeetingPaper.PaperTitle,PaperRetrievalCondition.PRCondition,Teacher.TeacherID,Teacher.TeacherName,TeacherANDJoinAcademicMeeting.FinalScore   from JoinAcademicMeeting,MeetingPaper,TeacherANDJoinAcademicMeeting,MeetingType,MeetingPlace,PaperRetrievalCondition,Teacher	where Teacher.ResearchLabID = '";
	public final String joinacademicSQLt = "' and JoinAcademicMeeting.MeetingPlaceID = MeetingPlace.MeetingPlaceID and JoinAcademicMeeting.MeetingTypeID = MeetingType.MeetingTypeID and MeetingPaper.PRConditionID = PaperRetrievalCondition.PRConditionID	and JoinAcademicMeeting.JoinAcaMID = TeacherANDJoinAcademicMeeting.JoinAcaMID and MeetingPaper.MeetingPaperID = TeacherANDJoinAcademicMeeting.MeetingPaperID	AND Teacher.TeacherID = TeacherANDJoinAcademicMeeting.TeacherID	AND TeacherANDJoinAcademicMeeting.SpareTire='1' and JoinAcademicMeeting.SpareTire='1' and MeetingPaper.SpareTire='1'  and TeacherANDJoinAcademicMeeting.CheckOut = '0'  and Teacher.SpareTire='1'";
	/* InviteExpertSpeech Parameter */
	public final static String[] inviteExpertSpeech = {"专家讲学编号","国家","专家类型","专家姓名","讲座名称","讲座时间","负责人ID","负责人姓名","当前教师编号","当前教师姓名","本人承担任务","当前教师绩效"};
	public final String inviteExpertSpeechSQLh = "select InvitedExpertsSpeech.IESpeechID,Nationality.CountryName,ExpertType.ExpertTypeName,	InvitedExpertsSpeech.ExpertsName,InvitedExpertsSpeech.LectureName,TeacherANDInvitedExpertsSpeech.TeacherID,Teacher.TeacherName,SelfUndertakeTask.UndertakeTaskName,TeacherANDInvitedExpertsSpeech.FinalScore		from InvitedExpertsSpeech,Teacher,Nationality,TeacherANDInvitedExpertsSpeech,SelfUndertakeTask,ExpertType		where Teacher.ResearchLabID = '";
	public final String inviteExpertSpeechSQLt = "'	and Teacher.TeacherID = TeacherANDInvitedExpertsSpeech.TeacherID		and SelfUndertakeTask.UndertakeTaskID = TeacherANDInvitedExpertsSpeech.UndertakeTaskID		and TeacherANDInvitedExpertsSpeech.IESpeechID = InvitedExpertsSpeech.IESpeechID		and Nationality.CountryID = InvitedExpertsSpeech.CountryID		and Teacher.SpareTire = '1'		and TeacherANDInvitedExpertsSpeech.CheckOut = '0'		and TeacherANDInvitedExpertsSpeech.SpareTire = '1'		and ExpertType.ExpertTypeID = InvitedExpertsSpeech.ExpertTypeID";
	/* ScientificResearchProject Parameter */
	public final static String[] scientificResearchProject = {"科研项目编号","科研项目名称","项目类型","项目原编号","项目来源","立项年份","项目总金/万","当年到款/万","负责人ID","负责人姓名","教师编号","教师姓名","本人承担任务","基础分数"};
	public final String scientificResearchPSQLh = "SELECT ScientificResearchProject.SRProjectID,ProjectType.ProjectTpName,ScientificResearchProject.SRPName,ScientificResearchProject.ChargePerson,ScientificResearchProject.ProjectNumber,ScientificResearchProject.ProjectSource,ScientificResearchProject.AdmitedProjectYear,ScientificResearchProject.SumFunds,Teacher.TeacherID,Teacher.TeacherName,SelfUndertakeTask.UndertakeTaskName,TeacherANDScientificResearchProject.YearFunds,TeacherANDScientificResearchProject.FinalScore 		FROM ProjectType,ScientificResearchProject,TeacherANDScientificResearchProject,Teacher,SelfUndertakeTask 		WHERE TeacherANDScientificResearchProject.SRProjectID = ScientificResearchProject.SRProjectID		and TeacherANDScientificResearchProject.UndertakeTaskID = SelfUndertakeTask.UndertakeTaskID		and TeacherANDScientificResearchProject.TeacherID = Teacher.TeacherID     and Teacher.ResearchLabID = '";
	public final String scientificResearchPSQLt = "'		and ScientificResearchProject.ProjectTypeID = ProjectType.ProjectTypeID		and TeacherANDScientificResearchProject.CheckOut = '0'		and Teacher.SpareTire = '1'		and TeacherANDScientificResearchProject.SpareTire = '1' and  TeacherANDScientificResearchProject.SRProjectID = ScientificResearchProject.SRProjectID ";
	/*ScientificResearchReward Parameter*/
	public static final String[] scientificResearchReward = {"科研奖励编号","获奖级别","获奖类别","科研奖励名称","授奖部门","获奖日期","获奖总人数","负责人ID","负责人姓名","当前教师编号","当前教师姓名","本人排名","当前教师绩效"};
	public final String scientificResearchRSQLh = "select ScientificResearchReward.SRRewardID ,RewardLevel.RewardLevelName,RewardType.RewardTypeName ,ScientificResearchReward.SRRewardName ,ScientificResearchReward.AwardDepartment ,ScientificResearchReward.RewardDate,	ScientificResearchReward.RewardTotalPeople ,TeacherANDScientificResearchReward.TeacherID ,Teacher.TeacherName,TeacherANDScientificResearchReward.SelfRanking ,TeacherANDScientificResearchReward.FinalScore		 from RewardType,RewardLevel,ScientificResearchReward ,Teacher,TeacherANDScientificResearchReward		 where Teacher.ResearchLabID = '";
	public final String scientificResearchRSQLt = "'   and TeacherANDScientificResearchReward.TeacherID = Teacher.TeacherID		and TeacherANDScientificResearchReward.SRRewardID = ScientificResearchReward.SRRewardID		and ScientificResearchReward.RewardLevelID = RewardLevel.RewardLevelID		and ScientificResearchReward.RewardTypeID = RewardType.RewardTypeID		AND TeacherANDScientificResearchReward.CheckOut = '0'		AND TeacherANDScientificResearchReward.SpareTire = '1'  	AND ScientificResearchReward.SpareTire = '1'	  AND Teacher.SpareTire = '1'";
	/*SelectedTalentProject Parameter*/
	public final static String[] selectedTalentProject = {"人才工程编号","人才工程名称","负责人ID","负责人姓名","当前教师编号","当前教师姓名","入选年份","当前教师绩效"};
	public final String selectedTalentSQLh = "select TalentProject.TalentProjectName,TalentProject.TalentProjectID,Teacher.TeacherID,Teacher.TeacherName, TeacherANDSelectedTalentProject.TPSelectedYear,TeacherANDSelectedTalentProject.FinalScore		from TalentProject,TeacherANDSelectedTalentProject,Teacher			where Teacher.ResearchLabID = '";
	public final String selectedTalentSQLt = "'		and TeacherANDSelectedTalentProject.TalentProjectID = TalentProject.TalentProjectID		and TeacherANDSelectedTalentProject.TeacherID = Teacher.TeacherID		AND Teacher.SpareTire = '1'		AND TeacherANDSelectedTalentProject.SpareTire = '1'		AND TeacherANDSelectedTalentProject.CheckOut = '0'";
	/*MainUndertakeAcademicMeeting Parameter*/
	public final static String[] mainundertakeacademicmeeting = {"主承办学术会议编号","主承办学术会议名称","会议类型","会议地点","会议时间","负责人ID","负责人姓名","参与教师编号","参与教师姓名","本人承担任务","基础分数"};
	public final String mainundertakeacameetSQLh = "select MainUndertakeAcademicMeeting.AcaMeetingID,MainUndertakeAcademicMeetingType.AcaMeetType,MainUndertakeAcademicMeetingPlace.AcaMeetPlace,	MainUndertakeAcademicMeeting.ChargePerson,MainUndertakeAcademicMeeting.AcaMeetingName,Teacher.TeacherID,Teacher.TeacherName,SelfUndertakeTask.UndertakeTaskName,TeacherANDMainUndertakeAcademicMeeting.FinalScore  from MainUndertakeAcademicMeeting,MainUndertakeAcademicMeetingPlace,MainUndertakeAcademicMeetingType,Teacher,TeacherANDMainUndertakeAcademicMeeting,SelfUndertakeTask   WHERE Teacher.ResearchLabID = '";
	public final String mainundertakeacameetSQLt = "'   AND TeacherANDMainUndertakeAcademicMeeting.AcaMeetingID = MainUndertakeAcademicMeeting.AcaMeetingID  AND Teacher.TeacherID = TeacherANDMainUndertakeAcademicMeeting.TeacherID    AND SelfUndertakeTask.UndertakeTaskID = TeacherANDMainUndertakeAcademicMeeting.UndertakeTaskID    AND MainUndertakeAcademicMeetingPlace.AcaMeetPlaceID = MainUndertakeAcademicMeeting.AcaMeetPlaceID    AND MainUndertakeAcademicMeetingType.AcaMeetTypeID = MainUndertakeAcademicMeeting.AcaMeetTypeID	AND Teacher.SpareTire = '1'    AND MainUndertakeAcademicMeeting.SpareTire = '1'		AND TeacherANDMainUndertakeAcademicMeeting.CheckOut = '0'		AND TeacherANDMainUndertakeAcademicMeeting.SpareTire = '1'";
	/*ScientificResearchDataSummary*/
	public final String[] scienresearchsum = {"研究所编号","研究所","均分","总分","年度"}; //table head :summary:  
	public final String[] sciensubModularhader = {"总分","均分","研究所编号","研究所","期刊论文","学术著作","邀请专家讲学","主承办学术会议","参加学术会议","科研项目","项目奖励","入选人才工程","年度"};
	public String getSperiodicalmodularSQL(String date1,String date2) {
		return "select ISNULL(AVG(TeacherANDPeriodical.FinalScore),0) as periodicalPaperAvg,ISNULL(SUM(TeacherANDPeriodical.FinalScore),0)as periodicalPaperSum from TeacherANDPeriodical where TeacherANDPeriodical.TeacherID in (select TeacherID from Teacher,PeriodicalPapers where  PeriodicalPapers.PPID = TeacherANDPeriodical.PPID and PeriodicalPapers.Year >'"+date1+"' and PeriodicalPapers.Year<'"+date2+"' and ResearchLabID = '";
	}
	public String getSacademicworkmodularSQL(String date1,String date2) {
		return "select ISNULL(AVG(TeacherANDAcademicWork.FinalScore),0) as academicWorkAvg,ISNULL(SUM(TeacherANDAcademicWork.FinalScore),0)as academicWorkSum from TeacherANDAcademicWork where TeacherANDAcademicWork.TeacherID in (select TeacherID from Teacher,AcademicWork where AcademicWork.AcaworkID = TeacherANDAcademicWork.AcaworkID and AcademicWork.PublishDate>'"+date1+"' and AcademicWork.PublishDate<'"+date2+"' and  ResearchLabID = '";
	}
	public String getSjoinacademicmodularSQL(String date1,String date2) {
		return "select ISNULL(AVG(TeacherANDJoinAcademicMeeting.FinalScore),0) as JoinAcademicMeetingAvg,ISNULL(SUM(TeacherANDJoinAcademicMeeting.FinalScore),0)as JoinAcademicMeetingSum from TeacherANDJoinAcademicMeeting where TeacherANDJoinAcademicMeeting.TeacherID in (select TeacherID from Teacher,JoinAcademicMeeting where JoinAcademicMeeting.JoinAcaMID = TeacherANDJoinAcademicMeeting.JoinAcaMID and JoinAcademicMeeting.meetingdate>'"+date1+"' and JoinAcademicMeeting.meetingdate<'"+date2+"' and ResearchLabID = '";
	}
	public String getSinviteexpertmodularSQL(String date1,String date2) {
		return "select ISNULL(AVG(TeacherANDInvitedExpertsSpeech.FinalScore),0) as InvitedExpertsSpeechAvg,ISNULL(SUM(TeacherANDInvitedExpertsSpeech.FinalScore),0)as InvitedExpertsSpeechSum from TeacherANDInvitedExpertsSpeech where TeacherANDInvitedExpertsSpeech.TeacherID in (select TeacherID from Teacher,InvitedExpertsSpeech where InvitedExpertsSpeech.IESpeechID = TeacherANDInvitedExpertsSpeech.IESpeechID and InvitedExpertsSpeech.SpeechDate>'"+date1+"' and InvitedExpertsSpeech.SpeechDate<'"+date2+"' and ResearchLabID = '";
	}
	public String getSmainundertakeacamodularSQL(String date1,String date2) {
		return "select ISNULL(AVG(TeacherANDMainUndertakeAcademicMeeting.FinalScore),0) as MainUndertakeAcademicMeetingAvg,ISNULL(SUM(TeacherANDMainUndertakeAcademicMeeting.FinalScore),0)as MainUndertakeAcademicMeetingSum from TeacherANDMainUndertakeAcademicMeeting where TeacherANDMainUndertakeAcademicMeeting.TeacherID in (select TeacherID from Teacher,MainUndertakeAcademicMeeting where MainUndertakeAcademicMeeting.AcaMeetingID = TeacherANDMainUndertakeAcademicMeeting.AcaMeetingID and MainUndertakeAcademicMeeting.meetingdate>'"+date1+"' and MainUndertakeAcademicMeeting.meetingdate<'"+date2+"' and ResearchLabID = '";
	}
	public String getSprojectmodularSQL(String date1,String date2) {
		return "select ISNULL(AVG(TeacherANDScientificResearchProject.FinalScore),0) as ScientificResearchProjectAvg,ISNULL(SUM(TeacherANDScientificResearchProject.FinalScore),0)as ScientificResearchProjectSum from TeacherANDScientificResearchProject where TeacherANDScientificResearchProject.TeacherID in (select TeacherID from Teacher,ScientificResearchProject where ScientificResearchProject.SRProjectID = TeacherANDScientificResearchProject.SRProjectID and ScientificResearchProject.AdmitedProjectYear>'"+date1+"' and ScientificResearchProject.AdmitedProjectYear<'"+date2+"' and ResearchLabID = '";
	}
	public String getSprojectrewardmodularSQL(String date1,String date2) {
		return "select ISNULL(AVG(TeacherANDScientificResearchReward.FinalScore),0) as ScientificResearchRewardAvg,ISNULL(SUM(TeacherANDScientificResearchReward.FinalScore),0)as ScientificResearchRewardSum from TeacherANDScientificResearchReward where TeacherANDScientificResearchReward.TeacherID in (select TeacherID from Teacher,ScientificResearchReward where ScientificResearchReward.SRRewardID = TeacherANDScientificResearchReward.SRRewardID and ScientificResearchReward.RewardDate>'"+date1+"' and ScientificResearchReward.RewardDate<'"+date2+"' and ResearchLabID = '";
	}
	public String getSselectedTalentmodularSQL(String date1,String date2) {
		return "select ISNULL(AVG(TeacherANDSelectedTalentProject.FinalScore),0) as SelectedTalentProjectAvg,ISNULL(SUM(TeacherANDSelectedTalentProject.FinalScore),0)as SelectedTalentProjectSum from TeacherANDSelectedTalentProject where TeacherANDSelectedTalentProject.TeacherID in (select TeacherID from Teacher,TalentProject where TalentProject.TalentProjectID = TeacherANDSelectedTalentProject.TalentProjectID and TalentProject.selectedDate>'"+date1+"' and TalentProject.selectedDate<'"+date2+"' and ResearchLabID = '";
	}
	//FOR TEACHING
	/*TeachingDataSummary*/
	public final String[] teachingsum = {"系编号","系","均分","总分","年度"};
	//FOR VA
	/*VaActListSum*/
	public final static String[] vaactList = {"活动发布编号","活动名称","活动类型","参与人员","活动日期","申请教师","申请教师ID","分数"};
	public final static String[] vaJoinedAct = {"活动发布编号","活动名称","参与教师ID","教师姓名","分数"};
	public final static String[] vasummary = {"活动参与（总/均）","活动缺席（总/均）","总计（总/均）"};
	
	public final static String[] scienReschModules={"科研项目（总/均）","科研奖励（总/均）","学术著作（总/均）","参加学术会议（总/均）","入选人才工程（总/均）","邀请专家讲学（总/均）","期刊论文（总/均）","主承办学术会议（总/均）","总计（总/均）"};
	
}
