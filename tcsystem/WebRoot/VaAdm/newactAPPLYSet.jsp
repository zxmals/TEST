<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    request.setAttribute("teachermp", StoreData.getTeachertranslate());
%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base target="_self">
</head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>UnjoinedAct-Set</title>

<link rel="shortcut icon" href="favicon.ico">
<link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">

<!-- Sweet Alert -->
<link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

<!-- Data Tables -->
<link href="css/plugins/dataTables/dataTables.bootstrap.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css" href="css/zxma.css">
<link href="css/animate.min.css" rel="stylesheet">
<link href="css/style.min.css?v=4.0.0" rel="stylesheet">
<base target="_blank">
<link rel="stylesheet" href="css/mermberTab.css">
<script src="My97DatePicker/WdatePicker.js"></script>
<script src="js/jquery.min.js?v=2.1.4"></script>
<script src="js/bootstrap.min.js?v=3.3.5"></script>
<script src="js/plugins/jeditable/jquery.jeditable.js"></script>
<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
<script src="js/content.min.js?v=1.0.0"></script>
<script src="js/plugins/iCheck/icheck.min.js"></script>
<script src="js/PublicCheck/PUB_SET.js"></script>
<!-- ISBN输入控制 -->
<script src="js/plugins/jasny/jasny-bootstrap.min.js"></script>
<!-- sweet-alert -->
<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
<script type="text/javascript">
    var teacherIds = "${teacher.teacherId}";
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
	<div class="datepick">
		<span>选择日期范围</span>
		<div>
			<form action="ATva_newactapplyManage!getWorkall?pagenum=1"
				method="post" name="pickdate" id="pickdates">
				从:<input type="text" id="date1" class="Wdate"
					onClick="WdatePicker()" value="${foredate_CT }" name="foredate_CT" />到:<input
					type="text" id="date2" onClick="WdatePicker()" class="Wdate"
					value="${afterdate_CT }" name="afterdate_CT" /> &nbsp;&nbsp;<input
					type="submit" id="datep" value="查询" title="点击查询">
			</form>
		</div>
	</div>
	<div class="chooseact">
		<div class="wrapper wrapper-content animated fadeInRight">
			<div class="row">
				<div class="col-sm-12">
					<div class="ibox float-e-margins">
						<div class="ibox-title">
							<h5>
								活动新增管理<small></small>
							</h5>
							<div class="ibox-tools"></div>
						</div>
						<div class="ibox-content" style="height:540px;">
							<div>
								<a>每页 <select id="changelength" style="width:45px">
										<option selected="selected"></option>
										<!-- 	                    		<option>1</option> -->
										<!-- 	                    		<option>2</option> -->
										<option>5</option>
										<option>10</option>
										<option>15</option>
										<option>20</option>
								</select> 条记录
								</a>
							</div>
							<br>
							<div class="example">
								<form method="post" name="f">
									<table id="tb"
										class="table table-striped table-bordered table-hover dataTables-example">
										<thead>
											<tr>
												<td>活动编号</td>
												<td>活动名称</td>
												<td>参与人员</td>
												<td>活动类型</td>
												<td>说明</td>
												<td>教师编号</td>
												<td>教师姓名</td>
												<td>活动分数</td>
												<td>状态</td>
												<td>操作</td>
											</tr>
										</thead>
										<!--                                     <tbody> -->
										<c:forEach var="entity" items="${List }">
											<tr>
												<td>${entity.actId }</td>
												<td>${entity.actName }</td>
												<td>${entity.attendee }</td>
												<c:if test="${entity.actType==0 }">
													<td>未选择活动类型</td>
												</c:if>
												<c:if test="${entity.actType==1 }">
													<td>规定性活动</td>
												</c:if>
												<c:if test="${entity.actType==2 }">
													<td>选择性活动</td>
												</c:if>
												<c:if test="${entity.actType==3 }">
													<td>其他活动</td>
												</c:if>
												<td>${fn:substring(entity.actapplyfile,0,8)  }……<!-- 													<button class="button_set"  -->
													<!-- 													data-toggle="modal" --> <!-- 														data-target="#myModal" >查看</button> -->
													<button onclick="tanchu('${entity.actapplyfile }')">查看</button>
													<!-- 														<button onclick="modal('${entity.actapplyfile }')">chakan</button> -->
												</td>




												<div class="modal fade" id="myModal" tabindex="-1"
													role="dialog" aria-labelledby="myModalLabel"
													aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">${entity.actId }&nbsp;&nbsp;
																&nbsp; ${entity.actName }</div>
															<div class="modal-body">${entity.actapplyfile }</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">关闭</button>

															</div>
														</div>
													</div>
												</div>

												<td>${entity.teacher.teacherId }</td>
												<td>${entity.teacher.teacherName }</td>
												<td>${entity.score }</td>
												<td><c:if test="${entity.aspareTire == '0'}">待审核</c:if>
													<c:if test="${entity.aspareTire == '1'}">审核通过</c:if> <c:if
														test="${entity.aspareTire == '2'}">不通过</c:if></td>
												<td><c:if test="${entity.aspareTire == '0' }">
														<!--                                                     <a href="" class="btn btn-primary btn-sm delwords delinf update"  data-toggle="modal" data-target="#update">修改</a> -->
														<a class="btn btn-primary btn-sm delwords delinf1"
															data-toggle="modal">删除</a>
													</c:if> <c:if test="${entity.aspareTire == '1' }">
														<a href=""
															class="btn btn-primary btn-sm delwords delinf update"
															data-toggle="modal" data-target="#update">修改</a>
														<a class="btn btn-primary btn-sm delwords delinf1"
															data-toggle="modal">删除</a>
													</c:if></td>
											</tr>
										</c:forEach>
										<!--                                     </tbody> -->
									</table>
								</form>
								<div style="text-align: center;">
									(共查询到${sumrow }条记录)&nbsp;&nbsp;&nbsp;&nbsp; 第${pagenum }/${sumpage }页&nbsp;&nbsp;&nbsp;
									<a class="comphref"
										href="ATva_newactapplyManage!getWorkall?pagenum=1">首页</a>&nbsp;&nbsp;&nbsp;
									<a class="comphref"
										href="ATva_newactapplyManage!getWorkall?pagenum=${prepage }">上一页</a>&nbsp;&nbsp;&nbsp;
									<a class="comphref"
										href="ATva_newactapplyManage!getWorkall?pagenum=${nextpage }">下一页</a>&nbsp;&nbsp;&nbsp;
									<a class="comphref"
										href="ATva_newactapplyManage!getWorkall?pagenum=${sumpage }">尾页</a>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>


			<div id="update" class="modal fade" aria-hidden="true" tabindex="-1"
				role="dialog" aria-labelledby="myModalLabel">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-body">
							<div class="row">
								<h3 class="m-t-none m-b">修改活动申请</h3>
								<form role="form" name="actupdate" id="updateInfoForm" action=""
									method="post">
									<div class="form-group">
										<label for="">活动申请编号</label>&nbsp;<label for=""></label> <input
											type="text" id="actapplyNo"
											class="form-control doCheck_update" name="actapplynumber">
									</div>
									<div class="form-group">
										<label for="">活动名称</label>&nbsp;<label for=""></label> <input
											type="text" id="actapplyName"
											class="form-control doCheck_update" name="actapplyname">
									</div>
									<div class="form-group">
										<label for="">活动类型</label>&nbsp;<label for=""></label> <select
											id="actapplyType" class="form-control" name="actapplytype">
											<option value="1">确定性活动</option>
											<option value="2">选择性活动</option>
											<option value="3">其他活动</option>
										</select>
									</div>
									<div class="form-group">
										<label for="">活动分数</label>&nbsp;<label for=""></label> <input
											type="text" id="actapplyScore"
											class="form-control doCheck_update" name="actapplyscore">
									</div>
									<!--                                 <div class="form-group"> -->
									<!--                                     <label for="" >活动说明</label>&nbsp;<label for=""></label> -->
									<!--                                     <input type="text" id="actapplyInstruction" class="form-control doCheck_update" name="actapplyinstruction"> -->
									<!--                                 </div> -->
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
		</div>

		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script src="js/jquery.min.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<!-- Just to make our placeholder images work. Don't actually copy the next line! -->
		<!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
		<script src="js/ie10-viewport-bug-workaround.js"></script>
		<script src="js/jquery.min.js?v=2.1.4"></script>
		<script src="js/bootstrap.min.js?v=3.3.5"></script>
		<script src="js/bootstrap-modal.js"></script>
		<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
		<script src="js/content.min.js?v=1.0.0"></script>
		<script src="js/plugins/iCheck/icheck.min.js"></script>
		<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
		<script src="js/PublicCheck/PUB_SET.js"></script>
		<script src="My97DatePicker/WdatePicker.js"></script>
		<script src="js/AuditSubmitController.js"></script>
		<script src="js/jquery.min.js?v=2.1.4"></script>
		<script src="js/bootstrap.min.js?v=3.3.5"></script>
		<script src="js/plugins/jeditable/jquery.jeditable.js"></script>
		<script src="js/content.min.js?v=1.0.0"></script>
		<script src="js/plugins/iCheck/icheck.min.js"></script>
		<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
		<!-- 		<script src="js/PublicCheck/PUB_SET.js"></script> -->
		<script src="js/PublicCheck/formFieldController.js"></script>
		<script type="text/javascript">
        //页面初始化处理
        $(document).ready(function() {
            var tds = $('.disp');
            for(var i=0;i<tds.length;i++){
                tds[i].innerHTML = tds[i].title.substring(0,5)+"...";
            };
        });
        $('#updateInfo').click(function(){
       		var x = confirm("确认更新？");
            if(x){
            	submitUpdatedInfo("ATva_newactapplyManage", "updateRecord", "getWorkall");
//         	$.post("ATva_newactapplyManage!updateRecord?pagenum=1",
//                         {"id":row[0].cells[0].innerHTML,
//                         "level":row[0].cells[1].innerHTML,
//                         "score":row[0].cells[2].innerHTML
//                         },
//                         function(data,status){
//                             if(status=="success"){
//                                 if(data=="succ"){
//                                     swal("删除成功","","success");
//                                     setTimeout(function() {
//                                         window.location.replace("ATva_unjoinedactManage!getWorkall?pagenum=1");
//                                     }, 2000);
//                                 }else{
//                                     swal("操作失败",data,"error");
//                                 }
//                             }else{
//                                 swal("请求失败","","error");
//                             }
//                         }
//                 );
                }
        });
        
        function tanchu(file){
        	window.alert(file);
        };
        
        function modal(file){
        	window.showModalDialog(file);
        };
        
        
        $('.update').click(function(){
        
        	$('#actapplyNo').val($(this).parent().parent()[0].cells[0].innerHTML);
        	$('#actapplyScore').val($(this).parent().parent()[0].cells[7].innerHTML);
			$('#actapplyName').val($(this).parent().parent()[0].cells[1].innerHTML);
			
			var temp=$(this).parent().parent()[0].cells[3].innerHTML;
			$("#actapplyType option").each(function(){
				if($(this).text()==temp.trim()){
					$(this).prop("selected",true);
				}
			});
        });
        //
        var limit = getParameters("limit");
        comphref(limit);
        set_selected_option($('#changelength option'), limit);
        //对所有跳转链接加 limit字段
        function comphref(limits) {
            var hrefs = $('.comphref');
            for(var i=0;i<hrefs.length;i++){
                hrefs[i].href = hrefs[i].href+"&limit="+limits+"&foredate_CT="+$('#date1').val().trim()+"&afterdate_CT="+$('#date2').val().trim();
            }
            $('#pickdates')[0].action = $('#pickdates')[0].action+"&limit="+limits;
        }
        //变更每页显示记录数
        $('#changelength').change(function() {
            comphref($(this).val().trim());
            window.location.replace("ATva_newactapplyManage!getWorkall??pagenum=1&limit="+$(this).val().trim()+"&foredate_CT="+$('#date1').val().trim()+"&afterdate_CT="+$('#date2').val().trim());
        });
    </script>
		<!-- carry-data -->
		<script type="text/javascript">
        $('.delinf1').click(function() {
            var row = $(this).parent().parent();
            var x = confirm("确认删除？");
            if(x){
                $.post("ATva_newactapplyManage!deleteRecord?pagenum=1",
                        {"id":row[0].cells[0].innerHTML},
                        function(data,status){
                            if(status=="success"){
                                if(data=="succ"){
                                    swal("删除成功","","success");
                                    setTimeout(function() {
                                        window.location.replace("ATva_newactapplyManage!getWorkall?pagenum=1");
                                    }, 2000);
                                }else{
                                    swal("操作失败",data,"error");
                                }
                            }else{
                                swal("请求失败","","error");
                            }
                        }
                );
            }  else {
                alert("未删除");
            }
        });
        $("#datep").click(function(){
            if(($("#date1").val().length!=0 && $("#date2").val().length==0)
                    ||($("#date1").val().length==0 && $("#date2").val().length!=0)){
                window.alert("请填写完整日期");
                return false;
            }else{
                $("#conditionForm").submit();
            }
        });
        
    </script>
		<script type="text/javascript"
			src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
		<s:debug></s:debug>
</body>
</html>