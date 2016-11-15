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
							实践创新指导绩效管理 <small></small>
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
						<form action="ATStudentCompetitionGuidancePerformanceSet!getAllRecord">
							<div>
								<a>每页 <select id="changelength" style="width:80px;height:25px;border-radius:3px;" name="pageSize_ATSCG">
										<c:forEach var="pageSize" items="${pageSizeList }">
											<option value="${pageSize }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pageSize }</option>
										</c:forEach>
								</select>条记录
								</a>
								&nbsp;&nbsp;
								<font color="#337AB7">学期：</font>
								<select id="termSelection" name="termId_ATSCG" style="width:120px;height:25px;border-radius:3px;">
									<option value="">全部学期</option>
									<c:forEach var="tfterm" items="${tftermList }">
										<option value="${tfterm.termId }">${tfterm.term }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="#337AB7">检索条件：</font>
								<input type="text" id="searchCondition_ATSCG" name="searchCondition_ATSCG"  placeholder="请输入竞赛的名称"
								 style="width:150px;height:25px;border-radius:3px;border:1px solid #A9A9A9;"/>
								 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
											<td>竞赛编号</td>
											<td>工号</td>
											<td>姓名</td>
											<td>当前学期</td>
											<td>竞赛名称</td>
											<td>竞赛级别</td>
											<td>获奖级别</td>
											<td>得分</td>
											<td style="display: none;">upid</td>
											<td>状态</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="studCompetGuidPerfUnionTfterm"
											items="${studCompetGuidPerfUnionTftermList }">
											<tr>
												<!-- 竞赛编号 -->
												<td>${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.competitionId }</td>
												<!-- 工号 -->
												<td>${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.teacher.teacherId }</td>
												<!-- 姓名 -->
												<td>${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.teacher.teacherName }</td>
												<!-- 当前学期 -->
												<td>${studCompetGuidPerfUnionTfterm.currentTerm.term }</td>
												<!-- 竞赛名称 -->
												<td>${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.competitionName }</td>
												<!-- 竞赛级别 -->
												<td>${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceCompetitionType.competitionType }</td>
												<!-- 获奖级别 -->
												<td>${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.tfstudentCompetitionGuidanceScore.tfstudentCompetitionGuidanceRewardLevel.rewardLevel }</td>
												<!-- 得分 -->
												<td>${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.finalScore }</td>
												<!-- upid -->
												<td style="display:none;">${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.upid }</td>
												<!-- 状态 -->
												<c:if test="${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.checkOut ==0 }">
													<td style="color:blue;">待审核</td>
												</c:if>
												<c:if test="${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.checkOut ==1 }">
													<td style="color: green;">审核通过</td>
												</c:if>
												<c:if test="${studCompetGuidPerfUnionTfterm.studentCompetGuidPerf.checkOut ==2 }">
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
								(共查询到${sessionScope.recordNumber_ATSCG }记录)&nbsp;&nbsp;&nbsp;&nbsp; 第${pageIndex }/${sessionScope.pageCount_ATSCG }页&nbsp;&nbsp;&nbsp;
								<a class="comphref"
									href="ATStudentCompetitionGuidancePerformanceSet!getAllRecord">首页</a>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageIndex>1 }">
									<a class="comphref"
										href="ATStudentCompetitionGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageIndex-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:forEach var="index" begin="${pageIndex }" end="${pageIndex+4 }" step="1">
									<c:if test="${index<pageCount_ATSCG }">
										<a class="comphref"
										href="ATStudentCompetitionGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${index }">${index }</a>&nbsp;&nbsp;&nbsp;
									</c:if>
								</c:forEach>
								<c:if test="${pageIndex<pageCount_ATSCG }">
									<a class="comphref"
										href="ATStudentCompetitionGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageIndex+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<a class="comphref"
									href="ATStudentCompetitionGuidancePerformanceSet!getAllRecord?isDivided=true&pageIndex=${pageCount_ATSCG }">尾页</a>
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
							<h3 class="m-t-none m-b">修改学生竞赛指导绩效</h3>
							<form role="form" name="" id="updateInfoForm"
								action=""
								method="post">
								<div class="form-group" style="display: none;">
									<label>ID</label>&nbsp;<label></label> <input id="up_upid"
										type="text" class="form-control doCheck_update" name="studentCompetGuidPerf.upid" 
										value="">
								</div>
								<div class="form-group">
									<label>教师工号</label>&nbsp;<label></label> <input id="up_teacherId"
										type="text" class="form-control doCheck_update" name="teacher.teacherId" 
										value="" readonly="readonly">
								</div>
								<div class="form-group" style="display: none;">
									<label>竞赛ID</label>&nbsp;<label></label> <input id="up_competitionId"
										type="text" class="form-control doCheck_update" name="studentCompetGuidPerf.competitionId" 
										value="">
								</div>
								<div class="form-group">
									<label>学期:</label>&nbsp;<label></label> 
									<select id="up_termSelection" name="studentCompetGuidPerf.termId" class="form-control">
										<c:forEach var="tfterm" items="${tftermList }">
											<option value="${tfterm.termId }">${tfterm.term }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>竞赛名称:</label>&nbsp;<label></label> <input
										id="up_competitionName" type="text"
										class="form-control doCheck_update"
										name="studentCompetGuidPerf.competitionName"
										>
								</div>
								<div class="form-group">
									<label>竞赛级别:</label>&nbsp;<label></label> 
									<select
										id="up_competitionTypeId" class="form-control"  onchange="checkCompetitionTypeSelection('up')"
										name="studentCompetGuidanceCompetitionType.competitionTypeId">
										<c:forEach var="studentCompetitionGuidanceCompetitionType"
											items="${studentCompetitionGuidanceCompetitionTypeList }">
											<option value="${studentCompetitionGuidanceCompetitionType.competitionTypeId }">${studentCompetitionGuidanceCompetitionType.competitionType }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>获奖级别:</label>&nbsp;<label></label> 
									<select
										id="up_rewardLevelId" class="form-control" onchange="checkRewardLevelSelection('up')"
										name="studentCompetGuidRewardLevel.rewardLevelId">
										<c:forEach var="studentCompetitionGuidanceRewardLevel"
											items="${studentCompetitionGuidanceRewardLevelList }">
											<option value="${studentCompetitionGuidanceRewardLevel.rewardLevelId }">${studentCompetitionGuidanceRewardLevel.rewardLevel }</option>
										</c:forEach>
									</select>
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
		<script src="js/jquery.min.js?v=2.1.4"></script>
		<script src="js/bootstrap.min.js?v=3.3.5"></script>
		<script src="js/plugins/jeditable/jquery.jeditable.js"></script>
		<script src="js/content.min.js?v=1.0.0"></script>
		<script src="js/plugins/iCheck/icheck.min.js"></script>
		<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
