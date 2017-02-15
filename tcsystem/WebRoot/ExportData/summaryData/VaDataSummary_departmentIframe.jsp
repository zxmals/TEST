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

<title>Teaching-DATASUMMARY --DEPART-</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="../../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="../../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

<!-- Sweet Alert -->
<link href="../../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<!-- Data Tables -->
<link href="../../css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="../../css/zxma.css">
<link href="../../css/animate.min.css" rel="stylesheet">
<link href="../../css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_blank">
<link rel="stylesheet" href="../../css/mermberTab.css">
<script src="../../My97DatePicker/WdatePicker.js"></script>
<script src="../../js/jquery.min.js?v=2.1.4"></script>
<script src="../../js/bootstrap.min.js?v=3.3.5"></script>
<script src="../../js/plugins/jeditable/jquery.jeditable.js"></script>
<script src="../../js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="../../js/content.min.js?v=1.0.0"></script>
<script src="../../js/plugins/iCheck/icheck.min.js"></script>
<script src="../../js/PublicCheck/PUB_SET.js"></script>
<!-- ISBN输入控制 -->
<script src="../../js/plugins/jasny/jasny-bootstrap.min.js"></script>
<!-- sweet-alert -->
<script src="../../js/plugins/sweetalert/sweetalert.min.js"></script>
</head>

<body class="gray-bg">
	<div class="ibox-content" style="height:540px;">
		<button class="btn  btn-primary openaddm" type="button"
			data-backdrop="true" onclick="exportData();">
			<strong>导出</strong>
		</button>
		<br>
		<div class="example" style="overflow: auto">
			<table id="tb"
				class="table table-striped table-bordered table-hover dataTables-example">
				<tr class="info">
					<td>研究所</td>
					<td>活动参与</td>
					<td>活动缺席</td>
					<td>总计(总/均)</td>
				</tr>
				<tbody>
					<c:forEach var="entity" items="${vaActSummaryDataByDepartment }">
						<tr>
							<td>${entity.departmentName }</td>
							<td>${entity.teacherJoinedData.sum }/${entity.teacherJoinedData.average }</td>
							<td>${entity.unjoinedActData.sum }/${entity.unjoinedActData.average }</td>
							<td>${entity.sum }/${entity.average }</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<script>
    //页面初始化处理
    $(document).ready(function() {
		var tds = $('.disp');
		for(var i=0;i<tds.length;i++){
			tds[i].innerHTML = tds[i].title.substring(0,5)+"...";
		}
	});
    function exportData(){
    	window.open("ATVaActDataSummary_departmentExcel!getSummaryDataExcel");
    }
	</script>
	<!-- carry-data -->
	<script type="text/javascript"
		src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>

</body>
</html>