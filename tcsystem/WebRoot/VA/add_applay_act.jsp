<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>AddVAapplyAct</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	 <link rel="stylesheet" type="text/css" href="css/zxma.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">function loads() { var status = "${addResStatus}";if(status!="")alert(status);}</script>
  </head>
  
  <body onload="loads()" >
	  	<div id="f">
	        <form method="post" name="vaaddreq"action="GTadd_applay_act!addAnoAct" enctype="multipart/form-data">
	            <div class="mediuam">
	                <label>申请其他公益活动</label>
	                <hr>
	            </div><br>
	            <div class="betu">
	                <label>活动名称:</label>
	                <input type="text" name="vaact.actName">
	            </div><br><br>
	            <div class="betu" style="display: none">
	                <label>&nbsp;申请人:</label>
	                <input type="text" name="teacher.teacherId" value="${teacher.teacherId }">
	            </div><br>
	            <div class="betu">
	                <label>&nbsp;参与人:</label>
	                <input type="text" name="vaact.attendee">
	            </div><br><br>
	            <div class="betu">
	                <label>活动描述:</label>
	                <textarea name="vaact.actapplyfile" rows="8" cols="43"></textarea>
	            </div><br><br>
	            <div class="betu" style=" display: none;">
	                <label>活动类别:</label>
	                <input type="text" name="vaact.actType" value="0">
	            </div>
	            <div class="button">
	                <button>提交</button>
	            </div>
	        </form>
	    </div>
  </body>
</html>
