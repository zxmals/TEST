<?xml version="1.0" encoding="utf-8" ?>
<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
	+ request.getServerName() + ":" + request.getServerPort()
	+ path + "/";
	request.setAttribute("researchLabList", StoreData.getResearchLabList());
%>
<jsp:directive.page import="javax.servlet.http.HttpSession" />
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base target="_self">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<title>管理员界面</title>

<!-- Bootstrap core CSS -->
<!-- 自己copy的CSS样式 -->
<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

	<!-- Sweet Alert -->
<link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

	<!-- Data Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_blank">
<link href="css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/zxma.css">
<link rel="stylesheet" type="text/css"
	href="css/Audit_CSS/scientific.css">
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

<body style="padding-top:0px;margin-top:0px;">
	<!-- <h1 class="page-header" style="margin-top:0px;">审核</h1> -->
	<form action="ScientificResearchProjectAudit!getSRPToBeAudited"
				method="post" name="pickdate">
	<div class="datepick" style="font-size:12px;">
		<span>选择日期范围</span>
		<div>
			
				从:<input type="text" id="date1" class="Wdate"
					onClick="WdatePicker()" value="${sessionScope.foredate }" name="foredate"
					id="foredate" />到:<input type="text" id="date2"
					onClick="WdatePicker()" class="Wdate" value="${sessionScope.afterdate }"
					name="afterdate" id="afterdate" /> &nbsp;&nbsp;<input
					type="submit" id="datep" value="查询" title="点击查询">
		</div>
	</div>
	<h3 style="padding:0px;margin-left: 10px;">科研项目审核</h3>
	<hr>
		<span style="margin-left:10px;">研究所：&nbsp;&nbsp;&nbsp;&nbsp;</span> 
		<span> 
			<select name="researchLab.researchLabId" id="reserchLabSelection">
				<c:forEach var="researchLab" items="${researchLabList }">
					<option value="${researchLab.researchLabId }">${researchLab.researchLabName }</option>
				</c:forEach>
			</select>
		</span>&nbsp;&nbsp;&nbsp;&nbsp;
	 	<span>每页显示：
			<select name="pageSize" id="pageSizeSelection">
				<option value="1" >&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;</option>
				<option value="2">&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;</option>
				<option value="10" >&nbsp;&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;</option>
				<option value="20" >&nbsp;&nbsp;&nbsp;20&nbsp;&nbsp;&nbsp;</option>
				<option value="30" >&nbsp;&nbsp;&nbsp;30&nbsp;&nbsp;&nbsp;</option>
			</select> 条记录
		</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;
			审核状态：
			<select name="checkout" id="checkoutStatus">
				<option value="0">未审核</option>
				<option value="1">审核通过</option>
				<option value="2">未通过审核</option>
			</select>
		</span>
		<span style="margin-left:15px;"><button type="submit" class="button_set" style="height:25px;">确认</button></span>
	</form>
	<hr />
	<!-- <a href="">科研项目审核</a><br /><br /> -->
	<form name="Audit" action="" method="post">
		<table class="table table-striped table-bordered table-hover dataTables-example"
			style="border-collapse:collapse;font-size: 13px;">
			<tr>
				<td>科研项目编号</td>
				<td>科研项目名称</td>
				<td>项目原编号</td>
				<td>项目类型</td>
				<td>负责人</td>
				<td>项目来源</td>
				<td>立项年份</td>
				<td>教师编号</td>
				<td>教师姓名</td>
				<td>本人承担任务</td>
				<td>项目总金/万</td>
				<td>当年到款/万</td>
				<td>最终分数</td>
				<c:if test="${sessionScope.checkoutStatus=='0' }">
					<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck"
						onchange="allAlowOrNot()" /></td>
				</c:if>
				<c:if test="${sessionScope.checkoutStatus=='1' }">
					<td><font color="green" >通过</td>
				</c:if>
				<c:if test="${sessionScope.checkoutStatus=='2' }">
					<td><font color="red">未通过</td>
				</c:if>				
			</tr>
			<c:forEach var="SRProject" items="${TA_SRProjectList }">
				<tr>
					<td>${SRProject.scientificResearchProject.srprojectId }</td>
					<td>${SRProject.scientificResearchProject.srpname }</td>
					<td>${SRProject.scientificResearchProject.projectNumber }</td>
					<td>${SRProject.scientificResearchProject.projectType.projectTpName }</td>
					<td>${SRProject.scientificResearchProject.chargePerson }</td>
					<td>${SRProject.scientificResearchProject.projectSource }</td>
					<td>${SRProject.scientificResearchProject.admitedProjectYear }</td>
					<td>${SRProject.teacher.teacherId }</td>
					<td>${SRProject.teacher.teacherName }</td>
					<td>${SRProject.selfUndertakeTask.undertakeTaskName }</td>
					<td>${SRProject.scientificResearchProject.sumFunds }</td>
					<td>${SRProject.yearFunds }</td>
					<td>${SRProject.finalScore }</td>
					<c:if test="${sessionScope.checkoutStatus=='0' }">
						<td>通过&nbsp;<input type="checkbox" name="chooseWhichToAudit" value="${SRProject.teacherAscienRpid }"/></td>
					</c:if>
					<c:if test="${sessionScope.checkoutStatus=='1' }">
						<td><font color="green" size:"3">√</td>
					</c:if>
					<c:if test="${sessionScope.checkoutStatus=='2' }">
						<td><font color="red" size="3">×</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</form>
	<!-- 分页页码显示处 -->
	<div id="dividePageDev" style="height: 30px;">
		<span style="font-size:12px;color:#727272;">
			当前是第<font style=" color:blue; font-weight: bold;">${pageIndex }</font>页
		</span>
		<span>
			<c:if test="${pageIndex>1}">
				<a href="ScientificResearchProjectAudit!getRecordsInWithConditions?pageIndex=${pageIndex-1 }">上一页</a>
			</c:if>
		</span>
		
		<c:forEach begin="${pageIndex }" end="${pageIndex+4 }" var="index" step="1">
			<c:if test="${index<=pageCount }">
				<span>
					<a href="ScientificResearchProjectAudit!getRecordsInWithConditions?pageIndex=${index }">${index }</a>
				</span>
			</c:if>
		</c:forEach>
		 <span>
		 	<c:if test="${pageIndex<pageCount }">
		 		<a href="ScientificResearchProjectAudit!getRecordsInWithConditions?pageIndex=${pageIndex+1 }">下一页</a>
		 	</c:if>
	 	</span> 
	 	<span>
	 		共<font style="color:blue;">${sessionScope.pageCount }</font>页
 		</span> 
		<span>
			共<font style="color:blue;">${sessionScope.recordNumber }</font>条记录
		</span>
	</div>
	<input type="submit" value="提交"
		class="button_set" style="margin-left:10px;" id="doCheckout"></input>
	<input type="submit" value="注销" style="display: none;"
		onclick="window.location.href='<%=basePath%>logout!out'"
		class="button_set" style="margin-left:10px;" />
	<input type="submit" value="返回"  style="display: none; onclick="window.location.href='back'"
		class="button_set" style="margin-left:10px;" />
	<s:debug></s:debug>


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
	<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
	<script src="js/ie10-viewport-bug-workaround.js"></script>
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.5"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="js/content.min.js?v=1.0.0"></script>
	<script src="js/plugins/iCheck/icheck.min.js"></script>
	<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<script src="js/PublicCheck/PUB_SET.js"></script>
	<script src="My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function changeListener(){
