<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
<jsp:directive.page import="java.text.DateFormat"/>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>

<head>
<base target="_self"> 
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>NUAA-CEM绩效管理系统 - 管理主页</title>  
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet">
    <script type="text/javascript">    		
    		function checkID() {
				var resAdmin = "${teacher.researchLabAdmin}";
				var resA = document.getElementById("resAdmin");
				var departAdmin = "${teacher.departAdmin}";
				var departA = document.getElementById("departAdmin");
				if(resAdmin =="1" )
					resA.style.display = "block";
				if(departAdmin =="1" )
					departA.style.display = "block";
				var message = "${loginstatus}";
				if(message!=""&&message!=null)
					alert(message);
			}
    		function get(action,predate,lasdate) {
    			var date1 = document.getElementById(predate).value;
    			var date2 = document.getElementById(lasdate).value;
				window.open(action+"?date1="+date1+"&date2="+date2);
			}
    </script>
        <!-- href="summaryScientificResearch!getView" -->
</head>

<body onload="checkID()"  class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src="" /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${teacher.teacherName }</strong></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li></a>
                                </li>
                                <li><a class="J_menuItem" href="searchjiben.jsp">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="xiugaimima.jsp">修改密码</a>
                                </li>
                                <li></a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="logout!out">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">bamanker
                    </li>
<!--                     <li> -->
<!--                         <a href="#"> -->
<!--                             <i class="fa fa-home"></i> -->
<!--                             <span class="nav-label">任务管理</span> -->
<!--                             <span class="fa arrow"></span> -->
<!--                         </a> -->
<!--                         <ul class="nav nav-second-level"> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="index_v1.html" data-index="0">待完成任务</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="index_v2.html">已完成任务</a> -->
<!--                             </li> -->
<!--                             <li> -->
<!--                                 <a class="J_menuItem" href="index_v3.html">发出任务</a> -->
<!--                             </li> -->
<!--                         </ul> -->

