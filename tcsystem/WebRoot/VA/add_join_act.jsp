<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
        "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>add_act</title>
<link rel="shortcut icon" href="favicon.ico">
<link href="../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<!-- Sweet Alert -->
<link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/zxma.css">
<link href="../css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">
<link href="../css/animate.min.css" rel="stylesheet">
<link href="../css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_self">
<script type="text/javascript" src="../js/eye-all.js"></script>
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/eye-base.js"></script>
<script type="text/javascript">
	function downsubm() {
		var btn = document.getElementById("subm");
		btn.style.border = "2px inset";
	}
	function upsubm() {
		var btn = document.getElementById("subm");
		btn.style.border = "0.5px outset";
	/*	var x = document.getElementsByTagName("1a").value;
		alert(x);
		if(=="")
			return;*/
		document.f.submit();
	}
	function downsearch() {
		var btn = document.getElementById("datep");
		btn.style.border = "2px inset";
	}
	function upsearch() {
		var btn = document.getElementById("datep");
		btn.style.border = "1px outset";
		var foredate = document.getElementById("date1").value;
		var afterdate = document.getElementById("date2").value;
		if (foredate == "" || afterdate == "" || afterdate < foredate) {
			alert("请正确的选择日期！");
			return;
		}
		document.pickdate.submit();
	}
	
	function addAtcResult() {
		var mess = "${addactstatus}";
		if(mess!="")
			alert(mess);
	}
	
</script>
</head>
<body onload="addAtcResult()">
	<div class="datepick">
		<span>选择日期范围</span>
		<div>
			<form action="add_join_act!getPubAct" method="post" name="pickdate">
				从:<input type="text" id="date1" style="width: 116px;" onClick="eye.datePicker.show(this);" readonly="readonly" value="${foredate }" name="foredate" />到:<input type="text" id="date2" style="width: 116px;" onClick="eye.datePicker.show(this);" readonly="readonly" value="${afterdate }" name="afterdate" />
				&nbsp;&nbsp;<input type="button" id="datep" value="查寻" title="点击查询" onmousedown="downsearch()" onmouseup="upsearch()">
			</form>
		</div>
	</div>
	<div class="chooseact">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<h5>
							<small></small>
						</h5>
						<div class="ibox-content"></div>
						<div class="example">
							<form method="post" name="f" action="add_join_act!addJoinedAct">
								<table id="tb" class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
											<td class="sorting_asc" style="display: none">ID</td>
											<td class="sorting_asc">活动名称</td>
											<td class="sorting_asc">参与人员</td>
											<td class="sorting_asc">活动日期</td>
											<td class="sorting_asc">添加</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="vap" items="${sreqvapm }">
											<tr>
												<td style="display: none">${vap.actPubId }</td>
												<td>${vap.vacollectiveAct.actName }</td>
												<td>${vap.vacollectiveAct.attendee }</td>
												<td>${vap.actDate }</td>
												<td><input type="radio" name="vapm.actPubId" id="1a" value="${vap.actPubId }"></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>								
							</form>
							<input type="submit" value="选定提交" id="subm" onmousedown="downsubm()" onmouseup="upsubm()">
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="../js/jquery.min.js?v=2.1.4"></script>
	<script src="../js/bootstrap.min.js?v=3.3.5"></script>
	<script src="../js/plugins/jeditable/jquery.jeditable.js"></script>
	<script src="../js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="../js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="../js/content.min.js?v=1.0.0"></script>
	<script src="../js/plugins/iCheck/icheck.min.js"></script>
	<script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
	<script>
		$(document).ready(function() {
			$(".dataTables-example").dataTable();
			var oTable = $("#editable").dataTable();
			oTable.$("td").editable("../example_ajax.php", {
				"callback" : function(sValue, y) {
					var aPos = oTable.fnGetPosition(this);
					oTable.fnUpdate(sValue, aPos[0], aPos[1])
				},
				"submitdata" : function(value, settings) {
					return {
						"row_id" : this.parentNode.getAttribute("id"),
						"column" : oTable.fnGetPosition(this)[2]
					}
				},
				"width" : "90%",
				"height" : "100%"
			})
		});
		function fnClickAddRow() {
			$("#editable").dataTable()
					.fnAddData(
							[ "Custom row", "New row", "New row", "New row",
									"New row" ])
		};
		$(document).ready(function() {
			$(".i-checks").iCheck({
				checkboxClass : "icheckbox_square-green",
				radioClass : "iradio_square-green",
			})
		});
	</script>
	<script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
</body>
</html>