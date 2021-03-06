<?xml version="1.0" encoding="utf-8" ?>
<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("departmentList", StoreData.getDepartmentList());
	request.setAttribute("tftermList", StoreData.getTftermList());
%>
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
	<form
		action="ATTfteachingAchievementPerformanceAudit!getTfteachingAchievementPerformanceList"
		method="post" name="pickdate">
		<h3 style="padding:0px;margin-left: 10px;">教学成果奖绩效审核</h3>
		<hr>
		<span style="margin-left:10px;">所在系：&nbsp;&nbsp;&nbsp;&nbsp;</span> <span>
			<select name="department_TAP.departmentId"
			id="departmentSelection">
				<c:forEach var="department" items="${departmentList }">
					<option value="${department.departmentId }">${department.departmentName }</option>
				</c:forEach>
		</select>
		</span>&nbsp;&nbsp;&nbsp;&nbsp; 
		<span>
			学期：
			<select id="termSelection" name="termId_TAP">
				<c:forEach var="tfterm" items="${tftermList }">
					<option value="${tfterm.termId }">${tfterm.term }</option>
				</c:forEach>
			</select>
		</span>&nbsp;&nbsp;&nbsp;&nbsp; 
		<span>每页显示： <select
			name="pageSize_TAP" id="pageSizeSelection">
				<option value="1">&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;</option>
				<option value="2">&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;</option>
				<option value="10">&nbsp;&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;</option>
				<option value="20">&nbsp;&nbsp;&nbsp;20&nbsp;&nbsp;&nbsp;</option>
				<option value="30">&nbsp;&nbsp;&nbsp;30&nbsp;&nbsp;&nbsp;</option>
		</select> 条记录
		</span> <span>&nbsp;&nbsp;&nbsp;&nbsp; 审核状态： <select
			name="checkOutStatus_TAP" id="checkoutStatus">
				<c:forEach var="status" items="${auditStatus }">
					<c:if test="${status.key!='5' }">
						<c:choose>
							<c:when test="${status.key == '0' }">
								<option value="${status.key }"/>待系主任审核
							</c:when>
							<c:otherwise>
								<option value="${status.key }">${status.value }</option>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
		</select>
		</span> <span style="margin-left:15px;"><button type="submit"
				class="button_set" style="height:25px;">确认</button></span>
	</form>
	<hr />
	<form name="Audit" action="" method="post">
		<table
			class="table table-striped table-bordered table-hover dataTables-example"
			style="border-collapse:collapse;">
			<!--font-size:13px;border-bottom: 1px solid silver;  -->
			<tr>
				<td>教学成果获奖编号</td>
				<td>教学成果奖名称</td>
				<td>获奖级别</td>
				<td>本人承担任务</td>
				<td>是否有其他作者参与</td>
				<td>教师编号</td>
				<td>教师姓名</td>
				<td>项目总分</td>
				<td>最终分数</td>
				<c:if test="${sessionScope.checkOutStatus_TAP=='1' }">
					<td>全通过&nbsp;<input type="checkbox" name="" id="allAudit"/></td>
					<td>全不通过<input type="checkbox" id="allNotAudit"></td>
				</c:if>
				<c:if test="${sessionScope.checkOutStatus_TAP=='0' }">
					<td><font color="blue">待系主任审核</td>
				</c:if>				
				<c:if test="${sessionScope.checkOutStatus_TAP=='2' }">
					<td><font color="red">未通过</td>
				</c:if>				
				<c:if test="${sessionScope.checkOutStatus_TAP=='3' }">
					<td><font color="green">管理员审核通过</td>
				</c:if>				
				<c:if test="${sessionScope.checkOutStatus_TAP=='4' }">
					<td><font color="blue">查看全部记录</td>
				</c:if>	

			</tr>
			<c:forEach var="TfteachingAchievementPerformance"
				items="${TfteachingAchievementPerformanceList }">
				<tr>
					<!-- 教学成果获奖编号-->
					<td>${TfteachingAchievementPerformance.tfteachingAchievementProject.projectId }</td>
					<!-- 教学成果奖名称-->
					<td>${TfteachingAchievementPerformance.tfteachingAchievementProject.projectName }</td>
					<!-- 获奖级别 -->
					<td>${TfteachingAchievementPerformance.tfteachingAchievementProject.tfteachingAchievementRewardLevel.rewardName }</td>
					<!-- 本人承担任务 -->
					<td>${TfteachingAchievementPerformance.selfUndertakeTask.undertakeTaskName }</td>
					<!-- 是否有其他作者参与 -->
					<td>${TfteachingAchievementPerformance.tfteachingAchievementProject.cooperator }</td>
					<!-- 教师编号 -->
					<td>${TfteachingAchievementPerformance.teacher.teacherId }</td>
					<!-- 教师姓名 -->
					<td>${TfteachingAchievementPerformance.teacher.teacherName }</td>
					<!-- 项目总分 -->
					<td>${TfteachingAchievementPerformance.tfteachingAchievementProject.projectSumScore }</td>
					<!-- 最终分数 -->
					<td>${TfteachingAchievementPerformance.singelScore }</td>
					<c:if test="${sessionScope.checkOutStatus_TAP=='1' }">
						<td class="c1">通过&nbsp;<input type="checkbox" name="chooseWhichToAudit"
							value="${TfteachingAchievementPerformance.upid}"   class="check1"/></td>
						<td class="c2">不通过<input value="${TfteachingAchievementPerformance.upid}" type="checkbox"
						 name="notAudit" class="check2"/></td>
					</c:if>
					<c:if test="${sessionScope.checkOutStatus_TAP=='0' }">
						<td><font color="blue" size:"3">待系主任审核项目</td>
					</c:if>
					<c:if test="${sessionScope.checkOutStatus_TAP=='2' }">
						<td><font color="red" size="3">×</td>
					</c:if>
					<c:if test="${sessionScope.checkOutStatus_TAP=='3' }">
						<td><font color="green" size="2">管理员审核通过</td>
					</c:if>
					<c:if test="${sessionScope.checkOutStatus_TAP=='4' }">
						<c:if test="${TfteachingAchievementPerformance.checkOut=='0' }">
							<td><font color="orange" >等待系主任审核</td>
						</c:if>
						<c:if test="${TfteachingAchievementPerformance.checkOut=='1' }">
							<td><font color="blue" >待管理员审核</td>
						</c:if>
						<c:if test="${TfteachingAchievementPerformance.checkOut=='2' }">
							<td><font color="red">未通过</td>
						</c:if>				
						<c:if test="${TfteachingAchievementPerformance.checkOut=='3' }">
							<td><font color="green">管理员审核通过</td>
						</c:if>				
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</form>
	<!-- 分页页码显示处 -->
	<div id="dividePageDev" style="height: 30px;">
		<span style="font-size:12px;color:#727272;"> 当前是第<font
			style=" color:blue; font-weight: bold;">${pageIndex }/${sessionScope.pageCount_TAP }</font>页
		</span> <span> <c:if test="${pageIndex>1}">
				<a
					href="ATTfteachingAchievementPerformanceAudit!getTfteachingAchievementPerformanceList?isDivided=true&pageIndex=${pageIndex-1 }">上一页</a>
			</c:if>
		</span>

		<c:forEach begin="${pageIndex }" end="${pageIndex+4 }" var="index"
			step="1">
			<c:if test="${index<=pageCount_TAP }">
				<span> <a
					href="ATTfteachingAchievementPerformanceAudit!getTfteachingAchievementPerformanceList?isDivided=true&pageIndex=${index }">${index }</a>
				</span>
			</c:if>
		</c:forEach>
		<span> <c:if test="${pageIndex<pageCount_TAP }">
				<a
					href="ATTfteachingAchievementPerformanceAudit!getTfteachingAchievementPerformanceList?isDivided=true&pageIndex=${pageIndex+1 }">下一页</a>
			</c:if>
		</span> <span> 共<font style="color:blue;">${sessionScope.pageCount_TAP }</font>页
		</span> <span> 共<font style="color:blue;">${sessionScope.recordNumber_TAP }</font>条记录
		</span>
	</div>
	<c:if test="${sessionScope.checkOutStatus_TAP=='1'}">
		<input type="button" value="审核" class="button_set"
			style="margin-left:10px;" id="doCheckout"></input>
	</c:if>
	<input type="submit" value="注销" style="display: none;"
		onclick="window.location.href='<%=basePath%>logout!out'"
		class="button_set" style="margin-left:10px;" />
	<input type="submit" value="返回" style="display: none; onclick="
		window.location.href='back' "
		class="button_set"
		style="margin-left:10px;" />

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
	<script src="js/AuditSubmitController.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			$("#pageSizeSelection option[value='${sessionScope.pageSize_TAP}']").attr("selected",true);
			$("#departmentSelection option[value='${sessionScope.department_TAP.departmentId}']").attr("selected",true);
			$("#checkoutStatus option[value='${sessionScope.checkOutStatus_TAP}']").attr("selected",true);
			$("#termSelection option[value='${sessionScope.termId_TAP}']").attr("selected",true);
		});
		$("#doCheckout").click(function(){
			submitAudit("ATTfteachingAchievementPerformanceAudit!doCheckOutTask",
					"ATTfteachingAchievementPerformanceAudit!getTfteachingAchievementPerformanceList");
		});
	</script>
</body>
</html>
