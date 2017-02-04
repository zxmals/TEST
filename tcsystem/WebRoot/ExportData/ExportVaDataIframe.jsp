<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("teachermp", StoreData.getTeachertranslate());
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base target="_self">
</head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>va Iframe</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

<!-- Sweet Alert -->
<link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<!-- Data Tables -->
<link href="../css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="../css/zxma.css">
<link href="../css/animate.min.css" rel="stylesheet">
<link href="../css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_blank">
<link rel="stylesheet" href="../css/mermberTab.css">
<script src="../My97DatePicker/WdatePicker.js"></script>
<script src="../js/jquery.min.js?v=2.1.4"></script>
<script src="../js/bootstrap.min.js?v=3.3.5"></script>
<script src="../js/plugins/jeditable/jquery.jeditable.js"></script>
<script src="../js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../js/content.min.js?v=1.0.0"></script>
<script src="../js/plugins/iCheck/icheck.min.js"></script>
<script src="../js/PublicCheck/PUB_SET.js"></script>
<!-- ISBN输入控制 -->
<script src="../js/plugins/jasny/jasny-bootstrap.min.js"></script>
<!-- sweet-alert -->
<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
</head>

<body class="gray-bg">
	<div class="ibox-content" style="height:540px;">
		<div class="example" style="overflow: auto">
			<table id="tb"
				class="table table-striped table-bordered table-hover dataTables-example">
				<tr class="info">
					<td>活动发布编号</td>
					<td>活动名称</td>
					<td>活动日期</td>
					<td>活动类型</td>
					<td>操作</td>
				</tr>
				<tbody>
					<c:forEach var="vaact" items="${vaactList }">
						<tr>
							<td>${vaact.actPubId }</td>
							<td>${vaact.vacollectiveAct.actName }</td>
							<td>${vaact.actDate  }</td>
							<c:if test="${vaact.vacollectiveAct.actType==1 }">
								<td>规定性活动</td>
							</c:if>
							<c:if test="${vaact.vacollectiveAct.actType==2 }">
								<td>选择性活动</td>
							</c:if>
							<c:if test="${vaact.vacollectiveAct.actType==3 }">
								<td>其他活动</td>
							</c:if>

							<td><button class="button_set" type="submit"
									id="vaactexports_${vaact.actPubId }" data-backdrop="true"
									onclick="exportAct('${vaact.actPubId}')">导出该活动</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script type="text/javascript">
	//页面初始化处理
    $(document).ready(function() {
		var tds = $('.disp');
		for(var i=0;i<tds.length;i++){
			tds[i].innerHTML = tds[i].title.substring(0,5)+"...";
		}
	});
	function exportAct(actPubId){
		window.location.replace("ATVaActListExport!oneActExport?actPubId=" + actPubId);
    }
	</script>
	<!-- carry-data -->
	<script type="text/javascript"
		src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
</body>
</html>
