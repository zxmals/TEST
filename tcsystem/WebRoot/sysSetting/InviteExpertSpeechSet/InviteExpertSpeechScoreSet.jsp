<%@page import="com.nuaa.ec.science.model.ExpertType"%>
<%@page import="com.nuaa.ec.science.model.InvitedExpertsSpeechScore"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<InvitedExpertsSpeechScore>   invitexpertscorelist = (List)session.getAttribute("InvitedExpertsSpeechScore");
	List<ExpertType>  ExpertTypelist = (List)session.getAttribute("ExpertType");
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
		<h1>InviteExpertSpeechScoreset</h1>
		
			<input type="button" value="添加" onclick="window.location.href='InviteExpertSpeechScoreset!addInviteExpertSpeechScore'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;子模块&nbsp;</td>
					<td>&nbsp;专家类别&nbsp;</td>
					<td>&nbsp;邀请专家讲学评分编号&nbsp;</td>
					<td>&nbsp;基准分数&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(invitexpertscorelist!=null)
				if(invitexpertscorelist.size()!=0){
					for(int i=0;i<invitexpertscorelist.size();i++){
					%>
					<tr>
						<td><%=invitexpertscorelist.get(i).getSubModular()%></td>
						<td><%=invitexpertscorelist.get(i).getExpertType()%></td>
						<td><%=invitexpertscorelist.get(i).getIESScoreID()%></td>
						<td><%=invitexpertscorelist.get(i).getScore()%></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='InviteExpertSpeechScoreset!updateInviteExpertScore?i=<%=i %>&InviteExpertSpeechScoreId=<%=invitexpertscorelist.get(i).getIESScoreID()%>'"/>&nbsp;
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
					<td>子模块:</td>
					<td><input type="text"  value="<%=invitexpertscorelist.get(uodate).getSubModularID()  %>"  name="SubModularID"  readonly="readonly"/></td>
				</tr>
				<tr>
					<td>&nbsp;专家类别:&nbsp;</td>
					<td>
						<select name="ExpertTypeId">
							<%for(int i=0;i<ExpertTypelist.size();i++){ %>
								<option value="<%=ExpertTypelist.get(i).getExpertTypeID()%>" 
								<%if(ExpertTypelist.get(i).getExpertTypeID().equals(invitexpertscorelist.get(uodate).getExpertTypeID())){ %>selected="selected" <%} %>>
								<%=ExpertTypelist.get(i).getExpertTypeName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;邀请专家讲学评分编号:&nbsp;</td>
					<td>
						<input type="text" name="InviteExpertSpeechScoreId" value="<%=invitexpertscorelist.get(uodate).getIESScoreID() %>"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="InviteExpertSpeechScore" value="<%=invitexpertscorelist.get(uodate).getScore() %>"/></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='InviteExpertSpeechScoreset!doupdateInviteScore';document.f.submit();"/></td> 
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
					<td>&nbsp;专家类别:&nbsp;</td>
					<td>
						<select name="ExpertTypeId">
							<%for(int i=0;i<ExpertTypelist.size();i++){ %>
								<option value="<%=ExpertTypelist.get(i).getExpertTypeID()%>" >
								<%=ExpertTypelist.get(i).getExpertTypeName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;邀请专家讲学评分编号:&nbsp;</td>
					<td>
						<input type="text" name="InviteExpertSpeechScoreId"  />
					</td>
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="InviteExpertSpeechScore" /></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<!-- ?SubModularID=<%=subModular %> -->
					<input type="submit" value="提交" onclick="javascript:document.f.action='InviteExpertSpeechScoreset!doaddInviteExpertScore';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>