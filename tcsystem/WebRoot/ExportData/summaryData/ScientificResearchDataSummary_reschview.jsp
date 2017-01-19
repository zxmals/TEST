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
	                    <button class="btn  btn-primary openaddm" type="button" data-backdrop="true">
	                        	 <strong>导出</strong>
	                         </button>
	                    <br>
	                        <div class="example" style="overflow: auto">
	                       <table id="tb" class="table table-striped table-bordered table-hover dataTables-example">
									<tr class="info">
										<td>研究所</td>
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
									<c:forEach var="entity" items="${scienReschModlSumryData }">
										<tr>
											<td>${entity.researchLabName }</td>
											<td>${entity.scientificResearchProData.sum }/${entity.scientificResearchProData.avg }</td>
											<td>${entity.scientificRewardData.sum }/${entity.scientificRewardData.avg }</td>
											<td>${entity.academicWorkData.sum }/${entity.academicWorkData.avg }</td>
											<td>${entity.joinAcademicMeetingData.sum }/${entity.joinAcademicMeetingData.avg }</td>
											<td>${entity.talentProData.sum }/${entity.talentProData.avg }</td>
											<td>${entity.inviteExpertSpeechData.sum }/${entity.inviteExpertSpeechData.avg }</td>
											<td>${entity.periodicalData.sum }/${entity.periodicalData.avg }</td>
											<td>${entity.undertakeAcademicMeetingData.sum }/${entity.undertakeAcademicMeetingData.avg }</td>
											<td>${entity.sum }/${entity.avg }</td>
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