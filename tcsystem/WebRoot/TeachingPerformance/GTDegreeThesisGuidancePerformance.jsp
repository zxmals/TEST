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
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<title>periodicalTypeSet</title>

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
							学术论文指导绩效管理 <small></small>
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
							data-backdrop="true" data-toggle="modal" data-target="#add">
							<strong>新增学术论文指导绩效</strong>
						</button>
						<br>
						<br>
						<form action="GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord">
							<div>
								<a>每页 <select id="changelength" style="width:80px;height:25px;border-radius:3px;" name="pageSize_GTDTG">
										<c:forEach var="pageSize" items="${pageSizeList }">
											<option value="${pageSize }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pageSize }</option>
										</c:forEach>
								</select>条记录
								</a>
								&nbsp;&nbsp;
								<font color="#337AB7">学期：</font>
								<select id="termSelection" name="termId_GTDTG" style="width:120px;height:25px;border-radius:3px;">
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
											<td>ID</td>
											<td>姓名</td>
											<td>工号</td>
											<td>学期</td>
											<td>学位论文名称</td>
											<td>奖励级别</td>
											<td>分数</td>
											<td>状态</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="degreeThesisGuidancePerfUnionTfterm"
											items="${degreeThesisGuidancePerfUnionTftermList }">
											<tr>
												<!-- ID -->
												<td>${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.degreeThesisId }</td>
												<!-- 姓名 -->
												<td>${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.teacher.teacherName }</td>
												<!-- 工号 -->
												<td>${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.teacher.teacherId }</td>
												<!-- 学期 -->
												<td>${degreeThesisGuidancePerfUnionTfterm.currentTerm.term }</td>
												<!-- 学位论文名称 -->
												<td>${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.degreeThesisnName }</td>
												<!-- 奖励级别 -->
												<td>${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.tfdegreeThesisGuidanceRewardLevel.rewardLevel }</td>
												<!-- 分数 -->
												<td>${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.finalScore }</td>
												<!-- 状态 -->
												<c:if test="${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.checkOut ==1 }">
													<td style="color:blue;">待审核</td>
												</c:if>
												<c:if test="${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.checkOut ==3 }">
													<td style="color: green;">审核通过</td>
												</c:if>
												<c:if test="${degreeThesisGuidancePerfUnionTfterm.degreeThesisGuidancePerformance.checkOut ==2 }">
													<td style="color: red;">审核未通过</td>
												</c:if>
												<!-- 操作 -->
												<td>
													<a  class="btn btn-primary btn-sm update" data-toggle="modal" data-target="#update" onclick="resetGreen">修改</a>
													<a  class="btn btn-primary btn-sm deleteInfo" data-toggle="modal">删除</a>
