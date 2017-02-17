<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>



<!DOCTYPE html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <title>NUAA-CEM绩效管理系统 - 主页</title>

    
    <!--[if lt IE 8]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico">
    <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet">    
     <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
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
    </script>
</head>

<body onload="checkID()" class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <span><img alt="image" class="img-circle" src=""  /></span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">${teacher.teacherName }</strong></span>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="GTGetInfo!getPersonalInfo">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="${pageContext.request.contextPath }/personalInfoManager/alterPassword.jsp">修改密码</a>
                                </li>
                                <li class="divider"></li>
                                <li><a href="logout!out">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">bamanker</div>
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
                                	<span class="nav-label">科研绩效管理</span>
                                	<span class="fa arrow"></span>
                                </a>
                                <ul class="nav nav-second-level">
		                            <li>
		                                <a href="#">
		                                	<i class="fa fa fa-bar-chart-o"></i>
		                                	<span class="nav-label">期刊论文</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTperiodicalpaper-paperset!getPeriodicalPaperINF?currentrow=0">论文管理</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="GTperiodicalpaper-personset!getPersonalJoinC?pagenum=1&">个人参与管理</a>
				                            </li>
				                        </ul>
		                            </li>
		                            <li>
		                            	<a href="#">
		                                	<i class="fa fa fa-bar-chart-o"></i>
		                                	<span class="nav-label">学术著作</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTacademicwork-workset!getWorkall">著作管理</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="GTacademicwork-personset!getPersonJoin">个人参与管理</a>
				                            </li>
				                        </ul>
		                            </li>
		                            <li>
		                            	<a href="#">
		                                	<i class="fa fa fa-bar-chart-o"></i>
		                                	<span class="nav-label">科研项目奖励</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTscienceresearch-rewardset!gainscienceReward">科研奖励管理</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="GTscienceresearch-personset!getPersonJoin">个人参与管理</a>
				                            </li>
				                        </ul>
		                            </li>
		                            <li>
		                            	<a href="#">
		                                	<i class="fa fa fa-bar-chart-o"></i>
		                                	<span class="nav-label">参加学术会议</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTjoinacademicmeeting-meetingset!gainAllJoinacademic">学术会议管理</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="GTjoinacademicmeeting-personset!getPersonJoin">个人参与管理</a>
				                            </li>
				                        </ul>
		                            </li>
		                            <li>
		                            	<a href="#">
		                                	<i class="fa fa fa-bar-chart-o"></i>
		                                	<span class="nav-label">邀请专家讲学</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTinviteexpertspeech-speechset!gainAllspeech">专家讲座管理</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="GTinviteexpertspeech-personset!getPersonJoin">个人参与管理</a>
				                            </li>
				                        </ul>
		                            </li>
		                            <li>
		                            	<a href="#">
		                                	<i class="fa fa fa-bar-chart-o"></i>
		                                	<span class="nav-label">主承办学术会议</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTundertakeacademicmeet-meetset!gainAllacademicm">学术会议管理</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="GTundertakeacademicmeet-personset!getPersonJoin">个人参与管理</a>
				                            </li>
				                        </ul>
		                            </li>
		                            <li>
		                            	<a href="#">
		                                	<i class="fa fa fa-bar-chart-o"></i>
		                                	<span class="nav-label">入选人才工程</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTselectedtalentprojec-talentset!gainAlltalentprojec">人才工程管理</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="GTselectedtalentprojec-personset!getPersonJoin">个人参与管理</a>
				                            </li>
				                        </ul>
		                            </li>
		                            <li>
		                            	<a href="#">
		                                	<i class="fa fa fa-bar-chart-o"></i>
		                                	<span class="nav-label">科研项目</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTscientificresearchpro-projectset!gainAllscienpro">科研项目管理</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="GTscientificresearchpro-personset!getPersonJoin">个人参与管理</a>
				                            </li>
				                        </ul>
		                            </li>
		                        </ul>
                            </li>
                            <li>
                                <a href="#">
									<i class="fa fa fa-bar-chart-o"></i>
									<span class="nav-label">教学绩效管理 </span>
									<span class="fa arrow"></span>
								</a>
                                <ul class="nav nav-second-level">
									<li>
										<a href="#">
											<i class="fa fa fa-bar-chart-o"></i>
											<span class="nav-label">教学能力与实效 </span>
											<span class="fa arrow"></span>
										</a>
										<ul class="nav nav-second-level firstslide">
											<li><a class="J_menuItem" href="GTClassTeachPerformanceSet!getAllRecord">课堂教学绩效</a></li>
											<li><a class="J_menuItem" href="GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord">学位论文指导质量绩效</a></li>
											<li><a class="J_menuItem" href="GTTeachingCompetitionPerformanceSet!getAllRecordOfCurrentTeacher">教学竞赛绩效</a></li>
											<li><a class="J_menuItem" href="GTTeachingAbilityImprovePerformanceSet!getAllRecord">教学能力提升绩效</a></li>
											<li>
												<a href="#">
													<i class="fa fa fa-bar-chart-o"></i>
													<span class="nav-label">教学名师和教学团队绩效 </span>
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-second-level">
												<li><a class="J_menuItem" href="GTteachingfamousteamPerformanceSet-project!gainAllProject">团队项目设置</a></li>
													<li><a class="J_menuItem" href="GTteachingfamousteamPerformanceSet-person!getPersonJoin">个人参与设置</a></li>
												</ul>
											</li>
										</ul>
									</li>
									<li>
										<a href="#">
											<i class="fa fa fa-bar-chart-o"></i>
											<span class="nav-label">综合改革与教学研究 </span>
											<span class="fa arrow"></span>
										</a>
										<ul class="nav nav-second-level firstslide">
											<li>
												<a href="#">
													<i class="fa fa fa-bar-chart-o"></i>
													<span class="nav-label">教学研究绩效 </span>
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-second-level">
													<li><a class="J_menuItem" href="GTteachingresearchPerformanceSet-project!gainAllProject">研究项目设置</a></li>
													<li><a class="J_menuItem" href="GTteachingresearchPerformanceSet-person!getPersonJoin">个人参与设置</a></li>
												</ul>
											</li>
											<li>
												<a href="#">
													<i class="fa fa fa-bar-chart-o"></i>
													<span class="nav-label">教学论文绩效 </span>
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-second-level">
													<li><a class="J_menuItem" href="GTteachingpaperPerformanceSet-project!gainAllProject">教学论文设置</a></li>
													<li><a class="J_menuItem" href="GTteachingpaperPerformanceSet-person!getPersonJoin">个人参与设置</a></li>
												</ul>
											</li>
											<li>
												<a href="#">
													<i class="fa fa fa-bar-chart-o"></i>
													<span class="nav-label">教学成果奖绩效 </span>
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-second-level">
													<li><a class="J_menuItem" href="GTteachingachievePerformanceSet-project!gainAllProject">教学成果设置</a></li>
													<li><a class="J_menuItem" href="GTteachingachievePerformanceSet-person!getPersonJoin">个人参与设置</a></li>
												</ul>
											</li>
											<li>
												<a href="#">
													<i class="fa fa fa-bar-chart-o"></i>
													<span class="nav-label">教材建设绩效 </span>
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-second-level">
													<li><a class="J_menuItem" href="GTteachingtextbookPerformanceSet-project!gainAllProject">教材建设设置</a></li>
													<li><a class="J_menuItem" href="GTteachingtextbookPerformanceSet-person!getPersonJoin">个人参与设置</a></li>
												</ul>
											</li>
											<li>
												<a href="#">
													<i class="fa fa fa-bar-chart-o"></i>
													<span class="nav-label">精品课程绩效 </span>
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-second-level">
													<li><a class="J_menuItem" href="GTteachingfinecoursePerformanceSet-project!gainAllProject">精品课程设置</a></li>
													<li><a class="J_menuItem" href="GTteachingfinecoursePerformanceSet-person!getPersonJoin">个人参与设置</a></li>
												</ul>
											</li>
											<li>
												<a href="#">
													<i class="fa fa fa-bar-chart-o"></i>
													<span class="nav-label">专业建设项目申报绩效 </span>
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-second-level">
													<li><a class="J_menuItem" href="GTteachingprofeprojePerformanceSet-project!gainAllProject">专业建设与项目申报设置</a></li>
													<li><a class="J_menuItem" href="GTteachingprofeprojePerformanceSet-person!getPersonJoin">个人参与设置</a></li>
												</ul>
											</li>
											<li>
												<a href="#">
													<i class="fa fa fa-bar-chart-o"></i>
													<span class="nav-label">企业工作站和联合培养基地建设绩效 </span>
													<span class="fa arrow"></span>
												</a>
												<ul class="nav nav-second-level">
													<li><a class="J_menuItem" href="GTteachingfirmbaseconPerformanceSet-project!gainAllProject">企业工作站与联合培养基地设置</a></li>
													<li><a class="J_menuItem" href="GTteachingfirmbaseconPerformanceSet-person!getPersonJoin">个人参与设置</a></li>
												</ul>
											</li>
											<li><a class="J_menuItem" href="GTSummerCourseInternationalConstructionPerformanceSet!getAllRecord">暑期课程与国际课程建设绩效</a></li>
										</ul>
									</li>
									<li>
										<a href="#">
											<i class="fa fa fa-bar-chart-o"></i>
											<span class="nav-label">学生指导工作 </span>
											<span class="fa arrow"></span>
										</a>
										<ul class="nav nav-second-level firstslide">
											<li><a class="J_menuItem" href="GTPracticeInnovationGuidePerformanceSet!getAllRecord">实践创新指导绩效</a></li>
											<li><a class="J_menuItem" href="GTStudentCompetitionGuidancePerformanceSet!getAllRecord">学生竞赛指导绩效</a></li>
											<li><a class="J_menuItem" href="GTJoinStudentActivityPerformanceSet!getAllRecord">参与学生活动绩效</a></li>
											<li><a class="J_menuItem" href="GTUndergraduateTutorGuidancePerformanceSet!getAllRecord">本科生导师指导绩效</a></li>
											<li><a class="J_menuItem" href="GTOffCampusPracticeGuidancePerformanceSet!getAllRecord">校外实践指导绩效</a></li>
										</ul>
									</li>
								</ul>
                            </li>
                            <li>
