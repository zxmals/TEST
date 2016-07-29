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
				var departAdmin = "${teacher.deaprtAdmin}";
				var departA = document.getElementById("departAdmin");
				if(resAdmin =="1" )
					resA.style.display = "block";
				if(departAdmin =="1" )
					departA.style.display = "block";
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
                            <span><img alt="image" class="img-circle" src="img/profile_small.jpg" /></span>
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
                    <li>
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">任务管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="index_v1.html" data-index="0">待完成任务</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="index_v2.html">已完成任务</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="index_v3.html">发出任务</a>
                            </li>
                        </ul>

                    </li>
                    <li>
                        <a href="#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">绩效管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="ViewTeacherPerformance">科研绩效管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="">教学绩效管理</a>
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
                            <li><a class="J_menuItem" href="form_basic.html">按教师审核</a>
                            </li>
							<li><a href="#">科研审核 <span class="fa arrow"></span></a>
								<ul class="nav nav-third-level">									
									<li><a class="J_menuItem" href="ScientificAuditbrowse!getExportData">审核</a></li>
								</ul>
							</li>
							<li>
                                <a href="#">教学审核 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">                                                                    
									<li><a class="J_menuItem" href="TeahingAuditpreview!getExportData">审核</a>
                                    </li>
                                </ul>
                            </li>
                            <li>
                                <a href="#">公益审核 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">                                   
                                    <li><a class="J_menuItem" href="">审核</a></li>
                                </ul>
                             </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-table"></i> <span class="nav-label">系统设置</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="baseSet!getBaseSetInfo">基础设置 <span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li><a class="J_menuItem" href="sysSetting/Departmentset!getDepartinfo">系设置</a></li>
                                    <li><a class="J_menuItem" href="sysSetting/ResearchLabset!getResearchLabInfo">研究所设置</a></li>
                                    <li><a class="J_menuItem" href="sysSetting/Nationalityset!getNationalinfo">国籍设置</a></li>
                                </ul>
                             </li>
                            </li>
                            <li>
                            <a href="#">科研设置<span class="fa arrow"></span></a>
                                <ul class="nav nav-third-level">
                                    <li>
                                    	<a href="#">期刊论文设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="PeriodicalTypeset!viewPeriodicalType">期刊类别设置</a></li>
												<li><a class="J_menuItem"  href="PeriodicalFormset!viewPeriodicalForm">期刊表设置</a></li>
												<li><a class="J_menuItem"  href="PeriodicalScoreset!viewPeriodicalScore">期刊评分表设置</a></li>
	                                      </ul>
                                    </li>
                                     <li>
                                     	<a href="#">学术著作设置<span class="fa arrow"></span></a>
	                                      <ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="AcademicWorkWordNumberset!viewWordNumber">字数类别设置</a></li>
												<li><a class="J_menuItem"  href="AcademicWorkPublishClubTypeset!viewPublishClubType">出版社类别设置</a></li>
												<li><a class="J_menuItem"  href="AcademicWorkPublishClubset!viewPublishClub">出版社设置</a></li>
												<li><a class="J_menuItem"  href="AcademicWorkScoreset!viewAcadWorkScore">学术著作评分设置</a></li>
	                                      </ul>
                                     </li>
                                    <li>
                                    	<a href="#">科研项目设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ScientificResearchProjectTypeset!viewScienResearchProjectType">科研项目类别设置</a></li>
												<li><a class="J_menuItem"  href="ScientificResearchProjectScoreset!viewScienResearchProjectScore">科研项目评分设置</a></li>																							
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">科研项目奖励设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="ProjectRewardTypeset!viewProjectRewardType">科研奖励类别设置</a></li>
												<li><a class="J_menuItem"  href="ProjectRewardLevelset!viewProjectRewardLevel">科研奖励级别设置</a></li>
												<li><a class="J_menuItem"  href="ProjectRewardScoreset!viewProjectRewardScore">科研奖励评分设置</a></li>												
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">参加学术会议设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="JoinAcademicMeetingTypeset!viewMeetingType">学术会议类别设置</a></li>
												<li><a class="J_menuItem"  href="JoinAcademicMeetingPaperRetrievalset!viewPaperRetrieval">论文检索类别设置</a></li>
												<li><a class="J_menuItem"  href="JoinAcademicMeetingPlaceset!viewMeetingPlace">学术会议地点设置</a></li>
												<li><a class="J_menuItem"  href="JoinAcademicMeetingScoreset!viewMeetingScore">学术会议评分设置</a></li>
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">邀请专家讲学设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="InviteExpertSpeechExpertTypeset!viewExpertType">专家类别设置</a></li>
												<li><a class="J_menuItem"  href="sysSetting/Nationalityset">国籍设置</a></li>
												<li><a class="J_menuItem"  href="InviteExpertSpeechScoreset!viewInviteExpertSpeechScore">邀请专家讲学评分设置</a></li>
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">主承办学术会议设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="MainUndertakeAcademicMeetingTypeset!viewMeetingType">主承办学术会议类别设置</a></li>
												<li><a class="J_menuItem"  href="MainUndertakeAcademicMeetingPlaceset!viewMeetingPlace">主承办学术会议地点设置</a></li>
												<li><a class="J_menuItem"  href="MainUndertakeAcademicMeetingScoreset!viewMeetingScore">学术会议评分设置</a></li>
	                                      </ul>
                                    </li>
                                    <li>
                                    	<a href="#">入选人才项目设置<span class="fa arrow"></span></a>
                                    	<ul class="nav nav-third-level">
		                                      	 <li><a class="J_menuItem"  href="SelectedTalentProjectFormset!viewSelectedTalentProject">人才工程条目设置</a></li>
												<li><a class="J_menuItem"  href="SelectedTalentProjectScoreset!viewSelectedTalentProjectScore">入选人才工程评分设置</a></li>												
	                                      </ul>
                                    </li>
                                    <li><a class="J_menuItem" href="selfUndertakeTaskset!viewselfUndertakeTask">本人承担任务设置</a></li>
                                </ul>
                            </li>
                             <li>
                             	<a class="J_menuItem" href="">教学设置<span class="fa arrow"></span></a>
                                    	 <ul class="nav nav-third-level">
		                                 		<li><a class="J_menuItem"  href="">课堂教学<span class="fa arrow"></span></a>
				                                    	<ul class="nav nav-third-level">
						                                      	 <li><a class="J_menuItem"  href="TsetClassTeaching_sumtimeratio!putclassteachtimeData">总站堂时系数设置</a></li>
																<li><a class="J_menuItem"  href="TsetClassTeaching_evaluation!putevaluationresult">综合教学评估设置</a></li>
																<li><a class="J_menuItem"  href="TsetGuidencequality_degree!putTFDegreeThesisGuidance_RewardLevel">论文指导系数设置</a></li>
																<li><a class="J_menuItem"  href="TsetTeachingCompetition_competition!putTFTeachingCompetition_RewardLevel">教学竞赛系数设置</a></li>
																<li><a class="J_menuItem"  href="TsetTeachingAbility_improve!putTFTeachingAbilityImprove__Level">教学能力提升设置</a></li>
																<li><a class="J_menuItem"  href="TsetFamousTeacherteam_rewardlevel!putTFFamousTeacherTeam_RewadLevel">优秀教师团队称号系数设置</a></li>												
					                                     </ul>
	                                      		 </li>													
													<li><a class="J_menuItem"  href="">教学研究<span class="fa arrow"></span></a>
				                                    	<ul class="nav nav-third-level">
						                                      	 <li><a class="J_menuItem"  href="TsetTeachingReach_fundlevel!putTFTeaching_rearch_fundlevel">教学研究规模设置</a></li>
																<li><a class="J_menuItem"  href="TsetTeachingReach_evaluation!putTFTeaching_rearch_evaluation">教学研究结果设置</a></li>
																<li><a class="J_menuItem"  href="TsetRetrievalCondition_paper!putTFTeachingPaper_RetrievalCondition">论文发表等级设置</a></li>
																<li><a class="J_menuItem"  href="TsetTeachingachievement_achievement!putTFTeaching_achievement_RewardLevel">教学成就等级设置</a></li>
																<li><a class="J_menuItem"  href="TsetConstructionTBlevel_text!putTFTextbookConstruction_TBlevel">教材编写等级设置</a></li>
																<li><a class="J_menuItem"  href="TsetFineCourseConstruction_course!putTFFineCourseConstruction_Level">精品课程等级设置</a></li>
																<li><a class="J_menuItem"  href="TsetProfessionalProject_declare!putTFProfessionalProjectDeclare_Level">品牌专业等级设置</a></li>
																<li><a class="J_menuItem"  href="TsetEnterpriseWorkstation_construction!putTFEnterpriseWorkstationTrainingbaseConstruction_Level">企业培养基地设置</a></li>
																<li><a class="J_menuItem"  href="TsetSummerCourse_construction!putTFSummerCourseInternationalConstruction_Level">暑期课程设置</a></li>												
					                                     </ul>
	                                      		 </li>													
													<li><a class="J_menuItem"  href="">创新项目<span class="fa arrow"></span></a>
				                                    	<ul class="nav nav-third-level">
						                                      	 <li><a class="J_menuItem"  href="TsetInnovationGuide_level!putTFPracticeInnovationGuide_Level">创新项目等级设置</a></li>
																<li><a class="J_menuItem"  href="TsetInnovationGuide_evaluation!putTFPracticeInnovationGuide_GraduationThesisGuideEvalution">创新项目成绩设置</a></li>
																<li><a class="J_menuItem"  href="">人才工程条目设置</a></li>
																<li><a class="J_menuItem"  href="">人才工程条目设置</a></li>
																<li><a class="J_menuItem"  href="">人才工程条目设置</a></li>
																<li><a class="J_menuItem"  href="">人才工程条目设置</a></li>
																<li><a class="J_menuItem"  href="">人才工程条目设置</a></li>			     	 																	
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
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown hidden-xs">
                            <a class="right-sidebar-toggle" aria-expanded="false">
                                <i class="fa fa-tasks"></i> 主题
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
                <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
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
                    <div id="tab-1" class="tab-pane active">
                        <div class="sidebar-title">
                            <h3> <i class="fa fa-comments-o"></i> 主题设置</h3>
                            <small><i class="fa fa-tim"></i> 你可以从这里选择和预览主题的布局和样式，这些设置会被保存在本地，下次打开的时候会直接应用这些设置。</small>
                        </div>
                        <div class="skin-setttings">
                            <div class="title">主题设置</div>
                            <div class="setings-item">
                                <span>收起左侧菜单</span>
                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="collapsemenu" class="onoffswitch-checkbox" id="collapsemenu">
                                        <label class="onoffswitch-label" for="collapsemenu">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>固定顶部</span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="fixednavbar" class="onoffswitch-checkbox" id="fixednavbar">
                                        <label class="onoffswitch-label" for="fixednavbar">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="setings-item">
                                <span>
                        固定宽度
                    </span>

                                <div class="switch">
                                    <div class="onoffswitch">
                                        <input type="checkbox" name="boxedlayout" class="onoffswitch-checkbox" id="boxedlayout">
                                        <label class="onoffswitch-label" for="boxedlayout">
                                            <span class="onoffswitch-inner"></span>
                                            <span class="onoffswitch-switch"></span>
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="title">皮肤选择</div>
                            <div class="setings-item default-skin nb">
                                <span class="skin-name ">
                         <a href="#" class="s-skin-0">
                             默认皮肤
                         </a>
                    </span>
                            </div>
                            <div class="setings-item blue-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-1">
                            蓝色主题
                        </a>
                    </span>
                            </div>
                            <div class="setings-item yellow-skin nb">
                                <span class="skin-name ">
                        <a href="#" class="s-skin-3">
                            黄色/紫色主题
                        </a>
                    </span>
                            </div>
                        </div>
                    </div>

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
</body>

</html>