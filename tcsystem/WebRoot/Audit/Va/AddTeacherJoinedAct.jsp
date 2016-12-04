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
	request.setAttribute("departmentList",
			StoreData.getDepartmentList());
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
		action="GTAddJoinedActAudit!getAddJoinedActList"
		method="post" name="pickdate" id="conditionForm">
		<div class="datepick" style="font-size:12px;">
			<span>选择日期范围</span>
			<div>

				从:<input type="text" class="Wdate" id="foredate"
					onClick="WdatePicker()" value="${sessionScope.foredate_CT }"
					name="foredate_CT"  />到:<input type="text"
					onClick="WdatePicker()" class="Wdate" id="afterdate"
					value="${sessionScope.afterdate_CT }" name="afterdate_CT"
					 /> &nbsp;&nbsp;<input type="submit" id="datep"
					value="查询" title="点击查询">
			</div>
		</div>
		<h3 style="padding:0px;margin-left: 10px;">活动参与审核</h3>
		<hr>
		<span style="margin-left:10px;">系：&nbsp;&nbsp;&nbsp;&nbsp;</span> <span>
			<select name="department_CT.departmentId"
			id="departmentSelection">
				<c:forEach var="department" items="${departmentList }">
					<option value="${department.departmentId }">${department.departmentName }</option>
				</c:forEach>
		</select>
		</span>&nbsp;&nbsp;&nbsp;&nbsp;
		<span>每页显示： <select
			name="pageSize_CT" id="pageSizeSelection">
				<option value="1">&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;</option>
				<option value="2">&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;</option>
				<option value="10">&nbsp;&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;</option>
				<option value="20">&nbsp;&nbsp;&nbsp;20&nbsp;&nbsp;&nbsp;</option>
				<option value="30">&nbsp;&nbsp;&nbsp;30&nbsp;&nbsp;&nbsp;</option>
		</select> 条记录
		</span> <span>&nbsp;&nbsp;&nbsp;&nbsp; 审核状态： <select
			name="checkOutStatus_CT" id="checkoutStatus">
				<option value="0">未审核</option>
				<option value="1">审核通过</option>
				<option value="2">未通过审核</option>
		</select>
		</span> <span style="margin-left:15px;"><button type="submit"
				class="button_set" style="height:25px;" id="confirm">确认</button></span>
	</form>
	<hr />
	<form name="Audit" action="" method="post">
		<table
			class="table table-striped table-bordered table-hover dataTables-example"
			style="border-collapse:collapse;">
			<!--font-size:13px;border-bottom: 1px solid silver;  -->
			<tr>
				<td>公益活动编号</td>
				<td>公益活动名称</td>
				<td>参与人员</td>
				<td>活动日期</td>
				<td>活动类型</td>
				<td>教师编号</td>
				<td>教师姓名</td>
				<td>最终分数</td>
				<c:if test="${sessionScope.checkOutStatus_CT=='0' }">
					<td>全通过&nbsp;<input type="checkbox" name="" id="allAudit"/></td>
					<td>全不通过<input type="checkbox" id="allNotAudit"></td>
				</c:if>
				<c:if test="${sessionScope.checkOutStatus_CT=='1' }">
					<td><font color="blue">通过</td>
				</c:if>
				<c:if test="${sessionScope.checkOutStatus_CT=='2' }">
					<td><font color="red">未通过</td>
				</c:if>

			</tr>
			<c:forEach var="VaAddJoinedAct"
				items="${VaAddJoinedActList }">
				<tr>
					<!-- 公益活动编号 -->
					<td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.actPubId }</td>
					<!-- 公益活动名称-->
					<td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.vacollectiveAct.actName }</td>
					<!-- 参与人员 -->
					<td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.vacollectiveAct.attendee }</td>
					<!-- 活动日期 -->
					<td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.actDate}</td>
					<!-- 活动类型 -->
					<td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.vacollectiveAct.actType }</td>
					<!-- 教师编号 -->
					<td>${VaAddJoinedAct.id.teacher.teacherId }</td>
					<!-- 教师姓名 -->
					<td>${VaAddJoinedAct.id.teacher.teacherName }</td>
					<!-- 最终分数 -->
					<td>${VaAddJoinedAct.score }</td>
					<c:if test="${sessionScope.checkOutStatus_CT=='0' }">
						<td class="c1">通过&nbsp;<input type="checkbox" name="chooseWhichToAudit"
							value="${VaAddJoinedAct.id.vacollectiveActivitiesPublish.actPubId}"   class="check1"/></td>
						<td class="c2">不通过<input value="${VaAddJoinedAct.id.vacollectiveActivitiesPublish.actPubId}" type="checkbox"
						 name="notAudit" class="check2"/></td>
					</c:if>
					<c:if test="${sessionScope.checkOutStatus_CT=='1' }">
						<td><font color="green"size:"3">√</td>
					</c:if>
					<c:if test="${sessionScope.checkOutStatus_CT=='2' }">
						<td><font color="red" size="3">×</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</form>
	<!-- 分页页码显示处 -->
	<div id="dividePageDev" style="height: 30px;">
		<span style="font-size:12px;color:#727272;"> 当前是第<font
			style=" color:blue; font-weight: bold;">${pageIndex }/${sessionScope.pageCount_CT }</font>页
		</span> <span> <c:if test="${pageIndex>1}">
				<a
					href="GTAddJoinedActAudit!getAddJoinedActListAfterDivide?pageIndex=${pageIndex-1 }">上一页</a>
			</c:if>
		</span>

		<c:forEach begin="${pageIndex }" end="${pageIndex+4 }" var="index"
			step="1">
			<c:if test="${index<=pageCount_CT }">
				<span> <a
					href="GTAddJoinedActAudit!getAddJoinedActListAfterDivide?pageIndex=${index }">${index }</a>
				</span>
			</c:if>
		</c:forEach>
		<span> <c:if test="${pageIndex<pageCount_CT }">
				<a
					href="GTAddJoinedActAudit!getAddJoinedActListAfterDivide?pageIndex=${pageIndex+1 }">下一页</a>
			</c:if>
		</span> <span> 共<font style="color:blue;">${sessionScope.pageCount_CT }</font>页
		</span> <span> 共<font style="color:blue;">${sessionScope.recordNumber_CT }</font>条记录
		</span>
	</div>
	<c:if test="${sessionScope.checkOutStatus_CT=='0'}">
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
			$("#pageSizeSelection option[value='${sessionScope.pageSize_CT}']").attr("selected",true);
			$("#departmentSelection option[value='${sessionScope.department_CT.departmentId}']").attr("selected",true);
			$("#checkoutStatus option[value='${sessionScope.checkOutStatus_CT}']").attr("selected",true);
		});
		$("#doCheckout").click(function(){
			submitAudit("GTAddJoinedActAudit!doCheckOutTask",
					"GTAddJoinedActAudit!getAddJoinedActList");
		});
// 		$("#datep").click(function(){
// 			if($("#foredate").val.length!=0 && $("#afterdate").val.length ==0 || $("#foredate").val.length ==0 && $("#afterdate").val.length !=0){
// 				window.alert("请选择完整的日期");
// 				return false;
// 			}else{
// 				document.pickdate.submit();
// 			}	
// 		});
		
		$("#datep").click(function(){
			if(($("#foredate").val().length!=0 && $("#afterdate").val().length==0) 
					||($("#foredate").val().length==0 && $("#afterdate").val().length!=0)){
				window.alert("请填写完整日期");
				return false;
			}else{
				document.pickdate.submit();
			}
		});
		$("#confirm").click(function(){
			if(($("#foredate").val().length!=0 && $("#afterdate").val().length==0) 
					||($("#foredate").val().length==0 && $("#afterdate").val().length!=0)){
				window.alert("请填写完整日期");
				return false;
			}else{
				document.pickdate.submit();
			}
		});
	</script>
</body>
</html>
