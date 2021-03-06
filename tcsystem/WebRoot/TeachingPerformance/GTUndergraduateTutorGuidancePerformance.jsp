<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java"
	import="java.util.*,com.nuaa.ec.model.ProjectType" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<%
	request.setAttribute("tftermList", StoreData.getTftermList());
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base target="_self">
</head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>本科生导师指导绩效管理</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

<!-- Sweet Alert -->
<link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<!-- Data Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css?v=4.0.0" rel="stylesheet">
<link href="css/Audit_CSS/teachingPerformance.css" rel="stylesheet">

<base target="_blank">
<script type="text/javascript">
    	function DoCheck() {
    		var res = '${operstatus}';
    		//alert(addres);
			switch (res){
				case '-1':alert("操作失败 fail !!!");
				break;
				case '1':alert("添加成功!");
				break;
				default: break;
			}
		}
    </script>
</head>

<body class="gray-bg" onload="DoCheck()">

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							本科生导师指导绩效管理 <small></small>
						</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> <a class="dropdown-toggle" data-toggle="dropdown"
								href="table_data_tables.html#"> <i class="fa fa-wrench"></i>
							</a>
							<ul class="dropdown-menu dropdown-user">
								<li><a href="table_data_tables.html#">选项1</a></li>
								<li><a href="table_data_tables.html#">选项2</a></li>
							</ul>
							<a class="close-link"> <i class="fa fa-times"></i>
							</a>
						</div>
					</div>
					<div class="ibox-content">
						<button class="btn  btn-primary openaddm" type="submit"
							data-backdrop="true" data-toggle="modal" data-target="#add" id="submitNewRecord">
							<strong>新增本科生导师指导绩效</strong>
						</button>
						<br>
						<br>
						<form action="GTUndergraduateTutorGuidancePerformanceSet!getAllRecord">
							<div>
								<a>每页 <select id="changelength" style="width:80px;height:25px;border-radius:3px;" name="pageSize_GTUTG">
										<c:forEach var="pageSize" items="${pageSizeList }">
											<option value="${pageSize }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pageSize }</option>
										</c:forEach>
								</select>条记录
								</a>
								&nbsp;&nbsp;
								<font color="#337AB7">学期：</font>
								<select id="termSelection" name="termId_GTUTG" style="width:120px;height:25px;border-radius:3px;">
									<option value="">全部学期</option>
									<c:forEach var="tfterm" items="${tftermList }">
										<option value="${tfterm.termId }">${tfterm.term }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<button class="button_set" type="submit" id="AlterPageSize"
								data-backdrop="true" data-toggle="modal">
								<strong>确认</strong>
								</button>
							</div>
						</form>
						<div style="margin-top: 10px;">
							<span><font color="red"><b>说明：</b></font>本绩效录入周期为<font color="red"><b>一学年</b></font>，
							无论在该学年的哪个学期录入绩效记录，日期都默认存储为该学年的
							<font color="red"><b>第一学期</b></font>。
							每学年只有<font color="red"><b>第一学期</b></font>才有用户的绩效记录。
							</span>
						</div>
						<br>
						<div class="example">
							<form method="post" name="f">
								<table id="tb"
									class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
											<td>工号</td>
											<td>姓名</td>
<!-- 											<td>学期</td> -->
											<td>指导学生数量</td>
											<td>得分</td>
											<td style="display: none;">upid</td>
											<td>状态</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="undergraduateTutorGuidPerfUnionTfterm"
											items="${undergraduateTutorGuidancePerformanceUnionTftermList }">
											<tr>
												<!-- 工号 -->
												<td>${undergraduateTutorGuidPerfUnionTfterm.undergraduateTutorGuidancePerformance.teacher.teacherId }</td>
												<!-- 姓名 -->
												<td>${undergraduateTutorGuidPerfUnionTfterm.undergraduateTutorGuidancePerformance.teacher.teacherName }</td>
												<!-- 学期 -->
