<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
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


<title>JoinAcademicMeeting--TYPE --Set</title>

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
							教学研究--本科生指导等级设置<small></small>
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

						<div class="">
							<button class="btn  btn-primary openaddm" type="submit"
								data-backdrop="true" data-toggle="modal" data-target="#add">
								<strong>添加</strong>
							</button>

						</div>
						<div class="example">
							<form method="post" name="f">
								<table id="tb"
									class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
											<td>指导等级Id</td>
											<td>描述</td>
											<td>系数</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="ebj" items="${entityList }">
											<tr>
												<td>${ebj.guidanceId }</td>
												<td>${ebj.describe}</td>
												<td>${ebj.score }</td>
												<td><a class="btn btn-primary btn-sm delwords delinf"
													data-toggle="modal">删除</a> <a
													class="btn btn-primary btn-sm openupdatem carrydata"
													data-toggle="modal" data-target="#update">修改</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="update" class="modal fade" aria-hidden="true" tabindex="-1"
			role="dialog"="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div class="row">
							<h3 class="m-t-none m-b">修改</h3>
							<div class="form-group" style="display: none">
								<label>ID:</label> <input id="upinfID" type="text"
									class="form-control" name="" value="" readonly="readonly">
							</div>
							<div class="form-group">
								<label>描述:</label> <input id="upinfscope" type="text"
									class="form-control nullcheck" name="" value="">
							</div>
							<div class="form-group">
								<label>系数:</label> <input id="upinfratio" type="text"
									class="form-control nullcheck" name="" value="">
							</div>
							<div>
								<button type="button"
									class="btn btn-outline btn-primary pull-right m-t-n-xs"
									data-dismiss="modal">关闭</button>
								<button id="subupdate"
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

		<div id="add" class="modal fade" aria-hidden="true" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div class="row">
							<h3 class="m-t-none m-b">添加</h3>
							<form role="form" id="onlyForm" name="adds"
								action="ATundergraduateTutorGuidanceCacheset!addEntity" method="post">
								<div class="form-group">
									<label>描述:</label> <input id="addinf1" type="text"
										class="form-control describe" name="entity.fundLevel"
										value="">
								</div>
								<div class="form-group">
									<label>系数:</label> <input id="addinf2" type="text"
										class="form-control nullcheck"
										name="entity.score" value="">
								</div>
							</form>
							<div>
								<button type="button"
									class="btn btn-outline btn-primary pull-right m-t-n-xs"
									data-dismiss="modal">关闭</button>
								<button id="subadds"
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
		<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
		<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
		<script src="js/content.min.js?v=1.0.0"></script>
		<script src="js/plugins/iCheck/icheck.min.js"></script>
		<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
		<script src="js/PublicCheck/PUB_SET.js"></script>

		<script>
    $('#subadds').click(function() {
		if($('#addinf1').val().trim()!="" & $('#addinf2').val().trim()!=""){
			document.adds.submit();
		}
	});
    $('#subupdate').click(function() {
		if($('#upinfscope').val().trim()!=""){//$('#upinfscope').val().trim()!=""
			$.post("ATundergraduateTutorGuidanceCacheset!updateEntity",
					{"entity.guidanceId":$('#upinfID').val().trim(),
					 "entity.describe":$('#upinfscope').val().trim(),
					 "entity.score":$('#upinfratio').val().trim()},
					function(data,status){
						if(status=="success"){
							if(data.trim()=="succ"){
								swal("Good job!","更新成功","success");
								window.location.replace("ATundergraduateTutorGuidanceCacheset!entityList");
							}else{
								swal("Oops...","更新失败","error");
							}
						}else{
							swal("Oops...","请求失败","error");
						}
					});
		}
	});
	$('.delinf').click(function() {
		var row = $(this).parent().parent();
		 swal({
			  title: "Are you sure?",
			  text: "你将不能恢复这行数据!",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "Yes, delete it!",
			  cancelButtonText: "No, cancel plx!",
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			function(isConfirm){
			  if (isConfirm) {
				  $.post("ATundergraduateTutorGuidanceCacheset!deleteEntity",
							{"entity.guidanceId":row[0].cells[0].innerHTML,
							 "entity.describe":row[0].cells[1].innerHTML,
							 "entity.score":row[0].cells[2].innerHTML},
							function(data,status){
								if(status=="success"){
									if(data.trim()=="succ"){
										swal("Deleted!", "删除成功.", "success");
										row.remove();
									}else{
										swal("Oops...","删除失败","error");
									}
								}else{
									swal("Oops...","请求失败","error");
								}
							});
				}  else {
				    swal("Cancelled", "Your data is safe", "error");
			  }
			});
	});
    $('.openupdatem').click(function() {
		$('#upinfID')[0].value=$(this).parent().parent()[0].cells[0].innerHTML;
		$('#upinfscope')[0].value=$(this).parent().parent()[0].cells[1].innerHTML;
		$('#upinfratio')[0].value=$(this).parent().parent()[0].cells[2].innerHTML;
	});
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
		<script type="text/javascript"
			src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
		<s:debug></s:debug>
</body>
</html>