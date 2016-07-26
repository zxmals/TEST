
<%@page import="com.nuaa.ec.science.model.ScientificResearchProjectScore"%>
<%@page import="com.nuaa.ec.science.model.ScienResearchProjectType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<ScienResearchProjectType> scientypelist = (List)session.getAttribute("ScienType");
	List<ScientificResearchProjectScore> scienscorelist = (List)session.getAttribute("ScienScore");
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
		
			<input type="button" value="添加" onclick="window.location.href='ScientificResearchProjectScoreset!add'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;子模块编号&nbsp;</td>
					<td>&nbsp;科研项目类型编号&nbsp;</td>
					<td>&nbsp;科研项目评分编号&nbsp;</td>
					<td>&nbsp;基础分数&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(scienscorelist!=null)
				if(scienscorelist.size()!=0){
					for(int i=0;i<scienscorelist.size();i++){
					%>
					<tr>
						<td><%=scienscorelist.get(i).getSubModularID() %></td>
						<td><%=scienscorelist.get(i).getProjectTypeID() %></td>
						<td><%=scienscorelist.get(i).getSRProjectScoreID() %></td>
						<td><%=scienscorelist.get(i).getScore() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='ScientificResearchProjectScoreset!updateScienScore?i=<%=i %>&ScienScoreId=<%=scienscorelist.get(i).getSRProjectScoreID() %>'"/>&nbsp;
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
					<td>&nbsp;子模块:&nbsp;</td>
					<td><input type="text" name="SRPSSubModularID" value="科研项目" disabled="disabled"/></td>
				</tr>
				<tr>
					<td>&nbsp;科研项目类型:&nbsp;</td>
					<td>
						<select name="SRPTypeId">
							<%for(int i=0;i<scientypelist.size();i++){ %>
								<option value="<%=scientypelist.get(i).getProjectTypeID() %>">
								<%=scientypelist.get(i).getProjectTpName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;科研项目评分编号:&nbsp;</td>
					<td><input type="text" name="SRPScoreID"/></td>	
				</tr>
				<tr>
					<td>&nbsp;分数:&nbsp;</td>
					<td><input type="text" name="SRPScore"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='ScientificResearchProjectScoreset!doaddScienScore?SRPSSubModularID=SM5';document.f.submit();"/></td> 
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
					<td>&nbsp;子模块编号:&nbsp;</td>
					<td><input type="text" name="SRPSSubModularID" value="<%=scienscorelist.get(0).getSubModularID() %>" disabled="disabled"/></td>
				</tr>
				<tr>
					<td>&nbsp;科研项目类型编号:&nbsp;</td>
					<td>
						<select name="SRPTypeId">
							<%for(int i=0;i<scientypelist.size();i++){ %>
								<option value="<%=scientypelist.get(i).getProjectTypeID() %>" 
								<%if(scientypelist.get(i).getProjectTypeID().equals(scienscorelist.get(uodate).getProjectTypeID())) {%> 
								selected="selected"<%} %>>
								<%=scientypelist.get(i).getProjectTpName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;科研项目评分编号:&nbsp;</td>
					<td><input type="text" name="SRPScoreID" value="<%=scienscorelist.get(uodate).getSRProjectScoreID() %>"/></td>	
				</tr>
				<tr>
					<td>&nbsp;分数:&nbsp;</td>
					<td><input type="text" name="SRPScore" value="<%=scienscorelist.get(uodate).getScore() %>"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='ScientificResearchProjectScoreset!doupdateScienScore?SRPSSubModularID=<%=scienscorelist.get(0).getSubModularID() %>';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>