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


<title>公益管理员设置</title>

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
							公益管理员设置<small></small>
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
								<strong>添加公益管理员</strong>
							</button>

						</div>
						<div class="example">
							<form method="post" name="f">
								<table id="tb"
									class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
											<td>公益管理员ID</td>
											<td>公益管理员姓名</td>
											<td>操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="ebj" items="${Vaadmin }">
											<tr>
												<td>${ebj.teacherId }</td>
												<td>${ebj.teacherName}</td>
												<td><a class="btn btn-primary btn-sm delwords delinf"
													data-toggle="modal">删除</a> </td>
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
		

		<div id="add" class="modal fade" aria-hidden="true" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-body">
						<div class="row">
							<h3 class="m-t-none m-b">添加公益管理员</h3>
							<form role="form" id="onlyForm" name="adds"
								action="ATVaSetting2!addVaAdmin" method="post">
								
								<div class="form-group">
									<label>教师信息:</label> 
									<input id="VaAdmin" type="text"
										autocomplete="off" class="form-control keyUp"
										placeholder="输入教师工号或姓名查询">
									<div style="width:568px;overflow-y: auto;display: none"
										class="selecthead">
										<c:forEach items="${notVaadmin}" var="teacher">
											<div class="selectsele">${teacher.teacherId}-${teacher.teacherName}</div>
										</c:forEach>
									</div>
								</div>
							</form>
							<div>
								<button type="button"
									class="btn btn-outline btn-primary pull-right m-t-n-xs"
									data-dismiss="modal">关闭</button>
								<button id="subadd"
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
    $('#subadd').click(function() {
    	nullcheck();
		if($('#VaAdmin').val().trim()!=""){//$('#upinfscope').val().trim()!=""
			$.post("ATVaSetting2!addVaAdmin",
				{"entity.TeacherId":$('#VaAdmin').val().substring(0,$('#VaAdmin').val().lastIndexOf("-"))},
					function(data,status){
						if(status=="success"){
							if(data.trim()=="succ"){
								alert("添加成功");
								window.location.replace("<%=basePath %>ATVaSetting2!entityList");
							}else{
								alert("添加失败");
							}
						}else{
							alert("请求失败");
						}
					});
		}
	});
	$('.delinf').click(function() {
		 var row = $(this).parent().parent();
		 var x = confirm("确认删除？");
		 if(x){
				  $.post("ATVaSetting2!deleteVaAdmin",
							{"entity.teacherId":row[0].cells[0].innerHTML,
							 "entity.teacherName":row[0].cells[1].innerHTML},
							function(data,status){
								if(status=="success"){
									if(data.trim()=="succ"){
									window.location.replace("<%=basePath %>ATVaSetting2!entityList");
										alert("删除成功");
										row.remove();
									}else{
										alert("删除失败");
									}
								}else{
									alert("请求失败");
								}
							});
				}  else {
				    alert("管理员未删除");
			  }
	});
	
	$('#VaAdmin').keyup(function() {
		var vals = $('.selectsele');
		var targets = $(this).val();
		if($('.selecthead').css('display')=="none"){
    		for(var i=0;i<vals.length;i++){
    			vals[i].style.display = "";
    		}
			$('.selecthead').css("height","150px");
			$('.selecthead').slideDown();
		}
		for(var i=0;i<vals.length;i++){
			if(vals[i].innerHTML.indexOf(targets)>=0){
				vals[i].style.display = "";
			}else{
				vals[i].style.display = "none";
			}
		}
	});
	$('#VaAdmin').click(function(){
		var selects = $('.selectsele');
		if($('.selecthead').css('display')=="none"){
    		for(var i=0;i<selects.length;i++){
    			selects[i].style.display = "";
    		}
			$('.selecthead').css("height","150px");
			$('.selecthead').slideDown();
		}else{
			$('.selecthead').slideUp();
		}
	});
	$('.selectsele').click(function(){
		$('#VaAdmin')[0].value = $(this)[0].innerHTML;
		$('#VaAdmin').css("background-color","white");
	});
	

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
				$("#editable").dataTable().fnAddData(
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
		<script type="text/javascript"
			src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
		<s:debug></s:debug>
</body>
</html>