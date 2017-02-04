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

    <title>Teaching-DATASUMMARY --DEPART-</title>
    
    <link rel="shortcut icon" href="favicon.ico"> <link href="../../css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="../../css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    
    <!-- Sweet Alert -->
    <link href="../../css/plugins/sweetalert/sweetalert.css" rel="stylesheet">

    <!-- Data Tables -->
    <link href="../../css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="../../css/zxma.css">
    <link href="../../css/animate.min.css" rel="stylesheet">
    <link href="../../css/style.min.css?v=4.0.0" rel="stylesheet"><base target="_blank">
    <link rel="stylesheet" href="../../css/mermberTab.css" >
    <style>
    	#tb td a:hover{
			color:deeppink;
			font-weight:bold;
		}
    </style>
    <script  src="My97DatePicker/WdatePicker.js"></script>
    <script src="../../js/jquery.min.js?v=2.1.4"></script>
    <script src="../../js/bootstrap.min.js?v=3.3.5"></script>
    <script src="../../js/plugins/jeditable/jquery.jeditable.js"></script>
    <script src="../../js/plugins/dataTables/dataTables.bootstrap.js"></script>
    <script src="../../js/content.min.js?v=1.0.0"></script>
    <script src="../../js/plugins/iCheck/icheck.min.js"></script>
    <script  src="../../js/PublicCheck/PUB_SET.js"></script>
    <!-- ISBN输入控制 -->
    <script src="../../js/plugins/jasny/jasny-bootstrap.min.js"></script>
    <!-- sweet-alert -->
    <script src="../../js/plugins/sweetalert/sweetalert.min.js"></script>
</head>

<body class="gray-bg">
     <div class="ibox-content" style="height:540px;">
<!--      <button class="btn  btn-primary openaddm" type="button" data-backdrop="true" data-target="#dataDiv" data-toggle="modal"> -->
<!--          	 <strong>导出</strong> -->
<!--           </button> -->
     <br>
     <!--  -->
     <div id="dataDiv" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"  aria-labelledby="myModalLabel">
      	 <div class="modal-dialog" style="width:1100px;">
 			<div class="modal-content">
		     	<div class="modal-body">
		        	 <div class="row">
		        	   <h3 id="tableCaption" style="text-align:center;">无对应记录...</h3>
<!-- 		               <h3 class="m-t-none m-b" id="updatemodaldialogTitle">${sessionScope.moduleName_person }</h3> -->
		               <table id="dataTable" border="1px" style="cellspacing:0px;cellpadding:0px;width:1050px;margin:0 auto;font-size: 11px;"
		                class="table table-striped table-bordered table-hover dataTables-example">
		               </table>
	                   <div>
	                       <button type="button" style="margin-top:15px;" class="btn btn-outline btn-primary pull-right m-t-n-xs" data-dismiss="modal">关闭</button>
	                  </div>
	                </div>
   		     	</div>
        	</div>
		</div>
             </div>
                <div class="example" style="overflow: auto">
               <table id="tb" class="table table-striped table-bordered table-hover dataTables-example">
					<tr class="info">
						<td>教师姓名</td>
						<td>教师工号</td>
						<td>活动参与</td>
						<td>活动缺席</td>
						<td>总计(总/均)</td>
					</tr>
                    <tbody>
						<c:forEach var="entity" items="${vaActSummaryDateByPerson }">
						<tr>
							<td>${entity.teacherName }</td>
							<td>${entity.teacherId }</td>
							<td><a onclick="showDetail('${entity.teacherId }','scienReschPro');" data-backdrop="true" data-target="#dataDiv" data-toggle="modal">${entity.teacherJoinedData.sum }/${entity.teacherJoinedData.average }</a></td>
							<td><a onclick="showDetail('${entity.teacherId }','scienReschRewd');" data-backdrop="true" data-target="#dataDiv" data-toggle="modal">${entity.unjoinedActData.sum }/${entity.unjoinedActData.average }</a></td>
							<td>${entity.sum }/${entity.average }</td>
						</tr>
						</c:forEach>
           		 </tbody>                           
         	</table>
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
    function showDetail(teacherId,moduleName){
    	clear();
    	$.post("ATVaActDataSummary_teacherIframe!getDetailDataInfoJson",
			{
   				teacherId:teacherId,
   				moduleName:moduleName,
   				foredate:'${sessionScope.foredate_person}',
   				afterdate:'${sessionScope.afterdate_person}'
   			},
   			function(data,status){
    			if(status=="success"){
//     				window.alert("heloo");
    				var d = eval("("+data+")")
//     				window.alert(d.field.length);
//     				window.alert(d.dataArray[0].length);
//     				window.alert(d.dataArray.length);
					create(d);
    			}
    		});
    }
    
	</script>
	<!-- carry-data -->
	<script type="text/javascript" src="../../js/createTable.js"></script>
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>