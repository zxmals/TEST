
<%@page import="com.nuaa.ec.science.model.ScienResearchProjectType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<ScienResearchProjectType> scientypelist = (List)session.getAttribute("ScienType");
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
		<h1>ScienResearchProjectTypeSet</h1>
		
			<input type="button" value="添加" onclick="window.location.href='ScientificResearchProjectTypeset!add'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;科研项目类型编号&nbsp;</td>
					<td>&nbsp;科研项目类型名称&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(scientypelist!=null)
				if(scientypelist.size()!=0){
					for(int i=0;i<scientypelist.size();i++){
					%>
					<tr>
						<td><%=scientypelist.get(i).getProjectTypeID() %></td>
						<td><%=scientypelist.get(i).getProjectTpName() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='ScientificResearchProjectTypeset!updateScienType?i=<%=i %>&ScienTypeId=<%=scientypelist.get(i).getProjectTypeID() %>'"/>&nbsp;
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
					<td>&nbsp;科研项目类型编号:&nbsp;</td>
					<td><input type="text" name="SRPTypeId"/></td>
				</tr>
				<tr>
					<td>&nbsp;科研项目类型名称:&nbsp;</td>
					<td><input type="text" name="SRPTypeName"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='ScientificResearchProjectTypeset!doaddScienType';document.f.submit();"/></td> 
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
					<td>&nbsp;期刊类型编号:&nbsp;</td>
					<td><input type="text" name="SRPTypeId" value="<%=scientypelist.get(uodate).getProjectTypeID() %>"/></td>
				</tr>
				<tr>
					<td>&nbsp;期刊类型名称:&nbsp;</td>
					<td><input type="text" name="SRPTypeName" value="<%=scientypelist.get(uodate).getProjectTpName() %>"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='ScientificResearchProjectTypeset!doupdateScienType';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>