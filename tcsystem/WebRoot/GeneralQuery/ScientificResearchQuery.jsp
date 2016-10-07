<%@page import="com.nuaa.ec.science.model.WordsNumber"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<WordsNumber> wordnumberlist = (List)session.getAttribute("WordNumber");
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
		<h1>WordNumberSet</h1>
		
			<input type="button" value="添加" onclick="window.location.href='AcademicWorkWordNumberset!add'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;字数编号&nbsp;</td>
					<td>&nbsp;字数类别&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(wordnumberlist!=null)
				if(wordnumberlist.size()!=0){
					for(int i=0;i<wordnumberlist.size();i++){
					%>
					<tr>
						<td><%=wordnumberlist.get(i).getWordID() %></td>
						<td><%=wordnumberlist.get(i).getWordNumber() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='AcademicWorkWordNumberset!updateWordNumber?i=<%=i %>&WordId=<%=wordnumberlist.get(i).getWordID() %>'"/>&nbsp;
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
					<td>&nbsp;字数编号:&nbsp;</td>
					<td><input type="text" name="WordId"/></td>
				</tr>
				<tr>
					<td>&nbsp;字数类别:&nbsp;</td>
					<td><input type="text" name="WordNumber"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='AcademicWorkWordNumberset!doaddWordNumber';document.f.submit();"/></td> 
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
					<td>&nbsp;字数编号:&nbsp;</td>
					<td><input type="text" name="WordId" value="<%=wordnumberlist.get(uodate).getWordID() %>"/></td>
				</tr>
				<tr>
					<td>&nbsp;字数类别:&nbsp;</td>
					<td><input type="text" name="WordNumber" value="<%=wordnumberlist.get(uodate).getWordNumber() %>"/></td>	
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='AcademicWorkWordNumberset!doupdateWordNumber';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>