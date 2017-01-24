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
    <style>
    	#tb td a:hover{
			color:deeppink;
			font-weight:bold;
		}
    </style>
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
</head>

<body class="gray-bg">
     <div class="ibox-content" style="height:540px;">
     <button class="btn  btn-primary openaddm" type="button" data-backdrop="true" data-target="#test" data-toggle="modal">
         	 <strong>导出</strong>
          </button>
     <br>
     <!--  -->
     <div id="test" class="modal fade" aria-hidden="true"tabindex="-1" role="dialog"  aria-labelledby="myModalLabel">
      	 <div class="modal-dialog" style="width:800px;">
 			<div class="modal-content">
		     	<div class="modal-body">
		        	 <div class="row">
		        	   <h3>正在查询数据，请稍后......</h3>
		               <h3 class="m-t-none m-b" id="updatemodaldialogTitle">修改科研奖励</h3>
		               <table border="1px" style="cellspacing:0px;cellpadding:0px;width:750px;margin:0 auto;">
		               		<tr>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               		</tr>
		               		<tr>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               			<td>data</td>
		               		</tr>
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
						<td>科研项目</td>
						<td>科研奖励</td>
						<td>学术著作</td>
						<td>参加学术会议</td>
						<td>入选人才工程</td>
						<td>邀请专家讲学</td>
						<td>期刊论文</td>
						<td>主承办学术会议</td>
						<td>总计(总/均)</td>
					</tr>
                    <tbody>
						<c:forEach var="entity" items="${scienReschModlSumryDataPerson }">
						<tr>
							<td>${entity.teacherName }</td>
							<td>${entity.teacherId }</td>
							<td><a href="#">${entity.scientificResearchProData.sum }/${entity.scientificResearchProData.avg }</a></td>
							<td><a href="#">${entity.scientificRewardData.sum }/${entity.scientificRewardData.avg }</a></td>
							<td><a href="#">${entity.academicWorkData.sum }/${entity.academicWorkData.avg }</a></td>
							<td><a href="#">${entity.joinAcademicMeetingData.sum }/${entity.joinAcademicMeetingData.avg }</a></td>
							<td><a href="#">${entity.talentProData.sum }/${entity.talentProData.avg }</a></td>
							<td><a href="#">${entity.inviteExpertSpeechData.sum }/${entity.inviteExpertSpeechData.avg }</a></td>
							<td><a href="#">${entity.periodicalData.sum }/${entity.periodicalData.avg }</a></td>
							<td><a href="#">${entity.undertakeAcademicMeetingData.sum }/${entity.undertakeAcademicMeetingData.avg }</a></td>
							<td><a href="#">${entity.sum }/${entity.avg }</td>
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
	</script>
	<!-- carry-data -->
    <script type="text/javascript" src="http://tajs.qq.com/stats?sId=9051096" charset="UTF-8"></script>
	<s:debug></s:debug>
     
</body>
</html>