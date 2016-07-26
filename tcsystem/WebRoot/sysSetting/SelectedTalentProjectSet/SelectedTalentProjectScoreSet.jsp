
<%@page import="com.nuaa.ec.science.model.TalentProject"%>
<%@page import="com.nuaa.ec.science.model.SelectedTalentProjectScore"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<TalentProject> talentProjectlist = (List)session.getAttribute("TalentProjectForm");
	List<SelectedTalentProjectScore> STPScorelist = (List)session.getAttribute("SelectedTPScore");
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
		<h1>SelectedTalentProjectScoreSet</h1>
		
			<input type="button" value="添加" onclick="window.location.href='SelectedTalentProjectScoreset!add'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;入选人才工程编号&nbsp;</td>
					<td>&nbsp;入选人才工程评分编号&nbsp;</td>
					<td>&nbsp;基准分数&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(STPScorelist!=null)
				if(STPScorelist.size()!=0){
					for(int i=0;i<STPScorelist.size();i++){
					%>
					<tr>
						<td><%=STPScorelist.get(i).getTalentProjectID() %></td>
						<td><%=STPScorelist.get(i).getSTPScoreID() %></td>
						<td><%=STPScorelist.get(i).getScore() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='SelectedTalentProjectScoreset!updateSTPScore?i=<%=i %>&STPScoreId=<%=STPScorelist.get(i).getSTPScoreID() %>'"/>&nbsp;
							<input type="submit" value="删除" onclick=""/>
						</td>
					</tr>
					<%}} %>
			</table>
			
			<%
			if(add!=null)	
				if(add.equals("1")) {%>
			<form name="f" action="" method="post">
				<table border="1px" cellspacing="0px" style="border-collapse:collapse;margin-top: 120px">
				<tr>
					<td>&nbsp;入选人才工程:&nbsp;</td>
					<td>
						<select name="STProId">
							<%for(int i=0;i<talentProjectlist.size();i++){ %>
								<option value="<%=talentProjectlist.get(i).getTalentProjectID() %>">
								<%=talentProjectlist.get(i).getTalentProjectName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;入选人才工程评分编号:&nbsp;</td>
					<td><input type="text" name="STPScoreID"/></td>	
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="STPScore"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='SelectedTalentProjectScoreset!doaddSTalentPScore';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
			<%
			if(update!=null)	
				if(update.equals("1")) {%>
			<form name="f" action="" method="post">
				<table border="1px" cellspacing="0px" style="border-collapse:collapse;margin-top: 120px">
				<tr>
					<td>&nbsp;入选人才工程:&nbsp;</td>
					<td>
						<select name="STProId">
							<%for(int i=0;i<talentProjectlist.size();i++){ %>
								<option value="<%=talentProjectlist.get(i).getTalentProjectID() %>"
								<%if(talentProjectlist.get(i).getTalentProjectID().equals(STPScorelist.get(uodate).getTalentProjectID())){ %>selected="selected"<%} %>>
								<%=talentProjectlist.get(i).getTalentProjectName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;入选人才工程评分编号:&nbsp;</td>
					<td><input type="text" name="STPScoreID" value="<%=STPScorelist.get(uodate).getSTPScoreID() %>"/></td>	
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="STPScore" value="<%=STPScorelist.get(uodate).getScore() %>"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='SelectedTalentProjectScoreset!doupdateSTalentPScore';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>