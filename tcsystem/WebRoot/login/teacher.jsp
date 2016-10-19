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
                                <li><a class="J_menuItem" href="searchjiben.jsp">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="xiugaimima.jsp">修改密码</a>
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
		                                <a class="J_menuItem" href="doPerformance_tf.jsp">邀请专家讲学</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="VA/act_summary.jsp">主承办学术会议</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="doPerformance_tf.jsp">入选人才工程</a>
		                            </li>
		                            <li>
		                                <a class="J_menuItem" href="doPerformance_tf.jsp">科研项目</a>
		                            </li>
		                        </ul>
                            </li>
                            <li>
                                <a class="J_menuItem" href="doPerformance_tf.jsp">教学绩效管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="VA/act_summary.jsp">公益绩效管理 </a>
                            </li>
                        </ul>
                    </li>
                    <li id="resAdmin"  style="display: none"> <a  class="J_menuItem"   href="#"><i class="fa fa-edit"></i> <span class="nav-label">所审核</span></a> </li>
                       <li id="departAdmin"  style="display: none"> <a  class="J_menuItem"   href="#"><i class="fa fa-edit"></i> <span class="nav-label">系审核</span></a> </li>
                       <li id="vaadmin"  style="display: none">
		                            	<a href="#">
		                                	<i class="fa fa-edit"></i>
		                                	<span class="nav-label">公益审核</span>
		                                	<span class="fa arrow"></span>
		                                </a>
		                                <ul class="nav nav-second-level firstslide">
				                            <li>
				                                <a class="J_menuItem" href="GTAddJoinedActAudit!getAddJoinActToBeAudited">活动加入审核</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="#">活动缺席审核</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="#">活动发布审核</a>
				                            </li>
				                            <li>
				                                <a class="J_menuItem" href="#">活动新增申请审核</a>
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