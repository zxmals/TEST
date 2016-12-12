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

    <title>TFteachingPaper --Personal-Set</title>
    
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
    <script type="text/javascript">
    	var teacherIds = "${teacher.teacherId}"; 
    	function DoCheck() {
    		var res = '${operstatus}';
    		//alert(addres);
			switch (res){
				case '-1':swal("操作失败 fail !!!");
				break;
				case '1':swal("添加成功!");
				break;
				default: break;
			}
		}
    </script>
</head>

<body class="gray-bg"  onload="DoCheck()">
	<div class="chooseact">
    <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>个人参与教学论文管理<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    <form id="adjusts" action="GTteachingpaperPerformanceSet-person!getPersonJoin" method="get">
							<div>
			                    	<a>每页   
			                    	<select name="limit" id="changelength" style="width:60px;height:25px;border-radius:3px;">
			                    		<option selected="selected"></option>
			                    		<option>2</option>
			                    		<option>1</option>
			                    		<option>5</option>
			                    		<option>10</option>
			                    		<option>15</option>
			                    		<option>20</option>
			                    	</select>  条记录</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="#337AB7">学期：</font>
								<select id="termSelection" name="term.termId" style="width:120px;height:25px;border-radius:3px;">
									<option value="">全部学期</option>
									<c:forEach var="tfterm" items="${tftermList }">
										<option value="${tfterm.termId }">${tfterm.term }</option>
									</c:forEach>
								</select>
								&nbsp;&nbsp;&nbsp;
								<button class="button_set" type="submit" id="AlterPageSize"
								data-backdrop="true" data-toggle="modal">
								<strong>查询</strong>
								</button>
							</div>
						</form>
	                    <br>
	                        <div class="example">
	                        <form method="post" name="f">
	                       <table  id="tb" class="table table-striped table-bordered table-hover dataTables-example">
	                            <thead>
	                                <tr>
										<td>教学论文Id</td>
										<td>论文标题</td>
										<td>项目总分</td>
										<td>学期</td>
										<td>个人所得分</td>
										<td>登记负责人Id</td>
										<td>登记负责人</td>
										<td>状态</td>
										<td>操作</td>
									</tr>
	                            </thead>
	                            <tbody>
									<c:forEach var="ebj" items="${teachpaperpreceli }">
											<td>${ebj.tfteachingPaperProject.teachPaperId }</td>
											<td>${ebj.tfteachingPaperProject.teachPaperName }</td>
											<td>${ebj.tfteachingPaperProject.projectSumScore }</td>
											<td title="${ebj.tfteachingPaperProject.tfterm.termId }">${ebj.tfteachingPaperProject.tfterm.term }</td>
											<td>${ebj.singelScore }</td>
											<td>${ebj.tfteachingPaperProject.chargePersonId }</td>
											<td>${teachermp[ebj.tfteachingPaperProject.chargePersonId] }</td>
											<td title="${ebj.checkOut }">
												<c:if test="${ebj.checkOut==0 }">待完善</c:if>
												<c:if test="${ebj.checkOut==1 }">已完善,待审核</c:if>
												<c:if test="${ebj.checkOut==3 }">已审核</c:if>
												<c:if test="${ebj.checkOut==2 }">未通过</c:if>
											</td>
											<td>
												<c:if test="${teacher.teacherId!=ebj.tfteachingPaperProject.chargePersonId }">
													<c:if test="${ebj.checkOut==0 }"><a class="btn btn-primary btn-sm quitpaper">退出</a></c:if>
													<c:if test="${ebj.checkOut==1 }"><a  class="btn btn-primary btn-sm quitpaper">退出</a></c:if>
													<c:if test="${ebj.checkOut==3 }">&nbsp;&nbsp;&nbsp;√</c:if>
													<c:if test="${ebj.checkOut==2 }"><a  class="btn btn-primary btn-sm quitpaper">退出</a></c:if>
												</c:if>
												<c:if test="${ebj.checkOut==3 }">&nbsp;&nbsp;&nbsp;√</c:if>
											</td>
										</tr>
									</c:forEach>
	                            </tbody>                           
	                        </table>
	                        </form>
	                        <div style="text-align: center;">
	                        	(共查询到${sumrow }记录)&nbsp;&nbsp;&nbsp;&nbsp;
	                        	第${pagenum }/${sumpage }页&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTteachingpaperPerformanceSet-person!getPersonJoin?pagenum=1">首页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTteachingpaperPerformanceSet-person!getPersonJoin?pagenum=${prepage }">上一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTteachingpaperPerformanceSet-person!getPersonJoin?pagenum=${nextpage }">下一页</a>&nbsp;&nbsp;&nbsp;
	                        	<a class="comphref" href="GTteachingpaperPerformanceSet-person!getPersonJoin?pagenum=${sumpage }">尾页</a>
	                        </div>
	                   </div>
	                </div>
	            </div>
	        </div>
	    </div>   
	    
	    
    </div>
    </div>
    <script src="js/jquery.min.js?v=2.1.4"></script>
    <script src="js/bootstrap.min.js?v=3.3.5"></script>
    <script src="js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="js/content.min.js?v=1.0.0"></script>
    <script src="js/plugins/iCheck/icheck.min.js"></script>
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script  src="js/PublicCheck/PUB_SET.js"></script>
    <script  src="My97DatePicker/WdatePicker.js"></script>
    <script>
    //页面初始化处理
	var limit = getParameters("limit");
	var termId = getParameters("term.termId");
	set_selected_option($('#changelength option'), limit);
	set_selected_option($('#termSelection option'), termId);
	comphref(limit);
	//对所有跳转链接加 limit字段
	function comphref(limits) {
		var hrefs = $('.comphref');
		for(var i=0;i<hrefs.length;i++){
			hrefs[i].href = hrefs[i].href+"&limit="+limits+"&term.termId="+$('#termSelection').val().trim();
		}
	}
	//退出
	$('.quitpaper').click(function() {
		var row = $(this).parent().parent();
		swal({   title: "确定退出?",   
				 text: "即将推出 . . . ",   
				 type: "warning",   
				 showCancelButton: true,   
				 confirmButtonColor: "#DD6B55",   
				 confirmButtonText: "退出",   
				 cancelButtonText: "取消",   
				 closeOnConfirm: false,   
				 closeOnCancel: true }, 
				 function(isConfirm){   
					 if (isConfirm) {     
						 $.post("GTteachingpaperPerformanceSet-person!quitProject",
									{"teachpaperproject.teachPaperId":row[0].cells[0].innerHTML.trim()},
									function(data,status){
										 if(status=="success"){
											 if(data=="succ"){
												 swal("退出成功","","success");
												 row.remove();
											 }else{
												 swal("退出错误","","warning");
											 }
										 }else{
											 swal("请求失败","","warning");
										 }
									}
							);   
						 }    
					 } 
			);
	});
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>