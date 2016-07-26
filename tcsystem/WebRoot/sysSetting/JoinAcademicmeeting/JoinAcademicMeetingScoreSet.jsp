<%@page import="com.nuaa.ec.science.model.MeetingType"%>
<%@page import="com.nuaa.ec.science.model.PaperRetrievalCondition"%>
<%@page import="com.nuaa.ec.science.model.JoinAcademicMeetingScore"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<JoinAcademicMeetingScore> MeetingScorelist = (List)session.getAttribute("MeetingScore");
	List<PaperRetrievalCondition> MeetingPaperRetrievallist = (List)session.getAttribute("MeetingPaperRetrieval");
	List<MeetingType> meetingtypelist = (List)session.getAttribute("MeetingType");
	String add = (String)request.getAttribute("add"); 
	String uudate = (String)request.getAttribute("i");
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
		<h1>JoinAcademicMeetingScoreset</h1>
		
			<input type="button" value="添加" onclick="window.location.href='JoinAcademicMeetingScoreset!add'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;参加学术会议评分编号&nbsp;</td>
					<td>&nbsp;会议论文检索条目编号&nbsp;</td>
					<td>&nbsp;参加学术会议类型编号&nbsp;</td>
					<td>&nbsp;子模块编号&nbsp;</td>
					<td>&nbsp;基准分数&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(MeetingScorelist!=null)
				if(MeetingScorelist.size()!=0){
					for(int i=0;i<MeetingScorelist.size();i++){
					%>
					<tr>
						<td><%=MeetingScorelist.get(i).getJoinAMScoreID()%></td>
						<td><%=MeetingScorelist.get(i).getPRConditionID()%></td>
						<td><%=MeetingScorelist.get(i).getMeetingTypeID() %></td>
						<td>SM5</td>
						<td><%=MeetingScorelist.get(i).getScore() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='JoinAcademicMeetingScoreset!updateJoinAcaMScore?i=<%=i %>&JAMScoreId=<%=MeetingScorelist.get(i).getJoinAMScoreID()%>'"/>&nbsp;
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
					<td><input type="text"  value="SM5"  name="SubModularID"  disabled="disabled"/></td>
				</tr>
				<tr>
					<td>&nbsp;参加学术会议类型:&nbsp;</td>
					<td>
						<select name="JoinAcaMTId">
							<%for(int i=0;i<meetingtypelist.size();i++){ %>
								<option value="<%=meetingtypelist.get(i).getMeetingTypeID()%>" 
								<%if(meetingtypelist.get(i).getMeetingTypeID().equals(MeetingScorelist.get(uodate).getMeetingTypeID())){ %>selected="selected" <%} %>>
								<%=meetingtypelist.get(i).getMeetingTypeName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;会议论文检索条目:&nbsp;</td>
					<td>
						<select name="JoinAcaMPRID">
							<%for(int i=0;i<MeetingPaperRetrievallist.size();i++){ %>
								<option value="<%=MeetingPaperRetrievallist.get(i).getPRConditionID()%>"
								<%if(MeetingPaperRetrievallist.get(i).getPRConditionID().equals(MeetingScorelist.get(uodate).getPRConditionID())){ %>selected="selected" <%} %>>
								<%=MeetingPaperRetrievallist.get(i).getPRCondition() %>
								</option>
							<%} %>
						</select>
					</td>		
				</tr>
				<tr>
					<td>&nbsp;参加学术会议评分编号:&nbsp;</td>
					<td>
						<input type="text" name="JoinAcaMSID" value="<%=MeetingScorelist.get(uodate).getJoinAMScoreID() %>"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="JoinAcaMScore" value="<%=MeetingScorelist.get(uodate).getScore() %>"/></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='JoinAcademicMeetingScoreset!doupdateJoinAcaMScore';document.f.submit();"/></td> 
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
					<td>子模块编号:</td>
					<td><input type="text"  value="SM4"  name="SubModularID"  disabled="disabled"/></td>
				</tr>
				<tr>
					<td>&nbsp;参加学术会议类型:&nbsp;</td>
					<td>
						<select name="JoinAcaMTId">
							<%for(int i=0;i<meetingtypelist.size();i++){ %>
								<option value="<%=meetingtypelist.get(i).getMeetingTypeID()%>">
								<%=meetingtypelist.get(i).getMeetingTypeName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;会议论文检索条目:&nbsp;</td>
					<td>
						<select name="JoinAcaMPRID">
							<%for(int i=0;i<MeetingPaperRetrievallist.size();i++){ %>
								<option value="<%=MeetingPaperRetrievallist.get(i).getPRConditionID()%>">
								<%=MeetingPaperRetrievallist.get(i).getPRCondition() %>
								</option>
							<%} %>
						</select>
					</td>		
				</tr>
				<tr>
					<td>&nbsp;参加学术会议评分编号:&nbsp;</td>
					<td><input type="text" name="JoinAcaMSID"/></td>
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="JoinAcaMScore"/></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='JoinAcademicMeetingScoreset!doaddJoinAcademicMeetingScore';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>