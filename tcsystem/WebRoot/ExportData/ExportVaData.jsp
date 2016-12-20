<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("teachermp", StoreData.getTeachertranslate());
request.setAttribute("departmentList",StoreData.getDepartmentList());
request.setAttribute("VAexports",StoreData.getVaexporcts());
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

    <title>EXPORT-VaActivity-DATA</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="../css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="../css/zxma.css">
    <link href="../css/animate.min.css" rel="stylesheet">
    <link href="../css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <link rel="stylesheet" href="../css/mermberTab.css" >
</head>

<body class="gray-bg"  onload="">
	<div class="chooseact">
    <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>科研数据导出<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    	 <br>
	                         
	                         <div>
	                         	<p>导出活动列表</p>
									<a>[时间范围]&nbsp;&nbsp;从:<input type="text" id="date1" class="Wdate" onClick="WdatePicker()"  value="${foredate }" name="foredate" style="width:120px;height:25px;border-radius:3px;" />
									到:<input type="text" id="date2" onClick="WdatePicker()" class="Wdate addcheck nullcheck"  value="${afterdate }" name="afterdate" style="width:120px;height:25px;border-radius:3px;" />
									&nbsp;&nbsp;</a>
		                    	<a>[系]:
			                    	<select style="width:120px;height:25px;border-radius:3px;" id="departmentId" class="addcheck nullcheck" >
			                    			<option selected="selected" value=""></option>
											<c:forEach var="department" items="${departmentList }">
												<option value="${department.departmentId }">${department.departmentName }</option>
											</c:forEach>
									</select></a>&nbsp;&nbsp;&nbsp;&nbsp;
									<a>
										[活动类型]:<select style="width:120px;height:25px;border-radius:3px;" id="vaacttype" class="addcheck nullcheck" >
			                    			<option selected="selected" value=""></option>
<!-- 											<c:forEach var="exp" items="${VAexports }"> -->
<!-- 												<option value="${exp.value }">${exp.key }</option> -->
<!-- 											</c:forEach> -->
											
											<option value="1">规定性活动</option>
											<option value="2">选择性活动</option>
											<option value="3">其他活动</option>
											<option value="0">全部活动</option>

									</select></a>&nbsp;&nbsp;&nbsp;&nbsp;
									</a>
									<button class="btn btn-primary" type="submit" id="searchvaList" data-backdrop="true"><strong>查找活动</strong></button>
									<button class="btn btn-primary" type="submit" id="vaListexports" data-backdrop="true"><strong>导出活动列表</strong></button>
		                    </div>
		                    	<div id="vaactlist">
		                    		<c:forEach var="vaact" items="${vaactList }">
		                    			<p>${vaact.vacollectiveAct.actName }&nbsp;${vaact.actDate }&nbsp;${vaact.vacollectiveAct.actType }&nbsp;<button class="button_set" type="submit" id="vaactexports" data-backdrop="true" value="${vaact.actPubId }">导出该活动</button>
		                    			</p>
		                    		</c:forEach>
		                    	</div>
<!-- 		                    <div> -->
<!-- 		                    	<p>导出活动信息</p> -->
<!-- 									<a>[时间范围]&nbsp;&nbsp;从:<input type="text" id="date1" class="Wdate" onClick="WdatePicker()"  value="${foredate }" name="foredate" style="width:120px;height:25px;border-radius:3px;" /> -->
<!-- 									到:<input type="text" id="date2" onClick="WdatePicker()" class="Wdate addcheck nullcheck"  value="${afterdate }" name="afterdate" style="width:120px;height:25px;border-radius:3px;" /> -->
<!-- 									&nbsp;&nbsp;</a> -->
<!-- 		                    	<a>[系]: -->
<!-- 			                    	<select style="width:120px;height:25px;border-radius:3px;" id="researchlabId" class="addcheck nullcheck" > -->
<!-- 			                    			<option selected="selected" value=""></option> -->
<!-- 											<c:forEach var="researchLab" items="${researchLabList }"> -->
<!-- 												<option value="${researchLab.researchLabId }">${researchLab.researchLabName }</option> -->
<!-- 											</c:forEach> -->
<!-- 									</select></a>&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 									<a> -->
<!-- 										[科研模块]:<select style="width:120px;height:25px;border-radius:3px;" id="scienmodual" class="addcheck nullcheck" > -->
<!-- 			                    			<option selected="selected" value=""></option> -->
<!-- 											<c:forEach var="exp" items="${scienexports }"> -->
<!-- 												<option value="${exp.value }">${exp.key }</option> -->
<!-- 											</c:forEach> -->
<!-- 									</select></a>&nbsp;&nbsp;&nbsp;&nbsp; -->
<!-- 									</a> -->
<!-- 									<button class="btn  btn-primary" type="submit" id="subexports" data-backdrop="true"> -->
<!-- 		                        	 <strong>导出</strong> -->
<!-- 		                         	</button> -->
<!-- 		                    </div> -->
	                    <br><hr>
	                </div>
	            </div>
	            
	            
	        </div>
	    </div>   
    </div>
    
    
	    
    <script src="../js/jquery.min.js?v=2.1.4"></script>
    <script src="../js/bootstrap.min.js?v=3.3.5"></script>
    <script src="../js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="../js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="../js/content.min.js?v=1.0.0"></script>
    <script src="../js/plugins/iCheck/icheck.min.js"></script>
    <script  src="../js/PublicCheck/PUB_SET.js"></script>
    <script  src="../My97DatePicker/WdatePicker.js"></script>
    <script src="js/bootstrap-modal.js"></script>
    
    <!-- ISBN输入控制 -->
    <script src="../js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <!-- sweet-alert -->
    <script src="../js/plugins/sweetalert/sweetalert.min.js"></script>
    <script>
//    //页面初始化处理
// 	var limit = getParameters("limit");
// 	comphref(limit);
// 	set_selected_option($('#changelength option'), limit);
//	//对所有跳转链接加 limit字段
// 	function comphref(limits) {
// 		var hrefs = $('.comphref');
// 		for(var i=0;i<hrefs.length;i++){
// 			hrefs[i].href = hrefs[i].href+"&limit="+limits+"&foredate="+$('#date1').val().trim()+"&afterdate="+$('#date2').val().trim();
// 		}
// 		$('#pickdates')[0].action = $('#pickdates')[0].action+"&limit="+limits;
// 	}
//	//变更每页显示记录数
//     $('#changelength').change(function() {
//     	comphref($(this).val().trim());
// 		window.location.replace("GTacademicwork-workset!getWorkall?pagenum=1&limit="+$(this).val().trim()+"&foredate="+$('#date1').val().trim()+"&afterdate="+$('#date2').val().trim());
// 	});
	
    $('#vaListexports').click(function() {
		if(checkadds()){
			window.location.replace("ATVaActListExport!generateExportDataList?foredate="+$('#date1').val().trim()
					+"&afterdate="+$('#date2').val().trim()+"&vaacttype="+$('#vaacttype').val().trim()
					+"&department.departmentId="+$('#departmentId').val().trim());
		}else{
				swal("WARNING!","请完善所有信息后提交","warning");
		}
	});
	$('#searchvaList').click(function(){
		if(checkadds()){
			window.location.replace("ATVaActListExport!lookateveryAct?foredate="+$('#date1').val().trim()
					+"&afterdate="+$('#date2').val().trim()+"&vaacttype="+$('#vaacttype').val().trim()
					+"&department.departmentId="+$('#departmentId').val().trim()
			);
		}else{
				swal("WARNING!","请完善所有信息后提交","warning");		
		}
	});
	$('#vaactexports').click(function(){
		
	});
	
    
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>