<!--                                 <a class="J_menuItem" href="VA/act_summary.jsp">公益绩效管理 </a> -->
                                <a href="#">
									<i class="fa fa fa-bar-chart-o"></i>
									<span class="nav-label">公益绩效管理 </span>
									<span class="fa arrow"></span>
								</a>
								<ul class="nav nav-third-level">
									<li><a class="J_menuItem" href="VA/act_summary.jsp">公益绩效管理</a></li>
								</ul>
                            </li>
                   	   <li id="resAdmin"  style="display: none">
	                   	    <a href="#">
	                   	    	<i class="fa fa-edit"></i> 
		                   	    <span class="nav-label">所审核</span>
		                   	    <span class="fa arrow"></span>
	                   	    </a>
                   	   	 	<ul class="nav nav-second-level firstslide">
								<li><a class="J_menuItem" href="GTScientificResearchProjectAudit_project!getAllRecord_project">科研项目审核</a></li>
								<li><a class="J_menuItem" href="GTScientificResearchRewardAudit_project!getAllRecord">科研项目奖励审核</a></li>
                               	<li><a class="J_menuItem" href="GTSelectedTalentProjectAudit_project!getAllRecord">入选人才项目审核</a></li>
                               	<li><a class="J_menuItem" href="GTPeriodicalPaperAudit_project!getAllRecord">期刊论文审核</a></li>
                               	<li><a class="J_menuItem" href="GTMainUndertakeAcademicMeetingAudit_project!getAllRecord">承担学术会议审核</a></li>
                               	<li><a class="J_menuItem" href="GTJoinAcademicMeetingAudit_project!getAllRecord">参加学术会议审核</a></li>
                               	<li><a class="J_menuItem" href="GTInvitedExpertsSpeechAudit_project!getAllRecord">邀请专家讲座审核</a></li>
                               	<li><a class="J_menuItem" href="GTAcademicWorkAudit_project!getAllRecord">学术著作审核</a></li>
                      	   </ul>
                   	    </li>
                   	    
                       <li id="departAdmin"  style="display: none"> 
                       		<a href="#">
                            	<i class="fa fa-edit"></i>
                            	<span class="nav-label">系审核</span>
                            	<span class="fa arrow"></span>
                            </a>
                            <ul class="nav nav-second-level firstslide">
	                            <li>
	                            	<a class="J_menuItem" href="GTTFfamousTeacherTeam_projectAudit11!getAllRecord_project">教学名师与教学团队审核</a>
								</li>
								<li>
	                            	<a class="J_menuItem" href="GTTFteachingresearch_projectAudit11!getAllRecord_project">教学研究审核</a>
								</li>
								<li>
	                            	<a class="J_menuItem" href="GTTFteachingpaper_projectAudit11!getAllRecord_project">教学论文审核</a>
								</li>
								<li>
	                            	<a class="J_menuItem" href="GTTFteachingachievement_projectAudit11!getAllRecord_project">教学成果奖审核</a>
								</li>
								<li>
	                            	<a class="J_menuItem" href="GTTFteachingtextbook_projectAudit11!getAllRecord_project">教材建设审核</a>
								</li>
								<li>
	                            	<a class="J_menuItem" href="GTTFteachingfinecourse_projectAudit11!getAllRecord_project">精品课程审核</a>
								</li>
								<li>
	                            	<a class="J_menuItem" href="GTTFteachingprofessional_projectAudit11!getAllRecord_project">专业建设项目申报审核</a>
								</li>
								<li>
	                            	<a class="J_menuItem" href="GTTFteachingfirmbasecon_projectAudit11!getAllRecord_project">企业工作站和联合培养基地建设审核</a>
								</li>
							</ul>
                       </li>
                       
                       <li id="vaadmin"  style="display: none">
                           	<a href="#">
                            	<i class="fa fa-edit"></i>
                            	<span class="nav-label">公益审核</span>
                            	<span class="fa arrow"></span>
                            </a>
                             <ul class="nav nav-second-level firstslide">
	                            <li>
	                                <a class="J_menuItem" href="GTAddJoinedActAudit!getAddJoinedActList">活动加入审核</a>
	                            </li>
	                            <li>
	                                <a class="J_menuItem" href="GTUnjoinedActAudit!getUnjoinedActList">活动缺席审核</a>
	                            </li>
	                            <li>
	                                <a class="J_menuItem" href="GTNewActPublishActAudit!getNewActPublishList">活动发布审核</a>
	                            </li>
	                            <li>
	                                <a class="J_menuItem" href="GTNewActApplyAudit!getNewActApplyList">活动新增申请审核</a>
	                            </li>
                      	   </ul>
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
                <a href="login.jsp" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
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


            </div>
        </div>
        <!--右侧边栏结束-->
        
    </div>
    
    
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/jquery.dataTables.js"></script>
      <script src="js/content.min.js?v=1.0.0"></script>
      <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
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
		});
    </script>
</body>

</html>