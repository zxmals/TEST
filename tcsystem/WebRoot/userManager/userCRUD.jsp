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


<title>用户管理</title>

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
							用户管理 <small></small>
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
							<strong>添加教师</strong>
						</button>
						<br>
						<br>
						<form action="AT_user!get">
							<div>
								<a>每页 <select id="changelength" style="width:80px;height:25px;border-radius:3px;" name="pageSize_user">
										<c:forEach var="pageSize" items="${pageSizeList }">
											<option value="${pageSize }">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${pageSize }</option>
										</c:forEach>
								</select>条记录
								</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="#337AB7">检索条件：</font><input type="text" placeholder="请输入要查询教师的工号" id="findCondition" name="findCondition"
								 style="width:150px;height:25px;border-radius:3px;border:1px solid #A9A9A9;"/>
								 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
											<td>教师工号</td>
											<td>教师姓名</td>
											<td>研究所</td>
											<td>所在系</td>
											<td>教师身份</td>
											<td>教师职位</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="Teacher"
											items="${Teachers }">
											<tr>
												<!-- 教师工号 -->
												<td>${Teacher.teacherId }</td>
												<!-- 教师姓名 -->
												<td>${Teacher.teacherName }</td>
												<!-- 研究所 -->
												<td>${Teacher.researchLab.researchLabName }</td>
												<!-- 所在系 -->
												<td>${Teacher.department.departmentName }</td>
												<!-- 教师身份 -->
												<c:if test="${Teacher.departAdmin==1 }">
													<td>系主任</td>
												</c:if>
												<c:if test="${Teacher.vaadmin==1 }">
													<td>公益管理员</td>
												</c:if>
												<c:if test="${Teacher.researchLabAdmin==1 }">
													<td>研究所所长</td>
												</c:if>
												<c:if test="${Teacher.researchLabAdmin==0 && Teacher.vaadmin==0 && Teacher.departAdmin==0 }">
													<td>普通教师</td>
												</c:if>
												<!-- 教师职位 -->
												<td>
													<c:choose>
														<c:when test="${Teacher.teacherPost!=null }">
															${Teacher.teacherPost}
														</c:when>
														<c:otherwise>
															''
														</c:otherwise>
													</c:choose>
												</td>
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
								(共查询到${sessionScope.recordNumber_user }记录)&nbsp;&nbsp;&nbsp;&nbsp; 第${pageIndex }/${sessionScope.pageCount_user }页&nbsp;&nbsp;&nbsp;
								<a class="comphref"
									href="AT_user!get?isDivided=true&pageIndex=1">首页</a>&nbsp;&nbsp;&nbsp;
								<c:if test="${pageIndex>1 }">
									<a class="comphref"
										href="AT_user!get?isDivided=true&pageIndex=${pageIndex-1 }">上一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<c:forEach var="index" begin="${pageIndex }" end="${pageIndex+4 }" step="1">
									<c:if test="${index<pageCount_user }">
										<a class="comphref"
										href="AT_user!get?isDivided=true&pageIndex=${index }">${index }</a>&nbsp;&nbsp;&nbsp;
									</c:if>
								</c:forEach>
								<c:if test="${pageIndex<pageCount_user }">
									<a class="comphref"
										href="AT_user!get?isDivided=true&pageIndex=${pageIndex+1 }">下一页</a>&nbsp;&nbsp;&nbsp;
								</c:if>
								<a class="comphref"
									href="AT_user!get?isDivided=true&pageIndex=${pageCount_user }">尾页</a>
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
							<h3 class="m-t-none m-b">修改教师信息</h3>
							<form role="form" name="" id="updateInfoForm"
								action=""
								method="post">
								<div class="form-group">
									<label>教师编号</label>&nbsp;<label></label> <input id="up_teacherId"
										type="text" class="form-control doCheck_update" name="teacher.teacherId"  
										value="" readonly="readonly">
								</div>
								<div class="form-group">
									<label>教师姓名:</label>&nbsp;<label></label> 
									 <input id="up_teacherName"
										type="text" class="form-control doCheck_update" name="teacher.teacherName"  
										value="">
								</div>
								<div class="form-group">
									<label>研究所:</label>&nbsp;<label></label> 
									<select
										id="up_researchLab" class="form-control"
										name="researchLab.researchLabId">
										<c:forEach var="researchLab"
											items="${researchLabList }">
											<option value="${researchLab.researchLabId }">${researchLab.researchLabName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>所在系:</label>&nbsp;<label></label> 
									<select
										id="up_department" class="form-control"
										name="department.departmentId">
										<c:forEach var="department"
											items="${departmentList }">
											<option value="${department.departmentId }">${department.departmentName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>教师身份</label>&nbsp;<label></label> 
									<select
										id="up_role" class="form-control"
										name="role">
										<option value="0">普通教师</option>
										<option value="1">系主任</option>
										<option value="2">研究所所长</option>
										<option value="3">公益管理员</option>
									</select>
								</div>
								<div class="form-group">
									<label>教师职位</label>&nbsp;<label></label> <input
										id="up_position" type="text"
										class="form-control doCheck_update"
										name="teacher.teacherPost"
										value="">
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
							<h3 class="m-t-none m-b">添加教师信息</h3>
							<form role="form" name="adds" id="addInfoForm"
								action=""
								method="post">
								<div class="form-group">
									<label>教师工号</label>&nbsp;<label></label> <input
										id="add_teacherId" type="text"
										class="form-control doCheck_add"
										name="teacher.teacherId"
										value="">
								</div>
								<div class="form-group">
									<label>教师姓名</label>&nbsp;<label></label> <input
										id="add_teacherName" type="text"
										class="form-control doCheck_add"
										name="teacher.teacherName"
										value="">
								</div>
								<div class="form-group">
									<label>研究所:</label>&nbsp;<label></label> 
									<select
										id="add_researchLab" class="form-control"
										name="researchLab.researchLabId">
										<c:forEach var="researchLab"
											items="${researchLabList }">
											<option value="${researchLab.researchLabId }">${researchLab.researchLabName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>所在系:</label>&nbsp;<label></label> 
									<select
										id="add_department" class="form-control"
										name="department.departmentId">
										<c:forEach var="department"
											items="${departmentList }">
											<option value="${department.departmentId }">${department.departmentName }</option>
										</c:forEach>
									</select>
								</div>
								<div class="form-group">
									<label>教师身份</label>&nbsp;<label></label> 
									<select
										id="add_role" class="form-control"
										name="role">
										<option value="0">普通教师</option>
										<option value="1">系主任</option>
										<option value="2">研究所所长</option>
										<option value="3">公益管理员</option>
									</select>
								</div>
								<div class="form-group">
									<label>教师职位</label>&nbsp;<label></label> <input
										id="add_position" type="text"
										class="form-control doCheck_add"
										name="teacher.teacherPost"
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
	/* 说明：更新窗口的id前缀是add_，增加是add_ */
	$().ready(function(){
		$("#changelength option[value='${sessionScope.pageSize_user}']").attr("selected",true);
		$("#findCondition").val('${sessionScope.findCondition}');
	});
	$("#submitAddInfo").click(function(){
		//执行提交表单
		submitAddedInfo("AT_user", "add", "get");
	});
    $('#updateInfo').click(function() {
    	submitUpdatedInfo("AT_user", "update", "get");
	});
    $('.update').click(function() {
		$('#up_teacherId').val($(this).parent().parent()[0].cells[0].innerHTML);
		$('#up_teacherName').val($(this).parent().parent()[0].cells[1].innerHTML);
		var temp=$(this).parent().parent()[0].cells[2].innerHTML;
		$("#up_researchLab option").each(function(){
			if($(this).text()==temp.trim()){
				$(this).prop("selected",true);//这里用attr会有问题。
			}
		});
		var temp1=$(this).parent().parent()[0].cells[3].innerHTML;
		$("#up_department option").each(function(){
			if($(this).text()==temp1.trim()){
				$(this).prop("selected",true);//这里用attr会有问题。
			}
		});
		var temp2=$(this).parent().parent()[0].cells[4].innerHTML;
		$("#up_role option").each(function(){
			if($(this).text()==temp2.trim()){
				$(this).prop("selected",true);//这里用attr会有问题。
			}
		});
		$('#up_position').val($(this).parent().parent()[0].cells[5].innerHTML);
	});
    $('.deleteInfo').click(function() {
		var teacherId = $(this).parent().parent()[0].cells[0].innerHTML;
		deleteRecord({
			"teacher.teacherId":teacherId
		}, "AT_user", "delete",
		"get?isDivided=false");
	});
    </script>
	<s:debug></s:debug>
</body>
</html>