<!-- 												<td>${undergraduateTutorGuidPerfUnionTfterm.currentTerm.term }</td> -->
												<!-- 指导学生数量 -->
												<td>${undergraduateTutorGuidPerfUnionTfterm.undergraduateTutorGuidancePerformance.studentQuantity }</td>
												<!-- 得分 -->
												<td>${undergraduateTutorGuidPerfUnionTfterm.undergraduateTutorGuidancePerformance.finalScore }</td>
												<!-- upid -->
												<td style="display:none;">${undergraduateTutorGuidPerfUnionTfterm.undergraduateTutorGuidancePerformance.upid }</td>
												<!-- 状态 -->
												<c:if test="${undergraduateTutorGuidPerfUnionTfterm.undergraduateTutorGuidancePerformance.checkOut ==1 }">
													<td style="color:blue;">待审核</td>
												</c:if>
												<c:if test="${undergraduateTutorGuidPerfUnionTfterm.undergraduateTutorGuidancePerformance.checkOut ==3 }">
													<td style="color: green;">审核通过</td>
												</c:if>
												<c:if test="${undergraduateTutorGuidPerfUnionTfterm.undergraduateTutorGuidancePerformance.checkOut ==2 }">
													<td style="color: red;">审核未通过</td>
												</c:if>
												<!-- 操作 -->
												<td>
													<a  class="btn btn-primary btn-sm update" data-toggle="modal" data-target="#update" id="submitUpdateRecord">修改</a>
													<a  class="btn btn-primary btn-sm deleteInfo" data-toggle="modal">删除</a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
							<div style="text-align: center;">
								(共查询到${sessionScope.recordNumber_GTUTG }记录)&nbsp;&nbsp;&nbsp;&nbsp; 第${pageIndex }/${sessionScope.pageCount_GTUTG }页&nbsp;&nbsp;&nbsp;
								<a class="comphref"
									href="GTUndergraduateTutorGuidancePerformanceSet!getAllRecord">首页</a>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageIndex>1 }">
									<a class="comphref"
										href="GTUndergraduateTutorGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageIndex-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:forEach var="index" begin="${pageIndex }" end="${pageIndex+4 }" step="1">
									<c:if test="${index<pageCount_GTUTG }">
										<a class="comphref"
										href="GTUndergraduateTutorGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${index }">${index }</a>&nbsp;&nbsp;&nbsp;
									</c:if>
								</c:forEach>
								<c:if test="${pageIndex<pageCount_GTUTG }">
									<a class="comphref"
										href="GTUndergraduateTutorGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageIndex+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<a class="comphref"
									href="GTUndergraduateTutorGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageCount_GTUTG }">尾页</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 修改 -->
		<div id="update" class="modal fade" aria-hidden="true" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div class="row">
							<h3 class="m-t-none m-b">修改本科生导师指导绩效</h3>
							<form role="form" name="" id="updateInfoForm"
								action=""
								method="post">
								<div class="form-group">
									<label>ID</label>&nbsp;<label></label> <input id="up_upid"
										type="text" class="form-control doCheck_update" name="undergraduateTutorGuidancePerformance.upid" 
										value="">
								</div>
								<div class="form-group">
									<label>学期:</label>&nbsp;<label></label> 
									<select id="up_termSelection" name="undergraduateTutorGuidancePerformance.termId" class="form-control">
										<c:forEach var="tfterm" items="${tftermList }">
											<option value="${tfterm.termId }">${tfterm.term }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>指导学生数量:</label>&nbsp;<label></label> <input
										id="up_studentQuantity" type="text"
										class="form-control doCheck_update"
										name="undergraduateTutorGuidancePerformance.studentQuantity"
										>
								</div>
							</form>
							<div>
								<button type="button"
									class="btn btn-outline btn-primary pull-right m-t-n-xs"
									data-dismiss="modal">关闭</button>
								<button id="updateInfo"
									class="btn  btn-primary pull-left m-t-n-xs subcheck"
									type="submit">
									<i class="fa fa-check"></i> <strong>提交</strong>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- 添加 -->
		<div id="add" class="modal fade" aria-hidden="true" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div class="row">
							<h3 class="m-t-none m-b">添加参与学生活动绩效</h3>
							<form role="form" name="adds" id="addInfoForm"
								action=""
								method="post">
								<div class="form-group">
									<label>学期:</label>&nbsp;<label></label><br>
									<select id="add_termSelection" name="undergraduateTutorGuidancePerformance.termId" class="form-control">
										<c:forEach var="tfterm" items="${tftermList }">
											<option value="${tfterm.termId }">${tfterm.term }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>指导学生数量:</label>&nbsp;<label></label> <input
										id="add_studentQuantity" type="text"
										class="form-control doCheck_add"
										name="undergraduateTutorGuidancePerformance.studentQuantity"
										value="">
								</div>
							</form>
							<div>
								<button type="button"
									class="btn btn-outline btn-primary pull-right m-t-n-xs"
									data-dismiss="modal">关闭</button>
								<button id="submitAddInfo"
									class="btn  btn-primary pull-left m-t-n-xs subcheck"
									type="submit">
									<i class="fa fa-check"></i> <strong>提交</strong>
								</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<script src="js/jquery.min.js?v=2.1.4"></script>
		<script src="js/bootstrap.min.js?v=3.3.5"></script>
		<script src="js/plugins/jeditable/jquery.jeditable.js"></script>
		<script src="js/content.min.js?v=1.0.0"></script>
		<script src="js/plugins/iCheck/icheck.min.js"></script>
		<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
<!-- 		<script src="js/PublicCheck/PUB_SET.js"></script> -->
		<script src="js/PublicCheck/formFieldController.js"></script>
	<script type="text/javascript">
	$().ready(function(){
		$("#changelength option[value='${sessionScope.pageSize_GTUTG}']").attr("selected",true);
		$("#termSelection option[value='${sessionScope.termId_GTUTG}']").attr("selected",true);
		$("#add_termSelection option[value='${sessionScope.termId_GTUTG}']").attr("selected",true);
		$("#up_termSelection option[value='${sessionScope.termId_GTUTG}']").attr("selected",true);
	});
	$("#submitAddInfo").click(function(){
		//执行提交表单
		submitAddedInfo("GTUndergraduateTutorGuidancePerformanceSet", "insertRecord", "getAllRecord");
	});
    $('#updateInfo').click(function() {
    	submitUpdatedInfo("GTUndergraduateTutorGuidancePerformanceSet", "updateRecord", "getAllRecord");
	});
    $('.update').click(function() {
		$('#up_upid').val($(this).parent().parent()[0].cells[4].innerHTML);
		$('#up_studentQuantity').val($(this).parent().parent()[0].cells[2].innerHTML);
// 		var currentTerm=$(this).parent().parent()[0].cells[3].innerHTML;
// 		$("#up_termSelection option").each(function(){
// 			if($(this).text()==currentTerm){
// 				$(this).prop("selected",true);
// 			}
// 		});
	});
    $('.deleteInfo').click(function() {
		var upid = $(this).parent().parent()[0].cells[4].innerHTML;
		deleteRecord({
			"undergraduateTutorGuidancePerformance.upid":upid
		}, "GTUndergraduateTutorGuidancePerformanceSet", "deleteRecord",
		"getAllRecord?isDivided=false");
	});
    </script>
	<s:debug></s:debug>
</body>
</html>