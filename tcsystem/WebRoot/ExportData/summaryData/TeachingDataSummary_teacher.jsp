<%@page import="com.nuaa.ec.utils.StoreData"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("teachermp", StoreData.getTeachertranslate());
request.setAttribute("termList", StoreData.getTftermList());
%>
  <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
  <base target="results"> 
    </head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>EXPORT-TEACHING_teacher-DATA</title>
    
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
</head>

<body class="gray-bg"  onload="">
	<div class="chooseact">
    <div class="wrapper wrapper-content animated fadeInRight">
	        <div class="row">
	            <div class="col-sm-12">
	                <div class="ibox float-e-margins">
	                    <div class="ibox-title">
	                        <h5>教学数据导出--按教师导出<small></small></h5>
	                        <div class="ibox-tools" >
	                        </div>
	                    </div>
	                    <div class="ibox-content" style="height:540px;">
	                    	 <br>
	                         <form name="sumf" method="post" action="ATTeachingDataSUM_teacherview!getTeacherSummaryData" >
	                         <div>
									<a>[学期范围]&nbsp;&nbsp;从:
										<select style="width:120px;height:25px;border-radius:3px;" id="date1" class="addcheck nullcheck" name="foreterm">
											<option selected="selected"></option>
											<c:forEach var="term" items="${termList }">
												<option value="${term.termId }">${term.term }</option>
											</c:forEach>
										</select>									
										到:
										<select style="width:120px;height:25px;border-radius:3px;" id="date2" class="addcheck nullcheck" name="afterterm">
											<option selected="selected"></option>
											<c:forEach var="term" items="${termList }">
												<option value="${term.termId }">${term.term }</option>
											</c:forEach>
										</select>									
										&nbsp;&nbsp;</a>
									<a>
										[教师编号]:<input type="text" name="teacherId" id="teacherId" 
											 style="width:120px;height:25px;border-radius:3px;border:1px solid #C0C0C0;" placeholder="请输入教师编号"/>
									</a>&nbsp;&nbsp;&nbsp;&nbsp;
									</a>
									<button class="btn  btn-primary" type="button" id="subcheck" data-backdrop="true">
		                        	 <strong>查询</strong>
		                         	</button>
		                    </div>
		                    </form>
	                    <br><hr>
	                    <iframe style="border: none;width: 100%;height: 400px" name="results" src=""></iframe>
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
    <script  src="js/PublicCheck/PUB_SET.js"></script>
    <script  src="My97DatePicker/WdatePicker.js"></script>
    <!-- ISBN输入控制 -->
    <script src="js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <!-- sweet-alert -->
    <script src="js/plugins/sweetalert/sweetalert.min.js"></script>
    <script>
    $('#subcheck').click(function() {
		if(checkadds()){
			document.sumf.submit();
		}else{
				swal("WARNING!","请完善所有信息后提交","warning");
		}
	});
    	
    
    </script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>