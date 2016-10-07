<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<jsp:directive.page import="com.nuaa.ec.teaching.dao.GetAllDAO"/>
<jsp:directive.page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"/>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
     TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
     GetAllDAO gadao = new GetAllDAO();

     List<String> b = gadao.getAllColname("TFTeaching_rearch_fundlevel", "FundLevel");
     List<String> c = gadao.getAllColname("TFTeaching_rearch_evaluation", "Reaults");
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
            <li><a href="../searchjiben.jsp">查看基本信息</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">教学研究</h1>

		  <center>
          <form action="" method="post" name="f">
          <table class="table table-striped"  style="border-collapse:collapse">
			<tr><td>工号</td><td><input type="text" name="TeacherID" value="<%=a.getTeacherID() %>"  readonly="true"></td></tr>
			<tr><td>姓名</td><td><input type="text" name="TeacherName" value="<%=a.getTeacherName() %>" readonly="true"></td></tr>
			<tr><td>ID</td><td><input type="text" name="ProjectID"/></td></tr>
			<tr><td>项目名称</td><td><input type="text" name="Project" /></td></tr>
			<tr><td>项目拨款</td>
			<td><select name="FundLevel"  >
			     <%if (b!=null ){
			     for(int i=0;i<b.size();i++){ %>
					<option value="<%=b.get(i)%>"  selected><%=b.get(i) %></option>
				<%} }%>			
				 </select>
			</td></tr>
			<tr><td>评估结果</td>
			<td><select name="Reaults"  >
			     <%if (c!=null ){
			     for(int i=0;i<c.size();i++){ %>
					<option value="<%=c.get(i)%>"  selected><%=c.get(i) %></option>
				<%} }%>			
				 </select>
			</td></tr>

			<tr><td>操作</td><td><input type="submit" value="提交" onclick="javascript:document.f.action='AddTeachingResearch!addTeachingResearch';document.f.submit();"></input></td></tr>
	</table>
	</form>
	</center>
	<s:debug></s:debug>

</body>
</html>