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
	request.setAttribute("researchLabList", StoreData.getResearchLabList());
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
	<form action="ATScientificResearchRewardAudit!getRewardInfo"
		method="post" name="pickdate">
		<div class="datepick" style="font-size:12px;">
			<span>选择日期范围</span>
			<div>

				从:<input type="text" id="date1" class="Wdate"
					onClick="WdatePicker()" value="${sessionScope.foredate_TARR }"
					name="foredate" id="foredate" />到:<input type="text" id="date2"
					onClick="WdatePicker()" class="Wdate"
					value="${sessionScope.afterdate_TARR }" name="afterdate"
					id="afterdate" /> &nbsp;&nbsp;<input type="submit" id="datep"
					value="查询" title="点击查询">
			</div>
		</div>
		<h3 style="padding:0px;margin-left: 10px;">科研项目奖励审核</h3>
		<hr>
		<span style="margin-left:10px;">研究所：&nbsp;&nbsp;&nbsp;&nbsp;</span> <span>
			<select name="researchLab.researchLabId" id="reserchLabSelection">
				<c:forEach var="researchLab" items="${researchLabList }">
					<option value="${researchLab.researchLabId }">${researchLab.researchLabName }</option>
				</c:forEach>
		</select>
		</span>&nbsp;&nbsp;&nbsp;&nbsp; <span>每页显示： <select name="pageSize"
			id="pageSizeSelection">
				<c:forEach items="${pageSizeList }" var="pageSize">
					<option value="${pageSize }">&nbsp;&nbsp;&nbsp;${pageSize }&nbsp;&nbsp;&nbsp;</option>
				</c:forEach>
		</select> 条记录
		</span> <span>&nbsp;&nbsp;&nbsp;&nbsp; 审核状态： <select name="checkout"
			id="checkoutStatus">
				<c:forEach var="status" items="${auditStatus }">
					<option value="${status.key }">${status.value }</option>
				</c:forEach>
		</select>
		</span> <span style="margin-left:15px;"><button type="submit"
				class="button_set" style="height:25px;">确认</button></span>
	</form>
	<hr />
	<!-- <a href="">科研项目审核</a><br /><br /> -->
	<form name="Audit" action="" method="post">
		<table
			class="table table-striped table-bordered table-hover dataTables-example"
			style="border-collapse:collapse;">
			<!--font-size:13px;border-bottom: 1px solid silver;  -->
			<tr>
				<td>科研奖励编号</td>
				<td>科研奖励名称</td>
				<td>获奖级别</td>
				<td>获奖类别</td>
				<td>授奖部门</td>
				<td>获奖总人数</td>
				<td>教师编号</td>
				<td>教师姓名</td>
				<td>本人排名</td>
				<td>最终分数</td>
				<td>获奖时间</td>
