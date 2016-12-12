<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java"
	import="java.util.*,com.nuaa.ec.model.ProjectType" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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


<title>学生竞赛指导绩效管理</title>

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
							校外实践指导绩效管理 <small></small>
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
							<strong>新增校外实践指导绩效</strong>
						</button>
						<br>
						<br>
						<form action="GTOffCampusPracticeGuidancePerformanceSet!getAllRecord">
							<div>
								<a>每页 <select id="changelength" style="width:80px;height:25px;border-radius:3px;" name="pageSize_GTOCP">
										<c:forEach var="pageSize" items="${pageSizeList }">
											<option value="${pageSize }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pageSize }</option>
										</c:forEach>
								</select>条记录
								</a>
								&nbsp;&nbsp;
								<font color="#337AB7">学期：</font>
								<select id="termSelection" name="termId_GTOCP" style="width:120px;height:25px;border-radius:3px;">
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
						<br>
						<div class="example">
							<form method="post" name="f">
								<table id="tb"
									class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
											<td>指导编号</td>
											<td>工号</td>
											<td>姓名</td>
											<td>当前学期</td>
											<td>项目名称</td>
											<td>基地数量</td>
											<td>指导总时长</td>
											<td>绩效级别</td>
											<td>得分</td>
											<td style="display: none;">upid</td>
											<td>状态</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="offCampusPracGuidPerfUnionTfterm"
											items="${offCampusPracGuidPerfUnionTftermList }">
											<tr>
												<!-- 指导编号 -->
												<td>${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.offguidanceId }</td>
												<!-- 工号 -->
												<td>${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.teacher.teacherId }</td>
												<!-- 姓名 -->
												<td>${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.teacher.teacherName }</td>
												<!-- 当前学期 -->
												<td>${offCampusPracGuidPerfUnionTfterm.currentTerm.term }</td>
												<!-- 项目名称 -->
												<td>${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.projectName }</td>
												<!-- 基地数量 -->
												<td>${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.quantityUnit }</td>
												<!-- 指导总时长-->
												<td>${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.sumhours }</td>
												<!-- 绩效级别 -->
												<td>${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.tfoffCampusPracticeGuidanceLevel.projectType }</td>
												<!-- 得分 -->
												<td>${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.finalScore }</td>
												<!-- upid -->
												<td style="display:none;">${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.upid }</td>
												<!-- 状态 -->
												<c:if test="${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.checkOut ==1 }">
													<td style="color:blue;">待审核</td>
												</c:if>
												<c:if test="${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.checkOut ==3 }">
													<td style="color: green;">审核通过</td>
												</c:if>
												<c:if test="${offCampusPracGuidPerfUnionTfterm.offCampusPracticeGuidancePerformance.checkOut ==2 }">
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
								(共查询到${sessionScope.recordNumber_GTOCP }记录)&nbsp;&nbsp;&nbsp;&nbsp; 第${pageIndex }/${sessionScope.pageCount_GTOCP }页&nbsp;&nbsp;&nbsp;
								<a class="comphref"
									href="GTOffCampusPracticeGuidancePerformanceSet!getAllRecord">首页</a>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageIndex>1 }">
									<a class="comphref"
										href="GTOffCampusPracticeGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageIndex-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:forEach var="index" begin="${pageIndex }" end="${pageIndex+4 }" step="1">
									<c:if test="${index<pageCount_GTOCP }">
										<a class="comphref"
										href="GTOffCampusPracticeGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${index }">${index }</a>&nbsp;&nbsp;&nbsp;
									</c:if>
								</c:forEach>
								<c:if test="${pageIndex<pageCount_GTOCP }">
									<a class="comphref"
										href="GTOffCampusPracticeGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageIndex+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<a class="comphref"
									href="GTOffCampusPracticeGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageCount_GTOCP }">尾页</a>
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
							<h3 class="m-t-none m-b">修改校外实践指导绩效</h3>
							<form role="form" name="" id="updateInfoForm"
								action=""
								method="post">
								<div class="form-group" style="display: none;">
									<label>ID</label>&nbsp;<label></label> <input id="up_upid"
										type="text" class="form-control doCheck_update" name="offCampusPracticeGuidancePerformance.upid" 
										value="">
								</div>
								<div class="form-group">
									<label>指导编号</label>&nbsp;<label></label> <input id="up_offguidanceId"
										type="text" class="form-control doCheck_update" name="offCampusPracticeGuidancePerformance.offguidanceId" 
										value="" readonly="readonly">
								</div>
								<div class="form-group">
									<label>学期:</label>&nbsp;<label></label> 
									<select id="up_termSelection" name="offCampusPracticeGuidancePerformance.termId" class="form-control">
										<c:forEach var="tfterm" items="${tftermList }">
											<option value="${tfterm.termId }">${tfterm.term }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>项目名称</label>&nbsp;<label></label> <input id="up_projectName"
										type="text" class="form-control doCheck_update" name="offCampusPracticeGuidancePerformance.projectName" 
										value="">
								</div>
								<div class="form-group">
									<label>绩效级别:</label>&nbsp;<label></label> 
									<select
										id="up_projectId" class="form-control"
										name="offCampusPracticeGuidanceLevel.projectId">
										<c:forEach var="tfoffCampusPracticeGuidanceLevel"
											items="${tfoffCampusPracticeGuidanceLevelList }">
											<option value="${tfoffCampusPracticeGuidanceLevel.projectId }">${tfoffCampusPracticeGuidanceLevel.projectType }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group" id="up_baseQuantityDiv">
									<label>基地数量:</label>&nbsp;<label></label> <input
										id="up_quantityUnit" type="text"
										class="form-control doCheck_update"
										name="offCampusPracticeGuidancePerformance.quantityUnit"
										value="">
								</div>
								<div class="form-group" id="up_guidanceSumHoursDiv">
									<label>指导总时长:</label>&nbsp;<label></label> <input
										id="up_sumhours" type="text"
										class="form-control doCheck_update"
										name="offCampusPracticeGuidancePerformance.sumhours"
										value="" disabled="disabled">
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
							<h3 class="m-t-none m-b">添加校外实践指导绩效</h3>
							<form role="form" name="adds" id="addInfoForm"
								action=""
								method="post">
								<div class="form-group">
									<label>学期:</label>&nbsp;<label></label><br>
									<select id="add_termSelection" name="offCampusPracticeGuidancePerformance.termId" class="form-control">
										<c:forEach var="tfterm" items="${tftermList }">
											<option value="${tfterm.termId }">${tfterm.term }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>项目名称:</label>&nbsp;<label></label> <input
										id="projectName" type="text"
										class="form-control doCheck_add"
										name="offCampusPracticeGuidancePerformance.projectName"
										value="">
								</div>
								<div class="form-group">
									<label>绩效级别:</label>&nbsp;<label></label> 
									<select
										id="add_projectId" class="form-control"
										name="offCampusPracticeGuidanceLevel.projectId">
										<c:forEach var="tfoffCampusPracticeGuidanceLevel"
											items="${tfoffCampusPracticeGuidanceLevelList }">
											<option value="${tfoffCampusPracticeGuidanceLevel.projectId }">${tfoffCampusPracticeGuidanceLevel.projectType }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group" id="baseQuantityDiv">
									<label>基地数量:</label>&nbsp;<label></label> <input
										id="add_quantityUnit" type="text"
										class="form-control doCheck_add"
										name="offCampusPracticeGuidancePerformance.quantityUnit"
										value="" disabled="disabled">
								</div>
								<div class="form-group" id="guidanceSumHoursDiv">
									<label>指导总时长:</label>&nbsp;<label></label> <input
										id="add_sumhours" type="text"
										class="form-control"
										name="offCampusPracticeGuidancePerformance.sumhours"
										value="" disabled="disabled" style="display: none;">
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
	$("#add_projectId").change(function(){
		if($(this).val().trim()=='TFOCP1'){
			$("#add_quantityUnit").removeAttr("disabled");
			$("#add_quantityUnit").removeAttr("style");
			$("#add_quantityUnit").addClass("doCheck_add");
			$("#add_sumhours").attr("disabled","disabled");
			$("#add_sumhours").attr("style","border:1px solid red;");
			$("#add_sumhours").removeClass("doCheck_add");
		}else{
			$("#add_sumhours").removeAttr("disabled");
			$("#add_sumhours").removeAttr("style");
			$("#add_sumhours").addClass("doCheck_add");
			$("#add_quantityUnit").attr("disabled","disabled");
			$("#add_quantityUnit").attr("style","border:1px solid red;");
			$("#add_quantityUnit").removeClass("doCheck_add");
		}
	});
	$("#submitNewRecord").click(function(){
		$("#add_quantityUnit").removeAttr("disabled");
		$("#add_quantityUnit").removeAttr("style");
    	$("#add_sumhours").attr("disabled");
    	$("#add_sumhours").attr("style","border:1px solid red;");
    	$("#add_quantityUnit").addClass("doCheck_add");
	});
	/* 说明：更新窗口的id前缀是add_，增加是add_ */
	$().ready(function(){
		$("#changelength option[value='${sessionScope.pageSize_GTOCP}']").attr("selected",true);
		$("#termSelection option[value='${sessionScope.termId_GTOCP}']").attr("selected",true);
		$("#add_termSelection option[value='${sessionScope.termId_GTOCP}']").attr("selected",true);
		$("#up_termSelection option[value='${sessionScope.termId_GTOCP}']").attr("selected",true);
	});
	$("#submitAddInfo").click(function(){
		//执行提交表单
		submitAddedInfo("GTOffCampusPracticeGuidancePerformanceSet", "insertRecord", "getAllRecord");
	});
    $('#updateInfo').click(function() {
    	submitUpdatedInfo("GTOffCampusPracticeGuidancePerformanceSet", "updateRecord", "getAllRecord");
	});
    $("#up_projectId").change(function(){
    	if($(this).val().trim()=='TFOCP1'){
			$("#up_quantityUnit").removeAttr("disabled");
			$("#up_quantityUnit").removeAttr("style");
			$("#up_quantityUnit").addClass("doCheck_update");
			$("#up_sumhours").attr("disabled","disabled");
			$("#up_sumhours").attr("style","border:1px solid red;");
			$("#up_sumhours").removeClass("doCheck_update");
		}else{
			$("#up_sumhours").removeAttr("disabled");
			$("#up_sumhours").removeAttr("style");
			$("#up_sumhours").addClass("doCheck_update");
			$("#up_quantityUnit").attr("disabled","disabled");
			$("#up_quantityUnit").attr("style","border:1px solid red;");
			$("#up_quantityUnit").removeClass("doCheck_update");
		}
    });
    $('.update').click(function() {
		$('#up_upid').val($(this).parent().parent()[0].cells[9].innerHTML);
		$('#up_offguidanceId').val($(this).parent().parent()[0].cells[0].innerHTML);
		$('#up_projectName').val($(this).parent().parent()[0].cells[4].innerHTML);
		$('#up_quantityUnit').val($(this).parent().parent()[0].cells[5].innerHTML);
		$('#up_sumhours').val($(this).parent().parent()[0].cells[6].innerHTML);
		var temp=$(this).parent().parent()[0].cells[7].innerHTML;
		$("#up_projectId option").each(function(){
			if($(this).text()==temp.trim()){
				$(this).prop("selected",true);//这里用attr会有问题。
				if($(this).val()=='TFOCP1'){//说明是基地类别
					$("#up_quantityUnit").removeAttr("disabled");
					$("#up_quantityUnit").removeAttr("style");
					$("#up_quantityUnit").addClass("doCheck_update");
					$("#up_sumhours").attr("disabled","disabled");
					$("#up_sumhours").attr("style","border:1px solid red;");
					$("#up_sumhours").removeClass("doCheck_update");
				}else{
					$("#up_sumhours").removeAttr("disabled");
					$("#up_sumhours").removeAttr("style");
					$("#up_sumhours").addClass("doCheck_update");
					$("#up_quantityUnit").attr("disabled","disabled");
					$("#up_quantityUnit").attr("style","border:1px solid red;");
					$("#up_quantityUnit").removeClass("doCheck_update");
				}
			}
		});
		var currentTerm=$(this).parent().parent()[0].cells[3].innerHTML;
		$("#up_termSelection option").each(function(){
			if($(this).text()==currentTerm){
				$(this).prop("selected",true);
			}
		});
	});
    $('.deleteInfo').click(function() {
		var upid = $(this).parent().parent()[0].cells[9].innerHTML;
		deleteRecord({
			"offCampusPracticeGuidancePerformance.upid":upid
		}, "GTOffCampusPracticeGuidancePerformanceSet", "deleteRecord",
		"getAllRecord?isDivided=false");
	});
    </script>
	<s:debug></s:debug>
</body>
</html>