<!-- 													<button class="btn btn-primary btn-sm deleteInfo" >删除</button> -->
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
							<div style="text-align: center;">
								(共查询到${sessionScope.recordNumber_GTDTG }记录)&nbsp;&nbsp;&nbsp;&nbsp; 第${pageIndex }/${sessionScope.pageCount_GTDTG }页&nbsp;&nbsp;&nbsp;
								<a class="comphref"
									href="GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord">首页</a>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageIndex>1 }">
									<a class="comphref"
										href="GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord?isDivided=true&pageIndex=${pageIndex-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:forEach var="index" begin="${pageIndex }" end="${pageIndex+4 }" step="1">
									<c:if test="${index<pageCount_GTDTG }">
										<a class="comphref"
										href="GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord?isDivided=true&pageIndex=${index }">${index }</a>&nbsp;&nbsp;&nbsp;
									</c:if>
								</c:forEach>
								<c:if test="${pageIndex<pageCount_GTDTG }">
									<a class="comphref"
										href="GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord?isDivided=true&pageIndex=${pageIndex+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<a class="comphref"
									href="GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord?isDivided=true&pageIndex=${pageCount_GTDTG }">尾页</a>
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
							<h3 class="m-t-none m-b">修改</h3>
							<form role="form" name="" id=""
								action="GTDegreeThesisGuidancePerformanceSet!insertDegreeThesisGuidanceRecord"
								method="post">
								<div class="form-group" style="display: none;">
									<label>ID</label>&nbsp;<label></label> <input id="up_ID"
										type="text" class="form-control nullcheck" name="degreeThesisGuidancePerformance.degreeThesisId" 
										value="">
								</div>
								<div class="form-group">
									<label>学期:</label>&nbsp;<label></label> 
									<select id="up_termSelection" name="degreeThesisGuidancePerformance.termId" style="width:568px;height:33px;border-radius:2px;border:1px #E5E6E7 solid;">
										<c:forEach var="tfterm" items="${tftermList }">
											<option value="${tfterm.termId }">${tfterm.term }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>学位论文名称:</label>&nbsp;<label></label> <input
										id=up_degreeThesisName " type="text"
										class="form-control nullcheck"
										name="degreeThesisGuidancePerformance.degreeThesisnName"
										value="">
								</div>
								<div class="form-group">
									<label>奖励级别:</label>&nbsp;<label></label> 
									<select
										id="up_rewardLevel" class="form-control nullcheck"
										name="degreeThsisGuidanceRewardLevel.rewardLevelId">
										<c:forEach var="degreeGuidanceRewardLevel"
											items="${degreeGuidanceRewardLevels }">
											<option value="${degreeGuidanceRewardLevel.rewardLevelId }">${degreeGuidanceRewardLevel.rewardLevel }</option>
										</c:forEach>
									</select>
								</div>
							</form>
							<div>
								<button type="button"
									class="btn btn-outline btn-primary pull-right m-t-n-xs"
									data-dismiss="modal" onclick="refresh()">关闭</button>
								<button id="updateDTGPerformance"
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
							<h3 class="m-t-none m-b">添加学术论文指导绩效</h3>
							<form role="form" name="adds" id="addInfoForm"
								action="GTDegreeThesisGuidancePerformanceSet!insertDegreeThesisGuidanceRecord"
								method="post">
								<div class="form-group" style="display: none;">
									<label>ID</label>&nbsp;<label></label> <input id="ID"
										type="text" class="form-control nullcheck" name="" value="">
								</div>
								<div class="form-group">
									<label>学期:</label>&nbsp;<label></label><br>
									<select id="add_termSelection" name="degreeThesisGuidancePerformance.termId" style="width:568px;height:33px;border-radius:2px;border:1px #E5E6E7 solid;">
										<c:forEach var="tfterm" items="${tftermList }">
											<option value="${tfterm.termId }">${tfterm.term }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>学位论文名称:</label>&nbsp;<label></label> <input
										id=degreeThesisName " type="text"
										class="form-control nullcheck"
										name="degreeThesisGuidancePerformance.degreeThesisnName"
										value="">
								</div>
								<div class="form-group">
									<label>奖励级别:</label>&nbsp;<label></label> 
									<select
										id="rewardLevel" class="form-control nullcheck"
										name="degreeThsisGuidanceRewardLevel.rewardLevelId">
										<c:forEach var="degreeGuidanceRewardLevel"
											items="${degreeGuidanceRewardLevels }">
											<option value="${degreeGuidanceRewardLevel.rewardLevelId }">${degreeGuidanceRewardLevel.rewardLevel }</option>
										</c:forEach>
									</select>
								</div>
							</form>
							<div>
								<button type="button"
									class="btn btn-outline btn-primary pull-right m-t-n-xs"
									data-dismiss="modal" onclick="refresh()">关闭</button>
								<button id="addDTGPerformance"
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
		<script src="js/PublicCheck/PUB_SET.js"></script>
	<script type="text/javascript">
	$().ready(function(){
		$("#changelength option[value='${sessionScope.pageSize_GTDTG}']").attr("selected",true);
		$("#termSelection option[value='${sessionScope.termId_GTDTG}']").attr("selected",true);
		$("#add_termSelection option[value='${sessionScope.termId_GTDTG}']").attr("selected",true);
		$("#up_termSelection option[value='${sessionScope.termId_GTDTG}']").attr("selected",true);
	});
	function refresh(){
		window.location.reload(false);
	}