<!--                     </li> -->
                    <li>
                        <a href="#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">绩效管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
	                            <a href="#">
	                                <i class="fa fa fa-bar-chart-o"></i>
	                            	<span class="nav-label">科研绩效管理</span>
	                            	<span class="fa arrow"></span>
	                            </a>
	                            <ul class="nav nav-second-level firstslide">
	                            	<li>
				                        <a class="J_menuItem" href="ATperiodicalpaper-paperset!getPeriodicalPaperINF">期刊论文</a>
				                    </li>
				                    <li>
				                         <a class="J_menuItem" href="ATacademicwk-workset!getWorkall">学术著作</a>
				                    </li>
				                    <li>
				                         <a class="J_menuItem" href="ATscienceresearchred-rewardset!gainscienceReward">科研项目奖励</a>
				                     </li>
				                     <li>
				                         <a class="J_menuItem" href="ATjoinacademicmeeting-meetingset!gainAllJoinacademic">参加学术会议</a>
				                     </li>
				                     <li>
				                         <a class="J_menuItem" href="ATinviteexpertspeech-speechset!gainAllspeech">邀请专家讲学</a>
				                     </li>
				                     <li>
				                         <a class="J_menuItem" href="ATundertakeacademicmeet-meetset!gainAllacademicm">主承办学术会议</a>
				                     </li>
				                     <li>
				                          <a class="J_menuItem" href="ATselectedtalentprojec-talentset!gainAlltalentprojec">入选人才工程</a>
				                     </li>
				                     <li>
				                          <a class="J_menuItem" href="ATscientificresearchpro-projectset!gainAllscienpro">科研项目</a>
				                     </li>
		                        </ul>
                            </li>
                            <li>
                                <a class="" href="#">教学绩效管理 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
									<li><a href="#">教学能力与实效 <span class="fa arrow"></span></a>
										<ul class="nav nav-third-level"">
											<li><a class="J_menuItem" href="ATClassTeachPerformanceSet!getAllRecord">课堂教学绩效</a></li>
											<li><a class="J_menuItem" href="ATDegreeThesisGuidancePerformanceSet!getAllRecord">学位论文指导质量绩效</a></li>
											<li><a class="J_menuItem" href="ATTeachingCompetitionPerformanceSet!getAllRecord">教学竞赛绩效</a></li>
											<li><a class="J_menuItem" href="ATTeachingAbilityImprovePerformanceSet!getAllRecord">教学能力提升绩效</a></li>
											<li><a class="J_menuItem" href="">教学名师和教学团队绩效</a></li>
										</ul>
									</li>
									<li><a href="">综合改革与教学研究 <span class="fa arrow"></span></a>
										<ul class="nav nav-third-level">
											<li><a class="J_menuItem" href="">教学研究绩效</a></li>
											<li><a class="J_menuItem" href="">教学论文绩效</a></li>
											<li><a class="J_menuItem" href="">教学成果奖绩效</a></li>
											<li><a class="J_menuItem" href="">教材建设绩效</a></li>
											<li><a class="J_menuItem" href="">精品课程建设绩效</a></li>
											<li><a class="J_menuItem" href="">专业建设项目申报绩效</a></li>
											<li><a class="J_menuItem" href="">企业工作站和联合培养基地建设绩效</a></li>
											<li><a class="J_menuItem" href="ATSummerCourseInternationalConstructionPerformanceSet!getAllRecord">暑期课程与国际课程建设绩效</a></li>
										</ul>
									</li>
									<li><a href="">学生指导工作 <span class="fa arrow"></span></a>
										<ul class="nav nav-third-level">
											<li><a class="J_menuItem" href="ATPracticeInnovationGuidePerformanceSet!getAllRecord">实践创新指导绩效</a></li>
											<li><a class="J_menuItem" href="ATStudentCompetitionGuidancePerformanceSet!getAllRecord">学生竞赛指导绩效</a></li>
											<li><a class="J_menuItem" href="ATJoinStudentActivityPerformanceSet!getAllRecord">参与学生活动绩效</a></li>
											<li><a class="J_menuItem" href="ATUndergraduateTutorGuidancePerformanceSet!getAllRecord">本科生导师指导绩效</a></li>
											<li><a class="J_menuItem" href="ATOffCampusPracticeGuidancePerformanceSet!getAllRecord">校外实践指导绩效</a></li>
										</ul>
									</li>
								</ul>
                            </li>
                            <li>
                                <a class="J_menuItem" href="">公益绩效管理</a>
                            </li>
                             <li>
                                <a class="J_menuItem" href="">绩效总览</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                    <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">导出数据</span><span class="fa arrow"></span></a>
				                      <ul class="nav nav-third-level">
				                      <li><a class="J_menuItem" href="">科研<span class="fa arrow"></span></a>
				                      <ul class="nav nav-third-level">
												<li><a  data-toggle="modal"  data-backdrop="true" class=""  data-target="#Resummary"   >汇总数据</a></li>
												 <li>
				                                    <a href="">明细定制导出<span class="fa arrow"></span></a>
				                                    	 <ul class="nav nav-third-level">
															<li><a class="J_menuItem" href="periodicalcustomized">期刊论文</a></li>
															<li><a class="J_menuItem" href="academicworkcustomized">学术著作</a></li>
															<li><a class="J_menuItem" href="joinacademicmeetcustomized">参加学术会议</a></li>
															<li><a class="J_menuItem" href="inviteExpertSpeechcustomized">邀请专家讲学</a></li>
															<li><a class="J_menuItem" href="scientificResearchProjectcustomized">科研项目</a></li>
															<li><a class="J_menuItem" href="scientificResearchRewardcustomized">科研奖励</a></li>
															<li><a class="J_menuItem" href="selectedTalentProjectcustomized">人选人才工程</a></li>
															<li><a class="J_menuItem" href="mainundertakeacademicmeetingcustomized">主承办学术会议</a></li>
														</ul>
												</li>
											</ul>
											</li>
				                      <li><a class="J_menuItem" href="">教学<span class="fa arrow"></span></a>
				                      		<ul class="nav nav-third-level">
	                                    		<li><a class="J_menuItem" href="downlaodVIEW!dataViewTeach">导出汇总数据</a></li>
												<li><a href="">明细定制导出<span class="fa arrow"></span></a>
													<ul class="nav nav-third-level">
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
														<li><a class="J_menuItem" href="">审核据定制</a></li>
													</ul>
												</li>
										</ul>
				                      </li>
				                      <li><a class="J_menuItem" href="">公益<span class="fa arrow"></span></a>
				                     		<ul class="nav nav-third-level">
												<li><a class="J_menuItem" href="downlaodVIEW!dataViewScience">汇总数据</a></li>
												 <li>                                    
				                                    <a href="">明细定制导出<span class="fa arrow"></span></a>
				                                    	 <ul class="nav nav-third-level">
															<li><a class="J_menuItem" href="">期刊论文</a></li>
															<li><a class="J_menuItem" href="">审核据定制</a></li>
															<li><a class="J_menuItem" href="">审核据定制</a></li>
															<li><a class="J_menuItem" href="">审核据定制</a></li>
															<li><a class="J_menuItem" href="">审核据定制</a></li>
															<li><a class="J_menuItem" href="">审核据定制</a></li>
															<li><a class="J_menuItem" href="">审核据定制</a></li>
														</ul>
												</li>
											</ul> 
				                     </li>
				                      </ul>
											
					</li>
                    <li>
                        <a href="#"><i class="fa fa-edit"></i> <span class="nav-label">审核管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li><a class="J_menuItem" href="form_basic.html" style="display: none;">按教师审核</a>
                            </li>
							<li><a href="#">科研审核 <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">									
									<li><a class="J_menuItem" href="ATScientificResearchProjectAudit!getSRPToBeAudited">科研项目审核</a></li>
									<li><a class="J_menuItem" href="ATScientificResearchRewardAudit!getRewardInfo">科研项目奖励审核</a></li>
                                	<li><a class="J_menuItem" href="ATTeacherAndSelectedTalentProjectAudit!getTASTalentProjectInfo">入选人才项目审核</a></li>
                                	<li><a class="J_menuItem" href="ATTeacherAndPeriodicalAudit!getTAPeriodicalList">期刊论文审核</a></li>
                                	<li><a class="J_menuItem" href="ATTeacherAndmainUndertakeAcademicMeetingAudit!getTAUAcademicMeetingList">承担学术会议审核</a></li>
                                	<li><a class="J_menuItem" href="ATTeacherAndjoinAcademicMeetingAudit!getTAAMeetingList">参加学术会议审核</a></li>
                                	<li><a class="J_menuItem" href="ATTeacherAndinvitedExpertsSpeechAudit!getTAExpertSpeechList">邀请专家讲座审核</a></li>
                                	<li><a class="J_menuItem" href="ATTeacherAndacademicWorkAudit!getTAAcademicWork">学术著作审核</a></li>
								</ul>
							</li>
							<li>
                                <a href="#">教学审核 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">                                                                    
									<li><a href="#">教学能力与实效 <span class="fa arrow"></span></a>
										<ul class="nav nav-third-level"">
											<li><a class="J_menuItem" href="ATTfclassTeachPerformanceAudit!getTF_classTeachPerformanceList">课堂教学绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfdegreeThesisGuidancePerformanceAudit!getTfDegreeThesisGuidancePerformList">学位论文指导质量绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfteachingCompetitionPerformanceAudit!getTFTeachingCompetitionPerfList">教学竞赛绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfteachingAbilityImprovePerformanceAudit!getTeachingAbilityImprovePerfList">教学能力提升绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTffamousTeacherTeamPerformanceAudit!getTfFamousTeacherTermPerfList">教学名师和教学团队绩效审核</a></li>
										</ul>
									</li>
									<li><a href="">综合改革与教学研究 <span class="fa arrow"></span></a>
										<ul class="nav nav-third-level">
											<li><a class="J_menuItem" href="ATTfteachingRearchPerformanceAudit!getTfteachingRearchPerformanceList">教学研究绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfteachingPaperPerformanceAudit!getTfteachingPaperPerformanceList">教学论文绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfteachingAchievementPerformanceAudit!getTfteachingAchievementPerformanceList">教学成果奖绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTftextbookConstructionPerformanceAudit!getTftextbookConstructionPerformanceList">教材建设绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTffineCourseConstructionPerformanceAudit!getTffineCourseConstructionPerformanceList">精品课程建设绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfprofessionalProjectDeclarePerformanceAudit!getTfprofessionalProjectDeclarePerformanceList">专业建设项目申报绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfenterpriseWorkstationTrainingBaseConstructionPerformanceAudit!getTfenterpriseWorkstationTrainingBaseConstructionList">企业工作站和联合培养基地建设绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfsummerCourseInternationalConstructionPerformanceAudit!getTfSummerAndInternationalCourseConstructionPerformanceList">暑期课程与国际课程建设绩效审核</a></li>
										</ul>
									</li>
									<li><a href="">学生指导工作 <span class="fa arrow"></span></a>
										<ul class="nav nav-third-level">
											<li><a class="J_menuItem" href="ATTfpracticeInnovationGuidePerformanceAudit!getTfPracticeInnovationGuidePerformanceList">实践创新指导绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfstudentCompetitionGuidancePerformanceAudit!getTfStudentCompetitionGuidancePerformanceList">学生竞赛指导绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfjoinStudentActivityPerformanceAudit!getTfStudentActivityPerformanceList">参与学生活动绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfundergraduateTutorGuidancePerformanceAudit!getTfUndergraduateTutorGuidancePerformanceList">本科生导师指导绩效审核</a></li>
											<li><a class="J_menuItem" href="ATTfoffCampusPracticeGuidancePerformanceAudit!getTfOffCampusPracticeGuidancePerformanceList">校外实践指导绩效审核</a></li>
										</ul>
									</li>
									
                                </ul>
                            </li>
                            <li>
                                <a href="#">公益审核 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">                                   
                                    <li><a class="J_menuItem" href="">活动加入审核</a></li>
                                    <li><a class="J_menuItem" href="">活动缺席审核</a></li>
                                    <li><a class="J_menuItem" href="">活动发布审核</a></li>
                                    <li><a class="J_menuItem" href="">活动申请添加审核</a></li>
                                </ul>
                             </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-table"></i> <span class="nav-label">系统设置</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">基础设置 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="ATDepartmentBaseset!getDepartinfo">系设置</a></li>
                                    <li><a class="J_menuItem" href="ATResearchLabBaseset!getResearchLabinfo">研究所设置</a></li>
                                    <li><a class="J_menuItem" href="ATNationalityBaseset!getNationalityinfo">国籍设置</a></li>
                                    <li><a class="J_menuItem" href="ATTermBaseset!getTermList">学期设置</a></li>
                                    <li><a class="J_menuItem" href="ATVaSetting2!entityList">公益管理员设置</a></li>
                                    
                                </ul>
                             </li>
                            </li>
                            <li>
                            <a href="#">科研设置<span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li>
                                    	<a href="#">期刊论文设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ATPeriodicalTypeset!getPeriodicalTypeINF">期刊类别设置</a></li>
												<li><a class="J_menuItem"  href="ATPeriodicalset!getPeriodicalINF">期刊表设置</a></li>
												<li><a class="J_menuItem"  href="ATPeriodicalScoreset!getPeriodicalScoreINF">期刊评分表设置</a></li>
	                                      </ul>
                                    </li>
                                     <li>
                                     	<a href="#">学术著作设置<span class="fa arrow"></span></a>
	                                      <ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ATAcademicWorkWordNumberset!getWordNumberINF">字数类别设置</a></li>
												<li><a class="J_menuItem"  href="ATAcademicWorkPublishClubTypeset!getPublishClubTypeINF">出版社类别设置</a></li>
												<li><a class="J_menuItem"  href="ATAcademicWorkPublishClubset!getPublishClubINF">出版社设置</a></li>
												<li><a class="J_menuItem"  href="ATAcademicWorkScoreset!getAcadWorkScoreINF">学术著作评分设置</a></li>
	                                      </ul>
                                     </li>
                                    <li>
                                    	<a href="#">科研项目设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ATScientificResearchProjectTypeset!viewScienResearchProjectType">科研项目类别设置</a></li>
												<li><a class="J_menuItem"  href="ATScientificResearchProjectScoreset!viewScienResearchProjectScore">科研项目评分设置</a></li>																							
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">科研项目奖励设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ATProjectRewardTypeset!getProjectRewardTypeINF">科研奖励类别设置</a></li>
												<li><a class="J_menuItem"  href="ATProjectRewardLevelset!getProjectRewardLevelINF">科研奖励级别设置</a></li>
												<li><a class="J_menuItem"  href="ATProjectRewardScoreset!getProjectRewardScoreINF">科研奖励评分设置</a></li>												
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">参加学术会议设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ATJoinAcademicMeetingTypeset!getAcademicMeetingTypeINF">学术会议类别设置</a></li>
												<li><a class="J_menuItem"  href="ATJoinAcademicMeetingPaperRetrievalset!getPaperRetrievalConditionINF">论文检索类别设置</a></li>
												<li><a class="J_menuItem"  href="ATJoinAcademicMeetingPlaceset!getAcademicMeetingPlaceINF">学术会议地点设置</a></li>
												<li><a class="J_menuItem"  href="ATJoinAcademicMeetingScoreset!getJoinAcademicMeetingScoreINF">学术会议评分设置</a></li>
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">邀请专家讲学设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ATInviteExpertSpeechExpertTypeset!getExpertTypeINF">专家类别设置</a></li>
												<li><a class="J_menuItem"  href="ATNationalityBaseset!getNationalityinfo">国籍设置</a></li>
												<li><a class="J_menuItem"  href="ATInviteExpertSpeechScoreset!getInviteExpertSpeechScoreINF">邀请专家讲学评分设置</a></li>
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">主承办学术会议设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ATMainUndertakeAcademicMeetingTypeset!getMeetingTypeINF">学术会议类别设置</a></li>
												<li><a class="J_menuItem"  href="ATMainUndertakeAcademicMeetingPlaceset!getMeetingPlaceINF">学术会议地点设置</a></li>
												<li><a class="J_menuItem"  href="ATMainUndertakeAcademicMeetingScoreset!getMeetingScoreINF">学术会议评分设置</a></li>
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">入选人才项目工程设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ATSelectedTalentProjectFormset!getSelectedTalentProjectINF">人才工程设置</a></li>
												<li><a class="J_menuItem"  href="ATSelectedTalentProjectScoreset!getSelectedTalentProjectScoreINF">人才工程评分设置</a></li>												
	                                      </ul>
                                    </li>
                                    <li><a class="J_menuItem" href="ATSelfUndertakeTaskSet!viewAllTaskUndertaked">本人承担任务设置</a></li>
                                </ul>
                            </li>
                             <li>
                             	<a href="#">教学设置<span class="fa arrow"></span></a>
                                    	 <ul class="nav nav-third-level">
		                                 		<li><a href="#">课堂教学<span class="fa arrow"></span></a>
				                                    	<ul class="nav nav-third-level">
						                                      	<li><a class="J_menuItem"  href="ATclassTeachRatioset!totalClassTimeRatio">总站堂时系数设置</a></li>
																<li><a class="J_menuItem"  href="ATteachEvaluationset!teachEvaluation">综合教学评估设置</a></li>
																<li><a class="J_menuItem"  href="ATdegreeThesisGuidanceRewardLevelset!degreeThesisGuidanceRewardLevelList">论文指导系数设置</a></li>
																<li><a class="J_menuItem"  href="ATteachingCompetitionRewardLevelset!teachingCompetitionRewardLevelList">教学竞赛系数设置</a></li>
																<li><a class="J_menuItem"  href="ATteachingAbilityImproveLevelset!teachingAbilityImproveLevelList">教学能力提升设置</a></li>
																<li><a class="J_menuItem"  href="ATfamousTeacherTeamRewadLevelset!famousTeacherTeamRewadLevelList">优秀教师团队称号系数设置</a></li>	
																<li><a class="J_menuItem"  href="ATTerm1set!getTermList">学期设置</a></li>	
																											
					                                     </ul>
	                                      		 </li>													
													<li><a href="#">教学研究<span class="fa arrow"></span></a>
				                                    	<ul class="nav nav-third-level">
						                                      	 <li><a class="J_menuItem"  href="ATteachRearchFundLevelset!entityList">教学研究资金规模设置</a></li>
																<li><a class="J_menuItem"  href="ATteachRearchEvaluationset!entityList">教学研究成果设置</a></li>
																<li><a class="J_menuItem"  href="ATteachPaperRetrievalConditionset!entityList">论文发表等级设置</a></li>
																<li><a class="J_menuItem"  href="ATteachAchievementRewardLevelset!entityList">教学成果等级设置</a></li>
																<li><a class="J_menuItem"  href="ATtextBookConstructionTbLevelset!entityList">教材编写等级设置</a></li>
																<li><a class="J_menuItem"  href="ATfineCourseConstructionLevelset!entityList">精品课程等级设置</a></li>
																<li><a class="J_menuItem"  href="ATprofessionalProjectDeclareLevelset!entityList">品牌专业等级设置</a></li>
																<li><a class="J_menuItem"  href="ATenterpriseWorkstationTrainingbaseConstructionLevelset!entityList">企业培养基地设置</a></li>
																<li><a class="J_menuItem"  href="ATsummerCourseInternationalConstructionLevelset!entityList">暑期课程设置</a></li>												
					                                     </ul>
	                                      		 </li>													
													<li><a href="#">创新项目<span class="fa arrow"></span></a>
				                                    	<ul class="nav nav-third-level">
						                                      	 <li><a class="J_menuItem"  href="ATpracticeInnovationGuideLevelset!entityList">创新项目等级设置</a></li>
																<li><a class="J_menuItem"  href="ATpracticeInnovationGuideGraduationThesisGuideEvalutionset!entityList">学生竞赛指导等级设置</a></li>
																<li><a class="J_menuItem"  href="ATstudentCompetitionGuidanceRewardLevelset!entityList">学生竞赛等级设置</a></li>
																<li><a class="J_menuItem"  href="ATstudentCompetitionGuidanceScoreset!entityList">竞赛获奖级别设置</a></li>
																<li><a class="J_menuItem"  href="ATstudentCompetitionGuidanceCompetitionTypeset!entityList">竞赛级别设置</a></li>
																<li><a class="J_menuItem"  href="ATundergraduateTutorGuidanceCacheset!entityList">本科生导师等级设置</a></li>
																<li><a class="J_menuItem"  href="AToffCampusPracticeGuidanceLevelset!entityList">校外实践等级设置</a></li>			     	 																	
					                                     </ul>
	                                      		 </li>																										
	                                     </ul>
                             </li>
                            <li><a class="J_menuItem" href="">公益设置</a>
                            <li>
                                <a href="#">用户管理 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="add_Teacher!getsinfo">增加用户</a>
                                    </li>
                                    <li><a class="J_menuItem" href="update_Teacher!viewTeacher">修改用户</a>
                                    </li>
                                </ul>
                             </li>                                                    
                        </ul>
                        <li id="resAdmin"  style="display: none"> <a href="resAdminAuditbrowse"  class="J_menuItem" ><i class="fa fa-edit"></i> <span class="nav-label">所长审核</span></a> </li>
                        <li id="departAdmin"  style="display: none"> <a href="#"  class="J_menuItem"  ><i class="fa fa-edit"  ></i> <span class="nav-label">系主任审核</span></a> </li>
                    </li>
                </ul>
            </div>
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header">
	                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#">
	                    	<i class="fa fa-bars"></i> 
	                    </a>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                            </a>
                        </li>
                    </ul>
                </nav>
            </div>
            <div class="row content-tabs">
                <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                    </div>
                </nav>
                <a href="login.html" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="" seamless></iframe>
            </div>
            <div class="footer">
                <div class="pull-right">&copy; 2015-2016 <a href="" target="_blank">bamanker</a>
                </div>
            </div>
        </div>
        <!--右侧部分结束-->
        <!--右侧边栏开始-->
        <div id="right-sidebar">
            <div class="sidebar-container">

                <ul class="nav nav-tabs navs-3">

                    <li class="active">
                        <a data-toggle="tab" href="#tab-1">
                            <i class="fa fa-gear"></i> 主题
                        </a>
                    </li>
                </ul>

                <div class="tab-content">

                </div>

            </div>
        </div>
        <!--右侧边栏结束-->
        
    </div>

    <div id="Resummary" class="modal fade" aria-hidden="true"  >
        <div class="modal-dialog">
            <div class="modal-content"  style="windth: 1000px">
                <div class="modal-body" >
                    <div class="row">                        
                            <h3 class="m-t-none m-b">汇总时间范围</h3>
							<form role="form" action="doadd!add">
								<div class="form-group">
									<div class="example">
										<form method="post" name="f">
											从:<input type="text"  id="sciendate1" style="width: 116px;" onClick="eye.datePicker.show(this);" readonly="readonly" />到:<input type="text"  id="sciendate2" style="width: 116px;" onClick="eye.datePicker.show(this);" readonly="readonly" />
										</form>
									</div>
								</div>
								<button class="btn  btn-primary pull-left m-t-n-xs "  onclick="get('summaryScientificResearch!getView','sciendate1','sciendate2')"  type="submit">
										 <strong>确定</strong>
									</button>
								<div>
									<button type="button" class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>									
								</div>
							</form>
						</div>
                </div>
            </div>
        </div>
    </div>                
     <script type="text/javascript" language="javascript" src="js/jquery.js"></script>
    <script type="text/javascript" language="javascript" src="js/eye-base.js"></script>
    <script type="text/javascript" language="javascript" src="js/eye-all.js"></script>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="js/plugins/layer/layer.min.js"></script>
    <script src="js/hplus.min.js?v=4.0.0"></script>
    <script type="text/javascript" src="js/contabs.min.js"></script>
    <script src="js/plugins/pace/pace.min.js"></script>
    <script type="text/javascript">
    $(document).ready(function() {
		$('.firstslide li').css("margin-left","12%");
		if("${teacher.vaadmin}"=="1"){
			$('#vaadmin').css("display","");
		}
		$('.gohome .bounceInUp').remove();
	});
    	$('.J_menuItem').click(function() {
    		$('.minimalize-styl-2').click();
    		$('.gohome .bounceInUp').remove();
		});
    </script>
</body>

</html>