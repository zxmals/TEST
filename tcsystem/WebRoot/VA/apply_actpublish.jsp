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
<title>apply_ActPublish</title>
<link rel="shortcut icon" href="favicon.ico">
<link href="../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<!-- Sweet Alert -->
<link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="../css/zxma.css">
<link href="../css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
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
	    var x = document.getElementsByName("vapm.actPubId");   //数组
	   for(var i=0;i<x.length;i++){
	    	if(x[i].checked)
	    		document.f.submit();
	    }		
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
	<div class="datepick" >
		<span  style="width: 18%">选择要发布的活动</span>
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
											<td class="sorting_asc">活动名称</td>
											<td class="sorting_asc">参与人员</td>										
											<td class="sorting_asc">活动类型</td>
											<td class="sorting_asc">选定</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="var" items="${readyactli }">
											<tr>
												<td>${var.actName }</td>
												<td>${var.attendee }</td>
												<c:if test="${var.actType==1 }"><td>规定性活动</td></c:if>
												<c:if test="${var.actType==2 }"><td>选择性活动</td></c:if>
												<c:if test="${var.actType==3 }"><td>其他活动</td></c:if>
												<td><input type="radio"  name="actId"  id="1a"  value="${var.actId }"></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>								
							</form>
							<input type="submit" value="下一步"   data-toggle="modal"    id="subm"   onmousedown="downsubm()" >
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<div id="PickActDate" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                            <h3 class="m-t-none m-b"   style="margin-left: 37%">确定活动举办日期</h3><hr>
                            		<form action="apply_actpublish!addPublishApply"  method="post"   id="apply_act">
                            			<div class="form-group"   style="display: none">
		                                    <label>活动ID:</label>
		                                    <input id="apply_actID" type="text"   class="form-control" name="foreact.vacollectiveAct.actId"    value=""  readonly="readonly">
		                                </div>
		                                <div class="form-group">
		                                    <label>活动名称:</label>
		                                    <input id="apply_actName" type="text"   class="form-control"    value=""  readonly="readonly">
		                                </div>
		                                 <div class="form-group">
		                                    <label>活动举办日期:</label>
		                                    <input  type="text"    onClick="eye.datePicker.show(this);"    class="form-control" name="foreact.actDate"   id="apply_actDate"   value=""  readonly="readonly">
		                                </div>
                            		</form>
                                <div>                              
                                    <button  class="btn  btn-primary pull-left m-t-n-xs "  type="submit"   id="subm_apply">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs"  data-dismiss="modal">关闭</button>
                               </div>
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
	$('.conve > input').click(function(){
		$(this).attr("checked","true");
	});
	$('#subm_apply').click(function(){
		if($('#apply_actDate').val()==''|$('#apply_actDate').val()==null){
			alert("日期选择为空！");
			return;
		}
		$('#apply_act').submit();
	});
	$('#subm').mouseup(function(){
		$(this).css("border","0.5px outset");
		var ids = $("[name='actId']");
		for(var i=0;i<ids.length;i++){
			if(ids[i].checked){
				$('#apply_actName').attr("value",ids[i].parentNode.parentNode.cells[0].innerHTML);
// 				$('#apply_actName').attr("value",ids[i].parent().parent().cells[0].innerHTML);
				$('#apply_actID').attr("value",ids[i].value);
				$(this).attr("data-target","#PickActDate");
				return;
			}
		}
		alert("请选择活动！");
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