<!-- 				<c:if test="${sessionScope.checkoutStatus_TARR=='0' }"> -->
<!-- 					<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck" -->
<!-- 						onchange="allAlowOrNot()" /></td> -->
<!-- 				</c:if> -->
				<c:if test="${sessionScope.checkoutStatus_TARR=='1' }">
					<td>全通过&nbsp;<input type="checkbox" name="" id="allAudit"/></td>
					<td>全不通过<input type="checkbox" id="allNotAudit"></td>
				</c:if>
					<c:if test="${sessionScope.checkoutStatus_TARR=='0' }">
					<td><font color="blue">待所长审核项目</td>
				</c:if>				
				<c:if test="${sessionScope.checkoutStatus_TARR=='2' }">
					<td><font color="red">未通过审核</td>
				</c:if>				
				<c:if test="${sessionScope.checkoutStatus_TARR=='3' }">
					<td><font color="green">管理员审核通过</td>
				</c:if>				
				<c:if test="${sessionScope.checkoutStatus_TARR=='4' }">
					<td><font color="blue">查看全部记录</td>
				</c:if>	
			</tr>
			<c:forEach var="TARReward" items="${TAR_RewardList }">
				<tr>
					<td>${TARReward.scientificResearchReward.srrewardId }</td>
					<td>${TARReward.scientificResearchReward.srrewardName }</td>
					<td>${TARReward.scientificResearchReward.rewardLevel.rewardLevelName }</td>
					<td>${TARReward.scientificResearchReward.rewardType.rewardTypeName }</td>
					<td>${TARReward.scientificResearchReward.awardDepartment }</td>
					<td>${TARReward.scientificResearchReward.rewardTotalPeople }</td>
					<td>${TARReward.teacher.teacherId }</td>
					<td>${TARReward.teacher.teacherName }</td>
					<td>${TARReward.selfRanking }</td>
					<td>${TARReward.finalScore }</td>
					<td>${TARReward.scientificResearchReward.rewardDate }</td>
					<c:if test="${sessionScope.checkoutStatus_TARR=='1' }">
						<td class="c1">通过&nbsp;<input type="checkbox" name="chooseWhichToAudit"
							value="${TARReward.teacherAsrrid }"   class="check1"/></td>
						<td class="c2">不通过<input value="${TARReward.teacherAsrrid }" type="checkbox"
						 name="notAudit" class="check2"/></td>
					</c:if>
					<c:if test="${sessionScope.checkoutStatus_TARR=='0' }">
						<td><font color="blue" size:"3">待所长审核项目</td>
					</c:if>
					<c:if test="${sessionScope.checkoutStatus_TARR=='2' }">
						<td><font color="red" size="3">×</td>
					</c:if>
					<c:if test="${sessionScope.checkoutStatus_TARR=='3' }">
						<td><font color="green" size="3">管理员审核通过</td>
					</c:if>
					<c:if test="${sessionScope.checkoutStatus_TARR=='4' }">
						<c:if test="${TARReward.checkOut=='0' }">
							<td><font color="orange" >待所长审核项目</td>
						</c:if>
						<c:if test="${TARReward.checkOut=='1' }">
							<td><font color="blue" >待管理员审核</td>
						</c:if>
						<c:if test="${TARReward.checkOut=='2' }">
							<td><font color="red">未通过审核</td>
						</c:if>				
						<c:if test="${TARReward.checkOut=='3' }">
							<td><font color="green">管理员审核通过</td>
						</c:if>				
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</form>
	<!-- 分页页码显示处 -->
	<div id="dividePageDev" style="height: 30px;">
		<span style="font-size:12px;color:#727272;">
			当前是第<font style=" color:blue; font-weight: bold;">${pageIndex_TARR }</font>页
		</span>
		 <span> 
			<c:if
				test="${pageIndex_TARR>1}">
				<a
					href="ATScientificResearchRewardAudit!getTARRewardWithDividedPage?pageIndex_TARR=${pageIndex_TARR-1 }">上一页</a>
			</c:if>
		</span>

		<c:forEach begin="${pageIndex_TARR }" end="${pageIndex_TARR+4 }" var="index"
			step="1">
			<c:if test="${index<=pageCount_TARR }">
				<span> <a
					href="ATScientificResearchRewardAudit!getTARRewardWithDividedPage?pageIndex_TARR=${index }">${index }</a>
				</span>
			</c:if>
		</c:forEach>
		<span> <c:if test="${pageIndex_TARR<pageCount_TARR }">
				<a
					href="ATScientificResearchRewardAudit!getTARRewardWithDividedPage?pageIndex_TARR=${pageIndex_TARR+1 }">下一页</a>
			</c:if>
		</span> <span> 共<font style="color:blue;">${sessionScope.pageCount_TARR }</font>页
		</span> <span> 共<font style="color:blue;">${sessionScope.recordNumber_TARR }</font>条记录
		</span>
	</div>
	<c:if test="${sessionScope.checkoutStatus_TARR=='1'}">
		<input type="button" value="提交" class="button_set"
		style="margin-left:10px;" id="doCheckout"></input>
	</c:if>
	<input type="submit" value="注销" style="display: none;"
		onclick="window.location.href='<%=basePath%>logout!out'"
		class="button_set" style="margin-left:10px;" />
	<input type="submit" value="返回" style="display: none; onclick="
		window.location.href='back' "
		class="button_set"
		style="margin-left:10px;" />
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
	<script src="js/AuditSubmitController.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			$("#pageSizeSelection option[value='${pageSize_TARR}']").attr("selected",true);
			$("#reserchLabSelection option[value='${sessionScope.selectedReserchLab_TARR.researchLabId}']").attr("selected",true);
			$("#checkoutStatus option[value='${sessionScope.checkoutStatus_TARR}']").attr("selected",true);
		});
		$("#doCheckout").click(function(){
			submitAudit("ATScientificResearchRewardAudit!doCheckOutTask",
					"ATScientificResearchRewardAudit!getRewardInfo");
		});
	</script>
</body>
</html>
