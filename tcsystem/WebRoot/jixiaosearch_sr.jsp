<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" import="com.nuaa.ec.science.daoimpl.TeacherANDPeriodicalDAOimpl"%>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"/>
<jsp:directive.page import="java.text.DateFormat"/>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
%>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
   

    <title>教师管理界面</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="js/ie-emulation-modes-warning.js"></script>

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
          <a class="navbar-brand" href="#">欢迎你，<%=a.getTeacherName()%></a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
           
               <li><a href="#">设置</a></li>
            <li><a href="logout!out">注销</a></li>
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
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><font size="5">任务管理</a></font></li>
            <li><a href="teacher.jsp">待完成任务</a></li>
            <li><a href="finished.html">已完成任务</a></li>
            <li><a href="fachu.html">发出任务</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><font size="5">个人绩效管理</a></font></li>
            <li><a href="jixiaosearch.jsp">绩效操作</a></li>
            <li><a href="tianjia.jsp">添加项目</a></li>
          </ul>
          <ul class="nav nav-sidebar">
            <li><font size="5">基本信息管理</a></font></li>
            <li><a href="xiugaimima.jsp">修改密码</a></li>
            <li><a href="searchjiben.jsp">查询基本信息</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">科研绩效操作</h1>
 
    <center>
	<form action="" method="post" name="f">
		 <div class="table-responsive">
          <table class="table table-striped"  style="border-collapse:collapse">
		<tr><td>期刊论文:</td><td>
          <input type="button" name="TeacherID" value="查看"
                 onclick="document.f.action='<%=basePath %>PersonManager/queryPerfomance_qklw';document.f.submit();"/>
          <input type="button" name="TeacherID" value="修改"
                 onclick="document.f.action='<%=basePath %>PersonManager/updatePerformance_qklw';document.f.submit();"/>
          <input type="button" name="TeacherID" value="删除"
                 onclick="document.f.action='<%=basePath %>PersonManager/deletePerformance_qklw';document.f.submit();"/>
		</td></tr>
		<tr><td>学术著作:</td><td>
          <input type="button" name="TeacherID" value="查看"
                 onclick="document.f.action='<%=basePath %>PersonManager/queryPerfomance_xszz';document.f.submit();"/>
          <input type="button" name="TeacherID" value="修改"
                 onclick="document.f.action='<%=basePath %>PersonManager/updatePerformance_xszz';document.f.submit();"/>
          <input type="button" name="TeacherID" value="删除"
                 onclick="document.f.action='<%=basePath %>PersonManager/deletePerformance_xszz';document.f.submit();"/>
		</td></tr>
		<tr><td>科研项目:</td><td>
          <input type="button" name="TeacherID" value="查看"
                 onclick="document.f.action='<%=basePath %>PersonManager/queryPerfomance_kyxm';document.f.submit();"/>
          <input type="button" name="TeacherID" value="修改"
                 onclick="document.f.action='<%=basePath %>PersonManager/updatePerformance_kyxm';document.f.submit();"/>
          <input type="button" name="TeacherID" value="删除"
                 onclick="document.f.action='<%=basePath %>PersonManager/deletePerformance_kyxm';document.f.submit();"/>
		</td></tr>
		<tr><td>项目奖励:</td><td>
          <input type="button" name="TeacherID" value="查看"
                 onclick="document.f.action='<%=basePath %>PersonManager/queryPerfomance_xmjl';document.f.submit();"/>
          <input type="button" name="TeacherID" value="修改"
                 onclick="document.f.action='<%=basePath %>PersonManager/updatePerformance_xmjl';document.f.submit();"/>
          <input type="button" name="TeacherID" value="删除"
                 onclick="document.f.action='<%=basePath %>PersonManager/deletePerformance_xmjl';document.f.submit();"/>
		</td></tr>
		<tr><td>参加学术会议:</td><td>
          <input type="button" name="TeacherID" value="查看"
                 onclick="document.f.action='<%=basePath %>PersonManager/queryPerfomance_cjxshy';document.f.submit();"/>
          <input type="button" name="TeacherID" value="修改"
                 onclick="document.f.action='<%=basePath %>PersonManager/updatePerformance_cjxshy';document.f.submit();"/>
          <input type="button" name="TeacherID" value="删除"
                 onclick="document.f.action='<%=basePath %>PersonManager/deletePerformance_cjxshy';document.f.submit();"/>
		</td></tr>
		<tr><td>邀请专家讲学:</td><td>
          <input type="button" name="TeacherID" value="查看"
                 onclick="document.f.action='<%=basePath %>PersonManager/queryPerfomance_yqzjjx';document.f.submit();"/>
          <input type="button" name="TeacherID" value="修改"
                 onclick="document.f.action='<%=basePath %>PersonManager/updatePerformance_yqzjjx';document.f.submit();"/>
          <input type="button" name="TeacherID" value="删除"
                 onclick="document.f.action='<%=basePath %>PersonManager/deletePerformance_yqzjjx';document.f.submit();"/>
		</td></tr>
		<tr><td>主承办学术会议:</td><td>
          <input type="button" name="TeacherID" value="查看"
                 onclick="document.f.action='<%=basePath %>PersonManager/queryPerfomance_zcbxshy';document.f.submit();"/>
          <input type="submit" name="TeacherID" value="修改"
                 onclick="document.f.action='<%=basePath %>PersonManager/updatePerformance_zcbxshy';document.f.submit();"/>
          <input type="button" name="TeacherID" value="删除"
                 onclick="document.f.action='<%=basePath %>PersonManager/deletePerformance_zcbxshy';document.f.submit();"/>
		</td></tr>
		<tr><td>入选人材工程:</td><td>
          <input type="button" name="TeacherID" value="查看"
                 onclick="document.f.action='<%=basePath %>PersonManager/queryPerfomance_rxrcgc';document.f.submit();"/>
          <input type="button" name="TeacherID" value="修改"
                 onclick="document.f.action='<%=basePath %>PersonManager/updatePerformance_rxrcgc';document.f.submit();"/>
          <input type="button" name="TeacherID" value="删除"
                 onclick="document.f.action='<%=basePath %>PersonManager/deletePerformance_rxrcgc';document.f.submit();"/>
		</table>
	</form>	
	</center>
   </div>
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
