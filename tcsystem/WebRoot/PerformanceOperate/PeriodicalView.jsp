<%@page import="com.nuaa.ec.science.model.Teacher"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@page import="com.nuaa.ec.science.model.Periodical"%>
    
 <%@page import="com.nuaa.ec.science.Permodel.PeriodicalManaView"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	List<PeriodicalManaView> periodical = new ArrayList<PeriodicalManaView>();
	periodical = (List)session.getAttribute("periodicalview");
	PeriodicalManaView periodicals = new PeriodicalManaView();
	periodicals = (PeriodicalManaView)request.getAttribute("periodical");
	List<Periodical> periodicalnamelist = (List)session.getAttribute("periodicalname");
	String add = (String)request.getAttribute("add");
	List<Teacher> teachlist = (List)request.getAttribute("Teacher");
%>
    <%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>Insert title here</title>
</head>
<body>
<center>
	<input type="submit" value="添加" onclick="window.location.href='add_Periodical!addPeriodicalPaper'"/>
	<table border="1px" cellspacing="0px" style="border-collapse:collapse">
		<tr>
			<td>TeacherID</td>
			<td>TeacherName</td>
			<td>期刊类型</td>
			<td>期刊名称</td>
			<td>第一作者</td>
			<td>第二作者</td>
			<td>论文编号</td>
			<td>论文题目</td>
			<td>论文概述</td>
			<td>年</td>
			<td>卷</td>
			<td>期</td>
			<td>分数/绩效</td>
			<td>操作</td>	
		</tr>
		<%for(int i=0;i<periodical.size();i++){ %>
			<tr>
				<td><%=periodical.get(i).getTeacherID() %></td>
				<td><%=periodical.get(i).getTeacherName() %></td>
				<td><%=periodical.get(i).getPeriodicalType() %></td>
				<td><%=periodical.get(i).getPeriodicalName() %></td>
				<td><%=periodical.get(i).getFirstAuthor() %></td>
				<td><%=periodical.get(i).getSceonAuthor() %></td>
				<td><%=periodical.get(i).getPPId() %></td>
				<td><%=periodical.get(i).getPaperTitle() %></td>
				<td><%=periodical.get(i).getPaperDescribe() %></td> 
				<td><%=periodical.get(i).getYear() %></td>
				<td><%=periodical.get(i).getFile() %></td>
				<td><%=periodical.get(i).getPhrase() %></td>
				<td><%=periodical.get(i).getScore() %></td>
				<td>
					<input type="submit" value="修改" onclick="window.location.href='update_Periodical!updatePeriodical?TeacherID=<%=periodical.get(i).getTeacherID()%>&PeriodicalName=<%=periodical.get(i).getPeriodicalName() %>&PPId=<%=periodical.get(i).getPPId() %>'"/>&nbsp;
					<input type="submit" value="删除" onclick=""/>
					
				</td>
			</tr>
		<%} %>
	</table>
	<%if(periodicals!=null){ %>
	<form action="" method="post" name="f">
		<table border="1px" cellspacing="0px" style="border-collapse:collapse;margin-top: 150px">
			<tr><td>TeacherID</td><td><%=periodicals.getTeacherID() %></td></tr>
			<tr><td>TeacherName</td><td><%=periodicals.getTeacherName() %></td></tr>
			<tr><td>期刊名称</td><td>
			<select name="PeriodicalName">
					<%for(int i=0;i<periodicalnamelist.size();i++){ %>
  <option value="<%=periodicalnamelist.get(i).getPeriodicalID() %>" 
  <% if(periodicalnamelist.get(i).getPeriodicalName().equals(periodicals.getPeriodicalName())){ %> selected="selected" <%} %>>
  
  <%=periodicalnamelist.get(i).getPeriodicalName()%></option>
  			<%} %>
				</select>
			</td></tr>
			<tr><td>第一作者</td><td><input type="text" name="FirstAuthor" value="<%=periodicals.getFirstAuthor() %>"/></td></tr>
			<tr><td>第二作者</td><td><input type="text" name="SceonAuthor" value="<%=periodicals.getSceonAuthor() %>"/></td></tr>
			<tr><td>论文编号</td><td><input type="text" name="PPId" value="<%=periodicals.getPPId() %>"/></td></tr>
			<tr><td>论文题目</td><td><input type="text" name="PaperTitle" value="<%=periodicals.getPaperTitle() %>"/></td></tr>
			<tr><td>论文概述</td><td><textarea  name="PaperDescribe" rows="7" cols="40"><%=periodicals.getPaperDescribe() %></textarea></td></tr>
			<tr><td>年</td><td><input type="text" name="Year" value="<%=periodicals.getYear() %>"/></td></tr>
			<tr><td>卷</td><td><input type="text" name="File" value="<%=periodicals.getFile() %>"/></td></tr>
			<tr><td>期</td><td><input type="text" name="Phrase" value="<%=periodicals.getPhrase() %>"/></td></tr>
			<tr><td>分数/绩效</td><td><input type="text" name="Score" value="<%=periodicals.getScore() %>"/></td></tr>
			<tr>
				<td>操作</td>
				<td><input type="submit" value="提交"
						   onclick="document.f.action='PeriodicalView!doupdatePeriodicalPaper?TeacherID=<%=periodicals.getTeacherID() %>';document.f.submit();"></input>
				</td>
			</tr>
	</table>
	</form>
	<%} %>
	
	<%
		if(add!=null)
		if(add.equals("1")){ %>
		<form action="" method="post" name="f">
		<table border="1px" cellspacing="0px" style="border-collapse:collapse;margin-top: 150px">
			<tr><td>TeacherID</td>
			<td>
				<select name="TeacherID">
					<%for(int i=0;i<teachlist.size();i++){ %>
						<option><%=teachlist.get(i).getTeacherID() %></option>
					<%} %>
				</select>
			</td>
			</tr>
			<tr><td>期刊名称</td><td>
			<select name="PeriodicalName">
					<%for(int i=0;i<periodicalnamelist.size();i++){ %>
  <option value="<%=periodicalnamelist.get(i).getPeriodicalID() %>"><%=periodicalnamelist.get(i).getPeriodicalName()%></option>
  <%} %>
				</select>
			</td></tr>
			<tr><td>期刊论文编号</td><td><input type="text" name="PPId"/></td></tr>
			<tr><td>论文题目</td><td><input type="text" name="PaperTitle"/></td></tr>
			<tr><td>论文概述</td><td><textarea  name="PaperDescribe" rows="7" cols="40"></textarea></td></tr>
			<tr><td>第一作者</td><td><input type="text" name="FirstAuthor" /></td></tr>
			<tr><td>第二作者</td><td><input type="text" name="SceonAuthor" /></td></tr>
			<tr><td>年</td><td><input type="text" name="Year" /></td></tr>
			<tr><td>卷</td><td><input type="text" name="File" /></td></tr>
			<tr><td>期</td><td><input type="text" name="Phrase" /></td></tr>
			<tr>
				<td>操作</td>
				<td><input type="submit" value="提交"
						   onclick="document.f.action='PeriodicalView!doaddPeriodicalPaper';document.f.submit();"></input>
				</td>
			</tr>
	</table>
	</form>
	<%} %>
	</center>
	<input type="submit" value="注销" onclick="window.location.href='<%=basePath %>logout!out'"/>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>