// 	$('#AlterPageSize').click(function(){
// 		if($("#changelength option:selected").text().trim().length!=0){
// 			window.open("GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord?isDivided=false&pageSize_GTDTG="+$("#changelength option:selected").text().trim(), "_self");
// 		}
// 	});
    $('#addDTGPerformance').click(function() {
		if($('#degreeThesisName').val().trim()!=""){
			$.ajax({
				cache:true,
				type:"POST",
				url:"GTDegreeThesisGuidancePerformanceSet!insertDegreeThesisGuidanceRecord",
				data:$("#addInfoForm").serialize(),
				async:true,
				error:function(){
					alert("连接失败！!!");
				},
				success:function(data){
					if(data=="succ"){
						window.alert("添加成功！");
						window.location.replace("<%=basePath %>GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord");
					}else{
						window.alert("添加成败！");
					}
				}
			});
		}
// 		else{
// 			window.location.replace("<%=basePath %>GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord?isDivided=true&pageIndex=${pageIndex}");
// 		}
	});
    $('#updateDTGPerformance').click(function() {
    	if($('#up_degreeThesisName').val().trim()!=""){
    		window.alert($("#up_termSelection option:selected").val());
    		$.post("GTDegreeThesisGuidancePerformanceSet!updateDegreeThesisGuidanceRecord",
    				{"degreeThesisGuidancePerformance.degreeThesisId":$('#up_ID').val().trim(),
    				"degreeThesisGuidancePerformance.degreeThesisnName":$('#up_degreeThesisName').val().trim(),
    				"degreeThsisGuidanceRewardLevel.rewardLevelId":$("#up_rewardLevel option:selected").val().trim(),
    				"degreeThesisGuidancePerformance.termId":$("#up_termSelection option:selected").val().trim()},
    				function(data,status){
    					if(status=="success"){
    						if(data=="succ"){
    							alert("修改成功");
    							window.location.replace("<%=basePath %>GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord");
    						}else{
    							alert("修改失败"+data);
    						}
    					}else{
    						alert("请求失败");
    					}
    				});
    	}
	});
    $('.update').click(function() {
    	var perios = $('.nullcheck');
		for(var i=0;i<perios.length;i++){
			perios[i].style.backgroundColor = "white";
		}
// 		$('#up_ID').attr("value",$(this).parent().parent()[0].cells[0].innerHTML);
		$('#up_ID')[0].value = $(this).parent().parent()[0].cells[0].innerHTML;
		$('#up_degreeThesisName')[0].value = $(this).parent().parent()[0].cells[4].innerHTML;
		var temp=$(this).parent().parent()[0].cells[5].innerHTML;
		$("#up_rewardLevel option").each(function(){
			if($(this).text()==temp.trim()){
				$(this).attr("selected",true);
			}
		});
		var term=$(this).parent().parent()[0].cells[3].innerHTML;
		$("#up_termSelection option").each(function(){
			if($(this).text()==term.trim()){
				$(this).attr("selected",true);
			}
		});
	});
    $('.deleteInfo').click(function() {
		var x = confirm("确定删除 ? ");
		var row = $(this).parent().parent();
		if(x){
			$.post("GTDegreeThesisGuidancePerformanceSet!deleteDegreeThesisGuidanceRecord",
    				{"degreeThesisGuidancePerformance.degreeThesisId":row[0].cells[0].innerHTML},
    				function(data,status){
    					if(status=="success"){
    						if(data=="succ"){
    							alert("删除成功");
    							window.location.replace("<%=basePath %>GTDegreeThesisGuidancePerformanceSet!getDegreeThesisGuidanceRecord?isDivided=true&pageIndex=${pageIndex}");
    						}else{
    							alert("删除失败");
    						}
    					}else{
    						alert("请求失败");
    					}
    				});
		}
	});
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
<!--     <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script> -->
	<s:debug></s:debug>
</body>
</html>