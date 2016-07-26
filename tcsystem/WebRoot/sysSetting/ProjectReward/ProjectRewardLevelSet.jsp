
<%@page import="com.nuaa.ec.science.model.RewardLevel"%>
<%@page import="com.nuaa.ec.science.model.RewardLevel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<RewardLevel>  RewardLevellist = (List)session.getAttribute("RewardLevel");
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
		<h1>RewardLevelSet</h1>
		
			<input type="button" value="添加" onclick="window.location.href='ProjectRewardLevelset!add'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;科研奖励级别编号&nbsp;</td>
					<td>&nbsp;科研级别名称&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(RewardLevellist!=null)
				if(RewardLevellist.size()!=0){
					for(int i=0;i<RewardLevellist.size();i++){
					%>
					<tr>
						<td><%=RewardLevellist.get(i).getRewardLevelID() %></td>
						<td><%=RewardLevellist.get(i).getRewardLevelName() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='ProjectRewardLevelset!updateRewardLevel?i=<%=i %>&RewardLevelId=<%=RewardLevellist.get(i).getRewardLevelID() %>'"/>&nbsp;
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
					<td>&nbsp;科研奖励级别编号:&nbsp;</td>
					<td><input type="text" name="RewardLevelId"/></td>
				</tr>
				<tr>
					<td>&nbsp;科研奖励级别名称:&nbsp;</td>
					<td><input type="text" name="RewardLevel"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='ProjectRewardLevelset!doaddRewardLevel';document.f.submit();"/></td> 
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
					<td>&nbsp;出版社级别编号:&nbsp;</td>
					<td><input type="text" name="RewardLevelId" value="<%=RewardLevellist.get(uodate).getRewardLevelID()%>"/></td>
				</tr>
				<tr>
					<td>&nbsp;出版社级别:&nbsp;</td>
					<td><input type="text" name="RewardLevel" value="<%=RewardLevellist.get(uodate).getRewardLevelName()%>"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='ProjectRewardLevelset!doupdateRewardLevel';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>