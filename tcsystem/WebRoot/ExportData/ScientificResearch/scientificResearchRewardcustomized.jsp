<%@page import="com.nuaa.ec.science.model.PeriodicalType"%>
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


<title>periodicalDatacustomized</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

<!-- Sweet Alert -->
<link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<!-- Data Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_blank">
<script  src="js/checkbox.js"></script>
<script type="text/javascript">
		function dosubmit() {
			var vau = document.getElementById("values").value;
			var forms = document.getElementById("onlyForm");
			forms.action = "scientificResearchRewardcustomized!carryoutScientificResearchRewardexcel?res = "+vau;
			forms.submit();
		}
</script>
</head>

<body class="gray-bg" >

	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
			<div class="col-sm-12">
				<div class="ibox float-e-margins">
					<div class="ibox-title">
						<h5>
							期刊论文 <small></small>
						</h5>
						<div class="ibox-tools">
							<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
							</a> 
							<a class="dropdown-toggle" data-toggle="dropdown" href="table_data_tables.html#"> <i class="fa fa-wrench"></i>
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
							<button class="btn  btn-primary" type="submit"   onclick="confirms('12')"  data-backdrop="true" data-toggle="modal" data-target="#confirm">
								<strong>选定提交</strong>
							</button>
						</div>
						<div class="example">
							<form method="post" name="f">
								<table id="tb" class="table table-striped table-bordered table-hover ">
									<thead>
										<tr>
											<td><input type="checkbox"  value="1"></td>
											<td><input type="checkbox"  value="2"></td>
											<td><input type="checkbox"  value="3"></td>
											<td><input type="checkbox"  value="4"></td>
											<td><input type="checkbox"  value="5"></td>
											<td><input type="checkbox"  value="6"></td>
											<td><input type="checkbox"  value="7"></td>
											<td><input type="checkbox"  value="8"></td>
											<td><input type="checkbox"  value="9"></td>
											<td><input type="checkbox"  value="10"></td>
											<td><input type="checkbox"  value="11"></td>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>科研奖励编号</td>
											<td>获奖级别</td>
											<td>获奖类别</td>
											<td>科研奖励名称</td>
											<td>授奖部门</td>
											<td>获奖日期</td>
											<td>获奖总人数</td>
											<td>教师编号</td>
											<td>教师姓名</td>
											<td>本人排名</td>											
											<td>基础分数</td>
										</tr>
									</tbody>
								</table>
								<input type="text"  value=""  id="values"  style="display: none">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		 <div id="confirm" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                            <h3 class="m-t-none m-b">确认列项</h3>
                            <form role="form" id="onlyForm"  method="post" action="periodicalcustomized!carryoutPeriodicalexcel">                           
								<table id="tab" class="table table-striped table-bordered table-hover ">
									<tbody>
									<tr>
										<td></td><td></td><td></td><td></td><td></td><td></td>
										<td></td><td></td><td></td><td></td><td></td><td></td>
									</tr>
									<tr>
										<td></td><td></td><td></td><td></td><td></td><td></td>
										<td></td><td></td><td></td><td></td><td></td><td></td>
									</tr>
									<tr>
										<td></td><td></td><td></td><td></td><td></td><td></td>
										<td></td><td></td><td></td><td></td><td></td><td></td>
									</tr>
									</tbody>
								</table>                                
								<div>		
									<input type="text"  value=""  id ="values"  name="res" style="display:  none">					
									<button type="button" class="btn btn-outline btn-primary pull-right m-t-n-xs"  data-dismiss="modal">关闭</button>
									<button class="btn  btn-primary pull-left m-t-n-xs "  onclick="dosubmit()"  type="submit">
										<i class="fa fa-check"></i> <strong>生成并|下载|</strong>
									</button>
								</div>
							</form>
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
		<script>
        $(document).ready(function(){$(".dataTables-example").dataTable();var oTable=$("#editable").dataTable();oTable.$("td").editable("../example_ajax.php",{"callback":function(sValue,y){var aPos=oTable.fnGetPosition(this);oTable.fnUpdate(sValue,aPos[0],aPos[1])},"submitdata":function(value,settings){return{"row_id":this.parentNode.getAttribute("id"),"column":oTable.fnGetPosition(this)[2]}},"width":"90%","height":"100%"})});function fnClickAddRow(){$("#editable").dataTable().fnAddData(["Custom row","New row","New row","New row","New row"])};         
        $(document).ready(function(){$(".i-checks").iCheck({checkboxClass:"icheckbox_square-green",radioClass:"iradio_square-green",})});            
    </script>
		<script type="text/javascript"
			src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
		<s:debug></s:debug>
</body>
</html>