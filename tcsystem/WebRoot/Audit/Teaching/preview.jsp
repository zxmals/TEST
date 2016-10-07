<?xml version="1.0" encoding="utf-8" ?>
<%@page import="com.nuaa.ec.teaching.model.TeachingExportDataType"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="com.nuaa.ec.science.Permodel.TeacherLoginStatus"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
TeacherLoginStatus a = (TeacherLoginStatus)session.getAttribute("teacherloginStatus");
List<TeachingExportDataType>  teached = (List)session.getAttribute("expor");
String oint = (String)request.getParameter("oints");
int o = 0;
if(oint!=null)
	o = Integer.parseInt(oint);
%>
<jsp:directive.page import="javax.servlet.http.HttpSession" />
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<base target="_self">
<script type="text/javascript">
  		function relocal() {
			var AuditpartID = "<%=request.getParameter("AuditpartID")%>
	";
		document.getElementById(AuditpartID + 'l').style.display = "block";
	}
</script>
<script src="js/TeachAuditCheckbox.js"></script>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<meta name="description" content="">
<meta name="author" content="">
<title>管理员界面</title>

<!-- Bootstrap core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
<link href="css/dashboard.css" rel="stylesheet">

	<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
	<!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="js/ie-emulation-modes-warning.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="http://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<!-- col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main -->
