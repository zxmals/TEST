<%@page import="com.nuaa.ec.science.model.ResearchLab"%>
<%@page import="com.nuaa.ec.science.model.Department"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" import="com.nuaa.ec.science.Permodel.AddTeacherinfo"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<AddTeacherinfo> teach = (List)session.getAttribute("model");
	List<Department> depart = (List)session.getAttribute("departmentinfo");
	List<ResearchLab> research = (List)session.getAttribute("researchlabinfo");
	String s = (String)request.getAttribute("delete");
	String Message = "修改教师信息，点击继续->>>>";
	if(s=="1")
		Message = "删除成功";
	else
		if(s=="0")
			Message = "删除失败！";
	String uudate = (String)request.getAttribute("i");
	int uodate = 0;
	if(uudate!=null)
		uodate = Integer.parseInt(uudate);
	String update = (String)request.getAttribute("update");
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
            <li><a href="ViewTeacherPerformance">科研绩效管理</a></li>
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
          <h1 class="page-header">修改教师信息</h1>
          <center>
  <input type="submit" value="back" onclick="javascript:document.f.action='back';document.f.submit();">
   <form action="" name="f" method="post">
   		  <table class="table table-striped"  style="border-collapse:collapse">
   			<tr>
   				<td>&nbsp;教师ID&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;姓名&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;&nbsp;&nbsp;所属系&nbsp;&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;&nbsp;所属研究所&nbsp;&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;&nbsp;身份&nbsp;&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;&nbsp;操作&nbsp;&nbsp;&nbsp;</td>
   			</tr>
   			<%for(int i=0;i<teach.size();i++){ %>
   			<tr>
   			<td name="TeacherID"><%=teach.get(i).getTeacherID() %></td>
   				<td><%=teach.get(i).getTeacherName() %></td>
   				<td><%=teach.get(i).getDepartment() %></td>
   				<td><%=teach.get(i).getResearchLab() %></td>
   				<td><%=teach.get(i).getLevel() %></td>
   				<% if(teach.get(i).getLevel()=="管理员教师"){%>
   				<td>&nbsp;<input type="submit" value="删除" disabled="true" >&nbsp;
   				<input type="button" value="修改" disabled="true"></td>
   				<%}else{ %>
   				<td>&nbsp;<input type="button" value="删除" onclick="javascript:document.f.action='update_Teacher!delete?TeacherID=<%=teach.get(i).getTeacherID() %>';document.f.submit();" >&nbsp;
   				<input type="button" value="修改" onClick="javascript:document.f.action='exeUpdate!update?i=<%=i %>&preteacher.TeacherID=<%=teach.get(i).getTeacherID() %>';document.f.submit();"></td>
   				<%} %>
   			</tr>
   			<% }%>
   		</table>
   		<% 
   			if(update!=null)
   				if(update.equals("1")){ %>
   			 <table class="table table-striped"  style="border-collapse:collapse">
   			<tr>
   				<td>&nbsp;教师ID&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;姓名&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;&nbsp;&nbsp;所属系&nbsp;&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;&nbsp;所属研究所&nbsp;&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;&nbsp;身份&nbsp;&nbsp;&nbsp;</td>
   				<td>&nbsp;&nbsp;&nbsp;操作&nbsp;&nbsp;&nbsp;</td>
   			</tr>
   			<td><%=teach.get(uodate).getTeacherID()%></td>
   			<td><input type="text" name="TeacherName" value="<%=teach.get(uodate).getTeacherName() %>"></td>
   			<td>
   			<select name="Department">
		<%if(depart!=null)for(int i=0;i<depart.size();i++){ %>
  <option
  	<% if(depart.get(i).getDepartmentName().equals(teach.get(uodate).getDepartment())){ %>selected="selected"<%} %>><%=depart.get(i).getDepartmentName()%></option>
  <%} %>
</select></td>
   			<td>
   			<select name="ResearchLab">
				<%if(research!=null)for(int i=0;i<research.size();i++){ %>
  <option
  	<% if(research.get(i).getInstituteName().equals(teach.get(uodate).getResearchLab())){ %>selected="selected"<%} %>><%=research.get(i).getInstituteName()%></option>
  <%} %>
			</select>
   			</td>
   			</td>
   			<td>
   			<select name="Level">
   			 <option >普通教师</option>
   			  <option >管理员教师</option>
   			</select>
   			</td>
   			<td><input type="button" value="提交" onclick="javascript:document.f.action='exeUpdate!exeUpdate?TeacherID=<%=teach.get(uodate).getTeacherID() %>';document.f.submit();" ></td></td>
   		</table>
   		<%} %>
   </form>
   </center>
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