// 			window.alert($("#foredate").val());
// 			window.alert($("#afterdate").val());
			window.alert($("#reserchLabSelection option:selected").attr("value"));
			window.open("ScientificResearchProjectAudit!getSRPToBeAudited?pageSize="+
					$("#pageSizeSelection").val()+"&researchLabId="+$("#reserchLabSelection option:selected").attr("value"),"_self");
		}
// 		
		$().ready(function(){
// 			var selection=document.getElementById("pageSizeSelection");
// 			window.alert(typeof ("${pageSize==1}"=="true"));
			$("#pageSizeSelection option[value='${pageSize}']").attr("selected",true);
			$("#reserchLabSelection option[value='${sessionScope.selectedResearchLab.researchLabId}']").attr("selected",true);
			$("#checkoutStatus option[value='${sessionScope.checkoutStatus}']").attr("selected",true);
		});
	</script>
</body>
		<script type="text/javascript">
		function getCheckOutResult(){
			var arr = "";
		      $('input:checkbox[name=chooseWhichToAudit]:checked').each(function(i){
		    	  arr=arr+$(this).val()+",";
		      });
// 		      window.open("ScientificResearchProjectAudit!doCheckOut?checkOutIDs="+arr.substring(0,arr.length-1),"_self");
			  return arr; 
		}
		$("#doCheckout").click(function(){
			var IDs=getCheckOutResult();
			if(IDs.length==0){
				window.alert("请选择要通过审核的项目！");
				return;
			}
			if(window.confirm("您确认要提交审核吗？")){
				$.post("ScientificResearchProjectAudit!doCheckOut",{
					checkOutIDs:IDs
				},function(data,status){
					if(status=="success"){
						if(data=="succ"){
							window.alert("审核成功！");
							window.location.replace("<%=basePath %>ScientificResearchProjectAudit!getSRPToBeAudited");
						} else {
							window.alert("审核失败！");
						}
					}
				});
			}else{
				return ;
			}
		})
		</script>
</html>
