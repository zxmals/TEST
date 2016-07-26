<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
   

    <title>管理员界面</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">欢迎你，---</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           
            <li><a href="#">设置</a></li>
            <li><a href="#">注销</a></li>
            <li><a href="#">帮助</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar" id="accordion">
          <ul class="nav nav-sidebar">
            <li><font size="5">表格管理</a></font></li>
            <li><a class="collapsed" data-toggle="collapse" data-parent="#accordion"  href="#shangchuanbiaoge" aria-expanded="false" aria-controls="shangchuanbiaoge">上传表格</a>
			<div id="shangchuanbiaoge" class="collapse">
			  <div class="well">
				<ul class="nav nav-sidebar">
				<li><a href="">新建表格</a></li>
				<li><a href="">已生成表格</a></li>
				</ul>
			  </div>
			</div>
			</li>
			
            <li><a class="collapsed" data-toggle="collapse" data-parent="#accordion"  href="#shengchengbiaoge" aria-expanded="true" aria-controls="shengchuanbiaoge">生成表格</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><font size="5">任务管理</a></font></li>
            <li><a href="gljixiao.jsp">绩效查询</a></li>
            <li><a href="gladdxm.jsp">添加项目</a></li>
            <li><a href="gldeljx.jsp">删除绩效</a></li>
            <li><a href="glupdatejx.jsp">更改绩效</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><font size="5">查询管理</a></font></li>
           
          </ul>
		  <ul class="nav nav-sidebar">
            <li><font size="5">绩效管理</a></font></li>
            <li><a href="Scientificmodelview">科研绩效管理</a></li>
            <li><a href="">教学绩效管理</a></li>
            <li><a href="">公益绩效管理</a></li>
            <li><a href="teacherjx.jsp">教师绩效查询</a></li>
          </ul>
		  <ul class="nav nav-sidebar">
            <li><font size="5">系统设置</a></font></li>
            <li><a href="viewAllScientificModular">科研设置</a></li>
            <li><a href="">公益设置</a></li>
			<li><a href="">教学设置</a></li>
            <li><a href="baseSet!getBaseSetInfo">基础设置</a></li>
          </ul>
		  <ul class="nav nav-sidebar">
            <li><font size="5">用户管理</a></font></li>
            <li><a href="add_Teacher">增加用户</a></li>
             <li><a href="update_Teacher!viewTeacher">修改用户</a></li>
          </ul>

        </div>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<a href="Periodicalset">期刊论文设置</a><br /><br />
	<a href="AcademicWorkset">学术著作设置</a><br /><br />
	<a href="ScientificResearchProjectset">科研项目设置</a><br /><br />
	<a href="ProjectRewardset">项目奖励设置</a><br /><br />
	<a href="JoinAcademicMeetingset">参加学术会议设置</a><br /><br />
	<a href="InviteExpertSpeechset">邀请专家讲学设置</a><br /><br />
	<a href="MainUndertakeAcademicMeetingset">主承办学术会议设置</a><br /><br />
	<a href="selfUndertakeTaskset!viewselfUndertakeTask">本人承担任务设置</a><br /><br />
	<a href="SelectedTalentProjectset">入选人才项目设置</a><br /><br />
	<input type="submit" value="注销" onclick="window.location.href='<%=basePath %>logout!out'"/>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</div>
	

       
          
    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
