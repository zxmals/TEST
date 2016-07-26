<%@page import="com.nuaa.ec.science.model.dataExportRecord"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"%>
    <%
TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
List<dataExportRecord> derlist = (List)request.getAttribute("viewdata");
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
      <script type="text/javascript">
      function makeitdata(a) {
    	  if(a==1)
    	  	window.location.replace("makeitdata!executeExportDataScience");
    	  else
    		  if(a==2)
    			  window.location.replace("makeitdata!executeExportDataTeach");
    		  else
    			  window.location.replace("makeitdata!executeExportDataScience");
      }
      </script>
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
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          <h1 class="page-header">数据导出</h1>
        <input type="button" value = "生成科研审核数据"  onclick="makeitdata('1')">&nbsp;&nbsp;<input type="button" value = "生成教学审核数据"  onclick="makeitdata('2')"><br />
    DataExport. <br>
    <form name = "f"  method = "post">
    <table class="table table-striped"  style="border-collapse:collapse">
    <tr>
    	<td>文件名称</td>
    	<td>&nbsp;&nbsp;&nbsp;&nbsp;生成时间</td>
    </tr>
    	<% if(derlist!=null)
    			for(int i=0;i<derlist.size();i++) {%>
    	 <tr>
    	<td><input type="submit"  onclick="javascript:document.f.action='download?filename=<%=derlist.get(i).getFileName() %>.xls';document.f.submit();"  value = "<%=derlist.get(i).getFileName() %>.xls（点击下载）" style="background-color:white ;color:#5599FF" ></td>
    	<td>&nbsp;&nbsp;&nbsp;&nbsp;<%=derlist.get(i).getExportDate() %> </td>
    	</tr>
    	<%} %>
    	</table>
    	</form>
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