<body onload="relocal()">
	`
	<div class="col-sm-9 main">
		<h1 class="page-header">教学审核</h1>
		<form name="f" method="post">
			<table
				class="table table-striped table-bordered table-hover dataTables-example">
				<tr>
					<td>教学绩效模块:</td>
					<td><select name="" id="modualSelect"
						onchange="modualchange()">
							<option>&nbsp;</option>
							<option value="0">教学能力与实效</option>
							<option value="1">综合改革与教学研究</option>
							<option value="2">学生指导工作</option>
					</select></td>
					<td>系:</td>
					<td><select name="" id="departSelect" onchange="">
							<%
								if (teached != null)
									for (int i = 0; i < teached.size(); i++) {
							%>
							<option value="<%=i%>" <%if (i == o) {%> selected="selected"
								<%}%>>
								<%=teached.get(i).getDepartName()%></option>
							<%
								}
							%>
					</select></td>
				</tr>
			</table>
		</form>
		<hr />
		<br />
		<div id="case0" style="display:none">
			<a onclick="choosepreAudit('zxma1')" id="zxma1" href="#">课堂教学绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma2')" id="zxma2" href="#">学位论文指导质量绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma3')" id="zxma3" href="#">教学竞赛绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma4')" id="zxma4" href="#">教学能力提升绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma5')" id="zxma5" href="#">教学名师和教学团队绩效审核</a><br />
			<br />
		</div>
		<div id="case1" style="display:none">
			<a onclick="choosepreAudit('zxma6')" id="zxma6" href="#">教学研究绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma7')" id="zxma7" href="#">教学论文绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma8')" id="zxma8" href="#">教学成果奖绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma9')" id="zxma9" href="#">教材建设绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma10')" id="zxma10" href="#">精品课程建设绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma11')" id="zxma11" href="#">专业建设项目申报绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma12')" id="zxma12" href="#">企业工作站和联合培养基地建设绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma13')" id="zxma13" href="#">暑期课程与国际课程建设绩效审核</a><br />
			<br />
		</div>
		<div id="case2" style="display:none">
			<a onclick="choosepreAudit('zxma14')" id="zxma14" href="#">实践创新指导绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma15')" id="zxma15" href="#">学生竞赛指导绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma16')" id="zxma16" href="#">参与学生活动绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma17')" id="zxma17" href="#">本科生导师指导绩效审核</a><br />
			<br /> <a onclick="choosepreAudit('zxma18')" id="zxma18" href="#">校外实践指导绩效审核</a><br />
			<br />
		</div>
		<div style="display:none" id="zxma1l">
			<a href="#">课堂教学绩效</a><br />
			<br />
			<div class="ibox-title">
				<h5>
					系 <small></small>
				</h5>
				<div class="ibox-tools">
					<a class="collapse-link"> <i class="fa fa-chevron-up"></i>
					</a> <a class="dropdown-toggle" data-toggle="dropdown"
						href="table_data_tables.html#"> <i class="fa fa-wrench"></i>
					</a>
					<ul class="dropdown-menu dropdown-user">
						<li><a href="table_data_tables.html#">选项1</a></li>
						<li><a href="table_data_tables.html#">选项2</a></li>
					</ul>
					<a class="close-link"> <i class="fa fa-times"></i>
					</a>
				</div>
			</div>
			<form name="" id="Audit" action="" method="post">
				<table id="tb"
					class="table table-striped table-bordered table-hover dataTables-example">
					<tr>
						<td>课堂教学绩效编号</td>
						<td>总课时</td>
						<td>综合教学评估结果</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck"
							<%if (teached.get(o).getClassteach() != null) {%>
							onchange="allcheckchange('zxma1l','<%=teached.get(o).getClassteach().size()%>','allCheck')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getClassteach() != null)
								for (int i = 0; i < teached.get(o).getClassteach().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getClassteach().get(i)
								.getClassPefromanceID()%></td>
						<td><%=teached.get(o).getClassteach().get(i)
								.getSumtime()%></td>
						<td><%=teached.get(o).getClassteach().get(i)
								.getEvaluResult()%></td>
						<td><%=teached.get(o).getClassteach().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o).getClassteach().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getClassteach().get(i)
								.getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma1l<%=i%>"
							value="<%=teached.get(o).getClassteach().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o).getClassteach().get(i)
										.getClassPefromanceID()%>"
							onchange="nullChange('zxma1l<%=i%>','allCheck')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getClassteach() != null) {%>
				onclick="Submitdata('zxma1l','<%=teached.get(o).getClassteach().size()%>','Audit')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma2l">
			<a href="#">学位论文指导质量</a><br />
			<br />
			<form name="" id="Audit0" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>学位论文编号</td>
						<td>学位论文标题</td>
						<td>获奖级别</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck0"
							<%if (teached.get(o).getDegreeePaperGuide() != null) {%>
							onclick="allcheckchange('zxma2l','<%=teached.get(o).getDegreeePaperGuide().size()%>','allCheck0')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getDegreeePaperGuide() != null)
								for (int i = 0; i < teached.get(o).getDegreeePaperGuide()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getDegreeePaperGuide().get(i)
								.getDegree_thesisID()%></td>
						<td><%=teached.get(o).getDegreeePaperGuide().get(i)
								.getDegree_thesisnName()%></td>
						<td><%=teached.get(o).getDegreeePaperGuide().get(i)
								.getRewardLevel()%></td>
						<td><%=teached.get(o).getDegreeePaperGuide().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o).getDegreeePaperGuide().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getDegreeePaperGuide().get(i)
								.getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma2l<%=i%>"
							value="<%=teached.get(o).getDegreeePaperGuide().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o).getDegreeePaperGuide().get(i)
										.getDegree_thesisID()%>"
							onchange="nullChange('zxma2l<%=i%>','allCheck0')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getDegreeePaperGuide() != null) {%>
				onclick="Submitdata('zxma2l','<%=teached.get(o).getDegreeePaperGuide().size()%>','Audit0')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma3l">
			<a href="#">教学竞赛绩效</a><br />
			<br />
			<form name="" id="Audit1" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>教学竞赛编号</td>
						<td>竞赛名称</td>
						<td>竞赛获奖等级</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck1"
							<%if (teached.get(o).getTeachingCompetition() != null) {%>
							onclick="allcheckchange('zxma3l','<%=teached.get(o).getTeachingCompetition().size()%>','allCheck1')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getTeachingCompetition() != null)
								for (int i = 0; i < teached.get(o).getTeachingCompetition()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getTeachingCompetition()
								.get(i).getCompetitionID()%></td>
						<td><%=teached.get(o).getTeachingCompetition()
								.get(i).getCompetitionName()%></td>
						<td><%=teached.get(o).getTeachingCompetition()
								.get(i).getCompetRewardLevel()%></td>
						<td><%=teached.get(o).getTeachingCompetition()
								.get(i).getTeacherID()%></td>
						<td><%=teached.get(o).getTeachingCompetition()
								.get(i).getTeacherName()%></td>
						<td><%=teached.get(o).getTeachingCompetition()
								.get(i).getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma3l<%=i%>"
							value="<%=teached.get(o).getTeachingCompetition()
								.get(i).getTeacherID()
								+ ","
								+ teached.get(o).getTeachingCompetition()
										.get(i).getCompetitionID()%>"
							onchange="nullChange('zxma3l<%=i%>','allCheck1')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getTeachingCompetition() != null) {%>
				onclick="Submitdata('zxma3l','<%=teached.get(o).getTeachingCompetition().size()%>','Audit1')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma4l">
			<a href="#">教学能力提升绩效</a><br />
			<br />
			<form name="" id="Audit2" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>教学能力提升项目编号</td>
						<td>教学能力提升项目名称</td>
						<td>参与总时间 单位/h</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck2"
							<%if (teached.get(o).getTeachingAbilityImprove() != null) {%>
							onclick="allcheckchange('zxma4l','<%=teached.get(o).getTeachingAbilityImprove().size()%>','allCheck2')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getTeachingAbilityImprove() != null)
								for (int i = 0; i < teached.get(o)
										.getTeachingAbilityImprove().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getTeachingAbilityImprove()
								.get(i).getEventID()%></td>
						<td><%=teached.get(o).getTeachingAbilityImprove()
								.get(i).getEventName()%></td>
						<td><%=teached.get(o).getTeachingAbilityImprove()
								.get(i).getSumhours()%></td>
						<td><%=teached.get(o).getTeachingAbilityImprove()
								.get(i).getTeacherID()%></td>
						<td><%=teached.get(o).getTeachingAbilityImprove()
								.get(i).getTeacherName()%></td>
						<td><%=teached.get(o).getTeachingAbilityImprove()
								.get(i).getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma4l<%=i%>"
							value="<%=teached.get(o).getTeachingAbilityImprove()
								.get(i).getTeacherID()
								+ ","
								+ teached.get(o).getTeachingAbilityImprove()
										.get(i).getEventID()%>"
							onchange="nullChange('zxma4l<%=i%>','allCheck2')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getTeachingAbilityImprove() != null) {%>
				onclick="Submitdata('zxma4l','<%=teached.get(o).getTeachingAbilityImprove().size()%>','Audit2')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma5l">
			<a href="#">教学名师和教学团队绩效</a><br />
			<br />
			<form name="" id="Audit3" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>教学名师或教学团队编号</td>
						<td>获奖级别</td>
						<td>项目总分</td>
						<td>本人承担任务</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck3"
							<%if (teached.get(o).getFamousTeacherTeam() != null) {%>
							onclick="allcheckchange('zxma5l','<%=teached.get(o).getFamousTeacherTeam().size()%>','allCheck3')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getFamousTeacherTeam() != null)
								for (int i = 0; i < teached.get(o).getFamousTeacherTeam()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getFamousTeacherTeam().get(i)
								.getTeacherTeamPerformanceID()%></td>
						<td><%=teached.get(o).getFamousTeacherTeam().get(i)
								.getTeachRewardLevel()%></td>
						<td><%=teached.get(o).getFamousTeacherTeam().get(i)
								.getProjectSumScore()%></td>
						<td><%=teached.get(o).getFamousTeacherTeam().get(i)
								.getSelfUndertakeTask()%></td>
						<td><%=teached.get(o).getFamousTeacherTeam().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o).getFamousTeacherTeam().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getFamousTeacherTeam().get(i)
								.getSingelScore()%></td>
						<td><input type="checkbox" name="" id="zxma5l<%=i%>"
							value="<%=teached.get(o).getFamousTeacherTeam().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o).getFamousTeacherTeam().get(i)
										.getTeacherTeamPerformanceID()%>"
							onchange="nullChange('zxma5l<%=i%>','allCheck3')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getFamousTeacherTeam() != null) {%>
				onclick="Submitdata('zxma5l','<%=teached.get(o).getFamousTeacherTeam().size()%>','Audit3')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma6l">
			<a href="#">教学研究绩效</a><br />
			<br />
			<form name="" id="Audit4" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>教学研究项目编号</td>
						<td>教学研究项目</td>
						<td>当年项目到款等级</td>
						<td>项目评估等级</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck4"
							<%if (teached.get(o).getTeaching_research() != null) {%>
							onclick="allcheckchange('zxma6l','<%=teached.get(o).getTeaching_research().size()%>','allCheck4')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getTeaching_research() != null)
								for (int i = 0; i < teached.get(o).getTeaching_research()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getTeaching_research().get(i)
								.getProjectID()%></td>
						<td><%=teached.get(o).getTeaching_research().get(i)
								.getProject()%></td>
						<td><%=teached.get(o).getTeaching_research().get(i)
								.getFundLevel()%></td>
						<td><%=teached.get(o).getTeaching_research().get(i)
								.getEvaluationReaults()%></td>
						<td><%=teached.get(o).getTeaching_research().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o).getTeaching_research().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getTeaching_research().get(i)
								.getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma6l<%=i%>"
							value="<%=teached.get(o).getTeaching_research().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o).getTeaching_research().get(i)
										.getProjectID()%>"
							onchange="nullChange('zxma6l<%=i%>','allCheck4')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getTeaching_research() != null) {%>
				onclick="Submitdata('zxma6l','<%=teached.get(o).getTeaching_research().size()%>','Audit4')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma7l">
			<a href="#">教学论文绩效</a><br />
			<br />
			<form name="" id="Audit5" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>教学论文编号</td>
						<td>论文题目</td>
						<td>论文检索情况</td>
						<td>本人承担任务</td>
						<td>是否有其他作者参与</td>
						<td>教师担任作者</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>项目总分</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck5"
							<%if (teached.get(o).getTeachingPaper() != null) {%>
							onclick="allcheckchange('zxma7l','<%=teached.get(o).getTeachingPaper().size()%>','allCheck5')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getTeachingPaper() != null)
								for (int i = 0; i < teached.get(o).getTeachingPaper()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getTeachPaperID()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getTeachPaperName()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getThesisRetrieval()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getUndertkaeTask()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getOtherAuthorJoin()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getTeacherAffordAuthor()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getProjectSumScore()%></td>
						<td><%=teached.get(o).getTeachingPaper().get(i)
								.getSingelScore()%></td>
						<td><input type="checkbox" name="" id="zxma7l<%=i%>"
							value="<%=teached.get(o).getTeachingPaper().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o).getTeachingPaper().get(i)
										.getTeachPaperID()%>"
							onchange="nullChange('zxma7l<%=i%>','allCheck5')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getTeachingPaper() != null) {%>
				onclick="Submitdata('zxma7l','<%=teached.get(o).getTeachingPaper().size()%>','Audit5')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma8l">
			<a href="#">教学成果奖绩效</a><br />
			<br />
			<form name="" id="Audit6" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>教学成果获奖编号</td>
						<td>教学成果奖名称</td>
						<td>获奖级别</td>
						<td>本人承担任务</td>
						<td>是否有其他参与</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>项目总分</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck6"
							<%if (teached.get(o).getTeaching_achievement() != null) {%>
							onclick="allcheckchange('zxma8l','<%=teached.get(o).getTeaching_achievement().size()%>','allCheck6')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getTeaching_achievement() != null)
								for (int i = 0; i < teached.get(o)
										.getTeaching_achievement().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getProjectID()%></td>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getProjectName()%></td>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getRewardName()%></td>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getUndertakeTask()%></td>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getCooperator()%></td>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getTeacherID()%></td>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getTeacherName()%></td>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getProjectSumScore()%></td>
						<td><%=teached.get(o).getTeaching_achievement()
								.get(i).getSingelScore()%></td>
						<td><input type="checkbox" name="" id="zxma8l<%=i%>"
							value="<%=teached.get(o).getTeaching_achievement()
								.get(i).getTeacherID()
								+ ","
								+ teached.get(o).getTeaching_achievement()
										.get(i).getProjectID()%>"
							onchange="nullChange('zxma8l<%=i%>','allCheck6')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getTeaching_achievement() != null) {%>
				onclick="Submitdata('zxma8l','<%=teached.get(o).getTeaching_achievement().size()%>','Audit6')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma9l">
			<a href="#">教材建设绩效</a><br />
			<br />
			<form name="" id="Audit7" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>教材编号</td>
						<td>教材名称</td>
						<td>获评等级</td>
						<td>本人承担任务</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>项目总分</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck7"
							<%if (teached.get(o).getTextbookConstruction() != null) {%>
							onclick="allcheckchange('zxma9l','<%=teached.get(o).getTextbookConstruction().size()%>','allCheck7')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getTextbookConstruction() != null)
								for (int i = 0; i < teached.get(o)
										.getTextbookConstruction().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getTextbookConstruction()
								.get(i).getBookID()%></td>
						<td><%=teached.get(o).getTextbookConstruction()
								.get(i).getBookName()%></td>
						<td><%=teached.get(o).getTextbookConstruction()
								.get(i).getTBLevel()%></td>
						<td><%=teached.get(o).getTextbookConstruction()
								.get(i).getUndertakeTask()%></td>
						<td><%=teached.get(o).getTextbookConstruction()
								.get(i).getTeacherID()%></td>
						<td><%=teached.get(o).getTextbookConstruction()
								.get(i).getTeacherName()%></td>
						<td><%=teached.get(o).getTextbookConstruction()
								.get(i).getProjectSumScore()%></td>
						<td><%=teached.get(o).getTextbookConstruction()
								.get(i).getSingelScore()%></td>
						<td><input type="checkbox" name="" id="zxma9l<%=i%>"
							value="<%=teached.get(o).getTextbookConstruction()
								.get(i).getTeacherID()
								+ ","
								+ teached.get(o).getTextbookConstruction()
										.get(i).getBookID()%>"
							onchange="nullChange('zxma9l<%=i%>','allCheck7')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getTextbookConstruction() != null) {%>
				onclick="Submitdata('zxma9l','<%=teached.get(o).getTextbookConstruction().size()%>','Audit7')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma10l">
			<a href="#">精品课程建设绩效</a><br />
			<br />
			<form name="" id="Audit8" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>精品课程编号</td>
						<td>精品课程名称</td>
						<td>获评等级</td>
						<td>本人承担任务</td>
						<td>是否有其他参与者</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>项目总分</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck8"
							<%if (teached.get(o).getFineCourse() != null) {%>
							onclick="allcheckchange('zxma10l','<%=teached.get(o).getFineCourse().size()%>','allCheck8')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getFineCourse() != null)
								for (int i = 0; i < teached.get(o).getFineCourse().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getCourseID()%></td>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getCourseName()%></td>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getCourseLevel()%></td>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getUndertaketaskID()%></td>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getCooperator()%></td>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getProjectSumScore()%></td>
						<td><%=teached.get(o).getFineCourse().get(i)
								.getSingelScore()%></td>
						<td><input type="checkbox" name="" id="zxma10l<%=i%>"
							value="<%=teached.get(o).getFineCourse().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o).getFineCourse().get(i)
										.getCourseID()%>"
							onchange="nullChange('zxma10l<%=i%>','allCheck8')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getFineCourse() != null) {%>
				onclick="Submitdata('zxma10l','<%=teached.get(o).getFineCourse().size()%>','Audit8')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma11l">
			<a href="#">专业建设项目申报绩效</a><br />
			<br />
			<form name="" id="Audit9" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>项目编号</td>
						<td>项目名称</td>
						<td>获评等级</td>
						<td>本人承担任务</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>项目总分</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck9"
							<%if (teached.get(o).getProfessionalProject() != null) {%>
							onclick="allcheckchange('zxma11l','<%=teached.get(o).getProfessionalProject().size()%>','allCheck9')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getProfessionalProject() != null)
								for (int i = 0; i < teached.get(o).getProfessionalProject()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getProfessionalProject()
								.get(i).getProjectId()%></td>
						<td><%=teached.get(o).getProfessionalProject()
								.get(i).getProjectName()%></td>
						<td><%=teached.get(o).getProfessionalProject()
								.get(i).getProjectLevel()%></td>
						<td><%=teached.get(o).getProfessionalProject()
								.get(i).getUndertakeTask()%></td>
						<td><%=teached.get(o).getProfessionalProject()
								.get(i).getTeacherID()%></td>
						<td><%=teached.get(o).getProfessionalProject()
								.get(i).getTeacherName()%></td>
						<td><%=teached.get(o).getProfessionalProject()
								.get(i).getProjectSumScore()%></td>
						<td><%=teached.get(o).getProfessionalProject()
								.get(i).getSingleScore()%></td>
						<td><input type="checkbox" name="" id="zxma11l<%=i%>"
							value="<%=teached.get(o).getProfessionalProject()
								.get(i).getTeacherID()
								+ ","
								+ teached.get(o).getProfessionalProject()
										.get(i).getProjectId()%>"
							onchange="nullChange('zxma11l<%=i%>','allCheck9')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getProfessionalProject() != null) {%>
				onclick="Submitdata('zxma11l','<%=teached.get(o).getProfessionalProject().size()%>','Audit9')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma12l">
			<a href="#">企业工作站和联合培养基地建设绩效</a><br />
			<br />
			<form name="" id="Audit10" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>项目编号</td>
						<td>项目名称</td>
						<td>项目类型</td>
						<td>本人承担任务</td>
						<td>单位数量</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>项目总分</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck10"
							<%if (teached.get(o).getEnterpriseWorkstation() != null) {%>
							onclick="allcheckchange('zxma12l','<%=teached.get(o).getEnterpriseWorkstation().size()%>','allCheck10')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getEnterpriseWorkstation() != null)
								for (int i = 0; i < teached.get(o)
										.getEnterpriseWorkstation().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getProjectId()%></td>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getProjectName()%></td>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getTrainingConstruLeve()%></td>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getSelfUndertakeTask()%></td>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getQuantityUnit()%></td>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getTeaherID()%></td>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getTeaherName()%></td>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getProjectSumScore()%></td>
						<td><%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getSingleScore()%></td>
						<td><input type="checkbox" name="" id="zxma12l<%=i%>"
							value="<%=teached.get(o).getEnterpriseWorkstation()
								.get(i).getTeaherID()
								+ ","
								+ teached.get(o).getEnterpriseWorkstation()
										.get(i).getProjectId()%>"
							onchange="nullChange('zxma12l<%=i%>','allCheck10')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getEnterpriseWorkstation() != null) {%>
				onclick="Submitdata('zxma12l','<%=teached.get(o).getEnterpriseWorkstation().size()%>','Audit10')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma13l">
			<a href="#">暑期课程与国际课程建设绩效</a><br />
			<br />
			<form name="" id="Audit11" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>项目编号</td>
						<td>项目名称</td>
						<td>项目内容</td>
						<td>参与课程数量</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck11"
							<%if (teached.get(o).getSummerCourse() != null) {%>
							onclick="allcheckchange('zxma13l','<%=teached.get(o).getSummerCourse().size()%>','allCheck11')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getSummerCourse() != null)
								for (int i = 0; i < teached.get(o).getSummerCourse().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getSummerCourse().get(i)
								.getProjectID()%></td>
						<td><%=teached.get(o).getSummerCourse().get(i)
								.getProjectName()%></td>
						<td><%=teached.get(o).getSummerCourse().get(i)
								.getProjectLevel()%></td>
						<td><%=teached.get(o).getSummerCourse().get(i)
								.getQuantityUnit()%></td>
						<td><%=teached.get(o).getSummerCourse().get(i)
								.getTeacherId()%></td>
						<td><%=teached.get(o).getSummerCourse().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getSummerCourse().get(i)
								.getScore()%></td>
						<td><input type="checkbox" name="" id="zxma13l<%=i%>"
							value="<%=teached.get(o).getSummerCourse().get(i)
								.getTeacherId()
								+ ","
								+ teached.get(o).getSummerCourse().get(i)
										.getProjectID()%>"
							onchange="nullChange('zxma13l<%=i%>','allCheck11')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getSummerCourse() != null) {%>
				onclick="Submitdata('zxma13l','<%=teached.get(o).getSummerCourse().size()%>','Audit11')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma14l">
			<a href="#">实践创新指导绩效</a><br />
			<br />
			<form name="" id="Audit12" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>实践创新指导项目编号</td>
						<td>项目名称</td>
						<td>结题项目等级</td>
						<td>成绩评估</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck12"
							<%if (teached.get(o).getPracticeInnovation() != null) {%>
							onclick="allcheckchange('zxma14l','<%=teached.get(o).getPracticeInnovation().size()%>','allCheck12')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getPracticeInnovation() != null)
								for (int i = 0; i < teached.get(o).getPracticeInnovation()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getPracticeInnovation().get(i)
								.getProjectID()%></td>
						<td><%=teached.get(o).getPracticeInnovation().get(i)
								.getProjectName()%></td>
						<td><%=teached.get(o).getPracticeInnovation().get(i)
								.getInnovationGuideLevel()%></td>
						<td><%=teached.get(o).getPracticeInnovation().get(i)
								.getThesisEvaluationLevel()%></td>
						<td><%=teached.get(o).getPracticeInnovation().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o).getPracticeInnovation().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getPracticeInnovation().get(i)
								.getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma14l<%=i%>"
							value="<%=teached.get(o).getPracticeInnovation().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o).getPracticeInnovation().get(i)
										.getProjectID()%>"
							onchange="nullChange('zxma14l<%=i%>','allCheck12')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getPracticeInnovation() != null) {%>
				onclick="Submitdata('zxma14l','<%=teached.get(o).getPracticeInnovation().size()%>','Audit12')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma15l">
			<a href="#">学生竞赛指导绩效</a><br />
			<br />
			<form name="" id="Audit13" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>指导竞赛编号</td>
						<td>竞赛名称</td>
						<td>竞赛等级</td>
						<td>获奖级别</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck13"
							<%if (teached.get(o).getStudentCompetitionGuide() != null) {%>
							onclick="allcheckchange('zxma15l','<%=teached.get(o).getStudentCompetitionGuide().size()%>','allCheck13')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getStudentCompetitionGuide() != null)
								for (int i = 0; i < teached.get(o)
										.getStudentCompetitionGuide().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getStudentCompetitionGuide()
								.get(i).getCompetitionID()%></td>
						<td><%=teached.get(o).getStudentCompetitionGuide()
								.get(i).getCompetitionName()%></td>
						<td><%=teached.get(o).getStudentCompetitionGuide()
								.get(i).getCompetitionType()%></td>
						<td><%=teached.get(o).getStudentCompetitionGuide()
								.get(i).getRewardLevel()%></td>
						<td><%=teached.get(o).getStudentCompetitionGuide()
								.get(i).getTeacherID()%></td>
						<td><%=teached.get(o).getStudentCompetitionGuide()
								.get(i).getTeacherName()%></td>
						<td><%=teached.get(o).getStudentCompetitionGuide()
								.get(i).getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma15l<%=i%>"
							value="<%=teached.get(o).getStudentCompetitionGuide()
								.get(i).getTeacherID()
								+ ","
								+ teached.get(o).getStudentCompetitionGuide()
										.get(i).getCompetitionID()%>"
							onchange="nullChange('zxma15l<%=i%>','allCheck13')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getStudentCompetitionGuide() != null) {%>
				onclick="Submitdata('zxma15l','<%=teached.get(o).getStudentCompetitionGuide().size()%>','Audit13')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma16l">
			<a href="#">参与学生活动绩效</a><br />
			<br />
			<form name="" id="Audit14" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>参与活动编号</td>
						<td>活动名称</td>
						<td>参与时长 单位/h</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck14"
							<%if (teached.get(o).getJoinStudentActivity() != null) {%>
							onclick="allcheckchange('zxma16l','<%=teached.get(o).getJoinStudentActivity().size()%>','allCheck14')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getJoinStudentActivity() != null)
								for (int i = 0; i < teached.get(o).getJoinStudentActivity()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getJoinStudentActivity()
								.get(i).getActivityID()%></td>
						<td><%=teached.get(o).getJoinStudentActivity()
								.get(i).getActivityName()%></td>
						<td><%=teached.get(o).getJoinStudentActivity()
								.get(i).getSumhours()%></td>
						<td><%=teached.get(o).getJoinStudentActivity()
								.get(i).getTeacherID()%></td>
						<td><%=teached.get(o).getJoinStudentActivity()
								.get(i).getTeacherName()%></td>
						<td><%=teached.get(o).getJoinStudentActivity()
								.get(i).getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma16l<%=i%>"
							value="<%=teached.get(o).getJoinStudentActivity()
								.get(i).getTeacherID()
								+ ","
								+ teached.get(o).getJoinStudentActivity()
										.get(i).getActivityID()%>"
							onchange="nullChange('zxma16l<%=i%>','allCheck14')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getJoinStudentActivity() != null) {%>
				onclick="Submitdata('zxma16l','<%=teached.get(o).getJoinStudentActivity().size()%>','Audit14')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma17l">
			<a href="#">本科生导师指导绩效</a><br />
			<br />
			<form name="" id="Audit15" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>指导编号</td>
						<td>学生数量</td>
						<td>指导年数</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck15"
							<%if (teached.get(o).getUndergraduateTutorGuidance() != null) {%>
							onclick="allcheckchange('zxma17l','<%=teached.get(o).getUndergraduateTutorGuidance().size()%>','allCheck15')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getUndergraduateTutorGuidance() != null)
								for (int i = 0; i < teached.get(o)
										.getUndergraduateTutorGuidance().size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o)
								.getUndergraduateTutorGuidance().get(i)
								.getGuidanceID()%></td>
						<td><%=teached.get(o)
								.getUndergraduateTutorGuidance().get(i)
								.getStudent_quantity()%></td>
						<td><%=teached.get(o)
								.getUndergraduateTutorGuidance().get(i)
								.getYears()%></td>
						<td><%=teached.get(o)
								.getUndergraduateTutorGuidance().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o)
								.getUndergraduateTutorGuidance().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o)
								.getUndergraduateTutorGuidance().get(i)
								.getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma17l<%=i%>"
							value="<%=teached.get(o)
								.getUndergraduateTutorGuidance().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o)
										.getUndergraduateTutorGuidance().get(i)
										.getGuidanceID()%>"
							onchange="nullChange('zxma17l<%=i%>','allCheck15')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getUndergraduateTutorGuidance() != null) {%>
				onclick="Submitdata('zxma17l','<%=teached.get(o).getUndergraduateTutorGuidance().size()%>','Audit15')"
				<%}%> />
		</div>
		<div style="display:none" id="zxma18l">
			<a href="#">校外实践指导绩效</a><br />
			<br />
			<form name="" id="Audit16" action="" method="post">
				<table
					class="table table-striped table-bordered table-hover dataTables-example"
					style="border-collapse:collapse">
					<tr>
						<td>校外实践指导项目编号</td>
						<td>校外实践指导项目</td>
						<td>项目类型</td>
						<td>单位数量</td>
						<td>实习时长</td>
						<td>教师编号</td>
						<td>教师姓名</td>
						<td>最终分数</td>
						<td>全通过&nbsp;<input type="checkbox" name="" id="allCheck16"
							<%if (teached.get(o).getOff_campusPractice() != null) {%>
							onclick="allcheckchange('zxma18l','<%=teached.get(o).getOff_campusPractice().size()%>','allCheck16')"
							<%}%> /></td>
					</tr>
					<%
						if (teached.get(o) != null)
							if (teached.get(o).getOff_campusPractice() != null)
								for (int i = 0; i < teached.get(o).getOff_campusPractice()
										.size(); i++) {
					%>
					<tr>
						<td><%=teached.get(o).getOff_campusPractice().get(i)
								.getProjectID()%></td>
						<td><%=teached.get(o).getOff_campusPractice().get(i)
								.getProjectName()%></td>
						<td><%=teached.get(o).getOff_campusPractice().get(i)
								.getProjectType()%></td>
						<td><%=teached.get(o).getOff_campusPractice().get(i)
								.getQuantityUnit()%></td>
						<td><%=teached.get(o).getOff_campusPractice().get(i)
								.getSumhours()%></td>
						<td><%=teached.get(o).getOff_campusPractice().get(i)
								.getTeacherID()%></td>
						<td><%=teached.get(o).getOff_campusPractice().get(i)
								.getTeacherName()%></td>
						<td><%=teached.get(o).getOff_campusPractice().get(i)
								.getFinalScore()%></td>
						<td><input type="checkbox" name="" id="zxma18l<%=i%>"
							value="<%=teached.get(o).getOff_campusPractice().get(i)
								.getTeacherID()
								+ ","
								+ teached.get(o).getOff_campusPractice().get(i)
										.getProjectID()%>"
							onchange="nullChange('zxma18l<%=i%>','allCheck16')" /></td>
					</tr>
					<%
						}
					%>
				</table>
			</form>
			<input type="submit" value="提交"
				<%if (teached.get(o).getOff_campusPractice() != null) {%>
				onclick="Submitdata('zxma18l','<%=teached.get(o).getOff_campusPractice().size()%>','Audit16')"
				<%}%> />
		</div>
		<input type="submit" value="注销"
			onclick="window.location.href='<%=basePath%>logout!out'" /> <input
			type="submit" value="test" onclick="test()" />
		<s:debug></s:debug>
	</div>


	<!-- Bootstrap core JavaScript ================================================== -->
	<script src="js/jquery.min.js?v=2.1.4"></script>
	<script src="js/bootstrap.min.js?v=3.3.5"></script>
	<script src="js/plugins/jeditable/jquery.jeditable.js"></script>
	<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
	<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>
	<script src="js/content.min.js?v=1.0.0"></script>
	<script src="js/plugins/iCheck/icheck.min.js"></script>
	<script src="js/plugins/sweetalert/sweetalert.min.js"></script>
	<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>