<!-- 		<script src="js/PublicCheck/PUB_SET.js"></script> -->
		<script src="js/PublicCheck/formFieldController.js"></script>
	<script type="text/javascript">
	/* 不同的竞赛级别拥有的获奖级别不尽相同，
	所以加以控制，如果不控制，用户可能勾选错误数据导致，插入数据会产生异常
	下面的第一个函数是用户先勾选竞赛级别然后勾写获奖级别的情况
	第二个是用户先勾选获奖级别然后在勾选竞赛级别的情况，一般第二种情况少见*/
	function checkCompetitionTypeSelection(flag){
		if($("#"+flag+"_competitionTypeId option:selected").val()=='TFCOMPT1'){
			$("#"+flag+"_rewardLevelId option")[0].removeAttribute("disabled");
			$("#"+flag+"_rewardLevelId option")[1].removeAttribute("disabled");
			$("#"+flag+"_rewardLevelId option")[2].removeAttribute("disabled");
		}else if($("#"+flag+"_competitionTypeId option:selected").val()=='TFCOMPT2'){
			$("#"+flag+"_rewardLevelId option")[0].setAttribute("disabled","disabled");
			$("#"+flag+"_rewardLevelId option")[1].removeAttribute("disabled");
			$("#"+flag+"_rewardLevelId option")[2].removeAttribute("disabled");
		}else if($("#"+flag+"_competitionTypeId option:selected").val()=='TFCOMPT3'){
			$("#"+flag+"_rewardLevelId option")[0].setAttribute("disabled","disabled");
			$("#"+flag+"_rewardLevelId option")[1].removeAttribute("disabled");
			$("#"+flag+"_rewardLevelId option")[2].removeAttribute("disabled");
		}else{
			$("#"+flag+"_rewardLevelId option")[0].setAttribute("disabled","disabled");
			$("#"+flag+"_rewardLevelId option")[1].setAttribute("disabled","disabled");
			$("#"+flag+"_rewardLevelId option")[2].removeAttribute("disabled");
		}
	}
	function checkRewardLevelSelection(flag){
		if($("#"+flag+"_rewardLevelId option:selected").val()=='TFREWARD1'){
			$("#"+flag+"_competitionTypeId option")[0].removeAttribute("disabled");
			$("#"+flag+"_competitionTypeId option")[1].setAttribute("disabled","disabled");
			$("#"+flag+"_competitionTypeId option")[2].setAttribute("disabled","disabled");
			$("#"+flag+"_competitionTypeId option")[3].setAttribute("disabled","disabled");
		}else if($("#"+flag+"_rewardLevelId option:selected").val()=='TFREWARD2'){
			$("#"+flag+"_competitionTypeId option")[0].removeAttribute("disabled");
			$("#"+flag+"_competitionTypeId option")[1].removeAttribute("disabled");
			$("#"+flag+"_competitionTypeId option")[2].removeAttribute("disabled");
			$("#"+flag+"_competitionTypeId option")[3].setAttribute("disabled","disabled");
		}else{
			$("#"+flag+"_competitionTypeId option")[0].removeAttribute("disabled");
			$("#"+flag+"_competitionTypeId option")[1].removeAttribute("disabled");
			$("#"+flag+"_competitionTypeId option")[2].removeAttribute("disabled");
			$("#"+flag+"_competitionTypeId option")[3].removeAttribute("disabled");
		}
	}
	/* 说明：更新窗口的id前缀是add_，增加是add_ */
	$().ready(function(){
		$("#changelength option[value='${sessionScope.pageSize_ATSCG}']").attr("selected",true);
		$("#termSelection option[value='${sessionScope.termId_ATSCG}']").attr("selected",true);
		$("#add_termSelection option[value='${sessionScope.termId_ATSCG}']").attr("selected",true);
		$("#up_termSelection option[value='${sessionScope.termId_ATSCG}']").attr("selected",true);
		$("#searchCondition_ATSCG").val('${sessionScope.searchCondition_ATSCG}');
	});
	function resetSelection(){
		$("#add_competitionTypeId option").each(function(){
    		$(this).get(0).removeAttribute("disabled");
    	});
    	$("#add_rewardLevelId option").each(function(){
    		$(this).get(0).removeAttribute("disabled");
    	});
	}
	$("#submitAddInfo").click(function(){
		//执行提交表单
		submitAddedInfo("ATStudentCompetitionGuidancePerformanceSet", "insertRecord", "getAllRecord");
	});
    $('#updateInfo').click(function() {
    	submitUpdatedInfo("ATStudentCompetitionGuidancePerformanceSet", "updateRecord", "getAllRecord");
	});
    $('.update').click(function() {
    	$("#up_competitionTypeId option").each(function(){
    		$(this).get(0).removeAttribute("disabled");
    	});
    	$("#up_rewardLevelId option").each(function(){
    		$(this).get(0).removeAttribute("disabled");
    	});
		$('#up_upid').val($(this).parent().parent()[0].cells[8].innerHTML);
		$('#up_competitionId').val($(this).parent().parent()[0].cells[0].innerHTML);
		$('#up_competitionName').val($(this).parent().parent()[0].cells[4].innerHTML);
		$('#up_teacherId').val($(this).parent().parent()[0].cells[1].innerHTML);
		var temp=$(this).parent().parent()[0].cells[5].innerHTML;
		$("#up_competitionTypeId option").each(function(){
			if($(this).text()==temp.trim()){
				$(this).prop("selected",true);//这里用attr会有问题。
			}
		});
		var temp2=$(this).parent().parent()[0].cells[6].innerHTML;
		$("#up_rewardLevelId option").each(function(){
			if($(this).text()==temp2.trim()){
				$(this).prop("selected",true);//这里用attr会有问题。
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
		var upid = $(this).parent().parent()[0].cells[8].innerHTML;
		deleteRecord({
			"studentCompetGuidPerf.upid":upid
		}, "ATStudentCompetitionGuidancePerformanceSet", "deleteRecord",
		"getAllRecord?isDivided=false");
	});
    </script>
	<s:debug></s:debug>
</body>
</html>