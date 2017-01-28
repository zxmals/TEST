<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("teachermp", StoreData.getTeachertranslate());
%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="_self"> 
    </head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>AcademicWork --work-Set</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="css/zxma.css">
    <link href="css/animate.min.css" rel="stylesheet">
    <link href="css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <link rel="stylesheet" href="css/mermberTab.css" >
    <script  src="My97DatePicker/WdatePicker.js"></script>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script  src="js/PublicCheck/PUB_SET.js"></script>
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

<body class="gray-bg"  onload="DoCheck()">
	<div class="datepick">
		<span>选择日期范围</span>
		<div>
			<form action="ATva_actManage!getWorkall?pagenum=1" method="post" name="pickdate" id="pickdates">
				从:<input type="text" id="date1" class="Wdate" onClick="WdatePicker()"  value="${foredate_CT }" name="foredate_CT" />到:<input type="text" id="date2" onClick="WdatePicker()" class="Wdate"  value="${afterdate_CT }" name="afterdate_CT" />
				&nbsp;&nbsp;<input type="submit" id="datep" value="查寻" title="点击查询" >
			</form>
		</div>
	</div>
	<div class="chooseact">
    <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>活动参与管理<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    <div>
	                    	<a>每页   
	                    	<select id="changelength" style="width:45px">
	                    		<option selected="selected"></option>
<!-- 	                    		<option>1</option> -->
<!-- 	                    		<option>2</option> -->
	                    		<option>5</option>
	                    		<option>10</option>
	                    		<option>15</option>
	                    		<option>20</option>
	                    	</select>  条记录</a>
	                    </div>
	                    <br>
	                        <div class="example">
	                        <form method="post" name="f">
	                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
	                            <thead>
	                                <tr>
										<td>公益活动编号</td>
                                        <td>活动名称</td>
                                        <td>参与人员</td>
                                        <td>活动日期</td>
                                        <td>教师编号</td>
                                        <td>教师姓名</td>
                                        <td>状态</td>
                                        <td>操作</td>
									</tr>
	                            </thead>
	                            <tbody>
									<c:forEach var="VaAddJoinedAct" items="${List }">
										<tr>
											<td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.actPubId }</td>
                                            <td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.vacollectiveAct.actName }</td>
                                            <td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.vacollectiveAct.attendee }</td>
                                            <td>${VaAddJoinedAct.id.vacollectiveActivitiesPublish.actDate}</td>
                                            <td>${VaAddJoinedAct.id.teacher.teacherId }</td>
                                            <td>${VaAddJoinedAct.id.teacher.teacherName }</td>
                                            <td>
                                            <c:if test="${VaAddJoinedAct.aspareTire == '0'}">待审核</c:if>
                                            <c:if test="${VaAddJoinedAct.aspareTire == '1'}">审核通过</c:if>
                                            <c:if test="${VaAddJoinedAct.aspareTire == '2'}">不通过</c:if>
                                            </td>
											<td>
													<c:if test="${VaAddJoinedAct.aspareTire == '0' }">
														<a class="btn btn-primary btn-sm delwords delinf" data-toggle="modal">删除</a>
													</c:if>
											</td>
										</tr>
									</c:forEach>
	                            </tbody>                           
	                        </table>
	                        </form>
	                        <div style="text-align: center;">
	                        	(共查询到${sumrow }条记录)&nbsp;&nbsp;&nbsp;&nbsp;
	                        	第${pagenum }/${sumpage }页&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="ATva_actManage!getWorkall?pagenum=1">首页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="ATva_actManage!getWorkall?pagenum=${prepage }">上一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="ATva_actManage!getWorkall?pagenum=${nextpage }">下一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="ATva_actManage!getWorkall?pagenum=${sumpage }">尾页</a>
	                        </div>
	                   </div>
	                </div>
	            </div>
	        </div>
	    </div>   
    </div>
    <script>
    //页面初始化处理
    $(document).ready(function() {
		var tds = $('.disp');
		for(var i=0;i<tds.length;i++){
			tds[i].innerHTML = tds[i].title.substring(0,5)+"...";
		}
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
		window.location.replace("ATva_actManage!getWorkall??pagenum=1&limit="+$(this).val().trim()+"&foredate_CT="+$('#date1').val().trim()+"&afterdate_CT="+$('#date2').val().trim());
	});
	</script>
	<!-- carry-data -->
	<script type="text/javascript">
  	$('.delinf').click(function() {
		 var row = $(this).parent().parent();
		 var x = confirm("确认删除？");
		 if(x){
				  $.post("ATva_actManage!deleteRecord?pagenum=1",
    							{"actPubId":row[0].cells[0].innerHTML,
    							 "teacherId":row[0].cells[4].innerHTML},
    							function(data,status){
    								if(status=="success"){
    									if(data=="succ"){
    										swal("删除成功","","success");
    										setTimeout(function() {
    											window.location.replace("ATva_actManage!getWorkall?pagenum=1");
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
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>