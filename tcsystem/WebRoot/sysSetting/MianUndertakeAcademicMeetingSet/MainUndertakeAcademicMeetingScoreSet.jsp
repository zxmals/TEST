<%@page import="com.nuaa.ec.science.model.MainUndertakeAcademicMeetingPlace"%>
<%@page import="com.nuaa.ec.science.model.MainUndertakeAcademicMeetingType"%>
<%@page import="com.nuaa.ec.science.model.MainUndertakeAcademicMeetingScore"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<MainUndertakeAcademicMeetingScore> MeetingScorelist = (List)session.getAttribute("MainUndertakeAcademicMeetingScore");
	List<MainUndertakeAcademicMeetingType> meetingtypelist = (List)session.getAttribute("MainUndertakeAcademicMeetingType");
	List<MainUndertakeAcademicMeetingPlace> MUAMeetingPlacelist = (List)session.getAttribute("MainUndertakeAcademicMeetingPlace");
	String add = (String)request.getAttribute("add"); 
	String uudate = (String)request.getAttribute("i");
	String subModular = (String)request.getAttribute("SubModular");
	int uodate = 0;
	if(uudate!=null)
		uodate = Integer.parseInt(uudate);
	String update = (String)request.getAttribute("update");
%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>MianUndertakeAcademicMeetingScoreset</h1>
		
			<input type="button" value="添加" onclick="window.location.href='MainUndertakeAcademicMeetingScoreset!addMainAcaMscore'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;主承办学术会议评分编号&nbsp;</td>
					<td>&nbsp;主承办学术会议地点&nbsp;</td>
					<td>&nbsp;主承办学术会议类型&nbsp;</td>
					<td>&nbsp;子模块&nbsp;</td>
					<td>&nbsp;基准分数&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(MeetingScorelist!=null)
				if(MeetingScorelist.size()!=0){
					for(int i=0;i<MeetingScorelist.size();i++){
					%>
					<tr>
						<td><%=MeetingScorelist.get(i).getAcaMeetScoreID()%></td>
						<td><%=MeetingScorelist.get(i).getAcaMeetPlace()%></td>
						<td><%=MeetingScorelist.get(i).getAcaMeetType() %></td>
						<td><%=MeetingScorelist.get(i).getSubModular() %></td>
						<td><%=MeetingScorelist.get(i).getScore() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='MainUndertakeAcademicMeetingScoreset!updateMainAcaMScore?i=<%=i %>&MainAcaMScoreId=<%=MeetingScorelist.get(i).getAcaMeetScoreID() %>'"/>&nbsp;
							<input type="submit" value="删除" onclick=""/>
						</td>
					</tr>
					<%}} %>
			</table>
			
			<%
			if(update!=null)	
				if(update.equals("1")) {%>
			<form name="f" action="" method="post">
				<table border="1px" cellspacing="0px" style="border-collapse:collapse;margin-top: 120px">
				<tr>
					<td>子模块编号:</td>
					<td><input type="text"  value="<%=MeetingScorelist.get(uodate).getSubModularID() %>"  name="SubModularID"  readonly="readonly"/></td>
				</tr>
				<tr>
					<td>&nbsp;主承办学术会议类型:&nbsp;</td>
					<td>
						<select name="MeetingTypeId">
							<%for(int i=0;i<meetingtypelist.size();i++){ %>
								<option value="<%=meetingtypelist.get(i).getAcaMeetTypeID()%>" 
								<%if(meetingtypelist.get(i).getAcaMeetTypeID().equals(MeetingScorelist.get(uodate).getAcaMeetTypeID())){ %>selected="selected" <%} %>>
								<%=meetingtypelist.get(i).getAcaMeetType() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;主承办学术会议地点:&nbsp;</td>
					<td>
						<select name="MeetingPlaceId">
							<%for(int i=0;i<MUAMeetingPlacelist.size();i++){ %>
								<option value="<%=MUAMeetingPlacelist.get(i).getAcaMeetPlaceID()%>"
								<%if(MUAMeetingPlacelist.get(i).getAcaMeetPlaceID().equals(MeetingScorelist.get(uodate).getAcaMeetPlaceID())){ %>selected="selected" <%} %>>
								<%=MUAMeetingPlacelist.get(i).getAcaMeetPlace()%>
								</option>
							<%} %>
						</select>
					</td>		
				</tr>
				<tr>
					<td>&nbsp;主承办学术会议评分编号:&nbsp;</td>
					<td>
						<input type="text" name="MainAcaMScoreId" value="<%=MeetingScorelist.get(uodate).getAcaMeetScoreID()%>"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="MainAcaMScore" value="<%=MeetingScorelist.get(uodate).getScore()%>"/></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='MainUndertakeAcademicMeetingScoreset!doupdateMianAcaMScore';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
			<%
			if(add!=null)	
				if(add.equals("1")) {%>
			<form name="f" action="" method="post">
				<table border="1px" cellspacing="0px" style="border-collapse:collapse;margin-top: 120px">
				<tr>
					<td>&nbsp;子模块:</td>
					<td><input type="text"  value="<%=subModular %>" name="SubModularID"  readonly="readonly"/></td>
				</tr>
				<tr>
					<td>&nbsp;主承办学术会议类型:&nbsp;</td>
					<td>
						<select name="MeetingTypeId">
							<%for(int i=0;i<meetingtypelist.size();i++){ %>
								<option value="<%=meetingtypelist.get(i).getAcaMeetTypeID()%>" >
								<%=meetingtypelist.get(i).getAcaMeetType() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;主承办学术会议地点:&nbsp;</td>
					<td>
						<select name="MeetingPlaceId">
							<%for(int i=0;i<MUAMeetingPlacelist.size();i++){ %>
								<option value="<%=MUAMeetingPlacelist.get(i).getAcaMeetPlaceID()%>">
								<%=MUAMeetingPlacelist.get(i).getAcaMeetPlace()%>
								</option>
							<%} %>
						</select>
					</td>		
				</tr>
				<tr>
					<td>&nbsp;主承办学术会议评分编号:&nbsp;</td>
					<td>
						<input type="text" name="MainAcaMScoreId" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="MainAcaMScore" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='MainUndertakeAcademicMeetingScoreset!doaddMainAcaMScore';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>