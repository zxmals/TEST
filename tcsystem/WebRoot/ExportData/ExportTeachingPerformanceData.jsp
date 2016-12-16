<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("teachermp", StoreData.getTeachertranslate());
request.setAttribute("teachingModule", StoreData.getTeachingModule());
request.setAttribute("termList", StoreData.getTftermList());
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

    <title>EXPORT-TEACHING-DATA</title>
    
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
	                        <h5>教学数据导出<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    	 <br>
	                         
	                         <div>
									<a>[时间范围]&nbsp;&nbsp;从:
									<select style="width:120px;height:25px;border-radius:3px;" id="date1">
										<c:forEach var="term" items="${termList }">
											<option value="${term.termId }">${term.term }</option>
										</c:forEach>
									</select>									
									到:
									<select style="width:120px;height:25px;border-radius:3px;" id="date2">
										<c:forEach var="term" items="${termList }">
											<option value="${term.termId }">${term.term }</option>
										</c:forEach>
									</select>									
									&nbsp;&nbsp;</a>
<!-- 									<a>[时间范围]&nbsp;&nbsp;从:<input type="text" id="date1" class="" onClick=""  value="${foredate }" name="foredate" style="width:120px;height:25px;border-radius:3px;" /> -->
<!-- 									到:<input type="text" id="date2" onClick="" class="addcheck nullcheck"  value="${afterdate }" name="afterdate" style="width:120px;height:25px;border-radius:3px;" /> -->
<!-- 									&nbsp;&nbsp;</a> -->
		                    	<a>[系别]:
			                    	<select style="width:120px;height:25px;border-radius:3px;" id="departmentId" class="addcheck nullcheck" >
			                    			<option selected="selected" value=""></option>
											<c:forEach var="department" items="${departmentList }">
												<option value="${department.departmentId }">${department.departmentName }</option>
											</c:forEach>
									</select></a>&nbsp;&nbsp;&nbsp;&nbsp;
									<a>
										[教学模块]:<select style="width:120px;height:25px;border-radius:3px;" id="teachingModule" class="addcheck nullcheck" >
			                    			<option selected="selected" value=""></option>
											<c:forEach var="exp" items="${teachingModule }">
												<option value="${exp.value }">${exp.key }</option>
											</c:forEach>
									</select></a>&nbsp;&nbsp;&nbsp;&nbsp;
									</a>
									<button class="btn  btn-primary" type="submit" id="subexports" data-backdrop="true">
		                        	 <strong>导出</strong>
		                         	</button>
		                    </div>
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
	
    $('#subexports').click(function() {
		if(checkadds()){
// 			window.alert($('#departmentId option:selected').text().trim());
// 			window.alert($('#date2').val().trim());
			
			window.location.replace("ATTeachingModuleDataExport!generateExportData?foredate="+$('#date1 option:selected').val().trim()
					+"&afterdate="+$('#date2 option:selected').val().trim()+"&entitys="+$('#teachingModule').val().trim()
					+"&department.departmentId="+$('#departmentId').val().trim());
		}else{
				swal("WARNING!","请完善所有信息后提交","warning");
		}
	});
    
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>