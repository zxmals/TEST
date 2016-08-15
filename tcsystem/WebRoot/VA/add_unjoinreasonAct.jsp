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
<title>add_unjoin_ruled-act_reason</title>
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
	
	function addReasonResult() {
		var mess = "${submitstatus}";
		if(mess!="")
			alert(mess);
	}
	
</script>
</head>
<body onload="addReasonResult()">
	<div class="datepick">
		<span>选择日期范围</span>
		<div>
			<form action="add_unjoinreasonAct!getUnjoinedRuledAct" method="post" name="pickdate">
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
						<h4>
							我的未参与的规定性公益活动<small></small>
						</h4>
						<div class="ibox-content"></div>
						<div class="example">
								<table id="tb" class="table table-striped table-bordered table-hover dataTables-example">
									<thead>
										<tr>
											<td class="sorting_asc" style="display: none">actID</td>											
											<td class="sorting_asc">活动名称</td>
											<td class="sorting_asc">参与人员</td>
											<td class="sorting_asc">活动日期</td>
											<td class="sorting_asc">未参与原因</td>
											<td style="display: none"  />
											<td class="sorting_asc">是否请假</td>
											<td class="sorting_asc">最终得分</td>
											<td style="display: none"  />
											<td class="sorting_asc">状态</td>
											<td class="sorting_asc">操作</td>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="vaunj" items="${vaunjoinedli }">
											<tr>
												<td style="display: none">${vaunj.actId }</td>
												<td>${vaunj.actName }</td>
												<td>${vaunj.actAttendee }</td>
												<td>${vaunj.actDate }</td>
												<td class="overflows">${vaunj.unjoinreason }</td>
												<td style="display: none">${vaunj.unjoinreason }</td>
												<c:if test="${vaunj.leavereqobtain==1 }"><td abbr="${vaunj.leavereqobtain }">已请假</td></c:if>
												<c:if test="${vaunj.leavereqobtain==0 }"><td abbr="${vaunj.leavereqobtain }">未请假</td></c:if>
												<c:if test="${vaunj.leavereqobtain==null }"><td abbr="2">待完善</td></c:if>
												<td>${vaunj.resultscore }</td>
												<td style="display: none">${vaunj.unjoinId }</td>
												<c:if test="${vaunj.asparetire==1 }"><td>已审核</td></c:if>
												<c:if test="${vaunj.asparetire==0 }"><td>未审核</td></c:if>
												<c:if test="${vaunj.asparetire==2 }"><td>审核未通过</td></c:if>
												<c:if test="${vaunj.asparetire==null }"><td>待完善</td></c:if>
												<td>
													<button  class="check"   data-toggle="modal"  >查看</button>
													<button  class="complete"		data-toggle="modal" >完善&修改</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>								
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
	<div id="complete" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                            <h3 class="m-t-none m-b"   style="margin-left: 37%">补充未参与活动的说明</h3><hr>
                            <form role="form" id="onlyForm"  method="post"   name="upd"action="add_unjoinreasonAct!addOrUpdateUNjoinreason">
                            
                                <div class="form-group"  style="display: none">
                                	<label>actID:</label>                                	
									<input id="com_actID" type="text"  class="form-control" name="unjoinact.actId"     >
                                </div>
                                <div class="form-group"  style="display: none">
                                	<label>reasonID:</label>                                	
									<input id="com_reaID" type="text"  class="form-control" name="unjoinact.unjoinId"     >
                                </div>
                                <div class="form-group">
                                    <label>活动名称:</label>
                                    <input id="com_actName" type="text"  class="form-control" name="com_actName" value=""  readonly="readonly">
                                </div>
                                <div class="form-group">                                
                                    <label>未参与原因:</label>
                                    <textarea   id="com_unjoinreason"   name="unjoinact.unjoinreason"   rows="5" cols="10"  class="form-control"   value=""  ></textarea>
                                </div>
                                 <div class="form-group">
									<label>是否请假:</label>
									<div style="position:relative;">
											<select   class="form-control"   style="width:218px;" name="unjoinact.leavereqobtain" id="com_leavereqper">
												<option  value="2"  selected="selected"></option>
												<option value="1">已请假</option>
												<option value="0">未请假</option>																						
											</select>
									</div>
								</div>								                        
                                <div>                                    
                                    <button  class="btn  btn-primary pull-left m-t-n-xs "  type="submit">
                                     <i class="fa fa-check"></i>
                                    <strong>提交</strong>
                                    </button	>
                                    <button type="button"   class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
                               </div>
                            </form>
                    </div>
                </div>
            </div>
        </div>
    </div>			
	
	<div id="checkreason" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"     aria-labelledby="myModalLabel">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="row">
                            <h3 class="m-t-none m-b"   style="margin-left: 37%">查看明细</h3><hr>
                                <div class="form-group">
                                    <label>活动名称:</label>
                                    <input id="check_actName" type="text"  class="form-control" name="com_actName" value=""  readonly="readonly">
                                </div>
                                <div class="form-group">                              
                                    <label>未参与原因:</label>
                                    <textarea   id="check_unjoinreason"   name="com_unjoinreason"  readonly="readonly"  rows="5" cols="10"  class="form-control"   value=""  ></textarea>
                                </div>                                 						                       
                                <div>                                    
                                    <button type="button" style="margin-left: 46%"  class="btn btn-outline btn-primary m-t-n-xs" data-dismiss="modal">关闭</button>
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
	$('.check').click(function(){
		$('#check_actName').attr("value",$(this).parent().parent()[0].cells[1].innerHTML.trim());
		var textarea = $('#check_unjoinreason');
		textarea[0].value = $(this).parent().parent()[0].cells[5].innerHTML.trim();
		$(this).attr("data-target","#checkreason");
	});
	$('.complete').click(function(){
		$('#com_actID').attr("value",$(this).parent().parent()[0].cells[0].innerHTML.trim());
		$('#com_reaID').attr("value",$(this).parent().parent()[0].cells[8].innerHTML.trim());
		$('#com_actName').attr("value",$(this).parent().parent()[0].cells[1].innerHTML.trim());
		var textarea = $('#com_unjoinreason');
		textarea[0].value = $(this).parent().parent()[0].cells[5].innerHTML.trim();
		var selectedval = $(this).parent().parent()[0].cells[6].abbr;
		var select = $('#com_leavereqper');
		for(var i=0;i<select[0].options.length;i++){
			if(select[0].options[i].value==selectedval)
				select[0].options[i].selected = true;
			else
				select[0].options[i].selected = false;
		}
		$(this).attr("data-target","#complete");
	});
		$(document).ready(function() {
			var tds = $('.overflows');
			for(var i=0;i<tds.length;i++){
				if(tds[i].innerHTML.trim()==""||tds[i].innerHTML.trim()==null)
					tds[i].innerHTML = '还没有补充任何原因. . . . . . ';		
				else{
					tds[i].title = tds[i].innerHTML;
					tds[i].innerHTML = tds[i].innerHTML.substring(0,9)+'. . . . . . ';
				}
					
			}
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