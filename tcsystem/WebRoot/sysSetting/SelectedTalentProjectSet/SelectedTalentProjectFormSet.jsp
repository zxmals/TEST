
<%@page import="com.nuaa.ec.science.model.TalentProject"%>
<%@page import="com.nuaa.ec.science.model.ScienResearchProjectType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<TalentProject> talentprolist = (List)session.getAttribute("TalentProjectForm");
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
		<h1>TalentProjecSet</h1>
		
			<input type="button" value="添加" onclick="window.location.href='SelectedTalentProjectFormset!add'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;入选人才工程编号&nbsp;</td>
					<td>&nbsp;入选人才工程名称&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(talentprolist!=null)
				if(talentprolist.size()!=0){
					for(int i=0;i<talentprolist.size();i++){
					%>
					<tr>
						<td><%=talentprolist.get(i).getTalentProjectID() %></td>
						<td><%=talentprolist.get(i).getTalentProjectName() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='SelectedTalentProjectFormset!updateSelectedTalentPro?i=<%=i %>&STProId=<%=talentprolist.get(i).getTalentProjectID() %>'"/>&nbsp;
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
					<td>&nbsp;入选人才工程编号:&nbsp;</td>
					<td><input type="text" name="STProId"/></td>
				</tr>
				<tr>
					<td>&nbsp;入选人才工程名称:&nbsp;</td>
					<td><input type="text" name="STProName"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='SelectedTalentProjectFormset!doaddSelectedTalentPro';document.f.submit();"/></td> 
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
					<td>&nbsp;入选人才工程编号:&nbsp;</td>
					<td><input type="text" name="STProId" value="<%=talentprolist.get(uodate).getTalentProjectID() %>"/></td>
				</tr>
				<tr>
					<td>&nbsp;期刊类型名称:&nbsp;</td>
					<td><input type="text" name="STProName" value="<%=talentprolist.get(uodate).getTalentProjectName() %>"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='SelectedTalentProjectFormset!doupdateSelectedTalentPro';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>