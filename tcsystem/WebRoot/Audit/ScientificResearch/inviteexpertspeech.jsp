<?xml version="1.0" encoding="utf-8" ?>
<%@page import="java.util.ArrayList"%>
<%@page import="com.nuaa.ec.science.daoimpl.ResearchLabDAOimpl"%>
<%@page import="com.nuaa.ec.science.model.ScientificResearchExportDataType"%>
<%@page import="com.nuaa.ec.science.model.ScientificResearchReward"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
List<ScientificResearchExportDataType>  SREDTdao = (List)session.getAttribute("expor");
ResearchLabDAOimpl researdao = new ResearchLabDAOimpl(); 
List<String> AuditResult = new ArrayList<String>();
String oint = (String)request.getParameter("oint");
int uu = 4;
int o = 100000;
if(oint!=null)
	o = Integer.parseInt(oint);
%>
<jsp:directive.page import="javax.servlet.http.HttpSession"/>
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
      <script src="js/checkbox.js"></script>
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
        <div class="col-sm-3 col-md-2 sidebar" id="accordion">
          <ul class="nav nav-sidebar">
            <li><font size="5">任务管理</a></font></li>
            <li><a href="gljixiao.jsp">绩效查询</a></li>
            <li><a href="gladdxm.jsp">添加项目</a></li>
            <li><a href="gldeljx.jsp">删除绩效</a></li>
            <li><a href="glupdatejx.jsp">更改绩效</a></li>
          </ul>
          <ul class="nav nav-sidebar" >
            <li><font size="5">审核管理</a></font></li>
           <li><a href="">按教师审核</a><li>
            <li><a font size="5">科研审核</a></font><li>
            <li><a href="makeitdata!executeExportData">&nbsp;&nbsp;&nbsp;&nbsp;生成审核数据</a></li>
            <li><a href="downlaodVIEW!dataView">&nbsp;&nbsp;&nbsp;&nbsp;导出审核数据</a></li>
            <li><a href="ScientificAudit">&nbsp;&nbsp;&nbsp;&nbsp;审核</a></li>
             <li><a font size="5">教学审核</a></font><li>
             <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;生成审核数据</a></li>
             <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;导出审核数据</a></li>
            <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;审核</a></li>
              <li><a font size="5">公益审核</a></font><li>
               <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;导出审核数据</a></li>
            <li><a href="">&nbsp;&nbsp;&nbsp;&nbsp;审核</a></li>
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
            <li><a href="add_Teacher!getsinfo">增加用户</a></li>
             <li><a href="update_Teacher!viewTeacher">修改用户</a></li>
          </ul>

        </div>       
	`		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<h1 class="page-header">审核</h1>
	<form name="ff" method="post">
		<table>
			<tr> 
				<td>研究所:</td>
				<td>
					<select name = "oint">
						<%if(SREDTdao!=null)
									for(int i=0;i<SREDTdao.size();i++){%>
						<option value="<%=i %>">
							<%= SREDTdao.get(i).getResearchLabName()%>
						</option>
						<%} %>
					</select>
				</td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="submit"  value ="更替项目" onclick="javascript:document.ff.action='ScientificAuditbrowse?oints=<%=o %>';document.ff.submit();"/> </td>
			</tr>
		</table>
	</form>
	<hr /><br />
	<a href="">邀请专家讲学审核</a><br /><br />
	<form name="Audit"  action=""  method="post">
		<table class="table table-striped"  style="border-collapse:collapse">
			<tr>
				<td>邀请专家讲学编号</td>				<td>讲学名称</td>
				<td>专家姓名</td>				<td>专家类型</td>
				<td>国家</td>								<td>教师编号</td>				
				<td>教师姓名</td>				<td>本人承担任务</td>				
				<td>最终分数</td>
				<td>全通过&nbsp;<input type = "checkbox"  name =""  id="allCheck" onchange="allAlowOrNot()" /></td>
			</tr>
			<%
			if(o!=100000)
				if(SREDTdao.get(o).getTE()!=null)
					for(int i=0;i<SREDTdao.get(o).getTE().size();i++){ %>	
			<tr>
				<td><%=SREDTdao.get(o).getTE().get(i).getIESpeechID()%></td>
				<td><%=SREDTdao.get(o).getTE().get(i).getLectureName() %></td>
				<td><%=SREDTdao.get(o).getTE().get(i).getExpertsName() %></td>
				<td><%=SREDTdao.get(o).getTE().get(i).getExpertTypeName() %></td>
				<td><%=SREDTdao.get(o).getTE().get(i).getCountryName()%></td>
				<td><%=SREDTdao.get(o).getTE().get(i).getTeacherID() %></td>
				<td><%=SREDTdao.get(o).getTE().get(i).getTeacherNmae() %></td>
				<td><%=SREDTdao.get(o).getTE().get(i).getSelfUndertake() %></td>
				<td><%=SREDTdao.get(o).getTE().get(i).getBaseScore() %></td>
				<td>
				<input type = "checkbox"  name =""  
				id="<%=SREDTdao.get(o).getTE().get(i).getTeacherID()+SREDTdao.get(o).getTE().get(i).getIESpeechID() %>" 
				value="<%=SREDTdao.get(o).getTE().get(i).getTeacherID() %>,<%=SREDTdao.get(o).getTE().get(i).getIESpeechID()%>"
				onchange="nullChange('<%=SREDTdao.get(o).getTE().get(i).getTeacherID()+SREDTdao.get(o).getTE().get(i).getIESpeechID() %>')"/></td>
			</tr>
			<%}%>
		</table>
	</form>
  <input type="submit" value="提交" onclick="changeCheckboxValueAndSubmit('InviteExpertSpeech',<%=o %>);"></input>
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
