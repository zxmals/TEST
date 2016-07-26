<?xml version="1.0" encoding="GB18030" ?>
<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
    <%@taglib uri="/struts-tags" prefix="s" %>
    <%
     String path = request.getContextPath();
     String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
     %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030" />
<title>Insert title here</title>
</head>
<body>
  <td>
    <a href="<%=basePath %>PersonManager/view_person">个人信息查询</a>
  </td>
  <br/>
  <br/>
  <td>
    <a href="<%=basePath %>PersonManager/viewPerformance">个人绩效查询</a>
  </td>
  <br/>
  <br/>
  <td>login succed</td>
	<s:debug></s:debug>
</body>
</html>