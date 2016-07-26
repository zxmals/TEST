
<%@page import="com.nuaa.ec.science.model.ScientificResearchRewardScore"%>
<%@page import="com.nuaa.ec.science.model.ScientificResearchReward"%>
<%@page import="com.nuaa.ec.science.model.RewardLevel"%>
<%@page import="com.nuaa.ec.science.model.RewardType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
    <%
    	String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    %>

<%
	List<ScientificResearchRewardScore> ScientificResearchRewardScorelist = (List)session.getAttribute("ScientificResearchRewardScore");
	List<RewardType>  RewardTypelist = (List)session.getAttribute("RewardType");
	List<RewardLevel>  RewardLevellist = (List)session.getAttribute("RewardLevel");
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
		<h1>ScientificSearchRewardScoreset</h1>
		
			<input type="button" value="添加" onclick="window.location.href='ProjectRewardScoreset!addProjectRewardScore'"/>
			<table border="1px" cellspacing="0px" style="border-collapse:collapse">
				<tr>
					<td>&nbsp;子模块&nbsp;</td>
					<td>&nbsp;科研奖励评分编号&nbsp;</td>
					<td>&nbsp;奖励类别&nbsp;</td>
					<td>&nbsp;奖励级别&nbsp;</td>
					<td>&nbsp;基准分数&nbsp;</td>
					<td>&nbsp;操作&nbsp;</td>
				</tr>
				<%
			if(ScientificResearchRewardScorelist!=null)
				if(ScientificResearchRewardScorelist.size()!=0){
					for(int i=0;i<ScientificResearchRewardScorelist.size();i++){
					%>
					<tr>
						<td><%=ScientificResearchRewardScorelist.get(i).getSubModular()%></td>
						<td><%=ScientificResearchRewardScorelist.get(i).getSRRScoreID()%></td>
						<td><%=ScientificResearchRewardScorelist.get(i).getRewardType() %></td>
						<td><%=ScientificResearchRewardScorelist.get(i).getRewardLevel() %></td>
						<td><%=ScientificResearchRewardScorelist.get(i).getScore() %></td>
						<td> 
							<input type="submit" value="修改" onclick="window.location.href='ProjectRewardScoreset!updateSRRScore?i=<%=i %>&SRRScoreId=<%=ScientificResearchRewardScorelist.get(i).getSRRScoreID()%>'"/>&nbsp;
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
					<td><input type="text"  value="<%=ScientificResearchRewardScorelist.get(uodate).getSubModularID() %>"  name="SubModularID"  readonly="readonly"/></td>
				</tr>
				<tr>
					<td>&nbsp;奖励类别:&nbsp;</td>
					<td>
						<select name="RewardTypeId">
							<%for(int i=0;i<RewardTypelist.size();i++){ %>
								<option value="<%=RewardTypelist.get(i).getRewardTypeID()%>" 
								<%if(RewardTypelist.get(i).getRewardTypeID().equals(ScientificResearchRewardScorelist.get(uodate).getRewardTypeID())){ %>selected="selected" <%} %>>
								<%=RewardTypelist.get(i).getRewardTypeName()%>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;奖励级别:&nbsp;</td>
					<td>
						<select name="RewardLevelId">
							<%for(int i=0;i<RewardLevellist.size();i++){ %>
								<option value="<%=RewardLevellist.get(i).getRewardLevelID()%>" 
								<%if(RewardLevellist.get(i).getRewardLevelID().equals(ScientificResearchRewardScorelist.get(uodate).getRewardLevelID())){ %>selected="selected" <%} %>>
								<%=RewardLevellist.get(i).getRewardLevelName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
					<td>&nbsp;科研奖励评分编号:&nbsp;</td>
					<td>
						<input type="text" name="SRRScoreId" value="<%=ScientificResearchRewardScorelist.get(uodate).getSRRScoreID() %>"/>
					</td>
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="SRRScore" value="<%=ScientificResearchRewardScorelist.get(uodate).getScore() %>"/></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='ProjectRewardScoreset!doupdateSRRScore';document.f.submit();"/></td> 
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
					<td>&nbsp;奖励类别:&nbsp;</td>
					<td>
						<select name="RewardTypeId">
							<%for(int i=0;i<RewardTypelist.size();i++){ %>
								<option value="<%=RewardTypelist.get(i).getRewardTypeID()%>" >
								<%=RewardTypelist.get(i).getRewardTypeName()%>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;奖励级别:&nbsp;</td>
					<td>
						<select name="RewardLevelId">
							<%for(int i=0;i<RewardLevellist.size();i++){ %>
								<option value="<%=RewardLevellist.get(i).getRewardLevelID()%>" >
								<%=RewardLevellist.get(i).getRewardLevelName() %>
								</option>
							<%} %>
						</select>
					</td>
				</tr>
					<td>&nbsp;科研奖励评分编号:&nbsp;</td>
					<td>
						<input type="text" name="SRRScoreId" />
					</td>
				</tr>
				<tr>
					<td>&nbsp;基准分数:&nbsp;</td>
					<td><input type="text" name="SRRScore"/></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;&nbsp;操作</td>
					<td>&nbsp;&nbsp;&nbsp;
					<input type="submit" value="提交" onclick="javascript:document.f.action='ProjectRewardScoreset!doaddSRRScore';document.f.submit();"/></td> 
				</tr>
			</table>
			</form>
			<%} %>
	</center>
	<input type="submit" value="back" onclick="window.location.href='back'"/>
	<s:debug></s:debug>
</body>
</html>