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
<title>所长审核界面</title>

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
	<form action="GTMainUndertakeAcademicMeetingAudit_project!getAllRecord?isDivided=false"
				method="post" name="pickdate" id="conditionForm">
	<div class="datepick" style="font-size:12px;">
		<span>选择日期范围</span>
		<div>
			
				从:<input type="text" id="date1" class="Wdate"
					onClick="WdatePicker()" value="${sessionScope.foredate_GTMUAM }" name="foredate_GTMUAM"
					 />到:<input type="text" id="date2"
					onClick="WdatePicker()" class="Wdate" value="${sessionScope.afterdate_GTMUAM }"
					name="afterdate_GTMUAM" /> &nbsp;&nbsp;<input
					type="button" id="datep" value="查询" title="点击查询" name="submitCondition">
		</div>
	</div>
	<h3 style="padding:0px;margin-left: 10px;">承担学术会议审核</h3>
	<hr>
		<span style="margin-left:10px;">当前研究所：&nbsp;&nbsp;&nbsp;&nbsp;</span> 
		<span style="color:blue;font-weight:bold">
		${sessionScope.teacherResearchLab } 
		</span>&nbsp;&nbsp;&nbsp;&nbsp;
	 	<span>每页显示：
			<select name="pageSize_GTMUAM" id="pageSizeSelection">
				<option value="1" >&nbsp;&nbsp;&nbsp;1&nbsp;&nbsp;&nbsp;</option>
				<option value="2">&nbsp;&nbsp;&nbsp;2&nbsp;&nbsp;&nbsp;</option>
				<option value="10" >&nbsp;&nbsp;&nbsp;10&nbsp;&nbsp;&nbsp;</option>
				<option value="20" >&nbsp;&nbsp;&nbsp;20&nbsp;&nbsp;&nbsp;</option>
				<option value="30" >&nbsp;&nbsp;&nbsp;30&nbsp;&nbsp;&nbsp;</option>
			</select> 条记录
		</span>
		<span>&nbsp;&nbsp;&nbsp;&nbsp;
			审核状态：
			<select name="checkout_GTMUAM" id="checkoutStatus" style="width:160px;">
				<c:forEach var="status" items="${auditStatus }">
					<c:if test="${status.key!='3' }">
						<c:choose>
							<c:when test="${status.key=='1' }">
								<option value="${status.key }">审核通过</option>
							</c:when>
							<c:otherwise>
								<option value="${status.key }">${status.value }</option>
							</c:otherwise>
						</c:choose>
					</c:if>
				</c:forEach>
			</select>
		</span>
		<span style="margin-left:15px;"><button type="submit" class="button_set" style="height:25px;" name="submitCondition">确认</button></span>
	</form>
	<hr/>
	<!-- <a href="">科研项目审核</a><br /><br /> --> 
	<form name="Audit" action="" method="post" style="margin-top:0px;">
		<table class="table table-striped table-bordered table-hover dataTables-example"
			style="border-collapse:collapse;font-size: 13px;">
			<tr>
				<td>主承办学术会议编号</td>
				<td>主承办学术会议名称</td>
				<td>负责人ID</td>
				<td>负责人</td>
				<td>会议类型</td>
				<td>会议地点</td>
				<td>会议时间</td>
				<c:if test="${sessionScope.checkout_GTMUAM=='0' }">
					<td>全通过&nbsp;<input type="checkbox" name="" id="allAudit"/></td>
					<td>全不通过<input type="checkbox" id="allNotAudit"></td>
				</c:if>
				<c:if test="${sessionScope.checkout_GTMUAM=='1' }">
					<td><font color="blue">所长审核通过</td>
				</c:if>	
				<c:if test="${sessionScope.checkout_GTMUAM=='2' }">
					<td><font color="red">未通过审核</td>
				</c:if>	
				<c:if test="${sessionScope.checkout_GTMUAM=='4' }">
					<td><font color="blue">审核状态</td>
				</c:if>	
				<c:if test="${sessionScope.checkout_GTMUAM=='5' }">
					<td><font color="orange">待完善</td>
				</c:if>	
			</tr>
			<c:forEach var="mainUndertakeAcademicMeeting" items="${mainUndertakeAcademicMeetingList }">
				<tr>
					<!-- 主承办学术会议编号 -->
					<td>${mainUndertakeAcademicMeeting.acaMeetingId }</td>
					<!-- 主承办学术会议名称 -->
					<td>${mainUndertakeAcademicMeeting.acaMeetingName }</td>
					<!-- 负责人ID -->
					<td>${mainUndertakeAcademicMeeting.chargePersonId}</td>
					<!-- 负责人 -->
					<td>${mainUndertakeAcademicMeeting.chargePerson}</td>
					<!-- 会议类型 -->
					<td>${mainUndertakeAcademicMeeting.mainUndertakeAcademicMeetingType.acaMeetType }</td>
					<!-- 会议地点 -->
					<td>${mainUndertakeAcademicMeeting.mainUndertakeAcademicMeetingPlace.acaMeetPlace }</td>
					<!-- 时间 -->
					<td>${mainUndertakeAcademicMeeting.meetingdate }</td>
					<c:if test="${sessionScope.checkout_GTMUAM=='0' }">
						<td class="c1">通过&nbsp;<input type="checkbox" name="chooseWhichToAudit"
							value="${mainUndertakeAcademicMeeting.acaMeetingId}"   class="check1"/></td>
						<td class="c2">不通过<input value="${mainUndertakeAcademicMeeting.acaMeetingId}" type="checkbox"
						 name="notAudit" class="check2"/></td>
					</c:if>
					<c:if test="${sessionScope.checkout_GTMUAM=='1' }">
						<td><font color="blue" size:"3">√</td>
					</c:if>
					<c:if test="${sessionScope.checkout_GTMUAM=='2' }">
						<td><font color="red" size="3">×</td>
					</c:if>
					<c:if test="${sessionScope.checkout_GTMUAM=='4' }">
						<c:if test="${mainUndertakeAcademicMeeting.checkout=='0' }">
							<td>待审核</td>
						</c:if>
						<c:if test="${mainUndertakeAcademicMeeting.checkout=='1' }">
							<td><font color="blue" >所长审核通过</td>
						</c:if>
						<c:if test="${mainUndertakeAcademicMeeting.checkout=='2' }">
							<td><font color="red">未通过审核</td>
						</c:if>	
						<c:if test="${mainUndertakeAcademicMeeting.checkout=='5' }">
							<td><font color="orange">待完善</td>
						</c:if>	
					</c:if>
					<c:if test="${sessionScope.checkout_GTMUAM=='5' }">
						<td><font color="orange" size="2">待完善</td>
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
				<a href="GTMainUndertakeAcademicMeetingAudit_project!getAllRecord?isDivided=true&pageIndex=${pageIndex-1 }">上一页</a>
			</c:if>
		</span>
		
		<c:forEach begin="${pageIndex }" end="${pageIndex+4 }" var="index" step="1">
			<c:if test="${index<=pageCount_GTMUAM }">
				<span>
					<a href="GTMainUndertakeAcademicMeetingAudit_project!getAllRecord?isDivided=true&pageIndex=${index }">${index }</a>
				</span>
			</c:if>
		</c:forEach>
		 <span>
		 	<c:if test="${pageIndex<pageCount_GTMUAM }">
		 		<a href="GTMainUndertakeAcademicMeetingAudit_project!getAllRecord?isDivided=true&pageIndex=${pageIndex+1 }">下一页</a>
		 	</c:if>
	 	</span> 
	 	<span>
	 		共<font style="color:blue;">${sessionScope.pageCount_GTMUAM }</font>页
 		</span> 
		<span>
			共<font style="color:blue;">${sessionScope.recordNumber_GTMUAM }</font>条记录
		</span>
	</div>
	<c:if test="${sessionScope.checkout_GTMUAM=='0' }">
		<input type="submit" value="提交"
		class="button_set" style="margin-left:10px;" id="doCheckout"></input>
	</c:if>
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
	<script src="js/AuditSubmitController.js"></script>
	<script type="text/javascript">
		$().ready(function(){
			$("#pageSizeSelection option[value='${pageSize_GTMUAM}']").attr("selected",true);
			$("#reserchLabSelection option[value='${sessionScope.selectedResearchLab.researchLabId}']").attr("selected",true);
			$("#checkoutStatus option[value='${sessionScope.checkout_GTMUAM}']").attr("selected",true);
		});
		$("#doCheckout").click(function(){
			submitAudit("GTMainUndertakeAcademicMeetingAudit_project!doCheckOut_project",
					"GTMainUndertakeAcademicMeetingAudit_project!getAllRecord?isDivided=false");
		});
		$("#datep").click(function(){
			if(($("#date1").val().length!=0 && $("#date2").val().length==0) 
					||($("#date1").val().length==0 && $("#date2").val().length!=0)){
				window.alert("请填写完整日期");
				return false;
			}else{
				$("#conditionForm").submit();
			}
		});
	</script>
</body>
</html>
