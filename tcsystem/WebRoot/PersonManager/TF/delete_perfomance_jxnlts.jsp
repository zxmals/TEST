
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:directive.page import="com.nuaa.ec.teaching.dao.TeachingAbilityImprove_PerformanceDAO"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"/>
<jsp:directive.page import="com.nuaa.ec.teaching.model.TFTeachingAbilityImprove_Performance"/>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
<jsp:directive.page import="java.util.*"/>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
   TeachingAbilityImprove_PerformanceDAO tcpdao = new TeachingAbilityImprove_PerformanceDAO();
     List<TFTeachingAbilityImprove_Performance> f = tcpdao.getAllInfoTeachingAbilityImprove(a.getTeacherID());
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
    <link href="../css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="../css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script src="../js/ie-emulation-modes-warning.js"></script>

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
            <li><a href="../jixiaosearch.jsp">绩效操作</a></li>
            <li><a href="../tianjia.jsp">添加项目</a></li>

          </ul>
          <ul class="nav nav-sidebar">
            <li><font size="5">基本信息管理</a></font></li>
            <li><a href="../xiugaimima.jsp">修改密码</a></li>
            <li><a href="../searchjiben.jsp">完善基本信息</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">教学能力提升</h1>
  <center>
      <br><br>
            <form action="" method="post" name="f">
     <table class="table table-striped"  style="border-collapse:collapse">
		<tr>
		   
	<td>工号</td>
			<td>姓名</td>
			<td>活动名称</td>
			<td>活动类别</td>
			<td>得分</td>
			<td>操作</td>
			
		</tr>
		<%if(f!=null)for(int i=0;i<f.size();i++){ %>
			<tr>
			<td><%=f.get(i).getTeacherID() %></td>
			<td><%=a.getTeacherName() %></td>
			<td><%=f.get(i).getEventName()%></td>
			<td><%=f.get(i).getImprove_level()%></td>
			<td><%=f.get(i).getFinalScore()%></td>
				<td><input type="submit" value="删除" onclick="javascript:document.f.action='AddTeachingAbilityImprove!delTeachingAbilityImprove?EventID=<%=f.get(i).getEventID()%>';document.f.submit();"></input></td>
			</tr>
		<%} %>
	</table>
	</center>
  </body>
</html>