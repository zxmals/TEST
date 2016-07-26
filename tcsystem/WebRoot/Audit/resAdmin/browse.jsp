<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"  %>
<%@taglib uri="/struts-tags" prefix="s" %>     
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%int i = 0; 
String oint = (String)request.getParameter("oints");
int uu = 4;
int o = 0;
if(oint!=null)
	o = Integer.parseInt(oint);
%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="_self">
  <script src="js/TeachAuditCheckbox.js"></script>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <meta name="description" content="">
    <meta name="author" content="">
   

    <title>科研审核导航页</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script type="text/javascript">
    	function DoCheck() {
			var selectedcheck = <%=o %>;
			var obj = document.getElementById("ResearchLabSelect");
			for(var i=0;i<obj.options.length;i++){
				if(selectedcheck==obj.options[i].value)
					obj.options[i].selected = true;
			}
			var res = '${c }';
			switch (res) {
			case '0':
				alert("抱歉！审核失败。");
				break;		
			case '1':
				alert("恭喜！审核成功。");
				break;		
			default:
				break;
			}
		}
    </script>    
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
<!-- col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main -->
  <body onload="DoCheck()">      
	`		<div class="col-sm-9 main">
	<h1 class="page-header">科研审核</h1>	<br />
		<div id="case0">
			<a onclick="replacese('ScientificAuditperiodical')" >期刊论文审核</a><br /><br />
			<a onclick="replacese('ScientificAuditacademicwork')" >学术著作审核</a><br /><br />
			<a  onclick="replacese('ScientificAuditscienresearchproject')" >科研项目审核</a><br /><br />
			<a onclick="replacese('ScientificAuditprojectreward')" >项目奖励审核</a><br /><br /> 
			<a onclick="replacese('ScientificAuditjoinacameeting')" >参加学术会议审核</a><br /><br /> 
			<a onclick="replacese('ScientificAuditinviteexpertspeech')" >邀请专家讲学审核</a><br /><br /> 
			<a onclick="replacese('ScientificAuditmainundertakeacameeting')" >主承办学术会议审核</a><br /><br /> 
			<a onclick="replacese('ScientificAuditselectedtalentproject')" >入选人才项目审核</a><br /><br /> 
		</div>

		<input type="submit" value="注销" onclick="window.location.href='logout!out'"/>
	<input type="submit" value="test" onclick="test()"/>
	<s:debug></s:debug>
	</div>
     <script type="text/javascript">
     	function replacese(action) {
			window.location.replace(action+"?oint="+document.getElementById("ResearchLabSelect").value)
		}
     </script>

    <!-- Bootstrap core JavaScript ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="/js/jquery.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
    <!-- Just to make our placeholder images work. Don't actually copy the next line! -->
    <script src="/js/vendor/holder.min.js"></script>
    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</html>
